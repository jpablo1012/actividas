package Gui;

import Entidades.UsuarioE;
import Negocio.UsuarioN;
import api.*;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

public class InicioSesion implements MouseListener, KeyListener {

    AFrame frame;
    APanel inicio;
    ALabel lblCedula;
    ALabel lblCodigo;
    ALabel lblCedulaM;
    ALabel lblCodigoM;
    ALabel lblMensaje;
    ALabel lblSesion;
    ATextField txtCedula;
    APassword pswCodigo;
    AButton btnIniciar;
    UsuarioE us = new UsuarioE();
    UsuarioN un = new UsuarioN();

    public InicioSesion() {
        frame = new AFrame(400, 300);
        frame.setLocationRelativeTo(null);

        inicio = new APanel(0, 0, 400, 300);
        inicio.add(inicio.plastiser);
        inicio.setBorder(new MatteBorder(1, 1, 1, 1, Colores.borde_ventana));
        frame.getContentPane().add(inicio);

        lblCedula = new ALabel("C\u00E9dula:");
        lblCedula.setHorizontalAlignment(SwingConstants.RIGHT);
        lblCedula.setBounds(95, 96, 70, 24);
        inicio.add(lblCedula);

        lblCodigo = new ALabel("Contrase\u00F1a:");
        lblCodigo.setHorizontalAlignment(SwingConstants.RIGHT);
        lblCodigo.setBounds(93, 151, 70, 24);
        inicio.add(lblCodigo);

        txtCedula = new ATextField();
        txtCedula.setBounds(185, 97, 120, 24);
        txtCedula.setPlaceHolder("C\u00E9dula");
        inicio.add(txtCedula);

        lblCedulaM = new ALabel("");
        lblCedulaM.setBounds(185, 121, 160, 20);
        lblCedulaM.setVisible(false);
        inicio.add(lblCedulaM);

        pswCodigo = new APassword();
        pswCodigo.setBounds(185, 152, 120, 24);
        pswCodigo.setPlaceHolder("Contrase\u00F1a");
        pswCodigo.addKeyListener(this);
        inicio.add(pswCodigo);

        lblCodigoM = new ALabel("");
        lblCodigoM.setBounds(185, 176, 160, 20);
        lblCodigoM.setVisible(false);
        inicio.add(lblCodigoM);

        btnIniciar = new AButton("Iniciar sesi\u00F3n");
        btnIniciar.setBounds(135, 207, 130, 30);
        btnIniciar.addMouseListener(this);
        inicio.add(btnIniciar);

        lblMensaje = new ALabel("");
        lblMensaje.setHorizontalAlignment(SwingConstants.CENTER);
        lblMensaje.setBounds(10, 257, 380, 24);
        lblMensaje.setVisible(false);
        inicio.add(lblMensaje);

        lblSesion = new ALabel("Inicio de Sesi\u00F3n");
        lblSesion.setFont(new Font("Calibri", Font.PLAIN, 20));
        lblSesion.setForeground(Colores.titulo_normal);
        lblSesion.setBounds(135, 41, 130, 32);
        inicio.add(lblSesion);

        frame.repaint();
        frame.setVisible(true);
        frame.toFront();

        Main.dialog.enInicio(true);
        
        frame.setAlwaysOnTop(true);
        frame.setAlwaysOnTop(false);

        frame.setState(AFrame.NORMAL);
    }

    @SuppressWarnings({ "deprecation", "unused" })
    public void validar() {
        Main.dialog.mostrar(frame.getLocation(), frame.getSize());

        boolean ced = true;
        boolean cod = true;
        String cedula = txtCedula.getText();
        String codigo = pswCodigo.getText();

        if (cedula.equals("")) {
            txtCedula.setEstado(Estado.error);
            lblCedulaM.setEstado(Estado.error);
            lblCedulaM.setText("Campo vaci\u00F3");
            lblCedulaM.setVisible(true);
            ced = false;
        }
        if (ced) {
            try {
                double d = Double.parseDouble(cedula);
            } catch (Exception e) {
                txtCedula.setEstado(Estado.error);
                lblCedulaM.setEstado(Estado.error);
                lblCedulaM.setText("S\u00F3lo d\u00EDgitos");
                lblCedulaM.setVisible(true);
                ced = false;
            }
        }
        if (codigo.equals("")) {
            pswCodigo.setEstado(Estado.error);
            lblCodigoM.setEstado(Estado.error);
            lblCodigoM.setText("Campo vaci\u00F3");
            lblCodigoM.setVisible(true);
            cod = false;
        }

        if (ced && cod) {
            ArrayList<UsuarioE> alueEmpleado = un.buscarUsuario("empleado_cedula", cedula, true);
            ArrayList<UsuarioE> alueCliente = un.buscarUsuario("cliente_cedula", cedula, true);

            UsuarioE ueEmpleado = new UsuarioE();
            UsuarioE ueCliente = new UsuarioE();

            if (alueEmpleado == null || alueCliente == null) {
                txtCedula.setEstado(Estado.error);
                pswCodigo.setEstado(Estado.error);
                lblMensaje.setEstado(Estado.error);
                lblMensaje.setText("Error al conectarse a la base de datos");
                lblMensaje.setVisible(true);
                pswCodigo.setText("");
            } else {
                if (alueEmpleado.size() > 0) {
                    ueEmpleado = alueEmpleado.get(0);
                }

                if (alueCliente.size() > 0) {
                    ueCliente = alueCliente.get(0);
                }

                String com = losDos(ueEmpleado, ueCliente);

                if (com.equals("dos")) {
                    String s = ueEmpleado.getTipo();

                    String[] botones = {pasarMayus(s), "Cliente"};
                    int option = JOptionPane.showOptionDialog(frame, "Iniciar sesi\u00F3n como:", "Inicio sesi\u00F3n", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, botones, botones[0]);

                    if (option == 0) {
                        ir(ueEmpleado);
                    }

                    if (option == 1) {
                        ir(ueCliente);
                    }
                }

                if (com.equals("emp")) {
                    ir(ueEmpleado);
                }

                if (com.equals("cli")) {
                    ir(ueCliente);
                }

                if (com.equals("nada")) {
                    txtCedula.setEstado(Estado.error);
                    pswCodigo.setEstado(Estado.error);
                    lblMensaje.setEstado(Estado.error);
                    lblMensaje.setText("C\u00E9dula o c\u00F3digo incorrectos");
                    lblMensaje.setVisible(true);
                    pswCodigo.setText("");
                }
            }

        }

        Main.dialog.ocultar();

    }

    public static String pasarMayus(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1, s.length());

    }

    private void ir(UsuarioE ue) {
        Main.dialog.ocultar();
        frame.setVisible(false);
        Main.menu = new Menu(ue);
        Main.inicio = new Inicio();
        Main.menu.frame.getContentPane().add(Main.inicio.panel);
        Main.inicio.panel.setVisible(true);
    }

    @SuppressWarnings("deprecation")
    private String losDos(UsuarioE ueEmpleado, UsuarioE ueCliente) {
        String codigo = pswCodigo.getText();

        if (codigo.equals(ueEmpleado.getCodigo())
                && codigo.equals(ueCliente.getCodigo())) {
            return "dos";
        }

        if (codigo.equals(ueEmpleado.getCodigo())) {
            return "emp";
        }

        if (codigo.equals(ueCliente.getCodigo())) {
            return "cli";
        }

        return "nada";
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        if (e.getSource() == btnIniciar) {
            lblMensaje.setVisible(false);
            txtCedula.setEstado(Estado.normal);
            pswCodigo.setEstado(Estado.normal);
            lblCedulaM.setVisible(false);
            lblCodigoM.setVisible(false);
            validar();
        }
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        if (e.getSource() == pswCodigo) {
            int k = e.getKeyCode();

            if (k == 10) {
                lblMensaje.setVisible(false);
                txtCedula.setEstado(Estado.normal);
                pswCodigo.setEstado(Estado.normal);
                lblCedulaM.setVisible(false);
                lblCodigoM.setVisible(false);
                validar();
            }
        }
    }

    public void keyReleased(KeyEvent e) {
    }
}
