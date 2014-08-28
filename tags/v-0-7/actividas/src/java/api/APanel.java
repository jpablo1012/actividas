package api;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.UIManager;

@SuppressWarnings("serial")
public class APanel extends JPanel implements MouseListener {

    public JLabel acercaDe;
    public JLabel titulo;

    public APanel(int x, int y, int w, int h) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        setBackground(Colores.FONDO_NORMAL);
        setOpaque(true);
        setLayout(null);
        setLocation(x, y);
        setSize(w, h);
        setBorder(new MatteBorder(1, 0, 1, 1, Colores.BORDE_VENTANA));

        acercaDe = new JLabel();
        acercaDe.setBounds(getWidth() - 75, getHeight() - 21, 71, 20);
        acercaDe.setText("Acerca de");
        acercaDe.setFont(new Font("Calibri", Font.BOLD, 16));
        acercaDe.setForeground(Colores.TEXTO_DESACTIVADO);
        acercaDe.setVerticalAlignment(SwingConstants.BOTTOM);
        acercaDe.setHorizontalAlignment(SwingConstants.RIGHT);
        acercaDe.setFocusable(false);
        acercaDe.addMouseListener(this);
        acercaDe.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(acercaDe);
        
        titulo = new JLabel();
        titulo.setBounds(0, 1, w - 2, 36);
        titulo.setFont(new Font("Calibri", Font.BOLD, 28));
        titulo.setBorder(new MatteBorder(5, 10, 0, 0, Colores.FONDO_TITULO_PANEL));
        titulo.setForeground(Colores.TITULO_NORMAL);
        titulo.setOpaque(true);
        titulo.setBackground(Colores.FONDO_TITULO_PANEL);
        add(titulo);

    }
    
    public void setTitulo(String titulo){
	this.titulo.setText(titulo);
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == acercaDe) {
            acercaDe.setForeground(Colores.TEXTO_DESACTIVADO_HOVER);
        }
    }

    public void mouseExited(MouseEvent e) {
        if (e.getSource() == acercaDe) {
            acercaDe.setForeground(Colores.TEXTO_DESACTIVADO);
        }
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
        if (e.getSource() == acercaDe) {
            AcercaDe a = new AcercaDe();
            a.frame.setLocationRelativeTo(this);
        }
    }
}
