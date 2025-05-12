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
        
    }

    @Override
    public void actualizarAdministrador(Administrador administrador) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminarAdministrador(int idAdministrador) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Administrador obtenerAdministrador(int idAdministrador) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Administrador> listarAdministrador() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
