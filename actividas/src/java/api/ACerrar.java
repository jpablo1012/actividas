/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class ACerrar extends JLabel implements MouseListener {

    public ACerrar() {
        setHorizontalAlignment(SwingConstants.CENTER);
        setFont(new Font("Calibri", Font.BOLD, 18));
        setText("X");
        setOpaque(true);
        setBackground(Colores.fondo_normal);
        setForeground(Colores.titulo_normal);
        setFocusable(false);
        addMouseListener(this);
    }

    public void setAnchoVentana(int x) {
        setBounds(x - 33, 1, 32, 32);
        this.repaint();
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
	//File f = new File("C://Users//"+System.getProperty("user.name")+"//AppData//plastisoft");
	//f.delete();
        System.exit(0);
    }

    public void mouseEntered(MouseEvent e) {
        setBackground(Colores.fondo_hover);
        setForeground(Colores.titulo_hover);
    }

    public void mouseExited(MouseEvent e) {
        setBackground(Colores.fondo_normal);
        setForeground(Colores.titulo_normal);
    }
}
