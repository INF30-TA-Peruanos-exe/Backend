/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pucp.capanegocio.categorias;

import com.pucp.capadominio.categorias.Facultad;
import com.pucp.capanegocio.interfacesService.FacultadService;
import com.pucp.da.categorias.FacultadCRUD;
import com.pucp.interfacesDAO.FacultadDAO;
import java.util.ArrayList;

/**
 *
 * @author Axel
 */
public class FacultadServiceImpl implements FacultadService{
    private final FacultadDAO facultadDAO;
    
    public FacultadServiceImpl(){
        this.facultadDAO = new FacultadCRUD();
    }

    @Override
    public void registrarFacultad(Facultad facultad) throws Exception {
        //Validaciones
        if(facultad.getNombre() == null || facultad.getNombre().trim().isEmpty()){
            throw new Exception("El nombre no puede estar vacio");
        }
        
        //Llamar al DAO
        facultadDAO.insertar(facultad);
    }

    @Override
    public void actualizarFacultad(Facultad facultad) throws Exception {
        //Validar existencia
        if(facultadDAO.obtenerPorId(facultad.getIdFacultad()) == null){
            throw  new Exception("El Facultad no existe");
        }
        
        //Validaciones
        if(facultad.getNombre() == null || facultad.getNombre().trim().isEmpty()){
            throw new Exception("El nombre no puede estar vacio");
        }
        
        //Llamar al DAO
        facultadDAO.actualizar(facultad);
    }

    @Override
    public void eliminarFacultad(int idFacultad) throws Exception {
        Facultad facultad = facultadDAO.obtenerPorId(idFacultad);
        if(facultad == null){
            throw new Exception("El Facultad no existe");
        }
        
        facultadDAO.eliminar(idFacultad);
    }

    @Override
    public Facultad obtenerFacultad(int idFacultad) throws Exception {
        Facultad facultad = facultadDAO.obtenerPorId(idFacultad);
        if(facultad == null){
            throw new Exception("Facultad no encontrado");
        }
        
        return facultad;
    }

    @Override
    public ArrayList<Facultad> listarFacultad() throws Exception {
        return facultadDAO.listarTodos();
    }
    
}
