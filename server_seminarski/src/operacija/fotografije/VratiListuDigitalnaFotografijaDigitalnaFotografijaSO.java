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
public class VratiListuDigitalnaFotografijaDigitalnaFotografijaSO extends ApstraktnaGenerickaOperacija{
List<DigitalnaFotografija> digitalneFotografije;
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if(objekat == null || !(objekat instanceof DigitalnaFotografija)){
               throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        
        String naziv = ((DigitalnaFotografija)objekat).getNazivFotografije();
        double cena = ((DigitalnaFotografija)objekat).getCena();
        
        
        
        
        
        
        
        if(!naziv.isEmpty() && cena != -1){
            digitalneFotografije = broker.getAll(new DigitalnaFotografija(), " WHERE nazivFotografije = '" + naziv + "' AND cena = '" + cena + "'");
            
        }else{
            if(cena != -1){
                
                digitalneFotografije = broker.getAll(new DigitalnaFotografija(), " WHERE cena = '" + cena + "'");
                return;
            }
        
            if(!naziv.isEmpty()){
                digitalneFotografije = broker.getAll(new DigitalnaFotografija(), " WHERE nazivFotografije = '" + naziv + "'");
                return;
            }
        }
        
        
        
        

    }
        
    

    public List<DigitalnaFotografija> getDigitalneFotografije() {
        return digitalneFotografije;
    }
    
}
