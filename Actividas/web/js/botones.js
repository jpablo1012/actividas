$(document).ready(function() {

    $(".info1 li").click(function() {
        var s = $(this).next();
        if (s.attr("class") == "effectDes" || s.attr("class") == s.attr("class") == undefined) {
            s.removeClass("effectDes");
            s.addClass("effectAct");
            s.css("height", "400px");
        } else {
            s.removeClass("effectAct");
            s.addClass("effectDes");
            s.css("height", "0px");

        }
        return false;
    });

    $("#check4").click(function() {
        if ($("#tps").attr("class") == "hidden") {
            $("#tps").removeClass("hidden");
            $("#tps").addClass("form");
        } else {
            $("#tps").removeClass("form");
            $("#tps").addClass("hidden");
        }
    });

    $("#check5").click(function() {
        if ($("#tpi").attr("class") == "hidden") {
            $("#tpi").removeClass("hidden");
            $("#tpi").addClass("form");
        } else {
            $("#tpi").removeClass("form");
            $("#tpi").addClass("hidden");
        }
    });

    $("#check6").click(function() {
        if ($("#flli").attr("class") == "hidden") {
            $("#flli").removeClass("hidden");
            $("#flli").addClass("form");
        } else {
            $("#flli").removeClass("form");
            $("#flli").addClass("hidden");
        }
    });

    $("#check7").click(function() {
        if ($("#flls").attr("class") == "hidden") {
            $("#flls").removeClass("hidden");
            $("#flls").addClass("form");
        } else {
            $("#flls").removeClass("form");
            $("#flls").addClass("hidden");
        }
    });

    $("#check8").click(function() {
        if ($("#flll").attr("class") == "hidden") {
            $("#flll").removeClass("hidden");
            $("#flll").addClass("form");
        } else {
            $("#flll").removeClass("form");
            $("#flll").addClass("hidden");
        }
    });

    $("#check12").click(function() {
        if ($("#slp").attr("class") == "hidden") {
            $("#slp").removeClass("hidden");
            $("#slp").addClass("form");
        } else {
            $("#slp").removeClass("form");
            $("#slp").addClass("hidden");
        }
    });
    
    $("#bOrden").click(function() {
        $(".info").load("buscarOrden.jsp");
    })

    $("#extru").click(function() {
        cargarOrdenes();
        $(".info").load("extrusion.jsp");
    })

    $("#impre").click(function() {
        cargarOrdenesI();
        $(".info").load("impresion.jsp");
    })

    $("#sella").click(function() {
        cargarOrdenesS();
        $(".info").load("sellado.jsp");
    })

    $("#mopa").click(function() {
        cargarOrdenes();
        $(".info").load("extrusion.jsp");
    });

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

    $("#mpa").click(function() {
        $(".info").load("crearPedido.jsp");
    });

    $("#mca").click(function() {
        $(".info").load("introCli.jsp");
    });

    $("#buscar").click(function() {
        $(".info").load("buscarCliente.jsp");
    });

    $("#crear").click(function() {
        $(".info").load("crearCliente.jsp");
    });

    $("#mea").click(function() {
        $(".info").load("crearEmpleado.jsp");
    });

    $("#crearE").click(function() {
        $(".info").load("crearEmpleado.jsp");
    });

    $("#buscarE").click(function() {
        $(".info").load("buscarEmpleado.jsp");
    });

    $("#mmpa").click(function() {
        $(".info").load("insumos.jsp");
    });

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

    $("#pmc").click(function() {
        $(".info").load("perfilCliente.jsp");
    });

    $("#pme").click(function() {
        $(".info").load("perfilEmpleado.jsp");
    });

    $("#mia").click(function() {
        $(".info").load("informes.jsp");
    });
});


var bo = true;

function animacion() {
    this.next().disabled = true

    if (bo) {
        $("#producto1").removeClass("effectDes");
        $("#producto1").addClass("effectAct");
        bo = !bo;
    } else {
        $("#producto1").removeClass("effectAct");
        $("#producto1").addClass("effectDes");
        bo = !bo;
    }
}

function cargarPedidos() {
    $.ajax({
        url: '/Actividas/SvlPedido',
        type: 'POST',
        timeout: 20000,
        dataType: 'text',
        data: {
            pista: "pendiente",
            valor: "estado",
            tipo: "getPedidos"
        }
    }).done(function(responseText) {
        var k = JSON.parse(responseText);
        insertarP(k);
    }).fail(function() {

    });

}

function insertarP(k) {

    var nd = 0;
    nd = k.buscar.length;
    var tabla = document.getElementById('pedidosR');
    var texto = "";
    for (var l = 0; l < nd; l++) {
        var numeroPedido = "";
        numeroPedido = k.buscar[l].referenciaP;
        var cedula = k.buscar[l].cedula;
        var fechaEntrega = k.buscar[l].Fentrega;
        var tipoVenta = k.buscar[l].Tventa;
        texto += '<div id="pedidod"><label id="cedula' + l + '" style="margin: 30px 0px 0px 20px;">' + cedula + '</label>' +
                '<label id="fechaEntrega" style="margin: 0px 0px 0px 200px;">' + fechaEntrega + '</label>' +
                '<label id="tipoVenta" style="margin: 0px 0px 0px 64px;">' + tipoVenta + '</label>' +
                '<br><input id="' + numeroPedido + '" name="aprobado" onclick="aprobar(this.id,this.name)" value="Aprobar" type="button" style="margin: 15px 0px 0px 10px;">' +
                '<input id="' + numeroPedido + '" name="rechazado" onclick="aprobar(this.id,this.name)" value="Rechazar" type="button" style="margin: 0px 0px 0px 10px;">' +
                '<input id="masInf' + l + '" value="Mas informacion" type="button" style="margin: 0px 0px 0px 150px;">' +
                '<label id="n' + l + '" style="margin: 0px 0px 0px 10px;">Numero de Pedido: ' + numeroPedido + '</label></div>';
    }

    tabla.innerHTML = texto;
}

function cargarOrdenes() {

    $.ajax({
        url: '/Actividas/SvlOrden',
        type: 'POST',
        timeout: 20000,
        dataType: 'text',
        data: {
            tipo: "extrusion",
            pista: "estado",
            valor: "ejecutando"
        }
    }).done(function(responseText) {
        var ko = JSON.parse(responseText);
        insertarO(ko);
    }).fail(function() {

    });

}

function insertarO(k) {
    var nd = 0;
    nd = k.buscar.length;
    var tabla = document.getElementById('ordenR');
    var texto = "";
    for (var l = 0; l < nd; l++) {
        var numeroOrden = k.buscar[l].nOrden;
        var bolsaRef = k.buscar[l].Bref;
        var numeroExtrusion = k.buscar[l].nExtrusion;
        var tipoCantidad = k.buscar[l].Tcantidad;
        var cantidad = k.buscar[l].cantidad;
        var fechaInicio = k.buscar[l].fechaIni;
        var color;
        if ((l % 2) == 0) {
            color = "#D4D0D0";
        } else {
            color = "#F8F7F7";
        }
        texto += '<div id="ordend" fecha="' + fechaInicio + '" name="' + bolsaRef + '" refeBolsa="' + bolsaRef + '" numeroOrden="' + numeroOrden + '" extrusion="' + numeroExtrusion + '" style="background: ' + color + '" onclick="llenar(this)"><label id="numeroO' + l + '" style="position:absolute;left:195px; margin-top:12px;">' + numeroOrden + '</label>' +
                '<label id="refBolsa" style="position:absolute;left:375px; margin-top:12px;">' + bolsaRef + '</label>' +
                '<label id="cantidad" style="position:absolute;left:513px; margin-top:12px;">' + cantidad + " " + tipoCantidad + '</label>' +
                '<label id="fechaInicio" style="position:absolute;left:665px; margin-top:12px;">' + fechaInicio + '</label></div>';
    }

    tabla.innerHTML = texto;
}

function cargarOrdenesI() {
    $("#mensajea").text(" ");
    $.ajax({
        url: '/Actividas/SvlOrden',
        type: 'POST',
        timeout: 20000,
        dataType: 'text',
        data: {
            tipo: "impresion",
        }
    }).done(function(responseText) {
        var ko = JSON.parse(responseText);
        insertarI(ko);
    }).fail(function() {

    });

}

function insertarI(k) {
    var nd = 0;
    nd = k.buscar.length;
    var tabla = document.getElementById('ordenI');
    var texto = "";
    for (var l = 0; l < nd; l++) {
        var notas = k.buscar[l].nota;
        var impresionI = k.buscar[l].impresion;
        var pesoInicial = k.buscar[l].pIncial;
        var pesoFinal = k.buscar[l].pFinal;
        var numeroOrden = k.buscar[l].nOrden;
        var bolsaRef = k.buscar[l].Bref;
        var numeroImpresion = k.buscar[l].nImpresion;
        var tipoCantidad = k.buscar[l].Tcantidad;
        var cantidad = k.buscar[l].cantidad;
        var fechaInicio = k.buscar[l].fechaIni;
        var fechaPlaz = k.buscar[l].fechaPlazo;
        var color;
        if ((l % 2) == 0) {
            color = "#D4D0D0";
        } else {
            color = "#F8F7F7";
        }
        texto += '<div id="ordendid" impresion="' + impresionI + '" pesoIni="' + pesoInicial + '" pesoFin="' + pesoFinal + '" nota="' + notas + '" fecha="' + fechaInicio + '" fechaPla="' + fechaPlaz + '" name="' + bolsaRef + '" numeroOrden="' + numeroOrden + '" impresion="' + numeroImpresion + '" style="background: ' + color + '" onclick="llenar(this)"><label id="numeroOI' + l + '" style="position:absolute;left:195px; margin-top:12px;">' + numeroOrden + '</label>' +
                '<label id="refBolsaI" style="position:absolute;left:375px; margin-top:12px;">' + bolsaRef + '</label>' +
                '<label id="cantidad" style="position:absolute;left:513px; margin-top:12px;">' + cantidad + " " + tipoCantidad + '</label>' +
                '<label id="fechaInicio" style="position:absolute;left:665px; margin-top:12px;">' + fechaPlaz + '</label></div>';
    }

    tabla.innerHTML = texto;
}

function cargarOrdenesS(){
    $("#mensajeI").text(" ");
    $.ajax({
        url: '/Actividas/SvlOrden',
        type: 'POST',
        timeout: 20000,
        dataType: 'text',
        data: {
            tipo: "sellado",
        }
    }).done(function(responseText) {
        var ks = JSON.parse(responseText);
        insertarS(ks);
    }).fail(function() {

    });

}

function insertarS(k) {
    var nd = 0;
    nd = k.buscar.length;
    var tabla = document.getElementById('ordenS');
    var texto = "";
    for (var l = 0; l < nd; l++) {
        var notas = k.buscar[l].nota;
        var retalS = k.buscar[l].retal;
        var bolsasSell = k.buscar[l].bolsasSelladas;
        var pesoFinal = k.buscar[l].pFinal;
        var numeroOrden = k.buscar[l].nOrden;
        var bolsaRef = k.buscar[l].Bref;
        var numeroSellado = k.buscar[l].nSellado;
        var tipoCantidad = k.buscar[l].Tcantidad;
        var cantidad = k.buscar[l].cantidad;
        var fechaInicio = k.buscar[l].fechaIni;
        var fechaPlaz = k.buscar[l].fechaPlazo;
        var color;
        if ((l % 2) == 0) {
            color = "#D4D0D0";
        } else {
            color = "#F8F7F7";
        }
        texto += '<div id="ordendis" Tcantidad="' + tipoCantidad + '" cantidad="' + cantidad + '" retal="' + retalS + '" bolsasSelladas="' + bolsasSell + '" nota="' + notas + '" fecha="' + fechaInicio + '" fechaPla="' + fechaPlaz + '" name="' + bolsaRef + '" numeroOrden="' + numeroOrden + '" sellado="' + numeroSellado + '" style="background: ' + color + '" onclick="llenarS(this)"><label id="numeroOS' + l + '" style="position:absolute;left:195px; margin-top:12px;">' + numeroOrden + '</label>' +
                '<label id="refBolsaS" style="position:absolute;left:375px; margin-top:12px;">' + bolsaRef + '</label>' +
                '<label id="cantidadS" style="position:absolute;left:513px; margin-top:12px;">' + cantidad + " " + tipoCantidad + '</label>' +
                '<label id="fechaInicioS" style="position:absolute;left:665px; margin-top:12px;">' + fechaPlaz + '</label></div>';
    }

    tabla.innerHTML = texto;
}