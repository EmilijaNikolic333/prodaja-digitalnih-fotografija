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
public class Umetnik implements ApstraktniModelskiObjekat{
    private int idUmetnik;
    private String ime;
    private String prezime;
    private String korisničkoIme;
    private String šifra;
    private List<UmSS> umetnikStrucnaSprema;
    

    public Umetnik() {
    }

    public Umetnik(int idUmetnik, String ime, String prezime, String korisničkoIme, String šifra, List<UmSS> umetnikStrucnaSprema) {
        this.idUmetnik = idUmetnik;
        this.ime = ime;
        this.prezime = prezime;
        this.korisničkoIme = korisničkoIme;
        this.šifra = šifra;
        this.umetnikStrucnaSprema = umetnikStrucnaSprema;
    }
    
    public Umetnik(String ime, String prezime, String korisničkoIme, String šifra, List<UmSS> umetnikStrucnaSprema) {
        
        this.ime = ime;
        this.prezime = prezime;
        this.korisničkoIme = korisničkoIme;
        this.šifra = šifra;
        this.umetnikStrucnaSprema = umetnikStrucnaSprema;
    }
    
    
    public Umetnik(String ime, String prezime, String korisničkoIme, String šifra) {
        
        this.ime = ime;
        this.prezime = prezime;
        this.korisničkoIme = korisničkoIme;
        this.šifra = šifra;
    }
    
    public Umetnik(int idUmetnik, String ime, String prezime, String korisničkoIme, String šifra) {
        this.idUmetnik = idUmetnik;
        this.ime = ime;
        this.prezime = prezime;
        this.korisničkoIme = korisničkoIme;
        this.šifra = šifra;
    }

    public List<UmSS> getUmetnikStrucnaSprema() {
        return umetnikStrucnaSprema;
    }

    public void setUmetnikStrucnaSprema(List<UmSS> umetnikStrucnaSprema) {
        this.umetnikStrucnaSprema = umetnikStrucnaSprema;
    }
    
    

    

    
    
    public int getIdUmetnik() {
        return idUmetnik;
    }

    public void setIdUmetnik(int idUmetnik) {
        this.idUmetnik = idUmetnik;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getKorisničkoIme() {
        return korisničkoIme;
    }

    public void setKorisničkoIme(String korisničkoIme) {
        this.korisničkoIme = korisničkoIme;
    }

    public String getŠifra() {
        return šifra;
    }

    public void setŠifra(String šifra) {
        this.šifra = šifra;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
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
        final Umetnik other = (Umetnik) obj;
        if (!Objects.equals(this.korisničkoIme, other.korisničkoIme)) {
            return false;
        }
        return Objects.equals(this.šifra, other.šifra);
    }

    
    
    
    
    
    
    @Override
    public String vratiNazivTabelle() {
        return "umetnik";
    }

    @Override
    public List<ApstraktniModelskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniModelskiObjekat> lista = new ArrayList<>();
        while(rs.next()){
            int idUmetnik = rs.getInt("umetnik.idUmetnik");
            String ime = rs.getString("umetnik.ime");
            String prezime = rs.getString("umetnik.prezime");
            String korisničkoIme = rs.getString("umetnik.korisničkoIme");
            String šifra = rs.getString("umetnik.šifra");
            
            
            
            
            
            Umetnik u = new Umetnik(idUmetnik, ime, prezime, korisničkoIme, šifra);
            lista.add(u);
        }
        
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "ime, prezime, korisničkoIme, šifra";
    }

    @Override
    public String vratiVredostiZaUbacivanje() {
        return "'" + ime + "', '" + prezime + "', '" + korisničkoIme + "', '" + šifra + "'" ;
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "umetnik.idUmetnik = " + idUmetnik;
    }

    @Override
    public ApstraktniModelskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        Umetnik umetnik = null;
        while(rs.next()){
            int idUmetnik = rs.getInt("umetnik.idUmetnik");
            String ime = rs.getString("umetnik.ime");
            String prezime = rs.getString("umetnik.prezime");
            String korisničkoIme = rs.getString("umetnik.korisničkoIme");
            String šifra = rs.getString("umetnik.šifra");
            
           
            Umetnik u = new Umetnik(idUmetnik, ime, prezime, korisničkoIme, šifra);
            umetnik = u;
        }
        
        return umetnik;
    }

    @Override
    public String vratiVredostiZaIzmenu() {
        return "ime = '" + ime + "', prezime = '" + prezime + "', korisničkoIme = '" + korisničkoIme + "', šifra = '" + šifra + "'"  ; 
    }

    
 
    
    
}