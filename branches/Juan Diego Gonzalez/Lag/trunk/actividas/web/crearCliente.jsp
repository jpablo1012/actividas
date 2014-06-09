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
    System.out.print(val);
    b.decrypt(val);
    if(b.getDecryptedString().equals("validado")){   
%>
<html>
    <head>
        <script src="js/menu.js"></script>
    </head>
    <body>
        <ul id='navigatione'>
            <li id="crear"><a href='#'><p><b>Crear</b></p></a></li>
            <li id="buscar"><a href='#'><p><b>Buscar</b></p></a></li>
        </ul>
        <div class="form1">

            <form id="crearCliente">
                <fieldset>
                    <legend><h4 align="center">Cliente</h4></legend>
                    <table align="center">
                        <tr>
                            <td>
                                <label for="Cedula"><span style="color:#D3362D;">*</span>C&eacute;dula</label><br>
                                <input type="text" class="form" id="cedulaf" style="width:100px" onkeypress="return justNumbers(event);" required/>
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                        </tr>
                        <tr>
                            <td>
                                <label for="Nombre"><span style="color:#D3362D;">*</span>Nombre</label><br>
                                <input type="text" class="form" id="nombre" style="width:120px" placeholder=" Nombre" required/>
                                <input type="text" class="form" id="apellido" style="width:120px" placeholder=" Apellido" required/>
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                        </tr>
                        <tr>
                            <td><label for="codigo"><span style="color:#D3362D;">*</span>Contraseña</label><br>
                                <input type="password" class="form" id="codigof" name="codigo" style="width:100px" placeholder="Contraseña" required/>
                                <input type="password" class="form" id="conCodigo" name="concodigo" style="width:100px" placeholder="Confirmar contraseña" required/>
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                        </tr>
                        <tr>
                            <td><label for="Direccion"><span style="color:#D3362D;">*</span>Direcci&oacute;n</label><br>
                                <input type="text" class="form" name="Direccion" id="direccion" style="width:120px" required/>
                                <input type="text" class="form" name="Ciudad" id="ciudad" style="width:80px" placeholder="Ciudad" required/>
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                        </tr>
                        <tr>
                            <td><label for="Direccion"><span style="color:#D3362D;">*</span>Tel&eacute;fono</label><br>
                                <input type="text" class="form" name="Telefono" id="telefono" onkeypress="return justNumbers(event);" style="width:80px" placeholder="Tel&eacute;fono" required/>
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                        </tr>
                        <tr>
                            <td colspan="1"><label for="email"><span style="color:#D3362D;">*</span>Correo</label><br>
                                <input type="email" class="form" name="email" id="email" style="width:150px" />
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                        </tr>
                    </table>
                    <span style="color:#D3362D;">* </span><span><b>Campo obligatorio</b></span>
                    <br><br>
                </fieldset>
                <table align="center">
                    <tr>
                        <td colspan="2">
                    <center>
                        <input type="submit" class="buttons" id="register" value="Crear"/>
                        <input type="reset" class="buttons" value="Limpiar"/>
                    </center>
                    </td>
                    </tr>
                    <tr>
                        <td colspan="2" id="mensaje">

                        </td>
                    </tr>
                </table>
            </form>

        </div>
        <script src="js/crearCliente.js"></script>

    </body>
</html>
<%}else{
    
}
%>