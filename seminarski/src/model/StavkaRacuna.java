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
public class StavkaRacuna implements ApstraktniModelskiObjekat{
    private Racun račun;
    private int rb;
    private double iznos;
    private int količina;
    private double cenaFotografije;
    private DigitalnaFotografija digitalnaFotografija;

    public StavkaRacuna() {
    }

    public StavkaRacuna(double iznos, int količina, double cenaFotografije, DigitalnaFotografija digitalnaFotografija) {
        
        
        this.iznos = iznos;
        this.količina = količina;
        this.cenaFotografije = cenaFotografije;
        this.digitalnaFotografija = digitalnaFotografija;
    }
    
    public StavkaRacuna(Racun račun, int rb, double iznos, int količina, double cenaFotografije, DigitalnaFotografija digitalnaFotografija) {
        this.račun = račun;
        this.rb = rb;
        this.iznos = iznos;
        this.količina = količina;
        this.cenaFotografije = cenaFotografije;
        this.digitalnaFotografija = digitalnaFotografija;
    }
    
    public StavkaRacuna(int rb, double iznos, int količina, double cenaFotografije, DigitalnaFotografija digitalnaFotografija) {
        //this.račun = račun;
        this.rb = rb;
        this.iznos = iznos;
        this.količina = količina;
        this.cenaFotografije = cenaFotografije;
        this.digitalnaFotografija = digitalnaFotografija;
    }

    public Racun getRačun() {
        return račun;
    }

    public void setRačun(Racun račun) {
        this.račun = račun;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public double getIznos() {
        return iznos;
    }

    public void setIznos(double iznos) {
        this.iznos = iznos;
    }

    public int getKoličina() {
        return količina;
    }

    public void setKoličina(int količina) {
        this.količina = količina;
    }

    public double getCenaFotografije() {
        return cenaFotografije;
    }

    public void setCenaFotografije(double cenaFotografije) {
        this.cenaFotografije = cenaFotografije;
    }

    public DigitalnaFotografija getDigitalnaFotografija() {
        return digitalnaFotografija;
    }

    public void setDigitalnaFotografija(DigitalnaFotografija digitalnaFotografija) {
        this.digitalnaFotografija = digitalnaFotografija;
    }

    @Override
    public String toString() {
        return "StavkaRacuna{" +
                "računId=" + (račun != null ? račun.getIdRačun() : "null") +
                ", rb=" + rb +
                ", iznos=" + iznos +
                ", količina=" + količina +
                ", cenaFotografije=" + cenaFotografije +
                ", digitalnaFotografijaId=" + (digitalnaFotografija != null ? digitalnaFotografija.getIdDigitalnaFotografija() : "null") +
                '}';
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
        final StavkaRacuna other = (StavkaRacuna) obj;
        if (this.rb != other.rb) {
            return false;
        }
        if (Double.doubleToLongBits(this.iznos) != Double.doubleToLongBits(other.iznos)) {
            return false;
        }
        if (this.količina != other.količina) {
            return false;
        }
        if (Double.doubleToLongBits(this.cenaFotografije) != Double.doubleToLongBits(other.cenaFotografije)) {
            return false;
        }
        if (!Objects.equals(this.račun, other.račun)) {
            return false;
        }
        return Objects.equals(this.digitalnaFotografija, other.digitalnaFotografija);
    }

    @Override
    public String vratiNazivTabelle() {
        return "stavkaračuna";
    }

    @Override
    public List<ApstraktniModelskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniModelskiObjekat> lista = new ArrayList<>();
    
    
            while (rs.next()) {
                int rb = rs.getInt("sr.rb");
                
                double iznos = rs.getDouble("sr.iznos");
                
                int kolicina = rs.getInt("sr.količina");
                
                double cenaFotografije = rs.getDouble("sr.cenaFotografije");
                
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
                

                
                int idDigitalnaFotografija = rs.getInt("df.idDigitalnaFotografija");
                String nazivFotografije = rs.getString("df.nazivFotografije");
                double cena = rs.getDouble("df.cena");
                
                DigitalnaFotografija df = new DigitalnaFotografija(idDigitalnaFotografija, nazivFotografije, cena);
                
                StavkaRacuna sr = new StavkaRacuna(r, rb, iznos, kolicina, cenaFotografije, df);
                // Dodavanje u listu
                lista.add(sr);


            }
    
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "rb, račun, iznos, količina, cenaFotografije, digitalnaFotografija";
    }

    @Override
    public String vratiVredostiZaUbacivanje() {
        return rb + ", " + račun.getIdRačun() + ", " + iznos + ", " + količina + ", " + cenaFotografije + ", " + digitalnaFotografija.getIdDigitalnaFotografija();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "stavkaračuna.rb = " + rb + " AND stavkaračuna.račun = " + račun.getIdRačun();
    }

    @Override
    public ApstraktniModelskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVredostiZaIzmenu() {
        return ", iznos = " + iznos + ", količina = " + količina + ", cenaFotografije = " + cenaFotografije + ", digitalnafotografija = " + digitalnaFotografija.getIdDigitalnaFotografija();
    }

    
    
    
}
