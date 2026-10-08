package konfiguracija;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import javax.swing.JOptionPane;

public class Konfiguracija {

    private static Konfiguracija instance;
    private Properties konfiguracija;
    private final String putanja = "config/config.properties";

    private Konfiguracija() {
        konfiguracija = new Properties();
    }

    public static Konfiguracija getInstance() {
        if (instance == null) {
            instance = new Konfiguracija();
        }
        return instance;
    }

    /**
     * Proverava da li konfiguracioni fajl postoji.
     */
    public boolean daLiPostojiKonfigFajl() {
        File fajl = new File(putanja);
        return fajl.exists();
    }

    /**
     * Učitaj konfiguraciju iz fajla.
     * @return true ako je uspešno učitano, false ako nije
     */
    public boolean ucitaj() {
        if (!daLiPostojiKonfigFajl()) {
            return false;
        }

        try (FileInputStream fis = new FileInputStream(putanja)) {
            konfiguracija.load(fis);

            // Validacija kritičnih parametara
            String url = konfiguracija.getProperty("url", "").trim();
            String username = konfiguracija.getProperty("username", "").trim();
            String password = konfiguracija.getProperty("password", "").trim();

            if (url.isEmpty() || !url.startsWith("jdbc:")) {
                JOptionPane.showMessageDialog(null, "URL baze je neispravan ili nije definisan.",
                        "Neispravan URL", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            if (username.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Korisničko ime za bazu nije definisano.",
                        "Nedostaje korisničko ime", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            

            return true;

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Greška pri učitavanju konfiguracije:\n" + ex.getMessage(),
                    "Greška", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /**
     * Sačuvaj trenutne izmene u konfiguracionom fajlu.
     */
    public boolean sacuvajIzmene() {
        try (FileOutputStream fos = new FileOutputStream(putanja)) {
            konfiguracija.store(fos, "Konfiguracioni fajl aplikacije");
            return true;
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Greška pri čuvanju konfiguracije:\n" + ex.getMessage(),
                    "Greška", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /**
     * Dohvati vrednost za dati ključ ili "n/a" ako ne postoji.
     */
    public String getProperty(String key) {
        return konfiguracija.getProperty(key, "n/a");
    }

    /**
     * Postavi vrednost za dati ključ.
     */
    public void setProperty(String key, String value) {
        konfiguracija.setProperty(key, value);
    }
}
