package swf;

package swf;

package swf;

package swf;



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML >

<html>
<head>
     <script src="js/botones.js"></script>
</head>
<body>
    <ul id='navigatione'>
            <li id="crear"><a href='#'><p><b>Crear</b></p></a></li>
            <li id="buscar"><a href='#'><p><b>Buscar</b></p></a></li>
	</ul>
<div class="form1">
      
      <form id="crearCliente">
          
           <fieldset>
               <legend><h4 align="center" >Cliente</h4></legend>
        	<table style="margin:40px 0px 0px 28%" align="center" cellspacing="10" >
            <tr>
                <td align="right" style="padding: 5px 30px 5px 0px">
                    <label for="Cedula">C&eacute;dula</label><br>
                </td>
                <td colspan="2">
                    <input type="text" class="form" id="cedulaf" style="width:100px" required/>
                </td>
            </tr>
            <tr>
                <td colspan="2">

                </td>
            </tr>
            <tr>
                <td align="right" style="padding: 5px 30px 5px 0px">
	              <label for="Nombre">Nombre</label><br>
                </td>
                <td>
                  <input type="text" class="form" id="nombre" style="width:120px" placeholder=" Nombre" required/>
	              <input type="text" class="form" id="apellido" style="width:120px;margin:0px 10px" placeholder=" Apellido" required/>
              	</td>
            </tr>
            <tr>
                <td colspan="2">

                </td>
            </tr>
            <tr>
              <td align="right" style="padding: 5px 30px 5px 0px">
                  <label for="Direccion">Direcci&oacute;n</label><br>
              </td>
              <td>
              <input type="text" class="form" name="Direccion" id="direccion" style="width:120px" required/>
              <input type="text" class="form" name="Telefono" id="telefono" style="width:80px; margin:0px 10px" placeholder="telefono" required/>
              </td>
            </tr>
             <tr>
              <td colspan="2">

              </td>
            </tr>
            <tr>
              <td align="right" style="padding: 5px 30px 5px 0px">
                  <label for="Ciudad">Ciudad</label><br>
              </td>
              <td colspan="2">
                  <input type="text" class="form" name="Ciudad" id="ciudad" style="width:80px" placeholder="ciudad" required/>
              </td>
            </tr>
             <tr>
              <td colspan="2">

              </td>
            </tr>
            <tr>
            	<td align="right" style="padding: 5px 30px 5px 0px">
                    <label for="codigo">C&oacute;digo</label><br>
                </td>
                <td colspan="2">
                    <input type="password" class="form" id="codigof" name="codigo" style="width:100px" placeholder="Codigo" required/>
                </td>
            </tr>
            <tr>
              <td colspan="2">

              </td>
            </tr>
            <tr>
            	<td align="right" style="padding: 5px 30px 5px 0px">
                    <label for="Concodigo">Confirmar C&oacute;digo</label><br>
                </td>
                <td colspan="2">
                    <input type="password" class="form" id="conCodigo" name="concodigo" style="width:100px" placeholder="Confirmar codigo" required/>
              </td>
            </tr>
            <tr>
              <td colspan="2">

              </td>
            </tr>
            
            <tr>
            <td align="right" style="padding: 5px 30px 5px 0px">
                <label for="email">E-mail</label><br>
            </td>
            <td colspan="2">
              <input type="email" class="form" name="email" id="email" style="width:150px" required/>
            </td>
            </tr>
            <tr>
            <td  colspan="2">

            </td>
            </tr>
              </table><br><br>
                  </fieldset>
              <table align="center">
              <tr>
              	<td colspan="2">
                    <center>
                        <input type="submit" class="buttons" id="register" value="Crear"/>
                        <input type="reset" class="buttons" value="Borrar"/>
                    </center>
              	</td>
              </tr>
              <tr>
                <td colspan="2" id="mensaje">

                </td>
             </tr>
            </table>
      </form>
        
        </div>
        <script src="js/crearCliente.js"></script>

</body>
</html>
