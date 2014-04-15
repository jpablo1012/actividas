/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Entidades.MaterialE;
import Entidades.ColorE;
import Entidades.BolsaE;
import Negocio.MaterialN;
import Negocio.ColorN;
import Negocio.BolsaN;
import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
    
/**
 *
 * @author Usuario
 */
public class SvlInsumos extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String tipo = request.getParameter("tipo");
        String respuesta = "";

        if (tipo.equals("crearma")) {
            crearMaterial(request, response);
        }

        if (tipo.equals("crearco")) {
            crearColor(request, response);
        }

        if (tipo.equals("crearbo")) {
            respuesta = crearBolsa(request, response);
        }

        if (tipo.equals("llenarma")) {
            llenarma(request, response);
        }

        if (tipo.equals("llenarco")) {
            llenarco(request, response);
        }

        if (tipo.equals("buscarc")) {
            buscarColor(request, response);
        }

        if (tipo.equals("buscarm")) {
            buscarMaterial(request, response);
        }

        if (tipo.equals("buscarb")) {
            buscarBolsa(request, response);
        }

        if (tipo.equals("actualizarc")) {
            actualizarColor(request, response);
        }

        if (tipo.equals("actualizarma")) {
            actualizarMaterial(request, response);
        }

    }

    public void crearMaterial(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        double cantidadm;
        String nombre = request.getParameter("nombre");
        String cantidad = request.getParameter("cantidad");
        cantidadm = Double.parseDouble(cantidad);

        MaterialE me = new MaterialE();
        MaterialN mn = new MaterialN();

        me.setNombre(nombre);
        me.setCantidad(cantidadm);

        String s = mn.crearMaterial(me);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.print(respuestaCrear(s));
        } finally {
            out.close();
        }
    }

    public void crearColor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        double cantidadm;
        String nombre = request.getParameter("nombre");
        String cantidad = request.getParameter("cantidad");
        cantidadm = Double.parseDouble(cantidad);

        ColorE ce = new ColorE();
        ColorN cn = new ColorN();

        ce.setNombre(nombre);
        ce.setCantidad(cantidadm);

        String s = cn.crearColor(ce);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.print(respuestaCrear(s));
        } finally {
            out.close();
        }
    }

    public String crearBolsa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        double calibrem = 0, ancho_Rollo = 0, largo = 0, transpA = 0, transpD = 0, fuelleI = 0, fuelleS = 0, fuelleL = 0, solapa = 0;
        int referencia_Material = 0, referencia_Color = 0;
        String tranA = request.getParameter("transparenciaA");
        String tranD = request.getParameter("transparenciaD");
        String fuI = request.getParameter("fuelleI");
        String fuS = request.getParameter("fuelleS");
        String fuL = request.getParameter("fuelleL");
        String sol = request.getParameter("solapa");
        String troquel = request.getParameter("troquel");
        String calibre = request.getParameter("calibre");
        String cedulaa = request.getParameter("cedula");
        calibre = calibre.replaceAll(",", ".");
        String medida = request.getParameter("medida");
        String largol = request.getParameter("largo");
        largol = largol.replaceAll(",", ".");
        String anchoRollo = request.getParameter("anchoRollo");
        anchoRollo = anchoRollo.replaceAll(",", ".");
        String referenciaMaterial = request.getParameter("referenciaMaterial");
        String referenciaColor = request.getParameter("referenciaColor");
        String tratado = request.getParameter("tratado");

        try {
            tranA = tranA.replaceAll(",", ".");
            transpA = Double.parseDouble(tranA);
        } catch (Exception e) {
        }

        try {
            tranD = tranD.replaceAll(",", ".");
            transpD = Double.parseDouble(tranD);
        } catch (Exception e) {
        }

        try {
            fuI = fuI.replaceAll(",", ".");
            fuelleI = Double.parseDouble(fuI);
        } catch (Exception e) {
        }

        try {
            fuL = fuL.replaceAll(",", ".");
            fuelleL = Double.parseDouble(fuL);
        } catch (Exception e) {
        }

        try {
            fuS = fuS.replaceAll(",", ".");
            fuelleS = Double.parseDouble(fuS);
        } catch (Exception e) {
        }

        try {
            sol = sol.replaceAll(",", ".");
            solapa = Double.parseDouble(sol);
        } catch (Exception e) {
        }

        try {
            calibrem = Double.parseDouble(calibre);
        } catch (Exception e) {
        }

        try {
            largo = Double.parseDouble(largol);
        } catch (Exception e) {
        }

        try {
            ancho_Rollo = Double.parseDouble(anchoRollo);
        } catch (Exception e) {
        }

        try {
            referencia_Material = Integer.parseInt(referenciaMaterial);
        } catch (Exception e) {
        }

        try {
            referencia_Color = Integer.parseInt(referenciaColor);
        } catch (Exception e) {
        }





        BolsaE be = new BolsaE();
        BolsaN bn = new BolsaN();

        be.setCalibre(calibrem);
        be.setFuelle_fondo(fuelleI);
        be.setFuelle_lateral(fuelleL);
        be.setFuelle_superior(fuelleS);
        be.setTroquel(troquel);
        be.setSolapa(solapa);
        be.setTransAbajo(transpD);
        be.setTransArriba(transpA);
        be.setCliente_cedula(cedulaa);
        be.setAncho(ancho_Rollo);
        be.setLargo(largo);
        be.setMedida(medida);
        be.setMaterial_referencia(referencia_Material);
        be.setColor_codigo(referencia_Color);
        be.setTratado(Boolean.parseBoolean(tratado));
        // be.setImagen_file(filecontent);
        String nombre = request.getParameter("nombre");
        System.out.print("nombre: " + nombre);
        try {
            if (nombre.equals("")) {
            } else {
                nombre = URLDecoder.decode(nombre, "UTF-8");
                File f = new File("C://Users//" + System.getProperty("user.name") + "//AppData//archivo", nombre);
                be.setImagen(f);
            }
        } catch (Exception e) {
        }
        String s = bn.crearBolsa(be);
        System.out.println("Resultado :" + s);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            //out.print(respuestaCrear(s));
        } finally {
            out.close();
        }
        return s;
    }

    public void llenarma(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //      String referencia_Material = request.getParameter("referencia_Material");
        ArrayList<MaterialE> me = new MaterialN().buscarMaterial("referencia", "", false);
        // ArrayList<ColorE> ce = new ColorN().buscarColor("referencia", "", false);
        String send = "{\"buscar\":[";
        for (int i = 0; i < me.size(); i++) {
            if (i == (me.size() - 1)) {
                send += respuestaLlenarma(me.get(i), "");
            } else {
                send += respuestaLlenarma(me.get(i), "") + ",";
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

    public void llenarco(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //      String referencia_Material = request.getParameter("referencia_Material");
        //      ArrayList<MaterialE> me = new MaterialN().buscarMaterial("referencia", "", false);
        ArrayList<ColorE> ce = new ColorN().buscarColor("codigo", "", false);
        String send = "{\"buscar\":[";
        for (int i = 0; i < ce.size(); i++) {
            System.out.print(ce);
            if (i == (ce.size() - 1)) {
                send += respuestaLlenarco(ce.get(i), "");
            } else {
                send += respuestaLlenarco(ce.get(i), "") + ",";
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

    public void buscarColor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String valor = request.getParameter("variable");
        String buscarCon = request.getParameter("buscarCon");
        String enviar = "";
        ArrayList<ColorE> ce;
        ColorN cn = new ColorN();
        String send = "{\"buscar\":[";

        ce = cn.buscarColor(buscarCon, valor, false);
        if (ce == null) {
            enviar = respuestaBuscar("El valor que usted busca no existe en la base de datos");
        } else {

            if (ce.size() == 0) {
                enviar = respuestaBuscar("El valor que usted busca no existe en la base de datos");
            } else {
                for (int i = 0; i < ce.size(); i++) {
                    if (i == (ce.size() - 1)) {
                        send += respuestaBuscarco(ce.get(i), "");
                    } else {
                        send += respuestaBuscarco(ce.get(i), "") + ",";
                    }
                }
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

    public void buscarMaterial(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String valor = request.getParameter("variable");
        String buscarCon = request.getParameter("buscarCon");
        String enviar = "";
        ArrayList<MaterialE> me;
        MaterialN mn = new MaterialN();
        String send = "{\"buscar\":[";

        me = mn.buscarMaterial(buscarCon, valor, false);
        if (me == null) {
            enviar = respuestaBuscar("El valor que usted busca no existe en la base de datos");
        } else {

            if (me.size() == 0) {
                enviar = respuestaBuscar("El valor que usted busca no existe en la base de datos");
            } else {
                for (int i = 0; i < me.size(); i++) {
                    if (i == (me.size() - 1)) {
                        send += respuestaBuscarma(me.get(i), "");
                    } else {
                        send += respuestaBuscarma(me.get(i), "") + ",";
                    }
                }
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

    public void buscarBolsa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String valor = request.getParameter("variable");
        String buscarCon = request.getParameter("buscarCon");
        String enviar = "";
        ArrayList<BolsaE> be;
        BolsaN bn = new BolsaN();
        String send = "{\"buscar\":[";

        be = bn.buscarBolsa(buscarCon, valor, false);
        if (be == null) {
            enviar = respuestaBuscar("El valor que usted busca no existe en la base de datos");
        } else {

            if (be.size() == 0) {
                enviar = respuestaBuscar("El valor que usted busca no existe en la base de datos");
            } else {
                for (int i = 0; i < be.size(); i++) {
                    if (i == (be.size() - 1)) {
                        send += respuestaBuscarbo(be.get(i), "");
                    } else {
                        send += respuestaBuscarbo(be.get(i), "") + ",";
                    }
                }
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
    
    public void actualizarColor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String nombre = request.getParameter("nombre");
        String cantidadc = request.getParameter("cantidad");
        String idc = request.getParameter("id");
        Double cantidad = Double.parseDouble(cantidadc);
        int id = Integer.parseInt(idc);

        ColorE ce = new ColorE();
        ColorN cn = new ColorN();

        ce.setCantidad(cantidad);
        ce.setCodigo(id);
        ce.setNombre(nombre);

        String s = cn.actualizarColor(ce);
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.print(respuestaActualizar(s));
        } finally {
            out.close();
        }
    }
    
    public void actualizarMaterial(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String nombre = request.getParameter("nombre");
        String cantidadc = request.getParameter("cantidad");
        String idc = request.getParameter("id");
        Double cantidad = Double.parseDouble(cantidadc);
        int id = Integer.parseInt(idc);

        MaterialE me = new MaterialE();
        MaterialN mn = new MaterialN();

        me.setCantidad(cantidad);
        me.setReferencia(id);
        me.setNombre(nombre);

        String s = mn.actualizarMaterial(me);
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.print(respuestaActualizar(s));
        } finally {
            out.close();
        }
    }

    public String respuestaCrear(String s) {
        return "{\"mensaje\":\"" + s + "\"}";
    }

    public String respuestaLlenarma(MaterialE m, String s) {
        return "{\"id\":\"" + m.getReferencia() + "\"," +
                "\"nombre\":\"" + m.getNombre() + "\"}";
    }

    public String respuestaLlenarco(ColorE m, String s) {
        return "{\"id\":\"" + m.getCodigo() + "\"," +
                "\"nombre\":\"" + m.getNombre() + "\"}";
    }

    public String respuestaBuscarco(ColorE ce, String s) {
        return "{\"id\":\"" + ce.getCodigo() + "\"," +
                "\"nombre\":\"" + ce.getNombre() + "\"," +
                "\"cantidad\":\"" + ce.getCantidad() + "\"}";
    }

    public String respuestaBuscarma(MaterialE me, String s) {
        return "{\"id\":\"" + me.getReferencia() + "\"," +
                "\"nombre\":\"" + me.getNombre() + "\"," +
                "\"cantidad\":\"" + me.getCantidad() + "\"}";
    }

    public String respuestaBuscarbo(BolsaE be, String s) { String lugar = "";
        try {
            lugar = URLEncoder.encode(be.getImagen().getAbsolutePath(), "UTF-8");
        } catch (Exception e) {
        }
        String retorne = "{\"cedula\":\"" + be.getCliente_cedula() + "\"," +
                "\"medida\":\"" + be.getMedida() + "\"," +
                "\"troquel\":\"" + be.getTroquel()+ "\"," +
                "\"tratado\":\"" + be.isTratado()+ "\"," +
                "\"ancho\":\"" + be.getAncho() + "\"," +
                "\"calibre\":\"" + be.getCalibre() + "\"," +
                "\"codcolor\":\"" + be.getColor_codigo() + "\"," +
                "\"fuelle_fondo\":\"" + be.getFuelle_fondo() + "\"," +
                "\"fuelle_lateral\":\"" + be.getFuelle_lateral() + "\"," +
                "\"fuelle_superior\":\"" + be.getFuelle_superior() + "\"," +
                "\"largo\":\"" + be.getLargo() + "\"," +
                "\"refmaterial\":\"" + be.getMaterial_referencia() + "\"," +
                "\"id\":\"" + be.getReferencia() + "\"," +
                "\"solapa\":\"" + be.getSolapa() + "\"," +
                "\"transabajo\":\"" + be.getTransAbajo() + "\"," +
                "\"transarriba\":\"" + be.getTransArriba() + "\",";
        try {
            retorne += "\"imagen\":\"" + be.getImagen().getName() + "\"," ;
            retorne += "\"direccionimg\":\"" + lugar + "\",";
        } catch (Exception e) {
        }
            retorne += "\"mensaje\":\"" + s + "\"}";

            return retorne;
    }

    public String respuestaBuscar(String s) {
        return "{\"mensaje\":\"" + s + "\"}";
    }
    
    public String respuestaActualizar(String s) {
        return "{\"mensaje\":\"" + s + "\"}";
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
        processRequest(request, response);
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
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>// 
}
