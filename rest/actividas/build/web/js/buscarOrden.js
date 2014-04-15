$("document").ready(function(){

    $("#buscarord").click(function(){
        enviarO();
    });

    $("#limpiarO").click(function(){
        $("#answerO").removeClass("effectAct");
        $("#answerO").addClass("effectDes");
        $("#answerO").css("height","0px")
    });

    $("#masInfoO").click(function(){
        var nump = $(".seleccionado").children().eq(0).text();
        enviar2(nump);
    });

});

function enviarO(){
    $("#mensajeo").text("");
    $("#buscarord").attr("disabled", "disabled");
    $.ajax({
        url: '/Actividas/SvlOrden',
        type: 'POST',
        timeout: 20000,
        dataType: 'text',
        data:{
            pista: $("#searchO").val(),
            valor: $("#buscarconO").val(),
            tipo: "buscar"
        }
    }).done(function(responseText){
        var j=JSON.parse(responseText);
        translate(j);
        $("#buscarord").removeAttr("disabled");
    }).fail(function() {
        $("#mensajeo").text("Error al comunicarse con el servidor");
        $("#buscarord").removeAttr("disabled");
    });
}

function enviar2(nump){

    $.ajax({
        url: '/Actividas/SvlPedido',
        type: 'POST',
        timeout: 20000,
        dataType: 'text',
        data:{
            pista: nump,
            valor: "numeroPedido",
            tipo: "buscar"
        }
    }).done(function(responseText){
        console.log("founded");
        var k=JSON.parse(responseText);
        translate2(k);
    }).fail(function() {
        console.log("end fail");
    });
}

function translate2(k){
 
   $("#cedulacp").val(k.buscar[0].cedula);
   $("#estadoP").val(k.buscar[0].estado);
    $("#refBol").val(k.buscar[0].refereciaB);
    $("#fechaEnt").val(k.buscar[0].Fentrega);
    $("#fechaCre").val(k.buscar[0].Fcreacion);
    $("#cantiP").val(k.buscar[0].cantidad);
    $("#tipoVenta").val(k.buscar[0].Tventa);
    $("#cantiPT").html(k.buscar[0].Tcantidad);
    $("#precioP").val(k.buscar[0].precio);
    
    if((k.buscar[0].extrusion)=="true"){
        $("#extP").attr("checked","checked");
    }
    if((k.buscar[0].impresion)=="true"){
        $("#impP").attr("checked","checked");
    }
    if((k.buscar[0].sellado)=="true"){
        $("#selP").attr("checked","checked");
    }
}

function translate(j){
    $("#mensajeo").text("");
    var nd=0;
    var o=0;
    nd=j.buscar.length;
    var tabla = document.getElementById('resanswero'),trp,tdp;
    tabla.innerHTML = "";

    trp = tabla.insertRow(tabla.rows.length);
    tdp = trp.insertCell(trp.cells.length);
    tdp.innerHTML ="<p class='titletable'>Numero Pedido</p>";
    tdp = trp.insertCell(trp.cells.length);
    tdp.innerHTML ="<p class='titletable'>Numero Orden</p>";
    tdp = trp.insertCell(trp.cells.length);
    tdp.innerHTML = "<p class='titletable'>Ref Bolsa</p>";
    tdp = trp.insertCell(trp.cells.length);
    tdp.innerHTML = "<p class='titletable'>Cantidad</p>";
    tdp = trp.insertCell(trp.cells.length);
    tdp.innerHTML = "<p class='titletable'>Estado</p>";

    for(o=0;o<nd;o++){
        var td0 = j.buscar[o].nPedido;
        var td1 = j.buscar[o].nOrden;
        var td2 = j.buscar[o].bReferencia;
        var td3 = j.buscar[o].cantidad;//+" "+j.buscar[o].Tcantidad;
        var td4 = j.buscar[o].estado;

        trp = tabla.insertRow(tabla.rows.length);
        tdp = trp.insertCell(trp.cells.length);
        tdp.innerHTML =td0;
        tdp = trp.insertCell(trp.cells.length);
        tdp.innerHTML =td1;
        tdp = trp.insertCell(trp.cells.length);
        tdp.innerHTML = td2;
        tdp = trp.insertCell(trp.cells.length);
        tdp.innerHTML = td3;
        tdp = trp.insertCell(trp.cells.length);
        tdp.innerHTML = td4;

    }
    $("#mensajeo").text("Se han encontrado "+o+" ordenes");
    $("#answerO").addClass("effectAct");
    $("#answerO").css("height","330px");
    cli(j);
}

function cli(j){
    $("#resanswero tr:not(:first-of-type)").click(function(){
        $("#resanswero tr:not(:first-of-type)").removeClass("seleccionado");
        $(this).addClass("seleccionado");
    });

  
    }
