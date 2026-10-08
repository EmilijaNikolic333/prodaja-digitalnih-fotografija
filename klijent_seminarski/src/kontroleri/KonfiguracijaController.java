/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import forme.KonfiguracijaForma;
import forme.LoginForma;
import forme.model.ModelTabeleDigitalneFotogradije;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.WindowConstants;
import model.DigitalnaFotografija;

/**
 *
 * @author nikol
 */
public class KonfiguracijaController {
    
    private final KonfiguracijaForma kf;

    public KonfiguracijaController(KonfiguracijaForma kf) {
        this.kf = kf;
        
    }
    
    public void otvoriFormu() {
        pripremiFormu();
        kf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        kf.setVisible(true);
        kf.setTitle("Konfiguracija");
        ImageIcon ikonica = new ImageIcon(getClass().getResource("/slike/digital-drawing (2).png"));  // Stavi sliku u src/ikonice folder
        kf.setIconImage(ikonica.getImage());
        
        
        
        kf.getContentPane().setBackground(new Color(230, 200, 255)); // Svetlo ljubičasta
        
        kf.getjLabelPort().setFont(new Font("Arial", Font.BOLD, 28));
        kf.getjLabelPort().setForeground(new Color(138, 43, 226));
        
        kf.getjLabel1().setFont(new Font("Arial", Font.BOLD, 28));
        kf.getjLabel1().setForeground(new Color(138, 43, 226));
        
        
        ImageIcon originalIkona = new ImageIcon(getClass().getResource("/slike/konfig1.png")); // Promeni ime ako treba
        Image slika = originalIkona.getImage().getScaledInstance(kf.getjLabelKonfig().getWidth(), kf.getjLabelKonfig().getHeight(), Image.SCALE_SMOOTH);
        kf.getjLabelKonfig().setIcon(new ImageIcon(slika));
        
    }

    public void pripremiFormu()  {
        String port = konfig.Konfiguracija.getInstance().getProperty("port");
        kf.getjLabelPort().setText(port);
        
    }
    
}
