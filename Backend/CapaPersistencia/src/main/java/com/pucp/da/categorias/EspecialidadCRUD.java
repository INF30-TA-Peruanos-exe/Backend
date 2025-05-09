/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pucp.da.categorias;

import com.pucp.capadominio.categorias.Especialidad;
import com.pucp.config.DBManager;
import com.pucp.interfacesDAO.EspecialidadDAO;

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
public class EspecialidadCRUD implements EspecialidadDAO{

    @Override
    public void insertar(Especialidad especialidad) {
        String query = "INSERT INTO Especialidad(nombre,activo)"
                + "values(?,?)";
        try(Connection con = DBManager.getConnection();
            PreparedStatement ps = con.prepareStatement(query);) {        
            setParametrosEspecialidad(ps, especialidad);
            ps.executeUpdate(); 
            //Traer el ultimo ID autogenerado
            try(Statement st = con.createStatement();
                ResultSet rskeys = st.executeQuery("select @@last_insert_id");){            
                if(rskeys.next()){
                    especialidad.setIdEspecialidad(rskeys.getInt(1));
                }
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public ArrayList<Especialidad> listarTodos() {
        
        ArrayList<Especialidad> especialidades = new ArrayList<>();
        String query = "SELECT id_especialidad,nombre,activo FROM Especialidad WHERE activo = 1";
        try(Connection con  =DBManager.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);){
            while(rs.next()){
                Especialidad especialidad= mapaEspecialidad(rs);
                especialidades.add(especialidad);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return especialidades;
    }

    @Override
    public Especialidad obtenerPorId(int id) {
        String query = "SELECT id_especialidad,nombre,activo FROM Especialidad WHERE id_especialidad = ?";
        try (Connection conn = DBManager.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapaEspecialidad(rs);
                }
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return null;
        
    }

    @Override
    public void actualizar(Especialidad especialidad) {
        String query = "UPDATE Especialidad SET nombre = ?, activo = ? WHERE id_especialidad = ?";
        try(Connection con = DBManager.getConnection();
            PreparedStatement ps = con.prepareStatement(query);){
            setParametrosEspecialidad(ps,especialidad);
            ps.setInt(3,especialidad.getIdEspecialidad());
            ps.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
    }

    @Override
    public void eliminar(int id) {
        //Eliminar lógico
        String query = "UPDATE Especialidad SET activo = 0 WHERE id_especialidad = ?";
        try (Connection conn = DBManager.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(query)) {            
             ps.setInt(1, id);
             ps.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
    }
    
    private void setParametrosEspecialidad(PreparedStatement ps, Especialidad espe) throws SQLException{
        ps.setString(1, espe.getNombre());
        ps.setBoolean(2, espe.isActivo());
    }
    
    private Especialidad mapaEspecialidad(ResultSet rs) throws SQLException{
        Especialidad espe = new Especialidad();
        espe.setIdEspecialidad(rs.getInt("id_especialidad"));
        espe.setNombre(rs.getString("nombre"));
        espe.setActivo(rs.getBoolean("activo"));
        return espe;
    }
    
}
