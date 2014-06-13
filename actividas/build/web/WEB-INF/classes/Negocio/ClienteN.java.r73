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
 * @since 2013
 */
public class ClienteN {

    public DaosCliente dao;
    public static List<ClienteE> head = null;
    public Historial historial;
    public boolean conBD = false;

    /**
     * Contructor de la clase ClienteN
     */
    public ClienteN() {
        dao = new DaosCliente();
        if (head == null) {
            head = getList();
            System.out.println("lista llenada");
        }
        
        if(historial == null)
            historial = new Historial();
    }

    /**
     * Retorna toda la tabla cliente en una lista
     * @return 
     */
    public final List<ClienteE> getList() {
        Conexion con = new Conexion();
        Connection c = con.getCon();
        List<ClienteE> aux = dao.fillList(c, "cedula", "", false);
        return aux;
    }
    
    /**
     * Crea un cliente en el sistema
     * @param ee Cliente a crear
     * @return "2": Error al conectarse a la base de datos, "1": El cliente ya
     * existe, "": Cliente creado
     */
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

     /**
      * Busca clientes en la tabla "cliente" de la base de datos
      * @param variable columna de la tabla cliente 
     * (cedula, direccion, email, nombre, telefono, apellido, ciudad) 
     * que va a usar para buscar
      * @param valor valor a comparar con la columna seleccionada
      * @param exactamente true si los datos de la columna tienen que ser iguales
      * a el valor a buscar false si los datos pueden empezar por el valor a buscar
      * @return ArrayList de ClienteE o null si hubo un error al conectare a la
     * base de datos
      */
    public ArrayList<ClienteE> buscarCliente(String variable, String valor, boolean exactamente) {
        Conexion con = new Conexion();
        Connection c = con.getCon();
        return dao.buscarCliente(c, variable, valor, exactamente);
    }
    
    /**
     * Busca clientes en la lista clientes
     * @param valor valor a comparar con la columna seleccionada
     * @return List de ClienteE
     */
    public List<ClienteE> buscarCliente(String valor){
        return dao.buscarCliente(head ,valor);
    }

    /**
     * Actualiza un cliente del sistema
     * @param ce Cliente con datos actualizados
     * @return "2": Error al conectarse a la base de datos, "1": El cliente no 
     * existe en el sistema, "": Cliente actualizado
     */
   public String actualizarCliente(ClienteE ce) {
        if(conBD){
            Conexion con = new Conexion();
            return dao.actualizarCliente(con.getCon(), ce);
        }else{
            return dao.actualizarCliente(ce, head, historial);
        }
    }
    
    /**
     * Elimina un cliente del sistema
     * @param cedula Cédula del cliente a eliminar
     * @return "2": Error al conectarse a la base de datos, "1": El cliente no 
     * se puede eliminar, "": Cliente eliminado
     */
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
