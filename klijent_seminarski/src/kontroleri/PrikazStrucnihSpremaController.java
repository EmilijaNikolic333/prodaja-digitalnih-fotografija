/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import cordinator.Cordinator;
import forme.PrikazUmetnikaForma;
import forme.PrikaziStrucneSpremeForma;
import forme.model.ModelTabeleStavkeRacuna;
import forme.model.ModelTabeleStrucnaSprema;
import forme.model.ModelTabeleUmetnici;
import java.awt.Color;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class PrikazStrucnihSpremaController {
    private final PrikaziStrucneSpremeForma pssf;
    

    public PrikazStrucnihSpremaController(PrikaziStrucneSpremeForma pssf) {
        this.pssf = pssf;
        addActionListener();
    }

    public void otvoriFormu() throws IOException, ClassNotFoundException {
        pripremiFormu();
        pssf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pssf.setVisible(true);
        ImageIcon ikonica = new ImageIcon(getClass().getResource("/slike/digital-drawing (2).png"));  // Stavi sliku u src/ikonice folder
        pssf.setIconImage(ikonica.getImage());
        pssf.setTitle("Stručne spreme");
        
        
        pssf.getContentPane().setBackground(new Color(230, 200, 255)); // Svetlo ljubičasta
        pssf.getjButtonPretraži().setBackground(new Color(138, 43, 226));
        pssf.getjButtonPretraži().setForeground(Color.white);
        
        pssf.getjButtonResetuj().setBackground(new Color(138, 43, 226));
        pssf.getjButtonResetuj().setForeground(Color.white);
        
        pssf.getjButtonDetalji().setBackground(new Color(138, 43, 226));
        pssf.getjButtonDetalji().setForeground(Color.white);
        
        
        
        // Postavljanje boje pozadine tabele
        pssf.getjTableStručnaSprema().setBackground(new Color(147, 112, 219)); 
        pssf.getjTableStručnaSprema().setForeground(Color.WHITE); 

        // Postavljanje boje zaglavlja tabele
        pssf.getjTableStručnaSprema().getTableHeader().setBackground(new Color(138, 43, 226)); // Tamno ljubičasta
        pssf.getjTableStručnaSprema().getTableHeader().setForeground(Color.WHITE); // Boja teksta u zaglavlju
        
        pssf.getjScrollPane1().getViewport().setBackground(new Color(147, 112, 219)); // Svetlo ljubičasta
        
        pssf.getjTableStručnaSprema().setGridColor(Color.BLACK); // Crne linije između redova i kolona
        pssf.getjTableStručnaSprema().setShowGrid(true); // Omogućava prikaz linija
        pssf.getjTableStručnaSprema().setIntercellSpacing(new java.awt.Dimension(1, 1)); // Podešava razmak između ćelija (debljina linija)

        ImageIcon originalIkona = new ImageIcon(getClass().getResource("/slike/sprema1.png")); // Promeni ime ako treba
        Image slika = originalIkona.getImage().getScaledInstance(pssf.getjLabelSlika().getWidth(), pssf.getjLabelSlika().getHeight(), Image.SCALE_SMOOTH);
        pssf.getjLabelSlika().setIcon(new ImageIcon(slika));
        ////
    }

    public void pripremiFormu() throws IOException, ClassNotFoundException  {
        List<StrucnaSprema> strucneSpreme = komunikacija.Komunikacija.getInstance().ucitajStrucneSpreme();
        
        
        ModelTabeleStrucnaSprema mtss = new ModelTabeleStrucnaSprema(strucneSpreme);
        pssf.getjTableStručnaSprema().setModel(mtss);
        
        centrirajTekst(pssf.getjTableStručnaSprema());
    }
    
    
    private void addActionListener() {
        pssf.addBtnPretrazActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                String naziv = pssf.getjTextFieldNaziv().getText().trim();
               
                
                
                
                if(naziv.isEmpty()){
                    JOptionPane.showMessageDialog(pssf, "Polje za pretragu je prazno!", "GREŠKA", JOptionPane.ERROR_MESSAGE);

                }else{
                    try {
                        List<StrucnaSprema> pretragaStrucneSpreme = Komunikacija.getInstance().vratiListuSviStrSpremaStrSprema(naziv);
                        if(pretragaStrucneSpreme.isEmpty()){
                            JOptionPane.showMessageDialog(pssf, "Sistem ne može da nađe stručne spreme po zadatim kriterijumima.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                            
                        }else{
                            JOptionPane.showMessageDialog(pssf, "Sistem je našao stručne spreme po zadatim kriterijumima.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                            
                            ModelTabeleStrucnaSprema mtss = new ModelTabeleStrucnaSprema(pretragaStrucneSpreme);
                            pssf.getjTableStručnaSprema().setModel(mtss);
                            
                            centrirajTekst(pssf.getjTableStručnaSprema());
                        }
                    } catch (IOException ex) {
                        //Logger.getLogger(PrikazStrucnihSpremaController.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(pssf, "Sistem ne može da nađe stručne spreme po zadatim kriterijumima.", "GREŠKA", JOptionPane.ERROR_MESSAGE);

                        for (Window window : Window.getWindows()) {
                                window.dispose();
                            }
                            Cordinator.getInstance().otvoriLoginFormu();
                    } catch (ClassNotFoundException ex) {
                        //Logger.getLogger(PrikazStrucnihSpremaController.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(pssf, "Sistem ne može da nađe stručne spreme po zadatim kriterijumima.", "GREŠKA", JOptionPane.ERROR_MESSAGE);

                        for (Window window : Window.getWindows()) {
                                window.dispose();
                            }
                            Cordinator.getInstance().otvoriLoginFormu();
                    }
                }
                   
                
            }
        });
        
        pssf.addBtnResetujActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    pripremiFormu();
                } catch (IOException ex) {
                    //Logger.getLogger(PrikazStrucnihSpremaController.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(pssf, "Sistem ne može da resetuje pretragu.", "GREŠKA", JOptionPane.ERROR_MESSAGE);

                        for (Window window : Window.getWindows()) {
                                window.dispose();
                            }
                            Cordinator.getInstance().otvoriLoginFormu();
                } catch (ClassNotFoundException ex) {
                    //Logger.getLogger(PrikazStrucnihSpremaController.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(pssf, "Sistem ne može da resetuje pretragu.", "GREŠKA", JOptionPane.ERROR_MESSAGE);

                        for (Window window : Window.getWindows()) {
                                window.dispose();
                            }
                            Cordinator.getInstance().otvoriLoginFormu();
                }
            }
        });
        
        
        
        
        
        pssf.addBtnDetaljiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pssf.getjTableStručnaSprema().getSelectedRow();
                if(red == -1){
                    JOptionPane.showMessageDialog(pssf, "Niste izabralistručnu spremu.", "UPOZORENJE", JOptionPane.WARNING_MESSAGE);
                } else{
                    try {
                        ModelTabeleStrucnaSprema mtss = (ModelTabeleStrucnaSprema) pssf.getjTableStručnaSprema().getModel();
                        StrucnaSprema ss = mtss.getLista().get(red);
                        StrucnaSprema strucnaSprema = Komunikacija.getInstance().pretraziStrucnuSpremu(ss);
                        
                        JOptionPane.showMessageDialog(pssf, "Sistem je našao stručnu spremu.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                        
                        cordinator.Cordinator.getInstance().otvoriDetaljiStrucneSpremeFormu(strucnaSprema);
                    } catch (IOException ex) {
                        //Logger.getLogger(PrikazStrucnihSpremaController.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(pssf, "Sistem ne moze da nađe stručnu spremu.", "GREŠKA", JOptionPane.ERROR_MESSAGE);

                        
                        for (Window window : Window.getWindows()) {
                                window.dispose();
                            }
                            Cordinator.getInstance().otvoriLoginFormu();
                    } catch (ClassNotFoundException ex) {
                        //Logger.getLogger(PrikazStrucnihSpremaController.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(pssf, "Sistem ne moze da nađe stručnu spremu.", "GREŠKA", JOptionPane.ERROR_MESSAGE);

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

