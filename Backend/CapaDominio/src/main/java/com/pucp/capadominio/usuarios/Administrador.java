/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pucp.capadominio.usuarios;

import com.pucp.capadominio.denuncia.Denuncia;
import java.util.ArrayList;

/**
 *
 * @author Axel
 */
public class Administrador extends Usuario{
    //ATRIBUTOS
    private String claveMaestra;
    private ArrayList<Denuncia> adminDenuncias;
	
    //CONSTRUCTORES
    public Administrador() {
        super();
        this.adminDenuncias = new ArrayList<>();
    }
    
    public Administrador(int idUsuario, int codigoPUCP, String nombreUsuario, String contrasena,
            String nombre, String correo, EstadoUsuario estado, boolean activo, String claveMaestra) {
        super(idUsuario, codigoPUCP, nombreUsuario, contrasena, nombre, correo, estado, activo);
        this.claveMaestra = claveMaestra;
        this.adminDenuncias = new ArrayList<>();
    }

    //FUNCIONES PARA LOS ARRAY LIST
    public void agregarPublicacion(Denuncia denuncia) {
        this.adminDenuncias.add(denuncia);
    }
    
    public void eliminarPublicacion(Denuncia denuncia) {
        this.adminDenuncias.remove(denuncia);
    } 
    
    //GETTERS & SETTERS
    public String getClaveMaestra() {
        return claveMaestra;
    }

    public void setClaveMaestra(String claveMaestra) {
        this.claveMaestra = claveMaestra;
    }

    public ArrayList<Denuncia> getAdminDenuncias() {
        return new ArrayList<>(adminDenuncias);
    }

    @Override
    public String toString() {
        return "Administrador{" + "claveMaestra=" + claveMaestra + ", idAdministrador=" + super.getIdUsuario() + ", Nombre: " + super.getNombre() + '}';
    }
}
