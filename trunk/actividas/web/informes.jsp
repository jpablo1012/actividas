<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    </head>
    <body>
        <ul id='navigatione'>
            <li id="sellado"><a href='#'><p><b>Sellado</b></p></a></li>
        </ul>
        <div class="form1">
            <form id="informeSellado">
                <fieldset>
                    <legend><h4 align="center">Informe</h4></legend>
                    <table align="center" cellspacing="10">
                        <tr>
                            <td><label>Informe entre</label>
                            </td>
                        </tr>
                        <tr>
                            <td>
                            <input type="Date" id="fecha1" class="form" width="145px"></td>
                            <td><label>hasta</label></td>
                            <td><input type="Date" id="fecha2" class="form" width="145px"></td>
                        </tr>
                    </table>
                </fieldset>
                <table align="center">
                    <tr>
                        <td align="right"><input type="submit" class="buttons" id="infefech" style="width: 190px" value="Generar informe entre fechas"></td>
                    </tr>
                    <tr>
                        <td><input type="submit" id="infsfech" class="buttons" style="width: 190px" value="Generar informe sin fechas"><br></td>
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
