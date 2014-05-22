/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entidades;

import java.util.Date;
import java.util.Random;

/**
 *
 * @author jpablo
 */
public class SesionE {
    private String appId;
    private final String sesionId;
    private Date tiempo;
    private UsuarioE usuario;
    
    public SesionE(){
        tiempo = new Date();
        sesionId = generarSesionId();
    }
    
    private String generarSesionId(){
        char[] chars = "abcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
        String r = "";
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            char c = chars[random.nextInt(chars.length)];
            r += c;
        }
        return r;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSesionId() {
        return sesionId;
    }

    /*public void setSesionId(String sesionId) {
        this.sesionId = sesionId;
    }*/

    public Date getTiempo() {
        return tiempo;
    }

    public void setTiempo(Date tiempo) {
        this.tiempo = tiempo;
    }

    public UsuarioE getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioE usuario) {
        this.usuario = usuario;
    }
}
