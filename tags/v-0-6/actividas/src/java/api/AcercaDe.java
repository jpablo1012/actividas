package api;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.border.MatteBorder;
import javax.swing.SwingConstants;

import java.awt.Font;


public class AcercaDe implements MouseListener{

    
    APanel panel;
    APlastiser plastisoft;
    ALabel lblTexto;
    AButton btnCerrar;
    AFrame frame;
    
    private String texto = "<html><body><p>Plastisoft escritorio es un software desarrollado por Act&iacute;vidas para Plastiser S.A.</p>"
    			+ "<p>Act&iacute;vidas est\u00E1 compuesto por:</p>"
	    		+ "<div align='left'>"
	    		+ "<ul style='color: rgb(211, 54, 45)'>"
	    		+ "<li><span style='color:black'>Nelson david Aristizabal Amaya.</span></li>"
	    		+ "<li><span style='color:black'>Oscar Julian Buitrago Castro.</span></li>"
	    		+ "<li><span style='color:black'>Juan Diego Gonzalez Mena.</span></li>"
	    		+ "<li><span style='color:black'>Juan Pablo Lopera Estrada.</span></li>"
	    		+ "</ul>"
	    		+ "</div>"
    			+ "<p>JCalendar es una librer\u00EDa de java desarrollada por \u00A9Kai Toedter.</p>"
    			+ "<p>JFreeChart es una librer\u00EDa de java desarrolladada por \u00A9 2005-2013 Object Refinery Limited</p>"
    			+ "<center><p>Act&iacute;vidas</p><p>2014</p></center><body><html>";
    
    public AcercaDe() {
	frame = new AFrame(600, 400);
	//setLocationRelativeTo(null);
	frame.cerrar.setVisible(false);
	frame.minimizar.setVisible(false);
	frame.setDefaultCloseOperation(AFrame.DISPOSE_ON_CLOSE);
	
	panel = new APanel(0, 0, frame.getWidth(), frame.getHeight());
	panel.titulo.setVisible(false);
	panel.acercaDe.setVisible(false);
	panel.setBorder(new MatteBorder(2, 2, 2, 2, Colores.BORDE_CAJATEXTO_ERROR));
	frame.getContentPane().add(panel);
	
	plastisoft = new APlastiser(0);
	plastisoft.setFont(new Font("Brush Script MT", Font.PLAIN, 74));
	plastisoft.setHorizontalAlignment(SwingConstants.CENTER);
	plastisoft.setText("Plastisoft");
	plastisoft.setBounds(2, 2, frame.getWidth() - 4, 80);
	panel.add(plastisoft);
	
	lblTexto = new ALabel(texto);
	lblTexto.setFont(new Font("Calibri", Font.PLAIN, 15));
	//lblTexto.setHorizontalAlignment(SwingConstants.CENTER);
	lblTexto.setVerticalAlignment(SwingConstants.TOP);
	lblTexto.setBounds(45, 93, 506, 236);
	panel.add(lblTexto);
	
	btnCerrar = new AButton("Cerrar");
	btnCerrar.setBounds(235, 348, 130, 30);
	btnCerrar.addMouseListener(this);
	panel.add(btnCerrar);
	
	frame.setVisible(true);
	frame.toFront();
	//frame.setAlwaysOnTop(true);
	
    }

    public void mouseClicked(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {
	if(e.getSource() == btnCerrar){
	    frame.setVisible(false);
	}
    }

    public void mouseReleased(MouseEvent e) {}

}
