/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.fotografije;



import java.util.List;
import model.DigitalnaFotografija;


import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author nikol
 */
public class PretraziDigitalnaFotografijaSO extends ApstraktnaGenerickaOperacija{
    

    
    DigitalnaFotografija digitalnaFotografija;
    
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if(objekat == null || !(objekat instanceof DigitalnaFotografija)){
               throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        
        int idDigitalneFotografije = ((DigitalnaFotografija)objekat).getIdDigitalnaFotografija();
        
        
        digitalnaFotografija = (DigitalnaFotografija) broker.getObject(((DigitalnaFotografija)objekat), " WHERE idDigitalnaFotografija = "  + idDigitalneFotografije);
       
    }

    public DigitalnaFotografija getDigitalnaFotografija() {
        return digitalnaFotografija;
    }

    public void setDigitalnaFotografija(DigitalnaFotografija digitalnaFotografija) {
        this.digitalnaFotografija = digitalnaFotografija;
    }

    

    
}
