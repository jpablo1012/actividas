/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Entidades.BolsaE;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URLDecoder;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author JPABLO
 */
public class DaosBolsa {

    public String crearBolsa(Connection con, BolsaE be) {
        try {
            String sql = sqlCrearBolsa();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, be.getColor_codigo());
            ps.setInt(2, be.getMaterial_referencia());
            ps.setString(3, be.getCliente_cedula());
            ps.setString(4, be.getMedida());
            ps.setDouble(5, be.getAncho());
            ps.setDouble(6, be.getLargo());
            ps.setDouble(7, be.getCalibre());
            ps.setBoolean(8, be.isTratado());
            ps.setDouble(9, be.getTransArriba());
            ps.setDouble(10, be.getTransAbajo());
            ps.setDouble(11, be.getFuelle_fondo());
            ps.setDouble(12, be.getFuelle_superior());
            ps.setDouble(13, be.getFuelle_lateral());
            // try {
            System.out.println(be.getImagen().getAbsolutePath());
            FileInputStream file = new FileInputStream(be.getImagen());
            ps.setBinaryStream(14, file, (int) be.getImagen().length());
            // } catch (Exception e) {}
            ps.setDouble(15, be.getSolapa());
            ps.setString(16, be.getTroquel());

            ps.execute();

            file.close();
        } catch (Exception e) {
            System.out.println("Error 01 DaosBolsa: " + e.getMessage());
            return "1";
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error 02 DaosBolsa: " + e.getMessage());
                return "2";
            }
        }

        return "";
    }

    public ArrayList<BolsaE> buscarBolsa(Connection con, String variable, String valor, boolean exactamente) {
        return buscarBolsa(con, variable, valor, exactamente, false);
    }

    public ArrayList<BolsaE> buscarBolsa(Connection con, String variable, String valor, boolean exactamente, boolean imagen) {
        ArrayList<BolsaE> albe = new ArrayList<BolsaE>();
        try {
            String sql = sqlBuscarBolsa(variable);
            PreparedStatement ps = con.prepareStatement(sql);

            if (exactamente) {
                ps.setString(1, valor);
            } else {
                ps.setString(1, valor + "%");
            }

            ResultSet rs = ps.executeQuery();
            /*
             * String clase = "C://Users//" + System.getProperty("user.name") +
             * "//AppData//plastisoft"; File carpeta = new File(clase);
             * carpeta.mkdirs();
             */

            String clase = this.getClass().getResource("").toString();

            clase = clase.replaceAll("file:/", "");
            try {
                clase = URLDecoder.decode(clase, "UTF-8");
            } catch (Exception ex) {
            }
            clase = clase.replaceAll("WEB-INF//classes//Persistencia//", "bolsas");
            System.out.println(clase);
            File alt = new File(clase);
            alt.mkdirs();
            while (rs.next()) {
                BolsaE be = new BolsaE();
                be.setReferencia(rs.getInt("referencia"));
                be.setColor_codigo(rs.getInt("color_codigo"));
                be.setMaterial_referencia(rs.getInt("material_referencia"));
                be.setCliente_cedula(rs.getString("cliente_cedula"));
                be.setMedida(rs.getString("medida"));
                be.setAncho(rs.getDouble("ancho"));
                be.setLargo(rs.getDouble("largo"));
                be.setCalibre(rs.getDouble("calibre"));
                be.setTratado(rs.getBoolean("tratado"));
                be.setTransArriba(rs.getDouble("transparenciaArriba"));
                be.setTransAbajo(rs.getDouble("transparenciaAbajo"));
                be.setFuelle_fondo(rs.getDouble("fuelle_fondo"));
                be.setFuelle_superior(rs.getDouble("fuelle_superior"));
                be.setFuelle_lateral(rs.getDouble("fuelle_lateral"));
                be.setSolapa(rs.getDouble("solapa"));
                be.setTroquel(rs.getString("troquel"));

                if (imagen) {
                    File image = new File(clase, "bolsa" + be.getReferencia() + ".png");
                    FileOutputStream fos = new FileOutputStream(image);
                    byte[] buffer = new byte[1];
                    InputStream is = rs.getBinaryStream("imagen");
                    while (is.read(buffer) > 0) {
                        fos.write(buffer);
                    }
                    is.close();
                    if (image.length() == 0) {
                        image.delete();
                    } else {
                        be.setImagen(image);
                    }
                }

                albe.add(be);

            }

        } catch (Exception e) {
            System.out.println("Error 03 DaosBolsa: " + e.getMessage());
            return albe;
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error 04 DaosBolsa: " + e.getMessage());
                return null;
            }
        }

        return albe;
    }

    public String actualizarBolsa(Connection con, BolsaE be) {
        try {
            String sql = sqlActualizarBolsa();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, be.getColor_codigo());
            ps.setInt(2, be.getMaterial_referencia());
            ps.setString(3, be.getCliente_cedula());
            ps.setString(4, be.getMedida());
            ps.setDouble(5, be.getAncho());
            ps.setDouble(6, be.getLargo());
            ps.setDouble(7, be.getCalibre());
            ps.setBoolean(8, be.isTratado());
            ps.setDouble(9, be.getTransArriba());
            ps.setDouble(10, be.getTransAbajo());
            ps.setDouble(11, be.getFuelle_fondo());
            ps.setDouble(12, be.getFuelle_superior());
            ps.setDouble(13, be.getFuelle_lateral());

            FileInputStream file = new FileInputStream(be.getImagen());
            ps.setBinaryStream(14, file, (int) be.getImagen().length());

            ps.setDouble(15, be.getSolapa());
            ps.setString(16, be.getTroquel());

            ps.setInt(17, be.getReferencia());

            ps.executeUpdate();

            file.close();
        } catch (Exception e) {
            System.out.println("Error 05 DaosBolsa: " + e.getMessage());
            return "1";
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error 06 DaosBolsa: " + e.getMessage());
                return "2";
            }
        }

        return "";
    }

    public String eliminarBolsa(Connection con, BolsaE be) {
        try {
            String sql = sqlEliminarBolsa();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, be.getReferencia());

            ps.execute();
        } catch (Exception e) {
            System.out.println("Error 07 DaosBolsa: " + e.getMessage());
            return "1";
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error 08 DaosBolsa: " + e.getMessage());
                return "2";
            }
        }

        return "";
    }

    public static String sqlCrearBolsa() {
        return "insert into bolsa (color_codigo, material_referencia, cliente_cedula, medida, ancho, largo, calibre, tratado, transparenciaArriba, transparenciaAbajo, fuelle_fondo, fuelle_superior, fuelle_lateral, imagen, solapa, troquel) "
                + "value (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }

    public static String sqlBuscarBolsa(String variable) {
        return "select * from bolsa where " + variable + " LIKE ?";
    }

    public static String sqlActualizarBolsa() {
        return "update bolsa set color_codigo = ?, material_referencia = ?, cliente_cedula = ?, medida = ?, ancho = ?, largo = ?, calibre = ?, tratado = ?, transparenciaArriba = ?, transparenciaAbajo = ?, fuelle_fondo = ?, fuelle_superior = ?, fuelle_lateral = ?, imagen = ?, solapa = ?, troquel = ? where referencia = ?";
    }

    public static String sqlEliminarBolsa() {
        return "delete from bolsa where referencia = ?";
    }
}
