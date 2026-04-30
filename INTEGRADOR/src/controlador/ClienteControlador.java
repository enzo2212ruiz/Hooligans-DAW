package controlador;

import java.util.ArrayList;

import modelo.Cliente;
import modelo.ClienteB;

public class ClienteControlador {

	private ClienteB clienteB;

	public ClienteControlador() {
		clienteB = new ClienteB();
	}

	public ArrayList<Cliente> obtenerClientes() {
		return clienteB.obtenerClientes();
	}

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

	public boolean eliminarCliente(int idCliente) {

		if (idCliente <= 0) {
			return false;
		}

		return clienteB.eliminarCliente(idCliente);
	}
}