package api;

import java.awt.Font;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class AScrollPanel extends JScrollPane {

    private String text;

    public AScrollPanel(String s) {
        this.text = s;
        setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        setBorder(new TitledBorder(new MatteBorder(1, 1, 1, 1, Colores.BORDE_CONTENEDOR), s, TitledBorder.LEFT, TitledBorder.TOP, new Font("Calibri", Font.PLAIN, 20), Colores.TITULO_HOVER));
        setBackground(Colores.FONDO_NORMAL);
        this.viewport.setBackground(Colores.FONDO_NORMAL);
        JScrollBar jv = this.getVerticalScrollBar();
        jv.setBlockIncrement(75);
        jv.setUnitIncrement(75);
        setVerticalScrollBar(jv);
        
        JScrollBar jh = this.getHorizontalScrollBar();
        jh.setBlockIncrement(70);
        jh.setUnitIncrement(70);
        setHorizontalScrollBar(jh);  
    }

    public void setText(String s){
        this.text = s;
        setBorder(new TitledBorder(new MatteBorder(1, 1, 1, 1, Colores.BORDE_CONTENEDOR), s, TitledBorder.LEFT, TitledBorder.TOP, new Font("Calibri", Font.PLAIN, 20), Colores.TITULO_HOVER));
        this.repaint();
    }

    public String getText(){
        return this.text;
    }
}

