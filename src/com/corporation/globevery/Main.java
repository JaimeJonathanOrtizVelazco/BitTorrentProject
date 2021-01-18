package com.corporation.globevery;

import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static final int PORT = 4200;
    public static void main(String[] args) {
        ServerSocket server = null;
        Socket socket = null;
        try {
            server = new ServerSocket(PORT);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        while (true) {
            try {
                socket = server.accept();
                ((TrackerClient) new TrackerClient(socket)).start();
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }
}
