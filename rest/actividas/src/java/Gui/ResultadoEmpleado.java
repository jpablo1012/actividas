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

public class ResultadoEmpleado implements MouseListener {

    public APanel panel;
    AContainer emple;
    AContainer usua;
    String style = "style='color:#D3362D;'";
    String[] emp = {"-Seleccionar cargo-", "Administrador", "Extrusor", "Impresor", "Sellador"};
    ALabel msjMensaje;
    ALabel lblCargo;
    ALabel lblCedula;
    ALabel lblNombre;
    ALabel lblClave;
    ALabel lblConfirmar;
    ALabel lblApellido;
    ALabel lblObligatorio;
    AComboBox comCargo;
    ALabel msjNombre;
    ALabel msjCedula;
    ALabel msjCodigo;
    ALabel msjApellido;
    ALabel msjCargo;
    ATextField txtCedula;
    ATextField txtNombre;
    ATextField txtApellido;
    APassword pswClave;
    APassword pswConfirmar;
    AButton btnGuardar;
    AButton btnCancelar;
    UsuarioE ue;
    EmpleadoE ee;
    private boolean aBuscar = true;

    ALabel lblImagen;
    ALabel msjImagen;
    AButton btnSeleccionar;
    AButton btnVer;
    AButton btnQuitar;

    File file;

    public ResultadoEmpleado() {

        panel = new APanel(Main.x, 0, 750, 600);
        panel.setTitulo("Empleado| Actualizar");

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

        lblConfirmar = new ALabel("<html><body align='right'><b " + style + ">*</b>Confirmar Contrase\u00F1a:</body></html>");
        lblConfirmar.setHorizontalAlignment(SwingConstants.RIGHT);
        lblConfirmar.setBounds(250, 20, 74, 36);
        usua.add(lblConfirmar);

        lblObligatorio = new ALabel("<html><body><b " + style + ">*</b>Campo obligatorio</body></html>");
        lblObligatorio.setBounds(315, 20, 120, 24);
        panel.add(lblObligatorio);

        msjNombre = new ALabel("");
        msjNombre.setBounds(117, 54, 120, 20);
        emple.add(msjNombre);

        comCargo = new AComboBox(emp);
        comCargo.setBounds(336, 85, 120, 24);
        emple.add(comCargo);

        msjCedula = new ALabel("");
        msjCedula.setBounds(117, 109, 120, 20);
        emple.add(msjCedula);

        msjCodigo = new ALabel("");
        msjCodigo.setVerticalAlignment(SwingConstants.TOP);
        msjCodigo.setBounds(117, 55, 339, 20);
        usua.add(msjCodigo);

        msjApellido = new ALabel("");
        msjApellido.setBounds(336, 54, 120, 20);
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
        txtCedula.setEnabled(false);
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
        msjMensaje.setBounds(0, 490, 750, 24);
        msjMensaje.setFont(new Font("Calibri", Font.PLAIN, 18));
        panel.add(msjMensaje);

        btnGuardar = new AButton("Guardar cambios");
        btnGuardar.setBounds(235, 440, 130, 30);
        btnGuardar.addMouseListener(this);
        panel.add(btnGuardar);

        btnCancelar = new AButton("Cancelar cambios");
        btnCancelar.setBounds(385, 440, 130, 30);
        btnCancelar.addMouseListener(this);
        panel.add(btnCancelar);

        file = null;

        //Main.dialog = new ADialog("Cargando...", Main.menu.frame.getLocation(), Main.menu.frame.getSize());
    }

    public void borrarCampos() {
        comCargo.setSelectedIndex(0);
        txtNombre.setText("");
        txtApellido.setText("");
        txtCedula.setText("");
        pswClave.setText("");
        pswConfirmar.setText("");
    }

    public void setDatos(UsuarioE ue, EmpleadoE ee) {
        this.ue = ue;
        this.ee = ee;

        if (this.ee.getCargo().equals("administrador")) {
            comCargo.setSelectedIndex(1);
        }
        if (this.ee.getCargo().equals("extrusor")) {
            comCargo.setSelectedIndex(2);
        }
        if (this.ee.getCargo().equals("impresor")) {
            comCargo.setSelectedIndex(3);
        }
        if (this.ee.getCargo().equals("sellador")) {
            comCargo.setSelectedIndex(4);
        }

        txtNombre.setText(this.ee.getNombre());
        txtApellido.setText(this.ee.getApellido());
        txtCedula.setText(this.ee.getCedula());
        pswClave.setText(this.ue.getCodigo());
        pswConfirmar.setText(this.ue.getCodigo());

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

    public void regresoAbuscar(boolean b) {
        this.aBuscar = b;
    }

    @SuppressWarnings({ "deprecation", "unused" })
    private void actualizar() {
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

        if (cargo.equals((String) comCargo.getItemAt(0))) {
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
                msjCodigo.setText("<html><body>Las contrase\u00F1a no coinciden<body></html>");
                msjCodigo.setVisible(true);
                cont = false;
            } else {
                pswClave.setEstado(Estado.EXITO);
                pswConfirmar.setEstado(Estado.EXITO);
            }
        }

        if (cont) {
            cargo = cargo.toLowerCase();

            EmpleadoE ee2 = new EmpleadoE();
            EmpleadoN en2 = new EmpleadoN();

            ee2.setApellido(apellido);
            ee2.setCargo(cargo);
            ee2.setCedula(cedula);
            ee2.setNombre(nombre);

            String s = en2.actualizarEmpleado(ee2);
            if (s.equals("")) {
                UsuarioE ue2 = new UsuarioE();
                UsuarioN un2 = new UsuarioN();

                ue2.setApellido(apellido);
                ue2.setClienteCedula(null);
                ue2.setCodigo(codigo);
                ue2.setEmpleadoCedula(cedula);
                ue2.setIdUsuario(this.ue.getIdUsuario());
                ue2.setNombre(nombre);
                ue2.setTipo(cargo);
                ue2.setImagen(file);
                s = un2.actualizarUsuario(ue2);

                if (s.equals("")) {
                    if (aBuscar) {
                        Main.buscarEmpleado.buscar();
                        Main.esconderTodos();
                        Main.buscarEmpleado.panel.setVisible(true);
                        Main.buscarEmpleado.msjMensaje.setText("El empleado ha sido modificado exitosamente");
                        Main.buscarEmpleado.msjMensaje.setEstado(Estado.EXITO);
                        Main.buscarEmpleado.msjMensaje.setVisible(true);
                    } else {
                        Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());
                        Main.removerTodos();
                        Main.menu.frame.getContentPane().add(Main.ajustes.panel);
                        Main.esconderTodos();
                        Main.ajustes.panel.setVisible(true);
                        Main.ajustes.msjMensaje.setText("Te has modificado exitosamente");
                        Main.ajustes.msjMensaje.setEstado(Estado.EXITO);
                        Main.ajustes.msjMensaje.setVisible(true);
                    }

                }
            }

            if (s.equals("1")) {
                msjMensaje.setText("Oh, ha ocurrido un error y no sabemos que es. :C");
                msjMensaje.setEstado(Estado.ERROR);
                msjMensaje.setVisible(true);
            }

            if (s.equals("2")) {
                msjMensaje.setText("Error al conectarse a la base de datos");
                msjMensaje.setEstado(Estado.ERROR);
                msjMensaje.setVisible(true);
            }
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

    public void mousePressed(MouseEvent e) {

        if (e.getSource() == btnGuardar) {
            Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());
            actualizar();
            Main.dialog.ocultar();
        }

        if (e.getSource() == btnCancelar) {
            Main.dialog.mostrar(Main.menu.frame.getLocation(), Main.menu.frame.getSize());
            if (aBuscar) {
                Main.esconderTodos();
                Main.buscarEmpleado.buscar();
                Main.buscarEmpleado.panel.setVisible(true);

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

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }
}
