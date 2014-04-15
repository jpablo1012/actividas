/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Entidades.BolsaE;
import Persistencia.DaosBolsa;
import Utilidades.Conexion;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;

/**
 * 
 * @author JPABLO
 */
public class BolsaN {

    public DaosBolsa daos;

    public BolsaN() {
	daos = new DaosBolsa();
    }

    public String crearBolsa(BolsaE be) {
	if (be.getImagen() == null) {
	    String clase = this.getClass().getResource("txt.txt").toString();
	    clase = clase.replaceAll("file:/", "");
	    clase = clase.replaceAll("/", "//");
	    try {
		clase = URLDecoder.decode(clase, "UTF-8");
	    } catch (UnsupportedEncodingException ex) {
	    }
	    
	    be.setImagen(new File(clase));

	}

	Conexion con = new Conexion();
	return daos.crearBolsa(con.getCon(), be);
    }

    public ArrayList<BolsaE> buscarBolsa(String variable, String valor, boolean exactamente) {
    	Conexion con = new Conexion();
    	return daos.buscarBolsa(con.getCon(), variable, valor, exactamente);
    }
    
    public ArrayList<BolsaE> buscarBolsa(String variable, String valor, boolean exactamente, boolean imagen) {
    	Conexion con = new Conexion();
    	return daos.buscarBolsa(con.getCon(), variable, valor, exactamente, imagen);
    }

    public String actualizarBolsa(BolsaE be) {
	if (be.getImagen() == null) {
	    String clase = this.getClass().getResource("txt.txt").toString();
	    clase = clase.replaceAll("file:/", "");
	    clase = clase.replaceAll("/", "//");
	    try {
		clase = URLDecoder.decode(clase, "UTF-8");
	    } catch (UnsupportedEncodingException ex) {
	    }
	    
	    be.setImagen(new File(clase));

	}
	
	Conexion con = new Conexion();
	return daos.actualizarBolsa(con.getCon(), be);
    }

    public String eliminarBolsa(BolsaE be) {
	Conexion con = new Conexion();
	return daos.eliminarBolsa(con.getCon(), be);
    }
}
