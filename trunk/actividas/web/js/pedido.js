$(document).ready(function() {
    $("#buscarP").click(function() {
        $(".info").load("buscarPedido.jsp");
    });

    $("#Pedidos").click(function() {
        $(".info").load("crearPedido.jsp");
    });

    $("#esperaP").click(function() {
        $(".info").load("esperaPedidos.jsp");
        cargarPedidos();
    });
});