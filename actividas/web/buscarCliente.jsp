
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML >

<html>
    <head>
        <script src="js/botones.js"></script>
        <script src="js/heartcode-canvasloader-min-0.9.1.js"></script>
        <script type='text/javascript' src='js/jquery.modal.js'></script>
        <script src='js/actualizarCliente.js'></script>
        <script src="js/buscarCliente.js"></script>
        <script src="js/modfwin.js"></script>
        <script src="js/menu.js"></script>
    </head>
    <body>
        <ul id='navigatione'>
            <li id="crear"><a href='#'><p><b>Crear</b></p></a></li>
            <li id="buscar"><a href='#'><p><b>Buscar</b></p></a></li>
        </ul>
        <div class="form1">

            <form id="buscarCliente">
                <fieldset id="answers1">
                    <legend><h4>Buscar Cliente</h4></legend>
                    <table class="infosearch" cellspacing="10">
                        <tr>
                            <td>
                                <input type="text" id="search" class="form" style="width:420px;height:28px" required/>
                                <!-- <label for="campo" style="color:silver">Buscar con:</label>-->
                                <select id="buscarCon" class="form" style="height:28px">
                                    <option value="ninguno">-Seleccionar-
                                    <option value="cedula">C&eacute;dula
                                    <option value="direccion">Direcci&oacute;n
                                    <option value="email">Email
                                    <option value="nombre">Nombre
                                    <option value="apellido">Apellido
                                    <option value="telefono">Tel&eacute;fono
                                </select>
                            </td>
                        </tr>

                        <tr>
                            <td align="center" >
                                <div></div><br>
                                <input type="button" id="buscarc" class="buttons" value="Buscar"/><br><br>
                                <div id="canvasloader-container"></div>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" id="mensaje"></td>
                        </tr>
                    </table>
                </fieldset>
            </form>

            <fieldset id="answer1" class="effectDes">
                <table id="resanswer1" align="center" class="resanswer1" border="0" >

                </table>


                <table align="center"><tr>
                        <td><input type="reset" id="buscarl" class="buttons" value="Limpiar" style="margin:42px 38%"></td>
                        <td><input type="button" id="modificarl" class="buttons" value="Modificar" style="margin:42px 38%"></td>
                <td><input type="button" id="eliminarl" class="buttons" value="Eliminar" style="margin:42px 38%"></td></tr></table>
            </fieldset>

        </div>


        <form id="actualizarCliente" class="actualizarCliente">

            <div id="actualizarCliente" class="overlay"></div>
            <div id="actualizarCliente" class="modal">
                <div style="background:white; margin:0 auto; text-align:center;">
                    <legend><h4 align="center">Modificar Cliente</h4></legend>
                    <span id="close" class="close">X</span>
                    <table align="center" cellspacing="10">
                        <tr>
                            <td>
                                <label for="Cedula">C&eacute;dula</label><br>
                                <input type="text" class="formM" id="cedulam" style="width:100px" disabled="disbled"/>
                                <label id="IdUsuario"></label>
                            </td>

                            <td>

                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label for="Nombre">Nombre</label><br>
                                <input type="text" class="formM" id="nombrem" style="width:120px" placeholder="Nombre" required/>
                                <input type="text" class="formM" id="apellidom" style="width:120px" placeholder="Apellido" required/>
                            </td>

                            <td>

                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label for="codigo">Contraseña</label><br>
                                <input type="password" class="formM" id="codigom" name="codigo" style="width:130px" placeholder="Contraseña" required/>
                                <input type="password" class="formM" id="conCodigo" name="concodigo" style="width:130px" placeholder="Confirmar contraseña"/>
                            </td>
                            <td>

                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label for="Direccion">Direcci&oacute;n/Tel&eacute;fono/Ciudad</label><br>
                                <input type="text" class="formM" name="Direccion" id="direccionm" style="width:120px" required/>
                                <input type="text" class="formM" name="Telefono" id="telefonom" style="width:80px" placeholder="Tel&eacute;fono" required/>
                                <input type="text" class="formM" name="Ciudad" id="ciudadm" style="width:80px" placeholder="Ciudad" required/>
                            </td>
                            <td>

                            </td>
                        </tr>
                        <tr>
                            <td colspan="1">
                                <label for="email">Correo</label><br>
                                <input type="email" class="formM" name="email" id="emailm" style="width:150px" required/>
                            </td>
                            <td>

                            </td>
                        </tr>
                    </table>
                    <br>
                    <table align="center">
                        <tr>
                            <td colspan="2">
                                <center>
                                    <input type="submit" class="buttons" id="modificate" value="Guardar"/>
                                </center>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" id="mensajea">

                            </td>
                        </tr>
                        <div id="imagen"></div>
                    </table>
                </div><!-- content here -->
            </div>
        </form>

    </body>
</html>
