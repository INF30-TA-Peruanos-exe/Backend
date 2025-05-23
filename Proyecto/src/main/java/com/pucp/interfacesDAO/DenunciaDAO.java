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
public interface DenunciaDAO {
    
    void insertar(Denuncia denuncia);
    ArrayList<Denuncia> listarTodos();
    Denuncia obtenerPorId(int id);
    void actualizar(Denuncia denuncia);
    void eliminar(int id);
    
}
