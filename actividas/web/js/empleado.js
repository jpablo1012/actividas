$(document).ready(function() {
    $("#crearE").click(function() {
        $(".info").load("crearEmpleado.jsp");
    });

    $("#buscarE").click(function() {
        $(".info").load("buscarEmpleado.jsp");
    });
            
});