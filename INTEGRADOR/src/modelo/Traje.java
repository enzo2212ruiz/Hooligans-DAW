package modelo;

/**
 * Clase modelo que representa un traje del sistema.
 * Guarda los datos principales de un traje.
 */
public class Traje {

	private int idTraje;
	private String nombreTraje;
	private String estadoTraje;
	private int idCliente;
	private String nombreCliente;

	/**
	 * Constructor vacío que permite crear un traje sin datos
	 * para rellenarlo posteriormente con los métodos set.
	 */
	public Traje() {
	}

	/**
	 * Constructor con todos los datos del traje.
	 */
	public Traje(int idTraje, String nombreTraje, String estadoTraje, int idCliente, String nombreCliente) {
		this.idTraje = idTraje;
		this.nombreTraje = nombreTraje;
		this.estadoTraje = estadoTraje;
		this.idCliente = idCliente;
		this.nombreCliente = nombreCliente;
	}

	public int getIdTraje() {
		return idTraje;
	}

	public void setIdTraje(int idTraje) {
		this.idTraje = idTraje;
	}

	/**
	 * Devuelve el nombre del traje para mostrarlo correctamente
	 * en componentes como listas o desplegables.
	 */
	public String getNombreTraje() {
		return nombreTraje;
	}

	public void setNombreTraje(String nombreTraje) {
		this.nombreTraje = nombreTraje;
	}

	public String getEstadoTraje() {
		return estadoTraje;
	}

	public void setEstadoTraje(String estadoTraje) {
		this.estadoTraje = estadoTraje;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	/**
	 * Devuelve el nombre del traje como representación del objeto.
	 */
	@Override
	public String toString() {
		return nombreTraje;
	}
}
