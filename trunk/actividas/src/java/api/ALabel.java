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

    private int estado = Estado.normal;

    public ALabel(String s) {
        setForeground(Colores.texto_normal);
        setFont(new Font("Calibri", Font.PLAIN, 14));
        setText(s);
    }

    public void setEstado(int i) {
        switch (i) {
            case 1:
                this.setForeground(Colores.texto_normal_error);
                estado = i;
                break;
            case 2:
                this.setForeground(Colores.texto_normal_exito);
                estado = i;
                break;
            default:
                this.setForeground(Colores.texto_normal);
                estado = i;
                break;
        }
    }

    public int getEstado() {
        return estado;
    }

    public void borde(boolean b) {
        if (b) {
            this.setBorder(new MatteBorder(1, 1, 1, 1, Colores.borde_cajaTexto));
            this.repaint();
        } else {
            this.setBorder(null);
            this.repaint();
        }

    }
}
