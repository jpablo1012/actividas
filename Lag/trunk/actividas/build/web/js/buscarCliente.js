var cl //Loader
var l = 1; //For confirmation message
cl = new CanvasLoader('canvasloader-container');
cl.setColor('#adadad'); // default is '#000000'
cl.setDiameter(55); // default is 40
cl.setDensity(55); // default is 40
cl.setRange(1); // default is 1.3
cl.setFPS(20); // default is 24
$("document").ready(function() {

    $("#buscarc").click(function() {
        comprobar();
    });
    $("#buscarl").click(function() {
        $("#answer1").removeClass("effectAct");
        $("#answer1").addClass("effectDes");
        $("#answer1").css("height", "0px")
    });
    $("#actualizarCliente").submit(function() {
        $("#modificate").click(function() {
            cl.show();
            comprobar();
        });
    });
});

function comprobar() {
    var variable = $("#search").val();
    var buscar = $("#buscarCon").val();
    var cont = true;

    if (buscar != "ninguno") {
        $("#buscarCon").addClass("exito");
    } else {
        $("#buscarCon").addClass("error");
        cont = false;
        cl.hide();
    }
    if (cont) {
        cl.show(); // Hidden by default hide();
        enviar();
    }
}

function enviar() {
    $("#mensaje").text("");
    $("#buscarc").attr("disabled", "disabled");
    $.ajax({
        url: '/Actividas/SvlCliente',
        type: 'POST',
        timeout: 20000,
        dataType: 'text',
        data: {
            variable: $("#search").val(),
            buscar: $("#buscarCon").val(),
            tipo: "buscar"
        }
    }).done(function(responseText) {
        var j = JSON.parse(responseText);
        console.log(j);
        traducir(j);
        $("#buscarc").removeAttr("disabled");
    }).fail(function() {
        $("#mensaje").text("Error al comunicarse con el servidor");
        $("#buscarc").removeAttr("disabled");
        cl.hide();
    });
}

function traducir(j) {
    console.log(j);
    $("#mensaje").text("");
    var nd = 0;
    nd = j.buscar.length;
    var tabla = document.getElementById('resanswer1'), tr, td;
    tabla.innerHTML = "";

    tr = tabla.insertRow(tabla.rows.length);
    td = tr.insertCell(tr.cells.length);
    td.innerHTML = "<p class='titletable'>Nombre</p>";
    td = tr.insertCell(tr.cells.length);
    td.innerHTML = "<p class='titletable'>Apellido</p>";
    td = tr.insertCell(tr.cells.length);
    td.innerHTML = "<p class='titletable'>Cédula</p>";
    td = tr.insertCell(tr.cells.length);
    td.innerHTML = "<p class='titletable'>Teléfono</p>";

    for (var o = 0; o < nd; o++) {

        var td1 = j.buscar[o].nombre;
        var td2 = j.buscar[o].apellido;
        var td3 = j.buscar[o].cedula;
        var td4 = j.buscar[o].telefono;

        tr = tabla.insertRow(tabla.rows.length);
        td = tr.insertCell(tr.cells.length);
        td.innerHTML = td1;
        td = tr.insertCell(tr.cells.length);
        td.innerHTML = td2;
        td = tr.insertCell(tr.cells.length);
        td.innerHTML = td3;
        td = tr.insertCell(tr.cells.length);
        td.innerHTML = td4;

    }
    $("#mensaje").text("Se han encontrado " + o + " clientes");
    $("#answer1").addClass("effectAct");
    $("#answer1").css("height", "330px");
    cli(j);
    cl.hide();
}

function cli(j) {
    $("#resanswer1 tr:not(:first-of-type)").click(function() {
        $("#resanswer1 tr:not(:first-of-type)").removeClass("seleccionado");
        $(this).addClass("seleccionado");
    });

    $("#modificarl").click(function() {
        $("#mensajea").text("");
        var s = $(".seleccionado").children().eq(2).text();
        console.log(s);
        var n = 0, r = 0;

        for (n = 0; n < j.buscar.length; n++) {

            if (j.buscar[n].cedula == s) {
                r = n;
                console.log(r);
            }
        }
        console.log(j.buscar[r].ciudad)
        $("#nombrem").val(j.buscar[r].nombre);
        $("#codigom").val(j.buscar[r].codigo);
        $("#apellidom").val(j.buscar[r].apellido);
        $("#cedulam").val(j.buscar[r].cedula);
        $("#direccionm").val(j.buscar[r].direccion);
        $("#telefonom").val(j.buscar[r].telefono);
        $("#emailm").val(j.buscar[r].correo);
        $("#ciudadm").val(j.buscar[r].ciudad);
        $("#IdUsuario").val(j.buscar[r].IdUsuario);
        if (j.buscar[r].direccionimg == undefined) {
            $("#imagen").attr("direccion", "");
        } else {
            $("#imagen").attr("direccion", j.buscar[r].direccionimg);
        }
    });
    $("#eliminarl").click(function() {
        if (l == 1) {
            var m = confirm("¿Seguro que desea eliminar este cliente?");
            if (m == true) {
                var s = $(".seleccionado").children().eq(2).text();
                var n = 0;
                var cedulac, codigoc, nombrec, apellidoc, direccionc, ciudadc, telefonoc, correoc, idusuarioc;
                for (n = 0; n < j.buscar.length; n++) {

                    if (j.buscar[n].cedula == s) {
                        cedulac = j.buscar[n].cedula;
                        nombrec = j.buscar[n].nombre;
                        apellidoc = j.buscar[n].apellido;
                        codigoc = j.buscar[n].codigo;
                        ciudadc = j.buscar[n].ciudad;
                        direccionc = j.buscar[n].direccion;
                        telefonoc = j.buscar[n].telefono;
                        correoc = j.buscar[n].correo;
                        idusuarioc = j.buscar[n].IdUsuario;
                    }
                }
                $.ajax({
                    url: '/Actividas/SvlCliente', //indica la direccion a la cual se va a enviar la informacion
                    type: 'POST', //establece el metodo de envio
                    timeout: 20000, //establece el tiempo maximo de espera en milisegundos
                    dataType: 'text', //establece el tipo de datos
                    data: {//datos que se envian al servidor
                        cedula: (cedulac),
                        nombre: (nombrec),
                        apellido: (apellidoc),
                        codigo: (codigoc),
                        ciudad: (ciudadc),
                        direccion: (direccionc),
                        telefono: (telefonoc),
                        correo: (correoc),
                        IdUsuario: (idusuarioc),
                        tipo: "eliminar"

                    }
                }).done(function(responseText) {//cuando el servidor entrega una respuesta se ejecuta esta funcion anonima
                    var k = JSON.parse(responseText); //convierte la respuesta en un JSON
                }).fail(function() { //si el tiempo de espera se acaba ejecuta esta funcion anonima
                    $("#mensaje").text("Error al comunicarse con el servidor");
                });
            }
            l = 2;
        }
        cl.show();
        comprobar();
    });
    l = 1;
}
$("#modificate").click(function() {
    cl.show();
    comprobar();
});

function tomarDatos() {
    var cedu = $(".seleccionado").children().eq(2).text();
//console.log(cedu);
}