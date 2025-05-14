/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pucp.capanegocio.usuarios;

import com.pucp.capadominio.usuarios.Administrador;
import com.pucp.capanegocio.interfacesService.AdministradorService;
import com.pucp.da.usuarios.AdministradorCRUD;
import com.pucp.interfacesDAO.AdministradorDAO;
import java.util.ArrayList;

/**
 *
 * @author Axel
 */
public class AdministradorServiceImpl implements AdministradorService{
    private final AdministradorDAO administradorDAO;
    
    public AdministradorServiceImpl(){
        this.administradorDAO = new AdministradorCRUD();
    }

    @Override
    public void registrarAdministrador(Administrador administrador) throws Exception {
        //Usuario
        if(administrador.getCodigoPUCP() <= 0){
            throw new Exception("El codigo no puede ser un número negativo");
        }
        if(administrador.getContrasena() == null || administrador.getContrasena().trim().isEmpty()){
            throw new Exception("La contraseña no puede estar vacia");
        }
        if(administrador.getCorreo() == null || administrador.getCorreo().trim().isEmpty()){
            throw new Exception("El correo no puede estar vacio");
        }
        if(!"pucp.edu.pe".equals(administrador.getCorreo().split("@")[1])){
            throw new Exception("El correo debe ser: example@pucp.edu.pe");
        }
        if(administrador.getEstado() == null){
            throw new Exception("El estado no puede estar vacio");
        }
        if(administrador.getNombre() == null || administrador.getNombre().trim().isEmpty()){
            throw new Exception("El nombre no puede estar vacio");
        }
        if(administrador.getNombreUsuario() == null || administrador.getNombreUsuario().trim().isEmpty()){
            throw new Exception("El nombre de usuario no puede estar vacio");
        }
        //Admin
        if(administrador.getClaveMaestra() == null || administrador.getClaveMaestra().trim().isEmpty()){
            throw new Exception("La clave maestra no puede estar vacia");
        }
        
        administradorDAO.insertar(administrador);
    }

    @Override
    public void actualizarAdministrador(Administrador administrador) throws Exception {
        //Usuario
        if(administrador.getCodigoPUCP() <= 0){
            throw new Exception("El codigo no puede ser un número negativo");
        }
        if(administrador.getContrasena() == null || administrador.getContrasena().trim().isEmpty()){
            throw new Exception("La contraseña no puede estar vacia");
        }
        if(administrador.getCorreo() == null || administrador.getCorreo().trim().isEmpty()){
            throw new Exception("El correo no puede estar vacio");
        }
        if(!"pucp.edu.pe".equals(administrador.getCorreo().split("@")[1])){
            throw new Exception("El correo debe ser: example@pucp.edu.pe");
        }
        if(administrador.getEstado() == null){
            throw new Exception("El estado no puede estar vacio");
        }
        if(administrador.getNombre() == null || administrador.getNombre().trim().isEmpty()){
            throw new Exception("El nombre no puede estar vacio");
        }
        if(administrador.getNombreUsuario() == null || administrador.getNombreUsuario().trim().isEmpty()){
            throw new Exception("El nombre de usuario no puede estar vacio");
        }
        //Admin
        if(administrador.getClaveMaestra() == null || administrador.getClaveMaestra().trim().isEmpty()){
            throw new Exception("La clave maestra no puede estar vacia");
        }
        
        administradorDAO.actualizar(administrador);
    }

    @Override
    public void eliminarAdministrador(int idAdministrador) throws Exception {   
        Administrador administrador = administradorDAO.obtenerPorId(idAdministrador);
        if(administrador == null){
            throw new Exception("El administrador no existe");
        }
        administradorDAO.eliminar(idAdministrador);
    }

    @Override
    public Administrador obtenerAdministrador(int idAdministrador) throws Exception {
        Administrador administrador = administradorDAO.obtenerPorId(idAdministrador);
        if(administrador == null){
            throw new Exception("El administrador no existe");
        }
        return administrador;
    }

    @Override
    public ArrayList<Administrador> listarAdministrador() throws Exception {
        return administradorDAO.listarTodos();
    }
    
}
