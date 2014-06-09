package Persistencia;

import Entidades.ClienteE;
import Entidades.List;
import Entidades.Nodo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DaosCliente {

    //Usando listas ligadas
    public String crearCliente(Connection con, ClienteE ee, List head) {
        try {
            head.add(ee);
        } catch (Exception e) {
            System.out.println("Error 01 DaosCliente: " + e.getMessage());
            return "1";//el cliente ya existe
        } finally {
            try {
                con.close();
            } catch (Exception a) {
                System.out.println("Error 02 DaosCliente: " + a.getMessage());
                return "2";//"Error al guardar en la lista"
            }
        }
        return "";
    }

    //Crear cliente usando Arraylist y conexion directa con la DB 
 /*  public String crearCliente(Connection con, ClienteE ee) {
     try {
     String sql = sqlCrearCliente();
     PreparedStatement ps = con.prepareStatement(sql);

     ps.setString(1, (String) ee.getCedula());
     ps.setString(2, (String) ee.getDireccion());
     ps.setString(3, (String) ee.getCorreo());
     ps.setString(4, (String) ee.getNombre());
     ps.setString(5, (String) ee.getApellido());
     ps.setString(6, (String) ee.getTelefono());
     ps.setString(7, (String) ee.getCiudad());

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

    //Usando listas ligadas
  /*  public String actualizarCliente(Connection con, ClienteE ce, List head) {
        try {
            Nodo aux = head.getFirst();
            for(;aux != null; aux = aux.getNext()){
                ClienteE cli = (ClienteE) aux.getDato();
                if(ce.getCedula().equals(cli.getCedula())){
                     aux.setDato(aux);
                }
            }
           
        } catch (Exception e) {
            System.out.println("Error 05 DaosCliente: " + e.getMessage());
            return "1";
        } 
         
        return "";
    }*/
    
    //Usando conexion directa a la DB
    public String actualizarCliente(Connection con, ClienteE ce) {
        try {
            String sql = sqlActualizarCliente();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, (String) ce.getCedula());
            ps.setString(2, (String) ce.getDireccion());
            ps.setString(3, (String) ce.getCorreo());
            ps.setString(4, (String) ce.getNombre());
            ps.setString(5, (String) ce.getApellido());
            ps.setString(6, (String) ce.getTelefono());
            ps.setString(7, (String) ce.getCiudad());
            ps.setString(8, (String) ce.getCedula());

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

    //Eliminar en lista ligada
   /* public String eliminarCliente(Connection con, ClienteE deletable, List head) {
     try {
     Nodo aux = head.getFirst();
     boolean find = false;
     for (; aux != null; aux = aux.getNext()) {
     if (aux.getDato() == deletable) {
     aux.getBack().setNext(aux.getNext());
     aux.getNext().setBack(aux.getBack());
     find = true;
     aux = null;
     break;
     }
     }
     } catch (Exception e) {
     System.out.println("Error 07 DaosCliente: " + e.getMessage());
     return "1";
     }
     return "";
     }*/
    
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
