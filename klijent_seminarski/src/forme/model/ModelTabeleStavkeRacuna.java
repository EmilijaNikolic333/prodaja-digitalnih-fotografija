/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.DigitalnaFotografija;
import model.Kupac;
import model.StavkaRacuna;

/**
 *
 * @author nikol
 */
public class ModelTabeleStavkeRacuna extends AbstractTableModel {

    List<StavkaRacuna> lista;
    String[] kolone = {"rb stavke", "iznos", "količina", "cena fotografije", "naziv fotografije"};

    public ModelTabeleStavkeRacuna(List<StavkaRacuna> lista) {
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
        StavkaRacuna sr = lista.get(rowIndex);
        switch (columnIndex) {
            
            case 0: return sr.getRb();
            case 1: return sr.getIznos();
            case 2: return sr.getKoličina();
            case 3: return sr.getCenaFotografije();
            case 4: return sr.getDigitalnaFotografija().getNazivFotografije();
               
            default: return "NA";
        }
    }

    public List<StavkaRacuna> getLista() {
        return lista;
    }
    
}
