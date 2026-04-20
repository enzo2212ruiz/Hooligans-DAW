//ACCESO A LA BASE DE DATOS DE TALLERES
package controlador;

import modelo.ConexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TallerDAO {

    /**
     * Obtiene la lista completa de talleres.
     */
    public List<Taller> listar() {
        List<Taller> lista = new ArrayList<>();
        String sql = "SELECT * FROM TALLER";
        try (Connection con = ConexionBD.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Taller t = new Taller();
                t.setId(rs.getInt("ID_taller"));
                t.setNombre(rs.getString("nombre"));
                t.setTipo(rs.getString("tipo"));
                lista.add(t);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return lista;
    }
}