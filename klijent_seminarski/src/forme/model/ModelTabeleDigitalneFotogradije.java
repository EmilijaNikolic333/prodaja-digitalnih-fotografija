/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;
import model.DigitalnaFotografija;
import model.Kupac;
import model.Umetnik;

/**
 *
 * @author nikol
 */
public class ModelTabeleDigitalneFotogradije extends AbstractTableModel {

    List<DigitalnaFotografija> lista;
    String[] kolone = {"naziv fotografije", "cena fotografije"};

    public ModelTabeleDigitalneFotogradije(List<DigitalnaFotografija> lista) {
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
        DigitalnaFotografija df = lista.get(rowIndex);
        switch (columnIndex) {
            
            case 0: return df.getNazivFotografije();
            case 1: return df.getCena();
               
            default: return "NA";
        }
    }

    public List<DigitalnaFotografija> getLista() {
        return lista;
    }

    public boolean pretrazi(String nazivFotografije, String cena) {
        List<DigitalnaFotografija> filteredList = lista.stream()
                .filter(df -> (nazivFotografije == null || nazivFotografije.isEmpty() || df.getNazivFotografije().toLowerCase().contains(nazivFotografije.toLowerCase())))
                .filter(df -> (cena == null || cena.isEmpty() || Double.parseDouble(cena) == df.getCena()))
                
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
    
    
    
}
