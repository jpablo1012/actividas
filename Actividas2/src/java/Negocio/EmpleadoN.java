/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import java.sql.*;
import java.util.ArrayList;

import Entidades.EmpleadoE;
import Persistencia.DaosEmpleado;
import Utilidades.Conexion;

/**
 *
 * @author Usuario
 */
public class EmpleadoN {

    public DaosEmpleado dao;

    public EmpleadoN() {
        dao = new DaosEmpleado();
    }

    public String crearEmpleado(EmpleadoE ee) {
        Conexion con = new Conexion();
        Connection c = con.getCon();
        String s = dao.crearEmpleado(c, ee);
        return s;
    }

    public ArrayList<EmpleadoE> buscarEmpleado(String variable, String valor, boolean exactamente) {
        Conexion con = new Conexion();
        Connection c = con.getCon();
        
        if(buscarEmpleadoCont(variable)){
           return dao.buscarEmpleado(c, variable, valor, exactamente);
        }
        
        return null;
    }
    
    private boolean buscarEmpleadoCont(String variable){
         String[] variables = {"cedula", "nombre", "apellido", "cargo"};
        
        for(String s: variables){
            if(variable.equals(s)){
                return true;
            }
        }
        
        return false;
    }

    public String actualizarEmpleado(EmpleadoE ee) {
        Conexion con = new Conexion();
        Connection c = con.getCon();
        return dao.actualizarEmpleado(c, ee);
    }

    public String eliminarEmpleado(String cedula) {
        Conexion con = new Conexion();
        return dao.eliminarEmpleado(con.getCon(), cedula);
    }
    
    public ArrayList<Object[]> informesSellador(){
	Conexion con = new Conexion();
        return dao.informeSinFechas(con.getCon()); 
    }
    
    public ArrayList<Object[]> informesSellador(java.util.Date inicio, java.util.Date fin){
	Conexion con = new Conexion();
        Connection c = con.getCon();
        return dao.informeConFechas(c, inicio, fin);
    }
}
