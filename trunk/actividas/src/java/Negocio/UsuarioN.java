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
 * @author JPABLO - Juan Diego
 */
public final class UsuarioN {

    public DaosUsuario dao;
    public static List<UsuarioE> usersH = null;
    public boolean conBd = false;
    public Historial historial;

    /**
     * Constructor Clase UsuarioN: se la lista de usuarios 'usersH' esta vacia
     * la llena y si no se ha inicializado el historial lo inicializa
     */
    public UsuarioN() {

        dao = new DaosUsuario();
        if (usersH == null) {
            usersH = getList();
        }
        if (historial == null) {
            historial = new Historial();
        }
    }

    public static void imprimir(List<UsuarioE> a) {
        for (int i = 0; i < a.size(); i++) {
            UsuarioE ce = a.get(i);
            System.out.println("Tipo: " + ce.getTipo() + " Cédula: " + ce.getClienteCedula() + " ó " + ce.getEmpleadoCedula() + ", Nombre: " + ce.getNombre());
        }
        System.out.println("---------------");
    }

    /**
     *
     * @return aux Retorna la tabla usuario, En una lista
     */
    public List<UsuarioE> getList() {
        Conexion con = new Conexion();
        Connection c = con.getCon();
        List<UsuarioE> aux = dao.fillList(c, "nombre", "", false, true);
        return aux;
    }
    /**
     *
     * @param cedula Cedula del supuesto cliente a validar inicio de sesión
     * @return Retorna un UsuarioE, si el usuario existe, de lo contrario
     * retornara null
     */
    public UsuarioE validarIngreso(String cedula) {
        Conexion con = new Conexion();
        Connection c = con.getCon();
        System.out.println(cedula);
        UsuarioE u = dao.validarUsuario(c, cedula);
        return u;
    }

    /**
     * Crea un usuario, en la base de datos o en una lista; Todo segun el caso
     *
     * @param ue Usuario a crear
     * @return mensaje indicando si el cliente pudo ser creado o no
     */
    public String crearUsuario(UsuarioE ue) {
        String msg;
        if (conBd) {
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
            msg = dao.crearUsuario(c, ue);
        } else {
            msg = dao.crearUsuario(ue, usersH, historial);
        }
        return msg;
    }

    /**
     *
     * @param valor valor que buscaremos entre todos los usuarios
     * @return una lista de usuarios que coinciden con el valor a buscar
     */
    public List<UsuarioE> buscarUsuario(String valor) {
        return dao.buscarUsuario(usersH, valor);
    }

    /**
     *
     * @param variable
     * @param valor
     * @param exactamente
     * @return
     */
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
     *
     * @param ue datos del usuario a actualizar en la lista o en la DB
     * @return Confirmacion de la actualizaciob, de lo contrario el respectivo
     * error
     */
    public String actualizarUsuario(UsuarioE ue) {

        if (conBd) {
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
        } else {
            return dao.actualizarUsuario(ue, usersH, historial);
        }
    }

    public String eliminarUsuario(String id) {
        if (conBd) {
            Conexion con = new Conexion();
            return dao.eliminarUsuario(con.getCon(), id);
        } else {
            return dao.eliminarUsuario(id, usersH, historial);
        }
    }
}
