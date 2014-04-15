package Gui;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import api.AButton;
import api.APanel;

public class Empleado implements MouseListener {

    public APanel panel;
    public AButton btnCrear;
    public AButton btnModificar;
    public AButton btnEliminar;
    public AButton btnBuscar;

    public Empleado() {
        panel = new APanel(Main.x, 0, 750, 600);
        panel.setTitulo("Empleados");

        btnCrear = new AButton("Crear empleado");
        btnCrear.setBounds(295, 199, 160, 60);
        btnCrear.addMouseListener(this);
        panel.add(btnCrear);

        btnBuscar = new AButton("<html><body align='center'>Buscar, actualizar o eliminar empleados</body></html>");
        btnBuscar.setBounds(295, 279, 160, 60);
        btnBuscar.addMouseListener(this);
        panel.add(btnBuscar);

        panel.setVisible(false);

    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        if (e.getSource() == btnCrear) {
            Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());
            Main.crearEmpleado = new CrearEmpleado();
            Main.removerTodos();
            Main.menu.frame.add(Main.crearEmpleado.panel);
            Main.esconderTodos();
            Main.crearEmpleado.panel.setVisible(true);
            Main.dialog.ocultar();
        }

        if (e.getSource() == btnBuscar) {
            Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());
            Main.buscarEmpleado = new BuscarEmpleado();
            Main.removerTodos();
            Main.menu.frame.add(Main.buscarEmpleado.panel);
            Main.esconderTodos();
            Main.buscarEmpleado.panel.setVisible(true);
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
