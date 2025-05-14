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
    private final UsuarioDAO usuarioDAO;
    
    public UsuarioServiceImpl(){
        this.usuarioDAO = new UsuarioCRUD();
    }

    @Override
    public void registrarUsuario(Usuario usuario) throws Exception {
        if(usuario.getCodigoPUCP() <= 0){
            throw new Exception("El codigo no puede ser un número negativo");
        }
        if(usuario.getContrasena() == null || usuario.getContrasena().trim().isEmpty()){
            throw new Exception("La contraseña no puede estar vacia");
        }
        if(usuario.getCorreo() == null || usuario.getCorreo().trim().isEmpty()){
            throw new Exception("El correo no puede estar vacio");
        }
        if(!"pucp.edu.pe".equals(usuario.getCorreo().split("@")[1])){
            throw new Exception("El correo debe ser: example@pucp.edu.pe");
        }
        if(usuario.getEstado() == null){
            throw new Exception("El estado no puede estar vacio");
        }
        if(usuario.getNombre() == null || usuario.getNombre().trim().isEmpty()){
            throw new Exception("El nombre no puede estar vacio");
        }
        if(usuario.getNombreUsuario() == null || usuario.getNombreUsuario().trim().isEmpty()){
            throw new Exception("El nombre de usuario no puede estar vacio");
        }
        
        usuarioDAO.insertar(usuario);
    }

    @Override
    public void actualizarUsuario(Usuario usuario) throws Exception {
        if(usuarioDAO.obtenerPorId(usuario.getIdUsuario())==null){
            throw new Exception("El usuario no existe");
        }
        
        if(usuario.getCodigoPUCP() <= 0){
            throw new Exception("El codigo no puede ser un número negativo");
        }
        if(usuario.getContrasena() == null || usuario.getContrasena().trim().isEmpty()){
            throw new Exception("La contraseña no puede estar vacia");
        }
        if(usuario.getCorreo() == null || usuario.getCorreo().trim().isEmpty()){
            throw new Exception("El correo no puede estar vacio");
        }
        if(!"pucp.edu.pe".equals(usuario.getCorreo().split("@")[1])){
            throw new Exception("El correo debe ser: example@pucp.edu.pe");
        }
        if(usuario.getEstado() == null){
            throw new Exception("El estado no puede estar vacio");
        }
        if(usuario.getNombre() == null || usuario.getNombre().trim().isEmpty()){
            throw new Exception("El nombre no puede estar vacio");
        }
        if(usuario.getNombreUsuario() == null || usuario.getNombreUsuario().trim().isEmpty()){
            throw new Exception("El nombre de usuario no puede estar vacio");
        }
        
        usuarioDAO.actualizar(usuario);
    }

    @Override
    public void eliminarUsuario(int idUsuario) throws Exception {
        Usuario usuario = usuarioDAO.obtenerPorId(idUsuario);
        if(usuario == null){
            throw new Exception("El usuario no existe");
        }
        usuarioDAO.eliminar(idUsuario);
    }

    @Override
    public Usuario obtenerUsuario(int idUsuario) throws Exception {
        Usuario usuario = usuarioDAO.obtenerPorId(idUsuario);
        if(usuario == null){
            throw new Exception("El usuario no existe");
        }
        return usuario;
    }

    @Override
    public ArrayList<Usuario> listarUsuario() throws Exception {
        return usuarioDAO.listarTodos();
    }
    
}
