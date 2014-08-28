$("document").ready(function(){
    $("#formulario").submit(function(){
        enviar();
        return false;
    });
});

function enviar(){
    $("#mensaje").text(""); //deja en blanco el contenido que haya en el elemento con id "mensaje"
    $.ajax({
        url: '/Actividas/SvlAutentificacion', //indica la direccion a la cual se va a enviar la informacion
        type: 'POST', //establece el metodo de envio
        timeout: 12000, //establece el tiempo maximo de espera en milisegundos
        dataType: 'text', //establece el tipo de datos
        data: { //datos que se envian al servidor
            cedula: $("#cedula").val(),
            codigo: $("#codigo").val()
        }
    }).done(function(responseText){//cuando el servidor entrega una respuesta se ejecuta esta funcion anonima
        var j=JSON.parse(responseText); //convierte la respuesta en un JSON
        traducir(j); //envia el JSON a "traducir" para su posterior analisis
    }).fail(function() { //si el tiempo de espera se acaba ejecuta esta funcion anonima
        $("#mensaje").text('Error con el servidor');
    });
}
function traducir(j){
    var continuar = true;

    //si el JSON.mensaje contiene algun texto entonces lo mostrara en el elemento con id "mensaje" ,
    //borrara lo que contenga el campo de codigo y establecera continuar como falso
    if(j.mensaje!=""){
        $("#mensaje").text(j.mensaje);
        $("#mensaje").show();
        $("#codigo").val("");
        $("#cedula").val("");
        continuar = false;
    }


    if(continuar){
        //guardara el tipo, nombre y cedula del usaurio que ha iniciado sesion con 1 dia de expiración
        $.cookie('tipo',j.tipo,{
            expires: 1
        });
        $.cookie('nombre',j.nombre,{
            expires: 1
        });
        $.cookie('cedula',j.cedula,{
            expires: 1
        });
        $.cookie('real',j.real,{
            expires: 1
        });
        //sessionStorage.tipo = j.tipo;
        //sessionStorage.nombre = j.nombre;
        //sessionStorage.cedula = j.cedula;

        window.location = ""; //recarga la pagina
    //sesion();
    }

}