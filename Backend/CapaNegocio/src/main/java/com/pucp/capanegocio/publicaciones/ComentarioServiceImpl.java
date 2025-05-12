/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pucp.capanegocio.publicaciones;

import com.pucp.capadominio.publicacion.Comentario;
import com.pucp.capanegocio.interfacesService.ComentarioService;
import com.pucp.da.publicaciones.ComentarioCRUD;
import com.pucp.interfacesDAO.ComentarioDAO;
import java.util.ArrayList;

/**
 *
 * @author Axel
 */
public class ComentarioServiceImpl implements ComentarioService{
    private final ComentarioDAO ComentarioDAO;
    
    public ComentarioServiceImpl(){
        this.ComentarioDAO = new ComentarioCRUD();
    }

    @Override
    public void registrarComentario(Comentario comentario) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actualizarComentario(Comentario comentario) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminarComentario(int idComentario) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Comentario obtenerComentario(int idComentario) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Comentario> listarComentario() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
