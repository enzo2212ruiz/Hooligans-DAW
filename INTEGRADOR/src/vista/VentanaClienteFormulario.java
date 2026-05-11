package vista;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controlador.ClienteControlador;
import modelo.Cliente;

/**
 * Ventana utilizada para crear o modificar clientes.
 * Recoge los datos del formulario y los envía al controlador.
 */
public class VentanaClienteFormulario extends JFrame {



	private JPanel panelFondo;
	private JPanel panelFormulario;

	private JLabel lblTitulo;
	private JLabel lblNombreCliente;
	private JLabel lblSuperpoderCliente;

	private JTextField txtNombreCliente;
	private JTextField txtSuperpoderCliente;

	private JButton btnGuardarCliente;
	private JButton btnCancelar;

	private ClienteControlador clienteControlador;
	private PanelClientes panelClientes;
	private Cliente clienteEditar;

	/**
	 * Constructor usado para crear un cliente nuevo.
	 */
	public VentanaClienteFormulario(PanelClientes panelClientes) {
		this.panelClientes = panelClientes;
		this.clienteEditar = null;
		clienteControlador = new ClienteControlador();

		configurarVentana();
		configurarPaneles();
		configurarLabels();
		configurarCamposTexto();
		configurarBotones();
		agregarComponentes();

		lblTitulo.setText("Nuevo cliente");
	}

	/**
	 * Constructor usado para editar un cliente existente.
	 */
	public VentanaClienteFormulario(PanelClientes panelClientes, Cliente clienteEditar) {
		this.panelClientes = panelClientes;
		this.clienteEditar = clienteEditar;
		clienteControlador = new ClienteControlador();

		configurarVentana();
		configurarPaneles();
		configurarLabels();
		configurarCamposTexto();
		configurarBotones();
		agregarComponentes();
		cargarDatosCliente();

		lblTitulo.setText("Editar cliente");
	}

	/**
	 * Configura la ventana principal.
	 */
	private void configurarVentana() {
		setTitle("Formulario cliente");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 470, 390);
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
		panelFondo.setBounds(0, 0, 454, 351);

		panelFormulario = new JPanel();
		panelFormulario.setLayout(null);
		panelFormulario.setBackground(new Color(255, 252, 247));
		panelFormulario.setBorder(BorderFactory.createLineBorder(new Color(220, 200, 180)));
		panelFormulario.setBounds(35, 30, 385, 285);
	}

	/**
	 * Configura los labels del formulario.
	 */
	private void configurarLabels() {
		lblTitulo = new JLabel();
		lblTitulo.setFont(new Font("Serif", Font.BOLD, 30));
		lblTitulo.setForeground(new Color(91, 62, 46));
		lblTitulo.setBounds(35, 25, 300, 40);

		lblNombreCliente = new JLabel("Nombre");
		lblNombreCliente.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNombreCliente.setForeground(new Color(55, 45, 40));
		lblNombreCliente.setBounds(35, 85, 120, 25);

		lblSuperpoderCliente = new JLabel("Superpoder");
		lblSuperpoderCliente.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSuperpoderCliente.setForeground(new Color(55, 45, 40));
		lblSuperpoderCliente.setBounds(35, 145, 120, 25);
	}

	/**
	 * Configura los campos de texto.
	 */
	private void configurarCamposTexto() {
		txtNombreCliente = new JTextField();
		txtNombreCliente.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtNombreCliente.setBounds(150, 85, 195, 30);
		txtNombreCliente.setBorder(BorderFactory.createLineBorder(new Color(210, 190, 170)));

		txtSuperpoderCliente = new JTextField();
		txtSuperpoderCliente.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtSuperpoderCliente.setBounds(150, 145, 195, 30);
		txtSuperpoderCliente.setBorder(BorderFactory.createLineBorder(new Color(210, 190, 170)));
	}

	/**
	 * Configura los botones del formulario.
	 */
	private void configurarBotones() {
		btnGuardarCliente = crearBoton("Guardar");
		btnGuardarCliente.setBounds(115, 220, 105, 35);

		btnCancelar = crearBoton("Cancelar");
		btnCancelar.setBounds(240, 220, 105, 35);

		btnGuardarCliente.addActionListener(e -> guardarCliente());
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
		panelFormulario.add(lblNombreCliente);
		panelFormulario.add(txtNombreCliente);
		panelFormulario.add(lblSuperpoderCliente);
		panelFormulario.add(txtSuperpoderCliente);
		panelFormulario.add(btnGuardarCliente);
		panelFormulario.add(btnCancelar);
	}

	/**
	 * Carga en el formulario los datos del cliente que se va a editar.
	 */
	private void cargarDatosCliente() {
		if (clienteEditar != null) {
			txtNombreCliente.setText(clienteEditar.getNombreCliente());
			txtSuperpoderCliente.setText(clienteEditar.getSuperpoderCliente());
		}
	}

	/**
	 * Guarda o modifica el cliente según corresponda.
	 */
	private void guardarCliente() {
		String nombreCliente = txtNombreCliente.getText();
		String superpoderCliente = txtSuperpoderCliente.getText();

		boolean guardado;

		if (clienteEditar == null) {
			guardado = clienteControlador.guardarCliente(nombreCliente, superpoderCliente);
		} else {
			guardado = clienteControlador.modificarCliente(clienteEditar.getIdCliente(), nombreCliente,
					superpoderCliente);
		}

		if (guardado) {
			JOptionPane.showMessageDialog(this, "Cliente guardado correctamente.");
			panelClientes.refrescarTablaClientes();
			dispose();
		} else {
			JOptionPane.showMessageDialog(this, "No se ha podido guardar el cliente. Revisa los datos.");
		}
	}
}
