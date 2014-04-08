package api;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class AMinimizar extends JLabel implements MouseListener {

    public AMinimizar() {
        setHorizontalAlignment(SwingConstants.CENTER);
        setFont(new Font("Calibri", Font.BOLD, 18));
        setText("_");
        setOpaque(true);
        setBackground(Colores.BOTON_MINIMIZAR);
        setForeground(Colores.TITULO_NORMAL);
        setFocusable(false);
        addMouseListener(this);
    }

    public void setAnchoVentana(int x) {
        setBounds(x - 73, 1, 36, 36);
        this.repaint();
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
        setBackground(Colores.FONDO_HOVER);
        setForeground(Colores.TITULO_HOVER);
    }

    public void mouseExited(MouseEvent e) {
        setBackground(Colores.BOTON_MINIMIZAR);
        setForeground(Colores.TITULO_NORMAL);
    }
}
