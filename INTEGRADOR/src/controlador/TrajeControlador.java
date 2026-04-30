package controlador;

import java.util.ArrayList;

import modelo.Cliente;
import modelo.ClienteB;
import modelo.Traje;
import modelo.TrajeB;

public class TrajeControlador {

	private TrajeB trajeB;
	private ClienteB clienteB;

	public TrajeControlador() {
		trajeB = new TrajeB();
		clienteB = new ClienteB();
	}

	public ArrayList<Traje> obtenerTrajes() {
		return trajeB.obtenerTrajes();
	}

	public ArrayList<Cliente> obtenerClientes() {
		return clienteB.obtenerClientes();
	}

	public boolean guardarTraje(String nombreTraje, String estadoTraje, int idCliente) {

		if (nombreTraje == null || nombreTraje.trim().isEmpty()) {
			return false;
		}

		if (estadoTraje == null || estadoTraje.trim().isEmpty()) {
			return false;
		}

		if (idCliente <= 0) {
			return false;
		}

		Traje traje = new Traje();
		traje.setNombreTraje(nombreTraje);
		traje.setEstadoTraje(estadoTraje);
		traje.setIdCliente(idCliente);

		return trajeB.insertarTraje(traje);
	}

	public boolean modificarTraje(int idTraje, String nombreTraje, String estadoTraje, int idCliente) {

		if (idTraje <= 0) {
			return false;
		}

		if (nombreTraje == null || nombreTraje.trim().isEmpty()) {
			return false;
		}

		if (estadoTraje == null || estadoTraje.trim().isEmpty()) {
			return false;
		}

		if (idCliente <= 0) {
			return false;
		}

		Traje traje = new Traje();
		traje.setIdTraje(idTraje);
		traje.setNombreTraje(nombreTraje);
		traje.setEstadoTraje(estadoTraje);
		traje.setIdCliente(idCliente);

		return trajeB.modificarTraje(traje);
	}

	public boolean eliminarTraje(int idTraje) {

		if (idTraje <= 0) {
			return false;
		}

		return trajeB.eliminarTraje(idTraje);
	}
}