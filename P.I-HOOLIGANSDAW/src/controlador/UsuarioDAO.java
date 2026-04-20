//ACCESO A LA BASE DE DATOS
package controlador;

import modelo.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Clase encargada de acceder a los datos de los usuarios en la base de datos.
 * En este caso uti lo utilizamos para comprobar el login.
 */
public class UsuarioDAO {

    /**
     * Comprueba si existe un empleado con el apodo y password indicados.
     * Si existe, devuelve un objeto Usuario con sus datos.
     * Si no existe, devuelve null.
     * 
     * @param usuario apodo introducido en el login
     * @param password contraseña introducida en el login
     * @return objeto Usuario si el login es correcto, null si es incorrecto
     */
    public Usuario login(String usuario, String password) {

        Usuario u = null;

        try {
            // Se obtiene la conexión a la base de datos
            Connection con = ConexionBD.conectar();

            // Consulta SQL para buscar al empleado por apodo y contraseña
            String sql = "SELECT * FROM EMPLEADO WHERE apodo=? AND password=?";
            PreparedStatement ps = con.prepareStatement(sql);

            // Se sustituyen los ? por los valores introducidos en el login
            ps.setString(1, usuario);
            ps.setString(2, password);

            // Se ejecuta la consulta
            ResultSet rs = ps.executeQuery();

            // Si hay resultado, el usuario existe y el login es correcto
            if (rs.next()) {
                u = new Usuario();
                u.setNombre(rs.getString("nom_ape"));
                u.setCategoria(rs.getString("categoria"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return u;
    }
}