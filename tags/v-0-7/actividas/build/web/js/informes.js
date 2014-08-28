$("document").ready(function() {
    fecha();

    $("#infefech").click(function() {
        validar();
        return false;
    });
});

function validar() {
    console.log("lala");
}

function fecha() { // coloca la fecha por defecto y el max
    var fecha;
    var f = new Date();
    fecha = f.getFullYear() + "-";
    if (f.getMonth() + 1 <= 9) {
        fecha = fecha + "0";
    }
    fecha = fecha + (f.getMonth() + 1) + "-";
    if (f.getDate() <= 9) {
        fecha = fecha + "0";
    }
    fecha = fecha + f.getDate();
    $("#fecha2").val(fecha);
    $("#fecha2").attr("max", fecha);
}