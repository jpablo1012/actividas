package api;

/*package api;

import Entidades.EmpleadoE;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.SwingConstants;

import Entidades.UsuarioE;


public class AResultado extends JPanel{

    public AButton btnActualizar;
    public AButton btnEliminar;
    public AButton btnInfo;
    private UsuarioE ue;
    private EmpleadoE ee;
    public AResultado(UsuarioE ue, EmpleadoE ee){
        this.ue = ue;
        this.ee = ee;
        setSize(530,120);
        setLayout(null);
        setBorder(new TitledBorder(new MatteBorder(1, 0, 1, 0, Colores.borde_container), this.ee.getNombre() + " " + this.ee.getApellido(), TitledBorder.CENTER, TitledBorder.TOP, new Font("Calibri",Font.PLAIN, 18), Colores.titulo_hover));
        setForeground(Colores.texto_normal);
        setBackground(Colores.fondo_normal);
        setOpaque(true);

        btnActualizar = new AButton("Actualizar");
        btnActualizar.setBounds(197, 72, 120, 25);
        this.add(btnActualizar);

        btnEliminar = new AButton("Eliminar");
        btnEliminar.setBounds(337, 72, 120, 25);
        this.add(btnEliminar);

        ALabel lbl1 = new ALabel("Cargo");
        lbl1.setHorizontalAlignment(SwingConstants.RIGHT);
        lbl1.setBounds(39, 28, 75, 24);
        this.add(lbl1);

        ATextField txt1 = new ATextField();
        txt1.setBounds(134, 28, 120, 24);
        txt1.setText(this.ee.getCargo());
        txt1.setEditable(false);
        this.add(txt1);

        ALabel lbl2 = new ALabel("Código");
        lbl2.setHorizontalAlignment(SwingConstants.RIGHT);
        lbl2.setBounds(274, 28, 75, 24);
        this.add(lbl2);

        APassword txt2 = new APassword();
        txt2.setBounds(369, 28, 120, 24);
        txt2.setText(this.ue.getCodigo());
        txt2.setEditable(false);
        this.add(txt2);
        
        btnInfo = new AButton("Más información");
        btnInfo.setBounds(57,72,120,25);
        this.add(btnInfo);
    }
    
    public UsuarioE getUsuario(){
        return this.ue;
    }

    public EmpleadoE getEmpleado(){
        return this.ee;
    }


}*/
