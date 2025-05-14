/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pucp.da.publicaciones;

import com.pucp.base.BaseDAOImpl;
import com.pucp.capadominio.publicacion.Comentario;
import com.pucp.da.usuarios.UsuarioCRUD;
import com.pucp.interfacesDAO.ComentarioDAO;
import java.sql.CallableStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 *
 * @author Axel
 */
public class ComentarioCRUD extends BaseDAOImpl<Comentario> implements ComentarioDAO{
    
    private final UsuarioCRUD usuarioDAO;
    private final PublicacionCRUD publicacionDAO;
    
    public ComentarioCRUD(){
        usuarioDAO = new UsuarioCRUD();
        publicacionDAO = new PublicacionCRUD();
    }

    @Override
    protected CallableStatement getInsertCS(Connection conn, Comentario comentario) throws SQLException {
        String sql = "{CALL INSERTAR_COMENTARIO(?, ?, ?, ?, ?, ?, ?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setString(1, comentario.getContenido());
        cs.setInt(2, comentario.getValoracion());
        cs.setDate(3, comentario.getFecha());
        cs.setInt(4, comentario.getPublicacion().getIdPublicacion());
        cs.setInt(5, comentario.getComentador().getIdUsuario());
        cs.setBoolean(6, comentario.isActivo());
        cs.registerOutParameter(7, Types.INTEGER);
        return cs; 
    }

    @Override
    protected CallableStatement getUpdateCS(Connection conn, Comentario comentario) throws SQLException {
        String sql = "{CALL MODIFICAR_COMENTARIO(?, ?, ?, ?, ?, ?, ?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, comentario.getIdComentario());
        cs.setString(2, comentario.getContenido());
        cs.setInt(3, comentario.getValoracion());
        cs.setDate(4, comentario.getFecha());
        cs.setInt(5, comentario.getPublicacion().getIdPublicacion());
        cs.setInt(6, comentario.getComentador().getIdUsuario());
        cs.setBoolean(7, comentario.isActivo());
        return cs;  
    }

    @Override
    protected CallableStatement getDeleteCS(Connection conn, int id) throws SQLException {
        String sql = "{CALL ELIMINAR_COMENTARIO(?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement getSelectByIdCS(Connection conn, int id) throws SQLException {
        String sql = "{CALL OBTENER_COMENTARIO_X_ID(?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement getSelectAllCS(Connection conn) throws SQLException {
        String sql = "{CALL LISTAR_COMENTARIO_TODOS()}";
        CallableStatement cs = conn.prepareCall(sql);
        return cs;    
    }

    @Override
    protected Comentario createFromResultSet(ResultSet rs) throws SQLException {
        Comentario comen = new Comentario();
        comen.setIdComentario(rs.getInt("id_comentario"));
        comen.setContenido(rs.getString("contenido"));
        comen.setValoracion(rs.getInt("valoracion"));
        comen.setFecha(rs.getDate("fecha"));
        comen.setPublicacion(publicacionDAO.obtenerPorId(rs.getInt("id_publicacion")));
        comen.setComentador(usuarioDAO.obtenerPorId(rs.getInt("id_usuario")));
        comen.setActivo(rs.getBoolean("activo"));
        return comen;
    }

    @Override
    protected void setId(Comentario comentario, int id) {
        comentario.setIdComentario(id);
    }
    
}
