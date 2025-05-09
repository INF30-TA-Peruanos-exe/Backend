/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pucp.interfacesDAO;

import java.util.ArrayList;

/**
 *
 * @author SEBASTIAN
 */
public interface BaseDAO<T> {
    void insertar(T entidad);
    ArrayList<T> listarTodos();
    T obtenerPorId(int id);
    void actualizar(T entidad);
    void eliminar(int id); 
}
