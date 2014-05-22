$("document").ready(function(){
    
    });

function rectangle(){
    var c=document.getElementById ('prel');
    var r= c.getContext ('2d');
    r.fillStyle='lightgray';
    r.fillRect(0,0,600,600);
    var a=$("#ancho").val();
    var b=$("#largo").val();
    a=a*10;
    b=b*4;
    var r= c.getContext ('2d');
    var img = new Image();
    img.src = 'images/bolsa.png';
    img.onload = function(){
        r.drawImage(img, 30, 10,a,b);
    /*var r= c.getContext ('2d');
    r.fillStyle='#fff';
    r.fillRect(50,35,a,b);*/
}

function limpiarT(){
    
  $("#largo").val("");
  $("#ancho").val("");  
}

function limpiarT2(){
  var c=document.getElementById ('prel');
  var r= c.getContext ('2d');
  r.fillStyle='lightgray';
   r.fillRect(0,0,600,600);
}

function rectangle2(){
    var c=document.getElementById ('prel2');
    var r= c.getContext ('2d');
    r.fillStyle='lightgray';
    r.fillRect(0,0,600,600);
    var a=$("#anchon").val();
    var b=$("#largon").val();
    a=a*10;
    b=b*4;
    var r= c.getContext ('2d');
    var img = new Image();
    img.src = 'images/bolsa.png';
    img.onload = function(){
        r.drawImage(img, 30, 10,a,b);
    };
    /*var r= c.getContext ('2d');
    r.fillStyle='#fff';
    r.fillRect(50,35,a,b);*/
}

function fLateral(elem){
    if(elem.checked) {
        $("#fLn").removeClass("hidden");
        $("#fLn").addClass("form");
    }else{
        $("#fLn").removeClass("form");
        $("#fLn").addClass("hidden");
    }
}

function fFondo(elem){
    if(elem.checked) {
        $("#fFn").removeClass("hidden");
        $("#fFn").addClass("form");
    }else{
        $("#fFn").removeClass("form");
        $("#fFn").addClass("hidden");
    }
}

function extrusionCh(elem){
    if(elem.checked) {
        document.getElementById('cantidad').disabled = false;
        document.getElementById('cantidadU').disabled = false;
        document.getElementById('medida').disabled = false;
        document.getElementById('largon').disabled = false;
        document.getElementById('anchon').disabled = false;
        document.getElementById('calibren').disabled = false;
        document.getElementById('materialn').disabled = false;
        document.getElementById('colorn').disabled = false;
    } else {
        document.getElementById('cantidad').disabled = true;
        document.getElementById('cantidadU').disabled = true;
        document.getElementById('medida').disabled = true;
        document.getElementById('largon').disabled = true;
        document.getElementById('anchon').disabled = true;
        document.getElementById('calibren').disabled = true;
        document.getElementById('materialn').disabled = true;
        document.getElementById('colorn').disabled = true;
    }
}

function impresionCh(elem){
    if(elem.checked) {
        rectangle2();
        document.getElementById('logoUo').disabled = false;
    }else{
        limpiarT2();
        document.getElementById('logoUo').disabled = true;
    }
}

function selladoCh(elem){
    if(elem.checked) {
        document.getElementById('medidan2').disabled = false;
        document.getElementById('largon2').disabled = false;
        document.getElementById('anchon2').disabled = false;
        document.getElementById('fLa').disabled = false;
        document.getElementById('fFo').disabled = false;
        document.getElementById('fLn').disabled = false;
        document.getElementById('fFn').disabled = false;

    } else {
        document.getElementById('medidan2').disabled = true;
        document.getElementById('largon2').disabled = true;
        document.getElementById('anchon2').disabled = true;
        document.getElementById('fLa').disabled = true;
        document.getElementById('fFo').disabled = true;
        document.getElementById('fLn').disabled = true;
        document.getElementById('fFn').disabled = true;

    }
}


function enviar(){
    $("#crearPed").attr("disabled", "disabled");
    $("#mensaje2").text(""); //deja en blanco el contenido que haya en el elemento con id "mensaje"
    $.ajax({
        url: '/Actividas/SvlPedido', //indica la direccion a la cual se va a enviar la informacion
        type
        : 'POST', //establece el metodo de envio
        timeout: 12000, //establece el tiempo maximo de espera en milisegundos
        dataType: 'text', //establece el tipo de datos
        data
        : { //datos que se envian al servidor
            cantidad: $("#cedulaf").val(),
            cantidadU: $("#nombre").val(),
            medida: $("#apellido").val(),
            largo: $("#codigof").val(),
            ancho: $("#direccion").val(),
            calibre: $("#telefono").val(),
            material: $("#email").val(),
            color: $("#ciudad").val(),
            fuelleL:$("#ciudad").val(),
            fuelleF:$("#ciudad").val(),
            tipo
            : "crear"

        }
    }).done(function(responseText){//cuando el servidor entrega una respuesta se ejecuta esta funcion anonima
        var j=JSON.parse(responseText); //convierte la respuesta en un JSON
        traducir(j); //envia el JSON a "traducir" para su posterior analisis
        $("#crearPed").removeAttr("disabled");
    }).fail(function() { //si el tiempo de espera se acaba ejecuta esta funcion anonima
        $("#mensaje2").text("Error al comunicarse con el servidor");
        $("#crearPed").removeAttr("disabled");
    });
}
}