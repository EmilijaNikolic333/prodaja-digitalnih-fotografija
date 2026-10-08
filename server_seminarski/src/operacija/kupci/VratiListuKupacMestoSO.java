/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.kupci;

import java.util.List;
import model.Kupac;
import model.Mesto;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author nikol
 */
public class VratiListuKupacMestoSO extends ApstraktnaGenerickaOperacija{
    List<Kupac> kupci;
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if(objekat == null || !(objekat instanceof Mesto)){
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        int idMesta = ((Mesto)objekat).getIdMesto();
        
        kupci = broker.getAll(new Kupac(), " k JOIN mesto m ON m.idMesto = k.mesto WHERE k.mesto = '" + idMesta + "'");
            
        
        
    }

    public List<Kupac> getKupci() {
        return kupci;
    }
}
