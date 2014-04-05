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

public class APanel extends JPanel implements MouseListener {

    // JLabel Cerrar;
    // public JLabel Minimizar;
    public JLabel Actividas;
    public APlastiser plastiser;

    public APanel(int x, int y, int w, int h) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        setBackground(Colores.fondo_normal);
        setOpaque(true);
        setLayout(null);
        setLocation(x, y);
        setSize(w, h);
        setBorder(new MatteBorder(1, 0, 1, 1, Colores.borde_ventana));

        Actividas = new JLabel();
        Actividas.setBounds(getWidth() - 75, getHeight() - 21, 71, 20);
        Actividas.setText("Acerca de");
        Actividas.setFont(new Font("Calibri", Font.BOLD, 16));
        Actividas.setForeground(Colores.texto_desactivado);
        Actividas.setVerticalAlignment(SwingConstants.BOTTOM);
        Actividas.setHorizontalAlignment(SwingConstants.RIGHT);
        Actividas.setFocusable(false);
        Actividas.addMouseListener(this);
        Actividas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(Actividas);

        plastiser = new APlastiser();
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == Actividas) {
            Actividas.setForeground(Colores.texto_desactivado_hover);
        }
    }

    public void mouseExited(MouseEvent e) {
        if (e.getSource() == Actividas) {
            Actividas.setForeground(Colores.texto_desactivado);
        }
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
        if (e.getSource() == Actividas) {
            AcercaDe a = new AcercaDe();
            a.frame.setLocationRelativeTo(this);
        }
    }
}
