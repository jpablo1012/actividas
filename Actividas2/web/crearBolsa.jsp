
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML >

<html>
    <head>

        <script src="js/jquery.js"></script>
        <script src="js/jquery-1.10.2.js"></script>
        <script src="js/jquery.cookie.js"></script>
        <script src="js/jquery-ui.js"></script>
        <script src="js/insumos.js"></script>
        <script src="js/upload.js"></script>
        <script src="js/crearInsumos.js"></script>
        <script src="js/graficos.js"></script>
        <script src="js/botones.js"></script>
        <script src="js/quitar.js"></script>

        <meta name="viewport" content="width=device-width">
    </head>
    <body>
        <ul id='navigatione'>
            <li id="crearM"><a href='#'><p><b>Crear Material</b></p></a></li>
            <li id="crearCo"><a href='#'><p><b>Crear Color</b></p></a></li>
            <li id="crearB"><a href='#'><p><b>Crear Bolsa</b></p></a></li>
            <li id="buscarI"><a href='#'><p><b>Buscar</b></p></a></li>
        </ul>
        <div class="form1">
            <form id="crearBolsa" onsubmit="return false" enctype="multipart/form-data">
                <fieldset style="height: 610px;">
                    <legend><h4 align="center">Crear Bolsa</h4></legend>
                    <div id="colum1" style="position:relative; left: 105px; top: 59px;">
                        <label for="referenciama"><span style="color:#D3362D;">*</span>Referencia material</label><br>
                        <select id="referenciama" class="form" style="margin: 5px 0 0 22px; max-width: 177px;" required>
                        </select><br>
                        <label for="calibre" max="7" style="margin: 10px 0 0 0px;"><span style="color:#D3362D;">*</span>Calibre</label><br>
                        <input type="number" id="calibre" step="0.1" class="form" min="1" max="7" style="margin: 5px 0 0 22px; width: 60px"><br>
                        <label for="medida" style="margin: 10px 0 0 0px;"><span style="color:#D3362D;">*</span>Medidas</label><br>
                        <select id="medida" class="form" style="margin: 5px 0 0 22px;" required>
                            <option value="null">-Seleccionar-
                            <option value="cm">Cm
                            <option value="pulgadas">Pulgadas
                        </select><br>
                        <input type="number" step="0.1" placeholder="Largo" onchange="rectangle()" id="largo" class="form" min="0" style="width: 65px; margin: 15px 0 0 20px;"><br>
                        <input type="number" step="0.1" placeholder="Ancho" onchange="rectangle()" id="ancho" class="form" min="0" style="margin: 15px 0 0 20px; width: 65px;"><br>
                        <div id="tratado"><label style="margin: 10px 0 0 0px;" for="tratado"><span style="color:#D3362D;">*</span>Tratado</label><br>
                            <input style="margin: 5px 0 0 22px;" type="radio" name="tratado" id="si" value="true">Si<br>
                            <input style="margin: 5px 0 0 22px;" type="radio" name="tratado" id="no" value="false">No</div>
                        <label for="imagen" style="margin: 10px 0 0 0px;">Imagen</label><br>
                        <input type="file" id="images" name="images" class="form" style="margin: 5px 0 0 22px;"><br>
                    </div>
                    <div id="colum2" style="position:relative; left: 310px; top: -361px">
                        <label for="referenciaco"><span style="color:#D3362D;">*</span>Referencia color</label><br>
                        <select id="referenciaco" class="form" style="margin: 5px 0 0 22px;" required>
                        </select><br>
                        <label for="transparencia" style="margin: 10px 0 0 0px;">Transparencia</label><br>
                        <input style="margin: 5px 0 0 22px;" id="check4" class="check4" type="checkbox"> Superior</input>
                        <input type="number" id="tps" class="hidden" min="0" style="width: 45px; margin: 5px 0 0 10px;"><br>
                        <input style="margin: 5px 0 0 22px;" id="check5" class="check5" type="checkbox"> Inferior</input>
                        <input type="number" id="tpi" class="hidden" min="0" style="width: 45px; margin: 5px 0 0 20px;"><br>
                        <label for="fuelle" style="margin: 20px 0 0 0px;">Fuelle</label><br>
                        <input style="margin: 5px 0 0 22px;" id="check6" class="check6" type="checkbox"> Inferior</input>
                        <input type="number" id="flli" class="hidden" min="0" style="width: 45px; margin: 5px 0 0 20px;"><br>
                        <input style="margin: 5px 0 0 22px;" id="check7" class="check7" type="checkbox"> Superior</input>
                        <input type="number" id="flls" class="hidden" min="0" style="width: 45px; margin: 5px 0 0 10px;"><br>
                        <input style="margin: 5px 0 0 22px;" id="check8" class="check8" type="checkbox"> Lateral</input>
                        <input type="number" id="flll" class="hidden" min="0" style="width: 45px; margin: 5px 0 0 19px;"><br>
                        <input style="margin: 5px 0 0 22px;" id="check12" class="check12" type="checkbox"> Solapa</input>
                        <input type="number" id="slp" class="hidden" min="0" style="width: 45px; margin: 5px 0 0 18px;"><br><br>

                        <label for="troquel">Troquel</label><br>
                        <select id="troquel" class="form" style="margin: 5px 0 0 22px;">
                            <option value="ninguno">Ninguno</option>
                            <option value="camiseta">Camiseta</option>
                            <option value="riñon">Riñon</option>
                            <option value="tiras">Tiras</option>
                        </select><br>
                    </div>
                    <canvas value="hola" id="prel" style="border:5px solid rgba(0, 0, 0, 0.08); box-shadow: 10px 9px 20px 7px rgba(0, 0, 0, 0.1); background:lightgray; height:300px; width:240px; position:absolute; left:570px; top:170px" ></canvas>
                </fieldset>
                <table align="center">
                    <tr>
                        <td align="right"><input type="submit" class="buttons" id="registerBol" value="Crear Bolsa"></td>
                        <td><input type="reset" id="borrar" onclick="limpiarT2()" class="buttons" value="Limpiar"><br></td>
                    </tr>
                    <tr>
                    <center><td colspan="2" id="Mensaje">

                        </td></center>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>
