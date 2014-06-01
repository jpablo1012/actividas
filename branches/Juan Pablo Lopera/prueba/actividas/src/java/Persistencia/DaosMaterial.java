package Persistencia;

import Entidades.MaterialE;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DaosMaterial {

    public String crearMaterial(Connection con, MaterialE me) {
        try {
            String sql = sqlCrearMaterial();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, me.getNombre());
            ps.setDouble(2, me.getCantidad());

            ps.execute();
        } catch (Exception e) {
            System.out.println("Error 01 DaosMaterial: " + e.getMessage());
            return "1";
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error 02 DaosMaterial: " + e.getMessage());
                return "2";
            }
        }
        return "";
    }

    public ArrayList<MaterialE> buscarMaterial(Connection con, String variable, String valor, boolean exactamente) {
        ArrayList<MaterialE> alme = new ArrayList<MaterialE>();

        try {
            String sql = sqlBuscarMaterial(variable);
            PreparedStatement ps = con.prepareStatement(sql);

            if (exactamente) {
                ps.setString(1, valor);
            } else {
                ps.setString(1, valor + "%");
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                MaterialE me = new MaterialE();

                me.setCantidad(rs.getDouble("cantidad"));
                me.setNombre(rs.getString("nombre"));
                me.setReferencia(rs.getInt("referencia"));

                alme.add(me);
            }
        } catch (Exception e) {
            System.out.println("Error 03 DaosMaterial: " + e.getMessage());
            return alme;
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error 04 DaosMaterial: " + e.getMessage());
                return null;
            }
        }

        return alme;
    }

    public String actualizarMaterial(Connection con, MaterialE me) {
        try {
            String sql = sqlActualizarMaterial();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, me.getNombre());
            ps.setDouble(2, me.getCantidad());
            ps.setInt(3, me.getReferencia());
            ps.setInt(4, me.getReferencia());

            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error 05 DaosMaterial: " + e.getMessage());
            return "1";
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error 06 DaosMaterial: " + e.getMessage());
                return "2";
            }
        }

        return "";
    }

    public String eliminarMaterial(Connection con, MaterialE me) {
        try {
            String sql = sqlEliminarMaterial();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, me.getReferencia());
            ps.execute();
        } catch (Exception e) {
            System.out.println("Error 07 DaosMaterial: " + e.getMessage());
            return "1";
        } finally {
            try {
            } catch (Exception e) {
                System.out.println("Error 08 DaosMaterial: " + e.getMessage());
                return "2";
            }
        }
        return "";
    }

    public static String sqlCrearMaterial() {
        return "insert into material (nombre, cantidad) values(?,?)";
    }

    public static String sqlBuscarMaterial(String variable) {
        return "select * from material where " + variable + " LIKE ?";
    }

    public static String sqlActualizarMaterial() {
        return "update material set nombre=?, cantidad=?, referencia=? where referencia = ?";
    }

    public static String sqlEliminarMaterial() {
        return "delete from material where referencia = ?";
    }
}
