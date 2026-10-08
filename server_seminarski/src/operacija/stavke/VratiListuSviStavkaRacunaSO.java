/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.stavke;

import java.util.List;
import model.Mesto;
import model.StavkaRacuna;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author nikol
 */
public class VratiListuSviStavkaRacunaSO extends ApstraktnaGenerickaOperacija {

    List<StavkaRacuna> stavke;
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        stavke = broker.getAll(new StavkaRacuna(), " sr JOIN racun r ON r.idRačun = sr.račun JOIN digitalnafotografija df ON df.idDigitalnaFotografija = sr.digitalnaFotografija JOIN umetnik u ON r.umetnik = u.idUmetnik JOIN kupac k ON r.kupac = k.idKupac JOIN mesto m ON k.mesto = m.idMesto");
    }

    public List<StavkaRacuna> getStavke() {
        return stavke;
    }
    
    
    
}
