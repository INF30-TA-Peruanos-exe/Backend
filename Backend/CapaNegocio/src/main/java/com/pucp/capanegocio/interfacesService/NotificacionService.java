/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pucp.capanegocio.interfacesService;

import com.pucp.capadominio.notificacion.Notificacion;
import java.util.ArrayList;

/**
 *
 * @author Axel
 */
public interface NotificacionService {
    void registrarNotificacion(Notificacion notificacion) throws Exception;
    void actualizarNotificacion(Notificacion notificacion) throws Exception;
    void eliminarNotificacion(int idNotificacion) throws Exception;
    Notificacion obtenerNotificacion(int idNotificacion) throws Exception;
    ArrayList<Notificacion> listarNotificacion() throws Exception;
}
