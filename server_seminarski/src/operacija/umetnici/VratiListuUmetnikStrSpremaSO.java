/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.umetnici;

import java.util.List;
import model.Umetnik;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author nikol
 */
public class VratiListuUmetnikStrSpremaSO extends ApstraktnaGenerickaOperacija{
    List<Umetnik> umetnici;
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
       
        String strucnaSprema = (String)objekat;
        umetnici = broker.getAll(new Umetnik(), " JOIN umss ON umetnik.idUmetnik = umss.umetnik JOIN strucnasprema ON umss.stručnaSprema = strucnasprema.idStrucnaSprema WHERE strucnasprema.naziv = '" + strucnaSprema + "' ORDER BY idUmetnik");
        
        
        
    }

    public List<Umetnik> getUmetnici() {
        return umetnici;
    }
}
