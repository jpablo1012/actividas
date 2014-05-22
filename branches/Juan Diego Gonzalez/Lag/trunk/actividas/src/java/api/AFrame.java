package api;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.UIManager;

@SuppressWarnings("serial")
public class AFrame extends JFrame implements MouseListener, MouseMotionListener {

    private int x,  y;
    public ACerrar cerrar;
    public AMinimizar minimizar;
    ImageIcon icono;
    public AFrame(int w, int h) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        setUndecorated(true);
        setLayout(null);
        setSize(w, h);
        setResizable(false);
        setTitle("Plastiser");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Colores.FONDO_NORMAL);
        addMouseListener(this);
        addMouseMotionListener(this);

        icono = new ImageIcon(getClass().getResource("icono.png"));
        this.setIconImage(icono.getImage());

        cerrar = new ACerrar();
        cerrar.setAnchoVentana(w);
        this.add(cerrar);

        minimizar = new AMinimizar();
        minimizar.setAnchoVentana(w);
        minimizar.addMouseListener(this);
        this.add(minimizar);

        toFront();
    }

    public void mouseDragged(MouseEvent e) {
	if(y <= 36){
	    Point point = MouseInfo.getPointerInfo().getLocation();
	    setLocation(point.x - x, point.y - y);
	}
        
    }

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    public void mouseReleased(MouseEvent e) {
        if (e.getSource() == minimizar) {
            setExtendedState(ICONIFIED);
        }
    }
}
