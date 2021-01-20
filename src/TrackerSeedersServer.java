import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TrackerSeedersServer extends Thread {
    private static final int PORT = 4201;

    @Override
    public void run() {
        ServerSocket server = null;
        try {
            server = new ServerSocket(PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            assert server != null;
            Socket socket = null;
            try {
                socket = server.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
            new TrackerSeeders(socket).start();
        }
    }
}
