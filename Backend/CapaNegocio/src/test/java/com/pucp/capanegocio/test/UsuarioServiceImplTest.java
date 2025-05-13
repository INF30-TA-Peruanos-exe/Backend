package com.pucp.capanegocio.test;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import com.pucp.capadominio.usuarios.EstadoUsuario;
import com.pucp.capadominio.usuarios.Usuario;
import com.pucp.capanegocio.usuarios.UsuarioServiceImpl;

import com.pucp.interfacesDAO.UsuarioDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author Axel
 */

public class UsuarioServiceImplTest {

    private UsuarioDAO usuarioDAO;
    private UsuarioServiceImpl usuarioService;

    @BeforeEach
    public void setUp() {
        usuarioDAO = mock(UsuarioDAO.class);
        usuarioService = new UsuarioServiceImpl();
    }

    private Usuario crearUsuarioValido() {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1);
        usuario.setCodigoPUCP(20201234);
        usuario.setCorreo("test@pucp.edu.pe");
        usuario.setContrasena("123456");
        usuario.setEstado(EstadoUsuario.HABILITADO);
        usuario.setNombre("Nombre Test");
        usuario.setNombreUsuario("usuario123");
        return usuario;
    }

    @Test
    public void testRegistrarUsuario_Exitoso() throws Exception {
        Usuario usuario = crearUsuarioValido();
        doNothing().when(usuarioDAO).insertar(usuario);
        assertDoesNotThrow(() -> usuarioService.registrarUsuario(usuario));
    }

    @Test
    public void testRegistrarUsuario_CodigoInvalido() {
        Usuario usuario = crearUsuarioValido();
        usuario.setCodigoPUCP(-1);
        Exception ex = assertThrows(Exception.class, () -> usuarioService.registrarUsuario(usuario));
        assertEquals("El codigo no puede ser un número negativo", ex.getMessage());
    }

    @Test
    public void testRegistrarUsuario_CorreoInvalido() {
        Usuario usuario = crearUsuarioValido();
        usuario.setCorreo("test@gmail.com");
        Exception ex = assertThrows(Exception.class, () -> usuarioService.registrarUsuario(usuario));
        assertEquals("El correo debe ser: example@pucp.edu.pe", ex.getMessage());
    }

    @Test
    public void testActualizarUsuario_UsuarioNoExiste() {
        Usuario usuario = crearUsuarioValido();
        when(usuarioDAO.obtenerPorId(usuario.getIdUsuario())).thenReturn(null);
        Exception ex = assertThrows(Exception.class, () -> usuarioService.actualizarUsuario(usuario));
        assertEquals("El usuario no existe", ex.getMessage());
    }

    @Test
    public void testActualizarUsuario_Exitoso() throws Exception {
        Usuario usuario = crearUsuarioValido();
        when(usuarioDAO.obtenerPorId(usuario.getIdUsuario())).thenReturn(usuario);
        doNothing().when(usuarioDAO).actualizar(usuario);
        assertDoesNotThrow(() -> usuarioService.actualizarUsuario(usuario));
    }

    @Test
    public void testEliminarUsuario_UsuarioNoExiste() {
        when(usuarioDAO.obtenerPorId(1)).thenReturn(null);
        Exception ex = assertThrows(Exception.class, () -> usuarioService.eliminarUsuario(1));
        assertEquals("El usuario no existe", ex.getMessage());
    }

    @Test
    public void testEliminarUsuario_Exitoso() throws Exception {
        Usuario usuario = crearUsuarioValido();
        when(usuarioDAO.obtenerPorId(1)).thenReturn(usuario);
        doNothing().when(usuarioDAO).eliminar(1);
        assertDoesNotThrow(() -> usuarioService.eliminarUsuario(1));
    }

    @Test
    public void testObtenerUsuario_Exitoso() throws Exception {
        Usuario usuario = crearUsuarioValido();
        when(usuarioDAO.obtenerPorId(1)).thenReturn(usuario);
        Usuario result = usuarioService.obtenerUsuario(1);
        assertNotNull(result);
        assertEquals("usuario123", result.getNombreUsuario());
    }

    @Test
    public void testListarUsuario() throws Exception {
        ArrayList<Usuario> lista = new ArrayList<>();
        lista.add(crearUsuarioValido());
        when(usuarioDAO.listarTodos()).thenReturn(lista);
        ArrayList<Usuario> resultado = usuarioService.listarUsuario();
        assertEquals(1, resultado.size());
    }
}