package modelo;

/**
 * Clase modelo que representa un cliente del sistema.
 * Guarda los datos principales de un cliente.
 */
public class Cliente {

	private int idCliente;
	private String nombreCliente;
	private String superpoderCliente;

	/**
	 * Constructor vacío que permite crear un cliente sin datos
	 * para rellenarlo posteriormente con los métodos set.
	 */
	public Cliente() {
	}

	/**
	 * Constructor con todos los datos del cliente.
	 */
	public Cliente(int idCliente, String nombreCliente, String superpoderCliente) {
		this.idCliente = idCliente;
		this.nombreCliente = nombreCliente;
		this.superpoderCliente = superpoderCliente;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	/**
	 * Devuelve el nombre del cliente para mostrarlo correctamente
	 * en componentes como listas o desplegables.
	 */
	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getSuperpoderCliente() {
		return superpoderCliente;
	}

	public void setSuperpoderCliente(String superpoderCliente) {
		this.superpoderCliente = superpoderCliente;
	}

	/**
	 * Devuelve el nombre del cliente como representación del objeto.
	 */
	@Override
	public String toString() {
		return nombreCliente;
	}
}
