$(document).ready(function(){
    cargarOrdenes();
});

function cargarOrdenes(){
    $("#mensajea").text(" ");
       console.log("lala");
   $.ajax({
        url: '/Actividas/SvlOrden',
        type: 'POST',
        timeout: 20000,
        dataType: 'text',
        data: {
            tipo: "extrusion",
            pista: "estado",
            valor: "ejecutando"
        }
    }).done(function (responseText){
        var ko = JSON.parse(responseText);
        insertarO(ko);
    }).fail(function() {
        
    });

}

function insertarO(k) {
    var nd = 0;
    nd=k.buscar.length;
    var tabla = document.getElementById('ordenR');
    var texto = "";
    for (var l = 0; l<nd; l++){
        var notas = k.buscar[l].nota;
        var maquinaE = k.buscar[l].maquina;
        var desperdicioE = k.buscar[l].desperdicio;
        var rollos = k.buscar[l].rollo;
        var numeroOrden = k.buscar[l].nOrden;
        var bolsaRef = k.buscar[l].Bref;
        var numeroExtrusion = k.buscar[l].nExtrusion;
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
        texto +='<div id="ordend" rollo="'+rollos+'" desperdicioEx="'+desperdicioE+'" maquina="'+maquinaE+'" nota="'+notas+'" fecha="'+fechaInicio+'" fechaPla="'+fechaPlaz+'" name="'+bolsaRef+'" refeBolsa="'+bolsaRef+'" numeroOrden="'+numeroOrden+'" extrusion="'+numeroExtrusion+'" style="background: '+color+'" onclick="llenar(this)"><label id="numeroO'+l+'" style="position:absolute;left:195px; margin-top:12px;">'+numeroOrden+'</label>'+
                         '<label id="refBolsa" style="position:absolute;left:375px; margin-top:12px;">'+bolsaRef+'</label>'+
                         '<label id="cantidad" style="position:absolute;left:513px; margin-top:12px;">'+cantidad+" "+tipoCantidad+'</label>'+
                         '<label id="fechaInicio" style="position:absolute;left:665px; margin-top:12px;">'+fechaInicio+'</label></div>';
    }

    tabla.innerHTML = texto;
}

function llenar(a) {
    $("#mensajea").text(" ");
    $("#fechaInicial").val($(a).attr("fecha"));
    $("#fechaFinal").val($(a).attr("fechaPla"));
   $("#referBolsa").val($(a).attr("name"));
   $("#numOrden").val($(a).attr("numeroOrden"));
   $("#rollosUsados").val($(a).attr("rollo"));
   $("#desperdicio").val($(a).attr("desperdicioEx"));
   $("#maquinaUsada").val($(a).attr("maquina"));
   $("#notas").val($(a).attr("nota"));
   $("#numExtrusion").val($(a).attr("extrusion"));
    
   var boton = document.getElementById('ordenda');
   boton.click();
   
   
 
}

function guardar(est){
  var  cedula = $.cookie('cedula');
  var numeroE = $("#numExtrusion").val();
  var rollosU = $("#rollosUsados").val();
  var desperdicion = $("#desperdicio").val();
  var maquinae = $("#maquinaUsada").val();
  var nota = $("#notas").val();
  var numeroOrd = $("#numOrden").val();
   
  $.ajax({
        url: '/Actividas/SvlOrden',
        type: 'POST',
        timeout: 20000,
        dataType: 'text',
        data: {
            cedulaEx:cedula,
            tipo: est,
            numero: numeroE,
            numeroOr: numeroOrd,
            rollos: rollosU,
            desperdicio: desperdicion,
            maquina: maquinae,
            notas: nota
        }
    }).done(function (responseText){
        cargarOrdenes();
        var ko = responseText;
        $("#mensajea").text(ko);
    }).fail(function() {
        
    }); 
   
}