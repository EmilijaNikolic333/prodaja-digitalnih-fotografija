/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.db.impl;

import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.ApstraktniModelskiObjekat;
import repository.db.DbConnectionFactory;
import repository.db.DbRepository;

/**
 *
 * @author nikol
 */
public class DbRepositoryGeneric implements DbRepository<ApstraktniModelskiObjekat> {

    @Override
    public List<ApstraktniModelskiObjekat> getAll(ApstraktniModelskiObjekat parm, String uslov) throws Exception {
        List<ApstraktniModelskiObjekat> lista = new ArrayList<>();
        
        String upit = "SELECT * FROM " + parm.vratiNazivTabelle();
        if(uslov != null){ //todo
            upit += uslov;
        }
        
        
        Statement st = DbConnectionFactory.getInstance().getConnection().createStatement();
        ResultSet rs = st.executeQuery(upit);
        
        lista = parm.vratiListu(rs);
        
        rs.close();
        st.close();
        
        
        
        return lista;
    }

    @Override
    public void add(ApstraktniModelskiObjekat parm) throws Exception {
        String upit = "INSERT INTO " + parm.vratiNazivTabelle() + " (" + parm.vratiKoloneZaUbacivanje() + ")  VALUES (" + parm.vratiVredostiZaUbacivanje() + ")";
        
        Statement st = DbConnectionFactory.getInstance().getConnection().createStatement();
        st.executeUpdate(upit);
        st.close();


    }

    @Override
    public void edit(ApstraktniModelskiObjekat parm) throws Exception {
        String upit = "UPDATE " + parm.vratiNazivTabelle()+ " SET " + parm.vratiVredostiZaIzmenu() + " WHERE " + parm.vratiPrimarniKljuc();
        
        
        Statement st = DbConnectionFactory.getInstance().getConnection().createStatement(); 
        st.executeUpdate(upit);
        st.close();
        
        
        
    }

    @Override
    public void delete(ApstraktniModelskiObjekat parm) throws Exception {
        String upit = "DELETE FROM " + parm.vratiNazivTabelle() + " WHERE " + parm.vratiPrimarniKljuc();
        
        Statement st = DbConnectionFactory.getInstance().getConnection().createStatement();
        st.executeUpdate(upit);
        st.close();
    }

    @Override
    public List<ApstraktniModelskiObjekat> getAll() { //todo
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

    }

    @Override
    public ApstraktniModelskiObjekat getObject(ApstraktniModelskiObjekat parm, String uslov) throws Exception {
        
        ApstraktniModelskiObjekat objekat = null;
        
        String upit = "SELECT * FROM " + parm.vratiNazivTabelle();
        if(uslov != null){ //todo
            upit += uslov;
        }
        
        
        Statement st = DbConnectionFactory.getInstance().getConnection().createStatement();
        ResultSet rs = st.executeQuery(upit);
        
        
        objekat = parm.vratiObjekatIzRS(rs);
        
        rs.close();
        st.close();
        
        
        
        return objekat;
    }

    
    
}
