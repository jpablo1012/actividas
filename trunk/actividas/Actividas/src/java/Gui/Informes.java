package Gui;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingConstants;

import api.*;

public class Informes implements MouseListener{

    APanel panel;
    ALabel lblInformes, msjMensaje;
    AButton btnSellados;

    public Informes() {
        panel = new APanel(Main.x, 0, 750, 600);

        lblInformes = new ALabel("Informes");
        lblInformes.setHorizontalAlignment(SwingConstants.CENTER);
        lblInformes.setFont(new Font("Calibri", Font.PLAIN, 40));
        lblInformes.setForeground(Colores.titulo_normal);
        lblInformes.setBounds(0, 74, panel.getWidth(), 50);
        panel.add(lblInformes);

        btnSellados = new AButton("Sellador");
        btnSellados.setBounds(295, 270, 160, 60);
        btnSellados.addMouseListener(this);
        panel.add(btnSellados);
        
        msjMensaje = new ALabel("");
        msjMensaje.setHorizontalAlignment(SwingConstants.CENTER);
        msjMensaje.setBounds(0, 538, 749, 20);
        msjMensaje.setFont(new Font("Calibri", Font.PLAIN, 18));
        panel.add(msjMensaje);
        
    }

    public void mouseClicked(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {
	if(e.getSource() == btnSellados){
	    Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());
	    Main.informesSellador = new InformesSellador();
	    Main.removerTodos();
	    Main.menu.frame.getContentPane().add(Main.informesSellador.panel);
	    Main.esconderTodos();
	    Main.informesSellador.panel.setVisible(true);
	    Main.dialog.ocultar();
	}
	
    }

    public void mouseReleased(MouseEvent e) {}
}
