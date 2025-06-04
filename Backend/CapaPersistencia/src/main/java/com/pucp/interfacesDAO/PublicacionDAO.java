/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pucp.interfacesDAO;



import com.pucp.capadominio.publicacion.Publicacion;
import java.util.ArrayList;

/**
 *
 * @author SEBASTIAN
 */
public interface PublicacionDAO extends BaseDAO<Publicacion>{
    
    ArrayList<Publicacion> listarporFacultad(int idFacultad);
    ArrayList<Publicacion> listarporEspecialidad(int idEspecialidad);
    ArrayList<Publicacion> listarporCurso(int idCurso);
    
    public void agregarFavorito(int idUsuario,int idPublicacion);
    public void eliminarfavorito(int idUsuario,int idPublicacion);
   ArrayList<Publicacion> listarFavorito(int idUsuario);
   boolean esPublicacionFavorito(int idUsuario,int idPublicacion);
}
