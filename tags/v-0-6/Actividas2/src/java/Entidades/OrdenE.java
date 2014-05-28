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
public class OrdenE {

    private int numeroOrden;
    private int numeroPedido;
    private int bolsa_referencia;
    private double total_kilos;
    private double kilos_reales;
    private double cantidad_final;
    private String estado;
    private String referencia;
    private double cantidad;
    private String tipo_cantidad;
    private boolean extrusion;
    private boolean impresion;
    private boolean sellado;
    private Date fecha_inicio;
    private Date fecha_fin;
    private Date fecha_plazo;
    
    public int getNumeroOrden() {
        return numeroOrden;
    }
    public void setNumeroOrden(int numeroOrden) {
        this.numeroOrden = numeroOrden;
    }
    public int getNumeroPedido() {
        return numeroPedido;
    }
    public void setNumeroPedido(int numeroPedido) {
        this.numeroPedido = numeroPedido;
    }
    public int getBolsa_referencia() {
        return bolsa_referencia;
    }
    public void setBolsa_referencia(int bolsa_referencia) {
        this.bolsa_referencia = bolsa_referencia;
    }
    public double getTotal_kilos() {
        return total_kilos;
    }
    public void setTotal_kilos(double total_kilos) {
        this.total_kilos = total_kilos;
    }
    public double getKilos_reales() {
        return kilos_reales;
    }
    public void setKilos_reales(double kilos_reales) {
        this.kilos_reales = kilos_reales;
    }
    public double getCantidad_final() {
        return cantidad_final;
    }
    public void setCantidad_final(double cantidad_final) {
        this.cantidad_final = cantidad_final;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public String getReferencia() {
        return referencia;
    }
    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }
    public double getCantidad() {
        return cantidad;
    }
    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }
    public String getTipo_cantidad() {
        return tipo_cantidad;
    }
    public void setTipo_cantidad(String tipo_cantidad) {
        this.tipo_cantidad = tipo_cantidad;
    }
    public boolean isExtrusion() {
        return extrusion;
    }
    public void setExtrusion(boolean extrusion) {
        this.extrusion = extrusion;
    }
    public boolean isImpresion() {
        return impresion;
    }
    public void setImpresion(boolean impresion) {
        this.impresion = impresion;
    }
    public boolean isSellado() {
        return sellado;
    }
    public void setSellado(boolean sellado) {
        this.sellado = sellado;
    }
    public Date getFecha_inicio() {
        return fecha_inicio;
    }
    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }
    public Date getFecha_fin() {
        return fecha_fin;
    }
    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }
    public Date getFecha_plazo() {
        return fecha_plazo;
    }
    public void setFecha_plazo(Date fecha_plazo) {
        this.fecha_plazo = fecha_plazo;
    }
    
}
