/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.util.HashMap;
import mail.Client;

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
    }

    public int newClient(Client c) {
        if (clients.containsKey(c.getUsername())) {
            return 1;
        }

        clients.put(c.getUsername(), c);
        return 0;
    }

    public int login(String username, String password) {
        boolean a = clients.containsKey(username);
        if (!a) {
            return 1;
        }
        a = clients.get(username).isPasswordCorrect(password);
        return a ? 0 : 2;
    }

}
