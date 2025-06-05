/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package com.pucp.webservice.publicacion;

import com.pucp.capadominio.publicacion.Publicacion;
import com.pucp.capanegocio.interfacesService.PublicacionService;
import com.pucp.capanegocio.publicaciones.PublicacionServiceImpl;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.ws.WebServiceException;
import java.util.ArrayList;


/**
 *
 * @author Axel
 */
@WebService(serviceName = "PublicacionWS", targetNamespace = "com.pucp.pucpqhatu")
public class PublicacionWS {

    private final PublicacionService publicacionService;
    
    public PublicacionWS(){
        publicacionService = new PublicacionServiceImpl();
    }

    @WebMethod(operationName = "crearPublicacion")
    public void crearPublicacion(@WebParam(name = "publicacion") Publicacion publicacion) {
        try{
            publicacionService.registrarPublicacion(publicacion);
        } catch (Exception ex) {
            throw new WebServiceException("Error al crear publicación: " + ex.getMessage());
        }
    }
    
    @WebMethod(operationName = "actualizarPublicacion")
    public void actualizarPublicacion(@WebParam(name = "publicacion") Publicacion publicacion){
        try{
            publicacionService.actualizarPublicacion(publicacion);
        } catch (Exception ex) {
            throw new WebServiceException("Error al actualizar publicación: " + ex.getMessage());
        }       
    }
    
    @WebMethod(operationName = "eliminarPublicacion")
    public void eliminarPublicacion(@WebParam(name = "idPublicacion") int idPublicacion) {
        try{
            publicacionService.eliminarPublicacion(idPublicacion);
        } catch (Exception ex) {
            throw new WebServiceException("Error al eliminar publicación: " + ex.getMessage());
        }
    }
    
    @WebMethod(operationName = "cambiarEstadoPublicacion")
    public void cambiarEstadoPublicacion(
            @WebParam(name = "idPublicacion") int idPublicacion,
            @WebParam(name = "estado") String estado
    ) {
        try{
            publicacionService.cambiarEstadoPublicacion(idPublicacion, estado);
        } catch (Exception ex) {
            throw new WebServiceException("Error al eliminar publicación: " + ex.getMessage());
        }
    }
    
    @WebMethod(operationName = "obtenerPublicacion")
    public Publicacion obtenerPublicacion(@WebParam(name = "idPublicacion") int idPublicacion){
        try{
            return publicacionService.obtenerPublicacion(idPublicacion);
        } catch (Exception ex) {
            throw new WebServiceException("Error al obtener publicación: " + ex.getMessage());
        }        
    }
    
    @WebMethod(operationName = "listarPublicacion")
    public ArrayList<Publicacion> listarPublicacion(){
        try{
            return publicacionService.listarPublicacion();
        } catch (Exception ex) {
            throw new WebServiceException("Error al listar publicaciones: " + ex.getMessage());
        }       
    }
    
    @WebMethod(operationName = "listarPorFacultad")
    public ArrayList<Publicacion> listarPorFacultad(@WebParam(name = "idFacultad") int idFacultad){
        try{
            return publicacionService.listarPorFacultad(idFacultad);
        } catch (Exception ex) {
            throw new WebServiceException("Error al listar publicaciones por facultad: " + ex.getMessage());
        }       
    }
    
    @WebMethod(operationName = "listarPorEspecialidad")
    public ArrayList<Publicacion> listarPorEspecialidad(@WebParam(name = "idEspecialidad") int idEspecialidad){
        try{
            return publicacionService.listarPorEspecialidad(idEspecialidad);
        } catch (Exception ex) {
            throw new WebServiceException("Error al listar publicaciones por especialidad: " + ex.getMessage());
        }       
    }
    
    @WebMethod(operationName = "listarPorCurso")
    public ArrayList<Publicacion> listarPorCurso(@WebParam(name = "idCurso") int idCurso){
        try{
            return publicacionService.listarPorCurso(idCurso);
        } catch (Exception ex) {
            throw new WebServiceException("Error al listar publicaciones por curso: " + ex.getMessage());
        }       
    }    
    @WebMethod(operationName = "listarFavoritos")
    public ArrayList<Publicacion> listarFavoritos(@WebParam(name = "idUsuario") int idUsuario){
        try{
            return publicacionService.listarFavoritos(idUsuario);
        } catch (Exception ex) {
            throw new WebServiceException("Error al listar publicaciones favoritas " + ex.getMessage());
        }       
    }
    @WebMethod(operationName = "agregarFavoritos")
    public void agregarFavorito(@WebParam(name = "idUsuario")int idUsuario,
            @WebParam(name = "idPublicacion")int idPublicacion){
        try{
            publicacionService.agregarFavorito(idUsuario, idPublicacion);
        }catch (Exception ex) {
            throw new WebServiceException("Error al agregar publicacion favorita" + ex.getMessage());
        }
    }
     @WebMethod(operationName = "eliminarFavoritos")
    public void eliminarFavorito(@WebParam(name = "idUsuario")int idUsuario,
            @WebParam(name = "idPublicacion")int idPublicacion){
        try{
            publicacionService.eliminarFavorito(idUsuario, idPublicacion);
        }catch (Exception ex) {
            throw new WebServiceException("Error al eliminar publicacion favorita" + ex.getMessage());
        }   
    }
    @WebMethod(operationName = "esFavorito")
    public boolean esFavorito(@WebParam(name = "idUsuario")int idUsuario,
            @WebParam(name = "idPublicacion")int idPublicacion){
        try{
            return publicacionService.esFavorito(idUsuario, idPublicacion);
        }catch (Exception ex) {
            throw new WebServiceException("Error si es publicacion favorita" + ex.getMessage());
        }
    }
    
    @WebMethod(operationName = "obtenerFechaPublicacionFormateada")
    public String obtenerFechaPublicacionFormateada(@WebParam(name = "idPublicacion") int idPublicacion) {
        try {
            return publicacionService.getFechaPublicacionString(idPublicacion);
        } catch (Exception ex) {
            return "Error: " + ex.getMessage();
        }
    }

    //Esto iria en la parte de usuario
//    @WebMethod(operationName = "marcarFavorito")
//    public void marcarFavorito(@WebParam(name = "idPublicacion") int idPublicacion) {
//        try{
//            publicacionService.eliminarPublicacion(idPublicacion);
    //Falta implementar una funcion que llame a Add -> actualice
//        } catch (Exception ex) {
//            throw new WebServiceException("Error al eliminar publicación: " + ex.getMessage());
//        }
//    }
    
}
