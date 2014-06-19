package Entidades;

public class ClienteE <String> {

    private String cedula;
    private String direccion;
    private String correo;
    private String nombre;
    private String apellido;
    private String telefono;
    private String ciudad;
    private ClienteE<String> next;
    private ClienteE<String> back;

    //Defining constructor
    public ClienteE(){
        this(null,null,null,null,null,null,null,null,null);
    }
    
    public ClienteE(String id, String address, String mail, String name, String lastName, String phone, String city){
        this(id, address, mail, name, lastName, phone, city, null, null);
    }
    
    public ClienteE(String id, String address, String mail, String name, String lastName, String phone, String city, ClienteE next, ClienteE back){
        this.cedula = id;
        this.direccion = address;
        this.correo = mail;
        this.nombre = name;
        this.apellido = lastName;
        this.telefono = phone;
        this.ciudad = city;
        this.next = next;
        this.back = back;
        
    }
    
    //getters and setters
    public ClienteE getNext() {
        return next;
    }
    
     public void setNext(ClienteE next) {
        this.next = next;
    }

    public ClienteE getBack() {
        return back;
    }

    public void setBack(ClienteE back) {
        this.back = back;
    }
    
    
    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}