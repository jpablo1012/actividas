package Gui;

import Entidades.EmpleadoE;
import Entidades.UsuarioE;
import Negocio.EmpleadoN;
import Negocio.UsuarioN;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import api.AButton;
import api.AComboBox;
import api.AContainer;
import api.ALabel;
import api.APanel;
import api.APassword;
import api.ATextField;
import api.Estado;

public class CrearEmpleado implements MouseListener {

    String style = "style='color:#D3362D;'";
    public APanel panel;
    AContainer emple;
    AContainer usua;
    String[] emp = {"-Seleccionar cargo-", "Administrador", "Extrusor", "Impresor", "Sellador"};
    AComboBox comCargo;
    ALabel lblCargo;
    ALabel lblCedula;
    ALabel lblNombre;
    ALabel lblClave;
    ALabel lblConfirmar;
    ALabel lblApellido;
    ALabel msjNombre;
    ALabel msjCedula;
    ALabel msjCodigo;
    ALabel msjApellido;
    ALabel msjMensaje;
    ALabel msjCargo;
    ALabel lblObligatorio;
    ATextField txtCedula;
    ATextField txtNombre;
    ATextField txtApellido;
    APassword pswClave;
    APassword pswConfirmar;
    AButton btnCrear;
    AButton btnNuevo;
    ALabel lblImagen;
    ALabel msjImagen;
    AButton btnSeleccionar;
    AButton btnVer;
    AButton btnQuitar;
    File file;

    public CrearEmpleado() {
        panel = new APanel(Main.x, 0, 750, 600);
        panel.setTitulo("Empleado| Crear");

        emple = new AContainer("Empleado");
        emple.setBounds(110, 90, 530, 150);
        panel.add(emple);

        usua = new AContainer("Usuario");
        usua.setBounds(110, 260, 530, 160);
        panel.add(usua);

        lblNombre = new ALabel("<html><body><b " + style + ">*</b>Nombre:</body></html>");
        lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNombre.setBounds(35, 30, 70, 24);
        emple.add(lblNombre);

        lblApellido = new ALabel("<html><body><b " + style + ">*</b>Apellido:</body></html>");
        lblApellido.setHorizontalAlignment(SwingConstants.RIGHT);
        lblApellido.setBounds(254, 30, 70, 24);
        emple.add(lblApellido);

        lblCedula = new ALabel("<html><body><b " + style + ">*</b>C\u00E9dula:</body></html>");
        lblCedula.setHorizontalAlignment(SwingConstants.RIGHT);
        lblCedula.setBounds(35, 85, 70, 24);
        emple.add(lblCedula);

        lblCargo = new ALabel("<html><body><b " + style + ">*</b>Cargo:</body></html>");
        lblCargo.setHorizontalAlignment(SwingConstants.RIGHT);
        lblCargo.setBounds(254, 85, 70, 24);
        emple.add(lblCargo);

        lblClave = new ALabel("<html><body><b " + style + ">*</b>Contrase\u00F1a:</body></html>");
        lblClave.setHorizontalAlignment(SwingConstants.RIGHT);
        lblClave.setBounds(15, 30, 80, 24);
        usua.add(lblClave);

        lblConfirmar = new ALabel("<html><body align='right'><b " + style + ">*</b>Confirmar contrase\u00F1a:</body></html>");
        lblConfirmar.setHorizontalAlignment(SwingConstants.RIGHT);
        lblConfirmar.setBounds(249, 20, 75, 36);
        usua.add(lblConfirmar);

        lblObligatorio = new ALabel("<html><body><b " + style + ">*</b>Campo obligatorio</body></html>");
        lblObligatorio.setBounds(315, 50, 120, 24);
        panel.add(lblObligatorio);

        msjNombre = new ALabel("");
        msjNombre.setBounds(117, 54, 120, 20);
        msjNombre.setVisible(false);
        emple.add(msjNombre);

        comCargo = new AComboBox(emp);
        comCargo.setBounds(336, 85, 120, 24);
        emple.add(comCargo);

        msjCedula = new ALabel("");
        msjCedula.setBounds(117, 109, 120, 20);
        msjCedula.setVisible(false);
        emple.add(msjCedula);

        msjCodigo = new ALabel("");
        msjCodigo.setHorizontalAlignment(SwingConstants.CENTER);
        msjCodigo.setBounds(117, 55, 339, 20);
        msjCodigo.setVisible(false);
        usua.add(msjCodigo);

        msjApellido = new ALabel("");
        msjApellido.setBounds(336, 54, 120, 20);
        msjApellido.setVisible(false);
        emple.add(msjApellido);

        msjCargo = new ALabel("");
        msjCargo.setBounds(336, 109, 182, 20);
        msjCargo.setVisible(false);
        emple.add(msjCargo);

        txtNombre = new ATextField();
        txtNombre.setBounds(117, 30, 120, 24);
        txtNombre.setPlaceHolder("Nombre");
        emple.add(txtNombre);

        txtApellido = new ATextField();
        txtApellido.setBounds(336, 30, 120, 24);
        txtApellido.setPlaceHolder("Apellido");
        emple.add(txtApellido);

        txtCedula = new ATextField();
        txtCedula.setBounds(117, 85, 120, 24);
        txtCedula.setPlaceHolder("C\u00E9dula");
        emple.add(txtCedula);

        pswClave = new APassword();
        pswClave.setBounds(117, 30, 120, 24);
        pswClave.setPlaceHolder("Contrase\u00F1a");
        usua.add(pswClave);

        pswConfirmar = new APassword();
        pswConfirmar.setBounds(336, 30, 120, 24);
        pswConfirmar.setPlaceHolder("Confirmar contrase\u00F1a");
        usua.add(pswConfirmar);

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

        msjMensaje = new ALabel("");
        msjMensaje.setHorizontalAlignment(SwingConstants.CENTER);
        msjMensaje.setBounds(0, 490, panel.getWidth(), 20);
        msjMensaje.setFont(new Font("Calibri", Font.PLAIN, 18));
        msjMensaje.setVisible(false);
        panel.add(msjMensaje);

        btnCrear = new AButton("Crear Empleado");
        btnCrear.setBounds(310, 440, 130, 30);
        btnCrear.addMouseListener(this);
        panel.add(btnCrear);

        btnNuevo = new AButton("Crear otro empleado");
        btnNuevo.setBounds(295, 270, 160, 60);
        btnNuevo.setVisible(false);
        btnNuevo.addMouseListener(this);
        panel.add(btnNuevo);

        file = null;

    }

    @SuppressWarnings({ "deprecation", "unused" })
    public void validar() {
        String cedula = txtCedula.getText();
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String cargo = (String) comCargo.getSelectedItem();
        String codigo = pswClave.getText();
        String confirmar = pswConfirmar.getText();
        boolean cont = true;

        if (cedula.equals("")) {
            txtCedula.setEstado(Estado.ERROR);
            msjCedula.setEstado(Estado.ERROR);
            msjCedula.setText("Campo vaci\u00F3");
            msjCedula.setVisible(true);
            cont = false;
        } else {
            try {
                double d = Double.parseDouble(cedula);
                txtCedula.setEstado(Estado.EXITO);

                if (cedula.length() < 6 || cedula.length() > 13) {
                    txtCedula.setEstado(Estado.ERROR);
                    msjCedula.setEstado(Estado.ERROR);
                    msjCedula.setText("C\u00E9dula invalida");
                    msjCedula.setVisible(true);
                    cont = false;
                }
            } catch (Exception e) {
                txtCedula.setEstado(Estado.ERROR);
                msjCedula.setEstado(Estado.ERROR);
                msjCedula.setText("S\u00F3lo n\u00FAmeros");
                msjCedula.setVisible(true);
                cont = false;
            }
        }

        if (nombre.equals("")) {
            txtNombre.setEstado(Estado.ERROR);
            msjNombre.setEstado(Estado.ERROR);
            msjNombre.setText("Campo vaci\u00F3");
            msjNombre.setVisible(true);
            cont = false;
        } else {
            txtNombre.setEstado(Estado.EXITO);
        }

        if (apellido.equals("")) {
            txtApellido.setEstado(Estado.ERROR);
            msjApellido.setEstado(Estado.ERROR);
            msjApellido.setText("Campo vaci\u00F3");
            msjApellido.setVisible(true);
            cont = false;
        } else {
            txtApellido.setEstado(Estado.EXITO);
        }

        if (cargo.equals(emp[0])) {
            msjCargo.setEstado(Estado.ERROR);
            msjCargo.setText("Seleccione un cargo");
            msjCargo.setVisible(true);
            comCargo.setEstado(Estado.ERROR);
            cont = false;
        } else {
            comCargo.setEstado(Estado.EXITO);
        }

        if (codigo.length() < 4) {
            pswClave.setEstado(Estado.ERROR);
            msjCodigo.setEstado(Estado.ERROR);
            msjCodigo.setText("<html><body>La contrase\u00F1a debe ser m\u00E1s de 3 caracteres</body></html>");
            msjCodigo.setVisible(true);
            cont = false;
        } else {
            if (!(codigo.equals(confirmar))) {
                pswClave.setEstado(Estado.ERROR);
                pswConfirmar.setEstado(Estado.ERROR);
                msjCodigo.setEstado(Estado.ERROR);
                msjCodigo.setText("<html><body>Las contrase\u00F1as no coinciden<body></html>");
                msjCodigo.setVisible(true);
                cont = false;
            } else {
                pswClave.setEstado(Estado.EXITO);
                pswConfirmar.setEstado(Estado.EXITO);
            }
        }

        if (cont) {
            crear();
        }
    }

    @SuppressWarnings("deprecation")
    public void crear() {
        String cedula = txtCedula.getText();
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String cargo = (String) comCargo.getSelectedItem();
        String codigo = pswClave.getText();

        cargo = cargo.toLowerCase();

        EmpleadoE ee = new EmpleadoE();
        ee.setCargo(cargo);
        ee.setCedula(cedula);
        ee.setNombre(nombre);
        ee.setApellido(apellido);

        UsuarioE ue = new UsuarioE();
        ue.setCodigo(codigo);
        ue.setEmpleadoCedula(cedula);
        ue.setNombre(nombre);
        ue.setApellido(apellido);
        ue.setTipo(cargo);
        ue.setImagen(file);

        EmpleadoN en = new EmpleadoN();
        UsuarioN un = new UsuarioN();

        //String s = en.crearEmpleado(ee);
        String s = en.crearEmpleadoL(ee);
        if (s.equals("")) {
            //s = un.crearUsuario(ue);
            s = un.crearUsuarioL(ue);
            if (s.equals("")) {

                msjMensaje.setEstado(Estado.EXITO);
                msjMensaje.setText("El empleado ha sido creado exitosamente");
                visible(false);
                msjMensaje.setVisible(true);
            } else {
                if (s.equals("1")) {//el usuario ya existe
                    txtCedula.setEstado(Estado.ERROR);
                    msjMensaje.setEstado(Estado.ERROR);
                    msjMensaje.setText("El empleado ya existe");
                    msjMensaje.setVisible(true);
                }
            }
        } else {
            if (s.equals("1")) {//el empleado ya existe
                txtCedula.setEstado(Estado.ERROR);
                msjMensaje.setEstado(Estado.ERROR);
                msjMensaje.setText("El empleado ya existe");
                msjMensaje.setVisible(true);
            }
            if (s.equals("2")) {//"Error al conectarse a la base de datos"
                msjMensaje.setEstado(Estado.ERROR);
                msjMensaje.setText("Error al conectarse a la base de datos");
                msjMensaje.setVisible(true);
            }

        }

    }

    public void visible(boolean b) {
        emple.setVisible(b);
        usua.setVisible(b);
        txtCedula.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        pswClave.setText("");
        pswConfirmar.setText("");

        comCargo.setSelectedIndex(0);

        txtCedula.setEstado(Estado.NORMAL);
        txtNombre.setEstado(Estado.NORMAL);
        txtApellido.setEstado(Estado.NORMAL);
        pswConfirmar.setEstado(Estado.NORMAL);
        pswClave.setEstado(Estado.NORMAL);
        comCargo.setEstado(Estado.NORMAL);

        msjNombre.setVisible(false);
        msjCedula.setVisible(false);
        msjCodigo.setVisible(false);
        msjApellido.setVisible(false);
        msjMensaje.setVisible(false);
        msjCargo.setVisible(false);

        lblObligatorio.setVisible(b);
        msjMensaje.setVisible(false);

        btnCrear.setVisible(b);
        btnNuevo.setVisible(!b);

        quitarImagen();
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

    public void mousePressed(MouseEvent e) {
        if (e.getSource() == btnCrear) {
            txtCedula.setEstado(Estado.NORMAL);
            txtNombre.setEstado(Estado.NORMAL);
            pswConfirmar.setEstado(Estado.NORMAL);
            pswClave.setEstado(Estado.NORMAL);
            comCargo.setEstado(Estado.NORMAL);
            msjNombre.setVisible(false);
            msjCedula.setVisible(false);
            msjCodigo.setVisible(false);
            msjApellido.setVisible(false);
            msjMensaje.setVisible(false);
            msjCargo.setVisible(false);
            Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());
            validar();
            Main.dialog.ocultar();
        }
        if (e.getSource() == btnNuevo) {
            visible(true);
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

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }
}
