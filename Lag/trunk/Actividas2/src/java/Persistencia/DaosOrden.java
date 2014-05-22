/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Persistencia;
import Entidades.OrdenE;

import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author Usuario
 */
public class DaosOrden {

    public String crearOrden(Connection con, OrdenE oe){
        try{
            String sql = sqlCrearOrden();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, oe.getNumeroPedido());
            ps.setInt(2, oe.getBolsa_referencia());
            ps.setString(3, oe.getEstado());
            ps.setString(4, oe.getReferencia());
            ps.setDouble(5, oe.getCantidad());
            ps.setString(6, oe.getTipo_cantidad());
            ps.setBoolean(7, oe.isExtrusion());
            ps.setBoolean(8, oe.isImpresion());
            ps.setBoolean(9, oe.isSellado());
            ps.setDate(10, new Date(oe.getFecha_inicio().getTime()));
            ps.setDate(11, new Date(oe.getFecha_plazo().getTime()));
            ps.execute();
        }catch(Exception e){
            System.out.println("Error 01 DaosOrden: " + e.getMessage());
            return "1";
        }finally{
            try{
                con.close();
            }catch(Exception e){
                System.out.println("Error 02 DaosOrden: " + e.getMessage());
                return "2";
            }
        }

        return "";
    }

    public String actualizarOrden(Connection con, OrdenE oe){
        try{
            String sql = sqlActualizarOrden();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, oe.getNumeroPedido());
            ps.setInt(2, oe.getBolsa_referencia());
            ps.setDouble(3, oe.getTotal_kilos());
            ps.setDouble(4, oe.getKilos_reales());
            ps.setDouble(5, oe.getCantidad_final());
            ps.setString(6, oe.getEstado());
            ps.setString(7, oe.getReferencia());
            ps.setDouble(8, oe.getCantidad());
            ps.setString(9, oe.getTipo_cantidad());
            ps.setBoolean(10, oe.isExtrusion());
            ps.setBoolean(11, oe.isImpresion());
            ps.setBoolean(12, oe.isSellado());
            ps.setDate(13, new Date(oe.getFecha_inicio().getTime()));
            if(oe.getFecha_fin() == null){
        	ps.setNull(14, Types.DATE);
            }else{
        	ps.setDate(14, new Date(oe.getFecha_fin().getTime()));
            }
            ps.setInt(15, oe.getNumeroOrden());

            ps.executeUpdate();
        }catch(Exception e){
            System.out.println("Error 03 DaosOrden: " + e.getMessage());
            return "1";
        }finally{
            try{
                con.close();
            }catch(Exception e){
                System.out.println("Error 04 DaosOrden: " + e.getMessage());
                return "2";
            }
        }

        return "";
    }

    public ArrayList<OrdenE> buscarOrden(Connection con, String variable, String valor, boolean exactamente){
        ArrayList<OrdenE> aloe = new ArrayList<OrdenE>();

        try{
            String sql = sqlBuscarOrden(variable);
            PreparedStatement ps = con.prepareStatement(sql);

            if(exactamente){
                ps.setString(1, valor);
            }else{
                ps.setString(1, valor + "%");
            }

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                OrdenE oe = new OrdenE();
                oe.setNumeroOrden(rs.getInt("numeroOrden"));
                oe.setNumeroPedido(rs.getInt("numeroPedido"));
                oe.setBolsa_referencia(rs.getInt("bolsa_referencia"));
                oe.setTotal_kilos(rs.getDouble("total_kilos"));
                oe.setKilos_reales(rs.getDouble("kilos_reales"));
                oe.setCantidad_final(rs.getDouble("cantidad_final"));
                oe.setEstado(rs.getString("estado"));
                oe.setReferencia(rs.getString("referencia"));
                oe.setCantidad(rs.getDouble("cantidad"));
                oe.setTipo_cantidad(rs.getString("tipo_cantidad"));
                oe.setExtrusion(rs.getBoolean("extrusion"));
                oe.setImpresion(rs.getBoolean("impresion"));
                oe.setSellado(rs.getBoolean("sellado"));
                oe.setFecha_inicio(rs.getDate("fecha_inicio"));
                oe.setFecha_fin(rs.getDate("fecha_fin"));
                oe.setFecha_plazo(rs.getDate("fecha_plazo"));

                aloe.add(oe);
            }
        }catch(Exception e){
            System.out.println("Error 05 DaosOrden: " + e.getMessage());
            return aloe;
        }finally{
            try{
                con.close();
            }catch(Exception e){
                System.out.println("Error 06 DaosOrden: " + e.getMessage());
                return null;
            }
        }

        return aloe;
    }
    
    public String eliminarOrden(Connection con, OrdenE oe) {
        try {
            String sql = sqlEliminarOrden();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, oe.getNumeroOrden());

            ps.execute();
        } catch (Exception e) {
            System.out.println("Error 07 DaosOrden: " + e.getMessage());
            return "1";
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error 8 DaosOrden: " + e.getMessage());
                return "2";//"Error al conectarse a la base de datos"
            }
        }
        return "";
    }

    public static String sqlCrearOrden(){
        return "insert into ordenproduccion (numeroPedido, bolsa_referencia, estado, referencia, cantidad, tipo_cantidad, extrusion, impresion, sellado, fecha_inicio, fecha_plazo)" + 
                "values (?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?)";
    }

    public static String sqlActualizarOrden(){
        return "update ordenproduccion set numeroPedido = ?, bolsa_referencia = ?, total_kilos = ?, kilos_reales = ?, cantidad_final = ?, estado = ?, referencia = ?, cantidad = ?, tipo_cantidad = ?, extrusion = ?, impresion = ?, sellado = ?, fecha_inicio = ?, fecha_fin = ? where numeroOrden = ?";
    }

    public static String sqlBuscarOrden(String variable){
        return "select * from ordenproduccion where " + variable + " LIKE ?";
    }
    
    public static String sqlEliminarOrden(){
	return "delete from ordenproduccion where numeroOrden = ?";
    }

}
