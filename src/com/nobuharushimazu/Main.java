package com.nobuharushimazu;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {
	    try (ServerSocket serverSocket = new ServerSocket(6000)) {
            while (true) {
                Socket socket = serverSocket.accept();
                new Echoer(socket).start();
            }

        } catch (IOException e) {
            System.out.println("Server exception: " + e.getMessage());
        }
    }
}
