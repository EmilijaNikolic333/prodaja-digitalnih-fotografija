/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import cordinator.Cordinator;
import forme.GlavnaForma;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import model.Umetnik;


/**
 *
 * @author nikol
 */
public class GlavnaFormaController {
    private final GlavnaForma gf;
    
    
    
    public GlavnaFormaController(GlavnaForma gf){
        this.gf = gf;
        
    }

    

    public void otvoriFormu() {
        // Postavi naslov prozora
        gf.setTitle("Glavna stranica");

        // Postavi ikonicu prozora (ovde može biti neka mala ikonica)
        ImageIcon ikonica = new ImageIcon(getClass().getResource("/slike/digital-drawing (2).png"));
        gf.setIconImage(ikonica.getImage());

        // Povećaj prozor na maksimalnu veličinu
        gf.setExtendedState(Frame.MAXIMIZED_BOTH);

        // Kreiraj centralni panel za tekst + sliku
        JPanel centralniPanel = new JPanel();
        centralniPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50));  // Razmak između komponenata

        // Postavi tekst u jLabelUlogovani
        Umetnik ulogovani = cordinator.Cordinator.getInstance().getUlogovani();
        String imePrezime = ulogovani.getIme() + " " + ulogovani.getPrezime();
        gf.getjLabelUlogovani().setText("<html>Zdravo, " + imePrezime + "!<br><br><br>Dobrodošli u aplikaciju za praćenje prodaje digitalnih fotografija.</html>");
        gf.getjLabelUlogovani().setFont(new Font("Arial", Font.BOLD, 28));
        gf.getjLabelUlogovani().setHorizontalAlignment(SwingConstants.CENTER);
        centralniPanel.add(gf.getjLabelUlogovani());

        // Učitaj i postavi sliku u jLabelSlika
        ImageIcon originalIkona = new ImageIcon(getClass().getResource("/slike/logo.png")); // promeni ime
        Image slika = originalIkona.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);  // promeni dimenzije po potrebi
        gf.getjLabelSlika().setIcon(new ImageIcon(originalIkona.getImage()));
        centralniPanel.add(gf.getjLabelSlika());

        // Dodaj centralni panel u glavni layout
        //gf.setLayout(new BorderLayout());
        //gf.add(centralniPanel, BorderLayout.CENTER);
        
        // Postavi layout forme i dodaj centralni panel
        gf.setLayout(new BorderLayout());
        gf.add(centralniPanel, BorderLayout.CENTER);

        // Kreiraj panel za dugme Odjava i postavi ga u donji desni ugao
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 20)); // FlowLayout sa poravnanjem u desno
        southPanel.add(gf.getjButtonOdjava());

        gf.add(southPanel, BorderLayout.SOUTH);

        
        
        gf.getContentPane().setBackground(new Color(230, 200, 255)); // Svetlo ljubičasta
        gf.getjButtonOdjava().setBackground(new Color(138, 43, 226));
        gf.getjButtonOdjava().setForeground(Color.white);
        
        centralniPanel.setOpaque(true);
        centralniPanel.setBackground(new Color(230, 200, 255));  

  
        southPanel.setOpaque(true);
        southPanel.setBackground(new Color(230, 200, 255));
        
        // Povećaj meni
        povecajMeni();

        // Prikazi formu
        gf.setVisible(true);

    }

    private void povecajMeni() {
        
        gf.getjMenu1().setBackground(new Color(138, 43, 226));
        
        
        Font font = new Font("Arial", Font.BOLD, 20); // Povećani font za meni i stavke
        gf.getjButtonOdjava().setFont(font);
        // Povećaj font za `JMenu1`
        gf.getjMenu1().setFont(font);
        gf.getjMenu1().setPreferredSize(new Dimension(gf.getWidth(), 40)); // Povećaj visinu menija

        // Povećaj širinu menija da bude max širina prozora
        gf.getjMenu1().setMaximumSize(new Dimension(gf.getWidth(), 40)); // Povećaj širinu menija da bude max širina prozora

        // Iteracija kroz sve komponente u jMenu1
        for (Component comp : gf.getjMenu1().getComponents()) {
            // Ako je komponenta meni (JMenu)
            if (comp instanceof JMenu menu) {
                menu.setFont(font); // Povećaj font za `JMenu`
                menu.setForeground(Color.white);
                // Povećaj širinu menija da bude do kraja širine prozora
                menu.setMaximumSize(new Dimension(gf.getWidth(), menu.getPreferredSize().height)); 

                // Iteracija kroz sve stavke u `JMenu` (JMenuItem)
                for (int i = 0; i < menu.getItemCount(); i++) {
                    JMenuItem item = menu.getItem(i);
                    if (item != null) {
                        item.setFont(font); // Povećaj font za stavku menija (JMenuItem)

                        // Podesi širinu svakog JMenuItem da se prostire po celoj širini menija
                        item.setMaximumSize(new Dimension(gf.getWidth(), item.getPreferredSize().height));

                        // Postavi stavke da se poravnaju levo
                        item.setHorizontalAlignment(SwingConstants.LEFT);

                        // Ako `JMenuItem` sadrži podstavke (subitems), iteriraj kroz njih
                        if (item instanceof JMenu menuItem) {
                            for (int j = 0; j < menuItem.getItemCount(); j++) {
                                JMenuItem subItem = menuItem.getItem(j);
                                if (subItem != null) {
                                    subItem.setFont(font); // Povećaj font za podstavke
                                    subItem.setMaximumSize(new Dimension(gf.getWidth(), subItem.getPreferredSize().height));
                                }
                            }
                        }
                    }
                }
            }
        }

        // Osveži formu nakon promena
        gf.revalidate();
        gf.repaint();
    }


    
                

    
      

   
    
    
}
