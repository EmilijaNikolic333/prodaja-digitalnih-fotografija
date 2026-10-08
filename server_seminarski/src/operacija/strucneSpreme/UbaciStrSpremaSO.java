/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.strucneSpreme;

import model.StrucnaSprema;
import model.Umetnik;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author nikol
 */
public class UbaciStrSpremaSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if(objekat == null || !(objekat instanceof StrucnaSprema)){
            throw new Exception("Sistem ne može da doda stručnu spremu.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        broker.add((StrucnaSprema)objekat);
    }
    
}
