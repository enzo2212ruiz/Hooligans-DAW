package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Clase encargada de realizar las operaciones de base de datos
 * relacionadas con los trajes.
 */
public class TrajeB {

	/**
	 * Obtiene todos los trajes almacenados en la base de datos
	 * y los devuelve en una lista de objetos Traje.
	 */
	public ArrayList<Traje> obtenerTrajes() {
		ArrayList<Traje> listaTrajes = new ArrayList<Traje>();

		String sql = "SELECT TRAJE.ID_traje, TRAJE.nombre, TRAJE.estado, "
				+ "TRAJE.ID_cliente, CLIENTE.nombre AS nombre_cliente " + "FROM TRAJE "
				+ "INNER JOIN CLIENTE ON TRAJE.ID_cliente = CLIENTE.ID_cliente " + "ORDER BY TRAJE.ID_traje";

		try {
			Connection conexion = ConexionBD.conectar();
			PreparedStatement sentencia = conexion.prepareStatement(sql);
			ResultSet resultado = sentencia.executeQuery();

			while (resultado.next()) {
				Traje traje = new Traje();

				traje.setIdTraje(resultado.getInt("ID_traje"));
				traje.setNombreTraje(resultado.getString("nombre"));
				traje.setEstadoTraje(resultado.getString("estado"));
				traje.setIdCliente(resultado.getInt("ID_cliente"));
				traje.setNombreCliente(resultado.getString("nombre_cliente"));

				listaTrajes.add(traje);
			}

			resultado.close();
			sentencia.close();
			conexion.close();

		} catch (SQLException e) {
			System.out.println("Error al obtener los trajes.");
			System.out.println(e.getMessage());
		}

		return listaTrajes;
	}

	/**
	 * Inserta un nuevo traje en la base de datos.
	 */
	public boolean insertarTraje(Traje traje) {
		String sql = "INSERT INTO TRAJE (nombre, estado, ID_cliente) VALUES (?, ?, ?)";

		try {
			Connection conexion = ConexionBD.conectar();
			PreparedStatement sentencia = conexion.prepareStatement(sql);

			sentencia.setString(1, traje.getNombreTraje());
			sentencia.setString(2, traje.getEstadoTraje());
			sentencia.setInt(3, traje.getIdCliente());

			int filasInsertadas = sentencia.executeUpdate();

			sentencia.close();
			conexion.close();

			return filasInsertadas > 0;

		} catch (SQLException e) {
			System.out.println("Error al insertar el traje.");
			System.out.println(e.getMessage());
			return false;
		}
	}

	/**
	 * Modifica un traje existente en la base de datos.
	 */
	public boolean modificarTraje(Traje traje) {
		String sql = "UPDATE TRAJE SET nombre = ?, estado = ?, ID_cliente = ? WHERE ID_traje = ?";

		try {
			Connection conexion = ConexionBD.conectar();
			PreparedStatement sentencia = conexion.prepareStatement(sql);

			sentencia.setString(1, traje.getNombreTraje());
			sentencia.setString(2, traje.getEstadoTraje());
			sentencia.setInt(3, traje.getIdCliente());
			sentencia.setInt(4, traje.getIdTraje());

			int filasModificadas = sentencia.executeUpdate();

			sentencia.close();
			conexion.close();

			return filasModificadas > 0;

		} catch (SQLException e) {
			System.out.println("Error al modificar el traje.");
			System.out.println(e.getMessage());
			return false;
		}
	}

	/**
	 * Elimina un traje por su ID.
	 */
	public boolean eliminarTraje(int idTraje) {
		String sql = "DELETE FROM TRAJE WHERE ID_traje = ?";

		try {
			Connection conexion = ConexionBD.conectar();
			PreparedStatement sentencia = conexion.prepareStatement(sql);

			sentencia.setInt(1, idTraje);

			int filasEliminadas = sentencia.executeUpdate();

			sentencia.close();
			conexion.close();

			return filasEliminadas > 0;

		} catch (SQLException e) {
			System.out.println("Error al eliminar el traje.");
			System.out.println(e.getMessage());
			return false;
		}
	}
}
