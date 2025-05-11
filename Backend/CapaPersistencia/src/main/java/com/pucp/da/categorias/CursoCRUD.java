/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pucp.da.categorias;

import com.pucp.base.BaseDAOImpl;
import com.pucp.capadominio.categorias.Curso;
import com.pucp.interfacesDAO.CursoDAO;
import java.sql.CallableStatement;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author SEBASTIAN
 */
public class CursoCRUD extends BaseDAOImpl<Curso> implements CursoDAO{
  
    @Override
    protected CallableStatement getInsertCS(Connection conn, Curso curso) throws SQLException {
        String sql = "{CALL INSERTAR_CURSO(?, ?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setString(1, curso.getNombre());
        cs.setBoolean(2, curso.isActivo());
        return cs;    
    }

    @Override
    protected CallableStatement getUpdateCS(Connection conn, Curso curso) throws SQLException {
        String sql = "{CALL MODIFICAR_CURSO(?, ?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setString(1, curso.getNombre());
        cs.setBoolean(2, curso.isActivo());
        return cs;
    }

    @Override
    protected CallableStatement getDeleteCS(Connection conn, int id) throws SQLException {
        String sql = "{CALL ELIMINAR_CURSO(?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement getSelectByIdCS(Connection conn, int id) throws SQLException {
        String sql = "{CALL OBTENER_CURSO_X_ID(?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement getSelectAllCS(Connection conn) throws SQLException {
        String sql = "{CALL LISTAR_CURSO_TODOS()}";
        CallableStatement cs = conn.prepareCall(sql);
        return cs;
    }

    @Override
    protected Curso createFromResultSet(ResultSet rs) throws SQLException {
        Curso cur = new Curso();
        cur.setIdCurso(rs.getInt("id_curso"));
        cur.setNombre(rs.getString("nombre"));
        cur.setActivo(rs.getBoolean("activo"));
        return cur;    }

    @Override
    protected void setId(Curso curso, int id) {
        curso.setIdCurso(id);
    }
    
}
