$("document").ready(function(){
    $("#crearCliente").submit(function(){
        comprobar();
        return false;
    });
});

function comprobar(){
    var cedula;
    var ciudad;
    var direccion;
    var email;
    var nombre;
    var apellido;
    var telefono;
    var codigo;
    var conCodigo;
    var cont = true;
    cedula = $("#cedulaf").val();
    direccion = $("#direccion").val();
    email = $("#email").val();
    nombre = $("#nombre").val();
    apellido = $("#apellido").val();
    telefono = $("#telefono").val();
    codigo = $("#codigof").val();
    conCodigo = $("#conCodigo").val();
    ciudad = $("#ciudad").val();

    $("#cedulaf").removeAttr("class");
    $("#direccion").removeAttr("class");
    $("#email").removeAttr("class");
    $("#nombre").removeAttr("class");
    $("#apellido").removeAttr("class");
    $("#telefono").removeAttr("class");
    $("#codigof").removeAttr("class");
    $("#conCodigo").removeAttr("class");
    $("#ciudad").removeAttr("class");
    $("#mensaje").text("");

    var regex = /^\d+$/;
    console.log("lala");
    if(regex.test(cedula) && cedula.length>=6 && cedula.length<=13){
        $("#cedulaf").addClass("exito");
        $("#cedulaf").parent().parent().next().text("");
    }else{
        $("#cedulaf").addClass("error");
        $("#cedulaf").parent().parent().next().text("Cédula no válida");
        cont = false;
    }

    if(direccion == "" || direccion == null){
        $("#direccion").addClass("error");
        $("#direccion").parent().parent().next().text("Campo vacio");
        cont = false;
    }else{
        $("#direccion").parent().parent().next().text("");
        $("#direccion").addClass("exito");
    }

    if(email == "" || email == null){
        $("#email").addClass("error");
        $("#email").parent().parent().next().text("Campo vacio");
        cont = false;
    }else{
        $("#email").parent().parent().next().text("");
        $("#email").addClass("exito");
    }

    if(nombre == "" || nombre == null){
        $("#nombre").addClass("error");
        $("#nombre").parent().parent().next().text("Campo vacio");
        cont = false;
    }else{
        $("#nombre").parent().parent().next().text("");
        $("#nombre").addClass("exito");
    }

    if(apellido == "" || apellido == null){
        $("#apellido").addClass("error");
        $("#apellido").parent().parent().next().text("Campo vacio");
        cont = false;
    }else{
        $("#apellido").parent().parent().next().text("");
        $("#apellido").addClass("exito");
    }

    if(ciudad == "" || ciudad == null){
        $("#ciudad").addClass("error");
        $("#ciudad").parent().parent().next().text("Campo vacio");
        cont = false;
    }else{
        $("#ciudad").parent().parent().next().text("");
        $("#ciudad").addClass("exito");
    }

    if(regex.test(telefono)){
        $("#telefono").addClass("exito");
        $("#telefono").parent().parent().next().text("");
    }else{
        $("#telefono").addClass("error");
        $("#telefono").parent().parent().next().text("Sólo numeros");
        cont = false;
    }

    if(codigo.length <= 3){
        $("#codigof").addClass("error");
        $("#codigof").parent().parent().next().text("El código debe ser más de 3 caracteres");
        cont = false;
    }else{
        if(codigo == conCodigo){
            $("#codigof").addClass("exito");
            $("#conCodigo").addClass("exito");
            $("#codigof").parent().parent().next().text("");
        }else{
            $("#codigof").addClass("error");
            $("#conCodigo").addClass("error");
            $("#codigof").parent().parent().next().text("Los códigos no coinciden");
            cont = false;
        }
    }

    if(cont){
        enviar();
    }
}

function enviar(){
    $("#register").attr("disabled", "disabled");
    $("#mensaje").text(""); //deja en blanco el contenido que haya en el elemento con id "mensaje"
    $.ajax({
        url: '/Actividas/SvlCliente', //indica la direccion a la cual se va a enviar la informacion
        type
        : 'POST', //establece el metodo de envio
        timeout: 12000, //establece el tiempo maximo de espera en milisegundos
        dataType: 'text', //establece el tipo de datos
        data
        : { //datos que se envian al servidor
            cedula: $("#cedulaf").val(),
            nombre: $("#nombre").val(),
            apellido: $("#apellido").val(),
            codigo: $("#codigof").val(),
            direccion: $("#direccion").val(),
            telefono: $("#telefono").val(),
            email: $("#email").val(),
            ciudad: $("#ciudad").val(),
            tipo
            : "crear"

        }
    }).done(function(responseText){//cuando el servidor entrega una respuesta se ejecuta esta funcion anonima
        var j=JSON.parse(responseText); //convierte la respuesta en un JSON
        traducir(j); //envia el JSON a "traducir" para su posterior analisis
        $("#register").removeAttr("disabled");
    }).fail(function() { //si el tiempo de espera se acaba ejecuta esta funcion anonima
        $("#mensaje").text("Error al comunicarse con el servidor");
        $("#register").removeAttr("disabled");
    });
}

function traducir(j){
    var s = j.mensaje;

    if(s == ""){
        $("#mensaje").text("El cliente ha sido creado exitosamente");

        $("#cedulaf").val("");
        $("#direccion").val("");
        $("#email").val("");
        $("#nombre").val("");
        $("#apellido").val("");
        $("#telefono").val("");
        $("#codigof").val("");
        $("#conCodigo").val("");
        $("#ciudad").val("");
    }
    if(s == "1"){
        $("#mensaje").text("El cliente ya existe");
        $("#cedulaf").addClass("error");
    }
    if(s == "2"){
        $("#mensaje").text("Error al conectarse a la base de datos");
    }
}
  function justNumbers(e)
            {
                var keynum = window.event ? window.event.keyCode : e.which;
                if ((keynum == 8) || (keynum == 46))
                    return true;

                return /\d/.test(String.fromCharCode(keynum));
            }