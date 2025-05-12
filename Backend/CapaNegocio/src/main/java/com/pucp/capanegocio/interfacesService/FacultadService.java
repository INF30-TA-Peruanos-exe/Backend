/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pucp.capanegocio.interfacesService;

import com.pucp.capadominio.categorias.Facultad;
import java.util.ArrayList;

/**
 *
 * @author Axel
 */
public interface FacultadService {
    void registrarFacultad(Facultad facultad) throws Exception;
    void actualizarFacultad(Facultad facultad) throws Exception;
    void eliminarFacultad(int idFacultad) throws Exception;
    Facultad obtenerFacultad(int idFacultad) throws Exception;
    ArrayList<Facultad> listarFacultad() throws Exception;
    
}
