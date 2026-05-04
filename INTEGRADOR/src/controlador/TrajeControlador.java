package controlador;

import java.util.ArrayList;

import modelo.Cliente;
import modelo.ClienteB;
import modelo.Traje;
import modelo.TrajeB;

/**
 * Controlador que gestiona las operaciones relacionadas con los trajes.
 */
public class TrajeControlador {

	private TrajeB trajeB;
	private ClienteB clienteB;

	/**
	 * Inicializa el acceso a los datos de trajes y clientes.
	 */
	public TrajeControlador() {
		trajeB = new TrajeB();
		clienteB = new ClienteB();
	}

	/**
	 * Obtiene la lista de trajes.
	 * 
	 * @return lista de trajes
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
	 * Guarda un nuevo traje si los datos son válidos.
	 * 
	 * @return true si se guardó correctamente
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
	 * Modifica un traje existente si los datos son válidos.
	 * 
	 * @return true si se modificó correctamente
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
	 * Elimina un traje por su ID.
	 * 
	 * @return true si se eliminó correctamente
	 */
	public boolean eliminarTraje(int idTraje) {

		if (idTraje <= 0) {
			return false;
		}

		return trajeB.eliminarTraje(idTraje);
	}
}
