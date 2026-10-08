/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import cordinator.Cordinator;
import forme.PrikazKupacaForma;
import forme.model.ModelTabeleKupci;
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
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableCellRenderer;
import komunikacija.Komunikacija;
import model.Kupac;
import model.Mesto;
import model.Umetnik;

/**
 *
 * @author nikol
 */
public class PrikazKupcaController {
    
    private final PrikazKupacaForma pkf;

    public PrikazKupcaController(PrikazKupacaForma pkf) {
        this.pkf = pkf;
        addActionListener();
        ButtonGroup grupa = new ButtonGroup();
        grupa.add(pkf.getjRadioButtonImeIPrezime());
        grupa.add(pkf.getjRadioButtonMesto());
    }

    public void otvoriFormu() throws IOException, ClassNotFoundException {
        pripremiFormu();
        pkf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pkf.setVisible(true);
        ImageIcon ikonica = new ImageIcon(getClass().getResource("/slike/digital-drawing (2).png"));  // Stavi sliku u src/ikonice folder
        pkf.setIconImage(ikonica.getImage());
        pkf.setTitle("Kupci");
        
        pkf.getContentPane().setBackground(new Color(230, 200, 255)); // Svetlo ljubičasta
        pkf.getjButtonPretraži().setBackground(new Color(138, 43, 226));
        pkf.getjButtonPretraži().setForeground(Color.white);
        
        pkf.getjButtonResetuj().setBackground(new Color(138, 43, 226));
        pkf.getjButtonResetuj().setForeground(Color.white);
        
        pkf.getjButtonDetalji().setBackground(new Color(138, 43, 226));
        pkf.getjButtonDetalji().setForeground(Color.white);
        
       
        
        
        
        // Postavljanje boje pozadine tabele
        pkf.getjTableKupci().setBackground(new Color(147, 112, 219)); 
        pkf.getjTableKupci().setForeground(Color.WHITE); 

        // Postavljanje boje zaglavlja tabele
        pkf.getjTableKupci().getTableHeader().setBackground(new Color(138, 43, 226)); // Tamno ljubičasta
        pkf.getjTableKupci().getTableHeader().setForeground(Color.WHITE); // Boja teksta u zaglavlju
        
        pkf.getjScrollPane1().getViewport().setBackground(new Color(147, 112, 219)); // Svetlo ljubičasta
        
        pkf.getjTableKupci().setGridColor(Color.BLACK); // Crne linije između redova i kolona
        pkf.getjTableKupci().setShowGrid(true); // Omogućava prikaz linija
        pkf.getjTableKupci().setIntercellSpacing(new java.awt.Dimension(1, 1)); // Podešava razmak između ćelija (debljina linija)

        ImageIcon originalIkona = new ImageIcon(getClass().getResource("/slike/kupac2.png")); // Promeni ime ako treba
        Image slika = originalIkona.getImage().getScaledInstance(pkf.getjLabelSlika().getWidth(), pkf.getjLabelSlika().getHeight(), Image.SCALE_SMOOTH);
        pkf.getjLabelSlika().setIcon(new ImageIcon(slika));
        
        
        pkf.getjRadioButtonImeIPrezime().setBackground(new Color(230, 200, 255));
        pkf.getjRadioButtonMesto().setBackground(new Color(230, 200, 255));
        
        pkf.getjTextFieldImeIPrezime().setEditable(false);
        pkf.getjComboBoxMesto().setEnabled(false);
    }

    public void pripremiFormu() throws IOException, ClassNotFoundException  {
        List<Mesto> mesta = komunikacija.Komunikacija.getInstance().ucitajMesta();
        
        pkf.getjComboBoxMesto().removeAllItems();
                
        for (Mesto mesto : mesta) {
            pkf.getjComboBoxMesto().addItem(mesto);
        }
        pkf.getjComboBoxMesto().setSelectedIndex(-1);
        
        List<Kupac> kupci = komunikacija.Komunikacija.getInstance().ucitajKupce();
     
        ModelTabeleKupci mtk = new ModelTabeleKupci(kupci);
        pkf.getjTableKupci().setModel(mtk);
        
        centrirajTekst(pkf.getjTableKupci());
    }

    private void addActionListener() {
        
        
        pkf.addBtnDetaljiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pkf.getjTableKupci().getSelectedRow();
                if(red == -1){
                    JOptionPane.showMessageDialog(pkf, "Niste izabrali kupca.", "UPOZORENJE", JOptionPane.WARNING_MESSAGE);
                    //JOptionPane.showMessageDialog(pkf, "Sistem ne moze da nađe kupca.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                } else{
                    try {
                        ModelTabeleKupci mtk = (ModelTabeleKupci) pkf.getjTableKupci().getModel();
                        Kupac k = mtk.getLista().get(red);
                        Kupac kupac = Komunikacija.getInstance().pretraziKupca(k);
                        
                        JOptionPane.showMessageDialog(pkf, "Sistem je našao kupca.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                        
                        cordinator.Cordinator.getInstance().otvoriDetaljiKupcaFormu(kupac);
                    } catch (IOException ex) {
                        //Logger.getLogger(PrikazKupcaController.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(pkf, "Sistem ne moze da nađe kupca.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                            for (Window window : Window.getWindows()) {
                                    window.dispose();
                                }
                                Cordinator.getInstance().otvoriLoginFormu();
                    } catch (ClassNotFoundException ex) {
                        //Logger.getLogger(PrikazKupcaController.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(pkf, "Sistem ne moze da nađe kupca.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                            for (Window window : Window.getWindows()) {
                                    window.dispose();
                                }
                                Cordinator.getInstance().otvoriLoginFormu();
                    }
                    
                    
      
                }
            }
        });
        
        pkf.addBtnPretrazActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                String imePrezime = pkf.getjTextFieldImeIPrezime().getText().trim();

                
                Mesto mesto =  (Mesto) pkf.getjComboBoxMesto().getSelectedItem();
                
                
                if(!pkf.getjRadioButtonImeIPrezime().isSelected() && !pkf.getjRadioButtonMesto().isSelected()){
                    JOptionPane.showMessageDialog(pkf, "Izaberite kriterijum za pretragu!", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                }
                
                
                
                if(pkf.getjRadioButtonImeIPrezime().isSelected()){
                    
                    if(imePrezime.isEmpty()){
                        JOptionPane.showMessageDialog(pkf, "Popunite polje za pretragu!", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                    }else{
                        
                        try {
                            List<Kupac> pretragaKupci = Komunikacija.getInstance().vratiListuSviKupacKupac(imePrezime);
                            if(pretragaKupci.isEmpty()){
                                JOptionPane.showMessageDialog(pkf, "Sistem ne može da nađe kupce po zadatim kriterijumima.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                                
                            }else{
                                JOptionPane.showMessageDialog(pkf, "Sistem je našao kupce po zadatim kriterijumima.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                                
                                ModelTabeleKupci mtk = new ModelTabeleKupci(pretragaKupci);
                                pkf.getjTableKupci().setModel(mtk);
                                
                                centrirajTekst(pkf.getjTableKupci());
                            }
                        } catch (IOException ex) {
                            //Logger.getLogger(PrikazKupcaController.class.getName()).log(Level.SEVERE, null, ex);
                            JOptionPane.showMessageDialog(pkf, "Sistem ne može da nađe kupce po zadatim kriterijumima.", "GREŠKA", JOptionPane.ERROR_MESSAGE);

                            
                            for (Window window : Window.getWindows()) {
                                    window.dispose();
                                }
                                Cordinator.getInstance().otvoriLoginFormu();
                        } catch (ClassNotFoundException ex) {
                            //Logger.getLogger(PrikazKupcaController.class.getName()).log(Level.SEVERE, null, ex);
                            JOptionPane.showMessageDialog(pkf, "Sistem ne može da nađe kupce po zadatim kriterijumima.", "GREŠKA", JOptionPane.ERROR_MESSAGE);

                            
                            for (Window window : Window.getWindows()) {
                                    window.dispose();
                                }
                                Cordinator.getInstance().otvoriLoginFormu();
                        }
                        
                        
                        
                        
                        
                    }
                    
                }
                
                
                
                if(pkf.getjRadioButtonMesto().isSelected()){
                    if(mesto.getGrad().isEmpty()){
                        JOptionPane.showMessageDialog(pkf, "Popunite polje za pretragu!", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                    }else{
                        
                        
                        try {
                            List<Kupac> pretragaKupci = Komunikacija.getInstance().vratiListuSviKupacMesto(mesto);
                            if(pretragaKupci.isEmpty()){
                                JOptionPane.showMessageDialog(pkf, "Sistem ne može da nađe kupce po zadatim kriterijumima.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                                
                            }else{
                                JOptionPane.showMessageDialog(pkf, "Sistem je našao kupce po zadatim kriterijumima.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                                
                                ModelTabeleKupci mtk = new ModelTabeleKupci(pretragaKupci);
                                pkf.getjTableKupci().setModel(mtk);
                                
                                centrirajTekst(pkf.getjTableKupci());
                            }
                        } catch (IOException ex) {
                            //Logger.getLogger(PrikazKupcaController.class.getName()).log(Level.SEVERE, null, ex);
                            JOptionPane.showMessageDialog(pkf, "Sistem ne može da nađe kupce po zadatim kriterijumima.", "GREŠKA", JOptionPane.ERROR_MESSAGE);

                            
                            for (Window window : Window.getWindows()) {
                                    window.dispose();
                                }
                                Cordinator.getInstance().otvoriLoginFormu();
                        } catch (ClassNotFoundException ex) {
                            //Logger.getLogger(PrikazKupcaController.class.getName()).log(Level.SEVERE, null, ex);
                            JOptionPane.showMessageDialog(pkf, "Sistem ne može da nađe kupce po zadatim kriterijumima.", "GREŠKA", JOptionPane.ERROR_MESSAGE);

                            
                            for (Window window : Window.getWindows()) {
                                    window.dispose();
                                }
                                Cordinator.getInstance().otvoriLoginFormu();
                        }
                          
                        
                        
                        
                        
                    }
                    
                }
                
                
               
                
                
            }
        });
        
        pkf.addBtnSelektovanKupacActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    pkf.getjTextFieldImeIPrezime().setEditable(true);
                    
                    
                    pkf.getjComboBoxMesto().setSelectedItem(null);
                    pkf.getjComboBoxMesto().setEnabled(false);
                    
                    
                    
      
                }
            
        });
        
        pkf.addBtnSelektovanoMestoActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    pkf.getjTextFieldImeIPrezime().setEditable(false);
                    pkf.getjTextFieldImeIPrezime().setText("");
                    
                    pkf.getjComboBoxMesto().setEnabled(true);
                    
                    
                    
                    
      
                }
            
        });
        
        pkf.addBtnResetujActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    pripremiFormu();
                } catch (IOException ex) {
                    //Logger.getLogger(PrikazKupcaController.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(pkf, "Sistem ne može da resetuje pretragu.", "GREŠKA", JOptionPane.ERROR_MESSAGE);

                            
                            for (Window window : Window.getWindows()) {
                                    window.dispose();
                                }
                                Cordinator.getInstance().otvoriLoginFormu();
                } catch (ClassNotFoundException ex) {
                    //Logger.getLogger(PrikazKupcaController.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(pkf, "Sistem ne može da resetuje pretragu.", "GREŠKA", JOptionPane.ERROR_MESSAGE);

                            
                            for (Window window : Window.getWindows()) {
                                    window.dispose();
                                }
                                Cordinator.getInstance().otvoriLoginFormu();
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
