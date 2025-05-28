/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pucp.capadominio.categorias;

/**
 *
 * @author Axel
 */
public class Especialidad {
    //ATRIBUTOS
    private int idEspecialidad;
    private String nombre;
    private boolean activo;
    
    //CONSTRUCTORES
    public Especialidad(){
        
    }
	
    public Especialidad(int idEspecialidad, String nombre, boolean activo){
        this.idEspecialidad=idEspecialidad;
        this.nombre=nombre;
        this.activo=activo;
    }
	
    //GETTERS & SETTERS
    public int getIdEspecialidad(){
        return idEspecialidad;
    }

    public void setIdEspecialidad(int idEspecialidad){
        this.idEspecialidad=idEspecialidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre=nombre;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Especialidad{" + "idEspecialidad=" + idEspecialidad + ", nombre=" + nombre + ", activo=" + activo + '}';
    }
}
