/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.umetnikStrucneSpreme;

import java.util.List;
import model.Racun;
import model.UmSS;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author nikol
 */
public class VratiListuSviUmSSSO extends ApstraktnaGenerickaOperacija{
    
    List<UmSS> umetniciStrucneSpreme;
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        
        umetniciStrucneSpreme = broker.getAll(new UmSS(), " umss JOIN umetnik u ON umss.umetnik = u.idUmetnik JOIN strucnasprema ss ON umss.stručnaSprema = ss.idStrucnaSprema");
    }

    public List<UmSS> getRacuni() {
        return umetniciStrucneSpreme;
    }
    
}
