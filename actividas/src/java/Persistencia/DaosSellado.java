/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Persistencia;
import Entidades.SelladoE;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author JPABLO
 */
public class DaosSellado {


    public String crearSellado(Connection con, SelladoE se){
        try{
            String sql = sqlCrearSellado();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, se.getNumeroOrden());
            ps.setInt(2, se.getBolsa_referencia());
            ps.setDate(3, new Date(se.getFechaInicio().getTime()));
            ps.setString(4, se.getEstado());

            ps.execute();
        }catch(Exception e){
            System.out.println("Error 01 DaosSellado: " + e.getMessage());
            return "1";
        }finally{
            try{
                con.close();
            }catch(Exception e){
                System.out.println("Error 02 DaosSellado: " + e.getMessage());
                return "2";
            }
        }

        return "";
    }

    public String actualizarSellado(Connection con, SelladoE se){
        try{
            String sql = sqlActualizarSellado();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, se.getNumeroOrden());
            ps.setString(2, se.getEmpleado_cedula());
            ps.setInt(3, se.getBolsa_referencia());
            
            if(se.getFechaFin() == null){
        	ps.setNull(4, Types.DATE);
            }else{
        	ps.setDate(4, new Date(se.getFechaFin().getTime()));
            }
            ps.setDate(5, new Date(se.getFechaInicio().getTime()));
            ps.setString(6, se.getNota());
            ps.setDouble(7, se.getBolsasSelladas());
            ps.setDouble(8, se.getRetal());
            ps.setString(9, se.getEstado());
            
            ps.setInt(10, se.getCodigo());

            ps.executeUpdate();
        }catch(Exception e){
            System.out.println("Error 03 DaosSellado: " + e.getMessage());
            return "1";
        }finally{
            try{
                con.close();
            }catch(Exception e){
                System.out.println("Error 04 DaosSellado: " + e.getMessage());
                return "2";
            }
        }

        return "";
    }

    public ArrayList<SelladoE> buscarSellado(Connection con, String variable, String valor, boolean exactamente){
        ArrayList<SelladoE> alse = new ArrayList<SelladoE>();

        try{
            String sql = sqlBuscarSellado(variable);
            PreparedStatement ps = con.prepareStatement(sql);

            if(exactamente){
                ps.setString(1, valor);
            }else{
                ps.setString(1, valor + "%");
            }

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                SelladoE se = new SelladoE();

                se.setCodigo(rs.getInt("codigo"));
                se.setNumeroOrden(rs.getInt("numeroOrden"));
                se.setEmpleado_cedula(rs.getString("empleado_cedula"));
                se.setBolsa_referencia(rs.getInt("bolsa_referencia"));
                se.setFechaFin(rs.getDate("fechaFin"));
                se.setFechaInicio(rs.getDate("fechaInicio"));
                se.setNota(rs.getString("nota"));
                se.setBolsasSelladas(rs.getDouble("bolsasSelladas"));
                se.setRetal(rs.getDouble("retal"));
                se.setEstado(rs.getString("estado"));

                alse.add(se);
            }
        }catch(Exception e){
                System.out.println("Error 05 DaosSellado: " + e.getMessage());
                return alse;
        }finally{
            try{
                con.close();
            }catch(Exception e){
                System.out.println("Error 06 DaosSellado: " + e.getMessage());
                return null;
            }
        }

        return alse;
    }

    public static String sqlCrearSellado(){
        return "insert into sellado (numeroOrden, bolsa_referencia, fechaInicio, estado)" +
                "value (?, ?, ?, ?)";
    }

    public static String sqlActualizarSellado(){
        return "update sellado set numeroOrden = ?, empleado_cedula = ?, bolsa_referencia = ?, fechaFin = ?, fechaInicio = ?, nota = ?, bolsasSelladas = ?, retal = ?, estado = ? where codigo = ?";
    }

    public static String sqlBuscarSellado(String variable){
        return "select * from sellado where " + variable + " LIKE ?";
    }
}
