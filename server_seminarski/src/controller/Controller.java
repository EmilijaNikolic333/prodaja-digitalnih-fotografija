/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import forme.ServerskaForma;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.DigitalnaFotografija;
import model.Kupac;
import model.Mesto;
import model.Racun;
import model.StavkaRacuna;
import model.StrucnaSprema;
import model.UmSS;
import model.Umetnik;
import nit.ObradaKlijentskihZahteva;
import operacija.fotografije.PromeniDigitalnaFotografijaSO;
import operacija.fotografije.ObrisiDigitalnaFotografijaSO;
import operacija.fotografije.KreirajDigitalnaFotografijaSO;
import operacija.fotografije.PretraziDigitalnaFotografijaSO;
import operacija.fotografije.VratiListuDigitalnaFotografijaDigitalnaFotografijaSO;
import operacija.fotografije.VratiListuSviDigitalnaFotografijaSO;
import operacija.kupci.PromeniKupacSO;
import operacija.kupci.KreirajKupacSO;
import operacija.kupci.ObrisiKupacSO;
import operacija.kupci.PretraziKupacSO;
import operacija.kupci.VratiListuKupacKupacSO;
import operacija.kupci.VratiListuKupacMestoSO;
import operacija.kupci.VratiListuSviKupacSO;
import operacija.strucneSpreme.VratiListuSviStrSpremaSO;
import operacija.umetnici.PrijaviUmetnikSO;
import operacija.mesto.KreirajMestoSO;
import operacija.mesto.PromeniMestoSO;
import operacija.mesto.ObrisiMestoSO;
import operacija.mesto.PretraziMestoSO;
import operacija.mesto.VratiListuMestoMestoSO;
import operacija.mesto.VratiListuSviMestoSO;
import operacija.racuni.PromeniRacunSO;
import operacija.racuni.KreirajRacunSO;
import operacija.racuni.PretraziRacunSO;
import operacija.racuni.VratiListuRacunDigitalnaFotografijaSO;
import operacija.racuni.VratiListuRacunKupacSO;
import operacija.racuni.VratiListuRacunRacunSO;
import operacija.racuni.VratiListuSviRacunSO;
import operacija.racuni.VratiListuRacunUmetnikSO;


import operacija.stavke.VratiListuSviStavkaRacunaSO;
import operacija.strucneSpreme.PromeniStrSpremaSO;
import operacija.strucneSpreme.UbaciStrSpremaSO;
import operacija.strucneSpreme.ObrisiStrSpremaSO;
import operacija.strucneSpreme.PretraziStrSpremaSO;
import operacija.strucneSpreme.VratiListuStrSpremaStrSpremaSO;
import operacija.umetnici.PromeniUmetnikSO;
import operacija.umetnici.KreirajUmetnikSO;
import operacija.umetnici.ObrisiUmetnikSO;
import operacija.umetnici.PretraziUmetnikSO;
import operacija.umetnici.VratiListuSviUmetnikSO;
import operacija.umetnici.VratiListuUmetnikStrSpremaSO;
import operacija.umetnici.VratiListuUmetnikUmetnikSO;
import operacija.umetnikStrucneSpreme.KreirajUmSSSO;
import operacija.umetnikStrucneSpreme.ObrisiUmSSSO;
import operacija.umetnikStrucneSpreme.VratiListuSviUmSSSO;
import server.Server;

/**
 *
 * @author nikol
 */
public class Controller {
    private static Controller instance;

    

    
    
    ServerskaForma sf = new ServerskaForma();
    
    

    private List<Umetnik> ulogovaniUmetnici = new ArrayList<>();
    private List<Umetnik> neulogovaniUmetnici = new ArrayList<>();
    
    public void setUlogovaniUmetnici(List<Umetnik> ulogovaniUmetnici) {
        this.ulogovaniUmetnici = ulogovaniUmetnici;
    }
    
    public List<Umetnik> getUlogovaniUmetnici() {
        return ulogovaniUmetnici;
    }
    
    public void setNeulogovaniUmetnici(List<Umetnik> neulogovaniUmetnici) {
        this.neulogovaniUmetnici = neulogovaniUmetnici;
    }

    public ServerskaForma getSf() {
        return sf;
    }

    public void setSf(ServerskaForma sf) {
        this.sf = sf;
    }
    
    
    
    private Controller() {
        
        
    }
    
    public static Controller getInstance(){
        if(instance == null){
            instance = new Controller();
        }
        return instance;
    }

    public Umetnik login(Umetnik u) throws Exception {
        PrijaviUmetnikSO operacija = new PrijaviUmetnikSO();
        operacija.izvrsi(u, null);
        
        
        return operacija.getUmetnik();
    }

    public List<Kupac> ucitajKupce() throws Exception {
        VratiListuSviKupacSO operacija = new VratiListuSviKupacSO();
        operacija.izvrsi(null, null);
        
        return operacija.getKupci();
    }
    
    public List<Mesto> ucitajMesta() throws Exception {
        VratiListuSviMestoSO operacija = new VratiListuSviMestoSO();
        operacija.izvrsi(null, null);
        
        return operacija.getMesta();
    }

    public void obrisiKupca(Kupac k) throws Exception {
        ObrisiKupacSO operacija = new ObrisiKupacSO();
        operacija.izvrsi(k, null);
    }

    public void dodajKupca(Kupac k) throws Exception {
        KreirajKupacSO operacija = new KreirajKupacSO();
        operacija.izvrsi(k, null);
    }

    public void azurirajKupca(Kupac k) throws Exception {
        PromeniKupacSO operacija = new PromeniKupacSO();
        operacija.izvrsi(k, null);
    }

    public List<Racun> ucitajRacune() throws Exception {
        VratiListuSviRacunSO operacija = new VratiListuSviRacunSO();       
        operacija.izvrsi(null, null);
        
        return operacija.getRacuni();
    }

    public List<Umetnik> ucitajUmetnike() throws Exception {
        VratiListuSviUmetnikSO operacija = new VratiListuSviUmetnikSO();      
        operacija.izvrsi(null, null);
        
        return operacija.getUmetnici();
    }

    public List<DigitalnaFotografija> ucitajDigitalneFotografije() throws Exception {
        VratiListuSviDigitalnaFotografijaSO operacija = new VratiListuSviDigitalnaFotografijaSO();
        operacija.izvrsi(null, null);
        
        return operacija.getDigitalneFotografije();
    }

    public void dodajRacun(Racun r) throws Exception {
        KreirajRacunSO operacija = new KreirajRacunSO();
        operacija.izvrsi(r, null);
    }

  

    

    public List<StavkaRacuna> ucitajStavke() throws Exception {
        VratiListuSviStavkaRacunaSO operacija = new VratiListuSviStavkaRacunaSO();
        operacija.izvrsi(null, null);
        
        return operacija.getStavke();
    }

    public void izmeniRacun(Racun r1) throws Exception {
        PromeniRacunSO operacija = new PromeniRacunSO();
        operacija.izvrsi(r1, null);
    }

   

    public void dodajUmetnika(Umetnik u1) throws Exception {
        KreirajUmetnikSO operacija = new KreirajUmetnikSO();
        
        operacija.izvrsi(u1, null);
        sf.osveziTabele();
    }

    public void obrisiUmetnika(Umetnik u2) throws Exception {
        ObrisiUmetnikSO operacija = new ObrisiUmetnikSO();
        operacija.izvrsi(u2, null);
        if(!ulogovaniUmetnici.contains(u2)){
            
        
            sf.osveziTabele();
        }else{
            Server.odjaviKlijenta(u2);
            sf.osveziTabele();
        }
        
       
        
        
    }

    public void izmeniUmetnika(Umetnik u3) throws Exception {
        PromeniUmetnikSO operacija = new PromeniUmetnikSO();
        operacija.izvrsi(u3, null);
        sf.osveziTabele();
    }

    public void obrisiDigitalnuFotografiju(DigitalnaFotografija df) throws Exception {
        ObrisiDigitalnaFotografijaSO operacija = new ObrisiDigitalnaFotografijaSO();
        operacija.izvrsi(df, null);
    }

    public void dodajDigitalnuFotografiju(DigitalnaFotografija df) throws Exception {
        KreirajDigitalnaFotografijaSO operacija = new KreirajDigitalnaFotografijaSO();
        operacija.izvrsi(df, null);
    }

    

    public void izmeniDigitalnuFotografiju(DigitalnaFotografija df) throws Exception {
        PromeniDigitalnaFotografijaSO operacija = new PromeniDigitalnaFotografijaSO();
        operacija.izvrsi(df, null);
    }

    public void obrisiMesto(Mesto m) throws Exception {
        ObrisiMestoSO operacija = new ObrisiMestoSO();
        operacija.izvrsi(m, null);
    }

    public void dodajMesto(Mesto m) throws Exception {
        KreirajMestoSO operacija = new KreirajMestoSO();
        operacija.izvrsi(m, null);
    }

    public void izmeniMesto(Mesto m) throws Exception {
        PromeniMestoSO operacija = new PromeniMestoSO();
        operacija.izvrsi(m, null);
    }

    public List<StrucnaSprema> ucitajStrucneSpreme() throws Exception {
        VratiListuSviStrSpremaSO operacija = new VratiListuSviStrSpremaSO();
        operacija.izvrsi(null, null);
        
        return operacija.getStrucneSpreme();
    }

    public void obrisiStrucnuSpremu(StrucnaSprema ss) throws Exception {
        ObrisiStrSpremaSO operacija = new ObrisiStrSpremaSO();
        operacija.izvrsi(ss, null);
    }

    public void dodajStrucnuSpremu(StrucnaSprema ss) throws Exception {
        UbaciStrSpremaSO operacija = new UbaciStrSpremaSO();
        operacija.izvrsi(ss, null);
    }

    public void izmeniStrucnuSpremu(StrucnaSprema ss) throws Exception {
        PromeniStrSpremaSO operacija = new PromeniStrSpremaSO();
        operacija.izvrsi(ss, null);
    }

    public void dodajUlogovanog(Umetnik ulogovani) throws Exception {
        for (ObradaKlijentskihZahteva korisnik : Server.getKlijenti()) {

                if(korisnik.getIme() == null 
                    && korisnik.getPrezime() == null
                    && korisnik.getKorisnickoIme() == null
                    && korisnik.getSifra() == null){
                        korisnik.setIme(ulogovani.getIme());
                        korisnik.setPrezime(ulogovani.getPrezime());
                        korisnik.setKorisnickoIme(ulogovani.getKorisničkoIme());
                        korisnik.setSifra(ulogovani.getŠifra());
                        
                        break;             
                        
                }
            }
        ulogovaniUmetnici.add(ulogovani);
        System.out.println("Klijent " + ulogovani.getIme() + " " + ulogovani.getPrezime() + " se prijavio" + ".");
        sf.osveziTabele();
    }

    public List<Umetnik> getUlogovani() {
        return ulogovaniUmetnici;
    }

    

    public List<Umetnik> neulogovaniUmetnici() throws Exception {
        neulogovaniUmetnici = ucitajUmetnike();
        return neulogovaniUmetnici;
    }

    public List<Umetnik> getNeulogovaniUmetnici() throws Exception {
        neulogovaniUmetnici = ucitajUmetnike();
        return neulogovaniUmetnici;
    }

    public void odjaviUlogovanog(Umetnik ulogovani1) throws Exception {
        ulogovaniUmetnici.remove(ulogovani1);
        neulogovaniUmetnici.add(ulogovani1);
        
        Server.odjaviKlijenta(ulogovani1);
        
        sf.osveziTabele();
    }

    public void dodajNeulogovanog(Umetnik neulogovani) throws Exception {
        neulogovaniUmetnici.add(neulogovani);
        Server.odjaviKlijenta(neulogovani);
        sf.osveziTabele();
    }

    public void osveziTabele() {
        sf.osveziTabele();
    }

    public List<UmSS> ucitajUmetnikeStrucneSpreme() throws Exception {
        VratiListuSviUmSSSO operacija = new VratiListuSviUmSSSO();       
        operacija.izvrsi(null, null);
        
        return operacija.getRacuni();
    }

    public void dodajUmSS(UmSS umss) throws Exception {
        KreirajUmSSSO operacija = new KreirajUmSSSO();
        operacija.izvrsi(umss, null);
    }

    public void obrisiUmetnikaStrucnuSpremu(UmSS umetnikss) throws Exception {
        ObrisiUmSSSO operacija = new ObrisiUmSSSO();
        operacija.izvrsi(umetnikss, null);
    }

    public Umetnik pretraziUmetnik(Umetnik trazeniUmetnik) throws Exception {
        PretraziUmetnikSO operacija = new PretraziUmetnikSO();
        operacija.izvrsi(trazeniUmetnik, null);
        return operacija.getUmetnik();
    }

    public Racun pretraziRacun(Racun trazeniRacun) throws Exception {
        PretraziRacunSO operacija = new PretraziRacunSO();
        operacija.izvrsi(trazeniRacun, null);
        return operacija.getRacun();
    }

    public Kupac pretraziKupac(Kupac trazeniKupac) throws Exception {
        PretraziKupacSO operacija = new PretraziKupacSO();
        operacija.izvrsi(trazeniKupac, null);
        return operacija.getKupac();
    }

    public Mesto pretraziMesto(Mesto trazenoMesto) throws Exception {
        PretraziMestoSO operacija = new PretraziMestoSO();
        operacija.izvrsi(trazenoMesto, null);
        return operacija.getMesto();
    }

    public DigitalnaFotografija pretraziDigitalnuFotografiju(DigitalnaFotografija trazenaDigitalnaFotografija) throws Exception {
        PretraziDigitalnaFotografijaSO operacija = new PretraziDigitalnaFotografijaSO();
        operacija.izvrsi(trazenaDigitalnaFotografija, null);
        return operacija.getDigitalnaFotografija();
    }

    public StrucnaSprema pretraziStrucnuSpremu(StrucnaSprema trazenaStrucnaSprema) throws Exception {
        PretraziStrSpremaSO operacija = new PretraziStrSpremaSO();
        operacija.izvrsi(trazenaStrucnaSprema, null);
        return operacija.getStrucnaSprema();
    }

    public List<Umetnik> vratiListuUmetnikUmetnik(String umetnikKriterijum) throws Exception {
        VratiListuUmetnikUmetnikSO operacija = new VratiListuUmetnikUmetnikSO();
        operacija.izvrsi(umetnikKriterijum, null);
        return operacija.getUmetnici();
    }

    public List<Umetnik> vratiListuUmetnikStrucnaSprema(String strucnaSpremaKriterijum) throws Exception {
        VratiListuUmetnikStrSpremaSO operacija = new VratiListuUmetnikStrSpremaSO();
        operacija.izvrsi(strucnaSpremaKriterijum, null);
        return operacija.getUmetnici();
    }

    public List<StrucnaSprema> vratiListuSviStrSpremaStrSprema(String par) throws Exception {
        VratiListuStrSpremaStrSpremaSO operacija = new VratiListuStrSpremaStrSpremaSO();
        operacija.izvrsi(par, null);
        return operacija.getStrucneSpreme();
    }

    public List<Racun> vratiListuSviRacunDigitalnaFotografija(String string) throws Exception {
        VratiListuRacunDigitalnaFotografijaSO operacija = new VratiListuRacunDigitalnaFotografijaSO();
        operacija.izvrsi(string, null);
        return operacija.getRacuni();
    }

    public List<Racun> vratiListuSviRacunKupac(String string) throws Exception {
        VratiListuRacunKupacSO operacija = new VratiListuRacunKupacSO();
        operacija.izvrsi(string, null);
        return operacija.getRacuni();
    }

    public List<Racun> vratiListuSviRacunUmetnik(String string1) throws Exception {
        VratiListuRacunUmetnikSO operacija = new VratiListuRacunUmetnikSO();
        operacija.izvrsi(string1, null);
        return operacija.getRacuni();
    }

    public List<Racun> vratiListuSviRacunRacun(String string3) throws Exception {
        VratiListuRacunRacunSO operacija = new VratiListuRacunRacunSO();
        operacija.izvrsi(string3, null);
        return operacija.getRacuni();
    }

    public List<DigitalnaFotografija> vratiListuSviDigitalnaFotografijaDigitalnaFotografija(DigitalnaFotografija digitalnaFotografija) throws Exception {
        VratiListuDigitalnaFotografijaDigitalnaFotografijaSO operacija = new VratiListuDigitalnaFotografijaDigitalnaFotografijaSO();
        operacija.izvrsi(digitalnaFotografija, null);
        return operacija.getDigitalneFotografije();
    }

    public List<Mesto> vratiListuSviMestoMesto(Mesto mesto) throws Exception {
        VratiListuMestoMestoSO operacija = new VratiListuMestoMestoSO();
        operacija.izvrsi(mesto, null);
        return operacija.getMesta();
    }

    public List<Kupac> vratiListuSviKupacKupac(String kupac) throws Exception {
        VratiListuKupacKupacSO operacija = new VratiListuKupacKupacSO();
        operacija.izvrsi(kupac, null);
        return operacija.getKupci();
    }

    public List<Kupac> vratiListuSviKupacMesto(Mesto mesto) throws Exception {
        VratiListuKupacMestoSO operacija = new VratiListuKupacMestoSO();
        operacija.izvrsi(mesto, null);
        return operacija.getKupci();
    }


    
    
     
    
    
}
