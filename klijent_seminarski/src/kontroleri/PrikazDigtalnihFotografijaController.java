/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import cordinator.Cordinator;
import forme.PrikazDigitalneFotografijeForma;
import forme.PrikazUmetnikaForma;
import forme.model.ModelTabeleDigitalneFotogradije;
import forme.model.ModelTabeleRacuni;
import forme.model.ModelTabeleUmetnici;
import java.awt.Color;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableCellRenderer;
import komunikacija.Komunikacija;
import model.DigitalnaFotografija;
import model.Racun;
import model.Umetnik;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 *
 * @author nikol
 */
public class PrikazDigtalnihFotografijaController {
    private final PrikazDigitalneFotografijeForma pdff;
    

    public PrikazDigtalnihFotografijaController(PrikazDigitalneFotografijeForma pdff) {
        this.pdff = pdff;
        addActionListener();
    }

    public void otvoriFormu() throws IOException, ClassNotFoundException {
        pripremiFormu();
        pdff.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pdff.setVisible(true);
        ImageIcon ikonica = new ImageIcon(getClass().getResource("/slike/digital-drawing (2).png"));  // Stavi sliku u src/ikonice folder
        pdff.setIconImage(ikonica.getImage());
        pdff.setTitle("Digitalne fotografije");
        
        
        pdff.getContentPane().setBackground(new Color(230, 200, 255)); // Svetlo ljubičasta
        pdff.getjButtonPretraži().setBackground(new Color(138, 43, 226));
        pdff.getjButtonPretraži().setForeground(Color.white);
        
        pdff.getjButtonResetuj().setBackground(new Color(138, 43, 226));
        pdff.getjButtonResetuj().setForeground(Color.white);
        
        pdff.getjButtonDetalji().setBackground(new Color(138, 43, 226));
        pdff.getjButtonDetalji().setForeground(Color.white);
        
        
        
        // Postavljanje boje pozadine tabele
        pdff.getjTableDigitalneFotografije().setBackground(new Color(147, 112, 219)); 
        pdff.getjTableDigitalneFotografije().setForeground(Color.WHITE); 

        // Postavljanje boje zaglavlja tabele
        pdff.getjTableDigitalneFotografije().getTableHeader().setBackground(new Color(138, 43, 226)); // Tamno ljubičasta
        pdff.getjTableDigitalneFotografije().getTableHeader().setForeground(Color.WHITE); // Boja teksta u zaglavlju
        
        pdff.getjScrollPane1().getViewport().setBackground(new Color(147, 112, 219)); // Svetlo ljubičasta
        
        pdff.getjTableDigitalneFotografije().setGridColor(Color.BLACK); // Crne linije između redova i kolona
        pdff.getjTableDigitalneFotografije().setShowGrid(true); // Omogućava prikaz linija
        pdff.getjTableDigitalneFotografije().setIntercellSpacing(new java.awt.Dimension(1, 1)); // Podešava razmak između ćelija (debljina linija)

        ImageIcon originalIkona = new ImageIcon(getClass().getResource("/slike/digital-drawing.png")); // Promeni ime ako treba
        Image slika = originalIkona.getImage().getScaledInstance(pdff.getjLabelSlika().getWidth(), pdff.getjLabelSlika().getHeight(), Image.SCALE_SMOOTH);
        pdff.getjLabelSlika().setIcon(new ImageIcon(slika));
        
    }

    public void pripremiFormu() throws IOException, ClassNotFoundException  {
        List<DigitalnaFotografija> digitalneFotografije = komunikacija.Komunikacija.getInstance().ucitajDigitalneFotografije();
        
        ModelTabeleDigitalneFotogradije mtdf = new ModelTabeleDigitalneFotogradije(digitalneFotografije);
        pdff.getjTableDigitalneFotografije().setModel(mtdf);
        
        centrirajTekst(pdff.getjTableDigitalneFotografije());
    }
    
    
    private void addActionListener() {
        pdff.addBtnPretrazActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                
                
               
                    String nazivFotografije = pdff.getjTextFieldNazivFotografije().getText().trim();
                  
                     
                    String cenaString = pdff.getjTextFieldCenaFotografije().getText().trim();
                    
                    if(nazivFotografije.isEmpty() && cenaString.isEmpty()){
                        JOptionPane.showMessageDialog(pdff, "Sva polja za pretragu su prazna!", "GREŠKA", JOptionPane.ERROR_MESSAGE);

                    }else{
                        try {
                            double cena = -1;
                            if (cenaString != null && !cenaString.trim().isEmpty()) {
                                cena = Double.parseDouble(cenaString);
                            }
                            
                            DigitalnaFotografija trazenaFotografija = new DigitalnaFotografija(nazivFotografije, cena);
                            
                            
                            List<DigitalnaFotografija> pretragaFotografije = Komunikacija.getInstance().vratiListuSviDigitalnaFotografijaDigitalnaFotografija(trazenaFotografija);
                            if(pretragaFotografije.isEmpty()){
                                JOptionPane.showMessageDialog(pdff, "Sistem ne može da nađe digitalne fotografije po zadatim kriterijumima.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                                
                            }else{
                                JOptionPane.showMessageDialog(pdff, "Sistem je našao digitalne fotografije po zadatim kriterijumima.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                                
                                ModelTabeleDigitalneFotogradije mtdf = new ModelTabeleDigitalneFotogradije(pretragaFotografije);
                                pdff.getjTableDigitalneFotografije().setModel(mtdf);
                                
                                centrirajTekst(pdff.getjTableDigitalneFotografije());
                            }   } catch (IOException ex) {
                            //Logger.getLogger(PrikazDigtalnihFotografijaController.class.getName()).log(Level.SEVERE, null, ex);
                            JOptionPane.showMessageDialog(pdff, "Sistem ne moze da pretraži digitalnu fotografiju.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                                
                                
                            
                                for (Window window : Window.getWindows()) {
                                    window.dispose();
                                }
                            Cordinator.getInstance().otvoriLoginFormu();
                        } catch (ClassNotFoundException ex) {
                            //Logger.getLogger(PrikazDigtalnihFotografijaController.class.getName()).log(Level.SEVERE, null, ex);
                            JOptionPane.showMessageDialog(pdff, "Sistem ne moze da pretraži digitalnu fotografiju.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                                
                                
                            
                                for (Window window : Window.getWindows()) {
                                    window.dispose();
                                }
                            Cordinator.getInstance().otvoriLoginFormu();
                        }
               
                    }
                    
                    
            
                
               
            }
        });
        
        pdff.addBtnResetujActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    pripremiFormu();
                } catch (IOException ex) {
                    //Logger.getLogger(PrikazDigtalnihFotografijaController.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(pdff, "Sistem ne moze da resetuje digitalne fotografije.", "GREŠKA", JOptionPane.ERROR_MESSAGE);

                    
                                for (Window window : Window.getWindows()) {
                                    window.dispose();
                                }
                    Cordinator.getInstance().otvoriLoginFormu();
                } catch (ClassNotFoundException ex) {
                    //Logger.getLogger(PrikazDigtalnihFotografijaController.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(pdff, "Sistem ne moze da resetuje digitalne fotografije.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                                
                                
                                for (Window window : Window.getWindows()) {
                                    window.dispose();
                                }
                                Cordinator.getInstance().otvoriLoginFormu();
                }
            }
        });
        
        
        
        
        pdff.addBtnDetaljiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pdff.getjTableDigitalneFotografije().getSelectedRow();
                if(red == -1){
                    JOptionPane.showMessageDialog(pdff, "Niste izabrali digitalnu fotografiju.", "UPOZORENJE", JOptionPane.WARNING_MESSAGE);
                } else{
                    try {
                        ModelTabeleDigitalneFotogradije mtdf = (ModelTabeleDigitalneFotogradije) pdff.getjTableDigitalneFotografije().getModel();
                        DigitalnaFotografija df = mtdf.getLista().get(red);
                        DigitalnaFotografija digitalnaFotografija = Komunikacija.getInstance().pretraziDigitalnuFotografiju(df);
                        
                        JOptionPane.showMessageDialog(pdff, "Sistem je našao digitalnu fotografiju.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                        
                        
                        cordinator.Cordinator.getInstance().otvoriDetaljiDigitalneFotografijeFormu(digitalnaFotografija);
                    } catch (IOException ex) {
                        //Logger.getLogger(PrikazDigtalnihFotografijaController.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(pdff, "Sistem ne može da nađe digitalnu fotografiju.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                            
                            for (Window window : Window.getWindows()) {
                                    window.dispose();
                                }
                            Cordinator.getInstance().otvoriLoginFormu();
                    } catch (ClassNotFoundException ex) {
                        //Logger.getLogger(PrikazDigtalnihFotografijaController.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(pdff, "Sistem ne može da nađe digitalnu fotografiju.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                            
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
