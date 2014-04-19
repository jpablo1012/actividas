package Gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import Negocio.EmpleadoN;
import api.*;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.io.File;
import java.io.FileOutputStream;

public class InformesSellador implements MouseListener, PropertyChangeListener {

    APanel panel;
    AContainer busq;
    ALabel lblInforme, lblTexto, lblY, lblGrafico;
    AButton btnInforme, btnNueva, btnInicio, btnGrafico;
    ALabel msjMensaje;
    AScrollPanel resultado, grafico;
    ATable tabla;
    ADateChosser dtFechaInicio, dtFechaFinal;
    AButton btnGuardar;

    DefaultTableModel dtm;

    ArrayList<Object[]> alo;

    JFreeChart barra;

    public InformesSellador() {

	panel = new APanel(Main.x, 0, 750, 600);

	lblInforme = new ALabel("Informes| Sellador");
	lblInforme.setFont(new Font("Calibri", Font.PLAIN, 24));
	lblInforme.setForeground(Colores.titulo_normal);
	lblInforme.setBounds(10, 0, 336, 50);
	panel.add(lblInforme);

	busq = new AContainer("Informe");
	busq.setBounds(110, 200, 530, 171);
	panel.add(busq);

	lblTexto = new ALabel("Informe entre: ");
	lblTexto.setBounds(30, 30, 90, 24);
	lblTexto.setHorizontalAlignment(SwingConstants.RIGHT);
	busq.add(lblTexto);

	dtFechaInicio = new ADateChosser();
	dtFechaInicio.addPropertyChangeListener(this);
	dtFechaInicio.setBounds(140, 30, 120, 24);
	dtFechaInicio.setMaxSelectableDate(new Date());
	busq.add(dtFechaInicio);

	lblY = new ALabel("y");
	lblY.setBounds(280, 30, 8, 24);
	busq.add(lblY);

	dtFechaFinal = new ADateChosser();
	dtFechaFinal.setDate(new Date());
	dtFechaFinal.setMaxSelectableDate(new Date());
	dtFechaFinal.setBounds(308, 30, 120, 24);
	busq.add(dtFechaFinal);

	btnInforme = new AButton("Generar informe entre fechas");
	btnInforme.setBounds(165, 74, 200, 30);
	btnInforme.addMouseListener(this);
	busq.add(btnInforme);

	btnInicio = new AButton("Generar informe sin fechas");
	btnInicio.setBounds(165, 115, 200, 30);
	btnInicio.addMouseListener(this);
	busq.add(btnInicio);

	msjMensaje = new ALabel("");
	msjMensaje.setHorizontalAlignment(SwingConstants.CENTER);
	msjMensaje.setBounds(0, 538, 749, 20);
	msjMensaje.setFont(new Font("Calibri", Font.PLAIN, 18));
	panel.add(msjMensaje);

	resultado = new AScrollPanel("");
	resultado.setBounds(75, 60, 600, 400);
	resultado.setVisible(false);
	panel.add(resultado);

	tabla = new ATable();
	tabla.setPreferredSize(new Dimension(650, 340));
	resultado.setViewportView(tabla);

	btnNueva = new AButton("Regresar");
	btnNueva.setBounds(235, 480, 130, 30);
	btnNueva.setVisible(false);
	btnNueva.addMouseListener(this);
	panel.add(btnNueva);

	btnGrafico = new AButton("Generar grafico");
	btnGrafico.setBounds(385, 480, 130, 30);
	btnGrafico.setVisible(false);
	btnGrafico.addMouseListener(this);
	panel.add(btnGrafico);

	grafico = new AScrollPanel("Grafico");
	grafico.setBounds(60, 50, 630, 410);
	grafico.setVisible(false);
	panel.add(grafico);

	lblGrafico = new ALabel("");
	grafico.setViewportView(lblGrafico);

	btnGuardar = new AButton("Guardar grafico");
	btnGuardar.setBounds(385, 480, 130, 30);
	btnGuardar.addMouseListener(this);
	btnGuardar.setVisible(false);
	panel.add(btnGuardar);

	alo = null;
    }

    public String informeSinFechas() {
	alo = new EmpleadoN().informesSellador();

	if (alo == null) {
	    return "1";
	} else if (alo.size() == 0) {
	    return "1";
	} else {
	    int alto = 0;
	    Object[][] result = new Object[alo.size()][5];
	    for (int i = 0; i < alo.size(); i++) {
		Object[] o = alo.get(i);

		result[i][0] = i + 1;
		result[i][1] = o[0];
		result[i][2] = o[1];
		result[i][3] = o[2];
		result[i][4] = o[3];

		alto += 25;
	    }
	    resultado.setText("<html><body>Informe sellador<b></b></body></html>");

	    dtm = new DefaultTableModel(result, new String[] { "Puesto", "Nombre", "Apellido", "C\u00E9dula", "Bolsas selladas" });
	    tabla.setModel(dtm);
	    tabla.repaint();
	    tabla.setPreferredSize(new Dimension(650, alto));
	}

	return "";
    }

    public boolean comprobar() {
	Date inicio = dtFechaInicio.getDate();
	Date fin = dtFechaFinal.getDate();
	boolean cont = true;

	try {
	    msjMensaje.setText("");
	    dtFechaInicio.setEstado(Estado.normal);
	    if (inicio.compareTo(fin) > 1) {
		msjMensaje.setText("No debes seleccionar una fecha anterior al primer campo de fecha");
		msjMensaje.setEstado(Estado.error);
		dtFechaFinal.setEstado(Estado.error);
		cont = false;
	    } else {
		msjMensaje.setText("");
		dtFechaFinal.setEstado(Estado.normal);
	    }

	} catch (Exception e) {
	    msjMensaje.setText("El primer campo de fecha esta Campo vac\u00EDo");
	    msjMensaje.setEstado(Estado.error);
	    dtFechaInicio.setEstado(Estado.error);
	    cont = false;
	}

	return cont;

    }

    public void visibleInforme(boolean b) {
	resultado.setVisible(b);
	btnNueva.setVisible(b);
	btnGrafico.setVisible(b);
	busq.setVisible(!b);

	btnNueva.setBounds(235, 480, 130, 30);
    }

    public String informeConFechas() {
	Date inicio = dtFechaInicio.getDate();
	Date fin = dtFechaFinal.getDate();
	this.alo = new EmpleadoN().informesSellador(inicio, fin);

	if (alo == null) {
	    return "1";
	} else if (alo.size() == 0) {
	    return "1";
	} else {
	    int alto = 0;
	    Object[][] result = new Object[alo.size()][5];
	    for (int i = 0; i < alo.size(); i++) {
		Object[] o = alo.get(i);

		result[i][0] = i + 1;
		result[i][1] = o[0];
		result[i][2] = o[1];
		result[i][3] = o[2];
		result[i][4] = o[3];

		alto += 25;
	    }
	    resultado.setText("<html><body>Informe sellador<b></b></body></html>");

	    dtm = new DefaultTableModel(result, new String[] { "Puesto", "Nombre", "Apellido", "C\u00E9dula", "Bolsas selladas" });
	    tabla.setModel(dtm);
	    tabla.repaint();
	    tabla.setPreferredSize(new Dimension(650, alto));
	}

	return "";
    }

    public void generarGrafico() {
	visibleInforme(false);
	busq.setVisible(false);

	DefaultCategoryDataset datos = new DefaultCategoryDataset();
	for (int i = 0; i < this.alo.size(); i++) {
	    Object[] o = this.alo.get(i);
	    datos.addValue(Double.parseDouble((String) o[3]), "", (String) o[2]);
	}

	barra = ChartFactory.createBarChart("Informe sellador", "C\u00E9dula", "Bolsas selladas", datos, PlotOrientation.HORIZONTAL, false, false, true);
	barra.setBackgroundPaint(Colores.fondo_normal);
	barra.setBackgroundImageAlpha(0f);
	barra.setBorderVisible(false);
	final CategoryPlot plot = barra.getCategoryPlot();
	plot.setRenderer(new ARendered());
	
	int multi = 150;
	
	if(this.alo.size() > 2){
	    multi = this.alo.size() * 60;
	}

	BufferedImage grafico = barra.createBufferedImage(lblGrafico.getWidth() - 20, multi);
	lblGrafico.setIcon(new ImageIcon(grafico));

	this.grafico.setVisible(true);
	this.grafico.repaint();

	this.btnNueva.setVisible(true);
	this.btnGuardar.setVisible(true);

    }

    public void guardarGrafico() {
	String clase = "C://Users//" + System.getProperty("user.name") + "//Desktop//Gr\u00E1ficos";
	File carpeta = new File(clase);
	carpeta.mkdirs();

	String text = "Informe selladores " + new Date().getTime() + ".png";
	File imagen = new File(carpeta, text);

	try {
	    FileOutputStream fileOut = new FileOutputStream(imagen);
	    if(this.alo.size() <= 2){
		 ChartUtilities.writeChartAsPNG(fileOut, barra, lblGrafico.getWidth(), 150);
	    }else{
		 ChartUtilities.writeChartAsPNG(fileOut, barra, lblGrafico.getWidth(), this.alo.size() * 60);
	    }
	   
	    fileOut.close();
	    
	    msjMensaje.setText("La grafica se ha guardado en Escritorio/Gr\u00E1ficos");
	    msjMensaje.setEstado(Estado.exito);

	    String s = imagen.getAbsolutePath();
	    String h = s.substring(3, s.length());
	    String v = "\"" + h + "\"";
	    s = s.replace(h, v);
	    System.out.println(s);
	    try {
		Runtime.getRuntime().exec("cmd /c " + s);
	    } catch (Exception e) {
		System.out.println(e.getMessage());
	    }
	} catch (Exception e) {
	    System.out.println(e);
	}

    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
	if (e.getSource() == btnInicio) {
	    Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());
	    msjMensaje.setText("");
	    String s = informeSinFechas();
	    if (s.equals("")) {
		visibleInforme(true);
	    } else {
		visibleInforme(false);
		msjMensaje.setText("No hay datos para generar un informe");
		msjMensaje.setEstado(Estado.error);
	    }
	    Main.dialog.ocultar();
	}

	if (e.getSource() == btnInforme) {
	    Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());
	    if (comprobar()) {
		String s = informeConFechas();
		if (s.equals("")) {
		    visibleInforme(true);
		} else {
		    visibleInforme(false);
		    msjMensaje.setText("No hay datos para generar un informe");
		    msjMensaje.setEstado(Estado.error);
		}
	    }
	    Main.dialog.ocultar();
	}

	if (e.getSource() == btnNueva) {
	    if (grafico.isVisible()) {
		visibleInforme(true);
		grafico.setVisible(false);
		btnGuardar.setVisible(false);
	    } else {
		visibleInforme(false);
		dtFechaFinal.setDate(new Date());
		dtFechaInicio.setDate(null);
		this.alo = null;
	    }

	}

	if (e.getSource() == btnGrafico) {
	    Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());
	    generarGrafico();
	    Main.dialog.ocultar();
	}

	if (e.getSource() == btnGuardar) {
	    Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());
	    guardarGrafico();
	    Main.dialog.ocultar();
	}
    }

    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void propertyChange(PropertyChangeEvent e) {
	if (e.getSource() == dtFechaInicio) {
	    try {
		dtFechaFinal.setMinSelectableDate(dtFechaInicio.getDate());
	    } catch (Exception ee) {
	    }
	}
    }

}
