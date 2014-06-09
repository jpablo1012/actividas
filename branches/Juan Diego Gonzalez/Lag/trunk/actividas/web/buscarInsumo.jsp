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
        <script src="js/botones.js"></script>
        <script type='text/javascript' src='js/jquery.modal.js'></script>
        <script src="js/buscarInsumo.js"></script>
        <script src="js/heartcode-canvasloader-min-0.9.1.js"></script>
        <script src="js/modfwin.js"></script>
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
                    <legend><h4 align="center">Buscar Insumo</h4></legend>
                    <table class="infosearch" align="center" cellspacing="10">
                        <tr>
                            <td id="opciones">
                                <input type="radio" name="buscar" id="colors" value="color" >Color
                                <input type="radio" name="buscar" id="materials" value="material">Material
                                <input type="radio" name="buscar" id="bolsas" value="bolsa">Bolsa
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <input type="text" id="search" class="form" style="width:420px;height:28px"/>
                                <select id="buscarcon" class="form" style="height:28px; max-width: 120px">
                                    <option value="ninguno">-Seleccionar-</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td align="center">
                                <div></div><br>
                                <input type="button" class="buttons" id="buscari" value="Buscar"/><br><br>
                                <div id="canvasloader-container"></div>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" id="mensaje"></td>
                        </tr>
                    </table>
                </fieldset>
                <fieldset id="answer" class="effectDes">
                    <table id="resansweri" align="center" class="resansweri" border="0">

                    </table>
                    <table align="center"><tr>
                            <td><input type="reset" id="limp" class="buttons" value="Limpiar" style="margin:42px 38%"></td>
                            <td><input type="button" id="modificarl" class="buttons" value="Modificar" style="margin:42px 38%">
                                <input type="button" id="masInfoP" class="buttons" value="Ver" style="margin:42px 38%; display:none;"></td>
                            <td><input type="button" id="eliminarin" class="buttons" value="Eliminar" style="margin:42px 38%"></td></tr></table>
                </fieldset>
            </form>
        </div>
        <form id="actualizarInsumo" class="actualizarInsumo">

            <div id="actualizarInsumo" class="overlay"></div>
            <div id="actualizarInsumo" class="modal">
                <div id="lal" style="background:white; margin:0 auto; height: 100%;">
                    <legend><h4 id="insbo" align="center">Modificar Insumo</h4></legend>
                    <span id="close" class="close">X</span>
                    <table cellspacing="10">
                        <label id="rc" for="idI" style="margin-left: 30px;">Referencia</label><br>
                        <input type="text" class="formM" id="idI" style="width:100px; margin-left: 31px;" disabled="disbled"/><br><br>
                        <div id="modin">
                            <label for="nombreI">Nombre</label><br>
                            <input type="text" class="formM" id="nombreI" style="width:120px" placeholder="Nombre" required/>
                            <br><br>
                            <label for="cantidad">cantidad</label><br>
                            <input type="text" class="formM" id="cantidadI" name="cantidad" style="width:100px" placeholder=""/>
                        </div>
                        <div id="verbol" style="height: 500px;margin-left: 30px;">
                            <div style="margin-left: 21px">
                                <label for="referenciama">Referencia material</label><br>
                                <select id="referenciama" class="form" style="margin: 5px 0 0 22px; max-width: 177px;" disabled="disbled">
                                </select><br>
                                <label for="calibre" style="margin: 10px 0 0 0px;">Calibre</label><br>
                                <input type="number" id="calibre" class="form" min="1" max="7" style="margin: 5px 0 0 22px; width: 60px" disabled="disbled"><br>
                                <label for="medida" style="margin: 10px 0 0 0px;">Medidas</label><br>
                                <select id="medida" class="form" style="margin: 5px 0 0 22px;" disabled="disbled">
                                    <option value="cm">Cm
                                    <option value="pulgadas">Pulgadas
                                </select><br>
                                <input type="number" placeholder="Largo" onchange="rectangle()" id="largo" class="form" min="0" style="width: 65px; margin: 15px 0 0 20px;" disabled="disbled"><br>
                                <input type="number" placeholder="Ancho" onchange="rectangle()" id="ancho" class="form" min="0" style="margin: 15px 0 0 20px; width: 65px;" disabled="disbled"><br>
                                <div id="tratado"><label style="margin: 10px 0 0 0px;" for="tratado">Tratado</label><br>
                                    <input style="margin: 5px 0 0 22px;" type="radio" name="tratado" id="si" value="true" disabled="disbled">Si<br>
                                    <input style="margin: 5px 0 0 22px;" type="radio" name="tratado" id="no" value="false"disabled="disbled">No</div>
                            </div>
                            <div id="colum2" style="position:absolute ;left: 310px; top: 89px">
                                <label for="referenciaco">Referencia color</label><br>
                                <select id="referenciaco" class="form" style="margin: 5px 0 0 22px;" disabled="disbled">
                                </select><br>
                                <label for="transparencia" style="margin: 10px 0 0 0px;">Transparencia</label><br>
                                <input style="margin: 5px 0 0 22px;" id="check4" class="check4" type="checkbox" disabled="disbled"> Superior</input>
                                <input type="number" id="tps"x min="0" style="width: 45px; margin: 5px 0 0 10px;" disabled="disbled"><br>
                                <input style="margin: 5px 0 0 22px;" id="check5" class="check5" type="checkbox" disabled="disbled"> Inferior</input>
                                <input type="number" id="tpi" min="0" style="width: 45px; margin: 5px 0 0 20px;" disabled="disbled"><br>
                                <label for="fuelle" style="margin: 20px 0 0 0px;">Fuelle</label><br>
                                <input style="margin: 5px 0 0 22px;" id="check6" class="check6" type="checkbox" disabled="disbled"> Inferior</input>
                                <input type="number" id="flli" min="0" style="width: 45px; margin: 5px 0 0 20px;" disabled="disbled"><br>
                                <input style="margin: 5px 0 0 22px;" id="check7" class="check7" type="checkbox" disabled="disbled"> Superior</input>
                                <input type="number" id="flls" min="0" style="width: 45px; margin: 5px 0 0 10px;" disabled="disbled"><br>
                                <input style="margin: 5px 0 0 22px;" id="check8" class="check8" type="checkbox" disabled="disbled"> Lateral</input>
                                <input type="number" id="flll" min="0" style="width: 45px; margin: 5px 0 0 19px;" disabled="disbled"><br>
                                <input style="margin: 5px 0 0 22px;" id="check12" class="check12" type="checkbox" disabled="disbled"> Solapa</input>
                                <input type="number" id="slp" min="0" style="width: 45px; margin: 5px 0 0 18px;" disabled="disbled"><br><br>

                                <label for="troquel">Troquel</label><br>
                                <select id="troquel" class="form" style="margin: 5px 0 0 22px;" disabled="disbled">
                                    <option value="ninguno">Ninguno</option>
                                    <option value="camiseta">Camiseta</option>
                                    <option value="riñon">Riñon</option>
                                    <option value="tiras">Tiras</option>
                                </select>
                            </div>
                        </div>
                        <div id="imagen"></div>
                    </table>
                    <br>
                    <br>
                    <table align="center">
                        <tr>
                            <td colspan="2">
                        <center>
                            <input type="submit" class="buttons" id="modificatei" value="Guardar"/>
                        </center>
                        </td>
                        </tr>
                        <tr>
                            <td id="mensajea">

                            </td>
                        </tr>
                    </table>
                </div><!-- content here -->
            </div>
        </form>
    </body>
</html>
<%}else{
    
}
%>