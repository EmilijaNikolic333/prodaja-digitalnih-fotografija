package komunikacija1;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Posiljalac {
    private final Socket socket;
    private ObjectOutputStream out;

    public Posiljalac(Socket socket) {
        this.socket = socket;
        try {
            this.out = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(Posiljalac.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void posalji(Object obj) {
        try {
            if (socket != null && !socket.isClosed() && socket.isConnected()) {
                out.writeObject(obj);
                out.flush();
            } else {
                System.out.println("Pokušaj slanja na zatvoren ili neispravan socket.");
            }
        } catch (IOException ex) {
            System.out.println("Greška prilikom slanja objekta: " + ex.getMessage());
            // Možeš dodati i logiku za zatvaranje konekcije ako želiš
        }
    }
}
