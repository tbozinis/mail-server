/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mail;

import java.io.Serializable;

/**
 *
 * @author admin
 */
public class Answer implements Serializable{
    private static final long serialVersionUID = 1L;
    public enum Type{
        NEW_MAIL_SEND,
        GET_MAILS_RECEIVING,
        NEW_CLIENT_ADDED,
        ACCEPED,
        WRONG_USERNAME,
        WRONG_PASSWORD,
        CLIENT,
        WRONG_ID,
        MAIL, MAIL_DELETED
    }
    public Type type;
    
    private Object obj;

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
    
    public Type getType() {
        return type;
    }

    public Answer(Type type) {
        this.type = type;
    }
    
    public Answer(Type type, Object obj) {
        this.type = type;
        this.obj = obj;
    }
}
