package Persistencia;

import Entidades.Accion;
import Entidades.ClienteE;
import Entidades.List;
import Entidades.Registro;
import Negocio.Historial;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DaosCliente {
    
    public String crearCliente(Connection con, ClienteE ee) {
        try {
            String sql = sqlCrearCliente();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, ee.getCedula());
            ps.setString(2, ee.getDireccion());
            ps.setString(3, ee.getCorreo());
            ps.setString(4, ee.getNombre());
            ps.setString(5, ee.getApellido());
            ps.setString(6, ee.getTelefono());
            ps.setString(7, ee.getCiudad());

            ps.execute();
        } catch (Exception e) {
            System.out.println("Error 01 DaosCliente: " + e.getMessage());
            return "1";//el cliente ya existe
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error 02 DaosCliente: " + e.getMessage());
                return "2";//"Error al conectarse a la base de datos"
            }
        }
        return "";
    }

    /**
     * Añade un cliente a la lista, comprueba que la cedula sea unica
     * @param ee Cliente a añadir a la lista
     * @param head Lista a la cual se le va a añadir el cliente
     * @param cola Registro de la acciones
     * @return 
     */
    public String crearCliente(ClienteE ee, List<ClienteE> head, Historial cola) {
        boolean unico = true;
        for(int i = 0; i < head.size(); i++){
            ClienteE ce = head.get(i);
            if(ce.getCedula().equals(ee.getCedula())){
                unico = false;
                break;
            }
        }
        
        if(unico){
            ClienteE ce = new ClienteE(ee.getCedula(), ee.getDireccion(), ee.getCorreo(), ee.getNombre(), ee.getApellido(), ee.getTelefono(), ee.getCiudad());
            head.add(ce);
            Registro<ClienteE> recl = new Registro<ClienteE>(ce, Accion.CREAR_CLIENTE);
            cola.añadir(recl);
            return "";
        }else{
            return "1";//El cliente ya existe
        }
    }
    
    /**
     * Retorna toda la tabla "cliente" de la base de datos en una lista de ClienteE
     * @param con Conexión a la base de datos
     * @param variable columna de la tabla cliente 
     * (cedula, direccion, email, nombre, telefono, apellido, ciudad) 
     * que va a usar para buscar
     * @param valor valor a comparar con la columna seleccionada
     * @param exactamente true si los datos de la columna tienen que ser iguales
     * a el valor a buscar false si los datos pueden empezar por el valor a buscar
     * @return Lista de ClienteE de toda la tabla
     */
    public List<ClienteE> fillList(Connection con, String variable, String valor, boolean exactamente) {
        List<ClienteE> alce = new List();
        try {
            String sql = sqlBuscarCliente(variable);
            PreparedStatement ps = con.prepareStatement(sql);

            if (exactamente) {
                ps.setString(1, valor);
            } else {
                ps.setString(1, valor + "%");
            }
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ClienteE ce = new ClienteE();
                ce.setApellido(rs.getString("apellido"));
                ce.setCedula(rs.getString("cedula"));
                ce.setCorreo(rs.getString("email"));
                ce.setDireccion(rs.getString("direccion"));
                ce.setNombre(rs.getString("nombre"));
                ce.setTelefono(rs.getString("telefono"));
                ce.setCiudad(rs.getString("ciudad"));

                alce.addLast(ce);
            }
        } catch (Exception e) {
            System.out.println("Error 03 DaosEmpleado: " + e.getMessage());
            return alce;
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error 04 DaosUsuario: " + e.getMessage());
                return null;
            }
        }
        return alce;
    }

    /**
     * Busca clientes en la tabla cliente que cumplan con las condiciones dadas
     * @param con Conexión a la base de datos
     * @param variable columna de la tabla cliente 
     * (cedula, direccion, email, nombre, telefono, apellido, ciudad) 
     * que va a usar para buscar
     * @param valor valor a comparar con la columna seleccionada
     * @param exactamente true si los datos de la columna tienen que ser iguales
     * a el valor a buscar false si los datos pueden empezar por el valor a buscar
     * @return ArrayList de UsuarioE o null si hubo un error al conectare a la
     * base de datos
     */
    public ArrayList<ClienteE> buscarCliente(Connection con, String variable, String valor, boolean exactamente) {
        ArrayList<ClienteE> alce = new ArrayList<ClienteE>();
        try {
            String sql = sqlBuscarCliente(variable);
            PreparedStatement ps = con.prepareStatement(sql);

            if (exactamente) {
                ps.setString(1, valor);
            } else {
                ps.setString(1, valor + "%");
            }
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ClienteE ce = new ClienteE();
                ce.setApellido(rs.getString("apellido"));
                ce.setCedula(rs.getString("cedula"));
                ce.setCorreo(rs.getString("email"));
                ce.setDireccion(rs.getString("direccion"));
                ce.setNombre(rs.getString("nombre"));
                ce.setTelefono(rs.getString("telefono"));
                ce.setCiudad(rs.getString("ciudad"));

                alce.add(ce);
            }
        } catch (Exception e) {
            System.out.println("Error 03 DaosEmpleado: " + e.getMessage());
            return alce;
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error 04 DaosUsuario: " + e.getMessage());
                return null;
            }
        }
        return alce;
    }
    
    /**
     * Busca clientes en la lista que cumpla con la condición de que cualquier dato
     * empiece por el valor dado
     * @param lista Lista de clientes
     * @param valor Valor por el cual se buscara
     * @return Lista con los clientes que cumplen esa condición
     */
    public List<ClienteE> buscarCliente(List<ClienteE> lista, String valor){
        List<ClienteE> resultado = new List<ClienteE>();
        for(int i = 0; i < lista.size(); i++){
            ClienteE ce = lista.get(i);
            if(stringIniciaCon(ce.getApellido(), valor) ||
                    stringIniciaCon(ce.getCedula(), valor) ||
                    stringIniciaCon(ce.getCiudad(), valor) ||
                    stringIniciaCon(ce.getCorreo(), valor) ||
                    stringIniciaCon(ce.getDireccion(), valor) ||
                    stringIniciaCon(ce.getNombre(), valor) ||
                    stringIniciaCon(ce.getTelefono(), valor)){
                ClienteE encontrado = new ClienteE(ce.getCedula(), ce.getDireccion(), ce.getCorreo(), ce.getNombre(), ce.getApellido(), ce.getTelefono(), ce.getCiudad());
                resultado.add(encontrado);
            }
        }
        
        return resultado;
    }
    
    /**
     * 
     * @param cliente
     * @param lista
     * @param registro
     * @return 
     */
    public String actualizarCliente(ClienteE cliente, List<ClienteE> lista, Historial registro){
        ClienteE reemplazo = new ClienteE(cliente.getCedula(), cliente.getDireccion(), cliente.getCorreo(), cliente.getNombre(), cliente.getApellido(), cliente.getTelefono(), cliente.getCiudad());
        for(int i = 0; i < lista.size(); i++){
            ClienteE ce = lista.get(i);
            if(ce.getCedula().equals(reemplazo.getCedula())){
                lista.set(reemplazo, i);
                Registro<ClienteE> re = new Registro<ClienteE>(reemplazo, Accion.ACTUALIZAR_CLIENTE);
                registro.añadir(re);
                return "";
            }
        }
        
        return "1";
    }
    
    public String eliminarCliente(String cedula, List<ClienteE> lista, Historial registro){
        for(int i = 0; i < lista.size(); i++){
            ClienteE ce = lista.get(i);
            if(ce.getCedula().equals(cedula)){
                lista.remove(i);
                ClienteE eliminado = new ClienteE(ce.getCedula(), ce.getDireccion(), ce.getCorreo(), ce.getNombre(), ce.getApellido(), ce.getTelefono(), ce.getCiudad());
                Registro<ClienteE> re = new Registro<ClienteE>(eliminado, Accion.ELIMINAR_CLIENTE);
                registro.añadir(re);
                return "";
            }
        }
        
        return "1";
    }
    
    private boolean stringIniciaCon(String original, String comparar){
        original = original.toLowerCase();
        comparar = comparar.toLowerCase();
        
        return original.startsWith(comparar);
    }
    
    //Usando conexion directa a la DB
    public String actualizarCliente(Connection con, ClienteE ce) {
        try {
            String sql = sqlActualizarCliente();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, ce.getCedula());
            ps.setString(2, ce.getDireccion());
            ps.setString(3, ce.getCorreo());
            ps.setString(4, ce.getNombre());
            ps.setString(5, ce.getApellido());
            ps.setString(6, ce.getTelefono());
            ps.setString(7, ce.getCiudad());
            ps.setString(8, ce.getCedula());
            

            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error 05 DaosCliente: " + e.getMessage());
            return "1";
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error 06 DaosCliente: " + e.getMessage());
                return "2";
            }
        }
        return "";
    }
    
    //Eliminar usando Arraylist
    public String eliminarCliente(Connection con, String cedula) {
        try {
            String sql = sqlEliminarCliente();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, cedula);

            ps.execute();
        } catch (Exception e) {
            System.out.println("Error 07 DaosCliente: " + e.getMessage());
            return "1";
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error 08 DaosCliente: " + e.getMessage());
                return "2";//"Error al conectarse a la base de datos"
            }
        }
        return "";
    }

    public static String sqlCrearCliente() {
        return "insert into cliente (cedula, direccion, email, nombre, apellido, telefono, ciudad) values(?,?,?,?,?,?,?)";
    }

    public static String sqlCantidadClientes(String variable) {
        return "select count(*) from cliente where " + variable + " LIKE ?";
    }

    public static String sqlBuscarCliente(String s) {
        return "select * from cliente where " + s + " LIKE ?";
    }

    public static String sqlActualizarCliente() {
        return "update cliente set cedula=?, direccion=?, email=?, nombre=?, apellido=?, telefono=?, ciudad=? where cedula=?";
    }

    public static String sqlEliminarCliente() {
        return "delete from cliente where cedula = ?";
    }
}
