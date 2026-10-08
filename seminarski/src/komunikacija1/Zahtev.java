/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija1;

import java.io.Serializable;
import model.Racun;
import model.StavkaRacuna;

/**
 *
 * @author Asus
 */
public class Zahtev implements Serializable {
    private Operacija operacija;
    private Object parametar;

    public Zahtev() {
    }

    public Zahtev(Operacija operacija, Object parametar) {
        this.operacija = operacija;
        this.parametar = parametar;
    }

    

    public Operacija getOperacija() {
        return operacija;
    }

    public Object getParametar() {
        return parametar;
    }

    public void setOperacija(Operacija operacija) {
        this.operacija = operacija;
    }

    public void setParametar(Object parametar) {
        this.parametar = parametar;
    }
    
    
}
