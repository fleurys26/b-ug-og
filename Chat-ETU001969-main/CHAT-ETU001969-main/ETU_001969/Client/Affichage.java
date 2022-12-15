package aff;

import listener.Ecouteur;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;

public class Affichage extends JFrame{
    JTextArea allMessage;
    JTextField input = new JTextField();
    JButton button = new JButton("Send");
    JScrollPane scrollPane = new JScrollPane();
    public Affichage(String title, DataOutputStream out, JTextArea textArea) throws Exception {
        allMessage = textArea;

        initFrame(title);

        allMessage.setEditable(false);
        scrollPane.setViewportView(allMessage);
        scrollPane.setPreferredSize(new Dimension(350,300));
        scrollPane.setBounds(50,50,(int) scrollPane.getPreferredSize().getWidth(),(int)scrollPane.getPreferredSize().getHeight());
        add(scrollPane);

        input.setBounds(50,400,250,45);
        add(input);
        button.setBounds(350,400,100,45);
        add(button);
        button.addActionListener(new Ecouteur(input,out));

        setVisible(true);
    }

    public void initFrame(String title) {
        setTitle(title);
        setSize(500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
    }
}
