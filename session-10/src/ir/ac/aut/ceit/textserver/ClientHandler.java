package ir.ac.aut.ceit.textserver;

import javax.swing.*;
import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private final Socket socket;
    private String text;

    public ClientHandler(Socket socket) {
        this.socket = socket;
        this.text = "";
    }

    @Override
    public void run() {
        try {
            DataInputStream inputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

            while(true) {
                int sz = inputStream.available();
                if(sz > 0) {
                    String input;
                    input = inputStream.readUTF();
                    System.out.println("From Thread " + Thread.currentThread().getName() +
                            " get input (" + input + ") from client.");
                    if(input.toLowerCase().equals("over")) {
                        break;
                    }
                    if(text.length() > 0) {
                        text += "\n$$ ";
                    }
                    text += input;
                    outputStream.writeUTF(text);
                }
            }
            socket.close();
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
