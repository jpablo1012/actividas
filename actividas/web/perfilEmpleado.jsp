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
        <script src="js/perfilEmpleado.js"></script>
    </head>
    <body>

        <div class="form1">
            <form id="perfilEmpleado">
                <fieldset>
                    <legend><h4 align="center">Perfil</h4></legend>
                    <input type="button" class="edit" id="editar" value="Editar"/>
                    <%--   <img id="lala" style="width: 70%; height: 393px;"> --%>
                    <table id="perfile" class="perfilc" style="margin: 10px 100px; position: relative; left: 15%;" align="center" cellspacing="10">
                        <tr style="display: none;">
                            <td colspan="2"><label id="IdUsuario"></label></td>
                        </tr>
                        <tr>
                            <td><label>Nombre:</label></td>
                            <td><label id="nombree"></label><label id="apellidoe"></label>
                                <input id="txtnombre" style="display: none; width: 120px;" type="text" class="form" placeholder="Nombre" required>
                                <input id="txtapellido" style="display: none; width: 120px;" type="text" class="form" placeholder="Apellido" required></td>
                        </tr>
                        <tr>
                            <td><label>C&eacute;dula:</label></td>
                            <td><label id="cedulae"></label></td>
                        </tr>
                        <tr>
                            <td><label>Cargo:</label></td>
                            <td><label id="cargoe"></label></td>
                        </tr>
                        <tr>
                            <td colspan="2"><label id="camcont" style="display: none;"><hr><span style="color:#D3362D;">* </span>Cambio de contraseņa</label></td>
                        </tr>
                        <tr id="conactual" style="visibility:hidden;">
                            <td><label>Contraseņa Actual:</label></td>
                            <td><input id="contactual" type="password" class="form" style="width: 124px;" placeholder="Contraseņa Actual">
                                <label id="clave"></label></td>
                        </tr>
                        <tr id="ncontraseņa" style="visibility:hidden;">
                            <td><label>Nueva contraseņa:</label></td>
                            <td><input id="contraseņa" type="password" class="form" style="width: 124px;" placeholder="Contraseņa">
                                <input id="concontraseņa" type="password" class="form" style="width: 124px;" placeholder="Confirmar contraseņa"></td>
                        </tr>
                        <tr>
                            <td colspan="2" id="mensaje"></td>
                        </tr>
                    </table>
                    <br>
                    <div id="nota" style="display: none;"><span style="color:#D3362D;">* </span><span><b>Nota:</b> Si desea cambiar la contraseņa debera colocar la contraseņa actual y luego la contraseņa nueva en sus campos requeridos</span></div>
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