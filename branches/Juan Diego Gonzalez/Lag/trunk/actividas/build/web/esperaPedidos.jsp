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
        <script src="js/esperaPedido.js"></script>
        <script src='js/jquery.modal.js'></script>
        <script src="js/pedido.js"></script>
    </head>
    <body>
        <ul id='navigatione'>
            <li id="Pedidos"><a href='#'><p><b>Crear</b></p></a></li>
            <li id="esperaP"><a href='#'><p><b>En Espera</b></p></a></li>
            <li id="buscarP"><a href='#'><p><b>Buscar</b></p></a></li>

        </ul>
        <div class="form2">
            <fieldset id="answersI">
                <legend><h4>Pedidos En Espera</h4></legend>
                <table align="center" cellpadding="20">
                    <tr>
                        <td>
                            <p id="pedidosE">Cedula</p>
                        </td>
                        <td>
                            <p id="pedidosE">Cliente</p>
                        </td>
                        <td>
                            <p id="pedidosE">Fecha de Entrega</p>
                        </td>
                        <td>
                            <p id="pedidosE">Tipo de Venta</p>
                        </td>
                    </tr>
                </table>
                <div id="pedidosR"></div>
            </fieldset>
        </div>
    </body>
</html>
<%}else{
    
}
%>