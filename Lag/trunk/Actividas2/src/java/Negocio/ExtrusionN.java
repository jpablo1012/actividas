/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio;

import Entidades.ExtrusionE;
import Persistencia.DaosExtrusion;
import Utilidades.Conexion;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Usuario
 */
public class ExtrusionN {

    public DaosExtrusion dao;

    public ExtrusionN(){
	
	
        dao = new DaosExtrusion();
    }

    public String crearExtrusion(ExtrusionE ee){
        Conexion con = new Conexion();
        
        Date date = new Date();
        ee.setFechaInicio(date);
        
        return dao.crearExtrusion(con.getCon(), ee);
    }

    public String actualizarExtrusion(ExtrusionE ee){
        Conexion con = new Conexion();
        return dao.actualizarExtrusion(con.getCon(), ee);
    }

    public ArrayList<ExtrusionE> buscarExtrusion(String variable, String valor, boolean exactamente){
        Conexion con = new Conexion();
        return dao.buscarExtrusion(con.getCon(), variable, valor, exactamente);
    }
}
