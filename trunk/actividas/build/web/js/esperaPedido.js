$("document").ready(function(){
   cargarPedidos();
});


function aprobar(a,b){
    var numPed = a;
    console.log(numPed);
    console.log(b);
   $.ajax({
        url: '/Actividas/SvlPedido',
        type: 'POST',
        timeout: 20000,
        dataType: 'text',
        data: {
            numeroPedido: numPed,
            estado: b,
            tipo: "aprobar"
        }
    }).done(function (responseText){
        cargarPedidos();
    }).fail(function() {

    }); 

}

function cargarPedidos(){
   $.ajax({
        url: '/Actividas/SvlOrden',
        type: 'POST',
        timeout: 20000,
        dataType: 'text',
        data: {
            tipo: "getPedidos",
            pista: "estado",
            valor: "pendiente"
        }
    }).done(function (responseText){
        var k = JSON.parse(responseText);
        insertarP(k);
    }).fail(function() {
        
    }); 
    
}

function insertarP(k) {
    
    var nd = 0;
    nd=k.buscar.length;
    var tabla = document.getElementById('pedidosR');
    var texto = " ";
    for (var l = 0; l<nd; l++){
        var numeroPedido = "";
        numeroPedido = k.buscar[l].referenciaP;
        var cedula = k.buscar[l].cedula;
        var fechaEntrega = k.buscar[l].Fentrega;
        var tipoVenta = k.buscar[l].Tventa;
        texto +='<div id="pedidod"><label id="cedula'+l+'" style="margin: 30px 0px 0px 20px;">'+cedula+'</label>'+
                         '<label id="fechaEntrega" style="margin: 0px 0px 0px 200px;">'+fechaEntrega+'</label>'+
                         '<label id="tipoVenta" style="margin: 0px 0px 0px 64px;">'+tipoVenta+'</label>'+
                         '<br><input id="'+numeroPedido+'" name="aprobado" onclick="aprobar(this.id,this.name)" value="Aprobar" type="button" style="margin: 15px 0px 0px 10px;" />'+
                         '<input id="'+numeroPedido+'" name="rechazado" onclick="aprobar(this.id,this.name)" value="Rechazar" type="button" style="margin: 0px 0px 0px 10px;" />'+
                         '<input id="masInf'+l+'" value="Mas informacion" type="button" style="margin: 0px 0px 0px 150px;"/>'+
                         '<label id="n'+l+'" style="margin: 0px 0px 0px 10px;">Numero de Pedido: '+numeroPedido+'</label></div>';
    }

    tabla.innerHTML = texto;
}