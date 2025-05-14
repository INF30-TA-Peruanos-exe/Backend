/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pucp.capanegocio.test;

import com.pucp.capadominio.categorias.Curso;
import com.pucp.capanegocio.categorias.CursoServiceImpl;
import com.pucp.capanegocio.interfacesService.CursoService;
import java.util.ArrayList;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author SEBASTIAN
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CursoServiceImplTest{
    private static CursoService cursoService;
    private static int cursoId;

    @BeforeAll
    public static void setUp() {
        cursoService = new CursoServiceImpl();
    }

    private Curso crearCursoPrueba() {
        Curso curso = new Curso();
        curso.setNombre("Curso de Pruebas");
        curso.setActivo(true);
        return curso;
    }

    @Test
    @Order(1)
    void registrarCurso() throws Exception {
        Curso curso = crearCursoPrueba();
        cursoService.registrarCurso(curso);

        ArrayList<Curso> lista = cursoService.listarCurso();
        assertNotNull(lista);
        assertFalse(lista.isEmpty());

        Curso registrado = null;
        for (Curso c : lista) {
            if (c.getNombre().equals(curso.getNombre())) {
                registrado = c;
                break;
            }
        }
        
        assertNotNull(registrado);
        cursoId = registrado.getIdCurso();
        assertEquals(curso.getNombre(), registrado.getNombre());
    }

    @Test
    @Order(2)
    void obtenerCurso() throws Exception {
        Curso curso = cursoService.obtenerCurso(cursoId);
        assertNotNull(curso);
        assertEquals("Curso de Pruebas", curso.getNombre());
    }

    @Test
    @Order(3)
    void actualizarCurso() throws Exception {
        Curso curso = cursoService.obtenerCurso(cursoId);
        curso.setNombre("Curso Modificado");
        cursoService.actualizarCurso(curso);

        Curso actualizado = cursoService.obtenerCurso(cursoId);
        assertEquals("Curso Modificado", actualizado.getNombre());
    }

    @Test
    @Order(4)
    void eliminarCurso() throws Exception {
        cursoService.eliminarCurso(cursoId);
        Curso cursoEliminado = cursoService.obtenerCurso(cursoId);
        
        assertNotNull(cursoEliminado);
        assertFalse(cursoEliminado.isActivo());
    }

    @Test
    @Order(5)
    void listarCursos() throws Exception {
        ArrayList<Curso> lista = cursoService.listarCurso();
        assertNotNull(lista);
    }       
}
