package Negocio;

import java.sql.*;
import java.util.ArrayList;
import Entidades.List;
import Entidades.ClienteE;
import Persistencia.DaosCliente;
import Utilidades.Conexion;

/**
 *
 * @author Usuario
 */
public class ClienteN {

    public DaosCliente dao;
    public static List <ClienteE> head = null;
    
    
    public ClienteN() {
        dao = new DaosCliente();
        if(head==null){
            head=getList();
            System.out.println("lista llenada");
        }
    }
    
    public List<ClienteE> getList(){
        List<ClienteE> aux = new List();
        Conexion con = new Conexion();
        Connection c = con.getCon();
        aux = dao.fillList(c, "cedula", null, false);
        return aux;
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
