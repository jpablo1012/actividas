package Persistencia;

import Entidades.UsuarioE;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DaosUsuario {

    public UsuarioE validarUsuario(Connection con, String cedula) {
        UsuarioE ue = new UsuarioE();
        ue.setNombre("No existe");
        try {
            String sql = sqlValidarUsuario();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cedula);
            ps.setString(2, cedula);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
        	ue.setApellido(rs.getString("apellido"));
                ue.setIdUsuario(rs.getString("idusuario"));
                ue.setNombre(rs.getString("nombre"));
                ue.setTipo(rs.getString("tipo"));
                ue.setClienteCedula(rs.getString("cliente_cedula"));
                ue.setEmpleadoCedula(rs.getString("empleado_cedula"));
                ue.setCodigo(rs.getString("codigod"));
                System.out.println(rs.getString("idusuario"));
            }
        } catch (Exception e) {
            System.out.println("Error 01 DaosUsuario: " + e.getMessage());
            return null;
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error 02 DaosUsuario: " + e.getMessage());
                return null;
            }
        }
        return ue;
    }

    public String crearUsuario(Connection con, UsuarioE ue) {
        try {
            String sql = sqlCrearUsuario();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, ue.getNombre());
            ps.setString(2, ue.getTipo());
            ps.setString(3, ue.getClienteCedula());
            ps.setString(4, ue.getEmpleadoCedula());
            ps.setString(5, ue.getCodigo());
            ps.setString(6, ue.getApellido());
            
            FileInputStream file = new FileInputStream(ue.getImagen());
    	    ps.setBinaryStream(7, file, (int) ue.getImagen().length());
            ps.execute();
            
            file.close();
        } catch (Exception e) {
            System.out.println("Error 03 DaosUsuario: " + e.getMessage());
            return "1";//el usuario ya existe
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error 04 DaosUsuario: " + e.getMessage());
                return "2";//"Error al conectarse a la base de datos"
            }
        }
        return "";
    }
    
    public ArrayList<UsuarioE> buscarUsuario(Connection con, String variable, String valor, boolean exactamente){
    	return buscarUsuario(con, variable, valor, exactamente, false);
    }

    public ArrayList<UsuarioE> buscarUsuario(Connection con, String variable, String valor, boolean exactamente, boolean imagen) {
        ArrayList<UsuarioE> alue = new ArrayList<UsuarioE>();
        try {
            String sql = sqlBuscarUsuario(variable);
            PreparedStatement ps = con.prepareStatement(sql);
            
            if (exactamente) {
                ps.setString(1, valor);
            } else {
                ps.setString(1, valor + "%");
            }
            
            ResultSet rs = ps.executeQuery();

            String clase = this.getClass().getResource("").toString();

            clase = clase.replaceAll("file:/", "");
            clase = clase.replaceAll("/", "//");
            try {
                clase = URLDecoder.decode(clase, "UTF-8");
            } catch (Exception ex) {
            }
            clase = clase.replaceAll("WEB-INF//classes//Persistencia//", "usuarios");
            File alt = new File(clase);
            alt.mkdirs();
            while (rs.next()) {
            	UsuarioE ue = new UsuarioE();
                ue.setApellido(rs.getString("apellido"));
                ue.setClienteCedula(rs.getString("cliente_cedula"));
                ue.setCodigo(rs.getString("codigod"));
                ue.setEmpleadoCedula(rs.getString("empleado_cedula"));
                ue.setIdUsuario(rs.getString("idusuario"));
                ue.setNombre(rs.getString("nombre"));
                ue.setTipo(rs.getString("tipo"));
                
                if(imagen){
                	try{
                    	File image = new File(clase, "user" + ue.getIdUsuario() + ".png");
                		FileOutputStream fos = new FileOutputStream(image);
                		byte[] buffer = new byte[1];
                		InputStream is = rs.getBinaryStream("imagen");
                		while (is.read(buffer) > 0) {
                		    fos.write(buffer);
                		}
                		fos.close();
                		is.close();
                		if (image.length() == 0) {
                		    image.delete();
                		} else {
                		    ue.setImagen(image);
                		}
                        
                    }catch(Exception e){
                    	try{
                    		File image = new File(clase, "user" + ue.getIdUsuario() + ".png");
                    		if (image.length() == 0) {
                    		    image.delete();
                    		}
                    		
                    	}catch(Exception ee){}
                    }
                }
                
                
                alue.add(ue);
            }
        } catch (Exception e) {
            System.out.println("Error 05 DaosUsuario: " + e.getMessage());
            return alue;
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error 06 DaosUsuario: " + e.getMessage());
                return null;
            }
        }
        return alue;
    }

    public String actualizarUsuario(Connection con, UsuarioE ue) {
        try {
            String sql = sqlActualizarUsuario();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, ue.getNombre());
            ps.setString(2, ue.getTipo());
            ps.setString(3, ue.getClienteCedula());
            ps.setString(4, ue.getEmpleadoCedula());
            ps.setString(5, ue.getCodigo());
            ps.setString(6, ue.getApellido());
            System.out.println(ue.getImagen().getName() + "dsf");
            FileInputStream file = new FileInputStream(ue.getImagen());
    	    ps.setBinaryStream(7, file, (int) ue.getImagen().length());
            ps.setString(8, ue.getIdUsuario());
            ps.executeUpdate();
            
            file.close();
        } catch (Exception e) {
            System.out.println("Error 07 DaosUsuario: " + e.getMessage());
            return "1";
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error 08 DaosUsuario: " + e.getMessage());
                return "2";//"Error al conectarse a la base de datos"
            }
        }
        return "";
    }

    public String eliminarUsuario(Connection con, String id) {
        try {
            String sql = sqlEliminarUsuario();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, id);

            ps.execute();
        } catch (Exception e) {
            System.out.println("Error 09 DaosUsuario: " + e.getMessage());
            return "1";//el usuario ya existe
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error 10 DaosUsuario: " + e.getMessage());
                return "2";//"Error al conectarse a la base de datos"
            }
        }
        return "";
    }

    public static String sqlValidarUsuario() {
        return "select *, AES_DECRYPT(codigo, 'sadivitca') as codigod from usuario where cliente_cedula = ? or empleado_cedula=?";
    }

    public static String sqlCrearUsuario() {
        return "insert into usuario (nombre, tipo, cliente_cedula, empleado_cedula, codigo,apellido, imagen) values(?,?,?,?,AES_ENCRYPT(?, 'sadivitca'), ?, ?)";
    }

    public static String sqlBuscarUsuario(String variable) {
        return "select *, AES_DECRYPT(codigo, 'sadivitca') as codigod from usuario where " + variable + " LIKE ?";
    }

    public static String sqlActualizarUsuario() {
        return "update usuario set nombre=?, tipo=?, cliente_cedula=?, empleado_cedula=?, codigo=AES_ENCRYPT(?, 'sadivitca'), apellido=?, imagen = ? where idusuario=?";
    }

    public static String sqlEliminarUsuario() {
        return "delete from usuario where idusuario = ?";
    }
}
