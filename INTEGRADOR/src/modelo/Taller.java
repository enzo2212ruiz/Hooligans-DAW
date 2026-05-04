package modelo;

/**
 * Modelo que representa un taller.
 */
public class Taller {

	private int idTaller;
	private String nombreTaller;
	private String tipoTaller;

	/**
	 * Constructor vacío.
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
