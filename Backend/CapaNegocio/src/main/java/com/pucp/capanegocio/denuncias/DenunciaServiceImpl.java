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
    private final DenunciaDAO DenunciaDAO;
    
    public DenunciaServiceImpl(){
        this.DenunciaDAO = new DenunciaCRUD();
    }

    @Override
    public void registrarDenuncia(Denuncia denuncia) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actualizarDenuncia(Denuncia denuncia) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminarDenuncia(int idDenuncia) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Denuncia obtenerDenuncia(int idDenuncia) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Denuncia> listarDenuncia() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
