/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pucp.interfacesDAO;


import com.pucp.capadominio.usuarios.Usuario;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author SEBASTIAN
 */
public interface UsuarioDAO extends BaseDAO<Usuario>{
    Usuario obtenerPorCorreoYContrasena(String correo, String contrasena) throws SQLException;
    ArrayList<Usuario> listarUsuariosFavoritos(int idPublicacion); 
}
