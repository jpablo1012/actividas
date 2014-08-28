<%-- 
    Document   : perfilCliente
    Created on : 21/10/2013, 11:42:35 PM
    Author     : Oscar
--%>
<%@page import="java.net.URLDecoder"%>
<%@page import="Controlador.AES"%>
<!DOCTYPE HTML >
<%@page contentType="java/html"%>
<%
    AES b = new AES();
    b.setKey("actividas");
    Cookie [] cok = null;
    cok = request.getCookies();
    String val = cok[4].getValue();
    try {
	    val = URLDecoder.decode(val, "UTF-8");
	} catch (Exception e1) {
	}
    b.decrypt(val);
    if(b.getDecryptedString().equals("validado")){   
%>
<html>
    <head>
        <script src="js/perfilCliente.js"></script>
    </head>
    <body>
        
        <div class="form1">
            <form id="perfilCliente">
                <fieldset>
                    <legend><h4 align="center">Perfil</h4></legend>
                    <input type="button" class="edit" id="editar" value="Editar"/>
                 <%--   <img id="lala" style="width: 70%; height: 393px;"> --%>
                    <table id="perfilc" class="perfilc" style="margin: 10px 100px; position: relative; left: 15%;" align="center" cellspacing="10">
                        <tr style="display: none;">
                            <td colspan="2"><label id="IdUsuario"></label></td>
                        </tr>
                        <tr>
                            <td><label>Nombre:</label></td>
                            <td><label id="nombrec"></label><label id="apellidoc"></label>
                            <input id="txtnombre" style="display: none; width: 120px;" type="text" class="form" placeholder="Nombre" required>
                            <input id="txtapellido" style="display: none; width: 120px;" type="text" class="form" placeholder="Apellido" required></td>
                        </tr>
                        <tr>
                            <td><label>C&eacute;dula:</label></td>
                            <td><label id="cedulac"></label></td>
                        </tr>
                        <tr>
                            <td><label>Direcci&oacute;n:</label></td>
                            <td><label id="direccionc"></label>
                            <input id="txtdireccion" style="display: none; width: 120px;" type="text" class="form" required></td>
                        </tr>
                        <tr>
                            <td><label>T&eacute;lefono:</label></td>
                            <td><label id="telefonoc"></label>
                            <input id="txttelefono" style="display: none; width: 88px;" type="text" class="form" required></td>
                        </tr>
                        <tr>
                            <td><label>Ciudad:</label></td>
                            <td><label id="ciudadc"></label>
                            <input id="txtciudad" style="display: none; width: 88px;" type="text" class="form" required></td>
                        </tr>
                        <tr>
                            <td><label>Correo:</label></td>
                            <td><label id="mailc"></label>
                            <input id="txtmail" style="display: none; width: 150px;" type="text" class="form" required></td>
                        </tr>
                        <tr>
                            <td colspan="2"><label id="camcont" style="display: none;"><hr><span style="color:#D3362D;">* </span>Cambio de contraseña</label></td>
                        </tr>
                        <tr id="conactual" style="visibility:hidden;">
                            <td><label>Contraseña Actual:</label></td>
                            <td><input id="contactual" type="password" class="form" style="width: 124px;" placeholder="Contraseña Actual">
                            <label id="clave"></label></td>
                        </tr>
                        <tr id="ncontraseña" style="visibility:hidden;">
                            <td><label>Nueva contraseña:</label></td>
                            <td><input id="contraseña" type="password" class="form" style="width: 124px;" placeholder="Contraseña">
                            <input id="concontraseña" type="password" class="form" style="width: 124px;" placeholder="Confirmar contraseña"></td>
                        </tr>
                        <tr>
                            <td colspan="2" id="mensaje"></td>
                        </tr>
                    </table>
                    <br>
                    <div id="nota" style="display: none;"><span style="color:#D3362D;">* </span><span><b>Nota:</b> Si desea cambiar la contraseña debera colocar la contraseña actual y luego la contraseña nueva en sus campos requeridos</span></div>
                    <div id="imagen"></div>
                </fieldset>
                <table align="center">
                    <tr>
                        <td>
                            <center>
                                <input type="submit" style="display: none;" class="buttons" id="modificarp" value="Modificar"/>
                                <input type="button" style="display: none;" class="buttons" id="cancelarm" value="Cancelar"/>
                            </center>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>
<%}else{
    
}
%>