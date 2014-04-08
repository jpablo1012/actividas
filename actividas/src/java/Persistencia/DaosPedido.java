package Persistencia;

import Entidades.PedidoE;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DaosPedido {

    public String crearPedido(Connection con, PedidoE pe) {
        try {
            String sql = sqlCrearPedido();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, pe.getCliente_cedula());
            ps.setInt(2, pe.getBolsa_referencia());
            ps.setDouble(3, pe.getPrecio());
            ps.setDate(4, new Date(pe.getFecha_entrega().getTime()));
            ps.setString(5, pe.getFactura());
            ps.setString(6, pe.getTipo_venta());
            ps.setDate(7, new Date(pe.getFecha_creacion().getTime()));
            ps.setString(8, pe.getFactura_despacho());        
            ps.setString(9, pe.getEstado());
            ps.setDouble(10, pe.getFactor());
            ps.setDouble(11, pe.getPeso_millar());
            ps.setDouble(12, pe.getBase());
            ps.setString(13, pe.getReferencia());
            ps.setDouble(14, pe.getCantidad());
            ps.setString(15, pe.getTipo_cantidad());
            ps.setBoolean(16, pe.isExtrusion());
            ps.setBoolean(17, pe.isImpresion());
            ps.setBoolean(18, pe.isSellado());

            ps.execute();

        } catch (Exception e) {
            System.out.println("Error 01 DaosPedido: " + e.getMessage());
            return "1";
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error 02 DaosPedido: " + e.getMessage());
                return "2";
            }
        }
        return "";
    }

    public String actualizarPedido(Connection con, PedidoE pe) {
        try {
            String sql = sqlActualizarPedido();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, pe.getCliente_cedula());
            ps.setInt(2, pe.getBolsa_referencia());
            ps.setDouble(3, pe.getPrecio());
            ps.setDate(4, new Date(pe.getFecha_entrega().getTime()));
            ps.setString(5, pe.getFactura());
            ps.setString(6, pe.getTipo_venta());
            ps.setDate(7, new Date(pe.getFecha_creacion().getTime()));
            ps.setString(8, pe.getFactura_despacho());
            if(pe.getFecha_despacho() == null){
            	ps.setNull(9, java.sql.Types.DATE);
            }else{
            	ps.setDate(9, new Date(pe.getFecha_despacho().getTime()));
            }
            
            ps.setString(10, pe.getEstado());
            ps.setDouble(11, pe.getFactor());
            ps.setDouble(12, pe.getPeso_millar());
            ps.setDouble(13, pe.getBase());
            ps.setString(14, pe.getReferencia());
            ps.setDouble(15, pe.getCantidad());
            ps.setString(16, pe.getTipo_cantidad());
            ps.setBoolean(17, pe.isExtrusion());
            ps.setBoolean(18, pe.isImpresion());
            ps.setBoolean(19, pe.isSellado());
            ps.setInt(20, pe.getNumeroPedido());


            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error 03 DaosPedido: " + e.getMessage());
            return "1";
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error 04 DaosPedido: " + e.getMessage());
                return "2";
            }
        }
        return "";
    }

    public ArrayList<PedidoE> buscarPedido(Connection con, String variable, String valor, boolean exactamente) {
        ArrayList<PedidoE> alpe = new ArrayList<PedidoE>();

        try {
            String sql = sqlBuscarPedido(variable);
            PreparedStatement ps = con.prepareStatement(sql);

            if (exactamente) {
                ps.setString(1, valor);
            } else {
                ps.setString(1, valor + "%");
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                PedidoE pe = new PedidoE();

                pe.setNumeroPedido(rs.getInt("numeroPedido"));
                pe.setCliente_cedula(rs.getString("cliente_cedula"));
                pe.setBolsa_referencia(rs.getInt("bolsa_referencia"));
                pe.setPrecio(rs.getDouble("precio"));
                pe.setFecha_entrega(rs.getDate("fecha_entrega"));
                pe.setFactura(rs.getString("factura"));
                pe.setTipo_venta(rs.getString("tipo_venta"));
                pe.setFecha_creacion(rs.getDate("fecha_creacion"));
                pe.setFactura_despacho(rs.getString("factura_despacho"));
                pe.setFecha_despacho(rs.getDate("fecha_despacho"));
                pe.setEstado(rs.getString("estado"));
                pe.setFactor(rs.getDouble("factor"));
                pe.setPeso_millar(rs.getDouble("peso_millar"));
                pe.setBase(rs.getDouble("base"));
                pe.setReferencia(rs.getString("referencia"));
                pe.setCantidad(rs.getDouble("cantidad"));
                pe.setTipo_cantidad(rs.getString("tipo_cantidad"));
                pe.setExtrusion(rs.getBoolean("extrusion"));
                pe.setImpresion(rs.getBoolean("impresion"));
                pe.setSellado(rs.getBoolean("sellado"));

                alpe.add(pe);
            }
        } catch (Exception e) {
            System.out.println("Error 05 DaosPedido: " + e.getMessage());
            return alpe;
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error 06 DaosPedido: " + e.getMessage());
                return null;
            }
        }
        return alpe;

    }

    public static String sqlCrearPedido() {
        return "insert into pedido (cliente_cedula, bolsa_referencia, precio, fecha_entrega, factura, tipo_venta, fecha_creacion, factura_despacho, estado, factor, peso_millar, base, referencia, cantidad, tipo_cantidad, extrusion, impresion, sellado)" +
                "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }

    public static String sqlActualizarPedido() {
        return "update pedido set cliente_cedula = ?, bolsa_referencia = ?, precio = ?, fecha_entrega = ?, factura = ?, tipo_venta = ?, fecha_creacion = ?, factura_despacho = ?, fecha_despacho = ?, estado = ?, factor = ?, peso_millar = ?, base = ?, referencia = ?, cantidad = ?, tipo_cantidad = ?, extrusion = ?, impresion = ?, sellado = ? where numeroPedido = ?";
    }

    public static String sqlBuscarPedido(String variable) {
        return "select * from pedido where " + variable + " LIKE ?";
    }
}
