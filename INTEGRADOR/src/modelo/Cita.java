package modelo;

/**
 * Modelo que representa una cita con toda su información.
 */
public class Cita {

	private int idCita;
	private String fechaCita;
	private String horaCita;
	private int duracionCita;

	private int idTaller;
	private int idResponsable;
	private int idTraje;

	private String nombreTaller;
	private String nombreResponsable;
	private String nombreTraje;

	/**
	 * Constructor vacío.
	 */
	public Cita() {
	}

	/**
	 * Constructor con todos los datos de la cita.
	 */
	public Cita(int idCita, String fechaCita, String horaCita, int duracionCita, int idTaller, int idResponsable,
			int idTraje, String nombreTaller, String nombreResponsable, String nombreTraje) {
		this.idCita = idCita;
		this.fechaCita = fechaCita;
		this.horaCita = horaCita;
		this.duracionCita = duracionCita;
		this.idTaller = idTaller;
		this.idResponsable = idResponsable;
		this.idTraje = idTraje;
		this.nombreTaller = nombreTaller;
		this.nombreResponsable = nombreResponsable;
		this.nombreTraje = nombreTraje;
	}

	public int getIdCita() {
		return idCita;
	}

	public void setIdCita(int idCita) {
		this.idCita = idCita;
	}

	public String getFechaCita() {
		return fechaCita;
	}

	public void setFechaCita(String fechaCita) {
		this.fechaCita = fechaCita;
	}

	public String getHoraCita() {
		return horaCita;
	}

	public void setHoraCita(String horaCita) {
		this.horaCita = horaCita;
	}

	public int getDuracionCita() {
		return duracionCita;
	}

	public void setDuracionCita(int duracionCita) {
		this.duracionCita = duracionCita;
	}

	public int getIdTaller() {
		return idTaller;
	}

	public void setIdTaller(int idTaller) {
		this.idTaller = idTaller;
	}

	public int getIdResponsable() {
		return idResponsable;
	}

	public void setIdResponsable(int idResponsable) {
		this.idResponsable = idResponsable;
	}

	public int getIdTraje() {
		return idTraje;
	}

	public void setIdTraje(int idTraje) {
		this.idTraje = idTraje;
	}

	public String getNombreTaller() {
		return nombreTaller;
	}

	public void setNombreTaller(String nombreTaller) {
		this.nombreTaller = nombreTaller;
	}

	public String getNombreResponsable() {
		return nombreResponsable;
	}

	public void setNombreResponsable(String nombreResponsable) {
		this.nombreResponsable = nombreResponsable;
	}

	public String getNombreTraje() {
		return nombreTraje;
	}

	public void setNombreTraje(String nombreTraje) {
		this.nombreTraje = nombreTraje;
	}

	/**
	 * Devuelve una representación simple de la cita.
	 */
	@Override
	public String toString() {
		return fechaCita + " - " + horaCita;
	}
}
