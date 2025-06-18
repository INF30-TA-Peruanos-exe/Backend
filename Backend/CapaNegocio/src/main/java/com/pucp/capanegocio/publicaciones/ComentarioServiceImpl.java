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
    private final ComentarioDAO comentarioDAO;
    
    public ComentarioServiceImpl(){
        this.comentarioDAO = new ComentarioCRUD();
    }

    @Override
    public void registrarComentario(Comentario comentario) throws Exception {
        if(comentario.getComentador()==null){
            throw new Exception("El comentario debe tener un comentador");
        }
        if(comentario.getContenido()==null || comentario.getContenido().trim().isEmpty()){
            throw new Exception("El contenido no puede estar vacio");
        }
        if(comentario.getFecha()==null){
            throw new Exception("La fecha no puede ser nula");
        }
        if(comentario.getPublicacion()==null){
            throw new Exception("El comentario debe tener una publicación de referencia");
        }
        if(comentario.getValoracion()<=0){
            throw new Exception("La valoración no puede ser un número negativo");
        }
        
        comentarioDAO.insertar(comentario);
    }

    @Override
    public void actualizarComentario(Comentario comentario) throws Exception {
        if(comentarioDAO.obtenerPorId(comentario.getIdComentario()) == null){
            throw new Exception("El comentario no existe");
        }
        
        if(comentario.getComentador()==null){
            throw new Exception("El comentario debe tener un comentador");
        }
        if(comentario.getContenido()==null || comentario.getContenido().trim().isEmpty()){
            throw new Exception("El contenido no puede estar vacio");
        }
        if(comentario.getFecha()==null){
            throw new Exception("La fecha no puede ser nula");
        }
        if(comentario.getPublicacion()==null){
            throw new Exception("El comentario debe tener una publicación de referencia");
        }
        if(comentario.getValoracion()<=0){
            throw new Exception("La valoración no puede ser un número negativo");
        }
        
        comentarioDAO.actualizar(comentario);
    }

    @Override
    public void eliminarComentario(int idComentario) throws Exception { 
        Comentario comentario = comentarioDAO.obtenerPorId(idComentario);
        if(comentario == null){
            throw new Exception("El comentario no existe");
        }
        comentarioDAO.eliminar(idComentario);
    }

    @Override
    public Comentario obtenerComentario(int idComentario) throws Exception {
        Comentario comentario = comentarioDAO.obtenerPorId(idComentario);
        if(comentario == null){
            throw new Exception("El comentario no existe");
        }
        return comentario;    
    }

    @Override
    public ArrayList<Comentario> listarComentario() throws Exception {
        return comentarioDAO.listarTodos();
    }
    
}
