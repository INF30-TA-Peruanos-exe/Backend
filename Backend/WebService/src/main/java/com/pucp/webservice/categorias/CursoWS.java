/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package com.pucp.webservice.categorias;

import com.pucp.capadominio.categorias.Curso;
import com.pucp.capanegocio.categorias.CursoServiceImpl;
import com.pucp.capanegocio.interfacesService.CursoService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.ws.WebServiceException;
import java.util.ArrayList;

/**
 *
 * @author Axel
 */
@WebService(serviceName = "CursoWS", targetNamespace = "com.pucp.pucpqhatu")
public class CursoWS {
    
    private final CursoService cursoService;
    
    public CursoWS(){
        cursoService = new CursoServiceImpl();
    }
    
    @WebMethod(operationName = "registrarCurso")
    public void registrarCurso(@WebParam(name = "curso") Curso curso){
        try {
            cursoService.registrarCurso(curso);
        } catch (Exception ex) {
            throw new WebServiceException("Error al registrar curso: " + ex.getMessage());
        }
    }
    
    @WebMethod(operationName = "actualizarCurso")
    public void actualizarCurso(@WebParam(name = "curso") Curso curso){
        try {
            cursoService.actualizarCurso(curso);
        } catch (Exception ex) {
            throw new WebServiceException("Error al actualizar curso: " + ex.getMessage());
        }
    }
    
    @WebMethod(operationName = "eliminarCurso")
    public void eliminarCurso(@WebParam(name = "idCurso") int idCurso){
        try {
            cursoService.eliminarCurso(idCurso);
        } catch (Exception ex) {
            throw new WebServiceException("Error al eliminar curso: " + ex.getMessage());
        }
    }
    
    @WebMethod(operationName = "obtenerCurso")
    public Curso obtenerCurso(@WebParam(name = "idCurso") int idCurso){
        try {
            return cursoService.obtenerCurso(idCurso);
        } catch (Exception ex) {
            throw new WebServiceException("Error al obtener curso: " + ex.getMessage());
        }        
    }
    
    @WebMethod(operationName = "listarCurso")
    public ArrayList<Curso> listarCurso(){
        try {
            return cursoService.listarCurso();
        } catch (Exception ex) {
            throw new WebServiceException("Error al listar cursos: " + ex.getMessage());
        }        
    }
    
}
