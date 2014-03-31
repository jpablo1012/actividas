/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Entidades.EmpleadoE;
import Entidades.UsuarioE;
import Negocio.EmpleadoN;
import Negocio.UsuarioN;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SvlEmpleado extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String tipo = request.getParameter("tipo");
        if (tipo.equals("crear")) {
            crearEmpleado(request, response);
        }
        if (tipo.equals("buscar")) {
            buscarEmpleado(request, response);
        }
        if (tipo.equals("actualizar")) {
            actualizarEmpleado(request, response);
        }
        if (tipo.equals("eliminar")) {
            eliminarEmpleado(request, response);
        }
        if (tipo.equals("perfil")) {
            perfilEmpleado(request, response);
        }
    }

    public void crearEmpleado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String cedula = request.getParameter("cedula");
        String cargo = request.getParameter("cargo");
        String codigo = request.getParameter("codigo");

        EmpleadoE ee = new EmpleadoE();
        EmpleadoN en = new EmpleadoN();

        ee.setApellido(apellido);
        ee.setCargo(cargo);
        ee.setCedula(cedula);
        ee.setNombre(nombre);

        String s = en.crearEmpleado(ee);
        if (s.equals("")) {
            UsuarioE ue = new UsuarioE();
            UsuarioN un = new UsuarioN();

            ue.setApellido(apellido);
            ue.setEmpleadoCedula(cedula);
            ue.setCodigo(codigo);
            ue.setNombre(nombre);
            ue.setTipo(cargo);

            s = un.crearUsuario(ue);
        }
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.print(respuestaCrear(s));
        } finally {
            out.close();
        }
    }

    public void buscarEmpleado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String valor = request.getParameter("variable");
        String buscarCon = request.getParameter("buscarCon");
        String enviar = "";
        ArrayList<EmpleadoE> alee;
        EmpleadoN en = new EmpleadoN();
        String send = "{\"buscar\":[";

        UsuarioN un = new UsuarioN();

        alee = en.buscarEmpleado(buscarCon, valor, false);
        ArrayList<UsuarioE> alue = new UsuarioN().buscarUsuario("empleado_cedula", "", false, true);
        if (alee == null) {
            enviar = respuestaBuscar("El valor que usted busca no existe en la base de datos");
        } else {

            if (alee.size() == 0) {
                enviar = respuestaBuscar("El valor que usted busca no existe en la base de datos");
            } else {
                for (int i = 0; i < alee.size(); i++) {
                    for (int j = 0; j < alue.size(); j++) {
                        EmpleadoE ee = alee.get(i);
                        UsuarioE ue = alue.get(j);

                        if (ee.getCedula().equals(ue.getEmpleadoCedula())) {
                            if (i == (alee.size() - 1)) {
                                send += respuestaBuscar(ee, ue, "");
                            } else {
                                send += respuestaBuscar(ee, ue, "") + ",";
                            }
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
    }

    public void actualizarEmpleado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.print("lala");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String cedula = request.getParameter("cedula");
        String cargo = request.getParameter("cargo");
        String codigo = request.getParameter("codigo");
        String IdUsuario = request.getParameter("IdUsuario");

        String direccion = request.getParameter("direccionImagen");

        UsuarioE ue = new UsuarioE();
        UsuarioN un = new UsuarioN();

        ue.setApellido(apellido);
        ue.setEmpleadoCedula(cedula);
        ue.setCodigo(codigo);
        ue.setNombre(nombre);
        ue.setIdUsuario(IdUsuario);
        ue.setTipo(cargo);

        try {
           if(direccion.equals("")){
           }else{
            direccion = URLDecoder.decode(direccion, "UTF-8");
            System.out.println(direccion);
            File f = new File(direccion);
            ue.setImagen(f);
           }
        } catch (Exception e) {
        }

        String s = un.actualizarUsuario(ue);
        if (s.equals("")) {
            EmpleadoE me = new EmpleadoE();
            EmpleadoN mn = new EmpleadoN();

            me.setCedula(cedula);
            me.setApellido(apellido);
            me.setNombre(nombre);
            me.setCargo(cargo);

            s = mn.actualizarEmpleado(me);
        }
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.print(respuestaActualizar(s));
        } finally {
            out.close();
        }
    }

    public void eliminarEmpleado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.print("lala2");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String cedula = request.getParameter("cedula");
        String cargo = request.getParameter("cargo");
        String codigo = request.getParameter("codigo");
        String IdUsuario = request.getParameter("IdUsuario");

        UsuarioE ue = new UsuarioE();
        UsuarioN un = new UsuarioN();

        ue.setApellido(apellido);
        ue.setEmpleadoCedula(cedula);
        ue.setCodigo(codigo);
        ue.setNombre(nombre);
        ue.setTipo(cargo);
        ue.setIdUsuario(IdUsuario);

        String s = un.eliminarUsuario(ue.getIdUsuario());
        if (s.equals("")) {
            System.out.println("ggfgfhrh");
            EmpleadoN en = new EmpleadoN();
            ArrayList<EmpleadoE> ee = en.buscarEmpleado("cedula", cedula, true);

            s = en.eliminarEmpleado(ee.get(0).getCedula());

        }
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.print(respuestaEliminar(s));
        } finally {
            out.close();
        }
    }
    public void perfilEmpleado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String c = request.getParameter("cedu");
        ArrayList<EmpleadoE> ee;
        String send = "{\"buscar\":[";
        ee = new EmpleadoN().buscarEmpleado("cedula", c, false);
        ArrayList<UsuarioE> ue = new UsuarioN().buscarUsuario("empleado_cedula", c, false, true);
        for (int i = 0; i < ee.size(); i++) {
            if (i == (ee.size() - 1)) {
                send += respuestaBuscar(ee.get(0), ue.get(0), "");
            } else {
                send += respuestaBuscar(ee.get(0), ue.get(0), "") + ",";
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

    public String respuestaCrear(String s) {
        return "{\"mensaje\":\"" + s + "\"}";
    }

    public String respuestaBuscar(EmpleadoE a, UsuarioE ue, String s) {
        String lugar = "";
        try {
            lugar = URLEncoder.encode(ue.getImagen().getAbsolutePath(), "UTF-8");
        } catch (Exception e) {
        }
        String retorne = "{\"nombre\":\"" + a.getNombre() + "\"," +
                "\"apellido\":\"" + a.getApellido() + "\"," +
                "\"cedula\":\"" + a.getCedula() + "\"," +
                "\"cargo\":\"" + a.getCargo() + "\"," +
                "\"codigo\":\"" + ue.getCodigo() + "\"," +
                "\"IdUsuario\":\"" + ue.getIdUsuario() + "\",";
        try {
            retorne += "\"imagen\":\"" + ue.getImagen().getName() + "\",";
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

    public String respuestaEliminar(String s) {
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
    }// </editor-fold>
}
