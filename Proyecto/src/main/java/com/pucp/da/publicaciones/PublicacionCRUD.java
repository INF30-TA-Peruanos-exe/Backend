/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pucp.da.publicaciones;

import com.pucp.config.DBManager;
import com.pucp.interfacesDAO.PublicacionDAO;
import com.pucp.modelo.publicaciones.Publicacion;
import com.pucp.modelo.publicaciones.EstadoPublicacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Axel
 */
public class PublicacionCRUD implements PublicacionDAO{

    @Override
    public void insertar(Publicacion publicacion) {
        String query = "INSERT INTO Publicacion(titulo,descripcion,estado,fechaPublicacion,url_imagen,activo)"
                + "values(?,?,?,?,?,?)";
        try(Connection con = DBManager.getConnection();
            PreparedStatement ps = con.prepareStatement(query);) {        
            setParametrosPublicacion(ps, publicacion);
            ps.executeUpdate(); 
            //Traer el ultimo ID autogenerado
            try(Statement st = con.createStatement();
                ResultSet rskeys = st.executeQuery("select @@last_insert_id");){            
                if(rskeys.next()){
                    publicacion.setIdPublicacion(rskeys.getInt(1));
                }
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public ArrayList<Publicacion> listarTodos() {
        
        ArrayList<Publicacion> publicaciones = new ArrayList<>();
        String query = "SELECT idPublicacion,titulo,descripcion,estado,fechaPublicacion,url_imagen,activo FROM Publicacion WHERE activo = 1";
        try(Connection con = DBManager.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);){
            while(rs.next()){
                Publicacion publicacion = mapaPublicacion(rs);
                publicaciones.add(publicacion);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return publicaciones;
        
    }

    @Override
    public Publicacion obtenerPorId(int id) {
        String query = "SELECT idPublicacion,titulo,descripcion,estado,fechaPublicacion,url_imagen,activo FROM Publicacion WHERE idPublicacion = ?";
        try (Connection conn = DBManager.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapaPublicacion(rs);
                }
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return null;  
    }

    @Override
    public void actualizar(Publicacion publicacion) {
        String query = "UPDATE Publicacion SET titulo = ?, descripcion = ?, estado = ?, fechaPublicacion = ?, url_imagen = ?, activo = ? WHERE idPublicacion = ?";
        try(Connection con = DBManager.getConnection();
            PreparedStatement ps = con.prepareStatement(query);){
            setParametrosPublicacion(ps,publicacion);
            ps.setInt(7,publicacion.getIdPublicacion());
            ps.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        //Eliminar lógico
        String query = "UPDATE Publicacion SET activo = 0 WHERE idPublicacion = ?";
        try (Connection conn = DBManager.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(query)) {            
             ps.setInt(1, id);
             ps.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
    }
    
    private void setParametrosPublicacion(PreparedStatement ps, Publicacion publi) throws SQLException{
        ps.setString(1, publi.getTitulo());
        ps.setString(2, publi.getDescripcion());
        ps.setString(3, publi.getEstado().name());
        ps.setDate(4, publi.getFechaPublicacion());
        ps.setString(5, publi.getRutaImagen());
        ps.setBoolean(6, publi.isActivo());
    }
    
    private Publicacion mapaPublicacion(ResultSet rs) throws SQLException{
        Publicacion publi = new Publicacion();
        publi.setIdPublicacion(rs.getInt("idPublicacion"));
        publi.setTitulo(rs.getString("titulo"));
        publi.setDescripcion(rs.getString("descripcion"));
        publi.setEstado(EstadoPublicacion.valueOf(rs.getString("estado")));
        publi.setFechaPublicacion(rs.getDate("fechaPublicacion"));
        publi.setRutaImagen(rs.getString("url_imagen"));
        publi.setActivo(rs.getBoolean("activo"));
        return publi;
    }
    
}
