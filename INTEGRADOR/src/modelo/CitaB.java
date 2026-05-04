package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Clase encargada de gestionar las operaciones de base de datos relacionadas con las citas.
 */
public class CitaB {

    /**
     * Obtiene todas las citas almacenadas en la base de datos.
     * @return lista de citas
     */
    public ArrayList<Cita> obtenerCitas() {
        ArrayList<Cita> listaCitas = new ArrayList<Cita>();

        String sql = "SELECT CITA.ID_cita, CITA.dia, CITA.hora, CITA.duracion, "
                + "CITA.ID_taller, CITA.ID_responsable, CITA.ID_traje, "
                + "TALLER.nombre AS nombre_taller, "
                + "EMPLEADO.nom_ape AS nombre_responsable, "
                + "TRAJE.nombre AS nombre_traje "
                + "FROM CITA "
                + "INNER JOIN TALLER ON CITA.ID_taller = TALLER.ID_taller "
                + "INNER JOIN EMPLEADO ON CITA.ID_responsable = EMPLEADO.ID_empleado "
                + "INNER JOIN TRAJE ON CITA.ID_traje = TRAJE.ID_traje "
                + "ORDER BY CITA.dia, CITA.hora";

        try {
            Connection conexion = ConexionBD.conectar();
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            ResultSet resultado = sentencia.executeQuery();

            while (resultado.next()) {
                Cita cita = new Cita();

                cita.setIdCita(resultado.getInt("ID_cita"));
                cita.setFechaCita(resultado.getString("dia"));
                cita.setHoraCita(resultado.getString("hora"));
                cita.setDuracionCita(resultado.getInt("duracion"));

                cita.setIdTaller(resultado.getInt("ID_taller"));
                cita.setIdResponsable(resultado.getInt("ID_responsable"));
                cita.setIdTraje(resultado.getInt("ID_traje"));

                cita.setNombreTaller(resultado.getString("nombre_taller"));
                cita.setNombreResponsable(resultado.getString("nombre_responsable"));
                cita.setNombreTraje(resultado.getString("nombre_traje"));

                listaCitas.add(cita);
            }

            resultado.close();
            sentencia.close();
            conexion.close();

        } catch (SQLException e) {
            System.out.println("Error al obtener las citas.");
            System.out.println(e.getMessage());
        }

        return listaCitas;
    }

    /**
     * Inserta una nueva cita en la base de datos.
     * @return true si se insertó correctamente
     */
    public boolean insertarCita(Cita cita) {
        String sql = "INSERT INTO CITA (dia, hora, duracion, ID_taller, ID_responsable, ID_traje) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try {
            Connection conexion = ConexionBD.conectar();
            PreparedStatement sentencia = conexion.prepareStatement(sql);

            sentencia.setString(1, cita.getFechaCita());
            sentencia.setString(2, cita.getHoraCita());
            sentencia.setInt(3, cita.getDuracionCita());
            sentencia.setInt(4, cita.getIdTaller());
            sentencia.setInt(5, cita.getIdResponsable());
            sentencia.setInt(6, cita.getIdTraje());

            int filasInsertadas = sentencia.executeUpdate();

            sentencia.close();
            conexion.close();

            return filasInsertadas > 0;

        } catch (SQLException e) {
            System.out.println("Error al insertar la cita.");
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Modifica una cita existente en la base de datos.
     * @return true si se modificó correctamente
     */
    public boolean modificarCita(Cita cita) {
        String sql = "UPDATE CITA SET dia = ?, hora = ?, duracion = ?, ID_taller = ?, "
                + "ID_responsable = ?, ID_traje = ? WHERE ID_cita = ?";

        try {
            Connection conexion = ConexionBD.conectar();
            PreparedStatement sentencia = conexion.prepareStatement(sql);

            sentencia.setString(1, cita.getFechaCita());
            sentencia.setString(2, cita.getHoraCita());
            sentencia.setInt(3, cita.getDuracionCita());
            sentencia.setInt(4, cita.getIdTaller());
            sentencia.setInt(5, cita.getIdResponsable());
            sentencia.setInt(6, cita.getIdTraje());
            sentencia.setInt(7, cita.getIdCita());

            int filasModificadas = sentencia.executeUpdate();

            sentencia.close();
            conexion.close();

            return filasModificadas > 0;

        } catch (SQLException e) {
            System.out.println("Error al modificar la cita.");
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Elimina una cita por su ID.
     * @return true si se eliminó correctamente
     */
    public boolean eliminarCita(int idCita) {
        String sql = "DELETE FROM CITA WHERE ID_cita = ?";

        try {
            Connection conexion = ConexionBD.conectar();
            PreparedStatement sentencia = conexion.prepareStatement(sql);

            sentencia.setInt(1, idCita);

            int filasEliminadas = sentencia.executeUpdate();

            sentencia.close();
            conexion.close();

            return filasEliminadas > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar la cita.");
            System.out.println(e.getMessage());
            return false;
        }
    }
}
