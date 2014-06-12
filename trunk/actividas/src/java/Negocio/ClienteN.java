package Negocio;

import Entidades.ClienteE;
import Entidades.List;
import Entidades.Registro;
import Persistencia.DaosCliente;
import Utilidades.Conexion;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Juan Pablo Lopera Estrada
 */
public class ClienteN {

    public DaosCliente dao;
    public static List<ClienteE> head = null;
    public Historial historial;
    public boolean conBD = false;

    public ClienteN() {
        dao = new DaosCliente();
        if (head == null) {
            head = getList();
            System.out.println("lista llenada");
        }
        
        if(historial == null)
            historial = new Historial();
    }

    public final List<ClienteE> getList() {
        Conexion con = new Conexion();
        Connection c = con.getCon();
        List<ClienteE> aux = dao.fillList(c, "cedula", "", false);
        return aux;
    }
    
     public String crearCliente(ClienteE ee) {
        if(conBD){
            Conexion con = new Conexion();
            Connection c = con.getCon();
            String s = dao.crearCliente(c, ee);
            return s;  
        }else{
            return dao.crearCliente(ee, head, historial);
        }
        
    }

    public ArrayList<ClienteE> buscarCliente(String variable, String valor, boolean exactamente) {
        Conexion con = new Conexion();
        Connection c = con.getCon();
        return dao.buscarCliente(c, variable, valor, exactamente);
    }
    
    public List<ClienteE> buscarCliente(String valor){
        return dao.buscarCliente(head ,valor);
    }

    //Usando conexion directa a la DB
   public String actualizarCliente(ClienteE ce) {
        if(conBD){
            Conexion con = new Conexion();
            return dao.actualizarCliente(con.getCon(), ce);
        }else{
            return dao.actualizarCliente(ce, head, historial);
        }
    }
    
    //Usando conexion directa a la DB
public String eliminarCliente(String cedula) {
        if(conBD){
            Conexion con = new Conexion();
            return dao.eliminarCliente(con.getCon(), cedula);
        }else{
            return dao.eliminarCliente(cedula, head, historial);
        }
    }

    public static void main(String[] args){
        ClienteN cn = new ClienteN();
        ClienteN cn2 = new ClienteN();
        ClienteE ce = new ClienteE("96457854", "carrera", "jpablo", "Juan Pablo", "Lopera Estrada", "2691450", "Medellin");
        cn.crearCliente(ce);
        imprimir();
        ClienteE ce2 = new ClienteE("9645785484", "carrera", "jpablo", "Juan Pablo", "Lopera Estrada", "2691450", "Medellin");
        cn2.crearCliente(ce2);
        imprimir();
        System.out.println("---------------------");
        List<Registro> cambios = cn.historial.getCambios();
        for(int i = 0; i < cambios.size(); i++){
            Registro r = cambios.get(i);
            System.out.println(r.getAccion());
        }
    }
    
    public static void imprimir(){
        for(int i = 0; i < ClienteN.head.size(); i++){
            ClienteE ce= ClienteN.head.get(i);
            System.out.println("Cédula: " + ce.getCedula() + ", Nombre: " + ce.getNombre());
        }
        System.out.println("---------------");
    }

}
