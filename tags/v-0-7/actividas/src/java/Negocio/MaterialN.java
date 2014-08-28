/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Entidades.MaterialE;
import Persistencia.DaosMaterial;
import Utilidades.Conexion;
import java.util.ArrayList;

/**
 *
 * @author JPABLO
 */
public class MaterialN {

    public DaosMaterial dao;

    public MaterialN() {
        dao = new DaosMaterial();
    }

    public String crearMaterial(MaterialE me) {
        Conexion con = new Conexion();
        return dao.crearMaterial(con.getCon(), me);
    }

    public ArrayList<MaterialE> buscarMaterial(String variable, String valor, boolean exactamente) {
        Conexion con = new Conexion();
        return dao.buscarMaterial(con.getCon(), variable, valor, exactamente);
    }

    public String actualizarMaterial(MaterialE me) {
        Conexion con = new Conexion();
        return dao.actualizarMaterial(con.getCon(), me);
    }

    public String eliminarMaterial(MaterialE me) {
        Conexion con = new Conexion();
        return dao.eliminarMaterial(con.getCon(), me);
    }
}
