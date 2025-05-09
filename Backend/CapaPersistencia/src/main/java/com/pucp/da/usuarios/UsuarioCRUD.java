/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pucp.da.usuarios;

import com.pucp.base.BaseDAOImpl;
import com.pucp.capadominio.usuarios.EstadoUsuario;
import com.pucp.capadominio.usuarios.Usuario;
import com.pucp.interfacesDAO.UsuarioDAO;
import java.sql.CallableStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Axel
 */
public class UsuarioCRUD extends BaseDAOImpl<Usuario> implements UsuarioDAO{

    @Override
    protected CallableStatement getInsertCS(Connection conn, Usuario usuario) throws SQLException {
        String sql = "{CALL INSERTAR_USUARIO(?, ?, ?, ?, ?, ?, ?, ?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, usuario.getIdUsuario());
        cs.setInt(2, usuario.getCodigoPUCP());
        cs.setString(3, usuario.getNombreUsuario());
        cs.setString(4, usuario.getContrasena());
        cs.setString(5, usuario.getNombre());
        cs.setString(6, usuario.getCorreo());
        cs.setString(7, usuario.getEstado().name());
        cs.setBoolean(8, usuario.isActivo());
        return cs; 
    }

    @Override
    protected CallableStatement getUpdateCS(Connection conn, Usuario usuario) throws SQLException {
        String sql = "{CALL MODIFICAR_USUARIO(?, ?, ?, ?, ?, ?, ?, ?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, usuario.getIdUsuario());
        cs.setInt(2, usuario.getCodigoPUCP());
        cs.setString(3, usuario.getNombreUsuario());
        cs.setString(4, usuario.getContrasena());
        cs.setString(5, usuario.getNombre());
        cs.setString(6, usuario.getCorreo());
        cs.setString(7, usuario.getEstado().name());
        cs.setBoolean(8, usuario.isActivo());
        return cs;         
    }

    @Override
    protected CallableStatement getDeleteCS(Connection conn, int id) throws SQLException {
        String sql = "{CALL ELIMINAR_USUARIO(?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement getSelectByIdCS(Connection conn, int id) throws SQLException {
        String sql = "{CALL OBTENER_USUARIO_X_ID(?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement getSelectAllCS(Connection conn) throws SQLException {
        String sql = "{CALL LISTAR_USUARIO_TODOS()}";
        CallableStatement cs = conn.prepareCall(sql);
        return cs;
    }

    @Override
    protected Usuario createFromResultSet(ResultSet rs) throws SQLException {
        Usuario usu = new Usuario();
        usu.setIdUsuario(rs.getInt("id_usuario"));
        usu.setCodigoPUCP(rs.getInt("codigo_PUCP"));
        usu.setNombreUsuario(rs.getString("nombreUsuario"));
        usu.setContrasena(rs.getString("contrasena"));
        usu.setNombre(rs.getString("nombre"));
        usu.setCorreo(rs.getString("correo"));
        usu.setEstado(EstadoUsuario.valueOf(rs.getString("estado")));
        usu.setActivo(rs.getBoolean("activo"));
        return usu;
    }

    @Override
    protected void setId(Usuario usuario, int id) {
        usuario.setIdUsuario(id);
    }
    
}
