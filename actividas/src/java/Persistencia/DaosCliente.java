package Persistencia;

import Entidades.ClienteE;

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
