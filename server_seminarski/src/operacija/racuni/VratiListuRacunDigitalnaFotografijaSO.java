/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.racuni;

import java.util.List;
import model.Racun;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author nikol
 */
public class VratiListuRacunDigitalnaFotografijaSO extends ApstraktnaGenerickaOperacija{
    List<Racun> racuni;
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        String digitalnaFotogString = (String)objekat;
        racuni = broker.getAll(new Racun(), " r JOIN umetnik u ON r.umetnik = u.idUmetnik JOIN kupac k ON r.kupac = k.idKupac JOIN mesto m ON k.mesto = m.idMesto JOIN stavkaRačuna sr ON sr.račun = r.idRačun JOIN digitalnaFotografija df ON sr.digitalnaFotografija = df.idDigitalnaFotografija WHERE df.nazivFotografije = '" + digitalnaFotogString + "'");
    }

    public List<Racun> getRacuni() {
        return racuni;
    }
}
