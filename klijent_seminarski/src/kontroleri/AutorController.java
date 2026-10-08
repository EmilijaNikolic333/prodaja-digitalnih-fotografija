/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import forme.AutorForma;
import forme.KonfiguracijaForma;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.WindowConstants;

/**
 *
 * @author nikol
 */
public class AutorController {
    private final AutorForma af;

    public AutorController(AutorForma af) {
        this.af = af;
        
    }
    
    public void otvoriFormu() {
        //pripremiFormu();
        af.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        af.setVisible(true);
        ImageIcon ikonica = new ImageIcon(getClass().getResource("/slike/digital-drawing (2).png"));  // Stavi sliku u src/ikonice folder
        af.setIconImage(ikonica.getImage());
        af.setTitle("Autor");
        
        af.getContentPane().setBackground(new Color(230, 200, 255)); // Svetlo ljubičasta
        
        af.getjLabel1().setFont(new Font("Arial", Font.BOLD, 28));
        af.getjLabel1().setForeground(new Color(138, 43, 226));
        
        af.getjLabel3().setFont(new Font("Arial", Font.BOLD, 28));
        af.getjLabel3().setForeground(new Color(138, 43, 226));
        
        af.getjLabel4().setFont(new Font("Arial", Font.BOLD, 28));
        af.getjLabel4().setForeground(new Color(138, 43, 226));
        
        af.getjLabelIme().setFont(new Font("Arial", Font.BOLD, 28));
        af.getjLabelIme().setForeground(new Color(138, 43, 226));
        
        af.getjLabelPrezime().setFont(new Font("Arial", Font.BOLD, 28));
        af.getjLabelPrezime().setForeground(new Color(138, 43, 226));
        
        af.getjLabelIndeks().setFont(new Font("Arial", Font.BOLD, 28));
        af.getjLabelIndeks().setForeground(new Color(138, 43, 226));
        
        
        ImageIcon originalIkona = new ImageIcon(getClass().getResource("/slike/autor1.png")); // Promeni ime ako treba
        Image slika = originalIkona.getImage().getScaledInstance(af.getjLabelAutor().getWidth(), af.getjLabelAutor().getHeight(), Image.SCALE_SMOOTH);
        af.getjLabelAutor().setIcon(new ImageIcon(slika));
        ////
    }

    
}
