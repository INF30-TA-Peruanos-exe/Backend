/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package com.pucp.webservice.usuarios;

import com.pucp.capadominio.usuarios.Administrador;
import com.pucp.capanegocio.interfacesService.AdministradorService;
import com.pucp.capanegocio.usuarios.AdministradorServiceImpl;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.ws.WebServiceException;
import java.util.ArrayList;

/**
 *
 * @author Axel
 */
@WebService(serviceName = "AdministradorWS", targetNamespace = "com.pucp.pucpqhatu")
public class AdministradorWS {

    private final AdministradorService administradorService;
    
    public AdministradorWS(){
        administradorService = new AdministradorServiceImpl();
    }
    
    @WebMethod(operationName = "registrarAdministrador")
    public boolean registrarAdministrador(@WebParam(name = "administrador") Administrador administrador){
        try {
            administradorService.registrarAdministrador(administrador);
            return true;
        } catch (Exception ex) {
            throw new WebServiceException("Error al registrar administrador: " + ex.getMessage());
        }       
    }
    
    @WebMethod(operationName = "actualizarAdministrador")
    public void actualizarAdministrador(@WebParam(name = "usuario") Administrador administrador) {
        try {
            administradorService.actualizarAdministrador(administrador);
        } catch (Exception ex) {
            throw new WebServiceException("Error al actualizar administrador: " + ex.getMessage());
        }
    }
    
    @WebMethod(operationName = "eliminarAdministrador")
    public void eliminarAdministrador(@WebParam(name = "idAdministrador") int idAdministrador) {
        try {
            administradorService.eliminarAdministrador(idAdministrador);
        } catch (Exception ex) {
            throw new WebServiceException("Error al eliminar administrador: " + ex.getMessage());
        }
    }
    
    @WebMethod(operationName = "obtenerAdministrador")
    public Administrador obtenerAdministrador(@WebParam(name = "idAdministrador") int idAdministrador) {
        try {
            return administradorService.obtenerAdministrador(idAdministrador);
        } catch (Exception ex) {
            throw new WebServiceException("Error al obtener administrador: " + ex.getMessage());
        }
    }
    
    @WebMethod(operationName = "listarAdministrador")
    public ArrayList<Administrador> listarAdministrador() {
        try {
            return administradorService.listarAdministrador();
        } catch (Exception ex) {
            throw new WebServiceException("Error al listar administradores: " + ex.getMessage());
        }
    }
    
    @WebMethod(operationName = "obtenerAdministradorPorCorreoYContra")
    public Administrador obtenerAdministradorPorCorreoYContra(@WebParam(name = "clave") String clave) {
        try {
            return administradorService.obtenerAdministradorPorCorreoYContra(clave);
        } catch (Exception ex) {
            throw new WebServiceException("Error al obtener administrador: " + ex.getMessage());
        }
    }
}
