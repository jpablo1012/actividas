/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.File;

/**
 *
 * @author USUARIO
 */
public class UsuarioE {

    private String idUsuario;
    private String nombre;
    private String tipo;
    private String clienteCedula;
    private String empleadoCedula;
    private String codigo;
    private String apellido;
    private File imagen;
    
    public UsuarioE(){
        this(null, null, null, null, null, null, null, null);
    }
    
    public UsuarioE(String id, String nombre, String apellido, String tipo, 
            String clienteCedula, String empleadoCedula, String codigo, File imagen){
        this.idUsuario = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipo = tipo;
        this.clienteCedula = clienteCedula;
        this.empleadoCedula = empleadoCedula;
        this.codigo = codigo;
        this.imagen = imagen;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getClienteCedula() {
        return clienteCedula;
    }

    public void setClienteCedula(String clienteCedula) {
        this.clienteCedula = clienteCedula;
    }

    public String getEmpleadoCedula() {
        return empleadoCedula;
    }

    public void setEmpleadoCedula(String empleadoCedula) {
        this.empleadoCedula = empleadoCedula;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public File getImagen() {
        return imagen;
    }

    public void setImagen(File imagen) {
        this.imagen = imagen;
    }
}
