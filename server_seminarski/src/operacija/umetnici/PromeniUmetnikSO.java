/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.umetnici;

import java.util.List;
import model.Kupac;
import model.StrucnaSprema;
import model.UmSS;
import model.Umetnik;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author nikol
 */
public class PromeniUmetnikSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if(objekat == null || !(objekat instanceof Umetnik)){
            throw new Exception("Sistem ne može da promeni umetnika.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        broker.edit((Umetnik)objekat);
        
        List<UmSS> umetniciStrucneSpreme = controller.Controller.getInstance().ucitajUmetnikeStrucneSpreme();
        for (UmSS umetnikss : umetniciStrucneSpreme) {
            if(umetnikss.getUmetnik().getIdUmetnik() == ((Umetnik)objekat).getIdUmetnik()){
                broker.delete(umetnikss);
            }
        }
                                
        List<UmSS> umetniciStrucneSpreme1 = ((Umetnik)objekat).getUmetnikStrucnaSprema();
                                
        for (UmSS umetnikss1 : umetniciStrucneSpreme1) {
                                    
            umetnikss1.setUmetnik(((Umetnik)objekat)); 
            broker.add(umetnikss1);
        }
                                
                                
    }
    
}
