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

import api.AButton;
import api.AComboBox;
import api.AContainer;
import api.ALabel;
import api.APanel;
import api.AScrollPanel;
import api.ATable;
import api.ATextField;
import api.Estado;

import Entidades.EmpleadoE;
import Entidades.UsuarioE;
import Negocio.EmpleadoN;
import Negocio.UsuarioN;

public class BuscarEmpleado implements MouseListener, KeyListener {

    public APanel panel;
    AContainer busq;
    String[] bus = { "-Seleccionar-", "Apellido", "Cargo", "C\u00E9dula", "Nombre" };
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
    DefaultTableModel dtm;
    ArrayList<EmpleadoE> alee;

    public BuscarEmpleado() {
	panel = new APanel(Main.x, 0, 750, 600);
	panel.setTitulo("Empleado| Buscar");

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

	comBuscar = new AComboBox(bus);
	comBuscar.setBounds(117, 30, 120, 24);
	busq.add(comBuscar);

	btnBuscar = new AButton("Buscar empleado");
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
	btnNueva.setBounds(128, 480, 130, 30);
	btnNueva.setVisible(false);
	btnNueva.addMouseListener(this);
	panel.add(btnNueva);

	btnActualizar = new AButton("Actualizar empleado");
	btnActualizar.setBounds(281, 480, 130, 30);
	btnActualizar.addMouseListener(this);
	btnActualizar.setVisible(false);
	panel.add(btnActualizar);

	btnEliminar = new AButton("Eliminar empleado");
	btnEliminar.setBounds(440, 480, 130, 30);
	btnEliminar.addMouseListener(this);
	btnEliminar.setVisible(false);
	panel.add(btnEliminar);
    }

    private void visibleBuscar(boolean b) {
	resultado.setVisible(b);
	busq.setVisible(!b);
	btnNueva.setVisible(b);
	btnActualizar.setVisible(b);
	btnEliminar.setVisible(b);
    }

    public boolean comprobar() {
	String var = (String) comBuscar.getSelectedItem();
	boolean cont = true;
	var = var.toLowerCase();

	if (var.equals("-seleccionar-")) {
	    comBuscar.setEstado(Estado.ERROR);
	    msjMensaje.setText("Seleccione un campo de la lista desplegable");
	    msjMensaje.setEstado(Estado.ERROR);
	    msjMensaje.setVisible(true);
	    cont = false;
	}

	if (cont) {
	    return true;
	} else {
	    return false;
	}
    }

    public void buscar() {
	String consulta = txtBuscar.getText();
	String var = (String) comBuscar.getSelectedItem();
	msjMensaje.setText("");

	var = var.replaceAll(bus[1], "apellido");
	var = var.replaceAll(bus[2], "cargo");
	var = var.replaceAll(bus[3], "cedula");
	var = var.replaceAll(bus[4], "nombre");

	EmpleadoN en = new EmpleadoN();

	this.alee = en.buscarEmpleado(var, consulta, false);
	ArrayList<UsuarioE> alue = new UsuarioN().buscarUsuario("empleado_cedula", "", false);

	if (this.alee == null) {
	    msjMensaje.setText("El valor que usted busca no existe en la base de datos");
	    msjMensaje.setEstado(Estado.ERROR);
	    msjMensaje.setVisible(true);
	} else {
	    int alto = 0;
	    int cont = 0;
	    if (alee.size() == 0) {
		msjMensaje.setText("El valor que usted busca no existe en la base de datos");
		msjMensaje.setEstado(Estado.ERROR);
		msjMensaje.setVisible(true);
		visibleBuscar(false);
	    } else {
		Object[][] result = new Object[this.alee.size()][4];
		for (int i = 0; i < this.alee.size(); i++) {
		    for (int j = 0; j < alue.size(); j++) {
			EmpleadoE eeh = this.alee.get(i);
			UsuarioE ueh = alue.get(j);

			if (eeh.getCedula().equals(ueh.getEmpleadoCedula())) {
			    result[i][0] = eeh.getCedula();
			    result[i][1] = eeh.getNombre();
			    result[i][2] = eeh.getApellido();
			    result[i][3] = eeh.getCargo();
			    cont++;
			    alto += 25;
			}
		    }
		}

		resultado.setText("<html><body>Empleados encontrados: <b>" + cont + "</b></body></html>");

		dtm = new DefaultTableModel(result, new String[] { "C\u00E9dula", "Nombre", "Apellido", "Cargo" });
		tabla.setModel(dtm);
		tabla.repaint();
		tabla.setPreferredSize(new Dimension(650, alto));
		visibleBuscar(true);
	    }

	}
    }

    private void actualizar() {
	int seleccionado = tabla.getSelectedRow();

	if (seleccionado >= 0) {
	    String valor = (String) tabla.getModel().getValueAt(seleccionado, 0);

	    for (int i = 0; i < this.alee.size(); i++) {
		if (this.alee.get(i).getCedula().equals(valor)) {
		    ArrayList<UsuarioE> actue;

		    actue = new UsuarioN().buscarUsuario("empleado_cedula", valor, true, true);
		    Main.resultadoEmpleado = new ResultadoEmpleado();
		    Main.menu.frame.getContentPane().add(Main.resultadoEmpleado.panel);
		    Main.esconderTodos();
		    Main.resultadoEmpleado.setDatos(actue.get(0), this.alee.get(i));
		    Main.resultadoEmpleado.panel.setVisible(true);
		    break;
		}
	    }
	} else {
	    msjMensaje.setText("Seleccione una fila para actualizar");
	    msjMensaje.setEstado(Estado.ERROR);
	    msjMensaje.setVisible(true);

	}
    }

    private void eliminar() {
	int seleccionado = tabla.getSelectedRow();

	if (seleccionado >= 0) {
	    String valor = (String) tabla.getModel().getValueAt(seleccionado, 0);
	    if (Main.menu.getUsuario().getEmpleadoCedula().equals(valor)) {
		msjMensaje.setText("No te puedes eliminar");
		msjMensaje.setEstado(Estado.ERROR);
		msjMensaje.setVisible(true);
	    } else {
		int cont = JOptionPane.showConfirmDialog(Main.menu.frame, "\u00BFDesea eliminar este empleado?", "Eliminar empleado", JOptionPane.YES_NO_OPTION);
		if (cont == 0) {

		    for (int i = 0; i < this.alee.size(); i++) {
			if (this.alee.get(i).getCedula().equals(valor)) {
			    ArrayList<UsuarioE> elue = new UsuarioN().buscarUsuario("empleado_cedula", valor, true);

			    String s = new UsuarioN().eliminarUsuario(elue.get(0).getIdUsuario());

			    if (s.equals("")) {
				s = new EmpleadoN().eliminarEmpleado(this.alee.get(i).getCedula());

				if (s.equals("")) {
				    buscar();
				    msjMensaje.setText("El empleado ha sido eliminado");
				    msjMensaje.setEstado(Estado.EXITO);
				    msjMensaje.setVisible(true);
				}
			    }

			    if (s.equals("1")) {
				msjMensaje.setText("Ha ocurrido un error al eliminar el empleado");
				msjMensaje.setEstado(Estado.ERROR);
				msjMensaje.setVisible(true);
			    }

			    if (s.equals("2")) {
				msjMensaje.setText("Error al conectarse a la base de datos");
				msjMensaje.setEstado(Estado.ERROR);
				msjMensaje.setVisible(true);
			    }

			    break;
			}
		    }
		}
	    }

	} else {
	    msjMensaje.setText("Seleccione una fila para eliminar");
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

	if (e.getSource() == btnActualizar) {
	    Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());
	    actualizar();
	    Main.dialog.ocultar();
	}

	if (e.getSource() == btnEliminar) {
	    Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());
	    eliminar();
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
