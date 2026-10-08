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
public class PretraziStrSpremaSO extends ApstraktnaGenerickaOperacija{
    

    
    StrucnaSprema strucnaSprema;
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if(objekat == null || !(objekat instanceof StrucnaSprema)){
               throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        
        int idStrucneSpreme = ((StrucnaSprema)objekat).getIdStručnaSprema();
        
        
        strucnaSprema = (StrucnaSprema) broker.getObject(((StrucnaSprema)objekat), " WHERE idStrucnaSprema = "  + idStrucneSpreme);
        
    }

    public StrucnaSprema getStrucnaSprema() {
        return strucnaSprema;
    }

    public void setStrucnaSprema(StrucnaSprema strucnaSprema) {
        this.strucnaSprema = strucnaSprema;
    }


    
}
