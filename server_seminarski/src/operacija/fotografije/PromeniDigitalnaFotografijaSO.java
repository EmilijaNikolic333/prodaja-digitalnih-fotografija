/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.fotografije;

import model.DigitalnaFotografija;
import model.Kupac;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author nikol
 */
public class PromeniDigitalnaFotografijaSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if(objekat == null || !(objekat instanceof DigitalnaFotografija)){
            throw new Exception("Sistem ne može da promeni digitalnu fotografiju.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        broker.edit((DigitalnaFotografija)objekat);
    }
    
}
