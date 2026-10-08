 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cordinator;

import forme.AutorForma;
import forme.DodajDigitalnuFotografijuForma;
import forme.DodajKupcaForma;
import forme.DodajMestoForma;
import forme.DodajRacunForma;
import forme.DodajStrucnuSpremuForma;
import forme.DodajUmetnikaForma;
import forme.FormaMod;
import forme.GlavnaForma;
import forme.KonfiguracijaForma;
import forme.LoginForma;
import forme.PrikazDigitalneFotografijeForma;
import forme.PrikazKupacaForma;
import forme.PrikazMestaForma;
import forme.PrikazRacunaForma;
import forme.PrikazUmetnikaForma;
import forme.PrikaziStrucneSpremeForma;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import kontroleri.AutorController;
import kontroleri.DodajDigitalnuFotografijuController;
import kontroleri.DodajKupcaController;
import kontroleri.DodajMestoController;
import kontroleri.DodajRacunController;
import kontroleri.DodajStrucnuSpremuController;
import kontroleri.DodajUmetnikaController;
import kontroleri.GlavnaFormaController;
import kontroleri.KonfiguracijaController;
import kontroleri.LoginController;
import kontroleri.PrikazDigtalnihFotografijaController;
import kontroleri.PrikazKupcaController;
import kontroleri.PrikazMestaController;
import kontroleri.PrikazRacunaController;
import kontroleri.PrikazStrucnihSpremaController;
import kontroleri.PrikazUmetnikaController;
import model.DigitalnaFotografija;
import model.Kupac;
import model.Mesto;
import model.Racun;
import model.StrucnaSprema;
import model.Umetnik;

/**
 *
 * @author nikol
 */
public class Cordinator {
    private static Cordinator instance;
    private Umetnik ulogovani;
    private LoginController loginController;
    private GlavnaFormaController glavnaFormaController;
    private PrikazKupcaController pkController;
    private DodajKupcaController dkController;
    private Map<String, Object> parametri;
    private PrikazRacunaController prController;
    private DodajRacunController drController;
    private PrikazUmetnikaController puController;
    private DodajUmetnikaController duController;
    private PrikazDigtalnihFotografijaController pdfController;
    private DodajDigitalnuFotografijuController ddfController;
    private PrikazMestaController pmController;
    private DodajMestoController dmController;
    private PrikazStrucnihSpremaController pssController;
    private DodajStrucnuSpremuController dssController;
    private KonfiguracijaController kController;
    private AutorController aController;
    
    private Cordinator(){
        parametri = new HashMap<>();
    }
    
    public static Cordinator getInstance(){
        if(instance == null){
            instance = new Cordinator();
        }
        return instance;
    }

    public void otvoriLoginFormu() {
        loginController = new LoginController(new LoginForma());
        loginController.otvoriFormu();
    }

    public void otvoriGlavnuFormu() {
        glavnaFormaController = new GlavnaFormaController(new GlavnaForma());
        glavnaFormaController.otvoriFormu();
    }
    
    public void otvoriPrikazKupacaForma() throws IOException, ClassNotFoundException {
        pkController = new PrikazKupcaController(new PrikazKupacaForma());
        pkController.otvoriFormu();
    }
    
    

    public Umetnik getUlogovani() {
        return ulogovani;
    }

    public void setUlogovani(Umetnik ulogovani) {
        this.ulogovani = ulogovani;
    }

    public void otvoriDodajKupcaFormu() throws IOException, ClassNotFoundException {
        
        dkController = new DodajKupcaController(new DodajKupcaForma());
        dkController.otvoriFormu(FormaMod.DODAJ, null);
        JOptionPane.showMessageDialog(null, "Sistem je kreirao kupca.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
    }

    public void osveziTabeluKupaca() throws IOException, ClassNotFoundException {
        pkController.pripremiFormu();
    }

    
    public void dodajParam(String s, Object o){
        parametri.put(s, o);
    }
    
    public Object vratiParam(String s){
        return parametri.get(s);
    }

    public void otvoriIzmeniKupcaFormu(Kupac kupac) throws IOException, ClassNotFoundException {
        dkController = new DodajKupcaController(new DodajKupcaForma());
        dkController.otvoriFormu(FormaMod.IZMENI, kupac);
    }

    public void osveziFormu() throws IOException, ClassNotFoundException {
        pkController.osveziFormu();
    }
    
    public void otvoriPrikazRacunaForma() throws IOException, ClassNotFoundException {
        prController = new PrikazRacunaController(new PrikazRacunaForma());
        prController.otvoriFormu();
    }

    public void otvoriIzmeniRacunFormu(Racun racun) throws IOException, ClassNotFoundException {
    
        drController = new DodajRacunController(new DodajRacunForma());
        drController.otvoriFormu(FormaMod.IZMENI, racun);
        
    }

    public void otvoriDodajRacunForma() throws IOException, ClassNotFoundException {
        
        drController = new DodajRacunController(new DodajRacunForma());
        drController.otvoriFormu(FormaMod.DODAJ, null);
        JOptionPane.showMessageDialog(null, "Sistem je kreirao račun.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
    }

    public void osveziTabeluStavkiRacuna() throws IOException, ClassNotFoundException {
        drController.pripremiStavku();
    }

    public void osveziRacunFormu() throws IOException, ClassNotFoundException {
        prController.osveziFormu();
    }

    public void otvoriPrikaziDetaljeRacunFormu(Racun racun) throws IOException, ClassNotFoundException {
        
        drController = new DodajRacunController(new DodajRacunForma());
        drController.otvoriFormu(FormaMod.DETALJI, racun);
    }

    public void otvoriDetaljiKupcaFormu(Kupac kupac) throws IOException, ClassNotFoundException {
        dkController = new DodajKupcaController(new DodajKupcaForma());
        dkController.otvoriFormu(FormaMod.DETALJI, kupac);
    }

    public void otvoriPrikazUmetnikaForma() throws IOException, ClassNotFoundException {
        puController = new PrikazUmetnikaController(new PrikazUmetnikaForma());
        puController.otvoriFormu();
    }

    public void otvoriDodajUmetnikaForma() throws IOException, ClassNotFoundException{
        
        duController = new DodajUmetnikaController(new DodajUmetnikaForma());
        duController.otvoriFormu(FormaMod.DODAJ, null);
        JOptionPane.showMessageDialog(null, "Sistem je kreirao umetnika.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
    }

    public void otvoriIzmeniUmetnikaFormu(Umetnik umetnik) throws IOException, ClassNotFoundException {
        duController = new DodajUmetnikaController(new DodajUmetnikaForma());
        duController.otvoriFormu(FormaMod.IZMENI, umetnik);
    }

    public void osveziUmetnikFormu() throws IOException, ClassNotFoundException {
        puController.osveziFormu();
    }

    public void otvoriDetaljiUmetnikaFormu(Umetnik umetnik) throws IOException, ClassNotFoundException {
        duController = new DodajUmetnikaController(new DodajUmetnikaForma());
        duController.otvoriFormu(FormaMod.DETALJI, umetnik);
    }

    public void otvoriPrikazDigitalnihFotografijaForma() throws IOException, ClassNotFoundException {
        pdfController = new PrikazDigtalnihFotografijaController(new PrikazDigitalneFotografijeForma());
        pdfController.otvoriFormu();
    }

    public void otvoriDodajDigitalnuFotografijuForma() throws IOException, ClassNotFoundException{
        
        ddfController = new DodajDigitalnuFotografijuController(new DodajDigitalnuFotografijuForma());
        ddfController.otvoriFormu(FormaMod.DODAJ, null);
        JOptionPane.showMessageDialog(null, "Sistem je kreirao digitalnu fotografiju.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
    }

    public void otvoriIzmeniDigitalnuFotografijuFormu(DigitalnaFotografija digitalnaFotografija) {
        ddfController = new DodajDigitalnuFotografijuController(new DodajDigitalnuFotografijuForma());
        ddfController.otvoriFormu(FormaMod.IZMENI, digitalnaFotografija);
    }

    public void otvoriDetaljiDigitalneFotografijeFormu(DigitalnaFotografija digitalnaFotografija) {
        ddfController = new DodajDigitalnuFotografijuController(new DodajDigitalnuFotografijuForma());
        ddfController.otvoriFormu(FormaMod.DETALJI, digitalnaFotografija);
    }

    public void osveziDigitalnaFotografijaFormu() throws IOException, ClassNotFoundException {
        pdfController.osveziFormu();
    }

    public void otvoriPrikazMestaForma() throws IOException, ClassNotFoundException {
        pmController = new PrikazMestaController(new PrikazMestaForma());
        pmController.otvoriFormu();
    }

    public void otvoriIzmeniMestoFormu(Mesto mesto) {
        dmController = new DodajMestoController(new DodajMestoForma());
        dmController.otvoriFormu(FormaMod.IZMENI, mesto);
    }

    public void otvoriDetaljiMestaFormu(Mesto mesto) {
        dmController = new DodajMestoController(new DodajMestoForma());
        dmController.otvoriFormu(FormaMod.DETALJI, mesto);
    }

    public void osveziMestoFormu() throws IOException, ClassNotFoundException {
        pmController.osveziFormu();
    }

    public void otvoriDodajMestoForma() throws IOException, ClassNotFoundException{
        
        dmController = new DodajMestoController(new DodajMestoForma());
        dmController.otvoriFormu(FormaMod.DODAJ, null);
        JOptionPane.showMessageDialog(null, "Sistem je kreirao mesto.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
    }

    public void otvoriPrikazStrucnihSpremaForma() throws IOException, ClassNotFoundException {
        pssController = new PrikazStrucnihSpremaController(new PrikaziStrucneSpremeForma());
        pssController.otvoriFormu();
    }

    public void otvoriIzmeniStrucnuSpremuFormu(StrucnaSprema strucnaSprema) {
        dssController = new DodajStrucnuSpremuController(new DodajStrucnuSpremuForma());
        dssController.otvoriFormu(FormaMod.IZMENI, strucnaSprema);
    }

    public void otvoriDetaljiStrucneSpremeFormu(StrucnaSprema strucnaSprema) {
        dssController = new DodajStrucnuSpremuController(new DodajStrucnuSpremuForma());
        dssController.otvoriFormu(FormaMod.DETALJI, strucnaSprema);
    }

    public void osveziStrucnuSpremuFormu() throws IOException, ClassNotFoundException {
        pssController.osveziFormu();
    }

    public void otvoriDodajStrucnuSpremuForma() throws IOException, ClassNotFoundException{
        JOptionPane.showMessageDialog(null, "Sistem je kreirao stručnu spremu.", "USPEH", JOptionPane.INFORMATION_MESSAGE);

        dssController = new DodajStrucnuSpremuController(new DodajStrucnuSpremuForma());
        dssController.otvoriFormu(FormaMod.DODAJ, null);
    }

    public void otvoriKonfigForma() throws IOException, ClassNotFoundException{
        kController = new KonfiguracijaController(new KonfiguracijaForma());
        kController.otvoriFormu();
    }

    public void otvoriAutorForma() throws IOException, ClassNotFoundException{
        aController = new AutorController(new AutorForma());
        aController.otvoriFormu();
    }
}
