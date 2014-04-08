package Gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Entidades.BolsaE;
import Entidades.ExtrusionE;
import Entidades.OrdenE;
import Negocio.Auxiliar;
import Negocio.BolsaN;
import Negocio.ExtrusionN;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import api.AButton;
import api.AContainer;
import api.ADateChosser;
import api.AEditorPane;
import api.ALabel;
import api.APanel;
import api.AScrollPanel;
import api.ASpinner;
import api.ATextField;
import api.Colores;
import api.Estado;

public class OrdenExtrusor implements MouseListener {

    String style = "style='color:#D3362D;'";

    APanel panel;
    AContainer ordenProduccion;
    AContainer extrusion;
    ALabel lblCodigo;
    ALabel lblOrden;
    ALabel lblBolsa;
    ALabel lblDesperdicio;
    ALabel lblFechaInicio;
    ALabel lblFechaFin;
    ALabel lblNota;
    ALabel lblRollo;
    ALabel lblMaquina;
    ALabel lblCantidad;
    ALabel lblTipoCantidad;
    ALabel lblObligatorio;
    ALabel msjCodigo;
    ALabel msjOrden;
    ALabel msjBolsa;
    ALabel msjFechaInicio;
    ALabel msjFechaFin;
    ALabel msjMaquina;
    ATextField txtCodigo;
    ATextField txtOrden;
    ATextField txtBolsa;
    ATextField txtMaquina;
    ATextField txtTipoCantidad;
    ASpinner spnDesperdicio;
    ASpinner spnRollo;
    ASpinner spnCantidad;
    AEditorPane editNota;
    ADateChosser dtFechaFin;
    ADateChosser dtFechaInicio;
    AButton btnBolsa;
    AScrollPanel nota;
    AButton btnGuardar;
    AButton btnFinalizar;
    AButton btnRegresar;
    ALabel msjMensaje;

    ExtrusionE ee;
    OrdenE oe;
    
    String fecha = "<html><body><b " + style + "></b>Fecha de finalizaci\u00F3n:</body></html>";

    int modo = 0;

    public OrdenExtrusor() {
	panel = new APanel(Main.x, 0, 750, 600);
	panel.setTitulo("Ordenes de producci\u00F3n| Extrusi\u00F3n");

	if(Main.menu.evaluar(Main.menu.getUsuario().getTipo()) == 0){
	    fecha = "<html><body><b " + style + "></b>Plazo hasta:</body></html>";
	}

	ordenProduccion = new AContainer("Orden de producci\u00F3n");
	ordenProduccion.setBounds(60, 41, 630, 138);
	panel.add(ordenProduccion);

	lblOrden = new ALabel("<html><body align='right'><b " + style + "></b>Codigo orden de producci\u00F3n:</body></html>");
	lblOrden.setBounds(30, 20, 120, 34);
	lblOrden.setHorizontalAlignment(SwingConstants.RIGHT);
	ordenProduccion.add(lblOrden);

	txtOrden = new ATextField();
	txtOrden.setBounds(170, 30, 120, 24);
	txtOrden.setEnabled(false);
	ordenProduccion.add(txtOrden);

	msjOrden = new ALabel("");
	msjOrden.setBounds(170, 54, 120, 20);
	ordenProduccion.add(msjOrden);

	lblCodigo = new ALabel("<html><body><b " + style + "></b>Codigo extrusi\u00F3n:</body></html>");
	lblCodigo.setBounds(310, 30, 120, 24);
	lblCodigo.setHorizontalAlignment(SwingConstants.RIGHT);
	ordenProduccion.add(lblCodigo);

	txtCodigo = new ATextField();
	txtCodigo.setBounds(450, 30, 120, 24);
	txtCodigo.setEnabled(false);
	ordenProduccion.add(txtCodigo);

	msjCodigo = new ALabel("");
	msjCodigo.setBounds(450, 100, 120, 20);
	ordenProduccion.add(msjCodigo);

	lblBolsa = new ALabel("<html><body><b " + style + "></b>Referencia bolsa:</body></html>");
	lblBolsa.setBounds(30, 94, 120, 24);
	lblBolsa.setHorizontalAlignment(SwingConstants.RIGHT);
	ordenProduccion.add(lblBolsa);

	txtBolsa = new ATextField();
	txtBolsa.setBounds(170, 94, 120, 24);
	txtBolsa.setEnabled(false);
	ordenProduccion.add(txtBolsa);

	msjBolsa = new ALabel("");
	msjBolsa.setBounds(170, 118, 120, 20);
	ordenProduccion.add(msjBolsa);

	btnBolsa = new AButton("Ver bolsa");
	btnBolsa.setBounds(310, 94, 120, 24);
	btnBolsa.addMouseListener(this);
	ordenProduccion.add(btnBolsa);

	extrusion = new AContainer("Extrusi\u00F3n");
	extrusion.setBounds(60, 199, 630, 282);
	panel.add(extrusion);

	lblFechaInicio = new ALabel("<html><body><b " + style + "></b>Fecha de inicio:</body></html>");
	lblFechaInicio.setBounds(30, 30, 120, 24);
	lblFechaInicio.setHorizontalAlignment(SwingConstants.RIGHT);
	extrusion.add(lblFechaInicio);

	dtFechaInicio = new ADateChosser();
	dtFechaInicio.setBounds(170, 30, 120, 24);
	dtFechaInicio.setEnabled(false);
	extrusion.add(dtFechaInicio);

	msjFechaInicio = new ALabel("");
	msjFechaInicio.setBounds(170, 54, 120, 20);
	// extrusion.add(msjFechaInicio);

	lblFechaFin = new ALabel(fecha);
	lblFechaFin.setHorizontalAlignment(SwingConstants.RIGHT);
	lblFechaFin.setBounds(310, 30, 120, 24);
	extrusion.add(lblFechaFin);

	dtFechaFin = new ADateChosser();
	dtFechaFin.setBounds(450, 30, 120, 24);
	dtFechaFin.setEnabled(false);
	extrusion.add(dtFechaFin);

	msjFechaFin = new ALabel("");
	msjFechaFin.setBounds(450, 54, 120, 20);
	// extrusion.add(msjFechaFin);

	lblTipoCantidad = new ALabel("<html><body><b " + style + "></b>Tipo de cantidad:</body></html>");
	lblTipoCantidad.setBounds(30, 74, 120, 24);
	lblTipoCantidad.setHorizontalAlignment(SwingConstants.RIGHT);
	extrusion.add(lblTipoCantidad);

	txtTipoCantidad = new ATextField();
	txtTipoCantidad.setBounds(170, 74, 120, 24);
	txtTipoCantidad.setEnabled(false);
	extrusion.add(txtTipoCantidad);

	lblCantidad = new ALabel("<html><body><b " + style + "></b>Cantidad:</body></html>");
	lblCantidad.setBounds(310, 74, 120, 24);
	lblCantidad.setHorizontalAlignment(SwingConstants.RIGHT);
	extrusion.add(lblCantidad);

	spnCantidad = new ASpinner();
	spnCantidad.setBounds(450, 74, 70, 24);
	spnCantidad.setEnabled(false);
	extrusion.add(spnCantidad);

	lblRollo = new ALabel("<html><body><b " + style + ">*</b>Rollos usados:</body></html>");
	lblRollo.setHorizontalAlignment(SwingConstants.RIGHT);
	lblRollo.setBounds(30, 118, 120, 24);
	extrusion.add(lblRollo);

	spnRollo = new ASpinner(1, 0, 1000);
	spnRollo.setBounds(170, 118, 70, 24);
	extrusion.add(spnRollo);

	lblMaquina = new ALabel("<html><body><b " + style + ">*</b>Maquina usada:</body></html>");
	lblMaquina.setBounds(310, 118, 120, 24);
	lblMaquina.setHorizontalAlignment(SwingConstants.RIGHT);
	extrusion.add(lblMaquina);

	txtMaquina = new ATextField();
	txtMaquina.setPlaceHolder("Maquina usada");
	txtMaquina.setBounds(450, 118, 120, 24);
	extrusion.add(txtMaquina);

	msjMaquina = new ALabel("");
	msjMaquina.setBounds(450, 98, 120, 20);
	extrusion.add(msjMaquina);

	lblDesperdicio = new ALabel("<html><body><b " + style + "></b>Desperdicio (kilos):</body></html>");
	lblDesperdicio.setBounds(30, 162, 120, 24);
	lblDesperdicio.setHorizontalAlignment(SwingConstants.RIGHT);
	extrusion.add(lblDesperdicio);

	spnDesperdicio = new ASpinner(0, 0, 100000);
	spnDesperdicio.setBounds(170, 162, 70, 24);
	extrusion.add(spnDesperdicio);

	lblNota = new ALabel("<html><body><b " + style + "></b>Nota:</body></html>");
	lblNota.setHorizontalAlignment(SwingConstants.RIGHT);
	lblNota.setBounds(30, 206, 120, 24);
	extrusion.add(lblNota);

	nota = new AScrollPanel("");
	nota.setBounds(170, 206, 400, 60);
	nota.setBorder(new MatteBorder(1, 1, 1, 1, Colores.BORDE_CAJATEXTO));
	nota.setHorizontalScrollBarPolicy(AScrollPanel.HORIZONTAL_SCROLLBAR_NEVER);
	extrusion.add(nota);

	editNota = new AEditorPane();
	editNota.setPlaceHolder("");
	editNota.setBorder(null);
	editNota.setBounds(0, 0, 400, 54);
	editNota.setPlaceHolder("Nota...");
	nota.setViewportView(editNota);

	btnGuardar = new AButton("Guardar progreso");
	btnGuardar.setBounds(315, 501, 130, 30);
	btnGuardar.addMouseListener(this);
	panel.add(btnGuardar);

	btnFinalizar = new AButton("Finalizar extrusi\u00F3n");
	btnFinalizar.setBounds(465, 501, 130, 30);
	btnFinalizar.addMouseListener(this);
	panel.add(btnFinalizar);

	btnRegresar = new AButton("Regresar");
	btnRegresar.setBounds(165, 501, 130, 30);
	btnRegresar.addMouseListener(this);
	panel.add(btnRegresar);

	msjMensaje = new ALabel("");
	msjMensaje.setHorizontalAlignment(SwingConstants.CENTER);
	msjMensaje.setBounds(0, 551, panel.getWidth(), 20);
	msjMensaje.setFont(new Font("Calibri", Font.PLAIN, 18));
	panel.add(msjMensaje);
	
	lblObligatorio = new ALabel("<html><body><b " + style + ">*</b>Campo obligatorio</body></html>");
	lblObligatorio.setVerticalAlignment(SwingConstants.TOP);
	lblObligatorio.setBounds(60, 480, 120, 20);
	panel.add(lblObligatorio);

	ee = null;
	oe = null;
    }
    
    public void allDisabled(){
	spnRollo.setEnabled(false);
	txtMaquina.setEnabled(false);
	spnDesperdicio.setEnabled(false);
	editNota.setEnabled(false);
	
	btnFinalizar.setVisible(false);
	btnGuardar.setVisible(false);
	
	btnRegresar.setBounds(310, 501, 130, 30);
    }
    
    public void setModo(int modo){
	this.modo = modo;
    }

    public void setDatos(ExtrusionE ee, OrdenE oe) {
	this.ee = ee;
	this.oe = oe;

	txtCodigo.setText(ee.getCodigo() + "");
	txtOrden.setText(ee.getNumeroOrden() + "");
	txtBolsa.setText(ee.getBolsa_referencia() + "");

	if(Main.menu.evaluar(Main.menu.getUsuario().getTipo()) == 0){
	    dtFechaFin.setDate(oe.getFecha_plazo());
	}else{
	    dtFechaFin.setDate(ee.getFechaFin());
	}
	
	spnDesperdicio.setValue(ee.getDesperdicio());
	spnRollo.setValue(ee.getRollo());
	txtMaquina.setText(ee.getMaquina());
	editNota.setText(ee.getNota());
	dtFechaInicio.setDate(ee.getFechaInicio());
	
	
	
	txtTipoCantidad.setText(oe.getTipo_cantidad());
	spnCantidad.setValue(oe.getCantidad());
    }

    public boolean comprobarNota() {
	String s = editNota.getText();
	boolean cont = true;

	if (s.length() > 1000) {
	    msjMensaje.setText("La nota supera los 1000 caracteres");
	    msjMensaje.setEstado(Estado.ERROR);
	    msjMensaje.setVisible(true);
	    cont = false;
	} else {
	    msjMensaje.setText("");
	}

	return cont;

    }

    public void verBolsa() {
	ArrayList<BolsaE> albe = new BolsaN().buscarBolsa("referencia", this.ee.getBolsa_referencia() + "", true, true);

	if (albe == null) {
	    msjMensaje.setText("Error desconocido :C");
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
		Main.caBolsa.setModo(1);
		Main.caBolsa.visible(false, true, false, true);
		Main.caBolsa.setDatos(albe.get(0));
		Main.caBolsa.panel.setVisible(true);
	    }
	}

    }

    private void regresar() {
	if (modo == 0) {
	    mensaje("");
	} else {
	    Main.removerTodos();
	    Main.menu.frame.add(Main.ordenFinalizar.panel);
	    Main.esconderTodos();
	    Main.ordenFinalizar.panel.setVisible(true);
	}
    }

    public void guardarProgreso() {
	ExtrusionE ee = this.ee;
	ee.setRollo(Double.parseDouble(spnRollo.getValor()));
	ee.setMaquina(txtMaquina.getText());
	ee.setDesperdicio(Double.parseDouble(spnDesperdicio.getValor()));
	ee.setNota(editNota.getText());

	String s = new ExtrusionN().actualizarExtrusion(ee);

	if (s.equals("")) {
	    mensaje("El proceso de extrusi\u00F3n se ha guardado");
	}

	if (s.equals("1")) {
	    msjMensaje.setText("Error desconocido :C");
	    msjMensaje.setEstado(Estado.ERROR);
	    msjMensaje.setVisible(true);
	}

	if (s.equals("2")) {
	    msjMensaje.setText("Error al conectarse a la base de datos");
	    msjMensaje.setEstado(Estado.ERROR);
	    msjMensaje.setVisible(true);
	}
    }

    private void mensaje(String s) {
	Main.buscarOrdenes = new BuscarOrdenes();
	Main.removerTodos();
	Main.menu.frame.getContentPane().add(Main.buscarOrdenes.panel);
	Main.esconderTodos();
	String h = Main.buscarOrdenes.pendientes_extrsuion();
	if (h.equals("")) {
	    Main.buscarOrdenes.msjMensaje.setText(s);
	    Main.buscarOrdenes.msjMensaje.setEstado(Estado.EXITO);
	    Main.buscarOrdenes.msjMensaje.setVisible(true);
	    Main.buscarOrdenes.panel.setVisible(true);
	} else {
	    Main.ordenes = new Ordenes();
	    Main.removerTodos();
	    Main.menu.frame.getContentPane().add(Main.ordenes.panel);
	    Main.esconderTodos();
	    Main.ordenes.msjMensaje.setText(s);
	    Main.ordenes.msjMensaje.setEstado(Estado.EXITO);
	    Main.ordenes.msjMensaje.setVisible(true);
	    Main.ordenes.panel.setVisible(true);
	}
    }

    public void finalizar() {
	ExtrusionE ee = this.ee;
	ee.setRollo(Double.parseDouble(spnRollo.getValor()));
	ee.setMaquina(txtMaquina.getText());
	ee.setDesperdicio(Double.parseDouble(spnDesperdicio.getValor()));
	ee.setNota(editNota.getText());
	ee.setFechaFin(new Date());
	ee.setEmpleado_cedula(Main.menu.getUsuario().getEmpleadoCedula());

	String s = new ExtrusionN().actualizarExtrusion(ee);
	if (s.equals("")) {
	    s = Auxiliar.pasarA(this.oe);

	    if (s.equals("" + Auxiliar.impresion)) {
		mensaje("El proceso de extrusi\u00F3n ha finalizado, la orden continuara a impresi\u00F3n");
	    }

	    if (s.equals("" + Auxiliar.sellado)) {
		mensaje("El proceso de extrusi\u00F3n ha finalizado, la orden continuara a sellado");
	    }

	    if (s.equals("" + Auxiliar.finalizado)) {
		mensaje("La orden de producci\u00F3n ha finalizdo");
	    }
	}

	if (s.equals("1")) {
	    msjMensaje.setText("Error desconocido :C");
	    msjMensaje.setEstado(Estado.ERROR);
	    msjMensaje.setVisible(true);
	}

	if (s.equals("2")) {
	    msjMensaje.setText("Error al conectarse a la base de datos");
	    msjMensaje.setEstado(Estado.ERROR);
	    msjMensaje.setVisible(true);
	}

    }

    public boolean comprobar() {
	String maquina = txtMaquina.getText();
	boolean cont = true;

	if (maquina.equals("")) {
	    msjMensaje.setText("El campo de maquina est\u00E1 vac\u00EDo");
	    msjMensaje.setEstado(Estado.ERROR);
	    txtMaquina.setEstado(Estado.ERROR);
	    cont = false;
	} else {
	    txtMaquina.setEstado(Estado.NORMAL);
	    msjMensaje.setText("");
	}

	return cont;
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
	if (e.getSource() == btnBolsa) {
	    Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());
	    verBolsa();
	    Main.dialog.ocultar();
	}

	if (e.getSource() == btnRegresar) {
	    Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());
	    regresar();
	    Main.dialog.ocultar();
	}

	if (e.getSource() == btnGuardar) {
	    Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());
	    if (comprobarNota()) {
		guardarProgreso();
	    }
	    Main.dialog.ocultar();
	}

	if (e.getSource() == btnFinalizar) {
	    Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());
	    if (comprobar() && comprobarNota()) {
		finalizar();
	    }
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
