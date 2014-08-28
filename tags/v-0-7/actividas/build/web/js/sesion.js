
//variables globales para su posterior uso
var cedula;
var tipo;
var nombre;
var real;
var ul;

//3 JSON con cada menu correspondiente al tipo de usuario
var admin = {
    "modulos": [{"nombre": "Insumos", "url": "#", "id": "mmpa"},
        {"nombre": "Pedidos", "url": "#", "id": "mpa"},
        {"nombre": "Informes", "url": "#", "id": "mia"},
        {"nombre": "Ordenes de producci&oacuten", "url": "#", "id": "mopa"},
        {"nombre": "Empleados", "url": "#", "id": "mea"},
        {"nombre": "Clientes", "url": "#", "id": "mca"},
        {"nombre": "Cerrar sesi&oacuten", "url": "#", "id": "cerrar"}]
};

var empleado = {
    "modulos": [{"nombre": "Perfil", "url": "#", "id": "pme"},
        {"nombre": "Orden de producci&oacuten", "url": "#", "id": "mopa"},
        {"nombre": "Cerrar sesi&oacuten", "url": "", "id": "cerrar"}]};

var cliente = {
    "modulos": [{"nombre": "Perfil", "url": "#", "id": "pmc"},
        {"nombre": "Bolsas", "url": "#", "id": "mcb"},
        {"nombre": "Pedidos", "url": "#", "id": "mpa"},
        {"nombre": "Cerrar sesi&oacuten", "url": "#", "id": "cerrar"}]};


$("document").ready(sesion());//cuando el documento ha cargado ejecuta "sesion()"

function sesion() {
    //trae las cookies de inicio de sesion llenadas anteriormente
    cedula = $.cookie('cedula');
    tipo = $.cookie('tipo');
    nombre = $.cookie('nombre');
    real = $.cookie('real');
    //tipo = sessionStorage.tipo;
    //nombre = sessionStorage.nombre;
    //cedula = sessionStorage.cedula;


    if (cedula == null || tipo == null || nombre == null) {


    } else {
        $("script[src='js/autentificar.js']").remove(); //borra el elemento script que tenga el etributo "src='js/autentificar.js'"
        $("script[src='js/js-image-slider.js']").remove();
        $(".toggler").remove();
        $("#slider").remove();
        ul = document.getElementById("barra"); //la variable ul es igual al elemento con la id "barra"
        ul.innerHTML = ""; //borra lo que contenga la variable

        //si el tipo de usuario es diferente a administrador o cliente ejecuta default
        switch (tipo) {
            case "administrador":
                barra(admin);
                $(".info").load("introCli.jsp");
                break;
            case "cliente":
                barra(cliente);
                $(".info").load("perfilCliente.jsp");
                break;
            default:
                barra(empleado);
                $(".info").load("perfilEmpleado.jsp");
                break;
        }
    }
}

function barra(t) {
    for (var i = 0; i < t.modulos.length; i++) {
        var li = document.createElement("li");//crea un elemento <li>
        var a = document.createElement("a");//crea un elemento <a>
        a.setAttribute("href", t.modulos[i].url);//agrega el atributo href al elemento <a>
        a.setAttribute("id", t.modulos[i].id);
        a.innerHTML = t.modulos[i].nombre;//agrega el texto a mostar del <a>

        li.appendChild(a);//agrega el elemento <a> al elemento <li>
        ul.appendChild(li);//agrega el elemento <li> al elemento <ul>
    }


    $("#cerrar").click(function() {
        cerrar();
    });
}

function cerrar() {
    //vacia todas las cookies y recarga la pagina
    $.cookie('cedula', null);
    $.cookie('tipo', null);
    $.cookie('nombre', null);
    $.cookie('real', null);
    //sessionStorage.clear();

    window.location = "index.jsp";
}

