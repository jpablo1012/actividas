package Gui;

import Entidades.BolsaE;
import Entidades.ColorE;
import Entidades.MaterialE;
import Negocio.BolsaN;
import Negocio.ColorN;
import Negocio.MaterialN;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ButtonGroup;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import api.AButton;
import api.AComboBox;
import api.AContainer;
import api.ALabel;
import api.APanel;
import api.ARadioButton;
import api.AScrollPanel;
import api.ATable;
import api.ATextField;
import api.Estado;

public class BuscarInsumos implements MouseListener, ActionListener, KeyListener {

    public APanel panel;
    AContainer busq;
    String[] busMaterial = {"-Seleccionar-", "Nombre", "Cantidad"};
    String[] busColor = {"-Seleccionar-", "Nombre", "Cantidad"};
    String[] busBolsa = {"-Seleccionar-", "Referencia color", "Referencia material", "C\u00E9dula del cliente", "Medida", "Ancho", "Largo", "Calibre", "Tratado", "Transparencia arriba", "Transparencia abajo", "Fuelle fondo", "Fuelle superior", "Fuelle lateral", "Solapa", "Troquel"};
    String[] reemplazo = {"", "color_codigo", "material_referencia", "cliente_cedula", "medida", "ancho", "largo", "calibre", "tratado", "transparenciaArriba", "transparenciaAbajo", "fuelle_fondo", "fuelle_superior", "fuelle_lateral", "solapa", "troquel"};
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
    ButtonGroup arb;
    ARadioButton arbColor;
    ARadioButton arbMaterial;
    ARadioButton arbBolsa;
    DefaultTableModel dtm;

    ArrayList<BolsaE> albe = null;
    ArrayList<MaterialE> alme = null;
    ArrayList<ColorE> alce = null;

    int modo = 0;//0 = bolsa, 1 = material, 2 = color


    public BuscarInsumos() {
        panel = new APanel(Main.x, 0, 750, 600);
        panel.setTitulo("Insumos| Buscar");

        busq = new AContainer("Buscar");
        busq.setBounds(110, 200, 530, 200);
        panel.add(busq);

        lblBuscar = new ALabel("Buscar con:");
        lblBuscar.setBounds(35, 30, 70, 24);
        busq.add(lblBuscar);

        txtBuscar = new ATextField();
        txtBuscar.setBounds(35, 85, 450, 24);
        txtBuscar.setPlaceHolder("No hace falta digitar todo para buscar, es m\u00E1s, intenta dejar este campo vac\u00EDo");
        txtBuscar.addKeyListener(this);
        busq.add(txtBuscar);

        comBuscar = new AComboBox(busBolsa);
        comBuscar.setBounds(117, 30, 120, 24);
        busq.add(comBuscar);

        btnBuscar = new AButton("Buscar insumos");
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
        resultado.setVisible(false);
        panel.add(resultado);

        tabla = new ATable();

        tabla.setPreferredSize(new Dimension(650, 340));
        resultado.setViewportView(tabla);

        btnNueva = new AButton("Nueva b\u00FAsqueda");
        btnNueva.setBounds(160, 480, 130, 30);
        btnNueva.setVisible(false);
        btnNueva.addMouseListener(this);
        panel.add(btnNueva);

        btnActualizar = new AButton("Actualizar insumo");
        btnActualizar.setBounds(310, 480, 130, 30);
        btnActualizar.addMouseListener(this);
        btnActualizar.setVisible(false);
        panel.add(btnActualizar);

        btnEliminar = new AButton("Eliminar insumo");
        btnEliminar.setBounds(460, 480, 130, 30);
        btnEliminar.addMouseListener(this);
        btnEliminar.setVisible(false);
        panel.add(btnEliminar);

        arb = new ButtonGroup();

        arbColor = new ARadioButton("Color");
        arbColor.setBounds(257, 30, 55, 24);
        arbColor.addActionListener(this);
        busq.add(arbColor);
        arb.add(arbColor);

        arbMaterial = new ARadioButton("Material");
        arbMaterial.setBounds(332, 30, 75, 24);
        arbMaterial.addActionListener(this);
        busq.add(arbMaterial);
        arb.add(arbMaterial);

        arbBolsa = new ARadioButton("Bolsa");
        arbBolsa.setBounds(427, 30, 70, 24);
        arbBolsa.addActionListener(this);
        arbBolsa.setSelected(true);
        busq.add(arbBolsa);
        arb.add(arbBolsa);

        panel.setVisible(false);

        if (Main.menu.evaluar(Main.menu.getUsuario().getTipo()) == 2) {
            txtBuscar.setText(Main.menu.getUsuario().getClienteCedula());
            btnActualizar.setText("Actualizar bolsa");
            btnEliminar.setText("Eliminar bolsa");
            btnActualizar.setBounds(235, 480, 130, 30);
            btnEliminar.setBounds(385, 480, 130, 30);
            comBuscar.setSelectedIndex(3);
            panel.remove(btnNueva);
            buscar();
            panel.setTitulo("Insumos| Mis bolsas");
            btnEliminar.setVisible(true);
        }

    }

    private void actualizarMaterial(int seleccionado) {
        MaterialE me = this.alme.get(seleccionado);

        Main.esconderTodos();
        Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());

        Main.caInsumos = new CAInsumos();
        Main.menu.frame.getContentPane().add(Main.caInsumos.panel);
        Main.caInsumos.datosMaterial(me);
        Main.caInsumos.visibleMaterial(true, false, false, true);
        Main.caInsumos.panel.setTitulo("Insumos| Actualizar");
        Main.caInsumos.color.setVisible(false);
        Main.caInsumos.material.setLocation((Main.caInsumos.panel.getWidth() / 2) - (Main.caInsumos.material.getWidth() / 2),
                (Main.caInsumos.panel.getHeight() / 2) - (Main.caInsumos.material.getHeight() / 2));
        Main.caInsumos.panel.setVisible(true);
        Main.dialog.ocultar();
    }

    private void actualizarColor(int seleccionado) {
        ColorE ce = this.alce.get(seleccionado);

        Main.esconderTodos();
        Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());

        Main.caInsumos = new CAInsumos();
        Main.menu.frame.getContentPane().add(Main.caInsumos.panel);
        Main.caInsumos.datosColor(ce);
        Main.caInsumos.visibleColor(true, false, false, true);
        Main.caInsumos.panel.setTitulo("Insumos| Actualizar");
        Main.caInsumos.material.setVisible(false);
        Main.caInsumos.color.setLocation((Main.caInsumos.panel.getWidth() / 2) - (Main.caInsumos.color.getWidth() / 2),
                (Main.caInsumos.panel.getHeight() / 2) - (Main.caInsumos.color.getHeight() / 2));
        Main.caInsumos.panel.setVisible(true);
        Main.dialog.ocultar();
    }

    private void actualizarBolsa(int seleccionado) {
        BolsaE be = this.albe.get(seleccionado);

        Main.esconderTodos();
        Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());

        Main.caBolsa = new CABolsa();
        Main.menu.frame.getContentPane().add(Main.caBolsa.panel);
        Main.caBolsa.setDatos(be);
        Main.caBolsa.visible(false, true, false, true);
        Main.caBolsa.panel.titulo.setText("Insumos| Actualizar");
        Main.caBolsa.panel.setVisible(true);

        Main.dialog.ocultar();
    }

    public void actualizar() {
        int seleccionado = tabla.getSelectedRow();

        if (seleccionado >= 0) {
            if (modo == 0) {
                actualizarBolsa(seleccionado);
            }

            if (modo == 1) {
                actualizarMaterial(seleccionado);
            }

            if (modo == 2) {
                actualizarColor(seleccionado);
            }
        } else {
            if (modo == 0) {
                msjMensaje.setText("Seleccione una fila para ver");
            } else {
                msjMensaje.setText("Seleccione una fila para actualizar");
            }
            msjMensaje.setEstado(Estado.ERROR);
            msjMensaje.setVisible(true);
        }
    }

    private void eliminarColor(int seleccionado) {
        ColorE ce = this.alce.get(seleccionado);

        String s = new ColorN().eliminarColor(ce);
        buscar();
        if (s.equals("")) {
            msjMensaje.setText("El color ha sido eliminado");
            msjMensaje.setEstado(Estado.EXITO);
            msjMensaje.setVisible(true);
        }
        if (s.equals("1")) {
            msjMensaje.setText("Upps. Este color esta en uso");
            msjMensaje.setEstado(Estado.ERROR);
            msjMensaje.setVisible(true);
        }
        if (s.equals("2")) {
            msjMensaje.setText("Error al conectarse a la base de datos");
            msjMensaje.setEstado(Estado.ERROR);
            msjMensaje.setVisible(true);
        }

    }

    private void eliminarMaterial(int seleccionado) {
        MaterialE me = this.alme.get(seleccionado);

        String s = new MaterialN().eliminarMaterial(me);
        buscar();
        if (s.equals("")) {
            msjMensaje.setText("El material ha sido eliminado");
            msjMensaje.setEstado(Estado.EXITO);
            msjMensaje.setVisible(true);

        }
        if (s.equals("1")) {
            msjMensaje.setText("Upps. Este material esta en uso");
            msjMensaje.setEstado(Estado.ERROR);
            msjMensaje.setVisible(true);
        }
        if (s.equals("2")) {
            msjMensaje.setText("Error al conectarse a la base de datos");
            msjMensaje.setEstado(Estado.ERROR);
            msjMensaje.setVisible(true);
        }
    }

    private void eliminarBolsa(int seleccionado) {
        if (Main.menu.evaluar(Main.menu.getUsuario().getTipo()) == 2) {
            BolsaE be = this.albe.get(seleccionado);
            be.setCliente_cedula(null);
            String s = new BolsaN().actualizarBolsa(be);
            buscar();
            if (s.equals("")) {
                msjMensaje.setText("La bolsa ha sido eliminada");
                msjMensaje.setEstado(Estado.EXITO);
                msjMensaje.setVisible(true);

            }
            if (s.equals("1")) {
                msjMensaje.setText("Error desconocido al eliminar la bolsa");
                msjMensaje.setEstado(Estado.ERROR);
                msjMensaje.setVisible(true);
            }
            if (s.equals("2")) {
                msjMensaje.setText("Error al conectarse a la base de datos");
                msjMensaje.setEstado(Estado.ERROR);
                msjMensaje.setVisible(true);
            }
        } else if (Main.menu.evaluar(Main.menu.getUsuario().getTipo()) == 1) {
            BolsaE be = this.albe.get(seleccionado);

            String s = new BolsaN().eliminarBolsa(be);
            buscar();
            if (s.equals("")) {
                msjMensaje.setText("La bolsa ha sido eliminada");
                msjMensaje.setEstado(Estado.EXITO);
                msjMensaje.setVisible(true);

            }
            if (s.equals("1")) {
                msjMensaje.setText("Upps. Esta bolsa esta en uso");
                msjMensaje.setEstado(Estado.ERROR);
                msjMensaje.setVisible(true);
            }
            if (s.equals("2")) {
                msjMensaje.setText("Error al conectarse a la base de datos");
                msjMensaje.setEstado(Estado.ERROR);
                msjMensaje.setVisible(true);
            }
        }

    }

    public void eliminar() {
        int seleccionado = tabla.getSelectedRow();
        if (seleccionado >= 0) {
            int cont = JOptionPane.showConfirmDialog(Main.menu.frame, "\u00BFDesea eliminar este insumo?", "Eliminar insumo", JOptionPane.YES_NO_OPTION);

            if (cont == 0) {
                Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());
                if (modo == 0) {
                    eliminarBolsa(seleccionado);
                }
                if (modo == 1) {
                    eliminarMaterial(seleccionado);
                }
                if (modo == 2) {
                    eliminarColor(seleccionado);
                }
                Main.dialog.ocultar();
            }
        } else {
            msjMensaje.setText("Seleccione una fila para eliminar");
            msjMensaje.setEstado(Estado.ERROR);
            msjMensaje.setVisible(true);
        }
    }

    private void buscarBolsa(String variable, String valor) {
        albe = null;
        albe = new BolsaN().buscarBolsa(variable, valor, false, true);

        if (albe == null) {
            msjMensaje.setText("El valor que usted busca no existe");
            msjMensaje.setEstado(Estado.ERROR);
            msjMensaje.setVisible(true);
            visibleBuscar(false);
        } else {
            if (albe.size() == 0) {
                msjMensaje.setText("El valor que usted busca no existe");
                msjMensaje.setEstado(Estado.ERROR);
                msjMensaje.setVisible(true);
                visibleBuscar(false);
            } else {
                int cont = 0;
                int alto = 0;
                Object[][] result = new Object[albe.size()][17];
                for (int i = 0; i < albe.size(); i++) {
                    BolsaE be = albe.get(i);

                    String boo = be.isTratado() + "";
                    boo = boo.replaceAll("true", "si");
                    boo = boo.replaceAll("false", "no");

                    result[i][0] = be.getReferencia();
                    result[i][1] = be.getColor_codigo();
                    result[i][2] = be.getMaterial_referencia();
                    result[i][3] = be.getCliente_cedula();
                    result[i][4] = be.getMedida();
                    result[i][5] = be.getAncho();
                    result[i][6] = be.getLargo();
                    result[i][7] = be.getCalibre();
                    result[i][8] = boo;
                    result[i][9] = be.getTransArriba();
                    result[i][10] = be.getTransAbajo();
                    result[i][11] = be.getFuelle_fondo();
                    result[i][12] = be.getFuelle_superior();
                    result[i][13] = be.getFuelle_lateral();
                    result[i][14] = be.getSolapa();
                    result[i][15] = be.getTroquel();
                    if (be.getImagen() == null) {
                        result[i][16] = "No";
                    } else {
                        result[i][16] = "Si";
                    }

                    cont++;
                    alto += 25;
                }
                resultado.setText("<html><body>Bolsas encontradas: <b>" + cont + "</b></body></html>");
                dtm = new DefaultTableModel(result, new String[]{"Referencia", "Ref. color", "Ref. material", "C\u00E9dula cliente", "Medida", "Ancho", "Largo", "Calibre", "Tratado", "Transpa. arriba", "Transpa. abajo", "Fuelle fondo", "Fuelle superior", "Fuelle lateral", "Solapa", "Troquel", "Imagen"});

                JTableHeader p = tabla.getTableHeader();
                p.setSize(2000, 30);
                p.setPreferredSize(p.getSize());
                p.setMinimumSize(p.getSize());
                tabla.setModel(dtm);
                tabla.setAutoResizeMode(ATable.AUTO_RESIZE_OFF);
                tabla.setPreferredSize(new Dimension(2000, alto));
                tabla.setTableHeader(p);
                tabla.repaint();

                visibleBuscar(true);

                if (Main.menu.evaluar(Main.menu.getUsuario().getTipo()) == 2) {
                    btnActualizar.setText("Actualizar bolsa");
                    btnEliminar.setText("Eliminar bolsa");
                    btnActualizar.setBounds(235, 480, 130, 30);
                    btnEliminar.setBounds(385, 480, 130, 30);
                    comBuscar.setSelectedIndex(3);
                    panel.setTitulo("Insumos| Mis bolsas");
                    //lblInsumos.setText("Insumos| Mis bolsas");
                    btnEliminar.setVisible(true);
                } else {
                    btnActualizar.setText("Ver insumo");
                    btnNueva.setBounds(245, 480, 130, 30);
                    btnActualizar.setBounds(385, 480, 130, 30);
                    btnEliminar.setVisible(false);
                }

            }
        }
    }

    private void buscarColor(String variable, String valor) {
        alce = null;
        alce = new ColorN().buscarColor(variable, valor, false);

        if (alce == null) {
            msjMensaje.setText("El valor que usted busca no existe");
            msjMensaje.setEstado(Estado.ERROR);
            msjMensaje.setVisible(true);
            visibleBuscar(false);
        } else {
            if (alce.size() == 0) {
                msjMensaje.setText("El valor que usted busca no existe");
                msjMensaje.setEstado(Estado.ERROR);
                msjMensaje.setVisible(true);
                visibleBuscar(false);
            } else {
                int alto = 0;
                int cont = 0;
                Object[][] result = new Object[alce.size()][3];
                for (int i = 0; i < alce.size(); i++) {
                    ColorE ce = alce.get(i);

                    result[i][0] = ce.getCodigo();
                    result[i][1] = ce.getNombre();
                    result[i][2] = ce.getCantidad();
                    alto += 25;
                    cont++;
                }
                resultado.setText("<html><body>Colores encontrados: <b>" + cont + "</b></body></html>");
                dtm = new DefaultTableModel(result, new String[]{"Referencia", "Nombre", "Cantidad"});

                tabla.setModel(dtm);
                tabla.setAutoResizeMode(ATable.AUTO_RESIZE_ALL_COLUMNS);
                tabla.setPreferredSize(new Dimension(650, alto));
                tabla.repaint();

                visibleBuscar(true);
            }
        }
    }

    private void buscarMaterial(String variable, String valor) {
        alme = null;
        alme = new MaterialN().buscarMaterial(variable, valor, false);

        if (alme == null) {
            msjMensaje.setText("El valor que usted busca no existe");
            msjMensaje.setEstado(Estado.ERROR);
            msjMensaje.setVisible(true);
            visibleBuscar(false);
        } else {
            if (alme.size() == 0) {
                msjMensaje.setText("El valor que usted busca no existe");
                msjMensaje.setEstado(Estado.ERROR);
                msjMensaje.setVisible(true);
                visibleBuscar(false);
            } else {
                int cont = 0;
                int alto = 0;
                Object[][] result = new Object[alme.size()][3];
                for (int i = 0; i < alme.size(); i++) {
                    MaterialE me = alme.get(i);

                    result[i][0] = me.getReferencia();
                    result[i][1] = me.getNombre();
                    result[i][2] = me.getCantidad();
                    alto += 25;
                    cont++;
                }
                resultado.setText("<html><body>Materiales encontrados: <b>" + cont + "</b></body></html>");
                dtm = new DefaultTableModel(result, new String[]{"Referencia", "Nombre", "Cantidad"});

                tabla.setModel(dtm);
                tabla.setAutoResizeMode(ATable.AUTO_RESIZE_ALL_COLUMNS);
                tabla.setPreferredSize(new Dimension(650, alto));
                tabla.repaint();

                visibleBuscar(true);
            }
        }
    }

    public void visibleBuscar(boolean b) {
        resultado.setVisible(b);
        busq.setVisible(!b);
        btnNueva.setVisible(b);
        btnActualizar.setVisible(b);
        btnEliminar.setVisible(b);

        if (!b) {
            if (Main.menu.evaluar(Main.menu.getUsuario().getTipo()) == 2) {

                Main.removerTodos();
                Main.insumos = new Insumos();
                Main.menu.frame.add(Main.insumos.panel);
                Main.esconderTodos();
                Main.insumos.panel.setVisible(true);
                Main.insumos.msjMensaje.setText("No tienes bolsas");
                Main.insumos.msjMensaje.setEstado(Estado.ERROR);
                Main.dialog.ocultar();
            }

        }

    }

    public void buscar() {
        String variable = (String) comBuscar.getSelectedItem();
        String valor = txtBuscar.getText();
        modo = 0;//0 = bolsa, 1 = material, 2 = color
        btnActualizar.setText("Actualizar insumo");
        btnActualizar.setBounds(310, 480, 130, 30);
        btnNueva.setBounds(160, 480, 130, 30);
        msjMensaje.setText("");
        if (arbBolsa.isSelected()) {
            modo = 0;
        }

        if (arbMaterial.isSelected()) {
            modo = 1;
        }

        if (arbColor.isSelected()) {
            modo = 2;
        }

        if (modo == 0) {
            for (int i = 1; i < busBolsa.length; i++) {
                variable = variable.replaceAll(busBolsa[i], reemplazo[i]);
            }
            System.out.println(variable);
            buscarBolsa(variable, valor);
        } else {
            variable = variable.toLowerCase();
            if (modo == 1) {
                buscarMaterial(variable, valor);
            }
            if (modo == 2) {
                buscarColor(variable, valor);
            }
        }

    }

    public boolean comprobar() {
        String s = (String) comBuscar.getSelectedItem();
        boolean b = true;

        if (s.equals(busColor[0]) || s.equals(busMaterial[0]) || s.equals(busBolsa[0])) {
            comBuscar.setEstado(Estado.ERROR);
            msjMensaje.setText("Seleccione un campo");
            msjMensaje.setEstado(Estado.ERROR);
            msjMensaje.setVisible(true);
            b = false;
        } else {
            comBuscar.setEstado(Estado.NORMAL);
            msjMensaje.setText("");
            msjMensaje.setEstado(Estado.NORMAL);
            msjMensaje.setVisible(false);
        }

        return b;
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        if (e.getSource() == btnBuscar) {
            Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());
            if (comprobar()) {

                buscar();
            }
            Main.dialog.ocultar();
        }

        if (e.getSource() == btnNueva) {
            visibleBuscar(false);
            txtBuscar.setText("");
            comBuscar.setSelectedIndex(0);
            msjMensaje.setText("");
        }

        if (e.getSource() == btnEliminar) {
            eliminar();
        }

        if (e.getSource() == btnActualizar) {
            actualizar();
        }
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == arbColor) {
            comBuscar.setTextos(busColor);
        }

        if (e.getSource() == arbMaterial) {
            comBuscar.setTextos(busMaterial);
        }

        if (e.getSource() == arbBolsa) {
            comBuscar.setTextos(busBolsa);
        }
    }

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

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

}
