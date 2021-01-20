import Seeder.SeederTorrentDTO;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class TrackerSeeders extends Thread {
    Socket socket;
    SocketConnection connection;
    public static ArrayList<SeederTorrentDTO> seederTorrents = new ArrayList<>();

    public TrackerSeeders(Socket socket) {
        this.socket = socket;
        connection = new SocketConnection(socket);
    }

    @Override
    public void run() {
        ArrayList<SeederTorrentDTO> seeders = (ArrayList<SeederTorrentDTO>) connection.ReceiveObject();
        if(seeders!=null){
            seeders.forEach(seed->{
                if(seederTorrents.stream().anyMatch(x-> x.id.equals(seed.getId()))){
                    seederTorrents.stream().filter(x->x.id.equals(seed.getId())).forEach(seedTorrent->{
                        if(seedTorrent.seeders.stream().noneMatch(s->s.seederIP.equals(seed.seeders.get(0).seederIP)))
                        {
                            seedTorrent.seeders.addAll(seed.seeders);
                        }
                    });
                }else{
                    seederTorrents.add(seed);
                }
            });
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
