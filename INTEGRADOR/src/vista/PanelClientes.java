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

import controlador.ClienteControlador;
import modelo.Cliente;

public class PanelClientes extends JPanel {

	private static final long serialVersionUID = 1L;

	private JLabel lblTitulo;

	private JPanel panelTabla;

	private JTable tablaClientes;
	private JScrollPane scrollClientes;
	private DefaultTableModel modeloClientes;

	private JButton btnNuevoCliente;
	private JButton btnEditarCliente;
	private JButton btnEliminarCliente;
	private JButton btnRefrescarClientes;

	private ClienteControlador clienteControlador;

	public PanelClientes() {
		clienteControlador = new ClienteControlador();

		setBackground(new Color(245, 239, 230));
		setBounds(0, 0, 764, 486);
		setLayout(null);

		lblTitulo = new JLabel("Gestión de clientes");
		lblTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitulo.setFont(new Font("Serif", Font.BOLD, 32));
		lblTitulo.setForeground(new Color(91, 62, 46));
		lblTitulo.setBounds(35, 25, 450, 40);
		add(lblTitulo);

		btnNuevoCliente = crearBoton("Nuevo");
		btnNuevoCliente.setBounds(35, 85, 135, 38);
		add(btnNuevoCliente);

		btnEditarCliente = crearBoton("Editar");
		btnEditarCliente.setBounds(185, 85, 135, 38);
		add(btnEditarCliente);

		btnEliminarCliente = crearBoton("Eliminar");
		btnEliminarCliente.setBounds(335, 85, 135, 38);
		add(btnEliminarCliente);

		btnRefrescarClientes = crearBoton("Refrescar");
		btnRefrescarClientes.setBounds(485, 85, 135, 38);
		add(btnRefrescarClientes);

		panelTabla = new JPanel();
		panelTabla.setLayout(null);
		panelTabla.setBackground(new Color(255, 252, 247));
		panelTabla.setBorder(BorderFactory.createLineBorder(new Color(220, 200, 180)));
		panelTabla.setBounds(35, 145, 690, 300);
		add(panelTabla);

		modeloClientes = new DefaultTableModel();
		modeloClientes.addColumn("ID");
		modeloClientes.addColumn("Nombre");
		modeloClientes.addColumn("Superpoder");

		tablaClientes = new JTable(modeloClientes);
		tablaClientes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tablaClientes.setRowHeight(26);
		tablaClientes.setForeground(new Color(55, 45, 40));
		tablaClientes.setBackground(new Color(255, 252, 247));
		tablaClientes.setGridColor(new Color(220, 200, 180));
		tablaClientes.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
		tablaClientes.getTableHeader().setForeground(new Color(91, 62, 46));
		tablaClientes.getTableHeader().setBackground(new Color(230, 215, 200));

		scrollClientes = new JScrollPane(tablaClientes);
		scrollClientes.setBounds(15, 15, 660, 270);
		scrollClientes.setBorder(BorderFactory.createLineBorder(new Color(220, 200, 180)));
		scrollClientes.getViewport().setBackground(new Color(255, 252, 247));
		panelTabla.add(scrollClientes);

		btnNuevoCliente.addActionListener(e -> abrirFormularioNuevoCliente());
		btnEditarCliente.addActionListener(e -> abrirFormularioEditarCliente());
		btnEliminarCliente.addActionListener(e -> eliminarCliente());
		btnRefrescarClientes.addActionListener(e -> cargarClientes());

		cargarClientes();
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

	private void cargarClientes() {
		modeloClientes.setRowCount(0);

		ArrayList<Cliente> listaClientes = clienteControlador.obtenerClientes();

		for (Cliente cliente : listaClientes) {
			Object[] fila = {
					cliente.getIdCliente(),
					cliente.getNombreCliente(),
					cliente.getSuperpoderCliente()
			};

			modeloClientes.addRow(fila);
		}
	}

	private Cliente obtenerClienteSeleccionado() {
		int filaSeleccionada = tablaClientes.getSelectedRow();

		if (filaSeleccionada == -1) {
			JOptionPane.showMessageDialog(this, "Selecciona un cliente de la tabla.");
			return null;
		}

		int idCliente = Integer.parseInt(modeloClientes.getValueAt(filaSeleccionada, 0).toString());
		String nombreCliente = modeloClientes.getValueAt(filaSeleccionada, 1).toString();
		String superpoderCliente = modeloClientes.getValueAt(filaSeleccionada, 2).toString();

		Cliente cliente = new Cliente();
		cliente.setIdCliente(idCliente);
		cliente.setNombreCliente(nombreCliente);
		cliente.setSuperpoderCliente(superpoderCliente);

		return cliente;
	}

	private void abrirFormularioNuevoCliente() {
		VentanaClienteFormulario ventanaClienteFormulario = new VentanaClienteFormulario(this);
		ventanaClienteFormulario.setVisible(true);
	}

	private void abrirFormularioEditarCliente() {
		Cliente clienteSeleccionado = obtenerClienteSeleccionado();

		if (clienteSeleccionado != null) {
			VentanaClienteFormulario ventanaClienteFormulario = new VentanaClienteFormulario(this, clienteSeleccionado);
			ventanaClienteFormulario.setVisible(true);
		}
	}

	private void eliminarCliente() {
		Cliente clienteSeleccionado = obtenerClienteSeleccionado();

		if (clienteSeleccionado != null) {
			int opcion = JOptionPane.showConfirmDialog(this,
					"¿Seguro que quieres eliminar este cliente?",
					"Confirmar eliminación",
					JOptionPane.YES_NO_OPTION);

			if (opcion == JOptionPane.YES_OPTION) {
				boolean eliminado = clienteControlador.eliminarCliente(clienteSeleccionado.getIdCliente());

				if (eliminado) {
					JOptionPane.showMessageDialog(this, "Cliente eliminado correctamente.");
					cargarClientes();
				} else {
					JOptionPane.showMessageDialog(this, "No se ha podido eliminar el cliente.");
				}
			}
		}
	}

	public void refrescarTablaClientes() {
		cargarClientes();
	}
}