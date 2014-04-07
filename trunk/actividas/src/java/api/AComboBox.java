/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;

/**
 *
 * @author JPABLO
 */
@SuppressWarnings({ "serial", "rawtypes" })
public class AComboBox extends JComboBox implements MouseListener {

    private int estado = Estado.normal;
    private String[] valor;
    @SuppressWarnings("unchecked")
    public AComboBox(String[] s) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        setBorder(new MatteBorder(1, 1, 1, 1, Colores.borde_cajaTexto));
        setBackground(Colores.fondo_normal);
        setFont(new Font("Calibri", Font.PLAIN, 14));
        setModel(new DefaultComboBoxModel(s));
        setOpaque(true);
        setVisible(true);
    }
    
    public AComboBox(){
    	try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        setBorder(new MatteBorder(1, 1, 1, 1, Colores.borde_cajaTexto));
        setBackground(Colores.fondo_normal);
        setFont(new Font("Calibri", Font.PLAIN, 14));
        setOpaque(true);
        setVisible(true);
    }
    
    @SuppressWarnings("unchecked")
    public void setTextos(String[] s){
	setModel(new DefaultComboBoxModel(s));
    }
    
    public void setValores(String[] s){
	this.valor = s;
    }
    
    public String getValor(int i){
	return this.valor[i];
    }
    
    public String[] getValores(){
	return this.valor;
    }
   
    public void setEstado(int i) {
        switch (i) {
            case 1:
                this.setBorder(new MatteBorder(1, 1, 1, 1, Colores.borde_cajaTexto_error));
                estado = i;
                break;
            case 2:
                this.setBorder(new MatteBorder(1, 1, 1, 1, Colores.borde_cajaTexto_exito));
                estado = i;
                break;
            default:
                this.setBorder(new MatteBorder(1, 1, 1, 1, Colores.borde_cajaTexto));
                estado = i;
                break;
        }
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
        switch (estado) {
            case 1:
                this.setBorder(new MatteBorder(1, 1, 1, 1, Colores.borde_cajaTexto_error_hover));
                break;
            case 2:
                this.setBorder(new MatteBorder(1, 1, 1, 1, Colores.borde_cajaTexto_exito_hover));
                break;
            default:
                this.setBorder(new MatteBorder(1, 1, 1, 1, Colores.borde_cajaTexto_hover));
                break;
        }
    }

    public void mouseExited(MouseEvent e) {
        switch (estado) {
            case 1:
                this.setBorder(new MatteBorder(1, 1, 1, 1, Colores.borde_cajaTexto_error));
                break;
            case 2:
                this.setBorder(new MatteBorder(1, 1, 1, 1, Colores.borde_cajaTexto_exito));
                break;
            default:
                this.setBorder(new MatteBorder(1, 1, 1, 1, Colores.borde_cajaTexto));
                break;
        }
    }
}
