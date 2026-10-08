/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import model.ApstraktniModelskiObjekat;
import model.Mesto;


/**
 *
 * @author Asus
 */
public class Kupac implements ApstraktniModelskiObjekat{
    private int idKupac;
    private String imePrezime;
    private String email;
    private LocalDate datum;
    private Mesto mesto;
    
    

    public Kupac() {
    }

    public Kupac(int idKupac, String imePrezime, String email, LocalDate datum, Mesto mesto) {
        this.idKupac = idKupac;
        this.imePrezime = imePrezime;
        this.email = email;
        this.datum = datum;
        this.mesto = mesto;
    }

    public int getIdKupac() {
        return idKupac;
    }

    public void setIdKupac(int idKupac) {
        this.idKupac = idKupac;
    }

    public String getImePrezime() {
        return imePrezime;
    }

    public void setImePrezime(String imePrezime) {
        this.imePrezime = imePrezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public Mesto getMesto() {
        return mesto;
    }

    public void setMesto(Mesto mesto) {
        this.mesto = mesto;
    }

    @Override
    public String toString() {
        return imePrezime;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Kupac other = (Kupac) obj;
        if (this.idKupac != other.idKupac) {
            return false;
        }
        if (!Objects.equals(this.imePrezime, other.imePrezime)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.datum, other.datum)) {
            return false;
        }
        return Objects.equals(this.mesto, other.mesto);
    }

    @Override
    public String vratiNazivTabelle() {
        return "kupac";
    }

    @Override
    public List<ApstraktniModelskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniModelskiObjekat> lista = new ArrayList<>();
    
    
            while (rs.next()) {
                int idKupac = rs.getInt("k.idKupac");
                String imePrezime = rs.getString("k.imePrezime");
                String email = rs.getString("k.email");

                LocalDate datumRodjenja = rs.getDate("k.datum").toLocalDate();

                
                int idMesto = rs.getInt("m.idMesto");
                String država = rs.getString("m.država");
                String grad = rs.getString("m.grad");
                String poštanskiBroj = rs.getString("m.poštanskiBroj");

                Mesto m = new Mesto(idMesto, država, grad, poštanskiBroj);

                

                // Kreiranje Kupca
                Kupac kupac = new Kupac(idKupac, imePrezime, email, datumRodjenja, m);

                // Dodavanje u listu
                lista.add(kupac);


            }
    
        return lista;
        
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "imePrezime, email, datum, mesto";
    }

    @Override
    public String vratiVredostiZaUbacivanje() {
        return "'" + imePrezime + "', '" + email + "', '" + datum + "', " + mesto.getIdMesto();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "kupac.idKupac = " + idKupac;
    }

    @Override
    public ApstraktniModelskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        Kupac kupac = null;
    
    
            while (rs.next()) {
                int idKupac = rs.getInt("k.idKupac");
                String imePrezime = rs.getString("k.imePrezime");
                String email = rs.getString("k.email");

                LocalDate datumRodjenja = rs.getDate("k.datum").toLocalDate();

                
                int idMesto = rs.getInt("m.idMesto");
                String država = rs.getString("m.država");
                String grad = rs.getString("m.grad");
                String poštanskiBroj = rs.getString("m.poštanskiBroj");

                Mesto m = new Mesto(idMesto, država, grad, poštanskiBroj);

                

                // Kreiranje Kupca
                Kupac kupac1 = new Kupac(idKupac, imePrezime, email, datumRodjenja, m);

                // Dodavanje u listu
                kupac = kupac1;


            }
    
        return kupac;
    }

    @Override
    public String vratiVredostiZaIzmenu() {
        return "imePrezime = '" + imePrezime + "', email = '" + email + "', datum = '" + datum + "', mesto = " + mesto.getIdMesto();
    }
    
    
    
}
