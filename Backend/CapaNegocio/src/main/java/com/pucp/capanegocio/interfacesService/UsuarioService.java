/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pucp.capanegocio.interfacesService;

import com.pucp.capadominio.usuarios.Usuario;
import java.util.ArrayList;

/**
 *
 * @author Axel
 */
public interface UsuarioService {
    void registrarUsuario(Usuario usuario) throws Exception;
    void actualizarUsuario(Usuario usuario) throws Exception;
    void eliminarUsuario(int idUsuario) throws Exception;
    Usuario obtenerUsuario(int idUsuario) throws Exception;
    ArrayList<Usuario> listarUsuario() throws Exception;
}
