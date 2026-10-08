/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.racuni;


import java.util.List;
import model.Racun;
import model.StavkaRacuna;


import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author nikol
 */
public class PretraziRacunSO extends ApstraktnaGenerickaOperacija{
    

    
    Racun racun;
    List<StavkaRacuna> stavkeRacuna;
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if(objekat == null || !(objekat instanceof Racun)){
               throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        
        int idRacuna = ((Racun)objekat).getIdRačun();
        
        
        racun = (Racun) broker.getObject(((Racun)objekat), " r JOIN umetnik u ON r.umetnik = u.idUmetnik JOIN kupac k ON r.kupac = k.idKupac JOIN mesto m ON k.mesto = m.idMesto WHERE r.idRačun = "  + idRacuna);
        
        stavkeRacuna = broker.getAll(new StavkaRacuna(), " sr JOIN racun r ON r.idRačun = sr.račun JOIN digitalnafotografija df ON df.idDigitalnaFotografija = sr.digitalnaFotografija JOIN umetnik u ON r.umetnik = u.idUmetnik JOIN kupac k ON r.kupac = k.idKupac JOIN mesto m ON k.mesto = m.idMesto WHERE r.idRačun = "  + idRacuna);

        racun.setStavke(stavkeRacuna);
    }

    public Racun getRacun() {
        return racun;
    }

    public void setRacun(Racun racun) {
        this.racun = racun;
    }

    

    

    
}
