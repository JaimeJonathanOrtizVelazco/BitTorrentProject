import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class TrackerSeedersServer extends Thread {
    private static final int PORT = 4201;
    private static ServerSocket server;

    public TrackerSeedersServer() throws IOException {
        server = new ServerSocket(PORT);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket socket = server.accept();
                System.out.println("El seeder " + socket.getInetAddress() + " se ha unido a la red");
                new TrackerSeeders(socket).start();
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
