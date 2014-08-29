package Gui;

import Entidades.ClienteE;
import Entidades.UsuarioE;
import Negocio.ClienteN;
import Negocio.UsuarioN;
import api.AButton;
import api.AComboBox;
import api.AContainer;
import api.ALabel;
import api.APanel;
import api.AScrollPanel;
import api.ATable;
import api.ATextField;
import api.Estado;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class BuscarCliente implements MouseListener, KeyListener {

    public APanel panel;
    AContainer busq;
    String[] bus = {"-Seleccionar-", "Nombre", "Apellido", "C\u00E9dula", "Direcci\u00F3n", "Email", "Tel\u00E9fono", "Ciudad"};
    ALabel lblBuscar;
    ALabel msjMensaje;
    ATextField txtBuscar;
    AComboBox comBuscar;
    AButton btnBuscar;
    AButton btnNueva;
    AButton btnActualizar;
    AButton btnEliminar;
    AScrollPanel resultado;
    ATable tabla;
    DefaultTableModel dtm;
    ArrayList<ClienteE> ce;

    public BuscarCliente() {
        panel = new APanel(Main.x, 0, 750, 600);
        panel.setTitulo("Clientes| Buscar");

        busq = new AContainer("Buscar");
        busq.setBounds(110, 200, 530, 200);
        panel.add(busq);

        lblBuscar = new ALabel("Buscar con:");
        lblBuscar.setBounds(35, 30, 70, 24);
        //busq.add(lblBuscar);

        txtBuscar = new ATextField();
        txtBuscar.setBounds(35, 85, 450, 24);
        txtBuscar.setPlaceHolder("No hace falta digitar todo para buscar, es m\u00E1s, intenta dejar este campo vac\u00EDo");
        txtBuscar.addKeyListener(this);
        busq.add(txtBuscar);

        comBuscar = new AComboBox(bus);
        comBuscar.setSelectedIndex(1);
        comBuscar.setBounds(117, 30, 120, 24);
        //busq.add(comBuscar);

        btnBuscar = new AButton("Buscar cliente");
        btnBuscar.setBounds(200, 147, 130, 30);
        btnBuscar.addMouseListener(this);
        busq.add(btnBuscar);

        msjMensaje = new ALabel("");
        msjMensaje.setHorizontalAlignment(SwingConstants.CENTER);
        msjMensaje.setBounds(110, 538, 530, 20);
        msjMensaje.setFont(new Font("Calibri", Font.PLAIN, 18));
        msjMensaje.setVisible(false);
        panel.add(msjMensaje);

        resultado = new AScrollPanel("Resultado de la b\u00FAsqueda");
        resultado.setBounds(75, 60, 600, 400);
        resultado.addMouseListener(this);
        resultado.setVisible(false);
        panel.add(resultado);

        tabla = new ATable();
        tabla.setPreferredSize(new Dimension(650, 340));
        resultado.setViewportView(tabla);

        btnNueva = new AButton("Nueva b\u00FAsqueda");
        btnNueva.setBounds(128, 480, 130, 30);
        btnNueva.setVisible(false);
        btnNueva.addMouseListener(this);
        panel.add(btnNueva);

        btnActualizar = new AButton("Actualizar cliente");
        btnActualizar.setBounds(281, 480, 130, 30);
        btnActualizar.addMouseListener(this);
        btnActualizar.setVisible(false);
        panel.add(btnActualizar);

        btnEliminar = new AButton("Eliminar cliente");
        btnEliminar.setBounds(440, 480, 130, 30);
        btnEliminar.addMouseListener(this);
        btnEliminar.setVisible(false);
        panel.add(btnEliminar);

    }

    public boolean comprobar() {
        String buscarCon = (String) comBuscar.getSelectedItem();

        if (buscarCon.equals(bus[0])) {
            comBuscar.setEstado(Estado.ERROR);
            msjMensaje.setText("Seleccione un campo de la lista desplegable");
            msjMensaje.setEstado(Estado.ERROR);
            msjMensaje.setVisible(true);
            return false;
        }

        return true;
    }

    public void visibleBuscar(boolean b) {
        resultado.setVisible(b);
        busq.setVisible(!b);
        btnNueva.setVisible(b);
        btnActualizar.setVisible(b);
        btnEliminar.setVisible(b);
    }

    public void actualizar() {
        int seleccionado = tabla.getSelectedRow();

        if (seleccionado >= 0) {
            String valor = (String) tabla.getModel().getValueAt(seleccionado, 0);

            for (int i = 0; i < this.ce.size(); i++) {
                if (this.ce.get(i).getCedula().equals(valor)) {
                    ArrayList<UsuarioE> actue = new UsuarioN().buscarUsuario("cliente_cedula", valor, true, true);

                    Main.caCliente = new CACliente();
                    Main.menu.frame.getContentPane().add(Main.caCliente.panel);
                    Main.esconderTodos();
                    Main.caCliente.setDatos(this.ce.get(i), actue.get(0));
                    Main.caCliente.panel.setVisible(true);
                    Main.caCliente.visibleCliente(true, false, false, true);
                    Main.caCliente.panel.setTitulo("Cliente| Actualizar");
                    break;
                }
            }
        } else {
            msjMensaje.setText("Seleccione una fila para actualizar");
            msjMensaje.setEstado(Estado.ERROR);
            msjMensaje.setVisible(true);

        }
    }

    public void buscar() {
        String buscarCon = (String) comBuscar.getSelectedItem();
        String texto = txtBuscar.getText();
        msjMensaje.setText("");
        // buscarCon = buscarCon.toLowerCase();

        buscarCon = buscarCon.replaceAll(bus[1], "nombre");
        buscarCon = buscarCon.replaceAll(bus[2], "apellido");
        buscarCon = buscarCon.replaceAll(bus[3], "cedula");
        buscarCon = buscarCon.replaceAll(bus[4], "direccion");
        buscarCon = buscarCon.replaceAll(bus[5], "email");
        buscarCon = buscarCon.replaceAll(bus[6], "telefono");
        buscarCon = buscarCon.replaceAll(bus[7], "ciudad");

        this.ce = new ClienteN().buscarCliente(buscarCon, texto, false);
        ArrayList<UsuarioE> alue = new UsuarioN().buscarUsuario("cliente_cedula", "", false);
        if (ce == null) {
            msjMensaje.setText("El valor que usted busca no existe en la base de datos");
            msjMensaje.setEstado(Estado.ERROR);
            msjMensaje.setVisible(true);
        } else {
            int cont = 0;
            int alto = 0;
            if (this.ce.size() == 0) {
                msjMensaje.setText("El valor que usted busca no existe en la base de datos");
                msjMensaje.setEstado(Estado.ERROR);
                msjMensaje.setVisible(true);
                visibleBuscar(false);
            } else {
                Object[][] result = new Object[this.ce.size()][7];
                for (int i = 0; i < this.ce.size(); i++) {
                    for (int j = 0; j < alue.size(); j++) {
                        ClienteE ceh = this.ce.get(i);
                        UsuarioE ueh = alue.get(j);

                        if (ceh.getCedula().equals(ueh.getClienteCedula())) {
                            result[i][0] = ceh.getCedula();
                            result[i][1] = ceh.getNombre();
                            result[i][2] = ceh.getApellido();
                            result[i][3] = ceh.getDireccion();
                            result[i][4] = ceh.getCorreo();
                            result[i][5] = ceh.getTelefono();
                            result[i][6] = ceh.getCiudad();
                            alto += 25;
                            cont++;
                        }
                    }
                    //System.out.println(alue.size());
                }

                resultado.setText("<html><body>Clientes encontrados: <b>" + cont + "</b></body></html>");
                dtm = new DefaultTableModel(result, new String[]{"C\u00E9dula", "Nombre", "Apellido", "Direcci\u00F3n", "Email", "Tel\u00E9fono", "Ciudad"});

                JTableHeader p = tabla.getTableHeader();
                p.setSize(1200, 30);
                p.setPreferredSize(p.getSize());
                p.setMinimumSize(p.getSize());
                tabla.setModel(dtm);
                tabla.setAutoResizeMode(ATable.AUTO_RESIZE_OFF);
                tabla.setPreferredSize(new Dimension(1200, alto));
                tabla.setTableHeader(p);
                tabla.repaint();

                visibleBuscar(true);
            }
        }

    }

    private void eliminar() {
        int seleccionado = tabla.getSelectedRow();
        if (seleccionado >= 0) {
            int cont = JOptionPane.showConfirmDialog(Main.menu.frame, "\u00BFDesea eliminar este cliente?", "Eliminar cliente", JOptionPane.YES_NO_OPTION);

            if (cont == 0) {
                Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());
                String valor = (String) tabla.getModel().getValueAt(seleccionado, 0);

                for (int i = 0; i < this.ce.size(); i++) {
                    if (this.ce.get(i).getCedula().equals(valor)) {
                        ArrayList<UsuarioE> alue = new UsuarioN().buscarUsuario("cliente_cedula", valor, true);

                        String s = new UsuarioN().eliminarUsuario(alue.get(0).getIdUsuario());

                        if (s.equals("")) {
                            s = new ClienteN().eliminarCliente((String) this.ce.get(i).getCedula());

                            if (s.equals("")) {
                                buscar();
                                msjMensaje.setText("El cliente ha sido eliminado");
                                msjMensaje.setEstado(Estado.EXITO);
                                msjMensaje.setVisible(true);

                            }
                        }

                        if (s.equals("1")) {
                            msjMensaje.setText("Ha ocurrido un error al eliminar el cliente");
                            msjMensaje.setEstado(Estado.ERROR);
                            msjMensaje.setVisible(true);
                        }

                        if (s.equals("2")) {
                            msjMensaje.setText("Error al conectarse a la base de datos");
                            msjMensaje.setEstado(Estado.ERROR);
                            msjMensaje.setVisible(true);
                        }

                        break;
                    }
                }
                Main.dialog.ocultar();
            }

        } else {
            msjMensaje.setText("Seleccione una fila para eliminar");
            msjMensaje.setEstado(Estado.ERROR);
            msjMensaje.setVisible(true);

        }
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        if (e.getSource() == btnBuscar) {
            Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());
            if (comprobar()) {
                buscar();
            }
            Main.dialog.ocultar();
        }

        if (e.getSource() == btnActualizar) {
            Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());
            actualizar();
            Main.dialog.ocultar();
        }

        if (e.getSource() == btnEliminar) {
            eliminar();
        }

        if (e.getSource() == btnNueva) {
            visibleBuscar(false);
            txtBuscar.setText("");
            //comBuscar.setSelectedIndex(0);
            msjMensaje.setText("");
        }
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getSource() == txtBuscar) {
            int k = e.getKeyCode();

            if (k == 10) {
                Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());
                if (comprobar()) {
                    buscar();
                }
                Main.dialog.ocultar();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
