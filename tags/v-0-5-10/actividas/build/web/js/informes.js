$("document").ready(function() {
    var fecha;
    var f = new Date();
    fecha= f.getFullYear() + "-" + (f.getMonth() +1) + "-" + f.getDate();
     $("#fecha2").val(fecha);
     $("#fecha2").attr("max",fecha);
     console.log(fecha);
});