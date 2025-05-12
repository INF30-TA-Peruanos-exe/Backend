/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pucp.capanegocio.publicaciones;

import com.pucp.capadominio.publicacion.Publicacion;
import com.pucp.capanegocio.interfacesService.PublicacionService;
import com.pucp.da.publicaciones.PublicacionCRUD;
import com.pucp.interfacesDAO.PublicacionDAO;
import java.util.ArrayList;

/**
 *
 * @author Axel
 */
public class PublicacionServiceImpl implements PublicacionService{
    private final PublicacionDAO PublicacionDAO;
    
    public PublicacionServiceImpl(){
        this.PublicacionDAO = new PublicacionCRUD();
    }

    @Override
    public void registrarPublicacion(Publicacion publicacion) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actualizarPublicacion(Publicacion publicacion) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminarPublicacion(int idPublicacion) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Publicacion obtenerPublicacion(int idPublicacion) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Publicacion> listarPublicacion() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
