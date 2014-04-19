/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.*;
import java.util.ArrayList;

import Persistencia.DaosUsuario;
import Utilidades.Conexion;
import Entidades.UsuarioE;

/**
 *
 * @author JPABLO
 */
public class UsuarioN {

    public DaosUsuario dao;

    public UsuarioN() {
        dao = new DaosUsuario();
    }

    public UsuarioE validarIngreso(String cedula) {
        Conexion con = new Conexion();
        Connection c = con.getCon();
        System.out.println(cedula);
        UsuarioE u = dao.validarUsuario(c, cedula);
        return u;
    }

    public String crearUsuario(UsuarioE ue) {
    	if (ue.getImagen() == null) {
    	    String clase = this.getClass().getResource("txt.txt").toString();
    	    clase = clase.replaceAll("file:", "");
    	    clase = clase.replaceAll("/", "//");
    	    try {
    		clase = URLDecoder.decode(clase, "UTF-8");
    	    } catch (UnsupportedEncodingException ex) {
    	    }
    	    
    	    ue.setImagen(new File(clase));

    	}
    	
        Conexion con = new Conexion();
        Connection c = con.getCon();
        String u = dao.crearUsuario(c, ue);
        return u;
    }

    public ArrayList<UsuarioE> buscarUsuario(String variable, String valor, boolean exactamente) {
        Conexion con = new Conexion();
        Connection c = con.getCon();
        return dao.buscarUsuario(c, variable, valor, exactamente);
    }
    
    public ArrayList<UsuarioE> buscarUsuario(String variable, String valor, boolean exactamente, boolean imagen) {
        Conexion con = new Conexion();
        Connection c = con.getCon();
        return dao.buscarUsuario(c, variable, valor, exactamente, imagen);
    }

    public String actualizarUsuario(UsuarioE ue) {
    	if (ue.getImagen() == null) {
    	    String clase = this.getClass().getResource("txt.txt").toString();
    	    clase = clase.replaceAll("file:/", "");
    	    clase = clase.replaceAll("/", "//");
    	    try {
    		clase = URLDecoder.decode(clase, "UTF-8");
    	    } catch (UnsupportedEncodingException ex) {
    	    }
    	    
    	    ue.setImagen(new File(clase));

    	}
    	
        Conexion con = new Conexion();
        Connection c = con.getCon();
        return dao.actualizarUsuario(c, ue);
    }
    

    public String eliminarUsuario(String id) {
        Conexion con = new Conexion();
        return dao.eliminarUsuario(con.getCon(), id);
    }
}
