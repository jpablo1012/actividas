
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML >

<html>
    <head>
        <script src="js/botones.js"></script>
        <script src="js/crearInsumos.js"></script>
    </head>
    <body>
        <ul id='navigatione'>
            <li id="crearM"><a href='#'><p><b>Crear Material</b></p></a></li>
            <li id="crearCo"><a href='#'><p><b>Crear Color</b></p></a></li>
            <li id="crearB"><a href='#'><p><b>Crear Bolsa</b></p></a></li>
            <li id="buscarI"><a href='#'><p><b>Buscar</b></p></a></li>
        </ul>
        <div class="form1">
            <form id="crearColor">
                <fieldset>
                    <legend><h4 align="center">Crear Color</h4></legend>
                    <table align="center" cellspacing="10">
                        <tr>
                            <td><label for="nombreco"><span style="color:#D3362D;">* </span>Nombre</label><br>
                                <input type="TextField" id="nombreco" class="form" width="145px" required></td>
                        </tr>
                        <tr>
                            <td></td>
                        </tr>
                        <tr>
                            <td><label for="cantidadco">Cantidad</label><br>
                            <input type="TextField" id="cantidadco" class="form" width="145px" placeholder="litros"></td>
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
                        <td align="right"><input type="submit" class="buttons" id="registerCo" value="Crear Color"></td>
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
