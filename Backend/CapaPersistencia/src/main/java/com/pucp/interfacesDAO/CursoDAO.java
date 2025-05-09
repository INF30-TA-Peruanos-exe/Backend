/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pucp.interfacesDAO;


import com.pucp.capadominio.categorias.Curso;
import java.util.ArrayList;
/**
 *
 * @author SEBASTIAN
 */
public interface CursoDAO {
    
    void insertar(Curso curso);
    ArrayList<Curso> listarTodos();
    Curso obtenerPorId(int id);
    void actualizar(Curso curso);
    void eliminar(int id);
    
}
