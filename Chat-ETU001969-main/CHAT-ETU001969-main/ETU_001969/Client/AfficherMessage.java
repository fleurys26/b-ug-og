package message;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;

public class AfficherMessage extends Thread{

    ObjectInputStream input;
    JTextArea affichage;
    private int nbLu=0;
    private boolean loop = true;

    public AfficherMessage(ObjectInputStream input, JTextArea message) {
        this.input = input;
        this.affichage = message;

        start();
    }

    private void showMessage(String[][] message) {
        for (int i=nbLu; i<message.length; i++) {
            this.affichage.setText(this.affichage.getText()+"\n"+message[i][0]+": "+message[i][1]);
        }
    }

    @Override
    public void run() {
        String[][] allMaessage;
        while (loop) {
            try {
                allMaessage = (String[][]) input.readObject();
                showMessage(allMaessage);
                nbLu = allMaessage.length;
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

