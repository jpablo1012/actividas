/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rest;

import Entidades.SesionE;
import Entidades.UsuarioE;
import Negocio.UsuarioN;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.Date;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
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
@Path("/inicio")
public class InicioRest {

    private static final String[] appsId = {"5Rdzlt1x4OSyvny1SJEQ4bN1k1lHv", //Id para plastisoft de Windows Phone
                                            "1U3gZbY1O842dCNbkmnWIUuDsrrTS" //Id para plastisoft desktop
};

    private static ArrayList<SesionE> sesion = new ArrayList<>();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static boolean isApp(String id) {

        for (String s : appsId) {
            if (s.equals(id)) {
                return true;
            }
        }

        return false;
    }

    private String añadir(UsuarioE ue, String appId) {
        SesionE se = new SesionE();
        se.setAppId(appId);
        se.setUsuario(ue);
        sesion.add(se);
        for (SesionE see : sesion) {
            System.out.println(see.getSesionId());
        }
        return se.getSesionId();

    }

    private static void comprobarSesiones() {
        try {
            Date primero = sesion.get(0).getTiempo();
            long resultado = new Date().getTime() - primero.getTime();

            if (resultado > 600000) {
                sesion.remove(0);
            }
        } catch (Exception e) {
        }

    }

    public static int comprobar(String appId, String sesionId) {
        comprobarSesiones();
        System.out.println(sesion.size());
        if (isApp(appId)) {
            for (SesionE se : sesion) {
                if (sesionId.equals(se.getSesionId())) {
                    if (appId.equals(se.getAppId())) {
                        return Statics.OK;
                    }
                }

            }
            return Statics.SESION_FINALIZADA;
        }

        return Statics.APPID_INVALIDA;
    }

    @GET
    public String iniciarSesion(@QueryParam("appId") String appId,
                                @QueryParam("cedula") String cedula,
                                @QueryParam("constraseña") String contraseña) {
        comprobarSesiones();
        
        Resultado rb = new Resultado();
        rb.setResultado(Statics.ERROR_CONEXION_BD);

        if (isApp(appId)) {
            UsuarioE ue = new UsuarioN().validarIngreso(cedula, contraseña);

            if (ue.getIdUsuario().equals("-1") || ue.getIdUsuario().equals("-0")) {
                rb.setResultado(Statics.ALGO_INVALIDO);
            } else if (ue.getIdUsuario().equals("-2")) {
                rb.setResultado(Statics.ERROR_CONEXION_BD);
            } else {
                String sesionId = añadir(ue, appId);
                rb.setResultado(Statics.OK);
                rb.setSesionId(sesionId);
                rb.setUe(ue);
            }
        } else {
            rb.setResultado(Statics.APPID_INVALIDA);
        }

        return gson.toJson(rb);
    }

    private class Resultado {

        private int resultado;
        private String sesionId;
        private UsuarioE usuario;

        public int getResultado() {
            return resultado;
        }

        public void setResultado(int resultado) {
            this.resultado = resultado;
        }

        public String getSesionId() {
            return sesionId;
        }

        public void setSesionId(String sesionId) {
            this.sesionId = sesionId;
        }

        public UsuarioE getUe() {
            return usuario;
        }

        public void setUe(UsuarioE ue) {
            this.usuario = ue;
        }
    }
}
