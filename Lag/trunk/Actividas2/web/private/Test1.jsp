<%-- 
    Document   : Test1
    Created on : 6/04/2014, 11:00:36 PM
    Author     : juanx96506
--%>


<!DOCTYPE html>

<%
    String sesion = (String) request.getSession().getAttribute("sesione");
    String menuType;
    menuType = (String) request.getSession().getAttribute("type");
%>
<html>
    <head>
        <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
        <meta charset="UTF-8">
        <title>PlastiSoft</title>
    </head>
    <body>

                <jsp:include page="header.jspf" flush="true"/>
                <jsp:include page="<%=menuType%>" flush="true"/>
                <%
                    if (sesion == "yes") {
                %>
                <jsp:include page="wellcc.jspf" flush="true"/>
                <script type="text/javascript">
                    alert("BIenvenido sr <%=(String) request.getSession().getAttribute("name")%>");
                </script>
                <%} else {%>
                <jsp:include page="inf.jspf" flush="true"/>
                <script type="text/javascript">
                    alert("ERROR AL INICIAR SESION");
                </script>
                <%}
                %> 
                <jsp:include page="footer.jspf" flush="true"/>
    </body>
</html>
