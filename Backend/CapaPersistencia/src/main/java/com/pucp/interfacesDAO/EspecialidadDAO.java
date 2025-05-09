/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pucp.interfacesDAO;
import com.pucp.capadominio.categorias.Especialidad;
import java.util.ArrayList;

/**
 *
 * @author SEBASTIAN
 */
public interface EspecialidadDAO {
    
    void insertar(Especialidad especialidad);
    ArrayList<Especialidad> listarTodos();
    Especialidad obtenerPorId(int id);
    void actualizar(Especialidad especialidad);
    void eliminar(int id);    
    
}
