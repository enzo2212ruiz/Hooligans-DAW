package controlador;

import modelo.Empleado;
import modelo.EmpleadoB;

public class LoginControlador {

	private EmpleadoB empleadoB;

	public LoginControlador() {
		empleadoB = new EmpleadoB();
	}

	public Empleado iniciarSesion(String apodoEmpleado, String passwordEmpleado) {

		if (apodoEmpleado == null || apodoEmpleado.trim().isEmpty()) {
			return null;
		}

		if (passwordEmpleado == null || passwordEmpleado.trim().isEmpty()) {
			return null;
		}

		return empleadoB.comprobarLogin(apodoEmpleado, passwordEmpleado);
	}
}