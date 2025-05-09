/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pucp.da.categorias;

import com.pucp.capadominio.categorias.Facultad;
import com.pucp.config.DBManager;
import com.pucp.interfacesDAO.FacultadDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
/**
 *
 * @author SEBASTIAN
 */
public class FacultadCRUD implements FacultadDAO{

    @Override
    public void insertar(Facultad facultad) {
        String query = "INSERT INTO Facultad(nombre,activo)"
                + "values(?,?)";
        try(Connection con = DBManager.getConnection();
            PreparedStatement ps = con.prepareStatement(query);) {        
            setParametrosFacultad(ps, facultad);
            ps.executeUpdate(); 
            //Traer el ultimo ID autogenerado
            try(Statement st = con.createStatement();
                ResultSet rskeys = st.executeQuery("select @@last_insert_id");){            
                if(rskeys.next()){
                    facultad.setIdFacultad(rskeys.getInt(1));
                }
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
    }

    @Override
    public ArrayList<Facultad> listarTodos() {
        
        ArrayList<Facultad> facultades = new ArrayList<>();
        String query = "SELECT id_facultad,nombre,activo FROM Facultad WHERE activo = 1";
        try(Connection con  =DBManager.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);){
            while(rs.next()){
                Facultad facultad= mapaFacultad(rs);
                facultades.add(facultad);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return facultades;
    }

    @Override
    public Facultad obtenerPorId(int id) {
        String query = "SELECT id_facultad,nombre,activo FROM Facultad WHERE id_facultad = ?";
        try (Connection conn = DBManager.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapaFacultad(rs);
                }
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return null;
        
    }

    @Override
    public void actualizar(Facultad facultad) {
        String query = "UPDATE Facultad SET nombre = ?, activo = ? WHERE id_facultad = ?";
        try(Connection con = DBManager.getConnection();
            PreparedStatement ps = con.prepareStatement(query);){
            setParametrosFacultad(ps,facultad);
            ps.setInt(3,facultad.getIdFacultad());
            ps.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
    }

    @Override
    public void eliminar(int id) {
        //Eliminar lógico
        String query = "UPDATE Facultad SET activo = 0 WHERE id_facultad = ?";
        try (Connection conn = DBManager.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(query)) {            
             ps.setInt(1, id);
             ps.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
    }
    
    private void setParametrosFacultad(PreparedStatement ps, Facultad facu) throws SQLException{
        ps.setString(1, facu.getNombre());
        ps.setBoolean(2, facu.isActivo());
    }
    
    private Facultad mapaFacultad(ResultSet rs) throws SQLException{
        Facultad facu = new Facultad();
        facu.setIdFacultad(rs.getInt("id_facultad"));
        facu.setNombre(rs.getString("nombre"));
        facu.setActivo(rs.getBoolean("activo"));
        return facu;
    }
    
}
