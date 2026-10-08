/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import cordinator.Cordinator;
import forme.DodajKupcaForma;
import forme.DodajRacunForma;
import forme.DodajUmetnikaForma;
import forme.FormaMod;
import static forme.FormaMod.DETALJI;
import static forme.FormaMod.DODAJ;
import static forme.FormaMod.IZMENI;
import forme.model.ModelTabeleStavkeRacuna;
import forme.model.ModelTabeleStrucnaSprema;
import forme.model.ModelTabeleUmetnici;
import forme.model.ModelTabeleUmss;
import java.awt.Color;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableCellRenderer;
import komunikacija.Komunikacija;
import model.DigitalnaFotografija;
import model.Kupac;
import model.Mesto;
import model.StavkaRacuna;
import model.StrucnaSprema;
import model.UmSS;
import model.Umetnik;

/**
 *
 * @author nikol
 */
public class DodajUmetnikaController {
    private final DodajUmetnikaForma duf;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    List<UmSS> umetniciStrucneSpreme = new ArrayList<>();
    private Umetnik umetnik = new Umetnik();

    public DodajUmetnikaController(DodajUmetnikaForma duf) {
        this.duf = duf;
        addActionListener();
    }
    
    public void otvoriFormu(FormaMod mod, Umetnik umetnik1) throws IOException, ClassNotFoundException{
        //pripremiFormu();
        
        umetnik = umetnik1;
        
        
        ImageIcon ikonica = new ImageIcon(getClass().getResource("/slike/digital-drawing (2).png"));  // Stavi sliku u src/ikonice folder
        duf.setIconImage(ikonica.getImage());
        
        duf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        duf.setVisible(true);
        
        duf.getContentPane().setBackground(new Color(230, 200, 255)); // Svetlo ljubičasta
        duf.getjButtonSačuvajStručnuSpremu().setBackground(new Color(138, 43, 226));
        duf.getjButtonSačuvajStručnuSpremu().setForeground(Color.white);
        
        duf.getjButtonObrišiStručnuSpremu().setBackground(new Color(138, 43, 226));
        duf.getjButtonObrišiStručnuSpremu().setForeground(Color.white);
        
        duf.getjButtonIzmeniUmetnika().setBackground(new Color(138, 43, 226));
        duf.getjButtonIzmeniUmetnika().setForeground(Color.white);
        
        duf.getjButtonKreirajUmetnika().setBackground(new Color(138, 43, 226));
        duf.getjButtonKreirajUmetnika().setForeground(Color.white);
        
        duf.getjButtonPrikaziSifru().setBackground(new Color(138, 43, 226));
        duf.getjButtonPrikaziSifru().setForeground(Color.white);
        
        duf.getjButtonOdustani().setBackground(new Color(138, 43, 226));
        duf.getjButtonOdustani().setForeground(Color.white);
        
        duf.getjButtonObrisi().setBackground(new Color(138, 43, 226));
        duf.getjButtonObrisi().setForeground(Color.white);
        
        // Postavljanje boje pozadine tabele
        duf.getjTableStrucneSpreme().setBackground(new Color(147, 112, 219)); 
        duf.getjTableStrucneSpreme().setForeground(Color.WHITE); 

        // Postavljanje boje zaglavlja tabele
        duf.getjTableStrucneSpreme().getTableHeader().setBackground(new Color(138, 43, 226)); // Tamno ljubičasta
        duf.getjTableStrucneSpreme().getTableHeader().setForeground(Color.WHITE); // Boja teksta u zaglavlju
        
        duf.getjScrollPane1().getViewport().setBackground(new Color(147, 112, 219)); // Svetlo ljubičasta
        duf.getjScrollPane1().setBackground(new Color(147, 112, 219)); // Promena boje ivica
        duf.getjPanel1().setBackground(new Color(147, 112, 219)); // Promena boje panela unutar JScrollPane
        
        
        duf.getjTableStrucneSpreme().setGridColor(Color.BLACK); // Crne linije između redova i kolona
        
        duf.getjTableStrucneSpreme().setIntercellSpacing(new java.awt.Dimension(1, 1)); // Podešava razmak između ćelija (debljina linija)
        
        
        ImageIcon originalIkona = new ImageIcon(getClass().getResource("/slike/umetnik1.png")); 
        Image slika = originalIkona.getImage().getScaledInstance(duf.getjLabelSlika().getWidth(), duf.getjLabelSlika().getHeight(), Image.SCALE_SMOOTH);
        duf.getjLabelSlika().setIcon(new ImageIcon(slika));
        
        
        duf.getjTableStrucneSpreme().setShowGrid(false); // Omogućava prikaz linija
        
        pripremiStrucnuSpremu();
        pripremifomru(mod);
        
        centrirajTekst(duf.getjTableStrucneSpreme());
        
    }
    
    private void pripremiFormu() {
        
    }

    private void pripremifomru(FormaMod mod) throws IOException, ClassNotFoundException {
        switch (mod) {
            case DODAJ:
                duf.getjTableStrucneSpreme().setShowGrid(false); // Omogućava prikaz linija
                duf.getjPanel1().setOpaque(true); // Omogućava da boja bude vidljiva
                duf.getjButtonObrisi().setVisible(false);
                duf.getjButtonIzmeniUmetnika().setVisible(false);
                duf.getjButtonKreirajUmetnika().setVisible(true);
                duf.getjTextFieldIdUmetnika().setEnabled(false);
                duf.setTitle("Kreiranje umetnika");
                
                break;
            case IZMENI:
                duf.getjTableStrucneSpreme().setShowGrid(true); // Omogućava prikaz linija
                duf.getjPanel1().setOpaque(true);
                duf.getjButtonIzmeniUmetnika().setVisible(true);
                duf.getjButtonKreirajUmetnika().setVisible(false);
                duf.getjTextFieldIdUmetnika().setEnabled(false);
                duf.setTitle("Promena umetnika");
                
                
                
                Umetnik u = umetnik;
                
                
                
                duf.getjTextFieldIdUmetnika().setText(u.getIdUmetnik() + "");
                duf.getjTextFieldImeUmetnika().setText(u.getIme());
                duf.getjTextFieldPrezimeUmetnika().setText(u.getPrezime());
                duf.getjTextFieldKorisnickoIme().setText(u.getKorisničkoIme());
                duf.getjPasswordFieldSifra().setText(u.getŠifra());
                
                umetniciStrucneSpreme = u.getUmetnikStrucnaSprema();
                
                
                
                ModelTabeleUmss mtus = new ModelTabeleUmss(umetniciStrucneSpreme);
                duf.getjTableStrucneSpreme().setModel(mtus);
                
                break;
                
            case DETALJI:
                
                duf.getjTableStrucneSpreme().setShowGrid(true); // Omogućava prikaz linija
                //duf.getjPanel1().setOpaque(false); // Omogućava da boja bude vidljiva
                //duf.getjButtonIzmeniUmetnika().setVisible(false);
                duf.getjButtonKreirajUmetnika().setVisible(false);
                //duf.getjButtonOdustani().setVisible(false);
                duf.getjTextFieldIdUmetnika().setEnabled(false);
                //duf.getjTextFieldImeUmetnika().setEnabled(false);
                //duf.getjTextFieldPrezimeUmetnika().setEnabled(false);
                //duf.getjTextFieldKorisnickoIme().setEnabled(false);
                //duf.getjPasswordFieldSifra().setEnabled(false);
                //duf.getjButtonPrikaziSifru().setVisible(false);
                //duf.getjPasswordFieldSifra().setEchoChar((char) 0); // Prikazuje lozinku
                duf.setTitle("Detalji o umetniku");
                
                //duf.getjButtonSačuvajStručnuSpremu().setVisible(false);
                //duf.getjButtonSačuvajStručnuSpremu().setVisible(false);
                
                //duf.getjButtonObrišiStručnuSpremu().setVisible(false);
                //duf.getjButtonObrišiStručnuSpremu().setVisible(false);
                
                //duf.getjLabelStrucnaSprema().setVisible(false);
                //duf.getjLabelDatum().setVisible(false);
                //duf.getjLabelUstanova().setVisible(false);
                
                //duf.getjTextFieldDatumZavršetka().setVisible(false);
                //duf.getjTextFieldUstanova().setVisible(false);
                //duf.getjComboBoxStručnaSprema().setVisible(false);
                
                
                
                
                Umetnik u1 = umetnik;
                
                duf.getjTextFieldIdUmetnika().setText(u1.getIdUmetnik() + "");
                duf.getjTextFieldImeUmetnika().setText(u1.getIme());
                duf.getjTextFieldPrezimeUmetnika().setText(u1.getPrezime());
                duf.getjTextFieldKorisnickoIme().setText(u1.getKorisničkoIme());
                duf.getjPasswordFieldSifra().setText(u1.getŠifra());
                
                
                
                umetniciStrucneSpreme = u1.getUmetnikStrucnaSprema();
                
                
                
                ModelTabeleUmss mtus1 = new ModelTabeleUmss(umetniciStrucneSpreme);
                duf.getjTableStrucneSpreme().setModel(mtus1);
                
                
                
                
                break;
                
            default:
                throw new AssertionError();
        }
    }
    
    
    private void addActionListener() {
        duf.dodajStrucnuSpremuAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //
                dodajStrucnuSpremu(e);
            }

            private void dodajStrucnuSpremu(ActionEvent e) {
                
                
                try{
                    
                StrucnaSprema strucnaSprema = (StrucnaSprema) duf.getjComboBoxStručnaSprema().getSelectedItem();
                
                String ustanova = duf.getjTextFieldUstanova().getText();
                
                String datumString = duf.getjTextFieldDatumZavršetka().getText().trim();
                
                    // Uklanjamo tačku na kraju ako postoji
                    datumString = datumString.trim().replaceAll("\\.$", "");

                    LocalDate datum = LocalDate.parse(datumString, formatter);
                    
                if(strucnaSprema == null || ustanova.trim().isEmpty() || datumString.isEmpty()){
                    JOptionPane.showMessageDialog(duf, "Niste popunili sva polja.", "UPOZORENJE", JOptionPane.WARNING_MESSAGE);
                } else{
                    if (datum.isAfter(LocalDate.now())) {
                        JOptionPane.showMessageDialog(duf, "Datum ne može biti u budućnosti.", "UPOZORENJE",JOptionPane.WARNING_MESSAGE);
                        
                    }else{
                        UmSS umss = new UmSS(strucnaSprema, datum, ustanova);
                
                    
                    
                        umetniciStrucneSpreme.add(umss);

                        if(umetniciStrucneSpreme.size() != 0){
                            duf.getjTableStrucneSpreme().setShowGrid(true); // Omogućava prikaz linija
                        }



                        ModelTabeleUmss mtumss = new ModelTabeleUmss(umetniciStrucneSpreme);
                        duf.getjTableStrucneSpreme().setModel(mtumss); 

                        centrirajTekst(duf.getjTableStrucneSpreme());
                    }
                    
                    
                
                }

            
            
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(duf, "Datum nije ispravno unesen.", "UPOZORENJE", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        duf.izbrisiStrucnuSpremuAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //
                izbrisiStrucnuSpremu(e);
            }

            private void izbrisiStrucnuSpremu(ActionEvent e) {
                
                int red = duf.getjTableStrucneSpreme().getSelectedRow();
                if(red == -1){
                    JOptionPane.showMessageDialog(duf, "Niste selektovali stručnu spremu za brisanje.", "UPOZORENJE", JOptionPane.WARNING_MESSAGE);
                } else{
                
    
                    


                    ModelTabeleUmss mtumss1 = (ModelTabeleUmss) duf.getjTableStrucneSpreme().getModel();
                    
                    UmSS umss = mtumss1.getLista().get(red);
                    
                    StrucnaSprema ss = umss.getStručnaSprema();
                    LocalDate datumZavršetka = umss.getDatumZavršetka();
                    String ustanova = umss.getUstanova();
                    
                    
                    umetniciStrucneSpreme.remove(umss);
                    
                    
                    
                   


                    mtumss1 = new ModelTabeleUmss(umetniciStrucneSpreme);
                    duf.getjTableStrucneSpreme().setModel(mtumss1); 
                    
                    centrirajTekst(duf.getjTableStrucneSpreme());
                }
                

            }
        });
        
        duf.addBtnObrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                    int odgovor = JOptionPane.showConfirmDialog(duf, "Da li ste sigurni da želite da obrišete umetnika?", "POTVRDA", JOptionPane.YES_NO_OPTION);
                        if(odgovor ==0){
                            
                            Umetnik u = umetnik;
                            Umetnik ulogovani = cordinator.Cordinator.getInstance().getUlogovani();
                            try{

                                    if(ulogovani.equals(u)){
                                        JOptionPane.showMessageDialog(duf, "Sistem ne moze da obriše umetnika jer je on trenutno ulogovan.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                                        return;
                                    }

                                    
                                        Komunikacija.getInstance().obrisiUmetnika(u); 
                                        Cordinator.getInstance().osveziUmetnikFormu();
                                        JOptionPane.showMessageDialog(duf, "Sistem je obrisao umetnika.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                                        duf.dispose();
                                        
                                    
                                    
                                   


                            } catch(Exception exc){

                                JOptionPane.showMessageDialog(duf, "Sistem ne moze da obriše umetnika.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                                
                            }
                        }
                    
                
            }
        });
        
        duf.dodajUmetnikaAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //
                dodaj(e);
            }

            private void dodaj(ActionEvent e) {

                
                    String ime = duf.getjTextFieldImeUmetnika().getText().trim();
                    
                    String prezime = duf.getjTextFieldPrezimeUmetnika().getText().trim();

                    String korisnickoIme = duf.getjTextFieldKorisnickoIme().getText().trim();
                    
                    String sifra = duf.getjPasswordFieldSifra().getText().trim();
                    
                    
                    
                    
                    
                    if(ime.isEmpty() || prezime.isEmpty() || korisnickoIme.isEmpty() || sifra.isEmpty()){
                        JOptionPane.showMessageDialog(duf, "Polja nisu pravilno popunjena.", "UPOZORENJE", JOptionPane.WARNING_MESSAGE);
                    }

                    else{
                        
                        
                        Umetnik umetnik = new Umetnik(ime, prezime, korisnickoIme, sifra, umetniciStrucneSpreme);
                        
                        
                        try{
                            Umetnik u = (Umetnik)Komunikacija.getInstance().dodajUmetnika(umetnik);
                            
                            
                            
                            JOptionPane.showMessageDialog(duf, "Sistem je zapamtio umetnika.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                            
                            
                            
                            
                            duf.dispose();

                        } catch(Exception exp){
                            JOptionPane.showMessageDialog(duf, "Sistem ne može da zapamti umetnika.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                            for (Window window : Window.getWindows()) {
                                window.dispose();
                            }
                            Cordinator.getInstance().otvoriLoginFormu();
                            //exp.printStackTrace();
                        }
                
                
                    }
                        
                
                

            
            }
            
            
        });
        duf.izmeniUmetnikaAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //
                izmeni(e);
            }

            private void izmeni(ActionEvent e) {

                
                    int idUmetnik = Integer.parseInt(duf.getjTextFieldIdUmetnika().getText());
                            
                    String ime = duf.getjTextFieldImeUmetnika().getText().trim();
                    
                    String prezime = duf.getjTextFieldPrezimeUmetnika().getText().trim();

                    String korisnickoIme = duf.getjTextFieldKorisnickoIme().getText().trim();
                    
                    String sifra = duf.getjPasswordFieldSifra().getText().trim();
                    
                    
                    
                    
                    
                    if(ime.isEmpty() || prezime.isEmpty() || korisnickoIme.isEmpty() || sifra.isEmpty()){
                        JOptionPane.showMessageDialog(duf, "Polja nisu pravilno popunjena.", "UPOZORENJE", JOptionPane.WARNING_MESSAGE);
                    }

                    else{
                    
                        

                        Umetnik umetnik1 = new Umetnik(idUmetnik, ime, prezime, korisnickoIme, sifra, umetniciStrucneSpreme);
                        
                        try{
                            komunikacija.Komunikacija.getInstance().promeniUmetnika(umetnik1);
                            JOptionPane.showMessageDialog(duf, "Sistem je zapamtio umetnika.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                            duf.dispose();

                        } catch(Exception exp){
                            JOptionPane.showMessageDialog(duf, "Sistem ne može da zapamti umetnika.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                            for (Window window : Window.getWindows()) {
                                window.dispose();
                            }
                            Cordinator.getInstance().otvoriLoginFormu();
                            //exp.printStackTrace();
                        }
                
                
                    }
                        
                
                

            
            }
            
            
        });
        
        duf.odustaniAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //
                odustani(e);
            }

            private void odustani(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(duf, "Da li stvarno želite da zatvorite formu?", "POTVDRA", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
       

        
                if (option == JOptionPane.YES_OPTION) {
                    duf.dispose();
                }
                
                
            }

            
        });
        
        duf.prikažiŠifruAddActionListener(new ActionListener() {
            private boolean prikazana = false;
            @Override
            public void actionPerformed(ActionEvent e) {
                prikazi(e);
            }

            private void prikazi(ActionEvent e) {
                
                
                if (prikazana) {
                    duf.getjPasswordFieldSifra().setEchoChar('*'); // Vraća zvezdice
                    duf.getjButtonPrikaziSifru().setText("prikaži šifru");
                } else {
                    duf.getjPasswordFieldSifra().setEchoChar((char) 0); // Prikazuje lozinku
                    duf.getjButtonPrikaziSifru().setText("sakrij šifru");
                }
                prikazana = !prikazana;
                
                
                
                

            }
        });
        
    }

    private void pripremiStrucnuSpremu() throws IOException, ClassNotFoundException {
        List<StrucnaSprema> strucneSpreme = komunikacija.Komunikacija.getInstance().ucitajStrucneSpreme();
        
        duf.getjComboBoxStručnaSprema().removeAllItems();
        
        for (StrucnaSprema ss : strucneSpreme) {
            duf.getjComboBoxStručnaSprema().addItem(ss);
        }
        
        duf.getjComboBoxStručnaSprema().setSelectedIndex(-1);
        
        
    }

    
    public void centrirajTekst(JTable tabela){
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        
        for(int i = 0; i < tabela.getColumnCount(); i++){
            tabela.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            
        }
    }
    
    
}
