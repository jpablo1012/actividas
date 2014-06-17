package Gui;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import api.AButton;
import api.AContainer;
import api.ALabel;
import api.APanel;
import api.APassword;
import api.ATextField;
import api.Estado;
import Entidades.ClienteE;
import Entidades.UsuarioE;
import Negocio.ClienteN;
import Negocio.UsuarioN;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CACliente implements MouseListener {

    String style = "style='color:#D3362D;'";
    public APanel panel;
    ALabel lblbnombre;
    ALabel lblapedillo;
    ALabel lblcedula;
    ALabel lbldireccion;
    ALabel lbltelefono;
    ALabel lblcorreo;
    ALabel lblcodigo;
    ALabel lblconfirmar;
    ALabel msjNombre;
    ALabel msjApellido;
    ALabel msjCedula;
    ALabel msjDireccion;
    ALabel msjTelefono;
    ALabel msjCorreo;
    ALabel msjCodigo;
    ALabel msjMensaje;
    ALabel msjCiudad;
    ALabel lblObligatorio;
    ALabel lblCiudad;
    ATextField txtnombre;
    ATextField txtapellido;
    ATextField txtcedula;
    ATextField txtdireccion;
    ATextField txttelefono;
    ATextField txtcorreo;
    ATextField txtCiudad;
    APassword pswcodigo;
    APassword pswconfirmar;
    AButton btncrear;
    AButton btnNuevo;
    AButton btnGuardar;
    AButton btnCancelar;
    AContainer clie;
    AContainer usua;
    ALabel lblImagen;
    ALabel msjImagen;
    AButton btnSeleccionar;
    AButton btnVer;
    AButton btnQuitar;

    ClienteE ce = new ClienteE();
    UsuarioE ue = new UsuarioE();
    private boolean aBuscar = true;
    File file;

    public CACliente() {
        panel = new APanel(Main.x, 0, 750, 600);
        panel.setTitulo("Cliente| Crear");

        clie = new AContainer("Cliente");
        clie.setBounds(110, 71, 530, 241);
        panel.add(clie);

        lblObligatorio = new ALabel("<html><body><b " + style + ">*</b> Campo obligatorio</body></html>");
        lblObligatorio.setBounds(315, 40, 120, 24);
        panel.add(lblObligatorio);

        usua = new AContainer("Usuario");
        usua.setBounds(110, 322, 530, 150);
        panel.add(usua);

        lblbnombre = new ALabel("<html><body><b " + style + ">*</b>Nombre:</body></html>");
        lblbnombre.setHorizontalAlignment(SwingConstants.RIGHT);
        lblbnombre.setBounds(35, 30, 70, 24);
        clie.add(lblbnombre);

        lblapedillo = new ALabel("<html><body><b " + style + ">*</b>Apellido:</body></html>");
        lblapedillo.setHorizontalAlignment(SwingConstants.RIGHT);
        lblapedillo.setBounds(254, 30, 70, 24);
        clie.add(lblapedillo);

        lblcedula = new ALabel("<html><body><b " + style + ">*</b>C\u00E9dula:</body></html>");
        lblcedula.setHorizontalAlignment(SwingConstants.RIGHT);
        lblcedula.setBounds(35, 85, 70, 24);
        clie.add(lblcedula);

        lbldireccion = new ALabel("<html><body><b " + style + ">*</b>Direcci\u00F3n:</body></html>");
        lbldireccion.setHorizontalAlignment(SwingConstants.RIGHT);
        lbldireccion.setBounds(254, 85, 70, 24);
        clie.add(lbldireccion);

        lbltelefono = new ALabel("<html><body><b style='color:D3362D;'>*</b>Tel\u00E9fono:</body></html>");
        lbltelefono.setHorizontalAlignment(SwingConstants.RIGHT);
        lbltelefono.setBounds(35, 140, 70, 24);
        clie.add(lbltelefono);

        lblcorreo = new ALabel("<html><body><b " + style + ">*</b>E-mail:</body></html>");
        lblcorreo.setHorizontalAlignment(SwingConstants.RIGHT);
        lblcorreo.setBounds(254, 140, 70, 24);
        clie.add(lblcorreo);

        lblCiudad = new ALabel("<html><body><b " + style + ">*</b>Ciudad:</body></html>");
        lblCiudad.setHorizontalAlignment(SwingConstants.RIGHT);
        lblCiudad.setBounds(35, 195, 70, 24);
        clie.add(lblCiudad);

        lblcodigo = new ALabel("<html><body><b style='color:D3362D;'>*</b>Contrase\u00F1a:</body></html>");
        lblcodigo.setHorizontalAlignment(SwingConstants.RIGHT);
        lblcodigo.setBounds(25, 30, 80, 24);
        usua.add(lblcodigo);

        lblconfirmar = new ALabel("<html><body align='right'><b " + style + ">*</b>Confirmar Contrase\u00F1a:</body></html>");
        lblconfirmar.setHorizontalAlignment(SwingConstants.RIGHT);
        lblconfirmar.setBounds(249, 20, 75, 36);
        usua.add(lblconfirmar);

        msjNombre = new ALabel("");
        msjNombre.setBounds(117, 54, 120, 20);
        clie.add(msjNombre);

        msjApellido = new ALabel("");
        msjApellido.setBounds(336, 54, 120, 20);
        clie.add(msjApellido);

        msjCedula = new ALabel("");
        msjCedula.setBounds(117, 109, 120, 20);
        clie.add(msjCedula);

        msjDireccion = new ALabel("");
        msjDireccion.setBounds(336, 109, 120, 24);
        clie.add(msjDireccion);

        msjTelefono = new ALabel("");
        msjTelefono.setBounds(117, 164, 120, 20);
        clie.add(msjTelefono);

        msjCorreo = new ALabel("");
        msjCorreo.setBounds(336, 164, 120, 20);
        clie.add(msjCorreo);

        msjCiudad = new ALabel("");
        msjCiudad.setBounds(257, 195, 199, 24);
        clie.add(msjCiudad);

        msjCodigo = new ALabel("");
        msjCodigo.setHorizontalAlignment(SwingConstants.CENTER);
        msjCodigo.setVerticalAlignment(SwingConstants.TOP);
        msjCodigo.setBounds(117, 54, 339, 20);
        usua.add(msjCodigo);

        msjMensaje = new ALabel("");
        msjMensaje.setHorizontalAlignment(SwingConstants.CENTER);
        msjMensaje.setFont(new Font("Calibri", Font.PLAIN, 18));
        msjMensaje.setBounds(0, 552, panel.getWidth(), 24);
        panel.add(msjMensaje);

        txtnombre = new ATextField();
        txtnombre.setBounds(117, 30, 120, 24);
        txtnombre.setPlaceHolder("Nombre");
        clie.add(txtnombre);

        txtapellido = new ATextField();
        txtapellido.setBounds(336, 30, 120, 24);
        txtapellido.setPlaceHolder("Apellido");
        clie.add(txtapellido);

        txtcedula = new ATextField();
        txtcedula.setBounds(117, 85, 120, 24);
        txtcedula.setPlaceHolder("C\u00E9dula");
        clie.add(txtcedula);

        txtdireccion = new ATextField();
        txtdireccion.setBounds(336, 85, 120, 24);
        txtdireccion.setPlaceHolder("Direcci\u00F3n");
        clie.add(txtdireccion);

        txttelefono = new ATextField();
        txttelefono.setBounds(117, 140, 120, 24);
        txttelefono.setPlaceHolder("Tel\u00E9fono");
        clie.add(txttelefono);

        txtcorreo = new ATextField();
        txtcorreo.setSelectionStart(0);
        txtcorreo.setBounds(336, 140, 120, 24);
        txtcorreo.setPlaceHolder("E-mail");
        clie.add(txtcorreo);

        txtCiudad = new ATextField();
        txtCiudad.setBounds(117, 195, 120, 24);
        txtCiudad.setPlaceHolder("Ciudad");
        clie.add(txtCiudad);

        pswcodigo = new APassword();
        pswcodigo.setBounds(117, 30, 120, 24);
        pswcodigo.setPlaceHolder("Contrase\u00F1a");
        usua.add(pswcodigo);

        pswconfirmar = new APassword();
        pswconfirmar.setBounds(336, 30, 120, 24);
        pswconfirmar.setPlaceHolder("Confirmar Contrase\u00F1a");
        usua.add(pswconfirmar);

        btncrear = new AButton("Crear cliente");
        btncrear.setBounds(310, 502, 130, 30);
        btncrear.addMouseListener(this);
        panel.add(btncrear);

        btnNuevo = new AButton("Crear otro cliente");
        btnNuevo.setBounds(295, 270, 160, 60);
        btnNuevo.addMouseListener(this);
        btnNuevo.setVisible(false);

        btnGuardar = new AButton("Guardar cambios");
        btnGuardar.setBounds(235, 502, 130, 30);
        btnGuardar.addMouseListener(this);
        btnGuardar.setVisible(false);
        panel.add(btnGuardar);

        btnCancelar = new AButton("Cancelar cambios");
        btnCancelar.setBounds(385, 502, 130, 30);
        btnCancelar.setVisible(false);
        btnCancelar.addMouseListener(this);
        panel.add(btnCancelar);

        lblImagen = new ALabel("<html><body align='right'><b " + style + "></b>Imagen de perfil:</body></html>");
        lblImagen.setHorizontalAlignment(SwingConstants.RIGHT);
        lblImagen.setBounds(25, 85, 70, 34);
        usua.add(lblImagen);

        btnSeleccionar = new AButton("Examinar...");
        btnSeleccionar.setBounds(117, 95, 120, 24);
        btnSeleccionar.addMouseListener(this);
        usua.add(btnSeleccionar);

        btnVer = new AButton("Ver");
        btnVer.setBounds(247, 95, 50, 24);
        btnVer.addMouseListener(this);
        btnVer.setVisible(false);
        usua.add(btnVer);

        btnQuitar = new AButton("Quitar");
        btnQuitar.setBounds(307, 95, 50, 24);
        btnQuitar.addMouseListener(this);
        btnQuitar.setVisible(false);
        usua.add(btnQuitar);

        msjImagen = new ALabel("Maximo 1MB");
        msjImagen.setBounds(117, 119, 339, 20);
        usua.add(msjImagen);

        panel.add(btnNuevo);
        panel.setVisible(false);

        file = null;

    }

    public void visibleCliente(boolean crear, boolean botoncrear, boolean nuevo, boolean actualizar) {
        txtnombre.setVisible(crear);
        txtapellido.setVisible(crear);
        txtcedula.setVisible(crear);
        txtdireccion.setVisible(crear);
        txttelefono.setVisible(crear);
        txtcorreo.setVisible(crear);
        txtCiudad.setVisible(crear);
        pswcodigo.setVisible(crear);
        pswconfirmar.setVisible(crear);
        lblObligatorio.setVisible(crear);

        btncrear.setVisible(botoncrear);
        btnNuevo.setVisible(nuevo);
        btnGuardar.setVisible(actualizar);
        btnCancelar.setVisible(actualizar);

        if (actualizar) {
            txtcedula.setEnabled(false);
        } else {
            txtcedula.setEnabled(true);
        }
    }

    public void limpiarCampos() {
        txtnombre.setText("");
        txtapellido.setText("");
        txtcedula.setText("");
        txtdireccion.setText("");
        txttelefono.setText("");
        txtcorreo.setText("");
        txtCiudad.setText("");
        pswcodigo.setText("");
        pswconfirmar.setText("");
        msjMensaje.setText("");

        txtnombre.setEstado(Estado.NORMAL);
        txtapellido.setEstado(Estado.NORMAL);
        txtcedula.setEstado(Estado.NORMAL);
        txtdireccion.setEstado(Estado.NORMAL);
        txttelefono.setEstado(Estado.NORMAL);
        txtcorreo.setEstado(Estado.NORMAL);
        txtCiudad.setEstado(Estado.NORMAL);
        pswcodigo.setEstado(Estado.NORMAL);
        pswconfirmar.setEstado(Estado.NORMAL);
        msjMensaje.setEstado(Estado.NORMAL);

        quitarImagen();
    }

    public void setDatos(ClienteE ce, UsuarioE ue) {
        this.ce = ce;
        this.ue = ue;

        txtapellido.setText(this.ce.getApellido());
        txtcedula.setText(this.ce.getCedula());
        txtcorreo.setText(this.ce.getCorreo());
        txtdireccion.setText(this.ce.getDireccion());
        txtnombre.setText(this.ce.getNombre());
        txttelefono.setText(this.ce.getTelefono());
        txtCiudad.setText(this.ce.getCiudad());

        pswcodigo.setText(this.ue.getCodigo());
        pswconfirmar.setText(this.ue.getCodigo());

        this.file = this.ue.getImagen();
        if (file != null) {
            msjImagen.setText(this.file.getName());
            btnQuitar.setVisible(true);
            btnVer.setVisible(true);

        } else {
            msjImagen.setText("M\u00E1ximo 1MB");
            btnQuitar.setVisible(false);
            btnVer.setVisible(false);
            quitarImagen();
        }
    }

    @SuppressWarnings("deprecation")
    private void actualizar() {
        String nombre = txtnombre.getText();
        String apellido = txtapellido.getText();
        String cedula = txtcedula.getText();
        String direccion = txtdireccion.getText();
        String telefono = txttelefono.getText();
        String correo = txtcorreo.getText();
        String codigo = pswcodigo.getText();
        String ciudad = txtCiudad.getText();

        ClienteE ace = new ClienteE();
        ClienteN cn = new ClienteN();

        ace.setApellido(apellido);
        ace.setCedula(cedula);
        ace.setCorreo(correo);
        ace.setDireccion(direccion);
        ace.setNombre(nombre);
        ace.setTelefono(telefono);
        ace.setCiudad(ciudad);

        //String s = cn.actualizarCliente(ace);
        String s = cn.actualizarClienteL(ace);
        UsuarioE cue = this.ue;
        if (s.equals("")) {

            UsuarioN un = new UsuarioN();

            cue.setApellido(apellido);
            cue.setClienteCedula(cedula);
            cue.setCodigo(codigo);
            cue.setNombre(nombre);
            cue.setTipo("cliente");
            cue.setImagen(file);

            //s = un.actualizarUsuario(cue);
            s = un.actualizarUsuarioL(cue);
        }

        if (s.equals("")) {
            if (aBuscar) {
                msjMensaje.setEstado(Estado.EXITO);
                Main.buscarCliente.buscar();
                Main.buscarCliente.msjMensaje.setText("El cliente ha sido actualizado exitosamente");
                Main.buscarCliente.msjMensaje.setVisible(true);
                Main.buscarCliente.msjMensaje.setEstado(Estado.EXITO);
                Main.esconderTodos();
                Main.buscarCliente.panel.setVisible(true);
                this.ce = ace;
                this.ue = cue;
            } else {
                Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());
                Main.removerTodos();
                Main.menu.frame.getContentPane().add(Main.ajustes.panel);
                Main.esconderTodos();
                Main.ajustes.panel.setVisible(true);
                Main.ajustes.msjMensaje.setText("Te has modificado exitosamente");
                Main.ajustes.msjMensaje.setEstado(Estado.EXITO);
                Main.ajustes.msjMensaje.setVisible(true);
                Main.dialog.ocultar();
            }

        }

        if (s.equals("1")) {
            msjMensaje.setEstado(Estado.ERROR);
            msjMensaje.setText("Error desconocido");
            msjMensaje.setVisible(true);
        }

        if (s.equals("2")) {
            msjMensaje.setEstado(Estado.ERROR);
            msjMensaje.setText("Error al conectarse a la base de datos");
            msjMensaje.setVisible(true);
        }

    }

    public void regresoAbuscar(boolean b) {
        this.aBuscar = b;
    }

    @SuppressWarnings({ "deprecation", "unused" })
    private boolean validar() {
        boolean cont = true;

        String nombre = txtnombre.getText();
        String apellido = txtapellido.getText();
        String cedula = txtcedula.getText();
        String direccion = txtdireccion.getText();
        String telefono = txttelefono.getText();
        String correo = txtcorreo.getText();
        String ciudad = txtCiudad.getText();
        String codigo = pswcodigo.getText();
        String conCodigo = pswconfirmar.getText();

        if (nombre.equals("")) {
            txtnombre.setEstado(Estado.ERROR);
            msjNombre.setEstado(Estado.ERROR);
            msjNombre.setText("Campo vac\u00EDo");
            msjNombre.setVisible(true);
            cont = false;
        } else {
            txtnombre.setEstado(Estado.EXITO);
            msjNombre.setEstado(Estado.EXITO);
            msjNombre.setText("");
            msjNombre.setVisible(false);
        }

        if (apellido.equals("")) {
            txtapellido.setEstado(Estado.ERROR);
            msjApellido.setEstado(Estado.ERROR);
            msjApellido.setText("Campo vac\u00EDo");
            msjApellido.setVisible(true);
            cont = false;
        } else {
            txtapellido.setEstado(Estado.EXITO);
            msjApellido.setEstado(Estado.EXITO);
            msjApellido.setText("");
            msjApellido.setVisible(false);
        }

        try {
            double d = Double.parseDouble(cedula);
            txtcedula.setEstado(Estado.EXITO);
            msjCedula.setEstado(Estado.EXITO);
            msjCedula.setText("");
            msjCedula.setVisible(false);

            if (cedula.length() < 6 || cedula.length() > 13) {
                txtcedula.setEstado(Estado.ERROR);
                msjCedula.setEstado(Estado.ERROR);
                msjCedula.setText("C\u00E9dula invalida");
                msjCedula.setVisible(true);
                cont = false;
            }
        } catch (Exception e) {
            txtcedula.setEstado(Estado.ERROR);
            msjCedula.setEstado(Estado.ERROR);
            msjCedula.setText("Campo vac\u00EDo");
            msjCedula.setVisible(true);
            cont = false;
        }

        if (direccion.equals("")) {
            txtdireccion.setEstado(Estado.ERROR);
            msjDireccion.setEstado(Estado.ERROR);
            msjDireccion.setText("Campo vac\u00EDo");
            msjDireccion.setVisible(true);
            cont = false;
        } else {
            txtdireccion.setEstado(Estado.EXITO);
            msjDireccion.setEstado(Estado.EXITO);
            msjDireccion.setText("");
            msjDireccion.setVisible(false);
        }
        try {
            double d = Double.parseDouble(telefono);

            txttelefono.setEstado(Estado.EXITO);
            msjTelefono.setEstado(Estado.EXITO);
            msjTelefono.setText("");
            msjTelefono.setVisible(false);
        } catch (Exception e) {
            txttelefono.setEstado(Estado.ERROR);
            msjTelefono.setEstado(Estado.ERROR);
            msjTelefono.setText("S\u00F3lo numeros");
            msjTelefono.setVisible(true);
            cont = false;
        }

        if (correo.equals("")) {
            txtcorreo.setEstado(Estado.ERROR);
            msjCorreo.setEstado(Estado.ERROR);
            msjCorreo.setText("Campo vac\u00EDo");
            msjCorreo.setVisible(true);
            cont = false;
        } else {
            if (comprobarEmail(correo)) {
                txtcorreo.setEstado(Estado.EXITO);
                msjCorreo.setEstado(Estado.EXITO);
                msjCorreo.setText("");
                msjCorreo.setVisible(false);
            } else {
                txtcorreo.setEstado(Estado.ERROR);
                msjCorreo.setEstado(Estado.ERROR);
                msjCorreo.setText("E-mail invalido");
                msjCorreo.setVisible(true);
                cont = false;
            }

        }

        if (ciudad.equals("")) {
            txtCiudad.setEstado(Estado.ERROR);
            msjCiudad.setEstado(Estado.ERROR);
            msjCiudad.setText("Campo vac\u00EDo");
            msjCiudad.setVisible(true);
            cont = false;
        } else {
            txtCiudad.setEstado(Estado.EXITO);
            msjCiudad.setEstado(Estado.EXITO);
            msjCiudad.setText("");
            msjCiudad.setVisible(false);
        }

        if (codigo.length() < 4) {
            pswcodigo.setEstado(Estado.ERROR);
            msjCodigo.setEstado(Estado.ERROR);
            msjCodigo.setText("<html><body>La contrase\u00F1a debe ser m\u00E1s de 3 caracteres</body></html>");
            msjCodigo.setVisible(true);
            cont = false;
        } else {
            if (!(codigo.equals(conCodigo))) {
                pswcodigo.setEstado(Estado.ERROR);
                pswconfirmar.setEstado(Estado.ERROR);
                msjCodigo.setEstado(Estado.ERROR);
                msjCodigo.setText("<html><body>Las contrase\u00F1as no coinciden</body></html>");
                msjCodigo.setVisible(true);
                cont = false;
            } else {
                pswcodigo.setEstado(Estado.EXITO);
                pswconfirmar.setEstado(Estado.EXITO);

                msjCodigo.setEstado(Estado.EXITO);
                msjCodigo.setText("");
                msjCodigo.setVisible(false);
            }
        }

        return cont;
    }

    public boolean comprobarEmail(String email) {
        // Establecer el patron
        Pattern p = Pattern.compile("[-\\w\\.]+@\\w+\\.\\w+");

        // Asociar el string al patron
        Matcher m = p.matcher(email);

        // Comprobar si encaja
        return m.matches();
    }

    @SuppressWarnings("deprecation")
    private void crear() {
        String nombre = txtnombre.getText();
        String apellido = txtapellido.getText();
        String cedula = txtcedula.getText();
        String direccion = txtdireccion.getText();
        String telefono = txttelefono.getText();
        String correo = txtcorreo.getText();
        String codigo = pswcodigo.getText();
        String ciudad = txtCiudad.getText();

        ClienteE cce = new ClienteE();
        ClienteN cn = new ClienteN();

        cce.setApellido(apellido);
        cce.setCedula(cedula);
        cce.setCorreo(correo);
        cce.setDireccion(direccion);
        cce.setNombre(nombre);
        cce.setTelefono(telefono);
        cce.setCiudad(ciudad);

        //String s = cn.crearCliente(cce);
        String s = cn.crearClienteL(cce);

        if (s.equals("")) {
            UsuarioE cue = new UsuarioE();
            UsuarioN un = new UsuarioN();

            cue.setApellido(apellido);
            cue.setClienteCedula(cedula);
            cue.setCodigo(codigo);
            cue.setNombre(nombre);
            cue.setTipo("cliente");
            cue.setImagen(file);
            //s = un.crearUsuario(cue);
            s = un.crearUsuarioL(cue);
        }

        if (s.equals("")) {
            msjMensaje.setEstado(Estado.EXITO);
            msjMensaje.setText("El cliente ha sido creado exitosamente");
            msjMensaje.setVisible(true);
            visibleCliente(false, false, true, false);
            usua.setVisible(false);
            clie.setVisible(false);
        }

        if (s.equals("1")) {
            txtcedula.setEstado(Estado.ERROR);
            msjMensaje.setEstado(Estado.ERROR);
            msjMensaje.setText("El cliente ya existe en la base de datos");
            msjMensaje.setVisible(true);
            visibleCliente(true, true, false, false);
        }

        if (s.equals("2")) {
            msjMensaje.setEstado(Estado.ERROR);
            msjMensaje.setText("Error al conectarse a la base de datos");
            msjMensaje.setVisible(true);
            visibleCliente(true, true, false, false);
        }
    }

    public void seleccionarImagen() {
        JFileChooser jfc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("png", "png");
        jfc.setFileFilter(filter);
        int seleccion = jfc.showOpenDialog(Main.menu.frame);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            if (CABolsa.isImage(jfc.getSelectedFile())) {
                if (jfc.getSelectedFile().length() <= 1048576) {
                    this.file = jfc.getSelectedFile();
                    msjImagen.setText(this.file.getName());
                    btnQuitar.setVisible(true);
                    btnVer.setVisible(true);
                } else {
                    msjImagen.setText("La imagen supera el l\u00EDmite de 1MB");
                }
            } else {
                msjImagen.setText("Seleccione una imagen");
            }
        }

    }

    public void verImagen() {
        String s = file.getAbsolutePath();
        String h = s.substring(3, s.length());
        System.out.println(h);
        String v = "\"" + h + "\"";
        System.out.println(v);
        s = s.replace(h, v);
        System.out.println(s);
        try {
            Runtime.getRuntime().exec("cmd /c " + s);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void quitarImagen() {
        this.file = null;
        btnQuitar.setVisible(false);
        btnVer.setVisible(false);
        msjImagen.setText("M\u00E1ximo 1MB");
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        if (e.getSource() == btncrear) {
            Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());
            if (validar()) {
                crear();
            }
            Main.dialog.ocultar();
        }
        if (e.getSource() == btnNuevo) {
            visibleCliente(true, true, false, false);
            limpiarCampos();
            usua.setVisible(true);
            clie.setVisible(true);
        }

        if (e.getSource() == btnGuardar) {
            Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());
            if (validar()) {
                actualizar();
            }
            Main.dialog.ocultar();
        }

        if (e.getSource() == btnCancelar) {
            Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());
            if (aBuscar) {
                limpiarCampos();
                Main.esconderTodos();
                Main.buscarCliente.buscar();
                Main.buscarCliente.panel.setVisible(true);
            } else {
                Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());
                Main.removerTodos();
                Main.menu.frame.getContentPane().add(Main.ajustes.panel);
                Main.esconderTodos();
                Main.ajustes.panel.setVisible(true);
                Main.dialog.ocultar();
                Main.ajustes.msjMensaje.setText("");
            }
            Main.dialog.ocultar();
        }

        if (e.getSource() == btnSeleccionar) {
            seleccionarImagen();
        }

        if (e.getSource() == btnVer) {
            verImagen();
        }

        if (e.getSource() == btnQuitar) {
            quitarImagen();
        }

    }

    public void mouseReleased(MouseEvent e) {
    }
}
