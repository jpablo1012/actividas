/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio;

import Entidades.PedidoE;
import Persistencia.DaosPedido;
import Utilidades.Conexion;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Usuario
 */
public class PedidoN {

    public DaosPedido dao;

    public PedidoN(){
        dao = new DaosPedido();
    }

    public String crearPedido(PedidoE pe){
    	Date hoy = new Date();
    	pe.setFecha_creacion(hoy);
    	pe.setEstado("pendiente");
    	
        Conexion con = new Conexion();
        return dao.crearPedido(con.getCon(), pe);
    }

    public String actualizarPedido(PedidoE pe){
        Conexion con = new Conexion();
        return dao.actualizarPedido(con.getCon(), pe);
    }

    public ArrayList<PedidoE> buscarPedido(String variable, String valor, boolean exactamente){
        Conexion con = new Conexion();
        return dao.buscarPedido(con.getCon(), variable, valor, exactamente);
    }

}
