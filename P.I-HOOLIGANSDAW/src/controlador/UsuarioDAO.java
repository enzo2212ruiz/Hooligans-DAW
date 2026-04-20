//ACCESO A LA BASE DE DATOS
package controlador;

import modelo.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de acceder a los datos de los usuarios en la base de datos.
 */
public class UsuarioDAO {

    /**
     * Comprueba el login del empleado.
     */
    public Usuario login(String usuario, String password) {
        Usuario u = null;
        try {
            Connection con = ConexionBD.conectar();
            String sql = "SELECT * FROM EMPLEADO WHERE apodo=? AND password=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                u = new Usuario();
                u.setId(rs.getInt("ID_empleado")); // Guardamos el ID para las citas
                u.setNombre(rs.getString("nom_ape"));
                u.setCategoria(rs.getString("categoria"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }

    /**
     * Obtiene la lista completa de empleados para la tabla.
     */
    public List<Usuario> listarUsuarios() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM EMPLEADO";
        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("ID_empleado"));
                u.setNombre(rs.getString("nom_ape"));
                u.setCategoria(rs.getString("categoria"));
                lista.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    /**
     * Obtiene empleados filtrados por su categoría (APRENDIZ, OFICIAL, etc.)
     */
    public List<Usuario> listarPorCategoria(String categoria) {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM EMPLEADO WHERE categoria = ?";
        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, categoria);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("ID_empleado"));
                u.setNombre(rs.getString("nom_ape"));
                u.setCategoria(rs.getString("categoria"));
                lista.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}