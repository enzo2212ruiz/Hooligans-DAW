package controlador;

import java.util.ArrayList;

import modelo.Empleado;
import modelo.EmpleadoB;

public class EmpleadoControlador {

	private EmpleadoB empleadoB;

	public EmpleadoControlador() {
		empleadoB = new EmpleadoB();
	}

	public ArrayList<Empleado> obtenerEmpleados() {
		return empleadoB.obtenerEmpleados();
	}

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

	public boolean eliminarEmpleado(int idEmpleado) {

		if (idEmpleado <= 0) {
			return false;
		}

		return empleadoB.eliminarEmpleado(idEmpleado);
	}
}