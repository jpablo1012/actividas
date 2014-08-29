package Persistencia;

import Entidades.EmpleadoE;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DaosEmpleado {

    public String crearEmpleado(Connection con, EmpleadoE ee) {
        try {
            String sql = sqlCrearEmpleado();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, ee.getCargo());
            ps.setString(2, ee.getCedula());
            ps.setString(3, ee.getNombre());
            ps.setString(4, ee.getApellido());
            ps.execute();
        } catch (SQLException e) {
            System.out.println("Error 01 DaosEmpleado: " + e.getMessage());
            return "1"; // el empleado ya existe
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error 02 DaosEmpleado: " + e.getMessage());
                return "2";// Error al conectarse a la base de datos
            }
        }
        return "";
    }

    public ArrayList<EmpleadoE> buscarEmpleado(Connection con, String variable, String valor, boolean exactamente) {
        ArrayList<EmpleadoE> alee = new ArrayList<EmpleadoE>();
        try {
            String sql = sqlBuscarEmpleado(variable);
            PreparedStatement ps = con.prepareStatement(sql);
            if (exactamente) {
                ps.setString(1, valor);
            } else {
                ps.setString(1, valor + "%");
            }
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                EmpleadoE ee = new EmpleadoE();
                ee.setApellido(rs.getString("apellido"));
                ee.setCargo(rs.getString("cargo"));
                ee.setCedula(rs.getString("cedula"));
                ee.setNombre(rs.getString("nombre"));

                alee.add(ee);
            }
        } catch (SQLException e) {
            System.out.println("Error 03 DaosEmpleado: " + e.getMessage());
            return alee;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error 04 DaosUsuario: " + e.getMessage());
                return null;
            }
        }

        return alee;
    }
    
    private boolean stringIniciaCon(String original, String comparar){
        original = original.toLowerCase();
        comparar = comparar.toLowerCase();
        
        return original.startsWith(comparar);
    }

    public String actualizarEmpleado(Connection con, EmpleadoE ee) {
        try {
            String sql = sqlActualizarEmpleado();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, ee.getCargo());
            ps.setString(2, ee.getCedula());
            ps.setString(3, ee.getNombre());
            ps.setString(4, ee.getApellido());
            ps.setString(5, ee.getCedula());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error 05 DaosEmpleado: " + e.getMessage());
            return "1";
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error 06 DaosEmpleado: " + e.getMessage());
                return "2";// Error al conectarse a la base de datos
            }
        }
        return "";
    }

    public String eliminarEmpleado(Connection con, String cedula) {
        try {
            String sql = sqlEliminarEmpleado();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, cedula);

            ps.execute();
        } catch (SQLException e) {
            System.out.println("Error 07 DaosUsuario: " + e.getMessage());
            return "1";
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error 08 DaosUsuario: " + e.getMessage());
                return "2";// "Error al conectarse a la base de datos"
            }
        }
        return "";
    }

    public ArrayList<Object[]> informeSinFechas(Connection con) {
        ArrayList<Object[]> als = new ArrayList<Object[]>();
        try {
            String sql = sqlInformeSinFecha();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Object[] re = new Object[4];

                re[0] = rs.getString("nombre");
                re[1] = rs.getString("apellido");
                re[2] = rs.getString("empleado_cedula");
                re[3] = rs.getString("suma");

                als.add(re);
            }
        } catch (SQLException e) {
            System.out.println("Error 03 DaosEmpleado: " + e.getMessage());
            return als;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error 04 DaosUsuario: " + e.getMessage());
                return null;
            }
        }

        return als;
    }

    public ArrayList<Object[]> informeConFechas(Connection con, java.util.Date inicio, java.util.Date ult) {
        ArrayList<Object[]> als = new ArrayList<Object[]>();
        try {
            String sql = sqlInformeConFecha();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setDate(1, new Date(inicio.getTime()));
            ps.setDate(2, new Date(ult.getTime()));

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Object[] re = new Object[4];

                re[0] = rs.getString("nombre");
                re[1] = rs.getString("apellido");
                re[2] = rs.getString("empleado_cedula");
                re[3] = rs.getString("suma");

                als.add(re);
            }
        } catch (SQLException e) {
            System.out.println("Error 03 DaosEmpleado: " + e.getMessage());
            return als;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error 04 DaosUsuario: " + e.getMessage());
                return null;
            }
        }

        return als;
    }

    public static String sqlCrearEmpleado() {
        return "insert into empleado (cargo, cedula, nombre, apellido) values(?,?,?,?)";
    }

    public static String sqlCantidadEmpleados(String variable) {
        return "select count(*) from empleado where " + variable + " LIKE ?";
    }

    public static String sqlBuscarEmpleado(String s) {
        return "select * from empleado where " + s + " LIKE ?";
    }

    public static String sqlActualizarEmpleado() {
        return "update empleado set cargo=?, cedula=?, nombre=?, apellido=? where cedula=?";
    }

    public static String sqlEliminarEmpleado() {
        return "delete from empleado where cedula = ?";
    }

    public static String sqlInformeSinFecha() {
        String s = "SELECT empleado.nombre, empleado.apellido, empleado_cedula, SUM( bolsasSelladas ) AS  'suma' "
                + "FROM sellado, empleado "
                + "WHERE empleado_cedula = cedula "
                + "GROUP BY empleado_cedula "
                + "ORDER BY  `suma` DESC ";

        return s;
    }

    public static String sqlInformeConFecha() {
        return "SELECT empleado.nombre, empleado.apellido, empleado_cedula, SUM( bolsasSelladas ) AS  'suma' "
                + "FROM sellado, empleado "
                + "WHERE empleado_cedula = cedula "
                + "AND fechaFin "
                + "BETWEEN  ? "
                + "AND  ? "
                + "GROUP BY empleado_cedula "
                + "ORDER BY  `suma` DESC ";
    }
}
