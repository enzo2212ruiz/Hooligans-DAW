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
	private JButton btnRefrescarCitas;

	private CitaControlador citaControlador;

	public PanelCitas() {
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

		btnRefrescarCitas = crearBoton("Refrescar");
		btnRefrescarCitas.setBounds(485, 85, 135, 38);
		add(btnRefrescarCitas);

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

		tablaCitas = new JTable(modeloCitas);
		tablaCitas.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tablaCitas.setRowHeight(26);
		tablaCitas.setForeground(new Color(55, 45, 40));
		tablaCitas.setBackground(new Color(255, 252, 247));
		tablaCitas.setGridColor(new Color(220, 200, 180));
		tablaCitas.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 13));
		tablaCitas.getTableHeader().setForeground(new Color(91, 62, 46));
		tablaCitas.getTableHeader().setBackground(new Color(230, 215, 200));

		scrollCitas = new JScrollPane(tablaCitas);
		scrollCitas.setBounds(15, 15, 660, 270);
		scrollCitas.setBorder(BorderFactory.createLineBorder(new Color(220, 200, 180)));
		scrollCitas.getViewport().setBackground(new Color(255, 252, 247));
		panelTabla.add(scrollCitas);

		btnNuevaCita.addActionListener(e -> abrirFormularioNuevaCita());
		btnEditarCita.addActionListener(e -> abrirFormularioEditarCita());
		btnEliminarCita.addActionListener(e -> eliminarCita());
		btnRefrescarCitas.addActionListener(e -> cargarCitas());

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

	private void cargarCitas() {
		modeloCitas.setRowCount(0);

		ArrayList<Cita> listaCitas = citaControlador.obtenerCitas();

		for (Cita cita : listaCitas) {
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
					cita.getNombreTraje()
			};

			modeloCitas.addRow(fila);
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

		return cita;
	}

	private void abrirFormularioNuevaCita() {
		VentanaCitaFormulario ventanaCitaFormulario = new VentanaCitaFormulario(this);
		ventanaCitaFormulario.setVisible(true);
	}

	private void abrirFormularioEditarCita() {
		Cita citaSeleccionada = obtenerCitaSeleccionada();

		if (citaSeleccionada != null) {
			VentanaCitaFormulario ventanaCitaFormulario = new VentanaCitaFormulario(this, citaSeleccionada);
			ventanaCitaFormulario.setVisible(true);
		}
	}

	private void eliminarCita() {
		Cita citaSeleccionada = obtenerCitaSeleccionada();

		if (citaSeleccionada != null) {
			int opcion = JOptionPane.showConfirmDialog(this,
					"¿Seguro que quieres eliminar esta cita?",
					"Confirmar eliminación",
					JOptionPane.YES_NO_OPTION);

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