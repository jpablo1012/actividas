/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.File;

/**
 *
 * @author Juan Diego - Juan Pablo
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

    public UsuarioE(String idU, String name, String type, String clienteC, String empleadoC, String code, String lastName, File img){
        this.idUsuario = idU;
        this.nombre = name;
        this.tipo = type;
        this.clienteCedula = clienteC;
        this.empleadoCedula = empleadoC;
        this.codigo = code;
        this.apellido = lastName;
        this.imagen = img;
    }
    
    public UsuarioE(){
        this.idUsuario = null;
        this.nombre = null;
        this.tipo = null;
        this.clienteCedula = null;
        this.empleadoCedula = null;
        this.codigo = null;
        this.apellido = null;
        this.imagen = null;
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
