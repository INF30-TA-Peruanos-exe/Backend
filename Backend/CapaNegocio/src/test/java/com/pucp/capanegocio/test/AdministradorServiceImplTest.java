/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pucp.capanegocio.test;

import com.pucp.capadominio.usuarios.Administrador;
import com.pucp.capadominio.usuarios.EstadoUsuario;
import com.pucp.capanegocio.interfacesService.AdministradorService;
import com.pucp.capanegocio.usuarios.AdministradorServiceImpl;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

/**
 *
 * @author Axel
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AdministradorServiceImplTest {
    private static AdministradorService administradorService;
    private static int administradorId;

    @BeforeAll
    public static void setUp() {
        administradorService = new AdministradorServiceImpl();
    }

    private static Administrador crearAdministradorPrueba() {
        Administrador admin = new Administrador();
        admin.setCodigoPUCP(20245555);
        admin.setContrasena("AdminPass123");
        admin.setCorreo("admin@pucp.edu.pe");
        admin.setEstado(EstadoUsuario.HABILITADO);
        admin.setNombre("Administrador Prueba");
        admin.setNombreUsuario("adminprueba");
        admin.setClaveMaestra("ClaveSecreta123");
        admin.setActivo(true);
        return admin;
    }

    @Test
    @Order(1)
    void registrarAdministrador() throws Exception {
        Administrador admin = crearAdministradorPrueba();
        administradorService.registrarAdministrador(admin);

        ArrayList<Administrador> lista = administradorService.listarAdministrador();
        assertNotNull(lista);
        assertFalse(lista.isEmpty());

        Administrador encontrado = null;
        for (Administrador a : lista) {
            if (a.getCorreo().equals(admin.getCorreo())) {
                encontrado = a;
                break;
            }
        }
        assertNotNull(encontrado);
        administradorId = encontrado.getIdUsuario(); // getIdAdministrador si lo tienes diferenciado
        assertEquals(admin.getNombreUsuario(), encontrado.getNombreUsuario());
    }

    @Test
    @Order(2)
    void obtenerAdministrador() throws Exception {
        Administrador admin = administradorService.obtenerAdministrador(administradorId);
        assertNotNull(admin);
        assertEquals("Administrador Prueba", admin.getNombre());
        assertEquals("admin@pucp.edu.pe", admin.getCorreo());
    }

    @Test
    @Order(3)
    void actualizarAdministrador() throws Exception {
        Administrador admin = administradorService.obtenerAdministrador(administradorId);
        admin.setNombre("Admin Modificado");
        admin.setCorreo("modadmin@pucp.edu.pe");
        administradorService.actualizarAdministrador(admin);

        Administrador actualizado = administradorService.obtenerAdministrador(administradorId);
        assertEquals("Admin Modificado", actualizado.getNombre());
        assertEquals("modadmin@pucp.edu.pe", actualizado.getCorreo());
    }

    @Test
    @Order(4)
    void eliminarAdministrador() throws Exception {
        administradorService.eliminarAdministrador(administradorId);
        Administrador eliminado = administradorService.obtenerAdministrador(administradorId);

        assertNotNull(eliminado);
        assertFalse(eliminado.isActivo()); // Verifica que se haya desactivado
    }

    @Test
    @Order(5)
    void listarAdministradores() throws Exception {
        ArrayList<Administrador> lista = administradorService.listarAdministrador();
        assertNotNull(lista); // Sin importar si está vacía
    }    
}
