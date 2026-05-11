package controlador;

import java.util.ArrayList;

import modelo.Taller;
import modelo.TallerB;

/**
 * Controlador encargado de gestionar la lógica relacionada con los talleres.
 * Recibe datos desde la vista, los valida y llama a TallerB para acceder a la base de datos.
 */
public class TallerControlador {

	private TallerB tallerB;

	/**
	 * Crea el controlador e inicializa la clase que trabaja con la base de datos de talleres.
	 */
	public TallerControlador() {
		tallerB = new TallerB();
	}

	/**
	 * Obtiene todos los talleres llamando a TallerB.
	 */
	public ArrayList<Taller> obtenerTalleres() {
		return tallerB.obtenerTalleres();
	}

	/**
	 * Valida los datos recibidos desde el formulario y guarda un taller nuevo.
	 */
	public boolean guardarTaller(String nombreTaller, String tipoTaller) {

		if (nombreTaller == null || nombreTaller.trim().isEmpty()) {
			return false;
		}

		if (tipoTaller == null || tipoTaller.trim().isEmpty()) {
			return false;
		}

		Taller taller = new Taller();
		taller.setNombreTaller(nombreTaller);
		taller.setTipoTaller(tipoTaller);

		return tallerB.insertarTaller(taller);
	}

	/**
	 * Modifica un taller existente si los datos son válidos.
	 * 
	 * @return true si se modificó correctamente
	 */
	public boolean modificarTaller(int idTaller, String nombreTaller, String tipoTaller) {

		if (idTaller <= 0) {
			return false;
		}

		if (nombreTaller == null || nombreTaller.trim().isEmpty()) {
			return false;
		}

		if (tipoTaller == null || tipoTaller.trim().isEmpty()) {
			return false;
		}

		Taller taller = new Taller();
		taller.setIdTaller(idTaller);
		taller.setNombreTaller(nombreTaller);
		taller.setTipoTaller(tipoTaller);

		return tallerB.modificarTaller(taller);
	}

	/**
	 * Elimina un taller por su ID.
	 */
	public boolean eliminarTaller(int idTaller) {

		if (idTaller <= 0) {
			return false;
		}

		return tallerB.eliminarTaller(idTaller);
	}
}
