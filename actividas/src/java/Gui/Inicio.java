package Gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import Entidades.ExtrusionE;
import Entidades.ImpresionE;
import Entidades.OrdenE;
import Entidades.PedidoE;
import Entidades.SelladoE;
import Entidades.UsuarioE;
import Negocio.ExtrusionN;
import Negocio.ImpresionN;
import Negocio.OrdenN;
import Negocio.PedidoN;
import Negocio.SelladoN;
import Negocio.UsuarioN;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import api.ALabel;
import api.APanel;
import api.AScrollPanel;
import api.ATable;
import api.Colores;

public class Inicio implements MouseListener {

    APanel panel;
    ALabel lblUsuario;
    ALabel lblCargo;
    ALabel imagen;
    ATable tabla;
    AScrollPanel resultado;
    DefaultTableModel dtm;

    public Inicio() {
	panel = new APanel(Main.x, 0, 750, 600);
	panel.setTitulo("Inicio");

	lblUsuario = new ALabel("");
	lblUsuario.setBounds(0, 50, panel.getWidth(), 30);
	lblUsuario.setFont(new Font("Calibri", Font.PLAIN, 34));
	lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
	lblUsuario.setForeground(Colores.TITULO_NORMAL);
	panel.add(lblUsuario);

	lblCargo = new ALabel("");
	lblCargo.setBounds(0, 80, panel.getWidth(), 24);
	lblCargo.setFont(new Font("Calibri", Font.PLAIN, 22));
	lblCargo.setHorizontalAlignment(SwingConstants.CENTER);
	lblCargo.setForeground(Colores.TITULO_NORMAL);
	panel.add(lblCargo);

	imagen = new ALabel("");
	imagen.setHorizontalAlignment(SwingConstants.CENTER);
	imagen.setVerticalAlignment(SwingConstants.CENTER);
	imagen.setBounds(300, 104, 150, 150);
	panel.add(imagen);

	resultado = new AScrollPanel("");
	resultado.setBounds(74, 297, 600, 190);
	resultado.setVisible(false);
	panel.add(resultado);

	tabla = new ATable();
	tabla.setEnabled(false);
	tabla.setPreferredSize(new Dimension(650, 125));
	resultado.setViewportView(tabla);

	setDatos();
	llenarTabla();
	Main.menu.frame.repaint();
	panel.setVisible(false);
    }

    private void setDatos() {
	ArrayList<UsuarioE> alue = new UsuarioN().buscarUsuario("idusuario", Main.menu.getUsuario().getIdUsuario(), true, true);

	Main.menu.setUsuario(alue.get(0));

	UsuarioE ue = Main.menu.getUsuario();
	lblUsuario.setText(ue.getNombre() + " " + ue.getApellido());
	lblCargo.setText(ue.getTipo());
	try {
	    ImageIcon fot = new ImageIcon(Main.menu.getUsuario().getImagen().getAbsolutePath());
	    Icon icono = new ImageIcon(fot.getImage().getScaledInstance(imagen.getWidth(), imagen.getHeight(), Image.SCALE_AREA_AVERAGING));
	    imagen.setIcon(icono);

	    if (ue.getImagen() != null) {
		imagen.setBorder(new MatteBorder(1, 1, 1, 1, Colores.BORDE_CONTENEDOR));
	    }
	} catch (Exception e) {
	}

    }

    private void llenarTabla() {
	int modo = Main.menu.evaluar(Main.menu.getUsuario().getTipo());
	if (modo == 2) {
	    llenarCliente();
	}

	if (modo == 1) {
	    llenarAdmin();
	}

	if (modo == 0) {
	    String s = Main.menu.getUsuario().getTipo();

	    if (s.equals("extrusor")) {
		llenarExtrusor();
	    }

	    if (s.equals("impresor")) {
		llenarImpresor();
	    }

	    if (s.equals("sellador")) {
		llenarSellador();
	    }
	}
    }

    private void llenarSellador() {
	ArrayList<SelladoE> alse = new SelladoN().buscarSellado("estado", "ejecutando", true);
	ArrayList<OrdenE> aloe = new OrdenN().buscarOrden("estado", "sellado", false);
	if (aloe == null || alse == null) {

	} else if (aloe.size() == 0 || alse.size() == 0) {

	} else {
	    int alto = 0;
	    Object[][] result = new Object[alse.size()][4];
	    int cont = 0;
	    for (int i = 0; i < aloe.size(); i++) {
		for (int j = 0; j < alse.size(); j++) {
		    SelladoE ee = alse.get(j);
		    OrdenE oe = aloe.get(i);
		    if (oe.getNumeroOrden() == ee.getNumeroOrden()) {
			result[i][0] = ee.getCodigo();
			result[i][1] = ee.getNumeroOrden();
			result[i][2] = ee.getFechaInicio();
			result[i][3] = oe.getFecha_plazo();
			cont++;
			alto += 25;
		    }

		}
	    }

	    resultado.setText("<html><body> Procesos de sellado pendientes: <b> " + cont + " </b></body></html>");

	    dtm = new DefaultTableModel(result, new String[] { "Codigo", "# orden", "Fecha de inicio", "Plazo hasta" });
	    tabla.setModel(dtm);
	    tabla.repaint();
	    tabla.setPreferredSize(new Dimension(650, alto));
	    resultado.setVisible(true);
	}
    }

    private void llenarImpresor() {
	ArrayList<ImpresionE> alie = new ImpresionN().buscarImpresion("estado", "ejecutando", true);
	ArrayList<OrdenE> aloe = new OrdenN().buscarOrden("estado", "impresi\u00F3n", false);
	if (aloe == null || alie == null) {

	} else if (aloe.size() == 0 || alie.size() == 0) {

	} else {
	    int alto = 0;
	    Object[][] result = new Object[alie.size()][4];
	    int cont = 0;
	    for (int i = 0; i < aloe.size(); i++) {
		for (int j = 0; j < alie.size(); j++) {
		    ImpresionE ee = alie.get(j);
		    OrdenE oe = aloe.get(i);
		    if (oe.getNumeroOrden() == ee.getNumeroOrden()) {
			result[i][0] = ee.getCodigo();
			result[i][1] = ee.getNumeroOrden();
			result[i][2] = ee.getFechaInicio();
			result[i][3] = oe.getFecha_plazo();
			cont++;
			alto += 25;
		    }

		}
	    }

	    resultado.setText("<html><body> Procesos de impresion pendientes: <b> " + cont + " </b></body></html>");

	    dtm = new DefaultTableModel(result, new String[] { "Codigo", "# orden", "Fecha de inicio", "Plazo hasta" });
	    tabla.setModel(dtm);
	    tabla.repaint();
	    tabla.setPreferredSize(new Dimension(650, alto));
	    resultado.setVisible(true);
	}
    }

    private void llenarExtrusor() {
	ArrayList<ExtrusionE> alee = new ExtrusionN().buscarExtrusion("estado", "ejecutando", true);
	ArrayList<OrdenE> aloe = new OrdenN().buscarOrden("estado", "extrusi\u00F3n", false);
	if (aloe == null || alee == null) {

	} else if (aloe.size() == 0 || alee.size() == 0) {

	} else {
	    int alto = 0;
	    Object[][] result = new Object[alee.size()][4];
	    int cont = 0;
	    for (int i = 0; i < aloe.size(); i++) {
		for (int j = 0; j < alee.size(); j++) {
		    ExtrusionE ee = alee.get(j);
		    OrdenE oe = aloe.get(i);
		    if (oe.getNumeroOrden() == ee.getNumeroOrden()) {
			result[i][0] = ee.getCodigo();
			result[i][1] = ee.getNumeroOrden();
			result[i][2] = ee.getFechaInicio();
			result[i][3] = oe.getFecha_plazo();
			cont++;
			alto += 25;
		    }

		}
	    }

	    resultado.setText("<html><body> Procesos de extrusion pendientes: <b> " + cont + " </b></body></html>");

	    dtm = new DefaultTableModel(result, new String[] { "Codigo", "# orden", "Fecha de inicio", "Plazo hasta" });
	    tabla.setModel(dtm);
	    tabla.repaint();
	    tabla.setPreferredSize(new Dimension(650, alto));
	    resultado.setVisible(true);
	}
    }

    private void llenarAdmin() {
	ArrayList<PedidoE> alpe = new PedidoN().buscarPedido("estado", "pendiente", true);
	if (alpe == null) {

	} else if (alpe.size() == 0) {

	} else {
	    int alto = 0;
	    Object[][] result = new Object[alpe.size()][4];
	    int cont = 0;
	    for (int i = 0; i < alpe.size(); i++) {
		PedidoE pe = alpe.get(i);
		result[cont][0] = pe.getNumeroPedido();
		result[cont][1] = pe.getCliente_cedula();
		result[cont][2] = pe.getFecha_creacion();
		result[cont][3] = pe.getFecha_entrega();
		cont++;
		alto += 25;
	    }

	    resultado.setText("<html><body>Pedidos pendientes por aprobar: <b> " + alpe.size() + " </b></body></html>");

	    dtm = new DefaultTableModel(result, new String[] { "# pedido", "Cedula del cliente", "Fecha de creacion", "Fecha de entrega" });
	    tabla.setModel(dtm);
	    tabla.repaint();
	    tabla.setPreferredSize(new Dimension(650, alto));
	    resultado.setVisible(true);
	}
    }

    private void llenarCliente() {
	ArrayList<PedidoE> alpe = new PedidoN().buscarPedido("cliente_cedula", Main.menu.getUsuario().getClienteCedula(), true);

	if (alpe == null) {

	} else if (alpe.size() == 0) {

	} else {
	    int alto = 0;
	    Object[][] result = new Object[5][4];
	    int cont = 0;
	    for (int i = 0; i < 5 && i < alpe.size(); i++) {
		PedidoE pe = alpe.get((alpe.size() - 1) - i);
		result[cont][0] = pe.getNumeroPedido();
		result[cont][1] = pe.getFecha_creacion();
		result[cont][2] = pe.getFecha_entrega();
		result[cont][3] = pe.getEstado();
		cont++;
		alto += 25;
	    }

	    resultado.setText("<html><body>Tus ultimos pedidos</body></html>");

	    dtm = new DefaultTableModel(result, new String[] { "# pedido", "Fecha de creacion", "Fecha de entrega", "Estado" });
	    tabla.setModel(dtm);
	    tabla.repaint();
	    tabla.setPreferredSize(new Dimension(650, alto));
	    resultado.setVisible(true);
	}
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

}
