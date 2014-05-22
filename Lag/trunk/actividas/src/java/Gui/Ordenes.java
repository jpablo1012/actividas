package Gui;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingConstants;

import api.AButton;
import api.ALabel;
import api.APanel;
import api.Estado;
import Entidades.UsuarioE;

public class Ordenes implements MouseListener{

    APanel panel;
    AButton btnExtrusion;
    AButton btnImpresion;
    AButton btnSellado;
    AButton btnBuscar;
    AButton btnFinalizadas;
    ALabel msjMensaje;

    public Ordenes() {
        panel = new APanel(Main.x, 0, 750, 600);
        panel.setTitulo("Ordenes de producci\u00F3n");

        btnExtrusion = new AButton("En extrusi\u00F3n");
        btnExtrusion.setBounds(295, 230, 160, 60);
        btnExtrusion.addMouseListener(this);
        panel.add(btnExtrusion);
        
        btnImpresion = new AButton("En impresi\u00F3n");
        btnImpresion.setBounds(295, 310, 160, 60);
        btnImpresion.addMouseListener(this);
        panel.add(btnImpresion);
        
        btnSellado = new AButton("En sellado");
        btnSellado.setBounds(295, 390, 160, 60);
        btnSellado.addMouseListener(this);
        panel.add(btnSellado);
        
        msjMensaje = new ALabel("");
        msjMensaje.setHorizontalAlignment(SwingConstants.CENTER);
        msjMensaje.setBounds(0, 558, panel.getWidth(), 20);
        msjMensaje.setFont(new Font("Calibri", Font.PLAIN, 18));
        msjMensaje.setVisible(false);
        panel.add(msjMensaje);
        
        btnBuscar = new AButton("Buscar");
        btnBuscar.setBounds(295, 150, 160, 60);
        btnBuscar.addMouseListener(this);
        panel.add(btnBuscar);
        
        btnFinalizadas = new AButton("Ordenes finalizadas");
        btnFinalizadas.setBounds(295, 470, 160, 60);
        btnFinalizadas.addMouseListener(this);
        panel.add(btnFinalizadas);
        
        UsuarioE ue = Main.menu.getUsuario();
        if(ue.getTipo().equals("extrusor")){
            visible(true, false, false, false);
            btnExtrusion.setBounds(295, 270, 160, 60);
        }
        
        if(ue.getTipo().equals("impresor")){
            visible(false, true, false, false);
            btnImpresion.setBounds(295, 270, 160, 60);
        }
        
        if(ue.getTipo().equals("sellador")){
            visible(false, false, true, false);
            btnSellado.setBounds(295, 270, 160, 60);
        }
    }
    
    private void visible(boolean extrusion, boolean impresion, boolean sellado, boolean buscar){
	btnSellado.setVisible(sellado);
	btnExtrusion.setVisible(extrusion);
	btnImpresion.setVisible(impresion);
	btnBuscar.setVisible(buscar);
	btnFinalizadas.setVisible(buscar);
    }

    public void mouseClicked(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {
	if(e.getSource() == btnExtrusion){
	    Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());
	    Main.buscarOrdenes = new BuscarOrdenes();
	    Main.removerTodos();
	    Main.menu.frame.getContentPane().add(Main.buscarOrdenes.panel);
	    Main.esconderTodos();
	    String s = Main.buscarOrdenes.pendientes_extrsuion();
	    if(s.equals("")){
		Main.buscarOrdenes.panel.setVisible(true);
	    }else{
		Main.removerTodos();
		Main.menu.frame.getContentPane().add(Main.ordenes.panel);
		Main.esconderTodos();
		Main.ordenes.panel.setVisible(true);
		Main.ordenes.msjMensaje.setText("No hay procesos de extrusi\u00F3n pendientes");
		Main.ordenes.msjMensaje.setEstado(Estado.ERROR);
		Main.ordenes.msjMensaje.setVisible(true);
	    }
	    Main.dialog.ocultar();
	}
	
	if(e.getSource() == btnImpresion){
	    Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());
	    Main.buscarOrdenes = new BuscarOrdenes();
	    Main.removerTodos();
	    Main.menu.frame.getContentPane().add(Main.buscarOrdenes.panel);
	    Main.esconderTodos();
	    String s = Main.buscarOrdenes.pendientes_impresion();
	    if(s.equals("")){
		Main.buscarOrdenes.panel.setVisible(true);
	    }else{
		Main.removerTodos();
		Main.menu.frame.getContentPane().add(Main.ordenes.panel);
		Main.esconderTodos();
		Main.ordenes.panel.setVisible(true);
		Main.ordenes.msjMensaje.setText("No hay procesos de impresi\u00F3n pendientes");
		Main.ordenes.msjMensaje.setEstado(Estado.ERROR);
		Main.ordenes.msjMensaje.setVisible(true);
	    }
	    Main.dialog.ocultar();
	}
	
	if(e.getSource() == btnSellado){
	    Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());
	    Main.buscarOrdenes = new BuscarOrdenes();
	    Main.removerTodos();
	    Main.menu.frame.getContentPane().add(Main.buscarOrdenes.panel);
	    Main.esconderTodos();
	    String s = Main.buscarOrdenes.pendientes_sellado();
	    if(s.equals("")){
		Main.buscarOrdenes.panel.setVisible(true);
	    }else{
		Main.removerTodos();
		Main.menu.frame.getContentPane().add(Main.ordenes.panel);
		Main.esconderTodos();
		Main.ordenes.panel.setVisible(true);
		Main.ordenes.msjMensaje.setText("No hay procesos de sellado pendientes");
		Main.ordenes.msjMensaje.setEstado(Estado.ERROR);
		Main.ordenes.msjMensaje.setVisible(true);
	    }
	    Main.dialog.ocultar();
	}
	
	if(e.getSource() == btnBuscar){
	    Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());
	    Main.buscarOrdenes = new BuscarOrdenes();
	    Main.removerTodos();
	    Main.menu.frame.getContentPane().add(Main.buscarOrdenes.panel);
	    Main.esconderTodos();
	    Main.buscarOrdenes.panel.setVisible(true);
	    Main.dialog.ocultar();
	}
	
	if(e.getSource() == btnFinalizadas){
	    Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());
	    Main.buscarOrdenes = new BuscarOrdenes();
	    Main.removerTodos();
	    Main.menu.frame.getContentPane().add(Main.buscarOrdenes.panel);
	    Main.esconderTodos();
	    String s = Main.buscarOrdenes.ordenes_finalizadas();
	    if(s.equals("")){
		Main.buscarOrdenes.panel.setVisible(true);
	    }else{
		Main.removerTodos();
		Main.menu.frame.getContentPane().add(Main.ordenes.panel);
		Main.esconderTodos();
		Main.ordenes.panel.setVisible(true);
		Main.ordenes.msjMensaje.setText("No hay ordenes de producci\u00F3n finalizadas");
		Main.ordenes.msjMensaje.setEstado(Estado.ERROR);
		Main.ordenes.msjMensaje.setVisible(true);
	    }
	    Main.dialog.ocultar();
	}
    }

    public void mouseReleased(MouseEvent e) {}
}
