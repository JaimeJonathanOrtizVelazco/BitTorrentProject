import java.io.*;
import java.net.Socket;

public class SocketConnection {
    private DataOutputStream DataOutput;
    private DataInputStream DataInput;
    private ObjectOutputStream ObjectOutput;
    private ObjectInputStream ObjectInput;
    private final Socket socket;

    public SocketConnection(Socket socket) {
        this.socket = socket;
        Init();
    }

    private void Init() {
        try {
            this.DataOutput = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ignored) {
            close();
        }
        try {
            this.DataInput = new DataInputStream(socket.getInputStream());
        } catch (IOException ignored) {
            close();
        }
        try {
            this.ObjectOutput = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException ignored) {
            close();
        }
        try {
            this.ObjectInput = new ObjectInputStream(socket.getInputStream());
        } catch (IOException ignored) {
            close();
        }
    }

    public boolean Status() {
        if (this.socket.isClosed()) {
            return false;
        } else {
            return this.socket.isConnected();
        }
    }

    public String getIp() {
        return this.socket.getInetAddress().getHostAddress();
    }

    public <T> void SendObject(T Object) {
        try {
            this.ObjectOutput.writeObject(Object);
        } catch (IOException ignored) {
            close();
        }
    }

    public void SendInput(int value) {
        try {
            this.DataOutput.writeInt(value);
        } catch (IOException ignored) {
            close();
        }
    }

    public Object ReceiveObject() {
        try {
            return this.ObjectInput.readObject();
        } catch (IOException | ClassNotFoundException ignored) {
            close();
            return null;
        }
    }

    public String ReceiveInput() {
        try {
            return this.DataInput.readUTF();
        } catch (IOException ignored) {
            close();
            return null;
        }
    }

    public void close() {
        try {
            this.socket.close();
        } catch (IOException ignored) {
        }
    }
}
