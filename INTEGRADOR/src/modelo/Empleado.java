//Esta clase solo sirve para guardar los datos de un empleado.
//El toString() devuelve el nombre porque más adelante nos vendrá 
//bien para mostrar empleados en combos o listas.
package modelo;

public class Empleado {

	private int idEmpleado;
	private String nombreEmpleado;
	private String apodoEmpleado;
	private String passwordEmpleado;
	private String categoriaEmpleado;

	public Empleado() {
	}

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
	public boolean esMaestro() {
	    return "MAESTRO".equalsIgnoreCase(categoriaEmpleado);
	}

	public boolean esOficial() {
	    return "OFICIAL".equalsIgnoreCase(categoriaEmpleado);
	}

	public boolean esAprendiz() {
	    return "APRENDIZ".equalsIgnoreCase(categoriaEmpleado);
	}
	@Override
	public String toString() {
		return nombreEmpleado;
	}
}