/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entidades.ClienteE;
import Entidades.EmpleadoE;
import Entidades.UsuarioE;
import Negocio.ClienteN;
import Negocio.EmpleadoN;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import api.*;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.SwingConstants;

/**
 *
 * @author Usuario
 */
public class Ajustes implements MouseListener {

    APanel panel;
    AButton btnModificar;
    ALabel lblAjustes, msjMensaje;

    public Ajustes() {
        panel = new APanel(Main.x, 0, 750, 600);

        btnModificar = new AButton("Modificar perfil");
        btnModificar.setBounds(295, 270, 160, 60);
        btnModificar.addMouseListener(this);
        panel.add(btnModificar);

        lblAjustes = new ALabel("Perfil");
        lblAjustes.setHorizontalAlignment(SwingConstants.CENTER);
        lblAjustes.setFont(new Font("Calibri", Font.PLAIN, 40));
        lblAjustes.setForeground(Colores.titulo_normal);
        lblAjustes.setBounds(0, 74, panel.getWidth(), 50);
        panel.add(lblAjustes);

        msjMensaje = new ALabel("");
        msjMensaje.setHorizontalAlignment(SwingConstants.CENTER);
        msjMensaje.setBounds(110, 478, 530, 20);
        msjMensaje.setFont(new Font("Calibri", Font.PLAIN, 18));
        msjMensaje.setVisible(true);
        panel.add(msjMensaje);
    }

    private void toEmpleado(UsuarioE ue) {
        msjMensaje.setText("");
        ArrayList<EmpleadoE> alee = new EmpleadoN().buscarEmpleado("cedula", ue.getEmpleadoCedula(), true);

        if (alee == null) {
            msjMensaje.setText("Error al conectarse a la base de datos");
            msjMensaje.setEstado(Estado.error);
            msjMensaje.setVisible(true);
        } else {
            if (alee.size() == 0) {
                msjMensaje.setText("Error desconocido :C");
                msjMensaje.setEstado(Estado.error);
                msjMensaje.setVisible(true);
            } else {
                Main.resultadoEmpleado = new ResultadoEmpleado();
                Main.resultadoEmpleado.regresoAbuscar(false);
                Main.resultadoEmpleado.setDatos(ue, alee.get(0));
                Main.resultadoEmpleado.comCargo.setEnabled(false);
                Main.resultadoEmpleado.lblRsultado.setText("Perfil| Modificar perfil");
                Main.removerTodos();
                Main.menu.frame.getContentPane().add(Main.resultadoEmpleado.panel);
                Main.esconderTodos();
                Main.resultadoEmpleado.panel.setVisible(true);
            }
        }
    }

    private void toCliente(UsuarioE ue) {
        msjMensaje.setText("");
        ArrayList<ClienteE> alce = new ClienteN().buscarCliente("cedula", ue.getClienteCedula(), true);

        if (alce == null) {
            msjMensaje.setText("Error al conectarse a la base de datos");
            msjMensaje.setEstado(Estado.error);
            msjMensaje.setVisible(true);
        } else {
            if (alce.size() == 0) {
                msjMensaje.setText("Error desconocido :C");
                msjMensaje.setEstado(Estado.error);
                msjMensaje.setVisible(true);
            } else {
                Main.caCliente = new CACliente();
                Main.removerTodos();
                Main.caCliente.setDatos(alce.get(0), ue);
                Main.caCliente.visibleCliente(true, false, false, true);
                Main.caCliente.lblCliente.setText("Perfil| Modificar perfil");
                Main.caCliente.regresoAbuscar(false);
                Main.menu.frame.getContentPane().add(Main.caCliente.panel);
                Main.esconderTodos();
                Main.caCliente.panel.setVisible(true);
            }
        }
    }

    private void cambiar() {
        UsuarioE ue = Main.menu.getUsuario();
        int tipo = Main.menu.evaluar(ue.getTipo());

        if (tipo <= 1) {
            toEmpleado(ue);
        } else {
            toCliente(ue);
        }
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        if (e.getSource() == btnModificar) {
            Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());
            cambiar();
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
