/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import java.awt.Font;

import javax.swing.JProgressBar;
import javax.swing.border.MatteBorder;

/**
 *
 * @author JPABLO
 */
@SuppressWarnings("serial")
public class AProgressBar extends JProgressBar {

    public AProgressBar() {
        setBorder(new MatteBorder(1, 1, 1, 1, Colores.BORDE_VENTANA));
        setForeground(Colores.BARRA_PROGRESO);
        setFont(new Font("Calibri", Font.PLAIN, 14));
        setString("");
        setMinimum(0);
        setMaximum(100);
        setStringPainted(true);
        setVisible(true);
    }
}
