/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.umetnikStrucneSpreme;

import model.StrucnaSprema;
import model.UmSS;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author nikol
 */
public class KreirajUmSSSO extends ApstraktnaGenerickaOperacija {
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if(objekat == null || !(objekat instanceof UmSS)){
            throw new Exception("Sistem ne može da doda umetnika stručnu spremu.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        broker.add((UmSS)objekat);
    }
}
