/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Persistencia;
import Entidades.ExtrusionE;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author Usuario
 */
public class DaosExtrusion {


    public String crearExtrusion(Connection con,ExtrusionE ee){
        try{
            String sql = sqlCrearExtrusion();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, ee.getNumeroOrden());
            ps.setInt(2, ee.getBolsa_referencia());
            ps.setDate(3, new Date(ee.getFechaInicio().getTime()));
            ps.setString(4, ee.getEstado());

            ps.execute();
        }catch(Exception e){
            System.out.println("Error 01 DaosExtrusion: " + e.getMessage());
            return "1";
        }finally{
            try{
                con.close();
            }catch(Exception e){
                System.out.println("Error 02 DaosExtrusion: " + e.getMessage());
            return "2";
            }
        }
        return "";
    }

    public String actualizarExtrusion(Connection con, ExtrusionE ee){
        try{
            String sql = sqlActualizarExtrusion();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, ee.getNumeroOrden());
            ps.setString(2, ee.getEmpleado_cedula());
            ps.setInt(3, ee.getBolsa_referencia());
            ps.setDouble(4, ee.getDesperdicio());
            ps.setDate(5, new Date(ee.getFechaInicio().getTime()));
            if(ee.getFechaFin() == null){
        	ps.setNull(6, Types.DATE);
            }else{
        	ps.setDate(6, new Date(ee.getFechaFin().getTime()));
            }
            ps.setString(7, ee.getNota());
            ps.setDouble(8, ee.getRollo());
            ps.setString(9, ee.getMaquina());
            ps.setString(10, ee.getEstado());
            ps.setInt(11, ee.getCodigo());
            ps.executeUpdate();

        }catch(Exception e){
            System.out.println("Error 03 DaosExtrusion: " + e.getMessage());
            return "1";
        }finally{
            try{
                con.close();
            }catch(Exception e){
                System.out.println("Error 04 DaosExtrusion: " + e.getMessage());
                return "2";
            }
        }
        return "";
    }

    public ArrayList<ExtrusionE> buscarExtrusion(Connection con, String variable, String valor, boolean exactamente){
        ArrayList<ExtrusionE> alee = new ArrayList<ExtrusionE>();

        try{
            String sql = sqlBuscarExtrusion(variable);
            PreparedStatement ps = con.prepareStatement(sql);

            if(exactamente){
                ps.setString(1, valor);
            }else{
                ps.setString(1, valor + "%");
            }

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                ExtrusionE ee = new ExtrusionE();

                ee.setCodigo(rs.getInt("codigo"));
                ee.setNumeroOrden(rs.getInt("numeroOrden"));
                ee.setEmpleado_cedula(rs.getString("empleado_cedula"));
                ee.setBolsa_referencia(rs.getInt("bolsa_referencia"));
                ee.setDesperdicio(rs.getDouble("desperdicio"));
                ee.setFechaInicio(rs.getDate("fechaInicio"));
                ee.setFechaFin(rs.getDate("fechaFin"));
                ee.setNota(rs.getString("nota"));
                ee.setRollo(rs.getDouble("rollo"));
                ee.setMaquina(rs.getString("maquina"));
                ee.setEstado(rs.getString("estado"));
                
                alee.add(ee);
            }
        }catch(Exception e){
            System.out.println("Error 05 DaosExtrusion: " + e.getMessage());
            return alee;
        }finally{
            try{
                con.close();
            }catch(Exception e){
                System.out.println("Error 06 DaosExtrusion: " + e.getMessage());
                return null;
            }
        }
        return alee;
    }

    public static String sqlCrearExtrusion(){
        return "insert into extrusion (numeroOrden, bolsa_referencia, fechaInicio, estado)" +
                "value(?, ?, ?, ?)";
    }

    public static String sqlActualizarExtrusion(){
        return "update extrusion set numeroOrden = ?, empleado_cedula = ?, bolsa_referencia = ?, desperdicio = ?, fechaInicio = ?, fechaFin = ?, nota = ?, rollo = ?, maquina = ?, estado = ? where codigo = ?";
    }

    public static String sqlBuscarExtrusion(String variable){
        return "select * from extrusion where " + variable + " LIKE ?";
    }
}
