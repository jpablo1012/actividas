package api;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.JTableHeader;

public class ATable extends JTable {
	JTableHeader jth;
    public ATable() {
        setShowVerticalLines(false);
        setBorder(new MatteBorder(0, 1, 1, 1, Colores.borde_cajaTexto));
        setRowHeight(25);
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setFont(new Font("Calibri", Font.PLAIN, 14));
        setBackground(Colores.t_fondo);
        setForeground(Colores.t_texto);
        setGridColor(Colores.t_barra_separacion);
        setSelectionBackground(Colores.t_fondo_seleccionado);
        setSelectionForeground(Colores.t_texto_seleccionado);
        //setAutoResizeMode(JTable.AUTO_RESIZE_OFF);


        jth = getTableHeader();
        jth.setFont(new Font("Calibri", Font.PLAIN, 14));
        jth.setBackground(Colores.t_fondo);
        jth.setOpaque(false);
        jth.setForeground(Colores.titulo_normal);
        jth.setVisible(true);
        jth.setAutoscrolls(true);     
        jth.setPreferredSize(new Dimension(30, 30));
        jth.setReorderingAllowed(false);//draggable las columnas
        jth.setBorder(new CompoundBorder(new MatteBorder(1, 1, 1, 1, Colores.t_fondo), new MatteBorder(0, 0, 2, 0, Colores.fondo_rojo)));
        setTableHeader(jth);

        //setDragEnabled(false);
        //setRowMargin(0);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int vColIndex) {
        return false;
    }
}

