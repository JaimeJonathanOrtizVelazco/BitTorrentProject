import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TrackerLeecherServer extends Thread{
    private static final int PORT = 4200;

    @Override
    public void run() {
        ServerSocket server = null;
        try {
            server = new ServerSocket(PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean loop=true;
        while (loop) {
            assert server != null;
            Socket socket = null;
            try {
                socket = server.accept();
            } catch (IOException e) {
                e.printStackTrace();
                loop=false;
            }
            new TrackerLeecher(socket).start();
        }
    }
}
