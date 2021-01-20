import java.io.*;
import java.net.Socket;

public class SocketConnection {
    private DataOutputStream DataOutput;
    private DataInputStream DataInput;
    private ObjectOutputStream ObjectOutput;
    private ObjectInputStream ObjectInput;
    private Socket socket;

    public SocketConnection(Socket socket) {
        this.socket = socket;
        try {
            this.DataOutput = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            this.DataInput = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            this.ObjectOutput = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            this.ObjectInput = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SocketConnection(String ip, int port) {
        try {
            this.socket = new Socket(ip, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            this.DataOutput = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            this.DataInput = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            this.ObjectOutput = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            this.ObjectInput = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public <T> void SendObject(T Object) {
        try {
            this.ObjectOutput.writeObject(Object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object ReceiveObject() {
        try {
            return this.ObjectInput.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    public String ReceiveInput(){
        try {
            return this.DataInput.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public int ReceiveInputInt(){
        try {
            return this.DataInput.readInt();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public void close(){
        try {
            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
