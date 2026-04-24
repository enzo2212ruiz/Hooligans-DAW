package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.net.URL;
import controlador.*;

public class VentanaMenu extends JFrame {

    private JPanel panelLateral, panelTop, panelContenido;
    private JButton btnCitas, btnClientes, btnTalleres, btnEmpleados;
    private Usuario usuario;

    public VentanaMenu(Usuario usuario) {
        this.usuario = usuario;
        setTitle("Menú principal - Edna Moda");
        setBounds(300, 100, 950, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        crearPanelLateral();
        crearPanelTop();
        crearPanelContenido();
        
        configurarPermisos(usuario.getCategoria());

        setVisible(true);
    }

    private void crearPanelLateral() {
        panelLateral = new JPanel();
        panelLateral.setLayout(null);
        panelLateral.setBounds(0, 0, 200, 650);
        panelLateral.setBackground(new Color(45, 52, 54));
        getContentPane().add(panelLateral);

        URL urlLogo = getClass().getResource("/img/logo_edna.png");
        if (urlLogo != null) {
            ImageIcon iconOriginal = new ImageIcon(urlLogo);
            Image imgResized = iconOriginal.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            JLabel lblLogo = new JLabel(new ImageIcon(imgResized));
            lblLogo.setBounds(50, 20, 100, 100); 
            panelLateral.add(lblLogo);
        }

        btnCitas = new JButton("Citas");
        btnCitas.setBounds(20, 180, 150, 35);
        panelLateral.add(btnCitas);

        btnClientes = new JButton("Clientes");
        btnClientes.setBounds(20, 230, 150, 35);
        panelLateral.add(btnClientes);

        btnTalleres = new JButton("Talleres");
        btnTalleres.setBounds(20, 280, 150, 35);
        panelLateral.add(btnTalleres);

        btnEmpleados = new JButton("Empleados");
        btnEmpleados.setBounds(20, 330, 150, 35);
        panelLateral.add(btnEmpleados);

        // --- ACCIONES DE LOS BOTONES (SISTEMA MODULAR) ---
        
        // Botón Citas
        btnCitas.addActionListener(e -> {
            limpiarPanel();
            panelContenido.add(new PanelCita(usuario));
            actualizarInterfaz();
        });

        // Botón Clientes
        btnClientes.addActionListener(e -> {
            limpiarPanel();
            panelContenido.add(new PanelCliente(usuario));
            actualizarInterfaz();
        });
        // Botón Empleado
        btnEmpleados.addActionListener(e -> {
            limpiarPanel();
            panelContenido.add(new PanelEmpleado(usuario));
            actualizarInterfaz();
        });

        // Botón Taller
        btnTalleres.addActionListener(e -> {
            limpiarPanel();
            panelContenido.add(new PanelTaller(usuario));
            actualizarInterfaz();
        });
        
    }

    private void crearPanelTop() {
        panelTop = new JPanel();
        panelTop.setLayout(null);
        panelTop.setBounds(200, 0, 750, 80);
        panelTop.setBackground(new Color(236, 240, 241));
        getContentPane().add(panelTop);

        JLabel lblUsuario = new JLabel("Bienvenido: " + usuario.getNombre() + " (" + usuario.getCategoria() + ")");
        lblUsuario.setBounds(20, 25, 450, 30);
        panelTop.add(lblUsuario);

        JButton btnCerrar = new JButton("Cerrar sesión");
        btnCerrar.setBounds(580, 25, 130, 30);
        panelTop.add(btnCerrar);
        btnCerrar.addActionListener(e -> { dispose(); new VentanaLogin(); });
    }

    private void crearPanelContenido() {
        panelContenido = new JPanel();
        panelContenido.setLayout(null);
        panelContenido.setBounds(200, 80, 750, 570);
        panelContenido.setBackground(Color.WHITE);
        getContentPane().add(panelContenido);
    }

    /**
     * Limpia el panel y fuerza el refresco visual
     */
    public void limpiarPanel() {
        panelContenido.removeAll();
    }

    private void actualizarInterfaz() {
        panelContenido.revalidate();
        panelContenido.repaint();
    }

    private void configurarPermisos(String rol) {
        if (rol.equals("APRENDIZ")) {
            btnClientes.setVisible(false); btnTalleres.setVisible(false); btnEmpleados.setVisible(false);
        } else if (rol.equals("OFICIAL")) {
            btnEmpleados.setVisible(false);
        }
    }



}