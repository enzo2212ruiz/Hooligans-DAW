//Esta clase solo sirve para guardar los datos de un empleado.
//El toString() devuelve el nombre porque más adelante nos vendrá 
//bien para mostrar empleados en combos o listas.
package modelo;

/**
 * Modelo que representa a un empleado.
 */
public class Empleado {

	private int idEmpleado;
	private String nombreEmpleado;
	private String apodoEmpleado;
	private String passwordEmpleado;
	private String categoriaEmpleado;

	/**
	 * Constructor vacío.
	 */
	public Empleado() {
	}

	/**
	 * Constructor con todos los datos del empleado.
	 */
	public Empleado(int idEmpleado, String nombreEmpleado, String apodoEmpleado, String passwordEmpleado,
			String categoriaEmpleado) {
		this.idEmpleado = idEmpleado;
		this.nombreEmpleado = nombreEmpleado;
		this.apodoEmpleado = apodoEmpleado;
		this.passwordEmpleado = passwordEmpleado;
		this.categoriaEmpleado = categoriaEmpleado;
	}

	public int getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public String getNombreEmpleado() {
		return nombreEmpleado;
	}

	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}

	public String getApodoEmpleado() {
		return apodoEmpleado;
	}

	public void setApodoEmpleado(String apodoEmpleado) {
		this.apodoEmpleado = apodoEmpleado;
	}

	public String getPasswordEmpleado() {
		return passwordEmpleado;
	}

	public void setPasswordEmpleado(String passwordEmpleado) {
		this.passwordEmpleado = passwordEmpleado;
	}

	public String getCategoriaEmpleado() {
		return categoriaEmpleado;
	}

	public void setCategoriaEmpleado(String categoriaEmpleado) {
		this.categoriaEmpleado = categoriaEmpleado;
	}

	/**
	 * Indica si el empleado pertenece a la categoría MAESTRO.
	 */
	public boolean esMaestro() {
		return "MAESTRO".equalsIgnoreCase(categoriaEmpleado);
	}

	/**
	 * Indica si el empleado pertenece a la categoría OFICIAL.
	 */
	public boolean esOficial() {
		return "OFICIAL".equalsIgnoreCase(categoriaEmpleado);
	}

	/**
	 * Indica si el empleado pertenece a la categoría APRENDIZ.
	 */
	public boolean esAprendiz() {
		return "APRENDIZ".equalsIgnoreCase(categoriaEmpleado);
	}

	/**
	 * Devuelve el nombre del empleado como representación del objeto.
	 */
	@Override
	public String toString() {
		return nombreEmpleado;
	}
}
