/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import java.io.IOException; 
import java.io.PrintWriter; 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Negocio.UsuarioN; 
import Entidades.UsuarioE;
/**
 *
 * @author Usuario
 */

public class SvlAutentificacion extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
        
        UsuarioN en= new UsuarioN();
        UsuarioE e = new UsuarioE();
        e = en.validarIngreso(cedula);//envia cedula al metodo "validarIngreso" de la clase EmpleadoN
        try{
            //envia la respuesta a la pagina web con el valor retornado de validarIngreso
            out.print(validarIngreso(e, codigo));
        }finally{
            out.close();//finaliza la respuesta
        }

    }

    public String validarIngreso(UsuarioE em,String co){
        AES a = new AES();
        a.setKey("actividas");
        String word = "validado";
        String name;
        String s="";
        if(em!=null){//si em es igual a null devuelve un mensaje de error
            if(!(em.getNombre().equals("No existe"))){ //si el usuario no existe devuelve un mensaje de error
                if(co.equals(em.getCodigo())){
                    //si la contraseña es igual devuelve los datos de cargo, nombre y cedula a la pagina web
                    if(em.getClienteCedula()!=null){
                        a.encrypt(word);
                        name = a.getEncryptedString();
                        s = json(em.getTipo(), em.getNombre(),em.getClienteCedula(), name ,"");
                        
                    }else{
                        a.encrypt(word);
                        name = a.getEncryptedString();
                        s = json(em.getTipo(),em.getNombre(),em.getEmpleadoCedula(), name,"");
                    }
                }else{
                    //si la contraseña no es igual devuelve un mensaje de error
                    s = json("","","","","Cédula o código incorrectos");
                }
            }else{
                s = json("","","","","Cédula o código incorrectos");
            }
        }else{
            s = json("","","","","Error con la base de datos");
        }
    return s;
    }

    public String json(String tipo,String nombre,String cedula, String real,String mensaje){
        // devuelve un String con el tipo de usuario, nombre y cedula
        return "{\"tipo\":\""+tipo+"\",\"nombre\":\""+nombre+"\",\"cedula\":\""+cedula+"\",\"real\":\""+real+"\",\"mensaje\":\""+mensaje+"\"}";
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