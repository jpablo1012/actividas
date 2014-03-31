package Gui;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import Entidades.BolsaE;
import Entidades.ImpresionE;
import Entidades.OrdenE;
import Negocio.Auxiliar;
import Negocio.BolsaN;
import Negocio.ImpresionN;
import api.*;

public class OrdenImpresor implements MouseListener {

    String style = "style='color:#D3362D;'";

    APanel panel;
    ALabel lblImpresion, lblOrden, lblCodigo, lblBolsa, lblFechaInicio, lblFechaFin, lblPesoInicial, lblPesoFinal, lblRodillo, lblNota, lblImpreso, lblTipoCantidad, lblCantidad, lblObligatorio;
    ALabel msjOrden, msjCodigo, msjBolsa, msjFechaInicio, msjFechaFin, msjPesoInicial, msjPesoFinal;
    ALabel msjMensaje;
    ASpinner spnPesoInicial, spnPesoFinal, spnCantidad;
    ATextField txtOrden, txtCodigo, txtBolsa, txtImpreso, txtTipoCantidad;
    AContainer ordenProduccion, impresion;
    ADateChosser dtFechaInicio, dtFechaFin;
    AButton btnBolsa;
    AEditorPane editNota;
    AScrollPanel nota;
    AButton btnGuardar, btnFinalizar, btnRegresar;
    OrdenE oe;
    ImpresionE ie;

    int modo = 0;
    
    String fecha = "<html><body><b " + style + "></b>Fecha de finalizaci\u00F3n:</body></html>";

    public OrdenImpresor() {
	panel = new APanel(Main.x, 0, 750, 600);
	
	if(Main.menu.evaluar(Main.menu.getUsuario().getTipo()) == 0){
	    fecha = "<html><body><b " + style + "></b>Plazo hasta:</body></html>";
	}

	lblImpresion = new ALabel("Ordenes de producci\u00F3n| Impresi\u00F3n");
	lblImpresion.setFont(new Font("Calibri", Font.PLAIN, 24));
	lblImpresion.setForeground(Colores.titulo_normal);
	lblImpresion.setBounds(10, 0, 460, 50);
	panel.add(lblImpresion);

	ordenProduccion = new AContainer("Orden de producci\u00F3n");
	ordenProduccion.setBounds(60, 40, 630, 138);
	panel.add(ordenProduccion);

	lblOrden = new ALabel("<html><body align='right'><b " + style + "></b>C\u00F3digo orden de producci\u00F3n:</body></html>");
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

	lblCodigo = new ALabel("<html><body><b " + style + "></b>C\u00F3digo impresi\u00F3n:</body></html>");
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

	msjMensaje = new ALabel("");
	msjMensaje.setHorizontalAlignment(SwingConstants.CENTER);
	msjMensaje.setBounds(0, 554, panel.getWidth(), 20);
	msjMensaje.setFont(new Font("Calibri", Font.PLAIN, 18));
	panel.add(msjMensaje);

	impresion = new AContainer("Impresi\u00F3n");
	impresion.setBounds(60, 198, 630, 284);
	panel.add(impresion);

	lblFechaInicio = new ALabel("<html><body><b " + style + "></b>Fecha de inicio:</body></html>");
	lblFechaInicio.setBounds(30, 30, 120, 24);
	lblFechaInicio.setHorizontalAlignment(SwingConstants.RIGHT);
	impresion.add(lblFechaInicio);

	dtFechaInicio = new ADateChosser();
	dtFechaInicio.setBounds(170, 30, 120, 24);
	dtFechaInicio.setEnabled(false);
	impresion.add(dtFechaInicio);

	msjFechaInicio = new ALabel("");
	msjFechaInicio.setBounds(170, 54, 120, 20);
	// extrusion.add(msjFechaInicio);

	lblFechaFin = new ALabel(fecha);
	lblFechaFin.setHorizontalAlignment(SwingConstants.RIGHT);
	lblFechaFin.setBounds(310, 30, 120, 24);
	impresion.add(lblFechaFin);

	dtFechaFin = new ADateChosser();
	dtFechaFin.setBounds(450, 30, 120, 24);
	dtFechaFin.setEnabled(false);
	impresion.add(dtFechaFin);

	msjFechaFin = new ALabel("");
	msjFechaFin.setBounds(450, 54, 120, 20);
	// extrusion.add(msjFechaFin);

	lblTipoCantidad = new ALabel("<html><body><b " + style + "></b>Tipo de cantidad:</body></html>");
	lblTipoCantidad.setBounds(30, 74, 120, 24);
	lblTipoCantidad.setHorizontalAlignment(SwingConstants.RIGHT);
	impresion.add(lblTipoCantidad);

	txtTipoCantidad = new ATextField();
	txtTipoCantidad.setBounds(170, 74, 120, 24);
	txtTipoCantidad.setEnabled(false);
	impresion.add(txtTipoCantidad);

	lblCantidad = new ALabel("<html><body><b " + style + "></b>Cantidad:</body></html>");
	lblCantidad.setBounds(310, 74, 120, 24);
	lblCantidad.setHorizontalAlignment(SwingConstants.RIGHT);
	impresion.add(lblCantidad);

	spnCantidad = new ASpinner();
	spnCantidad.setBounds(450, 74, 70, 24);
	spnCantidad.setEnabled(false);
	impresion.add(spnCantidad);

	lblPesoInicial = new ALabel("<html><body align='right'><b " + style + ">*</b>Peso antes de la impresi\u00F3n:</body></html>");
	lblPesoInicial.setHorizontalAlignment(SwingConstants.RIGHT);
	lblPesoInicial.setBounds(30, 152, 120, 34);
	impresion.add(lblPesoInicial);

	spnPesoInicial = new ASpinner(0, 0, 999999);
	spnPesoInicial.setBounds(170, 162, 70, 24);
	impresion.add(spnPesoInicial);

	msjPesoInicial = new ALabel("");
	msjPesoInicial.setBounds(170, 108, 120, 20);
	// impresion.add(msjPesoInicial);

	lblPesoFinal = new ALabel("<html><body align='right'><b " + style + ">*</b>Peso despu\u00E9s de la impresi\u00F3n:</body></html>");
	lblPesoFinal.setHorizontalAlignment(SwingConstants.RIGHT);
	lblPesoFinal.setBounds(310, 152, 120, 34);
	impresion.add(lblPesoFinal);

	spnPesoFinal = new ASpinner(0, 0, 999999);
	spnPesoFinal.setBounds(450, 162, 70, 24);
	impresion.add(spnPesoFinal);

	msjPesoFinal = new ALabel("");
	msjPesoFinal.setBounds(450, 108, 120, 20);
	// impresion.add(msjPesoFinal);

	lblImpreso = new ALabel("<html><body><b " + style + "></b>Impresi\u00F3n:</body></html>");
	lblImpreso.setHorizontalAlignment(SwingConstants.RIGHT);
	lblImpreso.setBounds(30, 118, 120, 24);
	impresion.add(lblImpreso);

	txtImpreso = new ATextField();
	txtImpreso.setEnabled(false);
	txtImpreso.setBounds(170, 118, 400, 24);
	impresion.add(txtImpreso);

	lblNota = new ALabel("<html><body><b " + style + "></b>Nota:</body></html>");
	lblNota.setHorizontalAlignment(SwingConstants.RIGHT);
	lblNota.setBounds(30, 206, 120, 24);
	impresion.add(lblNota);

	nota = new AScrollPanel("");
	nota.setBounds(170, 206, 400, 60);
	nota.setBorder(new MatteBorder(1, 1, 1, 1, Colores.borde_cajaTexto));
	nota.setHorizontalScrollBarPolicy(AScrollPanel.HORIZONTAL_SCROLLBAR_NEVER);
	impresion.add(nota);

	editNota = new AEditorPane();
	editNota.setPlaceHolder("");
	editNota.setBorder(null);
	editNota.setBounds(0, 0, 400, 54);
	editNota.setPlaceHolder("Nota...");
	nota.setViewportView(editNota);

	btnGuardar = new AButton("Guardar progreso");
	btnGuardar.setBounds(315, 504, 130, 30);
	btnGuardar.addMouseListener(this);
	panel.add(btnGuardar);

	btnFinalizar = new AButton("Finalizar impresi\u00F3n");
	btnFinalizar.setBounds(465, 504, 130, 30);
	btnFinalizar.addMouseListener(this);
	panel.add(btnFinalizar);

	btnRegresar = new AButton("Regresar");
	btnRegresar.setBounds(165, 504, 130, 30);
	btnRegresar.addMouseListener(this);
	panel.add(btnRegresar);
	
	lblObligatorio = new ALabel("<html><body><b " + style + ">*</b>Campo obligatorio</body></html>");
	lblObligatorio.setVerticalAlignment(SwingConstants.TOP);
	lblObligatorio.setBounds(60, 480, 120, 20);
	panel.add(lblObligatorio);

	oe = null;
	ie = null;
    }
    
    public void allDisabled(){
	spnPesoInicial.setEnabled(false);
	spnPesoFinal.setEnabled(false);
	editNota.setEnabled(false);
	
	btnFinalizar.setVisible(false);
	btnGuardar.setVisible(false);
	
	btnRegresar.setBounds(310, 501, 130, 30);
    }
    
    public void setModo(int modo){
	this.modo = modo;
    }

    public void verBolsa() {
	ArrayList<BolsaE> albe = new BolsaN().buscarBolsa("referencia", this.ie.getBolsa_referencia() + "", true, true);

	if (albe == null) {
	    msjMensaje.setText("Error desconocido :C");
	    msjMensaje.setEstado(Estado.error);
	    msjMensaje.setVisible(true);
	} else {
	    if (albe.size() == 0) {
		msjMensaje.setText("Error al conectarse a la base de datos");
		msjMensaje.setEstado(Estado.error);
		msjMensaje.setVisible(true);
	    } else {
		Main.caBolsa = new CABolsa();
		Main.removerTodos();
		Main.menu.frame.getContentPane().add(Main.caBolsa.panel);
		Main.esconderTodos();
		Main.caBolsa.setModo(2);
		Main.caBolsa.visible(false, true, false, true);
		Main.caBolsa.setDatos(albe.get(0));
		Main.caBolsa.panel.setVisible(true);
	    }
	}

    }

    public void setDatos(ImpresionE ie, OrdenE oe) {
	this.oe = oe;
	this.ie = ie;

	txtCodigo.setText(ie.getCodigo() + "");
	txtOrden.setText(ie.getNumeroOrden() + "");
	txtBolsa.setText(ie.getBolsa_referencia() + "");

	dtFechaInicio.setDate(ie.getFechaInicio());
	
	if(Main.menu.evaluar(Main.menu.getUsuario().getTipo()) == 0){
	    dtFechaFin.setDate(oe.getFecha_plazo());
	}else{
	    dtFechaFin.setDate(ie.getFechaFin());
	}

	spnPesoInicial.setValue(ie.getPesoInicial());
	spnPesoFinal.setValue(ie.getPesoFinal());
	txtImpreso.setText(oe.getReferencia());
	editNota.setText(ie.getNota());

	txtTipoCantidad.setText(oe.getTipo_cantidad());
	spnCantidad.setValue(oe.getCantidad());
    }

    private void mensaje(String s) {
	Main.buscarOrdenes = new BuscarOrdenes();
	Main.removerTodos();
	Main.menu.frame.getContentPane().add(Main.buscarOrdenes.panel);
	Main.esconderTodos();
	String h = Main.buscarOrdenes.pendientes_impresion();
	if (h.equals("")) {
	    Main.buscarOrdenes.msjMensaje.setText(s);
	    Main.buscarOrdenes.msjMensaje.setEstado(Estado.exito);
	    Main.buscarOrdenes.msjMensaje.setVisible(true);
	    Main.buscarOrdenes.panel.setVisible(true);
	} else {
	    Main.ordenes = new Ordenes();
	    Main.removerTodos();
	    Main.menu.frame.getContentPane().add(Main.ordenes.panel);
	    Main.esconderTodos();
	    Main.ordenes.msjMensaje.setText(s);
	    Main.ordenes.msjMensaje.setEstado(Estado.exito);
	    Main.ordenes.msjMensaje.setVisible(true);
	    Main.ordenes.panel.setVisible(true);
	}
    }

    private void regresar() {
	if (modo == 0) {
	    mensaje("");
	} else {
	    Main.removerTodos();
	    Main.menu.frame.getContentPane().add(Main.ordenFinalizar.panel);
	    Main.esconderTodos();
	    Main.ordenFinalizar.panel.setVisible(true);
	}
    }

    private void guardarProgreso() {
	ImpresionE ie = this.ie;
	double inicial = Double.parseDouble(spnPesoInicial.getValor());
	double pFinal = Double.parseDouble(spnPesoFinal.getValor());
	ie.setPesoInicial(inicial);
	ie.setPesoFinal(pFinal);
	ie.setDiferencia(inicial - pFinal);
	ie.setNota(editNota.getText());

	String s = new ImpresionN().actualizarImpresion(ie);

	if (s.equals("")) {
	    mensaje("El proceso de impresi\u00F3n se ha guardado");
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

    public boolean comprobarNota() {
	String s = editNota.getText();
	boolean cont = true;

	if (s.length() > 1000) {
	    msjMensaje.setText("La nota supera los 1000 caracteres");
	    msjMensaje.setEstado(Estado.error);
	    msjMensaje.setVisible(true);
	    cont = false;
	} else {
	    msjMensaje.setText("");
	}

	return cont;

    }

    public boolean comprobar() {
	String pesoInicial = spnPesoInicial.getValor();
	String pesoFinal = spnPesoFinal.getValor();

	System.out.println(pesoInicial);

	boolean cont = true;

	if (pesoInicial.equals("0.0")) {
	    spnPesoInicial.setEstado(Estado.error);
	    msjMensaje.setText("Peso inicial y/o peso final no pueden ser 0");
	    msjMensaje.setEstado(Estado.error);
	    cont = false;
	} else {
	    spnPesoInicial.setEstado(Estado.normal);
	    msjMensaje.setText("");
	}

	if (pesoFinal.equals("0.0")) {
	    spnPesoFinal.setEstado(Estado.error);
	    msjMensaje.setText("Peso inicial y/o peso final no pueden ser 0");
	    msjMensaje.setEstado(Estado.error);
	    cont = false;
	} else {
	    spnPesoFinal.setEstado(Estado.normal);
	    msjMensaje.setText("");
	}

	return cont;

    }

    public void finalizar() {
	ImpresionE ie = this.ie;
	double inicial = Double.parseDouble(spnPesoInicial.getValor());
	double pFinal = Double.parseDouble(spnPesoFinal.getValor());
	ie.setPesoInicial(inicial);
	ie.setPesoFinal(pFinal);
	ie.setDiferencia(pFinal - inicial);
	ie.setNota(editNota.getText());
	ie.setFechaFin(new Date());
	ie.setEmpleado_cedula(Main.menu.getUsuario().getEmpleadoCedula());

	String s = new ImpresionN().actualizarImpresion(ie);

	if (s.equals("")) {
	    s = Auxiliar.pasarA(oe);

	    if (s.equals("" + Auxiliar.sellado)) {
		mensaje("El proceso de impresi\u00F3n ha finalizado, la orden continuara a sellado");
	    }

	    if (s.equals("" + Auxiliar.finalizado)) {
		mensaje("La orden de producci\u00F3n ha finalizdo");
	    }
	}

	if (s.equals("1")) {
	    msjMensaje.setText("Error desconocido :C");
	    msjMensaje.setEstado(Estado.error);
	    msjMensaje.setVisible(true);
	}

	if (s.equals("2")) {
	    msjMensaje.setText("Error desconocido :C");
	    msjMensaje.setEstado(Estado.error);
	    msjMensaje.setVisible(true);
	}
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
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

}
