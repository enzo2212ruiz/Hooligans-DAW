package controlador;

import java.util.ArrayList;

import modelo.Cliente;
import modelo.ClienteB;
import modelo.Traje;
import modelo.TrajeB;

/**
 * Controlador encargado de gestionar la lógica relacionada con los trajes.
 * Recibe datos desde la vista, los valida y llama a TrajeB para acceder a la base de datos.
 */
public class TrajeControlador {

	private TrajeB trajeB;
	private ClienteB clienteB;

	/**
	 * Crea el controlador e inicializa la clase que trabaja con la base de datos de trajes.
	 */
	public TrajeControlador() {
		trajeB = new TrajeB();
		clienteB = new ClienteB();
	}

	/**
	 * Obtiene todos los trajes llamando a TrajeB.
	 */
	public ArrayList<Traje> obtenerTrajes() {
		return trajeB.obtenerTrajes();
	}

	/**
	 * Obtiene la lista de clientes.
	 * 
	 * @return lista de clientes
	 */
	public ArrayList<Cliente> obtenerClientes() {
		return clienteB.obtenerClientes();
	}

	/**
	 * Valida los datos recibidos desde el formulario y guarda un traje nuevo.
	 */
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

	/**
	 * Valida los datos recibidos desde el formulario y modifica un traje existente.
	 */
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

	/**
	 * Elimina un traje de la base de datos usando su identificador.
	 */
	public boolean eliminarTraje(int idTraje) {

		if (idTraje <= 0) {
			return false;
		}

		return trajeB.eliminarTraje(idTraje);
	}
}
