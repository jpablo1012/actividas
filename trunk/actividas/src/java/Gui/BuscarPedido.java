package Gui;

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

import Entidades.PedidoE;
import Negocio.Auxiliar;
import Negocio.PedidoN;
import api.*;

public class BuscarPedido implements MouseListener, KeyListener {

    APanel panel;
    AContainer busq;

    ALabel lblPedido;
    ALabel lblBuscar;
    ALabel msjMensaje;
    ATextField txtBuscar;
    AComboBox comBuscar;
    AButton btnBuscar;
    AButton btnNueva;
    AButton btnAprobar;
    AButton btnRechazar;
    AButton btnVer;
    AScrollPanel resultado;
    ATable tabla;
    DefaultTableModel dtm;

    private String[] strBuscarCon = { "-Seleccione-", "C\u00E9dula del cliente", "Referencia de la bolsa", "Tipo de venta", "Estado", "Referencia", "Cantidad", "Tipo de cantidad", "Extrusi\u00F3n", "Impresi\u00F3n", "Sellado" };
    private String[] strReemplazo = { "", "cliente_cedula", "bolsa_referencia", "tipo_venta", "estado", "referencia", "cantidad", "tipo_cantidad", "extrusion", "impresion", "sellado" };

    private int modo = 0; // 0 = buscar en general, 1 = pedidos pendientes en
			  // confirmar, 2 = mis pedidos

    private ArrayList<PedidoE> alpe;

    public BuscarPedido() {
	panel = new APanel(Main.x, 0, 750, 600);

	lblPedido = new ALabel("Pedidos| Buscar");
	lblPedido.setFont(new Font("Calibri", Font.PLAIN, 24));
	lblPedido.setForeground(Colores.titulo_normal);
	lblPedido.setBounds(10, 0, 460, 50);
	panel.add(lblPedido);

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

	comBuscar = new AComboBox(strBuscarCon);
	comBuscar.setValores(strReemplazo);
	comBuscar.setBounds(117, 30, 150, 24);
	busq.add(comBuscar);

	btnBuscar = new AButton("Buscar pedido");
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
	btnNueva.setBounds(235, 480, 130, 30);
	btnNueva.setVisible(false);
	btnNueva.addMouseListener(this);
	panel.add(btnNueva);

	btnAprobar = new AButton("Aprobar pedido");
	btnAprobar.setBounds(310, 480, 130, 30);
	btnAprobar.addMouseListener(this);
	btnAprobar.setVisible(false);
	panel.add(btnAprobar);

	btnRechazar = new AButton("Rechazar pedido");
	btnRechazar.setBounds(460, 480, 130, 30);
	btnRechazar.addMouseListener(this);
	btnRechazar.setVisible(false);
	panel.add(btnRechazar);

	btnVer = new AButton("Ver pedido");
	btnVer.setBounds(385, 480, 130, 30);
	btnVer.addMouseListener(this);
	btnVer.setVisible(false);
	panel.add(btnVer);

	panel.setVisible(false);

    }

    public void misPedidos() {
	txtBuscar.setText(Main.menu.getUsuario().getClienteCedula());
	comBuscar.setSelectedIndex(1);
	buscar();
	panel.remove(btnNueva);
	setModo(2);
    }

    public void enConfirmacion() {
	txtBuscar.setText("pendiente");
	comBuscar.setSelectedIndex(4);
	buscar();
	panel.remove(btnNueva);
	setModo(1);
	btnAprobar.setVisible(true);
	btnRechazar.setVisible(true);
    }

    public void aBuscar() {
	setModo(0);
    }

    private void setModo(int i) {
	this.modo = i;

	if (this.modo == 0) {
	    btnNueva.setBounds(235, 480, 130, 30);
	    btnVer.setBounds(385, 480, 130, 30);
	} else if (this.modo == 1) {
	    btnRechazar.setBounds(460, 480, 130, 30);
	    btnAprobar.setBounds(160, 480, 130, 30);
	    btnVer.setBounds(310, 480, 130, 30);
	} else {
	    btnVer.setBounds(310, 480, 130, 30);
	}

    }

    private void visibleBuscar(boolean b) {
	resultado.setVisible(b);
	busq.setVisible(!b);
	btnNueva.setVisible(b);
	btnVer.setVisible(b);
	if (modo == 1) {
	    btnAprobar.setVisible(b);
	    btnRechazar.setVisible(b);
	}
    }

    public boolean comprobar() {
	int buscarCon = comBuscar.getSelectedIndex();
	boolean con = true;

	if (buscarCon == 0) {
	    msjMensaje.setText("Seleccione un campo de la lista desplegable");
	    msjMensaje.setEstado(Estado.error);
	    msjMensaje.setVisible(true);
	    con = false;
	} else {
	    msjMensaje.setText("");
	}

	return con;
    }

    public void buscar() {
	int variable = comBuscar.getSelectedIndex();
	String valor = txtBuscar.getText();

	if (variable >= 8 && variable <= 10) {
	    valor = valor.toLowerCase();
	    if (valor.equals("s") || valor.equals("si")) {
		valor = "1";
	    }

	    if (valor.equals("n") || valor.equals("no")) {
		valor = "0";
	    }
	}

	this.alpe = new PedidoN().buscarPedido(comBuscar.getValor(variable), valor, false);

	if (this.alpe == null) {
	    msjMensaje.setText("El valor que usted busca no existe en la base de datos");
	    msjMensaje.setEstado(Estado.error);
	    msjMensaje.setVisible(true);
	} else {
	    int alto = 0;
	    if (alpe.size() == 0) {
		msjMensaje.setText("El valor que usted busca no existe en la base de datos");
		msjMensaje.setEstado(Estado.error);
		msjMensaje.setVisible(true);
		visibleBuscar(false);
	    } else {
		Object[][] result = new Object[this.alpe.size()][13];
		for (int i = 0; i < this.alpe.size(); i++) {
		    PedidoE pe = this.alpe.get(i);
		    String extrusion = "No", impresion = "No", sellado = "No";
		    String estado = pe.getEstado();
		    if (estado.equals("en aprobacion")) {
			estado = "En aprobaci�n";
		    }
		    result[i][0] = pe.getNumeroPedido();
		    result[i][1] = pe.getCliente_cedula();
		    result[i][2] = pe.getBolsa_referencia();
		    result[i][3] = pe.getFecha_entrega();
		    result[i][4] = pe.getFecha_creacion();
		    result[i][5] = pe.getTipo_venta();
		    result[i][6] = estado;
		    result[i][7] = pe.getReferencia();
		    result[i][8] = pe.getTipo_cantidad();
		    result[i][9] = pe.getCantidad();

		    if (pe.isExtrusion()) {
			extrusion = "Si";
		    }

		    if (pe.isImpresion()) {
			impresion = "Si";
		    }

		    if (pe.isSellado()) {
			sellado = "Si";
		    }

		    result[i][10] = extrusion;
		    result[i][11] = impresion;
		    result[i][12] = sellado;

		    alto += 25;
		}

		resultado.setText("<html><body>Pedidos encontrados: <b>" + this.alpe.size() + "</b></body></html>");
		dtm = new DefaultTableModel(result, new String[] { "N\u00BA pedido", "C\u00E9dula del cliente", "Ref. bolsa", "Fecha de entrega", "Fecha de creacion", "Tipo de venta", "Estado", "Texto de la bolsa", "Tipo de cantidad", "Cantidad", "Extrusion", "Impresion", "Sellado" });

		JTableHeader p = tabla.getTableHeader();
		p.setSize(1800, 30);
		p.setPreferredSize(p.getSize());
		p.setMinimumSize(p.getSize());
		tabla.setModel(dtm);
		tabla.setAutoResizeMode(ATable.AUTO_RESIZE_OFF);
		tabla.setPreferredSize(new Dimension(1800, alto));
		tabla.setTableHeader(p);
		tabla.repaint();

		visibleBuscar(true);
	    }
	}

    }

    public void verPedido() {
	int seleccionado = tabla.getSelectedRow();

	if (seleccionado >= 0) {

	    Main.caPedido = new CAPedido();
	    Main.removerTodos();
	    Main.menu.frame.getContentPane().add(Main.caPedido.panel);
	    Main.esconderTodos();
	    Main.caPedido.setDatos(this.alpe.get(seleccionado));
	    Main.caPedido.setEditable(false);
	    Main.caPedido.lblPedido.setText("Pedidos| Ver");
	    Main.caPedido.visible(false, false, true, true);
	    Main.caPedido.panel.setVisible(true);

	} else {
	    msjMensaje.setText("Seleccione un pedido para verlo");
	    msjMensaje.setEstado(Estado.error);
	    msjMensaje.setVisible(true);
	}
    }

    public void rechazarPedido() {
	int seleccionado = tabla.getSelectedRow();

	if (seleccionado >= 0) {
	    String[] botones = { "Si", "No" };
	    int option = JOptionPane.showOptionDialog(Main.menu.frame, "\u00BFEst\u00E1 seguro en rechazar este pedido?", "Pedido", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, botones, botones[1]);
	    if (option == 0) {
		PedidoE pe = this.alpe.get(seleccionado);
		pe.setEstado("rechazado");
		String s = new PedidoN().actualizarPedido(pe);

		if (s.equals("")) {
		    buscar();
		    if (msjMensaje.getText().equals("El valor que usted busca no existe en la base de datos")) {
			Main.pedido = new Pedido();
			Main.removerTodos();
			Main.menu.frame.add(Main.pedido.panel);
			Main.esconderTodos();
			Main.pedido.panel.setVisible(true);
			Main.pedido.msjMensaje.setText("El pedido ha sido rechazado");
			Main.pedido.msjMensaje.setEstado(Estado.exito);
		    } else {
			msjMensaje.setText("El pedido ha sido rechazado");
			msjMensaje.setEstado(Estado.exito);
			msjMensaje.setVisible(true);
		    }

		}

		if (s.equals("1")) {
		    msjMensaje.setText("Error desconocido :C");
		    msjMensaje.setEstado(Estado.error);
		    msjMensaje.setVisible(true);
		}

		if (s.equals("2")) {
		    msjMensaje.setText("Error al conectarse a la base de datos");
		    msjMensaje.setEstado(Estado.error);
		    msjMensaje.setVisible(true);
		}
	    }
	} else {
	    msjMensaje.setText("Seleccione unun pedido para rechazar");
	    msjMensaje.setEstado(Estado.error);
	    msjMensaje.setVisible(true);
	}
    }

    public void aprobarPedido() {
	int seleccionado = tabla.getSelectedRow();

	if (seleccionado >= 0) {
	    String[] botones = { "Si", "No" };
	    int option = JOptionPane.showOptionDialog(Main.menu.frame, "\u00BFEst\u00E1 seguro en aprobar este pedido?", "Pedido", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, botones, botones[1]);
	    if (option == 0) {
		PedidoE pe = this.alpe.get(seleccionado);
		pe.setEstado("aprobado");

		String s = Auxiliar.aprobarPedido(pe);

		if (s.equals("")) {
		    buscar();
		    if (msjMensaje.getText().equals("El valor que usted busca no existe en la base de datos")) {
			Main.pedido = new Pedido();
			Main.removerTodos();
			Main.menu.frame.add(Main.pedido.panel);
			Main.esconderTodos();
			Main.pedido.panel.setVisible(true);
			Main.pedido.msjMensaje.setText("El pedido ha entrado en " + pe.getEstado());
			Main.pedido.msjMensaje.setEstado(Estado.exito);
		    } else {
			msjMensaje.setText("El pedido ha entrado en " + pe.getEstado());
			msjMensaje.setEstado(Estado.exito);
			msjMensaje.setVisible(true);
		    }
		}

		if (s.equals("1")) {
		    msjMensaje.setText("Error desconocido :C");
		    msjMensaje.setEstado(Estado.error);
		    msjMensaje.setVisible(true);
		}

		if (s.equals("2")) {
		    msjMensaje.setText("Error al conectarse a la base de datos");
		    msjMensaje.setEstado(Estado.error);
		    msjMensaje.setVisible(true);
		}
	    }
	}
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

	if (e.getSource() == btnVer) {
	    Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());
	    verPedido();
	    Main.dialog.ocultar();
	}

	if (e.getSource() == btnRechazar) {
	    Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());
	    rechazarPedido();
	    Main.dialog.ocultar();
	}

	if (e.getSource() == btnAprobar) {
	    Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());
	    aprobarPedido();
	    Main.dialog.ocultar();
	}

    }

    public void mouseReleased(MouseEvent e) {
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
