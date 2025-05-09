/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pucp.da.notificaciones;

import com.pucp.capadominio.notificacion.Notificacion;
import com.pucp.capadominio.notificacion.TipoNotificacion;
import com.pucp.capadominio.publicacion.Publicacion;
import com.pucp.capadominio.usuarios.Usuario;
import com.pucp.config.DBManager;
import com.pucp.interfacesDAO.NotificacionDAO;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Axel
 */
public class NotificacionCRUD implements NotificacionDAO{
    
    @Override
    public void insertar(Notificacion notificacion){
        String query = "INSERT INTO Notificacion(mensaje,tipo_notificacion,cantidad,fecha,id_publicacion,id_usuario,activo)"
                + "values(?,?,?,?,?,?,?)";
        try(Connection con = DBManager.getConnection();
            PreparedStatement ps = con.prepareStatement(query);) {        
            setParametrosNotificacion(ps, notificacion);
            ps.executeUpdate(); 
            //Traer el ultimo ID autogenerado
            try(Statement st = con.createStatement();
                ResultSet rskeys = st.executeQuery("select @@last_insert_id");){            
                if(rskeys.next()){
                    notificacion.setIdNotificacion(rskeys.getInt(1));
                }
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    @Override
    public ArrayList<Notificacion> listarTodos(){
        ArrayList<Notificacion> notificaciones = new ArrayList<>();
        String query = "SELECT id_notificacion,mensaje,tipo_notificacion,cantidad,fecha,id_publicacion,id_usuario,activo FROM Notificacion WHERE activo = 1";
        try(Connection con  =DBManager.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);){
            while(rs.next()){
                Notificacion notificacion = mapaNotificacion(rs);
                notificaciones.add(notificacion);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return notificaciones;
    } 
    
    @Override
    public Notificacion obtenerPorId(int id){
        String query = "SELECT id_notificacion,mensaje,tipo_notificacion,cantidad,fecha,id_publicacion,id_usuario,activo FROM Notificacion WHERE id_notificacion = ?";
        try (Connection conn = DBManager.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapaNotificacion(rs);
                }
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return null;  
    }
    
    @Override
    public void actualizar(Notificacion notificacion){
        String query = "UPDATE Notificacion SET mensaje = ?, tipo_notificacion = ?, cantidad = ?, fecha = ?, id_publicacion = ?, id_usuario = ?, activo = ? WHERE id_notificacion = ?";
        try(Connection con = DBManager.getConnection();
            PreparedStatement ps = con.prepareStatement(query);){
            setParametrosNotificacion(ps,notificacion);
            ps.setInt(8,notificacion.getIdNotificacion());
            ps.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    @Override
    public void eliminar(int id){
        //Eliminar lógico
        String query = "UPDATE Notificacion SET activo = 0 WHERE id_notificacion = ?";
        try (Connection conn = DBManager.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(query)) {            
             ps.setInt(1, id);
             ps.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    private void setParametrosNotificacion(PreparedStatement ps, Notificacion noti) throws SQLException{
        ps.setString(1, noti.getMensaje());
        ps.setString(2, noti.getTipoNotificacion().name());
        ps.setInt(3, noti.getCantidad());
        ps.setDate(4, noti.getFecha());
        ps.setInt(5, noti.getAutor().getIdPublicacion());
        ps.setInt(6, noti.getNotificador().getIdUsuario());
        ps.setBoolean(7, noti.isActivo());
    }
    
    private Notificacion mapaNotificacion(ResultSet rs) throws SQLException{
        Notificacion noti = new Notificacion();
        Publicacion autor = new Publicacion();
        Usuario usu = new Usuario();
        noti.setIdNotificacion(rs.getInt("id_notificacion"));
        noti.setMensaje(rs.getString("mensaje"));
        noti.setTipoNotificacion(TipoNotificacion.valueOf(rs.getString("tipo_notificacion")));
        noti.setCantidad(rs.getInt("cantidad"));
        noti.setFecha(rs.getDate("fecha"));
        autor.setIdPublicacion(rs.getInt("id_publicacion"));
        noti.setAutor(autor);
        usu.setIdUsuario(rs.getInt("id_usuario"));
        noti.setNotificador(usu);
        noti.setActivo(rs.getBoolean("activo"));
        return noti;
    }
    
    
}
