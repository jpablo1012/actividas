var tipo;
$("document").ready(function() {
    fecha();
    completarref();
    tipo = $.cookie('tipo');
    if (tipo == "administrador") {
        var micapa = document.getElementById("crearPedi");
        micapa.innerHTML = '<label for="Cedulaa" style="margin:0 0 0 66px"><span style="color:#D3362D;">*</span>Cedula del Cliente</label><br><input class="form" style="width:120px; margin-left:66px;" type="text" id="cedulaa">';
    }
    if (tipo == "cliente") {
        var micapa2 = document.getElementById("esperaP");
        micapa2.style.display = "none";
    }

    $("#crearPedido").submit(function() {
        enviar();
        return false;
    });
});

function validar() {
    var cont = true;
    var cedula1 = $("#cedulaa").val();
    ;
    tipo = $.cookie('tipo');
    var fecha;
    var f = new Date();
    fecha = f.getFullYear() + "-" + (f.getMonth() + 1) + "-" + f.getDate();
    var fechaEntrega = $("#FEntrega").val();
    var dateh = new Date(fecha);
    var date = new Date(fechaEntrega);
    var cantidades = $("#cantidad").val();
    var medidaCa = $("#cantidadU").val();
    var tipoV = $("#tipoVenta").val();
    var refBolsa = $("#refBolsa").val();
    var referenciaT = $("#textBolsa").val();
    var extrusion1 = false;
    var impresion1 = false;
    var sellado1 = false;

    var regex = /^\d+$/;

    if (regex.test(cedula1) && cedula1.length >= 6 && cedula1.length <= 13) {
        $("#cedulaa").addClass("exito");
        $("#cedulaa").parent().parent().next().text("");
    } else {
        $("#cedulaa").addClass("error");
        $("#cedulaa").parent().parent().next().text("Cédula no válida");
        cont = false;
    }

    if (date < dateh) {
        $("#FEentrega").addClass("error");
        $("#FEentrega").parent().parent().next().text("Ingrese una fecha posterior");
        cont = false;
    } else {
        $("#FEentrega").parent().parent().next().text("");
        $("#FEentrega").addClass("exito");
    }

    if (tipoV == "seleccionar") {
        $("#tipoVenta").addClass("error");
        $("#tipoVenta").parent().parent().next().text("Seleccione un tipo de venta");
        cont = false;
    } else {
        $("#tipoVenta").parent().parent().next().text("");
        $("#tipoVenta").addClass("exito");
    }

    if (refBolsa == "ninguno") {
        $("#refBolsa").addClass("error");
        $("#refBolsa").parent().parent().next().text("Seleccione una bolsa");
        cont = false;
    } else {
        $("#refBolsa").parent().parent().next().text("");
        $("#refBolsa").addClass("exito");
    }

    if (medidaCa == "seleccionar") {
        $("#cantidadU").addClass("error");
        $("#cantidadU").parent().parent().next().text("Seleccione una unidad de medida");
        cont = false;
    } else {
        $("#cantidadU").parent().parent().next().text("");
        $("#cantidadU").addClass("exito");
    }

    if (cantidades == "" || cantidades == null) {
        $("#cantidad").addClass("error");
        $("#cantidad").parent().parent().next().text("Campo vacio");
        cont = false;
    } else {
        $("#cantidad").parent().parent().next().text("");
        $("#cantidad").addClass("exito");
    }


    if (cont) {
        enviar();
    }
}

function enviar() {
    var cedula1;
    tipo = $.cookie('tipo');
    var fechaEntrega = $("#FEntrega").val();
    var date = new Date(fechaEntrega);
    var cantidades = $("#cantidad").val();
    var medidaCa = $("#cantidadU").val();
    var tipoV = $("#tipoVenta").val();
    var refBolsa = $("#refBolsa").val();
    var referenciaT = $("#textBolsa").val();
    var extrusion1 = false;
    var impresion1 = false;
    var sellado1 = false;
    cedula1 = $("#cedulaa").val();

    if (tipo == "cliente") {
        cedula1 = $.cookie("cedula");
    }

    if (document.getElementById("check3").checked == true) {
        extrusion1 = true;
    }
    if (document.getElementById("check1").checked == true) {
        impresion1 = true;
    }
    if (document.getElementById("check2").checked == true) {
        sellado1 = true;
    }
    $("#crearPed").attr("disabled", "disabled");
    $("#Mensaje3").text(""); //deja en blanco el contenido que haya en el elemento con id "mensaje"
    $.ajax({
        url: '/Actividas/SvlPedido', //indica la direccion a la cual se va a enviar la informacion
        type: 'POST', //establece el metodo de envio
        timeout: 12000, //establece el tiempo maximo de espera en milisegundos
        dataType: 'text', //establece el tipo de datos
        data: {//datos que se envian al servidor
            fechaEntrega: date.getTime(),
            cantidad: cantidades,
            medidac: medidaCa,
            tipoVenta: tipoV,
            referenciaBolsa: refBolsa,
            referencia: referenciaT,
            extrusion: extrusion1,
            impresion: impresion1,
            sellado: sellado1,
            cedula: cedula1,
            tipo: "crear"
        }
    }).done(function(responseText) { //cuando el servidor entrega una respuesta se ejecuta esta funcion anonima
        var j = JSON.parse(responseText); //convierte la respuesta en un JSON
        traducira(j); //envia el JSON a "traducir" para su posterior analisis
        $("#crearPed").removeAttr("disabled");
    }).fail(function() { //si el tiempo de espera se acaba ejecuta esta funcion anonima
        $("#Mensaje3").text("Error al comunicarse con el servidor");
        $("#crearPed").removeAttr("disabled");
    });
}

function traducira(j) {
    var s = j.mensaje;

    if (s == "") {
        $("#Mensaje3").text("El Pedido ha sido creado exitosamente");
        $("#FEntrega").val("");
        $("#cantidadU").val("");
        $("#tipoVenta").val("");
        $("#refBolsa").val("");
        $("#textBolsa").val("");

    }
    if (s == "1") {
        $("#Mensaje3").text("Error al conectarse a la base de datos");
    }
    if (s == "2") {
        $("#Mensaje3").text("Error al conectarse a la base de datos");
        $("#crearPed").removeAttr("disabled");
    }
}

function completarref() {
    $.ajax({
        url: '/Actividas/SvlPedido',
        type: 'POST',
        timeout: 20000,
        dataType: 'text',
        data: {
            tipo: "llenarrf"
        }
    }).done(function(responseText) {
        var k = JSON.parse(responseText);
        llenarba(k);
    }).fail(function() {
    });
}

function llenarba(k) {
    var nd = 0;
    nd = k.buscar.length;
    $("#refBolsa").html("");
    $("<option value='ninguno'>-Seleccionar-</option>").appendTo("#refBolsa");
    for (var l = 0; l < nd; l++) {
        var id = k.buscar[l].id;
        $('<option value=' + id + '>' + "Ref:" + id + '</option>').appendTo("#refBolsa");

    }
}

function fecha() {
    var fecha;
    var f = new Date();
    fecha = f.getFullYear() + "-";
    if (f.getMonth() + 1 <= 9) {
        fecha = fecha + "0";
    }
    fecha = fecha + (f.getMonth() + 1) + "-";
    if (f.getDate() <= 9) {
        fecha = fecha + "0";
    }
    var d = f.getDate() + 1;
    fecha = fecha + d;
    $("#FEntrega").val(fecha);
    $("#FEntrega").attr("min", fecha);
}