var cl; //Loader
var l = 1; //For confirmation message
cl = new CanvasLoader('canvasloader-container');
cl.setColor('#adadad'); // default is '#000000'
cl.setDiameter(55); // default is 40
cl.setDensity(55); // default is 40
cl.setRange(1); // default is 1.3
cl.setFPS(20); // default is 24
$("document").ready(function() {
    $("#buscare").click(function() {
        comprobar();
    });
    $("#buscarl").click(function() {
        $("#answer").removeClass("effectAct");
        $("#answer").addClass("effectDes");
        $("#answer").css("height", "0px")
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
    $("#buscare").attr("disabled", "disabled");
    console.log("ddd");
    $.ajax({
        url: '/Actividas/SvlEmpleado',
        type: 'POST',
        timeout: 20000,
        dataType: 'text',
        data: {
            variable: $("#search").val(),
            buscarCon: $("#buscarCon").val(),
            tipo: "buscar"
        }
    }).done(function(responseText) {
        console.log("lala");
        var j = JSON.parse(responseText);
        console.log(j);
        translate(j);
        $("#buscare").removeAttr("disabled");
    }).fail(function() {
        $("#mensaje").text("Error al comunicarse con el servidor");
        $("#buscare").removeAttr("disabled");
        cl.hide();
    });
}

function translate(j) {
    console.log("lala");
    $("#mensaje").text("");
    var nd = 0;
    var o = 0;
    nd = j.buscar.length;
    var tabla = document.getElementById('resanswer'), tr, td;
    tabla.innerHTML = "";

    tr = tabla.insertRow(tabla.rows.length);
    td = tr.insertCell(tr.cells.length);
    td.innerHTML = "<p class='titletable'>Nombre</p>";
    td = tr.insertCell(tr.cells.length);
    td.innerHTML = "<p class='titletable'>Apellido</p>";
    td = tr.insertCell(tr.cells.length);
    td.innerHTML = "<p class='titletable'>Cédula</p>";
    td = tr.insertCell(tr.cells.length);
    td.innerHTML = "<p class='titletable'>Cargo</p>";

    for (o = 0; o < nd; o++) {

        var td1 = j.buscar[o].nombre;
        var td2 = j.buscar[o].apellido;
        var td3 = j.buscar[o].cedula;
        var td4 = j.buscar[o].cargo;

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
    $("#mensaje").text("Se han encontrado " + o + " empleados");
    $("#answer").addClass("effectAct");
    $("#answer").css("height", "330px");
    cli(j);
    cl.hide();
}
function cli(j) {
    $("#resanswer tr:not(:first-of-type)").click(function() {
        $("#resanswer tr:not(:first-of-type)").removeClass("seleccionado");
        $(this).addClass("seleccionado");
    });

    $("#modificarl").click(function() {
        $("#mensajea").text("");
        var s = $(".seleccionado").children().eq(2).text();
        var n = 0, r = 0;
        for (n = 0; n < j.buscar.length; n++) {

            if (j.buscar[n].cedula == s) {
                r = n;

            }
        }
        $("#nombrem").val(j.buscar[r].nombre);
        $("#codigom").val(j.buscar[r].codigo);
        $("#apellidom").val(j.buscar[r].apellido);
        $("#cedulam").val(j.buscar[r].cedula);
        $("#cargom").val(j.buscar[r].cargo);
        $("#IdUsuario").val(j.buscar[r].IdUsuario);
        //try{
        console.log(j.buscar[r].direccionimg);
        if (j.buscar[r].direccionimg == undefined) {
            $("#imagen").attr("direccion", "");
        } else {
            $("#imagen").attr("direccion", j.buscar[r].direccionimg);
        }

        //}catch(e){}
    });
    $("#eliminarl").click(function() {
        if (l == 1) {
            var m = confirm("¿Seguro que desea eliminar este empleado?");
            if (m) {
                var s = $(".seleccionado").children().eq(2).text();
                console.log(s);
                var n = 0;
                var cedulae, codigoe, nombree, apellidoe, cargoe, idusuarioe;
                for (n = 0; n < j.buscar.length; n++) {

                    if (j.buscar[n].cedula == s) {
                        cedulae = j.buscar[n].cedula;
                        nombree = j.buscar[n].nombre;
                        apellidoe = j.buscar[n].apellido;
                        codigoe = j.buscar[n].codigo;
                        cargoe = j.buscar[n].cargo;
                        idusuarioe = j.buscar[n].IdUsuario;
                    }
                }
                $.ajax({
                    url: '/Actividas/SvlEmpleado', //indica la direccion a la cual se va a enviar la informacion
                    type: 'POST', //establece el metodo de envio
                    timeout: 20000, //establece el tiempo maximo de espera en milisegundos
                    dataType: 'text', //establece el tipo de datos
                    data: {//datos que se envian al servidor
                        cedula: (cedulae),
                        nombre: (nombree),
                        apellido: (apellidoe),
                        codigo: (codigoe),
                        cargo: (cargoe),
                        IdUsuario: (idusuarioe),
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
}