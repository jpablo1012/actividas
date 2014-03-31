/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Entidades.ColorE;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author JPABLO
 */
public class DaosColor {

    public String crearColor(Connection con, ColorE ce) {
        try {
            String sql = sqlCrearColor();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setDouble(1, ce.getCantidad());
            ps.setString(2, ce.getNombre());

            ps.execute();
        } catch (Exception e) {
            System.out.println("Error 01 DaosColor: " + e.getMessage());
            return "1";
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error 02 DaosColor: " + e.getMessage());
                return "2";
            }
        }

        return "";
    }

    public ArrayList<ColorE> buscarColor(Connection con, String variable, String valor, boolean exactamente) {
        ArrayList<ColorE> alce = new ArrayList<ColorE>();
        try {
            String sql = sqlBuscarColor(variable);
            PreparedStatement ps = con.prepareStatement(sql);

            if (exactamente) {
                ps.setString(1, valor);
            } else {
                ps.setString(1, valor + "%");
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ColorE ce = new ColorE();
                ce.setCantidad(rs.getDouble("cantidad"));
                ce.setCodigo(rs.getInt("codigo"));
                ce.setNombre(rs.getString("nombre"));

                alce.add(ce);
            }
        } catch (Exception e) {
            System.out.println("Error 03 DaosColor: " + e.getMessage());
            return alce;
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error 04 DaosColor: " + e.getMessage());
                return null;
            }
        }

        return alce;
    }

    public String actualizarColor(Connection con, ColorE ce) {
        try {
            String sql = sqlActualizarColor();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setDouble(1, ce.getCantidad());
            ps.setInt(2, ce.getCodigo());
            ps.setString(3, ce.getNombre());
            ps.setInt(4, ce.getCodigo());

            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error 05 DaosColor: " + e.getMessage());
            return "1";
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error 06 DaosColor: " + e.getMessage());
                return "2";
            }
        }

        return "";
    }

    public String eliminarColor(Connection con, ColorE ce) {
        try {
            String sql = sqlEliminarColor();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, ce.getCodigo());

            ps.execute();
        } catch (Exception e) {
            System.out.println("Error 07 DaosColor: " + e.getMessage());
            return "1";
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error 08 DaosColor: " + e.getMessage());
                return "2";
            }
        }

        return "";
    }

    public static String sqlCrearColor() {
        return "insert into color(cantidad, nombre) values (?, ?)";
    }

    public static String sqlBuscarColor(String variable) {
        return "select * from color where " + variable + " LIKE ?";
    }

    public static String sqlActualizarColor() {
        return "update color set cantidad = ?, codigo = ?, nombre = ? where codigo = ?";
    }

    public static String sqlEliminarColor() {
        return "delete from color where codigo = ?";
    }
}
