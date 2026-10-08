/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.umetnici;

import java.util.List;
import model.UmSS;
import model.Umetnik;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author nikol
 */
public class PretraziUmetnikSO extends ApstraktnaGenerickaOperacija{
    

    
    Umetnik umetnik;
    List<UmSS> umetnikStrucneSpreme;
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if(objekat == null || !(objekat instanceof Umetnik)){
               throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        
        int idUmetnika = ((Umetnik)objekat).getIdUmetnik();
        
        umetnik = (Umetnik) broker.getObject(((Umetnik)objekat), " WHERE idUmetnik = "  + idUmetnika);
        
        umetnikStrucneSpreme = broker.getAll(new UmSS(), " umss JOIN umetnik u ON umss.umetnik = u.idUmetnik JOIN strucnasprema ss ON umss.stručnaSprema = ss.idStrucnaSprema WHERE idUmetnik = "  + idUmetnika);

        umetnik.setUmetnikStrucnaSprema(umetnikStrucneSpreme);
        
    }

    

    public Umetnik getUmetnik() {
        return umetnik;
    }

    public void setUmetnik(Umetnik umetnik) {
        this.umetnik = umetnik;
    }

    
}
