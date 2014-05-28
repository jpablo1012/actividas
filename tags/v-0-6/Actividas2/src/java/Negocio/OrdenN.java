/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio;

import Entidades.OrdenE;
import Persistencia.DaosOrden;
import Utilidades.Conexion;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Usuario
 */
public class OrdenN {
    public DaosOrden dao;

    public OrdenN(){
        dao = new DaosOrden();
    }

    public String crearOrden(OrdenE oe){
	/*if(oe.getEstado().equals("")){
	   return "1";
	}*/
	
	Date date = new Date();
	oe.setFecha_inicio(date);
	
        Conexion con = new Conexion();
        return dao.crearOrden(con.getCon(), oe);
    }

    public String actualizarOrden(OrdenE oe){
        Conexion con = new Conexion();
        return dao.actualizarOrden(con.getCon(), oe);
    }

    public ArrayList<OrdenE> buscarOrden(String variable, String valor, boolean exactamente){
        Conexion con = new Conexion();
        return dao.buscarOrden(con.getCon(), variable, valor, exactamente);
    }
    
    public String eliminarOrden(OrdenE oe){
	Conexion con = new Conexion();
	return dao.eliminarOrden(con.getCon(), oe);
    }
}
