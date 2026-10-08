/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.mesto;

import java.util.List;
import model.Kupac;
import model.Mesto;
import operacija.ApstraktnaGenerickaOperacija;

/** 
 *
 * @author nikol
 */
public class VratiListuSviMestoSO extends ApstraktnaGenerickaOperacija{

    List<Mesto> mesta;
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
//
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        mesta = broker.getAll(new Mesto(), "");
    }

    public List<Mesto> getMesta() {
        return mesta;
    }
    
    
}
