/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nit;

import controller.Controller;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import komunikacija1.Odgovor;
import komunikacija1.Operacija;
import komunikacija1.Operacija.*;
import static komunikacija1.Operacija.PRETRAZI_DIGITALNU_FOTOGRAFIJU;
import static komunikacija1.Operacija.PRETRAZI_KUPAC;
import static komunikacija1.Operacija.PRETRAZI_MESTO;
import static komunikacija1.Operacija.PRETRAZI_RACUN;
import static komunikacija1.Operacija.PRETRAZI_UMETNIK;
import static komunikacija1.Operacija.UCITAJ_DIGITANE_FOTOGRAFIJE_DIGITALNA_FOTOGRAFIJA;
import static komunikacija1.Operacija.UCITAJ_KUPCE_KUPAC;
import static komunikacija1.Operacija.UCITAJ_MESTA_MESTO;
import static komunikacija1.Operacija.UCITAJ_RACUNE_DIGITALNA_FOTOGRAFIJA;
import static komunikacija1.Operacija.UCITAJ_RACUNE_KUPAC;
import static komunikacija1.Operacija.UCITAJ_RACUNE_RACUN;
import static komunikacija1.Operacija.UCITAJ_STRUCNE_SPREME;
import static komunikacija1.Operacija.UCITAJ_UMETNIKE;
import static komunikacija1.Operacija.UCITAJ_UMETNIKE_UMETNIK;


//import static komunikacija1.Operacija.LOGIN;

import komunikacija1.Posiljalac;
import komunikacija1.Primalac;
import komunikacija1.Zahtev;
import model.DigitalnaFotografija;
import model.Kupac;
import model.Mesto;
import model.Racun;
import model.StavkaRacuna;
import model.StrucnaSprema;
import model.UmSS;
import model.Umetnik;
import server.Server;

/**
 *
 * @author nikol
 */
public class ObradaKlijentskihZahteva extends Thread {
    private Socket socket;
    private Primalac primalac;
    private Posiljalac posiljalac;
    private boolean kraj = false;
    private String ime;
    private String prezime;
    private String korisnickoIme;
    private String sifra;
    

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public ObradaKlijentskihZahteva(Socket socket, Primalac primalac, Posiljalac posiljalac, String ime, String prezime, String korisnickoIme, String sifra) {
        this.socket = socket;
        this.primalac = primalac;
        this.posiljalac = posiljalac;
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.sifra = sifra;
    }

    public Primalac getPrimalac() {
        return primalac;
    }

    public void setPrimalac(Primalac primalac) {
        this.primalac = primalac;
    }

    public Posiljalac getPosiljalac() {
        return posiljalac;
    }

    public void setPosiljalac(Posiljalac posiljalac) {
        this.posiljalac = posiljalac;
    }

    public boolean isKraj() {
        return kraj;
    }

    public void setKraj(boolean kraj) {
        this.kraj = kraj;
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

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }
    
    
    

    public ObradaKlijentskihZahteva(Socket socket) {
        this.socket = socket;
        this.posiljalac = new Posiljalac(socket);
        this.primalac = new Primalac(socket);
    }

    @Override
    
    public void run() {
        Odgovor odgovor = new Odgovor();

        try {
            while (socket != null && !socket.isClosed()) {
                try {
                    Zahtev zahtev = (Zahtev) primalac.primi();

                    if (zahtev == null) {
                        System.out.println("Klijent se diskonektovao.");
                        break;
                    }

                    // logika obrade zahteva
                    odgovor = obradiZahtev(zahtev);

                    // pokušaj slanja odgovora
                    posiljalac.posalji(odgovor);

                }  catch (IOException e) {
                    if (e instanceof EOFException) {
                        //System.out.println("Klijent zatvorio konekciju.");
                    } else if (e instanceof SocketException) {
                        //System.out.println("Greška u vezi sa socketom.");
                    } else {
                        e.printStackTrace();
                    }
                    break;
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } finally {
            try {
                if (socket != null && !socket.isClosed()) {
                    socket.close();
                    //System.out.println("Socket zatvoren.");
                }
            } catch (IOException ex) {
                Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }



    private Odgovor obradiZahtev(Zahtev zahtev) {
        Odgovor odgovor = new Odgovor();

        try {
            switch (zahtev.getOperacija()) {
                        case LOGIN:
                            
                            Umetnik umet = controller.Controller.getInstance().login((Umetnik) zahtev.getParametar());
                            odgovor.setOdgovor(umet);

                            break;

                        case UCITAJ_KUPCE:
                            List<Kupac> kupci = controller.Controller.getInstance().ucitajKupce();


                            odgovor.setOdgovor(kupci);

                            break;

                        case UCITAJ_MESTA:
                            List<Mesto> mesta = controller.Controller.getInstance().ucitajMesta();


                            odgovor.setOdgovor(mesta);

                            break;

                        case OBRISI_KUPCA:                        
                            try{
                                Kupac k = (Kupac) zahtev.getParametar();
                                Controller.getInstance().obrisiKupca(k);
                                odgovor.setOdgovor(null);

                            } catch(Exception e){
                                odgovor.setOdgovor(e);
                            }
                            break;

                        case DODAJ_KUPCA:
                            try{
                                Kupac k = (Kupac) zahtev.getParametar();
                                controller.Controller.getInstance().dodajKupca(k);

                                odgovor.setOdgovor(null);

                            } catch(Exception e){
                                odgovor.setOdgovor(e);
                            }
                            break;

                        case AZURIRAJ_KUPCA:
                            try{
                                Kupac k1 = (Kupac) zahtev.getParametar();
                                Controller.getInstance().azurirajKupca(k1);


                                odgovor.setOdgovor(null);

                            } catch(Exception e){
                                odgovor.setOdgovor(e);
                            }
                            break;

                        case UCITAJ_RACUNE:
                            List<Racun> racuni = controller.Controller.getInstance().ucitajRacune();
                            

                            odgovor.setOdgovor(racuni);

                            break;

                        case UCITAJ_UMETNIKE:
                            List<Umetnik> umetnici = controller.Controller.getInstance().ucitajUmetnike();
                            

                            odgovor.setOdgovor(umetnici);

                            break;

                        case UCITAJ_DIGITALNE_FOTOGRAFIJE:
                            List<DigitalnaFotografija> digitalneFotografije = controller.Controller.getInstance().ucitajDigitalneFotografije();
                            

                            odgovor.setOdgovor(digitalneFotografije);

                            break;

                        case DODAJ_RACUN:
                        try {
                            Racun r = (Racun) zahtev.getParametar();

                            controller.Controller.getInstance().dodajRacun(r);

                            

                            

                            odgovor.setOdgovor(null);
                        } catch (Exception e) {
                            e.printStackTrace();
                            odgovor.setOdgovor(e);
                        }
                        break;

                       


                        case UCITAJ_STAVKE_RACUN:
                            List<StavkaRacuna> stavke = controller.Controller.getInstance().ucitajStavke();


                            odgovor.setOdgovor(stavke);

                            break;

                        case IZMENI_RACUN:
                            try{
                                Racun r1 = (Racun) zahtev.getParametar();
                                controller.Controller.getInstance().izmeniRacun(r1);

                                

                                

                                odgovor.setOdgovor(null);

                            } catch(Exception e){
                                odgovor.setOdgovor(e);
                            }
                            break;
                        

                        case DODAJ_UMETNIKA:
                            try{
                                Umetnik u1 = (Umetnik) zahtev.getParametar();
                                controller.Controller.getInstance().dodajUmetnika(u1);

                                
                                odgovor.setOdgovor(u1);

                            } catch(Exception e){
                                odgovor.setOdgovor(e);
                            }
                            break;

                        case OBRISI_UMETNIKA:
                            try{
                                Umetnik u2 = (Umetnik) zahtev.getParametar();
                                Controller.getInstance().obrisiUmetnika(u2);
                                odgovor.setOdgovor(null);

                            } catch(Exception e){
                                odgovor.setOdgovor(e);
                            }
                            break;

                        case IZMENI_UMETNIKA:
                            try{
                                Umetnik u3 = (Umetnik) zahtev.getParametar();
                                controller.Controller.getInstance().izmeniUmetnika(u3);

                                
                                odgovor.setOdgovor(null);

                            } catch(Exception e){
                                odgovor.setOdgovor(e);
                            }
                            break;

                        case OBRISI_DIGITALNU_FOTOGRAFIJU:
                            try{
                                DigitalnaFotografija df = (DigitalnaFotografija) zahtev.getParametar();
                                Controller.getInstance().obrisiDigitalnuFotografiju(df);
                                odgovor.setOdgovor(null);

                            } catch(Exception e){
                                odgovor.setOdgovor(e);
                            }
                            break;

                        case DODAJ_DIGITALNU_FOTOGRAFIJU:
                            try{
                                DigitalnaFotografija df = (DigitalnaFotografija) zahtev.getParametar();
                                controller.Controller.getInstance().dodajDigitalnuFotografiju(df);


                                odgovor.setOdgovor(null);

                            } catch(Exception e){
                                odgovor.setOdgovor(e);
                            }
                            break;

                        case IZMENI_DIGITALNU_FOTOGRAFIJU:
                            try{
                                DigitalnaFotografija df = (DigitalnaFotografija) zahtev.getParametar();
                                controller.Controller.getInstance().izmeniDigitalnuFotografiju(df);


                                odgovor.setOdgovor(null);

                            } catch(Exception e){
                                odgovor.setOdgovor(e);
                            }
                            break;

                        case OBRISI_MESTO:                        
                            try{
                                Mesto m = (Mesto) zahtev.getParametar();
                                Controller.getInstance().obrisiMesto(m);
                                odgovor.setOdgovor(null);

                            } catch(Exception e){
                                odgovor.setOdgovor(e);
                            }
                            break;

                        case DODAJ_MESTO:
                            try{
                                Mesto m = (Mesto) zahtev.getParametar();
                                controller.Controller.getInstance().dodajMesto(m);


                                odgovor.setOdgovor(null);

                            } catch(Exception e){
                                odgovor.setOdgovor(e);
                            }
                            break;

                        case IZMENI_MESTO:
                            try{
                                Mesto m = (Mesto) zahtev.getParametar();
                                controller.Controller.getInstance().izmeniMesto(m);


                                odgovor.setOdgovor(null);

                            } catch(Exception e){
                                odgovor.setOdgovor(e);
                            }
                            break;

                        case UCITAJ_STRUCNE_SPREME:
                            List<StrucnaSprema> strucneSpreme = controller.Controller.getInstance().ucitajStrucneSpreme();
                            

                            odgovor.setOdgovor(strucneSpreme);

                            break;

                        case OBRISI_STRUCNU_SPREMU:                        
                            try{
                                StrucnaSprema ss = (StrucnaSprema) zahtev.getParametar();
                                Controller.getInstance().obrisiStrucnuSpremu(ss);
                                odgovor.setOdgovor(null);

                            } catch(Exception e){
                                odgovor.setOdgovor(e);
                            }
                            break;

                        case DODAJ_STRUCNU_SPREMU:
                            try{
                                StrucnaSprema ss = (StrucnaSprema) zahtev.getParametar();
                                controller.Controller.getInstance().dodajStrucnuSpremu(ss);


                                odgovor.setOdgovor(null);

                            } catch(Exception e){
                                odgovor.setOdgovor(e);
                            }
                            break;

                        case IZMENI_STRUCNU_SPREMU:
                            try{
                                StrucnaSprema ss = (StrucnaSprema) zahtev.getParametar();
                                controller.Controller.getInstance().izmeniStrucnuSpremu(ss);


                                odgovor.setOdgovor(null);

                            } catch(Exception e){
                                odgovor.setOdgovor(e);
                            }
                            break;

                        case DODAJ_ULOGOVANO:
                            try{
                                Umetnik ulogovani = (Umetnik) zahtev.getParametar();
                                controller.Controller.getInstance().dodajUlogovanog(ulogovani);


                                odgovor.setOdgovor(null);

                            } catch(Exception e){
                                odgovor.setOdgovor(e);
                            }


                            break;
                        
                        case ODJAVI_ULOGOVANOG:
                            try{
                                Umetnik ulogovani1 = (Umetnik) zahtev.getParametar();
                                controller.Controller.getInstance().odjaviUlogovanog(ulogovani1);
                                

                                odgovor.setOdgovor(null);
                                

                            } catch(Exception e){
                                odgovor.setOdgovor(e);
                            }


                            break;


                        case UCITAJ_ULOGOVANE_UMETNIKE:
                            List<Umetnik> ulogovani = controller.Controller.getInstance().getUlogovani();
                            

                            odgovor.setOdgovor(ulogovani);

                            break;
                        
                        case UCITAJ_UMETNIK_STRUCNESPREME:
                            List<UmSS> umetniciStrucneSpreme = controller.Controller.getInstance().ucitajUmetnikeStrucneSpreme();
                            

                            odgovor.setOdgovor(umetniciStrucneSpreme);
                            
                            break;
                            
                        case PRETRAZI_UMETNIK:
                            Umetnik trazeniUmetnik = (Umetnik) zahtev.getParametar();
                            Umetnik umetnik1 = controller.Controller.getInstance().pretraziUmetnik(trazeniUmetnik);
                                
                            List<UmSS> umss = controller.Controller.getInstance().ucitajUmetnikeStrucneSpreme();
                
                
                            List<UmSS> umetniciStrucneSpreme1 = new ArrayList<>();
                
                            for (UmSS us : umss) {
                                if(us.getUmetnik().getIdUmetnik() == umetnik1.getIdUmetnik()){

                                    umetniciStrucneSpreme1.add(us);

                                }
                            }
                            
                            umetnik1.setUmetnikStrucnaSprema(umetniciStrucneSpreme1);
                                odgovor.setOdgovor(umetnik1);
                            
                            break;
                        case PRETRAZI_RACUN:
                            Racun trazeniRacun = (Racun) zahtev.getParametar();
                            Racun racun1 = controller.Controller.getInstance().pretraziRacun(trazeniRacun);
                                
                            
                            List<StavkaRacuna> stavkeRacuna = controller.Controller.getInstance().ucitajStavke();
                
                
                            List<StavkaRacuna> stavkeRacuna1 = new ArrayList<>();
                
                            for (StavkaRacuna stavke1 : stavkeRacuna) {
                                if(stavke1.getRačun().getIdRačun() == racun1.getIdRačun()){

                                    stavkeRacuna1.add(stavke1);

                                }
                            }
                            
                            racun1.setStavke(stavkeRacuna1);
                           
                            odgovor.setOdgovor(racun1);
                            
                            break;
                            
                        case PRETRAZI_KUPAC:
                            Kupac trazeniKupac = (Kupac) zahtev.getParametar();
                            Kupac kupac1 = controller.Controller.getInstance().pretraziKupac(trazeniKupac);
                                
                            
                            
                           
                            odgovor.setOdgovor(kupac1);
                            
                            break;
                            
                        case PRETRAZI_MESTO:
                            Mesto trazenoMesto = (Mesto) zahtev.getParametar();
                            Mesto mesto1 = controller.Controller.getInstance().pretraziMesto(trazenoMesto);
                                
                            
                            
                           
                            odgovor.setOdgovor(mesto1);
                            
                            break;
                            
                        case PRETRAZI_DIGITALNU_FOTOGRAFIJU:
                            DigitalnaFotografija trazenaDigitalnaFotografija = (DigitalnaFotografija) zahtev.getParametar();
                            DigitalnaFotografija digitalnaFotografija1 = controller.Controller.getInstance().pretraziDigitalnuFotografiju(trazenaDigitalnaFotografija);
                                
                            
                            
                           
                            odgovor.setOdgovor(digitalnaFotografija1);
                            
                            break;
                            
                        case PRETRAZI_STRUCNA_SPREMA:
                            StrucnaSprema trazenaStrucnaSprema = (StrucnaSprema) zahtev.getParametar();
                            StrucnaSprema strucnaSprema1 = controller.Controller.getInstance().pretraziStrucnuSpremu(trazenaStrucnaSprema);
                                
                            
                            
                           
                            odgovor.setOdgovor(strucnaSprema1);
                            
                            break;
                            
                        case UCITAJ_UMETNIKE_UMETNIK:
                            String imeIPrezime = (String) zahtev.getParametar();
                            List<Umetnik> umetnici1 = controller.Controller.getInstance().vratiListuUmetnikUmetnik(imeIPrezime);
                            

                            odgovor.setOdgovor(umetnici1);

                            break;
                        case UCITAJ_UMETNIKE_STRUCNA_SPREMA:
                            String strucnaSpremaKriterijum = (String) zahtev.getParametar();
                            List<Umetnik> umetnici2 = controller.Controller.getInstance().vratiListuUmetnikStrucnaSprema(strucnaSpremaKriterijum);
                            

                            odgovor.setOdgovor(umetnici2);

                            break;
                        case UCITAJ_STRUCNE_SPREME_STRUCNA_SPREMA:
                            
                            List<StrucnaSprema> strucneSpreme1 = controller.Controller.getInstance().vratiListuSviStrSpremaStrSprema((String)zahtev.getParametar());
                            

                            odgovor.setOdgovor(strucneSpreme1);

                            break;
                            
                        case UCITAJ_RACUNE_DIGITALNA_FOTOGRAFIJA:
                            List<Racun> racuniDigitalneFotografije = controller.Controller.getInstance().vratiListuSviRacunDigitalnaFotografija((String)zahtev.getParametar());
                            

                            odgovor.setOdgovor(racuniDigitalneFotografije);
                            
                            
                            
                            break;
                            
                        case UCITAJ_RACUNE_UMETNIK:
                            List<Racun> racuniUmetnik = controller.Controller.getInstance().vratiListuSviRacunUmetnik((String)zahtev.getParametar());
                            

                            odgovor.setOdgovor(racuniUmetnik);
                            
                            
                            
                            break;
                            
                        case UCITAJ_RACUNE_KUPAC:
                            List<Racun> racuniKupac = controller.Controller.getInstance().vratiListuSviRacunKupac((String)zahtev.getParametar());
                            

                            odgovor.setOdgovor(racuniKupac);
                            
                            
                            
                            break;
                            
                        case UCITAJ_RACUNE_RACUN:
                            List<Racun> racuniRacun = controller.Controller.getInstance().vratiListuSviRacunRacun((String)zahtev.getParametar());
                            

                            odgovor.setOdgovor(racuniRacun);
                            
                            
                            
                            break;
                            
                        case UCITAJ_DIGITANE_FOTOGRAFIJE_DIGITALNA_FOTOGRAFIJA:
                            List<DigitalnaFotografija> digitalneFotografijeDF = controller.Controller.getInstance().vratiListuSviDigitalnaFotografijaDigitalnaFotografija((DigitalnaFotografija)zahtev.getParametar());
                            

                            odgovor.setOdgovor(digitalneFotografijeDF);
                            
                            
                            
                            break;
                            
                        case UCITAJ_MESTA_MESTO:
                            List<Mesto> mestaMesto = controller.Controller.getInstance().vratiListuSviMestoMesto((Mesto)zahtev.getParametar());
                            

                            odgovor.setOdgovor(mestaMesto);
                            
                            
                            
                            break;
                            
                        case UCITAJ_KUPCE_KUPAC:
                            List<Kupac> kupacKupac = controller.Controller.getInstance().vratiListuSviKupacKupac((String)zahtev.getParametar());
                            

                            odgovor.setOdgovor(kupacKupac);
                            
                            
                            
                            break;
                            
                        case UCITAJ_KUPCE_MESTO:
                            List<Kupac> kupacMesto = controller.Controller.getInstance().vratiListuSviKupacMesto((Mesto)zahtev.getParametar());
                            

                            odgovor.setOdgovor(kupacMesto);
                            
                            
                            
                            break;
                            
                        default:
                    System.out.println("Nepoznata operacija: " + zahtev.getOperacija());
                    odgovor.setOdgovor(new Exception("Nepoznata operacija."));
            }

        } catch (Exception e) {
            e.printStackTrace();
            odgovor.setOdgovor(e);
        }

        return odgovor;
    }

    public void prekini() {
        kraj = true;
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        interrupt();
    }
}
