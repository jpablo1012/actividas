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
public class SelladoE {

    private int codigo;
    private int numeroOrden;
    private String empleado_cedula;
    private int bolsa_referencia;
    private Date fechaFin;
    private Date fechaInicio;
    private String nota;
    private double bolsasSelladas;
    private double retal;
    private String estado;
    
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
    public String getEmpleado_cedula() {
        return empleado_cedula;
    }
    public void setEmpleado_cedula(String empleado_cedula) {
        this.empleado_cedula = empleado_cedula;
    }
    public int getBolsa_referencia() {
        return bolsa_referencia;
    }
    public void setBolsa_referencia(int bolsa_referencia) {
        this.bolsa_referencia = bolsa_referencia;
    }
    public Date getFechaFin() {
        return fechaFin;
    }
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
    public Date getFechaInicio() {
        return fechaInicio;
    }
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    public String getNota() {
        return nota;
    }
    public void setNota(String nota) {
        this.nota = nota;
    }
    public double getBolsasSelladas() {
        return bolsasSelladas;
    }
    public void setBolsasSelladas(double bolsasSelladas) {
        this.bolsasSelladas = bolsasSelladas;
    }
    public double getRetal() {
        return retal;
    }
    public void setRetal(double retal) {
        this.retal = retal;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
}
