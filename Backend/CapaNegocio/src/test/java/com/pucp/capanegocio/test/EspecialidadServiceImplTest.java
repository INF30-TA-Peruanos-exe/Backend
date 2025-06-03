///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.pucp.capanegocio.test;
//
//import com.pucp.capadominio.categorias.Especialidad;
//import com.pucp.capanegocio.categorias.EspecialidadServiceImpl;
//import com.pucp.capanegocio.interfacesService.EspecialidadService;
//import java.util.ArrayList;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.MethodOrderer;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//
///**
// *
// * @author Axel
// */
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//public class EspecialidadServiceImplTest{
//    private static EspecialidadService especialidadService;
//    private static int especialidadId;
//
//    @BeforeAll
//    public static void setUp() {
//        especialidadService = new EspecialidadServiceImpl();
//    }
//
//    private Especialidad crearEspecialidadPrueba() {
//        Especialidad especialidad = new Especialidad();
//        especialidad.setNombre("EspecialidadPrueba");
//        especialidad.setActivo(true);
//        return especialidad;
//    }
//
//    @Test
//    @Order(1)
//    void registrarEspecialidad() throws Exception {
//        Especialidad especialidad = crearEspecialidadPrueba();
//        especialidadService.registrarEspecialidad(especialidad);
//
//        ArrayList<Especialidad> lista = especialidadService.listarEspecialidad();
//        assertNotNull(lista);
//        assertFalse(lista.isEmpty());
//
//        Especialidad registrada = lista.get(lista.size() - 1);
//        especialidadId = registrada.getIdEspecialidad();
//        assertEquals("EspecialidadPrueba", registrada.getNombre());
//    }
//
//    @Test
//    @Order(2)
//    void obtenerEspecialidad() throws Exception {
//        Especialidad especialidad = especialidadService.obtenerEspecialidad(especialidadId);
//        assertNotNull(especialidad);
//        assertEquals("EspecialidadPrueba", especialidad.getNombre());
//    }
//
//    @Test
//    @Order(3)
//    void actualizarEspecialidad() throws Exception {
//        Especialidad especialidad = especialidadService.obtenerEspecialidad(especialidadId);
//        especialidad.setNombre("Especialidad Modificada");
//        especialidadService.actualizarEspecialidad(especialidad);
//
//        Especialidad actualizada = especialidadService.obtenerEspecialidad(especialidadId);
//        assertEquals("Especialidad Modificada", actualizada.getNombre());
//    }
//
//    @Test
//    @Order(4)
//    void eliminarEspecialidad() throws Exception {
//        especialidadService.eliminarEspecialidad(especialidadId);
//        Especialidad especialidadEliminado = especialidadService.obtenerEspecialidad(especialidadId);
//
//        assertNotNull(especialidadEliminado);
//        assertFalse(especialidadEliminado.isActivo()); // Verifica que esté desactivado
//    }
//
//    @Test
//    @Order(5)
//    void listarEspecialidades() throws Exception {
//        ArrayList<Especialidad> lista = especialidadService.listarEspecialidad();
//        assertNotNull(lista);
//    }     
//}
