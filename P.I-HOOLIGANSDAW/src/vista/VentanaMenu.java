package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;

public class VentanaMenu extends JFrame {

    // Paneles
    private JPanel panelLateral;
    private JPanel panelTop;
    private JPanel panelContenido;

    // Botones menú
    private JButton btnCitas;
    private JButton btnClientes;
    private JButton btnTalleres;
    private JButton btnEmpleados;

    public VentanaMenu(String rol) {

        setBounds(300, 100, 900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        crearPanelLateral();
        crearPanelTop();
        crearPanelContenido();
        configurarPermisos(rol);

        setVisible(true);
    }

    // PANEL IZQUIERDO
    private void crearPanelLateral() {

        panelLateral = new JPanel();
        panelLateral.setLayout(null);
        panelLateral.setBounds(0, 0, 200, 600);
        panelLateral.setBackground(new Color(70, 130, 180));
        getContentPane().add(panelLateral);

        JLabel lblTitulo = new JLabel("MENÚ");
        lblTitulo.setBounds(60, 30, 100, 30);
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
        panelLateral.add(lblTitulo);

        btnCitas = new JButton("Citas");
        btnCitas.setBounds(20, 100, 150, 30);
        panelLateral.add(btnCitas);

        btnClientes = new JButton("Clientes");
        btnClientes.setBounds(20, 150, 150, 30);
        panelLateral.add(btnClientes);

        btnTalleres = new JButton("Talleres");
        btnTalleres.setBounds(20, 200, 150, 30);
        panelLateral.add(btnTalleres);

        btnEmpleados = new JButton("Empleados");
        btnEmpleados.setBounds(20, 250, 150, 30);
        panelLateral.add(btnEmpleados);

        // Acciones botones
        btnCitas.addActionListener(e -> mostrarCitas());
        btnClientes.addActionListener(e -> mostrarClientes());
        btnTalleres.addActionListener(e -> mostrarTalleres());
    }

    // PANEL SUPERIOR
    private void crearPanelTop() {

        panelTop = new JPanel();
        panelTop.setLayout(null);
        panelTop.setBounds(200, 0, 700, 80);
        panelTop.setBackground(Color.LIGHT_GRAY);
        getContentPane().add(panelTop);

        JLabel lblUsuario = new JLabel("Bienvenido");
        lblUsuario.setBounds(20, 25, 200, 30);
        panelTop.add(lblUsuario);

        JButton btnCerrar = new JButton("Cerrar sesión");
        btnCerrar.setBounds(550, 25, 130, 30);
        panelTop.add(btnCerrar);

        btnCerrar.addActionListener(e -> {
            dispose();
            new VentanaLogin();
        });
    }

    // PANEL CONTENIDO
    private void crearPanelContenido() {

        panelContenido = new JPanel();
        panelContenido.setLayout(null);
        panelContenido.setBounds(200, 80, 700, 520);
        panelContenido.setBackground(Color.WHITE);
        getContentPane().add(panelContenido);
    }

    //  CAMBIAR CONTENIDO

    private void mostrarCitas() {
        limpiarPanel();

        JLabel lbl = new JLabel("GESTIÓN DE CITAS");
        lbl.setBounds(250, 20, 200, 30);
        panelContenido.add(lbl);

        JButton btnCrear = new JButton("Crear cita");
        btnCrear.setBounds(250, 60, 150, 30);
        panelContenido.add(btnCrear);
    }

    private void mostrarClientes() {
        limpiarPanel();

        JLabel lbl = new JLabel("GESTIÓN DE CLIENTES");
        lbl.setBounds(230, 20, 200, 30);
        panelContenido.add(lbl);
    }

    private void mostrarTalleres() {
        limpiarPanel();

        JLabel lbl = new JLabel("GESTIÓN DE TALLERES");
        lbl.setBounds(230, 20, 200, 30);
        panelContenido.add(lbl);
    }

    // LIMPIAR PANEL
    private void limpiarPanel() {
        panelContenido.removeAll();
        panelContenido.repaint();
        panelContenido.revalidate();
    }

    //  PERMISOS
    //	Según que tipo de empleado se meta le saldran diferentes opciones o permisos
    private void configurarPermisos(String rol) {

        if (rol.equals("APRENDIZ")) {
            btnClientes.setVisible(false);
            btnTalleres.setVisible(false);
            btnEmpleados.setVisible(false);

        } else if (rol.equals("OFICIAL")) {
            btnClientes.setVisible(false);
            btnTalleres.setVisible(false);

        } else if (rol.equals("MAESTRO")) {
            // todo visible
        }
    }
}
