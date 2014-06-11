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
    public static List<Registro<ClienteE>> cola = null;
    public boolean conBD = false;

    public ClienteN() {
        dao = new DaosCliente();
        if (head == null) {
            head = getList();
            System.out.println("lista llenada");
        }
        
        if(cola == null)
            cola = new List<Registro<ClienteE>>();
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
            return dao.crearCliente(ee, head, cola);
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
            return dao.actualizarCliente(ce, head, cola);
        }
    }
    
    //Usando conexion directa a la DB
public String eliminarCliente(String cedula) {
        if(conBD){
            Conexion con = new Conexion();
            return dao.eliminarCliente(con.getCon(), cedula);
        }else{
            return dao.eliminarCliente(cedula, head, cola);
        }
    }

    public static void main(String[] args){
        ClienteN cn = new ClienteN();
        ClienteE ce = new ClienteE("96457854", "carrera", "jpablo", "Juan Pablo", "Lopera Estrada", "2691450", "Medellin");
        cn.crearCliente(ce);
        imprimir();
        ce.setNombre("juan cambiado");
        ce.setCedula("123456");
        cn.actualizarCliente(ce);
        imprimir();
        ce.setNombre("cambiado cambiado");
        imprimir();
        cn.eliminarCliente(ce.getCedula());
        imprimir();
    }
    
    public static void imprimir(){
        for(int i = 0; i < ClienteN.head.size(); i++){
            ClienteE ce= ClienteN.head.get(i);
            System.out.println("Cédula: " + ce.getCedula() + ", Nombre: " + ce.getNombre());
        }
        System.out.println("---------------");
    }

}
