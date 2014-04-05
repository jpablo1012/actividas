package Gui;

import Entidades.UsuarioE;
import api.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Menu implements MouseListener {

    AFrame frame;
    AMenu menu;
    int ultimo, penultimo;
    private UsuarioE usuario;

    public Menu(UsuarioE ue) {
        this.usuario = ue;
        frame = new AFrame(920, 600);
        frame.setLocationRelativeTo(null);
        menu = new AMenu();
        menu.setTipo(evaluar(ue.getTipo()));
        frame.add(menu);

        ultimo = menu.ultimo;
        penultimo = menu.penultimo;
        menu.botones[ultimo].addMouseListener(this);
        menu.botones[penultimo].addMouseListener(this);

        frame.repaint();
        frame.setVisible(true);

        if (evaluar(ue.getTipo()) == 1) {
            menu.botones[0].addMouseListener(this);
            menu.botones[1].addMouseListener(this);
            menu.botones[2].addMouseListener(this);
            menu.botones[3].addMouseListener(this);
            menu.botones[4].addMouseListener(this);
            menu.botones[5].addMouseListener(this);
            menu.botones[6].addMouseListener(this);
        }

        if (evaluar(ue.getTipo()) == 0) {
            menu.botones[0].addMouseListener(this);
            menu.botones[1].addMouseListener(this);
        }

        if (evaluar(ue.getTipo()) == 2) {
            menu.botones[0].addMouseListener(this);
            menu.botones[1].addMouseListener(this);
            menu.botones[2].addMouseListener(this);
        }

        // menu.jcb.addChangeListener(this);
        Main.dialog.enInicio(false);
        Main.esconderTodos();
        frame.toFront();
    }

    public UsuarioE getUsuario() {
        return this.usuario;
    }

    public void setUsuario(UsuarioE ue) {
        this.usuario = ue;
    }

    public int evaluar(String s) {
        if (s.equals("administrador")) {
            return 1;
        }
        if (s.equals("cliente")) {
            return 2;
        }
        return 0;
    }
    
    private void limpiar(){
	for(int i = 0; i < menu.botones.length; i++){
	    menu.botones[i].setTipo(1);
	}
    }
    
    private void establecer(int b){
	limpiar();
	
	menu.botones[b].setTipo(2);
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        if (e.getSource() == menu.botones[0]) {
            Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());
            Main.removerTodos();
            Main.inicio = new Inicio();
            frame.add(Main.inicio.panel);
            Main.esconderTodos();
            Main.inicio.panel.setVisible(true);
            Main.dialog.ocultar();
            establecer(0);
        }

        if (e.getSource() == menu.botones[1]) {
            if (menu.botones[1].getText().equals(menu.administrador[1])) {
                Main.removerTodos();
                Main.insumos = new Insumos();
                frame.add(Main.insumos.panel);
                Main.esconderTodos();
                Main.insumos.panel.setVisible(true);
            }

            if (menu.botones[1].getText().equals(menu.empleado[1])) {
                Main.removerTodos();
                Main.ordenes = new Ordenes();
                frame.add(Main.ordenes.panel);
                Main.esconderTodos();
                Main.ordenes.panel.setVisible(true);
            }
            if (menu.botones[1].getText().equals(menu.cliente[1])) {
                Main.removerTodos();
                Main.insumos = new Insumos();
                frame.add(Main.insumos.panel);
                Main.esconderTodos();
                Main.insumos.panel.setVisible(true);
            }
            establecer(1);
        }

        if (e.getSource() == menu.botones[2]) {
            Main.removerTodos();
            Main.pedido = new Pedido();
            frame.add(Main.pedido.panel);
            Main.esconderTodos();
            Main.pedido.panel.setVisible(true);
            establecer(2);
        }

        try {
            if (e.getSource() == menu.botones[3]) {
                Main.removerTodos();
                Main.ordenes = new Ordenes();
                frame.add(Main.ordenes.panel);
                Main.esconderTodos();
                Main.ordenes.panel.setVisible(true);
                establecer(3);
            }
        } catch (Exception ee) {
        }

        if (e.getSource() == menu.botones[ultimo]) {
            Main.inicioSesion = new InicioSesion();
            frame.setVisible(false);
        }
        try {
            if (e.getSource() == menu.botones[4]) {
                Main.removerTodos();
                Main.cliente = new Cliente();
                frame.add(Main.cliente.panel);
                Main.esconderTodos();
                Main.cliente.panel.setVisible(true);
                establecer(4);
            }
        } catch (Exception ee) {
        }

        try {
            if (e.getSource() == menu.botones[5]) {
                Main.removerTodos();
                Main.empleado = new Empleado();
                frame.add(Main.empleado.panel);
                Main.esconderTodos();
                Main.empleado.panel.setVisible(true);
                establecer(5);
            }
        } catch (Exception ee) {
        }

        try {
            if (e.getSource() == menu.botones[6]) {
                Main.removerTodos();
                Main.informes = new Informes();
                frame.add(Main.informes.panel);
                Main.esconderTodos();
                Main.informes.panel.setVisible(true);
                establecer(6);
            }
        } catch (Exception ee) {
        }

        try {
            if (e.getSource() == menu.botones[penultimo]) {
                Main.removerTodos();
                Main.ajustes = new Ajustes();
                frame.add(Main.ajustes.panel);
                Main.esconderTodos();
                Main.ajustes.panel.setVisible(true);
                establecer(penultimo);
            }
        } catch (Exception ee) {
        }

    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }
}
