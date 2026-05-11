package modelo;

/**
 * Clase modelo que representa un taller del sistema.
 * Guarda los datos principales de un taller.
 */
public class Taller {

	private int idTaller;
	private String nombreTaller;
	private String tipoTaller;

	/**
	 * Constructor vacío que permite crear un taller sin datos
	 * para rellenarlo posteriormente con los métodos set.
	 */
	public Taller() {
	}

	/**
	 * Constructor con todos los datos del taller.
	 */
	public Taller(int idTaller, String nombreTaller, String tipoTaller) {
		this.idTaller = idTaller;
		this.nombreTaller = nombreTaller;
		this.tipoTaller = tipoTaller;
	}

	public int getIdTaller() {
		return idTaller;
	}

	public void setIdTaller(int idTaller) {
		this.idTaller = idTaller;
	}

	/**
	 * Devuelve el nombre del taller para mostrarlo correctamente
	 * en componentes como listas o desplegables.
	 */
	public String getNombreTaller() {
		return nombreTaller;
	}

	public void setNombreTaller(String nombreTaller) {
		this.nombreTaller = nombreTaller;
	}

	public String getTipoTaller() {
		return tipoTaller;
	}

	public void setTipoTaller(String tipoTaller) {
		this.tipoTaller = tipoTaller;
	}

	/**
	 * Devuelve el nombre del taller como representación del objeto.
	 */
	@Override
	public String toString() {
		return nombreTaller;
	}
}
