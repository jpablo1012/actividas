package api;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

/**
 *
 * @author Actividas
 */
public class AButton extends JLabel implements MouseListener, FocusListener {

    int tipo = 0;

    public AButton(String s) {

        setBorder(new MatteBorder(1, 1, 1, 1, Colores.borde_boton));
        setForeground(Colores.texto_boton);
        setBackground(Colores.fondo_boton);
        setFont(new Font("Calibri", Font.BOLD, 14));
        setOpaque(true);
        setText(s);
        setHorizontalAlignment(SwingConstants.CENTER);
        setHorizontalTextPosition(SwingConstants.CENTER);
        setFocusable(true);
        addMouseListener(this);
        addFocusListener(this);

    }

    public void setTipo(int tipo) {
        switch (tipo) {
            case 1:
                setBorder(null);
                setForeground(Colores.texto_boton);
                setBackground(Colores.fondo_boton);
                setFont(new Font("Calibri", Font.BOLD, 18));
                setOpaque(true);
                setHorizontalAlignment(SwingConstants.LEFT);
                setHorizontalTextPosition(SwingConstants.CENTER);
                setCursor(new Cursor(Cursor.HAND_CURSOR));
                this.tipo = 1;
                break;
                
            case 2:
        	setBorder(null);
        	setBackground(Colores.fondo_normal);
                setForeground(Colores.titulo_normal);
                setFont(new Font("Calibri", Font.BOLD, 18));
                setOpaque(true);
                setHorizontalAlignment(SwingConstants.LEFT);
                setHorizontalTextPosition(SwingConstants.CENTER);
                setCursor(new Cursor(Cursor.HAND_CURSOR));
                this.tipo = 2;
        	break;
            default:
                setBorder(new MatteBorder(1, 1, 1, 1, Colores.borde_boton));
                setForeground(Colores.texto_boton);
                setBackground(Colores.fondo_boton);
                setFont(new Font("Calibri", Font.BOLD, 14));
                setOpaque(true);
                setHorizontalAlignment(SwingConstants.CENTER);
                setHorizontalTextPosition(SwingConstants.CENTER);
                this.tipo = 0;
        }
    }

    public void focusGained(FocusEvent e) {
    	switch (tipo) {
            case 1:
                setBackground(Colores.fondo_normal);
                setForeground(Colores.titulo_normal);
                //setBorder(new MatteBorder(0, 3, 0, 0, Colores.titulo_normal));
                break;
                
            case 2:
        	setBackground(Colores.fondo_normal);
                setForeground(Colores.titulo_normal);
        	break;
            default:
                setBorder(new MatteBorder(1, 1, 1, 1, Colores.borde_boton_hover));
                setBackground(Colores.fondo_boton_hover);
                break;
        }
    }

    public void focusLost(FocusEvent e) {
        switch (tipo) {
            case 1:
                setForeground(Colores.texto_boton);
                setBackground(Colores.fondo_boton);
                setBorder(null);
                break;
                
            case 2:
        	setBackground(Colores.fondo_normal);
                setForeground(Colores.titulo_normal);
        	break;
            default:
                setBorder(new MatteBorder(1, 1, 1, 1, Colores.borde_boton));
                setBackground(Colores.fondo_boton);
                break;
        }
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
        switch (tipo) {
            case 1:
                setBackground(Colores.fondo_normal);
                setForeground(Colores.titulo_normal);
                // setBorder(new MatteBorder(0, 3, 0, 0, Colores.titulo_normal));
                break;
                
            case 2:
        	setBackground(Colores.fondo_normal);
                setForeground(Colores.titulo_normal);
        	break;
            default:
                setBorder(new MatteBorder(1, 1, 1, 1, Colores.borde_boton_hover));
                setBackground(Colores.fondo_boton_hover);
                break;
        }
    }

    public void mouseExited(MouseEvent e) {
        switch (tipo) {
            case 1:
                setForeground(Colores.texto_boton);
                setBackground(Colores.fondo_boton);
                setBorder(null);
                break;
                
            case 2:
        	setBackground(Colores.fondo_normal);
                setForeground(Colores.titulo_normal);
        	break;
            default:
                setBorder(new MatteBorder(1, 1, 1, 1, Colores.borde_boton));
                setBackground(Colores.fondo_boton);
                break;
        }
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }
}
