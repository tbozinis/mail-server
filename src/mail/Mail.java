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
public class Mail {

    private String sender;
    private String receiver;
    private String subject;
    private String mainBody;
    
    private boolean opened;

    public Mail(String sender, String receiver, String subject, String mainBody) {
        this.sender = sender;
        this.receiver = receiver;
        this.subject = subject;
        this.mainBody = mainBody;
        this.opened = false;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getSubject() {
        return subject;
    }

    public String getMainBody() {
        return mainBody;
    }

    public boolean isOpened() {
        return opened;
    }
    
}
