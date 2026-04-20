//Abre el menú según la categoría del usuario
package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;

import controlador.Usuario;

/**
 * Ventana principal de la aplicación.
 * Muestra un menú lateral y permite enseñar diferentes apartados
 * según la categoría del empleado que haya iniciado sesión.
 */
public class VentanaMenu extends JFrame {

    // Panel lateral donde van los botones del menú
    private JPanel panelLateral;

    // Panel superior donde se muestra el usuario y cerrar sesión
    private JPanel panelTop;

    // Panel central donde se carga el contenido
    private JPanel panelContenido;

    // Botones del menú lateral
    private JButton btnCitas;
    private JButton btnClientes;
    private JButton btnTalleres;
    private JButton btnEmpleados;

    // Usuario que ha iniciado sesión
    private Usuario usuario;

    /**
     * Constructor de la ventana menú.
     * 
     * @param usuario usuario que ha iniciado sesión
     */
    public VentanaMenu(Usuario usuario) {

        // Guarda el usuario recibido para poder usar sus datos
        this.usuario = usuario;

        setTitle("Menú principal");
        setBounds(300, 100, 900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        crearPanelLateral();
        crearPanelTop();
        crearPanelContenido();

        // Según la categoría del usuario se muestran unas opciones u otras
        configurarPermisos(usuario.getCategoria());

        setVisible(true);
    }

    /**
     * Crea el panel lateral con los botones del menú.
     */
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

        // Acciones de cada botón del menú
        btnCitas.addActionListener(e -> mostrarCitas());
        btnClientes.addActionListener(e -> mostrarClientes());
        btnTalleres.addActionListener(e -> mostrarTalleres());
        btnEmpleados.addActionListener(e -> mostrarEmpleados());
    }

    /**
     * Crea el panel superior donde se muestra el usuario logueado
     * y el botón de cerrar sesión.
     */
    private void crearPanelTop() {

        panelTop = new JPanel();
        panelTop.setLayout(null);
        panelTop.setBounds(200, 0, 700, 80);
        panelTop.setBackground(Color.LIGHT_GRAY);
        getContentPane().add(panelTop);

        // Muestra el nombre y la categoría del usuario conectado
        JLabel lblUsuario = new JLabel("Bienvenido: " + usuario.getNombre() + " - " + usuario.getCategoria());
        lblUsuario.setBounds(20, 25, 400, 30);
        panelTop.add(lblUsuario);

        JButton btnCerrar = new JButton("Cerrar sesión");
        btnCerrar.setBounds(550, 25, 130, 30);
        panelTop.add(btnCerrar);

        // Cierra esta ventana y vuelve a la de login
        btnCerrar.addActionListener(e -> {
            dispose();
            new VentanaLogin();
        });
    }

    /**
     * Crea el panel central en el que se irá cambiando el contenido.
     */
    private void crearPanelContenido() {

        panelContenido = new JPanel();
        panelContenido.setLayout(null);
        panelContenido.setBounds(200, 80, 700, 520);
        panelContenido.setBackground(Color.WHITE);
        getContentPane().add(panelContenido);
    }

    /**
     * Muestra el contenido del apartado de citas.
     */
    private void mostrarCitas() {
        limpiarPanel();

        JLabel lbl = new JLabel("GESTIÓN DE CITAS");
        lbl.setBounds(250, 20, 200, 30);
        panelContenido.add(lbl);

        JButton btnCrear = new JButton("Crear cita");
        btnCrear.setBounds(250, 60, 150, 30);
        panelContenido.add(btnCrear);

        panelContenido.repaint();
        panelContenido.revalidate();
    }

    /**
     * Muestra el contenido del apartado de clientes.
     */
    private void mostrarClientes() {
        limpiarPanel();

        JLabel lbl = new JLabel("GESTIÓN DE CLIENTES");
        lbl.setBounds(230, 20, 200, 30);
        panelContenido.add(lbl);

        panelContenido.repaint();
        panelContenido.revalidate();
    }

    /**
     * Muestra el contenido del apartado de talleres.
     */
    private void mostrarTalleres() {
        limpiarPanel();

        JLabel lbl = new JLabel("GESTIÓN DE TALLERES");
        lbl.setBounds(230, 20, 200, 30);
        panelContenido.add(lbl);

        panelContenido.repaint();
        panelContenido.revalidate();
    }

    /**
     * Muestra el contenido del apartado de empleados.
     */
    private void mostrarEmpleados() {
        limpiarPanel();

        JLabel lbl = new JLabel("GESTIÓN DE EMPLEADOS");
        lbl.setBounds(220, 20, 250, 30);
        panelContenido.add(lbl);

        panelContenido.repaint();
        panelContenido.revalidate();
    }

    /**
     * Limpia el panel de contenido para poder cargar otro apartado.
     */
    private void limpiarPanel() {
        panelContenido.removeAll();
        panelContenido.repaint();
        panelContenido.revalidate();
    }

    /**
     * Muestra u oculta botones del menú según la categoría del empleado.
     * 
     * @param rol categoría del usuario logueado
     */
    private void configurarPermisos(String rol) {

        // El aprendiz tiene acceso más limitado
        if (rol.equals("APRENDIZ")) {
            btnClientes.setVisible(false);
            btnTalleres.setVisible(false);
            btnEmpleados.setVisible(false);

        // El oficial tiene acceso intermedio
        } else if (rol.equals("OFICIAL")) {
            btnEmpleados.setVisible(false);

        // El maestro puede verlo todo
        } else if (rol.equals("MAESTRO")) {
            // No se oculta ningún botón
        }
    }
}