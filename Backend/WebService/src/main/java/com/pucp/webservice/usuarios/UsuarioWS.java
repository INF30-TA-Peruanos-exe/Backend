package com.pucp.webservice.usuarios;

import com.pucp.capadominio.usuarios.Usuario;
import com.pucp.capanegocio.usuarios.UsuarioServiceImpl;
import com.pucp.capanegocio.interfacesService.UsuarioService;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.ws.WebServiceException;

import java.util.ArrayList;

@WebService(serviceName = "UsuarioWS", targetNamespace = "com.pucp.pucpqhatu")
public class UsuarioWS {

    private final UsuarioService usuarioService;

    public UsuarioWS() {
        usuarioService = new UsuarioServiceImpl();
    }

    @WebMethod(operationName = "registrarUsuario")
    public void registrarUsuario(@WebParam(name = "usuario") Usuario usuario) {
        try {
            usuarioService.registrarUsuario(usuario);
        } catch (Exception ex) {
            throw new WebServiceException("Error al registrar usuario: " + ex.getMessage());
        }
    }

    @WebMethod(operationName = "actualizarUsuario")
    public void actualizarUsuario(@WebParam(name = "usuario") Usuario usuario) {
        try {
            usuarioService.actualizarUsuario(usuario);
        } catch (Exception ex) {
            throw new WebServiceException("Error al actualizar usuario: " + ex.getMessage());
        }
    }

    @WebMethod(operationName = "eliminarUsuario")
    public void eliminarUsuario(@WebParam(name = "idUsuario") int idUsuario) {
        try {
            usuarioService.eliminarUsuario(idUsuario);
        } catch (Exception ex) {
            throw new WebServiceException("Error al eliminar usuario: " + ex.getMessage());
        }
    }

    @WebMethod(operationName = "obtenerUsuario")
    public Usuario obtenerUsuario(@WebParam(name = "idUsuario") int idUsuario) {
        try {
            return usuarioService.obtenerUsuario(idUsuario);
        } catch (Exception ex) {
            throw new WebServiceException("Error al obtener usuario: " + ex.getMessage());
        }
    }

    @WebMethod(operationName = "listarUsuarios")
    public ArrayList<Usuario> listarUsuarios() {
        try {
            return usuarioService.listarUsuario();
        } catch (Exception ex) {
            throw new WebServiceException("Error al listar usuarios: " + ex.getMessage());
        }
    }
}
