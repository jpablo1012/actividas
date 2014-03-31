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
public class ImpresionE {

    private int codigo;
    private int numeroOrden;
    private String empleado_cedula;
    private double diferencia;
    private String impresion;
    private double montaje;
    private double pesoFinal;
    private double pesoInicial;
    private double rodaje;
    private int rodillo;
    private String nota;
    private Date fechaInicio;
    private Date fechaFin;
    private int bolsa_referencia;
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

    public double getDiferencia() {
        return diferencia;
    }

    public void setDiferencia(double diferencia) {
        this.diferencia = diferencia;
    }

    public String getImpresion() {
        return impresion;
    }

    public void setImpresion(String impresion) {
        this.impresion = impresion;
    }

    public double getMontaje() {
        return montaje;
    }

    public void setMontaje(double montaje) {
        this.montaje = montaje;
    }

    public double getPesoFinal() {
        return pesoFinal;
    }

    public void setPesoFinal(double pesoFinal) {
        this.pesoFinal = pesoFinal;
    }

    public double getPesoInicial() {
        return pesoInicial;
    }

    public void setPesoInicial(double pesoInicial) {
        this.pesoInicial = pesoInicial;
    }

    public double getRodaje() {
        return rodaje;
    }

    public void setRodaje(double rodaje) {
        this.rodaje = rodaje;
    }

    public int getRodillo() {
        return rodillo;
    }

    public void setRodillo(int rodillo) {
        this.rodillo = rodillo;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
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

    public int getBolsa_referencia() {
        return bolsa_referencia;
    }

    public void setBolsa_referencia(int bolsa_referencia) {
        this.bolsa_referencia = bolsa_referencia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
   
}
