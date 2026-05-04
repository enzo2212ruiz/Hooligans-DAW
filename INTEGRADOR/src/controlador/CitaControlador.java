package controlador;

import java.util.ArrayList;

import modelo.Cita;
import modelo.CitaB;
import modelo.Empleado;
import modelo.EmpleadoB;
import modelo.Taller;
import modelo.TallerB;
import modelo.Traje;
import modelo.TrajeB;

/**
 * Controlador que gestiona las operaciones de las citas: obtener, guardar,
 * modificar y eliminar.
 */
public class CitaControlador {

	private CitaB citaB;
	private TallerB tallerB;
	private EmpleadoB empleadoB;
	private TrajeB trajeB;

	/**
	 * Inicializa los objetos necesarios para acceder a los datos.
	 */
	public CitaControlador() {
		citaB = new CitaB();
		tallerB = new TallerB();
		empleadoB = new EmpleadoB();
		trajeB = new TrajeB();
	}

	/**
	 * Obtiene todas las citas.
	 */
	public ArrayList<Cita> obtenerCitas() {
		return citaB.obtenerCitas();
	}

	/**
	 * Obtiene todos los talleres.
	 */
	public ArrayList<Taller> obtenerTalleres() {
		return tallerB.obtenerTalleres();
	}

	/**
	 * Obtiene todos los empleados.
	 */
	public ArrayList<Empleado> obtenerEmpleados() {
		return empleadoB.obtenerEmpleados();
	}

	/**
	 * Obtiene todos los trajes.
	 */
	public ArrayList<Traje> obtenerTrajes() {
		return trajeB.obtenerTrajes();
	}

	/**
	 * Guarda una nueva cita si los datos son válidos.
	 * 
	 * @return true si se guardó correctamente
	 */
	public boolean guardarCita(String fechaCita, String horaCita, String duracionCita, int idTaller, int idResponsable,
			int idTraje) {

		if (fechaCita == null || fechaCita.trim().isEmpty())
			return false;
		if (horaCita == null || horaCita.trim().isEmpty())
			return false;
		if (duracionCita == null || duracionCita.trim().isEmpty())
			return false;
		if (idTaller <= 0 || idResponsable <= 0 || idTraje <= 0)
			return false;

		int duracion;
		try {
			duracion = Integer.parseInt(duracionCita);
		} catch (NumberFormatException e) {
			return false;
		}

		if (duracion <= 0)
			return false;

		Cita cita = new Cita();
		cita.setFechaCita(fechaCita);
		cita.setHoraCita(horaCita);
		cita.setDuracionCita(duracion);
		cita.setIdTaller(idTaller);
		cita.setIdResponsable(idResponsable);
		cita.setIdTraje(idTraje);

		return citaB.insertarCita(cita);
	}

	/**
	 * Modifica una cita existente si los datos son válidos.
	 * 
	 * @return true si se modificó correctamente
	 */
	public boolean modificarCita(int idCita, String fechaCita, String horaCita, String duracionCita, int idTaller,
			int idResponsable, int idTraje) {

		if (idCita <= 0)
			return false;
		if (fechaCita == null || fechaCita.trim().isEmpty())
			return false;
		if (horaCita == null || horaCita.trim().isEmpty())
			return false;
		if (duracionCita == null || duracionCita.trim().isEmpty())
			return false;
		if (idTaller <= 0 || idResponsable <= 0 || idTraje <= 0)
			return false;

		int duracion;
		try {
			duracion = Integer.parseInt(duracionCita);
		} catch (NumberFormatException e) {
			return false;
		}

		if (duracion <= 0)
			return false;

		Cita cita = new Cita();
		cita.setIdCita(idCita);
		cita.setFechaCita(fechaCita);
		cita.setHoraCita(horaCita);
		cita.setDuracionCita(duracion);
		cita.setIdTaller(idTaller);
		cita.setIdResponsable(idResponsable);
		cita.setIdTraje(idTraje);

		return citaB.modificarCita(cita);
	}

	/**
	 * Elimina una cita por su ID.
	 * 
	 * @return true si se eliminó correctamente
	 */
	public boolean eliminarCita(int idCita) {

		if (idCita <= 0)
			return false;

		return citaB.eliminarCita(idCita);
	}
}
