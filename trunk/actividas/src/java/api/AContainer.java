package api;

import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class AContainer extends JPanel {

    private String text;

    public AContainer(String s) {
        setLayout(null);
        setBorder(new TitledBorder(new MatteBorder(1, 1, 1, 1, Colores.borde_container), s, TitledBorder.LEFT, TitledBorder.TOP, new Font("Calibri", Font.PLAIN, 20), Colores.titulo_hover));
        setBackground(Colores.fondo_normal);
        setOpaque(true);
        setLayout(null);
        setVisible(true);
        text = s;
    }

    public void setText(String s){
        this.text = s;
        setBorder(new TitledBorder(new MatteBorder(1, 1, 1, 1, Colores.borde_container), s, TitledBorder.LEFT, TitledBorder.TOP, new Font("Calibri", Font.PLAIN, 20), Colores.titulo_hover));
        this.repaint();
    }

    public String getText(){
        return this.text;
    }
}
