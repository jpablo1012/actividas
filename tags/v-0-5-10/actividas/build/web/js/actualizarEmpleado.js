$("document").ready(function(){
    $("#mensajea").text("");
    $("#actualizarEmpleado").submit(function(){
        enviar2();
        return false;
    });

});
function enviar2(){
    $("#mensajea").text(""); //deja en blanco el contenido que haya en el elemento con id "mensaje"
    $.ajax({
        url: '/Actividas/SvlEmpleado', //indica la direccion a la cual se va a enviar la informacion
        type: 'POST', //establece el metodo de envio
        timeout: 20000, //establece el tiempo maximo de espera en milisegundos
        dataType: 'text', //establece el tipo de datos
        data: { //datos que se envian al servidor
            cedula: $("#cedulam").val(),
            nombre: $("#nombrem").val(),
            apellido: $("#apellidom").val(),
            codigo: $("#codigom").val(),
            cargo: $("#cargom").val(),
            IdUsuario: $("#IdUsuario").val(),
            direccionImagen: $("#imagen").attr("direccion"),
            tipo: "actualizar"

        }
    }).done(function(responseText){//cuando el servidor entrega una respuesta se ejecuta esta funcion anonima
        var k=JSON.parse(responseText); //convierte la respuesta en un JSON
        console.log(k);
        $("#mensajea").text("El empleado ha sido actualizado exitosamente");
    //    traducir(k);
    }).fail(function() { //si el tiempo de espera se acaba ejecuta esta funcion anonima
        $("#mensajea").text("Error al comunicarse con el servidor");
    });
}   