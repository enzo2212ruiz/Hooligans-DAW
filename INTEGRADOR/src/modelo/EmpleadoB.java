package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Clase encargada de realizar las operaciones de base de datos
 * relacionadas con los empleados.
 */
public class EmpleadoB {

	/**
	 * Comprueba si existe un empleado con el usuario y contraseña recibidos.
	 * Si existe, devuelve el empleado con todos sus datos.
	 * Si no existe, devuelve null.
	 */
	public Empleado comprobarLogin(String apodoEmpleado, String passwordEmpleado) {
		Empleado empleado = null;

		String sql = "SELECT * FROM EMPLEADO WHERE apodo = ? AND password = ?";

		try {
			Connection conexion = ConexionBD.conectar();
			PreparedStatement sentencia = conexion.prepareStatement(sql);

			sentencia.setString(1, apodoEmpleado);
			sentencia.setString(2, passwordEmpleado);

			ResultSet resultado = sentencia.executeQuery();

			if (resultado.next()) {
				empleado = new Empleado();

				empleado.setIdEmpleado(resultado.getInt("ID_empleado"));
				empleado.setNombreEmpleado(resultado.getString("nom_ape"));
				empleado.setApodoEmpleado(resultado.getString("apodo"));
				empleado.setPasswordEmpleado(resultado.getString("password"));
				empleado.setCategoriaEmpleado(resultado.getString("categoria"));
			}

			resultado.close();
			sentencia.close();
			conexion.close();

		} catch (SQLException e) {
			System.out.println("Error al comprobar el login.");
			System.out.println(e.getMessage());
		}

		return empleado;
	}

	/**
	 * Obtiene todos los empleados almacenados en la base de datos.
	 */
	public ArrayList<Empleado> obtenerEmpleados() {
		ArrayList<Empleado> listaEmpleados = new ArrayList<Empleado>();

		String sql = "SELECT * FROM EMPLEADO ORDER BY ID_empleado";

		try {
			Connection conexion = ConexionBD.conectar();
			PreparedStatement sentencia = conexion.prepareStatement(sql);
			ResultSet resultado = sentencia.executeQuery();

			while (resultado.next()) {
				Empleado empleado = new Empleado();

				empleado.setIdEmpleado(resultado.getInt("ID_empleado"));
				empleado.setNombreEmpleado(resultado.getString("nom_ape"));
				empleado.setApodoEmpleado(resultado.getString("apodo"));
				empleado.setPasswordEmpleado(resultado.getString("password"));
				empleado.setCategoriaEmpleado(resultado.getString("categoria"));

				listaEmpleados.add(empleado);
			}

			resultado.close();
			sentencia.close();
			conexion.close();

		} catch (SQLException e) {
			System.out.println("Error al obtener los empleados.");
			System.out.println(e.getMessage());
		}

		return listaEmpleados;
	}

	/**
	 * Inserta un nuevo empleado en la base de datos.
	 */
	public boolean insertarEmpleado(Empleado empleado) {
		String sql = "INSERT INTO EMPLEADO (nom_ape, apodo, password, categoria) VALUES (?, ?, ?, ?)";

		try {
			Connection conexion = ConexionBD.conectar();
			PreparedStatement sentencia = conexion.prepareStatement(sql);

			sentencia.setString(1, empleado.getNombreEmpleado());
			sentencia.setString(2, empleado.getApodoEmpleado());
			sentencia.setString(3, empleado.getPasswordEmpleado());
			sentencia.setString(4, empleado.getCategoriaEmpleado());

			int filasInsertadas = sentencia.executeUpdate();

			sentencia.close();
			conexion.close();

			return filasInsertadas > 0;

		} catch (SQLException e) {
			System.out.println("Error al insertar el empleado.");
			System.out.println(e.getMessage());
			return false;
		}
	}

	/**
	 * Modifica un empleado existente en la base de datos.
	 */
	public boolean modificarEmpleado(Empleado empleado) {
		String sql = "UPDATE EMPLEADO SET nom_ape = ?, apodo = ?, password = ?, categoria = ? WHERE ID_empleado = ?";

		try {
			Connection conexion = ConexionBD.conectar();
			PreparedStatement sentencia = conexion.prepareStatement(sql);

			sentencia.setString(1, empleado.getNombreEmpleado());
			sentencia.setString(2, empleado.getApodoEmpleado());
			sentencia.setString(3, empleado.getPasswordEmpleado());
			sentencia.setString(4, empleado.getCategoriaEmpleado());
			sentencia.setInt(5, empleado.getIdEmpleado());

			int filasModificadas = sentencia.executeUpdate();

			sentencia.close();
			conexion.close();

			return filasModificadas > 0;

		} catch (SQLException e) {
			System.out.println("Error al modificar el empleado.");
			System.out.println(e.getMessage());
			return false;
		}
	}

	/*
	 * Elimina un empleado por su ID.vamo
	 */
	public boolean eliminarEmpleado(int idEmpleado) {
		String sql = "DELETE FROM EMPLEADO WHERE ID_empleado = ?";

		try {
			Connection conexion = ConexionBD.conectar();
			PreparedStatement sentencia = conexion.prepareStatement(sql);

			sentencia.setInt(1, idEmpleado);

			int filasEliminadas = sentencia.executeUpdate();

			sentencia.close();
			conexion.close();

			return filasEliminadas > 0;

		} catch (SQLException e) {
			System.out.println("Error al eliminar el empleado.");
			System.out.println(e.getMessage());
			return false;
		}
	}
}
