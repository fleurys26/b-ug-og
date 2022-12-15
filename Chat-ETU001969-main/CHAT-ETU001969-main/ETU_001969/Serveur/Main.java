package main;


import client.Client;
import chat.Discussion;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static final String data = "D:\\ETU 1845\\Message\\";
    public static List<Client> clients = new ArrayList<>();
    public static final int port = 8989;
    public static boolean loop = true;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server ready!!");
        Socket socket;

        while (loop) {
            socket = serverSocket.accept();
            DataInputStream in = new DataInputStream(socket.getInputStream());

            String nom = in.readUTF();
            String groupe = in.readUTF();

            Client client = new Client();
            client.setIn(new DataInputStream(socket.getInputStream()));
            client.setOs(new ObjectOutputStream(socket.getOutputStream()));
            client.setNom(nom);
            client.setGroupe(groupe.toUpperCase());

            if(!ifListenGroupe(client)) {
                new chat.SendMessage(client);
            }

            clients.add(client);
            new Discussion(client);
        }

    }

    private static boolean ifListenGroupe(Client client) {
        for (Client c : clients) {
            if(c.getGroupe().equals(client.getGroupe())) {
                return true;
            }
        }

        return false;
    }
}

