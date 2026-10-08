/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import model.DigitalnaFotografija;
import model.Kupac;
import model.Mesto;
import model.Racun;
import model.StavkaRacuna;

/**
 *
 * @author nikol
 */
public class ModelTabeleRacuni extends AbstractTableModel {
    
    List<StavkaRacuna> stavkeRacuna = new ArrayList<>();
    List<StavkaRacuna> stavke = new ArrayList<>();
    List<Racun> lista;
    String[] kolone = {"datum", "ukupan iznos", "umetnik", "kupac"};

    public ModelTabeleRacuni(List<Racun> lista) {
        this.lista = lista;
    }

    
    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Racun r = lista.get(rowIndex);
        switch (columnIndex) {
            
            case 0: return r.getDatum() != null ? r.getDatum().format(DateTimeFormatter.ofPattern("dd.MM.yyyy.", new Locale("sr", "RS"))) : "NA";
            case 1: return r.getUkupanIznos();
            case 2: return r.getUmetnik().getIme() + " " + r.getUmetnik().getPrezime();
            case 3: return r.getKupac().getImePrezime();
               
            default: return "NA";
        }
    }

    public List<Racun> getLista() {
        return lista;
    }

    public void setLista(List<Racun> lista) {
        this.lista = lista;
    }

    public boolean pretrazi(String iznos, String umetnik, String kupac, String digitalnaFotografija) {
        List<Racun> filteredList = lista.stream()
                .filter(r -> (iznos == null || iznos.isEmpty() || r.getUkupanIznos() == Double.parseDouble(iznos)))
                .filter(r -> (umetnik == null || umetnik.isEmpty() || r.getUmetnik().getIme().toLowerCase().contains(umetnik.toLowerCase())))
                .filter(r -> (kupac == null || kupac.isEmpty() || r.getKupac().getImePrezime().toLowerCase().contains(kupac.toLowerCase())))
                .filter(r -> (digitalnaFotografija == null || digitalnaFotografija.isEmpty() || sadrzi(r, digitalnaFotografija)))
                .collect(Collectors.toList());
        if(filteredList.isEmpty()){
            return false;
        }
        else{
           this.lista = filteredList;
           fireTableDataChanged(); 
           return true;
        }
        
        
    }
    
    
    
    public boolean sadrzi(Racun r, String digitalnaFotografij){
        //System.out.println(r);
        
        
        try {
            stavke = komunikacija.Komunikacija.getInstance().ucitajStavkeRacuna();
        } catch (IOException ex) {
            Logger.getLogger(ModelTabeleRacuni.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ModelTabeleRacuni.class.getName()).log(Level.SEVERE, null, ex);
        }
                
                
                for (StavkaRacuna stavkaRacuna : stavke) {
                    if(stavkaRacuna.getRačun().getIdRačun() == r.getIdRačun()){
                        stavkeRacuna.add(stavkaRacuna);
                        
                    }
                }
        for (StavkaRacuna stavkaRacuna1 : stavkeRacuna) {
            System.out.println(stavkaRacuna1);
            if(stavkaRacuna1.getDigitalnaFotografija().getNazivFotografije().toLowerCase().equals(digitalnaFotografij.toLowerCase())){
                
                System.out.println(digitalnaFotografij);
                return true;
            }
        }
        return false;
    }

    
}
