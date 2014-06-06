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
        <script src="js/botones.js"></script>
        <script src="js/crearInsumos.js"></script>
        <script src="js/insumos.js"></script>
    </head>
    <body>
        <ul id='navigatione'>
            <li id="crearM"><a href='#'><p><b>Crear Material</b></p></a></li>
            <li id="crearCo"><a href='#'><p><b>Crear Color</b></p></a></li>
            <li id="crearB"><a href='#'><p><b>Crear Bolsa</b></p></a></li>
            <li id="buscarI"><a href='#'><p><b>Buscar</b></p></a></li>
        </ul>
        <div class="form1">
            <form id="crearMaterial">
                <fieldset>
                    <legend><h4 align="center">Crear Material</h4></legend>
                    <table align="center" cellspacing="10">
                        <tr>
                            <td><label for="nombrem"><span style="color:#D3362D;">* </span>Nombre</label><br>
                            <input type="TextField" id="nombrem" class="form" width="145px" required></td>
                        </tr>
                        <tr>
                            <td></td>
                        </tr>
                        <tr>
                            <td><label for="cantidadm">Cantidad</label><br>
                            <input type="TextField" id="cantidadm" class="form" width="145px" placeholder="kilos"></td>
                        </tr>
                        <tr>
                            <td></td>
                        </tr>
                    </table>
                    <br>
                    <br>
                    <span style="color:#D3362D;">* </span><span><b>Campo obligatorio</b></span>
                    <br>
                    <br>
                </fieldset>
                <table align="center">
                    <tr>
                        <td align="right"><input type="submit" class="buttons" id="registerM" value="Crear material"></td>
                        <td><input type="reset" id="borrar" class="buttons" value="Limpiar"><br></td>
                    </tr>
                    <tr>
                        <td colspan="2" id="Mensaje">

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