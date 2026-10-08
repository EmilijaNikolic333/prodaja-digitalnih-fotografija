/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import com.sun.java.accessibility.util.AWTEventMonitor;
import cordinator.Cordinator;
import forme.LoginForma;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import komunikacija1.Zahtev;
import model.Umetnik;

/**
 *
 * @author nikol
 */
public class LoginController {
    
    private final LoginForma lf;

    public LoginController(LoginForma lf) {
        this.lf = lf;
        addActionListeners();
        lf.setTitle("Login");
        ImageIcon ikonica = new ImageIcon(getClass().getResource("/slike/digital-drawing (2).png"));  // Stavi sliku u src/ikonice folder
        lf.setIconImage(ikonica.getImage());
        
        lf.getContentPane().setBackground(new Color(230, 200, 255)); // Svetlo ljubičasta
        lf.getjButtonPrikažiŠifru().setBackground(new Color(138, 43, 226));
        lf.getjButtonPrikažiŠifru().setForeground(Color.white);
        lf.getjButton1().setBackground(new Color(138, 43, 226));
        lf.getjButton1().setForeground(Color.white);
        
        lf.getjButton2().setBackground(new Color(138, 43, 226));
        lf.getjButton2().setForeground(Color.white);
    }
    
    
    
    

    private void addActionListeners() {
        lf.loginAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    prijava(e);
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            private void prijava(ActionEvent e) throws IOException, ClassNotFoundException, Exception {
                String ki = lf.getjTextFieldKorisnickoIme().getText().trim();
                String sifra = String.valueOf(lf.getjPasswordFieldSifra().getPassword()).trim();
                 
                Komunikacija.getInstance().konekcija();
                
                boolean uspesno = Komunikacija.uspeh;
                if(uspesno == true){
                    
                    Umetnik ulogovani = Komunikacija.getInstance().login(ki, sifra);
                    
                    
                
                
                    if(ulogovani == null){
                        JOptionPane.showMessageDialog(lf, "Korisničko ime i šifra nisu ispravni.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                        
                    } else{
                        
                        boolean postoji = false;
                        List<Umetnik> ulogovaniUmetnici = komunikacija.Komunikacija.getInstance().ucitajUlogovane();
                        for (Umetnik ulogovan : ulogovaniUmetnici) {
                            if(ulogovan.getIdUmetnik() == ulogovani.getIdUmetnik()){
                                JOptionPane.showMessageDialog(lf, "Korisnik je već prijavljen.", "UPOZORENJE", JOptionPane.WARNING_MESSAGE);
                                postoji = true;
                            }
                        }

                        
                        if(postoji == false){
                            Cordinator.getInstance().setUlogovani(ulogovani);
                            JOptionPane.showMessageDialog(lf, "Korisničko ime i šifra su ispravni.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                            komunikacija.Komunikacija.getInstance().dodajUlogovanog(ulogovani);
                            Cordinator.getInstance().otvoriGlavnuFormu();
                            lf.dispose();
                        }
                        
                        


                        
                    }
                
                }   else{
                    JOptionPane.showMessageDialog(lf, "Ne može da se otvori glavna forma i meni.", "GREŠKA", JOptionPane.ERROR_MESSAGE);

                }
                

            }
        });
        
        lf.prikažiŠifruAddActionListener(new ActionListener() {
            private boolean prikazana = false;
            @Override
            public void actionPerformed(ActionEvent e) {
                prikazi(e);
            }

            private void prikazi(ActionEvent e) {
                
                
                if (prikazana) {
                    lf.getjPasswordFieldSifra().setEchoChar('*'); // Vraća zvezdice
                    lf.getjButtonPrikažiŠifru().setText("prikaži šifru");
                } else {
                    lf.getjPasswordFieldSifra().setEchoChar((char) 0); // Prikazuje lozinku
                    lf.getjButtonPrikažiŠifru().setText("sakrij šifru");
                }
                prikazana = !prikazana;
                
                
                
                

            }
        });
        
        lf.odustaniAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //
                odustani(e);
            }

            private void odustani(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(lf, "Da li stvarno želite da odustanete?", "POTVDRA", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
       

        
                if (option == JOptionPane.YES_OPTION) {
                    lf.dispose();
                }
                
                
            }

            
        });
        
    
    }

    public void otvoriFormu() {
        lf.setVisible(true);
    }
    
    
    
    

    
    
    
    
    
}
