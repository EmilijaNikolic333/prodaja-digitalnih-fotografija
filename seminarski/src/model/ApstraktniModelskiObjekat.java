/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.List;
import java.sql.ResultSet;

/**
 *
 * @author Asus
 */
public interface ApstraktniModelskiObjekat extends Serializable {
    
    public String vratiNazivTabelle();
    
    public List<ApstraktniModelskiObjekat> vratiListu(ResultSet rs) throws Exception;
    
    public String vratiKoloneZaUbacivanje();
    
    public String vratiVredostiZaUbacivanje();
    
    public String vratiPrimarniKljuc();
    
    public ApstraktniModelskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception;
    
    public String vratiVredostiZaIzmenu();

}