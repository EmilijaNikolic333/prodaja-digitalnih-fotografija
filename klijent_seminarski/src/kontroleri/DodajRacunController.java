/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import cordinator.Cordinator;
import forme.DodajRacunForma;
import forme.FormaMod;
import static forme.FormaMod.DODAJ;
import forme.model.ModelTabeleKupci;
import forme.model.ModelTabeleStavkeRacuna;
import java.awt.Color;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;
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
import model.ApstraktniModelskiObjekat;
import model.DigitalnaFotografija;
import model.Kupac;
import model.Mesto;
import model.Racun;
import model.StavkaRacuna;
import model.Umetnik;


/**
 *
 * @author nikol
 */
public class DodajRacunController {
    private final DodajRacunForma drf;
    List<StavkaRacuna> stavkeRacuna = new ArrayList<>();
    List<StavkaRacuna> stavkeZaIzmenu = new ArrayList<>();
    List<StavkaRacuna> stavkeZaDodati = new ArrayList<>();
    List<StavkaRacuna> stavkeZaObrisati = new ArrayList<>();
    Racun racun = new Racun();
    double ukupanIznos = 0;
    int redniBroj = 1;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    

    public DodajRacunController(DodajRacunForma drf) {
        this.drf = drf;
        addActionListener();
    }
    
    public void otvoriFormu(FormaMod mod, Racun racun) throws IOException, ClassNotFoundException{
        
        this.racun = racun;
        ImageIcon ikonica = new ImageIcon(getClass().getResource("/slike/digital-drawing (2).png"));  // Stavi sliku u src/ikonice folder
        drf.setIconImage(ikonica.getImage());
        
        
        drf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        drf.setVisible(true);
        
        drf.getContentPane().setBackground(new Color(230, 200, 255)); // Svetlo ljubičasta
        drf.getjButtonDodajStavku().setBackground(new Color(138, 43, 226));
        drf.getjButtonDodajStavku().setForeground(Color.white);
        
        drf.getjButtonObrisiStavku().setBackground(new Color(138, 43, 226));
        drf.getjButtonObrisiStavku().setForeground(Color.white);
        
        drf.getjButtonIzmeniRacun().setBackground(new Color(138, 43, 226));
        drf.getjButtonIzmeniRacun().setForeground(Color.white);
        
        drf.getjButtonKreiraj().setBackground(new Color(138, 43, 226));
        drf.getjButtonKreiraj().setForeground(Color.white);
        
        drf.getjButtonOdustani().setBackground(new Color(138, 43, 226));
        drf.getjButtonOdustani().setForeground(Color.white);
        
        
        
        // Postavljanje boje pozadine tabele
        drf.getjTableStavkeRacuna().setBackground(new Color(147, 112, 219)); 
        drf.getjTableStavkeRacuna().setForeground(Color.WHITE); 

        // Postavljanje boje zaglavlja tabele
        drf.getjTableStavkeRacuna().getTableHeader().setBackground(new Color(138, 43, 226)); // Tamno ljubičasta
        drf.getjTableStavkeRacuna().getTableHeader().setForeground(Color.WHITE); // Boja teksta u zaglavlju
        
        drf.getjScrollPane1().getViewport().setBackground(new Color(147, 112, 219)); // Svetlo ljubičasta
        drf.getjScrollPane1().setBackground(new Color(147, 112, 219)); // Promena boje ivica
        drf.getjPanelStavka().setBackground(new Color(147, 112, 219)); // Promena boje panela unutar JScrollPane
        
        
        drf.getjTableStavkeRacuna().setGridColor(Color.BLACK); // Crne linije između redova i kolona
        
        drf.getjTableStavkeRacuna().setIntercellSpacing(new java.awt.Dimension(1, 1)); // Podešava razmak između ćelija (debljina linija)

        ImageIcon originalIkona = new ImageIcon(getClass().getResource("/slike/racun1.png")); // Promeni ime ako treba
        Image slika = originalIkona.getImage().getScaledInstance(drf.getjLabelSlika().getWidth(), drf.getjLabelSlika().getHeight(), Image.SCALE_SMOOTH);
        drf.getjLabelSlika().setIcon(new ImageIcon(slika));
    
        
        drf.getjTableStavkeRacuna().setShowGrid(false); // Omogućava prikaz linija
    
        pripremiFormu();
        pripremiStavku();
        pripremifomru(mod);
        
        centrirajTekst(drf.getjTableStavkeRacuna());
    }

    

    private void pripremifomru(FormaMod mod) throws IOException, ClassNotFoundException {
        switch (mod) {
            case DODAJ:
                drf.getjTableStavkeRacuna().setShowGrid(false); // Omogućava prikaz linija
                drf.getjPanelStavka().setOpaque(true); // Omogućava da boja bude vidljiva
                drf.getjTextFieldIdRacun().setEnabled(false);
                drf.getjButtonKreiraj().setVisible(true);
                drf.getjButtonIzmeniRacun().setVisible(false);
                drf.getjTextFieldUkupanIznos().setEnabled(false);
                drf.getjComboBoxUmetnik().setSelectedIndex(-1);
                drf.getjComboBoxKupac().setSelectedIndex(-1);
                drf.setTitle("Kreiranje računa");
                
                
                break;
            case IZMENI:
                
                drf.getjTableStavkeRacuna().setShowGrid(true); // Omogućava prikaz linija
                drf.getjPanelStavka().setOpaque(true); // Omogućava da boja bude vidljiva
                drf.getjTextFieldIdRacun().setEnabled(false);
                drf.getjButtonKreiraj().setVisible(false);
                drf.getjButtonIzmeniRacun().setVisible(true);
                drf.getjTextFieldUkupanIznos().setEnabled(false);
                drf.setTitle("Promena računa");
                
                Racun r = racun;
                
                drf.getjTextFieldIdRacun().setText(r.getIdRačun() + "");
                
                drf.getjComboBoxUmetnik().setSelectedItem(r.getUmetnik());
                
                drf.getjComboBoxKupac().setSelectedItem(r.getKupac());
                
                
                
                String datum = r.getDatum().format(formatter);
                drf.getjTextFieldDatum().setText(datum + ".");
                
                
                
                List<StavkaRacuna> stavke = r.getStavke();
                stavkeRacuna = r.getStavke();
                
                
                for (StavkaRacuna stavkaRacuna : stavke) {
                    
                        ukupanIznos += stavkaRacuna.getIznos();
                    
                }
                
                
                ModelTabeleStavkeRacuna mtsr = new ModelTabeleStavkeRacuna(stavke);
                drf.getjTableStavkeRacuna().setModel(mtsr);
                
                drf.getjTextFieldUkupanIznos().setText(ukupanIznos + "");
                
                break;
                
            case DETALJI:
                drf.getjTableStavkeRacuna().setShowGrid(true); // Omogućava prikaz linija
                //drf.getjPanelStavka().setOpaque(false); // Omogućava da boja bude vidljiva
                drf.getjTextFieldIdRacun().setEnabled(false);
                drf.getjButtonKreiraj().setVisible(false);
                drf.getjButtonOdustani().setVisible(true);
                drf.getjButtonIzmeniRacun().setVisible(true);
                drf.getjTextFieldUkupanIznos().setEnabled(false);
                drf.setTitle("Detalji o računu");
                
                //drf.getjButtonDodajStavku().setVisible(false);
                //drf.getjButtonObrisiStavku().setVisible(false);
                
                //drf.getjLabelDigiFoto().setVisible(false);
                //drf.getjLabelKolicina().setVisible(false);
                
                //drf.getjTextFieldKolicina().setVisible(false);
                //drf.getjComboBoxDigitalnaFotografija().setVisible(false);
                
                //drf.getjComboBoxKupac().setEnabled(false);
                //drf.getjComboBoxUmetnik().setEnabled(false);
                
                //drf.getjTextFieldDatum().setEnabled(false);
                
                
                Racun r1 = racun;
                
                drf.getjTextFieldIdRacun().setText(r1.getIdRačun() + "");
                
                drf.getjComboBoxUmetnik().setSelectedItem(r1.getUmetnik());
                
                drf.getjComboBoxKupac().setSelectedItem(r1.getKupac());
                
                
                
                String datum1 = r1.getDatum().format(formatter);
                drf.getjTextFieldDatum().setText(datum1 + ".");
                
                List<StavkaRacuna> stavke1 = r1.getStavke();
                stavkeRacuna = r1.getStavke();
                
                for (StavkaRacuna stavkaRacuna : stavke1) {
                    
                        ukupanIznos += stavkaRacuna.getIznos();
                    
                }
                
                
                
                ModelTabeleStavkeRacuna mtsr1 = new ModelTabeleStavkeRacuna(stavke1);
                drf.getjTableStavkeRacuna().setModel(mtsr1);
                
                drf.getjTextFieldUkupanIznos().setText(ukupanIznos + "");
                break;
                
            default:
                throw new AssertionError();
        }
        
    }

    private void pripremiFormu() throws IOException, ClassNotFoundException {
        List<Kupac> kupci = komunikacija.Komunikacija.getInstance().ucitajKupce();
        List<Umetnik> umetnici = komunikacija.Komunikacija.getInstance().ucitajUmetnika();
        
        for (Umetnik umetnik : umetnici) {
            drf.getjComboBoxUmetnik().addItem(umetnik);
        }
        
        for (Kupac kupac : kupci) {
            drf.getjComboBoxKupac().addItem(kupac);
        }
    }

    public void pripremiStavku() throws IOException, ClassNotFoundException {
        
        List<DigitalnaFotografija> digitalneFotografije = komunikacija.Komunikacija.getInstance().ucitajDigitalneFotografije();
        
        drf.getjComboBoxDigitalnaFotografija().removeAllItems();
        
        for (DigitalnaFotografija digitalnaFotografija : digitalneFotografije) {
            drf.getjComboBoxDigitalnaFotografija().addItem(digitalnaFotografija);
        }
        
        drf.getjComboBoxDigitalnaFotografija().setSelectedIndex(-1);
        
        drf.getjTextFieldKolicina().setText("1");
        
        
    }
    
    
    private void addActionListener() {
        drf.dodajStavkuAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //
                dodajStavku(e);
            }

            private void dodajStavku(ActionEvent e) {
                
                
                DigitalnaFotografija digitialnaFotografija = (DigitalnaFotografija) drf.getjComboBoxDigitalnaFotografija().getSelectedItem();
                //int dfID = digitialnaFotografija.getIdDigitalnaFotografija();
                
                
              
                if(digitialnaFotografija == null){
                    JOptionPane.showMessageDialog(drf, "Niste izabrali digitalnu fotografiju.", "UPOZORENJE", JOptionPane.WARNING_MESSAGE);
                }else if((drf.getjTextFieldKolicina().getText().trim()).isEmpty()){
                    JOptionPane.showMessageDialog(drf, "Niste izabrali željenu količinu.", "UPOZORENJE", JOptionPane.WARNING_MESSAGE);
                }else{
                
                    double cenaFotografije = digitialnaFotografija.getCena();
                
                    int kolicina = Integer.parseInt(drf.getjTextFieldKolicina().getText().trim());
                    
                    int redniBroj = stavkeRacuna.size() + 1;
                    

                    double iznos = cenaFotografije * kolicina;

                    ukupanIznos += iznos;

                    drf.getjTextFieldUkupanIznos().setText(ukupanIznos + "");
                
                    StavkaRacuna stra = new StavkaRacuna(redniBroj, iznos, kolicina, cenaFotografije, digitialnaFotografija);

                    
                    
                    //stavkeZaDodati.add(stra);

                    stavkeRacuna.add(stra);
                    if(stavkeRacuna.size() != 0){
                        drf.getjTableStavkeRacuna().setShowGrid(true); // Omogućava prikaz linija
                    }


                    ModelTabeleStavkeRacuna mtsr = new ModelTabeleStavkeRacuna(stavkeRacuna);
                    drf.getjTableStavkeRacuna().setModel(mtsr); 
                    
                    centrirajTekst(drf.getjTableStavkeRacuna());
                
                }

            }
        });
        drf.izbrisiStavkuAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //
                izbrisiStavku(e);
            }

            private void izbrisiStavku(ActionEvent e) {
                
                int red = drf.getjTableStavkeRacuna().getSelectedRow();
                if(red == -1){
                    JOptionPane.showMessageDialog(drf, "Niste selektovali stavku za brisanje.", "UPOZORENJE", JOptionPane.WARNING_MESSAGE);
                } else{
                
    
                    


                    ModelTabeleStavkeRacuna mtsr = (ModelTabeleStavkeRacuna) drf.getjTableStavkeRacuna().getModel();
                    StavkaRacuna stra = mtsr.getLista().get(red);
                    
                    int redni = stra.getRb();
                    
                    ukupanIznos -= stra.getIznos();
                
                    drf.getjTextFieldUkupanIznos().setText(ukupanIznos + "");

                    //stavkeZaObrisati.add(stra);
                    
                    stavkeRacuna.remove(stra);
                    
                    for (int i = redni - 1; i < stavkeRacuna.size(); i++) {
                        stavkeRacuna.get(i).setRb(i + 1); // rb kreće od 1
                    }


                    mtsr = new ModelTabeleStavkeRacuna(stavkeRacuna);
                    drf.getjTableStavkeRacuna().setModel(mtsr); 
                    
                    centrirajTekst(drf.getjTableStavkeRacuna());
                }
                

            }
        });
        drf.dodajRacunAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //
                dodajRacun(e);
            }

            private void dodajRacun(ActionEvent e) {
                
                
                
                try{
                    
                    String datumString = drf.getjTextFieldDatum().getText().trim();
                    // Uklanjamo tačku na kraju ako postoji
                    datumString = datumString.trim().replaceAll("\\.$", "");

                    LocalDate datum = LocalDate.parse(datumString, formatter);


                    Umetnik umetnik = (Umetnik) drf.getjComboBoxUmetnik().getSelectedItem(); 

                    Kupac kupac = (Kupac) drf.getjComboBoxKupac().getSelectedItem();

                    if(datumString.isEmpty() || drf.getjComboBoxUmetnik().getSelectedItem() == null || drf.getjComboBoxKupac().getSelectedItem() == null || ukupanIznos == 0){
                        JOptionPane.showMessageDialog(drf, "Niste popunili sva polja.", "UPOZORENJE", JOptionPane.WARNING_MESSAGE);
                    }
                    else{
                        if (datum.isAfter(LocalDate.now())) {
                            JOptionPane.showMessageDialog(drf, "Datum ne može biti u budućnosti.", "UPOZORENJE",JOptionPane.WARNING_MESSAGE);

                        }else{
                            Racun racun = new Racun(datum, ukupanIznos, umetnik, kupac, stavkeRacuna);

                        try{



                            Komunikacija.getInstance().dodajRacun(racun);

                            JOptionPane.showMessageDialog(drf, "Sistem je zapamtio račun.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                            drf.dispose();



                        } catch(Exception exp){
                            JOptionPane.showMessageDialog(drf, "Sistem ne može da zapamti račun.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                            for (Window window : Window.getWindows()) {
                                    window.dispose();
                                }
                             Cordinator.getInstance().otvoriLoginFormu();
                            //exp.printStackTrace();
                        }
                        }
                        
                        
                    }

                
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(drf, "Datum nije ispravno unesen.", "UPOZORENJE", JOptionPane.WARNING_MESSAGE);
                }
                
                
                
                

            }
        });
        drf.izmeniRacunAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //
                izmeniRacun(e);
            }

            private void izmeniRacun(ActionEvent e) {
                try{
                    int idRacun =  Integer.parseInt(drf.getjTextFieldIdRacun().getText().trim());

                    String datumString = drf.getjTextFieldDatum().getText().trim();

                    // Uklanjamo tačku na kraju ako postoji
                    datumString = datumString.trim().replaceAll("\\.$", "");

                    LocalDate datum = LocalDate.parse(datumString, formatter);

                    Umetnik umetnik = (Umetnik) drf.getjComboBoxUmetnik().getSelectedItem(); 

                    Kupac kupac = (Kupac) drf.getjComboBoxKupac().getSelectedItem();

                    double ukupIznos = Double.parseDouble(drf.getjTextFieldUkupanIznos().getText().trim());

                    if(datumString.isEmpty() || drf.getjComboBoxUmetnik().getSelectedItem() == null || drf.getjComboBoxKupac().getSelectedItem() == null || ukupanIznos == 0){
                        JOptionPane.showMessageDialog(drf, "Polja nisu pravilno popunjena.", "UPOZORENJE", JOptionPane.WARNING_MESSAGE);
                    }
                    else{

                        Racun racun = new Racun(idRacun, datum, ukupIznos, umetnik, kupac, stavkeRacuna);

                        try{
                            Komunikacija.getInstance().izmeniRacun(racun);

                           


                            JOptionPane.showMessageDialog(drf, "Sistem je zapamtio račun.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                            drf.dispose();

                        } catch(Exception exp){
                            JOptionPane.showMessageDialog(drf, "Sistem ne može da zapamti račun.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                            for (Window window : Window.getWindows()) {
                                window.dispose();
                            }
                            Cordinator.getInstance().otvoriLoginFormu();
                            //exp.printStackTrace();
                        }
                    }

                }  catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(drf, "Datum nije ispravno unesen.", "UPOZORENJE", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        
        drf.odustaniAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //
                odustani(e);
            }

            private void odustani(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(drf, "Da li stvarno želite da zatvorite formu?", "POTVDRA", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
       

        
                if (option == JOptionPane.YES_OPTION) {
                    drf.dispose();
                }
                
                
            }

            
        });
    }
    
    
    public void centrirajTekst(JTable tabela){
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        
        for(int i = 0; i < tabela.getColumnCount(); i++){
            tabela.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            
        }
    }
}
