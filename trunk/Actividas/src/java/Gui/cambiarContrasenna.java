package Gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import api.*;
import javax.swing.SwingConstants;

public class cambiarContrasenna implements MouseListener {

    APanel panel;
    AContainer contrasenna;
    ALabel lblActual, lblNueva, lblComprobar, lblObligatorio;
    APassword pswActual, pswNueva, pswComprobar;
    ALabel msjActual, msjNueva, msjComprobar, msjMensaje;
    AButton btnCambiar;

    String style = "style='color:#D3362D;'";

    public cambiarContrasenna() {
        panel = new APanel(Main.x, 0, 750, 600);

        contrasenna = new AContainer("Cliente");
        contrasenna.setBounds(245, 81, 260, 438);
        panel.add(contrasenna);

        lblActual = new ALabel("<html><body><b " + style + ">*</b>Contrase\u00F1a actual:</body></html>");
        lblActual.setBounds(30, 30, 120, 24);
        contrasenna.add(lblActual);

        pswActual = new APassword();
        pswActual.setBounds(30, 64, 200, 24);
        pswActual.setPlaceHolder("Contrase\u00F1a actual");
        contrasenna.add(pswActual);

        msjActual = new ALabel("");
        msjActual.setBounds(30, 88, 200, 20);
        contrasenna.add(msjActual);

        lblNueva = new ALabel("<html><body><b " + style + ">*</b>Contrase\u00F1a nueva:</body></html>");
        lblNueva.setBounds(30, 118, 120, 24);
        contrasenna.add(lblNueva);

        pswNueva = new APassword();
        pswNueva.setBounds(30, 152, 200, 24);
        pswNueva.setPlaceHolder("Contrase\u00F1a nueva");
        contrasenna.add(pswNueva);

        msjNueva = new ALabel("");
        msjNueva.setVerticalAlignment(SwingConstants.TOP);
        msjNueva.setBounds(30, 176, 200, 20);
        contrasenna.add(msjNueva);

        lblComprobar = new ALabel("<html><body><b " + style + ">*</b>Confirmar contrase\u00F1a nueva:</body></html>");
        lblComprobar.setBounds(30, 206, 200, 24);
        contrasenna.add(lblComprobar);

        pswComprobar = new APassword();
        pswComprobar.setBounds(30, 240, 200, 24);
        pswComprobar.setPlaceHolder("Confirmar contrase\u00F1a nueva:");
        contrasenna.add(pswComprobar);

        msjNueva = new ALabel("");
        msjNueva.setBounds(30, 264, 200, 20);
        contrasenna.add(msjNueva);

        lblObligatorio = new ALabel("<html><body><b " + style + ">*</b>Campo obligatorio</body></html>");
        lblObligatorio.setBounds(30, 294, 120, 24);
        contrasenna.add(lblObligatorio);

        btnCambiar = new AButton("Cambiar contrase\u00F1a");
        btnCambiar.setBounds(60, 328, 130, 30);
        contrasenna.add(btnCambiar);

        msjMensaje = new ALabel("");
        msjMensaje.setHorizontalAlignment(SwingConstants.CENTER);
        msjMensaje.setVerticalAlignment(SwingConstants.TOP);
        msjMensaje.setBounds(30, 368, 200, 40);
        contrasenna.add(msjMensaje);
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

}
