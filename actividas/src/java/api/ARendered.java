package api;

import org.jfree.chart.renderer.category.BarRenderer;

import java.awt.Color;
import java.awt.Paint;

@SuppressWarnings("serial")
public class ARendered extends BarRenderer {
    private Paint[] colors;

    public ARendered() {
	this.colors = new Paint[] { Colores.FONDO_ROJO, new Color(66, 126, 236), Colores.BORDE_CAJATEXTO_EXITO};
    }

    public Paint getItemPaint(final int row, final int column) {
	// returns color for each column
	return (this.colors[column % this.colors.length]);
    }
}