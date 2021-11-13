package com.nobuharushimazu;

import java.net.Socket;
import java.io.*;

public class Echoer extends Thread{
    private Socket socket;

    public Echoer(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            while (true) {
                String echoString = input.readLine();
                System.out.println("Received client input: " + echoString);
                if (echoString.equals("exit")) {
                    System.out.println("Client disconnected");
                    break;
                }
                // test for timeout
//                try {
//                    Thread.sleep(15000);
//                } catch (InterruptedException e) {
//                    System.out.println("Thread Interrupted");
//                }

                output.println(echoString);
            }
        } catch (IOException e) {
            System.out.println("Oops: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException ignored) {}
        }
    }
}
