package Negocio;

import java.util.ArrayList;
import java.util.Date;

import Entidades.SelladoE;
import Persistencia.DaosSellado;
import Utilidades.Conexion;

public class SelladoN {

    public DaosSellado dao;
    
    public SelladoN() {
	dao = new DaosSellado();
    }
    
    public String crearSellado(SelladoE se){
	Conexion con = new Conexion();
	
	Date date = new Date();
	se.setFechaInicio(date);
	
	return dao.crearSellado(con.getCon(), se);
    }
    
    public ArrayList<SelladoE> buscarSellado(String variable, String valor, boolean exactamente){
	Conexion con = new Conexion();
	
	return dao.buscarSellado(con.getCon(), variable, valor, exactamente);
    }
    
    public String actualizarSellado(SelladoE se){
	Conexion con = new Conexion();
	
	return dao.actualizarSellado(con.getCon(), se);
    }

}
