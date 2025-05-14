/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pucp.capanegocio.test;

import com.pucp.capadominio.denuncia.Denuncia;
import com.pucp.capadominio.publicacion.Publicacion;
import com.pucp.capadominio.usuarios.Administrador;
import com.pucp.capadominio.usuarios.Usuario;
import com.pucp.capanegocio.denuncias.DenunciaServiceImpl;
import com.pucp.capanegocio.interfacesService.DenunciaService;
import com.pucp.da.publicaciones.PublicacionCRUD;
import com.pucp.da.usuarios.AdministradorCRUD;
import com.pucp.da.usuarios.UsuarioCRUD;
import com.pucp.interfacesDAO.AdministradorDAO;
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
public class DenunciaServiceImplTest {
    private static DenunciaService denunciaService;
    private static int denunciaId;
    private static UsuarioDAO usuarioDAO;
    private static PublicacionDAO publicacionDAO;
    private static AdministradorDAO administradorDAO;
    
    private static Administrador admin;
    private static Publicacion autor;
    private static Usuario denunciante;
    @BeforeAll
    public static void setUp() {
        denunciaService = new DenunciaServiceImpl();
    }

    private static Denuncia crearDenunciaPrueba() {
        //Administrador
        admin = new Administrador();
        administradorDAO = new AdministradorCRUD();
        admin = administradorDAO.obtenerPorId(1);
        
        //Publicacion
        autor = new Publicacion();
        publicacionDAO = new PublicacionCRUD();
        autor = publicacionDAO.obtenerPorId(1);
        
        //Usuario
        denunciante = new Usuario();
        usuarioDAO = new UsuarioCRUD();
        denunciante = usuarioDAO.obtenerPorId(1);

        Denuncia denuncia = new Denuncia();
        denuncia.setAdmin(admin);
        denuncia.setAutor(autor);
        denuncia.setDenunciante(denunciante);
        //denuncia.setFechaDenuncia(LocalDateTime.now());
        denuncia.setMotivo("Contenido inapropiado");

        return denuncia;
    }

    @Test
    @Order(1)
    void registrarDenuncia() throws Exception {
        Denuncia denuncia = crearDenunciaPrueba();
        denunciaService.registrarDenuncia(denuncia);

        ArrayList<Denuncia> lista = denunciaService.listarDenuncia();
        assertNotNull(lista);
        assertFalse(lista.isEmpty());

        Denuncia registrada = lista.get(lista.size() - 1);
        denunciaId = registrada.getIdDenuncia();
        assertEquals("Contenido inapropiado", registrada.getMotivo());
    }

    @Test
    @Order(2)
    void obtenerDenuncia() throws Exception {
        Denuncia denuncia = denunciaService.obtenerDenuncia(denunciaId);
        assertNotNull(denuncia);
        assertEquals("Contenido inapropiado", denuncia.getMotivo());
    }

    @Test
    @Order(3)
    void actualizarDenuncia() throws Exception {
        Denuncia denuncia = denunciaService.obtenerDenuncia(denunciaId);
        denuncia.setMotivo("Motivo actualizado");
        denunciaService.actualizarDenuncia(denuncia);

        Denuncia actualizada = denunciaService.obtenerDenuncia(denunciaId);
        assertEquals("Motivo actualizado", actualizada.getMotivo());
    }

    @Test
    @Order(4)
    void eliminarDenuncia() throws Exception {
        denunciaService.eliminarDenuncia(denunciaId);
        Denuncia publicacionEliminada = denunciaService.obtenerDenuncia(denunciaId);

        assertNotNull(publicacionEliminada);
        assertFalse(publicacionEliminada.isActivo());
    }

    @Test
    @Order(5)
    void listarDenuncias() throws Exception {
        ArrayList<Denuncia> lista = denunciaService.listarDenuncia();
        assertNotNull(lista);
    }        
}
