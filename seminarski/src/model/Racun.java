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
public class Racun implements ApstraktniModelskiObjekat{
    private int idRačun;
    private LocalDate datum;
    private double ukupanIznos;
    private Umetnik umetnik;
    private Kupac kupac;
    private List<StavkaRacuna> stavke = new ArrayList<>();;
    
    

    public Racun() {
    }
    
    public Racun(int idRačun, LocalDate datum, double ukupanIznos, Umetnik umetnik, Kupac kupac, List<StavkaRacuna> stavke) {
        this.idRačun = idRačun;
        this.datum = datum;
        this.ukupanIznos = ukupanIznos;
        this.umetnik = umetnik;
        this.kupac = kupac;
        this.stavke = stavke;  
    }
    
    public Racun(LocalDate datum, double ukupanIznos, Umetnik umetnik, Kupac kupac, List<StavkaRacuna> stavke) {
        
        this.datum = datum;
        this.ukupanIznos = ukupanIznos;
        this.umetnik = umetnik;
        this.kupac = kupac;
        this.stavke = stavke;
    }
    
    
    public Racun(LocalDate datum, double ukupanIznos, Umetnik umetnik, Kupac kupac) {
        
        this.datum = datum;
        this.ukupanIznos = ukupanIznos;
        
        this.umetnik = umetnik;
        this.kupac = kupac; 
        
    }
    
    public Racun(int idRačun, LocalDate datum, double ukupanIznos, Umetnik umetnik, Kupac kupac) {
        this.idRačun = idRačun;
        this.datum = datum;
        this.ukupanIznos = ukupanIznos;
        
        
        
        this.umetnik = umetnik;
        this.kupac = kupac;
       
    }
    
    
    

    public int getIdRačun() {
        return idRačun;
    }

    public void setIdRačun(int idRačun) {
        this.idRačun = idRačun;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public double getUkupanIznos() {
        return ukupanIznos;
    }

    public void setUkupanIznos(double ukupanIznos) {
        this.ukupanIznos = ukupanIznos;
    }

    public Umetnik getUmetnik() {
        return umetnik;
    }

    public void setUmetnik(Umetnik umetnik) {
        this.umetnik = umetnik;
    }

    public Kupac getKupac() {
        return kupac;
    }

    public void setKupac(Kupac kupac) {
        this.kupac = kupac;
    }

    public List<StavkaRacuna> getStavke() {
        return stavke;
    }

    public void setStavke(List<StavkaRacuna> stavke) {
        this.stavke = stavke;
    }

    @Override
    public String toString() {
        return "Racun{id=" + idRačun +
                ", datum=" + datum +
                ", ukupanIznos=" + ukupanIznos +
                ", umetnikId=" + (umetnik != null ? umetnik.getIdUmetnik() : "null") +
                ", kupacId=" + (kupac != null ? kupac.getIdKupac() : "null") +
                ", brojStavki=" + (stavke != null ? stavke.size() : 0) +
                '}';
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
        final Racun other = (Racun) obj;
        if (Double.doubleToLongBits(this.ukupanIznos) != Double.doubleToLongBits(other.ukupanIznos)) {
            return false;
        }
        if (!Objects.equals(this.datum, other.datum)) {
            return false;
        }
        if (!Objects.equals(this.umetnik, other.umetnik)) {
            return false;
        }
        return Objects.equals(this.kupac, other.kupac);
    }

    

    @Override
    public String vratiNazivTabelle() {
        return "racun";
    }

    @Override
    public List<ApstraktniModelskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniModelskiObjekat> lista = new ArrayList<>();
    
    
            while (rs.next()) {
                int idRacun = rs.getInt("r.idRačun"); 
                LocalDate datum = rs.getDate("r.datum").toLocalDate(); 
                Double ukupanIznos = rs.getDouble("r.ukupanIznos");

                int idUmetnik = rs.getInt("u.idUmetnik");
                String ime = rs.getString("u.ime");
                String prezime = rs.getString("u.prezime");
                String korisničkoIme = rs.getString("u.korisničkoIme");
                String šifra = rs.getString("u.šifra");

                Umetnik u = new Umetnik(idUmetnik, ime, prezime, korisničkoIme, šifra);
                
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
                Kupac k = new Kupac(idKupac, imePrezime, email, datumRodjenja, m);

          
                
                Racun r = new Racun(idRacun, datum, ukupanIznos, u, k);
                

                // Dodavanje u listu
                lista.add(r);


            }
    
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "datum, ukupanIznos, umetnik, kupac";
    }

    @Override
    public String vratiVredostiZaUbacivanje() {
        return "'" + datum + "', " + ukupanIznos + ", " + umetnik.getIdUmetnik() + ", " + kupac.getIdKupac();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "racun.idRačun = " + idRačun;
    }

    @Override
    public ApstraktniModelskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        Racun racun = null;
    
            while (rs.next()) {
                int idRacun = rs.getInt("r.idRačun"); 
                LocalDate datum = rs.getDate("r.datum").toLocalDate(); 
                Double ukupanIznos = rs.getDouble("r.ukupanIznos");

                int idUmetnik = rs.getInt("u.idUmetnik");
                String ime = rs.getString("u.ime");
                String prezime = rs.getString("u.prezime");
                String korisničkoIme = rs.getString("u.korisničkoIme");
                String šifra = rs.getString("u.šifra");

                Umetnik u = new Umetnik(idUmetnik, ime, prezime, korisničkoIme, šifra);
                
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
                Kupac k = new Kupac(idKupac, imePrezime, email, datumRodjenja, m);

          
                
                Racun r = new Racun(idRacun, datum, ukupanIznos, u, k);
                

                racun = r;


            }
    
        return racun;
    }

    @Override
    public String vratiVredostiZaIzmenu() {
        return "datum = '" + datum + "', ukupanIznos = " + ukupanIznos + ", umetnik = " + umetnik.getIdUmetnik() + ", kupac = " + kupac.getIdKupac();
    }
    
    
    
}
