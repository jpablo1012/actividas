$(document).ready(function() {
    
    $("#crear").click(function() {
        console.log("cliqueado");
        $(".info").load("crearCliente.jsp");   
    });
    
    $("#buscar").click(function() {
        $(".info").load("buscarCliente.jsp");
    });
});