import Seeder.SeederDTO;

import java.net.Socket;
import java.util.ArrayList;

public class TrackerLeecher extends Thread {
    private final SocketConnection connection;

    public TrackerLeecher(Socket socket) {
        connection = new SocketConnection(socket);
    }

    @Override
    public void run() {
        ArrayList<SeederDTO> seeders = new ArrayList<>();
        String Id= connection.ReceiveInput();
        TrackerSeeders.seederTorrents.stream().filter(x-> x.id.equals(Id)).forEach(r->{
            seeders.addAll(r.seeders);
        });
        connection.SendObject(seeders);
        connection.close();
    }
}
