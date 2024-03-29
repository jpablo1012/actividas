/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rest;

import Entidades.EmpleadoE;
import Entidades.UsuarioE;
import Negocio.EmpleadoN;
import Negocio.UsuarioN;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.File;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author jpablo
 */
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Stateless
@Path("/empleado")
public class EmpleadoRest {

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @POST
    public String crear(@QueryParam("appId") String appId,
                        @QueryParam("sesionId") String sesionId,
                        @QueryParam("cedula") String cedula,
                        @QueryParam("nombre") String nombre,
                        @QueryParam("apellido") String apellido,
                        @QueryParam("cargo") String cargo,
                        @QueryParam("contraseña") String contraseña) {

        Resultado r = new Resultado();
        int result = InicioRest.comprobar(appId, sesionId);
        if (result == Statics.OK) {
            r = crear(cedula, nombre, apellido, cargo, contraseña);
        } else {
            r.setResultado(result);
        }

        return gson.toJson(r);
    }

    @GET
    public String buscar(@QueryParam("appId") String appId,
                        @QueryParam("sesionId") String sesionId,
                        @QueryParam("buscarCon") String buscarCon,
                        @QueryParam("valor") String valor) {
        Resultado r = new Resultado();
        int result = InicioRest.comprobar(appId, sesionId);

        if (result == Statics.OK) {
            JsonObject json = new JsonObject();
            JsonArray jarray = new JsonArray();
            ArrayList<ResultadoBuscar> rb = buscar(buscarCon, valor);
            JsonElement jelem = gson.toJsonTree(rb);

            jarray.add(jelem);
            json.add("resultado", jelem);
            //json.addProperty("length", rb.size());

            return gson.toJson(json);
        } else {
            r.setResultado(result);
        }

        return gson.toJson(r);
    }
    
    @PUT
    public String actualizar(@QueryParam("appId") String appId,
                            @QueryParam("sesionId") String sesionId,
                            @QueryParam("cedula") String cedula,
                            @QueryParam("nombre") String nombre,
                            @QueryParam("apellido") String apellido,
                            @QueryParam("cargo") String cargo,
                            @QueryParam("contraseña") String contraseña){
        
        Resultado r = new Resultado();
        int result = InicioRest.comprobar(appId, sesionId);
        
        if(result == Statics.OK){
            r = actualizar(cedula, nombre, apellido, cargo, contraseña);
        }else{
            r.setResultado(result);
        }
        
        return gson.toJson(r);
    }
    
    @DELETE
    public String eliminar(@QueryParam("appId") String appId,
                            @QueryParam("sesionId") String sesionId,
                            @QueryParam("cedula") String cedula){
        
        Resultado r = new Resultado();
        int result = InicioRest.comprobar(appId, sesionId);
        if(result == Statics.OK){
            r = eliminar(cedula);
        }else{
            r.setResultado(result);
        }
        return gson.toJson(r);
    }

    private Resultado crear(String cedula,
                            String nombre,
                            String apellido,
                            String cargo,
                            String contraseña) {

        Resultado r = new Resultado();

        EmpleadoE ee = new EmpleadoE();
        ee.setCedula(cedula);
        ee.setNombre(nombre);
        ee.setApellido(apellido);
        ee.setCargo(cargo);

        String s = new EmpleadoN().crearEmpleado(ee);

        if (s.equals("")) {
            UsuarioE ue = new UsuarioE();
            ue.setEmpleadoCedula(cedula);
            ue.setNombre(nombre);
            ue.setApellido(apellido);
            ue.setTipo(cargo);
            ue.setCodigo(contraseña);

            s = new UsuarioN().crearUsuario(ue);
        }

        switch (s) {
            case "":
                r.setResultado(Statics.OK);
                break;

            case "1":
                r.setResultado(Statics.ALGO_INVALIDO);
                break;

            default:
                r.setResultado(Statics.ERROR_CONEXION_BD);
                break;
        }

        return r;
    }

    private ArrayList<ResultadoBuscar> buscar(String buscarCon, String valor) {
        ArrayList<ResultadoBuscar> alrb = new ArrayList<>();
        ArrayList<EmpleadoE> alee;

        alee = new EmpleadoN().buscarEmpleado(buscarCon, valor, false);

        if (alee != null) {
            if (alee.size() > 0) {
                ArrayList<UsuarioE> alue = new UsuarioN().buscarUsuario("empleado_cedula", "", false);

                for (int i = 0; i < alee.size(); i++) {
                    for (int j = 0; j < alue.size(); j++) {
                        EmpleadoE ee = alee.get(i);
                        UsuarioE ue = alue.get(j);

                        if (ee.getCedula().equals(ue.getEmpleadoCedula())) {
                            ResultadoBuscar rb = new ResultadoBuscar();
                            rb.setCedula(ee.getCedula());
                            rb.setNombre(ee.getNombre());
                            rb.setApellido(ee.getApellido());
                            rb.setCargo(ee.getCargo());
                            rb.setContraseña(ue.getCodigo());
                            //rb.setImagen(ue.getImagen());
                            alrb.add(rb);
                        }
                    }
                }
            }
        }

        return alrb;
    }
    
    private Resultado actualizar(String cedula,
                                String nombre,
                                String apellido,
                                String cargo,
                                String contraseña) {
        Resultado r = new Resultado();

        EmpleadoE ee = new EmpleadoE();
        ee.setCedula(cedula);
        ee.setNombre(nombre);
        ee.setApellido(apellido);
        ee.setCargo(cargo);

        
        ArrayList<UsuarioE> alue = new UsuarioN().buscarUsuario("empleado_cedula", cedula, true, true);
        
        if(alue != null){
            if(alue.size() > 0){
                UsuarioE ue = alue.get(0);
                ue.setNombre(nombre);
                ue.setApellido(apellido);
                ue.setTipo(cargo);
                ue.setCodigo(contraseña);
                
                String s = new EmpleadoN().actualizarEmpleado(ee);
                
                if(s.equals("")){
                    s = new UsuarioN().actualizarUsuario(ue);
                }
                
                switch (s) {
                    case "":
                        r.setResultado(Statics.OK);
                        break;

                    case "1":
                        r.setResultado(Statics.ALGO_INVALIDO);
                        break;

                    default:
                        r.setResultado(Statics.ERROR_CONEXION_BD);
                        break;
                }
                
            }else{
                r.setResultado(Statics.ALGO_INVALIDO);
            }
        }else{
            r.setResultado(Statics.ERROR_CONEXION_BD);
        }
        
        return r;
    }
    
    private Resultado eliminar(String cedula){
        Resultado r = new Resultado();

        ArrayList<UsuarioE> alue = new UsuarioN().buscarUsuario("empleado_cedula", cedula, true);
        
        if(alue != null){
           if(alue.size() > 0){
               UsuarioE ue = alue.get(0);
               
               String s = new UsuarioN().eliminarUsuario(ue.getIdUsuario());
               
               if(s.equals("")){
                   s = new EmpleadoN().eliminarEmpleado(cedula);
               }
               
               switch (s) {
                    case "":
                        r.setResultado(Statics.OK);
                        break;

                    case "1":
                        r.setResultado(Statics.ALGO_INVALIDO);
                        break;

                    default:
                        r.setResultado(Statics.ERROR_CONEXION_BD);
                        break;
                }
           }else{
               r.setResultado(Statics.ALGO_INVALIDO);
           }
        }else{
            r.setResultado(Statics.ERROR_CONEXION_BD);
        }
        
        return r;
    }

    private class Resultado {

        public int getResultado() {
            return resultado;
        }

        public void setResultado(int resultado) {
            this.resultado = resultado;
        }
        private int resultado;
    }

    private class ResultadoBuscar {

        private String cedula;
        private String nombre;
        private String apellido;
        private String cargo;
        private String contraseña;
        private File imagen;

        public String getCedula() {
            return cedula;
        }

        public void setCedula(String cedula) {
            this.cedula = cedula;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getApellido() {
            return apellido;
        }

        public void setApellido(String apellido) {
            this.apellido = apellido;
        }

        public String getCargo() {
            return cargo;
        }

        public void setCargo(String cargo) {
            this.cargo = cargo;
        }

        public String getContraseña() {
            return contraseña;
        }

        public void setContraseña(String contraseña) {
            this.contraseña = contraseña;
        }

        public File getImagen() {
            return imagen;
        }

        public void setImagen(File imagen) {
            this.imagen = imagen;
        }

    }
}
