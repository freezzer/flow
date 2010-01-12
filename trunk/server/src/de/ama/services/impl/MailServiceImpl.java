package de.ama.services.impl;

import de.ama.framework.mail.Email;
import de.ama.framework.mail.MailFacade;
import de.ama.services.MailService;
import de.ama.util.Environment;
import de.ama.util.Ini;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: ama
 * Date: 03.07.2009
 * Time: 09:46:56
 * To change this template use File | Settings | File Templates.
 */
public class MailServiceImpl implements MailService {

    private MailFacade facade = null;

    public MailServiceImpl() {
        String serverName = Ini.getString("mail.smtp.server",         "na", "Name des SMTP Servers");
        String serverUser  = Ini.getString("mail.smtp.server.user",    "", "User des SMTP Servers");
        String serverPwd  = Ini.getString("mail.smtp.server.user.pwd", "", "Passwort des SMTP Users");
        int serverPort  = Ini.getInt("mail.smtp.server.port", 25  , "Port des SMTP Users");

        if(serverName.equals("na")){
            throw new RuntimeException("property mail.smtp.server in System.ini is not set");
        }


        facade = new MailFacade(serverName, serverUser, serverPwd);

    }

    public void send(Email emailData) {
        try {
            facade.send(emailData);
        } catch (Exception e) {
            throw new RuntimeException("send email failed:",e);
        }

    }

    public Email getMail(int id) {
        return facade.getMail(id);
    }

    public List receiveEmails(String popUser, String popPassword, int max) {
        String server = Ini.getString("mail.pop.server","web.de","default pop server name");
        int port = Ini.getInt("mail.pop.port",-1,"default pop server port ");

        Map map = new HashMap();
        File filesDir = Environment.getHomeRelativDir("files");
        map.put("*", Ini.getString("mail.attachment.dir", filesDir.getAbsolutePath()  , "temporaeres Verzeichnis fuer eingehende mail-attachements"));

        boolean del = false;
        try {
            return facade.receiveEmails(server,port,popUser,popPassword,map,del,max);
        } catch (Exception e) {
            throw new RuntimeException("receiveMails failed:",e);
        }
    }
}
