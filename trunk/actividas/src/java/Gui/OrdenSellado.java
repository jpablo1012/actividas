package Gui;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import Entidades.BolsaE;
import Entidades.OrdenE;
import Entidades.SelladoE;
import Negocio.Auxiliar;
import Negocio.BolsaN;
import Negocio.SelladoN;

public class OrdenSellado implements MouseListener {

    String style = "style='color:#D3362D;'";

    APanel panel;
    AContainer ordenProduccion;
    AContainer sellado;
    ALabel lblOrden;
    ALabel lblCodigo;
    ALabel lblBolsa;
    ALabel lblCantidad;
    ALabel lblTipoCantidad;
    ALabel lblNota;
    ALabel lblRetal;
    ALabel lblSelladas;
    ALabel lblFechaInicio;
    ALabel lblFechaFin;
    ALabel lblObligatorio;
    ATextField txtOrden;
    ATextField txtCodigo;
    ATextField txtBolsa;
    ATextField txtTipoCantidad;
    ALabel msjOrden;
    ALabel msjCodigo;
    ALabel msjBolsa;
    ALabel msjMensaje;
    ASpinner spnCantidad;
    ASpinner spnRetal;
    ASpinner spnSelladas;
    AButton btnBolsa;
    AButton btnGuardar;
    AButton btnRegresar;
    AButton btnFinalizar;
    AEditorPane editNota;
    AScrollPanel nota;
    OrdenE oe;
    SelladoE se;
    ADateChosser dtFechaInicio;
    ADateChosser dtFechaFin;
    
    String fecha = "<html><body><b " + style + "></b>Fecha de finalizaci\u00F3n:</body></html>";

    int modo = 0;
    
    public OrdenSellado() {
	panel = new APanel(Main.x, 0, 750, 600);
	panel.setTitulo("Ordenes de producci\u00F3n| Sellado");
	if(Main.menu.evaluar(Main.menu.getUsuario().getTipo()) == 0){
	    fecha = "<html><body><b " + style + "></b>Plazo hasta:</body></html>";
	}

	ordenProduccion = new AContainer("Orden de producci\u00F3n");
	ordenProduccion.setBounds(60, 63, 630, 138);
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

	lblCodigo = new ALabel("<html><body><b " + style + "></b>C\u00F3digo Sellado:</body></html>");
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
	msjMensaje.setBounds(0, 558, panel.getWidth(), 20);
	msjMensaje.setFont(new Font("Calibri", Font.PLAIN, 18));
	panel.add(msjMensaje);

	sellado = new AContainer("Sellado");
	sellado.setBounds(60, 221, 630, 242);
	panel.add(sellado);

	lblFechaInicio = new ALabel("<html><body><b " + style + "></b>Fecha de inicio:</body></html>");
	lblFechaInicio.setBounds(30, 30, 120, 24);
	lblFechaInicio.setHorizontalAlignment(SwingConstants.RIGHT);
	sellado.add(lblFechaInicio);

	dtFechaInicio = new ADateChosser();
	dtFechaInicio.setBounds(170, 30, 120, 24);
	dtFechaInicio.setEnabled(false);
	sellado.add(dtFechaInicio);

	lblFechaFin = new ALabel(fecha);
	lblFechaFin.setHorizontalAlignment(SwingConstants.RIGHT);
	lblFechaFin.setBounds(310, 30, 120, 24);
	sellado.add(lblFechaFin);

	dtFechaFin = new ADateChosser();
	dtFechaFin.setBounds(450, 30, 120, 24);
	dtFechaFin.setEnabled(false);
	sellado.add(dtFechaFin);

	lblTipoCantidad = new ALabel("<html><body><b " + style + "></b>Tipo de cantidad:</body></html>");
	lblTipoCantidad.setBounds(30, 74, 120, 24);
	lblTipoCantidad.setHorizontalAlignment(SwingConstants.RIGHT);
	sellado.add(lblTipoCantidad);

	txtTipoCantidad = new ATextField();
	txtTipoCantidad.setBounds(170, 74, 120, 24);
	txtTipoCantidad.setEnabled(false);
	sellado.add(txtTipoCantidad);

	lblCantidad = new ALabel("<html><body><b " + style + "></b>Cantidad:</body></html>");
	lblCantidad.setBounds(310, 74, 120, 24);
	lblCantidad.setHorizontalAlignment(SwingConstants.RIGHT);
	sellado.add(lblCantidad);

	spnCantidad = new ASpinner();
	spnCantidad.setBounds(450, 74, 70, 24);
	spnCantidad.setEnabled(false);
	sellado.add(spnCantidad);

	lblRetal = new ALabel("<html><body><b " + style + "></b>Retal:</body></html>");
	lblRetal.setBounds(30, 118, 120, 24);
	lblRetal.setHorizontalAlignment(SwingConstants.RIGHT);
	sellado.add(lblRetal);

	spnRetal = new ASpinner(0, 0, Integer.MAX_VALUE);
	spnRetal.setBounds(170, 118, 70, 24);
	sellado.add(spnRetal);

	lblSelladas = new ALabel("<html><body><b " + style + ">*</b>Bolsas selladas:</body></html>");
	lblSelladas.setBounds(310, 118, 120, 24);
	lblSelladas.setHorizontalAlignment(SwingConstants.RIGHT);
	sellado.add(lblSelladas);

	spnSelladas = new ASpinner(0, 0, Integer.MAX_VALUE);
	spnSelladas.setBounds(450, 118, 70, 24);
	sellado.add(spnSelladas);

	lblNota = new ALabel("<html><body><b " + style + "></b>Nota:</body></html>");
	lblNota.setHorizontalAlignment(SwingConstants.RIGHT);
	lblNota.setBounds(30, 162, 70, 24);
	sellado.add(lblNota);

	nota = new AScrollPanel("");
	nota.setBounds(120, 162, 450, 60);
	nota.setBorder(new MatteBorder(1, 1, 1, 1, Colores.BORDE_CAJATEXTO));
	nota.setHorizontalScrollBarPolicy(AScrollPanel.HORIZONTAL_SCROLLBAR_NEVER);
	sellado.add(nota);

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

	btnFinalizar = new AButton("Finalizar Sellado");
	btnFinalizar.setBounds(465, 504, 130, 30);
	btnFinalizar.addMouseListener(this);
	panel.add(btnFinalizar);

	btnRegresar = new AButton("Regresar");
	btnRegresar.setBounds(165, 504, 130, 30);
	btnRegresar.addMouseListener(this);
	panel.add(btnRegresar);
	
	lblObligatorio = new ALabel("<html><body><b " + style + ">*</b>Campo obligatorio</body></html>");
	lblObligatorio.setVerticalAlignment(SwingConstants.TOP);
	lblObligatorio.setBounds(60, 461, 120, 20);
	panel.add(lblObligatorio);

	this.oe = null;
	this.se = null;
    }
    
    public void allDisabled(){
	spnRetal.setEnabled(false);
	spnSelladas.setEnabled(false);
	editNota.setEnabled(false);
	
	btnFinalizar.setVisible(false);
	btnGuardar.setVisible(false);
	
	btnRegresar.setBounds(310, 501, 130, 30);
    }
    
    public void setModo(int modo){
	this.modo = modo;
    }

    public void verBolsa() {
	ArrayList<BolsaE> albe = new BolsaN().buscarBolsa("referencia", this.se.getBolsa_referencia() + "", true, true);

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
		Main.caBolsa.setModo(3);
		Main.caBolsa.visible(false, true, false, true);
		Main.caBolsa.setDatos(albe.get(0));
		Main.caBolsa.panel.setVisible(true);
	    }
	}
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

    private void mensaje(String s) {
	Main.buscarOrdenes = new BuscarOrdenes();
	Main.removerTodos();
	Main.menu.frame.getContentPane().add(Main.buscarOrdenes.panel);
	Main.esconderTodos();
	String h = Main.buscarOrdenes.pendientes_sellado();
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

    public void guardarProgreso() {
	SelladoE se = this.se;
	se.setRetal(Double.parseDouble(spnRetal.getValor()));
	se.setNota(editNota.getText());
	se.setBolsasSelladas(Double.parseDouble(spnSelladas.getValor()));

	String s = new SelladoN().actualizarSellado(se);

	if (s.equals("")) {
	    mensaje("El proceso de sellado se ha guardado");
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

    public void setDatos(SelladoE se, OrdenE oe) {
	this.oe = oe;
	this.se = se;

	txtCodigo.setText(se.getCodigo() + "");
	txtOrden.setText(se.getNumeroOrden() + "");
	txtBolsa.setText(se.getBolsa_referencia() + "");

	dtFechaInicio.setDate(se.getFechaInicio());
	
	if(Main.menu.evaluar(Main.menu.getUsuario().getTipo()) == 0){
	    dtFechaFin.setDate(oe.getFecha_plazo());
	}else{
	    dtFechaFin.setDate(se.getFechaFin());
	}

	txtTipoCantidad.setText(oe.getTipo_cantidad());
	spnCantidad.setValue(oe.getCantidad());

	spnRetal.setValue(se.getRetal());
	spnSelladas.setValue(se.getBolsasSelladas());
	editNota.setText(se.getNota());
    }

    private void regresar() {
	if(modo == 0){
	    mensaje("");
	}else{
	    Main.removerTodos();
	    Main.menu.frame.add(Main.ordenFinalizar.panel);
	    Main.esconderTodos();
	    Main.ordenFinalizar.panel.setVisible(true);
	}
    }

    private boolean comprobar() {
	//String retal = spnRetal.getValor();
	String selladas = spnSelladas.getValor();
	boolean cont = true;

	if (selladas.equals("0.0")) {
	    spnSelladas.setEstado(Estado.ERROR);
	    msjMensaje.setText("La cantidad de bolsas selladas no puede ser 0");
	    msjMensaje.setEstado(Estado.ERROR);
	    msjMensaje.setVisible(true);
	    cont = false;
	} else {
	    spnSelladas.setEstado(Estado.NORMAL);
	    msjMensaje.setText("");
	}
	return cont;
    }

    private void finalizar() {
	SelladoE se = this.se;
	se.setRetal(Double.parseDouble(spnRetal.getValor()));
	se.setNota(editNota.getText());
	se.setBolsasSelladas(Double.parseDouble(spnSelladas.getValor()));
	se.setFechaFin(new Date());
	se.setEmpleado_cedula(Main.menu.getUsuario().getEmpleadoCedula());

	String s = new SelladoN().actualizarSellado(se);

	if (s.equals("")) {
	    s = Auxiliar.pasarA(oe);

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

	if (e.getSource() == btnGuardar) {
	    Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());
	    if (comprobarNota()) {
		guardarProgreso();
	    }
	    Main.dialog.ocultar();

	}

	if (e.getSource() == btnRegresar) {
	    Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());
	    regresar();
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
