package api;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

@SuppressWarnings("serial")
public class AToggleButton extends JLabel implements MouseListener, FocusListener {

    boolean seleccionado = true;
    private String pos, neg;

    public AToggleButton(String pos, String neg) {
        this.pos = pos;
        this.neg = neg;
        this.setText(this.pos);
	
        setBorder(new MatteBorder(1, 1, 1, 1, Colores.BORDE_BOTON));
        setForeground(Colores.TEXTO_BOTON);
        setBackground(Colores.BOTON_FONDO);
        setFont(new Font("Calibri", Font.BOLD, 14));
        setOpaque(true);
        setHorizontalAlignment(SwingConstants.LEFT);
        setHorizontalTextPosition(SwingConstants.CENTER);
        setFocusable(true);
        addMouseListener(this);
        selected();
    }

    public boolean getSeleccionado(){
        return seleccionado;
    }

    public void setSeleccionado(boolean b){
        seleccionado = b;
        selected();
    }

    public void setText1(String s){
        this.pos = s;
    }

    public void setText2(String s){
        this.neg = s;
    }

    private void selected(){
    	if(this.isEnabled()){
	        if(seleccionado){
	            setBackground(Colores.BOTON_FONDO);
	            setBorder(new MatteBorder(1, 1, 1, 1, Colores.BORDE_BOTON));
	            setHorizontalAlignment(SwingConstants.LEFT);
	            setForeground(Colores.TEXTO_BOTON);
	            setText(this.pos);
	        }else{
	            setBackground(Colores.BOTON_FONDO_DESACTIVADO);
	            setBorder(new MatteBorder(1, 1, 1, 1, Colores.BORDE_BOTON_DESACTIVADO_HOVER));
	            setHorizontalAlignment(SwingConstants.RIGHT);
	            //setForeground(Colores.texto_normal);
	            setText(this.neg);
	        }
    	}
    }

     protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        if (seleccionado) {
            g.setColor(Colores.SWITCHE);
            g.fillRect(this.getWidth() - (int)(this.getWidth()*0.4), 0, (int)(this.getWidth()*0.4), this.getHeight());
            g.setColor(Colores.TEXTO_DESACTIVADO);
            g.drawRect(this.getWidth() - (int)(this.getWidth()*0.4), 0, (int)(this.getWidth()*0.4), this.getHeight());
        }else{
            g.setColor(Colores.SWITCHE);
            g.fillRect(0, 0, (int)(this.getWidth()*0.4), this.getHeight());
            g.setColor(Colores.FONDO_ROJO);
            g.drawRect(0, 0, (int)(this.getWidth()*0.4), this.getHeight());
        }
        if(!this.isEnabled() && seleccionado){
        	setBackground(Colores.TEXTO_DESACTIVADO_HOVER);
        	//setForeground(Colores.texto_boton);
        }
        g2.dispose();
    }

    public void mousePressed(MouseEvent e) {
        seleccionado = (!seleccionado);
            if(seleccionado){
                //this.setText(this.pos);
                selected();
            }else{
                //this.setText(this.neg);
                selected();
            }

    }

    public void mouseReleased(MouseEvent e) {}

    public void mouseClicked(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {
    	if(isEnabled()){
	        if(seleccionado){
	            setBackground(Colores.BOTON_FONDO_HOVER);
	            setBorder(new MatteBorder(1, 1, 1, 1, Colores.BORDE_BOTON_HOVER));
	        }else{
	            setBackground(Colores.TEXTO_DESACTIVADO_HOVER);
	            setBorder(new MatteBorder(1, 1, 1, 1, Colores.BORDE_BOTON_DESACTIVADO_HOVER));
	        }
    	}
    }

    public void mouseExited(MouseEvent e) {
    	if(isEnabled()){
	         if(seleccionado){
	            setBackground(Colores.BOTON_FONDO);
	            setBorder(new MatteBorder(1, 1, 1, 1, Colores.BORDE_BOTON));
	        }else{
	            setBackground(Colores.BOTON_FONDO_DESACTIVADO);
	            setBorder(new MatteBorder(1, 1, 1, 1, Colores.BORDE_BOTON_DESACTIVADO));
	        }
    	}
    }

    public void focusGained(FocusEvent e) {
    	if(isEnabled()){
	        if(seleccionado){
	            setBackground(Colores.BOTON_FONDO_HOVER);
	            setBorder(new MatteBorder(1, 1, 1, 1, Colores.BORDE_BOTON_HOVER));
	        }else{
	            setBackground(Colores.TEXTO_DESACTIVADO_HOVER);
	            setBorder(new MatteBorder(1, 1, 1, 1, Colores.BORDE_BOTON_DESACTIVADO_HOVER));
	        }
    	}
    }

    public void focusLost(FocusEvent e) {
    	if(isEnabled()){
	         if(seleccionado){
	            setBackground(Colores.BOTON_FONDO);
	            setBorder(new MatteBorder(1, 1, 1, 1, Colores.BORDE_BOTON));
	        }else{
	            setBackground(Colores.BOTON_FONDO_DESACTIVADO);
	            setBorder(new MatteBorder(1, 1, 1, 1, Colores.BORDE_BOTON_DESACTIVADO));
	        }
    	}
    }
}
