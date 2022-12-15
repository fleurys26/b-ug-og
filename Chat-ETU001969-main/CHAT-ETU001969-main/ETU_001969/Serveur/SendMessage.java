package chat;

import client.Client;
import main.Main;

import java.io.File;
import java.io.IOException;

import static input.AccesFichier.countLines;
import static input.AccesFichier.getData;
import static input.AccesFichier.creer;


public class SendMessage extends Thread {

    String filePath;
    int nbActu = 0;
    String[][] allMessage;
    private boolean loop = true;

    public SendMessage(Client client) {
        filePath = Main.data+client.getGroupe()+".txt";
        try {
            creer(new File(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        start();
    }

    private void sendMessage(Client client) throws Exception {
        allMessage = getData(filePath);
        client.getOs().writeObject(allMessage);
        client.getOs().flush();
    }

    @Override
    public void run() {
        while (loop) {
            try {
                int nbtemp = countLines(filePath);
                if(nbtemp > nbActu) {
                    for (Client c: Main.clients) {
                        if(filePath.contains(c.getGroupe()))  {
                            sendMessage(c);
                        }
                    }
                }
                nbActu = nbtemp;

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
