/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pucp.interfacesDAO;
import com.pucp.capadominio.categorias.Facultad;
import java.util.ArrayList;
/**
 *
 * @author SEBASTIAN
 */
public interface FacultadDAO {
    
    void insertar(Facultad facultad);
    ArrayList<Facultad> listarTodos();
    Facultad obtenerPorId(int id);
    void actualizar(Facultad facultad);
    void eliminar(int id);
    
}
