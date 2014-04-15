package Utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexion {

    public Connection con;

    public Connection getCon() {
        ConexionBD();
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }
    

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (ClassNotFoundException e) {
            System.out.println("Error 01 Conexion: " + e.getMessage());
        } catch (InstantiationException e) {
            System.out.println("Error 02 Conexion: " + e.getMessage());
        } catch (IllegalAccessException e) {
            System.out.print("Error 03 Conexion: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error 04 Conexion: " + e.getMessage());
        }
    }

    public void ConexionBD() {
        try {
           setCon(DriverManager.getConnection("jdbc:mysql://localhost:3306/plastiser","root","")); //base de datos local
           //setCon(DriverManager.getConnection("jdbc:mysql://www.db4free.net/plastiser", "actividas", "actividas")); //base de datos en la web
           
        } catch (SQLException e) {
            System.out.println("Error 05 Conexion: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error 06 Conexion: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Conexion connection = new Conexion();
        try {
            ResultSet r = connection.getCon().prepareStatement("select nombre from empleado").executeQuery();
            while (r.next()) {
                System.out.println(r.getString(1));
            }
        } catch (Exception e) {
            System.out.println("Error 07 Conexion: " + e.getMessage());
        }
    }
}
