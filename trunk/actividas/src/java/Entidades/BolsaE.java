package Entidades;

import java.io.File;

/**
 *
 * @author Usuario
 */
public class BolsaE {

    private int referencia;
    private int color_codigo;
    private int material_referencia;
    private String cliente_cedula;
    private String medida;
    private double ancho;
    private double largo;
    private double calibre;
    private boolean tratado;
    private double transArriba;
    private double transAbajo;
    private double fuelle_fondo;
    private double fuelle_superior;
    private double fuelle_lateral;
    private double ancho_rollo;
    private File imagen;
    private double solapa;
    private String troquel;

    public int getReferencia() {
        return referencia;
    }

    public void setReferencia(int referencia) {
        this.referencia = referencia;
    }

    public int getColor_codigo() {
        return color_codigo;
    }

    public void setColor_codigo(int color_codigo) {
        this.color_codigo = color_codigo;
    }

    public int getMaterial_referencia() {
        return material_referencia;
    }

    public void setMaterial_referencia(int material_referencia) {
        this.material_referencia = material_referencia;
    }

    public String getCliente_cedula() {
        return cliente_cedula;
    }

    public void setCliente_cedula(String cliente_cedula) {
        this.cliente_cedula = cliente_cedula;
    }

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    public double getAncho() {
        return ancho;
    }

    public void setAncho(double ancho) {
        this.ancho = ancho;
    }

    public double getLargo() {
        return largo;
    }

    public void setLargo(double largo) {
        this.largo = largo;
    }

    public double getCalibre() {
        return calibre;
    }

    public void setCalibre(double calibre) {
        this.calibre = calibre;
    }

    public boolean isTratado() {
        return tratado;
    }

    public void setTratado(boolean tratado) {
        this.tratado = tratado;
    }

    public double getTransArriba() {
        return transArriba;
    }

    public void setTransArriba(double transArriba) {
        this.transArriba = transArriba;
    }

    public double getTransAbajo() {
        return transAbajo;
    }

    public void setTransAbajo(double transAbajo) {
        this.transAbajo = transAbajo;
    }

    public double getFuelle_fondo() {
        return fuelle_fondo;
    }

    public void setFuelle_fondo(double fuelle_fondo) {
        this.fuelle_fondo = fuelle_fondo;
    }

    public double getFuelle_superior() {
        return fuelle_superior;
    }

    public void setFuelle_superior(double fuelle_superior) {
        this.fuelle_superior = fuelle_superior;
    }

    public double getFuelle_lateral() {
        return fuelle_lateral;
    }

    public void setFuelle_lateral(double fuelle_lateral) {
        this.fuelle_lateral = fuelle_lateral;
    }

    public double getAncho_rollo() {
        return ancho_rollo;
    }

    public void setAncho_rollo(double ancho_rollo) {
        this.ancho_rollo = ancho_rollo;
    }

    public File getImagen() {
        return imagen;
    }

    public void setImagen(File imagen) {
        this.imagen = imagen;
    }

    public double getSolapa() {
        return solapa;
    }

    public void setSolapa(double solapa) {
        this.solapa = solapa;
    }

    public String getTroquel() {
        return troquel;
    }

    public void setTroquel(String troquel) {
        this.troquel = troquel;
    }

}
