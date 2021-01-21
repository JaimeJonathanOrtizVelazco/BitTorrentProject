import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TrackerLeecherServer extends Thread {
    private static final int PORT = 4200;
    private static ServerSocket server;

    public TrackerLeecherServer() throws IOException {
        server = new ServerSocket(PORT);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket socket = server.accept();
                System.out.println("El leecher " + socket.getInetAddress().getHostAddress() + " se ha conectado a la red");
                new TrackerLeecher(socket).start();
            } catch (IOException e) {
                break;
            }
        }
    }
}
