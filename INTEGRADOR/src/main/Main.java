package main;

import vista.VentanaLogin;

/**
 * Clase principal que arranca todo el programa
 * 
 */
public class Main {

	/**
	 * llamamos al metodo de la clase VentanaLogin
	 */
	public static void main(String[] args) {
		VentanaLogin ventanaLogin = new VentanaLogin();
		ventanaLogin.setVisible(true);
	}
}

