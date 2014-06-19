<%@page import="java.net.URLDecoder"%>
<%@page import="Controlador.AES"%>
<!DOCTYPE HTML >
<%@page contentType="java"%>
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
        <script src="js/empleado.js"></script>
    </head>
    <body>
        <ul id='navigatione'>
            <li id="crearE"><a href='#'><p><b>Crear</b></p></a></li>
            <li id="buscarE"><a href='#'><p><b>Buscar</b></p></a></li>
        </ul>
        <div class="form1">
            <form id="crearEmpleado">
                <fieldset>
                    <legend><h4>Empleado</h4></legend>
                    <table align="center">
                        <tr>
                            <td><label for="Nombre"><span style="color:#D3362D;">*</span>Nombre</label><br>
                                <input type="TextField" id="Nombre" class="form" placeholder="Nombre" required>
                            <input type="TextField" id="Apellido" class="form" placeholder="Apellido" required></td>
                        </tr>
                        <tr>
                            <td colspan="2"></td>
                        </tr>
                        <tr>
                            <td><label for="Cedula"><span style="color:#D3362D;">*</span>C&eacute;dula</label><br>
                            <input type="TextField" id="Cedula" onkeypress="return justNumbers(event);" class="form" width="145px" required></td>
                        </tr>
                        <tr>
                            <td></td>
                        </tr>
                        <tr>
                            <td><label for="Cargo"><span style="color:#D3362D;">*</span>Cargo</label><br>
                                <select id="Cargo" class="form" required>
                                    <option value="Seleccionar">-Seleccionar-</option>
                                    <option value="administrador">Administrador</option>
                                    <option value="extrusor">Extrusor</option>
                                    <option value="impresor">Impresor</option>
                                    <option value="sellador">Sellador</option>
                            </select></td>
                        </tr>
                        <tr>
                            <td></td>
                        </tr>
                        <tr>
                            <td><label for="Codigo"><span style="color:#D3362D;">*</span>Contrase&ntilde;a</label><br>
                                <input type="password" id="Codigo" class="form" placeholder="Contrase&ntilde;a" required>
                                <input type="password" id="ConCodigo" class="form" placeholder="Confirmar contrase&ntilde;a" required></td>
                        </tr>
                        <tr>
                            <td colspan="2"></td>
                        </tr>
                    </table>
                    <span style="color:#D3362D;">* </span><span><b>Campo obligatorio</b></span>
                    <br />
                    <br />
                </fieldset>
                <table align="center">
                    <tr>
                        <td align="right"><input type="submit" class="buttons" id="registerEmp" value="Registrar"></td>
                        <td><input type="reset" id="borrar" class="buttons" value="Limpiar"></td><br>
                    </tr>
                    <tr>
                        <td colspan="2" id="Mensaje">

                        </td>
                    </tr>
                </table>
            </form>
        </div>
        <script src="js/crearEmpleado.js"></script>
    </body>
</html>
<%}else{
    
}
%>