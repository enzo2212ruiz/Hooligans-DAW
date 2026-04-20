//ACCESO A LA BASE DE DATOS DE TRAJES
package controlador;

import modelo.ConexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrajeB {

    /**
     * Obtiene la lista de trajes.
     */
    public List<Traje> listar() {
        List<Traje> lista = new ArrayList<>();
        String sql = "SELECT * FROM TRAJE";
        try (Connection con = ConexionBD.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Traje t = new Traje();
                t.setId(rs.getInt("ID_traje"));
                t.setNombre(rs.getString("nombre"));
                t.setEstado(rs.getString("estado"));
                // Si necesitas el ID_cliente también puedes añadirlo
                lista.add(t);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return lista;
    }
}