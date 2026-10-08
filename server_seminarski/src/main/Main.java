package main;

import forme.FormaKonfiguracijaBaza;
import forme.ServerskaForma;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import konfiguracija.Konfiguracija;
import model.Umetnik;
import modelTabela.ModelTabeleUmetnici;
import java.sql.Connection;
import java.sql.DriverManager;

public class Main {

    private static ServerskaForma sf;
    private static List<Umetnik> neulogovani = new ArrayList<>();

    public static void main(String[] args) {
    try {
        Konfiguracija konf = Konfiguracija.getInstance();

        // 1. Ako fajl ne postoji ili je korumpiran, otvori formu
        if (!konf.daLiPostojiKonfigFajl() || !konf.ucitaj()) {
            JOptionPane.showMessageDialog(null, "Konfiguracioni fajl nije pronađen ili je neispravan. Unesite parametre.");
            FormaKonfiguracijaBaza fkb = new FormaKonfiguracijaBaza(null, true);
            fkb.setVisible(true);
            konf.ucitaj();
        }

        // 2. U svakom slučaju, testiraj konekciju (jer url može biti loš čak i ako fajl postoji)
        boolean konekcijaUspesna = false;
        String url = konf.getProperty("url");
        String username = konf.getProperty("username");
        String password = konf.getProperty("password");

        try {
            if (url != null && url.startsWith("jdbc:") && username != null) {
                Connection testConn = DriverManager.getConnection(url, username, password);
                testConn.close();
                konekcijaUspesna = true;
            }
        } catch (Exception ex) {
            System.err.println("Neuspešna konekcija sa bazom: " + ex.getMessage());
        }

        // 3. Ako konekcija nije uspela, otvori formu ponovo dok ne uspe
        while (!konekcijaUspesna) {
            JOptionPane.showMessageDialog(null, "Konekcija sa bazom nije uspela. Unesite ispravne parametre.");
            FormaKonfiguracijaBaza fkb = new FormaKonfiguracijaBaza(null, true);
            fkb.setVisible(true);
            konf.ucitaj();

            url = konf.getProperty("url");
            username = konf.getProperty("username");
            password = konf.getProperty("password");

            try {
                Connection testConn = DriverManager.getConnection(url, username, password);
                testConn.close();
                konekcijaUspesna = true;
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Ponovna konekcija nije uspela.", "Greška", JOptionPane.ERROR_MESSAGE);
            }
        }

        // 4. Pokretanje serverske forme
        sf = new ServerskaForma();
        sf.setVisible(true);

        

        } catch (Exception e) {
            //e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Greška pri pokretanju servera:\n" + e.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }


    
}
