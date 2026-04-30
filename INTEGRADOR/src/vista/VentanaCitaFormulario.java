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

import controlador.CitaControlador;
import modelo.Cita;
import modelo.Empleado;
import modelo.Taller;
import modelo.Traje;

public class VentanaCitaFormulario extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel panelFondo;
	private JPanel panelFormulario;

	private JLabel lblTitulo;
	private JLabel lblFechaCita;
	private JLabel lblHoraCita;
	private JLabel lblDuracionCita;
	private JLabel lblTaller;
	private JLabel lblResponsable;
	private JLabel lblTraje;

	private JTextField txtFechaCita;
	private JTextField txtHoraCita;
	private JTextField txtDuracionCita;

	private JComboBox<Taller> comboTaller;
	private JComboBox<Empleado> comboResponsable;
	private JComboBox<Traje> comboTraje;

	private JButton btnGuardarCita;
	private JButton btnCancelar;

	private CitaControlador citaControlador;
	private PanelCitas panelCitas;
	private Cita citaEditar;

	/**
	 * @wbp.parser.constructor
	 */
	public VentanaCitaFormulario(PanelCitas panelCitas) {
		this.panelCitas = panelCitas;
		this.citaEditar = null;
		citaControlador = new CitaControlador();

		configurarVentana();
		configurarPaneles();
		configurarLabels();
		configurarCampos();
		configurarBotones();
		agregarComponentes();
		cargarCombos();

		lblTitulo.setText("Nueva cita");
	}

	public VentanaCitaFormulario(PanelCitas panelCitas, Cita citaEditar) {
		this.panelCitas = panelCitas;
		this.citaEditar = citaEditar;
		citaControlador = new CitaControlador();

		configurarVentana();
		configurarPaneles();
		configurarLabels();
		configurarCampos();
		configurarBotones();
		agregarComponentes();
		cargarCombos();
		cargarDatosCita();

		lblTitulo.setText("Editar cita");
	}

	private void configurarVentana() {
		setTitle("Formulario cita");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 540, 540);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(null);
	}

	private void configurarPaneles() {
		panelFondo = new JPanel();
		panelFondo.setLayout(null);
		panelFondo.setBackground(new Color(245, 239, 230));
		panelFondo.setBounds(0, 0, 524, 501);

		panelFormulario = new JPanel();
		panelFormulario.setLayout(null);
		panelFormulario.setBackground(new Color(255, 252, 247));
		panelFormulario.setBorder(BorderFactory.createLineBorder(new Color(220, 200, 180)));
		panelFormulario.setBounds(35, 30, 455, 430);
	}

	private void configurarLabels() {
		lblTitulo = new JLabel();
		lblTitulo.setFont(new Font("Serif", Font.BOLD, 30));
		lblTitulo.setForeground(new Color(91, 62, 46));
		lblTitulo.setBounds(35, 20, 330, 40);

		lblFechaCita = new JLabel("Fecha");
		lblFechaCita.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFechaCita.setForeground(new Color(55, 45, 40));
		lblFechaCita.setBounds(35, 80, 120, 25);

		lblHoraCita = new JLabel("Hora");
		lblHoraCita.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblHoraCita.setForeground(new Color(55, 45, 40));
		lblHoraCita.setBounds(35, 130, 120, 25);

		lblDuracionCita = new JLabel("Duración");
		lblDuracionCita.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDuracionCita.setForeground(new Color(55, 45, 40));
		lblDuracionCita.setBounds(35, 180, 120, 25);

		lblTaller = new JLabel("Taller");
		lblTaller.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTaller.setForeground(new Color(55, 45, 40));
		lblTaller.setBounds(35, 235, 120, 25);

		lblResponsable = new JLabel("Responsable");
		lblResponsable.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblResponsable.setForeground(new Color(55, 45, 40));
		lblResponsable.setBounds(35, 285, 120, 25);

		lblTraje = new JLabel("Traje");
		lblTraje.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTraje.setForeground(new Color(55, 45, 40));
		lblTraje.setBounds(35, 335, 120, 25);
	}

	private void configurarCampos() {
		txtFechaCita = new JTextField();
		txtFechaCita.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtFechaCita.setBounds(170, 80, 240, 30);
		txtFechaCita.setBorder(BorderFactory.createLineBorder(new Color(210, 190, 170)));
		txtFechaCita.setColumns(10);

		txtHoraCita = new JTextField();
		txtHoraCita.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtHoraCita.setBounds(170, 130, 240, 30);
		txtHoraCita.setBorder(BorderFactory.createLineBorder(new Color(210, 190, 170)));
		txtHoraCita.setColumns(10);

		txtDuracionCita = new JTextField();
		txtDuracionCita.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtDuracionCita.setBounds(170, 180, 240, 30);
		txtDuracionCita.setBorder(BorderFactory.createLineBorder(new Color(210, 190, 170)));
		txtDuracionCita.setColumns(10);

		comboTaller = new JComboBox<Taller>();
		comboTaller.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboTaller.setBounds(170, 235, 240, 30);
		comboTaller.setBackground(new Color(255, 252, 247));
		comboTaller.setForeground(new Color(55, 45, 40));

		comboResponsable = new JComboBox<Empleado>();
		comboResponsable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboResponsable.setBounds(170, 285, 240, 30);
		comboResponsable.setBackground(new Color(255, 252, 247));
		comboResponsable.setForeground(new Color(55, 45, 40));

		comboTraje = new JComboBox<Traje>();
		comboTraje.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboTraje.setBounds(170, 335, 240, 30);
		comboTraje.setBackground(new Color(255, 252, 247));
		comboTraje.setForeground(new Color(55, 45, 40));
	}

	private void configurarBotones() {
		btnGuardarCita = crearBoton("Guardar");
		btnGuardarCita.setBounds(170, 385, 110, 35);

		btnCancelar = crearBoton("Cancelar");
		btnCancelar.setBounds(300, 385, 110, 35);

		btnGuardarCita.addActionListener(e -> guardarCita());
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

		panelFormulario.add(lblFechaCita);
		panelFormulario.add(txtFechaCita);

		panelFormulario.add(lblHoraCita);
		panelFormulario.add(txtHoraCita);

		panelFormulario.add(lblDuracionCita);
		panelFormulario.add(txtDuracionCita);

		panelFormulario.add(lblTaller);
		panelFormulario.add(comboTaller);

		panelFormulario.add(lblResponsable);
		panelFormulario.add(comboResponsable);

		panelFormulario.add(lblTraje);
		panelFormulario.add(comboTraje);

		panelFormulario.add(btnGuardarCita);
		panelFormulario.add(btnCancelar);
	}

	private void cargarCombos() {
		ArrayList<Taller> listaTalleres = citaControlador.obtenerTalleres();
		ArrayList<Empleado> listaEmpleados = citaControlador.obtenerEmpleados();
		ArrayList<Traje> listaTrajes = citaControlador.obtenerTrajes();

		for (Taller taller : listaTalleres) {
			comboTaller.addItem(taller);
		}

		for (Empleado empleado : listaEmpleados) {
			comboResponsable.addItem(empleado);
		}

		for (Traje traje : listaTrajes) {
			comboTraje.addItem(traje);
		}
	}

	private void cargarDatosCita() {
		if (citaEditar != null) {
			txtFechaCita.setText(citaEditar.getFechaCita());
			txtHoraCita.setText(citaEditar.getHoraCita());
			txtDuracionCita.setText(String.valueOf(citaEditar.getDuracionCita()));

			for (int i = 0; i < comboTaller.getItemCount(); i++) {
				Taller taller = comboTaller.getItemAt(i);

				if (taller.getIdTaller() == citaEditar.getIdTaller()) {
					comboTaller.setSelectedIndex(i);
				}
			}

			for (int i = 0; i < comboResponsable.getItemCount(); i++) {
				Empleado empleado = comboResponsable.getItemAt(i);

				if (empleado.getIdEmpleado() == citaEditar.getIdResponsable()) {
					comboResponsable.setSelectedIndex(i);
				}
			}

			for (int i = 0; i < comboTraje.getItemCount(); i++) {
				Traje traje = comboTraje.getItemAt(i);

				if (traje.getIdTraje() == citaEditar.getIdTraje()) {
					comboTraje.setSelectedIndex(i);
				}
			}
		}
	}

	private void guardarCita() {
		String fechaCita = txtFechaCita.getText();
		String horaCita = txtHoraCita.getText();
		String duracionCita = txtDuracionCita.getText();

		Taller tallerSeleccionado = (Taller) comboTaller.getSelectedItem();
		Empleado responsableSeleccionado = (Empleado) comboResponsable.getSelectedItem();
		Traje trajeSeleccionado = (Traje) comboTraje.getSelectedItem();

		if (tallerSeleccionado == null || responsableSeleccionado == null || trajeSeleccionado == null) {
			JOptionPane.showMessageDialog(this, "Debes seleccionar taller, responsable y traje.");
			return;
		}

		boolean guardado;

		if (citaEditar == null) {
			guardado = citaControlador.guardarCita(
					fechaCita,
					horaCita,
					duracionCita,
					tallerSeleccionado.getIdTaller(),
					responsableSeleccionado.getIdEmpleado(),
					trajeSeleccionado.getIdTraje());
		} else {
			guardado = citaControlador.modificarCita(
					citaEditar.getIdCita(),
					fechaCita,
					horaCita,
					duracionCita,
					tallerSeleccionado.getIdTaller(),
					responsableSeleccionado.getIdEmpleado(),
					trajeSeleccionado.getIdTraje());
		}

		if (guardado) {
			JOptionPane.showMessageDialog(this, "Cita guardada correctamente.");
			panelCitas.refrescarTablaCitas();
			dispose();
		} else {
			JOptionPane.showMessageDialog(this, "No se ha podido guardar la cita. Revisa los datos.");
		}
	}
}