/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pucp.capanegocio.categorias;

import com.pucp.capadominio.categorias.Especialidad;
import com.pucp.capanegocio.interfacesService.EspecialidadService;
import com.pucp.da.categorias.EspecialidadCRUD;
import com.pucp.interfacesDAO.EspecialidadDAO;
import java.util.ArrayList;

/**
 *
 * @author Axel
 */
public class EspecialidadServiceImpl implements EspecialidadService{
    private final EspecialidadDAO especialidadDAO;
    
    public EspecialidadServiceImpl(){
        this.especialidadDAO = new EspecialidadCRUD();
    }

    @Override
    public void registrarEspecialidad(Especialidad especialidad) throws Exception {
        if(especialidad.getNombre() == null || especialidad.getNombre().trim().isEmpty()){
            throw new Exception("El nombre no puede estar vacío");
        }
        
        especialidadDAO.insertar(especialidad);
    }

    @Override
    public void actualizarEspecialidad(Especialidad especialidad) throws Exception {
        if(especialidadDAO.obtenerPorId(especialidad.getIdEspecialidad())==null){
            throw new Exception("La especialidad no existe");
        }
        
        if(especialidad.getNombre() == null || especialidad.getNombre().trim().isEmpty()){
            throw new Exception("El nombre no puede estar vacío");
        }
        
        especialidadDAO.actualizar(especialidad);    
    }

    @Override
    public void eliminarEspecialidad(int idEspecialidad) throws Exception {
        Especialidad especialidad = especialidadDAO.obtenerPorId(idEspecialidad);
        if(especialidad == null){
            throw new Exception("La especialidad no existe");
        }
        especialidadDAO.eliminar(idEspecialidad);
    }

    @Override
    public Especialidad obtenerEspecialidad(int idEspecialidad) throws Exception {
        Especialidad especialidad = especialidadDAO.obtenerPorId(idEspecialidad);
        if(especialidad == null){
            throw new Exception("La especialidad no existe");
        }
        return especialidad;
    }
    
    @Override
    public ArrayList<Especialidad> listarEspecialidad() throws Exception {
        return especialidadDAO.listarTodos();
    }
    
}
