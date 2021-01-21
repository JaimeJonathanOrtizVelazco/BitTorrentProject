import Seeder.SeederDTO;
import Seeder.SeederTorrentDTO;

import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class TrackerSeeders extends Thread {
    private final SocketConnection connection;
    public static ArrayList<SeederTorrentDTO> seederTorrents = new ArrayList<>();

    public TrackerSeeders(Socket socket) {
        connection = new SocketConnection(socket);
    }

    @Override
    public void run() {
        while (connection.Status()) {
            System.out.println("El seeder " + connection.getIp() + " esta conectado");
            connection.SendInput(1);
            ArrayList<SeederTorrentDTO> seeders = (ArrayList<SeederTorrentDTO>) connection.ReceiveObject();
            if (seeders != null)
                seeders.forEach(seed -> {
                    if (seederTorrents.stream().anyMatch(x -> x.id.equals(seed.getId()))) {
                        seederTorrents.stream().filter(x -> x.id.equals(seed.getId())).forEach(seedTorrent -> {
                            if (seedTorrent.seeders.stream().noneMatch(s -> s.seederIP.equals(connection.getIp()))) {
                                SeederDTO seederDTO = new SeederDTO(connection.getIp(), 4202);
                                seedTorrent.seeders.add(seederDTO);
                            }
                        });
                    } else {
                        seederTorrents.add(seed);
                    }
                });
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        connection.close();
        System.out.println("El seeder " + connection.getIp() + " se ha desconectado");
    }
}
