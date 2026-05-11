package controlador;

import modelo.Empleado;
import modelo.EmpleadoB;

/**
 * Controlador encargado de gestionar el inicio de sesión.
 * conectar la ventana de login con el modelo/base de datos.
 * Es decir es un ùente entre VentanaLogin -> LoginControlador -> EmpleadoB
 */
public class LoginControlador {

	private EmpleadoB empleadoB;

	/**
	 * CONSTRUCTOR
	 * Cuando se crea un LoginControlador, también se crea un EmpleadoB.
	 */
	public LoginControlador() {
		empleadoB = new EmpleadoB();
	}

	/**
	 * Valida el inicio de sesión llamando a la clase EmpleadoB.
	 * Si las credenciales son correctas, devuelve el empleado encontrado.
	 * Si no son correctas, devuelve null.
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
