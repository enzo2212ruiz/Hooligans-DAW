package vista;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controlador.EmpleadoControlador;
import modelo.Empleado;

/**
 * Ventana para crear o editar un empleado.
 */
public class VentanaEmpleadoFormulario extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel panelFondo;
	private JPanel panelFormulario;

	private JLabel lblTitulo;
	private JLabel lblNombreEmpleado;
	private JLabel lblApodoEmpleado;
	private JLabel lblPasswordEmpleado;
	private JLabel lblCategoriaEmpleado;

	private JTextField txtNombreEmpleado;
	private JTextField txtApodoEmpleado;
	private JPasswordField txtPasswordEmpleado;
	private JComboBox<String> comboCategoriaEmpleado;

	private JButton btnGuardarEmpleado;
	private JButton btnCancelar;

	private EmpleadoControlador empleadoControlador;
	private PanelEmpleados panelEmpleados;
	private Empleado empleadoEditar;

	/**
	 * Constructor para crear un nuevo empleado.
	 */
	public VentanaEmpleadoFormulario(PanelEmpleados panelEmpleados) {
		this.panelEmpleados = panelEmpleados;
		this.empleadoEditar = null;
		empleadoControlador = new EmpleadoControlador();

		configurarVentana();
		configurarPaneles();
		configurarLabels();
		configurarCampos();
		configurarBotones();
		agregarComponentes();

		lblTitulo.setText("Nuevo empleado");
	}

	/**
	 * Constructor para editar un empleado existente.
	 */
	public VentanaEmpleadoFormulario(PanelEmpleados panelEmpleados, Empleado empleadoEditar) {
		this.panelEmpleados = panelEmpleados;
		this.empleadoEditar = empleadoEditar;
		empleadoControlador = new EmpleadoControlador();

		configurarVentana();
		configurarPaneles();
		configurarLabels();
		configurarCampos();
		configurarBotones();
		agregarComponentes();
		cargarDatosEmpleado();

		lblTitulo.setText("Editar empleado");
	}

	/**
	 * Configura la ventana principal.
	 */
	private void configurarVentana() {
		setTitle("Formulario empleado");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 470);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(null);
	}

	/**
	 * Configura los paneles principales.
	 */
	private void configurarPaneles() {
		panelFondo = new JPanel();
		panelFondo.setLayout(null);
		panelFondo.setBackground(new Color(245, 239, 230));
		panelFondo.setBounds(0, 0, 484, 431);

		panelFormulario = new JPanel();
		panelFormulario.setLayout(null);
		panelFormulario.setBackground(new Color(255, 252, 247));
		panelFormulario.setBorder(BorderFactory.createLineBorder(new Color(220, 200, 180)));
		panelFormulario.setBounds(35, 30, 415, 365);
	}

	/**
	 * Configura los labels del formulario.
	 */
	private void configurarLabels() {
		lblTitulo = new JLabel();
		lblTitulo.setFont(new Font("Serif", Font.BOLD, 30));
		lblTitulo.setForeground(new Color(91, 62, 46));
		lblTitulo.setBounds(35, 25, 330, 40);

		lblNombreEmpleado = new JLabel("Nombre");
		lblNombreEmpleado.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNombreEmpleado.setForeground(new Color(55, 45, 40));
		lblNombreEmpleado.setBounds(35, 85, 120, 25);

		lblApodoEmpleado = new JLabel("Apodo");
		lblApodoEmpleado.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblApodoEmpleado.setForeground(new Color(55, 45, 40));
		lblApodoEmpleado.setBounds(35, 135, 120, 25);

		lblPasswordEmpleado = new JLabel("Contraseña");
		lblPasswordEmpleado.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPasswordEmpleado.setForeground(new Color(55, 45, 40));
		lblPasswordEmpleado.setBounds(35, 185, 120, 25);

		lblCategoriaEmpleado = new JLabel("Categoría");
		lblCategoriaEmpleado.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCategoriaEmpleado.setForeground(new Color(55, 45, 40));
		lblCategoriaEmpleado.setBounds(35, 235, 120, 25);
	}

	/**
	 * Configura los campos de texto y selección.
	 */
	private void configurarCampos() {
		txtNombreEmpleado = new JTextField();
		txtNombreEmpleado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtNombreEmpleado.setBounds(160, 85, 215, 30);
		txtNombreEmpleado.setBorder(BorderFactory.createLineBorder(new Color(210, 190, 170)));

		txtApodoEmpleado = new JTextField();
		txtApodoEmpleado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtApodoEmpleado.setBounds(160, 135, 215, 30);
		txtApodoEmpleado.setBorder(BorderFactory.createLineBorder(new Color(210, 190, 170)));

		txtPasswordEmpleado = new JPasswordField();
		txtPasswordEmpleado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPasswordEmpleado.setBounds(160, 185, 215, 30);
		txtPasswordEmpleado.setBorder(BorderFactory.createLineBorder(new Color(210, 190, 170)));

		comboCategoriaEmpleado = new JComboBox<String>();
		comboCategoriaEmpleado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboCategoriaEmpleado.setBounds(160, 235, 215, 30);
		comboCategoriaEmpleado.setBackground(new Color(255, 252, 247));
		comboCategoriaEmpleado.setForeground(new Color(55, 45, 40));
		comboCategoriaEmpleado.addItem("APRENDIZ");
		comboCategoriaEmpleado.addItem("OFICIAL");
		comboCategoriaEmpleado.addItem("MAESTRO");
	}

	/**
	 * Configura los botones del formulario.
	 */
	private void configurarBotones() {
		btnGuardarEmpleado = crearBoton("Guardar");
		btnGuardarEmpleado.setBounds(145, 305, 110, 35);

		btnCancelar = crearBoton("Cancelar");
		btnCancelar.setBounds(265, 305, 110, 35);

		btnGuardarEmpleado.addActionListener(e -> guardarEmpleado());
		btnCancelar.addActionListener(e -> dispose());
	}

	/**
	 * Crea un botón estilizado.
	 */
	private JButton crearBoton(String texto) {
		JButton boton = new JButton(texto);
		boton.setFont(new Font("Tahoma", Font.BOLD, 13));
		boton.setForeground(new Color(91, 62, 46));
		boton.setBackground(new Color(230, 215, 200));
		boton.setFocusPainted(false);
		boton.setBorder(BorderFactory.createLineBorder(new Color(210, 190, 170)));
		return boton;
	}

	/**
	 * Agrega todos los componentes al formulario.
	 */
	private void agregarComponentes() {
		getContentPane().add(panelFondo);
		panelFondo.add(panelFormulario);

		panelFormulario.add(lblTitulo);
		panelFormulario.add(lblNombreEmpleado);
		panelFormulario.add(txtNombreEmpleado);
		panelFormulario.add(lblApodoEmpleado);
		panelFormulario.add(txtApodoEmpleado);
		panelFormulario.add(lblPasswordEmpleado);
		panelFormulario.add(txtPasswordEmpleado);
		panelFormulario.add(lblCategoriaEmpleado);
		panelFormulario.add(comboCategoriaEmpleado);
		panelFormulario.add(btnGuardarEmpleado);
		panelFormulario.add(btnCancelar);
	}

	/**
	 * Carga los datos del empleado en el formulario para edición.
	 */
	private void cargarDatosEmpleado() {
		if (empleadoEditar != null) {
			txtNombreEmpleado.setText(empleadoEditar.getNombreEmpleado());
			txtApodoEmpleado.setText(empleadoEditar.getApodoEmpleado());
			txtPasswordEmpleado.setText(empleadoEditar.getPasswordEmpleado());
			comboCategoriaEmpleado.setSelectedItem(empleadoEditar.getCategoriaEmpleado());
		}
	}

	/**
	 * Guarda o modifica el empleado según corresponda.
	 */
	private void guardarEmpleado() {
		String nombreEmpleado = txtNombreEmpleado.getText();
		String apodoEmpleado = txtApodoEmpleado.getText();
		String passwordEmpleado = new String(txtPasswordEmpleado.getPassword());
		String categoriaEmpleado = comboCategoriaEmpleado.getSelectedItem().toString();

		boolean guardado;

		if (empleadoEditar == null) {
			guardado = empleadoControlador.guardarEmpleado(nombreEmpleado, apodoEmpleado, passwordEmpleado,
					categoriaEmpleado);
		} else {
			guardado = empleadoControlador.modificarEmpleado(empleadoEditar.getIdEmpleado(), nombreEmpleado,
					apodoEmpleado, passwordEmpleado, categoriaEmpleado);
		}

		if (guardado) {
			JOptionPane.showMessageDialog(this, "Empleado guardado correctamente.");
			panelEmpleados.refrescarTablaEmpleados();
			dispose();
		} else {
			JOptionPane.showMessageDialog(this, "No se ha podido guardar el empleado. Revisa los datos.");
		}
	}
}
