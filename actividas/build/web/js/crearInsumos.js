$("document").ready(function(){
    $("#crearMaterial").submit(function(){
        comprobarma();
        return false;
    });
});

$("document").ready(function(){
    completarma();
    completarco();
    $("#crearBolsa").submit(function(){
        console.log("here1");
        comprobarbo();
        return false;
    });
});

$("document").ready(function(){
    $("#crearColor").submit(function(){
        comprobarco();
        return false;
    });
});

function comprobarma(){
    var nombre;
    var cantidad;
    var cont = true;
    nombre = $("#nombrem").val();
    cantidad = $("#cantidadm").val();

    $("#nombrem").removeAttr("class");
    $("#cantidadm").removeAttr("class");
    $("#Mensaje").text("");

    var regex = /^\d+$/;
    if(nombre == "" || nombre == null){
        $("#nombrem").addClass("error");
        $("#nombrem").parent().parent().next().children(0).text("Campo vacio");
        cont = false;
    }else{
        $("#nombrem").parent().parent().next().children(0).text("");
        $("#nombrem").addClass("exito");
    }

    if(regex.test(cantidad) || $("#cantidadm").val() == ""){
        $("#cantidadm").addClass("exito");
        $("#cantidadm").parent().parent().next().children(0).text("");
    }else{
        $("#cantidadm").addClass("error");
        $("#cantidadm").parent().parent().next().children(0).text("Sólo números");
        cont = false;
    }

    if(cont){
        enviarma();
    }
}

function enviarma(){
    $("#registerM").attr("disabled","disabled");
    $("#Mensaje").text("");
    $.ajax({
        url: '/Actividas/SvlInsumos',
        type: 'POST',
        timeout: 12000,
        dataType: 'text',
        data: {
            nombre: $("#nombrem").val(),
            cantidad: $("#cantidadm").val(),
            tipo: "crearma"
        }
    }).done(function(responseText){
        var j=JSON.parse(responseText);
        traducir(j);
        $("#registerM").removeAttr("disabled");
    }).fail(function() {
        $("#Mensaje").text("Error al comunicarse con el servidor");
        $("#registerM").removeAttr("disabled");
    });
}

function comprobarco(){
    var nombre;
    var cantidad;
    var cont = true;
    nombre = $("#nombreco").val();
    cantidad = $("#cantidadco").val();

    $("#nombreco").removeAttr("class");
    $("#cantidadco").removeAttr("class");
    $("#Mensaje").text("");

    var regex = /^\d+$/;
    if(nombre == "" || nombre == null){
        $("#nombreco").addClass("error");
        $("#nombreco").parent().parent().next().children(0).text("Campo vacio");
        cont = false;
    }else{
        $("#nombreco").parent().parent().next().children(0).text("");
        $("#nombreco").addClass("exito");
    }

    if(regex.test(cantidad) || $("#cantidadco").val() == ""){
        $("#cantidadco").addClass("exito");
        $("#cantidadco").parent().parent().next().children(0).text("");
    }else{
        $("#cantidadco").addClass("error");
        $("#cantidadco").parent().parent().next().children(0).text("Sólo números");
        cont = false;
    }

    if(cont){
        enviarco();
    }
}

function enviarco(){
    $("#registerCo").attr("disabled","disabled");
    $("#Mensaje").text("");
    $.ajax({
        url: '/Actividas/SvlInsumos',
        type: 'POST',
        timeout: 12000,
        dataType: 'text',
        data: {
            nombre: $("#nombreco").val(),
            cantidad: $("#cantidadco").val(),
            tipo: "crearco"
        }
    }).done(function(responseText){
        var j=JSON.parse(responseText);
        traducir(j);
        $("#registerCo").removeAttr("disabled");
    }).fail(function() {
        $("#Mensaje").text("Error al comunicarse con el servidor");
        $("#registerCo").removeAttr("disabled");
    });
}

function comprobarbo(){
    var calibre;
    var anchoRollo;
    var referenciaMaterial;
    var refereciaColor;
    var tratado;
    var cont = true;
    calibre = $("#calibre").val();
    anchoRollo = $("#anchoro").val();
    referenciaMaterial = $("#referenciama").val();
    refereciaColor = $("#referenciaco").val();

    $("#calibre").removeAttr("class");
    $("#anchoro").removeAttr("class");
    $("#referenciama").removeAttr("class");
    $("#referenciaco").removeAttr("class");
    $("#tratado").removeAttr("class");
    $("#Mensaje").text("");

    var regex = /^\d+$/;
    if(regex.test(calibre) || calibre==""){
        $("#calibre").addClass("error");
        $("#calibre").parent().parent().next().children(0).text("Sólo números");
        cont = false;
    }else{
        $("#calibre").addClass("exito");
        $("#calibre").parent().parent().next().children(0).text("");
    }

    if(regex.test(anchoRollo) || anchoRollo==""){
        $("#anchoro").addClass("error");
        $("#anchoro").parent().parent().next().children(0).text("Sólo números");
        cont = false;
    }else{
        $("#anchoro").addClass("exito");
        $("#anchoro").parent().parent().next().children(0).text("");
    }

    if(referenciaMaterial=="ninguno"){
        $("#referenciama").addClass("error");
        $("#referenciama").parent().parent().next().children(0).text("Seleccione una referencia");
        cont = false;
    }else{
        $("#referenciama").parent().parent().next().children(0).text("");
        $("#referenciama").addClass("exito");
    }

    if(refereciaColor=="ninguno"){
        $("#referenciaco").addClass("error");
        $("#referenciaco").parent().parent().next().children(0).text("Seleccione una referencia");
        cont = false;
    }else{
        $("#referenciaco").parent().parent().next().children(0).text("");
        $("#referenciaco").addClass("exito");
    }
    if(!$("input[name='tratado']:checked").val()){
        $("#tratado").addClass("error");
        $("#tratado").parent().parent().next().children(0).text("Seleccione tratado");
        cont = false;
    }else{
        $("#tratado").parent().parent().next().children(0).text("");
        $("#tratado").addClass("exito");
    }
    if(cont){
        subir();
    }
}

function enviarbo(s){
    var tratadob, ccedula;
    console.log(document.getElementById("calibre").value);
    if($("input[name='tratado']:checked").val() == "true"){
        tratadob=true;
    }
    if($("input[name='tratado']:checked").val() == "false"){
        tratadob=false;
    }
    if($.cookie('tipo') == "cliente"){
        ccedula = $.cookie('cedula');
//    }else{
//        ccedula = "";
    }

    $("#registerBol").attr("disabled","disabled");
    $("#Mensaje").text("");

    $.ajax({
        url: '/Actividas/SvlInsumos',
        type: 'POST',
        timeout: 12000,
        dataType: 'text',
        data: {
            cedula:ccedula,
            transparenciaA:$("#tps").val(),
            transparenciaD:$("#tpi").val(),
            fuelleI:$("#flli").val(),
            fuelleS:$("#flls").val(),
            fuelleL:$("#flll").val(),
            solapa:$("#slp").val(),
            troquel:$("#troquel").val(),
            nombre: s.nombre,
            calibre: document.getElementById("calibre").value,
            medida: $("#medida").val(),
            anchoRollo: $("#ancho").val(),
            largo: $("#largo").val(),
            referenciaMaterial: $("#referenciama").val(),
            referenciaColor: $("#referenciaco").val(),
            tratado: tratadob,
            tipo: "crearbo"
        }
    }).done(function(res){
        $("#Mensaje").text("Bolsa creada Exitosamente");
    }).fail(function() {
        $("#Mensaje").text("Error al subir el archivo -dentro");
    });
}

function traducir(j){
    var s = j.mensaje;

    if(s == ""){
        $("#Mensaje").text("El insumo ha sido creado exitosamente");

        $("#nombrem").val("");
        $("#cantidadm").val("");
        $("#nombreco").val("");
        $("#cantidadco").val("");
        $("#registerM").removeAttr("disabled");
    }
    if(s == "1"){
        
    }
    if(s == "2"){
        $("#Mensaje").text("Error al conectarse a la base de datos");
        $("#registerM").removeAttr("disabled");
    }
}

function subir(){
    var s = {};
    s.nombre = "";
    $("#images").upload("subir.jsp", function(respuesta){
        s = respuesta;
        console.log("hello1");
    });
    enviarbo(s);
}


function llenarm(k){
    var nd=0;
    nd=k.buscar.length;
    $("<option value='ninguno'>-Seleccionar-</option>").appendTo("#referenciama");
    for(var l=0;l<nd;l++){
        var id = k.buscar[l].id;
        var e = " ";
        var nombre = k.buscar[l].nombre;
        $('<option value='+id+'>'+id+e+"-"+e+nombre+'</option>').appendTo("#referenciama");

    }
}
function llenarc(o){
    var nd=0;
    nd=o.buscar.length;
    $("<option value='ninguno'>-Seleccionar-</option>").appendTo("#referenciaco");
    for(var l=0;l<nd;l++){
        var id = o.buscar[l].id;
        var e = " ";
        var nombre = o.buscar[l].nombre;
        $('<option value='+id+'>'+"id:"+id+e+nombre+'</option>').appendTo("#referenciaco");
    }
}

function completarma(){
    $.ajax({
        url: '/Actividas/SvlInsumos',
        type: 'POST',
        timeout: 20000,
        dataType: 'text',
        data: {
            tipo: "llenarma"
        }
    }).done(function(responseText){
        var k=JSON.parse(responseText);
        console.log(k);
        llenarm(k);
    }).fail(function() {
        });
}
function completarco(){
    $.ajax({
        url: '/Actividas/SvlInsumos',
        type: 'POST',
        timeout: 20000,
        dataType: 'text',
        data: {
            tipo: "llenarco"
        }
    }).done(function(responseText){
        var o=JSON.parse(responseText);
        console.log(o);
        llenarc(o);
    }).fail(function() {
        });
}