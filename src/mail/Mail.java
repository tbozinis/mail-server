/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mail;

import java.io.Serializable;
import java.util.Scanner;

/**
 *
 * @author admin
 */
public class Mail implements Serializable {

    private static final long serialVersionUID = 1L;
    private String sender;
    private String receiver;
    private String subject;
    private String mainBody;

    private int id;

    private boolean opened;

    public void setId(int id) {
        this.id = id;
    }
    
    public void opened() {
        opened = true;
    }

    public Mail(String sender, String receiver, String subject, String mainBody) {
        this.sender = sender;
        this.receiver = receiver;
        this.subject = subject;
        this.mainBody = mainBody;
        this.id = id;
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

    public static Mail creatMail(String send, Scanner s) {
        System.out.println(">> SENDER: " + send);
        System.out.print(">> RECIVER: ");
        String rec = s.nextLine();
        System.out.print(">> SUBJECT: ");
        String subj = s.nextLine();
        System.out.println(">> MAIN BODY");
        String mb = s.nextLine();
        Mail m = new Mail(send, rec, subj, mb);
        return m;
    }

    public int getId() {
        return this.id;
    }
    
    @Override
    public String toString() {
        String ret = "";
        ret+="FROM: "+this.sender;
        ret+="\nSUBJECT: "+this.subject;
        ret+="\n\n"+this.mainBody;
        return ret;
    }
    
}
