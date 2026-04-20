//Abre el menú según la categoría del usuario
package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon; // Para cargar la imagen
import java.awt.Color;
import java.awt.Font;
import java.awt.Image; // Para manipular la imagen
import java.net.URL; // Para localizar el archivo
import java.util.ArrayList;
import java.util.List;

import controlador.*;

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
     * * @param usuario usuario que ha iniciado sesión
     */
    public VentanaMenu(Usuario usuario) {

        // Guarda el usuario recibido para poder usar sus datos
        this.usuario = usuario;

        setTitle("Menú principal - Edna Moda");
        setBounds(300, 100, 950, 650);
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
     * Crea el panel lateral con el logo y los botones del menú.
     */
    private void crearPanelLateral() {

        panelLateral = new JPanel();
        panelLateral.setLayout(null);
        panelLateral.setBounds(0, 0, 200, 650);
        panelLateral.setBackground(new Color(45, 52, 54)); // Color gris oscuro moderno
        getContentPane().add(panelLateral);

        // --- CARGA DEL LOGO PNG ---
        URL urlLogo = getClass().getResource("/img/logo_edna.png");
        
        if (urlLogo != null) {
            ImageIcon iconOriginal = new ImageIcon(urlLogo);
            Image imgResized = iconOriginal.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            JLabel lblLogo = new JLabel(new ImageIcon(imgResized));
            lblLogo.setBounds(50, 20, 100, 100); 
            panelLateral.add(lblLogo);
        }

        JLabel lblTitulo = new JLabel("MENÚ");
        lblTitulo.setBounds(70, 130, 100, 30); 
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
        panelLateral.add(lblTitulo);

        btnCitas = new JButton("Citas");
        btnCitas.setBounds(20, 180, 150, 35);
        btnCitas.setFocusPainted(false);
        panelLateral.add(btnCitas);

        btnClientes = new JButton("Clientes");
        btnClientes.setBounds(20, 230, 150, 35);
        btnClientes.setFocusPainted(false);
        panelLateral.add(btnClientes);

        btnTalleres = new JButton("Talleres");
        btnTalleres.setBounds(20, 280, 150, 35);
        btnTalleres.setFocusPainted(false);
        panelLateral.add(btnTalleres);

        btnEmpleados = new JButton("Empleados");
        btnEmpleados.setBounds(20, 330, 150, 35);
        btnEmpleados.setFocusPainted(false);
        panelLateral.add(btnEmpleados);

        // Acciones de cada botón del menú
        btnCitas.addActionListener(e -> mostrarCitas());
        btnClientes.addActionListener(e -> mostrarClientes());
        btnTalleres.addActionListener(e -> mostrarTalleres());
        btnEmpleados.addActionListener(e -> mostrarEmpleados());
    }

    /**
     * Crea el panel superior donde se muestra el usuario logueado
     */
    private void crearPanelTop() {
        panelTop = new JPanel();
        panelTop.setLayout(null);
        panelTop.setBounds(200, 0, 750, 80);
        panelTop.setBackground(new Color(236, 240, 241));
        getContentPane().add(panelTop);

        JLabel lblUsuario = new JLabel("Bienvenido: " + usuario.getNombre() + " - [" + usuario.getCategoria() + "]");
        lblUsuario.setBounds(20, 25, 450, 30);
        lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
        panelTop.add(lblUsuario);

        JButton btnCerrar = new JButton("Cerrar sesión");
        btnCerrar.setBounds(580, 25, 130, 30);
        panelTop.add(btnCerrar);

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
        panelContenido.setBounds(200, 80, 750, 570);
        panelContenido.setBackground(Color.WHITE);
        getContentPane().add(panelContenido);
    }

    /**
     * Muestra el contenido del apartado de citas.
     */
    private void mostrarCitas() {
        limpiarPanel();

        JLabel lbl = new JLabel("GESTIÓN DE CITAS");
        lbl.setBounds(20, 20, 200, 30);
        lbl.setFont(new Font("Tahoma", Font.BOLD, 16));
        panelContenido.add(lbl);

        // Botón Nueva Cita: Ahora con estilo estándar como el de "Volver"
        if (!usuario.getCategoria().equals("APRENDIZ")) {
            JButton btnNuevaCita = new JButton("Nueva Cita");
            btnNuevaCita.setBounds(570, 20, 150, 30);
            btnNuevaCita.setFocusPainted(false); // Quita el recuadro feo al hacer clic
            panelContenido.add(btnNuevaCita);
            
            btnNuevaCita.addActionListener(e -> mostrarFormularioCita());
        }

        String[] columnas = {"ID", "Fecha", "Hora", "Taller", "Responsable", "Traje"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
        
        CitaDAO dao = new CitaDAO();
        for (Cita c : dao.listarCitas()) {
            modelo.addRow(new Object[]{c.getId(), c.getFecha(), c.getHora(), c.getTaller(), c.getResponsable(), c.getTraje()});
        }

        JTable tabla = new JTable(modelo);
        tabla.setRowHeight(25); 
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(20, 60, 700, 450);
        panelContenido.add(scroll);

        panelContenido.repaint();
        panelContenido.revalidate();
    }

    /**
     * Muestra el formulario para crear una nueva cita con lógica de roles extendida.
     */
    private void mostrarFormularioCita() {
        limpiarPanel();

        JLabel lblTitulo = new JLabel("CREAR NUEVA CITA");
        lblTitulo.setBounds(20, 20, 250, 30);
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
        panelContenido.add(lblTitulo);

        // --- CAMPOS COMUNES ---
        JLabel lblClie = new JLabel("Cliente:"); lblClie.setBounds(20, 70, 100, 25); panelContenido.add(lblClie);
        JComboBox<Cliente> comboClie = new JComboBox<>();
        for(Cliente c : new ClienteDAO().listar()) comboClie.addItem(c);
        comboClie.setBounds(120, 70, 200, 25); panelContenido.add(comboClie);

        JLabel lblTall = new JLabel("Taller:"); lblTall.setBounds(20, 110, 100, 25); panelContenido.add(lblTall);
        JComboBox<Taller> comboTall = new JComboBox<>();
        for(Taller t : new TallerDAO().listar()) comboTall.addItem(t);
        comboTall.setBounds(120, 110, 200, 25); panelContenido.add(comboTall);

        JLabel lblFec = new JLabel("Fecha (aaaa-mm-dd):"); lblFec.setBounds(20, 150, 150, 25); panelContenido.add(lblFec);
        JTextField txtFec = new JTextField("2026-04-20"); txtFec.setBounds(170, 150, 150, 25); panelContenido.add(txtFec);

        JLabel lblHor = new JLabel("Hora (hh:mm):"); lblHor.setBounds(20, 190, 100, 25); panelContenido.add(lblHor);
        JTextField txtHor = new JTextField("10:00"); txtHor.setBounds(120, 190, 100, 25); panelContenido.add(txtHor);

        JLabel lblDur = new JLabel("Duración (min):"); lblDur.setBounds(20, 230, 100, 25); panelContenido.add(lblDur);
        JTextField txtDur = new JTextField("60"); txtDur.setBounds(120, 230, 100, 25); panelContenido.add(txtDur);

        JLabel lblTra = new JLabel("Traje:"); lblTra.setBounds(20, 270, 100, 25); panelContenido.add(lblTra);
        JComboBox<Traje> comboTra = new JComboBox<>();
        for(Traje t : new TrajeDAO().listar()) comboTra.addItem(t);
        comboTra.setBounds(120, 270, 200, 25); panelContenido.add(comboTra);

        // --- LÓGICA DE ROLES ---
        JComboBox<Usuario> comboResp = new JComboBox<>();
        JComboBox<Usuario> comboAp1 = new JComboBox<>();
        JComboBox<Usuario> comboAp2 = new JComboBox<>();

        if (usuario.getCategoria().equals("MAESTRO")) {
            JLabel lblR = new JLabel("Responsable:"); lblR.setBounds(400, 70, 100, 25); panelContenido.add(lblR);
            
            // CAMBIO AQUÍ: Ahora listamos TODOS los usuarios para que el Maestro elija libremente
            for(Usuario u : new UsuarioDAO().listarUsuarios()) {
                comboResp.addItem(u);
            }
            comboResp.setBounds(500, 70, 220, 25); panelContenido.add(comboResp);
            
        } else {
            // El Oficial sigue viendo solo aprendices para sus ayudantes
            JLabel lblA = new JLabel("Asignar Aprendices:"); lblA.setBounds(400, 70, 150, 25); panelContenido.add(lblA);
            
            Usuario ning = new Usuario(); 
            ning.setNombre("-- Ninguno --"); 
            ning.setId(-1);
            ning.setCategoria(" "); 
            
            comboAp1.addItem(ning); 
            comboAp2.addItem(ning);
            
            for(Usuario u : new UsuarioDAO().listarPorCategoria("APRENDIZ")) {
                comboAp1.addItem(u); 
                comboAp2.addItem(u);
            }
            comboAp1.setBounds(400, 100, 220, 25); 
            comboAp2.setBounds(400, 135, 220, 25);
            panelContenido.add(comboAp1); 
            panelContenido.add(comboAp2);
        }

        // --- BOTONES ---
        JButton btnConf = new JButton("Confirmar Cita");
        btnConf.setBounds(580, 500, 150, 30);
        btnConf.setFocusPainted(false);
        panelContenido.add(btnConf);

        JButton btnVol = new JButton("Volver");
        btnVol.setBounds(20, 500, 100, 30);
        btnVol.setFocusPainted(false);
        btnVol.addActionListener(e -> mostrarCitas());
        panelContenido.add(btnVol);

        btnConf.addActionListener(e -> {
            try {
                int idResp = usuario.getCategoria().equals("MAESTRO") ? ((Usuario)comboResp.getSelectedItem()).getId() : usuario.getId();
                
                List<Integer> asistentes = new ArrayList<>();
                if(usuario.getCategoria().equals("OFICIAL")) {
                    if(((Usuario)comboAp1.getSelectedItem()).getId() != -1) asistentes.add(((Usuario)comboAp1.getSelectedItem()).getId());
                    if(((Usuario)comboAp2.getSelectedItem()).getId() != -1) asistentes.add(((Usuario)comboAp2.getSelectedItem()).getId());
                }
                
                CitaDAO citaDao = new CitaDAO();
                if(citaDao.insertarCita(txtFec.getText(), txtHor.getText(), Integer.parseInt(txtDur.getText()), 
                   ((Taller)comboTall.getSelectedItem()).getId(), idResp, ((Traje)comboTra.getSelectedItem()).getId(), asistentes)) {
                    JOptionPane.showMessageDialog(this, "Cita guardada correctamente");
                    mostrarCitas();
                }
            } catch(Exception ex) { 
                JOptionPane.showMessageDialog(this, "Error en los datos."); 
            }
        });

        panelContenido.repaint();
        panelContenido.revalidate();
    }

    /**
     * Muestra el contenido del apartado de clientes.
     */
    private void mostrarClientes() {
        limpiarPanel();
        JLabel lbl = new JLabel("GESTIÓN DE CLIENTES");
        lbl.setBounds(20, 20, 200, 30);
        lbl.setFont(new Font("Tahoma", Font.BOLD, 16));
        panelContenido.add(lbl);

        String[] col = {"ID", "Nombre", "Superpoder"};
        DefaultTableModel modelo = new DefaultTableModel(col, 0);
        for (Cliente c : new ClienteDAO().listar()) {
            modelo.addRow(new Object[]{c.getId(), c.getNombre(), c.getSuperpoder()});
        }
        JTable tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(20, 60, 700, 450);
        panelContenido.add(scroll);
        panelContenido.repaint();
        panelContenido.revalidate();
    }

    /**
     * Muestra el contenido del apartado de talleres.
     */
    private void mostrarTalleres() {
        limpiarPanel();
        JLabel lbl = new JLabel("GESTIÓN DE TALLERES");
        lbl.setBounds(20, 20, 200, 30);
        lbl.setFont(new Font("Tahoma", Font.BOLD, 16));
        panelContenido.add(lbl);

        String[] col = {"ID", "Nombre", "Tipo"};
        DefaultTableModel modelo = new DefaultTableModel(col, 0);
        for (Taller t : new TallerDAO().listar()) {
            modelo.addRow(new Object[]{t.getId(), t.getNombre(), t.getTipo()});
        }
        JTable tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(20, 60, 700, 450);
        panelContenido.add(scroll);
        panelContenido.repaint();
        panelContenido.revalidate();
    }

    /**
     * Muestra el contenido del apartado de empleados.
     */
    private void mostrarEmpleados() {
        limpiarPanel();
        JLabel lbl = new JLabel("GESTIÓN DE EMPLEADOS");
        lbl.setBounds(20, 20, 250, 30);
        lbl.setFont(new Font("Tahoma", Font.BOLD, 16));
        panelContenido.add(lbl);

        String[] col = {"Nombre Completo", "Categoría"};
        DefaultTableModel modelo = new DefaultTableModel(col, 0);
        for (Usuario u : new UsuarioDAO().listarUsuarios()) {
            modelo.addRow(new Object[]{u.getNombre(), u.getCategoria()});
        }
        JTable tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(20, 60, 700, 450);
        panelContenido.add(scroll);
        panelContenido.repaint();
        panelContenido.revalidate();
    }

    private void limpiarPanel() {
        panelContenido.removeAll();
        panelContenido.repaint();
        panelContenido.revalidate();
    }

    /**
     * Muestra u oculta botones del menú según la categoría del empleado.
     */
    private void configurarPermisos(String rol) {
        if (rol.equals("APRENDIZ")) {
            btnClientes.setVisible(false);
            btnTalleres.setVisible(false);
            btnEmpleados.setVisible(false);
        } else if (rol.equals("OFICIAL")) {
            btnEmpleados.setVisible(false);
        }
    }
}