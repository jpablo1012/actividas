
<!DOCTYPE html>
<html>
    <head>
        <meta content="text/html" charset="utf-8" />
        <meta charset="utf-8">
        <title>PlastiSer S.A.</title>
        <link href="style.css" rel="stylesheet">
        <script src="js/jquery-1.10.2.js"></script>
        <script src="js/jquery.cookie.js"></script>
        <script src="js/jquery-ui.js"></script>

        <style>
            .toggler { width: 250px; height: 100px;top:145px;left:684px;padding:0 0 0 0; position:absolute}
            #effect { width: 240px; height: 135px; padding: 0.4em; position: relative; }
            #effect h3 { margin: 0; padding: 0.4em; text-align: center; }
        </style>

    </head>

    <body>
        <div class="main">
            <div class="principal">

                <header class="title">
                    <div class="title-cont">
                        <a href="index.jsp"><h1 class="logo">PlastiSer S.A.</h1></a>
                        <div id="art-flash-area">
                            <div id="art-flash-container">
                                <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width="900" height="150" id="art-flash-object">
                                    <param name="movie" value="container.swf">
                                    <param name="quality" value="high">
                                    <param name="scale" value="default">
                                    <param name="wmode" value="transparent">
                                    <param name="flashvars" value="color1=0xFFFFFF&amp;alpha1=.50&amp;framerate1=24&amp;loop=true&amp;wmode=transparent&amp;clip=images/flash.swf&amp;radius=12&amp;clipx=0&amp;clipy=-38&amp;initalclipw=900&amp;initalcliph=225&amp;clipw=900&amp;cliph=225&amp;width=900&amp;height=150&amp;textblock_width=0&amp;textblock_align=no&amp;hasTopCorners=false&amp;hasBottomCorners=true">
                                    <param name="swfliveconnect" value="true">
                                    <!--[if !IE]>-->
                                    <object type="application/x-shockwave-flash" data="swf/container.swf" width="900" height="150">
                                        <param name="quality" value="high">
                                        <param name="scale" value="default">
                                        <param name="wmode" value="transparent">
                                        <param name="flashvars" value="color1=0xFFFFFF&amp;alpha1=.50&amp;framerate1=24&amp;loop=true&amp;wmode=transparent&amp;clip=images/flash.swf&amp;radius=12&amp;clipx=0&amp;clipy=-38&amp;initalclipw=900&amp;initalcliph=225&amp;clipw=900&amp;cliph=225&amp;width=900&amp;height=150&amp;textblock_width=0&amp;textblock_align=no&amp;hasTopCorners=false&amp;hasBottomCorners=true">
                                        <param name="swfliveconnect" value="true">
                                        <!--<![endif]-->
                                        <<div class="art-flash-alt"><a href="http://www.adobe.com/go/getflashplayer"></a></div>
                                        <!--[if !IE]>-->
                                    </object>
                                    <!--<![endif]-->
                                </object>
                            </div>
                        </div>
                    </div>
                    <div class="menubar">
                        <ul class="art-hmenu" id="barra">
                            <li><a href="index.jsp" >Inicio</a></li>
                            <li><a href="quienessm.jsp">¿Quienes somos?</a></li>
                            <li><a href="productos.jsp" class="active">Productos</a><ul class="active"></ul></li>
                            <li><a href="contactenos.jsp">Cont&aacutectenos</a></li>
                            <li class="log"><a  id="button" href="#">Iniciar sesi&oacuten</a></li>
                        </ul>
                    </div>
                </header>
                <div class="toggler" >
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
                <div class="info">
                    <article id="contenido">
                        <div class="info1" >

                            <h4 style="text-align:center">Productos</h4><br><br>

                            <p style="font-size: 15px;">Somos fabricantes  de bolsas y tubulares en polietileno de baja, alta densidad y polipropileno impresos y sin impresión, con diferentes medidas, calibres, colores y formas. Con troquel camiseta, troquel riñón, con tiras, cinta resellable, broches y alma de cartón</p><br>

                            <li ><a  href="#"><h3>Bolsa en polietileno de baja densidad</h3></a></li>
                            <div id="productos" class="effectDes">
                                <p style="font-size: 15px;">Diseñadas para el comercio y la industria de alimentos, confecciones, productos agrícolas, almacenes entre otros.</p>
                                <img src="images/p1.png">
                            </div>
                            <li><a  href="#"><h3>Bolsa en polietileno de alta densidad</h3></a></li>
                            <div id="productos" class="effectDes">
                                <p style="font-size: 15px;">Por sus características son ideales para supermercados y diferentes sectores industriales.</p>
                                <img src="images/p2.png">
                            </div>
                            <li><a  href="#"><h3>Bolsa en polipropileno</h3></a></li>
                            <div id="productos" class="effectDes">
                                <p style="font-size: 15px;">Por ser un material de barrera y alta trasparencia y brillo, se utiliza con frecuencia para empaques de alimento.</p>
                                <img src="images/p3.jpg">
                            </div>
                            <li><a  href="#"><h3>Tubulares, semitubulares y laminas</h3></a></li>
                            <div id="productos" class="effectDes">
                                <p style="font-size: 15px;">En polietileno de baja, alta densidad y polipropileno en diferentes colores, medidas y calibres.</p>
                                <img src="images/p4.jpg">
                            </div>

                        </div>
                    </article>

                </div>
                <footer class="footer" >
                    <p>Copyright © 2014 <a href="http://actividas.260mb.net" target="_blank">Actividas Group.</a> All Rights Reserved.</p>
                </footer>
            </div>
        </div>
    </body>
    <script>
        var bo = true;

        $("#button").click(function() {
            animacion();
            return false;
        });

        function animacion() {
            if (bo) {
                $("#effect").removeClass("effectDes");
                $("#effect").addClass("effectAct");
                bo = !bo;
            } else {
                $("#effect").removeClass("effectAct");
                $("#effect").addClass("effectDes");
                bo = !bo;
            }
        }
    </script>
</html>