/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Asus
 */
public class DigitalnaFotografija implements ApstraktniModelskiObjekat{
    private int idDigitalnaFotografija;
    private String nazivFotografije;
    private double cena;

    public DigitalnaFotografija() {
    }

    public DigitalnaFotografija(String nazivFotografije, double cena) {
        
        this.nazivFotografije = nazivFotografije;
        this.cena = cena;
    }
    
    
    public DigitalnaFotografija(int idDigitalnaFotografija, String nazivFotografije, double cena) {
        this.idDigitalnaFotografija = idDigitalnaFotografija;
        this.nazivFotografije = nazivFotografije;
        this.cena = cena;
    }

    public int getIdDigitalnaFotografija() {
        return idDigitalnaFotografija;
    }

    public void setIdDigitalnaFotografija(int idDigitalnaFotografija) {
        this.idDigitalnaFotografija = idDigitalnaFotografija;
    }

    public String getNazivFotografije() {
        return nazivFotografije;
    }

    public void setNazivFotografije(String nazivFotografije) {
        this.nazivFotografije = nazivFotografije;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    @Override
    public String toString() {
        return nazivFotografije;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DigitalnaFotografija other = (DigitalnaFotografija) obj;
        if (this.idDigitalnaFotografija != other.idDigitalnaFotografija) {
            return false;
        }
        if (Double.doubleToLongBits(this.cena) != Double.doubleToLongBits(other.cena)) {
            return false;
        }
        return Objects.equals(this.nazivFotografije, other.nazivFotografije);
    }

    @Override
    public String vratiNazivTabelle() {
        return "digitalnafotografija";
    }

    @Override
    public List<ApstraktniModelskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniModelskiObjekat> lista = new ArrayList<>();
        while(rs.next()){
            int idDigitalnaFotografija = rs.getInt("digitalnafotografija.idDigitalnaFotografija");
            String nazivFotografije = rs.getString("digitalnafotografija.nazivFotografije");
            double cena = rs.getDouble("digitalnafotografija.cena");
            
            DigitalnaFotografija d = new DigitalnaFotografija(idDigitalnaFotografija, nazivFotografije, cena);
            lista.add(d);
        }
        
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "nazivFotografije, cena";
    }

    @Override
    public String vratiVredostiZaUbacivanje() {
        return "'" + nazivFotografije + "', " + cena;
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "digitalnafotografija.idDigitalnaFotografija = " + idDigitalnaFotografija;
    }

    @Override
    public ApstraktniModelskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        DigitalnaFotografija df = null;
        while(rs.next()){
            int idDigitalnaFotografija = rs.getInt("digitalnafotografija.idDigitalnaFotografija");
            String nazivFotografije = rs.getString("digitalnafotografija.nazivFotografije");
            double cena = rs.getDouble("digitalnafotografija.cena");
            
            DigitalnaFotografija d = new DigitalnaFotografija(idDigitalnaFotografija, nazivFotografije, cena);
            df = d;
        }
        
        return df;
    }

    @Override
    public String vratiVredostiZaIzmenu() {
        return "nazivFotografije = '" + nazivFotografije + "', cena = " + cena; 
    }
    
    
    
}
