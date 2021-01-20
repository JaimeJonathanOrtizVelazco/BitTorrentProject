import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static final int PORT = 4201;

    public static void main(String[] args) {
        TrackerSeedersServer seedersServer= new TrackerSeedersServer();
        seedersServer.start();
        TrackerLeecherServer leecherServer = new TrackerLeecherServer();
        leecherServer.start();
    }
}
