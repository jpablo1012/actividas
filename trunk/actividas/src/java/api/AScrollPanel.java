package api;

import java.awt.Font;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class AScrollPanel extends JScrollPane {

    private String text;

    public AScrollPanel(String s) {
        this.text = s;
        setBorder(new MatteBorder(1, 1, 1, 1, Colores.borde_cajaTexto));
        setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        setBorder(new TitledBorder(new MatteBorder(1, 1, 1, 1, Colores.borde_container), s, TitledBorder.LEFT, TitledBorder.TOP, new Font("Calibri", Font.PLAIN, 20), Colores.titulo_hover));
        setBackground(Colores.fondo_normal);
        JScrollBar jv = this.getVerticalScrollBar();
        jv.setBlockIncrement(75);
        jv.setUnitIncrement(75);
        setVerticalScrollBar(jv);
        
        JScrollBar jh = this.getHorizontalScrollBar();
        jh.setBlockIncrement(70);
        jh.setUnitIncrement(70);
        setHorizontalScrollBar(jh);
        //setHorizontalScrollBar(j);
        /*getVerticalScrollBar().setUI(new BasicScrollBarUI() {

            @Override
            protected JButton createDecreaseButton(int orientation) {
                JButton b = createButton();
                b.setText("<html><body>   \u02C4</body></html>");
                return b;
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                JButton b = createButton();
                b.setText("<html><body>   \u02C5</body></html>");
                return b;
            }

            protected void configureScrollBarColors() {
                thumbHighlightColor = Colores.b_borde;
                thumbLightShadowColor = Colores.b_borde;
                thumbDarkShadowColor = Colores.b_sombra;
                thumbColor = Colores.b_fondo;
                trackColor = Colores.b_fondo_desplazamiento;
                trackHighlightColor = Colores.b_fondo_desplazamiento_clic;
            }

            private JButton createButton() {
                final JButton jbutton = new JButton("");
                jbutton.setPreferredSize(new Dimension(12, 20));
                jbutton.setMinimumSize(new Dimension(0, 0));
                jbutton.setMaximumSize(new Dimension(12, 20));
                jbutton.setBorder(new MatteBorder(0, 0, 0, 0, Colores.borde_boton));
                jbutton.setForeground(Colores.texto_normal);
                jbutton.setBackground(Colores.b_fondo_desplazamiento);
                jbutton.setFont(new Font("Calibri", Font.BOLD, 16));
                jbutton.setHorizontalAlignment(SwingConstants.CENTER);
                jbutton.setHorizontalTextPosition(SwingConstants.CENTER);
                jbutton.setVerticalAlignment(SwingConstants.CENTER);
                jbutton.setHorizontalTextPosition(SwingConstants.CENTER);
                jbutton.setFocusable(false);
                jbutton.addMouseListener(new MouseListener() {

                    @Override
                    public void mousePressed(MouseEvent arg0) {
                        jbutton.setBackground(Colores.b_fondo_desplazamiento_clic);
                    }

                    @Override
                    public void mouseExited(MouseEvent arg0) {
                        jbutton.setBackground(Colores.b_fondo_desplazamiento);
                    }

                    @Override
                    public void mouseEntered(MouseEvent arg0) {
                        jbutton.setBackground(Colores.b_fondo_desplazamiento_clic);
                    }

                    @Override
                    public void mouseClicked(MouseEvent arg0) {
                        jbutton.setBackground(Colores.b_fondo_desplazamiento_clic);
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        jbutton.setBackground(Colores.b_fondo_desplazamiento);
                    }
                });
                jbutton.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent arg0) {
                        jbutton.setBackground(Colores.fondo_boton_clic);

                    }
                });
                return jbutton;
            }
        });*/
        
        
    }

    public void setText(String s){
        this.text = s;
        setBorder(new TitledBorder(new MatteBorder(1, 1, 1, 1, Colores.borde_container), s, TitledBorder.LEFT, TitledBorder.TOP, new Font("Calibri", Font.PLAIN, 20), Colores.titulo_hover));
        this.repaint();
    }

    public String getText(){
        return this.text;
    }
}

