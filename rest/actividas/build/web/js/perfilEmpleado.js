//var direccionImagen;
$("document").ready(function(){
    perfil();
    $("#editar").click(function(){
        activar();
        $("#editar").css({
            visibility: 'hidden'
        });
    });
    $("#perfilEmpleado").submit(function(){
        actualizar();
        return false;
    });
    $("#cancelarm").click(function(){
        location.reload(true);
    });
});

function perfil(){
    var cedula = $.cookie('cedula');
    $.ajax({
        url: '/Actividas/SvlEmpleado',
        type: 'POST',
        dataType: 'text',
        data: {
            cedu: cedula,
            tipo: "perfil"
        }
    }).done(function(responseText){
        var j=JSON.parse(responseText);
        console.log(j);
        translate(j);
    }).fail(function() {
        
        });
}

function translate(j){
    $("#IdUsuario").val(j.buscar[0].IdUsuario);
    $("#nombree").html(j.buscar[0].nombre);
    $("#apellidoe").html(j.buscar[0].apellido);
    $("#txtnombre").val(j.buscar[0].nombre);
    $("#txtapellido").val(j.buscar[0].apellido);
    $("#cedulae").html(j.buscar[0].cedula);
    $("#txtcedula").val(j.buscar[0].cedula);
    $("#cargoe").html(j.buscar[0].cargo);
    $("#clave").val(j.buscar[0].codigo);
//    $("#lala").css("background-image", "url('/Actividas/usuarios/" + j.buscar[0].imagen + "')");
  //  direccionImagen = j.buscar[0].direccionimg;
  //  console.log(direccionImagen);
}

function activar(){
    document.getElementById("conactual").style.visibility = "visible";
    document.getElementById("ncontraseña").style.visibility = "visible";
    document.getElementById("nombree").style.display = "none";
    document.getElementById("apellidoe").style.display = "none";
    document.getElementById("txtnombre").style.display = "initial";
    document.getElementById("txtapellido").style.display = "initial";
    document.getElementById("camcont").style.display = "initial";
    document.getElementById("nota").style.display = "initial";
    document.getElementById("cancelarm").style.display = "initial";
    document.getElementById("modificarp").style.display = "initial";
}

function actualizar(){
    $("#mensaje").text("");
    var password, c1, c2;
    var m, cont=false;
    c1 = $("#clave").val();
    c2 = $("#contraseña").val();
    console.log($("#contraseña").val());
    if($("#contraseña").val() == ""){
        password = c1;
        cont = true;
    }else{
        if($("#contactual").val() == c1){
            $("#contactual").addClass("exito");
            $("#mensaje").text("");
            if(c2.length <= 3){
                $("#contraseña").addClass("error");
                $("#concontraseña").addClass("error");
                $("#mensaje").text("La contraseña debe ser mas de 3 caracteres");
                cont=false;
            }else{
                if(c2 == $("#concontraseña").val()){
                    $("#contraseña").addClass("exito");
                    $("#concontraseña").addClass("exito");
                    $("#mensaje").text("");
                    password = c2;
                    cont = true;
                }else{
                    $("#contraseña").addClass("error");
                    $("#concontraseña").addClass("error");
                    $("#mensaje").text("Las contraseñas no coinciden");
                    cont = false;
                }
            }
        }else{
            $("#contraseña").removeClass("error");
            $("#concontraseña").removeClass("error");
            $("#contactual").removeClass("exito");
            $("#contactual").addClass("error");
            $("#mensaje").text("Contraseña  actual incorrecta");
        }
    }
    if(cont){
        m = confirm("¿Seguro que deseas modificarte?");
        if(m){
            $.ajax({
                url: '/Actividas/SvlEmleado', //indica la direccion a la cual se va a enviar la informacion
                type: 'POST', //establece el metodo de envio
                timeout: 20000, //establece el tiempo maximo de espera en milisegundos
                dataType: 'text', //establece el tipo de datos
                data: { //datos que se envian al servidor
                    cedula: $("#cedulae").html(),
                    nombre: $("#txtnombre").val(),
                    apellido: $("#txtapellido").val(),
                    codigo: password,
                    ciudad: $("#cargoe").html(),
                    IdUsuario: $("#IdUsuario").val(),
                    tipo: "actualizar"

                }
            }).done(function(responseText){//cuando el servidor entrega una respuesta se ejecuta esta funcion anonima
                var k=JSON.parse(responseText); //convierte la respuesta en un JSON
                console.log("El empleado ha sido modificado exitosamente");
                console.log(k);
                location.reload(true);
            }).fail(function() { //si el tiempo de espera se acaba ejecuta esta funcion anonima
                console.log("Error al comunicarse con el servidor");
            });
        }else{
        }
    }
}