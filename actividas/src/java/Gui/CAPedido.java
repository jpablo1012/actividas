package Gui;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Date;

import Entidades.BolsaE;
import Entidades.ClienteE;
import Entidades.PedidoE;
import Negocio.BolsaN;
import Negocio.ClienteN;
import Negocio.PedidoN;
import api.*;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class CAPedido implements MouseListener {

    APanel panel;

    AContainer pedido,
            procesos;

    ADateChosser dtEntrega;

    ALabel lblPedido,
            lblFechaEntrega,//
            lblReferencia,
            lblBolsaReferencia,//
            lblTipoCantidad,//
            lblCantidad,//
            lblTipo_venta,//
            lblExtrusion,
            lblImpresion,
            lblSellado,
            lblCedulaCliente;

    ASpinner spnCantidad;

    AComboBox cmbTipo_venta,//
            cmbBolsaReferencia,
            cmbTipoCantidad;

    ALabel msjFechaEntrega,//
            msjBolsaReferencia,//
            msjCantidad,//
            msjTipo_venta,//
            msjReferencia,
            msjCedulaCliente,
            msjMensaje;

    AToggleButton tbtnExtrusion,
            tbtnImpresion,
            tbtnSellado;

    ATextField txtReferencia,
    		txtCedulaCliente;

    AButton btnCrear,
            btnNuevo,
            btnAtras;

    String style = "style='color:#D3362D;'";
    String[] tipo_venta = {"-Seleccione-", "Venta de producto", "Servicio"};
    String[] strTipoVenta = {"", "producto", "servicio"};
    String[] tipo_cantidad = {"-Seleccione-", "Kilos", "Unidades"};
    String[] strTipoCantidad = {"", "kilos", "unidades"};
    String[] strBolsaRef;
    String[] strBolsaReferencia;
    String[] strCedulaCliente;

    PedidoE pe;

    public CAPedido() {
        panel = new APanel(Main.x, 0, 750, 600);

        lblPedido = new ALabel("Pedidos| Crear");
        lblPedido.setFont(new Font("Calibri", Font.PLAIN, 24));
        lblPedido.setForeground(Colores.titulo_normal);
        lblPedido.setBounds(10, 0, 181, 50);
        panel.add(lblPedido);

        pedido = new AContainer("Pedido");
        pedido.setBounds(60, 48, 630, 273);
        panel.add(pedido);

        lblFechaEntrega = new ALabel("<html><body><b " + style + ">*</b>Fecha de entrega:</body></html>");
        lblFechaEntrega.setHorizontalAlignment(SwingConstants.RIGHT);
        lblFechaEntrega.setBounds(30, 30, 120, 24);
        pedido.add(lblFechaEntrega);

        dtEntrega = new ADateChosser();
        dtEntrega.setBounds(170, 30, 120, 24);
        pedido.add(dtEntrega);

        msjFechaEntrega = new ALabel("");
        msjFechaEntrega.setVerticalAlignment(SwingConstants.TOP);
        msjFechaEntrega.setBounds(170, 54, 130, 34);
        pedido.add(msjFechaEntrega);

        lblTipo_venta = new ALabel("<html><body><b " + style + ">*</b>Tipo de compra:</body></html>");
        lblTipo_venta.setBounds(310, 30, 120, 24);
        lblTipo_venta.setHorizontalAlignment(SwingConstants.RIGHT);
        pedido.add(lblTipo_venta);

        cmbTipo_venta = new AComboBox(tipo_venta);
        cmbTipo_venta.setBounds(450, 30, 120, 24);
        cmbTipo_venta.setValores(strTipoVenta);
        pedido.add(cmbTipo_venta);

        msjTipo_venta = new ALabel("");
        msjTipo_venta.setVerticalAlignment(SwingConstants.TOP);
        msjTipo_venta.setBounds(450, 54, 130, 34);
        pedido.add(msjTipo_venta);

        lblTipoCantidad = new ALabel("<html><body><b " + style + ">*</b>Cantidad en:</body></html>");
        lblTipoCantidad.setBounds(30, 88, 120, 24);
        lblTipoCantidad.setHorizontalAlignment(SwingConstants.RIGHT);
        pedido.add(lblTipoCantidad);

        cmbTipoCantidad = new AComboBox(tipo_cantidad);
        cmbTipoCantidad.setBounds(170, 88, 120, 24);
        cmbTipoCantidad.setValores(strTipoCantidad);
        pedido.add(cmbTipoCantidad);

        spnCantidad = new ASpinner(1, 1, Integer.MAX_VALUE);
        spnCantidad.setBounds(450, 88, 70, 24);
        pedido.add(spnCantidad);

        lblCantidad = new ALabel("<html><body><b " + style + ">*</b>Cantidad:</body></html>");
        lblCantidad.setHorizontalAlignment(SwingConstants.RIGHT);
        lblCantidad.setBounds(310, 88, 120, 24);
        pedido.add(lblCantidad);

        msjCantidad = new ALabel("");
        msjCantidad.setVerticalAlignment(SwingConstants.TOP);
        msjCantidad.setBounds(170, 112, 130, 34);
        pedido.add(msjCantidad);

        lblBolsaReferencia = new ALabel("<html><body><b " + style + ">*</b>Referencia bolsa:</body></html>");
        lblBolsaReferencia.setHorizontalAlignment(SwingConstants.RIGHT);
        lblBolsaReferencia.setBounds(30, 205, 120, 24);
        pedido.add(lblBolsaReferencia);

        strBolsaReferencia = devolverReferenciasBolsa();
        cmbBolsaReferencia = new AComboBox(strBolsaReferencia);
        cmbBolsaReferencia.setValores(strBolsaRef);
        cmbBolsaReferencia.setBounds(170, 205, 120, 24);
        pedido.add(cmbBolsaReferencia);

        msjBolsaReferencia = new ALabel("");
        msjBolsaReferencia.setVerticalAlignment(SwingConstants.TOP);
        msjBolsaReferencia.setBounds(170, 228, 120, 34);
        pedido.add(msjBolsaReferencia);

        lblReferencia = new ALabel("<html><body><b " + style + "></b>Texto de la bolsa:</body></html>");
        lblReferencia.setHorizontalAlignment(SwingConstants.RIGHT);
        lblReferencia.setBounds(30, 151, 120, 24);
        pedido.add(lblReferencia);

        txtReferencia = new ATextField();
        txtReferencia.setBounds(170, 152, 400, 24);
        txtReferencia.setPlaceHolder("Texto de la bolsa");
        pedido.add(txtReferencia);

        msjReferencia = new ALabel("");
        msjReferencia.setBounds(450, 190, 120, 20);
        pedido.add(msjReferencia);

        procesos = new AContainer("Procesos");
        procesos.setBounds(124, 332, 500, 158);
        panel.add(procesos);

        lblExtrusion = new ALabel("<html><body><b " + style + ">*</b>Extrusi\u00F3n:</body></html>");
        lblExtrusion.setHorizontalAlignment(SwingConstants.RIGHT);
        lblExtrusion.setBounds(30, 30, 70, 24);
        procesos.add(lblExtrusion);

        tbtnExtrusion = new AToggleButton("Si", "No");
        tbtnExtrusion.setBounds(120, 30, 70, 24);
        procesos.add(tbtnExtrusion);

        lblImpresion = new ALabel("<html><body><b " + style + ">*</b>Impresi\u00F3n:</body></html>");
        lblImpresion.setHorizontalAlignment(SwingConstants.RIGHT);
        lblImpresion.setBounds(260, 30, 70, 24);
        procesos.add(lblImpresion);

        tbtnImpresion = new AToggleButton("Si", "No");
        tbtnImpresion.setBounds(350, 30, 70, 24);
        procesos.add(tbtnImpresion);

        lblSellado = new ALabel("<html><body><b " + style + ">*</b>Sellado:</body></html>");
        lblSellado.setHorizontalAlignment(SwingConstants.RIGHT);
        lblSellado.setBounds(30, 94, 70, 24);
        procesos.add(lblSellado);

        tbtnSellado = new AToggleButton("Si", "No");
        tbtnSellado.setBounds(120, 94, 70, 24);
        procesos.add(tbtnSellado);

        btnCrear = new AButton("Crear pedido");
        btnCrear.setBounds(310, 514, 130, 30);
        btnCrear.addMouseListener(this);
        panel.add(btnCrear);

        btnNuevo = new AButton("Crear otro pedido");
        btnNuevo.setBounds(295, 270, 160, 60);
        btnNuevo.addMouseListener(this);
        btnNuevo.setVisible(false);
        panel.add(btnNuevo);

        btnAtras = new AButton("Regresar");
        btnAtras.setBounds(310, 514, 130, 30);
        btnAtras.setVisible(false);
        btnAtras.addMouseListener(this);
        panel.add(btnAtras);

        msjMensaje = new ALabel("");
        msjMensaje.setBounds(0, 555, panel.getWidth(), 20);
        msjMensaje.setFont(new Font("Calibri", Font.PLAIN, 18));
        msjMensaje.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(msjMensaje);
        
        if(Main.menu.evaluar(Main.menu.getUsuario().getTipo()) == 1){
            lblCedulaCliente = new ALabel("<html><body align='right'><b " + style + "></b>Cedula del cliente:</body></html>");
            lblCedulaCliente.setBounds(360, 195, 70, 34);
            lblCedulaCliente.setHorizontalAlignment(SwingConstants.RIGHT);
            pedido.add(lblCedulaCliente);
            
            txtCedulaCliente = new ATextField();
            txtCedulaCliente.setPlaceHolder("Cedula del cliente");
            txtCedulaCliente.setBounds(450, 205, 120, 24);
            pedido.add(txtCedulaCliente);
            
            msjCedulaCliente = new ALabel("");
            msjCedulaCliente.setBounds(450, 228, 120, 34);
            pedido.add(msjCedulaCliente);
        }

        this.pe = null;
    }

    public void setDatos(PedidoE pe) {
        this.pe = pe;
        dtEntrega.setDate(this.pe.getFecha_entrega());

        spnCantidad.setValue(this.pe.getCantidad());

        tbtnExtrusion.setSeleccionado(this.pe.isExtrusion());
        tbtnImpresion.setSeleccionado(this.pe.isImpresion());
        tbtnSellado.setSeleccionado(this.pe.isSellado());

        txtReferencia.setText(this.pe.getReferencia());

        cmbTipo_venta.setSelectedIndex(devolver_posicion(this.pe.getTipo_venta(), strTipoVenta));

        cmbBolsaReferencia.setSelectedIndex(devolver_posicion(this.pe.getBolsa_referencia() + "", cmbBolsaReferencia.getValores()));
        cmbTipoCantidad.setSelectedIndex(devolver_posicion(this.pe.getTipo_cantidad(), strTipoCantidad));
        
        try{
            txtCedulaCliente.setText(pe.getCliente_cedula());
        }catch(Exception e){}
    }

    public void setEditable(boolean b) {
        dtEntrega.setEnabled(b);

        spnCantidad.setEnabled(b);

        tbtnExtrusion.setEnabled(b);
        tbtnImpresion.setEnabled(b);
        tbtnSellado.setEnabled(b);

        txtReferencia.setEnabled(b);

        cmbTipo_venta.setEnabled(b);

        cmbBolsaReferencia.setEnabled(b);
        cmbTipoCantidad.setEnabled(b);
        
        try{
            txtCedulaCliente.setEnabled(b);
        }catch(Exception e){}
    }

    public int devolver_posicion(String i, String[] vector) {
        try {
            for (int j = 0; j < vector.length; j++) {
                if (i.equals(vector[j])) {
                    return j;
                }
            }
        } catch (Exception e) {
        }

        return 0;
    }

    public void limpiarCampos() {
        spnCantidad.setValue(1);
        dtEntrega.setDate(null);
        dtEntrega.setEstado(Estado.normal);
        cmbTipo_venta.setSelectedIndex(0);
        cmbTipo_venta.setEstado(Estado.normal);
        
        strBolsaReferencia = devolverReferenciasBolsa();

        cmbBolsaReferencia.setTextos(strBolsaReferencia);
        cmbBolsaReferencia.setValores(strBolsaRef);
        cmbBolsaReferencia.setEstado(Estado.normal);
        cmbBolsaReferencia.setSelectedIndex(0);
        cmbTipoCantidad.setSelectedIndex(0);
        cmbTipoCantidad.setEstado(Estado.normal);

        tbtnExtrusion.setSeleccionado(true);
        tbtnImpresion.setSeleccionado(true);
        tbtnSellado.setSeleccionado(true);

        txtReferencia.setText("");
        msjMensaje.setText("");
        
        try{
            txtCedulaCliente.setText("");
            txtCedulaCliente.setEstado(Estado.normal);
        }catch(Exception e){}
    }

    private String[] devolverReferenciasBolsa() {
        String cedula = null;
        ArrayList<BolsaE> albe = new ArrayList<BolsaE>();

        if (Main.menu.evaluar(Main.menu.getUsuario().getTipo()) == 2) {
            cedula = Main.menu.getUsuario().getClienteCedula();
            albe = new BolsaN().buscarBolsa("cliente_cedula", cedula, false);
        } else {
            albe = new BolsaN().buscarBolsa("referencia", "", false);
        }

        String[] s = new String[albe.size() + 1];
        strBolsaRef = new String[albe.size() + 1];
        s[0] = "-Seleccione-";
        strBolsaRef[0] = "-1";

        for (int i = 1; i <= albe.size(); i++) {
            BolsaE be = albe.get(i - 1);
            s[i] = "Ref: " + be.getReferencia();
            strBolsaRef[i] = be.getReferencia() + "";
        }

        return s;

    }

    public void visible(boolean crear, boolean nuevo, boolean container, boolean atras) {
        btnCrear.setVisible(crear);
        btnNuevo.setVisible(nuevo);

        pedido.setVisible(container);
        procesos.setVisible(container);

        btnAtras.setVisible(atras);
    }

    public boolean comprobar() {
        Date tiempo = dtEntrega.getDate();
        int venta = cmbTipo_venta.getSelectedIndex();
        int bolsa = cmbBolsaReferencia.getSelectedIndex();
        int tipoCantidad = cmbTipoCantidad.getSelectedIndex();
        boolean extrusion = tbtnExtrusion.getSeleccionado();
        boolean impresion = tbtnImpresion.getSeleccionado();
        boolean sellado = tbtnSellado.getSeleccionado();

        boolean cont = true;

        if (tiempo == null) {
            msjFechaEntrega.setText("Ingrese una fecha");
            msjFechaEntrega.setEstado(Estado.error);
            dtEntrega.setEstado(Estado.error);
            cont = false;
        } else {
            Date hoy = new Date();
            if (hoy.before(tiempo)) {
                msjFechaEntrega.setText("");
                dtEntrega.setEstado(Estado.exito);
            } else {
                msjFechaEntrega.setText("<html><body>Ingrese una fecha posterior a hoy</body></html>");
                msjFechaEntrega.setEstado(Estado.error);
                dtEntrega.setEstado(Estado.error);
                cont = false;
            }
        }

        if (venta == 0) {
            msjTipo_venta.setText("<html><body>Seleccione un tipo de venta</body></html>");
            msjTipo_venta.setEstado(Estado.error);
            cmbTipo_venta.setEstado(Estado.error);
            cont = false;
        } else {
            msjTipo_venta.setText("");
            cmbTipo_venta.setEstado(Estado.exito);
        }

        if (bolsa == 0) {
            msjBolsaReferencia.setText("<html><body>Seleccione una referencia</body></html>");
            msjBolsaReferencia.setEstado(Estado.error);
            cmbBolsaReferencia.setEstado(Estado.error);
            cont = false;
        } else {
            msjBolsaReferencia.setText("");
            cmbBolsaReferencia.setEstado(Estado.exito);
        }

        if (tipoCantidad == 0) {
            msjCantidad.setText("<html><body>Seleccione un tipo de cantidad</body></html>");
            msjCantidad.setEstado(Estado.error);
            cmbTipoCantidad.setEstado(Estado.error);
            cont = false;
        } else {
            msjCantidad.setText("");
            cmbTipoCantidad.setEstado(Estado.exito);
        }

        if (extrusion || impresion || sellado) {
            msjMensaje.setText("");
        } else {
            msjMensaje.setText("El pedido tiene que tener como m\u00EDnimo un proceso activado");
            msjMensaje.setEstado(Estado.error);
            cont = false;
        }
        
        try{
            try{
        	String cedula = txtCedulaCliente.getText();
        	if(cedula.equals("")){
        	}else{
        	    double d = Double.parseDouble(cedula);
        	}
        	msjCedulaCliente.setText("");
    	    	txtCedulaCliente.setEstado(Estado.exito);
            }catch(Exception e){
        	msjCedulaCliente.setText("<html><body>Solo numeros</body></html>");
        	msjCedulaCliente.setEstado(Estado.error);
        	txtCedulaCliente.setEstado(Estado.error);
        	cont = false;
            }
        }catch(Exception e){}

        return cont;
    }

    private void crear() {
        Date tiempo = dtEntrega.getDate();
        int venta = cmbTipo_venta.getSelectedIndex();
        int bolsa = cmbBolsaReferencia.getSelectedIndex();
        int tipoCantidad = cmbTipoCantidad.getSelectedIndex();
        boolean extrusion = tbtnExtrusion.getSeleccionado();
        boolean impresion = tbtnImpresion.getSeleccionado();
        boolean sellado = tbtnSellado.getSeleccionado();
        String referencia = txtReferencia.getText();
        double cantidad = Double.parseDouble(spnCantidad.getValor());

        PedidoE pe = new PedidoE();
        pe.setFecha_entrega(tiempo);
        pe.setTipo_venta(cmbTipo_venta.getValor(venta));
        pe.setBolsa_referencia(Integer.parseInt(cmbBolsaReferencia.getValor(bolsa)));
        pe.setTipo_cantidad(cmbTipoCantidad.getValor(tipoCantidad));
        pe.setCantidad(cantidad);
        pe.setExtrusion(extrusion);
        pe.setImpresion(impresion);
        pe.setSellado(sellado);
        pe.setReferencia(referencia);

        if (Main.menu.evaluar(Main.menu.getUsuario().getTipo()) == 2) {
            pe.setCliente_cedula(Main.menu.getUsuario().getClienteCedula());
        }else{
            try{
        	String cedula = txtCedulaCliente.getText();
        	if(cedula.equals("")){
        	    
        	}else{
        	    pe.setCliente_cedula(cedula);
        	}
            }catch(Exception e){}
        }

        String s = new PedidoN().crearPedido(pe);

        if (s.equals("")) {
            msjMensaje.setText("El pedido ha entrado en proceso de confirmacion");
            msjMensaje.setEstado(Estado.exito);
            visible(false, true, false, false);
        }

        if (s.equals("1")) {
            msjMensaje.setText("Error desconocido :C");
            try{
        	txtCedulaCliente.setEstado(Estado.error);
        	msjMensaje.setText("La cedula del cliente no existe");
            }catch(Exception e){}
            msjMensaje.setEstado(Estado.error);
            visible(true, false, true, false);
        }

        if (s.equals("2")) {
            msjMensaje.setText("Error al conectarse a la base de datos");
            msjMensaje.setEstado(Estado.error);
            visible(true, false, true, false);
        }

    }

    public void regresar() {
        Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());
        Main.removerTodos();
        Main.buscarPedido.buscar();
        Main.esconderTodos();
        Main.menu.frame.getContentPane().add(Main.buscarPedido.panel);
        Main.buscarPedido.panel.setVisible(true);
        Main.dialog.ocultar();
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        if (e.getSource() == btnCrear) {
            Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());
            if (comprobar()) {
                String[] botones = {"Si", "No"};
                int option = JOptionPane.showOptionDialog(Main.menu.frame, "El pedido una vez creado no se puede modificar, \u00BFest\u00E1 seguro que quiere continuar?", "Pedido", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, botones, botones[0]);
                if (option == 0) {
                    crear();
                }

            }
            Main.dialog.ocultar();
        }

        if (e.getSource() == btnNuevo) {
            Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());
            limpiarCampos();
            visible(true, false, true, false);
            Main.dialog.ocultar();
        }

        if (e.getSource() == btnAtras) {
            regresar();
        }
    }

    public void mouseReleased(MouseEvent e) {
    }

}
