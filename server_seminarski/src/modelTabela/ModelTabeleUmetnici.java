/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelTabela;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;
import model.Racun;
import model.Umetnik;

/**
 *
 * @author nikol
 */
public class ModelTabeleUmetnici extends AbstractTableModel {

    
    private List<Umetnik> lista = new ArrayList<>();
    private String[] kolone = {"ime i prezime", "korisničko ime", "šifra"};

    public ModelTabeleUmetnici(List<Umetnik> lista) {
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
        Umetnik u = lista.get(rowIndex);
        switch (columnIndex) {
            //case 0: return u.getIdUmetnik();
            case 0: return u.getIme() + " " + u.getPrezime();
            case 1: return u.getKorisničkoIme();
            case 2: return u.getŠifra();
               
            default: return "NA";
        }
    }

    public List<Umetnik> getLista() {
        return lista;
    }

    public void setLista(List<Umetnik> lista) {
        this.lista = lista;
    }

    public boolean pretrazi(String ime, String prezime, String korisnickoIme) {
        List<Umetnik> filteredList = lista.stream()
                .filter(u -> (ime == null || ime.isEmpty() || u.getIme().toLowerCase().contains(ime.toLowerCase())))
                .filter(u -> (prezime == null || prezime.isEmpty() || u.getPrezime().toLowerCase().contains(prezime.toLowerCase())))
                .filter(u -> (korisnickoIme == null || korisnickoIme.isEmpty() || u.getKorisničkoIme().contains(korisnickoIme)))
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

    public void refresuj() {
        fireTableDataChanged();
    }
    
    
    
    
}
