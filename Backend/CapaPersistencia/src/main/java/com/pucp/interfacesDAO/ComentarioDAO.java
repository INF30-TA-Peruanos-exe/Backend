/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pucp.interfacesDAO;
import com.pucp.capadominio.publicacion.Comentario;
import java.util.ArrayList;

/**
 *
 * @author SEBASTIAN
 */
public interface ComentarioDAO {
    
    void insertar(Comentario comentario);
    ArrayList<Comentario> listarTodos();
    Comentario obtenerPorId(int id);
    void actualizar(Comentario comentario);
    void eliminar(int id);
}
