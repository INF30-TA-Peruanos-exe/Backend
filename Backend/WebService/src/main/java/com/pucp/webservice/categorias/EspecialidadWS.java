/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package com.pucp.webservice.categorias;

import com.pucp.capadominio.categorias.Especialidad;
import com.pucp.capanegocio.categorias.EspecialidadServiceImpl;
import com.pucp.capanegocio.interfacesService.EspecialidadService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.ws.WebServiceException;
import java.util.ArrayList;

/**
 *
 * @author Axel
 */
@WebService(serviceName = "EspecialidadWS", targetNamespace = "com.pucp.pucpqhatu")
public class EspecialidadWS {
    
    private final EspecialidadService especialidadService;
    
    public EspecialidadWS(){
        especialidadService = new EspecialidadServiceImpl();
    }
    
    @WebMethod(operationName = "registrarEspecialidad")
    public void registrarEspecialidad(@WebParam(name = "especialidad") Especialidad especialidad){
        try {
            especialidadService.registrarEspecialidad(especialidad);
        } catch (Exception ex) {
            throw new WebServiceException("Error al registrar especialidad: " + ex.getMessage());
        }
    }
    
    @WebMethod(operationName = "actualizarEspecialidad")
    public void actualizarEspecialidad(@WebParam(name = "especialidad") Especialidad especialidad){
        try {
            especialidadService.actualizarEspecialidad(especialidad);
        } catch (Exception ex) {
            throw new WebServiceException("Error al actualizar especialidad: " + ex.getMessage());
        }
    }
    
    @WebMethod(operationName = "eliminarEspecialidad")
    public void eliminarEspecialidad(@WebParam(name = "idEspecialidad") int idEspecialidad){
        try {
            especialidadService.eliminarEspecialidad(idEspecialidad);
        } catch (Exception ex) {
            throw new WebServiceException("Error al eliminar especialidad: " + ex.getMessage());
        }
    }
    
    @WebMethod(operationName = "obtenerEspecialidad")
    public Especialidad obtenerEspecialidad(@WebParam(name = "idEspecialidad") int idEspecialidad){
        try {
            return especialidadService.obtenerEspecialidad(idEspecialidad);
        } catch (Exception ex) {
            throw new WebServiceException("Error al obtener especialidad: " + ex.getMessage());
        }        
    }
    
    @WebMethod(operationName = "listarEspecialidad")
    public ArrayList<Especialidad> listarEspecialidad(){
        try {
            return especialidadService.listarEspecialidad();
        } catch (Exception ex) {
            throw new WebServiceException("Error al listar especialidades: " + ex.getMessage());
        }        
    }
}
