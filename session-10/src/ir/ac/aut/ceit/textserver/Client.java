package ir.ac.aut.ceit.textserver;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client implements Runnable {
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private Socket socket;

    public Client() {
        connectServer();
    }

    public void connectServer() {
        try {
            socket = new Socket("127.0.0.1", 8888);
            inputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            outputStream = new DataOutputStream(socket.getOutputStream());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(":: Enter Your String:\n>> ");
            try {
                String entered = scanner.nextLine();
                if(entered.toLowerCase().equals("over")) return;
                outputStream.writeUTF(entered);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                int sz = inputStream.available();
                if(sz > 0) {
                    String resp;
                    resp = inputStream.readUTF();
                    System.out.println(":: Server Response:\n$$ " + resp);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
