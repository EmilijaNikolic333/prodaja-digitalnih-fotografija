/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.kupci;

import java.sql.SQLIntegrityConstraintViolationException;
import model.Kupac;
import model.Mesto;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author nikol
 */
public class ObrisiKupacSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if(objekat == null || !(objekat instanceof Kupac)){
            throw new Exception("Sistem ne može da obriše kupca.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        try {
            broker.delete((Kupac)objekat);
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new Exception("Kupac ne može da se obriše jer ima povezane zapise u bazi.");
        }
    }
    
}
