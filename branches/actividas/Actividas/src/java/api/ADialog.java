package api;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.JDialog;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

public class ADialog extends JDialog{


    public boolean enque = true;
    private ALabel l;
    public ADialog(String s){

        setUndecorated(true);
        setBackground(new Color(0,0,0,0.2f));
        setLocationRelativeTo(null);
        setVisible(true);
        setLayout(null);
        setModal(false);

        l = new ALabel(s);
        l.setOpaque(true);
        l.setBackground(Colores.fondo_rojo);
        l.setHorizontalAlignment(SwingConstants.CENTER);
        
        l.setForeground(Colores.texto_boton);
        l.setFont(new Font("Calibri", Font.BOLD, 24));

        l.setSize(200, 100);
        l.setLocation((this.getWidth() / 2)-(l.getWidth() / 2),
                    (this.getHeight() / 2)-(l.getHeight() / 2));
        l.setBorder(new MatteBorder(1, 1, 1, 1, Colores.borde_ventana));
        this.add(l);

        ocultar();
        toBack();
    }

    public void enInicio(boolean b){
        this.enque = b;
    }

    public void setPosicionLabel(Point p, Dimension d){
        //this.setLocation(p);
        this.setSize(d);
        l.setLocation((this.getWidth() / 2)-(l.getWidth() / 2),
                    (this.getHeight() / 2)-(l.getHeight() / 2));
        
        //toBack();
    }

    public void mostrar(Point p, Dimension d){
        this.setLocation(p);
        this.setSize(d);

        l.setLocation((this.getWidth() / 2)-(l.getWidth() / 2),
                    (this.getHeight() / 2)-(l.getHeight() / 2));

        toFront();
    }

    public void ocultar(){
        Dimension s = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((int) (s.getWidth()) + 500, 0);
        toBack();
    }

    public void arrancar(Point p, Dimension d){
        mostrar(p, d);
        ocultar();
    }
}
