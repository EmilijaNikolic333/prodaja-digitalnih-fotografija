/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import cordinator.Cordinator;
import forme.DodajStrucnuSpremuForma;
import forme.DodajUmetnikaForma;
import forme.FormaMod;
import static forme.FormaMod.DETALJI;
import static forme.FormaMod.DODAJ;
import static forme.FormaMod.IZMENI;
import forme.model.ModelTabeleStrucnaSprema;
import java.awt.Color;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableCellRenderer;
import komunikacija.Komunikacija;
import model.StrucnaSprema;
import model.Umetnik;

/**
 *
 * @author nikol
 */
public class DodajStrucnuSpremuController {
    private final DodajStrucnuSpremuForma dssf;
    private StrucnaSprema strucnaSprema;

    public DodajStrucnuSpremuController(DodajStrucnuSpremuForma dssf) {
        this.dssf = dssf;
        addActionListener();
    }
    
    public void otvoriFormu(FormaMod mod, StrucnaSprema strucnaSprema){
        //pripremiFormu();
        this.strucnaSprema = strucnaSprema;
        pripremifomru(mod);
        
        ImageIcon ikonica = new ImageIcon(getClass().getResource("/slike/digital-drawing (2).png"));  // Stavi sliku u src/ikonice folder
        dssf.setIconImage(ikonica.getImage());
        dssf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dssf.setVisible(true);
        
        dssf.getContentPane().setBackground(new Color(230, 200, 255)); // Svetlo ljubičasta
        
        dssf.getjButtonPromeni().setBackground(new Color(138, 43, 226));
        dssf.getjButtonPromeni().setForeground(Color.white);
        
        dssf.getjButtonObriši().setBackground(new Color(138, 43, 226));
        dssf.getjButtonObriši().setForeground(Color.white);
        
        dssf.getjButtonUbaci().setBackground(new Color(138, 43, 226));
        dssf.getjButtonUbaci().setForeground(Color.white);
        
        dssf.getjButtonOdustani().setBackground(new Color(138, 43, 226));
        dssf.getjButtonOdustani().setForeground(Color.white);
        
        ImageIcon originalIkona = new ImageIcon(getClass().getResource("/slike/sprema1.png")); // Promeni ime ako treba
        Image slika = originalIkona.getImage().getScaledInstance(dssf.getjLabelSlika().getWidth(), dssf.getjLabelSlika().getHeight(), Image.SCALE_SMOOTH);
        dssf.getjLabelSlika().setIcon(new ImageIcon(slika));
        
        
    }
    
    private void pripremiFormu() {
        
    }

    private void pripremifomru(FormaMod mod) {
        switch (mod) {
            case DODAJ:
                dssf.getjButtonPromeni().setVisible(false);
                dssf.getjButtonObriši().setVisible(false);
                dssf.getjButtonUbaci().setVisible(true);
                dssf.getjButtonOdustani().setVisible(true);
                dssf.getjTextFieldId().setEnabled(false);
                dssf.setTitle("Kreiranje stručne spreme");
                
                
                
                break;
            case IZMENI:
                dssf.getjButtonPromeni().setVisible(true);
                dssf.getjButtonUbaci().setVisible(false);
                dssf.getjButtonOdustani().setVisible(true);
                dssf.getjTextFieldId().setEnabled(false);
                dssf.setTitle("Promena stručne spreme");
                
                StrucnaSprema ss = strucnaSprema;
                dssf.getjTextFieldId().setText(ss.getIdStručnaSprema() + "");
                dssf.getjTextFieldNaziv().setText(ss.getNaziv());
                dssf.setTitle("Detalji o stručnoj spremi");
                break;
                
            case DETALJI:
                //dssf.getjButtonPromeni().setVisible(false);
                dssf.getjButtonUbaci().setVisible(false);
                //dssf.getjButtonOdustani().setVisible(false);
                dssf.getjTextFieldId().setEnabled(false);
                //dssf.getjTextFieldNaziv().setEnabled(false);
                
                
                StrucnaSprema ss1 = strucnaSprema;
                dssf.getjTextFieldId().setText(ss1.getIdStručnaSprema() + "");
                dssf.getjTextFieldNaziv().setText(ss1.getNaziv());
                
                
                
                
                break;
                
            default:
                throw new AssertionError();
        }
    }

    private void addActionListener() {
        dssf.dodajUmetnikaAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //
                dodaj(e);
            }

            private void dodaj(ActionEvent e) {

                
                    String naziv = dssf.getjTextFieldNaziv().getText().trim();

                    

                    
                    if(naziv.isEmpty()){
                        JOptionPane.showMessageDialog(dssf, "Niste popunili polje.", "UPOZORENJE", JOptionPane.WARNING_MESSAGE);
                    }

                    else{
                    

                        StrucnaSprema ss = new StrucnaSprema(naziv);
                        
                        try{
                            Komunikacija.getInstance().dodajStrucnuSpremu(ss);
                            JOptionPane.showMessageDialog(dssf, "Sistem je zapamtio stručnu spremu.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                            dssf.dispose();

                        } catch(Exception exp){
                            JOptionPane.showMessageDialog(dssf, "Sistem ne može da zapamti stručnu spremu.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                            for (Window window : Window.getWindows()) {
                                window.dispose();
                            }
                            Cordinator.getInstance().otvoriLoginFormu();
                            //exp.printStackTrace();
                        }
                
                
                    }
                        
                
                

            
            }
            
            
        });
        
        dssf.addBtnObrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                    int odgovor = JOptionPane.showConfirmDialog(dssf, "Da li ste sigurni da želite da obrišete stručnu spremu?", "POTVRDA", JOptionPane.YES_NO_OPTION);
                        if(odgovor ==0){
                            StrucnaSprema ss = strucnaSprema;
                            try{
                                Komunikacija.getInstance().obrisiStrucnuSpremu(ss); 
                                Cordinator.getInstance().osveziStrucnuSpremuFormu();
                                JOptionPane.showMessageDialog(dssf, "Sistem je obrisao stručnu spremus.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                                dssf.dispose();

                            } catch(Exception exc){
                                JOptionPane.showMessageDialog(dssf, "Sistem ne moze da obriše stručnu spremu.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    
                
            }
        });
        
        dssf.izmeniUmetnikaAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //
                izmeni(e);
            }

            private void izmeni(ActionEvent e) {

                
                    int id = Integer.parseInt(dssf.getjTextFieldId().getText());
                            
                    String naziv = dssf.getjTextFieldNaziv().getText().trim();
                    
                    
                    
                    
                    
                    if(naziv.isEmpty()){
                        JOptionPane.showMessageDialog(dssf, "Polje nije pravilno popunjeno.", "UPOZORENJE", JOptionPane.WARNING_MESSAGE);
                    }

                    else{
                    

                        StrucnaSprema ss = new StrucnaSprema(id, naziv);
                        
                        try{
                            komunikacija.Komunikacija.getInstance().promeniStrucnuSpremu(ss);
                            JOptionPane.showMessageDialog(dssf, "Sistem je zapamtio stručnu spremu.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                            dssf.dispose();

                        } catch(Exception exp){
                            JOptionPane.showMessageDialog(dssf, "Sistem ne može da zapamti stručnu spremu.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                            for (Window window : Window.getWindows()) {
                                window.dispose();
                            }
                            Cordinator.getInstance().otvoriLoginFormu();
                            //exp.printStackTrace();
                        }
                
                
                    }
                        
                
                

            
            }
            
            
        });
        
        
        dssf.odustaniAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //
                odustani(e);
            }

            private void odustani(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(dssf, "Da li stvarno želite da zatvorite formu?", "POTVDRA", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
       

        
                if (option == JOptionPane.YES_OPTION) {
                    dssf.dispose();
                }
                
                
            }

            
        });
    }
    
    
    
}
