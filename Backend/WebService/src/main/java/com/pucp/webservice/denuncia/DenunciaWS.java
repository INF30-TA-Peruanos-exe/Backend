/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package com.pucp.webservice.denuncia;

import com.pucp.capadominio.denuncia.Denuncia;
import com.pucp.capanegocio.denuncias.DenunciaServiceImpl;
import com.pucp.capanegocio.interfacesService.DenunciaService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.ws.WebServiceException;
import java.util.ArrayList;

/**
 *
 * @author Axel
 */
@WebService(serviceName = "DenunciaWS", targetNamespace = "com.pucp.pucpqhatu")
public class DenunciaWS {

    private final DenunciaService denunciaService;
    
    public DenunciaWS(){
        denunciaService = new DenunciaServiceImpl();
    }
    
    @WebMethod(operationName = "registrarDenuncia")
    public void registrarDenuncia(@WebParam(name = "denuncia") Denuncia denuncia){
        try {
            denunciaService.registrarDenuncia(denuncia);
        } catch (Exception ex) {
            throw new WebServiceException("Error al registrar denuncia: " + ex.getMessage());
        }
    }
    
    @WebMethod(operationName = "actualizarDenuncia")
    public void actualizarDenuncia(@WebParam(name = "denuncia") Denuncia denuncia){
        try {
            denunciaService.actualizarDenuncia(denuncia);
        } catch (Exception ex) {
            throw new WebServiceException("Error al actualizar facultad: " + ex.getMessage());
        }
    }
    
    @WebMethod(operationName = "eliminarDenuncia")
    public void eliminarDenuncia(@WebParam(name = "idDenuncia") int idDenuncia){
        try {
            denunciaService.eliminarDenuncia(idDenuncia);
        } catch (Exception ex) {
            throw new WebServiceException("Error al eliminar denuncia: " + ex.getMessage());
        }
    }
    
    @WebMethod(operationName = "obtenerDenuncia")
    public Denuncia obtenerDenuncia(@WebParam(name = "idDenuncia") int idDenuncia){
        try {
            return denunciaService.obtenerDenuncia(idDenuncia);
        } catch (Exception ex) {
            throw new WebServiceException("Error al obtener denuncia: " + ex.getMessage());
        }        
    }
    
    @WebMethod(operationName = "listarDenuncia")
    public ArrayList<Denuncia> listarDenuncia(){
        try {
            return denunciaService.listarDenuncia();
        } catch (Exception ex) {
            throw new WebServiceException("Error al listar denuncias: " + ex.getMessage());
        }        
    }
}
