<!DOCTYPE HTML >

<html>

    <head>
        <script src="js/botones.js"></script>
        <script src="js/crearPedido.js"></script>
    </head>

    <body>
        <ul id='navigatione'>
            <li id="crearP">
                <a href='#'>
                    <p><b>Crear</b>
                    </p>
                </a>
            </li>
            <li id="esperaP">
                <a href='#'>
                    <p><b>En Espera</b>
                    </p>
                </a>
            </li>
            <li id="buscarP"><a href='#'><p><b>Buscar</b></p></a></li>
        </ul>
        <div class="form1">
            <form id="crearPedido" onsubmit="return false">
                <fieldset style="width:660px; margin:0px 0px 0px 80px; border-radius: 10px 10px 10px 10px;">
                    <legend>
                        <h4 align="center">Pedido</h4>
                    </legend>
                    <table align="center" >
                        <tr>
                            <td id="crearPedi">
                                
                            </td>
                            <td style="padding: 0px 9px 0px 59px;">
                                <span style="color:#D3362D;">*</span><label for="FEntrega">Fecha de Entrega</label>
                                <br>
                                <input type="date" class="form" id="FEntrega" style="width:140px" required>
                            </td>
                            <td>

                            </td>
                        </tr>
                    </table>
                    <br>
                    <br>
                </fieldset>

                <div class="cpestaña" id="extrusionp" style="height:300px">
                    <div id="columna1" style="margin: 0px 0px 0px 85px;">
                        <span style="color:#D3362D;">*</span><label id="tipoV" for="tipoV" style="margin: 20px 0px 5px 1px" disabled="true">Tipo de Venta</label>
                        <br>
                        <select id="tipoVenta" class="form" required>
                            <option value='seleccionar'>-Seleccionar-</option>
                            <option value='servicio'>Servicio</option>
                            <option value='producto'>Producto</option>
                        </select><br>
                        <span style="color:#D3362D;">*</span><label id="cantU" for="cantU" style="margin: 20px 0px 5px 1px;" disabled="true">Unidad de medida</label><br>
                        <select id="cantidadU" class="form" required>
                            <option value='seleccionar'>-Seleccionar-</option>
                            <option value='kg'>Kg</option>
                            <option value='unidades'>Unidades</option>
                        </select><br>
                        <span style="color:#D3362D;">*</span><label id="cantidadl" for="Cantidad" style="margin: 20px 0px 5px 1px;" disabled="true">Cantidad</label>
                        <br>
                        <input type="number" size="10" id="cantidad" class="form" style="width:50px; margin:0px 0px 0px 10px;" required>

                    </div>
                    <div id="columna2" style="margin: -241px 0px 0px 333px;">
                        <span style="color:#D3362D;">*</span><label id="refB" for="refB" style="margin: 30px 0px 5px 1px;">Referencia Bolsa</label>
                        <br>
                        <select id="refBolsa" class="form" required>

                        </select>
                        <br>

                        <label id="textBag" for="textBag" style="margin: 20px 0px 5px 1px;" disabled="true">Texto bolsa</label>
                        <br>
                        <input type="text" size="10" id="textBolsa" class="form" style="width:190px; margin:0px 0px 0px 0px;" required>
                        <br>

                        <span style="color:#D3362D;">*</span><label id="Procesos" for="Procesos" style="margin: 20px 0px 5px 1px;" disabled="true">Procesos</label>
                        <br>
                        <input style="margin: 0px 0px 7px 25px;" id="check3" name="check3" class="check3" type="checkbox"> Extrusión</input>
                        <br>
                        <input style="margin: 0px 0px 7px 25px;" id="check1" name="check1" class="check1" type="checkbox"> Impresión</input>
                        <br>
                        <input style="margin: 0px 0px 7px 25px;" id="check2" name="check2" class="check2" type="checkbox"> Sellado</input>
                        <br>
                    </div>

                    <br>

                    <br>



                </div>

                <table style="margin:20px auto 0 auto">
                    <tr>
                        <td>
                            <input type="submit" id="crearPed" class="buttons" value="Crear Pedido">
                        </td>
                        <td>
                            <input type="reset" id="cleanPed" class="buttons" value="Limpiar">
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" id="Mensaje3">

                        </td>
                    </tr>
                </table>
            </form>

        </div>

    </body>

</html>
