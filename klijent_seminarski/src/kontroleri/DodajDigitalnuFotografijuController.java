/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import cordinator.Cordinator;
import forme.DodajDigitalnuFotografijuForma;
import forme.DodajUmetnikaForma;
import forme.FormaMod;
import static forme.FormaMod.DETALJI;
import static forme.FormaMod.DODAJ;
import static forme.FormaMod.IZMENI;
import forme.model.ModelTabeleDigitalneFotogradije;
import java.awt.Color;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import komunikacija.Komunikacija;
import model.DigitalnaFotografija;
import model.Kupac;
import model.Mesto;
import model.Umetnik;

/**
 *
 * @author nikol
 */
public class DodajDigitalnuFotografijuController {
    private final DodajDigitalnuFotografijuForma ddff;
    private DigitalnaFotografija digitalnaFotografija;

    public DodajDigitalnuFotografijuController(DodajDigitalnuFotografijuForma ddff) {
        this.ddff = ddff;
        addActionListener();
    }
    
    public void otvoriFormu(FormaMod mod, DigitalnaFotografija digitalnaFotografija){
        //pripremiFormu();
        this.digitalnaFotografija = digitalnaFotografija;
        pripremifomru(mod);
        
        ddff.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        ddff.setVisible(true);
        ImageIcon ikonica = new ImageIcon(getClass().getResource("/slike/digital-drawing (2).png"));  // Stavi sliku u src/ikonice folder
        ddff.setIconImage(ikonica.getImage());
        
        
        ddff.getContentPane().setBackground(new Color(230, 200, 255)); // Svetlo ljubičasta
        
        ddff.getjButtonPromeni().setBackground(new Color(138, 43, 226));
        ddff.getjButtonPromeni().setForeground(Color.white);
        
        ddff.getjButtonObrišiDF().setBackground(new Color(138, 43, 226));
        ddff.getjButtonObrišiDF().setForeground(Color.white);
        
        ddff.getjButtonKreiraj().setBackground(new Color(138, 43, 226));
        ddff.getjButtonKreiraj().setForeground(Color.white);
        
        ddff.getjButtonOdustani().setBackground(new Color(138, 43, 226));
        ddff.getjButtonOdustani().setForeground(Color.white);
        
        ImageIcon originalIkona = new ImageIcon(getClass().getResource("/slike/digital-drawing.png")); // Promeni ime ako treba
        Image slika = originalIkona.getImage().getScaledInstance(ddff.getjLabelSlika().getWidth(), ddff.getjLabelSlika().getHeight(), Image.SCALE_SMOOTH);
        ddff.getjLabelSlika().setIcon(new ImageIcon(slika));
    }
    
    private void pripremiFormu() {
        
    }

    private void pripremifomru(FormaMod mod) {
        switch (mod) {
            case DODAJ:
                ddff.getjButtonKreiraj().setVisible(true);
                ddff.getjButtonPromeni().setVisible(false);
                ddff.getjButtonOdustani().setVisible(true);
                ddff.getjButtonObrišiDF().setVisible(false);
                ddff.getjTextFieldID().setEnabled(false);
                ddff.setTitle("Kreiranje digitalne fotografije");
                
                
                break;
            case IZMENI:
                ddff.getjButtonKreiraj().setVisible(false);
                ddff.getjButtonPromeni().setVisible(true);
                ddff.getjTextFieldID().setEnabled(false);
                ddff.setTitle("Promena digitalne fotografije");
                
                DigitalnaFotografija df = digitalnaFotografija;
                
                
                
                ddff.getjTextFieldID().setText(df.getIdDigitalnaFotografija() + "");
                
                ddff.getjTextFieldNaziv().setText(df.getNazivFotografije());
                
                ddff.getjTextFieldCena().setText(df.getCena() + "");
                
                
                
                break;
                
            case DETALJI:
                ddff.getjButtonKreiraj().setVisible(false);
                //ddff.getjButtonPromeni().setVisible(false);
                ddff.getjTextFieldID().setEnabled(false);
                //ddff.getjTextFieldNaziv().setEnabled(false);
                //ddff.getjTextFieldCena().setEnabled(false);
                //ddff.getjButtonOdustani().setVisible(false);
                ddff.setTitle("Detalji o digitalnoj fotografiji");
                
                
                DigitalnaFotografija df1 = digitalnaFotografija;
                
                ddff.getjTextFieldID().setText(df1.getIdDigitalnaFotografija() + "");
                
                ddff.getjTextFieldNaziv().setText(df1.getNazivFotografije());
                
                ddff.getjTextFieldCena().setText(df1.getCena() + "");
                
                
                
                
                break;
                
            default:
                throw new AssertionError();
        }
    }
    
    
    private void addActionListener() {
        ddff.dodajDigitalnuFotografijuAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //
                dodaj(e);
            }

            private void dodaj(ActionEvent e) {

                try{
                    String naziv = ddff.getjTextFieldNaziv().getText();
                    
                    double cena = Double.parseDouble(ddff.getjTextFieldCena().getText().trim());
                    
                    
                   
                    
                    if(naziv.isEmpty() || ddff.getjTextFieldCena().getText().trim().isEmpty()){
                        JOptionPane.showMessageDialog(ddff, "Niste popunili sva polja.", "UPOZORENJE", JOptionPane.WARNING_MESSAGE);
                    }

                    else{
                        

                        DigitalnaFotografija df = new DigitalnaFotografija(naziv, cena);
                        try{
                            Komunikacija.getInstance().dodajDigitalnuFotografiju(df);
                            JOptionPane.showMessageDialog(ddff, "Sistem je zapamtio digitalnu fotografiju.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                            ddff.dispose();

                        } catch(Exception exp){
                            JOptionPane.showMessageDialog(ddff, "Sistem ne može da zapamti digitalnu fotografiju.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                            for (Window window : Window.getWindows()) {
                                window.dispose();
                            }
                            Cordinator.getInstance().otvoriLoginFormu();
                            //exp.printStackTrace();
                        }
                
                
                    }
                        
                
                
                    
            
            }
             catch (NumberFormatException es) {
                // Ako nije unet validan broj
                //System.out.println("Greška: Unos nije validan broj.");
                // Možeš prikazati poruku korisniku npr. kroz JOptionPane
                JOptionPane.showMessageDialog(ddff, "Molimo unesite validnu cenu (broj).", "UPOZORENJE", JOptionPane.WARNING_MESSAGE);
            }
            
            }
        });
        
        ddff.izmeniDigitalnuFotografijuAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //
                izmeni(e);
            }

            private void izmeni(ActionEvent e) {
                try{
                    int id = Integer.parseInt(ddff.getjTextFieldID().getText().trim() + "");
                
                    String naziv = ddff.getjTextFieldNaziv().getText();
                    
                    double cena = Double.parseDouble(ddff.getjTextFieldCena().getText().trim());
                
                
                    if(naziv.isEmpty() || ddff.getjTextFieldCena().getText().trim().isEmpty()){
                        JOptionPane.showMessageDialog(ddff, "Polja nisu pravilno popunjena.", "UPOZORENJE", JOptionPane.WARNING_MESSAGE);
                    }

                    else{
                    

                        DigitalnaFotografija df = new DigitalnaFotografija(id, naziv, cena);
                        try{
                            Komunikacija.getInstance().izmeniDigitalnuFotografiju(df);
                            JOptionPane.showMessageDialog(ddff, "Sistem je zapamtio digitalnu fotografiju.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                            ddff.dispose();

                        } catch(Exception exp){
                            JOptionPane.showMessageDialog(ddff, "Sistem ne može da zapamti digitalnu fotografiju.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                            
                            for (Window window : Window.getWindows()) {
                                window.dispose();
                            }
                            Cordinator.getInstance().otvoriLoginFormu();
                            //exp.printStackTrace();
                        }
                
                
                    }
                }
             catch (NumberFormatException es) {
                // Ako nije unet validan broj
                //System.out.println("Greška: Unos nije validan broj.");
                // Možeš prikazati poruku korisniku npr. kroz JOptionPane
                JOptionPane.showMessageDialog(ddff, "Molimo unesite validnu cenu (broj).", "UPOZORENJE", JOptionPane.WARNING_MESSAGE);
            }
            }        
                
                

            
        });
        
        ddff.addBtnObrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                    int odgovor = JOptionPane.showConfirmDialog(ddff, "Da li ste sigurni da želite da obrišete digitalnu fotografiju?", "POTVRDA", JOptionPane.YES_NO_OPTION);
                        if(odgovor ==0){
                            DigitalnaFotografija df = digitalnaFotografija;

                            try{
                                Komunikacija.getInstance().obrisiDigitalnuFotografiju(df); 
                                Cordinator.getInstance().osveziDigitalnaFotografijaFormu();
                                JOptionPane.showMessageDialog(ddff, "Sistem je obrisao digitalnu fotografiju.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                                ddff.dispose();

                            } catch(Exception exc){
                                JOptionPane.showMessageDialog(ddff, "Sistem ne moze da obriše digitalnu fotografiju.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                                
                                
                                
            
                            }
                                
                            
                        }
                    
                    
                
            }
        });
        
        ddff.odustaniAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //
                odustani(e);
            }

            private void odustani(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(ddff, "Da li stvarno želite da zatvorite formu?", "POTVDRA", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
       

        
                if (option == JOptionPane.YES_OPTION) {
                    ddff.dispose();
                }
                
                
            }

            
        });
    }
}
