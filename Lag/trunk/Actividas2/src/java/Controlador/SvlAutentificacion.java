/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Entidades.UsuarioE;
import Negocio.UsuarioN;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Usuario
 */

public class SvlAutentificacion extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //variables cedula y codigo con el valor respectivo de la peticion de la pagina web
        String cedula = request.getParameter("cedula");
        String codigo = request.getParameter("codigo");

        response.setContentType("text/html;charset=UTF-8");//establece el tipo de respuesta
        PrintWriter out = response.getWriter();

        System.out.println(cedula);
        System.out.println(codigo);

        //response.sendRedirect("/Actividas/faces/welcomeJSF.jsp");
        UsuarioN en = new UsuarioN();
        UsuarioE e = new UsuarioE();
        e = en.validarIngreso(cedula);//envia cedula al metodo "validarIngreso" de la clase EmpleadoN
        try {
            //envia la respuesta a la pagina web con el valor retornado de validarIngreso
            String restd = validarIngreso(e, codigo);
            
            if (restd.equals("0")) {
                request.getSession().setAttribute("name", e.getNombre());
                request.getSession().setAttribute("sesione", "yes");
                request.getSession().setAttribute("type", "mna.jspf");
                request.setAttribute("cedula", "aeae");
                request.setAttribute("codigo", "aeae");
                RequestDispatcher dispatcher =  getServletConfig().getServletContext().getRequestDispatcher("/private/Test1.jsp");
                dispatcher.forward(request, response);
            }else{
                request.getSession().setAttribute("sesione", "none");
                getServletConfig().getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            }
            
        } finally {
            out.close();//finaliza la respuesta
        }

    }

    public String validarIngreso(UsuarioE em, String co) {
        String s = "";
        if (em != null) {//si em es igual a null devuelve un mensaje de error
            if (!(em.getNombre().equals("No existe"))) { //si el usuario no existe devuelve un mensaje de error
                if (co.equals(em.getCodigo())) {
                    //si la contraseña es igual devuelve los datos de cargo, nombre y cedula a la pagina web
                    if (em.getClienteCedula() != null) {
                        s = "0";
                    } else {
                        s = "0";
                    }
                } else {
                    //si la contraseña no es igual devuelve un mensaje de error
                    s = "Cédula o código incorrectos";
                }
            } else {
                s = "Cédula o código incorrectos";
            }
        } else {
            s = "Error con la base de datos";
        }
        return s;
    }

    /* public String json(String tipo,String nombre,String cedula,String mensaje){
     // devuelve un String con el tipo de usuario, nombre y cedula
     return "{\"tipo\":\""+tipo+"\",\"nombre\":\""+nombre+"\",\"cedula\":\""+cedula+"\",\"mensaje\":\""+mensaje+"\"}";
     }*/
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
