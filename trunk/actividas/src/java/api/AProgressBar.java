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
public class AProgressBar extends JProgressBar {

    public AProgressBar() {
        setBorder(new MatteBorder(1, 1, 1, 1, Colores.borde_ventana));
        setForeground(Colores.barra_progreso);
        setFont(new Font("Calibri", Font.PLAIN, 14));
        setString("");
        setMinimum(0);
        setMaximum(100);
        setStringPainted(true);
        setVisible(true);
    }
}
