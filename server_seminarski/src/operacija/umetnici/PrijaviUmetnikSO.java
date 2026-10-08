/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.umetnici;

import java.util.List;
import model.Kupac;
import model.Umetnik;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author nikol
 */
public class PrijaviUmetnikSO extends ApstraktnaGenerickaOperacija {

    
    Umetnik umetnik;
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if(objekat == null || !(objekat instanceof Umetnik)){
            throw new Exception("Korisničko ime i šifra nisu ispravni.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        List<Umetnik> sviUmetnici = broker.getAll((Umetnik)objekat, null);
        
        for (Umetnik u : sviUmetnici) {
            if(u.equals((Umetnik)objekat)){
                umetnik = u;
                return;
            }
        }
        umetnik = null;
    }

    public Umetnik getUmetnik() {
        return umetnik;
    }

    
    
    
}
