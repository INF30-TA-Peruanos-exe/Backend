/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pucp.modelo.publicaciones;

import com.pucp.modelo.categorias.Curso;
import com.pucp.modelo.categorias.Especialidad;
import com.pucp.modelo.categorias.Facultad;
import com.pucp.modelo.notificaciones.Notificacion;
import com.pucp.modelo.denuncias.Denuncia;

import java.util.ArrayList;

//----------IMAGEN----------
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import javax.imageio.ImageIO;

/**
 *
 * @author Axel
 */
public class Publicacion {
    //ATRIBUTOS
    private int idPublicacion;
    private String titulo;
    private String descripcion;
    private EstadoPublicacion estado;
    private ArrayList<Comentario> comentarios;
    private ArrayList<Curso> publicacionesCursos;
    private ArrayList<Especialidad> publicacionesEspecialidades;
    private ArrayList<Facultad> publicacionesFacultades;
    private ArrayList<Notificacion> notificaciones;
    private ArrayList<Denuncia> publicacionDenuncias;
    private Date fechaPublicacion;
    private boolean activo;
    //Nuevo
    private Image imagen;
    private String rutaImagen;

    //CONSTRUCTORES
    public Publicacion() {
        this.publicacionesCursos = new ArrayList<>();
        this.publicacionesEspecialidades = new ArrayList<>();
        this.publicacionesFacultades = new ArrayList<>();
        this.notificaciones = new ArrayList<>();
        this.publicacionDenuncias = new ArrayList<>();
    }
    
    
    public Publicacion(int idPublicacion, String titulo, String descripcion, 
            EstadoPublicacion estado, Date fechaPublicacion, String rutaImagen, boolean activo) {
        
        this.idPublicacion = idPublicacion;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estado = estado;
        this.comentarios = new ArrayList<>();
        this.publicacionesCursos = new ArrayList<>();
        this.publicacionesEspecialidades = new ArrayList<>();
        this.publicacionesFacultades = new ArrayList<>();
        this.notificaciones = new ArrayList<>();
        this.publicacionDenuncias = new ArrayList<>();
        this.fechaPublicacion = fechaPublicacion;
        this.activo = activo;
        
        //Nuevo
        this.rutaImagen = rutaImagen;
        this.imagen = cargarImagen(this.rutaImagen);
    }

    //FUNCIONES EXTRA
    private Image cargarImagen(String rutaImagen){
        try{
            File file = new File(rutaImagen);
            if (file.exists() && !file.isDirectory()) {
                return ImageIO.read(file);
            } else {
                System.out.println("La ruta de la imagen no es válida: " + rutaImagen);
                return null;
            }
        } catch (IOException e) {
            System.out.println("Error al cargar la imagen: " + 
                    e.getMessage());
            return null;
        }
    }
    
    //FUNCIONES DE LOS ARRAY LIST
    public void agregarComentario(Comentario comentario) {
        this.comentarios.add(comentario);
    }
    
    public void eliminarComentario(Comentario comentario) {
        this.comentarios.remove(comentario);
    } 
    
    public void agregarCurso(Curso curso) {
        this.publicacionesCursos.add(curso);
    }
    
    public void eliminarCurso(Curso curso) {
        this.publicacionesCursos.remove(curso);
    } 
    
    public void agregarEspecialidad(Especialidad especialidad) {
        this.publicacionesEspecialidades.add(especialidad);
    }
    
    public void eliminarEspecialidad(Especialidad especialidad) {
        this.publicacionesEspecialidades.remove(especialidad);
    } 
    
    public void agregarFacultad(Facultad facultad) {
        this.publicacionesFacultades.add(facultad);
    }
    
    public void eliminarFacultad(Facultad facultad) {
        this.publicacionesFacultades.remove(facultad);
    } 
    
    public void agregarNotificacion(Notificacion notificacion) {
        this.notificaciones.add(notificacion);
    }
    
    public void eliminarNotificacion(Notificacion notificacion) {
        this.notificaciones.remove(notificacion);
    }
    
    public void agregarPublicacion(Denuncia denuncia) {
        this.publicacionDenuncias.add(denuncia);
    }
    
    public void eliminarPublicacion(Denuncia denuncia) {
        this.publicacionDenuncias.remove(denuncia);
    } 
    
    //GETTERS & SETTERS
    public int getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(int idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public EstadoPublicacion getEstado() {
        return estado;
    }

    public void setEstado(EstadoPublicacion estado) {
        this.estado = estado;
    }

    public ArrayList<Comentario> getComentarios() {
        return new ArrayList<>(comentarios);
    }

    public ArrayList<Curso> getPublicacionesCursos() {
        return new ArrayList<>(publicacionesCursos);
    }

    public ArrayList<Especialidad> getPublicacionesEspecialidades() {
        return new ArrayList<>(publicacionesEspecialidades);
    }

    public ArrayList<Facultad> getPublicacionesFacultades() {
        return new ArrayList<>(publicacionesFacultades);
    }

    public ArrayList<Notificacion> getNotificaciones() {
        return new ArrayList<>(notificaciones);
    }

    public ArrayList<Denuncia> getPublicacionDenuncias() {
        return new ArrayList<>(publicacionDenuncias);
    }
    
    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }
    
    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public Image getImagen() {
        return imagen;
    }
    
    //Se seteara con una url
    public void setImagen(String rutaImagen) {
        this.imagen =  cargarImagen(rutaImagen);
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Publicacion{" + "idPublicacion=" + idPublicacion + ", titulo=" + titulo + ", descripcion=" + descripcion + ", estado=" + estado +  ", fechaPublicacion=" + fechaPublicacion + ", activo=" + activo  + ", rutaImagen=" + rutaImagen + '}';
    }
    
    
}
