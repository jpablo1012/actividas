$("document").ready(function() {
    $("#crearEmpleado").submit(function() {
        comprobar();
        return false;
    });
});

function comprobar() {
    var cargo;
    var cedula;
    var nombre;
    var apellido;
    var codigo;
    var conCodigo;
    var cont = true;
    cargo = $("#Cargo").val();
    cedula = $("#Cedula").val();
    nombre = $("#Nombre").val();
    apellido = $("#Apellido").val();
    codigo = $("#Codigo").val();
    conCodigo = $("#ConCodigo").val();

    $("#Cargo").removeAttr("class");
    $("#Cedula").removeAttr("class");
    $("#Nombre").removeAttr("class");
    $("#Apellido").removeAttr("class");
    $("#Codigo").removeAttr("class");
    $("#ConCodigo").removeAttr("class");
    $("#Mensaje").text("");

    var regex = /^\d+$/;
    console.log("lala");
    if (regex.test(cedula) && cedula.length >= 6 && cedula.length <= 13) {
        $("#Cedula").addClass("exito");
        $("#Cedula").parent().parent().next().children(0).text("");
    } else {
        $("#Cedula").addClass("error");
        $("#Cedula").parent().parent().next().children(0).text("Cédula no válida");
        cont = false;
    }

    if (cargo == "Seleccionar") {
        $("#Cargo").addClass("error");
        $("#Cargo").parent().parent().next().children(0).text("Seleccione cargo");
        cont = false;
    } else {
        $("#Cargo").parent().parent().next().children(0).text("");
        $("#Cargo").addClass("exito");
    }

    if (nombre == "" || nombre == null) {
        $("#Nombre").addClass("error");
        $("#Nombre").parent().parent().next().children(0).text("Campo vacio");
        cont = false;
    } else {
        $("#Nombre").parent().parent().next().children(0).text("");
        $("#Nombre").addClass("exito");
    }

    if (apellido == "" || apellido == null) {
        $("#Apellido").addClass("error");
        $("#Apellido").parent().parent().next().children(0).text("Campo vacio");
        cont = false;
    } else {
        $("#Apellido").parent().parent().next().children(0).text("");
        $("#Apellido").addClass("exito");
    }

    if (codigo.length <= 3) {
        $("#Codigo").addClass("error");
        $("#Codigo").parent().parent().next().children(0).text("El código debe ser más de 3 caracteres");
        cont = false;
    } else {
        if (codigo == conCodigo) {
            $("#Codigo").addClass("exito");
            $("#ConCodigo").addClass("exito");
            $("#Codigo").parent().parent().next().children(0).text("");
        } else {
            $("#Codigo").addClass("error");
            $("#ConCodigo").addClass("error");
            $("#Codigo").parent().parent().next().children(0).text("Los códigos no coinciden");
            cont = false;
        }
    }

    if (cont) {
        enviar();
    }
}

function enviar() {
    $("#registerEmp").attr("disabled", "disabled");
    $("#Mensaje").text("");
    $.ajax({
        url: '/Actividas/SvlEmpleado',
        type
                : 'POST',
        timeout: 12000,
        dataType: 'text',
        data
                : {
                    nombre: $("#Nombre").val(),
                    apellido: $("#Apellido").val(),
                    cedula: $("#Cedula").val(),
                    cargo: $("#Cargo").val(),
                    codigo: $("#Codigo").val(),
                    tipo
                            : "crear"
                }
    }).done(function(responseText) {
        console.log(responseText);
        var j = JSON.parse(responseText);
        traducir(j);
        $("#registerEmp").removeAttr("disabled");
    }).fail(function() {
        $("#Mensaje").text("Error al comunicarse con el servidor");
        $("#registerEmp").removeAttr("disabled");
    });
}

function traducir(j) {
    var s = j.mensaje;

    if (s == "") {
        $("#Mensaje").text("El empleado ha sido creado exitosamente");

        $("#Cargo").val("");
        $("#Cedula").val("");
        $("#Nombre").val("");
        $("#Apellido").val("");
        $("#Codigo").val("");
        $("#ConCodigo").val("");
        $("#registerEmp").removeAttr("disabled");
    }
    if (s == "1") {
        $("#Mensaje").text("El empleado ya existe");
        $("#Cedula").addClass("error");
        $("#registerEmp").removeAttr("disabled");
    }
    if (s == "2") {
        $("#Mensaje").text("Error al conectarse a la base de datos");
        $("#registerEmp").removeAttr("disabled");
    }
}
function justNumbers(e)
{
    var keynum = window.event ? window.event.keyCode : e.which;
    if ((keynum == 8) || (keynum == 46))
        return true;

    return /\d/.test(String.fromCharCode(keynum));
}