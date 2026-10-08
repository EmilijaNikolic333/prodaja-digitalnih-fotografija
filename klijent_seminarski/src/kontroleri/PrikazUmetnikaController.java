 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import cordinator.Cordinator;
import forme.PrikazRacunaForma;
import forme.PrikazUmetnikaForma;
import forme.model.ModelTabeleKupci;
import forme.model.ModelTabeleRacuni;
import forme.model.ModelTabeleUmetnici;
import java.awt.Color;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableCellRenderer;
import komunikacija.Komunikacija;
import model.Kupac;
import model.Mesto;
import model.Racun;
import model.StavkaRacuna;
import model.Umetnik;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 *
 * @author nikol
 */
public class PrikazUmetnikaController {
    
    
    private final PrikazUmetnikaForma puf;
    

    public PrikazUmetnikaController(PrikazUmetnikaForma puf) {
        this.puf = puf;
        addActionListener();
        ButtonGroup grupa = new ButtonGroup();
        grupa.add(puf.getjRadioButtonIme());
        grupa.add(puf.getjRadioButtonStrucnaSprema());
    }

    public void otvoriFormu() throws IOException, ClassNotFoundException {
        pripremiFormu();
        puf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        puf.setVisible(true);
        ImageIcon ikonica = new ImageIcon(getClass().getResource("/slike/digital-drawing (2).png"));  // Stavi sliku u src/ikonice folder
        puf.setIconImage(ikonica.getImage());
        puf.setTitle("Umetnici");
        
        puf.getContentPane().setBackground(new Color(230, 200, 255)); // Svetlo ljubičasta
        puf.getjButtonPretraži().setBackground(new Color(138, 43, 226));
        puf.getjButtonPretraži().setForeground(Color.white);
        
        puf.getjButtonResetujPretragu().setBackground(new Color(138, 43, 226));
        puf.getjButtonResetujPretragu().setForeground(Color.white);
        
        puf.getjButtonDetalji().setBackground(new Color(138, 43, 226));
        puf.getjButtonDetalji().setForeground(Color.white);
        
        
        
        // Postavljanje boje pozadine tabele
        puf.getjTableUmetnici().setBackground(new Color(147, 112, 219)); 
        puf.getjTableUmetnici().setForeground(Color.WHITE); 

        // Postavljanje boje zaglavlja tabele
        puf.getjTableUmetnici().getTableHeader().setBackground(new Color(138, 43, 226)); // Tamno ljubičasta
        puf.getjTableUmetnici().getTableHeader().setForeground(Color.WHITE); // Boja teksta u zaglavlju
        
        puf.getjScrollPane1().getViewport().setBackground(new Color(147, 112, 219)); // Svetlo ljubičasta
        
        puf.getjTableUmetnici().setGridColor(Color.BLACK); // Crne linije između redova i kolona
        puf.getjTableUmetnici().setShowGrid(true); // Omogućava prikaz linija
        puf.getjTableUmetnici().setIntercellSpacing(new java.awt.Dimension(1, 1)); // Podešava razmak između ćelija (debljina linija)

        ImageIcon originalIkona = new ImageIcon(getClass().getResource("/slike/umetnik1.png")); // Promeni ime ako treba
        Image slika = originalIkona.getImage().getScaledInstance(puf.getjLabelSlika().getWidth(), puf.getjLabelSlika().getHeight(), Image.SCALE_SMOOTH);
        puf.getjLabelSlika().setIcon(new ImageIcon(slika));

        puf.getjRadioButtonIme().setBackground(new Color(230, 200, 255));
        puf.getjRadioButtonStrucnaSprema().setBackground(new Color(230, 200, 255));
        
        puf.getjTextFieldImeIPrezime().setEditable(false);
        puf.getjTextFieldStrucnaSprema().setEditable(false);
    }

    public void pripremiFormu() throws IOException, ClassNotFoundException  {
        List<Umetnik> umetnici = komunikacija.Komunikacija.getInstance().ucitajUmetnika();
        
        ModelTabeleUmetnici mtu = new ModelTabeleUmetnici(umetnici);
        puf.getjTableUmetnici().setModel(mtu);
        
        centrirajTekst(puf.getjTableUmetnici());
    }
    
    
    private void addActionListener() {
        puf.addBtnPretrazActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                String imeIPrezime = puf.getjTextFieldImeIPrezime().getText().trim();
                
                String strucnaSprema = puf.getjTextFieldStrucnaSprema().getText().trim();
                
                if(!puf.getjRadioButtonIme().isSelected() && !puf.getjRadioButtonStrucnaSprema().isSelected()){
                    JOptionPane.showMessageDialog(puf, "Izaberite kriterijum za pretragu!", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                }
                
                if(puf.getjRadioButtonIme().isSelected()){
                    if(imeIPrezime.isEmpty()){
                        JOptionPane.showMessageDialog(puf, "Popunite polje za pretragu!", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                    }else{
                        try {
                            List<Umetnik> pretragaUmetnici = Komunikacija.getInstance().vratiListuSviUmetnikUmetnik(imeIPrezime);
                            if(pretragaUmetnici.isEmpty()){
                                JOptionPane.showMessageDialog(puf, "Sistem ne može da nađe umetnike po zadatim kriterijumima.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                                
                            }else{
                                JOptionPane.showMessageDialog(puf, "Sistem je našao umetnike po zadatim kriterijumima.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                                
                                ModelTabeleUmetnici mtu = new ModelTabeleUmetnici(pretragaUmetnici);
                                puf.getjTableUmetnici().setModel(mtu);
                                
                                centrirajTekst(puf.getjTableUmetnici());
                            }
                        } catch (IOException ex) {
                            //Logger.getLogger(PrikazUmetnikaController.class.getName()).log(Level.SEVERE, null, ex);
                            JOptionPane.showMessageDialog(puf, "Sistem ne može da nađe umetnike po zadatim kriterijumima.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                            for (Window window : Window.getWindows()) {
                                window.dispose();
                            }
                            Cordinator.getInstance().otvoriLoginFormu();
                        } catch (ClassNotFoundException ex) {
                            //Logger.getLogger(PrikazUmetnikaController.class.getName()).log(Level.SEVERE, null, ex);
                            JOptionPane.showMessageDialog(puf, "Sistem ne može da nađe umetnike po zadatim kriterijumima.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                            for (Window window : Window.getWindows()) {
                                window.dispose();
                            }
                            Cordinator.getInstance().otvoriLoginFormu();
                        }
                        
                        
                        
                    }
                    
                }
                
                if(puf.getjRadioButtonStrucnaSprema().isSelected()){
                    if(strucnaSprema.isEmpty()){
                        JOptionPane.showMessageDialog(puf, "Popunite polje za pretragu!", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                    }else{
                        
                        
                        try {
                            List<Umetnik> pretragaUmetnici = Komunikacija.getInstance().vratiListuSviUmetnikStrSprema(strucnaSprema);
                            if(pretragaUmetnici.isEmpty()){
                                JOptionPane.showMessageDialog(puf, "Sistem ne može da nađe umetnike po zadatim kriterijumima.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                                
                            }else{
                                JOptionPane.showMessageDialog(puf, "Sistem je našao umetnike po zadatim kriterijumima.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                                
                                ModelTabeleUmetnici mtu = new ModelTabeleUmetnici(pretragaUmetnici);
                                puf.getjTableUmetnici().setModel(mtu);
                                
                                centrirajTekst(puf.getjTableUmetnici());
                            }
                        } catch (IOException ex) {
                            //Logger.getLogger(PrikazUmetnikaController.class.getName()).log(Level.SEVERE, null, ex);
                            JOptionPane.showMessageDialog(puf, "Sistem ne može da nađe umetnike po zadatim kriterijumima.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                            for (Window window : Window.getWindows()) {
                                window.dispose();
                            }
                            Cordinator.getInstance().otvoriLoginFormu();
                        } catch (ClassNotFoundException ex) {
                            //Logger.getLogger(PrikazUmetnikaController.class.getName()).log(Level.SEVERE, null, ex);
                            JOptionPane.showMessageDialog(puf, "Sistem ne može da nađe umetnike po zadatim kriterijumima.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                            for (Window window : Window.getWindows()) {
                                window.dispose();
                            }
                            Cordinator.getInstance().otvoriLoginFormu();
                        }
                        
                        
                        
                        
                        
                    }
                    
                }
                
                
            }
        });
        
        puf.addBtnResetujActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    pripremiFormu();
                } catch (IOException ex) {
                    //Logger.getLogger(PrikazUmetnikaController.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(puf, "Sistem ne može da resetuje pretragu.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                            for (Window window : Window.getWindows()) {
                                window.dispose();
                            }
                            Cordinator.getInstance().otvoriLoginFormu();
                } catch (ClassNotFoundException ex) {
                    //Logger.getLogger(PrikazUmetnikaController.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(puf, "Sistem ne može da resetuje pretragu.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                            for (Window window : Window.getWindows()) {
                                window.dispose();
                            }
                            Cordinator.getInstance().otvoriLoginFormu();
                }
            }
        });
        
        
        
        puf.addBtnSelektovanUmetnikActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    puf.getjTextFieldImeIPrezime().setEditable(true);
                    
                    puf.getjTextFieldStrucnaSprema().setEditable(false);
                    puf.getjTextFieldStrucnaSprema().setText("");
                    
                    
                    
      
                }
            
        });
        
        puf.addBtnSelektovanaStrSpremaActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    puf.getjTextFieldImeIPrezime().setEditable(false);
                    puf.getjTextFieldImeIPrezime().setText("");
                    puf.getjTextFieldStrucnaSprema().setEditable(true);
                    
                    
                    
                    
      
                }
            
        });
        
        
        
        puf.addBtnDetaljiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = puf.getjTableUmetnici().getSelectedRow();
                if(red == -1){
                    JOptionPane.showMessageDialog(puf, "Niste izabrali umetnika.", "UPOZORENJE", JOptionPane.WARNING_MESSAGE);
                } else{
                    try {
                        ModelTabeleUmetnici mtu = (ModelTabeleUmetnici) puf.getjTableUmetnici().getModel();
                        Umetnik u = mtu.getLista().get(red);
                        
                        Umetnik umetnik = Komunikacija.getInstance().pretraziUmetnik(u);
                        
                        
                        JOptionPane.showMessageDialog(puf, "Sistem je našao umetnika.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                        
                        cordinator.Cordinator.getInstance().otvoriDetaljiUmetnikaFormu(umetnik);
                    } catch (IOException ex) {
                        //Logger.getLogger(PrikazUmetnikaController.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(puf, "Sistem ne moze da nađe umetnika.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                        for (Window window : Window.getWindows()) {
                                window.dispose();
                            }
                            Cordinator.getInstance().otvoriLoginFormu();
                            

                    } catch (ClassNotFoundException ex) {
                        //Logger.getLogger(PrikazUmetnikaController.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(puf, "Sistem ne moze da nađe umetnika.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                        for (Window window : Window.getWindows()) {
                                window.dispose();
                            }
                            Cordinator.getInstance().otvoriLoginFormu();
                            

                    }
                    
                    
      
                }
            }
        });
    }

    public void osveziFormu() throws IOException, ClassNotFoundException {
        pripremiFormu();
    }
    
    public void centrirajTekst(JTable tabela){
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        
        for(int i = 0; i < tabela.getColumnCount(); i++){
            tabela.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            
        }
    }
}
