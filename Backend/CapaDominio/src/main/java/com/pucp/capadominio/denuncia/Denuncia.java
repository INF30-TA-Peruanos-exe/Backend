/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pucp.capadominio.denuncia;

import com.pucp.capadominio.usuarios.Usuario;
import com.pucp.capadominio.usuarios.Administrador;
import com.pucp.capadominio.publicacion.Publicacion;
import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author Axel
 */
public class Denuncia {
    //ATRIBUTOS
    private int idDenuncia;
    private Publicacion autor;
    private Usuario denunciante;
    private String motivo;
    private Date fechaDenuncia;
    private Administrador admin;
    private boolean activo;
    
    //CONSTRUCTORES
    public Denuncia() {
        this.autor = new Publicacion();
        this.denunciante = new Usuario();
        this.admin = new Administrador();
        this.fechaDenuncia = Date.valueOf(LocalDate.now());
    }
    
    //Falta implementar el constructor copia para PUBLICACION, USUARIO
    public Denuncia(int idDenuncia, Publicacion autor, Usuario reportante, String motivo,
            boolean activo, Administrador admin) {
        this.idDenuncia = idDenuncia;
        this.autor = autor;
        this.denunciante = reportante;
        this.motivo = motivo;
        this.fechaDenuncia = Date.valueOf(LocalDate.now());
        this.activo = activo;
        this.admin = admin;
    }
    
    //GETTERS & SETTERS
    public int getIdDenuncia() {
        return idDenuncia;
    }

    public void setIdDenuncia(int idDenuncia) {
        this.idDenuncia = idDenuncia;
    }

    public Publicacion getAutor() {
        return autor;
    }

    public void setAutor(Publicacion autor) {
        this.autor = autor;
    }

    public Usuario getDenunciante() {
        return denunciante;
    }

    public void setDenunciante(Usuario denunciante) {
        this.denunciante = new Usuario(denunciante);
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Date getFechaDenuncia() {
        return fechaDenuncia;
    }

    public void setFechaDenuncia(Date fechaDenuncia) {
        this.fechaDenuncia = fechaDenuncia;
    }   

    public Administrador getAdmin() {
        return admin;
    }

    public void setAdmin(Administrador admin) {
        this.admin = admin;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    
    @Override
    public String toString() {
        return "Denuncia{" + "idDenuncia=" + idDenuncia + ", autor=" + autor.getIdPublicacion()+ ", denunciante=" + denunciante.getIdUsuario() + ", motivo=" + motivo + ", fechaDenuncia=" + fechaDenuncia + '}';
    }
}
