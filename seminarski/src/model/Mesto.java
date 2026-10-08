/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Asus
 */
public class Mesto implements ApstraktniModelskiObjekat{
    private int idMesto;
    private String država;
    private String grad;
    private String poštanskiBroj;

    public Mesto() {
    }

    public Mesto(String država, String grad, String poštanskiBroj) {
        
        this.država = država;
        this.grad = grad;
        this.poštanskiBroj = poštanskiBroj;
    }
    
    public Mesto(int idMesto, String država, String grad, String poštanskiBroj) {
        this.idMesto = idMesto;
        this.država = država;
        this.grad = grad;
        this.poštanskiBroj = poštanskiBroj;
    }

    public int getIdMesto() {
        return idMesto;
    }

    public void setIdMesto(int idMesto) {
        this.idMesto = idMesto;
    }

    public String getDržava() {
        return država;
    }

    public void setDržava(String država) {
        this.država = država;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public String getPoštanskiBroj() {
        return poštanskiBroj;
    }

    public void setPoštanskiBroj(String poštanskiBroj) {
        this.poštanskiBroj = poštanskiBroj;
    }

    @Override
    public String toString() {
        return grad;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Mesto other = (Mesto) obj;
        if (this.idMesto != other.idMesto) {
            return false;
        }
        if (!Objects.equals(this.država, other.država)) {
            return false;
        }
        if (!Objects.equals(this.grad, other.grad)) {
            return false;
        }
        return Objects.equals(this.poštanskiBroj, other.poštanskiBroj);
    }

    @Override
    public String vratiNazivTabelle() {
        return "mesto";
    }

    @Override
    public List<ApstraktniModelskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniModelskiObjekat> lista = new ArrayList<>();
        while(rs.next()){
            int idMesto = rs.getInt("mesto.idMesto");
            String država = rs.getString("mesto.država");
            String grad = rs.getString("mesto.grad");
            String poštanskiBroj = rs.getString("mesto.poštanskiBroj");

            Mesto m = new Mesto(idMesto, država, grad, poštanskiBroj);
            lista.add(m);
        }
        
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "država, grad, poštanskiBroj";
    }

    @Override
    public String vratiVredostiZaUbacivanje() {
        return "'"+ država + "', '" + grad + "', '" + poštanskiBroj + "'"; 
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "mesto.idMesto = " + idMesto;
    }

    @Override
    public ApstraktniModelskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        Mesto mesto = null;
        while(rs.next()){
            int idMesto = rs.getInt("mesto.idMesto");
            String država = rs.getString("mesto.država");
            String grad = rs.getString("mesto.grad");
            String poštanskiBroj = rs.getString("mesto.poštanskiBroj");

            Mesto m = new Mesto(idMesto, država, grad, poštanskiBroj);
            mesto = m;
        }
        
        return mesto;
    }

    @Override
    public String vratiVredostiZaIzmenu() {
        return "država = '" + država + "', grad = '" + grad + "', poštanskiBroj = '" + poštanskiBroj + "'";
    }
    
    
}
