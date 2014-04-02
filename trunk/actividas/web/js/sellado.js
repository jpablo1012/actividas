$(document).ready(function(){
    cargarOrdenesS();
});

function cargarOrdenesS(){
    $("#mensajeS").text(" ");
   $.ajax({
        url: '/Actividas/SvlOrden',
        type: 'POST',
        timeout: 20000,
        dataType: 'text',
        data: {
            tipo: "sellado",
        }
    }).done(function (responseText){
        var ks = JSON.parse(responseText);
        insertarS(ks);
    }).fail(function() {
        
    });

}

function insertarS(k) {
    var nd = 0;
    nd=k.buscar.length;
    var tabla = document.getElementById('ordenS');
    var texto = "";
    for (var l = 0; l<nd; l++){
        var notas = k.buscar[l].nota;
        var retalS = k.buscar[l].retal;
        var bolsasSell = k.buscar[l].bolsasSelladas;
        var numeroOrden = k.buscar[l].nOrden;
        var bolsaRef = k.buscar[l].Bref;
        var numeroSellado = k.buscar[l].nSellado;
        var tipoCantidad = k.buscar[l].Tcantidad;
        var cantidad = k.buscar[l].cantidad;
        var fechaInicio = k.buscar[l].fechaIni;
        var fechaPlaz = k.buscar[l].fechaPlazo;
        var color;
            if((l%2)== 0){
               color = "#D4D0D0";
            }else{
               color = "#F8F7F7";
            }
        texto +='<div id="ordendis" Tcantidad="'+tipoCantidad+'" cantidad="'+cantidad+'" retal="'+retalS+'" bolsasSelladas="'+bolsasSell+'" nota="'+notas+'" fecha="'+fechaInicio+'" fechaPla="'+fechaPlaz+'" name="'+bolsaRef+'" numeroOrden="'+numeroOrden+'" sellado="'+numeroSellado+'" style="background: '+color+'" onclick="llenarS(this)"><table><tr><td><label id="numeroOS'+l+'" style="margin-left:50px">'+numeroOrden+'</label></td>'+
                         '<td style="width: 50px;"><label id="refBolsaS" style="margin-left:156px">'+bolsaRef+'</label></td>'+
                         '<td style="width: 150px;"><label id="cantidadS" style="margin-left:113px">'+cantidad+" "+tipoCantidad+'</label></td>'+
                         '<td style="width: 180px; text-align: right"><label id="fechaInicioS" style="margin-left:60px">'+fechaPlaz+'</label></td></tr></table></div>';
    }

    tabla.innerHTML = texto;
}

function llenarS(a) {
    $("#mensajeS").text(" ");
    $("#pesoA").val($(a).attr("pesoInicio"));
    $("#impresionI").val($(a).attr("impresionI"));
    $("#fechaInicialI").val($(a).attr("fecha"));
    $("#fechaFinalI").val($(a).attr("fechaPla"));
   $("#referBolsaS").val($(a).attr("name"));
   $("#numOrdenS").val($(a).attr("numeroOrden"));
   $("#cantidadI").val($(a).attr("cantidad"));
   $("#TcantI").html($(a).attr("Tcantidad"));
   $("#retalS").val($(a).attr("retal"));
   $("#bolsasSell").val($(a).attr("bolsasSelladas"));
   $("#notasS").val($(a).attr("nota"));
   $("#numSellado").val($(a).attr("sellado"));
    
   var boton = document.getElementById('ordends');
   boton.click();
}

function guardarSe(esto){
  var cedula = $.cookie('cedula');
  var numeroS = $("#numSellado").val();
  var retalSe = $("#retalS").val();
  var bolsasSelladas = $("#bolsasSell").val();
  var nota = $("#notasS").val();
  var numeroOrdI = $("#numOrdenS").val();
   
  $.ajax({
        url: '/Actividas/SvlOrden',
        type: 'POST',
        timeout: 20000,
        dataType: 'text',
        data: {
            cedulaS:cedula,
            tipo: esto,
            numero: numeroS,
            numeroOr: numeroOrdI,
            retal: retalSe,
           bolsasSella: bolsasSelladas,
            notas: nota
        }
    }).done(function (responseText){
        cargarOrdenesS();
        var ko = responseText;
        $("#mensajeS").text(ko);
    }).fail(function() {
        
    }); 
   
}