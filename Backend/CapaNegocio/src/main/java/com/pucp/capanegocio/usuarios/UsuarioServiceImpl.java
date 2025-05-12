/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pucp.capanegocio.usuarios;

import com.pucp.capadominio.usuarios.Usuario;
import com.pucp.capanegocio.interfacesService.UsuarioService;
import com.pucp.da.usuarios.UsuarioCRUD;
import com.pucp.interfacesDAO.UsuarioDAO;
import java.util.ArrayList;

/**
 *
 * @author Axel
 */
public class UsuarioServiceImpl implements UsuarioService{
    private final UsuarioDAO UsuarioDAO;
    
    public UsuarioServiceImpl(){
        this.UsuarioDAO = new UsuarioCRUD();
    }

    @Override
    public void registrarUsuario(Usuario usuario) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actualizarUsuario(Usuario usuario) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminarUsuario(int idUsuario) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Usuario obtenerUsuario(int idUsuario) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Usuario> listarUsuario() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
