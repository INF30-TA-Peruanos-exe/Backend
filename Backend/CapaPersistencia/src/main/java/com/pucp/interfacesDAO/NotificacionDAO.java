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
public interface NotificacionDAO {
    
    void insertar(Notificacion notificacion);
    ArrayList<Notificacion> listarTodos();
    Notificacion obtenerPorId(int id);
    void actualizar(Notificacion notificacion);
    void eliminar(int id);
    
}
