/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pucp.capanegocio.interfacesService;

import com.pucp.capadominio.publicacion.Comentario;
import java.util.ArrayList;

/**
 *
 * @author Axel
 */
public interface ComentarioService {
    void registrarComentario(Comentario comentario) throws Exception;
    void actualizarComentario(Comentario comentario) throws Exception;
    void eliminarComentario(int idComentario) throws Exception;
    Comentario obtenerComentario(int idComentario) throws Exception;
    ArrayList<Comentario> listarComentario() throws Exception;
}
