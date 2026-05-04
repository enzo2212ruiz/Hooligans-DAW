package vista;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controlador.LoginControlador;
import modelo.Empleado;

/**
 * Ventana de inicio de sesión para acceder al sistema.
 */
public class VentanaLogin extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel panelFondo;
	private JPanel panelLogin;

	private JLabel lblTitulo;
	private JLabel lblSubtitulo;
	private JLabel lblApodo;
	private JLabel lblPassword;

	private JTextField txtApodo;
	private JPasswordField txtPassword;

	private JButton btnEntrar;
	private JButton btnLimpiar;

	private LoginControlador loginControlador;

	/**
	 * Construye la ventana de login.
	 */
	public VentanaLogin() {
		loginControlador = new LoginControlador();

		setTitle("Login Edna Moda");
		setBounds(100, 100, 620, 620);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		configurarPaneles();
		configurarLabels();
		configurarCamposTexto();
		configurarBotones();
		agregarComponentes();
	}

	/**
	 * Configura los paneles principales.
	 */
	private void configurarPaneles() {
		getContentPane().setLayout(null);

		panelFondo = new JPanel();
		panelFondo.setBackground(new Color(245, 239, 230));
		panelFondo.setBounds(0, 0, 604, 581);
		panelFondo.setLayout(null);

		panelLogin = new JPanel();
		panelLogin.setBackground(new Color(255, 252, 247));
		panelLogin.setBounds(76, 60, 450, 475);
		panelLogin.setLayout(null);
		panelLogin.setBorder(BorderFactory.createLineBorder(new Color(220, 200, 180)));
	}

	/**
	 * Configura los labels del formulario.
	 */
	private void configurarLabels() {
		lblTitulo = new JLabel("Edna Moda");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setForeground(new Color(91, 62, 46));
		lblTitulo.setFont(new Font("Serif", Font.BOLD, 42));
		lblTitulo.setBounds(60, 45, 330, 55);

		lblSubtitulo = new JLabel("Gestión del taller");
		lblSubtitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubtitulo.setForeground(new Color(160, 115, 80));
		lblSubtitulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSubtitulo.setBounds(60, 98, 330, 30);

		lblApodo = new JLabel("Usuario");
		lblApodo.setForeground(new Color(55, 45, 40));
		lblApodo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblApodo.setBounds(70, 165, 150, 25);

		lblPassword = new JLabel("Contraseña");
		lblPassword.setForeground(new Color(55, 45, 40));
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPassword.setBounds(70, 245, 150, 25);
	}

	/**
	 * Configura los campos de texto.
	 */
	private void configurarCamposTexto() {
		txtApodo = new JTextField();
		txtApodo.setBounds(70, 195, 310, 38);
		txtApodo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtApodo.setBorder(BorderFactory.createLineBorder(new Color(210, 190, 170)));

		txtPassword = new JPasswordField();
		txtPassword.setBounds(70, 275, 310, 38);
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtPassword.setBorder(BorderFactory.createLineBorder(new Color(210, 190, 170)));
	}

	/**
	 * Configura los botones del formulario.
	 */
	private void configurarBotones() {
		btnEntrar = new JButton("Entrar");
		btnEntrar.setBounds(70, 365, 140, 42);
		btnEntrar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnEntrar.setForeground(new Color(91, 62, 46));
		btnEntrar.setBackground(new Color(230, 215, 200));
		btnEntrar.setFocusPainted(false);
		btnEntrar.setBorder(BorderFactory.createLineBorder(new Color(210, 190, 170)));

		btnLimpiar = new JButton("Borrar");
		btnLimpiar.setBounds(240, 365, 140, 42);
		btnLimpiar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnLimpiar.setForeground(new Color(91, 62, 46));
		btnLimpiar.setBackground(new Color(230, 215, 200));
		btnLimpiar.setFocusPainted(false);
		btnLimpiar.setBorder(BorderFactory.createLineBorder(new Color(210, 190, 170)));

		btnEntrar.addActionListener(e -> iniciarSesion());
		btnLimpiar.addActionListener(e -> limpiarCampos());

		txtPassword.addActionListener(e -> iniciarSesion());
	}

	/**
	 * Agrega los componentes al panel.
	 */
	private void agregarComponentes() {
		getContentPane().add(panelFondo);
		panelFondo.add(panelLogin);

		panelLogin.add(lblTitulo);
		panelLogin.add(lblSubtitulo);
		panelLogin.add(lblApodo);
		panelLogin.add(txtApodo);
		panelLogin.add(lblPassword);
		panelLogin.add(txtPassword);
		panelLogin.add(btnEntrar);
		panelLogin.add(btnLimpiar);
	}

	/**
	 * Intenta iniciar sesión con los datos introducidos.
	 */
	private void iniciarSesion() {
		String apodoEmpleado = txtApodo.getText().trim();
		String passwordEmpleado = new String(txtPassword.getPassword()).trim();

		if (apodoEmpleado.isEmpty() || passwordEmpleado.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Introduce usuario y contraseña.");
			return;
		}

		Empleado empleado = loginControlador.iniciarSesion(apodoEmpleado, passwordEmpleado);

		if (empleado != null) {
			VentanaMenu ventanaMenu = new VentanaMenu(empleado);
			ventanaMenu.setVisible(true);
			dispose();
		} else {
			JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.");
			txtPassword.setText("");
			txtPassword.requestFocus();
		}
	}

	/**
	 * Limpia los campos del formulario.
	 */
	private void limpiarCampos() {
		txtApodo.setText("");
		txtPassword.setText("");
		txtApodo.requestFocus();
	}

	/**
	 * Método principal para ejecutar la ventana de login.
	 */
	public static void main(String[] args) {
		VentanaLogin ventanaLogin = new VentanaLogin();
		ventanaLogin.setVisible(true);
	}
}
