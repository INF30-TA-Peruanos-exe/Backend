/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pucp.capanegocio.notificaciones;

import com.pucp.capadominio.notificacion.Notificacion;
import com.pucp.capanegocio.interfacesService.NotificacionService;
import com.pucp.da.notificaciones.NotificacionCRUD;
import com.pucp.interfacesDAO.NotificacionDAO;
import java.util.ArrayList;

/**
 *
 * @author Axel
 */
public class NotificacionServiceImpl implements NotificacionService{
    private final NotificacionDAO notificacionDAO;
    
    public NotificacionServiceImpl(){
        this.notificacionDAO = new NotificacionCRUD();
    }

    @Override
    public void registrarNotificacion(Notificacion notificacion) throws Exception {
        if(notificacion.getAutor() == null){
            throw new Exception("La notificacion debe tener un autor"); 
        }
        if(notificacion.getCantidad() <= 0){
            throw new Exception("La cantidad debe ser un número positivo");
        }
        //Se puede validar para que no sea antigua a la fecha actual o una futura
        if(notificacion.getFecha() == null){
            throw new Exception("La fecha no puede ser nula");
        }
        if(notificacion.getMensaje() == null || notificacion.getMensaje().trim().isEmpty()){
            throw new Exception("El mensaje no puede estar vacío");
        }
        if(notificacion.getNotificador() == null){
            throw new Exception("La notificacion debe tener un notificador");
        }
        if(notificacion.getTipoNotificacion() == null){
            throw new Exception("La notificacion debe tener un tipo de notificacion");
        }
        notificacionDAO.insertar(notificacion);

    }

    @Override
    public void actualizarNotificacion(Notificacion notificacion) throws Exception {
        if(notificacionDAO.obtenerPorId(notificacion.getIdNotificacion()) == null){
            throw new Exception("La notificacion no existe");
        }
        
        if(notificacion.getAutor() == null){
            throw new Exception("La notificacion debe tener un autor"); 
        }
        if(notificacion.getCantidad() <= 0){
            throw new Exception("La cantidad debe ser un número positivo");
        }
        //Se puede validar para que no sea antigua a la fecha actual o una futura
        if(notificacion.getFecha() == null){
            throw new Exception("La fecha no puede ser nula");
        }
        if(notificacion.getMensaje() == null || notificacion.getMensaje().trim().isEmpty()){
            throw new Exception("El mensaje no puede estar vacío");
        }
        if(notificacion.getNotificador() == null){
            throw new Exception("La notificacion debe tener un notificador");
        }
        if(notificacion.getTipoNotificacion() == null){
            throw new Exception("La notificacion debe tener un tipo de notificacion");
        }
        
        notificacionDAO.actualizar(notificacion);
    }

    @Override
    public void eliminarNotificacion(int idNotificacion) throws Exception {
        Notificacion notificacion = notificacionDAO.obtenerPorId(idNotificacion);
        if(notificacion == null){
            throw new Exception("La notificacion no existe");
        }
        
        notificacionDAO.eliminar(idNotificacion);
    }

    @Override
    public Notificacion obtenerNotificacion(int idNotificacion) throws Exception {
        Notificacion notificacion = notificacionDAO.obtenerPorId(idNotificacion);
        if(notificacion == null){
            throw new Exception("La notificacion no existe");
        }
        
        return notificacion;
    }

    @Override
    public ArrayList<Notificacion> listarNotificacion() throws Exception {
        return notificacionDAO.listarTodos();
    }
    
}
