/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.racuni;

import java.util.List;
import model.Kupac;
import model.Racun;
import model.StavkaRacuna;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author nikol
 */
public class KreirajRacunSO extends ApstraktnaGenerickaOperacija{

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if(objekat == null || !(objekat instanceof Racun)){
            throw new Exception("Sistem ne može da doda račun.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        
        Racun racun = (Racun) objekat;
        
        broker.add(racun);
        
        List<Racun> racunii = controller.Controller.getInstance().ucitajRacune();
        for (Racun racun1 : racunii) { 
            if (racun1.equals(racun)) {
                racun.setIdRačun(racun1.getIdRačun());
                                    
            }
        }

                            
        if (racun.getStavke() != null) {
            for (StavkaRacuna stavka : racun.getStavke()) {
                stavka.setRačun(racun); 
                broker.add(stavka);
            }
        }
        
    }
    
}
