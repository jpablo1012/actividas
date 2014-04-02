package Gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import Entidades.ExtrusionE;
import Entidades.ImpresionE;
import Entidades.OrdenE;
import Entidades.SelladoE;
import Negocio.ExtrusionN;
import Negocio.ImpresionN;
import Negocio.OrdenN;
import Negocio.SelladoN;
import api.*;

public class BuscarOrdenes implements MouseListener, KeyListener {

    private int modo;// 1 = extrusion, 2 = impresion, 3 = sellado

    APanel panel;
    AContainer busq;

    ALabel lblOrdenes;
    ALabel lblBuscar;
    ALabel msjMensaje;
    ATextField txtBuscar;
    AComboBox comBuscar;
    AButton btnBuscar;
    AScrollPanel resultado;
    ATable tabla;
    DefaultTableModel dtm;
    AButton btnVer, btnNueva;

    ArrayList<OrdenE> aloe;
    ArrayList<ExtrusionE> alee;
    ArrayList<ImpresionE> alie;
    ArrayList<SelladoE> alse;

    String[] strBuscar = { "-Seleccione-", "Numero de la orden", "Numero del pedido", "Referencia de la bolsa", "Estado", "Texto de la bolsa", "Tipo de cantidad", "Cantidad", "Extrusi\u00F3n", "Impresi\u00F3n", "Sellado" };
    String[] strBuscarV = { "", "numeroOrden", "numeroPedido", "bolsa_referencia", "estado", "referencia", "tipo_cantidad", "cantidad", "extrusion", "impresion", "sellado" };

    public BuscarOrdenes() {
	panel = new APanel(Main.x, 0, 750, 600);

	lblOrdenes = new ALabel("Ordenes de producci\u00F3n| Buscar");
	lblOrdenes.setFont(new Font("Calibri", Font.PLAIN, 24));
	lblOrdenes.setForeground(Colores.titulo_normal);
	lblOrdenes.setBounds(10, 0, 460, 50);
	panel.add(lblOrdenes);

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

	comBuscar = new AComboBox(strBuscar);
	comBuscar.setValores(strBuscarV);
	comBuscar.setBounds(117, 30, 150, 24);
	busq.add(comBuscar);

	btnBuscar = new AButton("Buscar pedido");
	btnBuscar.setBounds(200, 147, 130, 30);
	btnBuscar.addMouseListener(this);
	busq.add(btnBuscar);

	msjMensaje = new ALabel("");
	msjMensaje.setHorizontalAlignment(SwingConstants.CENTER);
	msjMensaje.setBounds(0, 530, panel.getWidth(), 20);
	msjMensaje.setFont(new Font("Calibri", Font.PLAIN, 18));
	panel.add(msjMensaje);

	resultado = new AScrollPanel("Resultado de la b\u00FAsqueda");
	resultado.setBounds(75, 60, 600, 400);
	resultado.setVisible(false);
	panel.add(resultado);

	tabla = new ATable();
	tabla.setPreferredSize(new Dimension(650, 340));
	resultado.setViewportView(tabla);

	btnVer = new AButton("M\u00E1s informaci\u00F3n");
	btnVer.setBounds(315, 480, 130, 30);
	btnVer.addMouseListener(this);
	btnVer.setVisible(false);
	panel.add(btnVer);

	btnNueva = new AButton("Nueva b\u00FAsqueda");
	btnNueva.setBounds(128, 480, 130, 30);
	btnNueva.setVisible(false);
	btnNueva.addMouseListener(this);
	panel.add(btnNueva);

	this.aloe = null;
	this.alee = null;
	this.alie = null;
	this.alse = null;
    }

    public void visibleBuscar(boolean b) {
	resultado.setVisible(b);
	btnVer.setVisible(b);
	busq.setVisible(!b);

	if (modo == 0) {
	    btnNueva.setVisible(b);
	}

    }

    public String buscar() {

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

	msjMensaje.setText("");
	modo = 0;
	this.aloe = new OrdenN().buscarOrden(comBuscar.getValor(variable), valor, false);

	if (aloe == null) {
	    return "1";
	} else {
	    int alto = 0;
	    if (aloe.size() == 0) {
		return "1";
	    } else {
		Object[][] result = new Object[aloe.size()][13];

		for (int i = 0; i < aloe.size(); i++) {
		    OrdenE oe = aloe.get(i);
		    String extrusion = "No", impresion = "No", sellado = "No";
		    result[i][0] = oe.getNumeroOrden();
		    result[i][1] = oe.getNumeroPedido();
		    result[i][2] = oe.getBolsa_referencia();
		    result[i][3] = oe.getEstado();
		    result[i][4] = oe.getReferencia();
		    result[i][5] = oe.getTipo_cantidad();
		    result[i][6] = oe.getCantidad();
		    result[i][7] = oe.getFecha_inicio();
		    result[i][8] = oe.getFecha_fin();
		    result[i][9] = oe.getFecha_plazo();

		    if (oe.isExtrusion()) {
			extrusion = "Si";
		    }

		    if (oe.isImpresion()) {
			impresion = "Si";
		    }

		    if (oe.isSellado()) {
			sellado = "Si";
		    }

		    result[i][10] = extrusion;
		    result[i][11] = impresion;
		    result[i][12] = sellado;

		    alto += 25;
		}

		resultado.setText("<html><body>Ordenes de producci\u00F3n encontradas: <b>" + this.aloe.size() + "</b></body></html>");
		dtm = new DefaultTableModel(result, new String[] { "# Orden", "# Pedido", "Ref. bolsa", "Estado", "Texto de la bolsa", "Cantidad en", "Cantidad", "Fecha de inicio", "Fecha de finalizaci\u00F3n", "Plazo hasta", "Extrusi\u00F3n", "Impresi\u00F3n", "Sellado" });

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
		btnVer.setBounds(375, 489, 130, 30);
		btnNueva.setBounds(225, 489, 130, 30);
	    }
	}

	return "";
    }

    public String pendientes_impresion() {
	msjMensaje.setText("");
	modo = 2;
	this.alie = new ImpresionN().buscarImpresion("estado", "ejecutando", false);
	this.aloe = new OrdenN().buscarOrden("estado", "impresi\u00F3n", false);

	if (alie == null || aloe == null) {
	    return "1";
	} else {
	    int alto = 0;
	    int cont = 0;
	    if (alie.size() == 0 || aloe.size() == 0) {
		return "1";
	    } else {
		Object[][] result = new Object[alie.size()][8];
		for (int i = 0; i < aloe.size(); i++) {
		    for (int j = 0; j < alie.size(); j++) {
			OrdenE oe = aloe.get(i);
			ImpresionE ie = alie.get(j);

			if (oe.getNumeroOrden() == ie.getNumeroOrden()) {

			    result[i][0] = ie.getCodigo();
			    result[i][1] = ie.getNumeroOrden();
			    result[i][2] = ie.getBolsa_referencia();
			    result[i][3] = oe.getTipo_cantidad();
			    result[i][4] = oe.getCantidad();
			    result[i][5] = oe.getFecha_plazo();

			    cont++;
			    alto += 25;
			}
			resultado.setText("<html><body>Procesos de impresi\u00F3n encontrados: <b>" + cont + "</b></body></html>");
			dtm = new DefaultTableModel(result, new String[] { "C\u00F3digo", "# orden", "Ref. bolsa", "Cantidad en", "Cantidad", "Plazo hasta" });
			tabla.setModel(dtm);
			tabla.setPreferredSize(new Dimension(650, alto));
			tabla.repaint();
			lblOrdenes.setText("Ordenes de producci\u00F3n| Impresi\u00F3n");
			visibleBuscar(true);
		    }
		}
	    }
	}
	return "";
    }

    public String pendientes_extrsuion() {
	msjMensaje.setText("");
	modo = 1;
	this.alee = new ExtrusionN().buscarExtrusion("estado", "ejecutando", false);
	this.aloe = new OrdenN().buscarOrden("estado", "extrusi\u00F3n", false);

	if (alee == null || aloe == null) {
	    return "1";
	} else {
	    int alto = 0;
	    int cont = 0;
	    if (alee.size() == 0 || aloe.size() == 0) {
		return "1";
	    } else {
		Object[][] result = new Object[alee.size()][6];
		for (int i = 0; i < aloe.size(); i++) {
		    for (int j = 0; j < alee.size(); j++) {
			OrdenE oe = aloe.get(i);
			ExtrusionE ee = alee.get(j);

			if (oe.getNumeroOrden() == ee.getNumeroOrden()) {
			    result[i][0] = ee.getCodigo();
			    result[i][1] = ee.getNumeroOrden();
			    result[i][2] = ee.getBolsa_referencia();
			    result[i][3] = oe.getTipo_cantidad();
			    result[i][4] = oe.getCantidad();
			    result[i][5] = oe.getFecha_plazo();

			    cont++;
			    alto += 25;
			}

			resultado.setText("<html><body>Procesos de extrusi\u00F3n encontrados: <b>" + cont + "</b></body></html>");
			dtm = new DefaultTableModel(result, new String[] { "C\u00F3digo", "# orden", "Ref. bolsa", "Cantidad en", "Cantidad", "Plazo hasta" });
			tabla.setModel(dtm);
			tabla.setPreferredSize(new Dimension(650, alto));
			tabla.repaint();
			lblOrdenes.setText("Ordenes de producci\u00F3n| Extrusi\u00F3n");
			visibleBuscar(true);
		    }
		}
	    }
	}
	return "";
    }

    public String ordenes_finalizadas() {
	msjMensaje.setText("");
	modo = 4;
	this.aloe = new OrdenN().buscarOrden("estado", "finalizado", true);
	if (aloe == null) {
	    return "1";
	} else if (aloe.size() == 0) {
	    return "1";
	} else {
	    int alto = 0;
	    int cont = 0;
	    Object[][] result = new Object[aloe.size()][13];
	    for (int i = 0; i < aloe.size(); i++) {
		OrdenE oe = aloe.get((aloe.size() - 1) - i);
		String extrusion = "No", impresion = "No", sellado = "No";
		result[i][0] = oe.getNumeroOrden();
		result[i][1] = oe.getNumeroPedido();
		result[i][2] = oe.getBolsa_referencia();
		result[i][3] = oe.getEstado();
		result[i][4] = oe.getReferencia();
		result[i][5] = oe.getTipo_cantidad();
		result[i][6] = oe.getCantidad();
		result[i][7] = oe.getFecha_inicio();
		result[i][8] = oe.getFecha_fin();
		result[i][9] = oe.getFecha_plazo();

		if (oe.isExtrusion()) {
		    extrusion = "Si";
		}

		if (oe.isImpresion()) {
		    impresion = "Si";
		}

		if (oe.isSellado()) {
		    sellado = "Si";
		}

		result[i][10] = extrusion;
		result[i][11] = impresion;
		result[i][12] = sellado;

		alto += 25;
	    }

	    resultado.setText("<html><body>Ordenes de producci\u00F3n encontradas: <b>" + this.aloe.size() + "</b></body></html>");
	    dtm = new DefaultTableModel(result, new String[] { "# Orden", "# Pedido", "Ref. bolsa", "Estado", "Texto de la bolsa", "Cantidad en", "Cantidad", "Fecha de inicio", "Fecha de finalizaci\u00F3n", "Plazo hasta", "Extrusi\u00F3n", "Impresi\u00F3n", "Sellado" });

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
            lblOrdenes.setText("Ordenes de producci\u00F3n| Finalizadas");
	}
	
	return "";
    }

    public String pendientes_sellado() {
	msjMensaje.setText("");
	modo = 3;
	this.aloe = new OrdenN().buscarOrden("estado", "sellado", true);
	this.alse = new SelladoN().buscarSellado("estado", "ejecutando", true);

	if (aloe == null || alse == null) {
	    return "1";
	} else {
	    int alto = 0;
	    int cont = 0;
	    if (aloe.size() == 0 || alse.size() == 0) {
		return "1";
	    } else {
		Object[][] result = new Object[alse.size()][6];
		for (int i = 0; i < aloe.size(); i++) {
		    for (int j = 0; j < alse.size(); j++) {
			OrdenE oe = aloe.get(i);
			SelladoE se = alse.get(j);

			if (oe.getNumeroOrden() == se.getNumeroOrden()) {
			    result[i][0] = se.getCodigo();
			    result[i][1] = se.getNumeroOrden();
			    result[i][2] = se.getBolsa_referencia();
			    result[i][3] = oe.getNumeroOrden();
			    result[i][4] = oe.getCantidad();
			    result[i][5] = oe.getFecha_plazo();

			    cont++;
			    alto += 25;
			}
		    }
		}

		resultado.setText("<html><body>Procesos de sellado encontrados: <b>" + cont + "</b></body></html>");
		dtm = new DefaultTableModel(result, new String[] { "C\u00F3digo", "# orden", "Ref. bolsa", "Cantidad en", "Cantidad", "Plazo hasta" });
		tabla.setModel(dtm);
		tabla.setPreferredSize(new Dimension(650, alto));
		tabla.repaint();
		lblOrdenes.setText("Ordenes de producci\u00F3n| Sellado");
		visibleBuscar(true);
	    }
	}

	return "";
    }

    public void ver() {
	int seleccionado = tabla.getSelectedRow();
	if (seleccionado >= 0) {
	    if (modo == 1) {
		Main.ordenExtrusor = new OrdenExtrusor();
		Main.removerTodos();
		Main.menu.frame.getContentPane().add(Main.ordenExtrusor.panel);
		Main.esconderTodos();
		Main.ordenExtrusor.setDatos(alee.get(seleccionado), aloe.get(seleccionado));
		Main.ordenExtrusor.panel.setVisible(true);
	    }

	    if (modo == 2) {
		Main.ordenImpresor = new OrdenImpresor();
		Main.removerTodos();
		Main.menu.frame.getContentPane().add(Main.ordenImpresor.panel);
		Main.esconderTodos();
		Main.ordenImpresor.setDatos(alie.get(seleccionado), aloe.get(seleccionado));
		Main.ordenImpresor.panel.setVisible(true);
	    }

	    if (modo == 3) {
		Main.ordenSellado = new OrdenSellado();
		Main.removerTodos();
		Main.menu.frame.getContentPane().add(Main.ordenSellado.panel);
		Main.esconderTodos();
		Main.ordenSellado.setDatos(alse.get(seleccionado), aloe.get(seleccionado));
		Main.ordenSellado.panel.setVisible(true);
	    }

	    if (modo == 0 || modo == 4) {
		ExtrusionE ee = null;
		ImpresionE ie = null;
		SelladoE se = null;
                int select = seleccionado;
                
                if(modo == 4){
                    select = (this.aloe.size() - 1) - seleccionado;
                }
                
		String valor = this.aloe.get(select).getNumeroOrden() + "";
		ArrayList<ExtrusionE> alee = new ExtrusionN().buscarExtrusion("numeroOrden", valor, true);
		ArrayList<ImpresionE> alie = new ImpresionN().buscarImpresion("numeroOrden", valor, true);
		ArrayList<SelladoE> alse = new SelladoN().buscarSellado("numeroOrden", valor, true);

		try {
		    ee = alee.get(0);
		} catch (Exception e) {
		}

		try {
		    ie = alie.get(0);
		} catch (Exception e) {
		}

		try {
		    se = alse.get(0);
		} catch (Exception e) {
		}

		Main.ordenFinalizar = new OrdenFinalizar();
		Main.removerTodos();
		Main.menu.frame.getContentPane().add(Main.ordenFinalizar.panel);
		Main.esconderTodos();
		Main.ordenFinalizar.setDatos(this.aloe.get(select), ee, ie, se);
		Main.ordenFinalizar.setModo(modo);
		Main.ordenFinalizar.panel.setVisible(true);
	    }
	} else {
	    msjMensaje.setText("Seleccione una fila para ver mas informaci\u00F3n");
	    msjMensaje.setEstado(Estado.error);
	    msjMensaje.setVisible(true);
	}

    }

    private boolean comprobar() {
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

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
	if (e.getSource() == btnVer) {
	    Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());
	    ver();
	    Main.dialog.ocultar();
	}

	if (e.getSource() == btnBuscar) {
	    Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());
	    if (comprobar()) {
		String s = buscar();
		if (s.equals("1")) {
		    visibleBuscar(false);
		    msjMensaje.setText("El dato que usted busca no existe en la base de datos");
		    msjMensaje.setEstado(Estado.error);
		    msjMensaje.setVisible(true);
		}
	    }
	    Main.dialog.ocultar();
	}

	if (e.getSource() == btnNueva) {
	    visibleBuscar(false);
	    txtBuscar.setText("");
	    comBuscar.setSelectedIndex(0);
	    msjMensaje.setText("");
	}
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void keyPressed(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

}
