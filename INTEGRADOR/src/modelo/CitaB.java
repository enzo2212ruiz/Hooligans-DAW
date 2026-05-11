package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

/**
 * Clase encargada de realizar las operaciones de base de datos
 * relacionadas con las citas.
 */
public class CitaB {

	/**
	 * Obtiene todas las citas junto con los nombres del taller,
	 * responsable, traje y aprendices asociados.
	 */
	public ArrayList<Cita> obtenerCitas() {
		ArrayList<Cita> listaCitas = new ArrayList<Cita>();

		String sql = "SELECT CITA.ID_cita, CITA.dia, CITA.hora, CITA.duracion, "
				+ "CITA.ID_taller, CITA.ID_responsable, CITA.ID_traje, "
				+ "CITA.ID_aprendiz1, CITA.ID_aprendiz2, "
				+ "TALLER.nombre AS nombre_taller, "
				+ "RESP.nom_ape AS nombre_responsable, "
				+ "TRAJE.nombre AS nombre_traje, "
				+ "A1.nom_ape AS nombre_aprendiz1, "
				+ "A2.nom_ape AS nombre_aprendiz2 "
				+ "FROM CITA "
				+ "INNER JOIN TALLER ON CITA.ID_taller = TALLER.ID_taller "
				+ "INNER JOIN EMPLEADO RESP ON CITA.ID_responsable = RESP.ID_empleado "
				+ "INNER JOIN TRAJE ON CITA.ID_traje = TRAJE.ID_traje "
				+ "LEFT JOIN EMPLEADO A1 ON CITA.ID_aprendiz1 = A1.ID_empleado "
				+ "LEFT JOIN EMPLEADO A2 ON CITA.ID_aprendiz2 = A2.ID_empleado "
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

				cita.setIdAprendiz1(resultado.getInt("ID_aprendiz1"));
				cita.setIdAprendiz2(resultado.getInt("ID_aprendiz2"));

				cita.setNombreTaller(resultado.getString("nombre_taller"));
				cita.setNombreResponsable(resultado.getString("nombre_responsable"));
				cita.setNombreTraje(resultado.getString("nombre_traje"));
				cita.setNombreAprendiz1(resultado.getString("nombre_aprendiz1"));
				cita.setNombreAprendiz2(resultado.getString("nombre_aprendiz2"));

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
	 * Los aprendices son opcionales, por eso pueden guardarse como NULL.
	 */
	public boolean insertarCita(Cita cita) {
		String sql = "INSERT INTO CITA (dia, hora, duracion, ID_taller, ID_responsable, ID_traje, ID_aprendiz1, ID_aprendiz2) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			Connection conexion = ConexionBD.conectar();
			PreparedStatement sentencia = conexion.prepareStatement(sql);

			sentencia.setString(1, cita.getFechaCita());
			sentencia.setString(2, cita.getHoraCita());
			sentencia.setInt(3, cita.getDuracionCita());
			sentencia.setInt(4, cita.getIdTaller());
			sentencia.setInt(5, cita.getIdResponsable());
			sentencia.setInt(6, cita.getIdTraje());

			if (cita.getIdAprendiz1() > 0) {
				sentencia.setInt(7, cita.getIdAprendiz1());
			} else {
				sentencia.setNull(7, Types.INTEGER);
			}

			if (cita.getIdAprendiz2() > 0) {
				sentencia.setInt(8, cita.getIdAprendiz2());
			} else {
				sentencia.setNull(8, Types.INTEGER);
			}

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
	 */
	public boolean modificarCita(Cita cita) {
		String sql = "UPDATE CITA SET dia = ?, hora = ?, duracion = ?, ID_taller = ?, "
				+ "ID_responsable = ?, ID_traje = ?, ID_aprendiz1 = ?, ID_aprendiz2 = ? "
				+ "WHERE ID_cita = ?";

		try {
			Connection conexion = ConexionBD.conectar();
			PreparedStatement sentencia = conexion.prepareStatement(sql);

			sentencia.setString(1, cita.getFechaCita());
			sentencia.setString(2, cita.getHoraCita());
			sentencia.setInt(3, cita.getDuracionCita());
			sentencia.setInt(4, cita.getIdTaller());
			sentencia.setInt(5, cita.getIdResponsable());
			sentencia.setInt(6, cita.getIdTraje());

			if (cita.getIdAprendiz1() > 0) {
				sentencia.setInt(7, cita.getIdAprendiz1());
			} else {
				sentencia.setNull(7, Types.INTEGER);
			}

			if (cita.getIdAprendiz2() > 0) {
				sentencia.setInt(8, cita.getIdAprendiz2());
			} else {
				sentencia.setNull(8, Types.INTEGER);
			}

			sentencia.setInt(9, cita.getIdCita());

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
	 * Elimina una cita de la base de datos según su ID.
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