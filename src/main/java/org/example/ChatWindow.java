package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ChatWindow {
    private JTextField myUsername;
    private JPanel chatWindowPanel;
    private JTextField chatTextField;
    private JButton sendButton;
    private JLabel messageLabel;
    private JPanel chatPanel;

    private JFrame jFrame;


    Client client;

    public ChatWindow() {
        jFrame = new JFrame("Chat");
        jFrame.setLocation(300,300);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setContentPane(chatPanel);
        jFrame.pack();
        jFrame.setSize(500,500);
        jFrame.setVisible(true);

        chatWindowPanel.setLayout(new BoxLayout(chatWindowPanel, BoxLayout.Y_AXIS));

        client = new Client(this);
        client.run();
        sendButton.addActionListener((e) -> send());
        /*sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                send();
            }
        });*/
        chatTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == '\n') {
                    send();
                }
            }
        });
    }

    public void send() {
        if(!myUsername.getText().equals("") && !chatTextField.getText().equals("")) {
            String message = myUsername.getText() + ": " + chatTextField.getText();
            client.send(message);
            makeLabel(message);
            chatTextField.setText("");
        }
    }

    public void setMessageLabel(String message) {
        messageLabel.setText(message);
    }

    public void makeLabel(String message) {
        JLabel jLabel = new JLabel();
        jLabel.setHorizontalTextPosition(JLabel.LEFT);
        jLabel.setText(message);
        chatWindowPanel.add(jLabel);
        chatWindowPanel.revalidate();
    }
}
