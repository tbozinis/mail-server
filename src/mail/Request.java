/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mail;

import java.io.Serializable;
import mail.Client;
import mail.Mail;

/**
 *
 * @author admin
 */
public class Request implements Serializable{
    private static final long serialVersionUID = 2L;
    public enum Type{
        NEW_MAIL,
        GET_MAILS,
        NEW_CLIENT,
        LOGIN
    }
    public Type type;

    public Type getType() {
        return type;
    }
    
    private String sender;

    public String getSender() {
        return sender;
    }
        
    private Mail mail;

    public Mail getMail() {
        return mail;
    }
    
    private int mailIndex;

    public int getMailIndex() {
        return mailIndex;
    }
    
    private Client client;

    public Client getClient() {
        return client;
    }
    
    private String passwored;

    public String getPasswored() {
        return passwored;
    }
           
}
