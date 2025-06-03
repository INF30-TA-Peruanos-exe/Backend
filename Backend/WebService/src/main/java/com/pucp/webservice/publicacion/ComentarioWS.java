/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package com.pucp.webservice.publicacion;

import com.pucp.capadominio.publicacion.Comentario;
import com.pucp.capanegocio.interfacesService.ComentarioService;
import com.pucp.capanegocio.publicaciones.ComentarioServiceImpl;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.ws.WebServiceException;
import java.util.ArrayList;

/**
 *
 * @author Axel
 */
@WebService(serviceName = "ComentarioWS", targetNamespace = "com.pucp.pucpqhatu")
public class ComentarioWS {

    private final ComentarioService comentarioService;
    
    public ComentarioWS(){
        comentarioService = new ComentarioServiceImpl();
    }
    
    @WebMethod(operationName = "registrarComentario")
    public void registrarComentario(@WebParam(name = "comentario") Comentario comentario){
        try {
            comentarioService.registrarComentario(comentario);
        } catch (Exception ex) {
            throw new WebServiceException("Error al registrar comentario: " + ex.getMessage());
        }       
    }
    
    @WebMethod(operationName = "actualizarComentario")
    public void actualizarComentario(@WebParam(name = "comentario") Comentario comentario){
        try {
            comentarioService.actualizarComentario(comentario);
        } catch (Exception ex) {
            throw new WebServiceException("Error al actualizar comentario: " + ex.getMessage());
        }
    }
    
    @WebMethod(operationName = "eliminarComentario")
    public void eliminarComentario(@WebParam(name = "idComentario") int idComentario){
        try {
            comentarioService.eliminarComentario(idComentario);
        } catch (Exception ex) {
            throw new WebServiceException("Error al eliminar comentario: " + ex.getMessage());
        }
    }
    
    @WebMethod(operationName = "obtenerComentario")
    public Comentario obtenerComentario(@WebParam(name = "idComentario") int idComentario){
        try {
            return comentarioService.obtenerComentario(idComentario);
        } catch (Exception ex) {
            throw new WebServiceException("Error al obtener comentario: " + ex.getMessage());
        }        
    }
    
    @WebMethod(operationName = "listarComentario")
    public ArrayList<Comentario> listarComentario(){
        try {
            return comentarioService.listarComentario();
        } catch (Exception ex) {
            throw new WebServiceException("Error al listar comentarios: " + ex.getMessage());
        }        
    }
    
}
