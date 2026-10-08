/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;
import model.Kupac;
import model.Mesto;

/**
 *
 * @author nikol
 */
public class ModelTabeleKupci extends AbstractTableModel {

    List<Kupac> lista;
    String[] kolone = {"ime i Prezime", "email", "datum rođenja", "mesto"};

    public ModelTabeleKupci(List<Kupac> lista) {
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
        Kupac k = lista.get(rowIndex);
        switch (columnIndex) {
            
            case 0: return k.getImePrezime();
            case 1: return k.getEmail();
            case 2: return k.getDatum() != null ? k.getDatum().format(DateTimeFormatter.ofPattern("dd.MM.yyyy.", new Locale("sr", "RS"))) : "NA";
            case 3: return k.getMesto();
               
            default: return "NA";
        }
    }

    public List<Kupac> getLista() {
        return lista;
    }

    public boolean pretrazi(String imePrezime, String email, Mesto mesto) {
        List<Kupac> filteredList = lista.stream()
                .filter(k -> (imePrezime == null || imePrezime.isEmpty() || k.getImePrezime().toLowerCase().contains(imePrezime.toLowerCase())))
                .filter(k -> (email == null || email.isEmpty() || k.getEmail().toLowerCase().contains(email.toLowerCase())))
                .filter(k -> (mesto == null || k.getMesto().getIdMesto() == mesto.getIdMesto()))
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
