package modelo;

public class Taller {

	private int idTaller;
	private String nombreTaller;
	private String tipoTaller;

	public Taller() {
	}

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

	@Override
	public String toString() {
		return nombreTaller;
	}
}