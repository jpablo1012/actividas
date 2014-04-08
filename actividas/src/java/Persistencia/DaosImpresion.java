package Persistencia;

import Entidades.ImpresionE;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;

public class DaosImpresion {

    public String crearImpresion(Connection con, ImpresionE ie){
        try{
            String sql = sqlCrearImpresion();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, ie.getNumeroOrden());
            ps.setInt(2, ie.getBolsa_referencia());
            ps.setDate(3, new Date(ie.getFechaInicio().getTime()));
            ps.setString(4, ie.getEstado());
            ps.setString(5, ie.getImpresion());

            ps.execute();
        }catch(Exception e){
            System.out.println("Error 01 DaosImpresion: " + e.getMessage());
            return "1";
        }finally{
            try{
                con.close();
            }catch(Exception e){
                System.out.println("Error 02 DaosImpresion: " + e.getMessage());
                return "2";
            }
        }

        return "";
    }

    public String actualizarImpresion(Connection con, ImpresionE ie){
        try{
            String sql = sqlActualizarImpresion();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, ie.getNumeroOrden());
            ps.setString(2, ie.getEmpleado_cedula());
            ps.setInt(3, ie.getBolsa_referencia());
            ps.setDouble(4, ie.getDiferencia());
            ps.setString(5, ie.getImpresion());
            ps.setDouble(6, ie.getMontaje());
            ps.setDouble(7, ie.getPesoFinal());
            ps.setDouble(8, ie.getPesoInicial());
            ps.setInt(9, ie.getRodillo());
            ps.setString(10, ie.getNota());
            ps.setDate(11, new Date(ie.getFechaInicio().getTime()));
            
            if(ie.getFechaFin() == null){
        	ps.setNull(12, Types.DATE);
            }else{
        	ps.setDate(12, new Date(ie.getFechaFin().getTime()));
            }
            ps.setString(13, ie.getEstado());
            ps.setInt(14, ie.getCodigo());

            ps.executeUpdate();
        }catch(Exception e){
            System.out.println("Error 03 DaosImpresion: " + e.getMessage());
            return "1";
        }finally{
            try{

            }catch(Exception e){
                System.out.println("Error 04 DaosImpresion: " + e.getMessage());
                return "2";
            }
        }

        return "";
    }

    public ArrayList<ImpresionE> buscarImpresion(Connection con, String variable, String valor, boolean exactamente){
        ArrayList<ImpresionE> alie = new ArrayList<ImpresionE>();

        try{
            String sql = sqlBuscarImpresion(variable);
            PreparedStatement ps = con.prepareStatement(sql);

            if(exactamente){
                ps.setString(1, valor);
            }else{
                ps.setString(1, valor + "%");
            }

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                ImpresionE ie = new ImpresionE();

                ie.setCodigo(rs.getInt("codigo"));
                ie.setNumeroOrden(rs.getInt("numeroOrden"));
                ie.setEmpleado_cedula(rs.getString("empleado_cedula"));
                ie.setBolsa_referencia(rs.getInt("bolsa_referencia"));
                ie.setDiferencia(rs.getDouble("diferencia"));
                ie.setImpresion(rs.getString("impresion"));
                ie.setMontaje(rs.getDouble("montaje"));
                ie.setPesoFinal(rs.getDouble("pesoFinal"));
                ie.setPesoInicial(rs.getDouble("pesoInicial"));
                ie.setRodillo(rs.getInt("rodillo"));
                ie.setNota(rs.getString("nota"));
                ie.setFechaInicio(rs.getDate("fechaInicio"));
                ie.setFechaFin(rs.getDate("fechaFin"));
                ie.setEstado(rs.getString("estado"));

                alie.add(ie);
            }
        }catch(Exception e){
            System.out.println("Error 05 DaosImpresion: " + e.getMessage());
            return alie;
        }finally{
            try{

            }catch(Exception e){
                System.out.println("Error 06 DaosImpresion: " + e.getMessage());
                return null;
            }
        }

        return alie;
    }

    public static String sqlCrearImpresion(){
        return "insert into impresion (numeroOrden, bolsa_referencia, fechaInicio, estado, impresion)" +
                "value (?, ?, ?, ?, ?)";
    }

    public static String sqlActualizarImpresion(){
        return "update impresion set numeroOrden = ?, empleado_cedula = ?, bolsa_referencia = ?, diferencia = ?, impresion = ?, montaje = ?, pesoFinal = ?, pesoInicial = ?, rodillo = ?, nota = ?, fechaInicio = ?, fechaFin = ?, estado = ? where codigo = ?";
    }

    public static String sqlBuscarImpresion(String variable){
        return "select * from impresion where " + variable + " LIKE ?";
    }
}
