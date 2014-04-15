

package Negocio;

import Entidades.ImpresionE;
import Persistencia.DaosImpresion;
import Utilidades.Conexion;

import java.util.ArrayList;
import java.util.Date;


public class ImpresionN {

    public DaosImpresion dao;

    public ImpresionN(){
        dao = new DaosImpresion();
    }

    public String crearImpresion(ImpresionE ie){
        Conexion con = new Conexion();
        
        Date date = new Date();
        ie.setFechaInicio(date);
        return dao.crearImpresion(con.getCon(), ie);
    }

    public String actualizarImpresion(ImpresionE ie){
        Conexion con = new Conexion();
        return dao.actualizarImpresion(con.getCon(), ie);
    }

    public ArrayList<ImpresionE> buscarImpresion(String variable, String valor, boolean exactamente){
        Conexion con = new Conexion();
        return dao.buscarImpresion(con.getCon(), variable, valor, exactamente);
    }
}
