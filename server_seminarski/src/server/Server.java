package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Umetnik;
import nit.ObradaKlijentskihZahteva;
import repository.db.DbConnectionFactory;

public class Server extends Thread {

    
    private boolean kraj = false;
    private ServerSocket serverSocket;
    private static ObradaKlijentskihZahteva okz = null;
    private static List<ObradaKlijentskihZahteva> klijenti = new ArrayList<>();
    //private static List<ObradaKlijentskihZahteva> korisnici = new ArrayList<>();

    public Server() {
        klijenti = new ArrayList<>();
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public static List<ObradaKlijentskihZahteva> getKlijenti() {
        return klijenti;
    }

    public void setKlijenti(List<ObradaKlijentskihZahteva> klijenti) {
        this.klijenti = klijenti;
    }

    
    
    
    
    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(pronadjiPort());
            System.out.println("Server je pokrenut na portu: " + serverSocket.getLocalPort() + ".");

            while (!kraj) {
                try {
                    Socket s = serverSocket.accept();
                    //System.out.println("Klijent je povezan.");

                    okz = new ObradaKlijentskihZahteva(s);
                    klijenti.add(okz);
                    okz.start();
                } catch (SocketException se) {
                    if (kraj) {
                        System.out.println("Server je zaustavljen.");
                    } else {
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, se);
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (serverSocket != null && !serverSocket.isClosed()) {
                    serverSocket.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public int pronadjiPort() {
        return Integer.parseInt(konfiguracija.Konfiguracija.getInstance().getProperty("port"));
    }

    public void zaustaviServer() {
        kraj = true;
        try {
            for (ObradaKlijentskihZahteva k : klijenti) {
                k.interrupt();
                k.getSocket().close();
                k.prekini();  // pretpostavljam da pravilno zatvara klijentske sokete
                break;
            }
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
            
            DbConnectionFactory.resetuj();
            
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void odjaviKlijenta(Umetnik umetnik) {

        // Pronađi odgovarajuću nit na osnovu Umetnika
        if (umetnik != null) {
            for (ObradaKlijentskihZahteva nit : klijenti) {
                if (nit.getIme().equals(umetnik.getIme())
                        && nit.getPrezime().equals(umetnik.getPrezime())
                        
                        && nit.getKorisnickoIme().equals(umetnik.getKorisničkoIme())
                        && nit.getSifra().equals(umetnik.getŠifra())) {
                    try {
                        nit.interrupt();
                        nit.getSocket().close();
                        klijenti.remove(nit);
                        System.out.println("Klijent " + umetnik.getIme() + " " + umetnik.getPrezime() + " se odjavio.");

                        return;
                    } catch (IOException ex) {
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, "Greška prilikom odjavljivanja klijenta.", ex);
                    }
                }
            }
        }
        try {
            okz.interrupt();
            okz.getSocket().close();
            klijenti.remove(okz);
        } catch (IOException e) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, "Greška prilikom odjavljivanja klijenta.", e);

        }
    }
    
    public static void dodajKorisnika() {
        
        klijenti.add(okz);
    }
}
