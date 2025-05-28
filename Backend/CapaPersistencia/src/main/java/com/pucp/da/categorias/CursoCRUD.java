/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pucp.da.categorias;

import com.pucp.base.BaseDAOImpl;
import com.pucp.capadominio.categorias.Curso;
import com.pucp.config.DBManager;
import com.pucp.interfacesDAO.CursoDAO;
import java.sql.CallableStatement;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

/**
 *
 * @author SEBASTIAN
 */
public class CursoCRUD extends BaseDAOImpl<Curso> implements CursoDAO{
    
    //NUEVO CAMBIO
    @Override
    protected int obtenerIdGenerado(CallableStatement cs) throws SQLException {
        return cs.getInt(3); // Valor por defecto: no hay OUT
    }
    
  
    @Override
    protected CallableStatement getInsertCS(Connection conn, Curso curso) throws SQLException {
        String sql = "{CALL INSERTAR_CURSO(?, ?, ?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setString(1, curso.getNombre());
        cs.setBoolean(2, curso.isActivo());
        cs.registerOutParameter(3, Types.INTEGER);
        return cs;    
    }

    @Override
    protected CallableStatement getUpdateCS(Connection conn, Curso curso) throws SQLException {
        String sql = "{CALL MODIFICAR_CURSO(?, ?, ?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, curso.getIdCurso());
        cs.setString(2, curso.getNombre());
        cs.setBoolean(3, curso.isActivo());
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
    
    //METODOS ADICIONALES PARA TABLAS INTERMEDIAS
    public ArrayList<Curso> listarCursosPorPublicacion(int idPublicacion) {
    ArrayList<Curso> lista = new ArrayList<>();
    String sql = "{CALL LISTAR_CURSOS_X_PUBLICACION(?)}";
    try (Connection conn = DBManager.getInstance().obtenerConexion();
         CallableStatement cs = conn.prepareCall(sql)) {
        cs.setInt(1, idPublicacion);
        try (ResultSet rs = cs.executeQuery()) {
            System.err.println("Llamando a LISTAR_CURSOS_X_PUBLICACION con ID: " + idPublicacion);
            while (rs.next()) {
                Curso c = new Curso();
                c.setIdCurso(rs.getInt("id_curso"));
                c.setNombre(rs.getString("nombre")); // o los campos que uses
                c.setActivo(true);
                lista.add(c);
                System.err.println("Curso obtenido: " + c.getIdCurso() + " - " + c.getNombre());

            }
        }
    } catch (SQLException e) {
        throw new RuntimeException("Error al listar cursos de una publicación", e);
    }
    System.err.println("Cursos recuperados por publicación ID " + idPublicacion + ": " + lista.size());
    for (Curso c : lista) {
    System.err.println("- " + c.getNombre());
}
    return lista;
}
    
}
