/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.fotografije;

import java.util.List;
import model.DigitalnaFotografija;
import model.Kupac;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author nikol
 */
public class VratiListuSviDigitalnaFotografijaSO extends ApstraktnaGenerickaOperacija{
    List<DigitalnaFotografija> digitalneFotografije;
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        digitalneFotografije = broker.getAll(new DigitalnaFotografija(), "");
    }

    public List<DigitalnaFotografija> getDigitalneFotografije() {
        return digitalneFotografije;
    }
    
    
    
    
    
    
}
