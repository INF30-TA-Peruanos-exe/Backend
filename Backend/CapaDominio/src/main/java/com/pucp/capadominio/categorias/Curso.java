/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pucp.capadominio.categorias;


//nuevo
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "curso")  // Muy importante
@XmlType(propOrder = { "idCurso", "nombre", "activo" })  // Opcional pero recomendado para orden
public class Curso {
    //ATRIBUTOS
    private int idCurso;
    private String nombre;
    private boolean activo;
    
    //CONSTRUCTORES
    public Curso() {
        
    }

    public Curso(int idCurso, String nombre, boolean activo) {
        this.idCurso = idCurso;
        this.nombre = nombre;
        this.activo = activo;
    }

    //GETTERS & SETTERS
    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
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
        return "Curso{" + "idCurso=" + idCurso + ", nombre=" + nombre + ", activo=" + activo + '}';
    }
}
