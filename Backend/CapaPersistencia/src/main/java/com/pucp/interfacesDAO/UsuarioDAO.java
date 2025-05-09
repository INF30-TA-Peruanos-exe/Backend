/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pucp.interfacesDAO;
import com.pucp.capadominio.usuarios.Usuario;
import java.util.ArrayList;
/**
 *
 * @author SEBASTIAN
 */
public interface UsuarioDAO {
    
    void insertar(Usuario usuario);
    ArrayList<Usuario> listarTodos();
    Usuario obtenerPorId(int id);
    void actualizar(Usuario usuario);
    void eliminar(int id);
    
}
