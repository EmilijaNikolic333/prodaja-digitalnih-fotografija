/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;


import cordinator.Cordinator;
import forme.PrikazRacunaForma;
import forme.model.ModelTabeleKupci;
import forme.model.ModelTabeleRacuni;
import forme.model.ModelTabeleStavkeRacuna;
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

/**
 *
 * @author nikol
 */
public class PrikazRacunaController {
    private final PrikazRacunaForma prf;
    List<StavkaRacuna> stavkeRacuna = new ArrayList<>();

    public PrikazRacunaController(PrikazRacunaForma prf) {
        this.prf = prf;
        addActionListener();
        ButtonGroup grupa = new ButtonGroup();
        grupa.add(prf.getjRadioButtonDigitalnaFotografija());
        grupa.add(prf.getjRadioButtonKupac());
        grupa.add(prf.getjRadioButtonUmetnik());
        grupa.add(prf.getjRadioButtonIznos());
    }

    public void otvoriFormu() throws IOException, ClassNotFoundException {
        pripremiFormu();
        prf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        prf.setVisible(true);
        ImageIcon ikonica = new ImageIcon(getClass().getResource("/slike/digital-drawing (2).png"));  // Stavi sliku u src/ikonice folder
        prf.setIconImage(ikonica.getImage());
        prf.setTitle("Računi");
        
        prf.getContentPane().setBackground(new Color(230, 200, 255)); // Svetlo ljubičasta
        prf.getjButtonPretrazi().setBackground(new Color(138, 43, 226));
        prf.getjButtonPretrazi().setForeground(Color.white);
        
        prf.getjButtonResetuj().setBackground(new Color(138, 43, 226));
        prf.getjButtonResetuj().setForeground(Color.white);
        
        prf.getjButtonPrikaziDetalje().setBackground(new Color(138, 43, 226));
        prf.getjButtonPrikaziDetalje().setForeground(Color.white);
        
        
        
        
        
        // Postavljanje boje pozadine tabele
        prf.getjTableRacuni().setBackground(new Color(147, 112, 219)); 
        prf.getjTableRacuni().setForeground(Color.WHITE); 

        // Postavljanje boje zaglavlja tabele
        prf.getjTableRacuni().getTableHeader().setBackground(new Color(138, 43, 226)); // Tamno ljubičasta
        prf.getjTableRacuni().getTableHeader().setForeground(Color.WHITE); // Boja teksta u zaglavlju
        
        prf.getjScrollPane1().getViewport().setBackground(new Color(147, 112, 219)); // Svetlo ljubičasta
        
        prf.getjTableRacuni().setGridColor(Color.BLACK); // Crne linije između redova i kolona
        prf.getjTableRacuni().setShowGrid(true); // Omogućava prikaz linija
        prf.getjTableRacuni().setIntercellSpacing(new java.awt.Dimension(1, 1)); // Podešava razmak između ćelija (debljina linija)

        ImageIcon originalIkona = new ImageIcon(getClass().getResource("/slike/racun1.png")); // Promeni ime ako treba
        Image slika = originalIkona.getImage().getScaledInstance(prf.getjLabelSlika().getWidth(), prf.getjLabelSlika().getHeight(), Image.SCALE_SMOOTH);
        prf.getjLabelSlika().setIcon(new ImageIcon(slika));
        
        prf.getjRadioButtonDigitalnaFotografija().setBackground(new Color(230, 200, 255));
        prf.getjRadioButtonKupac().setBackground(new Color(230, 200, 255));
        prf.getjRadioButtonUmetnik().setBackground(new Color(230, 200, 255));
        prf.getjRadioButtonIznos().setBackground(new Color(230, 200, 255));
        
        prf.getjTextFieldDigitalnaFotografija().setEditable(false);
        prf.getjTextFieldIznos().setEditable(false);
        prf.getjTextFieldKupac().setEditable(false);
        prf.getjTextFieldUmetnik().setEditable(false);
        
    }

    public void pripremiFormu() throws IOException, ClassNotFoundException  {
        List<Racun> racuni = komunikacija.Komunikacija.getInstance().ucitajRacune();
        
        
        ModelTabeleRacuni mtr = new ModelTabeleRacuni(racuni);
        prf.getjTableRacuni().setModel(mtr);
        
        centrirajTekst(prf.getjTableRacuni());
    }

    
    private void addActionListener() {
        
        
        prf.addBtnPretrazActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                
                String iznos = prf.getjTextFieldIznos().getText().trim();
                //iznos = Double.parseDouble(prf.getjTextFieldIznos().getText().trim());
                

                String umetnik = prf.getjTextFieldUmetnik().getText().trim();
                
                String kupac = prf.getjTextFieldKupac().getText().trim();
                
                String digitalnaFotografija = prf.getjTextFieldDigitalnaFotografija().getText().trim();
                
                
                if(!prf.getjRadioButtonDigitalnaFotografija().isSelected() && !prf.getjRadioButtonKupac().isSelected() && !prf.getjRadioButtonUmetnik().isSelected() && !prf.getjRadioButtonIznos().isSelected()){
                    JOptionPane.showMessageDialog(prf, "Izaberite kriterijum za pretragu!", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                }
                
                if(prf.getjRadioButtonDigitalnaFotografija().isSelected()){
                    if(digitalnaFotografija.isEmpty()){
                        JOptionPane.showMessageDialog(prf, "Popunite polje za pretragu!", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                    }else{
                        
                        
                        
                        try {
                            List<Racun> pretragaRacuni = Komunikacija.getInstance().vratiListuSviRacunDigitalnaFotografija(digitalnaFotografija);
                            if(pretragaRacuni.isEmpty()){
                                JOptionPane.showMessageDialog(prf, "Sistem ne može da nađe račune po zadatim kriterijumima.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                                
                            }else{
                                JOptionPane.showMessageDialog(prf, "Sistem je našao račune po zadatim kriterijumima.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                                
                                ModelTabeleRacuni mtr = new ModelTabeleRacuni(pretragaRacuni);
                                prf.getjTableRacuni().setModel(mtr);
                                
                                centrirajTekst(prf.getjTableRacuni());
                            }
                        } catch (IOException ex) {
                            //Logger.getLogger(PrikazRacunaController.class.getName()).log(Level.SEVERE, null, ex);
                            JOptionPane.showMessageDialog(prf, "Sistem ne može da nađe račune po zadatim kriterijumima.", "GREŠKA", JOptionPane.ERROR_MESSAGE);

                            for (Window window : Window.getWindows()) {
                                window.dispose();
                            }
                            Cordinator.getInstance().otvoriLoginFormu();
                        } catch (ClassNotFoundException ex) {
                            //Logger.getLogger(PrikazRacunaController.class.getName()).log(Level.SEVERE, null, ex);
                            JOptionPane.showMessageDialog(prf, "Sistem ne može da nađe račune po zadatim kriterijumima.", "GREŠKA", JOptionPane.ERROR_MESSAGE);

                            for (Window window : Window.getWindows()) {
                                window.dispose();
                            }
                            Cordinator.getInstance().otvoriLoginFormu();
                        }
                          
                        }
                        
                        
                        
                        
                        
                    }
                    
                if(prf.getjRadioButtonKupac().isSelected()){
                    if(kupac.isEmpty()){
                        JOptionPane.showMessageDialog(prf, "Popunite polje za pretragu!", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                    }else{
                        
                        
                        
                        
                        try {
                            List<Racun> pretragaRacuni1 = Komunikacija.getInstance().vratiListuSviRacunKupac(kupac);
                            if(pretragaRacuni1.isEmpty()){
                                JOptionPane.showMessageDialog(prf, "Sistem ne može da nađe račune po zadatim kriterijumima.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                                
                            }else{
                                JOptionPane.showMessageDialog(prf, "Sistem je našao račune po zadatim kriterijumima.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                                
                                ModelTabeleRacuni mtr = new ModelTabeleRacuni(pretragaRacuni1);
                                prf.getjTableRacuni().setModel(mtr);
                                
                                centrirajTekst(prf.getjTableRacuni());
                            }
                        } catch (IOException ex) {
                            //Logger.getLogger(PrikazRacunaController.class.getName()).log(Level.SEVERE, null, ex);
                            JOptionPane.showMessageDialog(prf, "Sistem ne može da nađe račune po zadatim kriterijumima.", "GREŠKA", JOptionPane.ERROR_MESSAGE);

                            for (Window window : Window.getWindows()) {
                                window.dispose();
                            }
                            Cordinator.getInstance().otvoriLoginFormu();
                        } catch (ClassNotFoundException ex) {
                            //Logger.getLogger(PrikazRacunaController.class.getName()).log(Level.SEVERE, null, ex);
                            JOptionPane.showMessageDialog(prf, "Sistem ne može da nađe račune po zadatim kriterijumima.", "GREŠKA", JOptionPane.ERROR_MESSAGE);

                            for (Window window : Window.getWindows()) {
                                window.dispose();
                            }
                            Cordinator.getInstance().otvoriLoginFormu();
                        }
                        
                          
                        }
                        
                        
                        
                        
                        
                    }
                
                if(prf.getjRadioButtonUmetnik().isSelected()){
                    if(umetnik.isEmpty()){
                        JOptionPane.showMessageDialog(prf, "Popunite polje za pretragu!", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                    }else{
                        
                        
                        
                        
                        
                        try {
                            List<Racun> pretragaRacuni1 = Komunikacija.getInstance().vratiListuSviRacunUmetnik(umetnik);
                            if(pretragaRacuni1.isEmpty()){
                                JOptionPane.showMessageDialog(prf, "Sistem ne može da nađe račune po zadatim kriterijumima.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                                
                            }else{
                                JOptionPane.showMessageDialog(prf, "Sistem je našao račune po zadatim kriterijumima.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                                
                                ModelTabeleRacuni mtr = new ModelTabeleRacuni(pretragaRacuni1);
                                prf.getjTableRacuni().setModel(mtr);
                                
                                centrirajTekst(prf.getjTableRacuni());
                            }
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(prf, "Sistem ne može da nađe račune po zadatim kriterijumima.", "GREŠKA", JOptionPane.ERROR_MESSAGE);

                            for (Window window : Window.getWindows()) {
                                window.dispose();
                            }
                            Cordinator.getInstance().otvoriLoginFormu();
                            //Logger.getLogger(PrikazRacunaController.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ClassNotFoundException ex) {
                            JOptionPane.showMessageDialog(prf, "Sistem ne može da nađe račune po zadatim kriterijumima.", "GREŠKA", JOptionPane.ERROR_MESSAGE);

                            for (Window window : Window.getWindows()) {
                                window.dispose();
                            }
                            Cordinator.getInstance().otvoriLoginFormu();
                            //Logger.getLogger(PrikazRacunaController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        
                          
                        }
                  
                        
                        
                        
                        
                    }
                if(prf.getjRadioButtonIznos().isSelected()){
                    if(iznos.isEmpty()){
                        JOptionPane.showMessageDialog(prf, "Popunite polje za pretragu!", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                    }else{
                        
                        
                        
                        
                        
                        
                        try {
                            List<Racun> pretragaRacuni2 = Komunikacija.getInstance().vratiListuSviRacunRacun(iznos);
                            if(pretragaRacuni2.isEmpty()){
                                JOptionPane.showMessageDialog(prf, "Sistem ne može da nađe račune po zadatim kriterijumima.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                                
                            }else{
                                JOptionPane.showMessageDialog(prf, "Sistem je našao račune po zadatim kriterijumima.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                                
                                ModelTabeleRacuni mtr = new ModelTabeleRacuni(pretragaRacuni2);
                                prf.getjTableRacuni().setModel(mtr);
                                
                                centrirajTekst(prf.getjTableRacuni());
                            }
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(prf, "Sistem ne može da nađe račune po zadatim kriterijumima.", "GREŠKA", JOptionPane.ERROR_MESSAGE);

                            for (Window window : Window.getWindows()) {
                                window.dispose();
                            }
                            Cordinator.getInstance().otvoriLoginFormu();
                            //Logger.getLogger(PrikazRacunaController.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ClassNotFoundException ex) {
                            JOptionPane.showMessageDialog(prf, "Sistem ne može da nađe račune po zadatim kriterijumima.", "GREŠKA", JOptionPane.ERROR_MESSAGE);

                            for (Window window : Window.getWindows()) {
                                window.dispose();
                            }
                            Cordinator.getInstance().otvoriLoginFormu();
                            //Logger.getLogger(PrikazRacunaController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        
                        
                          
                        }
                  
                        
                        
                        
                        
                    }
                
            }
        });
        
       
        
        
        prf.addBtnSelektovanoActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    prf.getjTextFieldDigitalnaFotografija().setEditable(true);
                    prf.getjTextFieldIznos().setEditable(false);
                    prf.getjTextFieldIznos().setText("");
                    prf.getjTextFieldKupac().setEditable(false);
                    prf.getjTextFieldKupac().setText("");
                    prf.getjTextFieldUmetnik().setEditable(false);
                    prf.getjTextFieldUmetnik().setText("");
                    
                    
      
                }
            
        });
        prf.addBtnSelektovanIznosActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    prf.getjTextFieldDigitalnaFotografija().setEditable(false);
                    prf.getjTextFieldDigitalnaFotografija().setText("");
                    prf.getjTextFieldIznos().setEditable(true);
                    prf.getjTextFieldKupac().setEditable(false);
                    prf.getjTextFieldKupac().setText("");
                    prf.getjTextFieldUmetnik().setEditable(false);
                    prf.getjTextFieldUmetnik().setText("");
                    
                    
      
                }
            
        });
        
        prf.addBtnSelektovanKupacActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    prf.getjTextFieldDigitalnaFotografija().setEditable(false);
                    prf.getjTextFieldDigitalnaFotografija().setText("");
                    prf.getjTextFieldIznos().setEditable(false);
                    prf.getjTextFieldIznos().setText("");
                    prf.getjTextFieldKupac().setEditable(true);
                    prf.getjTextFieldUmetnik().setEditable(false);
                    prf.getjTextFieldUmetnik().setText("");
                    
                    
      
                }
            
        });
        
        prf.addBtnSelektovanUmetnikActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    prf.getjTextFieldDigitalnaFotografija().setEditable(false);
                    prf.getjTextFieldDigitalnaFotografija().setText(""); 
                    prf.getjTextFieldIznos().setEditable(false);
                    prf.getjTextFieldIznos().setText("");
                    prf.getjTextFieldKupac().setEditable(false);
                    prf.getjTextFieldKupac().setText("");
                    prf.getjTextFieldUmetnik().setEditable(true);
                    
                    
      
                }
            
        });
        
        
        
        
        
        prf.addBtnPrikaziDetaljeActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = prf.getjTableRacuni().getSelectedRow();
                if(red == -1){
                    JOptionPane.showMessageDialog(prf, "Niste izabrali račun.", "UPOZORENJE", JOptionPane.WARNING_MESSAGE);
                } else{
                   
                    
                    
                    try {
                        ModelTabeleRacuni mtr = (ModelTabeleRacuni) prf.getjTableRacuni().getModel();
                        Racun r = mtr.getLista().get(red);
                        
                        Racun racun = Komunikacija.getInstance().pretraziRacun(r);
                        
                        JOptionPane.showMessageDialog(prf, "Sistem je našao račun.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                        
                        cordinator.Cordinator.getInstance().otvoriPrikaziDetaljeRacunFormu(racun);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(prf, "Sistem ne može da nađe račun.", "GRESKA", JOptionPane.ERROR_MESSAGE);

                            for (Window window : Window.getWindows()) {
                                window.dispose();
                            }
                            Cordinator.getInstance().otvoriLoginFormu();
                        //Logger.getLogger(PrikazRacunaController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        JOptionPane.showMessageDialog(prf, "Sistem ne može da nađe račun.", "GRESKA", JOptionPane.ERROR_MESSAGE);

                            for (Window window : Window.getWindows()) {
                                window.dispose();
                            }
                            Cordinator.getInstance().otvoriLoginFormu();
                        //Logger.getLogger(PrikazRacunaController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    
      
                }
            }
        });
        
        prf.addBtnResetujActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    pripremiFormu();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(prf, "Sistem ne može da resetuje pretragu.", "GRESKA", JOptionPane.ERROR_MESSAGE);

                            for (Window window : Window.getWindows()) {
                                window.dispose();
                            }
                            Cordinator.getInstance().otvoriLoginFormu();
                    //Logger.getLogger(PrikazRacunaController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(prf, "Sistem ne može da resetuje pretragu.", "GRESKA", JOptionPane.ERROR_MESSAGE);

                            for (Window window : Window.getWindows()) {
                                window.dispose();
                            }
                            Cordinator.getInstance().otvoriLoginFormu();
                    //Logger.getLogger(PrikazRacunaController.class.getName()).log(Level.SEVERE, null, ex);
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
