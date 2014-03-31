/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import api.*;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingConstants;

/**
 *
 * @author JPABLO
 */
public class Cliente implements MouseListener {

    public APanel panel;
    ALabel lblCliente;
    public AButton btnCrear, btnBuscar;

    public Cliente() {
        panel = new APanel(Main.x, 0, 750, 600);

        lblCliente = new ALabel("Clientes");
        lblCliente.setHorizontalAlignment(SwingConstants.CENTER);
        lblCliente.setFont(new Font("Calibri", Font.PLAIN, 40));
        lblCliente.setForeground(Colores.titulo_normal);
        lblCliente.setBounds(0, 74, panel.getWidth(), 50);
        panel.add(lblCliente);

        btnCrear = new AButton("Crear cliente");
        btnCrear.setBounds(295, 199, 160, 60);
        btnCrear.addMouseListener(this);
        panel.add(btnCrear);

        btnBuscar = new AButton("<html><body align='center'>Buscar, actualizar o eliminar clientes</body></html>");
        btnBuscar.setBounds(295, 279, 160, 60);
        btnBuscar.addMouseListener(this);
        panel.add(btnBuscar);

        panel.setVisible(false);

    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        if (e.getSource() == btnCrear) {
            Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());
            Main.caCliente = new CACliente();
            Main.removerTodos();
            Main.menu.frame.add(Main.caCliente.panel);
            Main.esconderTodos();
            Main.caCliente.panel.setVisible(true);
            Main.dialog.ocultar();
        }

        if (e.getSource() == btnBuscar) {
            Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());
            Main.buscarCliente = new BuscarCliente();
            Main.removerTodos();
            Main.menu.frame.add(Main.buscarCliente.panel);
            Main.esconderTodos();
            Main.buscarCliente.panel.setVisible(true);
            Main.dialog.ocultar();
        }
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }
}
