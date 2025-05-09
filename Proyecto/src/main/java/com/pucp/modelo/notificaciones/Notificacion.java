/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pucp.modelo.notificaciones;

import com.pucp.modelo.publicaciones.Publicacion;
import com.pucp.modelo.usuarios.Usuario;
import java.sql.Date;

/**
 *
 * @author Axel
 */
public class Notificacion {
    //ATRIBUTOS
    private int idNotificacion;
    private String mensaje;
    private TipoNotificacion tipoNotificacion;
    private int cantidad;
    private Date fecha;
    private boolean activo;
    
    private Usuario notificador;
    private Publicacion autor;
    //CONSTRUCTORES
    public Notificacion() {
        this.notificador = new Usuario();
        this.autor = new Publicacion();
    }
    
    public Notificacion(int idNotificacion, String mensaje, TipoNotificacion tipoNot, int cantidad, Date fecha, boolean activo, Usuario notificador, Publicacion autor) {
        this.idNotificacion = idNotificacion;
        this.mensaje = mensaje;
        this.tipoNotificacion = tipoNot;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.activo = activo;
        this.notificador = notificador;
        this.autor = autor;
    }
    
    //GETTERS & SETTERS

    public int getIdNotificacion() {
        return idNotificacion;
    }

    public void setIdNotificacion(int idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFecha() {
        return fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public TipoNotificacion getTipoNotificacion() {
        return tipoNotificacion;
    }

    public void setTipoNotificacion(TipoNotificacion tipoNotificacion) {
        this.tipoNotificacion = tipoNotificacion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Usuario getNotificador() {
        return notificador;
    }

    public void setNotificador(Usuario notificador) {
        this.notificador = notificador;
    }

    public Publicacion getAutor() {
        return autor;
    }

    public void setAutor(Publicacion autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Notificacion{" + "idNotificacion=" + idNotificacion + ", mensaje=" + mensaje + ", tipoNotificacion=" + tipoNotificacion + ", cantidad=" + cantidad + ", fecha=" + fecha + ", activo=" + activo + ", notificador=" + notificador.getIdUsuario()+ ", autor=" + autor.getIdPublicacion()+ '}';
    }
    
    
    
}
