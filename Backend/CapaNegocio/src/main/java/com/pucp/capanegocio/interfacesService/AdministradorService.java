/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pucp.capanegocio.interfacesService;

import com.pucp.capadominio.usuarios.Administrador;
import java.util.ArrayList;

/**
 *
 * @author Axel
 */
public interface AdministradorService {
    void registrarAdministrador(Administrador admin) throws Exception;
    void actualizarAdministrador(Administrador admin) throws Exception;
    void eliminarAdministrador(int idAdmin) throws Exception;
    Administrador obtenerAdministrador(int idAdmin) throws Exception;
    ArrayList<Administrador> listarAdministrador() throws Exception;
}
