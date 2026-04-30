package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClienteB {

	public ArrayList<Cliente> obtenerClientes() {
		ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();

		String sql = "SELECT * FROM CLIENTE ORDER BY ID_cliente";

		try {
			Connection conexion = ConexionBD.conectar();
			PreparedStatement sentencia = conexion.prepareStatement(sql);
			ResultSet resultado = sentencia.executeQuery();

			while (resultado.next()) {
				Cliente cliente = new Cliente();

				cliente.setIdCliente(resultado.getInt("ID_cliente"));
				cliente.setNombreCliente(resultado.getString("nombre"));
				cliente.setSuperpoderCliente(resultado.getString("superpoder"));

				listaClientes.add(cliente);
			}

			resultado.close();
			sentencia.close();
			conexion.close();

		} catch (SQLException e) {
			System.out.println("Error al obtener los clientes.");
			System.out.println(e.getMessage());
		}

		return listaClientes;
	}

	public boolean insertarCliente(Cliente cliente) {
		String sql = "INSERT INTO CLIENTE (nombre, superpoder) VALUES (?, ?)";

		try {
			Connection conexion = ConexionBD.conectar();
			PreparedStatement sentencia = conexion.prepareStatement(sql);

			sentencia.setString(1, cliente.getNombreCliente());
			sentencia.setString(2, cliente.getSuperpoderCliente());

			int filasInsertadas = sentencia.executeUpdate();

			sentencia.close();
			conexion.close();

			return filasInsertadas > 0;

		} catch (SQLException e) {
			System.out.println("Error al insertar el cliente.");
			System.out.println(e.getMessage());
			return false;
		}
	}

	public boolean modificarCliente(Cliente cliente) {
		String sql = "UPDATE CLIENTE SET nombre = ?, superpoder = ? WHERE ID_cliente = ?";

		try {
			Connection conexion = ConexionBD.conectar();
			PreparedStatement sentencia = conexion.prepareStatement(sql);

			sentencia.setString(1, cliente.getNombreCliente());
			sentencia.setString(2, cliente.getSuperpoderCliente());
			sentencia.setInt(3, cliente.getIdCliente());

			int filasModificadas = sentencia.executeUpdate();

			sentencia.close();
			conexion.close();

			return filasModificadas > 0;

		} catch (SQLException e) {
			System.out.println("Error al modificar el cliente.");
			System.out.println(e.getMessage());
			return false;
		}
	}

	public boolean eliminarCliente(int idCliente) {
		String sql = "DELETE FROM CLIENTE WHERE ID_cliente = ?";

		try {
			Connection conexion = ConexionBD.conectar();
			PreparedStatement sentencia = conexion.prepareStatement(sql);

			sentencia.setInt(1, idCliente);

			int filasEliminadas = sentencia.executeUpdate();

			sentencia.close();
			conexion.close();

			return filasEliminadas > 0;

		} catch (SQLException e) {
			System.out.println("Error al eliminar el cliente.");
			System.out.println(e.getMessage());
			return false;
		}
	}
}