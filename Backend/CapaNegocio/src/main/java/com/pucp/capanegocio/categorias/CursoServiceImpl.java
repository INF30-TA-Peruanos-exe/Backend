/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pucp.capanegocio.categorias;

import com.pucp.capadominio.categorias.Curso;
import com.pucp.capanegocio.interfacesService.CursoService;
import com.pucp.da.categorias.CursoCRUD;
import com.pucp.interfacesDAO.CursoDAO;
import java.util.ArrayList;

/**
 *
 * @author SEBASTIAN
 */
public class CursoServiceImpl implements CursoService{
    private final CursoDAO cursoDAO;
    
    public CursoServiceImpl(){
        this.cursoDAO = new CursoCRUD();
    }

    @Override
    public void registrarCurso(Curso curso) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actualizarCurso(Curso curso) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminarCurso(int idCurso) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Curso obtenerCurso(int idCurso) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Curso> listarCurso() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
