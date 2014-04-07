package api;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class APlastiser extends JLabel implements MouseListener {

    int tipo = 0;

    public APlastiser() {
        setFont(new Font("Brush Script MT", Font.PLAIN, 30));
        setText("Plastiser S.A.");
        setBounds(10, 1, 156, 40);
        setOpaque(true);
        setBackground(Colores.fondo_normal);
        setForeground(Colores.titulo_normal);
        //setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addMouseListener(this);
        setVisible(true);
    }

    public void setTipo(int tipo) {
        switch (tipo) {
            case 1:
                setForeground(Colores.fondo_normal);
                setBackground(Colores.titulo_normal);
                this.tipo = tipo;
                break;
            default:
                setBackground(Colores.fondo_normal);
                setForeground(Colores.titulo_normal);
                this.tipo = 0;
        }
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
        switch (tipo) {
            case 1:
                setForeground(Colores.fondo_hover);
                break;
            default:
                setForeground(Colores.titulo_hover);
                break;
        }
    }

    public void mouseExited(MouseEvent e) {
        switch (tipo) {
            case 1:
                setForeground(Colores.fondo_normal);
                break;
            default:
                setForeground(Colores.titulo_normal);
                break;
        }
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
//        if (java.awt.Desktop.isDesktopSupported()) {
//            try {
//                Desktop dk = Desktop.getDesktop();
//                dk.browse(new URI("http://plastiser.com.co"));
//            } catch (Exception ex) {
//            }
//        }
    }
}
