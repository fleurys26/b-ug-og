package main;
import aff.Affichage;
import message.AfficherMessage;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws Exception{
        Socket socket;

        String ip = JOptionPane.showInputDialog("Entrer votre adresse IP:");
        socket = new Socket(ip,8989);

        DataOutputStream mamoka = new DataOutputStream(socket.getOutputStream());

        String nom = JOptionPane.showInputDialog("Entrer votre nom:");
        mamoka.writeUTF(nom);
        String anarana = nom;

        String groupe = JOptionPane.showInputDialog("Entrer le nom du groupe:");
        mamoka.writeUTF(groupe);
        String titre= groupe;

        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

        JTextArea textArea = new JTextArea();

        new AfficherMessage(in,textArea);

        Affichage a= new Affichage(titre,dos, textArea);
        JTextField t = new JTextField();
        t.setText(anarana);
        t.setBounds(50,10,50,15);
        a.add(t);
    }
}

