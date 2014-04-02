/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Entidades.ColorE;
import Persistencia.DaosColor;
import Utilidades.Conexion;
import java.util.ArrayList;

/**
 *
 * @author JPABLO
 */
public class ColorN {

    DaosColor daos;

    public ColorN() {
        daos = new DaosColor();
    }

    public String crearColor(ColorE ce) {
        Conexion con = new Conexion();
        return daos.crearColor(con.getCon(), ce);
    }

    public ArrayList<ColorE> buscarColor(String variable, String valor, boolean exactamente) {
        Conexion con = new Conexion();
        return daos.buscarColor(con.getCon(), variable, valor, exactamente);
    }

    public String actualizarColor(ColorE ce) {
        Conexion con = new Conexion();
        return daos.actualizarColor(con.getCon(), ce);
    }

    public String eliminarColor(ColorE ce) {
        Conexion con = new Conexion();
        return daos.eliminarColor(con.getCon(), ce);
    }
}
