package controlador;

import modelo.Empleado;
import modelo.EmpleadoB;

/**
 * Controlador encargado de gestionar el inicio de sesión.
 */
public class LoginControlador {

	private EmpleadoB empleadoB;

	/**
	 * Inicializa el acceso a los datos de empleados.
	 */
	public LoginControlador() {
		empleadoB = new EmpleadoB();
	}

	/**
	 * Intenta iniciar sesión comprobando apodo y contraseña.
	 * 
	 * @return el empleado si los datos son correctos, o null si no lo son
	 */
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
