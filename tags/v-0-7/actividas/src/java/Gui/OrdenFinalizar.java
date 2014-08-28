package Gui;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.SwingConstants;

import api.AButton;
import api.AContainer;
import api.ADateChosser;
import api.ALabel;
import api.APanel;
import api.ATextField;
import api.Estado;
import Entidades.BolsaE;
import Entidades.ExtrusionE;
import Entidades.ImpresionE;
import Entidades.OrdenE;
import Entidades.SelladoE;
import Negocio.BolsaN;

public class OrdenFinalizar implements MouseListener {

    String style = "style='color:#D3362D;'";
    
    APanel panel;
    AContainer extrusion;
    AContainer impresion;
    AContainer sellado;
    AContainer orden;
    ALabel lblFechaInicioE;
    ALabel lblFechaFinE;
    ALabel lblCedulaEmpleadoE;
    ALabel lblFechaInicioI;
    ALabel lblFechaFinI;
    ALabel lblCedulaEmpleadoI;
    ALabel lblFechaInicioS;
    ALabel lblFechaFinS;
    ALabel lblCedulaEmpleadoS;
    ADateChosser dtFechaInicioE;
    ADateChosser dtFechaFinE;
    ADateChosser dtFechaInicioI;
    ADateChosser dtFechaFinI;
    ADateChosser dtFechaInicioS;
    ADateChosser dtFechaFinS;
    ADateChosser dtFechaInicio;
    ATextField txtCedulaEmpleadoE;
    ATextField txtCedulaEmpleadoI;
    ATextField txtCedulaEmpleadoS;
    ATextField txtBolsa;
    ATextField txtNumeroPedido;
    ATextField txtNumeroOrden;
    ATextField txtEstado;
    AButton btnExtrusion;
    AButton btnImpresion;
    AButton btnSellado;
    AButton btnAtras;
    AButton btnBolsa;
    ALabel msjMensaje;
    ALabel lblEstado;
    ALabel lblNumeroPedido;
    ALabel lblNumeroOrden;
    ALabel lblBolsa;
    
    OrdenE oe;
    ExtrusionE ee;
    ImpresionE ie;
    SelladoE se;
    
    int modo;

    public OrdenFinalizar() {

	panel = new APanel(Main.x, 0, 750, 600);
	panel.setTitulo("Ordenes de producci\u00F3n| Informaci\u00F3n");

	extrusion = new AContainer("Extrusi\u00F3n");
	extrusion.setBounds(55, 243, 200, 284);
	panel.add(extrusion);

	lblFechaInicioE = new ALabel("Fecha de inicio:");
	lblFechaInicioE.setBounds(10, 30, 180, 24);
	extrusion.add(lblFechaInicioE);

	dtFechaInicioE = new ADateChosser();
	dtFechaInicioE.setBounds(10, 54, 180, 24);
	dtFechaInicioE.setEnabled(false);
	extrusion.add(dtFechaInicioE);

	lblFechaFinE = new ALabel("Fecha de finalizaci\u00F3n:");
	lblFechaFinE.setBounds(10, 98, 180, 24);
	extrusion.add(lblFechaFinE);

	dtFechaFinE = new ADateChosser();
	dtFechaFinE.setBounds(10, 122, 180, 24);
	dtFechaFinE.setEnabled(false);
	extrusion.add(dtFechaFinE);

	lblCedulaEmpleadoE = new ALabel("C\u00E9dula del empleado:");
	lblCedulaEmpleadoE.setBounds(10, 166, 180, 24);
	extrusion.add(lblCedulaEmpleadoE);

	txtCedulaEmpleadoE = new ATextField();
	txtCedulaEmpleadoE.setBounds(10, 190, 180, 24);
	txtCedulaEmpleadoE.setEnabled(false);
	extrusion.add(txtCedulaEmpleadoE);

	btnExtrusion = new AButton("Mas informaci\u00F3n");
	btnExtrusion.setBounds(10, 234, 180, 30);
	btnExtrusion.addMouseListener(this);
	extrusion.add(btnExtrusion);

	impresion = new AContainer("Impresi\u00F3n");
	impresion.setBounds(275, 243, 200, 284);
	panel.add(impresion);

	lblFechaInicioI = new ALabel("Fecha de inicio:");
	lblFechaInicioI.setBounds(10, 30, 180, 24);
	impresion.add(lblFechaInicioI);

	dtFechaInicioI = new ADateChosser();
	dtFechaInicioI.setBounds(10, 54, 180, 24);
	dtFechaInicioI.setEnabled(false);
	impresion.add(dtFechaInicioI);

	lblFechaFinI = new ALabel("Fecha de finalizaci\u00F3n:");
	lblFechaFinI.setBounds(10, 98, 180, 24);
	impresion.add(lblFechaFinI);

	dtFechaFinI = new ADateChosser();
	dtFechaFinI.setBounds(10, 122, 180, 24);
	dtFechaFinI.setEnabled(false);
	impresion.add(dtFechaFinI);

	lblCedulaEmpleadoI = new ALabel("C\u00E9dula del empleado:");
	lblCedulaEmpleadoI.setBounds(10, 166, 180, 24);
	impresion.add(lblCedulaEmpleadoI);

	txtCedulaEmpleadoI = new ATextField();
	txtCedulaEmpleadoI.setBounds(10, 190, 180, 24);
	txtCedulaEmpleadoI.setEnabled(false);
	impresion.add(txtCedulaEmpleadoI);

	btnImpresion = new AButton("Mas informaci\u00F3n");
	btnImpresion.setBounds(10, 234, 180, 30);
	btnImpresion.addMouseListener(this);
	impresion.add(btnImpresion);

	sellado = new AContainer("Sellado");
	sellado.setBounds(495, 243, 200, 284);
	panel.add(sellado);

	lblFechaInicioS = new ALabel("Fecha de inicio:");
	lblFechaInicioS.setBounds(10, 30, 180, 24);
	sellado.add(lblFechaInicioS);

	dtFechaInicioS = new ADateChosser();
	dtFechaInicioS.setBounds(10, 54, 180, 24);
	dtFechaInicioS.setEnabled(false);
	sellado.add(dtFechaInicioS);

	lblFechaFinS = new ALabel("Fecha de finalizaci\u00F3n:");
	lblFechaFinS.setBounds(10, 98, 180, 24);
	sellado.add(lblFechaFinS);

	dtFechaFinS = new ADateChosser();
	dtFechaFinS.setBounds(10, 122, 180, 24);
	dtFechaFinS.setEnabled(false);
	sellado.add(dtFechaFinS);

	lblCedulaEmpleadoS = new ALabel("C\u00E9dula del empleado:");
	lblCedulaEmpleadoS.setBounds(10, 166, 180, 24);
	sellado.add(lblCedulaEmpleadoS);

	txtCedulaEmpleadoS = new ATextField();
	txtCedulaEmpleadoS.setBounds(10, 190, 180, 24);
	txtCedulaEmpleadoS.setEnabled(false);
	sellado.add(txtCedulaEmpleadoS);

	btnSellado = new AButton("Mas informaci\u00F3n");
	btnSellado.setBounds(10, 234, 180, 30);
	btnSellado.addMouseListener(this);
	sellado.add(btnSellado);
	
	btnAtras = new AButton("Regresar");
	btnAtras.setBounds(316, 538, 130, 30);
	btnAtras.addMouseListener(this);
	panel.add(btnAtras);
	
	orden = new AContainer("Orden de producci\u00F3n");
	orden.setBounds(55, 50, 640, 182);
	panel.add(orden);
	
	lblNumeroOrden = new ALabel("<html><body align='right'><b " + style + "></b>C\u00F3digo orden de producci\u00F3n:</body></html>");
	lblNumeroOrden.setBounds(30, 20, 120, 34);
	lblNumeroOrden.setHorizontalAlignment(SwingConstants.RIGHT);
	orden.add(lblNumeroOrden);

	txtNumeroOrden = new ATextField();
	txtNumeroOrden.setBounds(170, 30, 120, 24);
	txtNumeroOrden.setEnabled(false);
	orden.add(txtNumeroOrden);


	lblNumeroPedido = new ALabel("<html><body><b " + style + "></b>C\u00F3digo del pedido:</body></html>");
	lblNumeroPedido.setBounds(310, 30, 120, 24);
	lblNumeroPedido.setHorizontalAlignment(SwingConstants.RIGHT);
	orden.add(lblNumeroPedido);

	txtNumeroPedido = new ATextField();
	txtNumeroPedido.setBounds(450, 30, 120, 24);
	txtNumeroPedido.setEnabled(false);
	orden.add(txtNumeroPedido);

	lblBolsa = new ALabel("<html><body><b " + style + "></b>Referencia bolsa:</body></html>");
	lblBolsa.setBounds(30, 74, 120, 24);
	lblBolsa.setHorizontalAlignment(SwingConstants.RIGHT);
	orden.add(lblBolsa);

	txtBolsa = new ATextField();
	txtBolsa.setBounds(170, 74, 120, 24);
	txtBolsa.setEnabled(false);
	orden.add(txtBolsa);

	btnBolsa = new AButton("Ver bolsa");
	btnBolsa.setBounds(310, 74, 120, 24);
	btnBolsa.addMouseListener(this);
	orden.add(btnBolsa);
	
	lblEstado = new ALabel("<html><body><b " + style + "></b>Estado actual:</body></html>");
	lblEstado.setBounds(30, 118, 120, 24);
	lblEstado.setHorizontalAlignment(SwingConstants.RIGHT);
	orden.add(lblEstado);
	
	txtEstado = new ATextField();
	txtEstado.setBounds(170, 118, 120, 24);
	txtEstado.setEnabled(false);
	orden.add(txtEstado);
	
	msjMensaje = new ALabel("");
	msjMensaje.setHorizontalAlignment(SwingConstants.CENTER);
	msjMensaje.setBounds(68, 579, 611, 20);
	msjMensaje.setFont(new Font("Calibri", Font.PLAIN, 18));
	panel.add(msjMensaje);

	this.oe = null;
	this.ee = null;
	this.ie = null;
	this.se = null;
    }
    
    public void setModo(int modo){
	this.modo = modo;
    }

    public void setDatos(OrdenE oe, ExtrusionE ee, ImpresionE ie, SelladoE se) {
	this.oe = oe;
	this.ee = ee;
	this.ie = ie;
	this.se = se;
	
	txtNumeroOrden.setText(oe.getNumeroOrden() + "");
	txtNumeroPedido.setText(oe.getNumeroPedido() + "");
	txtBolsa.setText(oe.getBolsa_referencia() + "");
	txtEstado.setText(oe.getEstado());

	if (ee != null) {
	    dtFechaInicioE.setDate(ee.getFechaInicio());
	    dtFechaFinE.setDate(ee.getFechaFin());
	    txtCedulaEmpleadoE.setText(ee.getEmpleado_cedula());
	} else {
	    extrusion.setVisible(false);
	}

	if (ie != null) {
	    dtFechaInicioI.setDate(ie.getFechaInicio());
	    dtFechaFinI.setDate(ie.getFechaFin());
	    txtCedulaEmpleadoI.setText(ie.getEmpleado_cedula());
	} else {
	    impresion.setVisible(false);
	}

	if (se != null) {
	    dtFechaInicioS.setDate(se.getFechaInicio());
	    dtFechaFinS.setDate(se.getFechaFin());
	    txtCedulaEmpleadoS.setText(se.getEmpleado_cedula());
	} else {
	    sellado.setVisible(false);
	}
    }
    
    public void verBolsa(){
	ArrayList<BolsaE> albe = new BolsaN().buscarBolsa("referencia", this.oe.getBolsa_referencia() + "", true, true);

	if (albe == null) {
	    msjMensaje.setText("Error desconocido");
	    msjMensaje.setEstado(Estado.ERROR);
	    msjMensaje.setVisible(true);
	} else {
	    if (albe.size() == 0) {
		msjMensaje.setText("Error al conectarse a la base de datos");
		msjMensaje.setEstado(Estado.ERROR);
		msjMensaje.setVisible(true);
	    } else {
		Main.caBolsa = new CABolsa();
		Main.removerTodos();
		Main.menu.frame.getContentPane().add(Main.caBolsa.panel);
		Main.esconderTodos();
		Main.caBolsa.setModo(4);
		Main.caBolsa.visible(false, true, false, true);
		Main.caBolsa.setDatos(albe.get(0));
		Main.caBolsa.panel.setVisible(true);
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
	if(e.getSource() == btnAtras){
	    Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());
	    Main.removerTodos();
	    Main.menu.frame.getContentPane().add(Main.buscarOrdenes.panel);
	    Main.esconderTodos();
	    if(modo == 0){
		Main.buscarOrdenes.buscar();
	    }else{
		Main.buscarOrdenes.ordenes_finalizadas();
	    }
	    Main.buscarOrdenes.panel.setVisible(true);
	    Main.dialog.ocultar();
	}
	
	if(e.getSource() == btnExtrusion){
	    Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());
	    Main.ordenExtrusor = new OrdenExtrusor();
	    Main.removerTodos();
	    Main.menu.frame.getContentPane().add(Main.ordenExtrusor.panel);
	    Main.esconderTodos();
	    Main.ordenExtrusor.setDatos(ee, oe);
	    Main.ordenExtrusor.setModo(1);
	    Main.ordenExtrusor.allDisabled();
	    Main.ordenExtrusor.panel.setVisible(true);
	    Main.dialog.ocultar();
	}
	
	if(e.getSource() == btnImpresion){
	    Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());
	    Main.ordenImpresor = new OrdenImpresor();
	    Main.removerTodos();
	    Main.menu.frame.getContentPane().add(Main.ordenImpresor.panel);
	    Main.esconderTodos();
	    Main.ordenImpresor.setDatos(ie, oe);
	    Main.ordenImpresor.setModo(1);
	    Main.ordenImpresor.allDisabled();
	    Main.ordenImpresor.panel.setVisible(true);
	    Main.dialog.ocultar();
	}
	
	if(e.getSource() == btnSellado){
	    Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());
	    Main.ordenSellado = new OrdenSellado();
	    Main.removerTodos();
	    Main.menu.frame.getContentPane().add(Main.ordenSellado.panel);
	    Main.esconderTodos();
	    Main.ordenSellado.setDatos(se, oe);
	    Main.ordenSellado.setModo(1);
	    Main.ordenSellado.allDisabled();
	    Main.ordenSellado.panel.setVisible(true);
	    Main.dialog.ocultar();
	}
	
	if(e.getSource() == btnBolsa){
	    Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());
	    verBolsa();
	    Main.dialog.ocultar();
	}
    }

    public void mouseReleased(MouseEvent e) {
    }

}
