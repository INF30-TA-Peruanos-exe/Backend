/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pucp.interfacesDAO;



import com.pucp.capadominio.usuarios.Administrador;
import java.util.ArrayList;

/**
 *
 * @author SEBASTIAN
 */
public interface AdministradorDAO {
    
    void insertar(Administrador administrador);
    ArrayList<Administrador> listarTodos();
    Administrador obtenerPorId(int id);
    void actualizar(Administrador administrador);
    void eliminar(int id);
    
}
