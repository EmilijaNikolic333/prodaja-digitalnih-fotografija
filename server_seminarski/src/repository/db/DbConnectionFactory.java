/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.db;

import forme.FormaKonfiguracijaBaza;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import konfiguracija.Konfiguracija;

/**
 *
 * @author nikol
 */
public class DbConnectionFactory {
    private static DbConnectionFactory instance;

    public static void resetuj() {
        instance = null;
    }
    private Connection connection;
    Konfiguracija konf = Konfiguracija.getInstance();
    
    private DbConnectionFactory() {
    try {
        if (connection == null || connection.isClosed()) {
            String url = konf.getProperty("url");
            String username = konf.getProperty("username");
            String password = konf.getProperty("password");

            if (url == null || url.equals("n/a") || url.isBlank() ||
                username == null || username.equals("n/a") || username.isBlank()) {
                JOptionPane.showMessageDialog(null, "Parametri baze nisu ispravno podešeni. Molimo unesite ispravne podatke.", "Greška u konfiguraciji", JOptionPane.ERROR_MESSAGE);
                FormaKonfiguracijaBaza fkb = new FormaKonfiguracijaBaza(null, true);
                fkb.setVisible(true);
                konf.ucitaj();
                url = konf.getProperty("url");
                username = konf.getProperty("username");
                password = konf.getProperty("password");
            }

            connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);
        }
        } catch (SQLException ex) {
            Logger.getLogger(DbConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Greška pri povezivanju sa bazom: " + ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    public static DbConnectionFactory getInstance(){
        if(instance == null){
            instance = new DbConnectionFactory();
        }
        return instance;
    }

    public Connection getConnection() {
        if (connection == null) {
            throw new IllegalStateException("Konekcija nije uspostavljena!");
        }
        return connection;
    }
    
    
}
