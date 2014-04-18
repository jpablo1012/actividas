package Controlador;

import Entidades.ExtrusionE;
import Entidades.ImpresionE;
import Entidades.OrdenE;
import Entidades.SelladoE;
import Negocio.SelladoN;
import Negocio.Auxiliar;
import Negocio.ExtrusionN;
import Negocio.ImpresionN;
import Negocio.OrdenN;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author juandiego
 */
public class SvlOrden extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String tipo = request.getParameter("tipo");
        
        if (tipo.equals("buscar")) {
            buscarOrden(request, response);
        }

        if (tipo.equals("extrusion")) {
            buscarExtrusionA(request, response);
        }

        if (tipo.equals("guardarE") || tipo.equals("fin")) {
            actualizarOrdenE(request, response);
        }
        
        if (tipo.equals("impresion")) {
            buscarImpresionA(request, response);
        }
        
        if (tipo.equals("sellado")) {
            buscarSelladoA(request, response);
        }
        
        if (tipo.equals("guardarI") || tipo.equals("finI")) {
            actualizarOrdenI(request, response);
        }
        
        if (tipo.equals("guardarS") || tipo.equals("finS")) {
            actualizarOrdenS(request, response);
        }

    }
    
    public void buscarOrden(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pista = request.getParameter("pista");
        String valor = request.getParameter("valor");
        String tipo = request.getParameter("tipo");

        ArrayList<OrdenE> mo = new OrdenN().buscarOrden("estado", "extrusi\u00F3n", false);

        String send = "{\"buscar\":[";
        for (int i = 0; i < mo.size(); i++) {
            if (i == (mo.size() - 1)) {
                send += respuestaBuscarO(mo.get(i), "");
            } else {
                send += respuestaBuscarO(mo.get(i), "") + ",";
            }
        }
        send += "]}";
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.print(send);
        } finally {
            out.close();
        }
    }

    public void buscarExtrusionA(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pista = request.getParameter("pista");
        String valor = request.getParameter("valor");
        String tipo = request.getParameter("tipo");

        ArrayList<ExtrusionE> me = new ExtrusionN().buscarExtrusion("estado", "ejecutand", false);
        ArrayList<OrdenE> mo = new OrdenN().buscarOrden("estado", "extrusi\u00F3n", false);

        String send = "{\"buscar\":[";
        for (int i = 0; i < me.size(); i++) {
            if (i == (me.size() - 1)) {
                send += respuestaBuscar(me.get(i), mo.get(i), "");
            } else {
                send += respuestaBuscar(me.get(i), mo.get(i), "") + ",";
            }
        }
        send += "]}";
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.print(send);
        } finally {
            out.close();
        }
    }
    
    public void buscarImpresionA(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ArrayList<ImpresionE> mi = new ImpresionN().buscarImpresion("estado", "ejecutand", false);
        ArrayList<OrdenE> mo = new OrdenN().buscarOrden("estado", "impresi\u00F3n", false);

        String send = "{\"buscar\":[";
        for (int i = 0; i < mi.size(); i++) {
            if (i == (mi.size() - 1)) {
                send += respuestaBuscarI(mi.get(i), mo.get(i), "");
            } else {
                send += respuestaBuscarI(mi.get(i), mo.get(i), "") + ",";
            }
        }
        send += "]}";
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.print(send);
        } finally {
            out.close();
        }
    }
    
    public void buscarSelladoA(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ArrayList<SelladoE> ms = new SelladoN().buscarSellado("estado", "ejecutand", false);
        ArrayList<OrdenE> mo = new OrdenN().buscarOrden("estado", "sellado", false);

        String send = "{\"buscar\":[";
        for (int i = 0; i < ms.size(); i++) {
            if (i == (ms.size() - 1)) {
                send += respuestaBuscarS(ms.get(i), mo.get(i), "");
            } else {
                send += respuestaBuscarS(ms.get(i), mo.get(i), "") + ",";
            }
        }
        send += "]}";
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.print(send);
        } finally {
            out.close();
        }
    }
    
    public void actualizarOrdenI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String s;
        String cedula = request.getParameter("cedulaIm");
        String tipo = request.getParameter("tipo");
        String numeroIm = request.getParameter("numero");
        String numeroOrdenI = request.getParameter("numeroOr");
        String pInicial = request.getParameter("pInicialI");
        String pFinal = request.getParameter("pFinalI");
        String notasIm = request.getParameter("notas");
        String impresionI = request.getParameter("impresion");
        System.out.println(tipo);
        double pesoInicial = 0;
        double pesoFinal = 0;

        try {
            pesoInicial = Double.parseDouble(pInicial);
        } catch (Exception e) {
        }

        try {
            pesoFinal = Double.parseDouble(pFinal);
        } catch (Exception e) {
        }

        ImpresionN in = new ImpresionN();
        OrdenN on = new OrdenN();
        System.out.println(numeroIm);
        ArrayList<ImpresionE> alee = in.buscarImpresion("codigo", numeroIm, true);
        ImpresionE uen = alee.get(0);
        uen.setPesoInicial(pesoInicial);
        uen.setPesoFinal(pesoFinal);
        uen.setDiferencia(pesoFinal-pesoInicial);
        uen.setImpresion(impresionI);
        uen.setEmpleado_cedula(cedula);
        uen.setNota(notasIm);

        if (tipo.equals("guardarI")) {
            s = in.actualizarImpresion(uen);
            s="Los avances han sido guardados";

        }else{
            in.actualizarImpresion(uen);
            System.out.println(numeroOrdenI);
            ArrayList<OrdenE> aloe = on.buscarOrden("numeroOrden", numeroOrdenI, true);
            OrdenE oen = aloe.get(0);
            s = Auxiliar.pasarA(oen);
            s="Impresión finalizada";
        }

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.print(s);
        } finally {
            out.close();
        }
    }

    public void actualizarOrdenE(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String s;
        String cedula = request.getParameter("cedulaEx");;
        String tipo = request.getParameter("tipo");
        String numeroEx = request.getParameter("numero");
        String numeroOrden = request.getParameter("numeroOr");
        String rollosEx = request.getParameter("rollos");
        String desperdicioEx = request.getParameter("desperdicio");
        String maquinaEx = request.getParameter("maquina");
        String notasEx = request.getParameter("notas");
        System.out.println(numeroEx);
        System.out.println(tipo);
        double rollos = 0;
        double desperdicio = 0;

        try {
            rollos = Double.parseDouble(rollosEx);
        } catch (Exception e) {
        }

        try {
            desperdicio = Double.parseDouble(desperdicioEx);
        } catch (Exception e) {
        }

        ExtrusionN un = new ExtrusionN();
        OrdenN on = new OrdenN();

        ArrayList<ExtrusionE> alee = un.buscarExtrusion("codigo", numeroEx, true);
        ExtrusionE uen = alee.get(0);
        uen.setRollo(rollos);
        uen.setDesperdicio(desperdicio);
        uen.setMaquina(maquinaEx);
        uen.setEmpleado_cedula(cedula);
        uen.setNota(notasEx);

        if (tipo.equals("guardarE")) {
            s = un.actualizarExtrusion(uen);
            s="Los avances han sido guardados";

        }else{
            un.actualizarExtrusion(uen);
            ArrayList<OrdenE> aloe = on.buscarOrden("numeroOrden", numeroOrden, true);
            OrdenE oen = aloe.get(0);
            s = Auxiliar.pasarA(oen);
            s="Extrusión finalizada";
        }

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.print(s);
        } finally {
            out.close();
        }
    }
    
    public void actualizarOrdenS(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String s;
        String cedula = request.getParameter("cedulaS");
        String tipo = request.getParameter("tipo");
        String numeroSe = request.getParameter("numero");
        String numeroOrden = request.getParameter("numeroOr");
        String retalSe = request.getParameter("retal");
        String bolsasSe = request.getParameter("bolsasSella");
        String notasSe = request.getParameter("notas");
        System.out.println(tipo);
        double retal = 0;
        double selladas = 0;

        try {
            retal = Double.parseDouble(retalSe);
        } catch (Exception e) {
        }

        try {
            selladas = Double.parseDouble(bolsasSe);
        } catch (Exception e) {
        }

        SelladoN un = new SelladoN();
        OrdenN on = new OrdenN();

        ArrayList<SelladoE> alee = un.buscarSellado("codigo", numeroSe, true);
        SelladoE uen = alee.get(0);
        uen.setRetal(retal);
        uen.setBolsasSelladas(selladas);
        uen.setNota(notasSe);
        uen.setEmpleado_cedula(cedula);

        if (tipo.equals("guardarS")) {
            s = un.actualizarSellado(uen);
            s="Los avances han sido guardados";

        }else{
            un.actualizarSellado(uen);
            ArrayList<OrdenE> aloe = on.buscarOrden("numeroOrden", numeroOrden, true);
            OrdenE oen = aloe.get(0);
            s = Auxiliar.pasarA(oen);
            s="Orden finalizada";
        }

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.print(s);
        } finally {
            out.close();
        }
    }

    public String respuestaBuscar(ExtrusionE m, OrdenE o, String s) {
        return "{\"nOrden\":\"" + o.getNumeroOrden() + "\","
                + "\"Bref\":\"" + m.getBolsa_referencia() + "\","
                + "\"nota\":\"" + m.getNota()+ "\","
                + "\"maquina\":\"" + m.getMaquina()+ "\","
                + "\"desperdicio\":\"" + m.getDesperdicio()+ "\","
                + "\"rollo\":\"" + m.getRollo()+ "\","
                + "\"nExtrusion\":\"" + m.getCodigo() + "\","
                + "\"Tcantidad\":\"" + o.getTipo_cantidad() + "\","
                + "\"cantidad\":\"" + o.getCantidad() + "\","
                + "\"referenciaP\":\"" + o.getNumeroPedido() + "\","
                + "\"fechaIni\":\"" + o.getFecha_inicio() + "\","
                + "\"fechaPlazo\":\"" + o.getFecha_plazo() + "\","
                + "\"extrusion\":\"" + o.isExtrusion() + "\","
                + "\"impresion\":\"" + o.isImpresion() + "\","
                + "\"sellado\":\"" + o.isSellado() + "\","
                + "\"estado\":\"" + m.getEstado() + "\"}";

    }
    
    public String respuestaBuscarI(ImpresionE im, OrdenE o, String s) {
        return "{\"nOrden\":\"" + o.getNumeroOrden() + "\","
                + "\"Bref\":\"" + im.getBolsa_referencia() + "\","
                + "\"nota\":\"" + im.getNota()+ "\","
                + "\"impresionI\":\"" + im.getImpresion()+ "\","
                + "\"pAntes\":\"" + im.getPesoInicial()+ "\","
                + "\"pFinal\":\"" + im.getPesoFinal()+ "\","
                + "\"nImpresion\":\"" + im.getCodigo() + "\","
                + "\"Tcantidad\":\"" + o.getTipo_cantidad() + "\","
                + "\"cantidad\":\"" + o.getCantidad() + "\","
                + "\"fechaIni\":\"" + o.getFecha_inicio() + "\","
                + "\"fechaPlazo\":\"" + o.getFecha_plazo() + "\","
                + "\"estado\":\"" + im.getEstado() + "\"}";

    }
    
    public String respuestaBuscarS(SelladoE im, OrdenE o, String s) {
        return "{\"nOrden\":\"" + o.getNumeroOrden() + "\","
                + "\"Bref\":\"" + im.getBolsa_referencia() + "\","
                + "\"nota\":\"" + im.getNota()+ "\","
                + "\"nSellado\":\"" + im.getCodigo() + "\","
                + "\"Tcantidad\":\"" + o.getTipo_cantidad() + "\","
                + "\"cantidad\":\"" + o.getCantidad() + "\","
                + "\"fechaIni\":\"" + o.getFecha_inicio() + "\","
                + "\"fechaPlazo\":\"" + o.getFecha_plazo() + "\","
                + "\"bolsasSelladas\":\"" + im.getBolsasSelladas() + "\","
                + "\"retal\":\"" + im.getRetal() + "\","
                + "\"estado\":\"" + im.getEstado() + "\"}";

    }
    
    public String respuestaBuscarO(OrdenE o, String s) {
        return "{\"nOrden\":\"" + o.getNumeroOrden() + "\","
                + "\"nPedido\":\"" + o.getNumeroPedido() + "\","
                + "\"bReferencia\":\"" + o.getBolsa_referencia()+ "\","
                + "\"estado\":\"" + o.getEstado()+ "\","
                + "\"extrusion\":\"" + o.isExtrusion()+ "\","
                + "\"impresion\":\"" + o.isImpresion() + "\","
                + "\"sellado\":\"" + o.isSellado()+ "\","
                + "\"Tcantidad\":\"" + o.getTipo_cantidad() + "\","
                + "\"cantidad\":\"" + o.getCantidad() + "\","
                + "\"fechaIni\":\"" + o.getFecha_inicio() + "\","
                + "\"fechaPlazo\":\"" + o.getFecha_plazo() + "\"}";


    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
