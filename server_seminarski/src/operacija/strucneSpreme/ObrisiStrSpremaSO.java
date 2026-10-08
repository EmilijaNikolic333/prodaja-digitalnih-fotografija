/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.strucneSpreme;

import java.sql.SQLIntegrityConstraintViolationException;
import model.StavkaRacuna;
import model.StrucnaSprema;
import model.UmSS;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author nikol
 */
public class ObrisiStrSpremaSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if(objekat == null || !(objekat instanceof StrucnaSprema)){
            throw new Exception("Sistem ne može da obriše stručnu spremu.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        try {
            broker.delete((StrucnaSprema)objekat);
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new Exception("Stručna sprema ne može da se obriše jer ima povezane zapise u bazi.");
        }
    }
    
}
