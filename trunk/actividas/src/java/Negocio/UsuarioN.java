/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Entidades.List;
import Entidades.UsuarioE;
import Persistencia.DaosUsuario;
import Utilidades.Conexion;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Juan Pablo Lopera - Juan Diego Gonzalez
 */
public class UsuarioN {

    public DaosUsuario dao;
    public static List<UsuarioE> usersH = null;
    public boolean conBD = false;
    
    public UsuarioN() {
        dao = new DaosUsuario();
        if(usersH == null){
            usersH = getList();
        }
    }
    
    /**
     * 
     * @return retorna la tabla usuario, en una lista
     */
    public List<UsuarioE> getList(){
        Conexion con = new Conexion();
        Connection c = con.getCon();
        return dao.fillList(c, "nombre", "", false, true);
    }

    /**
     * 
     * @param cedula Cédula del supuesto usuario a validar inicio de sesión
     * @return Retorna un UsuarioE, si el usuario existe, de lo contrario
     * retorna null
     */
    public UsuarioE validarIngreso(String cedula) {
        Conexion con = new Conexion();
        Connection c = con.getCon();
        System.out.println(cedula);
        UsuarioE u = dao.validarUsuario(c, cedula);
        return u;
    }

    /**
     * Crea un usuario, en la base de datos o en la lista; Todo según el caso
     * @param ue Usuario a crear
     * @return  mensaje indicando si el cliente pudo ser creado o no
     */
    public String crearUsuario(UsuarioE ue) {
    	if (conBD){
            if (ue.getImagen() == null) {
                String clase = this.getClass().getResource("txt.txt").toString();
                clase = clase.replaceAll("file:/", "");
                clase = clase.replaceAll("/", "//");
    	    try {
    		clase = URLDecoder.decode(clase, "UTF-8");
    	    }catch(UnsupportedEncodingException ex) {}
    	    
    	    ue.setImagen(new File(clase));

    	}
    	
        Conexion con = new Conexion();
        Connection c = con.getCon();
        String u = dao.crearUsuario(c, ue);
        return u;
        
        }else{
            return dao.crearUsuario(ue, usersH);
        }
    }
    
    public List<UsuarioE> buscarUsuario(String valor){
        return dao.buscarUsuario(usersH, valor);
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

    /**
     * Permite actualizar algunos datos de cierto usuario en la DB o en una lista
     * @param ue datos del usuario a actualizar en la lista o en la DB
     * @return Confirmacion de la actualización, de lo contrario el respectivo error
     */
    public String actualizarUsuario(UsuarioE ue) {
    	if(conBD){
            if (ue.getImagen() == null) {
                String clase = this.getClass().getResource("txt.txt").toString();
                clase = clase.replaceAll("file:/", "");
                clase = clase.replaceAll("/", "//");
    	    try {
    		clase = URLDecoder.decode(clase, "UTF-8");
    	    } catch (UnsupportedEncodingException ex) {}
    	    
    	    ue.setImagen(new File(clase));

    	}
    	
        Conexion con = new Conexion();
        Connection c = con.getCon();
        return dao.actualizarUsuario(c, ue);
        }else{
            return dao.actualizarUsuario(ue, usersH);
        }
    }

    public String eliminarUsuario(String id) {
        if(conBD){
            Conexion con = new Conexion();
            return dao.eliminarUsuario(con.getCon(), id);
        }else{
            return dao.eliminarUsuario(id, usersH);
        }
    }
}
