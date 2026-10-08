/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import cordinator.Cordinator;
import forme.DodajMestoForma;
import forme.DodajUmetnikaForma;
import forme.FormaMod;
import static forme.FormaMod.DETALJI;
import static forme.FormaMod.DODAJ;
import static forme.FormaMod.IZMENI;
import forme.model.ModelTabeleMesta;
import java.awt.Color;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import komunikacija.Komunikacija;
import model.Mesto;
import model.Umetnik;

/**
 *
 * @author nikol
 */
public class DodajMestoController {
    private final DodajMestoForma dmf;
    private Mesto mesto;

    public DodajMestoController(DodajMestoForma dmf) {
        this.dmf = dmf;
        addActionListener();
    }
    
    public void otvoriFormu(FormaMod mod, Mesto mesto){
        //pripremiFormu();
        this.mesto = mesto;
        pripremifomru(mod);
        
        dmf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dmf.setVisible(true);
        ImageIcon ikonica = new ImageIcon(getClass().getResource("/slike/digital-drawing (2).png"));  // Stavi sliku u src/ikonice folder
        dmf.setIconImage(ikonica.getImage());
        
        
        dmf.getContentPane().setBackground(new Color(230, 200, 255)); // Svetlo ljubičasta
        
        dmf.getjButtonPromeni().setBackground(new Color(138, 43, 226));
        dmf.getjButtonPromeni().setForeground(Color.white);
        
        dmf.getjButtonKreiraj().setBackground(new Color(138, 43, 226));
        dmf.getjButtonKreiraj().setForeground(Color.white);
        
        dmf.getjButtonObriši().setBackground(new Color(138, 43, 226));
        dmf.getjButtonObriši().setForeground(Color.white);
        
        dmf.getjButtonOdustani().setBackground(new Color(138, 43, 226));
        dmf.getjButtonOdustani().setForeground(Color.white);
        
        ImageIcon originalIkona = new ImageIcon(getClass().getResource("/slike/mesto1.png")); // Promeni ime ako treba
        Image slika = originalIkona.getImage().getScaledInstance(dmf.getjLabelSlika().getWidth(), dmf.getjLabelSlika().getHeight(), Image.SCALE_SMOOTH);
        dmf.getjLabelSlika().setIcon(new ImageIcon(slika));
    }
    
    private void pripremiFormu() {
        
    }

    private void pripremifomru(FormaMod mod) {
        switch (mod) {
            case DODAJ:
                dmf.getjButtonKreiraj().setVisible(true);
                dmf.getjButtonPromeni().setVisible(false);
                dmf.getjButtonObriši().setVisible(false);
                dmf.getjTextFieldId().setEnabled(false);
                dmf.setTitle("Kreiranje mesta");
                                
                
                break;
            case IZMENI:
                dmf.getjButtonKreiraj().setVisible(false);
                dmf.getjButtonPromeni().setVisible(true);
                dmf.getjTextFieldId().setEnabled(false);
                dmf.setTitle("Promena mesta");
                
                
                Mesto m = mesto;
                
                dmf.getjTextFieldId().setText(m.getIdMesto() + "");
                
                dmf.getjTextFieldDržava().setText(m.getDržava());
                dmf.getjTextFieldGrad().setText(m.getGrad());
                dmf.getjTextFieldPoštanski().setText(m.getPoštanskiBroj());
                
                
                
                break;
                
            case DETALJI:
                dmf.getjButtonKreiraj().setVisible(false);
                //dmf.getjButtonPromeni().setVisible(false);
                //dmf.getjButtonOdustani().setVisible(false);
                dmf.getjTextFieldId().setEnabled(false);
                //dmf.getjTextFieldDržava().setEnabled(false);
                //dmf.getjTextFieldGrad().setEnabled(false);
                //dmf.getjTextFieldPoštanski().setEnabled(false);
                dmf.setTitle("Detalji o mestu");
                
                
                Mesto m1 = mesto;
                
                dmf.getjTextFieldId().setText(m1.getIdMesto() + "");
                
                dmf.getjTextFieldDržava().setText(m1.getDržava());
                dmf.getjTextFieldGrad().setText(m1.getGrad());
                dmf.getjTextFieldPoštanski().setText(m1.getPoštanskiBroj());
                
                
                
                
                break;
                
            default:
                throw new AssertionError();
        }
    }

    private void addActionListener() {
        dmf.dodajUmetnikaAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //
                dodaj(e);
            }

            private void dodaj(ActionEvent e) {

                    String država = dmf.getjTextFieldDržava().getText().trim();
                    String grad = dmf.getjTextFieldGrad().getText().trim();
                    String poštanski = dmf.getjTextFieldPoštanski().getText().trim();
                
         
                    
     
                    
                    
                    if(država.isEmpty() || grad.isEmpty() || poštanski.isEmpty()){
                        JOptionPane.showMessageDialog(dmf, "Niste popunili sva polja.", "UPOZORENJE", JOptionPane.WARNING_MESSAGE);
                    }

                    else{
                    

                        Mesto mesto = new Mesto(država, grad, poštanski);
                        
                        try{
                            Komunikacija.getInstance().dodajMesto(mesto);
                            JOptionPane.showMessageDialog(dmf, "Sistem je zapamtio mesto.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                            dmf.dispose();

                        } catch(Exception exp){
                            JOptionPane.showMessageDialog(dmf, "Sistem ne može da zapamti mesto.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                            for (Window window : Window.getWindows()) {
                                window.dispose();
                            }
                            Cordinator.getInstance().otvoriLoginFormu();
                            //exp.printStackTrace();
                        }
                
                
                    }
                        
                
                

            
            }
            
            
        });
        
        dmf.addBtnObrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                    int odgovor = JOptionPane.showConfirmDialog(dmf, "Da li ste sigurni da želite da obrišete mesto?", "POTVRDA", JOptionPane.YES_NO_OPTION);
                        if(odgovor ==0){
                            
                            Mesto m = mesto;
                            try{
                                Komunikacija.getInstance().obrisiMesto(m); 
                                Cordinator.getInstance().osveziMestoFormu();
                                JOptionPane.showMessageDialog(dmf, "Sistem je obrisao mesto.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                                dmf.dispose();

                            } catch(Exception exc){
                                JOptionPane.showMessageDialog(dmf, "Sistem ne moze da obriše mesto.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    
                
            }
        });
        
        dmf.izmeniUmetnikaAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //
                izmeni(e);
            }

            private void izmeni(ActionEvent e) {

                
                    int id = Integer.parseInt(dmf.getjTextFieldId().getText());
                            
                    String država = dmf.getjTextFieldDržava().getText().trim();
                    String grad = dmf.getjTextFieldGrad().getText().trim();
                    String poštanski = dmf.getjTextFieldPoštanski().getText().trim();
                    
                    
                    
                    
                    
                    if(država.isEmpty() || grad.isEmpty() || poštanski.isEmpty()){
                        JOptionPane.showMessageDialog(dmf, "Polja nisu pravilno popunjena.", "UPOZORENJE", JOptionPane.WARNING_MESSAGE);
                    }

                    else{
                    

                        Mesto mesto = new Mesto(id, država, grad, poštanski);

                        
                        try{
                            komunikacija.Komunikacija.getInstance().promeniMesto(mesto);
                            JOptionPane.showMessageDialog(dmf, "Sistem je zapamtio mesto.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                            dmf.dispose();

                        } catch(Exception exp){
                            JOptionPane.showMessageDialog(dmf, "Sistem ne može da zapamti mesto.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                            for (Window window : Window.getWindows()) {
                                window.dispose();
                            }
                            Cordinator.getInstance().otvoriLoginFormu();
                            //exp.printStackTrace();
                        }
                
                
                    }
                        
                
                

            
            }
            
            
        });
        
        dmf.odustaniAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //
                odustani(e);
            }

            private void odustani(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(dmf, "Da li stvarno želite da zatvorite formu?", "POTVDRA", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
       

        
                if (option == JOptionPane.YES_OPTION) {
                    dmf.dispose();
                }
                
                
            }

            
        });
    }
}
