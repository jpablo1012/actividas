$(document).ready(function() {
    $("#crearM").click(function() {
        $(".info").load("crearMaterial.jsp");
    });

    $("#crearCo").click(function() {
        $(".info").load("crearColor.jsp");
    });

    $("#crearB").click(function() {
        $(".info").load("crearBolsa.jsp");
    });

    $("#mcb").click(function() {
        $(".info").load("crearBolsa.jsp");

    });
    
    $("#buscarI").click(function() {
        $(".info").load("buscarInsumo.jsp");
    });
});

