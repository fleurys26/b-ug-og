package listener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;

public class Ecouteur implements ActionListener {
    JTextField jt;
    DataOutputStream out;

    public Ecouteur(JTextField jt, DataOutputStream out) {
        this.jt = jt;
        this.out = out;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String message = jt.getText();
        try {
            out.writeUTF(message);
            out.flush();
            jt.setText("");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}

