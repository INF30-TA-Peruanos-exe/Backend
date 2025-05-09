/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pucp.capadominio.categorias;

/**
 *
 * @author Axel
 */
public class Facultad {
    //ATRIBUTOS
    private int idFacultad;
    private String nombre;
    private boolean activo;
    
    //CONSTRUCTORES
    public Facultad() {
    }

    public Facultad(int idFacultad, String nombre, boolean activo) {
        this.idFacultad = idFacultad;
        this.nombre = nombre;
        this.activo = activo;
    }

    //GETTERS & SETTERS
    public int getIdFacultad() {
        return idFacultad;
    }

    public void setIdFacultad(int idFacultad) {
        this.idFacultad = idFacultad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Facultad{" + "idFacultad=" + idFacultad + ", nombre=" + nombre + ", activo=" + activo + '}';
    }
}
