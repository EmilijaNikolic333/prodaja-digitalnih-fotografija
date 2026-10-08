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
public class PretraziKupacSO extends ApstraktnaGenerickaOperacija{
    

    
    Kupac kupac;
    
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if(objekat == null || !(objekat instanceof Kupac)){
               throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        
        int idKupca = ((Kupac)objekat).getIdKupac();
        
        
        kupac = (Kupac) broker.getObject(((Kupac)objekat), " k JOIN mesto m ON m.idMesto = k.mesto WHERE k.idKupac = "  + idKupca);
        
    }

    public Kupac getKupac() {
        return kupac;
    }

    public void setKupac(Kupac kupac) {
        this.kupac = kupac;
    }

    

    

    
}
