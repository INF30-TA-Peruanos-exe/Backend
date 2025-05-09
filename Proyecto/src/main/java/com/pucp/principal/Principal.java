/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.pucp.principal;
import com.pucp.da.notificaciones.NotificacionCRUD;
import com.pucp.da.usuarios.*;
import com.pucp.da.publicaciones.*;
import com.pucp.da.denuncias.DenunciaCRUD;
import com.pucp.modelo.denuncias.Denuncia;
import com.pucp.modelo.notificaciones.Notificacion;
import com.pucp.modelo.notificaciones.TipoNotificacion;
import com.pucp.modelo.usuarios.*;
import com.pucp.modelo.publicaciones.*;
import java.sql.Date;
import java.util.ArrayList;
/**
 *
 * @author Axel
 */
public class Principal {

    public static void main(String[] args) {
        try{
            //Pruebas de Usuario
            Usuario user1= new Usuario();
            //Setear datos del usuario
            user1.setCodigoPUCP(20201941);
            user1.setNombreUsuario("ExcelPremium");
            user1.setContrasena("123456789");
            user1.setNombre("Axel Cardenas");
            user1.setCorreo("axel@gmail.com");
            user1.setEstado(EstadoUsuario.HABILITADO);
            user1.setActivo(true);
            
            //Insertar a la bd
            UsuarioCRUD usuarioCRUD = new UsuarioCRUD();
            usuarioCRUD.insertar(user1);

            //Obtener usuario
            Usuario user2 = usuarioCRUD.obtenerPorId(user1.getIdUsuario());
            System.out.println("Usuario insertado recientemente: ");
            System.out.println(user2);
            System.out.println();
            
            //Actualizar datos
            user2.setCorreo("IloveDogs@gmail.com");
            user2.setNombreUsuario("IloveDogs");
            usuarioCRUD.actualizar(user2);
            
            ArrayList<Usuario> usuarios = usuarioCRUD.listarTodos();
            for(Usuario user : usuarios){
                System.out.println(user);
            }  
            System.out.println();
            
            //-----------------------------------
            //Pruebas de Administrador
            Administrador admin1 = new Administrador();
            //Setear datos del Administrador
            admin1.setCodigoPUCP(20201942);
            admin1.setNombreUsuario("ExcelAdmin");
            admin1.setContrasena("123456789");
            admin1.setNombre("Axel Cardenas");
            admin1.setCorreo("axel.cardenas@gmail.com");
            admin1.setEstado(EstadoUsuario.HABILITADO);
            admin1.setActivo(true);
            admin1.setClaveMaestra("0000");
            //Insertar a la bd
            AdministradorCRUD adminCRUD = new AdministradorCRUD();
            adminCRUD.insertar(admin1);

            //Obtener Administrador
            Administrador admin2 = adminCRUD.obtenerPorId(admin1.getIdUsuario());
            System.out.println("Administrador insertado recientemente: ");
            System.out.println(admin2);
            System.out.println();
            
            //Actualizar datos
            admin2.setCorreo("IloveCats@gmail.com");
            admin2.setNombreUsuario("IloveCats");
            adminCRUD.actualizar(admin2);
            
            ArrayList<Administrador> administradores = adminCRUD.listarTodos();
            for(Administrador admin : administradores){
                System.out.println(admin);
            }  
            System.out.println();
            //-----------------------------------
            //Pruebas de Publicacion
            Publicacion publi1 = new Publicacion();
            //Setear datos del Administrador
            publi1.setTitulo("Calculadora Premium");
            publi1.setDescripcion("Calculadora Cientifica x1000");
            publi1.setEstado(EstadoPublicacion.VISIBLE);
            publi1.setFechaPublicacion(Date.valueOf("2025-04-22"));
            publi1.setRutaImagen("example_url");
            publi1.setActivo(true);
            //Insertar a la bd
            PublicacionCRUD publiCRUD = new PublicacionCRUD();
            publiCRUD.insertar(publi1);

            //Obtener Publicacion
            Publicacion publi2 = publiCRUD.obtenerPorId(publi1.getIdPublicacion());
            System.out.println("Publicacion insertado recientemente: ");
            System.out.println(publi2);
            System.out.println();
            
            //Actualizar datos
            publi2.setTitulo("Calculadora para Ciencias");
            publiCRUD.actualizar(publi2);
            
            ArrayList<Publicacion> publicaciones = publiCRUD.listarTodos();
            for(Publicacion publi : publicaciones){
                System.out.println(publi);
            }  
            System.out.println();
            //-----------------------------------
            //Pruebas de Comentario
            Comentario com1 = new Comentario();
            //Setear datos del Comentario
            com1.setContenido("Muy buen producto");
            com1.setValoracion(5);
            com1.setFecha(Date.valueOf("2025-04-22"));
            com1.setPublicacion(publi2);
            com1.setComentador(user2);
            com1.setActivo(true);
            
            //Insertar a la bd
            ComentarioCRUD comCRUD = new ComentarioCRUD();
            comCRUD.insertar(com1);

            //Obtener Comentario
            Comentario com2 = comCRUD.obtenerPorId(publi1.getIdPublicacion());
            System.out.println("Publicacion insertado recientemente: ");
            System.out.println(com2);
            System.out.println();
            
            //Actualizar datos
            com2.setValoracion(4);
            comCRUD.actualizar(com2);
            
            ArrayList<Comentario> comentarios = comCRUD.listarTodos();
            for(Comentario com : comentarios){
                System.out.println(com);
            }  
            System.out.println();
            //-----------------------------------
            //Pruebas de Notificacion
            Notificacion not1 = new Notificacion();
            //Setear datos del Notificacion
            not1.setMensaje("Hola, lo quiero");
            not1.setTipoNotificacion(TipoNotificacion.COMENTADA);
            not1.setCantidad(1);
            not1.setFecha(Date.valueOf("2025-04-22"));
            not1.setAutor(publi2);
            not1.setNotificador(user2);
            not1.setActivo(true);
            
            //Insertar a la bd
            NotificacionCRUD notCRUD = new NotificacionCRUD();
            notCRUD.insertar(not1);

            //Obtener Notificacion
            Notificacion not2 = notCRUD.obtenerPorId(not1.getIdNotificacion());
            System.out.println("Notificacion insertado recientemente: ");
            System.out.println(not2);
            System.out.println();
            
            //Actualizar datos
            not2.setMensaje("Ahi nomas, ya no quiero");
            not2.setCantidad(0);
            notCRUD.actualizar(not2);
            
            ArrayList<Notificacion> notificaciones = notCRUD.listarTodos();
            for(Notificacion not : notificaciones){
                System.out.println(not);
            } 
            System.out.println();
            //-----------------------------------
            //Pruebas de Denuncia
            Denuncia den1 = new Denuncia();
            //Setear datos del Denuncia
            den1.setAutor(publi2);
            den1.setDenunciante(user2);
            den1.setMotivo("Me confundo");
            den1.setFechaDenuncia(Date.valueOf("2025-04-22"));
            den1.setAdmin(admin2);
            den1.setActivo(true);
            
            //Insertar a la bd
            DenunciaCRUD denCRUD = new DenunciaCRUD();
            denCRUD.insertar(den1);

            //Obtener Denuncia
            Denuncia den2 = denCRUD.obtenerPorId(not1.getIdNotificacion());
            System.out.println("Denuncia insertado recientemente: ");
            System.out.println(den2);
            System.out.println();
            
            //Actualizar datos
            den1.setMotivo("No se entiende");
            denCRUD.actualizar(den2);
            
            ArrayList<Denuncia> denuncias = denCRUD.listarTodos();
            for(Denuncia den : denuncias){
                System.out.println(den);
            } 
            
        } catch(Exception ex){
            ex.printStackTrace();
        }
        
    }
}
