
package vista;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controlador.TrajeControlador;
import modelo.Cliente;
import modelo.Traje;

/**
 * Ventana utilizada para crear o modificar trajes.
 * Recoge los datos del formulario y los envía al controlador.
 */
public class VentanaTrajeFormulario extends JFrame {



	private JPanel panelFondo;
	private JPanel panelFormulario;

	private JLabel lblTitulo;
	private JLabel lblNombreTraje;
	private JLabel lblEstadoTraje;
	private JLabel lblCliente;

	private JTextField txtNombreTraje;
	private JComboBox<String> comboEstadoTraje;
	private JComboBox<Cliente> comboCliente;

	private JButton btnGuardarTraje;
	private JButton btnCancelar;

	private TrajeControlador trajeControlador;
	private PanelTrajes panelTrajes;
	private Traje trajeEditar;

	/**
	 * Constructor usado para crear un traje nuevo.
	 */
	public VentanaTrajeFormulario(PanelTrajes panelTrajes) {
		this.panelTrajes = panelTrajes;
		this.trajeEditar = null;
		trajeControlador = new TrajeControlador();

		configurarVentana();
		configurarPaneles();
		configurarLabels();
		configurarCampos();
		configurarBotones();
		agregarComponentes();
		cargarClientes();

		lblTitulo.setText("Nuevo traje");
	}

	/**
	 * Constructor para editar un traje existente.
	 */
	public VentanaTrajeFormulario(PanelTrajes panelTrajes, Traje trajeEditar) {
		this.panelTrajes = panelTrajes;
		this.trajeEditar = trajeEditar;
		trajeControlador = new TrajeControlador();

		configurarVentana();
		configurarPaneles();
		configurarLabels();
		configurarCampos();
		configurarBotones();
		agregarComponentes();
		cargarClientes();
		cargarDatosTraje();

		lblTitulo.setText("Editar traje");
	}

	/**
	 * Configura la ventana principal.
	 */
	private void configurarVentana() {
		setTitle("Formulario traje");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 430);
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
		panelFondo.setBounds(0, 0, 484, 391);

		panelFormulario = new JPanel();
		panelFormulario.setLayout(null);
		panelFormulario.setBackground(new Color(255, 252, 247));
		panelFormulario.setBorder(BorderFactory.createLineBorder(new Color(220, 200, 180)));
		panelFormulario.setBounds(35, 30, 415, 325);
	}

	/**
	 * Configura los labels del formulario.
	 */
	private void configurarLabels() {
		lblTitulo = new JLabel();
		lblTitulo.setFont(new Font("Serif", Font.BOLD, 30));
		lblTitulo.setForeground(new Color(91, 62, 46));
		lblTitulo.setBounds(35, 25, 330, 40);

		lblNombreTraje = new JLabel("Nombre");
		lblNombreTraje.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNombreTraje.setForeground(new Color(55, 45, 40));
		lblNombreTraje.setBounds(35, 85, 120, 25);

		lblEstadoTraje = new JLabel("Estado");
		lblEstadoTraje.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEstadoTraje.setForeground(new Color(55, 45, 40));
		lblEstadoTraje.setBounds(35, 140, 120, 25);

		lblCliente = new JLabel("Cliente");
		lblCliente.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCliente.setForeground(new Color(55, 45, 40));
		lblCliente.setBounds(35, 195, 120, 25);
	}

	/**
	 * Configura los campos de texto y selección.
	 */
	private void configurarCampos() {
		txtNombreTraje = new JTextField();
		txtNombreTraje.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtNombreTraje.setBounds(160, 85, 215, 30);
		txtNombreTraje.setBorder(BorderFactory.createLineBorder(new Color(210, 190, 170)));

		comboEstadoTraje = new JComboBox<String>();
		comboEstadoTraje.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboEstadoTraje.setBounds(160, 140, 215, 30);
		comboEstadoTraje.setBackground(new Color(255, 252, 247));
		comboEstadoTraje.setForeground(new Color(55, 45, 40));
		comboEstadoTraje.addItem("Nuevo");
		comboEstadoTraje.addItem("En proceso");
		comboEstadoTraje.addItem("Usado");
		comboEstadoTraje.addItem("Roto");

		comboCliente = new JComboBox<Cliente>();
		comboCliente.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboCliente.setBounds(160, 195, 215, 30);
		comboCliente.setBackground(new Color(255, 252, 247));
		comboCliente.setForeground(new Color(55, 45, 40));
	}

	/**
	 * Configura los botones del formulario.
	 */
	private void configurarBotones() {
		btnGuardarTraje = crearBoton("Guardar");
		btnGuardarTraje.setBounds(145, 265, 110, 35);

		btnCancelar = crearBoton("Cancelar");
		btnCancelar.setBounds(265, 265, 110, 35);

		btnGuardarTraje.addActionListener(e -> guardarTraje());
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
		panelFormulario.add(lblNombreTraje);
		panelFormulario.add(txtNombreTraje);
		panelFormulario.add(lblEstadoTraje);
		panelFormulario.add(comboEstadoTraje);
		panelFormulario.add(lblCliente);
		panelFormulario.add(comboCliente);
		panelFormulario.add(btnGuardarTraje);
		panelFormulario.add(btnCancelar);
	}

	/**
	 * Carga los clientes en el combo correspondiente.
	 */
	private void cargarClientes() {
		ArrayList<Cliente> listaClientes = trajeControlador.obtenerClientes();

		for (Cliente cliente : listaClientes) {
			comboCliente.addItem(cliente);
		}
	}

	/**
	 * Carga en el formulario los datos del traje que se va a editar.
	 */
	private void cargarDatosTraje() {
		if (trajeEditar != null) {
			txtNombreTraje.setText(trajeEditar.getNombreTraje());
			comboEstadoTraje.setSelectedItem(trajeEditar.getEstadoTraje());

			for (int i = 0; i < comboCliente.getItemCount(); i++) {
				if (comboCliente.getItemAt(i).getIdCliente() == trajeEditar.getIdCliente()) {
					comboCliente.setSelectedIndex(i);
				}
			}
		}
	}

	/**
	 * Recoge los datos del formulario y decide si debe crear un traje nuevo
	 * o modificar uno existente.
	 */
	private void guardarTraje() {
		String nombreTraje = txtNombreTraje.getText();
		String estadoTraje = comboEstadoTraje.getSelectedItem().toString();
		Cliente clienteSeleccionado = (Cliente) comboCliente.getSelectedItem();

		if (clienteSeleccionado == null) {
			JOptionPane.showMessageDialog(this, "Debes seleccionar un cliente.");
			return;
		}

		boolean guardado;

		if (trajeEditar == null) {
			guardado = trajeControlador.guardarTraje(nombreTraje, estadoTraje, clienteSeleccionado.getIdCliente());
		} else {
			guardado = trajeControlador.modificarTraje(trajeEditar.getIdTraje(), nombreTraje, estadoTraje,
					clienteSeleccionado.getIdCliente());
		}

		if (guardado) {
			JOptionPane.showMessageDialog(this, "Traje guardado correctamente.");
			panelTrajes.refrescarTablaTrajes();
			dispose();
		} else {
			JOptionPane.showMessageDialog(this, "No se ha podido guardar el traje. Revisa los datos.");
		}
	}
}
