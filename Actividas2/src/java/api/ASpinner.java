package api;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.MatteBorder;

public class ASpinner extends JSpinner implements MouseListener, FocusListener{
    
    private int estado = Estado.normal;
    private double valor = 0;
    private double menor = Integer.MIN_VALUE;
    private double mayor = Integer.MAX_VALUE;
    public ASpinner(){
    	setBorder(new MatteBorder(1, 1, 1, 1, Colores.borde_cajaTexto));
        setFont(new Font("Calibri", Font.PLAIN, 14));
        setForeground(Colores.texto_normal);
        setBackground(Color.white);
        setOpaque(true);
        addMouseListener(this);
        addFocusListener(this);
        
        actualizar();
    }
    
    public ASpinner(double inicial, double menor,double mayor){
    	setBorder(new MatteBorder(1, 1, 1, 1, Colores.borde_cajaTexto));
        setFont(new Font("Calibri", Font.PLAIN, 14));
        setForeground(Colores.texto_normal);
        setBackground(Color.white);
        setOpaque(true);
        addMouseListener(this);
        addFocusListener(this);
        
        valor = inicial;
        this.menor = menor;
        this.mayor = mayor;
        
        actualizar();
    }
    
    public String getValorMaximo(){
	return mayor + "";
    }
    
    public String getValorMinimo(){
	return menor + "";
    }
    
    public String getValor(){
        String s = super.getValue().toString();
        //System.out.println(s);
        return s;
    }
    
    public void setValorInicial(double d){
	this.valor = d;
	actualizar();
    }
    
    public void setValorMaximo(double d){
	this.mayor = d;
	actualizar();
    }
    
    public void setValorMinimo(double d){
	this.menor = d;
	actualizar();
    }
    
    public boolean isOk(){
	double d =  Double.parseDouble(this.getValor());
	
	return d > mayor || d < menor;
    }
    
    private void actualizar(){
	this.setModel(new SpinnerNumberModel(valor, menor, mayor, 1));
    }
    
    public void setEstado(int i) {
        switch (i) {
            case 1:
                setBorder(new MatteBorder(1, 1, 1, 1, Colores.borde_cajaTexto_error));
                this.estado = i;
                break;
            case 2:
                setBorder(new MatteBorder(1, 1, 1, 1, Colores.borde_cajaTexto_exito));
                this.estado = i;
                break;
            default:
                setBorder(new MatteBorder(1, 1, 1, 1, Colores.borde_cajaTexto));
                this.estado = i;
                break;
        }
    }

    public void mouseClicked(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {
	switch (estado) {
        case 1:
            setBorder(new MatteBorder(1, 1, 1, 1, Colores.borde_cajaTexto_error_hover));
            break;
        case 2:
            setBorder(new MatteBorder(1, 1, 1, 1, Colores.borde_cajaTexto_exito_hover));
            break;
        default:
            setBorder(new MatteBorder(1, 1, 1, 1, Colores.borde_cajaTexto_hover));
            break;
	}
    }

    public void mouseExited(MouseEvent e) {
	
	double d =  Double.parseDouble(this.getValor());
    	
    	if(d < menor){
    	    this.setValue(menor);
    	}
    	
    	if(d > mayor){
    	    this.setValue(mayor);
    	}
        switch (estado) {
            case 1:
                setBorder(new MatteBorder(1, 1, 1, 1, Colores.borde_cajaTexto_error));
                break;
            case 2:
                setBorder(new MatteBorder(1, 1, 1, 1, Colores.borde_cajaTexto_exito));
                break;
            default:
                setBorder(new MatteBorder(1, 1, 1, 1, Colores.borde_cajaTexto));
                break;
        }
        
    }

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}

    @Override
    public void focusGained(FocusEvent e) {}

    @Override
    public void focusLost(FocusEvent e) {
	double d =  Double.parseDouble(this.getValor());
	
	if(d < menor){
	    this.setValue(menor);
	}
	
	if(d > mayor){
	    this.setValue(mayor);
	}
    }

}
