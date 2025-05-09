/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pucp.da.usuarios;

import com.pucp.config.DBManager;
import com.pucp.interfacesDAO.UsuarioDAO;
import com.pucp.modelo.usuarios.Usuario;
import com.pucp.modelo.usuarios.EstadoUsuario;
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
public class UsuarioCRUD implements UsuarioDAO{

    @Override
    public void insertar(Usuario usuario) {
        String query = "INSERT INTO Usuario(codigo_PUCP,nombreUsuario,contrasena,nombre,correo,estado,activo)"
                + "values(?,?,?,?,?,?,?)";
        try(Connection con = DBManager.getConnection();
            PreparedStatement ps = con.prepareStatement(query);) {        
            setParametrosUsuario(ps, usuario);
            ps.executeUpdate(); 
            //Traer el ultimo ID autogenerado
            try(Statement st = con.createStatement();
                ResultSet rskeys = st.executeQuery("select @@last_insert_id");){            
                if(rskeys.next()){
                    usuario.setIdUsuario(rskeys.getInt(1));
                }
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
    }

    @Override
    public ArrayList<Usuario> listarTodos() {
        
        ArrayList<Usuario> usuarios = new ArrayList<>();
        String query = "SELECT id_usuario,codigo_PUCP,nombreUsuario,contrasena,nombre,correo,estado,activo FROM Usuario WHERE activo = 1";
        try(Connection con  =DBManager.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);){
            while(rs.next()){
                Usuario usuario = mapaUsuario(rs);
                usuarios.add(usuario);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return usuarios;
        
    }

    @Override
    public Usuario obtenerPorId(int id) {
        String query = "SELECT id_usuario,codigo_PUCP,nombreUsuario,contrasena,nombre,correo,estado,activo FROM Usuario WHERE id_usuario = ?";
        try (Connection conn = DBManager.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapaUsuario(rs);
                }
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return null;
        
    }

    @Override
    public void actualizar(Usuario usuario) {
        String query = "UPDATE Usuario SET codigo_PUCP = ?, nombreUsuario = ?, contrasena = ?, nombre = ?, correo = ?, estado = ? ,activo = ? WHERE id_usuario = ?";
        try(Connection con = DBManager.getConnection();
            PreparedStatement ps = con.prepareStatement(query);){
            setParametrosUsuario(ps,usuario);
            ps.setInt(8,usuario.getIdUsuario());
            ps.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
    }

    @Override
    public void eliminar(int id) {
        //Eliminar lógico
        String query = "UPDATE Usuario SET activo = 0 WHERE id_usuario = ?";
        try (Connection conn = DBManager.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(query)) {            
             ps.setInt(1, id);
             ps.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
    }
    
    private void setParametrosUsuario(PreparedStatement ps, Usuario usu) throws SQLException{
        ps.setInt(1, usu.getCodigoPUCP());
        ps.setString(2, usu.getNombreUsuario());
        ps.setString(3, usu.getContrasena());
        ps.setString(4, usu.getNombre());
        ps.setString(5, usu.getCorreo());
        ps.setString(6, usu.getEstado().name());
        ps.setBoolean(7, usu.isActivo());
    }
    
    private Usuario mapaUsuario(ResultSet rs) throws SQLException{
        Usuario usu = new Usuario();
        usu.setIdUsuario(rs.getInt("id_usuario"));
        usu.setCodigoPUCP(rs.getInt("codigo_PUCP"));
        usu.setNombreUsuario(rs.getString("nombreUsuario"));
        usu.setContrasena(rs.getString("contrasena"));
        usu.setNombre(rs.getString("nombre"));
        usu.setCorreo(rs.getString("correo"));
        usu.setEstado(EstadoUsuario.valueOf(rs.getString("estado")));
        usu.setActivo(rs.getBoolean("activo"));
        return usu;
    }
    
    
    
}
