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

public class CitaControlador {

	private CitaB citaB;
	private TallerB tallerB;
	private EmpleadoB empleadoB;
	private TrajeB trajeB;

	public CitaControlador() {
		citaB = new CitaB();
		tallerB = new TallerB();
		empleadoB = new EmpleadoB();
		trajeB = new TrajeB();
	}

	public ArrayList<Cita> obtenerCitas() {
		return citaB.obtenerCitas();
	}

	public ArrayList<Taller> obtenerTalleres() {
		return tallerB.obtenerTalleres();
	}

	public ArrayList<Empleado> obtenerEmpleados() {
		return empleadoB.obtenerEmpleados();
	}

	public ArrayList<Empleado> obtenerResponsables() {
		ArrayList<Empleado> responsables = new ArrayList<Empleado>();

		for (Empleado empleado : empleadoB.obtenerEmpleados()) {
			if (empleado.esMaestro() || empleado.esOficial()) {
				responsables.add(empleado);
			}
		}

		return responsables;
	}

	public ArrayList<Empleado> obtenerAprendices() {
		ArrayList<Empleado> aprendices = new ArrayList<Empleado>();

		for (Empleado empleado : empleadoB.obtenerEmpleados()) {
			if (empleado.esAprendiz()) {
				aprendices.add(empleado);
			}
		}

		return aprendices;
	}

	public ArrayList<Traje> obtenerTrajes() {
		return trajeB.obtenerTrajes();
	}

	public boolean guardarCita(String fechaCita, String horaCita, String duracionCita, int idTaller, int idResponsable,
			int idTraje, int idAprendiz1, int idAprendiz2) {

		if (fechaCita == null || fechaCita.trim().isEmpty())
			return false;
		if (horaCita == null || horaCita.trim().isEmpty())
			return false;
		if (duracionCita == null || duracionCita.trim().isEmpty())
			return false;
		if (idTaller <= 0 || idResponsable <= 0 || idTraje <= 0)
			return false;
		if (idAprendiz1 > 0 && idAprendiz1 == idAprendiz2)
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
		cita.setIdAprendiz1(idAprendiz1);
		cita.setIdAprendiz2(idAprendiz2);

		return citaB.insertarCita(cita);
	}

	public boolean modificarCita(int idCita, String fechaCita, String horaCita, String duracionCita, int idTaller,
			int idResponsable, int idTraje, int idAprendiz1, int idAprendiz2) {

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
		if (idAprendiz1 > 0 && idAprendiz1 == idAprendiz2)
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
		cita.setIdAprendiz1(idAprendiz1);
		cita.setIdAprendiz2(idAprendiz2);

		return citaB.modificarCita(cita);
	}

	public boolean eliminarCita(int idCita) {
		if (idCita <= 0)
			return false;

		return citaB.eliminarCita(idCita);
	}
}
