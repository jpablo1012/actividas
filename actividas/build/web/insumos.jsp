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
            <h4 style="text-align:center">Insumos</h4>
        </div>
    </body>
</html>
<%}else{
    
}
%>