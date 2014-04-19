$(document).ready(function() {
    $("#button").click(function() {
        animacion();
        return false;
    });

   


var bo = true;


function animacion() {
    if (bo) {
        $("#effect").removeClass("effectDes");
        $("#effect").addClass("effectAct");
         $(".toggler").css("display", "block");
        bo = !bo;
    } else {
        $("#effect").removeClass("effectAct");
        $("#effect").addClass("effectDes");
         $(".toggler").css("display", "none");
        bo = !bo;
    }
}
});