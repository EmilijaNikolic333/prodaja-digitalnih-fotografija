/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;
import model.Mesto;
import model.Umetnik;

/**
 *
 * @author nikol
 */
public class ModelTabeleMesta extends AbstractTableModel {

    List<Mesto> lista;
    String[] kolone = {"država", "grad", "poštanski broj"};

    public ModelTabeleMesta(List<Mesto> lista) {
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
        Mesto m = lista.get(rowIndex);
        switch (columnIndex) {
            
            case 0: return m.getDržava();
            case 1: return m.getGrad();
            case 2: return m.getPoštanskiBroj();
               
            default: return "NA";
        }
    }

    public List<Mesto> getLista() {
        return lista;
    }

    public boolean pretrazi(String država, String grad, String poštanski) {
        List<Mesto> filteredList = lista.stream()
                .filter(m -> (država == null || država.isEmpty() || m.getDržava().toLowerCase().contains(država.toLowerCase())))
                .filter(m -> (grad == null || grad.isEmpty() || m.getGrad().toLowerCase().contains(grad.toLowerCase())))
                .filter(m -> (poštanski == null || poštanski.isEmpty() || m.getPoštanskiBroj().contains(poštanski)))
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
    
   

    
    

