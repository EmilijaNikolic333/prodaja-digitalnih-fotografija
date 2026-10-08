/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.mesto;


import java.util.List;
import model.Mesto;

import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author nikol
 */
public class PretraziMestoSO extends ApstraktnaGenerickaOperacija{
    

    
    Mesto mesto;

    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if(objekat == null || !(objekat instanceof Mesto)){
               throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        
        int idMesto = ((Mesto)objekat).getIdMesto();
        
        mesto = (Mesto) broker.getObject(((Mesto)objekat), " WHERE idMesto = "  + idMesto);
        
    }

    public Mesto getMesto() {
        return mesto;
    }

    public void setMesto(Mesto mesto) {
        this.mesto = mesto;
    }


   
    
    
}
