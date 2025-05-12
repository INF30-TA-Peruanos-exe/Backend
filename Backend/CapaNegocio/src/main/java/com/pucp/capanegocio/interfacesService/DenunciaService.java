/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pucp.capanegocio.interfacesService;

import com.pucp.capadominio.denuncia.Denuncia;
import java.util.ArrayList;

/**
 *
 * @author Axel
 */
public interface DenunciaService {
    void registrarDenuncia(Denuncia denuncia) throws Exception;
    void actualizarDenuncia(Denuncia denuncia) throws Exception;
    void eliminarDenuncia(int idDenuncia) throws Exception;
    Denuncia obtenerDenuncia(int idDenuncia) throws Exception;
    ArrayList<Denuncia> listarDenuncia() throws Exception;
}
