package com.corporation.globevery;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class TrackerClient extends Thread {
    private final Socket socket;
    private DataOutputStream DataOutput;
    private DataInputStream DataInput;
    private ObjectOutputStream ObjectOutput;
    private ObjectInputStream ObjectInput;
    private final static ArrayList<TorrentData> TorrentList = new ArrayList<>();

    public TrackerClient(Socket socket) {
        this.socket = socket;
        try {
            DataOutput = new DataOutputStream(socket.getOutputStream());
            DataInput = new DataInputStream(socket.getInputStream());
            ObjectOutput = new ObjectOutputStream(socket.getOutputStream());
            ObjectInput = new ObjectInputStream(socket.getInputStream());
        } catch (Exception ex) {
            System.out.println(ex);
            closeSocket();
        }
    }

    @Override
    public void run() {
        try {
            ArrayList<TorrentData> lechers = new ArrayList<>();
            TorrentData client = new TorrentData();
            client = (TorrentData) ObjectInput.readObject();
            TorrentList.add(client);
            ObjectOutput.writeObject(TorrentList);
            while(true){

            }
        } catch (Exception exception) {
            System.out.println(exception);
            closeSocket();
        }
    }
    public void closeSocket(){
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
