package api;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

/**
 *
 * @author JPABLO
 */
public class ATextField extends JTextField implements MouseListener {

    private int estado = Estado.normal;
    private Color place = Colores.texto_desactivado;
    private String placeHolder = "";

    public ATextField() {
        setBorder(new MatteBorder(1, 1, 1, 1, Colores.borde_cajaTexto));
        setFont(new Font("Calibri", Font.PLAIN, 14));
        setForeground(Colores.texto_normal);
        setBackground(Color.white);
        setOpaque(true);
        addMouseListener(this);
    }

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);

        if (getText().equals("")) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setBackground(Color.WHITE);
            g2.setColor(place);
            g2.setFont(new Font("Calibri", Font.PLAIN, 14));
            g2.drawString(placeHolder, 2, 16); //figure out x, y from font's FontMetrics and size of component.
            g2.dispose();
        }
    }

    public void setPlaceHolder(String s) {
        this.placeHolder = s;
    }

    public String getPlaceholder() {
        return placeHolder;
    }

    public void setEstado(int i) {
        switch (i) {
            case 1:
                setBorder(new MatteBorder(1, 1, 1, 1, Colores.borde_cajaTexto_error));
                estado = i;
                break;
            case 2:
                setBorder(new MatteBorder(1, 1, 1, 1, Colores.borde_cajaTexto_exito));
                estado = i;
                break;
            default:
                setBorder(new MatteBorder(1, 1, 1, 1, Colores.borde_cajaTexto));
                estado = i;
                break;
        }
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
        place = Colores.texto_desactivado_hover;
        switch (estado) {
            case 1:
                setBorder(new MatteBorder(1, 1, 1, 1, Colores.borde_cajaTexto_error_hover));
                break;
            case 2:
                setBorder(new MatteBorder(1, 1, 1, 1, Colores.borde_cajaTexto_exito_hover));
                break;
            default:
                setBorder(new MatteBorder(1, 1, 1, 1, Colores.borde_cajaTexto_hover));
                break;
        }
    }

    public void mouseExited(MouseEvent e) {
        place = Colores.texto_desactivado;
        switch (estado) {
            case 1:
                setBorder(new MatteBorder(1, 1, 1, 1, Colores.borde_cajaTexto_error));
                break;
            case 2:
                setBorder(new MatteBorder(1, 1, 1, 1, Colores.borde_cajaTexto_exito));
                break;
            default:
                setBorder(new MatteBorder(1, 1, 1, 1, Colores.borde_cajaTexto));
                break;
        }
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }
}
