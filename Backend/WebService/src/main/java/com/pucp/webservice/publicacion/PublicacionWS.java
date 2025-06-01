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


/**
 *
 * @author Axel
 */
@WebService(serviceName = "PublicacionWS")
public class PublicacionWS {

    private PublicacionService publicacionService;
    
    public PublicacionWS(){
        publicacionService = new PublicacionServiceImpl();
    }
    
    /**
     * This is a sample web service operation
     * @param publicacion
     */
    @WebMethod(operationName = "crearPublicacion")
    public void crearPublicacion(Publicacion publicacion) {
        try{
            publicacionService.registrarPublicacion(publicacion);
        } catch (Exception ex) {
            throw new WebServiceException("Error al crear publicación: " + ex.getMessage());
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
