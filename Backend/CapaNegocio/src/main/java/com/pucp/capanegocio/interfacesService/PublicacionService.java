/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pucp.capanegocio.interfacesService;

import com.pucp.capadominio.publicacion.Publicacion;
import java.util.ArrayList;

/**
 *
 * @author Axel
 */
public interface PublicacionService {
    void registrarPublicacion(Publicacion publicacion) throws Exception;
    void actualizarPublicacion(Publicacion publicacion) throws Exception;
    void eliminarPublicacion(int idPublicacion) throws Exception;
    Publicacion obtenerPublicacion(int idPublicacion) throws Exception;
    ArrayList<Publicacion> listarPublicacion() throws Exception;
    
    ArrayList<Publicacion> listarPorFacultad(int idFacultad) throws Exception;
    ArrayList<Publicacion> listarPorEspecialidad(int idEspecialidad) throws Exception;
    ArrayList<Publicacion> listarPorCurso(int idCurso) throws Exception;
}
