import Seeder.SeederDTO;

import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class TrackerLeecher extends Thread {
    private final SocketConnection connection;

    public TrackerLeecher(Socket socket) {
        connection = new SocketConnection(socket);
    }

    @Override
    public void run() {
        ArrayList<SeederDTO> seeders = new ArrayList<>();
        while (connection.Status()) {
            seeders.clear();
            connection.SendInput(1);
            String Id = connection.ReceiveInput();
            System.out.println("El leecher "+connection.getIp()+" esta comenzando a descargar el archivo "+Id);
            TrackerSeeders.seederTorrents.stream().filter(x -> x.id.equals(Id)).forEach(r -> {
                seeders.addAll(r.seeders);
            });
            connection.SendObject(seeders);
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        connection.close();
    }
}
