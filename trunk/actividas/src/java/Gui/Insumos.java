package Gui;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingConstants;

import api.AButton;
import api.ALabel;
import api.APanel;
import api.Estado;


public class Insumos implements MouseListener {

    public APanel panel;
    AButton btnCrearInsumos;
    AButton btnCrearBolsa;
    AButton btnBAEInsumos;
    ALabel  msjMensaje;

    public Insumos() {
        panel = new APanel(Main.x, 0, 750, 600);
        panel.setTitulo("Insumos");

        btnCrearInsumos = new AButton("<html><body>Crear Color o material</body></html>");
        btnCrearInsumos.addMouseListener(this);
        panel.add(btnCrearInsumos);

        btnCrearBolsa = new AButton("Crear bolsa");
        btnCrearBolsa.addMouseListener(this);
        panel.add(btnCrearBolsa);

        btnBAEInsumos = new AButton("<html><body align='center'>Buscar, actualizar o eliminar insumos</body></html>");
        btnBAEInsumos.addMouseListener(this);
        panel.add(btnBAEInsumos);

        msjMensaje = new ALabel("");
        msjMensaje.setHorizontalAlignment(SwingConstants.CENTER);
        msjMensaje.setBounds(110, 478, 530, 20);
        msjMensaje.setFont(new Font("Calibri", Font.PLAIN, 18));
        msjMensaje.setVisible(true);
        panel.add(msjMensaje);

        if (Main.menu.getUsuario().getTipo().equals("cliente")) {
            btnCrearBolsa.setBounds(295, 230, 160, 60);
            btnBAEInsumos.setBounds(295, 310, 160, 60);
            
            panel.setTitulo("Bolsa");
            //lblInsumos.setText("Bolsa");
            btnBAEInsumos.setText("<html><body align='center'>Mis bolsas</body></html>");

            btnCrearInsumos.setVisible(false);
        } else {
            btnCrearInsumos.setBounds(295, 199, 160, 60);
            btnCrearBolsa.setBounds(295, 279, 160, 60);
            btnBAEInsumos.setBounds(295, 359, 160, 60);
        }

        panel.setVisible(false);
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        if (e.getSource() == btnCrearInsumos) {
            Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());
            Main.caInsumos = new CAInsumos();
            Main.removerTodos();
            Main.menu.frame.add(Main.caInsumos.panel);
            Main.esconderTodos();
            Main.caInsumos.panel.setVisible(true);
            Main.dialog.ocultar();

        }

        if (e.getSource() == btnBAEInsumos) {
            Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());
            Main.buscarInsumos = new BuscarInsumos();
            Main.removerTodos();
            Main.menu.frame.add(Main.buscarInsumos.panel);
            Main.esconderTodos();
            Main.buscarInsumos.panel.setVisible(true);
            Main.dialog.ocultar();

            if (Main.buscarInsumos.msjMensaje.getText().equals("El valor que usted busca no existe")) {
                Main.removerTodos();
                Main.insumos = new Insumos();
                Main.menu.frame.add(Main.insumos.panel);
                Main.esconderTodos();
                Main.insumos.panel.setVisible(true);
                Main.insumos.msjMensaje.setText("No tienes bolsas");
                Main.insumos.msjMensaje.setEstado(Estado.ERROR);
            }
        }

        if (e.getSource() == btnCrearBolsa) {
            Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());
            Main.caBolsa = new CABolsa();
            Main.removerTodos();
            Main.menu.frame.add(Main.caBolsa.panel);
            Main.esconderTodos();
            Main.caBolsa.panel.setVisible(true);
            Main.dialog.ocultar();
        }
    }

    public void mouseReleased(MouseEvent e) {
    }
}
