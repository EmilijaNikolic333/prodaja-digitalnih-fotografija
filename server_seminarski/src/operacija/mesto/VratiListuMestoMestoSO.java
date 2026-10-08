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
public class VratiListuMestoMestoSO extends ApstraktnaGenerickaOperacija{
     List<Mesto> mesta;
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if(objekat == null || !(objekat instanceof Mesto)){
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
    }
    
    
    

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        
        String drzava = ((Mesto)objekat).getDržava();
        String grad = ((Mesto)objekat).getGrad();
        String postanski = ((Mesto)objekat).getPoštanskiBroj();
        
        if(!drzava.isEmpty() && !grad.isEmpty() && !postanski.isEmpty()){
            mesta = broker.getAll(new Mesto(), " WHERE država = '" + drzava + "' AND grad = '" + grad + "' AND poštanskiBroj = '" + postanski + "'");
            return;
        }
        
        if(drzava.isEmpty() && !grad.isEmpty() && !postanski.isEmpty()){
            mesta = broker.getAll(new Mesto(), " WHERE grad = '" + grad + "' AND poštanskiBroj = '" + postanski + "'");
            return;
        }
        
        if(!drzava.isEmpty() && grad.isEmpty() && !postanski.isEmpty()){
            mesta = broker.getAll(new Mesto(), " WHERE država = '" + drzava + "' AND poštanskiBroj = '" + postanski + "'");
            return;
        }
        
        if(!drzava.isEmpty() && !grad.isEmpty() && postanski.isEmpty()){
            mesta = broker.getAll(new Mesto(), " WHERE država = '" + drzava + "' AND grad = '" + grad + "'");
            return;
        }
        
        if(!drzava.isEmpty() && grad.isEmpty() && postanski.isEmpty()){
            mesta = broker.getAll(new Mesto(), " WHERE država = '" + drzava + "' ");
            return;
        }
        
        if(drzava.isEmpty() && !grad.isEmpty() && postanski.isEmpty()){
            mesta = broker.getAll(new Mesto(), " WHERE grad = '" + grad + "'");
            return;
        }
        
        
        if(drzava.isEmpty() && grad.isEmpty() && !postanski.isEmpty()){
            mesta = broker.getAll(new Mesto(), " WHERE poštanskiBroj = '" + postanski + "'");
            return;
        }
    }

    public List<Mesto> getMesta() {
        return mesta;
    }
}
