var busx = 0;
var c = false, ma = false, b = false;
//var cl;
//cl = new CanvasLoader('canvasloader-container');
//cl.setColor('#adadad'); // default is '#000000'
//cl.setDiameter(55); // default is 40
//cl.setDensity(55); // default is 40
//cl.setRange(1); // default is 1.3
//cl.setFPS(20); // default is 24
$("document").ready(function() {
    $("#opciones").click(function() {
        if ($("input[name='buscar']:checked").val() == "color") {
            $('#buscarcon')[0].options.length = 1;
            document.getElementById("opciones").style.border = "";
            $("<option value='codigo'>Código</option>").appendTo("#buscarcon");
            $("<option value='nombre'>Nombre</option>").appendTo("#buscarcon");
            busx = 1;
        }
        if ($("input[name='buscar']:checked").val() == "material") {
            $('#buscarcon')[0].options.length = 1;
            document.getElementById("opciones").style.border = "";
            $("<option value='referencia'>Referencia</option>").appendTo("#buscarcon");
            $("<option value='nombre'>Nombre</option>").appendTo("#buscarcon");
            busx = 2;
        }
        if ($("input[name='buscar']:checked").val() == "bolsa") {
            $('#buscarcon')[0].options.length = 1;
            document.getElementById("opciones").style.border = "";
            $("<option value='referencia'>Referencia</option>").appendTo("#buscarcon");
            $("<option value='cliente_cedula'>Cédula Cliente</option>").appendTo("#buscarcon");
            $("<option value='color_codigo'>Referencia color</option>").appendTo("#buscarcon");
            $("<option value='material_referencia'>Referencia material</option>").appendTo("#buscarcon");
            $("<option value='medida'>Medida</option>").appendTo("#buscarcon");
            $("<option value='ancho'>Ancho</option>").appendTo("#buscarcon");
            $("<option value='largo'>Largo</option>").appendTo("#buscarcon");
            $("<option value='calibre'>Calibre</option>").appendTo("#buscarcon");
            $("<option value='tratado'>Tratado</option>").appendTo("#buscarcon");
            $("<option value='transparenciaArriba'>Transparencia superior</option>").appendTo("#buscarcon");
            $("<option value='transparenciaAbajo'>Transparencia inferior</option>").appendTo("#buscarcon");
            $("<option value='fuelle_fondo'>Fuelle fondo</option>").appendTo("#buscarcon");
            $("<option value='fuelle_superior'>Fuelle superior</option>").appendTo("#buscarcon");
            $("<option value='fuelle_lateral'>Fuelle lateral</option>").appendTo("#buscarcon");
            $("<option value='solapa'>Solapa</option>").appendTo("#buscarcon");
            $("<option value='troquel'>Troquel</option>").appendTo("#buscarcon");
            busx = 3;
        }
    });
    if (busx === 0) {
        $('#buscarcon')[0].options.length = 1;
        busx = 0;
    }
    $("#buscari").click(function() {
        verificar(busx);
    });
    $("#limp").click(function() {
        $("#answer").removeClass("effectAct");
        $("#answer").addClass("effectDes");
        $("#answer").css("height", "0px")
    });
});

function verificar(busx) {
    var variable = $("#search").val();
    var buscar = $("#buscarcon").val();
    var cont = true;

    if (busx === 0) {
        document.getElementById("opciones").style.border = "1px solid red";
    }
    if (buscar != "ninguno") {
        $("#buscarCon").addClass("exito");
    } else {
        $("#buscarCon").addClass("error");
        cont = false;
    }
    if (cont) {
        if (busx === 1) {
            buscarc();
        }
        if (busx === 2) {
            buscarm();
        }
        if (busx === 3) {
            buscarb();
        }
    }
}

function buscarc() {
    c = true;
    ma = false;
    b = false;
    $("#mensaje").text("");
    $("#buscare").attr("disabled", "disabled");
    $.ajax({
        url: '/Actividas/SvlInsumos',
        type: 'POST',
        timeout: 20000,
        dataType: 'text',
        data: {
            variable: $("#search").val(),
            buscarCon: $("#buscarcon").val(),
            tipo: "buscarc"
        }
    }).done(function(responseText) {
        var j = JSON.parse(responseText);
        console.log(j);
        traducir(j);
        $("#buscare").removeAttr("disabled");
    }).fail(function() {
        $("#mensaje").text("Error al comunicarse con el servidor");
        $("#buscare").removeAttr("disabled");
    });
}
function buscarm() {
    ma = true
    c = false;
    b = false;
    $("#mensaje").text("");
    $("#buscare").attr("disabled", "disabled");
    $.ajax({
        url: '/Actividas/SvlInsumos',
        type: 'POST',
        timeout: 20000,
        dataType: 'text',
        data: {
            variable: $("#search").val(),
            buscarCon: $("#buscarcon").val(),
            tipo: "buscarm"
        }
    }).done(function(responseText) {
        var j = JSON.parse(responseText);
        console.log(j);
        traducir(j);
        $("#buscare").removeAttr("disabled");
    }).fail(function() {
        $("#mensaje").text("Error al comunicarse con el servidor");
        $("#buscare").removeAttr("disabled");
    });
}
function buscarb() {
    b = true;
    c = false;
    ma = false;
    $("#mensaje").text("");
    $("#buscare").attr("disabled", "disabled");
    $.ajax({
        url: '/Actividas/SvlInsumos',
        type: 'POST',
        timeout: 20000,
        dataType: 'text',
        data: {
            variable: $("#search").val(),
            buscarCon: $("#buscarcon").val(),
            tipo: "buscarb"
        }
    }).done(function(responseText) {
        var j = JSON.parse(responseText);
        console.log(j);
        traducir(j);
        $("#buscare").removeAttr("disabled");
    }).fail(function() {
        $("#mensaje").text("Error al comunicarse con el servidor");
        $("#buscare").removeAttr("disabled");
    });
}

function traducir(j) {
    if (c) {
        document.getElementById("modificarl").style.display = "initial";
        document.getElementById("masInfoP").style.display = "none";
        document.getElementById("eliminarin").style.display = "initial";
        $("#mensaje").text("");
        var nd = 0;
        var o = 0;
        nd = j.buscar.length;
        var tabla = document.getElementById('resansweri'), tr, td;
        tabla.innerHTML = "";

        tr = tabla.insertRow(tabla.rows.length);
        td = tr.insertCell(tr.cells.length);
        td.innerHTML = "<p class='titletable'>Código</p>";
        td = tr.insertCell(tr.cells.length);
        td.innerHTML = "<p class='titletable'>Nombre</p>";
        td = tr.insertCell(tr.cells.length);
        td.innerHTML = "<p class='titletable'>Cantidad (Lt.)</p>";

        for (o = 0; o < nd; o++) {

            var td1 = j.buscar[o].id;
            var td2 = j.buscar[o].nombre;
            var td3 = j.buscar[o].cantidad;

            tr = tabla.insertRow(tabla.rows.length);
            td = tr.insertCell(tr.cells.length);
            td.innerHTML = td1;
            td = tr.insertCell(tr.cells.length);
            td.innerHTML = td2;
            td = tr.insertCell(tr.cells.length);
            td.innerHTML = td3;
        }
        $("#mensaje").text("Se han encontrado " + o + " color");
        $("#answer").addClass("effectAct");
        $("#answer").css("height", "330px");
        cli(j);
    }
    if (ma) {
        document.getElementById("modificarl").style.display = "initial";
        document.getElementById("masInfoP").style.display = "none";
        document.getElementById("eliminarin").style.display = "initial";
        $("#mensaje").text("");
        var nd = 0;
        var o = 0;
        nd = j.buscar.length;
        var tabla = document.getElementById('resansweri'), tr, td;
        tabla.innerHTML = "";

        tr = tabla.insertRow(tabla.rows.length);
        td = tr.insertCell(tr.cells.length);
        td.innerHTML = "<p class='titletable'>Referencia</p>";
        td = tr.insertCell(tr.cells.length);
        td.innerHTML = "<p class='titletable'>Nombre</p>";
        td = tr.insertCell(tr.cells.length);
        td.innerHTML = "<p class='titletable'>Cantidad (Kg)</p>";

        for (o = 0; o < nd; o++) {

            var td1 = j.buscar[o].id;
            var td2 = j.buscar[o].nombre;
            var td3 = j.buscar[o].cantidad;

            tr = tabla.insertRow(tabla.rows.length);
            td = tr.insertCell(tr.cells.length);
            td.innerHTML = td1;
            td = tr.insertCell(tr.cells.length);
            td.innerHTML = td2;
            td = tr.insertCell(tr.cells.length);
            td.innerHTML = td3;
        }
        $("#mensaje").text("Se han encontrado " + o + " materiales");
        $("#answer").addClass("effectAct");
        $("#answer").css("height", "330px");
        cli(j);
    }
    if (b) {
        document.getElementById("modificarl").style.display = "none";
        document.getElementById("masInfoP").style.display = "initial";
        $("#mensaje").text("");
        if ($.cookie('tipo') == "administrador") {
            document.getElementById("eliminarin").style.display = "none";
        }
        var nd = 0;
        var o = 0;
        nd = j.buscar.length;
        var tabla = document.getElementById('resansweri'), tr, td;
        tabla.innerHTML = "";

        tr = tabla.insertRow(tabla.rows.length);
        td = tr.insertCell(tr.cells.length);
        td.innerHTML = "<p class='titletable'>Referencia</p>";
        td = tr.insertCell(tr.cells.length);
        td.innerHTML = "<p class='titletable'>Cédula Cliente</p>";
        td = tr.insertCell(tr.cells.length);
        td.innerHTML = "<p class='titletable'>Largo</p>";
        td = tr.insertCell(tr.cells.length);
        td.innerHTML = "<p class='titletable'>Ancho</p>";
        td = tr.insertCell(tr.cells.length);
        td.innerHTML = "<p class='titletable'>Medida</p>";

        for (o = 0; o < nd; o++) {

            var td1 = j.buscar[o].id;
            var td2 = j.buscar[o].cedula;
            var td3 = j.buscar[o].largo;
            var td4 = j.buscar[o].ancho;
            var td5 = j.buscar[o].medida;

            tr = tabla.insertRow(tabla.rows.length);
            td = tr.insertCell(tr.cells.length);
            td.innerHTML = td1;
            td = tr.insertCell(tr.cells.length);
            td.innerHTML = td2;
            td = tr.insertCell(tr.cells.length);
            td.innerHTML = td3;
            td = tr.insertCell(tr.cells.length);
            td.innerHTML = td4;
            td = tr.insertCell(tr.cells.length);
            td.innerHTML = td5;
        }
        $("#mensaje").text("Se han encontrado " + o + " bolsas");
        $("#answer").addClass("effectAct");
        $("#answer").css("height", "330px");
        cli(j);
    }
}
function cli(j) {
    $("#resansweri tr:not(:first-of-type)").click(function() {
        $("#resansweri tr:not(:first-of-type)").removeClass("seleccionado");
        $(this).addClass("seleccionado");
    });
    if (c || ma) {
        document.getElementById("modificatei").style.display = "initial";
        document.getElementById("modin").style.display = "initial";
        document.getElementById("verbol").style.display = "none";
        $("#lal").css("text-align", "center");
        $("#insbo").html("Modificar Insumo");
        $("#insbo").css("padding", "0px");
        $("#modificarl").click(function() {
            $("#mensajea").text("");
            var s = $(".seleccionado").children().eq(0).text();
            var n = 0, r = 0;
            for (n = 0; n < j.buscar.length; n++) {

                if (j.buscar[n].id == s) {

                    console.log("lala");
                    r = n;
                }
            }
            $("#idI").val(j.buscar[r].id);
            $("#nombreI").val(j.buscar[r].nombre);
            $("#cantidadI").val(j.buscar[r].cantidad);
        });
    }
    if (b) {
        document.getElementById("verbol").style.display = "initial";
        document.getElementById("modin").style.display = "none";
        $("#lal").css("text-align", "left");
        $("#insbo").html("Bolsa");
        $("#insbo").css("padding", "0px 190px");
        document.getElementById("modificatei").style.display = "none";
        $("#masInfoP").click(function() {
            $("#mensajea").text("");
            var s = $(".seleccionado").children().eq(12).text();
            var n = 0, r = 0;
            for (n = 0; n < j.buscar.length; n++) {

                if (j.buscar[n].id == s) {

                    console.log("lala");
                    r = n;
                }
            }
            $("#idI").val(j.buscar[r].id);
            $("#referenciama").val(j.buscar[r].refmaterial);
            $("#calibre").val(j.buscar[r].calibre);
            $("#medida").val(j.buscar[r].medida);
            $("#largo").val(j.buscar[r].largo);
            $("#ancho").val(j.buscar[r].ancho);
            $("#tratado").val(j.buscar[r].tratado);
            $("#referenciaco").val(j.buscar[r].codcolor);
            $("#tps").val(j.buscar[r].transarriba);
            $("#tpi").val(j.buscar[r].transabajo);
            $("#flli").val(j.buscar[r].fuelle_fondo);
            $("#flls").val(j.buscar[r].fuelle_superior);
            $("#flll").val(j.buscar[r].fuelle_lateral);
            $("#slp").val(j.buscar[r].solapa);
            $("#troquel").val(j.buscar[r].troquel);
        });
    }
//    $("#eliminarin").click(function(){
//        var m = confirm("¿Seguro que desea eliminar este Insumo?");
//        if(m==true){
//            var s = $(".seleccionado").children().eq(2).text();
//            var n=0;
//            var cedulac, codigoc, nombrec, apellidoc, direccionc, ciudadc, telefonoc, correoc, idusuarioc;
//            for(n=0;n<j.buscar.length;n++){
//
//                if(j.buscar[n].cedula == s){
//                    cedulac = j.buscar[n].cedula;
//                    nombrec = j.buscar[n].nombre;
//                    apellidoc = j.buscar[n].apellido;
//                    codigoc = j.buscar[n].codigo;
//                    ciudadc = j.buscar[n].ciudad;
//                    direccionc = j.buscar[n].direccion;
//                    telefonoc = j.buscar[n].telefono;
//                    correoc = j.buscar[n].correo;
//                    idusuarioc = j.buscar[n].IdUsuario;
//                }
//            }
//            $.ajax({
//                url: '/Actividas/SvlCliente', //indica la direccion a la cual se va a enviar la informacion
//                type: 'POST', //establece el metodo de envio
//                timeout: 20000, //establece el tiempo maximo de espera en milisegundos
//                dataType: 'text', //establece el tipo de datos
//                data: { //datos que se envian al servidor
//                    cedula: (cedulac),
//                    nombre: (nombrec),
//                    apellido: (apellidoc),
//                    codigo: (codigoc),
//                    ciudad: (ciudadc),
//                    direccion: (direccionc),
//                    telefono: (telefonoc),
//                    correo: (correoc),
//                    IdUsuario: (idusuarioc),
//                    tipo: "eliminar"
//
//                }
//            }).done(function(responseText){//cuando el servidor entrega una respuesta se ejecuta esta funcion anonima
//                var k=JSON.parse(responseText); //convierte la respuesta en un JSON
//            }).fail(function() { //si el tiempo de espera se acaba ejecuta esta funcion anonima
//                $("#mensaje").text("Error al comunicarse con el servidor");
//            });
//        }
//        verificar();
//    });
}
$("#modificatei").click(function() {
    if (c) {
        $("#mensajea").text(""); //deja en blanco el contenido que haya en el elemento con id "mensaje"
        $.ajax({
            url: '/Actividas/SvlInsumos', //indica la direccion a la cual se va a enviar la informacion
            type: 'POST', //establece el metodo de envio
            timeout: 20000, //establece el tiempo maximo de espera en milisegundos
            dataType: 'text', //establece el tipo de datos
            data: {//datos que se envian al servidor
                id: $("#idI").val(),
                nombre: $("#nombreI").val(),
                cantidad: $("#cantidadI").val(),
                tipo: "actualizarc"

            }
        }).done(function(responseText) {//cuando el servidor entrega una respuesta se ejecuta esta funcion anonima
            var k = JSON.parse(responseText); //convierte la respuesta en un JSON
            $("#mensajea").text("El color ha sido modificado exitosamente");
            console.log(k);
        }).fail(function() { //si el tiempo de espera se acaba ejecuta esta funcion anonima
            $("#mensajea").text("Error al comunicarse con el servidor");
        });
    }
    if (ma) {
        $("#mensajea").text(""); //deja en blanco el contenido que haya en el elemento con id "mensaje"
        $.ajax({
            url: '/Actividas/SvlInsumos', //indica la direccion a la cual se va a enviar la informacion
            type: 'POST', //establece el metodo de envio
            timeout: 20000, //establece el tiempo maximo de espera en milisegundos
            dataType: 'text', //establece el tipo de datos
            data: {//datos que se envian al servidor
                id: $("#idI").val(),
                nombre: $("#nombreI").val(),
                cantidad: $("#cantidadI").val(),
                tipo: "actualizarma"
            }
        }).done(function(responseText) {//cuando el servidor entrega una respuesta se ejecuta esta funcion anonima
            var k = JSON.parse(responseText); //convierte la respuesta en un JSON
            $("#mensajea").text("El material ha sido modificado exitosamente");
            console.log(k);
        }).fail(function() { //si el tiempo de espera se acaba ejecuta esta funcion anonima
            $("#mensajea").text("Error al comunicarse con el servidor");
        });
    }
});