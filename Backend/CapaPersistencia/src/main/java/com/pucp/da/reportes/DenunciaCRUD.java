/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pucp.da.reportes;

import com.pucp.base.BaseDAOImpl;
import com.pucp.capadominio.denuncia.Denuncia;
import com.pucp.da.publicaciones.PublicacionCRUD;
import com.pucp.da.usuarios.AdministradorCRUD;
import com.pucp.da.usuarios.UsuarioCRUD;
import com.pucp.interfacesDAO.DenunciaDAO;
import java.sql.CallableStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    @Override
    protected CallableStatement getInsertCS(Connection conn, Denuncia denuncia) throws SQLException {
        String sql = "{CALL INSERTAR_DENUNCIA(?, ?, ?, ?, ?, ?, ?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, denuncia.getIdDenuncia());
        cs.setInt(2, denuncia.getAutor().getIdPublicacion());
        cs.setInt(3, denuncia.getDenunciante().getIdUsuario());
        cs.setString(4, denuncia.getMotivo());
        cs.setDate(5, denuncia.getFechaDenuncia());
        cs.setInt(6,denuncia.getAdmin().getIdUsuario());
        cs.setBoolean(7, denuncia.isActivo());
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
        cs.setDate(5, denuncia.getFechaDenuncia());
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
        denun.setAutor(publicacionDAO.obtenerPorId(rs.getInt("autor")));
        denun.setDenunciante(usuarioDAO.obtenerPorId(rs.getInt("reportante")));
        denun.setMotivo(rs.getString("motivo"));
        denun.setFechaDenuncia(rs.getDate("fecha_reporte"));
        denun.setAdmin(administradorDAO.obtenerPorId(rs.getInt("id_administrador")));
        denun.setActivo(rs.getBoolean("activo"));
        return denun;
    }

    @Override
    protected void setId(Denuncia denuncia, int id) {
        denuncia.setIdDenuncia(id);
    }
}
