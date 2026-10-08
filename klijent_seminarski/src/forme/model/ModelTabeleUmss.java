/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import javax.swing.table.AbstractTableModel;
import model.UmSS;
import model.Umetnik;

/**
 *
 * @author nikol
 */
public class ModelTabeleUmss extends AbstractTableModel{
    List<UmSS> lista;
    String[] kolone = {"naziv stručne spreme", "datum završetka", "ustanova"};

    public ModelTabeleUmss(List<UmSS> lista) {
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
        UmSS u = lista.get(rowIndex);
        switch (columnIndex) {
            case 0: return u.getStručnaSprema();
            case 1: return u.getDatumZavršetka() != null ? u.getDatumZavršetka().format(DateTimeFormatter.ofPattern("dd.MM.yyyy.", new Locale("sr", "RS"))) : "NA";
            case 2: return u.getUstanova();
            
            
               
            default: return "NA";
        }
    }

    public List<UmSS> getLista() {
        return lista;
    }

    public void setLista(List<UmSS> lista) {
        this.lista = lista;
    }
}
