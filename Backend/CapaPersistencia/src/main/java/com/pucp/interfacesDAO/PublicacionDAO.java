/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pucp.interfacesDAO;



import com.pucp.capadominio.publicacion.Publicacion;
import java.util.ArrayList;

/**
 *
 * @author SEBASTIAN
 */
public interface PublicacionDAO extends BaseDAO<Publicacion>{
    
    ArrayList<Publicacion> listarporFacultad(String facultad);
    ArrayList<Publicacion> listarporEspecialidad(String especialidad);
    ArrayList<Publicacion> listarporCurso(String curso);
    
}
