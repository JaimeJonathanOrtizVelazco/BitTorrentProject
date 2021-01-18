package com.corporation.globevery;

import java.io.Serializable;
import java.util.ArrayList;

public class TorrentData implements Serializable {
    String IPAddress;
    int PORT;
    ArrayList<String> Files;

    public TorrentData(String IPAddress, int PORT) {
        this.IPAddress = IPAddress;
        this.PORT = PORT;
        this.Files = new ArrayList<>();
    }

    public TorrentData() {

    }

    public String getIPAddress() {
        return IPAddress;
    }

    public void setIPAddress(String IPAddress) {
        this.IPAddress = IPAddress;
    }

    public int getPORT() {
        return PORT;
    }

    public void setPORT(int PORT) {
        this.PORT = PORT;
    }

    public ArrayList<String> getFiles() {
        return Files;
    }

    public void setFiles(ArrayList<String> files) {
        Files = files;
    }
}
