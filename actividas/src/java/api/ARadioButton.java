package api;

import java.awt.Font;

import javax.swing.JRadioButton;

/**
 *
 * @author JPABLO
 */
@SuppressWarnings("serial")
public class ARadioButton extends JRadioButton {

    public ARadioButton(String s) {
        setText(s);
        setBackground(Colores.FONDO_NORMAL);
        setFont(new Font("Calibri", Font.PLAIN, 14));
        setForeground(Colores.TEXTO_NORMAL);
    }
}
