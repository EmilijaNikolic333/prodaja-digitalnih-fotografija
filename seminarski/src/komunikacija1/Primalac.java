package komunikacija1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class Primalac {
    private Socket socket;
    private ObjectInputStream in;

    public Primalac(Socket socket) {
        this.socket = socket;
        try {
            this.in = new ObjectInputStream(socket.getInputStream()); // samo jednom!
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object primi() throws IOException, ClassNotFoundException {
        return in.readObject(); // koristi već postojeći stream
    }
}
