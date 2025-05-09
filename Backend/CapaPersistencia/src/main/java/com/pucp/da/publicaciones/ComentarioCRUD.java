/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pucp.da.publicaciones;

import com.pucp.capadominio.publicacion.Comentario;
import com.pucp.capadominio.publicacion.Publicacion;
import com.pucp.capadominio.usuarios.Usuario;
import com.pucp.config.DBManager;
import com.pucp.interfacesDAO.ComentarioDAO;

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
public class ComentarioCRUD implements ComentarioDAO{
    
    @Override
    public void insertar(Comentario comentario){
        String query = "INSERT INTO Comentario(contenido,valoracion,fecha,id_publicacion,id_usuario,activo)"
                + "values(?,?,?,?,?,?)";
        try(Connection con = DBManager.getConnection();
            PreparedStatement ps = con.prepareStatement(query);) {        
            setParametrosComentario(ps, comentario);
            ps.executeUpdate(); 
            //Traer el ultimo ID autogenerado
            try(Statement st = con.createStatement();
                ResultSet rskeys = st.executeQuery("select @@last_insert_id");){            
                if(rskeys.next()){
                    comentario.setIdComentario(rskeys.getInt(1));
                }
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    @Override
    public ArrayList<Comentario> listarTodos(){
        ArrayList<Comentario> comentarios = new ArrayList<>();
        String query = "SELECT id_comentario,contenido,valoracion,fecha,id_publicacion,id_usuario,activo FROM Comentario WHERE activo = 1";
        try(Connection con  =DBManager.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);){
            while(rs.next()){
                Comentario comentario = mapaComentario(rs);
                comentarios.add(comentario);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return comentarios;
    } 
    
    @Override
    public Comentario obtenerPorId(int id){
        String query = "SELECT id_comentario,contenido,valoracion,fecha,id_publicacion,id_usuario,activo FROM Comentario WHERE id_comentario = ?";
        try (Connection conn = DBManager.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapaComentario(rs);
                }
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return null;  
    }
    
    @Override
    public void actualizar(Comentario notificacion){
        String query = "UPDATE Comentario SET contenido = ?, valoracion = ?, fecha = ?, id_publicacion = ?, id_usuario = ?, activo = ? WHERE id_comentario = ?";
        try(Connection con = DBManager.getConnection();
            PreparedStatement ps = con.prepareStatement(query);){
            setParametrosComentario(ps,notificacion);
            ps.setInt(7,notificacion.getIdComentario());
            ps.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    @Override
    public void eliminar(int id){
        //Eliminar lógico
        String query = "UPDATE Comentario SET activo = 0 WHERE id_comentario = ?";
        try (Connection conn = DBManager.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(query)) {            
             ps.setInt(1, id);
             ps.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    private void setParametrosComentario(PreparedStatement ps, Comentario comen) throws SQLException{
        ps.setString(1, comen.getContenido());
        ps.setInt(2, comen.getValoracion());
        ps.setDate(3, comen.getFecha());
        ps.setInt(4, comen.getPublicacion().getIdPublicacion());
        ps.setInt(5, comen.getComentador().getIdUsuario());
        ps.setBoolean(6, comen.isActivo());
    }
    
    private Comentario mapaComentario(ResultSet rs) throws SQLException{
        Comentario comen = new Comentario();
        Publicacion publi = new Publicacion();
        Usuario usu = new Usuario();
        comen.setIdComentario(rs.getInt("id_comentario"));
        comen.setContenido(rs.getString("contenido"));
        comen.setValoracion(rs.getInt("valoracion"));
        comen.setFecha(rs.getDate("fecha"));
        publi.setIdPublicacion(rs.getInt("id_publicacion"));
        comen.setPublicacion(publi);
        usu.setIdUsuario(rs.getInt("id_usuario"));
        comen.setComentador(usu);
        comen.setActivo(rs.getBoolean("activo"));
        return comen;
    }
    
}
