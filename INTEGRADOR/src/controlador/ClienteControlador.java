package controlador;

import java.util.ArrayList;

import modelo.Cliente;
import modelo.ClienteB;

/**
 * Controlador encargado de gestionar las operaciones relacionadas con los
 * clientes.
 */
public class ClienteControlador {

	private ClienteB clienteB;

	/**
	 * Crea el controlador e inicializa el acceso a los datos de clientes.
	 */
	public ClienteControlador() {
		clienteB = new ClienteB();
	}

	/**
	 * Obtiene la lista completa de clientes.
	 * 
	 * @return lista de clientes
	 */
	public ArrayList<Cliente> obtenerClientes() {
		return clienteB.obtenerClientes();
	}

	/**
	 * Guarda un nuevo cliente si los datos son válidos.
	 * 
	 * @param nombreCliente     nombre del cliente
	 * @param superpoderCliente superpoder del cliente
	 * @return true si se guardó correctamente
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
	 * Modifica un cliente existente si los datos son válidos.
	 * 
	 * @param idCliente         id del cliente
	 * @param nombreCliente     nombre del cliente
	 * @param superpoderCliente superpoder del cliente
	 * @return true si se modificó correctamente
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
	 * 
	 * @param idCliente id del cliente
	 * @return true si se eliminó correctamente
	 */
	public boolean eliminarCliente(int idCliente) {

		if (idCliente <= 0) {
			return false;
		}

		return clienteB.eliminarCliente(idCliente);
	}
}
