<%-- 
    Document   : sendEmail
    Created on : Nov 26, 2010, 11:28:24 AM
    Author     : vianey
--%>

<%@ page import="java.util.*,java.io.*,javax.mail.*, javax.mail.internet.*, javax.mail.Authenticator" %>
<% String conf_path = pageContext.getServletContext().getRealPath("/"); %>


<html>
    <head>
        <title>Enviando...</title>
    </head>
    <body>
        <%
            String to[] = new String[1];
            //Destinatario del correo (Debe ser una cuenta real)
            to[0] = "actividas@outlook.com";
            //Asunto del mensaje
            String subject = "Plastiser.com - mensaje contacto";
            //Cuenta desde la cual se envia el mensaje
            String from = "nnn@nn.com";
            //Mensaje que contendra el correo (En este caso, lo tomamos del formulario)
            String message = request.getParameter("nombre")+"\n"+
                             request.getParameter("from")+"\n"+
                             request.getParameter("comments");
            /**
             * Metodo de envío del Email
             */
            sendEmail("smtp.live.com", subject, to, from, message);
        %>
    </body>
</html>
<%!
    public void sendEmail(String mailServer, String subject, String to[], String from, String messageText) throws AddressException, MessagingException {

        /**
         * Propiedades del servidor de envío
         */
        Properties properties = new Properties();
        properties.put("mail.smtp.host", mailServer);
        properties.put("mail.transport.protocol", "SMPT");
        properties.put("mail.smtp.starttls.enable", "true");
        //puerto de envío, cambia segun el servidor
        properties.put("mail.smtp.port", "587");
        //Cuenta que se usara para enviar los correos
        properties.put("mail.user", "actividas@outlook.com");
        //Contraseña real de la cuenta
        properties.put("mail.password", "04101112$");
        properties.put("mail.smtp.auth", "true");


        try {
            Authenticator auth = new SMTPAuthenticator();
            //cramos una sesion del servidor de mail
            javax.mail.Session session = Session.getInstance(properties, auth);
            javax.mail.Message message = new MimeMessage(session);

            message.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(to[0]));
            InternetAddress dir = new InternetAddress(from);
            message.addFrom(new InternetAddress[]{dir});
            //Agregamos al paquete de envio el asunto
            message.setSubject(subject);
            //Agregamos el mensaje, al paquete de envio
            message.setContent(messageText, "text/plain");

            Transport transport = session.getTransport("smtp");
            transport.connect();
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

        } catch (MessagingException me) {
            me.printStackTrace();
        } catch (Exception me) {
            me.printStackTrace();
        }

    }

    private class SMTPAuthenticator extends javax.mail.Authenticator {

        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication("actividas@outlook.com", "04101112$");
        }
    }
%>
<script language="JavaScript">
    alert("El mensaje se ha enviado exitosamente.", location.replace("contactenos.jsp"));
</script>

