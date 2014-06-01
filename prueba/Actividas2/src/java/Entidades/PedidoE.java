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
public class PedidoE {

    private int numeroPedido;
    private String cliente_cedula;
    private int bolsa_referencia;
    private double precio;
    private Date fecha_entrega;
    private String factura;
    private String tipo_venta;
    private Date fecha_creacion;
    private String factura_despacho;
    private Date fecha_despacho;
    private String estado;
    private double factor;
    private double peso_millar;
    private double base;
    private String referencia;
    private double cantidad;
    private String tipo_cantidad;
    private boolean extrusion;
    private boolean impresion;
    private boolean sellado;

    public double getBase() {
        return base;
    }

    public void setBase(double base) {
        this.base = base;
    }

    public int getBolsa_referencia() {
        return bolsa_referencia;
    }

    public void setBolsa_referencia(int bolsa_referencia) {
        this.bolsa_referencia = bolsa_referencia;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public String getCliente_cedula() {
        return cliente_cedula;
    }

    public void setCliente_cedula(String cliente_cedula) {
        this.cliente_cedula = cliente_cedula;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean isExtrusion() {
        return extrusion;
    }

    public void setExtrusion(boolean extrusion) {
        this.extrusion = extrusion;
    }

    public double getFactor() {
        return factor;
    }

    public void setFactor(double factor) {
        this.factor = factor;
    }

    public String getFactura() {
        return factura;
    }

    public void setFactura(String factura) {
        this.factura = factura;
    }

    public String getFactura_despacho() {
        return factura_despacho;
    }

    public void setFactura_despacho(String factura_despacho) {
        this.factura_despacho = factura_despacho;
    }

    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public Date getFecha_despacho() {
        return fecha_despacho;
    }

    public void setFecha_despacho(Date fecha_despacho) {
        this.fecha_despacho = fecha_despacho;
    }

    public Date getFecha_entrega() {
        return fecha_entrega;
    }

    public void setFecha_entrega(Date fecha_entrega) {
        this.fecha_entrega = fecha_entrega;
    }

    public boolean isImpresion() {
        return impresion;
    }

    public void setImpresion(boolean impresion) {
        this.impresion = impresion;
    }

    public int getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(int numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public double getPeso_millar() {
        return peso_millar;
    }

    public void setPeso_millar(double peso_millar) {
        this.peso_millar = peso_millar;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public boolean isSellado() {
        return sellado;
    }

    public void setSellado(boolean sellado) {
        this.sellado = sellado;
    }

    public String getTipo_cantidad() {
        return tipo_cantidad;
    }

    public void setTipo_cantidad(String tipo_cantidad) {
        this.tipo_cantidad = tipo_cantidad;
    }

    public String getTipo_venta() {
        return tipo_venta;
    }

    public void setTipo_venta(String tipo_venta) {
        this.tipo_venta = tipo_venta;
    }
}
