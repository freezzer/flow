package de.ama.services;

import de.ama.framework.mail.Email;

import java.util.List;

/**
 * 
 */
public interface MailService {
    public static final String NAME = "MailService";

    public  void send(Email emailData);
    public  Email getMail(int id);
    public  List receiveEmails(String popUser, String popPassword,int max);


}