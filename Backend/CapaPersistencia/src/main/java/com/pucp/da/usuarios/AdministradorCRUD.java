/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pucp.da.usuarios;

import com.pucp.base.BaseDAOImpl;
import com.pucp.capadominio.usuarios.Administrador;
import com.pucp.capadominio.usuarios.EstadoUsuario;
import com.pucp.interfacesDAO.AdministradorDAO;
import java.sql.CallableStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Axel
 */
public class AdministradorCRUD extends BaseDAOImpl<Administrador> implements AdministradorDAO{

    @Override
    protected CallableStatement getInsertCS(Connection conn, Administrador administrador) throws SQLException {
        String sql = "{CALL INSERTAR_ADMINISTRADOR(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, administrador.getIdUsuario());
        cs.setInt(2, administrador.getCodigoPUCP());
        cs.setString(3, administrador.getNombreUsuario());
        cs.setString(4, administrador.getContrasena());
        cs.setString(5, administrador.getNombre());
        cs.setString(6, administrador.getCorreo());
        cs.setString(7, administrador.getEstado().name());
        cs.setBoolean(8, administrador.isActivo());
        cs.setString(9, administrador.getClaveMaestra());
        return cs; 
    }

    @Override
    protected CallableStatement getUpdateCS(Connection conn, Administrador administrador) throws SQLException {
        String sql = "{CALL MODIFICAR_ADMINISTRADOR(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, administrador.getIdUsuario());
        cs.setInt(2, administrador.getCodigoPUCP());
        cs.setString(3, administrador.getNombreUsuario());
        cs.setString(4, administrador.getContrasena());
        cs.setString(5, administrador.getNombre());
        cs.setString(6, administrador.getCorreo());
        cs.setString(7, administrador.getEstado().name());
        cs.setBoolean(8, administrador.isActivo());
        cs.setString(9, administrador.getClaveMaestra());
        return cs;  
    }

    @Override
    protected CallableStatement getDeleteCS(Connection conn, int id) throws SQLException {
        String sql = "{CALL ELIMINAR_ADMINISTRADOR(?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement getSelectByIdCS(Connection conn, int id) throws SQLException {
        String sql = "{CALL OBTENER_ADMINISTRADOR_X_ID(?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement getSelectAllCS(Connection conn) throws SQLException {
        String sql = "{CALL LISTAR_ADMINISTRADOR_TODOS()}";
        CallableStatement cs = conn.prepareCall(sql);
        return cs;
    }

    @Override
    protected Administrador createFromResultSet(ResultSet rs) throws SQLException {
        Administrador admin = new Administrador();
        admin.setIdUsuario(rs.getInt("id_usuario"));
        admin.setCodigoPUCP(rs.getInt("codigo_PUCP"));
        admin.setNombreUsuario(rs.getString("nombreUsuario"));
        admin.setContrasena(rs.getString("contrasena"));
        admin.setNombre(rs.getString("nombre"));
        admin.setCorreo(rs.getString("correo"));
        admin.setEstado(EstadoUsuario.valueOf(rs.getString("estado")));
        admin.setActivo(rs.getBoolean("activo"));
        admin.setClaveMaestra(rs.getString("clave_Maestra"));
        return admin;
    }

    @Override
    protected void setId(Administrador administrador, int id) {
        administrador.setIdUsuario(id);
    }
    
}
