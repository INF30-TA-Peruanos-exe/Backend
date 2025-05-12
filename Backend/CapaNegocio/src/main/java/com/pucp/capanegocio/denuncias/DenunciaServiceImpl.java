/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pucp.capanegocio.denuncias;

import com.pucp.capadominio.denuncia.Denuncia;
import com.pucp.capanegocio.interfacesService.DenunciaService;
import com.pucp.da.denuncias.DenunciaCRUD;
import com.pucp.interfacesDAO.DenunciaDAO;
import java.util.ArrayList;

/**
 *
 * @author Axel
 */
public class DenunciaServiceImpl implements DenunciaService{
    private final DenunciaDAO denunciaDAO;
    
    public DenunciaServiceImpl(){
        this.denunciaDAO = new DenunciaCRUD();
    }

    @Override
    public void registrarDenuncia(Denuncia denuncia) throws Exception {
        //En este caso no se si se tiene que validar esto
        //Porque recien se cargara este dato cuando un admin
        //Vea la denuncia
        if(denuncia.getAdmin()==null){
            throw new Exception("Campo por validar");
        }
        if(denuncia.getAutor()==null){
            throw new Exception("El autor no puede estar vacio");
        }
        if(denuncia.getDenunciante()==null){
            throw new Exception("El denunciante no puede estar vacio");
        }
        if(denuncia.getFechaDenuncia()==null){
            throw new Exception("La fecha no puede ser nula");
        }
        if(denuncia.getMotivo()==null || denuncia.getMotivo().trim().isEmpty()){
            throw new Exception("El motivo no puede estar vacio");
        }
        
        denunciaDAO.insertar(denuncia);
    }

    @Override
    public void actualizarDenuncia(Denuncia denuncia) throws Exception {
        if(denunciaDAO.obtenerPorId(denuncia.getIdDenuncia())==null){
            throw new Exception("La denuncia no existe");
        }
        
        if(denuncia.getAdmin()==null){
            throw new Exception("Campo por validar");
        }
        if(denuncia.getAutor()==null){
            throw new Exception("El autor no puede estar vacio");
        }
        if(denuncia.getDenunciante()==null){
            throw new Exception("El denunciante no puede estar vacio");
        }
        if(denuncia.getFechaDenuncia()==null){
            throw new Exception("La fecha no puede ser nula");
        }
        if(denuncia.getMotivo()==null || denuncia.getMotivo().trim().isEmpty()){
            throw new Exception("El motivo no puede estar vacio");
        }
        
        denunciaDAO.actualizar(denuncia);
    }

    @Override
    public void eliminarDenuncia(int idDenuncia) throws Exception {
        Denuncia denuncia = denunciaDAO.obtenerPorId(idDenuncia);
        if(denuncia == null){
            throw new Exception("La denuncia no existe");
        }
        denunciaDAO.eliminar(idDenuncia);
    }

    @Override
    public Denuncia obtenerDenuncia(int idDenuncia) throws Exception {
        Denuncia denuncia = denunciaDAO.obtenerPorId(idDenuncia);
        if(denuncia == null){
            throw new Exception("La denuncia no existe");
        }
        return denuncia;
    }

    @Override
    public ArrayList<Denuncia> listarDenuncia() throws Exception {
        return denunciaDAO.listarTodos();
    }
    
}
