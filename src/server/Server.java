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
    private int port;

    public Server(int port) throws IOException {
        server = new ServerSocket(port, 10);
        this.arcive = new Arcive();
        this.port = port;
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
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

            tempThread.start();

        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //kainourio request
    private void newConection(Socket s) throws IOException, ClassNotFoundException, InterruptedException {
        ObjectOutputStream out;
        ObjectInputStream in;
        System.out.println("Connection received from " + s.getInetAddress().getHostName());
        out = new ObjectOutputStream(s.getOutputStream());
        out.flush();
        in = new ObjectInputStream(s.getInputStream());

        Request r = (Request) in.readObject();

        Answer a;
        out.flush();
        //analoga me to req
        switch (r.getType()) {
            case NEW_MAIL:
                int i = arcive.newMail(r.getMail());
                if (i == 1) {
                    a = new Answer(Answer.Type.WRONG_USERNAME);
                } else {
                    a = new Answer(Answer.Type.NEW_MAIL_SEND);
                }
                out.writeObject(a);
                System.out.println("done...");
                break;
            case GET_MAILS:
                System.out.println("REQUEST GET_MAILS");
                ArrayList<String> al = arcive.getMails(r.getSender());
                a = new Answer(Answer.Type.GET_MAILS_RECEIVING);
                a.setObj(al);
                out.writeObject(a);
                System.out.println("done...");
                break;
            case NEW_CLIENT:
                System.out.println(">>REQUEST NEW_CLIENT");
                Client c = r.getClient();
                if (arcive.newClient(c) == 1) {
                    a = new Answer((Answer.Type.WRONG_USERNAME));
                    out.writeObject(a);
                    System.out.println("client already exists");
                } else {
                    a = new Answer((Answer.Type.ACCEPED));
                    out.writeObject(a);
                    System.out.println("- New client (" + r.getSender() + ")added...");
                }
                System.out.println("done...");
                break;
            case LOGIN:
                System.out.println(">>REQUEST LOGIN");
                int ret = arcive.login(r.getSender(), r.getPasswored());
                a = null;
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
                System.out.println("done...");
                break;
            case GET_CLIENT:
                System.out.println(">>REQUEST GET_CLIENT");
                c = arcive.getClient(r.getSender());
                a = new Answer(Answer.Type.CLIENT, c);
                out.writeObject(a);
                System.out.println("done...");
                break;
            case GET_MAIL:
                System.out.println(">>REQUEST GET_MAIL");

                c = arcive.getClient(r.getSender());
                Mail m = c.getMail(r.getMailIndex());
                if (m == null) {
                    a = new Answer(Answer.Type.WRONG_ID);
                } else {
                    a = new Answer(Answer.Type.MAIL, m);
                    arcive.getClient(r.getSender()).getArcive().get(r.getMailIndex()).opened();
                }
                out.writeObject(a);
                break;
            case DELETE_MAIL:
                System.out.println(">>REQUEST DELETE_MAIL");
                i = arcive.getClient(r.getSender()).deleteMail(r.getMailIndex());
                if (i == 1) {
                    a = new Answer(Answer.Type.WRONG_ID);
                } else {
                    a = new Answer(Answer.Type.MAIL_DELETED);
                }
                out.writeObject(a);
                break;
        }
        System.out.println("connection ended...");
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        try {
            int p = Integer.parseInt(args[0]);

            Server celia = new Server(p);

            celia.makeCnM();

            while (true) {
                celia.run();
                Thread.sleep(500);
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

    }

    //the clients and their mails
    protected void makeCnM() {
        this.arcive.newClient(new Client("celia", "2010"));
        this.arcive.newClient(new Client("theodor", "1916"));

        for (int m = 0; m < 3; m++) {
            Mail mail = new Mail("theodor", "celia", "test mail " + Integer.toString(m), "test mail " + Integer.toString(m));
            this.arcive.newMail(mail);
            mail = new Mail("celia", "theodor", "test mail " + Integer.toString(m), "test mail " + Integer.toString(m));
            this.arcive.newMail(mail);
        }

    }

}
