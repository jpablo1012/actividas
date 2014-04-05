/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

/**
 *
 * @author Usuario
 */
public class AMenu extends JPanel implements MouseListener {

    public String[] administrador = {"Inicio","Insumos", "Pedidos", "<html><body>Ordenes de producci&oacute;n</body></html>", "Clientes", "Empleados", "Informes","Perfil", "<html><body>Cerrar sesi&oacute;n</body></html>"};
    public String[] empleado = {"Inicio","<html><body>Ordenes de producci&oacute;n</body></html>", "Perfil", "<html><body>Cerrar sesi&oacute;n</body></html>"};
    public String[] cliente = {"Inicio","Bolsa","Pedidos", "Perfil", "<html><body>Cerrar sesi&oacute;n</body></html>"};
    int tipo;
    public int ultimo, penultimo;
    public AButton[] botones;
    APlastiser plastiser;
    private boolean hover = false;
    public JCheckBox jcb;

    public AMenu() {
        setLayout(null);
        setBounds(0, 0, 50, 600);
        setBackground(Colores.fondo_rojo);
        setBorder(new MatteBorder(1, 1, 1, 0, Colores.borde_ventana));

        plastiser = new APlastiser();
        plastiser.setTipo(1);
        add(plastiser);
        plastiser.addMouseListener(this);
        addMouseListener(this);

        setHover(false);
    }

    public void setHover(boolean b){
        hover = b;
        if(hover){
            setSize(50, 600);
        }else{
            setSize(170, 600);
        }
    }

    public void setTipo(int tipo) {
        switch (tipo) {
            case 1:
                this.tipo = 1;
                crearBotones(administrador);
                break;
            case 2:
                this.tipo = tipo;
                crearBotones(cliente); 
                break;
            default:
                this.tipo = tipo;
                crearBotones(empleado);
                break;
        }
    }

    private void crearBotones(String[] s) {
        botones = new AButton[s.length];
        int x = 10;
        int y = 70;
        ultimo = s.length - 1;
        penultimo = s.length -2;
        for (int i = 0; i < s.length; i++) {
            botones[i] = new AButton("");
            botones[i].setText(s[i]);
            botones[i].setBounds(x, y, 170, 30);
            botones[i].setTipo(1);
            if(i == 0){
        	botones[i].setTipo(2);
            }
            
            if(tipo == 1){
                if (i == 3) {
                    botones[i].setBounds(x, y, 170, 50);
                    y += 20;
                }
            }

            if(tipo == 0){
                if (i == 1) {
                    botones[i].setBounds(x, y, 170, 50);
                    y += 20;
                }
            }
            botones[i].addMouseListener(this);
            botones[i].setFocusable(false);
            this.add(botones[i]);
            y += 50;
        }
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
       /* if(hover){
            this.setSize(170, 600);
        }*/
        
    }

    public void mouseExited(MouseEvent e) {
        /*if(hover){
          this.setSize(50, 600);
        }*/
        
    }
}
