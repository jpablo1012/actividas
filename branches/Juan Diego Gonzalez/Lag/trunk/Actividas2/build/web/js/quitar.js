$("document").ready(function() {
    if($.cookie('tipo') == "cliente"){
        console.log("si entro");
        var b = $("#crearCo");
        var c = $("#buscarI");
        var a= $("#navigatione");
        var d= $("#esperaP");
        a.css("display", "none");
        b.css("display", "none");
        c.css("display", "none");
    }
});