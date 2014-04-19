/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.util.Date;

/**
 *
 * @author JPABLO
 */
public class ExtrusionE {

    private int codigo;
    private int numeroOrden;
    private int bolsa_referencia;
    private String empleado_cedula;
    private String nota;
    private String maquina;
    private String estado;

    private double desperdicio;
    private double rollo;

    private Date fechaInicio;
    private Date fechaFin;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(int numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public int getBolsa_referencia() {
        return bolsa_referencia;
    }

    public void setBolsa_referencia(int bolsa_referencia) {
        this.bolsa_referencia = bolsa_referencia;
    }

    public String getEmpleado_cedula() {
        return empleado_cedula;
    }

    public void setEmpleado_cedula(String empleado_cedula) {
        this.empleado_cedula = empleado_cedula;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getMaquina() {
        return maquina;
    }

    public void setMaquina(String maquina) {
        this.maquina = maquina;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getDesperdicio() {
        return desperdicio;
    }

    public void setDesperdicio(double desperdicio) {
        this.desperdicio = desperdicio;
    }

    public double getRollo() {
        return rollo;
    }

    public void setRollo(double rollo) {
        this.rollo = rollo;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
    
    
    
    
   
}
