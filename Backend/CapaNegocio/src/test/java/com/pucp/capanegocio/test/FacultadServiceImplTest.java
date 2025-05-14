/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pucp.capanegocio.test;

import com.pucp.capadominio.categorias.Facultad;
import com.pucp.capanegocio.categorias.FacultadServiceImpl;
import com.pucp.capanegocio.interfacesService.FacultadService;
import java.util.ArrayList;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


/**
 *
 * @author Axel
 */
public class FacultadServiceImplTest{
    private static FacultadService facultadService;
    private static int facultadId;

    @BeforeAll
    public static void setUp() {
        facultadService = new FacultadServiceImpl();
    }

    private Facultad crearFacultadPrueba() {
        Facultad facultad = new Facultad();
        facultad.setNombre("Facultad de Pruebas");
        return facultad;
    }

    @Test
    @Order(1)
    void registrarFacultad() throws Exception {
        Facultad facultad = crearFacultadPrueba();
        facultadService.registrarFacultad(facultad);

        ArrayList<Facultad> facultades = facultadService.listarFacultad();
        assertNotNull(facultades);
        assertFalse(facultades.isEmpty());

        Facultad registrada = facultades.get(facultades.size() - 1);
        facultadId = registrada.getIdFacultad();
        assertEquals("Facultad de Pruebas", registrada.getNombre());
    }

    @Test
    @Order(2)
    void obtenerFacultad() throws Exception {
        Facultad facultad = facultadService.obtenerFacultad(facultadId);
        assertNotNull(facultad);
        assertEquals("Facultad de Pruebas", facultad.getNombre());
    }

    @Test
    @Order(3)
    void actualizarFacultad() throws Exception {
        Facultad facultad = facultadService.obtenerFacultad(facultadId);
        facultad.setNombre("Facultad Modificada");
        facultadService.actualizarFacultad(facultad);

        Facultad actualizada = facultadService.obtenerFacultad(facultadId);
        assertEquals("Facultad Modificada", actualizada.getNombre());
    }

    @Test
    @Order(4)
    void eliminarFacultad() throws Exception {
        Facultad eliminada = facultadService.obtenerFacultad(facultadId);
        assertFalse(eliminada.isActivo());
    }

    @Test
    @Order(5)
    void listarFacultades() throws Exception {
        ArrayList<Facultad> facultades = facultadService.listarFacultad();
        assertNotNull(facultades);
    }
}
