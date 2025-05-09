/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pucp.capadominio.usuarios;

import com.pucp.capadominio.publicacion.Comentario;
import com.pucp.capadominio.publicacion.Publicacion;
import com.pucp.capadominio.denuncia.Denuncia;
import com.pucp.capadominio.notificacion.Notificacion;
import java.util.ArrayList;

/**
 *
 * @author Axel
 */
public class Usuario {
    //ATRIBUTOS
    private int idUsuario;
    private int codigoPUCP;
    private String nombreUsuario;
    private String contrasena;
    private String nombre;
    private String correo;
    private ArrayList<Publicacion> publicaciones;
    private ArrayList<Publicacion> favoritos;
    private ArrayList<Comentario> comentarios;
    private ArrayList<Notificacion> notificaciones;
    private ArrayList<Denuncia> denuncias;
    private EstadoUsuario estado;
    private boolean activo;
    
    //CONSTRUCTORES
    public Usuario() {
        publicaciones = new ArrayList<>();
        favoritos = new ArrayList<>();
        comentarios = new ArrayList<>();
        notificaciones = new ArrayList<>();
        denuncias = new ArrayList<>();
    }
    
    public Usuario(Usuario user){
        this.idUsuario = user.idUsuario;
        this.codigoPUCP = user.codigoPUCP;
        this.nombreUsuario = user.nombreUsuario;
        this.contrasena = user.contrasena;
        this.nombre = user.nombre;
        this.correo = user.correo;
        this.estado = user.estado;
        this.publicaciones = new ArrayList<>(user.publicaciones);
        this.favoritos = new ArrayList<>(user.favoritos);
        this.comentarios = new ArrayList<>(user.comentarios);
        this.notificaciones = new ArrayList<>(user.notificaciones);
        this.denuncias = new ArrayList<>(user.denuncias);
        this.activo = user.activo;
    }
    
    public Usuario(int idUsuario, int codigoPUCP, String nombreUsuario, 
            String contrasena, String nombre, String correo, EstadoUsuario estado, boolean activo){
        this.idUsuario = idUsuario;
        this.codigoPUCP = codigoPUCP;
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.nombre = nombre;
        this.correo = correo;
        this.estado = estado;
        this.publicaciones = new ArrayList<>();
        this.favoritos = new ArrayList<>();
        this.comentarios = new ArrayList<>();
        this.notificaciones = new ArrayList<>();
        this.denuncias = new ArrayList<>();
        this.activo = activo;
    }
    
    //Falta implementar el constructor copia para USUARIO
    
    //FUNCIONES DE LOS ARRAY LIST
    public void agregarPublicacion(Publicacion publicacion) {
        this.publicaciones.add(publicacion);
    }
    
    public void eliminarPublicacion(Publicacion publicacion) {
        this.publicaciones.remove(publicacion);
    }
    
    public void agregarFavorito(Publicacion publicacion) {
        this.favoritos.add(publicacion);
    }
    
    public void eliminarFavorito(Publicacion publicacion) {
        this.favoritos.remove(publicacion);
    }
    
    public void agregarComentario(Comentario comentario) {
        this.comentarios.add(comentario);
    }
    
    public void eliminarComentario(Comentario comentario) {
        this.comentarios.remove(comentario);
    }
    
    public void agregarNotificacion(Notificacion notificacion) {
        this.notificaciones.add(notificacion);
    }
    
    public void eliminarNotificacion(Notificacion notificacion) {
        this.notificaciones.remove(notificacion);
    }
    
    public void agregarDenuncia(Denuncia denuncia) {
        this.denuncias.add(denuncia);
    }
    
    public void eliminarDenuncia(Denuncia denuncia) {
        this.denuncias.remove(denuncia);
    }

    
    //GETTERS & SETTERS
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getCodigoPUCP() {
        return codigoPUCP;
    }

    public void setCodigoPUCP(int codigoPUCP) {
        this.codigoPUCP = codigoPUCP;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public ArrayList<Publicacion> getPublicaciones() {
        return new ArrayList<>(publicaciones);
    }

    public ArrayList<Publicacion> getFavoritos() {
        return new ArrayList<>(favoritos);
    }

    public ArrayList<Comentario> getComentarios() {
        return new ArrayList<>(comentarios);
    }

    public ArrayList<Notificacion> getNotificaciones() {
        return new ArrayList<>(notificaciones);
    }

    public ArrayList<Denuncia> getDenuncia() {
        return new ArrayList<>(denuncias);
    }

    public EstadoUsuario getEstado() {
        return estado;
    }

    public void setEstado(EstadoUsuario estado) {
        this.estado = estado;
    } 

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Usuario{" + "idUsuario=" + idUsuario + ", codigoPUCP=" + codigoPUCP + ", nombreUsuario=" + nombreUsuario + ", contrasena=" + contrasena + ", nombre=" + nombre + ", correo=" + correo + ", estado=" + estado + ", activo=" + activo + '}';
    }
}
