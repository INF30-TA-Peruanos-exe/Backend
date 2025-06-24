/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pucp.capanegocio.notificaciones;
import com.pucp.capadominio.publicacion.Publicacion;
import com.pucp.capadominio.usuarios.Usuario;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;

/**
 *
 * @author Jhonatan
 */
public class correoPorPubliGuardado {
    private static final String EMAIL_FROM="qhatupucp@gmail.com";
    private static String EMAIL_TO = null;
    private static final String APP_PASSWORD="dtwx gvhi hemg mvwt";

    public correoPorPubliGuardado(){
    }
    
    private static Session getEmailSession() {
        return Session.getInstance(getGmailProperties(), new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL_FROM, APP_PASSWORD);
            }
        });
    }

    private static Properties getGmailProperties() {
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.ssl.trust","smtp.gmail.com");
        return prop;
    }
    
    public void enviarCorreoPorGuardado(Publicacion publicacion,Usuario usQueGuardo){
        Message mensaje = new MimeMessage(getEmailSession());
        Usuario publicante = publicacion.getUsuario();
        EMAIL_TO = publicante.getCorreo();
        try{
            mensaje.setFrom(new InternetAddress(EMAIL_FROM));
            mensaje.setRecipients(Message.RecipientType.TO, InternetAddress.parse(EMAIL_TO));
            String asunto = "¡Tu publicación ha sido guardada!"; 
            mensaje.setSubject(asunto);
            String textoCuerpo = "El usuario '" + usQueGuardo.getNombreUsuario()
                    + "' ha guardado tu publicación titulada: \"" + publicacion.getTitulo() + "\".\n\n"
                    + "Sigue publicando en PUCPQhatu para llegar a más personas.\n\n"
                    + "Este mensaje fue generado automáticamente desde la aplicación PUCPQhatu.";

            mensaje.setText(textoCuerpo);
            Transport.send(mensaje);
        }catch(Exception ex){
            System.err.println("Error al enviar correo: " + ex.getMessage());
        }
    }
}

