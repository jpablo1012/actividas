package Gui;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingConstants;

import Entidades.ColorE;
import Entidades.MaterialE;
import Negocio.ColorN;
import Negocio.MaterialN;
import api.*;

public class CAInsumos implements MouseListener {

    public APanel panel;
    String style = "style='color:#D3362D;'";
    ALabel lblInsumos;
    ALabel lblObligatorio;

    // material
    AContainer material;
    ALabel lblMat_nombre, msjMat_nombre, lblMat_cantidad, msjMat_cantidad,
            msjMaterial;
    ATextField txtMat_nombre, txtMat_cantidad;
    AButton btnMat_crear, btnMat_guardar, btnMat_cancelar, btnMat_nuevo;
    // fin material
    // Color
    AContainer color;
    ALabel lblCo_cantidad, lblCo_referencia, msjCo_cantidad, msjCo_referencia,
            msjColor;
    ATextField txtCo_nombre;
    ATextField txtCo_cantidad;
    AButton btnCo_crear, btnCo_nuevo, btnCo_guardar, btnCo_cancelar;
    // fin Color
    ColorE ce = null;
    MaterialE me = null;

    public CAInsumos() {
        panel = new APanel(Main.x, 0, 750, 600);

        lblInsumos = new ALabel("Insumos| Crear");
        lblInsumos.setFont(new Font("Calibri", Font.PLAIN, 24));
        lblInsumos.setForeground(Colores.titulo_normal);
        lblInsumos.setBounds(10, 0, 250, 50);
        panel.add(lblInsumos);

        lblObligatorio = new ALabel("<html><body><b " + style + ">*</b>Campo obligatorio</body></html>");
        lblObligatorio.setHorizontalAlignment(SwingConstants.RIGHT);
        lblObligatorio.setBounds(319, 507, 112, 24);
        panel.add(lblObligatorio);

        material = new AContainer("Material");
        material.setBounds(110, 310, 530, 167);
        panel.add(material);

        lblMat_nombre = new ALabel("<html><body><b " + style + ">*</b>Nombre:</body></html>");
        lblMat_nombre.setHorizontalAlignment(SwingConstants.RIGHT);
        lblMat_nombre.setBounds(30, 30, 70, 24);
        material.add(lblMat_nombre);

        lblMat_cantidad = new ALabel("<html><body>Cantidad:</body></html>");
        lblMat_cantidad.setHorizontalAlignment(SwingConstants.RIGHT);
        lblMat_cantidad.setBounds(260, 30, 70, 24);
        material.add(lblMat_cantidad);

        txtMat_nombre = new ATextField();
        txtMat_nombre.setPlaceHolder("Nombre");
        txtMat_nombre.setBounds(120, 30, 120, 24);
        material.add(txtMat_nombre);

        txtMat_cantidad = new ATextField();
        txtMat_cantidad.setPlaceHolder("Cantidad (kilos)");
        txtMat_cantidad.setBounds(350, 30, 120, 24);
        material.add(txtMat_cantidad);

        msjMat_nombre = new ALabel("");
        msjMat_nombre.setBounds(120, 54, 120, 20);
        material.add(msjMat_nombre);

        msjMat_cantidad = new ALabel("");
        msjMat_cantidad.setBounds(350, 54, 120, 20);
        material.add(msjMat_cantidad);

        msjMaterial = new ALabel("");
        msjMaterial.setBounds(0, 134, material.getWidth(), 30);
        msjMaterial.setHorizontalAlignment(SwingConstants.CENTER);
        msjMaterial.setFont(new Font("Calibri", Font.PLAIN, 18));
        material.add(msjMaterial);

        btnMat_crear = new AButton("Crear material");
        btnMat_crear.setBounds(201, 94, 130, 30);
        btnMat_crear.addMouseListener(this);
        material.add(btnMat_crear);

        btnMat_guardar = new AButton("Guardar cambios");
        btnMat_guardar.setBounds(134, 94, 130, 30);
        btnMat_guardar.addMouseListener(this);
        btnMat_guardar.setVisible(false);
        material.add(btnMat_guardar);

        btnMat_cancelar = new AButton("Cancelar cambios");
        btnMat_cancelar.setBounds(268, 94, 130, 30);
        btnMat_cancelar.addMouseListener(this);
        btnMat_cancelar.setVisible(false);
        material.add(btnMat_cancelar);

        btnMat_nuevo = new AButton("Crear otro material");
        btnMat_nuevo.setBounds(185, 58, 160, 60);
        btnMat_nuevo.addMouseListener(this);
        btnMat_nuevo.setVisible(false);
        material.add(btnMat_nuevo);

        color = new AContainer("Color");
        color.setBounds(110, 113, 530, 167);
        panel.add(color);

        lblCo_referencia = new ALabel("<html><body align='right'><b " + style + ">*</b>Nombre:</body></html>");
        lblCo_referencia.setHorizontalAlignment(SwingConstants.RIGHT);
        lblCo_referencia.setBounds(30, 30, 70, 24);
        color.add(lblCo_referencia);

        lblCo_cantidad = new ALabel("Cantidad:");
        lblCo_cantidad.setHorizontalAlignment(SwingConstants.RIGHT);
        lblCo_cantidad.setBounds(260, 30, 70, 24);
        color.add(lblCo_cantidad);

        txtCo_nombre = new ATextField();
        txtCo_nombre.setPlaceHolder("Nombre");
        txtCo_nombre.setBounds(120, 30, 120, 24);
        color.add(txtCo_nombre);

        txtCo_cantidad = new ATextField();
        txtCo_cantidad.setBounds(350, 30, 120, 24);
        txtCo_cantidad.setPlaceHolder("Cantidad (litros)");
        color.add(txtCo_cantidad);

        msjCo_referencia = new ALabel("");
        msjCo_referencia.setBounds(120, 54, 120, 20);
        color.add(msjCo_referencia);

        msjCo_cantidad = new ALabel("");
        msjCo_cantidad.setBounds(350, 54, 120, 20);
        color.add(msjCo_cantidad);

        msjColor = new ALabel("");
        msjColor.setHorizontalAlignment(SwingConstants.CENTER);
        msjColor.setBounds(0, 134, color.getWidth(), 30);
        msjColor.setFont(new Font("Calibri", Font.PLAIN, 18));
        color.add(msjColor);

        btnCo_crear = new AButton("Crear color");
        btnCo_crear.setBounds(200, 94, 130, 30);
        btnCo_crear.addMouseListener(this);
        color.add(btnCo_crear);

        btnCo_guardar = new AButton("Guardar cambios");
        btnCo_guardar.setBounds(134, 94, 130, 30);
        btnCo_guardar.addMouseListener(this);
        btnCo_guardar.setVisible(false);
        color.add(btnCo_guardar);

        btnCo_cancelar = new AButton("Cancelar cambios");
        btnCo_cancelar.setBounds(268, 94, 130, 30);
        btnCo_cancelar.addMouseListener(this);
        btnCo_cancelar.setVisible(false);
        color.add(btnCo_cancelar);

        btnCo_nuevo = new AButton("Crear otro color");
        btnCo_nuevo.setBounds(185, 58, 160, 60);
        btnCo_nuevo.addMouseListener(this);
        btnCo_nuevo.setVisible(false);
        color.add(btnCo_nuevo);

        panel.setVisible(false);

        this.ce = null;
        this.me = null;
    }

    private void actualizarMaterial() {
        String nombre = txtMat_nombre.getText();
        String cantidad = txtMat_cantidad.getText().replaceAll(",", ".");

        MaterialE me = new MaterialE();
        me.setNombre(nombre);
        try {
            me.setCantidad(Double.parseDouble(cantidad));
        } catch (Exception e) {
            me.setCantidad(0);
        }
        me.setReferencia(this.me.getReferencia());

        String s = new MaterialN().actualizarMaterial(me);

        if (s.equals("")) {
            Main.esconderTodos();

            Main.buscarInsumos.buscar();
            Main.buscarInsumos.panel.setVisible(true);
            Main.buscarInsumos.msjMensaje.setText("El material ha sido actualizado correctamente");
            Main.buscarInsumos.msjMensaje.setEstado(Estado.exito);
            Main.buscarInsumos.msjMensaje.setVisible(true);
        }

        if (s.equals("1")) {
            visibleMaterial(true, true, false, false);
            msjMaterial.setEstado(Estado.error);
            msjMaterial.setText("Ha ocurrido un error al actualizar :C");
            msjMaterial.setVisible(true);
        }

        if (s.equals("2")) {
            visibleMaterial(true, true, false, false);
            msjMaterial.setEstado(Estado.error);
            msjMaterial.setText("Error al conectarse con la base de datos");
            msjMaterial.setVisible(true);
        }
    }

    public void datosMaterial(MaterialE me) {
        this.me = me;

        txtMat_cantidad.setText(this.me.getCantidad() + "");
        txtMat_nombre.setText(this.me.getNombre());
    }

    private void actualizarColor() {
        String cantidad = txtCo_cantidad.getText().replaceAll(",", ".");
        String nombre = txtCo_nombre.getText();

        ColorE ce = new ColorE();

        try {
            ce.setCantidad(Double.parseDouble(cantidad));
        } catch (Exception e) {
            ce.setCantidad(0);
        }
        ce.setNombre(nombre);
        ce.setCodigo(this.ce.getCodigo());

        String s = new ColorN().actualizarColor(ce);

        if (s.equals("")) {
            Main.esconderTodos();

            Main.buscarInsumos.buscar();
            Main.buscarInsumos.panel.setVisible(true);
            Main.buscarInsumos.msjMensaje.setText("El color ha sido actualizado correctamente");
            Main.buscarInsumos.msjMensaje.setEstado(Estado.exito);
            Main.buscarInsumos.msjMensaje.setVisible(true);
        }

        if (s.equals("1")) {
            visibleColor(true, true, false, false);
            msjColor.setEstado(Estado.error);
            msjColor.setText("Ha ocurrido un error al actualizar :C");
            msjColor.setVisible(true);
        }

        if (s.equals("2")) {
            visibleColor(true, true, false, false);
            msjColor.setEstado(Estado.error);
            msjColor.setText("Error al conectarse con la base de datos");
            msjColor.setVisible(true);
        }
    }

    public void datosColor(ColorE ce) {
        this.ce = ce;

        txtCo_cantidad.setText(this.ce.getCantidad() + "");
        txtCo_nombre.setText(this.ce.getNombre());
    }

    @SuppressWarnings("unused")
    public boolean comprobarColor() {
        String cantidad = txtCo_cantidad.getText().replaceAll(",", ".");
        String referencia = txtCo_nombre.getText();
        boolean b = true;

        if (referencia.equals("")) {
            txtCo_nombre.setEstado(Estado.error);
            msjCo_referencia.setEstado(Estado.error);
            msjCo_referencia.setText("Campo vac\u00EDo");
            msjCo_referencia.setVisible(true);
            b = false;
        } else {
            txtCo_nombre.setEstado(Estado.exito);
            msjCo_referencia.setEstado(Estado.exito);
            msjCo_referencia.setText("");
            msjCo_referencia.setVisible(false);
        }

        if (cantidad.equals("")) {
            txtCo_cantidad.setEstado(Estado.exito);
            msjCo_cantidad.setEstado(Estado.exito);
            msjCo_cantidad.setText("");
            msjCo_cantidad.setVisible(false);
        } else {
            try {
                double d = Double.parseDouble(cantidad);
                txtCo_cantidad.setEstado(Estado.exito);
                msjCo_cantidad.setEstado(Estado.exito);
                msjCo_cantidad.setText("");
                msjCo_cantidad.setVisible(false);
            } catch (Exception e) {
                txtCo_cantidad.setEstado(Estado.error);
                msjCo_cantidad.setEstado(Estado.error);
                msjCo_cantidad.setText("S\u00F3lo n\u00FAmeros");
                msjCo_cantidad.setVisible(true);
                b = false;
            }
        }

        return b;
    }

    public void crearColor() {
        String cantidad = txtCo_cantidad.getText().replaceAll(",", ".");
        String nombre = txtCo_nombre.getText();

        ColorE ce = new ColorE();

        try {
            ce.setCantidad(Double.parseDouble(cantidad));
        } catch (Exception e) {
            ce.setCantidad(0);
        }
        ce.setNombre(nombre);
        String s = new ColorN().crearColor(ce);

        if (s.equals("")) {
            msjColor.setEstado(Estado.exito);
            msjColor.setText("El color ha sido creado correctamente");
            msjColor.setVisible(true);
            visibleColor(false, false, true, false);
        }

        if (s.equals("1")) {
            msjColor.setEstado(Estado.error);
            msjColor.setText("Ha ocurrido un error al crear el material :C");
            msjColor.setVisible(true);
            visibleColor(true, true, false, false);
        }

        if (s.equals("2")) {
            msjColor.setEstado(Estado.error);
            msjColor.setText("Error al conectarse con la base de datos");
            msjColor.setVisible(true);
            visibleColor(true, true, false, false);
        }
    }

    public void limpiarColor() {
        txtCo_cantidad.setText("");
        txtCo_cantidad.setEstado(Estado.normal);
        txtCo_nombre.setText("");
        txtCo_nombre.setEstado(Estado.normal);
        msjColor.setText("");

    }

    public void visibleColor(boolean crear, boolean botonCrear, boolean nuevo, boolean actualizar) {
        lblCo_cantidad.setVisible(crear);
        lblCo_referencia.setVisible(crear);
        txtCo_cantidad.setVisible(crear);
        txtCo_nombre.setVisible(crear);
        btnCo_crear.setVisible(botonCrear);
        btnCo_guardar.setVisible(actualizar);
        btnCo_cancelar.setVisible(actualizar);
        btnCo_nuevo.setVisible(nuevo);
    }

    @SuppressWarnings("unused")
    public boolean comprobarMaterial() {
        String nombre = txtMat_nombre.getText();
        String cantidad = txtMat_cantidad.getText().replaceAll(",", ".");
        boolean b = true;
        if (nombre.equals("")) {
            txtMat_nombre.setEstado(Estado.error);
            msjMat_nombre.setEstado(Estado.error);
            msjMat_nombre.setText("Campo vac\u00EDo");
            msjMat_nombre.setVisible(true);
            b = false;
        } else {
            txtMat_nombre.setEstado(Estado.exito);
            msjMat_nombre.setEstado(Estado.exito);
            msjMat_nombre.setText("");
            msjMat_nombre.setVisible(false);
        }

        if (cantidad.equals("")) {
            txtMat_cantidad.setEstado(Estado.exito);
            msjMat_cantidad.setEstado(Estado.exito);
            msjMat_cantidad.setText("");
            msjMat_cantidad.setVisible(false);
        } else {
            try {
                double d = Double.parseDouble(cantidad);
                txtMat_cantidad.setEstado(Estado.exito);
                msjMat_cantidad.setEstado(Estado.exito);
                msjMat_cantidad.setText("");
                msjMat_cantidad.setVisible(false);
            } catch (Exception e) {
                txtMat_cantidad.setEstado(Estado.error);
                msjMat_cantidad.setEstado(Estado.error);
                msjMat_cantidad.setText("S\u00F3olo n\u00FAmeros");
                msjMat_cantidad.setVisible(true);
                b = false;
            }
        }

        return b;
    }

    public void crearMaterial() {
        String nombre = txtMat_nombre.getText();
        String cantidad = txtMat_cantidad.getText().replaceAll(",", ".");

        MaterialE me = new MaterialE();
        me.setNombre(nombre);
        try {
            me.setCantidad(Double.parseDouble(cantidad));
        } catch (Exception e) {
            me.setCantidad(0);
        }

        String s = new MaterialN().crearMaterial(me);

        if (s.equals("")) {
            msjMaterial.setEstado(Estado.exito);
            msjMaterial.setText("El material ha sido creado exitosamente");
            msjMaterial.setVisible(true);
            visibleMaterial(false, false, true, false);
        }

        if (s.equals("1")) {
            msjMaterial.setEstado(Estado.exito);
            msjMaterial.setText("Ha ocurrido un error al crear el material :C");
            msjMaterial.setVisible(true);
            visibleMaterial(true, true, false, false);
        }

        if (s.equals("2")) {
            msjMaterial.setEstado(Estado.exito);
            msjMaterial.setText("Error al comunicarse con la base de datos");
            msjMaterial.setVisible(true);
            visibleMaterial(true, true, false, false);
        }
    }

    public void limpiarMaterial() {
        txtMat_cantidad.setText("");
        txtMat_cantidad.setEstado(Estado.normal);
        txtMat_nombre.setText("");
        txtMat_nombre.setEstado(Estado.normal);
        msjMaterial.setText("");

    }

    public void visibleMaterial(boolean crear, boolean botonCrear, boolean nuevo, boolean actualizar) {
        lblMat_cantidad.setVisible(crear);
        lblMat_nombre.setVisible(crear);
        txtMat_nombre.setVisible(crear);
        txtMat_cantidad.setVisible(crear);
        btnMat_crear.setVisible(botonCrear);
        btnMat_nuevo.setVisible(nuevo);
        btnMat_guardar.setVisible(actualizar);
        btnMat_cancelar.setVisible(actualizar);
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {

        // Material
        if (e.getSource() == btnMat_crear) {
            Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());
            if (comprobarMaterial()) {
                crearMaterial();
            }
            Main.dialog.ocultar();
        }

        if (e.getSource() == btnMat_nuevo) {
            visibleMaterial(true, true, false, false);
            limpiarMaterial();
        }

        if (e.getSource() == btnMat_guardar) {
            Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());
            if (comprobarMaterial()) {
                actualizarMaterial();
            }
            Main.dialog.ocultar();
        }

        if (e.getSource() == btnMat_cancelar) {
            Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());
            Main.esconderTodos();

            Main.buscarInsumos.buscar();
            Main.buscarInsumos.panel.setVisible(true);
            Main.buscarInsumos.msjMensaje.setText("");
            Main.dialog.ocultar();
        }
        // Fin material

        // Color
        if (e.getSource() == btnCo_crear) {
            Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());
            if (comprobarColor()) {
                crearColor();
            }
            Main.dialog.ocultar();

        }

        if (e.getSource() == btnCo_nuevo) {
            visibleColor(true, true, false, false);
            limpiarColor();
        }

        if (e.getSource() == btnCo_guardar) {
            Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());
            if (comprobarColor()) {
                actualizarColor();
            }
            Main.dialog.ocultar();
        }

        if (e.getSource() == btnCo_cancelar) {
            Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());
            Main.esconderTodos();

            Main.buscarInsumos.buscar();
            Main.buscarInsumos.panel.setVisible(true);
            Main.buscarInsumos.msjMensaje.setText("");
            Main.dialog.ocultar();
        }
        // Fin Color
    }

    public void mouseReleased(MouseEvent e) {
    }
}
