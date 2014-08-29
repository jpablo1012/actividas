package Persistencia;

import Entidades.UsuarioE;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Juan Pablo Lopera Estrada
 * @since 2013
 */
public class DaosUsuario {

    /**
     * Comprueba que un usuario exista en la tabla usario para iniciar sesión
     *
     * @param con Conexión a la base de datos
     * @param cedula Cédula del usuario a comprobar en la base de datos
     * @return retorna un UsuarioE si el usuario existe en la base de datos,
     * null si la cedula es invalida o null si hubo un error al conectarse a la
     * base de datos
     */
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
        } catch (SQLException e) {
            System.out.println("Error 01 DaosUsuario: " + e.getMessage());
            return null;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error 02 DaosUsuario: " + e.getMessage());
                return null;
            }
        }
        return ue;
    }

    /**
     * Crea un usuario en la tabla usario
     *
     * @param con Conexión a la base de datos
     * @param ue Usuario a crear
     * @return String "2": Error al conectarse a la base de datos, "1": El
     * usuario ya existe, "": Usuario creado
     */
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
        } catch (SQLException e) {
            System.out.println("Error 03 DaosUsuario: " + e.getMessage());
            return "1";//el usuario ya existe
        } catch (IOException e) {
            System.out.println("Error 04 DaosUsuario: " + e.getMessage());
            return "1";//error con la imagen
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error 05 DaosUsuario: " + e.getMessage());
                return "2";//"Error al conectarse a la base de datos"
            }
        }
        return "";
    }

    /**
     * Busca usuarios en la tabla usuario que cumplan con las condiciones dadas
     *
     * @param con Conexión a la base de datos
     * @param variable columna de la tabla usuario (idusuario, nombre, apellido,
     * tipo, empleado_cedula, cliente_cedula) que va a usar para buscar
     * @param valor valor a comparar con la columna seleccionada
     * @param exactamente true si los datos de la columna tienen que ser iguales
     * a el valor a buscar false si los datos pueden empezar por el valor a
     * buscar
     * @return ArrayList de UsuarioE o null si hubo un error al conectare a la
     * base de datos
     */
    public ArrayList<UsuarioE> buscarUsuario(Connection con, String variable, String valor, boolean exactamente) {
        return buscarUsuario(con, variable, valor, exactamente, false);
    }

    /**
     * Busca usuarios en la tabla usuario que cumplan con las condiciones dadas
     *
     * @param con Conexión a la base de datos
     * @param variable columna de la tabla usuario (idusuario, nombre, apellido,
     * tipo, empleado_cedula, cliente_cedula) que va a usar para buscar
     * @param valor valor a comparar con la columna seleccionada
     * @param exactamente true si los datos de la columna tienen que ser iguales
     * a el valor a buscar false si los datos pueden empezar por el valor a
     * buscar
     * @param imagen true para que el resultado incluya la imagen del usuario y
     * false para que no la incluya
     * @return ArrayList de UsuarioE o null si hubo un error al conectare a la
     * base de datos
     */
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
            } catch (UnsupportedEncodingException ex) {
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

                if (imagen) {
                    try {
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

                    } catch (SQLException e) {
                        try {
                            File image = new File(clase, "user" + ue.getIdUsuario() + ".png");
                            if (image.length() == 0) {
                                image.delete();
                            }

                        } catch (Exception ee) {
                        }
                    } catch (IOException e) {
                        try {
                            File image = new File(clase, "user" + ue.getIdUsuario() + ".png");
                            if (image.length() == 0) {
                                image.delete();
                            }
                            
                        } catch (Exception ee) {
                        }
                    }
                }

                alue.add(ue);
            }
        } catch (SQLException e) {
            System.out.println("Error 06 DaosUsuario: " + e.getMessage());
            return alue;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error 07 DaosUsuario: " + e.getMessage());
                return null;
            }
        }
        return alue;
    }

    /**
     * Actualiza datos de un usuario en la tabla usuario
     *
     * @param con Conexión a la base de datos
     * @param ue Usuario con datos actualizados
     * @return "2": Error al conectarse a la base de datos, "1": El usuario no
     * existe, "": Usuario actualizado
     */
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
        } catch (SQLException e) {
            System.out.println("Error 08 DaosUsuario: " + e.getMessage());
            return "1";
        } catch (IOException e) {
            System.out.println("Error 08 DaosUsuario: " + e.getMessage());
            return "1";
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error 09 DaosUsuario: " + e.getMessage());
                return "2";//"Error al conectarse a la base de datos"
            }
        }
        return "";
    }

    /**
     * Elimina un usuario de la tabla usuario
     *
     * @param con Conexión a la base de datos
     * @param id Id del usuario a eliminar
     * @return "2": Error al conectarse a la base de datos, "1": El usuario no
     * se puede eliminar, "": Usuario eliminado
     */
    public String eliminarUsuario(Connection con, String id) {
        try {
            String sql = sqlEliminarUsuario();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, id);

            ps.execute();
        } catch (SQLException e) {
            System.out.println("Error 10 DaosUsuario: " + e.getMessage());
            return "1";//El usuario no se puede eliminar
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error 11 DaosUsuario: " + e.getMessage());
                return "2";//"Error al conectarse a la base de datos"
            }
        }
        return "";
    }
    
    private boolean stringIniciaCon(String original, String comparar) {
        if (original == null || comparar == null) {
            return false;
        }
        original = original.toLowerCase();
        comparar = comparar.toLowerCase();
        return original.startsWith(comparar);
    }

    /**
     * Sentencia SQL para validar que un usuario exista en la tabla usuario
     *
     * @return String - sentencia SQL
     */
    private static String sqlValidarUsuario() {
        return "select *, AES_DECRYPT(codigo, 'sadivitca') as codigod from usuario where cliente_cedula = ? or empleado_cedula=?";
    }

    /**
     * Sentencia SQL para crear un usuario en la tabla usuario
     *
     * @return String - sentencia SQL
     */
    private static String sqlCrearUsuario() {
        return "insert into usuario (nombre, tipo, cliente_cedula, empleado_cedula, codigo,apellido, imagen) values(?,?,?,?,AES_ENCRYPT(?, 'sadivitca'), ?, ?)";
    }

    /**
     * Sentencia SQL para buscar usuarios en la tabla usuario
     *
     * @param variable - Columna de la tabla usuario (idusuario, nombre,
     * apellido, tipo, empleado_cedula, cliente_cedula) que se usara para buscar
     * @return String - sentencia SQL
     */
    private static String sqlBuscarUsuario(String variable) {
        return "select *, AES_DECRYPT(codigo, 'sadivitca') as codigod from usuario where " + variable + " LIKE ?";
    }

    /**
     * Sentencia SQL para actualizar datos de un usuario en la tabla usuario
     *
     * @return String - sentencia SQL
     */
    private static String sqlActualizarUsuario() {
        return "update usuario set nombre=?, tipo=?, cliente_cedula=?, empleado_cedula=?, codigo=AES_ENCRYPT(?, 'sadivitca'), apellido=?, imagen = ? where idusuario=?";
    }

    /**
     * Sentencia SQL para eliminar un usuario de la tabla usuario
     *
     * @return String - sentencia SQL
     */
    private static String sqlEliminarUsuario() {
        return "delete from usuario where idusuario = ?";
    }
}
