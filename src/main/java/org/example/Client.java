package org.example;

import java.io.*;
import java.net.Socket;

public class Client {
    ChatWindow chatWindow;

    BufferedReader reader;
    BufferedWriter writer;

    public Client(ChatWindow chatWindow) {
        this.chatWindow = chatWindow;
    }
    public void run() {
        try {
            Socket socket = new Socket("localhost", 5555);
            chatWindow.setMessageLabel("Successfully connected.");

            InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
            writer = new BufferedWriter(outputStreamWriter);
            reader = new BufferedReader(inputStreamReader);

            Thread thread = new Thread(() -> listen());
            thread.start();

        } catch (IOException e) {
            System.out.println(e.getMessage());
            chatWindow.setMessageLabel("Unable to connect.");
        }
    }

    public void listen() {
        while (true) {
            try {
                String message = reader.readLine();
                chatWindow.makeLabel(message);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void send(String message) {
        try {
            writer.write(message);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
