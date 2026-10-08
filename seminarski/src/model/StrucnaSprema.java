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
public class StrucnaSprema implements ApstraktniModelskiObjekat{
    private int idStručnaSprema;
    private String naziv;

    public StrucnaSprema() {
    }
    
    public StrucnaSprema(String naziv) {
        
        this.naziv = naziv;
    }

    public StrucnaSprema(int idStručnaSprema, String naziv) {
        this.idStručnaSprema = idStručnaSprema;
        this.naziv = naziv;
    }

    public int getIdStručnaSprema() {
        return idStručnaSprema;
    }

    public void setIdStručnaSprema(int idStručnaSprema) {
        this.idStručnaSprema = idStručnaSprema;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public String toString() {
        return naziv ;
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
        final StrucnaSprema other = (StrucnaSprema) obj;
        if (this.idStručnaSprema != other.idStručnaSprema) {
            return false;
        }
        return Objects.equals(this.naziv, other.naziv);
    }

    @Override
    public String vratiNazivTabelle() {
        return "strucnasprema";
    }

    @Override
    public List<ApstraktniModelskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniModelskiObjekat> lista = new ArrayList<>();
        while(rs.next()){
            int idStručnaSprema = rs.getInt("strucnasprema.idStrucnaSprema");
            String naziv = rs.getString("strucnasprema.naziv");
            

            StrucnaSprema s = new StrucnaSprema(idStručnaSprema, naziv);
            lista.add(s);
        }
        
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "naziv";
    }

    @Override
    public String vratiVredostiZaUbacivanje() {
        return "'" + naziv + "'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "strucnasprema.idStrucnaSprema = " + idStručnaSprema;
    }

    @Override
    public ApstraktniModelskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        StrucnaSprema ss = null;
        while(rs.next()){
            int idStručnaSprema = rs.getInt("strucnasprema.idStrucnaSprema");
            String naziv = rs.getString("strucnasprema.naziv");
            

            StrucnaSprema s = new StrucnaSprema(idStručnaSprema, naziv);
            ss = s;
        }
        
        return ss;
    }

    @Override
    public String vratiVredostiZaIzmenu() {
        return "naziv = '" + naziv + "'";
    }
    
    
}
