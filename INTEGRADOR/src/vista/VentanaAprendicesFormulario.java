package vista;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controlador.CitaControlador;
import modelo.Empleado;

/**
 * Ventana que permite seleccionar de 0 a 2 aprendices para una cita.
 * Devuelve la selección a la ventana del formulario de cita.
 */
public class VentanaAprendicesFormulario extends JFrame {

	private JPanel panelFondo;
	private JPanel panelFormulario;

	private JLabel lblTitulo;
	private JLabel lblAprendiz1;
	private JLabel lblAprendiz2;

	private JComboBox<Empleado> comboAprendiz1;
	private JComboBox<Empleado> comboAprendiz2;

	private JButton btnAceptar;
	private JButton btnCancelar;

	private VentanaCitaFormulario ventanaCitaFormulario;
	private CitaControlador citaControlador;

	public VentanaAprendicesFormulario(VentanaCitaFormulario ventanaCitaFormulario) {
		this.ventanaCitaFormulario = ventanaCitaFormulario;
		this.citaControlador = new CitaControlador();

		configurarVentana();
		configurarPaneles();
		configurarLabels();
		configurarCombos();
		configurarBotones();
		agregarComponentes();
		cargarAprendices();
		cargarAprendicesSeleccionados();
	}

	private void configurarVentana() {
		setTitle("Seleccionar aprendices");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 420, 300);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(null);
	}

	private void configurarPaneles() {
		panelFondo = new JPanel();
		panelFondo.setLayout(null);
		panelFondo.setBackground(new Color(245, 239, 230));
		panelFondo.setBounds(0, 0, 404, 261);

		panelFormulario = new JPanel();
		panelFormulario.setLayout(null);
		panelFormulario.setBackground(new Color(255, 252, 247));
		panelFormulario.setBorder(BorderFactory.createLineBorder(new Color(220, 200, 180)));
		panelFormulario.setBounds(25, 25, 355, 210);
	}

	private void configurarLabels() {
		lblTitulo = new JLabel("Aprendices");
		lblTitulo.setFont(new Font("Serif", Font.BOLD, 26));
		lblTitulo.setForeground(new Color(91, 62, 46));
		lblTitulo.setBounds(25, 15, 250, 35);

		lblAprendiz1 = new JLabel("Aprendiz 1");
		lblAprendiz1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAprendiz1.setForeground(new Color(55, 45, 40));
		lblAprendiz1.setBounds(25, 70, 100, 25);

		lblAprendiz2 = new JLabel("Aprendiz 2");
		lblAprendiz2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAprendiz2.setForeground(new Color(55, 45, 40));
		lblAprendiz2.setBounds(25, 115, 100, 25);
	}

	private void configurarCombos() {
		comboAprendiz1 = new JComboBox<Empleado>();
		comboAprendiz1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboAprendiz1.setBounds(130, 70, 190, 30);

		comboAprendiz2 = new JComboBox<Empleado>();
		comboAprendiz2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboAprendiz2.setBounds(130, 115, 190, 30);

		DefaultListCellRenderer render = new DefaultListCellRenderer() {
			private static final long serialVersionUID = 1L;

			@Override
			public java.awt.Component getListCellRendererComponent(javax.swing.JList<?> list, Object value, int index,
					boolean isSelected, boolean cellHasFocus) {

				super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

				if (value == null) {
					setText("-- Ninguno --");
				}

				return this;
			}
		};

		comboAprendiz1.setRenderer(render);
		comboAprendiz2.setRenderer(render);
	}

	private void configurarBotones() {
		btnAceptar = crearBoton("Aceptar");
		btnAceptar.setBounds(80, 160, 100, 30);

		btnCancelar = crearBoton("Cancelar");
		btnCancelar.setBounds(195, 160, 100, 30);

		btnAceptar.addActionListener(e -> aceptar());
		btnCancelar.addActionListener(e -> dispose());
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

	private void agregarComponentes() {
		getContentPane().add(panelFondo);
		panelFondo.add(panelFormulario);

		panelFormulario.add(lblTitulo);
		panelFormulario.add(lblAprendiz1);
		panelFormulario.add(comboAprendiz1);
		panelFormulario.add(lblAprendiz2);
		panelFormulario.add(comboAprendiz2);
		panelFormulario.add(btnAceptar);
		panelFormulario.add(btnCancelar);
	}

	/**
	 * Carga en los dos desplegables únicamente los empleados con rol de aprendiz.
	 * También añade una opción nula para permitir no seleccionar aprendiz.
	 */
	private void cargarAprendices() {
		comboAprendiz1.addItem(null);
		comboAprendiz2.addItem(null);

		ArrayList<Empleado> listaAprendices = citaControlador.obtenerAprendices();

		for (Empleado aprendiz : listaAprendices) {
			comboAprendiz1.addItem(aprendiz);
			comboAprendiz2.addItem(aprendiz);
		}
	}
	
	/**
	 * Busca en el combo el aprendiz con el ID recibido y lo selecciona.
	 * Se usa al editar una cita para mostrar los aprendices ya asignados.
	 */
	private void cargarAprendicesSeleccionados() {
		int idAprendiz1 = ventanaCitaFormulario.getIdAprendiz1();
		int idAprendiz2 = ventanaCitaFormulario.getIdAprendiz2();

		seleccionarAprendiz(comboAprendiz1, idAprendiz1);
		seleccionarAprendiz(comboAprendiz2, idAprendiz2);
	}

	private void seleccionarAprendiz(JComboBox<Empleado> combo, int idAprendiz) {
		for (int i = 0; i < combo.getItemCount(); i++) {
			Empleado aprendiz = combo.getItemAt(i);

			if (aprendiz != null && aprendiz.getIdEmpleado() == idAprendiz) {
				combo.setSelectedIndex(i);
				return;
			}
		}
	}

	/**
	 * Recoge los aprendices seleccionados, comprueba que no sean el mismo
	 * y los devuelve a la ventana de cita.
	 */
	private void aceptar() {
		Empleado aprendiz1 = (Empleado) comboAprendiz1.getSelectedItem();
		Empleado aprendiz2 = (Empleado) comboAprendiz2.getSelectedItem();

		if (aprendiz1 != null && aprendiz2 != null && aprendiz1.getIdEmpleado() == aprendiz2.getIdEmpleado()) {
			JOptionPane.showMessageDialog(this, "No puedes seleccionar el mismo aprendiz dos veces.");
			return;
		}

		ventanaCitaFormulario.setAprendices(aprendiz1, aprendiz2);
		dispose();
	}
}