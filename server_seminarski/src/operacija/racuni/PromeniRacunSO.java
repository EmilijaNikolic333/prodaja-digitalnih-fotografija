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
public class PromeniRacunSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if(objekat == null || !(objekat instanceof Racun)){
            throw new Exception("Sistem ne može da promeni račun.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        
        broker.edit((Racun)objekat);
        
        List<StavkaRacuna> sveStavke = controller.Controller.getInstance().ucitajStavke();
        for (StavkaRacuna stavkaRacuna : sveStavke) {
            if(stavkaRacuna.getRačun().getIdRačun() == ((Racun)objekat).getIdRačun()){
                broker.delete(stavkaRacuna);
            }
        }
                                
        List<StavkaRacuna> stavke1 = ((Racun)objekat).getStavke();
                                
        for (StavkaRacuna stavka : stavke1) {
                                    
            stavka.setRačun(((Racun)objekat));
            broker.add(stavka);
        }
    }
    
}
