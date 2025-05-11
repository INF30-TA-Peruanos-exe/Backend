/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pucp.capanegocio.interfacesService;

import com.pucp.capadominio.categorias.Curso;
import java.util.ArrayList;

/**
 *
 * @author SEBASTIAN
 */
public interface CursoService {
    void registrarCurso(Curso curso) throws Exception;
    void actualizarCurso(Curso curso) throws Exception;
    void eliminarCurso(int idCurso) throws Exception;
    Curso obtenerCurso(int idCurso) throws Exception;
    ArrayList<Curso> listarCurso() throws Exception;
}
