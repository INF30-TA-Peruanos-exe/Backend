/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pucp.capadominio.publicacion;

import com.pucp.capadominio.usuarios.Usuario;
import java.sql.Date;

/**
 *
 * @author Axel
 */
public class Comentario {
    //ATRIBUTOS
    private int idComentario;
    private String contenido;
    private int valoracion;
    private Date fecha;
    private boolean activo;
    private Usuario comentador;
    private Publicacion publicacion;

    //CONSTRUCTORES
    public Comentario() {
        this.comentador = new Usuario();
        this.publicacion = new Publicacion();
    }
    
    public Comentario(int idComentario, String contenido, int valoracion, 
            Date fecha, boolean activo, Usuario comentador, Publicacion publicacion) {
        this.idComentario = idComentario;
        this.contenido = contenido;
        this.valoracion = valoracion;
        this.fecha = fecha;
        this.activo = activo;
        this.comentador = comentador;
        this.publicacion = publicacion;
    }

    //GETTERS & SETTERS
    public int getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(int idComentario) {
        this.idComentario = idComentario;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public int getValoracion() {
        return valoracion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Usuario getComentador() {
        return comentador;
    }

    public void setComentador(Usuario comentador) {
        this.comentador = comentador;
    }

    public Publicacion getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Publicacion publicacion) {
        this.publicacion = publicacion;
    }

    @Override
    public String toString() {
        return "Comentario{" + "idComentario=" + idComentario + ", contenido=" + contenido + ", valoracion=" + valoracion + ", fecha=" + fecha + ", activo=" + activo + ", comentador=" + comentador.getIdUsuario()+ ", publicacion=" + publicacion.getIdPublicacion() + '}';
    }
}
