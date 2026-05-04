package controlador;

import java.util.ArrayList;

import modelo.Empleado;
import modelo.EmpleadoB;

/**
 * Controlador que gestiona las operaciones relacionadas con los empleados.
 */
public class EmpleadoControlador {

	private EmpleadoB empleadoB;

	/**
	 * Inicializa el acceso a los datos de empleados.
	 */
	public EmpleadoControlador() {
		empleadoB = new EmpleadoB();
	}

	/**
	 * Obtiene la lista de empleados.
	 * 
	 * @return lista de empleados
	 */
	public ArrayList<Empleado> obtenerEmpleados() {
		return empleadoB.obtenerEmpleados();
	}

	/**
	 * Guarda un nuevo empleado si los datos son válidos.
	 * 
	 * @return true si se guardó correctamente
	 */
	public boolean guardarEmpleado(String nombreEmpleado, String apodoEmpleado, String passwordEmpleado,
			String categoriaEmpleado) {

		if (nombreEmpleado == null || nombreEmpleado.trim().isEmpty()) {
			return false;
		}

		if (apodoEmpleado == null || apodoEmpleado.trim().isEmpty()) {
			return false;
		}

		if (passwordEmpleado == null || passwordEmpleado.trim().isEmpty()) {
			return false;
		}

		if (categoriaEmpleado == null || categoriaEmpleado.trim().isEmpty()) {
			return false;
		}

		Empleado empleado = new Empleado();
		empleado.setNombreEmpleado(nombreEmpleado);
		empleado.setApodoEmpleado(apodoEmpleado);
		empleado.setPasswordEmpleado(passwordEmpleado);
		empleado.setCategoriaEmpleado(categoriaEmpleado);

		return empleadoB.insertarEmpleado(empleado);
	}

	/**
	 * Modifica un empleado existente si los datos son válidos.
	 * 
	 * @return true si se modificó correctamente
	 */
	public boolean modificarEmpleado(int idEmpleado, String nombreEmpleado, String apodoEmpleado,
			String passwordEmpleado, String categoriaEmpleado) {

		if (idEmpleado <= 0) {
			return false;
		}

		if (nombreEmpleado == null || nombreEmpleado.trim().isEmpty()) {
			return false;
		}

		if (apodoEmpleado == null || apodoEmpleado.trim().isEmpty()) {
			return false;
		}

		if (passwordEmpleado == null || passwordEmpleado.trim().isEmpty()) {
			return false;
		}

		if (categoriaEmpleado == null || categoriaEmpleado.trim().isEmpty()) {
			return false;
		}

		Empleado empleado = new Empleado();
		empleado.setIdEmpleado(idEmpleado);
		empleado.setNombreEmpleado(nombreEmpleado);
		empleado.setApodoEmpleado(apodoEmpleado);
		empleado.setPasswordEmpleado(passwordEmpleado);
		empleado.setCategoriaEmpleado(categoriaEmpleado);

		return empleadoB.modificarEmpleado(empleado);
	}

	/**
	 * Elimina un empleado por su ID.
	 * 
	 * @return true si se eliminó correctamente
	 */
	public boolean eliminarEmpleado(int idEmpleado) {

		if (idEmpleado <= 0) {
			return false;
		}

		return empleadoB.eliminarEmpleado(idEmpleado);
	}
}
