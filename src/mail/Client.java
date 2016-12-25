/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mail;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;
    private String username;
    private String password;
    private ArrayList<Mail> arcive;

    public int getNextId() {
        return arcive.size();
    }

    public Client(String username, ArrayList<Mail> arcive) {
        this.username = username;
        this.arcive = arcive;
    }

    public Client(String username) {
        this.username = username;
        this.arcive = new ArrayList<>();
    }

    public Client(String username, String password) {
        this.username = username;
        this.password = password;
        this.arcive = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public ArrayList<Mail> getArcive() {
        return arcive;
    }

    public void addMail(Mail mail) {
        mail.setId(arcive.size());
        arcive.add(mail);
    }

    public ArrayList<Mail> getUnreceivedMails(int mailIndex) {
        ArrayList<Mail> r = new ArrayList<>();
        for (int i = mailIndex; i < arcive.size(); i++) {
            r.add(arcive.get(i));
        }
        return r;
    }

    public boolean isPasswordCorrect(String pass) {
        return password.equals(pass);
    }

    public Mail getMail(int mailI) {
        return arcive.get(mailI);
    }

    public int deleteMail(int index) {
        if (index >= arcive.size() || index < 0) {
            return 1;
        } else {
            arcive.remove(index);
            return 0;
        }
    }
}
