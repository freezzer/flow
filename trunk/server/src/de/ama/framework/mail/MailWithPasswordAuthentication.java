package de.ama.framework.mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import java.util.Properties;

public class MailWithPasswordAuthentication {
	public static void main(String[] args) throws MessagingException {
		new MailWithPasswordAuthentication().run();
	}

	private void run() throws MessagingException {
		Message message = new MimeMessage(getSession());

		message.addRecipient(RecipientType.TO, new InternetAddress("andreas@marochow.de"));
		message.addFrom(new InternetAddress[] { new InternetAddress("andreas@marochow.de") });

		message.setSubject("the subject");
		message.setContent("the body comes here", "text/plain");

		Transport.send(message);
	}

	private Session getSession() {
		Authenticator authenticator = new Authenticator();

		Properties properties = new Properties();
		properties.setProperty("mail.smtp.submitter", authenticator.getPasswordAuthentication().getUserName());
		properties.setProperty("mail.smtp.auth", "true");

		properties.setProperty("mail.smtp.host", "marochow.de");
		properties.setProperty("mail.smtp.port", "25");

		return Session.getInstance(properties, authenticator);
	}

	private class Authenticator extends javax.mail.Authenticator {
		private PasswordAuthentication authentication;

		public Authenticator() {
			String username = "web545p1";
			String password = "modrow";
			authentication = new PasswordAuthentication(username, password);
		}

		protected PasswordAuthentication getPasswordAuthentication() {
			return authentication;
		}
	}
}
