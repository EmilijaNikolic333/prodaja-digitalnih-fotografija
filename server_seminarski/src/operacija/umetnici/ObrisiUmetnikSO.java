/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.umetnici;

import model.StavkaRacuna;
import model.Umetnik;
import operacija.ApstraktnaGenerickaOperacija;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 *
 * @author nikol
 */
public class ObrisiUmetnikSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if(objekat == null || !(objekat instanceof Umetnik)){
            throw new Exception("Sistem ne može da obriše umetnika.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        try {
            broker.delete((Umetnik)objekat);
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new Exception("Umetnik ne može da se obriše jer ima povezane zapise u bazi.");
        }
    }
    
}
