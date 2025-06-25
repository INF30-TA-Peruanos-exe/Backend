/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pucp.interfacesDAO;


import com.pucp.capadominio.notificacion.Notificacion;
import java.util.ArrayList;

/**
 *
 * @author SEBASTIAN
 */
public interface NotificacionDAO extends BaseDAO<Notificacion>{
    ArrayList<Notificacion> listarNotificacionesUsuario(int id_usuario);
    
}
