/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Entidades.ClienteE; 
import Entidades.UsuarioE;
import Negocio.ClienteN;
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

/**
 *
 * @author Usuario
 */
public class SvlCliente extends HttpServlet {

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
            crearCliente(request, response);
        }
        if (tipo.equals("buscar")) {
            buscarCliente(request, response);
        }
        if (tipo.equals("actualizar")) {
            actualizarCliente(request, response);
        }
        if (tipo.equals("eliminar")) {
            eliminarCliente(request, response);
        }
        if (tipo.equals("perfil")) {
            perfilCliente(request, response);
        }
    }

    public void crearCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String cedula = request.getParameter("cedula");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String codigo = request.getParameter("codigo");
        String direccion = request.getParameter("direccion");
        String telefono = request.getParameter("telefono");
        String email = request.getParameter("email");
        String ciudad = request.getParameter("ciudad");

        ClienteE ce = new ClienteE();
        ClienteN cn = new ClienteN();

        ce.setApellido(apellido);
        ce.setCedula(cedula);
        ce.setCorreo(email);
        ce.setDireccion(direccion);
        ce.setNombre(nombre);
        ce.setTelefono(telefono);
        ce.setCiudad(ciudad);

        String s = cn.crearCliente(ce);
        if (s.equals("")) {
            UsuarioE ue = new UsuarioE();
            UsuarioN un = new UsuarioN();

            ue.setApellido(apellido);
            ue.setClienteCedula(cedula);
            ue.setCodigo(codigo);
            ue.setNombre(nombre);
            ue.setTipo("cliente");

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

    public void buscarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String valor = request.getParameter("variable");
        String buscar = request.getParameter("buscar");
        String enviar = "";
        ArrayList<ClienteE> alce;
        ClienteN cn = new ClienteN();
        String send = "{\"buscar\":[";

        UsuarioN un = new UsuarioN();
        ArrayList<UsuarioE> alue = un.buscarUsuario("cliente_cedula", "", false, true);

        alce = cn.buscarCliente(buscar, valor, false);
        if (alce == null) {
            enviar = respuestaBuscar("El valor que usted busca no existe en la base de datos");
        } else {
            if (alce.size() == 0) {
                enviar = respuestaBuscar("El valor que usted busca no existe en la base de datos");
            } else {
                for (int i = 0; i < alce.size(); i++) {
                    for (int j = 0; j < alue.size(); j++) {
                        ClienteE ce = alce.get(i);
                        UsuarioE ue = alue.get(j);

                        if (ce.getCedula().equals(ue.getClienteCedula())) {
                            if (i == (alce.size() - 1)) {
                                send += respuestaBuscar(ce, ue, "");
                            } else {
                                send += respuestaBuscar(ce, ue, "") + ",";
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

    public void actualizarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String cedula = request.getParameter("cedula");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String codigo = request.getParameter("codigo");
        String direccion = request.getParameter("direccion");
        String telefono = request.getParameter("telefono");
        String email = request.getParameter("email");
        String ciudad = request.getParameter("ciudad");
        String IdUsuario = request.getParameter("IdUsuario");

        String direccionI = request.getParameter("direccionImagen");

        UsuarioE ue = new UsuarioE();
        UsuarioN un = new UsuarioN();
        ue.setApellido(apellido);
        ue.setClienteCedula(cedula);
        ue.setCodigo(codigo);
        ue.setNombre(nombre);
        ue.setIdUsuario(IdUsuario);
        ue.setTipo("cliente");

        try {
           if(direccionI.equals("")){
           }else{
            direccionI = URLDecoder.decode(direccionI, "UTF-8");
            System.out.println(direccionI);
            File f = new File(direccionI);
            ue.setImagen(f);
           }
        } catch (Exception e) {
        }

        String s = un.actualizarUsuario(ue);

        if (s.equals("")) {
            ClienteE me = new ClienteE();
            ClienteN mn = new ClienteN();

            me.setCedula(cedula);
            me.setApellido(apellido);
            me.setCorreo(email);
            me.setDireccion(direccion);
            me.setNombre(nombre);
            me.setTelefono(telefono);
            me.setCiudad(ciudad);

            s = mn.actualizarCliente(me);
        }
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.print(respuestaActualizar(s));
        } finally {
            out.close();
        }
    }

    public void eliminarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String cedula = request.getParameter("cedula");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String codigo = request.getParameter("codigo");
        String direccion = request.getParameter("direccion");
        String telefono = request.getParameter("telefono");
        String email = request.getParameter("email");
        String ciudad = request.getParameter("ciudad");
        String IdUsuario = request.getParameter("IdUsuario");

        UsuarioE ue = new UsuarioE();
        UsuarioN un = new UsuarioN();

        ue.setApellido(apellido);
        ue.setEmpleadoCedula(cedula);
        ue.setCodigo(codigo);
        ue.setNombre(nombre);
        ue.setTipo("cliente");
        ue.setIdUsuario(IdUsuario);

        String s = un.eliminarUsuario(ue.getIdUsuario());
        if (s.equals("")) {
            System.out.println("ggfgfhrh");
            ClienteN cn = new ClienteN();
            ArrayList<ClienteE> ce = cn.buscarCliente("cedula", cedula, true);

            s = cn.eliminarCliente(ce.get(0).getCedula());

        }
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.print(respuestaEliminar(s));
        } finally {
            out.close();
        }
    }

    public void perfilCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String c = request.getParameter("cedu");
        ArrayList<ClienteE> ce;
        String send = "{\"buscar\":[";
        ce = new ClienteN().buscarCliente("cedula", c, false);
        ArrayList<UsuarioE> ue = new UsuarioN().buscarUsuario("cliente_cedula", c, false, true);
        for (int i = 0; i < ce.size(); i++) {
            if (i == (ce.size() - 1)) {
                send += respuestaBuscar(ce.get(0), ue.get(0), "");
            } else {
                send += respuestaBuscar(ce.get(0), ue.get(0), "") + ",";
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

    public String respuestaBuscar(ClienteE a, UsuarioE ue, String s) {
        String lugar = "";
        try {
            lugar = URLEncoder.encode(ue.getImagen().getAbsolutePath(), "UTF-8");
        } catch (Exception e) {
        }
        String retorne = "{\"nombre\":\"" + a.getNombre() + "\"," +
                "\"apellido\":\"" + a.getApellido() + "\"," +
                "\"cedula\":\"" + a.getCedula() + "\"," +
                "\"direccion\":\"" + a.getDireccion() + "\"," +
                "\"telefono\":\"" + a.getTelefono() + "\"," +
                "\"correo\":\"" + a.getCorreo() + "\"," +
                "\"codigo\":\"" + ue.getCodigo() + "\"," +
                "\"ciudad\":\"" + a.getCiudad() + "\"," +
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

//    public String respuestaPerfil(ClienteE a, UsuarioE ue) {
//        return "{\"nombre\":\"" + a.getNombre() + "\"," +
//                "\"apellido\":\"" + a.getApellido() + "\"," +
//                "\"cedula\":\"" + a.getCedula() + "\"," +
//                "\"direccion\":\"" + a.getDireccion() + "\"," +
//                "\"telefono\":\"" + a.getTelefono() + "\"," +
//                "\"correo\":\"" + a.getCorreo() + "\"," +
//                "\"codigo\":\"" + ue.getCodigo() + "\"," +
//                "\"ciudad\":\"" + a.getCiudad() + "\"," +
//                "\"IdUsuario\":\"" + ue.getIdUsuario() + "\"}";
//    }

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
