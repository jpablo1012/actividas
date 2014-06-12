/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio;

import Entidades.List;
import Entidades.Registro;

/**
 *
 * @author Juan Pablo Lopera Estrada
 * @since 11/06/2014
 */
public class Historial {
    private static List<Registro> cambios;
    
    
    public Historial(){
        if(cambios == null){
            cambios = new List<Registro>();
        }
    }
    /**
     * Añade un cambio a la cola de cambios
     * @param registro cambio a añadir
     */
    public void añadir(Registro registro){
        cambios.push(registro);
    }
    
    /**
     * Retorna la cola de cambios
     * @return List - cola de cambios
     */
    public List<Registro> getCambios(){
        return cambios;
    }
}
