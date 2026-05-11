package controlador;

import java.util.ArrayList;

import modelo.Cliente;
import modelo.ClienteB;

/**
 * Controlador encargado de gestionar la lógica relacionada con clientes.
 */
public class ClienteControlador {

	private ClienteB clienteB;

	/**
	 * Crea el controlador e inicializa la clase que trabaja con la base de datos de clientes.
	 */
	public ClienteControlador() {
		clienteB = new ClienteB();
	}

	/**
	 * Obtiene todos los clientes llamando a ClienteB.
	 */
	public ArrayList<Cliente> obtenerClientes() {
		return clienteB.obtenerClientes();
	}

	/**
	 * Valida los datos recibidos desde el formulario y guarda un cliente nuevo.
	 */
	public boolean guardarCliente(String nombreCliente, String superpoderCliente) {

		if (nombreCliente == null || nombreCliente.trim().isEmpty()) {
			return false;
		}

		if (superpoderCliente == null || superpoderCliente.trim().isEmpty()) {
			return false;
		}

		Cliente cliente = new Cliente();
		cliente.setNombreCliente(nombreCliente);
		cliente.setSuperpoderCliente(superpoderCliente);

		return clienteB.insertarCliente(cliente);
	}

	/**
	 * Valida los datos recibidos desde el formulario y modifica un cliente existente.
	 */
	public boolean modificarCliente(int idCliente, String nombreCliente, String superpoderCliente) {

		if (idCliente <= 0) {
			return false;
		}

		if (nombreCliente == null || nombreCliente.trim().isEmpty()) {
			return false;
		}

		if (superpoderCliente == null || superpoderCliente.trim().isEmpty()) {
			return false;
		}

		Cliente cliente = new Cliente();
		cliente.setIdCliente(idCliente);
		cliente.setNombreCliente(nombreCliente);
		cliente.setSuperpoderCliente(superpoderCliente);

		return clienteB.modificarCliente(cliente);
	}

	/**
	 * Elimina un cliente por su ID.
	 */
	public boolean eliminarCliente(int idCliente) {

		if (idCliente <= 0) {
			return false;
		}

		return clienteB.eliminarCliente(idCliente);
	}
}
