package chat;

import client.Client;
import main.Main;
import java.io.IOException;

import static input.AccesFichier.enregistrer;

public class Discussion extends Thread {

    String path;
    Client client;
    private boolean loop = true;

    public Discussion(Client client) {
        this.client = client;
        this.path = Main.data+this.client.getGroupe();

        start();
    }

    @Override
    public void run() {
        String newMessage;
        String value;
        while (loop) {
            try {
                newMessage = client.getIn().readUTF();
                value = client.getNom()+":: "+newMessage;
                String file = client.getGroupe();
                enregistrer(value,file);
            } catch (IOException e) {
                Main.clients.remove(client);
            }
        }
    }
}
