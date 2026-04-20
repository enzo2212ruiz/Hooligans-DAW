//ACCESO A LA BASE DE DATOS DE CLIENTES
package controlador;

import modelo.ConexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteB {

    /**
     * Obtiene la lista completa de clientes.
     */
    public List<Cliente> listar() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM CLIENTE"; //sentencia de sql para que nos muestre la lista de clientes de la base de datos
        try (Connection con = ConexionBD.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setId(rs.getInt("ID_cliente"));
                c.setNombre(rs.getString("nombre"));
                c.setSuperpoder(rs.getString("superpoder"));
                lista.add(c);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return lista;
    }

    /**
     * Metodo para insertar un nuevo cliente en la base de datos
     */
    public boolean insertar(String nombre, String superpoder) {
        String sql = "INSERT INTO CLIENTE (nombre, superpoder) VALUES (?, ?)";
        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, superpoder);
            return ps.executeUpdate() > 0;
        } catch (Exception e) { 
            e.printStackTrace(); 
            return false; 
        }
    }
}