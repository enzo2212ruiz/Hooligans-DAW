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

import controlador.EmpleadoControlador;
import modelo.Empleado;

public class PanelEmpleados extends JPanel {

	private static final long serialVersionUID = 1L;

	private JLabel lblTitulo;

	private JPanel panelTabla;

	private JTable tablaEmpleados;
	private JScrollPane scrollEmpleados;
	private DefaultTableModel modeloEmpleados;

	private JButton btnNuevoEmpleado;
	private JButton btnEditarEmpleado;
	private JButton btnEliminarEmpleado;
	private JButton btnRefrescarEmpleados;

	private EmpleadoControlador empleadoControlador;

	public PanelEmpleados() {
		empleadoControlador = new EmpleadoControlador();

		setBackground(new Color(245, 239, 230));
		setBounds(0, 0, 764, 486);
		setLayout(null);

		lblTitulo = new JLabel("Gestión de empleados");
		lblTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitulo.setFont(new Font("Serif", Font.BOLD, 32));
		lblTitulo.setForeground(new Color(91, 62, 46));
		lblTitulo.setBounds(35, 25, 500, 40);
		add(lblTitulo);

		btnNuevoEmpleado = crearBoton("Nuevo");
		btnNuevoEmpleado.setBounds(35, 85, 135, 38);
		add(btnNuevoEmpleado);

		btnEditarEmpleado = crearBoton("Editar");
		btnEditarEmpleado.setBounds(185, 85, 135, 38);
		add(btnEditarEmpleado);

		btnEliminarEmpleado = crearBoton("Eliminar");
		btnEliminarEmpleado.setBounds(335, 85, 135, 38);
		add(btnEliminarEmpleado);

		btnRefrescarEmpleados = crearBoton("Refrescar");
		btnRefrescarEmpleados.setBounds(485, 85, 135, 38);
		add(btnRefrescarEmpleados);

		panelTabla = new JPanel();
		panelTabla.setLayout(null);
		panelTabla.setBackground(new Color(255, 252, 247));
		panelTabla.setBorder(BorderFactory.createLineBorder(new Color(220, 200, 180)));
		panelTabla.setBounds(35, 145, 690, 300);
		add(panelTabla);

		modeloEmpleados = new DefaultTableModel();
		modeloEmpleados.addColumn("ID");
		modeloEmpleados.addColumn("Nombre");
		modeloEmpleados.addColumn("Apodo");
		modeloEmpleados.addColumn("Contraseña");
		modeloEmpleados.addColumn("Categoría");

		tablaEmpleados = new JTable(modeloEmpleados);
		tablaEmpleados.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tablaEmpleados.setRowHeight(26);
		tablaEmpleados.setForeground(new Color(55, 45, 40));
		tablaEmpleados.setBackground(new Color(255, 252, 247));
		tablaEmpleados.setGridColor(new Color(220, 200, 180));
		tablaEmpleados.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
		tablaEmpleados.getTableHeader().setForeground(new Color(91, 62, 46));
		tablaEmpleados.getTableHeader().setBackground(new Color(230, 215, 200));

		tablaEmpleados.getColumnModel().getColumn(0).setMinWidth(0);
		tablaEmpleados.getColumnModel().getColumn(0).setMaxWidth(0);
		tablaEmpleados.getColumnModel().getColumn(0).setPreferredWidth(0);

		tablaEmpleados.getColumnModel().getColumn(3).setMinWidth(0);
		tablaEmpleados.getColumnModel().getColumn(3).setMaxWidth(0);
		tablaEmpleados.getColumnModel().getColumn(3).setPreferredWidth(0);
		
		scrollEmpleados = new JScrollPane(tablaEmpleados);
		scrollEmpleados.setBounds(15, 15, 660, 270);
		scrollEmpleados.setBorder(BorderFactory.createLineBorder(new Color(220, 200, 180)));
		scrollEmpleados.getViewport().setBackground(new Color(255, 252, 247));
		panelTabla.add(scrollEmpleados);

		btnNuevoEmpleado.addActionListener(e -> abrirFormularioNuevoEmpleado());
		btnEditarEmpleado.addActionListener(e -> abrirFormularioEditarEmpleado());
		btnEliminarEmpleado.addActionListener(e -> eliminarEmpleado());
		btnRefrescarEmpleados.addActionListener(e -> cargarEmpleados());

		cargarEmpleados();
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

	private void cargarEmpleados() {
		modeloEmpleados.setRowCount(0);

		ArrayList<Empleado> listaEmpleados = empleadoControlador.obtenerEmpleados();

		for (Empleado empleado : listaEmpleados) {
			Object[] fila = {
					empleado.getIdEmpleado(),
					empleado.getNombreEmpleado(),
					empleado.getApodoEmpleado(),
					empleado.getPasswordEmpleado(),
					empleado.getCategoriaEmpleado()
			};

			modeloEmpleados.addRow(fila);
		}
	}

	private Empleado obtenerEmpleadoSeleccionado() {
		int filaSeleccionada = tablaEmpleados.getSelectedRow();

		if (filaSeleccionada == -1) {
			JOptionPane.showMessageDialog(this, "Selecciona un empleado de la tabla.");
			return null;
		}

		int idEmpleado = Integer.parseInt(modeloEmpleados.getValueAt(filaSeleccionada, 0).toString());
		String nombreEmpleado = modeloEmpleados.getValueAt(filaSeleccionada, 1).toString();
		String apodoEmpleado = modeloEmpleados.getValueAt(filaSeleccionada, 2).toString();
		String passwordEmpleado = modeloEmpleados.getValueAt(filaSeleccionada, 3).toString();
		String categoriaEmpleado = modeloEmpleados.getValueAt(filaSeleccionada, 4).toString();

		Empleado empleado = new Empleado();
		empleado.setIdEmpleado(idEmpleado);
		empleado.setNombreEmpleado(nombreEmpleado);
		empleado.setApodoEmpleado(apodoEmpleado);
		empleado.setPasswordEmpleado(passwordEmpleado);
		empleado.setCategoriaEmpleado(categoriaEmpleado);

		return empleado;
	}

	private void abrirFormularioNuevoEmpleado() {
		VentanaEmpleadoFormulario ventanaEmpleadoFormulario = new VentanaEmpleadoFormulario(this);
		ventanaEmpleadoFormulario.setVisible(true);
	}

	private void abrirFormularioEditarEmpleado() {
		Empleado empleadoSeleccionado = obtenerEmpleadoSeleccionado();

		if (empleadoSeleccionado != null) {
			VentanaEmpleadoFormulario ventanaEmpleadoFormulario = new VentanaEmpleadoFormulario(this,
					empleadoSeleccionado);
			ventanaEmpleadoFormulario.setVisible(true);
		}
	}

	private void eliminarEmpleado() {
		Empleado empleadoSeleccionado = obtenerEmpleadoSeleccionado();

		if (empleadoSeleccionado != null) {
			int opcion = JOptionPane.showConfirmDialog(this,
					"¿Seguro que quieres eliminar este empleado?",
					"Confirmar eliminación",
					JOptionPane.YES_NO_OPTION);

			if (opcion == JOptionPane.YES_OPTION) {
				boolean eliminado = empleadoControlador.eliminarEmpleado(empleadoSeleccionado.getIdEmpleado());

				if (eliminado) {
					JOptionPane.showMessageDialog(this, "Empleado eliminado correctamente.");
					cargarEmpleados();
				} else {
					JOptionPane.showMessageDialog(this, "No se ha podido eliminar el empleado.");
				}
			}
		}
	}

	public void refrescarTablaEmpleados() {
		cargarEmpleados();
	}
}