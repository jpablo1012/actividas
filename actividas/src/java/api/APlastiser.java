package api;

import java.awt.Font;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class APlastiser extends JLabel {

    int tipo = 0;

    public APlastiser(int w) {
        setFont(new Font("Brush Script MT", Font.PLAIN, 32));
        setText("Plastiser S.A.");
        setBounds(1, 1, w, 36);
        setOpaque(true);
        setBackground(Colores.FONDO_PLASTISER);
        setForeground(Colores.FONDO_NORMAL);
        setVisible(true);
    }

    /*public void setTipo(int tipo) {
        switch (tipo) {
            case 1:
                setForeground(Colores.FONDO_NORMAL);
                setBackground(Colores.TITULO_NORMAL);
                this.tipo = tipo;
                break;
            default:
                setBackground(Colores.FONDO_NORMAL);
                setForeground(Colores.TITULO_NORMAL);
                this.tipo = 0;
        }
    }*/
}
