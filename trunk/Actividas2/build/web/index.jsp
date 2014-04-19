<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@page contentType="text/html" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
    </head>

    <body>
        <div class="main">
            <div class="principal">

                <jsp:include page="private/header.jspf" flush="true"/>
                <jsp:include page="private/mn1.jspf" flush="true"/>
                
                <div class="toggler">
                    <div id="effect" class="effectDes">
                        <form style="display: flex; height: 100px" name="validate" id="formulario" action="/Actividas/SvlAutentificacion" method="post">
                            <table class="loginc">
                                <tr>
                                    <td class="acom">C&eacutedula:</td>
                                    <td><input type="text" id="cedula" name="cedula" required></td>
                                </tr>
                                <tr>
                                    <td class="acom">Contraseña:</td>
                                    <td><input type="password" id="codigo" name="codigo" required></td>
                                </tr>
                                <tr>
                                    <td colspan="2" align="center"><input id="enter" type="submit" value="Ingresar" ></td>
                                </tr>
                                <tr>
                                    <td colspan="2"><p id="mensaje"></p></td>
                                </tr>
                            </table>
                        </form>
                    </div>
                </div>
                <jsp:include page="private/inf.jspf" flush="true"/>
                <jsp:include page="private/footer.jspf" flush="true"/>
            </div>
        </div>
    </body>

</html>
