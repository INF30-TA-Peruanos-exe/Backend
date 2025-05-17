/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pucp.capanegocio.test;

import com.pucp.capadominio.publicacion.Comentario;
import com.pucp.capadominio.publicacion.Publicacion;
import com.pucp.capadominio.usuarios.Usuario;
import com.pucp.capanegocio.interfacesService.ComentarioService;
import com.pucp.capanegocio.publicaciones.ComentarioServiceImpl;
import com.pucp.da.publicaciones.PublicacionCRUD;
import com.pucp.da.usuarios.UsuarioCRUD;
import com.pucp.interfacesDAO.PublicacionDAO;
import com.pucp.interfacesDAO.UsuarioDAO;

import java.util.ArrayList;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Axel
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ComentarioServiceImplTest {
    private static ComentarioService comentarioService;
    private static UsuarioDAO usuarioDAO;
    private static PublicacionDAO publicacionDAO;
    private static int comentarioId;
    
    private static Usuario usuarioPrueba;
    private static Publicacion publicacionPrueba;
    
    @BeforeAll
    public static void setUp() {
        comentarioService = new ComentarioServiceImpl();
    }

    private Comentario crearComentarioPrueba() {
        Comentario comentario = new Comentario();
        usuarioPrueba = new Usuario();
        publicacionPrueba = new Publicacion();
        
        usuarioDAO = new UsuarioCRUD();
        publicacionDAO = new PublicacionCRUD();
        
        usuarioPrueba = usuarioDAO.obtenerPorId(1);
        publicacionPrueba = publicacionDAO.obtenerPorId(1);
        
        comentario.setComentador(usuarioPrueba);
        comentario.setContenido("Este es un comentario de prueba.");
        //comentario.setFecha(new Date());
        comentario.setPublicacion(publicacionPrueba);
        comentario.setActivo(true);
        comentario.setValoracion(5);
        return comentario;
    }

    @Test
    @Order(1)
    void registrarComentario() throws Exception {
        Comentario comentario = crearComentarioPrueba();
        comentarioService.registrarComentario(comentario);

        ArrayList<Comentario> comentarios = comentarioService.listarComentario();
        assertNotNull(comentarios);
        assertFalse(comentarios.isEmpty());

        Comentario registrado = comentarios.get(comentarios.size() - 1);
        comentarioId = registrado.getIdComentario();
        assertEquals("Este es un comentario de prueba.", registrado.getContenido());
    }

    @Test
    @Order(2)
    void obtenerComentario() throws Exception {
        Comentario comentario = comentarioService.obtenerComentario(comentarioId);
        assertNotNull(comentario);
        assertEquals("Este es un comentario de prueba.", comentario.getContenido());
    }

    @Test
    @Order(3)
    void actualizarComentario() throws Exception {
        Comentario comentario = comentarioService.obtenerComentario(comentarioId);
        comentario.setContenido("Comentario actualizado");
        comentarioService.actualizarComentario(comentario);

        Comentario actualizado = comentarioService.obtenerComentario(comentarioId);
        assertEquals("Comentario actualizado", actualizado.getContenido());
    }

    @Test
    @Order(4)
    void eliminarComentario() throws Exception {
        comentarioService.eliminarComentario(comentarioId);
        Comentario eliminado = comentarioService.obtenerComentario(comentarioId);

        assertNotNull(eliminado);
        // Si solo se marca como inactivo, valida según sea necesario
        // assertFalse(eliminado.isActivo()); // Si tienes ese atributo
    }

    @Test
    @Order(5)
    void listarComentarios() throws Exception {
        ArrayList<Comentario> comentarios = comentarioService.listarComentario();
        assertNotNull(comentarios);
        assertTrue(comentarios.isEmpty());
    }    
}
