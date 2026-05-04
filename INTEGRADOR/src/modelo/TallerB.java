package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Clase encargada de gestionar las operaciones de base de datos relacionadas
 * con los talleres.
 */
public class TallerB {

	/**
	 * Obtiene todos los talleres de la base de datos.
	 * 
	 * @return lista de talleres
	 */
	public ArrayList<Taller> obtenerTalleres() {
		ArrayList<Taller> listaTalleres = new ArrayList<Taller>();

		String sql = "SELECT * FROM TALLER ORDER BY ID_taller";

		try {
			Connection conexion = ConexionBD.conectar();
			PreparedStatement sentencia = conexion.prepareStatement(sql);
			ResultSet resultado = sentencia.executeQuery();

			while (resultado.next()) {
				Taller taller = new Taller();

				taller.setIdTaller(resultado.getInt("ID_taller"));
				taller.setNombreTaller(resultado.getString("nombre"));
				taller.setTipoTaller(resultado.getString("tipo"));

				listaTalleres.add(taller);
			}

			resultado.close();
			sentencia.close();
			conexion.close();

		} catch (SQLException e) {
			System.out.println("Error al obtener los talleres.");
			System.out.println(e.getMessage());
		}

		return listaTalleres;
	}

	/**
	 * Inserta un nuevo taller en la base de datos.
	 * 
	 * @return true si se insertó correctamente
	 */
	public boolean insertarTaller(Taller taller) {
		String sql = "INSERT INTO TALLER (nombre, tipo) VALUES (?, ?)";

		try {
			Connection conexion = ConexionBD.conectar();
			PreparedStatement sentencia = conexion.prepareStatement(sql);

			sentencia.setString(1, taller.getNombreTaller());
			sentencia.setString(2, taller.getTipoTaller());

			int filasInsertadas = sentencia.executeUpdate();

			sentencia.close();
			conexion.close();

			return filasInsertadas > 0;

		} catch (SQLException e) {
			System.out.println("Error al insertar el taller.");
			System.out.println(e.getMessage());
			return false;
		}
	}

	/**
	 * Modifica un taller existente en la base de datos.
	 * 
	 * @return true si se modificó correctamente
	 */
	public boolean modificarTaller(Taller taller) {
		String sql = "UPDATE TALLER SET nombre = ?, tipo = ? WHERE ID_taller = ?";

		try {
			Connection conexion = ConexionBD.conectar();
			PreparedStatement sentencia = conexion.prepareStatement(sql);

			sentencia.setString(1, taller.getNombreTaller());
			sentencia.setString(2, taller.getTipoTaller());
			sentencia.setInt(3, taller.getIdTaller());

			int filasModificadas = sentencia.executeUpdate();

			sentencia.close();
			conexion.close();

			return filasModificadas > 0;

		} catch (SQLException e) {
			System.out.println("Error al modificar el taller.");
			System.out.println(e.getMessage());
			return false;
		}
	}

	/**
	 * Elimina un taller por su ID.
	 * 
	 * @return true si se eliminó correctamente
	 */
	public boolean eliminarTaller(int idTaller) {
		String sql = "DELETE FROM TALLER WHERE ID_taller = ?";

		try {
			Connection conexion = ConexionBD.conectar();
			PreparedStatement sentencia = conexion.prepareStatement(sql);

			sentencia.setInt(1, idTaller);

			int filasEliminadas = sentencia.executeUpdate();

			sentencia.close();
			conexion.close();

			return filasEliminadas > 0;

		} catch (SQLException e) {
			System.out.println("Error al eliminar el taller.");
			System.out.println(e.getMessage());
			return false;
		}
	}
}
