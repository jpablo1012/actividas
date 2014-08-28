$(document).ready(function() {

    $("#bOrden").click(function() {
        $(".info").load("buscarOrden.jsp");
    });

    $("#extru").click(function() {
        cargarOrdenes();
        $(".info").load("extrusion.jsp");
    });

    $("#impre").click(function() {
        cargarOrdenesI();
        $(".info").load("impresion.jsp");
    });

    $("#sella").click(function() {
        cargarOrdenesS();
        $(".info").load("sellado.jsp");
    })
});