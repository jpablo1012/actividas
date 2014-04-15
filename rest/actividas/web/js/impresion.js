$(document).ready(function(){
    cargarOrdenesI();
});

function cargarOrdenesI(){
    $("#mensajeI").text(" ");
   $.ajax({
        url: '/Actividas/SvlOrden',
        type: 'POST',
        timeout: 20000,
        dataType: 'text',
        data: {
            tipo: "impresion",
        }
    }).done(function (responseText){
        var ko = JSON.parse(responseText);
        insertarI(ko);
    }).fail(function() {
        
    });

}

function insertarI(k) {
    var nd = 0;
    nd=k.buscar.length;
    var tabla = document.getElementById('ordenI');
    var texto = "";
    for (var l = 0; l<nd; l++){
        var notas = k.buscar[l].nota;
        var impresionIm = k.buscar[l].impresionI;
        var pesoInicial = k.buscar[l].pAntes;
        var pesoFinal = k.buscar[l].pFinal;
        var numeroOrden = k.buscar[l].nOrden;
        var bolsaRef = k.buscar[l].Bref;
        var numeroImpresion = k.buscar[l].nImpresion;
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
        texto +='<div id="ordendid" Tcantidad="'+tipoCantidad+'" cantidad="'+cantidad+'" impresionI="'+impresionIm+'" pesoInicio="'+pesoInicial+'" pesoFin="'+pesoFinal+'" nota="'+notas+'" fecha="'+fechaInicio+'" fechaPla="'+fechaPlaz+'" name="'+bolsaRef+'" numeroOrden="'+numeroOrden+'" impresion="'+numeroImpresion+'" style="background: '+color+'" onclick="llenarI(this)"><label id="numeroOI'+l+'" style="position:absolute;left:195px; margin-top:12px;">'+numeroOrden+'</label>'+
                         '<label id="refBolsaI" style="position:absolute;left:375px; margin-top:12px;">'+bolsaRef+'</label>'+
                         '<label id="cantidad" style="position:absolute;left:513px; margin-top:12px;">'+cantidad+" "+tipoCantidad+'</label>'+
                         '<label id="fechaInicio" style="position:absolute;left:665px; margin-top:12px;">'+fechaPlaz+'</label></div>';
    }

    tabla.innerHTML = texto;
}

function llenarI(a){
    $("#mensajeI").text(" ");
    $("#pesoA").val($(a).attr("pesoInicio"));
    $("#impresionI").val($(a).attr("impresionI"));
    $("#fechaInicialI").val($(a).attr("fecha"));
    $("#fechaFinalI").val($(a).attr("fechaPla"));
   $("#referBolsaI").val($(a).attr("name"));
   $("#numOrdenI").val($(a).attr("numeroOrden"));
   $("#cantidadI").val($(a).attr("cantidad"));
   $("#TcantI").html($(a).attr("Tcantidad"));
   $("#pesoD").val($(a).attr("pesoFin"));
   $("#notasI").val($(a).attr("nota"));
   $("#numImpresion").val($(a).attr("impresion"));
    
   var boton = document.getElementById('ordendi');
   boton.click();
}

function guardarIm(esto){
    var  cedula = $.cookie('cedula');
  var numeroI = $("#numImpresion").val();
  var pesoInic = $("#pesoA").val();
  var pesoFina = $("#pesoD").val();
  var nota = $("#notasI").val();
  var numeroOrdI = $("#numOrdenI").val();
  var impresI = $("#impresionI").val();
   
  $.ajax({
        url: '/Actividas/SvlOrden',
        type: 'POST',
        timeout: 20000,
        dataType: 'text',
        data: {
            cedulaIm: cedula,
            tipo: esto,
            numero: numeroI,
            numeroOr: numeroOrdI,
            pInicialI: pesoInic,
            pFinalI: pesoFina,
            impresion: impresI,
            notas: nota
        }
    }).done(function (responseText){
        cargarOrdenesI();
        var ko = responseText;
        $("#mensajeI").text(ko);
    }).fail(function() {
        
    }); 
   
}