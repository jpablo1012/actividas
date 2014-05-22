package Gui;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import api.*;

import javax.swing.SwingConstants;

public class Pedido implements MouseListener {

    APanel panel;
    AButton btnCrear;
    AButton btnBuscar;
    AButton btnConfirmar;
    AButton btnMisPedidos;
    ALabel lblPedido;
    ALabel msjMensaje;

    public Pedido() {
        panel = new APanel(Main.x, 0, 750, 600);

        lblPedido = new ALabel("Pedidos");
        lblPedido.setHorizontalAlignment(SwingConstants.CENTER);
        lblPedido.setFont(new Font("Calibri", Font.PLAIN, 40));
        lblPedido.setForeground(Colores.titulo_normal);
        lblPedido.setBounds(0, 74, panel.getWidth(), 50);
        panel.add(lblPedido);

        btnCrear = new AButton("Crear pedido");
        btnCrear.setBounds(295, 190, 160, 60);
        btnCrear.addMouseListener(this);
        panel.add(btnCrear);

        btnBuscar = new AButton("Buscar pedido");
        btnBuscar.setBounds(295, 270, 160, 60);
        btnBuscar.addMouseListener(this);
        panel.add(btnBuscar);

        btnConfirmar = new AButton("Confirmar pedido");
        btnConfirmar.setBounds(295, 350, 160, 60);
        btnConfirmar.addMouseListener(this);
        panel.add(btnConfirmar);

        btnMisPedidos = new AButton("Mis Pedidos");
        btnMisPedidos.setBounds(295, 310, 160, 60);
        btnMisPedidos.addMouseListener(this);
        panel.add(btnMisPedidos);

        msjMensaje = new ALabel("");
        msjMensaje.setHorizontalAlignment(SwingConstants.CENTER);
        msjMensaje.setBounds(110, 478, 530, 20);
        msjMensaje.setFont(new Font("Calibri", Font.PLAIN, 18));
        msjMensaje.setVisible(true);
        panel.add(msjMensaje);

        panel.setVisible(false);
        if (Main.menu.evaluar(Main.menu.getUsuario().getTipo()) == 2) {
            btnConfirmar.setVisible(false);
            btnBuscar.setVisible(false);

            btnCrear.setBounds(295, 230, 160, 60);
            btnMisPedidos.setBounds(295, 310, 160, 60);
        } else {
            btnMisPedidos.setVisible(false);
        }
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        if (e.getSource() == btnCrear) {
            Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());
            Main.caPedido = new CAPedido();
            Main.removerTodos();
            Main.menu.frame.getContentPane().add(Main.caPedido.panel);
            Main.esconderTodos();
            Main.caPedido.panel.setVisible(true);
            Main.dialog.ocultar();
        }

        if (e.getSource() == btnBuscar) {
            Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());
            Main.buscarPedido = new BuscarPedido();
            Main.removerTodos();
            Main.menu.frame.getContentPane().add(Main.buscarPedido.panel);
            Main.esconderTodos();
            Main.buscarPedido.aBuscar();
            Main.buscarPedido.panel.setVisible(true);
            Main.dialog.ocultar();
        }

        if (e.getSource() == btnMisPedidos) {
            Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());
            Main.buscarPedido = new BuscarPedido();
            Main.removerTodos();
            Main.menu.frame.getContentPane().add(Main.buscarPedido.panel);
            Main.esconderTodos();
            Main.buscarPedido.misPedidos();
            Main.buscarPedido.lblPedido.setText("Pedidos| Mis pedidos");
            Main.buscarPedido.panel.setVisible(true);
            Main.dialog.ocultar();

            if (Main.buscarPedido.msjMensaje.getText().equals("El valor que usted busca no existe en la base de datos")) {
                Main.removerTodos();
                Main.pedido = new Pedido();
                Main.menu.frame.add(Main.pedido.panel);
                Main.esconderTodos();
                Main.pedido.panel.setVisible(true);
                Main.pedido.msjMensaje.setText("No tienes pedidos :C");
                Main.pedido.msjMensaje.setEstado(Estado.error);
            }
        }

        if (e.getSource() == btnConfirmar) {
            Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());
            Main.buscarPedido = new BuscarPedido();
            Main.removerTodos();
            Main.menu.frame.getContentPane().add(Main.buscarPedido.panel);
            Main.esconderTodos();
            Main.buscarPedido.enConfirmacion();
            Main.buscarPedido.lblPedido.setText("Pedidos| Confirmar");
            Main.buscarPedido.panel.setVisible(true);
            Main.dialog.ocultar();

            if (Main.buscarPedido.msjMensaje.getText().equals("El valor que usted busca no existe en la base de datos")) {
                Main.removerTodos();
                Main.pedido = new Pedido();
                Main.menu.frame.add(Main.pedido.panel);
                Main.esconderTodos();
                Main.pedido.panel.setVisible(true);
                Main.pedido.msjMensaje.setText("No hay pedidos pendientes para confirmar");
                Main.pedido.msjMensaje.setEstado(Estado.exito);
            }
        }
    }

    public void mouseReleased(MouseEvent e) {
    }
}
