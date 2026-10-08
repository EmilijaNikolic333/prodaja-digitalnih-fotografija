/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import cordinator.Cordinator;
import forme.DodajKupcaForma;
import forme.FormaMod;
import static forme.FormaMod.DODAJ;
import forme.model.ModelTabeleKupci;
import java.awt.Color;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import komunikacija.Komunikacija;
import model.Kupac;
import model.Mesto;
import model.Umetnik;

/**
 *
 * @author nikol
 */
public class DodajKupcaController {
    private final DodajKupcaForma dkf;
    private Kupac kupac = new Kupac();

    public DodajKupcaController(DodajKupcaForma dkf) {
        this.dkf = dkf;
        addActionListener();
    }
    
    public void otvoriFormu(FormaMod mod, Kupac kupac) throws IOException, ClassNotFoundException{
        
        this.kupac = kupac;
        
        pripremiFormu();
        pripremifomru(mod);
        
        dkf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dkf.setVisible(true);
        ImageIcon ikonica = new ImageIcon(getClass().getResource("/slike/digital-drawing (2).png"));  // Stavi sliku u src/ikonice folder
        dkf.setIconImage(ikonica.getImage());
        
        
        dkf.getContentPane().setBackground(new Color(230, 200, 255)); // Svetlo ljubičasta
        
        dkf.getjButtonDodaj().setBackground(new Color(138, 43, 226));
        dkf.getjButtonDodaj().setForeground(Color.white);
        
        dkf.getjButtonAžuriraj().setBackground(new Color(138, 43, 226));
        dkf.getjButtonAžuriraj().setForeground(Color.white);
        
        dkf.getjButtonOdustani().setBackground(new Color(138, 43, 226));
        dkf.getjButtonOdustani().setForeground(Color.white);
        
        dkf.getjButtonObrisi().setBackground(new Color(138, 43, 226));
        dkf.getjButtonObrisi().setForeground(Color.white);
        
        ImageIcon originalIkona = new ImageIcon(getClass().getResource("/slike/kupac2.png")); // Promeni ime ako treba
        Image slika = originalIkona.getImage().getScaledInstance(dkf.getjLabelSlika().getWidth(), dkf.getjLabelSlika().getHeight(), Image.SCALE_SMOOTH);
        dkf.getjLabelSlika().setIcon(new ImageIcon(slika));
    }
    
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private void addActionListener() {
        dkf.dodajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //
                dodaj(e);
            }

            private void dodaj(ActionEvent e) {

                try
                {
                    String imePrezime = dkf.getjTextFieldImePrezime().getText();

                    String email = dkf.getjTextFieldEmail().getText().trim();

                    String datumString = dkf.getjTextFieldDatumRodjenja().getText().trim();

                    // Uklanjamo tačku na kraju ako postoji
                    datumString = datumString.trim().replaceAll("\\.$", "");

                    LocalDate datum = LocalDate.parse(datumString, formatter);
                    
                    Mesto mesto =  (Mesto) dkf.getjComboBox2().getSelectedItem();
                    
                    if(datumString.isEmpty() || dkf.getjComboBox2().getSelectedItem() == null || email.isEmpty() || imePrezime.isEmpty()){
                        JOptionPane.showMessageDialog(dkf, "Niste popunili sva polja.", "UPOZORENJE", JOptionPane.WARNING_MESSAGE);
                    }

                    else{
                        if (datum.isAfter(LocalDate.now())) {
                            JOptionPane.showMessageDialog(dkf, "Datum ne može biti u budućnosti.", "UPOZORENJE",JOptionPane.WARNING_MESSAGE);

                        }else{
                            Kupac k = new Kupac(-1, imePrezime, email, datum, mesto);
                            try{
                                Komunikacija.getInstance().dodajKupca(k);
                                JOptionPane.showMessageDialog(dkf, "Sistem je zapamtio kupca.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                                dkf.dispose();

                            } catch(Exception exp){
                                JOptionPane.showMessageDialog(dkf, "Sistem ne može da zapamti kupca.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                                for (Window window : Window.getWindows()) {
                                    window.dispose();
                                }
                                Cordinator.getInstance().otvoriLoginFormu();
                                //exp.printStackTrace();
                            }
                        }
                    

                        
                
                
                    }
                        
                
                

            }  catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(dkf, "Datum nije ispravno unesen.", "UPOZORENJE", JOptionPane.WARNING_MESSAGE);
                }
            }
            
            
        });
        
        dkf.izmeniAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //
                izmeni(e);
            }

            private void izmeni(ActionEvent e) {
                
                try
                {
                
                    int id = Integer.parseInt(dkf.getjTextFieldId().getText());
                    String imePrezime = dkf.getjTextFieldImePrezime().getText();

                    String email = dkf.getjTextFieldEmail().getText().trim();

                    String datumString = dkf.getjTextFieldDatumRodjenja().getText().trim();

                    // Uklanjamo tačku na kraju ako postoji
                    datumString = datumString.trim().replaceAll("\\.$", "");

                    LocalDate datum = LocalDate.parse(datumString, formatter);
                    if(datumString.isEmpty() || dkf.getjComboBox2().getSelectedItem() == null || email.isEmpty() || imePrezime.isEmpty()){
                        JOptionPane.showMessageDialog(dkf, "Polja nisu pravilno popunjena.", "UPOZORENJE", JOptionPane.WARNING_MESSAGE);
                    }

                    else{

                        Mesto mesto =  (Mesto) dkf.getjComboBox2().getSelectedItem();
                        

                        Kupac k = new Kupac(id, imePrezime, email, datum, mesto);
                        try{
                            
                            Komunikacija.getInstance().azurirajKupca(k);
                            JOptionPane.showMessageDialog(dkf, "Sistem je zapamtio kupca.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                            dkf.dispose();

                        } catch(Exception exp){
                            JOptionPane.showMessageDialog(dkf, "Sistem ne može da zapamti kupca.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                            for (Window window : Window.getWindows()) {
                                window.dispose();
                            }
                            Cordinator.getInstance().otvoriLoginFormu();
                            //exp.printStackTrace();
                        }
                    }
                }  catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(dkf, "Datum nije ispravno unesen.", "UPOZORENJE", JOptionPane.WARNING_MESSAGE);
                }
            }        
                
                

            
        });
        
        dkf.addBtnObrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                    int odgovor = JOptionPane.showConfirmDialog(dkf, "Da li ste sigurni da želite da obrišete kupca?", "POTVRDA", JOptionPane.YES_NO_OPTION);
                        if(odgovor ==0){
                            
                            try{
                                Komunikacija.getInstance().obrisiKupca(kupac); 
                                Cordinator.getInstance().osveziTabeluKupaca();
                                JOptionPane.showMessageDialog(dkf, "Sistem je obrisao kupca.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                                dkf.dispose();
                                //pripremiFormu();

                            } catch(Exception exc){
                                JOptionPane.showMessageDialog(dkf, "Sistem ne moze da obriše kupca.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                                
                            }
                        }
                    
                }
            
        });
        
        dkf.odustaniAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //
                odustani(e);
            }

            private void odustani(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(dkf, "Da li stvarno želite da zatvorite formu?", "POTVDRA", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
       

        
                if (option == JOptionPane.YES_OPTION) {
                    dkf.dispose();
                }
                
                
            }

            
        });
    }

    private void pripremiFormu() throws IOException, ClassNotFoundException {
        List<Mesto> mesta = komunikacija.Komunikacija.getInstance().ucitajMesta();
        
        for (Mesto mesto : mesta) {
            dkf.getjComboBox2().addItem(mesto);
        }
    }

    private void pripremifomru(FormaMod mod) {
        switch (mod) {
            case DODAJ:
                dkf.getjButtonAžuriraj().setVisible(false);
                dkf.getjButtonDodaj().setVisible(true);
                dkf.getjButtonAžuriraj().setVisible(false);
                dkf.getjButtonObrisi().setVisible(false);
                dkf.getjTextFieldId().setEnabled(false);
                dkf.getjComboBox2().setSelectedIndex(-1);
                dkf.setTitle("Kreiranje kupca");
                break;
            case IZMENI:
                dkf.getjTextFieldId().setEnabled(false);
                dkf.getjButtonAžuriraj().setVisible(true);
                dkf.getjButtonDodaj().setVisible(false);
                dkf.getjButtonAžuriraj().setEnabled(true); 
                dkf.setTitle("Promena kupca");
                 
                Kupac k = kupac;
                dkf.getjTextFieldImePrezime().setText(k.getImePrezime());
                dkf.getjTextFieldEmail().setText(k.getEmail());
                
                String datum = k.getDatum().format(formatter);
                dkf.getjTextFieldDatumRodjenja().setText(datum + ".");
                
                dkf.getjComboBox2().setSelectedItem(k.getMesto());
                
                dkf.getjTextFieldId().setText(k.getIdKupac()+ "");
                
                break;
                
            case DETALJI:
                
                dkf.getjTextFieldId().setEnabled(false);
                dkf.getjButtonAžuriraj().setVisible(true);
                dkf.getjButtonDodaj().setVisible(false);
                dkf.getjButtonOdustani().setVisible(true);
                //dkf.getjButtonAžuriraj().setEnabled(true); 
                dkf.setTitle("Detalji o kupcu");
                 
                //dkf.getjTextFieldDatumRodjenja().setEnabled(false);
                //dkf.getjTextFieldEmail().setEnabled(false);
                //dkf.getjTextFieldImePrezime().setEnabled(false);
                //dkf.getjComboBox2().setEnabled(false);
                
                Kupac k1 = kupac;
                
                dkf.getjTextFieldImePrezime().setText(k1.getImePrezime());
                dkf.getjTextFieldEmail().setText(k1.getEmail());
                
                String datum1 = k1.getDatum().format(formatter);
                dkf.getjTextFieldDatumRodjenja().setText(datum1 + ".");
                
                
                dkf.getjComboBox2().setSelectedItem(k1.getMesto());
                
                dkf.getjTextFieldId().setText(k1.getIdKupac()+ "");
                break;
                
            default:
                throw new AssertionError();
        }
    }
    
    
    
}
