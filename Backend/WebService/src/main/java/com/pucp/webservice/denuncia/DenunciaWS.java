/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package com.pucp.webservice.denuncia;

import com.pucp.capadominio.denuncia.Denuncia;
import com.pucp.capanegocio.denuncias.DenunciaServiceImpl;
import com.pucp.capanegocio.interfacesService.DenunciaService;
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

/**
 *
 * @author Axel
 */
@WebService(serviceName = "DenunciaWS", targetNamespace = "com.pucp.pucpqhatu")
public class DenunciaWS {

    private final DenunciaService denunciaService;
    
    public DenunciaWS(){
        denunciaService = new DenunciaServiceImpl();
    }
    
    @WebMethod(operationName = "registrarDenuncia")
    public void registrarDenuncia(@WebParam(name = "denuncia") Denuncia denuncia){
        try {
            denunciaService.registrarDenuncia(denuncia);
        } catch (Exception ex) {
            throw new WebServiceException("Error al registrar denuncia: " + ex.getMessage());
        }
    }
    
    @WebMethod(operationName = "actualizarDenuncia")
    public void actualizarDenuncia(@WebParam(name = "denuncia") Denuncia denuncia){
        try {
            denunciaService.actualizarDenuncia(denuncia);
        } catch (Exception ex) {
            throw new WebServiceException("Error al actualizar facultad: " + ex.getMessage());
        }
    }
    
    @WebMethod(operationName = "eliminarDenuncia")
    public void eliminarDenuncia(@WebParam(name = "idDenuncia") int idDenuncia){
        try {
            denunciaService.eliminarDenuncia(idDenuncia);
        } catch (Exception ex) {
            throw new WebServiceException("Error al eliminar denuncia: " + ex.getMessage());
        }
    }
    
    @WebMethod(operationName = "obtenerDenuncia")
    public Denuncia obtenerDenuncia(@WebParam(name = "idDenuncia") int idDenuncia){
        try {
            return denunciaService.obtenerDenuncia(idDenuncia);
        } catch (Exception ex) {
            throw new WebServiceException("Error al obtener denuncia: " + ex.getMessage());
        }        
    }
    
    @WebMethod(operationName = "listarDenuncia")
    public ArrayList<Denuncia> listarDenuncia(){
        try {
            return denunciaService.listarDenuncia();
        } catch (Exception ex) {
            throw new WebServiceException("Error al listar denuncias: " + ex.getMessage());
        }        
    }
    
    @WebMethod(operationName = "reporteIncidencias")
    public byte[] reporteIncidencias(){
        try{
            Map<String, Object> params = new HashMap<>(); 
            params.put("logo",ImageIO.read(new File(getFileResource("pucp_logo.png"))));
            
            String fileXML = getFileResource("Publicaciones.jrxml");
            
            // Ruta del subreporte (solo para obtener el directorio)
            String subreportPath = getFileResource("Denuncias.jrxml");
            String subreportDir = subreportPath.substring(0, subreportPath.lastIndexOf("/") + 1);
            params.put("SUBREPORT_DIR", subreportDir);  // Necesario para el subreporte

            return generarBufferFromJP(fileXML, subreportPath, params);
        }catch(Exception ex){
            throw new WebServiceException("Error al generar report: " + ex.getMessage());
        }
    }
    
    private String getFileResource(String fileName){ 
        String filePath = getClass().getClassLoader().getResource(fileName).getPath();
        filePath = filePath.replace("%20", " ");
        return filePath;
    }
    
    private byte[] generarBufferFromJP(String inFileXML, String subreportJRXML, Map<String, Object> params) throws JRException, SQLException {
        // Compilar subreporte si no existe
        String subreportJasper = subreportJRXML.replace(".jrxml", ".jasper");
        if (!new File(subreportJasper).exists()) {
            JasperCompileManager.compileReportToFile(subreportJRXML, subreportJasper);
        }

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
