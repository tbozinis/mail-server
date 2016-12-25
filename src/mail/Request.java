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
public class Request implements Serializable {

    private static final long serialVersionUID = 2L;

    public enum Type {
        NEW_MAIL,
        GET_MAILS,
        GET_MAIL,
        DELETE_MAIL,
        NEW_CLIENT,
        LOGIN,
        GET_CLIENT
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

    private String password;

    public String getPasswored() {
        return password;
    }

    public Request(String sender, Client client, Type type) {
        this.sender = sender;
        this.client = client;
        this.type = type;
    }

    public Request(String sender, String password, Type type) {
        this.sender = sender;
        this.password = password;
        this.type = type;
    }

    public Request(Type type) {
        this.type = type;
    }

    public Request(Type type, String sender) {
        this.type = type;
        this.sender = sender;
    }

    public Request(Type type, String sender, Mail mail) {
        this.type = type;
        this.sender = sender;
        this.mail = mail;
    }

    public Request(Type type, String sender, int mailIndex) {
        this.type = type;
        this.sender = sender;
        this.mailIndex = mailIndex;
    }

    public void setMail(Mail mail) {
        this.mail = mail;
    }

}
