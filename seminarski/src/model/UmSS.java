/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Asus
 */
public class UmSS implements ApstraktniModelskiObjekat{
    private Umetnik umetnik;
    private StrucnaSprema stručnaSprema;
    private LocalDate datumZavršetka;
    private String ustanova;

    public UmSS() {
    }

    public UmSS(Umetnik umetnik, StrucnaSprema stručnaSprema, LocalDate datumZavršetka, String ustanova) {
        this.umetnik = umetnik;
        this.stručnaSprema = stručnaSprema;
        this.datumZavršetka = datumZavršetka;
        this.ustanova = ustanova;
    }
    
    public UmSS(StrucnaSprema stručnaSprema, LocalDate datumZavršetka, String ustanova) {
        
        this.stručnaSprema = stručnaSprema;
        this.datumZavršetka = datumZavršetka;
        this.ustanova = ustanova;
    }

    public Umetnik getUmetnik() {
        return umetnik;
    }

    public void setUmetnik(Umetnik umetnik) {
        this.umetnik = umetnik;
    }

    

    public StrucnaSprema getStručnaSprema() {
        return stručnaSprema;
    }

    public void setStručnaSprema(StrucnaSprema stručnaSprema) {
        this.stručnaSprema = stručnaSprema;
    }

    public LocalDate getDatumZavršetka() {
        return datumZavršetka;
    }

    public void setDatumZavršetka(LocalDate datumZavršetka) {
        this.datumZavršetka = datumZavršetka;
    }

    public String getUstanova() {
        return ustanova;
    }

    public void setUstanova(String ustanova) {
        this.ustanova = ustanova;
    }

    @Override
    public String toString() {
        return "UmSS{" + "umetnik=" + umetnik + ", stru\u010dnaSprema=" + stručnaSprema + ", datumZavr\u0161etka=" + datumZavršetka + ", ustanova=" + ustanova + '}';
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
        final UmSS other = (UmSS) obj;
        if (!Objects.equals(this.ustanova, other.ustanova)) {
            return false;
        }
        if (!Objects.equals(this.umetnik, other.umetnik)) {
            return false;
        }
        if (!Objects.equals(this.stručnaSprema, other.stručnaSprema)) {
            return false;
        }
        return Objects.equals(this.datumZavršetka, other.datumZavršetka);
    }

    @Override
    public String vratiNazivTabelle() {
        return "umss";
    }

    @Override
    public List<ApstraktniModelskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniModelskiObjekat> lista = new ArrayList<>();
    
    
            while (rs.next()) {
                
                
                String ustanova = rs.getString("umss.ustanova");
                LocalDate datum = rs.getDate("umss.datumZavršetka").toLocalDate(); 
                
                
                

                
                
                int idUmetnik = rs.getInt("u.idUmetnik");
                String ime = rs.getString("u.ime");
                String prezime = rs.getString("u.prezime");
                String korisničkoIme = rs.getString("u.korisničkoIme");
                String šifra = rs.getString("u.šifra");

                Umetnik u = new Umetnik(idUmetnik, ime, prezime, korisničkoIme, šifra);
                
                
                int idStrucnaSprema = rs.getInt("ss.idStrucnaSprema");
                String naziv = rs.getString("ss.naziv");
                
                StrucnaSprema ss = new StrucnaSprema(idStrucnaSprema, naziv);
                
                
                UmSS umss = new UmSS(u, ss, datum, ustanova);
          
                
                

                
                // Dodavanje u listu
                lista.add(umss);


            }
    
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "umetnik, stručnaSprema, datumZavršetka, ustanova";
    }

    @Override
    public String vratiVredostiZaUbacivanje() {
        return umetnik.getIdUmetnik() + ", " + stručnaSprema.getIdStručnaSprema() + ", '" + datumZavršetka + "', '" + ustanova + "'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "umetnik = " + umetnik.getIdUmetnik() + " AND stručnaSprema = " + stručnaSprema.getIdStručnaSprema();
    }

    @Override
    public ApstraktniModelskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVredostiZaIzmenu() {
        return "umetnik.idUmetnik = " + umetnik.getIdUmetnik() + ", stručnaSprema.idStručnaSprema = " + stručnaSprema.getIdStručnaSprema() + ", datumZavršetka = '" + datumZavršetka + "', ustanova = '" + ustanova + "'";
    }
    
    
}
