/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pucp.da.publicaciones;

import com.pucp.base.BaseDAOImpl;
import com.pucp.capadominio.categorias.Curso;
import com.pucp.capadominio.categorias.Especialidad;
import com.pucp.capadominio.categorias.Facultad;
import com.pucp.capadominio.publicacion.EstadoPublicacion;
import com.pucp.capadominio.publicacion.Publicacion;
import com.pucp.config.DBManager;
import com.pucp.da.usuarios.UsuarioCRUD;
import com.pucp.interfacesDAO.PublicacionDAO;
import java.sql.CallableStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Axel
 */
public class PublicacionCRUD extends BaseDAOImpl<Publicacion> implements PublicacionDAO{
    
    private final UsuarioCRUD usuarioDAO;

    public PublicacionCRUD() {
        this.usuarioDAO = new UsuarioCRUD();
    } 

    @Override
    protected CallableStatement getInsertCS(Connection conn, Publicacion publicacion) throws SQLException {
        String sql = "{CALL INSERTAR_PUBLICACION(?, ?, ?, ?, ?, ?, ?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setString(1, publicacion.getTitulo());
        cs.setString(2, publicacion.getDescripcion());
        cs.setString(3, publicacion.getEstado().name());
        cs.setDate(4, publicacion.getFechaPublicacion());
        cs.setString(5, publicacion.getRutaImagen());
        cs.setInt(6, publicacion.getUsuario().getIdUsuario());
        cs.setBoolean(7, publicacion.isActivo());
        
        return cs; 
    }

    @Override
    protected CallableStatement getUpdateCS(Connection conn, Publicacion publicacion) throws SQLException {
        String sql = "{CALL MODIFICAR_PUBLICACION(?, ?, ?, ?, ?, ?, ?, ?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, publicacion.getIdPublicacion());
        cs.setString(2, publicacion.getTitulo());
        cs.setString(3, publicacion.getDescripcion());
        cs.setString(4, publicacion.getEstado().name());
        cs.setDate(5, publicacion.getFechaPublicacion());
        cs.setString(6, publicacion.getRutaImagen());
        cs.setInt(7, publicacion.getUsuario().getIdUsuario());
        cs.setBoolean(8, publicacion.isActivo());
        return cs;  
    }

    @Override
    protected CallableStatement getDeleteCS(Connection conn, int id) throws SQLException {
        String sql = "{CALL ELIMINAR_PUBLICACION(?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement getSelectByIdCS(Connection conn, int id) throws SQLException {
        String sql = "{CALL OBTENER_PUBLICACION_X_ID(?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement getSelectAllCS(Connection conn) throws SQLException {
        String sql = "{CALL LISTAR_PUBLICACION_TODOS()}";
        CallableStatement cs = conn.prepareCall(sql);
        return cs;
    }

    @Override
    protected Publicacion createFromResultSet(ResultSet rs) throws SQLException {
        Publicacion publi = new Publicacion();
        publi.setIdPublicacion(rs.getInt("idPublicacion"));
        publi.setTitulo(rs.getString("titulo"));
        publi.setDescripcion(rs.getString("descripcion"));
        publi.setEstado(EstadoPublicacion.valueOf(rs.getString("estado")));
        publi.setFechaPublicacion(rs.getDate("fechaPublicacion"));
        publi.setRutaImagen(rs.getString("url_imagen"));
        publi.setUsuario(usuarioDAO.obtenerPorId(rs.getInt("id_usuario")));
        publi.setActivo(rs.getBoolean("activo"));
        return publi;
    }

    @Override
    protected void setId(Publicacion publicacion, int id) {
        publicacion.setIdPublicacion(id);
    }

    @Override
    public ArrayList<Publicacion> listarporFacultad(int idFacultad) {
        ArrayList<Publicacion> publicaciones = new ArrayList<>();
        String sql = "{CALL LISTAR_PUBLICACION_X_FACULTAD_TODOS(?)}";
        try (Connection conn = DBManager.getInstance().obtenerConexion();
             CallableStatement cs = conn.prepareCall(sql);) {
            
            cs.setInt(1,idFacultad);
                try (ResultSet rs = cs.executeQuery()){
                while (rs.next()) {
                publicaciones.add(createFromResultSet(rs));
                    }
                }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar publicacion por facultad", e);
        }
        return publicaciones;
    }

    @Override
    public ArrayList<Publicacion> listarporEspecialidad(int idEspecialidad) {
        ArrayList<Publicacion> publicaciones = new ArrayList<>();
        String sql = "{CALL LISTAR_PUBLICACION_X_ESPECIALIDAD_TODOS(?)}";
        try (Connection conn = DBManager.getInstance().obtenerConexion();
             CallableStatement cs = conn.prepareCall(sql);) {

            cs.setInt(1,idEspecialidad);
            try (ResultSet rs = cs.executeQuery()){
                while (rs.next()) {
                    publicaciones.add(createFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar publicacion por especialidad", e);
        }
        return publicaciones;
    }

    @Override
    public ArrayList<Publicacion> listarporCurso(int idCurso) {
        ArrayList<Publicacion> publicaciones = new ArrayList<>();
        String sql = "{CALL LISTAR_PUBLICACION_X_CURSO_TODOS(?)}";
        try (Connection conn = DBManager.getInstance().obtenerConexion();
             CallableStatement cs = conn.prepareCall(sql);) {
            
            cs.setInt(1,idCurso);
            try(ResultSet rs = cs.executeQuery()){
                while (rs.next()) {
                    publicaciones.add(createFromResultSet(rs));
                }      
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar publicacion por curso", e);
        }
        return publicaciones;
    }
    
    @Override
    public void insertar(Publicacion publicacion) {
        super.insertar(publicacion); // Inserta la publicación principal y genera el ID

        try (Connection conn = DBManager.getInstance().obtenerConexion()) {
            // CURSO
            for (Curso cur : publicacion.getPublicacionesCursos()) {
                String sql = "{CALL PubliCursoIntermedio(?, ?)}";
                try (CallableStatement cs = conn.prepareCall(sql)) {
                    cs.setInt(1, publicacion.getIdPublicacion());
                    cs.setInt(2, cur.getIdCurso());
                    cs.executeUpdate();
                }
            }

            // ESPECIALIDAD
            for (Especialidad esp : publicacion.getPublicacionesEspecialidades()) {
                String sql = "{CALL PublicEspIntermedio(?, ?)}";
                try (CallableStatement cs = conn.prepareCall(sql)) {
                    cs.setInt(1, publicacion.getIdPublicacion());
                    cs.setInt(2, esp.getIdEspecialidad());
                    cs.executeUpdate();
                }
            }

            // FACULTAD
            for (Facultad fac : publicacion.getPublicacionesFacultades()) {
                String sql = "{CALL PublicFacIntermedio(?, ?)}";
                try (CallableStatement cs = conn.prepareCall(sql)) {
                    cs.setInt(1, publicacion.getIdPublicacion());
                    cs.setInt(2, fac.getIdFacultad());
                    cs.executeUpdate();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar relaciones de publicación", e);
        }
    }

    
}
