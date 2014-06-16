package Persistencia;

import Entidades.EmpleadoE;
import Entidades.Accion;
import Entidades.List;
import Entidades.Registro;
import Negocio.Historial;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        } catch (Exception e) {
            System.out.println("Error 01 DaosEmpleado: " + e.getMessage());
            return "1"; // el empleado ya existe
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error 02 DaosEmpleado: " + e.getMessage());
                return "2";// Error al conectarse a la base de datos
            }
        }
        return "";
    }

    public String crearEmpleado(EmpleadoE ea, List<EmpleadoE> head) {
        boolean unico = true;
        for (int i = 0; i < head.size(); i++) {
            EmpleadoE ee = head.get(i);
            if (ee.getCedula().equals(ea.getCedula())) {
                unico = false;
                break;
            }
        }

        if (unico) {
            EmpleadoE ee = new EmpleadoE(ea.getCedula(), ea.getNombre(), ea.getApellido(), ea.getCargo());
            head.add(ee);
            Registro<EmpleadoE> recl = new Registro<EmpleadoE>(ee, Accion.CREAR_EMPLEADO);
            Historial.añadir(recl);
            return "";
        } else {
            return "1";
        }
    }

    public List<EmpleadoE> fillList(Connection con, String variable, String valor, boolean exactamente) {
        List<EmpleadoE> alee = new List();
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

                alee.addLast(ee);
            }
        } catch (Exception e) {
            System.out.println("Error 03 DaosEmpleado: " + e.getMessage());
            return alee;
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error 04 DaosUsuario: " + e.getMessage());
                return null;
            }
        }
        return alee;
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
        } catch (Exception e) {
            System.out.println("Error 03 DaosEmpleado: " + e.getMessage());
            return alee;
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error 04 DaosUsuario: " + e.getMessage());
                return null;
            }
        }

        return alee;
    }

    public List<EmpleadoE> buscarEmpleado(List<EmpleadoE> lista, String valor) {
        List<EmpleadoE> resultado = new List<EmpleadoE>();
        for (int i = 0; i < lista.size(); i++) {
            EmpleadoE ee = lista.get(i);
            if (stringIniciaCon(ee.getApellido(), valor) ||
                    stringIniciaCon(ee.getCargo(), valor) ||
                    stringIniciaCon(ee.getCedula(), valor) ||
                    stringIniciaCon(ee.getNombre(), valor)) {
                EmpleadoE encontrado = new EmpleadoE(ee.getCedula(), ee.getNombre(), ee.getApellido(), ee.getCargo());
                resultado.add(encontrado);
            }
        }
        return resultado;
    }

    public String actualizarEmpleado(EmpleadoE empleado, List<EmpleadoE> lista) {
        EmpleadoE reemplazo = new EmpleadoE(empleado.getCedula(), empleado.getNombre(), empleado.getApellido(), empleado.getCargo());
        for (int i = 0; i < lista.size(); i++) {
            EmpleadoE ee = lista.get(i);
            if (ee.getCedula().equals(reemplazo.getCedula())) {
                lista.set(reemplazo, i);
                Registro<EmpleadoE> re = new Registro<EmpleadoE>(reemplazo, Accion.ACTUALIZAR_EMPLEADO);
                Historial.añadir(re);
                return "";
            }
        }

        return "1";
    }

    public String eliminarEmpleado(String cedula, List<EmpleadoE> lista) {
        for (int i = 0; i < lista.size(); i++){
            EmpleadoE ee = lista.get(i);
            if(ee.getCedula().equals(cedula)){
                lista.remove(i);
                EmpleadoE eliminado = new EmpleadoE(ee.getCedula(), ee.getNombre(), ee.getApellido(), ee.getCargo());
                Registro<EmpleadoE> re = new Registro<EmpleadoE>(eliminado, Accion.ELIMINAR_EMPLEADO);
                Historial.añadir(re);
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
        } catch (Exception e) {
            System.out.println("Error 05 DaosEmpleado: " + e.getMessage());
            return "1";
        } finally {
            try {
                con.close();
            } catch (Exception e) {
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
        } catch (Exception e) {
            System.out.println("Error 07 DaosUsuario: " + e.getMessage());
            return "1";
        } finally {
            try {
                con.close();
            } catch (Exception e) {
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
        } catch (Exception e) {
            System.out.println("Error 03 DaosEmpleado: " + e.getMessage());
            return als;
        } finally {
            try {
                con.close();
            } catch (Exception e) {
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
        } catch (Exception e) {
            System.out.println("Error 03 DaosEmpleado: " + e.getMessage());
            return als;
        } finally {
            try {
                con.close();
            } catch (Exception e) {
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
