package vista;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import modelo.Empleado;

public class VentanaMenu extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel panelPrincipal;
	private JPanel panelMenu;
	private JPanel panelCabecera;
	private JPanel panelContenido;

	private JLabel lblTitulo;
	private JLabel lblSubtitulo;
	private JLabel lblEmpleadoActual;

	private JButton btnInicio;
	private JButton btnClientes;
	private JButton btnEmpleados;
	private JButton btnTalleres;
	private JButton btnTrajes;
	private JButton btnCitas;
	private JButton btnCerrarSesion;

	private Empleado empleadoActual;

	public VentanaMenu(Empleado empleadoActual) {
		this.empleadoActual = empleadoActual;

		setTitle("Edna Moda - Menú principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		setLocationRelativeTo(null);
		setResizable(false);

		configurarPaneles();
		configurarLabels();
		configurarBotones();
		agregarEventos();

		aplicarPermisos();
		recolocarBotones();
		mostrarInicio();
	}

	private void configurarPaneles() {
		panelPrincipal = new JPanel();
		panelPrincipal.setBackground(new Color(245, 239, 230));
		panelPrincipal.setLayout(null);
		setContentPane(panelPrincipal);

		panelMenu = new JPanel();
		panelMenu.setBackground(new Color(91, 62, 46));
		panelMenu.setBounds(0, 0, 220, 561);
		panelMenu.setLayout(null);
		panelPrincipal.add(panelMenu);

		panelCabecera = new JPanel();
		panelCabecera.setBackground(new Color(255, 252, 247));
		panelCabecera.setBorder(BorderFactory.createLineBorder(new Color(220, 200, 180)));
		panelCabecera.setBounds(220, 0, 764, 75);
		panelCabecera.setLayout(null);
		panelPrincipal.add(panelCabecera);

		panelContenido = new JPanel();
		panelContenido.setBackground(new Color(245, 239, 230));
		panelContenido.setBounds(220, 75, 764, 486);
		panelContenido.setLayout(null);
		panelPrincipal.add(panelContenido);
	}

	private void configurarLabels() {
		lblTitulo = new JLabel("EDNA MODA");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setForeground(new Color(255, 252, 247));
		lblTitulo.setFont(new Font("Serif", Font.BOLD, 28));
		lblTitulo.setBounds(20, 25, 180, 35);
		panelMenu.add(lblTitulo);

		lblSubtitulo = new JLabel("Gestión del taller");
		lblSubtitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubtitulo.setForeground(new Color(230, 215, 200));
		lblSubtitulo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSubtitulo.setBounds(20, 62, 180, 25);
		panelMenu.add(lblSubtitulo);

		lblEmpleadoActual = new JLabel("Empleado: " + empleadoActual.getNombreEmpleado() + " ("
				+ empleadoActual.getCategoriaEmpleado() + ")");
		lblEmpleadoActual.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEmpleadoActual.setForeground(new Color(91, 62, 46));
		lblEmpleadoActual.setBounds(30, 22, 600, 30);
		panelCabecera.add(lblEmpleadoActual);
	}

	private void configurarBotones() {
		btnInicio = crearBotonMenu("Inicio");
		btnInicio.setBounds(35, 115, 150, 36);
		panelMenu.add(btnInicio);

		btnClientes = crearBotonMenu("Clientes");
		btnClientes.setBounds(35, 165, 150, 36);
		panelMenu.add(btnClientes);

		btnEmpleados = crearBotonMenu("Empleados");
		btnEmpleados.setBounds(35, 215, 150, 36);
		panelMenu.add(btnEmpleados);

		btnTalleres = crearBotonMenu("Talleres");
		btnTalleres.setBounds(35, 265, 150, 36);
		panelMenu.add(btnTalleres);

		btnTrajes = crearBotonMenu("Trajes");
		btnTrajes.setBounds(35, 315, 150, 36);
		panelMenu.add(btnTrajes);

		btnCitas = crearBotonMenu("Citas");
		btnCitas.setBounds(35, 365, 150, 36);
		panelMenu.add(btnCitas);

		btnCerrarSesion = crearBotonMenu("Cerrar sesión");
		btnCerrarSesion.setBounds(35, 500, 150, 36);
		panelMenu.add(btnCerrarSesion);
	}

	private JButton crearBotonMenu(String texto) {
		JButton boton = new JButton(texto);
		boton.setFont(new Font("Tahoma", Font.BOLD, 13));
		boton.setForeground(new Color(91, 62, 46));
		boton.setBackground(new Color(230, 215, 200));
		boton.setFocusPainted(false);
		boton.setBorder(BorderFactory.createLineBorder(new Color(210, 190, 170)));
		return boton;
	}

	private void agregarEventos() {
		btnInicio.addActionListener(e -> mostrarInicio());
		btnClientes.addActionListener(e -> mostrarPanel(new PanelClientes()));
		btnEmpleados.addActionListener(e -> mostrarPanel(new PanelEmpleados()));
		btnTalleres.addActionListener(e -> mostrarPanel(new PanelTalleres()));
		btnTrajes.addActionListener(e -> mostrarPanel(new PanelTrajes()));
		btnCitas.addActionListener(e -> mostrarPanel(new PanelCitas()));
		btnCerrarSesion.addActionListener(e -> cerrarSesion());
	}

	private void aplicarPermisos() {
		String categoria = empleadoActual.getCategoriaEmpleado();

		if (categoria.equals("MAESTRO")) {
			btnClientes.setVisible(true);
			btnEmpleados.setVisible(true);
			btnTalleres.setVisible(true);
			btnTrajes.setVisible(true);
			btnCitas.setVisible(true);
		} else if (categoria.equals("OFICIAL")) {
			btnClientes.setVisible(true);
			btnEmpleados.setVisible(false);
			btnTalleres.setVisible(false);
			btnTrajes.setVisible(true);
			btnCitas.setVisible(true);
		} else if (categoria.equals("APRENDIZ")) {
			btnClientes.setVisible(false);
			btnEmpleados.setVisible(false);
			btnTalleres.setVisible(false);
			btnTrajes.setVisible(false);
			btnCitas.setVisible(true);
		}
	}

	private void recolocarBotones() {
		btnInicio.setBounds(35, 115, 150, 36);

		int posicionY = 165;

		if (btnClientes.isVisible()) {
			btnClientes.setBounds(35, posicionY, 150, 36);
			posicionY += 50;
		}

		if (btnEmpleados.isVisible()) {
			btnEmpleados.setBounds(35, posicionY, 150, 36);
			posicionY += 50;
		}

		if (btnTalleres.isVisible()) {
			btnTalleres.setBounds(35, posicionY, 150, 36);
			posicionY += 50;
		}

		if (btnTrajes.isVisible()) {
			btnTrajes.setBounds(35, posicionY, 150, 36);
			posicionY += 50;
		}

		if (btnCitas.isVisible()) {
			btnCitas.setBounds(35, posicionY, 150, 36);
		}

		btnCerrarSesion.setBounds(35, 500, 150, 36);
	}

	private void mostrarInicio() {
		panelContenido.removeAll();

		JPanel panelBienvenida = new JPanel();
		panelBienvenida.setLayout(null);
		panelBienvenida.setBackground(new Color(255, 252, 247));
		panelBienvenida.setBorder(BorderFactory.createLineBorder(new Color(220, 200, 180)));
		panelBienvenida.setBounds(85, 95, 590, 255);
		panelContenido.add(panelBienvenida);

		JLabel lblBienvenida = new JLabel("Bienvenido/a, " + empleadoActual.getNombreEmpleado());
		lblBienvenida.setHorizontalAlignment(SwingConstants.CENTER);
		lblBienvenida.setFont(new Font("Serif", Font.BOLD, 34));
		lblBienvenida.setForeground(new Color(91, 62, 46));
		lblBienvenida.setBounds(35, 45, 520, 45);
		panelBienvenida.add(lblBienvenida);

		JLabel lblDescripcion = new JLabel("Sistema de gestión del taller Edna Moda");
		lblDescripcion.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblDescripcion.setForeground(new Color(160, 115, 80));
		lblDescripcion.setBounds(35, 105, 520, 30);
		panelBienvenida.add(lblDescripcion);

		JLabel lblCategoria = new JLabel("Categoría: " + empleadoActual.getCategoriaEmpleado());
		lblCategoria.setHorizontalAlignment(SwingConstants.CENTER);
		lblCategoria.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCategoria.setForeground(new Color(55, 45, 40));
		lblCategoria.setBounds(35, 155, 520, 30);
		panelBienvenida.add(lblCategoria);

		JLabel lblInfo = new JLabel("Seleccione una opción del menú lateral");
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblInfo.setForeground(new Color(120, 95, 75));
		lblInfo.setBounds(35, 195, 520, 25);
		panelBienvenida.add(lblInfo);

		panelContenido.repaint();
		panelContenido.revalidate();
	}

	private void mostrarPanel(JPanel panel) {
		panelContenido.removeAll();

		panel.setBounds(0, 0, 764, 486);
		panelContenido.add(panel);

		panelContenido.repaint();
		panelContenido.revalidate();
	}

	private void cerrarSesion() {
		VentanaLogin ventanaLogin = new VentanaLogin();
		ventanaLogin.setVisible(true);
		dispose();
	}
}