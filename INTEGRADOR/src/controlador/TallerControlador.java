package controlador;

import java.util.ArrayList;

import modelo.Taller;
import modelo.TallerB;

public class TallerControlador {

	private TallerB tallerB;

	public TallerControlador() {
		tallerB = new TallerB();
	}

	public ArrayList<Taller> obtenerTalleres() {
		return tallerB.obtenerTalleres();
	}

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

	public boolean eliminarTaller(int idTaller) {

		if (idTaller <= 0) {
			return false;
		}

		return tallerB.eliminarTaller(idTaller);
	}
}