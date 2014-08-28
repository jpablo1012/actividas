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
    private Accion accion;
    
    public Registro(){
        dato = null;
        accion = null;
    }
    
    public Registro(T dato, Accion accion){
        this.dato = dato;
        this.accion = accion;
    }

    public T getDato() {
        return dato;
    }
    
    public void setAccion(Accion accion){
        this.accion = accion;
    }
    
    public Accion getAccion(){
        return this.accion;
    }
}