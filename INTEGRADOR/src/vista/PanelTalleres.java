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

import controlador.TallerControlador;
import modelo.Taller;

public class PanelTalleres extends JPanel {

	private static final long serialVersionUID = 1L;

	private JLabel lblTitulo;

	private JPanel panelTabla;

	private JTable tablaTalleres;
	private JScrollPane scrollTalleres;
	private DefaultTableModel modeloTalleres;

	private JButton btnNuevoTaller;
	private JButton btnEditarTaller;
	private JButton btnEliminarTaller;
	private JButton btnRefrescarTalleres;

	private TallerControlador tallerControlador;

	public PanelTalleres() {
		tallerControlador = new TallerControlador();

		setBackground(new Color(245, 239, 230));
		setBounds(0, 0, 764, 486);
		setLayout(null);

		lblTitulo = new JLabel("Gestión de talleres");
		lblTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitulo.setFont(new Font("Serif", Font.BOLD, 32));
		lblTitulo.setForeground(new Color(91, 62, 46));
		lblTitulo.setBounds(35, 25, 450, 40);
		add(lblTitulo);

		btnNuevoTaller = crearBoton("Nuevo");
		btnNuevoTaller.setBounds(35, 85, 135, 38);
		add(btnNuevoTaller);

		btnEditarTaller = crearBoton("Editar");
		btnEditarTaller.setBounds(185, 85, 135, 38);
		add(btnEditarTaller);

		btnEliminarTaller = crearBoton("Eliminar");
		btnEliminarTaller.setBounds(335, 85, 135, 38);
		add(btnEliminarTaller);

		btnRefrescarTalleres = crearBoton("Refrescar");
		btnRefrescarTalleres.setBounds(485, 85, 135, 38);
		add(btnRefrescarTalleres);

		panelTabla = new JPanel();
		panelTabla.setLayout(null);
		panelTabla.setBackground(new Color(255, 252, 247));
		panelTabla.setBorder(BorderFactory.createLineBorder(new Color(220, 200, 180)));
		panelTabla.setBounds(35, 145, 690, 300);
		add(panelTabla);

		modeloTalleres = new DefaultTableModel();
		modeloTalleres.addColumn("ID");
		modeloTalleres.addColumn("Nombre");
		modeloTalleres.addColumn("Tipo");

		tablaTalleres = new JTable(modeloTalleres);
		tablaTalleres.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tablaTalleres.setRowHeight(26);
		tablaTalleres.setForeground(new Color(55, 45, 40));
		tablaTalleres.setBackground(new Color(255, 252, 247));
		tablaTalleres.setGridColor(new Color(220, 200, 180));
		tablaTalleres.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
		tablaTalleres.getTableHeader().setForeground(new Color(91, 62, 46));
		tablaTalleres.getTableHeader().setBackground(new Color(230, 215, 200));

		scrollTalleres = new JScrollPane(tablaTalleres);
		scrollTalleres.setBounds(15, 15, 660, 270);
		scrollTalleres.setBorder(BorderFactory.createLineBorder(new Color(220, 200, 180)));
		scrollTalleres.getViewport().setBackground(new Color(255, 252, 247));
		panelTabla.add(scrollTalleres);

		btnNuevoTaller.addActionListener(e -> abrirFormularioNuevoTaller());
		btnEditarTaller.addActionListener(e -> abrirFormularioEditarTaller());
		btnEliminarTaller.addActionListener(e -> eliminarTaller());
		btnRefrescarTalleres.addActionListener(e -> cargarTalleres());

		cargarTalleres();
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

	private void cargarTalleres() {
		modeloTalleres.setRowCount(0);

		ArrayList<Taller> listaTalleres = tallerControlador.obtenerTalleres();

		for (Taller taller : listaTalleres) {
			Object[] fila = {
					taller.getIdTaller(),
					taller.getNombreTaller(),
					taller.getTipoTaller()
			};

			modeloTalleres.addRow(fila);
		}
	}

	private Taller obtenerTallerSeleccionado() {
		int filaSeleccionada = tablaTalleres.getSelectedRow();

		if (filaSeleccionada == -1) {
			JOptionPane.showMessageDialog(this, "Selecciona un taller de la tabla.");
			return null;
		}

		int idTaller = Integer.parseInt(modeloTalleres.getValueAt(filaSeleccionada, 0).toString());
		String nombreTaller = modeloTalleres.getValueAt(filaSeleccionada, 1).toString();
		String tipoTaller = modeloTalleres.getValueAt(filaSeleccionada, 2).toString();

		Taller taller = new Taller();
		taller.setIdTaller(idTaller);
		taller.setNombreTaller(nombreTaller);
		taller.setTipoTaller(tipoTaller);

		return taller;
	}

	private void abrirFormularioNuevoTaller() {
		VentanaTallerFormulario ventanaTallerFormulario = new VentanaTallerFormulario(this);
		ventanaTallerFormulario.setVisible(true);
	}

	private void abrirFormularioEditarTaller() {
		Taller tallerSeleccionado = obtenerTallerSeleccionado();

		if (tallerSeleccionado != null) {
			VentanaTallerFormulario ventanaTallerFormulario = new VentanaTallerFormulario(this, tallerSeleccionado);
			ventanaTallerFormulario.setVisible(true);
		}
	}

	private void eliminarTaller() {
		Taller tallerSeleccionado = obtenerTallerSeleccionado();

		if (tallerSeleccionado != null) {
			int opcion = JOptionPane.showConfirmDialog(this,
					"¿Seguro que quieres eliminar este taller?",
					"Confirmar eliminación",
					JOptionPane.YES_NO_OPTION);

			if (opcion == JOptionPane.YES_OPTION) {
				boolean eliminado = tallerControlador.eliminarTaller(tallerSeleccionado.getIdTaller());

				if (eliminado) {
					JOptionPane.showMessageDialog(this, "Taller eliminado correctamente.");
					cargarTalleres();
				} else {
					JOptionPane.showMessageDialog(this, "No se ha podido eliminar el taller.");
				}
			}
		}
	}

	public void refrescarTablaTalleres() {
		cargarTalleres();
	}
}