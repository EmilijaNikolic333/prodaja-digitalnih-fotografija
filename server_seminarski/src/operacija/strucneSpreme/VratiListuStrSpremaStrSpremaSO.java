/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.strucneSpreme;

import java.util.List;
import model.StrucnaSprema;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author nikol
 */
public class VratiListuStrSpremaStrSpremaSO extends ApstraktnaGenerickaOperacija {
    
    
    List<StrucnaSprema> strucneSpreme;
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        
        String naziv = (String) objekat;
        strucneSpreme = broker.getAll(new StrucnaSprema(), " WHERE naziv = '" + naziv + "' ORDER BY idStrucnaSprema");
    }

    public List<StrucnaSprema> getStrucneSpreme() {
        return strucneSpreme;
    }
}
