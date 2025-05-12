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
        if(notificacion.getCantidad() == 0){
            
        }
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
    }

    @Override
    public void actualizarNotificacion(Notificacion notificacion) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminarNotificacion(int idNotificacion) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Notificacion obtenerNotificacion(int idNotificacion) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Notificacion> listarNotificacion() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
