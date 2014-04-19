<%-- 
    Document   : processor
    Created on : 6/04/2014, 10:53:26 PM
    Author     : juanx96506
--%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@page import="java.lang.System"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
getServletConfig().getServletContext().getRequestDispatcher("/Actividas/Controlador/SvlAutentificacion").forward(request, response);
%>
<html>
    <head>
        <meta charset="utf-8">

    </head>
    <body>
         <div class="main">
            <div class="principal">
             <jsp:include page="private/header.jspf" flush="true"/>
             <jsp:include page="private/mn1.jspf" flush="true"/>
        <div class="info">
        </div>
        <jsp:include page="private/footer.jspf" flush="true"/>
            </div>
         </div>
    </body>
</html>
