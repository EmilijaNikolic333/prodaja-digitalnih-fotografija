/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.umetnici;

import java.util.List;
import model.Kupac;
import model.UmSS;
import model.Umetnik;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author nikol
 */
public class KreirajUmetnikSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if(objekat == null || !(objekat instanceof Umetnik)){
            throw new Exception("Sistem ne može da doda umetnika.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        broker.add((Umetnik)objekat);
        
        List<Umetnik> umetnici1 = controller.Controller.getInstance().ucitajUmetnike();
        for (Umetnik umetnik : umetnici1) { 
            if (umetnik.equals((Umetnik)objekat)) {
                ((Umetnik)objekat).setIdUmetnik(umetnik.getIdUmetnik());

            }
        }
        
        List<UmSS> umetnikStrucneSpreme = ((Umetnik)objekat).getUmetnikStrucnaSprema();
        
        for (UmSS umss : umetnikStrucneSpreme) {

            umss.setUmetnik(((Umetnik)objekat)); // postavi umetnika sa ID-jem
                                    
            broker.add(umss);
        }
                                
        


    }
    
}
