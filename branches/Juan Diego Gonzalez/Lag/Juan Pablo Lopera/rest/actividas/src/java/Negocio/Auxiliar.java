package Negocio;

import java.util.ArrayList;
import java.util.Date;

import Entidades.ExtrusionE;
import Entidades.ImpresionE;
import Entidades.OrdenE;
import Entidades.PedidoE;
import Entidades.SelladoE;

public class Auxiliar {

    private static PedidoN pn;
    private static OrdenN on;
    private static ExtrusionN en;
    private static ImpresionN in;
    private static SelladoN sn;

    public static String aprobarPedido(PedidoE pe) {

	pn = new PedidoN();
	int empieza = rechazado;

	boolean extrusionb = pe.isExtrusion(), impresionb = pe.isImpresion(), selladob = pe.isSellado();

	if (selladob) {
	    empieza = sellado;
	}

	if (impresionb) {
	    empieza = impresion;
	}

	if (extrusionb) {
	    empieza = extrusion;
	}

	if (empieza == rechazado || pe.getEstado().equals("rechazado")) {
	    pe.setEstado("rechazado");

	    return pn.actualizarPedido(pe);
	} else {
	    OrdenE oe = new OrdenE();

	    oe.setBolsa_referencia(pe.getBolsa_referencia());
	    oe.setNumeroPedido(pe.getNumeroPedido());
	    oe.setCantidad(pe.getCantidad());
	    oe.setTipo_cantidad(pe.getTipo_cantidad());
	    oe.setExtrusion(pe.isExtrusion());
	    oe.setImpresion(pe.isImpresion());
	    oe.setSellado(pe.isSellado());
	    oe.setReferencia(pe.getReferencia());
	    oe.setFecha_plazo(pe.getFecha_entrega());
	    oe.setEstado("");

	    on = new OrdenN();

	    String s = on.crearOrden(oe);

	    if (s.equals("")) {
		ArrayList<OrdenE> aloe = on.buscarOrden("numeroPedido", oe.getNumeroPedido() + "", true);

		if (aloe == null) {
		    return "1";
		} else if (aloe.size() == 0) {
		    return "1";
		} else {
		    oe = aloe.get(0);

		    if (empieza == extrusion) {
			ExtrusionE ee = new ExtrusionE();

			ee.setNumeroOrden(oe.getNumeroOrden());
			ee.setBolsa_referencia(oe.getBolsa_referencia());
			ee.setEstado("ejecutando");

			pe.setEstado("extrusi\u00F3n");

			s = pn.actualizarPedido(pe);

			if (s.equals("")) {

			    oe.setEstado("extrusi\u00F3n");

			    s = on.actualizarOrden(oe);

			    if (s.equals("")) {
				en = new ExtrusionN();

				s = en.crearExtrusion(ee);
			    } else {
				pe.setEstado("pendiente");

				s = pn.actualizarPedido(pe);

				return "1";
			    }

			    return s;
			} else {
			    on.eliminarOrden(oe);
			    return s;
			}
		    } else if (empieza == impresion) {
			ImpresionE ie = new ImpresionE();

			ie.setNumeroOrden(oe.getNumeroOrden());
			ie.setBolsa_referencia(oe.getBolsa_referencia());
			ie.setEstado("ejecutando");
			ie.setImpresion(oe.getReferencia());
			pe.setEstado("impresi\u00F3n");

			s = pn.actualizarPedido(pe);

			if (s.equals("")) {

			    oe.setEstado("impresi\u00F3n");

			    s = on.actualizarOrden(oe);

			    if (s.equals("")) {
				in = new ImpresionN();

				s = in.crearImpresion(ie);
			    } else {
				pe.setEstado("pendiente");

				s = pn.actualizarPedido(pe);
			    }

			    return s;
			} else {
			    on.eliminarOrden(oe);
			    return s;
			}
		    } else if (empieza == sellado) {
			SelladoE se = new SelladoE();

			se.setNumeroOrden(oe.getNumeroOrden());
			se.setBolsa_referencia(oe.getBolsa_referencia());
			se.setEstado("ejecutando");
			pe.setEstado("sellado");

			s = pn.actualizarPedido(pe);

			if (s.equals("")) {

			    oe.setEstado("sellado");

			    s = on.actualizarOrden(oe);

			    if (s.equals("")) {
				sn = new SelladoN();

				s = sn.crearSellado(se);
			    } else {
				pe.setEstado("pendiente");

				s = pn.actualizarPedido(pe);
			    }

			    return s;

			} else {
			    on.eliminarOrden(oe);
			}
		    } else {
			return "1";
		    }

		}
	    } else {
		return s;
	    }
	}

	return "1";
    }

    public static String pasarA(PedidoE pe) {

	on = new OrdenN();
	ArrayList<OrdenE> aloe = on.buscarOrden("numeroPedido", pe.getNumeroPedido() + "", true);

	if (aloe == null) {
	    return "1";
	} else {
	    if (aloe.size() == 0) {
		return "1";
	    } else {
		OrdenE oe = aloe.get(0);

		return pasarA(pe, oe);
	    }
	}

    }

    public static String pasarA(OrdenE oe) {

	pn = new PedidoN();
	ArrayList<PedidoE> alpe = pn.buscarPedido("numeroPedido", oe.getNumeroPedido() + "", true);

	if (alpe == null) {
	    return "1";
	} else {
	    if (alpe.size() == 0) {
		return "1";
	    } else {
		PedidoE pe = alpe.get(0);

		return pasarA(pe, oe);
	    }
	}

    }

    public static String pasarA(PedidoE pe, OrdenE oe) {

	String estadop = pe.getEstado();
	String estadoo = oe.getEstado();

	pn = new PedidoN();
	on = new OrdenN();
	en = new ExtrusionN();
	in = new ImpresionN();
	sn = new SelladoN();

	if (estadop.equals(estadoo)) {
	    if (estadop.equals("extrusi\u00F3n")) {
		en = new ExtrusionN();
		ArrayList<ExtrusionE> alee = en.buscarExtrusion("numeroOrden", oe.getNumeroOrden() + "", true);
		ExtrusionE eea = alee.get(0);
		eea.setEstado("finalizado");
		en.actualizarExtrusion(eea);

		if (pe.isImpresion()) {
		    ImpresionE ie = new ImpresionE();

		    ie.setNumeroOrden(oe.getNumeroOrden());
		    ie.setBolsa_referencia(oe.getBolsa_referencia());
		    ie.setEstado("ejecutando");
		    ie.setImpresion(oe.getReferencia());
		    pe.setEstado("impresi\u00F3n");

		    String s = pn.actualizarPedido(pe);

		    if (s.equals("")) {

			oe.setEstado("impresi\u00F3n");

			s = on.actualizarOrden(oe);

			if (s.equals("")) {
			    in = new ImpresionN();

			    s = in.crearImpresion(ie);

			    if (!s.equals("")) {
				pe.setEstado("extrusi\u00F3n");
				pn.actualizarPedido(pe);

				oe.setEstado("extrusi\u00F3n");
				on.actualizarOrden(oe);

				return "1";
			    } else {
				return "" + impresion;
			    }
			}
			return s;
		    }
		} else if (pe.isSellado()) {
		    SelladoE se = new SelladoE();

		    se.setNumeroOrden(oe.getNumeroOrden());
		    se.setBolsa_referencia(oe.getBolsa_referencia());
		    se.setEstado("ejecutando");

		    pe.setEstado("sellado");

		    String s = pn.actualizarPedido(pe);

		    if (s.equals("")) {

			oe.setEstado("sellado");

			s = on.actualizarOrden(oe);

			if (s.equals("")) {
			    sn = new SelladoN();

			    s = sn.crearSellado(se);

			    if (!s.equals("")) {
				pe.setEstado("extrusi\u00F3n");
				pn.actualizarPedido(pe);

				oe.setEstado("extrusi\u00F3n");
				on.actualizarOrden(oe);

				return "1";
			    } else {
				return "" + sellado;
			    }
			}

			return s;

		    }
		} else {
		    pe.setEstado("finalizado");
		    pn.actualizarPedido(pe);

		    oe.setEstado("finalizado");
		    oe.setFecha_fin(new Date());
		    on.actualizarOrden(oe);

		    return "" + finalizado;
		}
	    }

	    if (estadop.equals("impresi\u00F3n")) {
		ImpresionN in = new ImpresionN();
		ArrayList<ImpresionE> alie = in.buscarImpresion("numeroOrden", oe.getNumeroOrden() + "", true);
		ImpresionE iea = alie.get(0);
		iea.setEstado("finalizado");
		in.actualizarImpresion(iea);
		if (pe.isSellado()) {
		    SelladoE se = new SelladoE();

		    se.setNumeroOrden(oe.getNumeroOrden());
		    se.setBolsa_referencia(oe.getBolsa_referencia());
		    se.setEstado("ejecutando");

		    pe.setEstado("sellado");

		    String s = pn.actualizarPedido(pe);

		    if (s.equals("")) {

			oe.setEstado("sellado");

			s = on.actualizarOrden(oe);

			if (s.equals("")) {
			    sn = new SelladoN();

			    s = sn.crearSellado(se);

			    if (!s.equals("")) {
				pe.setEstado("impresi\u00F3n");
				pn.actualizarPedido(pe);

				oe.setEstado("impresi\u00F3n");
				on.actualizarOrden(oe);

				return "1";
			    } else {
				return "" + sellado;
			    }
			}

			return s;

		    }
		} else {
		    pe.setEstado("finalizado");
		    pn.actualizarPedido(pe);

		    oe.setEstado("finalizado");
		    oe.setFecha_fin(new Date());
		    on.actualizarOrden(oe);

		    return "" + finalizado;
		}
	    }

	    if (estadop.equals("sellado")) {
		pe.setEstado("finalizado");
		pn.actualizarPedido(pe);

		oe.setEstado("finalizado");
		oe.setFecha_fin(new Date());
		on.actualizarOrden(oe);

		ArrayList<SelladoE> alse = sn.buscarSellado("numeroOrden", oe.getNumeroOrden() + "", true);
		SelladoE se = alse.get(0);
		se.setEstado("finalizado");
		sn.actualizarSellado(se);

		return "" + finalizado;
	    }
	} else {
	    return "1";
	}

	return "1";
    }

    public static final int rechazado = -1;
    public static final int extrusion = 0;
    public static final int impresion = 3;
    public static final int sellado = 4;
    public static final int finalizado = 5;
    public static final int listo = 6;
}
