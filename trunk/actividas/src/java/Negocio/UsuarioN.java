/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

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

    public UsuarioN() {
        dao = new DaosUsuario();
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
     *
     * @param ue Usuario a crear
     * @return mensaje indicando si el cliente pudo ser creado o no
     */
    public String crearUsuario(UsuarioE ue) {
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

    /**
     * Permite actualizar algunos datos de cierto usuario en la DB o en una
     * lista
     *
     * @param ue datos del usuario a actualizar en la lista o en la DB
     * @return confirmación de la actualización, de lo contrario el respectivo
     * error
     */
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
