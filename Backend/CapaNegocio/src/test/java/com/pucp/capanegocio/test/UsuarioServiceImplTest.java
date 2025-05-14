package com.pucp.capanegocio.test;

import com.pucp.capadominio.usuarios.EstadoUsuario;
import com.pucp.capadominio.usuarios.Usuario;
import com.pucp.capanegocio.interfacesService.UsuarioService;
import com.pucp.capanegocio.usuarios.UsuarioServiceImpl;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UsuarioServiceImplTest {
    private static UsuarioService usuarioService;
    private static int usuarioId;

    @BeforeAll
    public static void setUp() {
        usuarioService = new UsuarioServiceImpl();
    }

    private static Usuario crearUsuarioPrueba() {
        Usuario usuario = new Usuario();
        usuario.setCodigoPUCP(20241234);
        usuario.setContrasena("SecurePass123");
        usuario.setCorreo("testuser@pucp.edu.pe");
        usuario.setEstado(EstadoUsuario.HABILITADO);
        usuario.setNombre("Test");
        usuario.setNombreUsuario("testuser");
        usuario.setActivo(true);
        return usuario;
    }

    @Test
    @Order(1)
    void registrarUsuario() throws Exception {
        Usuario usuario = crearUsuarioPrueba();
        usuarioService.registrarUsuario(usuario);

        ArrayList<Usuario> usuarios = usuarioService.listarUsuario();
        assertNotNull(usuarios);
        assertFalse(usuarios.isEmpty());

        Usuario registrado = null;
        for (Usuario u : usuarios) {
            if (u.getCorreo().equals(usuario.getCorreo())) {
                registrado = u;
                break;
            }
        }
        assertNotNull(registrado);
        usuarioId = registrado.getIdUsuario();
        assertEquals(usuario.getNombreUsuario(), registrado.getNombreUsuario());
    }

    @Test
    @Order(2)
    void obtenerUsuario() throws Exception {
        Usuario usuario = usuarioService.obtenerUsuario(usuarioId);
        assertNotNull(usuario);
        assertEquals("Test", usuario.getNombre());
        assertEquals("testuser@pucp.edu.pe", usuario.getCorreo());
    }

    @Test
    @Order(3)
    void actualizarUsuario() throws Exception {
        Usuario usuario = usuarioService.obtenerUsuario(usuarioId);
        usuario.setNombre("Usuario Modificado");
        usuario.setCorreo("modificado@pucp.edu.pe");
        usuarioService.actualizarUsuario(usuario);

        Usuario actualizado = usuarioService.obtenerUsuario(usuarioId);
        assertEquals("Usuario Modificado", actualizado.getNombre());
        assertEquals("modificado@pucp.edu.pe", actualizado.getCorreo());
    }

    @Test
    @Order(4)
    void eliminarUsuario() throws Exception {
        usuarioService.eliminarUsuario(usuarioId);
        Usuario usuarioEliminado = usuarioService.obtenerUsuario(usuarioId);

        assertNotNull(usuarioEliminado);
        assertFalse(usuarioEliminado.isActivo()); // Verifica que esté desactivado
    }
    
    @Test
    @Order(5)
    void listarUsuarios() throws Exception {
        ArrayList<Usuario> lista = usuarioService.listarUsuario();
        assertNotNull(lista); // Sin importar si está vacía
    }  
}