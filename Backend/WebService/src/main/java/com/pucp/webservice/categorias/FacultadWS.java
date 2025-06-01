/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package com.pucp.webservice.categorias;

import com.pucp.capadominio.categorias.Facultad;
import com.pucp.capanegocio.categorias.FacultadServiceImpl;
import com.pucp.capanegocio.interfacesService.FacultadService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.ws.WebServiceException;
import java.util.ArrayList;

/**
 *
 * @author Axel
 */
@WebService(serviceName = "FacultadWS", targetNamespace = "com.pucp.pucpqhatu")
public class FacultadWS {
    
    private final FacultadService facultadService;
    
    public FacultadWS(){
        facultadService = new FacultadServiceImpl();
    }
    
    @WebMethod(operationName = "registrarFacultad")
    public void registrarFacultad(@WebParam(name = "facultad") Facultad facultad){
        try {
            facultadService.registrarFacultad(facultad);
        } catch (Exception ex) {
            throw new WebServiceException("Error al registrar facultad: " + ex.getMessage());
        }
    }
    
    @WebMethod(operationName = "actualizarFacultad")
    public void actualizarFacultad(@WebParam(name = "facultad") Facultad facultad){
        try {
            facultadService.actualizarFacultad(facultad);
        } catch (Exception ex) {
            throw new WebServiceException("Error al actualizar facultad: " + ex.getMessage());
        }
    }
    
    @WebMethod(operationName = "eliminarFacultad")
    public void eliminarFacultad(@WebParam(name = "idFacultad") int idFacultad){
        try {
            facultadService.eliminarFacultad(idFacultad);
        } catch (Exception ex) {
            throw new WebServiceException("Error al eliminar facultad: " + ex.getMessage());
        }
    }
    
    @WebMethod(operationName = "obtenerFacultad")
    public Facultad obtenerFacultad(@WebParam(name = "idFacultad") int idFacultad){
        try {
            return facultadService.obtenerFacultad(idFacultad);
        } catch (Exception ex) {
            throw new WebServiceException("Error al obtener facultad: " + ex.getMessage());
        }        
    }
    
    @WebMethod(operationName = "listarFacultad")
    public ArrayList<Facultad> listarFacultad(){
        try {
            return facultadService.listarFacultad();
        } catch (Exception ex) {
            throw new WebServiceException("Error al listar facultades: " + ex.getMessage());
        }        
    }
}
