/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import mail.Request;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLServerSocket;
import mail.*;

/**
 *
 * @author Mpozinis Theodoros
 */
public class Server {

    private Arcive arcive;

    private ServerSocket server;
    private Socket conection;

    public Server() throws IOException {
        server = new ServerSocket(3000, 10);
        this.arcive=new Arcive();
    }

    public void run() {
        try {
            conection = server.accept();

            Thread tempThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        newConection(conection);
                    } catch (IOException ex) {
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

            tempThread.start();
            System.out.println("thread started...");

        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //kainourio request
    private void newConection(Socket s) throws IOException, ClassNotFoundException {
        ObjectOutputStream out;
        ObjectInputStream in;
        System.out.println("Connection received from " + s.getInetAddress().getHostName());
        out = new ObjectOutputStream(s.getOutputStream());
        out.flush();
        in = new ObjectInputStream(s.getInputStream());

        Request r = (Request) in.readObject();

        //analoga me to req
        switch (r.getType()) {
            case NEW_MAIL:
                //to bazoume stin lista tou mexri na to zitisei
                ///clients.get(r.getMail().getReceiver()).addMail(r.getMail());
                break;
            case GET_MAILS:
                ///ArrayList<Mail> mails = clients.get(r.getSender()).getUnreceivedMails(r.getMailIndex());
                //sendMails(out, mails);
                break;
            case NEW_CLIENT:
                arcive.newClient(r.getClient());
                System.out.println("- New client (" + r.getSender() + " )added...");
                break;
            case LOGIN:
                int ret = arcive.login(r.getSender(), r.getPasswored());
                Answer a = null;
                switch (ret) {
                    case 0:
                        a = new Answer(Answer.Type.ACCEPED);
                        break;
                    case 1:
                        a = new Answer(Answer.Type.WRONG_USERNAME);
                        break;
                    case 2:
                        a = new Answer(Answer.Type.WRONG_PASSWORD);
                        break;
                }
                out.writeObject(a);
        }

    }

    //stelnei sto out ta mails pou zitise
    private void sendMails(ObjectOutputStream out, ArrayList<Mail> mails) {
        try {
            out.writeObject(mails);
        } catch (IOException ex) {
            System.out.println("smth wend wrong...\n" + ex.toString());
        }
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        Server celia = new Server();

        while (true) {
            celia.run();
            Thread.sleep(500);
        }
    }
}
