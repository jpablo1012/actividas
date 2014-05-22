<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>


<head>
    <title>Bienvenido</title>
</head>
<body>
    <form id="lulo"  action="processor.jsp" method="post">
        <h3>Introduce tu nombre</h3>
        <table>
            <tr>
                <td>Nombre:</td>
                <td><input name="entrada" value=""/></td>
            </tr>
        </table>
        <p><input type="submit" value="Aceptar"/></p>
    </form>
</body>
</html>