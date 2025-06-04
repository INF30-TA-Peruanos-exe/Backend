/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pucp.capanegocio.publicaciones;

import com.pucp.capadominio.publicacion.EstadoPublicacion;
import com.pucp.capadominio.publicacion.Publicacion;
import com.pucp.capanegocio.interfacesService.PublicacionService;
import com.pucp.da.publicaciones.PublicacionCRUD;
import com.pucp.da.usuarios.UsuarioCRUD;
import com.pucp.interfacesDAO.PublicacionDAO;
import java.util.ArrayList;

/**
 *
 * @author Axel
 */
public class PublicacionServiceImpl implements PublicacionService{
    private final PublicacionDAO publicacionDAO;
    
    public PublicacionServiceImpl(){
        this.publicacionDAO = new PublicacionCRUD();
    }

    @Override
    public void registrarPublicacion(Publicacion publicacion) throws Exception {
        if(publicacion.getDescripcion()==null || publicacion.getDescripcion().trim().isEmpty()){
            throw new Exception("La descripcion no puede estar vacia");
        }
        if(publicacion.getEstado()==null){
            throw new Exception("El estado no puede estar vacia");
        }
        if(publicacion.getFechaPublicacion()==null){
            throw new Exception("La fecha no puede ser nula");
        }
        //Validaciones de rutas
        if(publicacion.getRutaImagen()==null || publicacion.getRutaImagen().trim().isEmpty()){
            throw new Exception("La ruta de imagen no puede estar vacía");
        }
        if(!(publicacion.getRutaImagen().endsWith(".jpg") || publicacion.getRutaImagen().endsWith(".jpeg") ||
                publicacion.getRutaImagen().endsWith(".png") || publicacion.getRutaImagen().endsWith(".gif"))){
            throw new Exception("La ruta debe apuntar a un archivo de imagen válido (.jpg, .png, etc.)");
        }
        
//        if(publicacion.getImagen()==null){
//            throw new Exception("La imagen no puede estar vacía");
//        }
        if(publicacion.getTitulo()==null || publicacion.getTitulo().trim().isEmpty()){
            throw new Exception("El titulo no puede estar vacio");
        }
        if(publicacion.getUsuario()==null){
            throw new Exception("El usuario no puede estar vacio");
        }
//        if(publicacion.getPublicacionesCursos()==null || publicacion.getPublicacionesCursos().isEmpty()){
//            throw new Exception("La lista de cursos no puede estar vacia");
//        }
//        if(publicacion.getPublicacionesEspecialidades()==null || publicacion.getPublicacionesEspecialidades().isEmpty()){
//            throw new Exception("La lista de especialidades no puede estar vacia");
//        }
//        if(publicacion.getPublicacionesFacultades()==null || publicacion.getPublicacionesFacultades().isEmpty()){
//            throw new Exception("La lista de facultades no puede estar vacia");
//        }
        
        publicacionDAO.insertar(publicacion);
    }

    @Override
    public void actualizarPublicacion(Publicacion publicacion) throws Exception {
        if(publicacionDAO.obtenerPorId(publicacion.getIdPublicacion())==null){
            throw new Exception("La publicacion no existe");
        }
        
        if(publicacion.getDescripcion()==null || publicacion.getDescripcion().trim().isEmpty()){
            throw new Exception("La descripcion no puede estar vacia");
        }
        if(publicacion.getEstado()==null){
            throw new Exception("El estado no puede estar vacia");
        }
        if(publicacion.getFechaPublicacion()==null){
            throw new Exception("La fecha no puede ser nula");
        }
        //Validaciones de rutas
        if(publicacion.getRutaImagen()==null || publicacion.getRutaImagen().trim().isEmpty()){
            throw new Exception("La ruta de imagen no puede estar vacía");
        }
        if(!(publicacion.getRutaImagen().endsWith(".jpg") || publicacion.getRutaImagen().endsWith(".jpeg") ||
                publicacion.getRutaImagen().endsWith(".png") || publicacion.getRutaImagen().endsWith(".gif"))){
            throw new Exception("La ruta debe apuntar a un archivo de imagen válido (.jpg, .png, etc.)");
        }
        
//        if(publicacion.getImagen()==null){
//            throw new Exception("La imagen no puede estar vacía");
//        }
        if(publicacion.getTitulo()==null || publicacion.getTitulo().trim().isEmpty()){
            throw new Exception("El titulo no puede estar vacio");
        }
        if(publicacion.getUsuario()==null){
            throw new Exception("El usuario no puede estar vacio");
        }
        if(publicacion.getPublicacionesCursos()==null || publicacion.getPublicacionesCursos().isEmpty()){
            throw new Exception("La lista de cursos no puede estar vacia");
        }
        if(publicacion.getPublicacionesEspecialidades()==null || publicacion.getPublicacionesEspecialidades().isEmpty()){
            throw new Exception("La lista de especialidades no puede estar vacia");
        }
        if(publicacion.getPublicacionesFacultades()==null || publicacion.getPublicacionesFacultades().isEmpty()){
            throw new Exception("La lista de facultades no puede estar vacia");
        }
        
        publicacionDAO.actualizar(publicacion);
    }

    @Override
    public void eliminarPublicacion(int idPublicacion) throws Exception {
        Publicacion publicacion = publicacionDAO.obtenerPorId(idPublicacion);
        if(publicacion == null){
            throw new Exception("La publicacion no existe");
        }
        publicacionDAO.eliminar(idPublicacion);
    }

    @Override
    public Publicacion obtenerPublicacion(int idPublicacion) throws Exception {
        Publicacion publicacion = publicacionDAO.obtenerPorId(idPublicacion);
        if(publicacion == null){
            throw new Exception("La publicacion no existe");
        }
        return publicacion;
    }
    
    @Override
    public void cambiarEstadoPublicacion(int idPublicacion, String estado) throws Exception {
        Publicacion publicacion = publicacionDAO.obtenerPorId(idPublicacion);
        if(publicacion == null){
            throw new Exception("La publicacion no existe");
        }
        publicacion.setEstado(EstadoPublicacion.valueOf(estado));
        publicacionDAO.actualizar(publicacion);
    }

    @Override
    public ArrayList<Publicacion> listarPublicacion() throws Exception {
        return publicacionDAO.listarTodos();
    }

    @Override
    public ArrayList<Publicacion> listarPorFacultad(int idFacultad) throws Exception {
        return publicacionDAO.listarporFacultad(idFacultad);
    }

    @Override
    public ArrayList<Publicacion> listarPorEspecialidad(int idEspecialidad) throws Exception {
        return publicacionDAO.listarporEspecialidad(idEspecialidad);
    }

    @Override
    public ArrayList<Publicacion> listarPorCurso(int idCurso) throws Exception {
        return publicacionDAO.listarporCurso(idCurso);
    }

    @Override
    public void agregarFavorito(int idUsuario, int idPublicacion) throws Exception {
        Publicacion publicacion = publicacionDAO.obtenerPorId(idPublicacion);
        UsuarioCRUD usuario = new UsuarioCRUD();
        if(publicacion == null){
            throw new Exception("La publicacion no existe");
        }
        if(usuario.obtenerPorId(idUsuario)==null){
            throw new Exception("El usuario no existe");
        }
        
        publicacionDAO.agregarFavorito(idPublicacion, idUsuario);
    }

    @Override
    public void eliminarFavorito(int idUsuario, int idPublicacion) throws Exception {
       Publicacion publicacion = publicacionDAO.obtenerPorId(idPublicacion);
       UsuarioCRUD usuario = new UsuarioCRUD();
        if(publicacion == null){
            throw new Exception("La publicacion no existe");
        }
        if(usuario.obtenerPorId(idUsuario)==null){
            throw new Exception("El usuario no existe");
        }
        publicacionDAO.eliminarfavorito(idUsuario, idPublicacion);
    }

    @Override
    public ArrayList<Publicacion> listarFavoritos(int idUsuario) throws Exception {
        UsuarioCRUD usuario = new UsuarioCRUD();
        if(usuario.obtenerPorId(idUsuario)==null){
            throw new Exception("El usuario no existe");
        }
        return publicacionDAO.listarFavorito(idUsuario);
    }

    @Override
    public boolean esFavorito(int idUsuario, int idPublicacion) throws Exception {
        return publicacionDAO.esPublicacionFavorito(idUsuario, idPublicacion);
    }
    
    
}
