package Gui;

import api.ADialog;

/**
 * 
 * @author JPABLO
 */
public class Main {

    public static Inicio inicio;
    public static InicioSesion inicioSesion;
    public static Menu menu;
    public static Empleado empleado;
    public static CrearEmpleado crearEmpleado;
    public static BuscarEmpleado buscarEmpleado;
    public static ResultadoEmpleado resultadoEmpleado;
    public static Cliente cliente;
    public static CACliente caCliente;
    public static BuscarCliente buscarCliente;
    public static Insumos insumos;
    public static CAInsumos caInsumos;
    public static BuscarInsumos buscarInsumos;
    public static Pedido pedido;
    public static CAPedido caPedido;
    public static Informes informes;
    public static Ordenes ordenes;
    public static int x = 170;
    public static ADialog dialog;
    public static Ajustes ajustes;
    public static CABolsa caBolsa;
    public static BuscarPedido buscarPedido;
    public static BuscarOrdenes buscarOrdenes;
    public static OrdenExtrusor ordenExtrusor;
    public static OrdenImpresor ordenImpresor;
    public static OrdenSellado ordenSellado;
    public static OrdenFinalizar ordenFinalizar;
    public static InformesSellador informesSellador;

    public static void main(String[] args) {
        Hilo h = new Hilo();
        h.start();
        dialog = new ADialog("Cargando...");
        inicioSesion = new InicioSesion();
    }

    public static void removerTodos() {
        try {
            menu.frame.remove(inicio.panel);
        } catch (Exception e) {
        }
        try {
            menu.frame.remove(empleado.panel);
        } catch (Exception e) {
        }
        try {
            menu.frame.remove(crearEmpleado.panel);
        } catch (Exception e) {
        }
        try {
            menu.frame.remove(buscarEmpleado.panel);
        } catch (Exception e) {
        }
        try {
            menu.frame.remove(resultadoEmpleado.panel);
        } catch (Exception e) {
        }
        try {
            menu.frame.remove(cliente.panel);
        } catch (Exception e) {
        }
        try {
            menu.frame.remove(caCliente.panel);
        } catch (Exception e) {
        }
        try {
            menu.frame.remove(buscarCliente.panel);
        } catch (Exception e) {
        }
        try {
            menu.frame.remove(insumos.panel);
        } catch (Exception e) {
        }
        try {
            menu.frame.remove(caInsumos.panel);
        } catch (Exception e) {
        }
        try {
            menu.frame.remove(buscarInsumos.panel);
        } catch (Exception e) {
        }
        try {
            menu.frame.remove(pedido.panel);
        } catch (Exception e) {
        }
        try {
            menu.frame.remove(caPedido.panel);
        } catch (Exception e) {
        }
        try {
            menu.frame.remove(informes.panel);
        } catch (Exception e) {
        }
        try {
            menu.frame.remove(ordenes.panel);
        } catch (Exception e) {
        }
        try {
            menu.frame.remove(ajustes.panel);
        } catch (Exception e) {
        }
        try {
            menu.frame.remove(caBolsa.panel);
        } catch (Exception e) {
        }
        try {
            menu.frame.remove(buscarPedido.panel);
        } catch (Exception e) {
        }
        try {
            menu.frame.remove(buscarOrdenes.panel);
        } catch (Exception e) {
        }
        try {
            menu.frame.remove(ordenExtrusor.panel);
        } catch (Exception e) {
        }
        try {
            menu.frame.remove(ordenImpresor.panel);
        } catch (Exception e) {
        }
        try {
            menu.frame.remove(ordenSellado.panel);
        } catch (Exception e) {
        }
        try {
            menu.frame.remove(ordenFinalizar.panel);
        } catch (Exception e) {
        }
        try {
            menu.frame.remove(informesSellador.panel);
        } catch (Exception e) {
        }
        menu.frame.repaint();
    }

    public static void esconderTodos() {
        try {
            inicio.panel.setVisible(false);
        } catch (Exception e) {
        }

        try {
            crearEmpleado.panel.setVisible(false);
        } catch (Exception e) {
        }
        try {
            buscarEmpleado.panel.setVisible(false);
        } catch (Exception e) {
        }
        try {
            resultadoEmpleado.panel.setVisible(false);
        } catch (Exception e) {
        }
        try {
            empleado.panel.setVisible(false);
        } catch (Exception e) {
        }
        try {
            cliente.panel.setVisible(false);
        } catch (Exception e) {
        }
        try {
            caCliente.panel.setVisible(false);
        } catch (Exception e) {
        }
        try {
            buscarCliente.panel.setVisible(false);
        } catch (Exception e) {
        }
        try {
            insumos.panel.setVisible(false);
        } catch (Exception e) {
        }
        try {
            caInsumos.panel.setVisible(false);
        } catch (Exception e) {
        }
        try {
            buscarInsumos.panel.setVisible(false);
        } catch (Exception e) {
        }
        try {
            pedido.panel.setVisible(false);
        } catch (Exception e) {
        }
        try {
            caPedido.panel.setVisible(false);
        } catch (Exception e) {
        }
        try {
            menu.frame.repaint();
        } catch (Exception e) {
        }
        try {
            informes.panel.setVisible(false);
        } catch (Exception e) {
        }
        try {
            ordenes.panel.setVisible(false);
        } catch (Exception e) {
        }
        try {
            ajustes.panel.setVisible(false);
        } catch (Exception e) {
        }
        try {
            caBolsa.panel.setVisible(false);
        } catch (Exception e) {
        }
        try {
            buscarPedido.panel.setVisible(false);
        } catch (Exception e) {
        }
        try {
            buscarOrdenes.panel.setVisible(false);
        } catch (Exception e) {
        }
        try {
            ordenExtrusor.panel.setVisible(false);
        } catch (Exception e) {
        }
        try {
            ordenImpresor.panel.setVisible(false);
        } catch (Exception e) {
        }
        try {
            ordenSellado.panel.setVisible(false);
        } catch (Exception e) {
        }
        try {
            ordenFinalizar.panel.setVisible(false);
        } catch (Exception e) {
        }
        try {
            informesSellador.panel.setVisible(false);
        } catch (Exception e) {
        }
    }
}
