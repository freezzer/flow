package de.ama.framework.mail;

import de.ama.db.DB;
import de.ama.framework.data.Condition;
import de.ama.framework.data.Query;
import de.ama.services.Environment;
import de.ama.util.StringDivider;
import de.ama.util.Util;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

/**
 */
public class MailFacade {

    private Session session;
    private String encoding;

    public MailFacade(String smtpServerName) {
       this(smtpServerName,"","");
    }

    public MailFacade(String smtpServerName, String user, String pwd) {
        Properties systemProperties = System.getProperties();
        systemProperties.put("mail.smtp.host", smtpServerName);
        systemProperties.put("mail.transport.protocol", "smtp");
        if(Util.isNotEmpty(pwd)){
           systemProperties.put("mail.smtp.auth", "true");
           systemProperties.put("mail.smtp.user", user);
           systemProperties.put("mail.smtp.user.pwd", pwd);
        }
        //session = Session.getDefaultInstance(systemProperties, null);
        session = Session.getInstance(systemProperties, null);
    }
           
    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String getEncoding() {
        if (Util.isEmpty(encoding)) {
            return getDefaultEncoding();
        }
        return encoding;
    }

    public String getDefaultEncoding() {
        return "iso-8859-1";
    }


    private Address[] getInternetAddresses(List adressaten) throws UnsupportedEncodingException {
        Address[] addresses = new Address[adressaten.size()];
        for (int i = 0; i < addresses.length; i++) {
            Adressat adressat = (Adressat) adressaten.get(i);
            addresses[i] = new InternetAddress(adressat.getAdresse(), adressat.getName());
        }
        return addresses;
    }

    /**
     * @param emailData Zu versendende Mail.
     */
    public void send(Email emailData) throws MessagingException, UnsupportedEncodingException {
        Message message = new MimeMessage(session);   // entspricht RFC 822
        message.setFrom(new InternetAddress(emailData.getFrom().getAdresse(), emailData.getFrom().getName()));
        if (emailData.getReplyTo() != null) {
            message.setReplyTo(new Address[]{new InternetAddress(emailData.getReplyTo().getAdresse(), emailData.getReplyTo().getName())});
        }
        message.setRecipients(Message.RecipientType.TO, getInternetAddresses(emailData.getToAdressats()));
        //message.setRecipients(Message.RecipientType.CC, getInternetAddresses(emailData.getToAdressats()));
        message.setSubject(emailData.getSubject());
        message.setSentDate(emailData.getDate());

        BodyPart messageBodyPart = new MimeBodyPart();

        String encodingInfo = "text/plain; charset=" + getEncoding();
        messageBodyPart.setContent(emailData.getMessage(), encodingInfo);
//        messageBodyPart.setText(emailData.getMessage());  // Problem hier: keine Angabe von Encoding m�glich !!!!

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        // Attachments
        Iterator ite = emailData.getAttachments().iterator();
        while (ite.hasNext()) {
            File attachment = new File((String) ite.next());
            String attachmentName = attachment.getName();
            if (!attachment.exists()) {
                BodyPart errorBodyPart = new MimeBodyPart();
                String hostName;
                try {
                    hostName = InetAddress.getLocalHost().getHostName();
                }
                catch (UnknownHostException e) {
                    hostName = "<unknown host>";
                }
                errorBodyPart.setText("*** The file <" + attachment.getAbsolutePath() + "> to be attached was not available on " + hostName + " ***");
                multipart.addBodyPart(errorBodyPart);
                continue;
            }
            messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(attachment);
            messageBodyPart.setDataHandler(new DataHandler(source));
            String originalFilename = attachmentName.substring(0, attachmentName.lastIndexOf("."));
            messageBodyPart.setFileName(originalFilename);
            multipart.addBodyPart(messageBodyPart);
        }
        message.setContent(multipart);

        Properties props = System.getProperties();
        String smtphost = props.getProperty("mail.smtp.host");
        String username = props.getProperty("mail.smtp.user");
        String userpwd = props.getProperty("mail.smtp.user.pwd");

        if(Util.isNotEmpty(userpwd)){
            Transport tr = session.getTransport();
            tr.connect(smtphost, username, userpwd);
            message.saveChanges();      // don't forget this
            tr.sendMessage(message, message.getAllRecipients());
            tr.close();
        } else {
            Transport.send(message);
        }
    }

    String toString(Address[] addresses) {
        String string = "";
        for (int i = 0; addresses != null && i < addresses.length; i++) {
            string += addresses[i] + " ";
        }
        return string;
    }


    /**
     * @param popServer     Der Server,  von dem Mails abgeholt werden sollen
     * @param popServerPort Port, auf dem der Server zu erreichen ist (-1 f�r default)
     * @param popUser       Username des Pop-Accounts
     * @param popPassword   Passwort des Pop-Accounts
     * @param fileFilter    see asEmail()
     * @return Eine Liste mit EmailIfcs
     */
    public List receiveEmails(String popServer, int popServerPort, String popUser, String popPassword, Map fileFilter, boolean deleteAtServer, int max)
            throws MessagingException, IOException {
        List emails = new ArrayList();
        Store store = null;
        Folder folder = null;
        try {
            Session session = Session.getInstance(System.getProperties(), null);
            store = session.getStore("pop3");
            store.connect(popServer, popServerPort, popUser, popPassword);
            folder = store.getDefaultFolder();
            if (folder == null) {
                throw new RuntimeException("No default folder");
            }
            folder = folder.getFolder("INBOX");
            if (folder == null) {
                throw new RuntimeException("No POP3 INBOX");
            }
            folder.open(Folder.READ_WRITE);

            Message[] messages = folder.getMessages();
            for (int i = 0; i < messages.length; i++) {
                if (!messages[i].isExpunged() && !messages[i].getFlags().contains(Flags.Flag.DELETED)) {
                    if (isReceivedMail(messages[i].getMessageNumber())) {
                        continue;
                    }
                    Email email = createIncomingEmail(messages[i], fileFilter);
                    DB.session().directUpdate(email);
                    emails.add(email);


                    if (emails.size() >= max) {
                        break;
                    }
                    if (deleteAtServer) {
                        messages[i].setFlag(Flags.Flag.DELETED, true);
                    }
                }
            }
            return emails;
        }
        finally {
            try {
                if (folder != null) {
                    folder.close(true);
                }
            }
            catch (Exception e) {
                System.out.println("Could not close folder");
                throw new RuntimeException("Could not close folder", e);
            }
            try {
                if (store != null) {
                    store.close();
                }
            }
            catch (Exception e) {
                throw new RuntimeException("Could not close store", e);
            }
        }
    }


    /**
     * Erzeugt eine Email aus einem
     *
     * @param message    Eine Message
     * @param fileFilter Map, die Suffixe auf Verzeichnisse (beides Strings!) abbildet.
     *                   Das Suffix ist ohne Punkt anzugeben. F�r Files, die kein Suffix oder "" als Suffix haben, kann als Suffix "" angegeben werden.
     *                   Als Joker ist "*" angebbar. Der Joker greift immer dann, wenn kein anderer Entrag greift .
     */
    private Email createIncomingEmail(Message message, Map fileFilter) throws MessagingException, IOException {
        List cache = new ArrayList();
        // Cc, To usw. kopieren
        Email email = new Email();
        email.setFrom(getOrCreateAdressat(message.getFrom()[0], cache));
        email.setSubject(message.getSubject());
        email.setMsgNum(message.getMessageNumber());

        Address[] to = message.getRecipients(Message.RecipientType.TO);
        for (int i = 0; i < to.length; i++) {
            email.addTo(getOrCreateAdressat(to[i], cache));
        }
        Address[] cc = message.getRecipients(Message.RecipientType.TO);
        for (int i = 0; i < cc.length; i++) {
            email.addTo(getOrCreateAdressat(cc[i], cache));
        }
        Address[] replyTo = message.getReplyTo();
        if (replyTo != null && replyTo.length > 0) {
            email.setReplyTo(getOrCreateAdressat(replyTo[0], cache));
        }
        email.setDate(message.getSentDate());
        // Die Messageparts in Messagetext und attachments zerlegen
        String messageString = "";
        List list = getParts(message);
        for (int i = 0; i < list.size(); i++) {
            Part part = (Part) list.get(i);
            if (part.getFileName() == null) {
                // MessageText
                if (part.getContentType().startsWith("text")) {
                    messageString += part.getContent();
                }
            } else {
                // Attachments filtern und ggf. speichern
                String fileName = part.getFileName();
                StringDivider fileNameDivider = new StringDivider(fileName, ".", true);
                String suffix = fileNameDivider.ok() ? fileNameDivider.post() : "";
                String destinationDir = (String) fileFilter.get(suffix);
                //  Ist ein default vorhanden?
                if (destinationDir == null) {
                    destinationDir = (String) fileFilter.get("*");
                }
                // Wenn kein Eintrag in der Map gefunden, wird das Attachment ignoriert, ansonsten am entsprechenden Ort gespeichert
                if (destinationDir != null) {
                    File destination = null;
                    while (destination == null || destination.exists()) {
                        if (fileNameDivider.ok()) {
                            destination = new File(destinationDir, fileNameDivider.pre() + "." + System.currentTimeMillis() + "." + fileNameDivider.post());
                        } else {
                            destination = new File(destinationDir, fileName + System.currentTimeMillis());
                        }
                    }
                    try {
                        destination.createNewFile();
                        FileOutputStream fos = new FileOutputStream(destination);
                        InputStream is = (InputStream) part.getContent();
                        byte[] buf = new byte[1024];
                        int len;
                        while ((len = is.read(buf)) > 0) {
                            fos.write(buf, 0, len);
                        }
                        is.close();
                        fos.close();
                        email.addAttachment(destination.getName());
                    } catch (Exception e) {
                        email.addAttachment("ERROR FILE_NAME: " + destination.getAbsolutePath());
                    }
                }
            }
        }
        email.setMessage(messageString);
        return email;
    }


    /**
     * Erzeugt aus einer Address einen Adressat. Die Address muss vom Typ InternetAddress sein.
     * Es werden alle Adressat Objecte in der Datenbank abgelegt .
     *
     * @param address eine InternetAddress.
     * @throws IllegalArgumentException wenn die Address keine InternetAddress ist.
     */

    private Adressat getOrCreateAdressat(Address address, List cache) {
        if (!(address instanceof InternetAddress)) {
            throw new IllegalArgumentException("The address is of unsupported type " + address.getClass().getName() + ".");
        }
        InternetAddress internetAddress = (InternetAddress) address;
        Adressat addr = new Adressat(internetAddress.getAddress(),internetAddress.getPersonal(),Adressat.EMAIL);

        for (int i = 0; i < cache.size(); i++) {
            Adressat a = (Adressat) cache.get(i);
            if(addr.equals(a)){
                return a;
            }
        }

        Condition c1 = new Condition("adresse", de.ama.db.Query.EQ, internetAddress.getAddress());
        Condition c2 = new Condition("name", de.ama.db.Query.EQ, internetAddress.getPersonal());
        Condition c3 = new Condition("typ", de.ama.db.Query.EQ, Adressat.EMAIL);

        addr = (Adressat) Environment.getPersistentService().getObject(new Query(Adressat.class, c1.and(c2).and(c3)),false);

        if(addr==null){
            addr = new Adressat(internetAddress.getAddress(),internetAddress.getPersonal(),Adressat.EMAIL);
            DB.session().setObject(addr);
            cache.add(addr);
        }

        return addr;
    }

    /**
     * L�st alle Schachtelungen durch Multiparts auf und sammelt die einzelnen Parts inklusive des �bergebenen Parts
     */
    private static List getParts(Part part) throws MessagingException, IOException {
        List parts = new ArrayList();
        Object content = part.getContent();
        if (content instanceof Multipart) {
            Multipart multipart = (Multipart) content;
            for (int i = 0; i < multipart.getCount(); i++) {
                parts.addAll(getParts(multipart.getBodyPart(i)));
            }
        } else {
            parts.add(part);
        }
        return parts;
    }


    public Email getMail(int nummer) {
        return (Email) DB.session().getObject(new de.ama.db.Query(Email.class, "msgNum", de.ama.db.Query.EQ, new Integer(nummer)));
    }

    public boolean isReceivedMail(int nummer) {
        int objectCount = DB.session().getObjectCount(new de.ama.db.Query(Email.class, "msgNum", de.ama.db.Query.EQ, new Integer(nummer)));
        if (objectCount > 0)
            System.out.println("Mail " + nummer + " is allready received.");
        return objectCount > 0;
    }

//    public static void main(String[] args) {
//        Map map = new HashMap();
//        map.put("bmp", "c:/mailtest");
//
//        try {
//
//            List mails = receiveEmails("dbhcom01.dbh.de", -1, "vera", ".vera", map, false, 100);
//            for (int i = 0; i < mails.size(); i++) {
//                Email email = (Email) mails.get(i);
//                DB.session().setObject(email);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }
//
//        DB.session().commit();
//
//
//    }


}
