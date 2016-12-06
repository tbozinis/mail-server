/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mail;

/**
 *
 * @author admin
 */
public class Answer {
    public enum Type{
        NEW_MAIL_SEND,
        GET_MAILS_RECEIVING,
        NEW_CLIENT_ADDED,
        ACCEPED,
        WRONG_USERNAME,
        WRONG_PASSWORD
    }
    public Type type;

    public Answer(Type type) {
        this.type = type;
    }
    
    
}
