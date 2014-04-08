package api;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.JTableHeader;

@SuppressWarnings("serial")
public class ATable extends JTable {
	JTableHeader jth;
    public ATable() {
        setShowVerticalLines(false);
        setBorder(new MatteBorder(0, 1, 1, 1, Colores.BORDE_CAJATEXTO));
        setRowHeight(25);
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setFont(new Font("Calibri", Font.PLAIN, 14));
        setBackground(Colores.TABLA_FONDO);
        setForeground(Colores.TABLA_TEXTO);
        setGridColor(Colores.TABLA_BARRASEPARACION);
        setSelectionBackground(Colores.TABLA_FONDO_SELECCIONADO);
        setSelectionForeground(Colores.TABLA_TEXTO_SELECCIONADO);

        jth = getTableHeader();
        jth.setFont(new Font("Calibri", Font.PLAIN, 14));
        jth.setBackground(Colores.TABLA_FONDO);
        jth.setOpaque(false);
        jth.setForeground(Colores.TITULO_NORMAL);
        jth.setVisible(true);
        jth.setAutoscrolls(true);     
        jth.setPreferredSize(new Dimension(30, 30));
        jth.setReorderingAllowed(false);//draggable las columnas
        jth.setBorder(new CompoundBorder(new MatteBorder(1, 1, 1, 1, Colores.TABLA_FONDO), new MatteBorder(0, 0, 2, 0, Colores.FONDO_ROJO)));
        setTableHeader(jth);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int vColIndex) {
        return false;
    }
}

