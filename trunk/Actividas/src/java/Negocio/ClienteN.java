package Negocio;

import java.sql.*;
import java.util.ArrayList;

import Entidades.ClienteE;
import Persistencia.DaosCliente;
import Utilidades.Conexion;

/**
 *
 * @author Usuario
 */
public class ClienteN {

    public DaosCliente dao;

    public ClienteN() {
        dao = new DaosCliente();
    }

    public String crearCliente(ClienteE ee) {
        Conexion con = new Conexion();
        Connection c = con.getCon();
        String s = dao.crearCliente(c, ee);
        return s;
    }

    public ArrayList<ClienteE> buscarCliente(String variable, String valor, boolean exactamente) {
        Conexion con = new Conexion();
        Connection c = con.getCon();
        return dao.buscarCliente(c, variable, valor, exactamente);
    }

    public String actualizarCliente(ClienteE ce) {
        Conexion con = new Conexion();
        return dao.actualizarCliente(con.getCon(), ce);
    }

    public String eliminarCliente(String cedula) {
        Conexion con = new Conexion();
        return dao.eliminarCliente(con.getCon(), cedula);
    }
}
