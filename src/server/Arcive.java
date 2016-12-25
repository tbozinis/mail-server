/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.util.ArrayList;
import java.util.HashMap;
import mail.Client;
import mail.Mail;

/**
 *
 * @author admin
 */
public class Arcive {

    private HashMap<String, Client> clients;

    public Arcive(HashMap<String, Client> clients) {
        this.clients = clients;
    }

    public Arcive() {
        this.clients = new HashMap<>();
    }

    public int newClient(Client c) {
        if (clients.containsKey(c.getUsername())) {
            return 1;
        }

        clients.put(c.getUsername(), c);
        return 0;
    }

    public Client getClient(String username) {
        return clients.get(username);
    }

    public int login(String username, String password) {
        boolean a = clients.containsKey(username);
        if (!a) {
            return 1;
        }
        a = clients.get(username).isPasswordCorrect(password);
        return a ? 0 : 2;
    }

    public ArrayList<String> getMails(String sender) {
        Client c = clients.get(sender);
        ArrayList<String> ret= new ArrayList<>();
        
        for(mail.Mail m : c.getArcive())
        {
            String str = Integer.toString(m.getId())+(!m.isOpened()?" <U> ":"")+". "+m.getSender()+" - "+m.getSubject();
            ret.add(str);
        }
        
        return ret;
    }

    public int newMail(Mail mail) {
        int i = (clients.containsKey(mail.getReceiver()))?0:1;
        if(i==1) return 1;
        clients.get(mail.getReceiver()).addMail(mail);
        return 0;
    }

}
