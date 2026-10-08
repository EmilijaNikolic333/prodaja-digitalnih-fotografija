/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import komunikacija1.Odgovor;
import komunikacija1.Primalac;
import komunikacija1.Operacija;

import komunikacija1.Zahtev;
import komunikacija1.Posiljalac;
import java.io.IOException;
import java.net.Socket;
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


/**
 *
 * @author nikol
 */
public class Komunikacija {
    private Socket socket;
    private Posiljalac posiljalac;
    private Primalac primalac;
    private static Komunikacija instance;
    public static boolean uspeh = false;

    public static boolean isUspeh() {
        return uspeh;
    }

    public static void setUspeh(boolean uspeh) {
        Komunikacija.uspeh = uspeh;
    }
    
    
    
    private Komunikacija(){
        
    }
    
    public static Komunikacija getInstance(){
        if(instance == null){
            instance = new Komunikacija();
        }
        return instance;
    }
    
    public void konekcija() {
        try {
            socket = new Socket("localhost",  pronadjiPort());
            posiljalac = new Posiljalac(socket);
            primalac = new Primalac(socket);
            uspeh = true;
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);

            System.out.println("SERVER NIJE POVEZAN");
            uspeh = false;
            return;
        }
        
        
    }
    
    public int pronadjiPort(){
        int port = Integer.parseInt(konfig.Konfiguracija.getInstance().getProperty("port"));
        return port;
    }

    public Umetnik login(String ki, String sifra) throws IOException, ClassNotFoundException, Exception{
        Umetnik u = new Umetnik();
        u.setKorisničkoIme(ki);
        u.setŠifra(sifra);
        Zahtev zahtev = new Zahtev(Operacija.LOGIN, u);
        
        posiljalac.posalji(zahtev);
        
        Odgovor odg = (Odgovor) primalac.primi();
        if(odg == null){
            throw new Exception("Server nije u funkciji.");
            
        }
        
        Umetnik prijaviUm = (Umetnik) odg.getOdgovor();
        
        return prijaviUm;
    }

    public List<Kupac> ucitajKupce() throws IOException, ClassNotFoundException{
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_KUPCE, null);
        List<Kupac> kupci = new ArrayList<>();
        
        posiljalac.posalji(zahtev);
        
        Odgovor odg = (Odgovor) primalac.primi();
        
        if(odg == null){
            JOptionPane.showMessageDialog(null, "Server nije u funkciji", "Greška", JOptionPane.ERROR_MESSAGE);
            
            return null;
        }
        
        kupci = (List<Kupac>) odg.getOdgovor();
        return kupci;
    }
    
    public List<Mesto> ucitajMesta() throws IOException, ClassNotFoundException{
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_MESTA, null);
        List<Mesto> mesta = new ArrayList<>();
        
        posiljalac.posalji(zahtev);
        
        Odgovor odg = (Odgovor) primalac.primi();
        mesta = (List<Mesto>) odg.getOdgovor();
        return mesta;
    }
    
    

    public void obrisiKupca(Kupac k) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.OBRISI_KUPCA, k);
        
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if(odg.getOdgovor() != null){
            
            ((Exception)odg.getOdgovor()).printStackTrace();
            throw new Exception("GRESKA");
        }
    }

    public void dodajKupca(Kupac k) throws IOException, ClassNotFoundException{
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_KUPCA, k);
        
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        
        
        
    }

    public void azurirajKupca(Kupac k)  throws IOException, ClassNotFoundException{
        Zahtev zahtev = new Zahtev(Operacija.AZURIRAJ_KUPCA, k);

        posiljalac.posalji(zahtev);
        
        
        Odgovor odg = (Odgovor) primalac.primi();

        if(odg.getOdgovor() == null){
            
            cordinator.Cordinator.getInstance().osveziFormu();
        } 
    }

    public List<Racun> ucitajRacune() throws IOException, ClassNotFoundException{
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_RACUNE, null);
        List<Racun> racuni = new ArrayList<>();
        
        posiljalac.posalji(zahtev);
        
        Odgovor odg = (Odgovor) primalac.primi();
        racuni = (List<Racun>) odg.getOdgovor();
        return racuni;
    }

    public List<Umetnik> ucitajUmetnika() throws IOException, ClassNotFoundException{
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_UMETNIKE, null);
        List<Umetnik> umetnici = new ArrayList<>();
        
        posiljalac.posalji(zahtev);
        
        Odgovor odg = (Odgovor) primalac.primi();
        umetnici = (List<Umetnik>) odg.getOdgovor();
        return umetnici;
    }

    public List<DigitalnaFotografija> ucitajDigitalneFotografije() throws IOException, ClassNotFoundException{
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_DIGITALNE_FOTOGRAFIJE, null);
        List<DigitalnaFotografija> digitalneFotografije = new ArrayList<>();
        
        posiljalac.posalji(zahtev);
        
        Odgovor odg = (Odgovor) primalac.primi();
        digitalneFotografije = (List<DigitalnaFotografija>) odg.getOdgovor();
        return digitalneFotografije;
    }

    public void dodajRacun(Racun racun) throws IOException, ClassNotFoundException{
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_RACUN, racun);
        
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        
        
    }

    public void dodajStavke(StavkaRacuna stavkeRacuna) throws IOException, ClassNotFoundException{
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_STAVKU_RACUNA, stavkeRacuna);
        
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        
        if(odg.getOdgovor() == null){
            
        } 
    }

    

    public List<StavkaRacuna> ucitajStavkeRacuna() throws IOException, ClassNotFoundException{
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_STAVKE_RACUN, null);
        List<StavkaRacuna> stavke = new ArrayList<>();
        
        posiljalac.posalji(zahtev);
        
        Odgovor odg = (Odgovor) primalac.primi();
        stavke = (List<StavkaRacuna>) odg.getOdgovor();
        return stavke;
    }

    public void izmeniRacun(Racun racun) throws IOException, ClassNotFoundException{
        Zahtev zahtev = new Zahtev(Operacija.IZMENI_RACUN, racun);

        posiljalac.posalji(zahtev);
        
        
        Odgovor odg = (Odgovor) primalac.primi();

        if(odg.getOdgovor() == null){
            
            cordinator.Cordinator.getInstance().osveziRacunFormu();
        } else{
            
            
           

        }
    }

    public void izbrisiStavke(StavkaRacuna stavkaRacuna) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.OBRISI_STAVKU, stavkaRacuna);
        
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if(odg.getOdgovor() != null){
            
        
            
            ((Exception)odg.getOdgovor()).printStackTrace();
            throw new Exception("GRESKA");
        }
    }

    public Umetnik dodajUmetnika(Umetnik umetnik) throws IOException, ClassNotFoundException{
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_UMETNIKA, umetnik);
        
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        umetnik = (Umetnik) odg.getOdgovor();
        return umetnik;
    }

    public void obrisiUmetnika(Umetnik u) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.OBRISI_UMETNIKA, u);
        
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if(odg.getOdgovor() != null){
            
            ((Exception)odg.getOdgovor()).printStackTrace();
            throw new Exception("GRESKA");
        }
    }

    public void promeniUmetnika(Umetnik umetnik1) throws IOException, ClassNotFoundException{
        Zahtev zahtev = new Zahtev(Operacija.IZMENI_UMETNIKA, umetnik1);

        posiljalac.posalji(zahtev);
        
        
        Odgovor odg = (Odgovor) primalac.primi();

        if(odg.getOdgovor() == null){
            
            cordinator.Cordinator.getInstance().osveziUmetnikFormu();
        }
    }

    public void obrisiDigitalnuFotografiju(DigitalnaFotografija df) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.OBRISI_DIGITALNU_FOTOGRAFIJU, df);
        
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if(odg.getOdgovor() != null){
            
            ((Exception)odg.getOdgovor()).printStackTrace();
            throw new Exception("GRESKA");
        }
    }

    public void dodajDigitalnuFotografiju(DigitalnaFotografija df) throws IOException, ClassNotFoundException{
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_DIGITALNU_FOTOGRAFIJU, df);
        
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        
        
    }

    public void izmeniDigitalnuFotografiju(DigitalnaFotografija df) throws IOException, ClassNotFoundException{
        Zahtev zahtev = new Zahtev(Operacija.IZMENI_DIGITALNU_FOTOGRAFIJU, df);

        posiljalac.posalji(zahtev);
        
        
        Odgovor odg = (Odgovor) primalac.primi();

        if(odg.getOdgovor() == null){
            
            cordinator.Cordinator.getInstance().osveziDigitalnaFotografijaFormu();
        }
    }

    public void obrisiMesto(Mesto m) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.OBRISI_MESTO, m);
        
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if(odg.getOdgovor() != null){
            
            ((Exception)odg.getOdgovor()).printStackTrace();
            throw new Exception("GRESKA");
        }
    }

    public void dodajMesto(Mesto mesto) throws IOException, ClassNotFoundException{
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_MESTO, mesto);
        
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        
        
    }

    public void promeniMesto(Mesto mesto) throws IOException, ClassNotFoundException{
        Zahtev zahtev = new Zahtev(Operacija.IZMENI_MESTO, mesto);

        posiljalac.posalji(zahtev);
        
        
        Odgovor odg = (Odgovor) primalac.primi();

        if(odg.getOdgovor() == null){
            
            cordinator.Cordinator.getInstance().osveziMestoFormu();
        } 
    }

    public List<StrucnaSprema> ucitajStrucneSpreme() throws IOException, ClassNotFoundException{
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_STRUCNE_SPREME, null);
        List<StrucnaSprema> strucneSpreme = new ArrayList<>();
        
        posiljalac.posalji(zahtev);
        
        Odgovor odg = (Odgovor) primalac.primi();
        strucneSpreme = (List<StrucnaSprema>) odg.getOdgovor();
        return strucneSpreme;
    }

    public void obrisiStrucnuSpremu(StrucnaSprema ss) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.OBRISI_STRUCNU_SPREMU, ss);
        
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if(odg.getOdgovor() != null){
           
            ((Exception)odg.getOdgovor()).printStackTrace();
            throw new Exception("GRESKA");
        }
    }

    public void dodajStrucnuSpremu(StrucnaSprema ss) throws IOException, ClassNotFoundException{
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_STRUCNU_SPREMU, ss);
        
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        
        
    }

    public void promeniStrucnuSpremu(StrucnaSprema ss) throws IOException, ClassNotFoundException{
        Zahtev zahtev = new Zahtev(Operacija.IZMENI_STRUCNU_SPREMU, ss);

        posiljalac.posalji(zahtev);
        
        
        Odgovor odg = (Odgovor) primalac.primi();

        if(odg.getOdgovor() == null){
            
            cordinator.Cordinator.getInstance().osveziStrucnuSpremuFormu();
        } 
    }

    public void dodajUlogovanog(Umetnik ulogovani) throws IOException, ClassNotFoundException{
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_ULOGOVANO, ulogovani);
        
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        
        System.out.println("Prijavili ste se.");
        
      
     
    }

    public List<Umetnik> ucitajUlogovane() throws IOException, ClassNotFoundException {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_ULOGOVANE_UMETNIKE, null);
        List<Umetnik> ulogovani = new ArrayList<>();
        
        posiljalac.posalji(zahtev);
       
        Odgovor odg = (Odgovor) primalac.primi();
        ulogovani = (List<Umetnik>) odg.getOdgovor();
        return ulogovani;
        
    
    }

    public void odjavaUlogovanog(Umetnik ulogovani) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.ODJAVI_ULOGOVANOG, ulogovani);
        
        
        posiljalac.posalji(zahtev);
        //Odgovor odg = (Odgovor) primalac.primi();
        
        if(socket!=null && !socket.isClosed()){
        try {
             socket.close();
             instance = null;
             System.out.println("Odjavili ste se.");

        } catch (IOException ex) {
            System.err.println("Greška prilikom odjavljivanja: " + ex.getMessage());
        }
    
    }
        
        
    }

    public void dodajNeulogovanog(Umetnik umetnik) throws IOException, ClassNotFoundException {
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_NEULOGOVANO, umetnik);
        
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        
        
    }

    public List<UmSS> ucitajUmetnikStrucneSpreme() throws IOException, ClassNotFoundException {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_UMETNIK_STRUCNESPREME, null);
        List<UmSS> umetnikStrucneSpreme = new ArrayList<>();
        
        posiljalac.posalji(zahtev);
        
        Odgovor odg = (Odgovor) primalac.primi();
        umetnikStrucneSpreme = (List<UmSS>) odg.getOdgovor();
        return umetnikStrucneSpreme;
    }

    public Umetnik pretraziUmetnik(Umetnik u) throws IOException, ClassNotFoundException {
        Zahtev zahtev = new Zahtev(Operacija.PRETRAZI_UMETNIK, u);
        Umetnik umetnik = new Umetnik();
        
        posiljalac.posalji(zahtev);
        
        Odgovor odg = (Odgovor) primalac.primi();
        umetnik = (Umetnik) odg.getOdgovor();
        return umetnik;
    }

    public Racun pretraziRacun(Racun r) throws IOException, ClassNotFoundException {
        Zahtev zahtev = new Zahtev(Operacija.PRETRAZI_RACUN, r);
        Racun racun = new Racun();
        
        posiljalac.posalji(zahtev);
        
        Odgovor odg = (Odgovor) primalac.primi();
        racun = (Racun) odg.getOdgovor();
        return racun;
    }

    public Kupac pretraziKupca(Kupac k) throws IOException, ClassNotFoundException {
        Zahtev zahtev = new Zahtev(Operacija.PRETRAZI_KUPAC, k);
        Kupac kupac = new Kupac();
        
        posiljalac.posalji(zahtev);
        
        Odgovor odg = (Odgovor) primalac.primi();
        kupac = (Kupac) odg.getOdgovor();
        return kupac;
    }

    public Mesto pretraziMesto(Mesto m) throws IOException, ClassNotFoundException {
        Zahtev zahtev = new Zahtev(Operacija.PRETRAZI_MESTO, m);
        Mesto mesto = new Mesto();
        
        posiljalac.posalji(zahtev);
        
        Odgovor odg = (Odgovor) primalac.primi();
        mesto = (Mesto) odg.getOdgovor();
        return mesto;
    }

    public DigitalnaFotografija pretraziDigitalnuFotografiju(DigitalnaFotografija df) throws IOException, ClassNotFoundException {
        Zahtev zahtev = new Zahtev(Operacija.PRETRAZI_DIGITALNU_FOTOGRAFIJU, df);
        DigitalnaFotografija digitalnaFotografija = new DigitalnaFotografija();
        
        posiljalac.posalji(zahtev);
        
        Odgovor odg = (Odgovor) primalac.primi();
        digitalnaFotografija = (DigitalnaFotografija) odg.getOdgovor();
        return digitalnaFotografija;
    }

    public StrucnaSprema pretraziStrucnuSpremu(StrucnaSprema ss) throws IOException, ClassNotFoundException {
        Zahtev zahtev = new Zahtev(Operacija.PRETRAZI_STRUCNA_SPREMA, ss);
        StrucnaSprema strucnaSprema = new StrucnaSprema();
        
        posiljalac.posalji(zahtev);
        
        Odgovor odg = (Odgovor) primalac.primi();
        strucnaSprema = (StrucnaSprema) odg.getOdgovor();
        return strucnaSprema;
    }

    public List<Umetnik> vratiListuSviUmetnikUmetnik(String imeIPrezime) throws IOException, ClassNotFoundException {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_UMETNIKE_UMETNIK, imeIPrezime);
        List<Umetnik> umetnici = new ArrayList<>();
        
        posiljalac.posalji(zahtev);
        
        Odgovor odg = (Odgovor) primalac.primi();
        umetnici = (List<Umetnik>) odg.getOdgovor();
        return umetnici;
    }

    public List<Umetnik> vratiListuSviUmetnikStrSprema(String strucnaSprema) throws IOException, ClassNotFoundException {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_UMETNIKE_STRUCNA_SPREMA, strucnaSprema);
        List<Umetnik> umetnici = new ArrayList<>();
        
        posiljalac.posalji(zahtev);
        
        Odgovor odg = (Odgovor) primalac.primi();
        umetnici = (List<Umetnik>) odg.getOdgovor();
        return umetnici;
    }

    public List<StrucnaSprema> vratiListuSviStrSpremaStrSprema(String naziv) throws IOException, ClassNotFoundException {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_STRUCNE_SPREME_STRUCNA_SPREMA, naziv);
        List<StrucnaSprema> strucneSpreme = new ArrayList<>();
        
        posiljalac.posalji(zahtev);
        
        Odgovor odg = (Odgovor) primalac.primi();
        strucneSpreme = (List<StrucnaSprema>) odg.getOdgovor();
        return strucneSpreme;
    }

    public List<Racun> vratiListuSviRacunDigitalnaFotografija(String digitalnaFotografija) throws IOException, ClassNotFoundException {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_RACUNE_DIGITALNA_FOTOGRAFIJA, digitalnaFotografija);
        List<Racun> racuni = new ArrayList<>();
        
        posiljalac.posalji(zahtev);
        
        Odgovor odg = (Odgovor) primalac.primi();
        racuni = (List<Racun>) odg.getOdgovor();
        return racuni;
    }

    public List<Racun> vratiListuSviRacunKupac(String kupac) throws IOException, ClassNotFoundException {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_RACUNE_KUPAC, kupac);
        List<Racun> racuni = new ArrayList<>();
        
        posiljalac.posalji(zahtev);
        
        Odgovor odg = (Odgovor) primalac.primi();
        racuni = (List<Racun>) odg.getOdgovor();
        return racuni;
    }

    public List<Racun> vratiListuSviRacunUmetnik(String umetnik) throws IOException, ClassNotFoundException {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_RACUNE_UMETNIK, umetnik);
        List<Racun> racuni1 = new ArrayList<>();
        
        posiljalac.posalji(zahtev);
        
        Odgovor odg = (Odgovor) primalac.primi();
        racuni1 = (List<Racun>) odg.getOdgovor();
        return racuni1;
    }

    public List<Racun> vratiListuSviRacunRacun(String iznos) throws IOException, ClassNotFoundException {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_RACUNE_RACUN, iznos);
        List<Racun> racuni2 = new ArrayList<>();
        
        posiljalac.posalji(zahtev);
        
        Odgovor odg = (Odgovor) primalac.primi();
        racuni2 = (List<Racun>) odg.getOdgovor();
        return racuni2;
    }

    public List<DigitalnaFotografija> vratiListuSviDigitalnaFotografijaDigitalnaFotografija(DigitalnaFotografija trazenaFotografija) throws IOException, ClassNotFoundException {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_DIGITANE_FOTOGRAFIJE_DIGITALNA_FOTOGRAFIJA, trazenaFotografija);
        List<DigitalnaFotografija> digitalneFotografije = new ArrayList<>();
        
        posiljalac.posalji(zahtev);
        
        Odgovor odg = (Odgovor) primalac.primi();
        digitalneFotografije = (List<DigitalnaFotografija>) odg.getOdgovor();
        return digitalneFotografije;
    }

    public List<Mesto> vratiListuSviMestoMesto(Mesto trazenoMesto) throws IOException, ClassNotFoundException {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_MESTA_MESTO, trazenoMesto);
        List<Mesto> mesta1 = new ArrayList<>();
        
        posiljalac.posalji(zahtev);
        
        Odgovor odg = (Odgovor) primalac.primi();
        mesta1 = (List<Mesto>) odg.getOdgovor();
        return mesta1;
    }

    public List<Kupac> vratiListuSviKupacKupac(String imePrezime) throws IOException, ClassNotFoundException {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_KUPCE_KUPAC, imePrezime);
        List<Kupac> kupci1 = new ArrayList<>();
        
        posiljalac.posalji(zahtev);
        
        Odgovor odg = (Odgovor) primalac.primi();
        kupci1 = (List<Kupac>) odg.getOdgovor();
        return kupci1;
    }

    public List<Kupac> vratiListuSviKupacMesto(Mesto mesto) throws IOException, ClassNotFoundException {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_KUPCE_MESTO, mesto);
        List<Kupac> kupci2 = new ArrayList<>();
        
        posiljalac.posalji(zahtev);
        
        Odgovor odg = (Odgovor) primalac.primi();
        kupci2 = (List<Kupac>) odg.getOdgovor();
        return kupci2;
    }

    

    

    

    

   
    
    
    
}
