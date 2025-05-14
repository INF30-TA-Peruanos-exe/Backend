/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pucp.capanegocio.test;

import com.pucp.capadominio.notificacion.*;
import com.pucp.capadominio.publicacion.Publicacion;
import com.pucp.capadominio.usuarios.Usuario;
import com.pucp.capanegocio.interfacesService.NotificacionService;
import com.pucp.capanegocio.notificaciones.NotificacionServiceImpl;
import com.pucp.da.publicaciones.PublicacionCRUD;
import com.pucp.da.usuarios.UsuarioCRUD;
import com.pucp.interfacesDAO.*;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

/**
 *
 * @author Axel
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class NotificacionServiceImplTest {
    private static NotificacionService notificacionService;
    private static int notificacionId;
    private static UsuarioDAO usuarioDAO;
    private static PublicacionDAO publicacionDAO;
    
    private static Publicacion autor;
    private static Usuario notificador;
    
    @BeforeAll
    public static void setUp() {
        notificacionService = new NotificacionServiceImpl();
    }

    private static Notificacion crearNotificacionPrueba() {

        usuarioDAO = new UsuarioCRUD();
        publicacionDAO = new PublicacionCRUD();
        //No hay ninguna publicacion
        autor = publicacionDAO.obtenerPorId(1);
        notificador = usuarioDAO.obtenerPorId(1);


        Notificacion notificacion = new Notificacion();
        notificacion.setAutor(autor);
        notificacion.setNotificador(notificador);
        notificacion.setTipoNotificacion(TipoNotificacion.COMENTADA);
        notificacion.setMensaje("Notificación de prueba");
        notificacion.setCantidad(3);
        notificacion.setActivo(true);
        //notificacion.setFecha(LocalDateTime.now());

        return notificacion;
    }

    @Test
    @Order(1)
    void registrarNotificacion() throws Exception {
        Notificacion notificacion = crearNotificacionPrueba();
        
        notificacionService.registrarNotificacion(notificacion);

        ArrayList<Notificacion> lista = notificacionService.listarNotificacion();
        assertNotNull(lista);
        assertFalse(lista.isEmpty());

        Notificacion registrado = null;
        for (Notificacion n : lista) {
            if (n.getMensaje().equals(notificacion.getMensaje())) {
                registrado = n;
                break;
            }
        }
        
        assertNotNull(registrado);
        notificacionId = registrado.getIdNotificacion();
        assertEquals(notificacion.getMensaje(), registrado.getMensaje());
        
    }

    @Test
    @Order(2)
    void obtenerNotificacion() throws Exception {
        Notificacion notificacion = notificacionService.obtenerNotificacion(notificacionId);
        assertNotNull(notificacion);
        assertEquals("Notificación de prueba", notificacion.getMensaje());
    }

    @Test
    @Order(3)
    void actualizarNotificacion() throws Exception {
        Notificacion notificacion = notificacionService.obtenerNotificacion(notificacionId);
        notificacion.setMensaje("Mensaje actualizado");
        notificacion.setCantidad(2);
        notificacionService.actualizarNotificacion(notificacion);

        Notificacion actualizada = notificacionService.obtenerNotificacion(notificacionId);
        assertEquals("Mensaje actualizado", actualizada.getMensaje());
        assertEquals(2, actualizada.getCantidad());
    }

    @Test
    @Order(4)
    void eliminarNotificacion() throws Exception {
        notificacionService.eliminarNotificacion(notificacionId);
        Notificacion notificacionEliminada = notificacionService.obtenerNotificacion(notificacionId);

        assertNotNull(notificacionEliminada);
        assertFalse(notificacionEliminada.isActivo());
    }

    @Test
    @Order(5)
    void listarNotificaciones() throws Exception {
        ArrayList<Notificacion> lista = notificacionService.listarNotificacion();
        assertNotNull(lista);
    }
}
