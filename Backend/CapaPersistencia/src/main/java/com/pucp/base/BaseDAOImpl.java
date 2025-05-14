/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pucp.base;

import com.pucp.config.DBManager;
import com.pucp.interfacesDAO.BaseDAO;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author SEBASTIAN
 */
public abstract class BaseDAOImpl<T> implements BaseDAO<T>{
    
    protected abstract CallableStatement getInsertCS(Connection conn, T entity) throws SQLException;
    protected abstract CallableStatement getUpdateCS(Connection conn, T entity) throws SQLException;
    protected abstract CallableStatement getDeleteCS(Connection conn, int id) throws SQLException;
    protected abstract CallableStatement getSelectByIdCS(Connection conn, int id) throws SQLException;
    protected abstract CallableStatement getSelectAllCS(Connection conn) throws SQLException;
    protected abstract T createFromResultSet(ResultSet rs) throws SQLException;
    protected abstract void setId(T entity, int id);
    
    @Override
    public void insertar(T entidad) {
        try (Connection conn = DBManager.getInstance().obtenerConexion();
             CallableStatement cs = getInsertCS(conn, entidad)) {

            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    setId(entidad, rs.getInt("id"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al agregar entidad", e);
        }
    }

    @Override
    public ArrayList<T> listarTodos() {
        ArrayList<T> entities = new ArrayList<>();
        try (Connection conn = DBManager.getInstance().obtenerConexion();
             CallableStatement cs = getSelectAllCS(conn);
             ResultSet rs = cs.executeQuery()) {

            while (rs.next()) {
                entities.add(createFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar entidades", e);
        }
        return entities;    
    }

    @Override
    public T obtenerPorId(int id) {
        try (Connection conn = DBManager.getInstance().obtenerConexion();
             CallableStatement cs = getSelectByIdCS(conn, id);
             ResultSet rs = cs.executeQuery()) {

            if (rs.next()) {
                return createFromResultSet(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener entidad", e);
        }
        return null;
    }

    @Override
    public void actualizar(T entidad) {
        try (Connection conn = DBManager.getInstance().obtenerConexion();
             CallableStatement cs = getUpdateCS(conn, entidad)) {

            cs.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar entidad", e);
        }    
    }

    @Override
    public void eliminar(int id) {
        try (Connection conn = DBManager.getInstance().obtenerConexion();
             CallableStatement cs = getDeleteCS(conn, id)) {

            cs.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar entidad", e);
        }    
    }
     
}
