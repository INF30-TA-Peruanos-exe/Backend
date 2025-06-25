/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pucp.da.denuncias;

import com.pucp.base.BaseDAOImpl;
import com.pucp.capadominio.denuncia.Denuncia;
import com.pucp.capadominio.publicacion.Publicacion;
import com.pucp.capadominio.usuarios.Administrador;
import com.pucp.capadominio.usuarios.Usuario;
import com.pucp.da.publicaciones.PublicacionCRUD;
import com.pucp.da.usuarios.AdministradorCRUD;
import com.pucp.da.usuarios.UsuarioCRUD;
import com.pucp.interfacesDAO.DenunciaDAO;
import java.sql.CallableStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
/**
 *
 * @author Axel
 */
public class DenunciaCRUD extends BaseDAOImpl<Denuncia>implements DenunciaDAO{
    
    private final UsuarioCRUD usuarioDAO;
    private final PublicacionCRUD publicacionDAO;
    private final AdministradorCRUD administradorDAO;
    
    public DenunciaCRUD(){
        usuarioDAO = new UsuarioCRUD();
        publicacionDAO = new PublicacionCRUD();
        administradorDAO = new AdministradorCRUD();
    }
    
    //NUEVO CAMBIO
    @Override
    protected int obtenerIdGenerado(CallableStatement cs) throws SQLException {
        return cs.getInt(7); // Valor por defecto: no hay OUT
    }

    @Override
    protected CallableStatement getInsertCS(Connection conn, Denuncia denuncia) throws SQLException {
        String sql = "{CALL INSERTAR_DENUNCIA(?, ?, ?, ?, ?, ?, ?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, denuncia.getAutor().getIdPublicacion());
        cs.setInt(2, denuncia.getDenunciante().getIdUsuario());
        cs.setString(3, denuncia.getMotivo());
        cs.setDate(4, new java.sql.Date(denuncia.getFechaDenuncia().getTime()));
        cs.setNull(5, java.sql.Types.INTEGER);
        cs.setBoolean(6, denuncia.isActivo());
        cs.registerOutParameter(7, Types.INTEGER);
        return cs; 
    }

    @Override
    protected CallableStatement getUpdateCS(Connection conn, Denuncia denuncia) throws SQLException {
        String sql = "{CALL MODIFICAR_DENUNCIA(?, ?, ?, ?, ?, ?, ?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, denuncia.getIdDenuncia());
        cs.setInt(2, denuncia.getAutor().getIdPublicacion());
        cs.setInt(3, denuncia.getDenunciante().getIdUsuario());
        cs.setString(4, denuncia.getMotivo());
        cs.setDate(5, new java.sql.Date(denuncia.getFechaDenuncia().getTime()));
        cs.setInt(6,denuncia.getAdmin().getIdUsuario());
        cs.setBoolean(7, denuncia.isActivo());
        return cs; 
    }

    @Override
    protected CallableStatement getDeleteCS(Connection conn, int id) throws SQLException {
        String sql = "{CALL ELIMINAR_DENUNCIA(?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement getSelectByIdCS(Connection conn, int id) throws SQLException {
        String sql = "{CALL OBTENER_DENUNCIA_X_ID(?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement getSelectAllCS(Connection conn) throws SQLException {
        String sql = "{CALL LISTAR_DENUNCIA_TODOS()}";
        CallableStatement cs = conn.prepareCall(sql);
        return cs;
    }

    @Override
    protected Denuncia createFromResultSet(ResultSet rs) throws SQLException {
        Denuncia denun = new Denuncia();
    denun.setIdDenuncia(rs.getInt("id_reporte"));
    denun.setMotivo(rs.getString("motivo"));
    denun.setFechaDenuncia(rs.getDate("fecha_reporte"));
    denun.setActivo(rs.getBoolean("activo"));

    // Autor (Publicación)
    Publicacion pub = new Publicacion();
    pub.setIdPublicacion(rs.getInt("idpublicacion"));
    pub.setTitulo(rs.getString("titulo_publicacion"));
    denun.setAutor(pub);

    // Reportante (Usuario)
    Usuario usuario = new Usuario();
    usuario.setIdUsuario(rs.getInt("id_reportante"));
    usuario.setNombre(rs.getString("nombre_reportante"));
    denun.setDenunciante(usuario);
    
    // Administrador (opcional)
    int idAdmin = rs.getInt("id_administrador");
    if (!rs.wasNull()) {
        Administrador admin = new Administrador();
        admin.setIdUsuario(idAdmin);
        denun.setAdmin(admin);
    }
    
        return denun;
    }

    @Override
    protected void setId(Denuncia denuncia, int id) {
        denuncia.setIdDenuncia(id);
    }
}
