package controlador;

import java.util.ArrayList;

import modelo.Taller;
import modelo.TallerB;

/**
 * Controlador que gestiona las operaciones relacionadas con los talleres.
 */
public class TallerControlador {

	private TallerB tallerB;

	/**
	 * Inicializa el acceso a los datos de talleres.
	 */
	public TallerControlador() {
		tallerB = new TallerB();
	}

	/**
	 * Obtiene la lista de talleres.
	 * 
	 * @return lista de talleres
	 */
	public ArrayList<Taller> obtenerTalleres() {
		return tallerB.obtenerTalleres();
	}

	/**
	 * Guarda un nuevo taller si los datos son válidos.
	 * 
	 * @return true si se guardó correctamente
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
	 * 
	 * @return true si se eliminó correctamente
	 */
	public boolean eliminarTaller(int idTaller) {

		if (idTaller <= 0) {
			return false;
		}

		return tallerB.eliminarTaller(idTaller);
	}
}
