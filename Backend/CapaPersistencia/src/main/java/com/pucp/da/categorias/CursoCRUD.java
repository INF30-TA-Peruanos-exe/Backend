/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pucp.da.categorias;

import com.pucp.capadominio.categorias.Curso;
import com.pucp.config.DBManager;
import com.pucp.interfacesDAO.CursoDAO;


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
public class CursoCRUD implements CursoDAO{

    @Override
    public void insertar(Curso curso) {
        String query = "INSERT INTO Curso(nombre,activo)"
                + "values(?,?)";
        try(Connection con = DBManager.getConnection();
            PreparedStatement ps = con.prepareStatement(query);) {        
            setParametrosCurso(ps, curso);
            ps.executeUpdate(); 
            //Traer el ultimo ID autogenerado
            try(Statement st = con.createStatement();
                ResultSet rskeys = st.executeQuery("select @@last_insert_id");){            
                if(rskeys.next()){
                    curso.setIdCurso(rskeys.getInt(1));
                }
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
    }

    @Override
    public ArrayList<Curso> listarTodos() {
        
        ArrayList<Curso> cursos = new ArrayList<>();
        String query = "SELECT id_curso,nombre,activo FROM Curso WHERE activo = 1";
        try(Connection con  =DBManager.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);){
            while(rs.next()){
                Curso curso = mapaCurso(rs);
                cursos.add(curso);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return cursos;
        
    }

    @Override
    public Curso obtenerPorId(int id) {
        String query = "SELECT id_curso,nombre,activo FROM Curso WHERE id_curso = ?";
        try (Connection conn = DBManager.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapaCurso(rs);
                }
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return null;
        
    }

    @Override
    public void actualizar(Curso curso) {
        String query = "UPDATE Curso SET nombre = ?, activo = ? WHERE id_curso = ?";
        try(Connection con = DBManager.getConnection();
            PreparedStatement ps = con.prepareStatement(query);){
            setParametrosCurso(ps,curso);
            ps.setInt(3,curso.getIdCurso());
            ps.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        //Eliminar lógico
        String query = "UPDATE Curso SET activo = 0 WHERE id_curso = ?";
        try (Connection conn = DBManager.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(query)) {            
             ps.setInt(1, id);
             ps.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
    }
    
    private void setParametrosCurso(PreparedStatement ps, Curso cur) throws SQLException{
        ps.setString(1, cur.getNombre());
        ps.setBoolean(2, cur.isActivo());
    }
    
    private Curso mapaCurso(ResultSet rs) throws SQLException{
        Curso cur = new Curso();
        cur.setIdCurso(rs.getInt("id_curso"));
        cur.setNombre(rs.getString("nombre"));
        cur.setActivo(rs.getBoolean("activo"));
        return cur;
    }    
    
    
    
}
