/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import cordinator.Cordinator;
import forme.PrikazMestaForma;
import forme.PrikazUmetnikaForma;
import forme.model.ModelTabeleDigitalneFotogradije;
import forme.model.ModelTabeleMesta;
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
import model.DigitalnaFotografija;
import model.Kupac;
import model.Mesto;
import model.Umetnik;

/**
 *
 * @author nikol
 */
public class PrikazMestaController {
    private final PrikazMestaForma pmf;
    

    public PrikazMestaController(PrikazMestaForma pmf) {
        this.pmf = pmf;
        addActionListener();
    }

    public void otvoriFormu() throws IOException, ClassNotFoundException {
        pripremiFormu();
        pmf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pmf.setVisible(true);
        ImageIcon ikonica = new ImageIcon(getClass().getResource("/slike/digital-drawing (2).png"));  // Stavi sliku u src/ikonice folder
        pmf.setIconImage(ikonica.getImage());
        pmf.setTitle("Mesta");
        
        pmf.getContentPane().setBackground(new Color(230, 200, 255)); // Svetlo ljubičasta
        pmf.getjButtonPretraži().setBackground(new Color(138, 43, 226));
        pmf.getjButtonPretraži().setForeground(Color.white);
        
        pmf.getjButtonResetuj().setBackground(new Color(138, 43, 226));
        pmf.getjButtonResetuj().setForeground(Color.white);
        
        pmf.getjButtonDetalji().setBackground(new Color(138, 43, 226));
        pmf.getjButtonDetalji().setForeground(Color.white);
        
        
        
        // Postavljanje boje pozadine tabele
        pmf.getjTableMesta().setBackground(new Color(147, 112, 219)); 
        pmf.getjTableMesta().setForeground(Color.WHITE); 

        // Postavljanje boje zaglavlja tabele
        pmf.getjTableMesta().getTableHeader().setBackground(new Color(138, 43, 226)); // Tamno ljubičasta
        pmf.getjTableMesta().getTableHeader().setForeground(Color.WHITE); // Boja teksta u zaglavlju
        
        pmf.getjScrollPane1().getViewport().setBackground(new Color(147, 112, 219)); // Svetlo ljubičasta
        
        pmf.getjTableMesta().setGridColor(Color.BLACK); // Crne linije između redova i kolona
        pmf.getjTableMesta().setShowGrid(true); // Omogućava prikaz linija
        pmf.getjTableMesta().setIntercellSpacing(new java.awt.Dimension(1, 1)); // Podešava razmak između ćelija (debljina linija)

        ImageIcon originalIkona = new ImageIcon(getClass().getResource("/slike/mesto1.png")); // Promeni ime ako treba
        Image slika = originalIkona.getImage().getScaledInstance(pmf.getjLabelMesto().getWidth(), pmf.getjLabelMesto().getHeight(), Image.SCALE_SMOOTH);
        pmf.getjLabelMesto().setIcon(new ImageIcon(slika));
        
    }

    public void pripremiFormu() throws IOException, ClassNotFoundException  {
        List<Mesto> mesta = komunikacija.Komunikacija.getInstance().ucitajMesta();
        
        ModelTabeleMesta mtm = new ModelTabeleMesta(mesta);
        pmf.getjTableMesta().setModel(mtm);
        
        centrirajTekst(pmf.getjTableMesta());
    }
    
    
    private void addActionListener() {
        pmf.addBtnPretrazActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                String država = pmf.getjTextFieldDržava().getText().trim();
                
                String grad = pmf.getjTextFieldGrad().getText().trim();
                
                String poštanski = pmf.getjTextFieldPoštanskiBroj().getText().trim();
                
                
                
                
                
                
                if(država.isEmpty() && grad.isEmpty() && poštanski.isEmpty()){
                    JOptionPane.showMessageDialog(pmf, "Sva polja za pretragu su prazna!", "GREŠKA", JOptionPane.ERROR_MESSAGE);

                }else{
                    try {
                        Mesto trazenoMesto = new Mesto(država, grad, poštanski);
                        
                        
                        List<Mesto> pretragaMesto = Komunikacija.getInstance().vratiListuSviMestoMesto(trazenoMesto);
                        if(pretragaMesto.isEmpty()){
                            JOptionPane.showMessageDialog(pmf, "Sistem ne može da nađe mesta po zadatim kriterijumima.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                            
                        }else{
                            JOptionPane.showMessageDialog(pmf, "Sistem je našao mesta po zadatim kriterijumima.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                            
                            ModelTabeleMesta mtm = new ModelTabeleMesta(pretragaMesto);
                            pmf.getjTableMesta().setModel(mtm);
                            
                            centrirajTekst(pmf.getjTableMesta());
                        }
                    } catch (IOException ex) {
                        //Logger.getLogger(PrikazMestaController.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(pmf, "Sistem ne može da nađe mesta po zadatim kriterijumima.", "GREŠKA", JOptionPane.ERROR_MESSAGE);

                        for (Window window : Window.getWindows()) {
                                window.dispose();
                            }
                            Cordinator.getInstance().otvoriLoginFormu();
                    } catch (ClassNotFoundException ex) {
                        //Logger.getLogger(PrikazMestaController.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(pmf, "Sistem ne može da nađe mesta po zadatim kriterijumima.", "GREŠKA", JOptionPane.ERROR_MESSAGE);

                        for (Window window : Window.getWindows()) {
                                window.dispose();
                            }
                            Cordinator.getInstance().otvoriLoginFormu();
                    }
                }
                   
                
            }
        });
        
        pmf.addBtnResetujActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    pripremiFormu();
                } catch (IOException ex) {
                    //Logger.getLogger(PrikazMestaController.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(pmf, "Sistem ne može da resetuje pretragu.", "GREŠKA", JOptionPane.ERROR_MESSAGE);

                    for (Window window : Window.getWindows()) {
                                window.dispose();
                            }
                            Cordinator.getInstance().otvoriLoginFormu();
                } catch (ClassNotFoundException ex) {
                    //Logger.getLogger(PrikazMestaController.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(pmf, "Sistem ne može da resetuje pretragu.", "GREŠKA", JOptionPane.ERROR_MESSAGE);

                    for (Window window : Window.getWindows()) {
                                window.dispose();
                            }
                            Cordinator.getInstance().otvoriLoginFormu();
                }
            }
        });
        
        
        
        
        
        pmf.addBtnDetaljiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pmf.getjTableMesta().getSelectedRow();
                if(red == -1){
                    JOptionPane.showMessageDialog(pmf, "Niste izabrali mesto.", "UPOZORENJE", JOptionPane.WARNING_MESSAGE);
                } else{
                    try {
                        ModelTabeleMesta mtm = (ModelTabeleMesta) pmf.getjTableMesta().getModel();
                        Mesto m = mtm.getLista().get(red);
                        Mesto mesto = Komunikacija.getInstance().pretraziMesto(m);
                        
                        JOptionPane.showMessageDialog(pmf, "Sistem je našao mesto.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                        
                        cordinator.Cordinator.getInstance().otvoriDetaljiMestaFormu(mesto);
                    } catch (IOException ex) {
                        //Logger.getLogger(PrikazMestaController.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(pmf, "Sistem ne moze da nađe mesto.", "GREŠKA", JOptionPane.ERROR_MESSAGE);

                        for (Window window : Window.getWindows()) {
                                window.dispose();
                            }
                            Cordinator.getInstance().otvoriLoginFormu();
                    } catch (ClassNotFoundException ex) {
                        //Logger.getLogger(PrikazMestaController.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(pmf, "Sistem ne moze da nađe mesto.", "GREŠKA", JOptionPane.ERROR_MESSAGE);

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
