package api;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

@SuppressWarnings("serial")
public class ADialog extends JDialog{


    public boolean enque = true;
    private ALabel l;
    public ADialog(String s){

        setUndecorated(true);
        setBackground(new Color(0,0,0,0.3f));
        setLocationRelativeTo(null);
        setVisible(true);
        setLayout(null);
        setModal(false);

        l = new ALabel(s);
        l.setOpaque(true);
        l.setBackground(Colores.FONDO_ROJO);
        l.setHorizontalAlignment(SwingConstants.CENTER);
        
        l.setForeground(Colores.TEXTO_BOTON);
        l.setFont(new Font("Calibri", Font.BOLD, 24));

        //l.setSize(this.getWidth(), 100);
        l.setLocation(0, (this.getHeight() / 2)-(l.getHeight() / 2));
        l.setBorder(new MatteBorder(1, 1, 1, 1, Colores.BORDE_VENTANA));
        this.add(l);

        ocultar();
        toBack();
    }

    public void enInicio(boolean b){
        this.enque = b;
    }

    public void setPosicionLabel(Point p, Dimension d){
        this.setSize(d);
        l.setSize(this.getWidth(), 100);
        l.setLocation(0, (this.getHeight() / 2)-(l.getHeight() / 2));
        
    }

    public void mostrar(Point p, Dimension d){
        this.setLocation(p);
        this.setSize(d);
        l.setSize(this.getWidth(), 100);
        l.setLocation(0, (this.getHeight() / 2)-(l.getHeight() / 2));

        toFront();
    }

    public void ocultar(){
        Dimension s = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((int) (s.getWidth()) + 1000, 0);
        toBack();
    }

    public void arrancar(Point p, Dimension d){
        mostrar(p, d);
        ocultar();
    }
}
