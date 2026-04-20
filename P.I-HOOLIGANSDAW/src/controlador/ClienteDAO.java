//ACCESO A LA BASE DE DATOS DE CLIENTES
package controlador;

import modelo.ConexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    /**
     * Obtiene la lista completa de clientes.
     */
    public List<Cliente> listar() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM CLIENTE";
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
}