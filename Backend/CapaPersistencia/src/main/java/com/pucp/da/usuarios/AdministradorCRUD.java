/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pucp.da.usuarios;

import com.pucp.capadominio.usuarios.Administrador;
import com.pucp.capadominio.usuarios.EstadoUsuario;
import com.pucp.config.DBManager;
import com.pucp.interfacesDAO.AdministradorDAO;

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
public class AdministradorCRUD implements AdministradorDAO{

    @Override
    public void insertar(Administrador administrador) {
        
        String query1 = "INSERT INTO Usuario(codigo_PUCP,nombreUsuario,contrasena,nombre,correo,estado,activo)"
                + "values(?,?,?,?,?,?,?)";
        String query2 = "INSERT INTO Administrador(id_Administrador,clave_Maestra)"
                + "values(?,?)";
        try(Connection con = DBManager.getConnection();
            PreparedStatement ps1 = con.prepareStatement(query1);
            PreparedStatement ps2 = con.prepareStatement(query2);) {
            //Insertar Usuario
            setParametrosUsuario(ps1, administrador);
            ps1.executeUpdate(); 
            //Traer el ultimo ID autogenerado
            try(Statement st = con.createStatement();
                ResultSet rskeys = st.executeQuery("select @@last_insert_id");){            
                if(rskeys.next()){
                    administrador.setIdUsuario(rskeys.getInt(1));
                }
            }
            //Insertar Administrador
            ps2.setInt(1, administrador.getIdUsuario());
            ps2.setString(2, administrador.getClaveMaestra());
            ps2.executeUpdate();            
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public ArrayList<Administrador> listarTodos() {
        
        ArrayList<Administrador> administradores = new ArrayList<>();
        String query = "SELECT u.id_usuario,u.codigo_PUCP,u.nombreUsuario,u.contrasena,u.nombre,u.correo,u.estado,u.activo,a.clave_Maestra "
                + "FROM Usuario u, Administrador a WHERE u.id_usuario = a.id_Administrador AND u.activo = 1 ";
        try(Connection con  =DBManager.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);){
            while(rs.next()){
                Administrador administrador = mapaAdministrador(rs);
                administradores.add(administrador);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return administradores;
    }

    @Override
    public Administrador obtenerPorId(int id) {
        
        String query = "SELECT u.id_usuario,u.codigo_PUCP,u.nombreUsuario,u.contrasena,u.nombre,u.correo,u.estado,u.activo,a.clave_Maestra "
                + "FROM Usuario u, Administrador a WHERE u.id_usuario = a.id_Administrador AND u.id_usuario = ? ";
        try (Connection conn = DBManager.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapaAdministrador(rs);
                }
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return null;

    }

    @Override
    public void actualizar(Administrador administrador) {
        
        String query1 = "UPDATE Usuario SET codigo_PUCP = ?, nombreUsuario = ?, contrasena = ?, nombre = ?, correo = ?, estado = ? ,activo = ? WHERE id_usuario = ?";
        String query2 = "UPDATE Administrador SET clave_Maestra = ? WHERE id_Administrador = ?";
        try(Connection con = DBManager.getConnection();
            PreparedStatement ps1 = con.prepareStatement(query1);
            PreparedStatement ps2 = con.prepareStatement(query2);){
            //Actualizar Usuario
            setParametrosUsuario(ps1,administrador);
            ps1.setInt(8,administrador.getIdUsuario());
            ps1.executeUpdate();
            //Actualizar Administrador
            setParametrosAdministrador(ps2,administrador);
            ps2.setInt(2,administrador.getIdUsuario());
            ps2.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        //Eliminar lógico
        String query = "UPDATE Usuario u, Administrador a SET u.activo = 0 WHERE u.id_usuario = a.id_Administrador AND u.id_usuario = ?";
        try (Connection conn = DBManager.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(query)) {            
             ps.setInt(1, id);
             ps.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }

    }
    
    private void setParametrosUsuario(PreparedStatement ps, Administrador admin) throws SQLException{
        ps.setInt(1, admin.getCodigoPUCP());
        ps.setString(2, admin.getNombreUsuario());
        ps.setString(3, admin.getContrasena());
        ps.setString(4, admin.getNombre());
        ps.setString(5, admin.getCorreo());
        ps.setString(6, admin.getEstado().name());
        ps.setBoolean(7, admin.isActivo());
    }
    
    private void setParametrosAdministrador(PreparedStatement ps, Administrador admin) throws SQLException{
        ps.setString(1, admin.getClaveMaestra());
    }
    
    private Administrador mapaAdministrador(ResultSet rs) throws SQLException{
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
}
