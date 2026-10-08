/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.mesto;

import java.sql.SQLIntegrityConstraintViolationException;
import model.Kupac;
import model.Mesto;
import model.StavkaRacuna;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author nikol
 */
public class ObrisiMestoSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if(objekat == null || !(objekat instanceof Mesto)){
            throw new Exception("Sistem ne može da obriše mesto.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        try {
            broker.delete((Mesto)objekat);
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new Exception("Mesto ne može da se obriše jer ima povezane zapise u bazi.");
        }
    }
    
}
