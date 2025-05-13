/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pucp.capanegocio.test;

import com.pucp.capadominio.categorias.*;

import com.pucp.capadominio.publicacion.EstadoPublicacion;
import com.pucp.capadominio.publicacion.Publicacion;
import com.pucp.capadominio.usuarios.Usuario;
import com.pucp.capanegocio.publicaciones.PublicacionServiceImpl;
import com.pucp.interfacesDAO.PublicacionDAO;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author Axel
 */
public class PublicacionServiceImplTest {
    private PublicacionDAO publicacionDAO;
    private PublicacionServiceImpl publicacionService;

    @BeforeEach
    public void setUp() {
        publicacionDAO = mock(PublicacionDAO.class);
        publicacionService = new PublicacionServiceImpl();
    }

    private Publicacion crearPublicacionValida() {
        Publicacion p = new Publicacion();
        p.setIdPublicacion(1);
        p.setDescripcion("Una publicación de prueba.");
        p.setEstado(EstadoPublicacion.VISIBLE);
        //p.setFechaPublicacion(new Date());
        p.setRutaImagen("/images/prueba.jpg");
        p.setImagen(p.getRutaImagen()); //Esto no se validara
        
        p.setTitulo("Título de prueba");
        p.setUsuario(new Usuario());

        
        //Aca faltaria sacar cursos, especialidades y facultades.
        
        Curso curso = new Curso(25, "Prueba_curso", true); 
        Especialidad especialidad = new Especialidad(25, "Prueba_especialidad", true);
        Facultad facultad = new Facultad(25, "Prueba_facultad", true);

        p.agregarCurso(curso); // Simulando objetos curso
        p.agregarEspecialidad(especialidad);
        p.agregarFacultad(facultad);

        return p;
    }

    @Test
    public void testRegistrarPublicacion_Exitoso() throws Exception {
        Publicacion p = crearPublicacionValida();
        doNothing().when(publicacionDAO).insertar(p);
        assertDoesNotThrow(() -> publicacionService.registrarPublicacion(p));
    }

    @Test
    public void testRegistrarPublicacion_TituloVacio() {
        Publicacion p = crearPublicacionValida();
        p.setTitulo("");
        Exception ex = assertThrows(Exception.class, () -> publicacionService.registrarPublicacion(p));
        assertEquals("El titulo no puede estar vacio", ex.getMessage());
    }

    @Test
    public void testRegistrarPublicacion_RutaImagenInvalida() {
        Publicacion p = crearPublicacionValida();
        p.setRutaImagen("archivo.txt");
        Exception ex = assertThrows(Exception.class, () -> publicacionService.registrarPublicacion(p));
        assertEquals("La ruta debe apuntar a un archivo de imagen válido (.jpg, .png, etc.)", ex.getMessage());
    }

    @Test
    public void testActualizarPublicacion_NoExiste() {
        Publicacion p = crearPublicacionValida();
        when(publicacionDAO.obtenerPorId(p.getIdPublicacion())).thenReturn(null);
        Exception ex = assertThrows(Exception.class, () -> publicacionService.actualizarPublicacion(p));
        assertEquals("La publicacion no existe", ex.getMessage());
    }

    @Test
    public void testActualizarPublicacion_Exitoso() throws Exception {
        Publicacion p = crearPublicacionValida();
        when(publicacionDAO.obtenerPorId(p.getIdPublicacion())).thenReturn(p);
        doNothing().when(publicacionDAO).actualizar(p);
        assertDoesNotThrow(() -> publicacionService.actualizarPublicacion(p));
    }

    @Test
    public void testEliminarPublicacion_NoExiste() {
        when(publicacionDAO.obtenerPorId(1)).thenReturn(null);
        Exception ex = assertThrows(Exception.class, () -> publicacionService.eliminarPublicacion(1));
        assertEquals("La publicacion no existe", ex.getMessage());
    }

    @Test
    public void testEliminarPublicacion_Exitoso() throws Exception {
        Publicacion p = crearPublicacionValida();
        when(publicacionDAO.obtenerPorId(1)).thenReturn(p);
        doNothing().when(publicacionDAO).eliminar(1);
        assertDoesNotThrow(() -> publicacionService.eliminarPublicacion(1));
    }

    @Test
    public void testObtenerPublicacion_Exitoso() throws Exception {
        Publicacion p = crearPublicacionValida();
        when(publicacionDAO.obtenerPorId(1)).thenReturn(p);
        Publicacion resultado = publicacionService.obtenerPublicacion(1);
        assertNotNull(resultado);
        assertEquals("Título de prueba", resultado.getTitulo());
    }

    @Test
    public void testListarPublicaciones() throws Exception {
        ArrayList<Publicacion> lista = new ArrayList<>();
        lista.add(crearPublicacionValida());
        when(publicacionDAO.listarTodos()).thenReturn(lista);
        ArrayList<Publicacion> resultado = publicacionService.listarPublicacion();
        assertEquals(1, resultado.size());
    } 
}
