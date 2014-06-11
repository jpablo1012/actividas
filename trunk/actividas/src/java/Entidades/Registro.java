/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entidades;

/**
 *
 * @author Juan Pablo Lopera Estrada
 * @param <T>
 */
public class Registro<T> {
    private T dato;
    private int accion;
    
    public Registro(){
        dato = null;
        accion = -1;
    }
    
    public Registro(T dato, int accion){
        this.dato = dato;
        this.accion = accion;
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public int getAccion() {
        return accion;
    }

    public void setAccion(int accion) {
        this.accion = accion;
    }
    
    public final static int CREAR = 0;
    public final static int ACTUALIZAR = 1;
    public final static int ELIMINAR = 2;
}
