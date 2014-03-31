/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;
import Entidades.BolsaE;
import Entidades.PedidoE;
import Negocio.Auxiliar;
import Negocio.BolsaN;
import Negocio.PedidoN;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level; 
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author USUARIO
 */
public class SvlPedido extends HttpServlet {
   

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException, ParseException {

        String tipo = request.getParameter("tipo");
        
        if(tipo.equals("getPedidos")){
            System.out.print("entró a get pedidos");
           buscarPedidoA(request,response);
        }

        if(tipo.equals("buscar")){
           buscarPedido(request,response);
        }

        if(tipo.equals("crear")){
           crearPedido(request,response);
        }

        
        if (tipo.equals("llenarrf")) {
            llenarrf(request, response);
        }

        if (tipo.equals("aprobar")) {
            actualizarPedido(request, response);
        }
    }


    public void crearPedido(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException{

        String fechaE = request.getParameter("fechaEntrega");
        Date fecha = new Date(Long.parseLong(fechaE));
        String cedula = request.getParameter("cedula");
        String cantidads = request.getParameter("cantidad");
        String medidaC = request.getParameter("medidac");
        String tipoV = request.getParameter("tipoVenta");
        String refB = request.getParameter("referenciaBolsa");
        String refetencia = request.getParameter("referencia");
        String impresion = request.getParameter("impresion");
        String extrusion = request.getParameter("extrusion");
        String sellado = request.getParameter("sellado");
        double cantidad;
        cantidad = Double.parseDouble(cantidads);
        int bolsa_referencia;
        bolsa_referencia = Integer.parseInt(refB);

        PedidoE pe=new PedidoE();
        PedidoN pn=new PedidoN();
        
        pe.setCliente_cedula(cedula);
        pe.setFecha_entrega(fecha);
        pe.setBolsa_referencia(bolsa_referencia);
        pe.setCantidad(cantidad);
        pe.setTipo_cantidad(medidaC);
        pe.setTipo_venta(tipoV);
        pe.setReferencia(refetencia);
        pe.setImpresion(Boolean.parseBoolean(impresion));
        pe.setExtrusion(Boolean.parseBoolean(extrusion));
        pe.setSellado(Boolean.parseBoolean(sellado));
        
        String s = pn.crearPedido(pe);

        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.print(respuestaCrear(s));
        } finally {
            out.close();
        }
    }

    public String respuestaCrear(String s) {
        return "{\"mensaje\":\"" + s + "\"}";
    }

    public void llenarrf(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //      String referencia_Material = request.getParameter("referencia_Material");
        ArrayList<BolsaE> me = new BolsaN().buscarBolsa("referencia", "", false);
        // ArrayList<ColorE> ce = new ColorN().buscarColor("referencia", "", false);
        String send = "{\"buscar\":[";
        for (int i = 0; i < me.size(); i++) {
            if (i == (me.size()- 1)) {
                send += respuestaLlenarrF(me.get(i), "");
            } else {
                send += respuestaLlenarrF(me.get(i), "") + ",";
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

    public void buscarPedido(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pista = request.getParameter("pista");
        String valor = request.getParameter("valor");
        System.out.print(pista+" otro "+valor);
        ArrayList<PedidoE> me = new PedidoN().buscarPedido(valor, pista, false);
        System.out.print("busqueda realizada");
        String send = "{\"buscar\":[";
        for (int i = 0; i < me.size(); i++) {
            if (i == (me.size() - 1)) {
                send += respuestaBuscar(me.get(i), "");
            } else {
                send += respuestaBuscar(me.get(i), "") + ",";
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

    public void buscarPedidoA(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pista = request.getParameter("pista");
        String valor = request.getParameter("valor");
        System.out.print(pista+" k "+valor);
        ArrayList<PedidoE> me = new PedidoN().buscarPedido(valor, pista, false);
        System.out.print("busqueda realizada");
        String send = "{\"buscar\":[";
        for (int i = 0; i < me.size(); i++) {
            if (i == (me.size() - 1)) {
                send += respuestaBuscar(me.get(i), "");
            } else {
                send += respuestaBuscar(me.get(i), "") + ",";
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

    public void actualizarPedido(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String numeroPed = request.getParameter("numeroPedido");
        String estado = request.getParameter("estado");
        System.out.println(numeroPed);

        PedidoN un = new PedidoN();

        ArrayList<PedidoE> alpe = un.buscarPedido("numeroPedido", numeroPed, true);
        PedidoE uen = alpe.get(0);
        uen.setEstado(estado);

        String s = Auxiliar.aprobarPedido(uen);
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.print(s);
        } finally {
            out.close();
        }
    }

    public String respuestaLlenarrF(BolsaE m, String s) {
        return "{\"id\":\"" + m.getReferencia() + "\"," +
                "\"ancho\":\"" + m.getAncho() + "\"," +
                "\"largo\":\"" + m.getLargo()+ "\"}";
    }

    public String respuestaBuscar(PedidoE m, String s) {

        return "{\"cedula\":\"" + m.getCliente_cedula() + "\"," +
               "\"Fentrega\":\"" + m.getFecha_entrega() + "\"," +
               "\"Fcreacion\":\"" + m.getFecha_creacion() + "\"," +
               "\"Tventa\":\"" + m.getTipo_venta() + "\"," +
                "\"refereciaB\":\"" + m.getReferencia() + "\"," +
                "\"Tcantidad\":\"" + m.getTipo_cantidad() + "\"," +
                "\"cantidad\":\"" + m.getCantidad() + "\"," +
                "\"referenciaP\":\"" + m.getNumeroPedido() + "\"," +
                "\"precio\":\"" + m.getPrecio() + "\"," +
                "\"extrusion\":\"" + m.isExtrusion() + "\"," +
                "\"impresion\":\"" + m.isImpresion() + "\"," +
                 "\"sellado\":\"" + m.isSellado() + "\"," +
                "\"estado\":\"" + m.getEstado() + "\"}";

    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(SvlPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(SvlPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
