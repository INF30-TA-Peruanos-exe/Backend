package com.pucp.webservice.usuarios;

import com.pucp.capadominio.usuarios.Usuario;
import com.pucp.capanegocio.usuarios.UsuarioServiceImpl;
import com.pucp.capanegocio.interfacesService.UsuarioService;
import com.pucp.config.DBManager;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.ws.WebServiceException;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

@WebService(serviceName = "UsuarioWS", targetNamespace = "com.pucp.pucpqhatu")
public class UsuarioWS {

    private final UsuarioService usuarioService;

    public UsuarioWS() {
        usuarioService = new UsuarioServiceImpl();
    }

    @WebMethod(operationName = "registrarUsuario")
    public void registrarUsuario(@WebParam(name = "usuario") Usuario usuario) {
        try {
            usuarioService.registrarUsuario(usuario);
        } catch (Exception ex) {
            throw new WebServiceException("Error al registrar usuario: " + ex.getMessage());
        }
    }

    @WebMethod(operationName = "actualizarUsuario")
    public void actualizarUsuario(@WebParam(name = "usuario") Usuario usuario) {
        try {
            usuarioService.actualizarUsuario(usuario);
        } catch (Exception ex) {
            throw new WebServiceException("Error al actualizar usuario: " + ex.getMessage());
        }
    }

    @WebMethod(operationName = "eliminarUsuario")
    public boolean eliminarUsuario(@WebParam(name = "idUsuario") int idUsuario) {
        try {
            usuarioService.eliminarUsuario(idUsuario);
            return true;
        } catch (Exception ex) {
            throw new WebServiceException("Error al eliminar usuario: " + ex.getMessage());
        }
    }

    @WebMethod(operationName = "obtenerUsuario")
    public Usuario obtenerUsuario(@WebParam(name = "idUsuario") int idUsuario) {
        try {
            return usuarioService.obtenerUsuario(idUsuario);
        } catch (Exception ex) {
            throw new WebServiceException("Error al obtener usuario: " + ex.getMessage());
        }
    }

    @WebMethod(operationName = "listarUsuarios")
    public ArrayList<Usuario> listarUsuarios() {
        try {
            return usuarioService.listarUsuario();
        } catch (Exception ex) {
            throw new WebServiceException("Error al listar usuarios: " + ex.getMessage());
        }
    }
    
    @WebMethod(operationName = "obtenerUsuarioPorCorreoYContra")
    public Usuario obtenerUsuarioPorCorreoYContra(@WebParam(name = "correo") String correo, @WebParam(name = "contra") String contra) {
        try {
            return usuarioService.obtenerUsuarioPorCorreoYContra(correo, contra);
        } catch (Exception ex) {
            throw new WebServiceException("Error al obtener usuario: " + ex.getMessage());
        }
    }
   
    @WebMethod(operationName = "reporteUsuarios")
    public byte[] reporteUsuarios(){
        try{
            Map<String, Object> params = new HashMap<>(); 
            params.put("logo", ImageIO.read(getClass().getClassLoader().getResourceAsStream("QhatuPucp.png")));
            
            String fileXML = getFileResource("Top_Usuarios.jrxml");

            return generarBufferFromJP(fileXML, params);
        }catch(Exception ex){
            throw new WebServiceException("Error al generar report: " + ex.getMessage());
        }
    }
    
    private String getFileResource(String fileName){ 
        String filePath = getClass().getClassLoader().getResource(fileName).getPath();
        filePath = filePath.replace("%20", " ");
        return filePath;
    }
    
    private byte[] generarBufferFromJP(String inFileXML, Map<String, Object> params) throws JRException, SQLException {
        // Compilar reporte principal si no existe
        String fileJasper = inFileXML.replace(".jrxml", ".jasper");
        if (!new File(fileJasper).exists()) {
            JasperCompileManager.compileReportToFile(inFileXML, fileJasper);
        }

        // Cargar y llenar el reporte
        JasperReport jr = (JasperReport) JRLoader.loadObjectFromFile(fileJasper);
        Connection conn = DBManager.getInstance().obtenerConexion();
        JasperPrint jp = JasperFillManager.fillReport(jr, params, conn);
        return JasperExportManager.exportReportToPdf(jp);
    }
}
