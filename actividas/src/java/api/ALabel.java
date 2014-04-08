package api;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.border.MatteBorder;

/**
 *
 * @author JPABLO
 */
@SuppressWarnings("serial")
public class ALabel extends JLabel {

    private int estado = Estado.NORMAL;

    public ALabel(String s) {
        setForeground(Colores.TEXTO_NORMAL);
        setFont(new Font("Calibri", Font.PLAIN, 14));
        setText(s);
    }

    public void setEstado(int i) {
        switch (i) {
            case 1:
                this.setForeground(Colores.TEXTO_NORMAL_ERROR);
                estado = i;
                break;
            case 2:
                this.setForeground(Colores.TEXTO_NORMAL_EXITO);
                estado = i;
                break;
            default:
                this.setForeground(Colores.TEXTO_NORMAL);
                estado = i;
                break;
        }
    }

    public int getEstado() {
        return estado;
    }

    public void borde(boolean b) {
        if (b) {
            this.setBorder(new MatteBorder(1, 1, 1, 1, Colores.BORDE_CAJATEXTO));
            this.repaint();
        } else {
            this.setBorder(null);
            this.repaint();
        }

    }
}
