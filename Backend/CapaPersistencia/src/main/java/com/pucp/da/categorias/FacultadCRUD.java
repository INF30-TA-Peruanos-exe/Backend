/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pucp.da.categorias;

import com.pucp.base.BaseDAOImpl;
import com.pucp.capadominio.categorias.Facultad;
import com.pucp.interfacesDAO.FacultadDAO;
import java.sql.CallableStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
/**
 *
 * @author SEBASTIAN
 */
public class FacultadCRUD extends BaseDAOImpl<Facultad> implements FacultadDAO{

    //NUEVO CAMBIO
    @Override
    protected int obtenerIdGenerado(CallableStatement cs) throws SQLException {
        return cs.getInt(3); // Valor por defecto: no hay OUT
    }
    
    @Override
    protected CallableStatement getInsertCS(Connection conn, Facultad facultad) throws SQLException {
        String sql = "{CALL INSERTAR_FACULTAD(?, ?, ?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setString(1, facultad.getNombre());
        cs.setBoolean(2, facultad.isActivo());
        cs.registerOutParameter(3, Types.INTEGER);
        return cs;
    }

    @Override
    protected CallableStatement getUpdateCS(Connection conn, Facultad facultad) throws SQLException {
        String sql = "{CALL MODIFICAR_FACULTAD(?, ?, ?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, facultad.getIdFacultad());
        cs.setString(2, facultad.getNombre());
        cs.setBoolean(3, facultad.isActivo());
        return cs; 
    }

    @Override
    protected CallableStatement getDeleteCS(Connection conn, int id) throws SQLException {
        String sql = "{CALL ELIMINAR_FACULTAD(?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement getSelectByIdCS(Connection conn, int id) throws SQLException {
        String sql = "{CALL OBTENER_FACULTAD_X_ID(?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement getSelectAllCS(Connection conn) throws SQLException {
        String sql = "{CALL LISTAR_FACULTAD_TODOS()}";
        CallableStatement cs = conn.prepareCall(sql);
        return cs;
    }

    @Override
    protected Facultad createFromResultSet(ResultSet rs) throws SQLException {
        Facultad facu = new Facultad();
        facu.setIdFacultad(rs.getInt("id_facultad"));
        facu.setNombre(rs.getString("nombre"));
        facu.setActivo(rs.getBoolean("activo"));
        return facu;
    }

    @Override
    protected void setId(Facultad facultad, int id) {
        facultad.setIdFacultad(id);
    }
    
}
