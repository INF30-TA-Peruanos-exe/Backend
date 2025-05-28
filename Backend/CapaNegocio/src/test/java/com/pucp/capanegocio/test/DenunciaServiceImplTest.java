/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pucp.capanegocio.test;

import com.pucp.capadominio.denuncia.Denuncia;
import com.pucp.capadominio.publicacion.EstadoPublicacion;
import com.pucp.capadominio.publicacion.Publicacion;
import com.pucp.capadominio.usuarios.Administrador;
import com.pucp.capadominio.usuarios.EstadoUsuario;
import com.pucp.capadominio.usuarios.Usuario;
import com.pucp.capanegocio.denuncias.DenunciaServiceImpl;
import com.pucp.capanegocio.interfacesService.DenunciaService;
import com.pucp.da.publicaciones.PublicacionCRUD;
import com.pucp.da.usuarios.AdministradorCRUD;
import com.pucp.da.usuarios.UsuarioCRUD;
import com.pucp.interfacesDAO.AdministradorDAO;
import com.pucp.interfacesDAO.PublicacionDAO;
import com.pucp.interfacesDAO.UsuarioDAO;
import java.sql.Date;
import java.time.LocalDate;
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
    // DAO init
    administradorDAO = new AdministradorCRUD();
    publicacionDAO = new PublicacionCRUD();
    usuarioDAO = new UsuarioCRUD();

    // Crear e insertar un usuario denunciante
    denunciante = new Usuario();
    denunciante.setCodigoPUCP(20241111);
    denunciante.setNombreUsuario("denunciante");
    denunciante.setContrasena("123456");
    denunciante.setNombre("Usuario Denunciante");
    denunciante.setCorreo("denunciante@pucp.edu.pe");
    denunciante.setEstado(EstadoUsuario.HABILITADO);
    denunciante.setActivo(true);
    usuarioDAO.insertar(denunciante);

    // Crear e insertar administrador
    admin = new Administrador();
    admin.setCodigoPUCP(20241112);
    admin.setNombreUsuario("adminDenuncia");
    admin.setContrasena("admin123");
    admin.setNombre("Administrador Denuncias");
    admin.setCorreo("admin.d@pucp.edu.pe");
    admin.setEstado(EstadoUsuario.HABILITADO);
    admin.setActivo(true);
    admin.setClaveMaestra("claveMaestra");
    administradorDAO.insertar(admin);

    // Crear e insertar publicación (autor es el mismo que denunciante para simplicidad)
    autor = new Publicacion();
    autor.setTitulo("Publicación falsa");
    autor.setDescripcion("Contenido sospechoso");
    autor.setUsuario(denunciante);
    autor.setActivo(true);
    autor.setEstado(EstadoPublicacion.VISIBLE);

    publicacionDAO.insertar(autor);

    // Crear denuncia
    Denuncia denuncia = new Denuncia();
    denuncia.setAdmin(admin);
    denuncia.setAutor(autor); //el autor es la publicacion reportada
    denuncia.setDenunciante(denunciante);
    denuncia.setMotivo("Contenido inapropiado");
    denuncia.setActivo(true);
    denuncia.setFechaDenuncia(Date.valueOf(LocalDate.now()));

    return denuncia;
}


    @Test
    @Order(1)
    void registrarDenuncia() throws Exception {
        Denuncia denuncia = crearDenunciaPrueba();
        System.out.println("Antes de registrar: total denuncias = " + denunciaService.listarDenuncia().size());
        denunciaService.registrarDenuncia(denuncia);
        System.out.println("Después de registrar: total denuncias = " + denunciaService.listarDenuncia().size());


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
