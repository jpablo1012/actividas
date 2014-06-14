<%@page import="java.net.URLDecoder"%>
<%@page import="Controlador.AES"%>
<!DOCTYPE HTML >
<%@page contentType="java/html" pageEncoding="utf-8"%>
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
        <script src="js/extrusion.js"></script>
        <script src="js/orden.js"></script>
    </head>
    <body>
        <ul id='navigatione'>
            <li id="extru"><a href='#'><p><b>Extrusión</b></p></a></li>
            <li id="impre"><a href='#'><p><b>Impresión</b></p></a></li>
            <li id="sella"><a href='#'><p><b>Sellado</b></p></a></li>
            <li id="bOrden"><a href='#'><p><b>Buscar</b></p></a></li>
        </ul>
        <div class="form1">
            <fieldset style="min-height:450px">
                <legend><h4 align="center">Extrusión</h4></legend>
                <table align="center" cellpadding="20" style="margin-left:55px">
                    <tr>
                        <td>
                            <p id="ordenT">N° de Orden</p>
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
                <div id="ordenR"></div>
                <input id="ordenda" type="button" value="" style="display: none">
                <div id="extrusionF" class="overlay"></div>
                <div id="extrusionF" class="modal" style="min-height:500px;width:750px">
                    <form id="extrusionF" class="extrusionF">
                        <div style="background:white; margin:0 auto; text-align:center;min-height:495px;">
                            <span id="close" class="close" style="margin-top: -3%">X</span>
                            <legend style="margin: 0 auto;"><h4 >Extrusión</h4></legend>
                            <div style="min-height:460px;margin-top:20px;margin-left:-330px">
                                <label for="numOr" style="margin:10px 0px 0px 25px">Número de Orden</label><br>
                                <input type="text" class="form" id="numOrden" style="width:100px; margin:10px 0px 0px 26px" disabled="disbled"/><br>

                                <label for="numExt" style="margin:20px 0px 0px 25px">Número de Extrusión</label><br>
                                <input type="text" class="form" id="numExtrusion" style="width:150px;margin:10px 0px 0px 25px"  disabled="disbled" /><br>

                                <label for="refBol" style="margin:20px 0px 0px 25px">Referencia de Bolsa</label><br>
                                <input type="text" class="form" id="referBolsa" style="width:130px;margin:10px 0px 0px 26px"  disabled="disbled" /><br>

                                <label for="nota" style="margin:20px 0px 0px 25px">Notas</label><br>
                                <textarea class="form" id="notas" style="width:160px;height: 120px;margin:10px 0px 0px 26px"></textarea><br>

                            </div>
                            <div style="position:absolute;top:75px;left:440px;margin-top:20px">
                                <label for="fechaIni" style="margin:20px 0px 0px 25px">Fecha de Inicio</label><br>
                                <input id="fechaInicial" style="margin:10px 0px 0px 26px; width:110px;" type="date" class="form" disabled><br>

                                <label for="fechaFin" style="margin:20px 0px 0px 25px">Fecha Plazo</label><br>
                                <input id="fechaFinal" style="margin:10px 0px 0px 26px; width:110px;" type="date" class="form" disabled><br>

                                <label for="rollos" style="margin:20px 0px 0px 25px">Rollos Usados</label><br>
                                <input id="rollosUsados" type="number" style="margin:10px 0px 0px 26px; width:63px;" class="form" ><label for="cantiPT" id="cantiPT"></label><br>

                                <label for="desper" style="margin:20px 0px 0px 25px">Desperdicio (Kilos)</label><br>
                                <input id="desperdicio" type="number" style="margin:10px 0px 0px 26px; width:63px" class="form" ><br>

                                <label for="maquina" style="margin:20px 0px 0px 25px">Maquina Usada</label><br>
                                <input id="maquinaUsada" type="text" style="margin:10px 0px 0px 26px; width:100px" class="form" ><br>

                                <div id="imagen"></div>

                            </div>
                            <input id="save" type="button" name="guardarE" onclick="guardar(this.name)" style="margin:10px 0px 15px 26px; width:120px" value="Guardar progeso" class="form">
                            <input id="confirmar" name="fin" onclick="guardar(this.name)" type="button" style="margin:0px 15px 0px 26px; width:120px" value="Finalizar extrusión" class="form">
                            <table align="center">
                                <tr>
                                    <td colspan="2" id="mensajea">

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