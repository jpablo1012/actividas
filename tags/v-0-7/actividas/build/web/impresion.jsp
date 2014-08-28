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
        <script src='js/jquery.modal.js'></script>
        <script src="js/modfwin.js"></script>
        <script src="js/botones.js"></script
        <script src="js/quitar.js"></script>
        <script src="js/impresion.js"></script>
        <script src="js/orden.js"></script>


    </head>
    <body>
        <ul id='navigatione'>
            <li id="extru"><a href='#'><p><b>Extrusi�n</b></p></a></li>
            <li id="impre"><a href='#'><p><b>Impresi�n</b></p></a></li>
            <li id="sella"><a href='#'><p><b>Sellado</b></p></a></li>
            <li id="bOrden"><a href='#'><p><b>Buscar</b></p></a></li>
        </ul>
        <div class="form1">
            <fieldset style="min-height:450px">
                <legend><h4 align="center">Impresi�n</h4></legend>
                <table align="center" cellpadding="20" style="margin-left:55px">
                    <tr>
                        <td>
                            <p id="ordenT">N� de Orden</p>
                        </td>
                        <td>
                            <p id="ordenT">Referencia Bolsa</p>
                        </td>
                        <td>
                            <p id="ordenT">Cantidad</p>
                        </td>
                        <td>
                            <p id="ordenT">Fecha de Entrega</p>
                        </td>
                    </tr>
                </table>
                <div id="ordenI"></div>
                <input id="ordendi" type="button" value="" style="display: none">
                <div id="impresionF" class="overlay"></div>
                <div id="impresionF" class="modal" style="min-height:500px;width:750px">
                    <form id="impresionF" class="impresionF">
                        <div style="background:white; margin:0 auto; text-align:center;min-height:495px;">
                            <span id="close" class="close" style="margin-top: -1%">X</span>
                            <legend style="margin: 0 auto;"><h4 >Impresi�n</h4></legend>
                            <div style="min-height:460px;margin-top:20px;margin-left:-330px">
                                <label for="numOrI" style="margin:10px 0px 0px 25px">N�mero de Orden</label><br>
                                <input type="text" class="form" id="numOrdenI" style="width:100px; margin:10px 0px 0px 26px" disabled="disbled"/><br>

                                <label for="numImp" style="margin:20px 0px 0px 25px">N�mero de Impresion</label><br>
                                <input type="text" class="form" id="numImpresion" style="width:150px;margin:10px 0px 0px 25px"  disabled="disbled" /><br>

                                <label for="refBol" style="margin:20px 0px 0px 25px">Referencia de Bolsa</label><br>
                                <input type="text" class="form" id="referBolsaI" style="width:130px;margin:10px 0px 0px 26px"  disabled="disbled" /><br>

                                <label for="notaI" style="margin:20px 0px 0px 25px">Notas</label><br>
                                <textarea class="form" id="notasI" style="width:160px;height: 120px;margin:10px 0px 0px 26px"></textarea><br>

                            </div>
                            <div style="position:absolute;top:75px;left:440px;margin-top:20px">
                                <label for="fechaIniI" style="margin:20px 0px 0px 25px">Fecha de Inicio</label><br>
                                <input id="fechaInicialI" style="margin:10px 0px 0px 26px; width:110px;" type="date" class="form" disabled><br>

                                <label for="fechaFinI" style="margin:20px 0px 0px 25px">Fecha Plazo</label><br>
                                <input id="fechaFinalI" style="margin:10px 0px 0px 26px; width:110px;" type="date" class="form" disabled><br>

                                <label for="cantI" style="margin:20px 0px 0px 25px">Cantidad</label><br>
                                <input id="cantidadI" type="number" style="margin:10px 0px 0px 26px; width:63px;" class="form" disabled><label for="TcantI" id="TcantI"></label><br>
                                
                                <label for="pesoAn" style="margin:20px 0px 0px 25px">Peso antes de la impresi�n</label><br>
                                <input id="pesoA" type="number" style="margin:10px 0px 0px 26px; width:63px" class="form" > <label for="TcantIp" id="TcantIp">Kg</label><br>

                                <label for="pesoD" style="margin:20px 0px 0px 25px">Peso despu�s de la impresi�n</label><br>
                                <input id="pesoD" type="number" style="margin:10px 0px 0px 26px; width:63px" class="form" ><label for="TcantIp" id="TcantIp">Kg</label><br>

                                <label for="imp" style="margin:20px 0px 0px 25px">Impresi�n</label><br>
                                <input id="impresionI" type="text" style="margin:10px 0px 0px 26px; width:100px" class="form" disabled><br>
                                
                                <div id="imagen"></div>

                            </div>
                            <input id="saveI" type="button" name="guardarI" onclick="guardarIm(this.name)" style="margin:60px 0px 15px 26px; width:120px" value="Guardar progeso" class="form">
                            <input id="confirmarI" name="finI" onclick="guardarIm(this.name)" type="button" style="margin:0px 15px 0px 26px; width:120px" value="Finalizar impresi�n" class="form">
                            <table align="center">
                                <tr>
                                    <td colspan="2" id="mensajeI">

                                    </td>
                                </tr>  
                            </table>
                        </div>

                    </form>
                </div>
            </fieldset>

        </div>
    </body>
</html>
<%}else{
    
}
%>