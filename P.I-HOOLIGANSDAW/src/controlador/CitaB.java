//ACCESO A LA BASE DE DATOS DE CITAS
package controlador;

import modelo.ConexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de gestionar las citas en la base de datos.
 */
public class CitaB {

    /**
     * Obtiene el listado de citas con los nombres de taller, responsable y traje.
     */
    public List<Cita> listarCitas() {
        List<Cita> lista = new ArrayList<>();
        String sql = "SELECT c.ID_cita, c.dia, c.hora, t.nombre as taller, e.nom_ape as responsable, tr.nombre as traje " +
                     "FROM CITA c " +
                     "JOIN TALLER t ON c.ID_taller = t.ID_taller " +
                     "JOIN EMPLEADO e ON c.ID_responsable = e.ID_empleado " +
                     "JOIN TRAJE tr ON c.ID_traje = tr.ID_traje";
        try (Connection con = ConexionBD.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Cita c = new Cita();
                c.setId(rs.getInt("ID_cita"));
                c.setFecha(rs.getString("dia"));
                c.setHora(rs.getString("hora"));
                c.setTaller(rs.getString("taller"));
                c.setResponsable(rs.getString("responsable"));
                c.setTraje(rs.getString("traje"));
                lista.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    /**
     * Inserta una nueva cita y asigna los asistentes (aprendices) en la tabla ASISTE.
     */
    public boolean insertarCita(String fecha, String hora, int duracion, int idTaller, int idRespon, int idTraje, List<Integer> idAsistentes) {
        Connection con = null;
        try {
            con = ConexionBD.conectar();
            con.setAutoCommit(false); // Inicia transacción para asegurar que se guarde todo o nada

            // 1. Insertar la Cita principal
            String sqlCita = "INSERT INTO CITA (dia, hora, duracion, ID_taller, ID_responsable, ID_traje) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement psCita = con.prepareStatement(sqlCita, Statement.RETURN_GENERATED_KEYS);
            psCita.setString(1, fecha);
            psCita.setString(2, hora);
            psCita.setInt(3, duracion);
            psCita.setInt(4, idTaller);
            psCita.setInt(5, idRespon);
            psCita.setInt(6, idTraje);
            psCita.executeUpdate();

            // Recuperamos el ID generado para la cita
            ResultSet rs = psCita.getGeneratedKeys();
            if (rs.next()) {
                int idCitaGenerada = rs.getInt(1);

                // 2. Insertar cada aprendiz asignado en la tabla ASISTE
                String sqlAsiste = "INSERT INTO ASISTE (ID_cita, ID_asistente) VALUES (?, ?)";
                PreparedStatement psAsiste = con.prepareStatement(sqlAsiste);
                for (Integer idAsistente : idAsistentes) {
                    psAsiste.setInt(1, idCitaGenerada);
                    psAsiste.setInt(2, idAsistente);
                    psAsiste.executeUpdate();
                }
            }
            return true;
        } catch (Exception e) {
            try { if (con != null) con.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            e.printStackTrace();
            return false;
        } finally {
            try { if (con != null) con.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }
}