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
    b.decrypt(val);
    if(b.getDecryptedString().equals("validado")){   
%>
<html>
<head>
     <script src="js/botones.js"></script>
     <script src="js/heartcode-canvasloader-min-0.9.1.js"></script>
      <script src="js/buscarEmpleado.js"></script>
     <script type='text/javascript' src='js/jquery.modal.js'></script>
     <script src='js/actualizarEmpleado.js'></script>
     <script src="js/modfwin.js"></script>
     <script src="js/empleado.js"></script>
</head>
<body>
    <ul id='navigatione'>
            <li id="crearE"><a href='#'><p><b>Crear</b></p></a></li>
            <li id="buscarE"><a href='#'><p><b>Buscar</b></p></a></li>
    </ul>
    <div class="form1">
      <form id="buscarEmpleado">
          <fieldset id="answers">
            <legend><h4>Buscar Empleado</h4></legend>
        	<table class="infosearch" align="center" cellspacing="10">
            <tr>
                <td>
                    <input type="text" id="search" class="form" style="width:420px;height:28px" required/>
                    <select id="buscarCon" class="form" style="height:28px">
                      <option value="ninguno">-Seleccionar-
                      <option value="cedula">C&eacute;dula
                      <option value="nombre">Nombre
                      <option value="apellido">Apellido
                      <option value="cargo">Cargo
                    </select>
                </td>
            </tr>
            <tr>
                <td align="center">
                    <div></div><br>
                    <input type="button" class="buttons" id="buscare" value="Buscar"/><br><br>
                    <div id="canvasloader-container"></div>
                </td>
            </tr>
            <tr>
                <td colspan="2" id="mensaje"></td>
            </tr>
              </table>
        </fieldset>
              <fieldset id="answer" class="effectDes">
                 <table id="resanswer" align="center" class="resanswer" border="0">

                 </table>
                 <table align="center"><tr>
                 <td><input type="reset" id="buscarl" class="buttons" value="Limpiar" style="margin:42px 38%"></td>
                 <td><input type="button" id="modificarl" class="buttons" value="Modificar" style="margin:42px 38%"></td>
                 <td><input type="button" id="eliminarl" class="buttons" value="Eliminar" style="margin:42px 38%"></td></tr></table>
              </fieldset>
      </form>
        </div>
        <form id="actualizarEmpleado" class="actualizarEmpleado">

                <div id="actualizarEmpleado" class="overlay"></div>
                    <div id="actualizarEmpleado" class="modal">
                        <div style="background:white; margin:0 auto; text-align:center;">
                            <legend><h4 align="center">Modificar Empleado</h4></legend>
                            <span id="close" class="close">X</span>
                            <table align="center" cellspacing="10" style="text-align: left">
                                 <tr>
                                     <td>
                                        <label for="cedulam">C&eacute;dula</label><br>
                                        <input type="text" class="formM" id="cedulam" style="width:100px" disabled="disbled"/>
                                        <label id="IdUsuario"></label>
                                    </td>

                                    <td>

                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label for="nombrem">Nombre</label><br>
                                        <input type="text" class="formM" id="nombrem" style="width:120px" placeholder=" Nombre" required/>
                                        <input type="text" class="formM" id="apellidom" style="width:120px" placeholder=" Apellido" required/>
                                    </td>

                                    <td>

                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label for="codigom">Contrase&ntilde;a</label><br>
                                        <input type="password" class="formM" id="codigom" name="codigo" style="width:100px" placeholder="Contrase&ntilde;a" required/>
                                        <input type="password" class="formM" id="conCodigo" name="concodigo" style="width:100px" placeholder="Confirmar contrase&ntilde;a"/>
                                    </td>
                                    <td>

                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label for="cargom">Cargo</label><br>
                                        <select id="cargom" class="form" required>
                                            <option value="administrador">Administrador</option>
                                            <option value="extrusor">Extrusor</option>
                                            <option value="impresor">Impresor</option>
                                            <option value="sellador">Sellador</option>
                                        </select>
                                    </td>
                                    <td>

                                    </td>
                                </tr>
                                <div id="imagen"></div>
                            </table>
                            <br>
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