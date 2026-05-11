
package modelo;

/**
 * Clase modelo que representa un empleado del sistema.
 * Guarda sus datos principales y permite comprobar su rol.
 */
public class Empleado {

	private int idEmpleado;
	private String nombreEmpleado;
	private String apodoEmpleado;
	private String passwordEmpleado;
	private String categoriaEmpleado;

	/**
	 * Constructor vacío que permite crear un empleado sin datos
	 * para rellenarlo posteriormente con los métodos set.
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

	//GETTERS Y SETTERS
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
	 * Comprueba si el empleado tiene rol de maestro, oficial o aprendiz.
	 */
	public boolean esMaestro() {
		return "MAESTRO".equalsIgnoreCase(categoriaEmpleado);
	}

	
	public boolean esOficial() {
		return "OFICIAL".equalsIgnoreCase(categoriaEmpleado);
	}

	
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
