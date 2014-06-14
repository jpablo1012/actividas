/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio;

import Entidades.ClienteE;
import Entidades.EmpleadoE;
import Entidades.List;
import Entidades.Registro;
import Entidades.UsuarioE;

/**
 *
 * @author Juan Pablo Lopera Estrada
 * @since 11/06/2014
 * @version 73
 */
public class Historial {
    private static List<Registro> cambios = new List<Registro>();
    public static boolean conBD = false;
    
    
    /**
     * Añade un cambio a la cola de cambios
     * @param registro cambio a añadir
     */
    public static void añadir(Registro registro){
        cambios.push(registro);
    }
    
    /**
     * Retorna la cola de cambios
     * @return List - cola de cambios
     */
    public static List<Registro> getCambios(){
        return cambios;
    }
    
    public static void guardar(){
        conBD = true;
        while(!cambios.isEmpty()){
            Registro registro = cambios.pop();
            switch(registro.getAccion()){
                case ACTUALIZAR_CLIENTE:
                    new ClienteN().actualizarCliente((ClienteE)registro.getDato());
                    break;
                case ACTUALIZAR_EMPLEADO:
                    new EmpleadoN().actualizarEmpleado((EmpleadoE)registro.getDato());
                    break;
                case ACTUALIZAR_USUARIO:
                    new UsuarioN().actualizarUsuario((UsuarioE)registro.getDato());
                    break;
                    
                case CREAR_CLIENTE:
                    new ClienteN().crearCliente((ClienteE)registro.getDato());
                    break;
                case CREAR_EMPLEADO:
                    new EmpleadoN().crearEmpleado((EmpleadoE)registro.getDato());
                    break;
                case CREAR_USUARIO:
                    new UsuarioN().crearUsuario((UsuarioE)registro.getDato());
                    break;
                
                case ELIMINAR_CLIENTE:
                    new ClienteN().eliminarCliente(((ClienteE)registro.getDato()).getCedula());
                    break;
                case ELIMINAR_EMPLEADO:
                    new EmpleadoN().eliminarEmpleado(((EmpleadoE)registro.getDato()).getCedula());
                    break;
                case ELIMINAR_USUARIO:
                    new UsuarioN().eliminarUsuario(((UsuarioE)registro.getDato()).getIdUsuario());
                    break;
            }
        }
    }
}
