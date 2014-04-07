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
        setFont(new Font("Calibri", Font.PLAIN, 14));
        setForeground(Colores.texto_normal);
    }
}
