package main;

import vista.VentanaLogin;

/**
 * Clase principal que inicia la aplicación mostrando la ventana de login.
 */
public class Main {

	/**
	 * Punto de entrada del programa.
	 */
	public static void main(String[] args) {
		VentanaLogin ventanaLogin = new VentanaLogin();
		ventanaLogin.setVisible(true);
	}
}
