package vista;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import controlador.CitaControlador;
import modelo.Cita;
import modelo.Empleado;

/**
 * Panel encargado de gestionar la visualización y administración de citas.
 */
public class PanelCitas extends JPanel {

	private static final long serialVersionUID = 1L;

	private JLabel lblTitulo;

	private JPanel panelTabla;

	private JTable tablaCitas;
	private JScrollPane scrollCitas;
	private DefaultTableModel modeloCitas;

	private JButton btnNuevaCita;
	private JButton btnEditarCita;
	private JButton btnEliminarCita;

	private CitaControlador citaControlador;

	private Empleado empleadoActual;

	public PanelCitas(Empleado empleadoActual) {
		this.empleadoActual = empleadoActual;
		citaControlador = new CitaControlador();

		setBackground(new Color(245, 239, 230));
		setBounds(0, 0, 764, 486);
		setLayout(null);

		lblTitulo = new JLabel("Gestión de citas");
		lblTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitulo.setFont(new Font("Serif", Font.BOLD, 32));
		lblTitulo.setForeground(new Color(91, 62, 46));
		lblTitulo.setBounds(35, 25, 450, 40);
		add(lblTitulo);

		btnNuevaCita = crearBoton("Nueva");
		btnNuevaCita.setBounds(35, 85, 135, 38);
		add(btnNuevaCita);

		btnEditarCita = crearBoton("Editar");
		btnEditarCita.setBounds(185, 85, 135, 38);
		add(btnEditarCita);

		btnEliminarCita = crearBoton("Eliminar");
		btnEliminarCita.setBounds(335, 85, 135, 38);
		add(btnEliminarCita);

		panelTabla = new JPanel();
		panelTabla.setLayout(null);
		panelTabla.setBackground(new Color(255, 252, 247));
		panelTabla.setBorder(BorderFactory.createLineBorder(new Color(220, 200, 180)));
		panelTabla.setBounds(35, 145, 690, 300);
		add(panelTabla);

		modeloCitas = new DefaultTableModel();
		modeloCitas.addColumn("ID");
		modeloCitas.addColumn("Fecha");
		modeloCitas.addColumn("Hora");
		modeloCitas.addColumn("Duración");
		modeloCitas.addColumn("ID Taller");
		modeloCitas.addColumn("Taller");
		modeloCitas.addColumn("ID Resp.");
		modeloCitas.addColumn("Responsable");
		modeloCitas.addColumn("ID Traje");
		modeloCitas.addColumn("Traje");
		modeloCitas.addColumn("ID Aprendiz 1");
		modeloCitas.addColumn("ID Aprendiz 2");

		tablaCitas = new JTable(modeloCitas);
		tablaCitas.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tablaCitas.setRowHeight(26);
		tablaCitas.setForeground(new Color(55, 45, 40));
		tablaCitas.setBackground(new Color(255, 252, 247));
		tablaCitas.setGridColor(new Color(220, 200, 180));
		tablaCitas.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 13));
		tablaCitas.getTableHeader().setForeground(new Color(91, 62, 46));
		tablaCitas.getTableHeader().setBackground(new Color(230, 215, 200));

		ocultarColumna(0);
		ocultarColumna(4);
		ocultarColumna(6);
		ocultarColumna(8);
		ocultarColumna(10);
		ocultarColumna(11);

		scrollCitas = new JScrollPane(tablaCitas);
		scrollCitas.setBounds(15, 15, 660, 270);
		scrollCitas.setBorder(BorderFactory.createLineBorder(new Color(220, 200, 180)));
		scrollCitas.getViewport().setBackground(new Color(255, 252, 247));
		panelTabla.add(scrollCitas);

		btnNuevaCita.addActionListener(e -> abrirFormularioNuevaCita());
		btnEditarCita.addActionListener(e -> abrirFormularioEditarCita());
		btnEliminarCita.addActionListener(e -> eliminarCita());

		aplicarPermisos();
		cargarCitas();
	}

	private JButton crearBoton(String texto) {
		JButton boton = new JButton(texto);
		boton.setFont(new Font("Tahoma", Font.BOLD, 13));
		boton.setForeground(new Color(91, 62, 46));
		boton.setBackground(new Color(230, 215, 200));
		boton.setFocusPainted(false);
		boton.setBorder(BorderFactory.createLineBorder(new Color(210, 190, 170)));
		return boton;
	}

	private void ocultarColumna(int numeroColumna) {
		tablaCitas.getColumnModel().getColumn(numeroColumna).setMinWidth(0);
		tablaCitas.getColumnModel().getColumn(numeroColumna).setMaxWidth(0);
		tablaCitas.getColumnModel().getColumn(numeroColumna).setPreferredWidth(0);
	}

	private void cargarCitas() {
		modeloCitas.setRowCount(0);

		ArrayList<Cita> listaCitas = citaControlador.obtenerCitas();

		for (Cita cita : listaCitas) {

			if (empleadoActual.esAprendiz()
					&& cita.getIdResponsable() != empleadoActual.getIdEmpleado()
					&& cita.getIdAprendiz1() != empleadoActual.getIdEmpleado()
					&& cita.getIdAprendiz2() != empleadoActual.getIdEmpleado()) {
				continue;
			}

			Object[] fila = {
					cita.getIdCita(),
					cita.getFechaCita(),
					cita.getHoraCita(),
					cita.getDuracionCita(),
					cita.getIdTaller(),
					cita.getNombreTaller(),
					cita.getIdResponsable(),
					cita.getNombreResponsable(),
					cita.getIdTraje(),
					cita.getNombreTraje(),
					cita.getIdAprendiz1(),
					cita.getIdAprendiz2()
			};

			modeloCitas.addRow(fila);
		}
	}

	private void aplicarPermisos() {

		if (empleadoActual.esOficial()) {
			btnEliminarCita.setVisible(false);
		}

		if (empleadoActual.esAprendiz()) {
			btnNuevaCita.setVisible(false);
			btnEditarCita.setVisible(false);
			btnEliminarCita.setVisible(false);
		}
	}

	private Cita obtenerCitaSeleccionada() {
		int filaSeleccionada = tablaCitas.getSelectedRow();

		if (filaSeleccionada == -1) {
			JOptionPane.showMessageDialog(this, "Selecciona una cita de la tabla.");
			return null;
		}

		int idCita = Integer.parseInt(modeloCitas.getValueAt(filaSeleccionada, 0).toString());
		String fechaCita = modeloCitas.getValueAt(filaSeleccionada, 1).toString();
		String horaCita = modeloCitas.getValueAt(filaSeleccionada, 2).toString();
		int duracionCita = Integer.parseInt(modeloCitas.getValueAt(filaSeleccionada, 3).toString());

		int idTaller = Integer.parseInt(modeloCitas.getValueAt(filaSeleccionada, 4).toString());
		String nombreTaller = modeloCitas.getValueAt(filaSeleccionada, 5).toString();

		int idResponsable = Integer.parseInt(modeloCitas.getValueAt(filaSeleccionada, 6).toString());
		String nombreResponsable = modeloCitas.getValueAt(filaSeleccionada, 7).toString();

		int idTraje = Integer.parseInt(modeloCitas.getValueAt(filaSeleccionada, 8).toString());
		String nombreTraje = modeloCitas.getValueAt(filaSeleccionada, 9).toString();

		int idAprendiz1 = Integer.parseInt(modeloCitas.getValueAt(filaSeleccionada, 10).toString());
		int idAprendiz2 = Integer.parseInt(modeloCitas.getValueAt(filaSeleccionada, 11).toString());

		Cita cita = new Cita();
		cita.setIdCita(idCita);
		cita.setFechaCita(fechaCita);
		cita.setHoraCita(horaCita);
		cita.setDuracionCita(duracionCita);

		cita.setIdTaller(idTaller);
		cita.setNombreTaller(nombreTaller);

		cita.setIdResponsable(idResponsable);
		cita.setNombreResponsable(nombreResponsable);

		cita.setIdTraje(idTraje);
		cita.setNombreTraje(nombreTraje);

		cita.setIdAprendiz1(idAprendiz1);
		cita.setIdAprendiz2(idAprendiz2);

		return cita;
	}

	private void abrirFormularioEditarCita() {
		Cita citaSeleccionada = obtenerCitaSeleccionada();

		if (citaSeleccionada != null) {

			if (empleadoActual.esOficial() && citaSeleccionada.getIdResponsable() != empleadoActual.getIdEmpleado()) {
				JOptionPane.showMessageDialog(this, "Solo puedes editar tus propias citas.");
				return;
			}

			VentanaCitaFormulario ventanaCitaFormulario = new VentanaCitaFormulario(this, citaSeleccionada);
			ventanaCitaFormulario.setVisible(true);
		}
	}

	private void abrirFormularioNuevaCita() {
		VentanaCitaFormulario ventanaCitaFormulario = new VentanaCitaFormulario(this);
		ventanaCitaFormulario.setVisible(true);
	}

	private void eliminarCita() {
		Cita citaSeleccionada = obtenerCitaSeleccionada();

		if (citaSeleccionada != null) {
			int opcion = JOptionPane.showConfirmDialog(this, "¿Seguro que quieres eliminar esta cita?",
					"Confirmar eliminación", JOptionPane.YES_NO_OPTION);

			if (opcion == JOptionPane.YES_OPTION) {
				boolean eliminado = citaControlador.eliminarCita(citaSeleccionada.getIdCita());

				if (eliminado) {
					JOptionPane.showMessageDialog(this, "Cita eliminada correctamente.");
					cargarCitas();
				} else {
					JOptionPane.showMessageDialog(this, "No se ha podido eliminar la cita.");
				}
			}
		}
	}

	public void refrescarTablaCitas() {
		cargarCitas();
	}
}