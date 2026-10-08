/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.kupci;

import java.util.List;
import model.Kupac;
import model.Umetnik;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author nikol
 */
public class VratiListuKupacKupacSO extends ApstraktnaGenerickaOperacija{
    List<Kupac> kupci;
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
       // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        String imeIPrezime = (String)objekat;
        
        kupci = broker.getAll(new Kupac(), " k JOIN mesto m ON m.idMesto = k.mesto WHERE imePrezime = '" + imeIPrezime + "'");
            
        
        
    }

    public List<Kupac> getKupci() {
        return kupci;
    }
}
