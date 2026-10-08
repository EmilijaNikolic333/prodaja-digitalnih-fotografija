/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.umetnikStrucneSpreme;

import java.sql.SQLIntegrityConstraintViolationException;
import model.StrucnaSprema;
import model.UmSS;
import model.Umetnik;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author nikol
 */
public class ObrisiUmSSSO extends ApstraktnaGenerickaOperacija{
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if(objekat == null || !(objekat instanceof UmSS)){
            throw new Exception("Sistem ne može da obriše umetnika stručnu spremu.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        try {
            broker.delete((UmSS)objekat);
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new Exception("Umetnik stručna sprema ne može da se obriše jer ima povezane zapise u bazi.");
        }
    }
}
