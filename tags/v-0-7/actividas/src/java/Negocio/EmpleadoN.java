/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import java.sql.*;
import java.util.ArrayList;
import Entidades.List;
import Entidades.EmpleadoE;
import Persistencia.DaosEmpleado;
import Utilidades.Conexion;

/**
 *
 * @author Usuario
 */
public class EmpleadoN {

    public DaosEmpleado dao;
    public static List<EmpleadoE> head = null;

    public EmpleadoN() {
        dao = new DaosEmpleado();
        if (head == null) {
            head = getList();
        }
    }

    public final List<EmpleadoE> getList() {
        Conexion con = new Conexion();
        Connection c = con.getCon();
        List<EmpleadoE> aux = dao.fillList(c, "cedula", "", false);
        return aux;
    }

    public String crearEmpleado(EmpleadoE ee) {
        Conexion con = new Conexion();
        Connection c = con.getCon();
        String s = dao.crearEmpleado(c, ee);
        return s;
    }

    public String crearEmpleadoL(EmpleadoE ee){
        return dao.crearEmpleado(ee, head);
    }
    
    public ArrayList<EmpleadoE> buscarEmpleado(String variable, String valor, boolean exactamente) {
        Conexion con = new Conexion();
        Connection c = con.getCon();
        return dao.buscarEmpleado(c, variable, valor, exactamente);
    }

    public List<EmpleadoE> buscarEmpleado(String valor) {
        return dao.buscarEmpleado(head, valor);
    }

    public String actualizarEmpleado(EmpleadoE ee) {
        Conexion con = new Conexion();
        Connection c = con.getCon();
        return dao.actualizarEmpleado(c, ee);
    }
    
    public String actualizarEmpleadoL(EmpleadoE ee){
        return dao.actualizarEmpleado(ee, head);
    }

    public String eliminarEmpleado(String cedula) {
        Conexion con = new Conexion();
        return dao.eliminarEmpleado(con.getCon(), cedula);
    }
    
    public String eliminarEmpleadoL(String cedula){
        return dao.eliminarEmpleado(cedula, head);
    }

    public ArrayList<Object[]> informesSellador() {
        Conexion con = new Conexion();
        return dao.informeSinFechas(con.getCon());
    }

    public ArrayList<Object[]> informesSellador(java.util.Date inicio, java.util.Date fin) {
        Conexion con = new Conexion();
        Connection c = con.getCon();
        return dao.informeConFechas(c, inicio, fin);
    }
}
