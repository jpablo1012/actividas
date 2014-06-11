package Entidades;

public class ClienteE {

    private String cedula;
    private String direccion;
    private String correo;
    private String nombre;
    private String apellido;
    private String telefono;
    private String ciudad;

    //Defining constructor
    public ClienteE(){
        this(null,null,null,null,null,null,null);
    }
    
    public ClienteE(String cedula, String direccion, String email, String nombre, String apellido, String telefono, String ciudad){
        this.cedula = cedula;
        this.direccion = direccion;
        this.correo = email;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.ciudad = ciudad;
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
