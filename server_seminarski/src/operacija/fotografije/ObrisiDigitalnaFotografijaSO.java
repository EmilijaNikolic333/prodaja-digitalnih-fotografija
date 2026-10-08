/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.fotografije;

import java.sql.SQLIntegrityConstraintViolationException;
import model.DigitalnaFotografija;
import model.Kupac;
import model.Umetnik;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author nikol
 */
public class ObrisiDigitalnaFotografijaSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if(objekat == null || !(objekat instanceof DigitalnaFotografija)){
            throw new Exception("Sistem ne može da obriše digitalnu fotografiju.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        
        try {
            broker.delete((DigitalnaFotografija)objekat);
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new Exception("Digitalna fotografija ne može da se obriše jer ima povezane zapise u bazi.");
        }
    }
    
}
