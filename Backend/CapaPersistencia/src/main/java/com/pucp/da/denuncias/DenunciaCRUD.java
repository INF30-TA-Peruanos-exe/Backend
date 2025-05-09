/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pucp.da.denuncias;

import com.pucp.capadominio.denuncia.Denuncia;
import com.pucp.capadominio.publicacion.Publicacion;
import com.pucp.capadominio.usuarios.Administrador;
import com.pucp.capadominio.usuarios.Usuario;
import com.pucp.config.DBManager;
import com.pucp.interfacesDAO.DenunciaDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
/**
 *
 * @author Axel
 */
public class DenunciaCRUD implements DenunciaDAO{

    @Override
    public void insertar(Denuncia denuncia) {
        
        String query = "INSERT INTO Denuncia(autor,reportante,motivo,fecha_reporte,id_administrador,activo)"
                + "values(?,?,?,?,?,?)";
        try(Connection con = DBManager.getConnection();
            PreparedStatement ps = con.prepareStatement(query);) {        
            setParametrosDenuncia(ps, denuncia);
            ps.executeUpdate(); 
            //Traer el ultimo ID autogenerado
            try(Statement st = con.createStatement();
                ResultSet rskeys = st.executeQuery("select @@last_insert_id");){            
                if(rskeys.next()){
                    denuncia.setIdDenuncia(rskeys.getInt(1));
                }
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public ArrayList<Denuncia> listarTodos() {
        
        ArrayList<Denuncia> denuncias = new ArrayList<>();
        String query = "SELECT id_reporte,autor,reportante,motivo,fecha_reporte,id_administrador,activo FROM Denuncia WHERE activo = 1";
        try(Connection con = DBManager.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);){
            while(rs.next()){
                Denuncia denuncia = mapaDenuncia(rs);
                denuncias.add(denuncia);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return denuncias;
    }

    @Override
    public Denuncia obtenerPorId(int id) {
        
        String query = "SELECT id_reporte,autor,reportante,motivo,fecha_reporte,id_administrador,activo FROM Denuncia WHERE id_reporte = ?";
        try (Connection conn = DBManager.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapaDenuncia(rs);
                }
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return null; 

    }

    @Override
    public void actualizar(Denuncia denuncia) {
        
        String query = "UPDATE Denuncia SET autor = ?, reportante = ?, motivo = ?, fecha_reporte = ?, id_administrador = ?, activo = ? WHERE id_reporte = ?";
        try(Connection con = DBManager.getConnection();
            PreparedStatement ps = con.prepareStatement(query);){
            setParametrosDenuncia(ps,denuncia);
            ps.setInt(7,denuncia.getIdDenuncia());
            ps.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }

    }

    @Override
    public void eliminar(int id) {
        //Eliminar lógico
        String query = "UPDATE Denuncia SET activo = 0 WHERE id_reporte = ?";
        try (Connection conn = DBManager.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(query)) {            
             ps.setInt(1, id);
             ps.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }

    }
    
    private void setParametrosDenuncia(PreparedStatement ps, Denuncia denun) throws SQLException{
        ps.setInt(1, denun.getAutor().getIdPublicacion());
        ps.setInt(2, denun.getDenunciante().getIdUsuario());
        ps.setString(3, denun.getMotivo());
        ps.setDate(4, denun.getFechaDenuncia());
        ps.setInt(5,denun.getAdmin().getIdUsuario());
        ps.setBoolean(6, denun.isActivo());
    }
    
    private Denuncia mapaDenuncia(ResultSet rs) throws SQLException{
        Denuncia denun = new Denuncia();
        Publicacion autor = new Publicacion();
        Usuario usu = new Usuario();
        Administrador admin = new Administrador();
        
        denun.setIdDenuncia(rs.getInt("id_reporte"));
        autor.setIdPublicacion(rs.getInt("autor"));
        denun.setAutor(autor);
        usu.setIdUsuario(rs.getInt("reportante"));
        denun.setDenunciante(usu);
        denun.setMotivo(rs.getString("motivo"));
        denun.setFechaDenuncia(rs.getDate("fecha_reporte"));
        admin.setIdUsuario(rs.getInt("id_administrador"));
        denun.setAdmin(admin);
        denun.setActivo(rs.getBoolean("activo"));
        return denun;
    }
}
