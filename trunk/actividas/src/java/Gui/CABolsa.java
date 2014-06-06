/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entidades.BolsaE;
import Entidades.ColorE;
import Entidades.MaterialE;
import Negocio.BolsaN;
import Negocio.ColorN;
import Negocio.MaterialN;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import api.AButton;
import api.AComboBox;
import api.AContainer;
import api.ALabel;
import api.APanel;
import api.AScrollPanel;
import api.ASpinner;
import api.AToggleButton;
import api.Estado;

import java.awt.Dimension;
import java.awt.Font;

public class CABolsa implements MouseListener, ItemListener, ChangeListener {

    String style = "style='color:#D3362D;'";
    String[] strMedida = { "(Cm) cent\u00EDmetros", "(Pulg) pulgadas" };
    String[] strMedida2 = { "cm", "pulg" };
    String[] strTroquel = { "-Seleccione-", "Ninguno", "Camiseta", "Ri\u00F1on", "Tiras" };
    String[] strTroquel2 = { "", "ninguno", "camiseta", "ri\u00F1on", "tiras" };
    String[] strMaterial;
    String[] strColor;
    String[] refenciaMaterial;
    String[] codigoColor;
    BolsaE be;
    APanel panel;
    AContainer bolsa;
    AScrollPanel superior;
    ALabel lblObligatorio;
    ALabel lblColorCodigo;
    ALabel lblMaterialReferencia;
    ALabel lblMedida;
    ALabel lblAncho;
    ALabel lblLargo;
    ALabel lblCalibre;
    ALabel lblTratado;
    ALabel lblTransArriba;
    ALabel lblTransAbajo;
    ALabel lblFuelleFondo;
    ALabel lblFuelleSuperior;
    ALabel lblFuelleLateral;
    ALabel lblImagen;
    ALabel lblSolapa;
    ALabel lblTroquel;
    ALabel msjColorCodigo;
    ALabel msjMaterialReferencia;
    ALabel msjMedida;
    ALabel msjAncho;
    ALabel msjLargo;
    ALabel msjCalibre;
    ALabel msjImagen;
    ALabel msjTroquel;
    ALabel msjMensaje;
    ASpinner spnAncho;
    ASpinner spnLargo;
    ASpinner spnCalibre;
    ASpinner spnTransArriba;
    ASpinner spnTransAbajo;
    ASpinner spnFuelleFondo;
    ASpinner spnFuelleSuperior;
    ASpinner spnFuelleLateral;
    ASpinner spnSolapa;
    ALabel mddAncho;
    ALabel mddLargo;
    ALabel mddTransArriba;
    ALabel mddTransAbajo;
    ALabel mddFuelleFondo;
    ALabel mddFuelleSuperior;
    ALabel mddFuelleLateral;
    ALabel mddSolapa;
    AComboBox cmbColorCodigo;
    AComboBox cmbMaterialReferencia;
    AComboBox cmbMedida;
    AComboBox cmbTroquel;
    AButton btnCrear;
    AButton btnGuardar;
    AButton btnCancelar;
    AButton btnNuevo;
    AButton btnImagen;
    AButton btnQuitar;
    AButton btnVer;
    AToggleButton tbtnTratado;
    File file;

    int modo = 0;

    public CABolsa() {
	panel = new APanel(Main.x, 0, 750, 600);
	panel.setTitulo("Insumos| Crear bolsa");
	
	lblObligatorio = new ALabel("<html><body><b " + style + ">*</b>Campo obligatorio</body></html>");
    lblObligatorio.setHorizontalAlignment(SwingConstants.RIGHT);
    lblObligatorio.setBounds(319, 40, 112, 24);
    panel.add(lblObligatorio);

	btnNuevo = new AButton("Crear otra bolsa");
	btnNuevo.setBounds(295, 270, 160, 60);
	panel.add(btnNuevo);
	btnNuevo.setVisible(false);
	btnNuevo.addMouseListener(this);

	superior = new AScrollPanel("");
	superior.setBounds(100, 80, 550, 430);
	panel.add(superior);

	bolsa = new AContainer("");
	bolsa.setPreferredSize(new Dimension(450, 550));
	bolsa.setBorder(null);
	superior.add(bolsa);
	superior.setViewportView(bolsa);

	lblMaterialReferencia = new ALabel("<html><body align='right'><b " + style + ">*</b>Referencia del material:</body></html>");
	lblMaterialReferencia.setHorizontalAlignment(SwingConstants.RIGHT);
	lblMaterialReferencia.setBounds(25, 20, 75, 34);
	bolsa.add(lblMaterialReferencia);

	strMaterial = devolverReferenciasMaterial();

	cmbMaterialReferencia = new AComboBox(strMaterial);
	cmbMaterialReferencia.setBounds(120, 30, 120, 24);
	cmbMaterialReferencia.setValores(refenciaMaterial);
	bolsa.add(cmbMaterialReferencia);

	msjMaterialReferencia = new ALabel("");
	msjMaterialReferencia.setBounds(120, 54, 130, 30);
	bolsa.add(msjMaterialReferencia);

	lblColorCodigo = new ALabel("<html><body align='right'><b " + style + ">*</b>Referencia del color:</body></html>");
	lblColorCodigo.setHorizontalAlignment(SwingConstants.RIGHT);
	lblColorCodigo.setBounds(255, 20, 75, 34);
	bolsa.add(lblColorCodigo);

	strColor = devolverCodigosColor();
	cmbColorCodigo = new AComboBox(strColor);
	cmbColorCodigo.setValores(codigoColor);
	cmbColorCodigo.setBounds(350, 30, 120, 24);
	bolsa.add(cmbColorCodigo);

	msjColorCodigo = new ALabel("");
	msjColorCodigo.setBounds(350, 54, 130, 30);
	bolsa.add(msjColorCodigo);

	lblMedida = new ALabel("<html><body align='right'><b " + style + ">*</b>Medida:</body></html>");
	lblMedida.setHorizontalAlignment(SwingConstants.RIGHT);
	lblMedida.setBounds(30, 94, 70, 24);
	bolsa.add(lblMedida);

	cmbMedida = new AComboBox(strMedida);
	cmbMedida.setBounds(120, 94, 120, 24);
	cmbMedida.addItemListener(this);
	cmbMedida.setValores(strMedida2);
	bolsa.add(cmbMedida);

	msjMedida = new ALabel("");
	msjMedida.setBounds(120, 118, 120, 20);
	bolsa.add(msjMedida);

	lblAncho = new ALabel("<html><body align='right'><b " + style + ">*</b>Ancho:</body></html>");
	lblAncho.setHorizontalAlignment(SwingConstants.RIGHT);
	lblAncho.setBounds(260, 94, 70, 24);
	bolsa.add(lblAncho);

	spnAncho = new ASpinner(5, 1, 10000);
	spnAncho.setBounds(350, 94, 70, 24);
	spnAncho.addChangeListener(this);
	bolsa.add(spnAncho);

	mddAncho = new ALabel("");
	mddAncho.setBounds(425, 94, 45, 24);
	bolsa.add(mddAncho);

	msjAncho = new ALabel("");
	msjAncho.setBounds(350, 118, 120, 20);
	bolsa.add(msjAncho);

	lblLargo = new ALabel("<html><body align='right'><b " + style + ">*</b>Largo:</body></html>");
	lblLargo.setHorizontalAlignment(SwingConstants.RIGHT);
	lblLargo.setBounds(30, 158, 70, 24);
	bolsa.add(lblLargo);

	spnLargo = new ASpinner(5, 1, 10000);
	spnLargo.setBounds(120, 158, 70, 24);
	spnLargo.addChangeListener(this);
	bolsa.add(spnLargo);

	mddLargo = new ALabel("");
	mddLargo.setBounds(195, 158, 45, 24);
	bolsa.add(mddLargo);

	msjLargo = new ALabel("");
	msjLargo.setBounds(120, 182, 120, 20);
	bolsa.add(msjLargo);

	lblCalibre = new ALabel("<html><body align='right'><b " + style + ">*</b>Calibre:</body></html>");
	lblCalibre.setHorizontalAlignment(SwingConstants.RIGHT);
	lblCalibre.setBounds(260, 158, 70, 24);
	bolsa.add(lblCalibre);

	spnCalibre = new ASpinner(1, 0.36, 7);
	spnCalibre.setBounds(350, 158, 70, 24);
	bolsa.add(spnCalibre);

	msjCalibre = new ALabel("");
	msjCalibre.setBounds(350, 182, 120, 20);
	bolsa.add(msjCalibre);

	lblTratado = new ALabel("<html><body align='right'><b " + style + ">*</b>Tratado:</body></html>");
	lblTratado.setHorizontalAlignment(SwingConstants.RIGHT);
	lblTratado.setBounds(30, 222, 70, 24);
	bolsa.add(lblTratado);

	tbtnTratado = new AToggleButton("Si", "No");
	tbtnTratado.setBounds(120, 222, 70, 24);
	bolsa.add(tbtnTratado);

	lblTransArriba = new ALabel("<html><body align='right'><b " + style + "></b>Transparencia Arriba:</body></html>");
	lblTransArriba.setHorizontalAlignment(SwingConstants.RIGHT);
	lblTransArriba.setBounds(250, 212, 80, 34);
	bolsa.add(lblTransArriba);

	spnTransArriba = new ASpinner(0, 0, 10000);
	spnTransArriba.setBounds(350, 222, 70, 24);
	bolsa.add(spnTransArriba);

	mddTransArriba = new ALabel("");
	mddTransArriba.setBounds(425, 222, 45, 24);
	bolsa.add(mddTransArriba);

	lblTransAbajo = new ALabel("<html><body align='right'><b " + style + "></b>Transparencia Abajo:</body></html>");
	lblTransAbajo.setHorizontalAlignment(SwingConstants.RIGHT);
	lblTransAbajo.setBounds(20, 276, 80, 34);
	bolsa.add(lblTransAbajo);

	spnTransAbajo = new ASpinner(0, 0, 10000);
	spnTransAbajo.setBounds(120, 286, 70, 24);
	bolsa.add(spnTransAbajo);

	mddTransAbajo = new ALabel("");
	mddTransAbajo.setBounds(195, 286, 45, 24);
	bolsa.add(mddTransAbajo);

	lblFuelleSuperior = new ALabel("<html><body align='right'><b " + style + "></b>Fuelle superior:</body></html>");
	lblFuelleSuperior.setHorizontalAlignment(SwingConstants.RIGHT);
	lblFuelleSuperior.setBounds(255, 276, 75, 34);
	bolsa.add(lblFuelleSuperior);

	spnFuelleSuperior = new ASpinner(0, 0, 10000);
	spnFuelleSuperior.setBounds(350, 286, 70, 24);
	bolsa.add(spnFuelleSuperior);

	mddFuelleSuperior = new ALabel("");
	mddFuelleSuperior.setBounds(425, 286, 45, 24);
	bolsa.add(mddFuelleSuperior);

	lblFuelleLateral = new ALabel("<html><body align='right'><b " + style + "></b>Fuelle lateral:</body></html>");
	lblFuelleLateral.setHorizontalAlignment(SwingConstants.RIGHT);
	lblFuelleLateral.setBounds(25, 340, 75, 34);
	bolsa.add(lblFuelleLateral);

	spnFuelleLateral = new ASpinner(0, 0, 10000);
	spnFuelleLateral.setBounds(120, 350, 70, 24);
	bolsa.add(spnFuelleLateral);

	mddFuelleLateral = new ALabel("");
	mddFuelleLateral.setBounds(195, 350, 45, 24);
	bolsa.add(mddFuelleLateral);

	lblFuelleFondo = new ALabel("<html><body align='right'><b " + style + "></b>Fuelle fondo:</body></html>");
	lblFuelleFondo.setHorizontalAlignment(SwingConstants.RIGHT);
	lblFuelleFondo.setBounds(258, 350, 72, 24);
	bolsa.add(lblFuelleFondo);

	spnFuelleFondo = new ASpinner(0, 0, 10000);
	spnFuelleFondo.setBounds(350, 350, 70, 24);
	bolsa.add(spnFuelleFondo);

	mddFuelleFondo = new ALabel("");
	mddFuelleFondo.setBounds(425, 350, 45, 24);
	bolsa.add(mddFuelleFondo);

	msjMensaje = new ALabel("");
	msjMensaje.setHorizontalAlignment(SwingConstants.CENTER);
	msjMensaje.setFont(new Font("Calibri", Font.PLAIN, 18));
	msjMensaje.setBounds(0, 569, 750, 20);
	panel.add(msjMensaje);

	lblSolapa = new ALabel("<html><body><b " + style + "></b>Solapa:</body></html>");
	lblSolapa.setBounds(30, 414, 70, 24);
	lblSolapa.setHorizontalAlignment(SwingConstants.RIGHT);
	bolsa.add(lblSolapa);

	spnSolapa = new ASpinner(0, 0, 10000);
	spnSolapa.setBounds(120, 414, 70, 24);
	bolsa.add(spnSolapa);

	mddSolapa = new ALabel("");
	mddSolapa.setBounds(195, 414, 45, 24);
	bolsa.add(mddSolapa);

	lblTroquel = new ALabel("<html><body><b " + style + ">*</b>Troquel:</body></html>");
	lblTroquel.setBounds(260, 414, 70, 24);
	lblTroquel.setHorizontalAlignment(SwingConstants.RIGHT);
	bolsa.add(lblTroquel);

	cmbTroquel = new AComboBox(strTroquel);
	cmbTroquel.setValores(strTroquel2);
	cmbTroquel.setBounds(350, 414, 120, 24);
	bolsa.add(cmbTroquel);

	msjTroquel = new ALabel("");
	msjTroquel.setBounds(350, 438, 120, 20);
	bolsa.add(msjTroquel);

	lblImagen = new ALabel("<html><body align='right'><b " + style + "></b>Imagen:</body></html>");
	lblImagen.setHorizontalAlignment(SwingConstants.RIGHT);
	lblImagen.setBounds(30, 482, 70, 24);
	bolsa.add(lblImagen);

	btnImagen = new AButton("Examinar...");
	btnImagen.addMouseListener(this);
	btnImagen.setBounds(120, 482, 120, 24);
	bolsa.add(btnImagen);

	btnVer = new AButton("Ver");
	btnVer.setVisible(false);
	btnVer.setBounds(250, 482, 50, 24);
	btnVer.addMouseListener(this);
	bolsa.add(btnVer);

	btnQuitar = new AButton("Quitar");
	btnQuitar.setVisible(false);
	btnQuitar.setBounds(310, 482, 50, 24);
	btnQuitar.addMouseListener(this);
	bolsa.add(btnQuitar);

	msjImagen = new ALabel("M\u00E1ximo 1MB");
	msjImagen.setVerticalAlignment(SwingConstants.BOTTOM);
	msjImagen.setBounds(120, 506, 350, 24);
	bolsa.add(msjImagen);

	btnCrear = new AButton("Crear bolsa");
	btnCrear.setBounds(310, 530, 130, 30);
	btnCrear.addMouseListener(this);
	panel.add(btnCrear);

	btnGuardar = new AButton("Guardar cambios");
	btnGuardar.setBounds(235, 530, 130, 30);
	btnGuardar.addMouseListener(this);
	btnGuardar.setVisible(false);
	// panel.add(btnGuardar);

	btnCancelar = new AButton("Regresar");
	btnCancelar.setBounds(310, 530, 130, 30);
	btnCancelar.addMouseListener(this);
	btnCancelar.setVisible(false);
	panel.add(btnCancelar);

	this.be = new BolsaE();
	this.file = null;

	setMedidas("<html><body><b " + style + ">Cm</b></body></html>");
    }

    public void setModo(int modo) {
	this.modo = modo;
    }

    public void setDatos(BolsaE be) {
	this.be = be;

	spnAncho.setValue(this.be.getAncho());
	spnLargo.setValue(this.be.getLargo());
	spnCalibre.setValue(this.be.getCalibre());
	spnTransArriba.setValue(this.be.getTransArriba());
	spnTransAbajo.setValue(this.be.getTransAbajo());
	spnFuelleFondo.setValue(this.be.getFuelle_fondo());
	spnFuelleSuperior.setValue(this.be.getFuelle_superior());
	spnFuelleLateral.setValue(this.be.getFuelle_lateral());
	tbtnTratado.setSeleccionado(this.be.isTratado());
	spnSolapa.setValue(this.be.getSolapa());

	if (this.be.getMedida().equals(strMedida2[0])) {
	    cmbMedida.setSelectedIndex(0);
	} else {
	    cmbMedida.setSelectedIndex(1);
	}

	cmbColorCodigo.setSelectedIndex(devolver_posicion(this.be.getColor_codigo() + "", codigoColor));
	cmbMaterialReferencia.setSelectedIndex(devolver_posicion(this.be.getMaterial_referencia() + "", refenciaMaterial));

	cmbTroquel.setSelectedIndex(devolver_posicion(this.be.getTroquel(), strTroquel2));

	this.file = be.getImagen();
	if (file != null) {
	    msjImagen.setText(this.file.getName());
	    btnQuitar.setVisible(true);
	    btnVer.setVisible(true);

	} else {
	    btnQuitar.setVisible(false);
	    btnVer.setVisible(false);
	    quitarImagen();

	    msjImagen.setText("Sin imagen");
	    msjImagen.setBounds(120, 482, 350, 24);
	}

	desactivar();
    }

    public void desactivar() {
	cmbColorCodigo.setEnabled(false);

	cmbMaterialReferencia.setEnabled(false);

	cmbTroquel.setEnabled(false);

	cmbMedida.setEnabled(false);

	tbtnTratado.setEnabled(false);

	spnAncho.setEnabled(false);
	spnLargo.setEnabled(false);
	spnCalibre.setEnabled(false);
	spnTransArriba.setEnabled(false);
	spnTransAbajo.setEnabled(false);
	spnFuelleFondo.setEnabled(false);
	spnFuelleSuperior.setEnabled(false);
	spnFuelleLateral.setEnabled(false);
	spnSolapa.setEnabled(false);
	bolsa.remove(btnQuitar);
	bolsa.remove(btnImagen);
	panel.remove(btnCrear);
	btnVer.setBounds(120, 482, 50, 24);
    }

    public int devolver_posicion(String i, String[] vector) {
	try {
	    for (int j = 0; j < vector.length; j++) {
		if (i.equals(vector[j])) {
		    return j;
		}
	    }
	} catch (Exception e) {
	}

	return 0;
    }

    public void visible(boolean nuevo, boolean actualizar, boolean crear, boolean container) {
	superior.setVisible(container);
	btnNuevo.setVisible(nuevo);
	btnCrear.setVisible(crear);

	btnGuardar.setVisible(actualizar);
	btnCancelar.setVisible(actualizar);

	superior.repaint();
	panel.repaint();
	Main.menu.frame.repaint();
    }

    public void limpiarCampos() {
	strColor = devolverCodigosColor();
	strMaterial = devolverReferenciasMaterial();

	cmbColorCodigo.setTextos(strColor);
	cmbColorCodigo.setValores(codigoColor);
	cmbColorCodigo.setEstado(Estado.NORMAL);
	cmbColorCodigo.setSelectedIndex(0);

	cmbMaterialReferencia.setTextos(strMaterial);
	cmbMaterialReferencia.setValores(refenciaMaterial);
	cmbMaterialReferencia.setEstado(Estado.NORMAL);
	cmbMaterialReferencia.setSelectedIndex(0);

	cmbTroquel.setSelectedIndex(0);
	cmbTroquel.setEstado(Estado.NORMAL);

	cmbMedida.setSelectedIndex(0);
	setMedidas("<html><body><b " + style + ">Cm</b></body></html>");

	tbtnTratado.setSeleccionado(true);

	spnAncho.setValue(Double.parseDouble(spnAncho.getValorMinimo()));
	spnLargo.setValue(Double.parseDouble(spnLargo.getValorMinimo()));
	spnCalibre.setValue(Double.parseDouble(spnCalibre.getValorMinimo()));
	spnTransArriba.setValue(Double.parseDouble(spnTransArriba.getValorMinimo()));
	spnTransAbajo.setValue(Double.parseDouble(spnTransAbajo.getValorMinimo()));
	spnFuelleFondo.setValue(Double.parseDouble(spnFuelleFondo.getValorMinimo()));
	spnFuelleSuperior.setValue(Double.parseDouble(spnFuelleSuperior.getValorMinimo()));
	spnFuelleLateral.setValue(Double.parseDouble(spnFuelleLateral.getValorMinimo()));
	spnSolapa.setValue(Double.parseDouble(spnSolapa.getValorMinimo()));

	quitarImagen();

    }

    public String[] devolverCodigosColor() {
	ArrayList<ColorE> alce = new ColorN().buscarColor("codigo", "", false);

	String[] s = new String[alce.size() + 1];
	codigoColor = new String[alce.size() + 1];
	s[0] = "-Seleccione-";
	codigoColor[0] = -1 + "";
	for (int i = 1; i <= alce.size(); i++) {
	    ColorE ce = alce.get(i - 1);
	    s[i] = "(Ref: " + ce.getCodigo() + ") " + ce.getNombre();
	    codigoColor[i] = ce.getCodigo() + "";
	}

	return s;
    }

    public String[] devolverReferenciasMaterial() {
	ArrayList<MaterialE> alme = new MaterialN().buscarMaterial("referencia", "", false);
	String[] s = new String[alme.size() + 1];
	refenciaMaterial = new String[alme.size() + 1];
	s[0] = "-Seleccione-";
	refenciaMaterial[0] = -1 + "";
	for (int i = 1; i <= alme.size(); i++) {
	    MaterialE me = alme.get(i - 1);
	    s[i] = "(Ref: " + me.getReferencia() + ") " + me.getNombre();
	    refenciaMaterial[i] = me.getReferencia() + "";
	}

	return s;
    }

    private void quitarImagen() {
	this.file = null;
	btnQuitar.setVisible(false);
	btnVer.setVisible(false);
	msjImagen.setText("M\u00E1ximo 1MB");
    }

    public static boolean isImage(File file) {
	String s = file.getName();
	char[] c = s.toCharArray();
	int cont = 0;
	String[] image = { ".png" };

	for (int i = 0; i < c.length; i++) {
	    if (c[i] == '.') {
		cont = i;
	    }
	}

	String h = s.substring(cont, s.length());
	h = h.toLowerCase();
	for (String image1 : image) {
	    if (h.equals(image1)) {
		return true;
	    }
	}

	return false;
    }

    public void seleccionarImagen() {
	JFileChooser jfc = new JFileChooser();
	FileNameExtensionFilter filter = new FileNameExtensionFilter("png", "png");
	jfc.setFileFilter(filter);
	int seleccion = jfc.showOpenDialog(Main.menu.frame);

	if (seleccion == JFileChooser.APPROVE_OPTION) {
	    if (isImage(jfc.getSelectedFile())) {
		if (jfc.getSelectedFile().length() <= 1048576) {
		    this.file = jfc.getSelectedFile();
		    msjImagen.setText(this.file.getName());
		    btnQuitar.setVisible(true);
		    btnVer.setVisible(true);
		} else {
		    msjImagen.setText("La imagen supera el l\u00EDmite de 1MB");
		}
	    } else {
		msjImagen.setText("Seleccione una imagen");
	    }
	}

    }

    private boolean comprobar() {
	String color = (String) cmbColorCodigo.getSelectedItem();
	String material = (String) cmbMaterialReferencia.getSelectedItem();
	int troquel = cmbTroquel.getSelectedIndex();
	boolean cont = true;

	if (color.equals(strColor[0])) {
	    msjColorCodigo.setText("Seleccion una referencia");
	    msjColorCodigo.setEstado(Estado.ERROR);
	    cmbColorCodigo.setEstado(Estado.ERROR);
	    cont = false;
	} else {
	    msjColorCodigo.setText("");
	    cmbColorCodigo.setEstado(Estado.EXITO);
	}

	if (material.equals(strMaterial[0])) {
	    msjMaterialReferencia.setText("Seleccione una referencia");
	    msjMaterialReferencia.setEstado(Estado.ERROR);
	    cmbMaterialReferencia.setEstado(Estado.ERROR);
	    cont = false;
	} else {
	    msjMaterialReferencia.setText("");
	    cmbMaterialReferencia.setEstado(Estado.EXITO);
	}

	if (troquel == 0) {
	    cmbTroquel.setEstado(Estado.ERROR);
	    msjTroquel.setText("Seleccione un troquel");
	    msjTroquel.setEstado(Estado.ERROR);
	    cont = false;
	} else {
	    cmbTroquel.setEstado(Estado.EXITO);
	    msjTroquel.setText("");
	}
	return cont;
    }

    private void crear() {
	int color = cmbColorCodigo.getSelectedIndex();
	int material = cmbMaterialReferencia.getSelectedIndex();
	int medida = cmbMedida.getSelectedIndex();
	String ancho = spnAncho.getValor();
	String largo = spnLargo.getValor();

	String calibre = spnCalibre.getValor();
	String transArriba = spnTransArriba.getValor();
	String transAbajo = spnTransAbajo.getValor();
	String fuelleFondo = spnFuelleFondo.getValor();
	String fuelleSuperior = spnFuelleSuperior.getValor();
	String fuelleLateral = spnFuelleLateral.getValor();
	String solapa = spnSolapa.getValor();
	String troquel = cmbTroquel.getValor(cmbTroquel.getSelectedIndex());
	BolsaE cbe = new BolsaE();

	if (Main.menu.getUsuario().getTipo().equals("cliente")) {
	    cbe.setCliente_cedula(Main.menu.getUsuario().getClienteCedula());
	}

	cbe.setColor_codigo(Integer.parseInt(cmbColorCodigo.getValor(color)));
	cbe.setMaterial_referencia(Integer.parseInt(cmbMaterialReferencia.getValor(material)));
	cbe.setAncho(Double.parseDouble(ancho));
	cbe.setLargo(Double.parseDouble(largo));
	cbe.setCalibre(Double.parseDouble(calibre));
	cbe.setTratado(tbtnTratado.getSeleccionado());
	cbe.setTransArriba(Double.parseDouble(transArriba));
	cbe.setTransAbajo(Double.parseDouble(transAbajo));
	cbe.setFuelle_fondo(Double.parseDouble(fuelleFondo));
	cbe.setFuelle_lateral(Double.parseDouble(fuelleLateral));
	cbe.setFuelle_superior(Double.parseDouble(fuelleSuperior));
	cbe.setMedida(cmbMedida.getValor(medida));
	cbe.setSolapa(Double.parseDouble(solapa));
	cbe.setTroquel(troquel);

	cbe.setImagen(file);

	String s = new BolsaN().crearBolsa(cbe);
	if (s.equals("")) {
	    msjMensaje.setText("La bolsa ha sido creada exitosamente");
	    msjMensaje.setEstado(Estado.EXITO);
	    visible(true, false, false, false);
	}

	if (s.equals("1")) {
	    msjMensaje.setText("Error desconocido :C");
	    msjMensaje.setEstado(Estado.ERROR);
	    visible(false, false, true, true);
	}

	if (s.equals("2")) {
	    msjMensaje.setText("Error al conectarse a la base de datos");
	    msjMensaje.setEstado(Estado.ERROR);
	    visible(false, false, true, true);
	}
    }

    public void verImagen() {
	String s = file.getAbsolutePath();
	String h = s.substring(3, s.length());
	System.out.println(h);
	String v = "\"" + h + "\"";
	System.out.println(v);
	s = s.replace(h, v);
	System.out.println(s);
	try {
	    Runtime.getRuntime().exec("cmd /c " + s);
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}
    }

    private void actualizar() {
	int color = cmbColorCodigo.getSelectedIndex();
	int material = cmbMaterialReferencia.getSelectedIndex();
	int medida = cmbMedida.getSelectedIndex();
	String ancho = spnAncho.getValor();
	String largo = spnLargo.getValor();

	String calibre = spnCalibre.getValor();
	String transArriba = spnTransArriba.getValor();
	String transAbajo = spnTransAbajo.getValor();
	String fuelleFondo = spnFuelleFondo.getValor();
	String fuelleSuperior = spnFuelleSuperior.getValor();
	String fuelleLateral = spnFuelleLateral.getValor();
	String solapa = spnSolapa.getValor();
	String troquel = cmbTroquel.getValor(cmbTroquel.getSelectedIndex());
	BolsaE cbe = this.be;

	if (Main.menu.getUsuario().getTipo().equals("cliente")) {
	    cbe.setCliente_cedula(Main.menu.getUsuario().getClienteCedula());
	}

	cbe.setColor_codigo(Integer.parseInt(cmbColorCodigo.getValor(color)));
	cbe.setMaterial_referencia(Integer.parseInt(cmbMaterialReferencia.getValor(material)));
	cbe.setAncho(Double.parseDouble(ancho));
	cbe.setLargo(Double.parseDouble(largo));
	cbe.setCalibre(Double.parseDouble(calibre));
	cbe.setTratado(tbtnTratado.getSeleccionado());
	cbe.setTransArriba(Double.parseDouble(transArriba));
	cbe.setTransAbajo(Double.parseDouble(transAbajo));
	cbe.setFuelle_fondo(Double.parseDouble(fuelleFondo));
	cbe.setFuelle_lateral(Double.parseDouble(fuelleLateral));
	cbe.setFuelle_superior(Double.parseDouble(fuelleSuperior));
	cbe.setMedida(cmbMedida.getValor(medida));
	System.out.println(cbe.getMedida());
	cbe.setSolapa(Double.parseDouble(solapa));
	cbe.setTroquel(troquel);
	cbe.setImagen(file);

	String s = new BolsaN().actualizarBolsa(be);

	if (s.equals("")) {
	    Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());
	    Main.removerTodos();
	    Main.menu.frame.getContentPane().add(Main.buscarInsumos.panel);
	    Main.esconderTodos();
	    Main.buscarInsumos.buscar();
	    Main.buscarInsumos.msjMensaje.setText("La bolsa se ha actualizado");
	    Main.buscarInsumos.msjMensaje.setEstado(Estado.EXITO);
	    Main.buscarInsumos.msjMensaje.setVisible(true);
	    Main.buscarInsumos.panel.setVisible(true);
	    Main.dialog.ocultar();
	}

	if (s.equals("1")) {
	    msjMensaje.setText("Error desconocido :C");
	    msjMensaje.setEstado(Estado.ERROR);
	    visible(false, true, false, true);
	}

	if (s.equals("2")) {
	    msjMensaje.setText("Error al conectarse a la base de datos");
	    msjMensaje.setEstado(Estado.ERROR);
	    visible(false, true, false, true);
	}
    }

    private void setMedidas(String s) {
	mddAncho.setText(s);
	mddLargo.setText(s);
	mddTransArriba.setText(s);
	mddTransAbajo.setText(s);
	mddFuelleFondo.setText(s);
	mddFuelleSuperior.setText(s);
	mddFuelleLateral.setText(s);
	mddSolapa.setText(s);
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
	if (e.getSource() == btnImagen) {
	    seleccionarImagen();
	}

	if (e.getSource() == btnQuitar) {
	    quitarImagen();
	}

	if (e.getSource() == btnCrear) {
	    Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());
	    if (comprobar()) {
		crear();
	    }
	    Main.dialog.ocultar();
	}

	if (e.getSource() == btnNuevo) {
	    Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());
	    limpiarCampos();
	    visible(false, false, true, true);
	    msjMensaje.setText("");
	    Main.dialog.ocultar();
	}

	if (e.getSource() == btnVer) {
	    verImagen();
	}

	if (e.getSource() == btnGuardar) {
	    Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());
	    if (comprobar()) {
		actualizar();
	    }
	    Main.dialog.ocultar();
	}

	if (e.getSource() == btnCancelar) {
	    if (modo == 0) {
		Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());
		Main.removerTodos();
		Main.menu.frame.getContentPane().add(Main.buscarInsumos.panel);
		Main.esconderTodos();
		Main.buscarInsumos.buscar();
		Main.buscarInsumos.panel.setVisible(true);
		Main.dialog.ocultar();
	    } else if (modo == 1) {
		Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());
		Main.removerTodos();
		Main.menu.frame.getContentPane().add(Main.ordenExtrusor.panel);
		Main.esconderTodos();
		Main.ordenExtrusor.panel.setVisible(true);
		Main.dialog.ocultar();
	    } else if (modo == 2) {
		Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());
		Main.removerTodos();
		Main.menu.frame.getContentPane().add(Main.ordenImpresor.panel);
		Main.esconderTodos();
		Main.ordenImpresor.panel.setVisible(true);
		Main.dialog.ocultar();
	    } else if (modo == 3) {
		Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());
		Main.removerTodos();
		Main.menu.frame.getContentPane().add(Main.ordenSellado.panel);
		Main.esconderTodos();
		Main.ordenSellado.panel.setVisible(true);
		Main.dialog.ocultar();
	    } else if (modo == 4) {
		Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());
		Main.removerTodos();
		Main.menu.frame.getContentPane().add(Main.ordenFinalizar.panel);
		Main.esconderTodos();
		Main.ordenFinalizar.panel.setVisible(true);
		Main.dialog.ocultar();
	    }
	}

    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void itemStateChanged(ItemEvent e) {

	if (e.getSource() == cmbMedida) {
	    if (cmbMedida.getSelectedIndex() == 0) {
		setMedidas("<html><body><b " + style + ">Cm</b></body></html>");
	    } else {
		setMedidas("<html><body><b " + style + ">Pulg</b></body></html>");
	    }
	}
    }

    @Override
    public void stateChanged(ChangeEvent e) {
	if (e.getSource() == spnAncho) {
	    double d = Double.parseDouble(spnAncho.getValor());
	    d = d / 2;
	    spnFuelleLateral.setValorMaximo(d);
	}

	if (e.getSource() == spnLargo) {
	    double d = Double.parseDouble(spnLargo.getValor());
	    spnTransArriba.setValorMaximo(d / 2);
	    spnTransAbajo.setValorMaximo(d / 2);
	    spnFuelleFondo.setValorMaximo(d / 2);
	    spnFuelleSuperior.setValorMaximo(d / 2);
	    spnSolapa.setValorMaximo(d / 2);
	}

    }
}
