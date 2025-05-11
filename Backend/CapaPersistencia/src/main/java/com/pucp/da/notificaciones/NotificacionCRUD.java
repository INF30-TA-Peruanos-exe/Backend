/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pucp.da.notificaciones;

import com.pucp.base.BaseDAOImpl;
import com.pucp.capadominio.notificacion.Notificacion;
import com.pucp.capadominio.notificacion.TipoNotificacion;
import com.pucp.da.publicaciones.PublicacionCRUD;
import com.pucp.da.usuarios.UsuarioCRUD;
import com.pucp.interfacesDAO.NotificacionDAO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *
 * @author Axel
 */
public class NotificacionCRUD extends BaseDAOImpl<Notificacion> implements NotificacionDAO{
    
    private final UsuarioCRUD usuarioDAO;
    private final PublicacionCRUD publicacionDAO;
    
    public NotificacionCRUD(){
        usuarioDAO = new UsuarioCRUD();
        publicacionDAO = new PublicacionCRUD();
    }

    @Override
    protected CallableStatement getInsertCS(Connection conn, Notificacion notificacion) throws SQLException {
        String sql = "{CALL INSERTAR_NOTIFICACION(?, ?, ?, ?, ?, ?, ?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setString(1, notificacion.getMensaje());
        cs.setString(2, notificacion.getTipoNotificacion().name());
        cs.setInt(3, notificacion.getCantidad());
        cs.setDate(4, notificacion.getFecha());
        cs.setInt(5, notificacion.getAutor().getIdPublicacion());
        cs.setInt(6, notificacion.getNotificador().getIdUsuario());
        cs.setBoolean(7, notificacion.isActivo());
        return cs; 
    }

    @Override
    protected CallableStatement getUpdateCS(Connection conn, Notificacion notificacion) throws SQLException {
        String sql = "{CALL MODIFICAR_NOTIFICACION(?, ?, ?, ?, ?, ?, ?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setString(1, notificacion.getMensaje());
        cs.setString(2, notificacion.getTipoNotificacion().name());
        cs.setInt(3, notificacion.getCantidad());
        cs.setDate(4, notificacion.getFecha());
        cs.setInt(5, notificacion.getAutor().getIdPublicacion());
        cs.setInt(6, notificacion.getNotificador().getIdUsuario());
        cs.setBoolean(7, notificacion.isActivo());
        return cs; 
    }

    @Override
    protected CallableStatement getDeleteCS(Connection conn, int id) throws SQLException {
        String sql = "{CALL ELIMINAR_NOTIFICACION(?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement getSelectByIdCS(Connection conn, int id) throws SQLException {
        String sql = "{CALL OBTENER_NOTIFICACION_X_ID(?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement getSelectAllCS(Connection conn) throws SQLException {
        String sql = "{CALL LISTAR_NOTIFICACION_TODOS()}";
        CallableStatement cs = conn.prepareCall(sql);
        return cs; 
    }

    @Override
    protected Notificacion createFromResultSet(ResultSet rs) throws SQLException {
        Notificacion noti = new Notificacion();
        noti.setIdNotificacion(rs.getInt("id_notificacion"));
        noti.setMensaje(rs.getString("mensaje"));
        noti.setTipoNotificacion(TipoNotificacion.valueOf(rs.getString("tipo_notificacion")));
        noti.setCantidad(rs.getInt("cantidad"));
        noti.setFecha(rs.getDate("fecha"));
        noti.setAutor(publicacionDAO.obtenerPorId(rs.getInt("id_publicacion")));
        noti.setNotificador(usuarioDAO.obtenerPorId(rs.getInt("id_usuario")));
        noti.setActivo(rs.getBoolean("activo"));
        return noti;
    }

    @Override
    protected void setId(Notificacion notificacion, int id) {
        notificacion.setIdNotificacion(id);
    }
    
}
