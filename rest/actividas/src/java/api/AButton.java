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
@SuppressWarnings("serial")
public class AButton extends JLabel implements MouseListener, FocusListener {

    int tipo = 0;

    public AButton(String s) {

        setBorder(new MatteBorder(1, 1, 1, 1, Colores.BORDE_BOTON));
        setForeground(Colores.TEXTO_BOTON);
        setBackground(Colores.BOTON_FONDO);
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
                setForeground(Colores.TEXTO_BOTON);
                setBackground(Colores.BOTON_FONDO);
                setFont(new Font("Calibri", Font.BOLD, 18));
                setOpaque(true);
                setHorizontalAlignment(SwingConstants.LEFT);
                setHorizontalTextPosition(SwingConstants.CENTER);
                setCursor(new Cursor(Cursor.HAND_CURSOR));
                this.tipo = 1;
                break;
                
            case 2:
        	setBorder(null);
        	setBackground(Colores.FONDO_NORMAL);
                setForeground(Colores.TITULO_NORMAL);
                setFont(new Font("Calibri", Font.BOLD, 18));
                setOpaque(true);
                setHorizontalAlignment(SwingConstants.LEFT);
                setHorizontalTextPosition(SwingConstants.CENTER);
                setCursor(new Cursor(Cursor.HAND_CURSOR));
                this.tipo = 2;
        	break;
            default:
                setBorder(new MatteBorder(1, 1, 1, 1, Colores.BORDE_BOTON));
                setForeground(Colores.TEXTO_BOTON);
                setBackground(Colores.BOTON_FONDO);
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
                setBackground(Colores.FONDO_NORMAL);
                setForeground(Colores.TITULO_NORMAL);
                //setBorder(new MatteBorder(0, 3, 0, 0, Colores.titulo_normal));
                break;
                
            case 2:
        	setBackground(Colores.FONDO_NORMAL);
                setForeground(Colores.TITULO_NORMAL);
        	break;
            default:
                setBorder(new MatteBorder(1, 1, 1, 1, Colores.BORDE_BOTON_HOVER));
                setBackground(Colores.BOTON_FONDO_HOVER);
                break;
        }
    }

    public void focusLost(FocusEvent e) {
        switch (tipo) {
            case 1:
                setForeground(Colores.TEXTO_BOTON);
                setBackground(Colores.BOTON_FONDO);
                setBorder(null);
                break;
                
            case 2:
        	setBackground(Colores.FONDO_NORMAL);
                setForeground(Colores.TITULO_NORMAL);
        	break;
            default:
                setBorder(new MatteBorder(1, 1, 1, 1, Colores.BORDE_BOTON));
                setBackground(Colores.BOTON_FONDO);
                break;
        }
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
        switch (tipo) {
            case 1:
                setBackground(Colores.FONDO_NORMAL);
                setForeground(Colores.TITULO_NORMAL);
                // setBorder(new MatteBorder(0, 3, 0, 0, Colores.titulo_normal));
                break;
                
            case 2:
        	setBackground(Colores.FONDO_NORMAL);
                setForeground(Colores.TITULO_NORMAL);
        	break;
            default:
                setBorder(new MatteBorder(1, 1, 1, 1, Colores.BORDE_BOTON_HOVER));
                setBackground(Colores.BOTON_FONDO_HOVER);
                break;
        }
    }

    public void mouseExited(MouseEvent e) {
        switch (tipo) {
            case 1:
                setForeground(Colores.TEXTO_BOTON);
                setBackground(Colores.BOTON_FONDO);
                setBorder(null);
                break;
                
            case 2:
        	setBackground(Colores.FONDO_NORMAL);
                setForeground(Colores.TITULO_NORMAL);
        	break;
            default:
                setBorder(new MatteBorder(1, 1, 1, 1, Colores.BORDE_BOTON));
                setBackground(Colores.BOTON_FONDO);
                break;
        }
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }
}
