/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pucp.da.categorias;

import com.pucp.base.BaseDAOImpl;
import com.pucp.capadominio.categorias.Especialidad;
import com.pucp.interfacesDAO.EspecialidadDAO;
import java.sql.CallableStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author SEBASTIAN
 */
public class EspecialidadCRUD extends BaseDAOImpl<Especialidad>implements EspecialidadDAO{

    @Override
    protected CallableStatement getInsertCS(Connection conn, Especialidad especialidad) throws SQLException {
        String sql = "{CALL INSERTAR_ESPECIALIDAD(?, ?, ?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, especialidad.getIdEspecialidad());
        cs.setString(2, especialidad.getNombre());
        cs.setBoolean(3, especialidad.isActivo());
        return cs;     
    }

    @Override
    protected CallableStatement getUpdateCS(Connection conn, Especialidad especialidad) throws SQLException {
        String sql = "{CALL MODIFICAR_ESPECIALIDAD(?, ?, ?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, especialidad.getIdEspecialidad());
        cs.setString(2, especialidad.getNombre());
        cs.setBoolean(3, especialidad.isActivo());
        return cs;    
    }

    @Override
    protected CallableStatement getDeleteCS(Connection conn, int id) throws SQLException {
        String sql = "{CALL ELIMINAR_ESPECIALIDAD(?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement getSelectByIdCS(Connection conn, int id) throws SQLException {
        String sql = "{CALL OBTENER_ESPECIALIDAD_X_ID(?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement getSelectAllCS(Connection conn) throws SQLException {
        String sql = "{CALL LISTAR_ESPECIALIDAD_TODOS()}";
        CallableStatement cs = conn.prepareCall(sql);
        return cs;
    }

    @Override
    protected Especialidad createFromResultSet(ResultSet rs) throws SQLException {
        Especialidad espe = new Especialidad();
        espe.setIdEspecialidad(rs.getInt("id_especialidad"));
        espe.setNombre(rs.getString("nombre"));
        espe.setActivo(rs.getBoolean("activo"));
        return espe;
    }

    @Override
    protected void setId(Especialidad especialidad, int id) {
        especialidad.setIdEspecialidad(id);
    }
    
}
