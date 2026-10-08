/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.umetnici;

import java.util.List;
import model.Umetnik;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author nikol
 */
public class VratiListuUmetnikUmetnikSO extends ApstraktnaGenerickaOperacija{
    
    
    List<Umetnik> umetnici;
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        String imeIPrezime[] = ((String)objekat).split(" ");
        if(imeIPrezime.length == 2){
            String ime = imeIPrezime[0]; 
            String prezime = imeIPrezime[1];
            umetnici = broker.getAll(new Umetnik(), " WHERE ime = '" + ime + "' AND prezime = '" + prezime + "' ORDER BY idUmetnik");
        }
        if(imeIPrezime.length == 1){
            String ime = (String)objekat;
            umetnici = broker.getAll(new Umetnik(), " WHERE ime = '" + ime + "' ORDER BY idUmetnik");
        }
        
        
    }

    public List<Umetnik> getUmetnici() {
        return umetnici;
    }
}
