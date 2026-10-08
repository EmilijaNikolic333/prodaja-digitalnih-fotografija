/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;
import model.StrucnaSprema;
import model.Umetnik;

/**
 *
 * @author nikol
 */
public class ModelTabeleStrucnaSprema extends AbstractTableModel {

    List<StrucnaSprema> lista;
    String[] kolone = { "naziv stručne spreme"};

    public ModelTabeleStrucnaSprema(List<StrucnaSprema> lista) {
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

    public List<StrucnaSprema> getLista() {
        return lista;
    }

    
    
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StrucnaSprema ss = lista.get(rowIndex);
        switch (columnIndex) {
           
            case 0: return ss.getNaziv();
               
            default: return "NA";
        }
    }

    public boolean pretrazi(String naziv) {
            List<StrucnaSprema> filteredList = lista.stream()
                .filter(ss -> (naziv == null || naziv.isEmpty() || ss.getNaziv().toLowerCase().contains(naziv.toLowerCase())))
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
