/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package com.pucp.webservice.notificacion;

import com.pucp.capadominio.notificacion.Notificacion;
import com.pucp.capanegocio.interfacesService.NotificacionService;
import com.pucp.capanegocio.notificaciones.NotificacionServiceImpl;
import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.xml.ws.WebServiceException;
import java.util.ArrayList;

/**
 *
 * @author SEBASTIAN
 */
@WebService(serviceName = "NotificacionWS", targetNamespace = "com.pucp.pucpqhatu")
public class NotificacionWS {
    
    private final NotificacionService notificacionService;
    
    public NotificacionWS(){
        notificacionService = new NotificacionServiceImpl();
    }
    
    @WebMethod(operationName = "registrarNotificacion")
    public void registrarNotificacion(@WebParam(name = "denuncia") Notificacion notificacion){
        try {
            notificacionService.registrarNotificacion(notificacion);
        } catch (Exception ex) {
            throw new WebServiceException("Error al registrar notificacion: " + ex.getMessage());
        }
    }
    
    @WebMethod(operationName = "actualizarNotificacion")
    public void actualizarNotificacion(@WebParam(name = "denuncia") Notificacion notificacion){
        try {
            notificacionService.actualizarNotificacion(notificacion);
        } catch (Exception ex) {
            throw new WebServiceException("Error al actualizar notificacion: " + ex.getMessage());
        }
    }
    
    @WebMethod(operationName = "eliminarDenuncia")
    public void eliminarNotificacion(@WebParam(name = "idNotificacion") int idNotificacion){
        try {
            notificacionService.eliminarNotificacion(idNotificacion);
        } catch (Exception ex) {
            throw new WebServiceException("Error al eliminar notificacion: " + ex.getMessage());
        }
    }
    
    @WebMethod(operationName = "obtenerNotificacion")
    public Notificacion obtenerNotificacion(@WebParam(name = "idNotificacion") int idNotificacion){
        try {
            return notificacionService.obtenerNotificacion(idNotificacion);
        } catch (Exception ex) {
            throw new WebServiceException("Error al obtener notificacion: " + ex.getMessage());
        }        
    }
    
    @WebMethod(operationName = "listarNotificacion")
    public ArrayList<Notificacion> listarNotificacion(){
        try {
            return notificacionService.listarNotificacion();
        } catch (Exception ex) {
            throw new WebServiceException("Error al listar notificaciones: " + ex.getMessage());
        }        
    }
    
}
