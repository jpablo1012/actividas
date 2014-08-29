package Negocio;

import Entidades.ClienteE;
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

    /**
     * Contructor de la clase ClienteN
     */
    public ClienteN() {
        dao = new DaosCliente();
    }

    /**
     * Crea un cliente en el sistema
     *
     * @param ee Cliente a crear
     * @return "2": Error al conectarse a la base de datos, "1": El cliente ya
     * existe, "": Cliente creado
     */
    public String crearCliente(ClienteE ee) {
        Conexion con = new Conexion();
        Connection c = con.getCon();
        String s = dao.crearCliente(c, ee);
        return s;
    }

    /**
     * Busca clientes en la tabla "cliente" de la base de datos
     *
     * @param variable columna de la tabla cliente (cédula, dirección, email,
     * nombre, teléfono, apellido, ciudad) que va a usar para buscar
     * @param valor valor a comparar con la columna seleccionada
     * @param exactamente true si los datos de la columna tienen que ser iguales
     * a el valor a buscar false si los datos pueden empezar por el valor a
     * buscar
     * @return ArrayList de ClienteE o null si hubo un error al conectare a la
     * base de datos
     */
    public ArrayList<ClienteE> buscarCliente(String variable, String valor, boolean exactamente) {
        Conexion con = new Conexion();
        Connection c = con.getCon();
        return dao.buscarCliente(c, variable, valor, exactamente);
    }

    /**
     * Actualiza un cliente del sistema
     *
     * @param ce Cliente con datos actualizados
     * @return "2": Error al conectarse a la base de datos, "1": El cliente no
     * existe en el sistema, "": Cliente actualizado
     */
    public String actualizarCliente(ClienteE ce) {
        Conexion con = new Conexion();
        return dao.actualizarCliente(con.getCon(), ce);
    }

    /**
     * Elimina un cliente del sistema
     *
     * @param cedula Cédula del cliente a eliminar
     * @return "2": Error al conectarse a la base de datos, "1": El cliente no
     * se puede eliminar, "": Cliente eliminado
     */
    public String eliminarCliente(String cedula) {
        Conexion con = new Conexion();
        return dao.eliminarCliente(con.getCon(), cedula);
    }
}
