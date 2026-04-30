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

import controlador.TallerControlador;
import modelo.Taller;

public class VentanaTallerFormulario extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel panelFondo;
	private JPanel panelFormulario;

	private JLabel lblTitulo;
	private JLabel lblNombreTaller;
	private JLabel lblTipoTaller;

	private JTextField txtNombreTaller;
	private JTextField txtTipoTaller;

	private JButton btnGuardarTaller;
	private JButton btnCancelar;

	private TallerControlador tallerControlador;
	private PanelTalleres panelTalleres;
	private Taller tallerEditar;

	public VentanaTallerFormulario(PanelTalleres panelTalleres) {
		this.panelTalleres = panelTalleres;
		this.tallerEditar = null;
		tallerControlador = new TallerControlador();

		configurarVentana();
		configurarPaneles();
		configurarLabels();
		configurarCamposTexto();
		configurarBotones();
		agregarComponentes();

		lblTitulo.setText("Nuevo taller");
	}

	public VentanaTallerFormulario(PanelTalleres panelTalleres, Taller tallerEditar) {
		this.panelTalleres = panelTalleres;
		this.tallerEditar = tallerEditar;
		tallerControlador = new TallerControlador();

		configurarVentana();
		configurarPaneles();
		configurarLabels();
		configurarCamposTexto();
		configurarBotones();
		agregarComponentes();
		cargarDatosTaller();

		lblTitulo.setText("Editar taller");
	}

	private void configurarVentana() {
		setTitle("Formulario taller");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 470, 390);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(null);
	}

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

	private void configurarLabels() {
		lblTitulo = new JLabel();
		lblTitulo.setFont(new Font("Serif", Font.BOLD, 30));
		lblTitulo.setForeground(new Color(91, 62, 46));
		lblTitulo.setBounds(35, 25, 300, 40);

		lblNombreTaller = new JLabel("Nombre");
		lblNombreTaller.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNombreTaller.setForeground(new Color(55, 45, 40));
		lblNombreTaller.setBounds(35, 85, 120, 25);

		lblTipoTaller = new JLabel("Tipo");
		lblTipoTaller.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTipoTaller.setForeground(new Color(55, 45, 40));
		lblTipoTaller.setBounds(35, 145, 120, 25);
	}

	private void configurarCamposTexto() {
		txtNombreTaller = new JTextField();
		txtNombreTaller.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtNombreTaller.setBounds(150, 85, 195, 30);
		txtNombreTaller.setBorder(BorderFactory.createLineBorder(new Color(210, 190, 170)));
		txtNombreTaller.setColumns(10);

		txtTipoTaller = new JTextField();
		txtTipoTaller.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTipoTaller.setBounds(150, 145, 195, 30);
		txtTipoTaller.setBorder(BorderFactory.createLineBorder(new Color(210, 190, 170)));
		txtTipoTaller.setColumns(10);
	}

	private void configurarBotones() {
		btnGuardarTaller = crearBoton("Guardar");
		btnGuardarTaller.setBounds(115, 220, 105, 35);

		btnCancelar = crearBoton("Cancelar");
		btnCancelar.setBounds(240, 220, 105, 35);

		btnGuardarTaller.addActionListener(e -> guardarTaller());
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
		panelFormulario.add(lblNombreTaller);
		panelFormulario.add(txtNombreTaller);
		panelFormulario.add(lblTipoTaller);
		panelFormulario.add(txtTipoTaller);
		panelFormulario.add(btnGuardarTaller);
		panelFormulario.add(btnCancelar);
	}

	private void cargarDatosTaller() {
		if (tallerEditar != null) {
			txtNombreTaller.setText(tallerEditar.getNombreTaller());
			txtTipoTaller.setText(tallerEditar.getTipoTaller());
		}
	}

	private void guardarTaller() {
		String nombreTaller = txtNombreTaller.getText();
		String tipoTaller = txtTipoTaller.getText();

		boolean guardado;

		if (tallerEditar == null) {
			guardado = tallerControlador.guardarTaller(nombreTaller, tipoTaller);
		} else {
			guardado = tallerControlador.modificarTaller(
					tallerEditar.getIdTaller(),
					nombreTaller,
					tipoTaller);
		}

		if (guardado) {
			JOptionPane.showMessageDialog(this, "Taller guardado correctamente.");
			panelTalleres.refrescarTablaTalleres();
			dispose();
		} else {
			JOptionPane.showMessageDialog(this, "No se ha podido guardar el taller. Revisa los datos.");
		}
	}
}