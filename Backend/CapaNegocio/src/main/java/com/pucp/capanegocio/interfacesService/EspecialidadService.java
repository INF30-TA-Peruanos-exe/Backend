/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pucp.capanegocio.interfacesService;

import com.pucp.capadominio.categorias.Especialidad;
import java.util.ArrayList;

/**
 *
 * @author Axel
 */
public interface EspecialidadService {
    void registrarEspecialidad(Especialidad especialidad) throws Exception;
    void actualizarEspecialidad(Especialidad especialidad) throws Exception;
    void eliminarEspecialidad(int idEspecialidad) throws Exception;
    Especialidad obtenerEspecialidad(int idEspecialidad) throws Exception;
    ArrayList<Especialidad> listarEspecialidad() throws Exception;
}
