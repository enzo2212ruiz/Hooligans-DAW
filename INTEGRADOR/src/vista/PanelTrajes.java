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

import controlador.TrajeControlador;
import modelo.Traje;

public class PanelTrajes extends JPanel {

	private static final long serialVersionUID = 1L;

	private JLabel lblTitulo;

	private JPanel panelTabla;

	private JTable tablaTrajes;
	private JScrollPane scrollTrajes;
	private DefaultTableModel modeloTrajes;

	private JButton btnNuevoTraje;
	private JButton btnEditarTraje;
	private JButton btnEliminarTraje;
	private JButton btnRefrescarTrajes;

	private TrajeControlador trajeControlador;

	public PanelTrajes() {
		trajeControlador = new TrajeControlador();

		setBackground(new Color(245, 239, 230));
		setBounds(0, 0, 764, 486);
		setLayout(null);

		lblTitulo = new JLabel("Gestión de trajes");
		lblTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitulo.setFont(new Font("Serif", Font.BOLD, 32));
		lblTitulo.setForeground(new Color(91, 62, 46));
		lblTitulo.setBounds(35, 25, 450, 40);
		add(lblTitulo);

		btnNuevoTraje = crearBoton("Nuevo");
		btnNuevoTraje.setBounds(35, 85, 135, 38);
		add(btnNuevoTraje);

		btnEditarTraje = crearBoton("Editar");
		btnEditarTraje.setBounds(185, 85, 135, 38);
		add(btnEditarTraje);

		btnEliminarTraje = crearBoton("Eliminar");
		btnEliminarTraje.setBounds(335, 85, 135, 38);
		add(btnEliminarTraje);

		btnRefrescarTrajes = crearBoton("Refrescar");
		btnRefrescarTrajes.setBounds(485, 85, 135, 38);
		add(btnRefrescarTrajes);

		panelTabla = new JPanel();
		panelTabla.setLayout(null);
		panelTabla.setBackground(new Color(255, 252, 247));
		panelTabla.setBorder(BorderFactory.createLineBorder(new Color(220, 200, 180)));
		panelTabla.setBounds(35, 145, 690, 300);
		add(panelTabla);

		modeloTrajes = new DefaultTableModel();
		modeloTrajes.addColumn("ID");
		modeloTrajes.addColumn("Nombre");
		modeloTrajes.addColumn("Estado");
		modeloTrajes.addColumn("ID Cliente");
		modeloTrajes.addColumn("Cliente");

		tablaTrajes = new JTable(modeloTrajes);
		tablaTrajes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tablaTrajes.setRowHeight(26);
		tablaTrajes.setForeground(new Color(55, 45, 40));
		tablaTrajes.setBackground(new Color(255, 252, 247));
		tablaTrajes.setGridColor(new Color(220, 200, 180));
		tablaTrajes.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
		tablaTrajes.getTableHeader().setForeground(new Color(91, 62, 46));
		tablaTrajes.getTableHeader().setBackground(new Color(230, 215, 200));

		tablaTrajes.getColumnModel().getColumn(0).setMinWidth(0);
		tablaTrajes.getColumnModel().getColumn(0).setMaxWidth(0);
		tablaTrajes.getColumnModel().getColumn(0).setPreferredWidth(0);

		tablaTrajes.getColumnModel().getColumn(3).setMinWidth(0);
		tablaTrajes.getColumnModel().getColumn(3).setMaxWidth(0);
		tablaTrajes.getColumnModel().getColumn(3).setPreferredWidth(0);
		
		scrollTrajes = new JScrollPane(tablaTrajes);
		scrollTrajes.setBounds(15, 15, 660, 270);
		scrollTrajes.setBorder(BorderFactory.createLineBorder(new Color(220, 200, 180)));
		scrollTrajes.getViewport().setBackground(new Color(255, 252, 247));
		panelTabla.add(scrollTrajes);

		btnNuevoTraje.addActionListener(e -> abrirFormularioNuevoTraje());
		btnEditarTraje.addActionListener(e -> abrirFormularioEditarTraje());
		btnEliminarTraje.addActionListener(e -> eliminarTraje());
		btnRefrescarTrajes.addActionListener(e -> cargarTrajes());

		cargarTrajes();
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

	private void cargarTrajes() {
		modeloTrajes.setRowCount(0);

		ArrayList<Traje> listaTrajes = trajeControlador.obtenerTrajes();

		for (Traje traje : listaTrajes) {
			Object[] fila = {
					traje.getIdTraje(),
					traje.getNombreTraje(),
					traje.getEstadoTraje(),
					traje.getIdCliente(),
					traje.getNombreCliente()
			};

			modeloTrajes.addRow(fila);
		}
	}

	private Traje obtenerTrajeSeleccionado() {
		int filaSeleccionada = tablaTrajes.getSelectedRow();

		if (filaSeleccionada == -1) {
			JOptionPane.showMessageDialog(this, "Selecciona un traje de la tabla.");
			return null;
		}

		int idTraje = Integer.parseInt(modeloTrajes.getValueAt(filaSeleccionada, 0).toString());
		String nombreTraje = modeloTrajes.getValueAt(filaSeleccionada, 1).toString();
		String estadoTraje = modeloTrajes.getValueAt(filaSeleccionada, 2).toString();
		int idCliente = Integer.parseInt(modeloTrajes.getValueAt(filaSeleccionada, 3).toString());
		String nombreCliente = modeloTrajes.getValueAt(filaSeleccionada, 4).toString();

		Traje traje = new Traje();
		traje.setIdTraje(idTraje);
		traje.setNombreTraje(nombreTraje);
		traje.setEstadoTraje(estadoTraje);
		traje.setIdCliente(idCliente);
		traje.setNombreCliente(nombreCliente);

		return traje;
	}

	private void abrirFormularioNuevoTraje() {
		VentanaTrajeFormulario ventanaTrajeFormulario = new VentanaTrajeFormulario(this);
		ventanaTrajeFormulario.setVisible(true);
	}

	private void abrirFormularioEditarTraje() {
		Traje trajeSeleccionado = obtenerTrajeSeleccionado();

		if (trajeSeleccionado != null) {
			VentanaTrajeFormulario ventanaTrajeFormulario = new VentanaTrajeFormulario(this, trajeSeleccionado);
			ventanaTrajeFormulario.setVisible(true);
		}
	}

	private void eliminarTraje() {
		Traje trajeSeleccionado = obtenerTrajeSeleccionado();

		if (trajeSeleccionado != null) {
			int opcion = JOptionPane.showConfirmDialog(this,
					"¿Seguro que quieres eliminar este traje?",
					"Confirmar eliminación",
					JOptionPane.YES_NO_OPTION);

			if (opcion == JOptionPane.YES_OPTION) {
				boolean eliminado = trajeControlador.eliminarTraje(trajeSeleccionado.getIdTraje());

				if (eliminado) {
					JOptionPane.showMessageDialog(this, "Traje eliminado correctamente.");
					cargarTrajes();
				} else {
					JOptionPane.showMessageDialog(this, "No se ha podido eliminar el traje.");
				}
			}
		}
	}

	public void refrescarTablaTrajes() {
		cargarTrajes();
	}
}