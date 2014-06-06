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
        <script src='js/jquery.modal.js'></script>
        <script src='js/buscarPedido.js'></script>
        <script src="js/modfwin.js"></script>
        <script src="js/pedido.js"></script>
        <script src="js/esperaPedido.js"></script>
        <script src="js/botones.js"></script>
        
    </head>
    <body>
        <ul id='navigatione'>
            <li id="Pedidos"><a href='#'><p><b>Crear</b></p></a></li>
            <li id="esperaP"><a href='#'><p><b>En Espera</b></p></a></li>
            <li id="buscarP"><a href='#'><p><b>Buscar</b></p></a></li>
        </ul>
        <div class="form1">
            <form id="buscarPedi">
                <fieldset>
                    <legend><h4 align="center">Buscar Pedido</h4></legend>
                    <table class="infosearch" align="center" cellspacing="10">
          
                        <tr>
                            <td>
                                <input type="text" id="searchP" class="form" style="width:420px;height:28px" required/>
                                <select id="buscarconP" class="form" style="height:28px">
                                    <option value="ninguno">-Seleccionar-
                                    <option value="numeroPedido">Numero pedido
                                    <option value="cliente_cedula">Cedula cliente
                                    <option value="bolsa_referencia">Bolsa Referencia
                                    <option value="estado">Estado
                                     <option value="extrusion">Extrusion
                                      <option value="impresion">Impresion
                                       <option value="sellado">Sellado
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td align="center">
                                <div></div><br>
                                <input type="button" class="buttons" id="buscarped" value="Buscar"/><br><br>
                                <div id="canvasloader-container"></div>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" id="mensajep"></td>
                        </tr>
                    </table>
                </fieldset>
                <fieldset id="answerP" class="effectDes">
                    <table id="resanswerp" align="center" class="resanswerp">

                    </table>
                    <table  style="top:611px;position:absolute;left: 40%"><tr>
                            <td><input type="button" id="masInfoP" class="buttons" value="Mas informacion" style="margin:10px auto; width:150px">
                                <input type="reset" id="limpiarP" class="buttons" value="Limpiar" style="margin:10px 0px 0px 10px; width:100px"></td>
                    <td></td></tr></table>
                </fieldset>
            </form>
        </div>
         <form id="masInformacion" class="masInformacion">

                <div id="masInformacion" class="overlay"></div>
                    <div id="masInformacion" class="modal" style="min-height:500px;width:650px">
                        <div style="background:white; margin:0 auto;">
                            <legend style="margin: 0 auto;"><h4 >Pedidos</h4></legend>
                            <span id="close" class="close">X</span>
                                <div style="min-height:500px;margin-top:20px;margin-left:70px">
                                        <label for="cedulacp" style="margin:10px 0px 0px 25px">Cedula Cliente</label><br>
                                        <input type="text" class="form" id="cedulacp" style="width:100px; margin:10px 0px 0px 26px" disabled="disbled"/><br>
                                  
                                        <label for="nombrem" style="margin:20px 0px 0px 25px">Nombre</label><br>
                                        <input type="text" class="form" id="nombrem" style="width:150px;margin:10px 0px 0px 25px" placeholder=" Nombre" disabled="disbled" /><br>

                                         <label for="fechaRe" style="margin:20px 0px 0px 25px">Fecha de creación</label><br>
                                        <input type="date" class="form" id="fechaCre" name="fechaCre" style="width:130px;margin:10px 0px 0px 26px"  disabled="disbled"/><br>

                                        <label for="fechaEn" style="margin:20px 0px 0px 25px">Fecha de entrega</label><br>
                                        <input type="date" class="form" id="fechaEnt" name="fechaEntrega" style="width:130px;margin:10px 0px 0px 26px"  disabled="disbled"/><br>
                                   
                                        <label for="tipoVen" style="margin:20px 0px 0px 25px">Tipo Venta</label><br>
                                        <input id="tipoVenta" style="margin:10px 0px 0px 26px" type="text" class="form" disabled><br>
                                </div>
                                <div style="position:absolute;top:75px;left:370px;margin-top:20px">
                                        <label for="estado" style="margin:20px 0px 0px 25px">Estado</label><br>
                                        <input id="estadoP" style="margin:10px 0px 0px 26px; width:110px;" type="text" class="form" disabled><br>

                                        <label for="refBol" style="margin:20px 0px 0px 25px">Referencia de Bolsa</label><br>
                                        <input id="refBol" style="margin:10px 0px 0px 26px" type="text" class="form" disabled><br>

                                        <label for="cantiP" style="margin:20px 0px 0px 25px">Cantidad</label><br>
                                        <input id="cantiP" type="number" style="margin:10px 0px 0px 26px; width:63px;" class="form" disabled><label for="cantiPT" id="cantiPT"></label><br>

                                        <label for="precioP" style="margin:20px 0px 0px 25px">Precio</label><br>
                                        <input id="precioP" type="text" style="margin:10px 0px 0px 26px; width:100px" class="form" disabled><br>

                                        <label for="procesosP" style="margin:20px 0px 0px 25px">Procesos</label><br>
                                        <input id="extP" type="checkbox" style="margin:10px 0px 0px 26px"  disabled> Extrusion<br>
                                        <input id="impP" type="checkbox" style="margin:10px 0px 0px 26px"  disabled> Impresion<br>
                                        <input id="selP" type="checkbox" style="margin:10px 0px 0px 26px"  disabled> Sellado<br>
                                            
                                    
                                <div id="imagen"></div>
                                </div>
                            <br>
                            <br>

                        </div><!-- content here -->
                    </div>
               </form>
    </body>
</html>
<%}else{
    
}
%>