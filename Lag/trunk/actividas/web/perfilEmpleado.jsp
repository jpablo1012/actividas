<%-- 
    Document   : perfilEmpleado
    Created on : 5/11/2013, 09:43:28 PM
    Author     : Oscar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <script src="js/perfilEmpleado.js"></script>
    </head>
    <body>

        <div class="form1">
            <form id="perfilEmpleado">
                <fieldset>
                    <legend><h4 align="center">Perfil</h4></legend>
                    <input type="button" class="edit" id="editar" value="Editar"/>
                    <%--   <img id="lala" style="width: 70%; height: 393px;"> --%>
                    <table id="perfile" class="perfilc" style="margin: 10px 100px; position: relative; left: 15%;" align="center" cellspacing="10">
                        <tr style="display: none;">
                            <td colspan="2"><label id="IdUsuario"></label></td>
                        </tr>
                        <tr>
                            <td><label>Nombre:</label></td>
                            <td><label id="nombree"></label><label id="apellidoe"></label>
                                <input id="txtnombre" style="display: none; width: 120px;" type="text" class="form" placeholder="Nombre" required>
                                <input id="txtapellido" style="display: none; width: 120px;" type="text" class="form" placeholder="Apellido" required></td>
                        </tr>
                        <tr>
                            <td><label>C&eacute;dula:</label></td>
                            <td><label id="cedulae"></label></td>
                        </tr>
                        <tr>
                            <td><label>Cargo:</label></td>
                            <td><label id="cargoe"></label></td>
                        </tr>
                        <tr>
                            <td colspan="2"><label id="camcont" style="display: none;"><hr><span style="color:#D3362D;">* </span>Cambio de contraseña</label></td>
                        </tr>
                        <tr id="conactual" style="visibility:hidden;">
                            <td><label>Contraseña Actual:</label></td>
                            <td><input id="contactual" type="password" class="form" style="width: 124px;" placeholder="Contraseña Actual">
                                <label id="clave"></label></td>
                        </tr>
                        <tr id="ncontraseña" style="visibility:hidden;">
                            <td><label>Nueva contraseña:</label></td>
                            <td><input id="contraseña" type="password" class="form" style="width: 124px;" placeholder="Contraseña">
                                <input id="concontraseña" type="password" class="form" style="width: 124px;" placeholder="Confirmar contraseña"></td>
                        </tr>
                        <tr>
                            <td colspan="2" id="mensaje"></td>
                        </tr>
                    </table>
                    <br>
                    <div id="nota" style="display: none;"><span style="color:#D3362D;">* </span><span><b>Nota:</b> Si desea cambiar la contraseña debera colocar la contraseña actual y luego la contraseña nueva en sus campos requeridos</span></div>
                </fieldset>
                    <table align="center">
                    <tr>
                        <td>
                            <center>
                                <input type="submit" style="display: none;" class="buttons" id="modificarp" value="Modificar"/>
                                <input type="button" style="display: none;" class="buttons" id="cancelarm" value="Cancelar"/>
                            </center>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>
