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
        <script src="js/js-image-slider.js"></script>
        <link href="js/js-image-slider.css" rel="stylesheet">
        
        <style>
            .toggler { width: 250px; height: 100px;top:145px;left:684px;padding:0 0 0 0; position:absolute}
            #effect { width: 240px; height: 135px; padding: 5.4px 4px 4px 0px; position: relative; }
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
                            <li><a href="index.jsp"  class="active">Inicio</a></li>
                            <li><a href="quienessm.jsp">¿Quienes somos?</a></li>
                            <li><a href="productos.jsp">Productos</a><ul class="active"></ul></li>
                            <li><a href="contactenos.jsp">Cont&aacutectenos</a></li>
                            <li class="log"><a  id="button" href="#">Iniciar sesión</a></li>
                        </ul>
                    </div>
                </header>
                <div class="toggler">
                    <div id="effect" class="effectDes">
                        <form id="formulario">
                            <table class="loginc">
                                <tr>
                                    <td class="acom">C&eacutedula:</td>
                                    <td><input type="text" id="cedula" required></td>
                                </tr>
                                <tr>
                                    <td class="acom">Contraseña:</td>
                                    <td><input type="password" id="codigo" required></td>
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
                        <div id="slider">
                            <img src="images/01.jpg" alt="" />
                            <img src="images/02.jpg" alt="" />
                            <img src="images/03.jpg" alt="" />
                            <img src="images/04.jpg" />
                        </div>
                    </article>

                </div>
                <footer class="footer" id="princ">
                    <p>Copyright © 2014 <a href="http://actividas.260mb.net" target="_blank">Actividas Group.</a> All Rights Reserved.</p><p>Derechos de algunas imagenes: https://es.123rf.com y http://www.cgtextures.com</p>
                </footer>
            </div>
        </div>
    </body>
    <script src="js/sesion.js"></script>
        <script src="js/botones.js"></script>
        <script src="js/autentificar.js"></script>
    <script>
        var bo = true;

        $("#button").click(function(){
            animacion();
            return false;
        });

        function animacion(){
            if(bo){
                $("#effect").removeClass("effectDes");
                $("#effect").addClass("effectAct");
                bo = !bo;
            }else{
                $("#effect").removeClass("effectAct");
                $("#effect").addClass("effectDes");
                bo = !bo;
            }
        }
    </script>
    
</html>