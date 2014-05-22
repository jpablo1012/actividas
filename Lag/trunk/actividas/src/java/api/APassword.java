package api;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPasswordField;
import javax.swing.border.MatteBorder;

@SuppressWarnings("serial")
public class APassword extends JPasswordField implements MouseListener {

    private int estado = Estado.NORMAL;
    private Color place = Colores.TEXTO_DESACTIVADO;
    private String placeHolder = "";

    public APassword() {
        setBorder(new MatteBorder(1, 1, 1, 1, Colores.BORDE_CAJATEXTO));
        setFont(new Font("Calibri", Font.PLAIN, 14));
        setForeground(Colores.TEXTO_NORMAL);
        setBackground(Color.WHITE);
        addMouseListener(this);
    }

    @SuppressWarnings("deprecation")
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
                setBorder(new MatteBorder(1, 1, 1, 1, Colores.BORDE_CAJATEXTO_ERROR));
                estado = i;
                break;
            case 2:
                setBorder(new MatteBorder(1, 1, 1, 1, Colores.BORDE_CAJATEXTO_EXITO));
                estado = i;
                break;
            default:
                setBorder(new MatteBorder(1, 1, 1, 1, Colores.BORDE_CAJATEXTO));
                estado = i;
                break;
        }
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
        place = Colores.TEXTO_DESACTIVADO_HOVER;
        switch (estado) {
            case 1:
                setBorder(new MatteBorder(1, 1, 1, 1, Colores.BORDE_CAJATEXTO_ERROR_HOVER));
                break;
            case 2:
                setBorder(new MatteBorder(1, 1, 1, 1, Colores.BORDE_CAJATEXTO_EXITO_HOVER));
                break;
            default:
                setBorder(new MatteBorder(1, 1, 1, 1, Colores.BORDE_CAJA_TEXTO_HOVER));
                break;
        }
    }

    public void mouseExited(MouseEvent e) {
        place = Colores.TEXTO_DESACTIVADO;
        switch (estado) {
            case 1:
                setBorder(new MatteBorder(1, 1, 1, 1, Colores.BORDE_CAJATEXTO_ERROR));
                break;
            case 2:
                setBorder(new MatteBorder(1, 1, 1, 1, Colores.BORDE_CAJATEXTO_EXITO));
                break;
            default:
                setBorder(new MatteBorder(1, 1, 1, 1, Colores.BORDE_CAJATEXTO));
                break;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }
}
