/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

/**
 *
 * @author Usuario
 */
@SuppressWarnings("serial")
public class AMenu extends JPanel {

    public String[] administrador = {"Inicio","Insumos", "Pedidos", "<html><body>Ordenes de producci&oacute;n</body></html>", "Clientes", "Empleados", "Informes","Perfil", "<html><body>Cerrar sesi&oacute;n</body></html>"};
    public String[] empleado = {"Inicio","<html><body>Ordenes de producci&oacute;n</body></html>", "Perfil", "<html><body>Cerrar sesi&oacute;n</body></html>"};
    public String[] cliente = {"Inicio","Bolsa","Pedidos", "Perfil", "<html><body>Cerrar sesi&oacute;n</body></html>"};
    int tipo;
    public int ultimo, penultimo;
    public AButton[] botones;
    APlastiser plastiser;
    public JCheckBox jcb;

    public AMenu() {
        setLayout(null);
        setBounds(0, 0, 170, 600);
        setBackground(Colores.FONDO_ROJO);
        setBorder(new MatteBorder(1, 1, 1, 0, Colores.BORDE_VENTANA));

        plastiser = new APlastiser(this.getWidth());
        //plastiser.setTipo(1);
        add(plastiser);

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
            botones[i].setFocusable(false);
            this.add(botones[i]);
            y += 50;
        }
    }
}
