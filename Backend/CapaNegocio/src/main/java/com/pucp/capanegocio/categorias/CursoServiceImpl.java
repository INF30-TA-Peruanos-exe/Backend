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
        //Validaciones
        if(curso.getNombre() == null || curso.getNombre().trim().isEmpty()){
            throw new Exception("El nombre no puede estar vacio");
        }
        
        //Llamar al DAO
        cursoDAO.insertar(curso);
    }

    @Override
    public void actualizarCurso(Curso curso) throws Exception {
        //Validar existencia
        if(cursoDAO.obtenerPorId(curso.getIdCurso()) == null){
            throw  new Exception("El curso no existe");
        }
        
        //Validaciones
        if(curso.getNombre() == null || curso.getNombre().trim().isEmpty()){
            throw new Exception("El nombre no puede estar vacio");
        }
        
        //Llamar al DAO
        cursoDAO.actualizar(curso);
    }

    @Override
    public void eliminarCurso(int idCurso) throws Exception {
        Curso curso = cursoDAO.obtenerPorId(idCurso);
        if(curso == null){
            throw new Exception("El curso no existe");
        }
        
        cursoDAO.eliminar(idCurso);
    }

    @Override
    public Curso obtenerCurso(int idCurso) throws Exception {
        Curso curso = cursoDAO.obtenerPorId(idCurso);
        if(curso == null){
            throw new Exception("Curso no encontrado");
        }
        
        return curso;
    }

    @Override
    public ArrayList<Curso> listarCurso() throws Exception {
        return cursoDAO.listarTodos();
    }
    
}
