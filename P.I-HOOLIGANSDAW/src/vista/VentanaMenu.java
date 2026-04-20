//Abre el menú según la categoría del usuario
package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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

        btnCitas.addActionListener(e -> mostrarCitas());
        btnClientes.addActionListener(e -> mostrarClientes());
        btnTalleres.addActionListener(e -> mostrarTalleres());
        btnEmpleados.addActionListener(e -> mostrarEmpleados());
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

    private void limpiarPanel() {
        panelContenido.removeAll();
        panelContenido.repaint();
        panelContenido.revalidate();
    }

    private void configurarPermisos(String rol) {
        if (rol.equals("APRENDIZ")) {
            btnClientes.setVisible(false); btnTalleres.setVisible(false); btnEmpleados.setVisible(false);
        } else if (rol.equals("OFICIAL")) {
            btnEmpleados.setVisible(false);
        }
    }

    // --- SECCIÓN CITAS ---
    private void mostrarCitas() {
        limpiarPanel();
        JLabel lbl = new JLabel("GESTIÓN DE CITAS");
        lbl.setBounds(20, 20, 200, 30);
        lbl.setFont(new Font("Tahoma", Font.BOLD, 16));
        panelContenido.add(lbl);

        if (!usuario.getCategoria().equals("APRENDIZ")) {
            JButton btnNueva = new JButton("Nueva Cita");
            btnNueva.setBounds(570, 20, 150, 30);
            btnNueva.setFocusPainted(false);
            panelContenido.add(btnNueva);
            btnNueva.addActionListener(e -> mostrarFormularioCita());
        }

        String[] columnas = {"ID", "Fecha", "Hora", "Taller", "Responsable", "Traje"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
        for (Cita c : new CitaB().listarCitas()) {
            modelo.addRow(new Object[]{c.getId(), c.getFecha(), c.getHora(), c.getTaller(), c.getResponsable(), c.getTraje()});
        }
        JTable tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(20, 60, 700, 450);
        panelContenido.add(scroll);
    }

    private void mostrarFormularioCita() {
        limpiarPanel();
        JLabel lblTitulo = new JLabel("CREAR NUEVA CITA");
        lblTitulo.setBounds(20, 20, 250, 30);
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
        panelContenido.add(lblTitulo);

        // Campos comunes (Cliente, Taller, Fecha, Hora, Duración, Traje)
        JLabel l1 = new JLabel("Cliente:"); l1.setBounds(20, 70, 100, 25); panelContenido.add(l1);
        JComboBox<Cliente> cbClie = new JComboBox<>(); for(Cliente c : new ClienteB().listar()) cbClie.addItem(c);
        cbClie.setBounds(120, 70, 200, 25); panelContenido.add(cbClie);

        JLabel l2 = new JLabel("Taller:"); l2.setBounds(20, 110, 100, 25); panelContenido.add(l2);
        JComboBox<Taller> cbTall = new JComboBox<>(); for(Taller t : new TallerB().listar()) cbTall.addItem(t);
        cbTall.setBounds(120, 110, 200, 25); panelContenido.add(cbTall);

        JLabel l3 = new JLabel("Fecha:"); l3.setBounds(20, 150, 100, 25); panelContenido.add(l3);
        JTextField tFec = new JTextField("2026-04-20"); tFec.setBounds(120, 150, 100, 25); panelContenido.add(tFec);

        JLabel l4 = new JLabel("Hora:"); l4.setBounds(230, 150, 50, 25); panelContenido.add(l4);
        JTextField tHor = new JTextField("10:00"); tHor.setBounds(280, 150, 80, 25); panelContenido.add(tHor);

        JLabel l5 = new JLabel("Traje:"); l5.setBounds(20, 190, 100, 25); panelContenido.add(l5);
        JComboBox<Traje> cbTraje = new JComboBox<>(); for(Traje t : new TrajeB().listar()) cbTraje.addItem(t);
        cbTraje.setBounds(120, 190, 200, 25); panelContenido.add(cbTraje);

        // Lógica de Responsable
        JComboBox<Usuario> cbResp = new JComboBox<>();
        JComboBox<Usuario> cbAp1 = new JComboBox<>();
        JComboBox<Usuario> cbAp2 = new JComboBox<>();

        if (usuario.getCategoria().equals("MAESTRO")) {
            JLabel lr = new JLabel("Responsable:"); lr.setBounds(400, 70, 100, 25); panelContenido.add(lr);
            for(Usuario u : new UsuarioB().listarUsuarios()) cbResp.addItem(u);
            cbResp.setBounds(500, 70, 220, 25); panelContenido.add(cbResp);
        } else {
            JLabel la = new JLabel("Asignar Aprendices:"); la.setBounds(400, 70, 150, 25); panelContenido.add(la);
            Usuario ning = new Usuario(); ning.setNombre("-- Ninguno --"); ning.setId(-1); ning.setCategoria(" ");
            cbAp1.addItem(ning); cbAp2.addItem(ning);
            for(Usuario u : new UsuarioB().listarPorCategoria("APRENDIZ")) { cbAp1.addItem(u); cbAp2.addItem(u); }
            cbAp1.setBounds(400, 100, 220, 25); cbAp2.setBounds(400, 135, 220, 25);
            panelContenido.add(cbAp1); panelContenido.add(cbAp2);
        }

        JButton btnConf = new JButton("Confirmar");
        btnConf.setBounds(580, 500, 120, 30);
        panelContenido.add(btnConf);
        btnConf.addActionListener(e -> {
            try {
                int idResp = usuario.getCategoria().equals("MAESTRO") ? ((Usuario)cbResp.getSelectedItem()).getId() : usuario.getId();
                List<Integer> asis = new ArrayList<>();
                if(usuario.getCategoria().equals("OFICIAL")){
                    if(((Usuario)cbAp1.getSelectedItem()).getId() != -1) asis.add(((Usuario)cbAp1.getSelectedItem()).getId());
                    if(((Usuario)cbAp2.getSelectedItem()).getId() != -1) asis.add(((Usuario)cbAp2.getSelectedItem()).getId());
                }
                if(new CitaB().insertarCita(tFec.getText(), tHor.getText(), 60, ((Taller)cbTall.getSelectedItem()).getId(), idResp, ((Traje)cbTraje.getSelectedItem()).getId(), asis)) {
                    JOptionPane.showMessageDialog(this, "Cita guardada");
                    mostrarCitas();
                }
            } catch(Exception ex) { JOptionPane.showMessageDialog(this, "Error"); }
        });

        JButton btnVol = new JButton("Volver");
        btnVol.setBounds(20, 500, 100, 30);
        panelContenido.add(btnVol);
        btnVol.addActionListener(e -> mostrarCitas());
    }

    // --- SECCIÓN CLIENTES ---
    private void mostrarClientes() {
        limpiarPanel();
        JLabel lbl = new JLabel("GESTIÓN DE CLIENTES");
        lbl.setBounds(20, 20, 200, 30);
        lbl.setFont(new Font("Tahoma", Font.BOLD, 16));
        panelContenido.add(lbl);

        if (usuario.getCategoria().equals("MAESTRO")) {
            JButton btnNuevo = new JButton("Añadir Cliente");
            btnNuevo.setBounds(570, 20, 150, 30);
            panelContenido.add(btnNuevo);
            btnNuevo.addActionListener(e -> formularioNuevoCliente());
        }

        String[] col = {"ID", "Nombre", "Superpoder"};
        DefaultTableModel modelo = new DefaultTableModel(col, 0);
        for (Cliente c : new ClienteB().listar()) modelo.addRow(new Object[]{c.getId(), c.getNombre(), c.getSuperpoder()});
        JTable tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(20, 60, 700, 450);
        panelContenido.add(scroll);
    }

    private void formularioNuevoCliente() {
        limpiarPanel();
        JLabel lbl = new JLabel("NUEVO CLIENTE");
        lbl.setBounds(20, 20, 200, 30);
        lbl.setFont(new Font("Tahoma", Font.BOLD, 16));
        panelContenido.add(lbl);

        JLabel l1 = new JLabel("Nombre:"); l1.setBounds(20, 70, 100, 25); panelContenido.add(l1);
        JTextField tNom = new JTextField(); tNom.setBounds(120, 70, 200, 25); panelContenido.add(tNom);
        JLabel l2 = new JLabel("Superpoder:"); l2.setBounds(20, 110, 100, 25); panelContenido.add(l2);
        JTextField tPow = new JTextField(); tPow.setBounds(120, 110, 200, 25); panelContenido.add(tPow);

        JButton btnG = new JButton("Guardar"); btnG.setBounds(120, 160, 100, 30); panelContenido.add(btnG);
        btnG.addActionListener(e -> {
            if(new ClienteB().insertar(tNom.getText(), tPow.getText())) { mostrarClientes(); }
        });
        JButton btnV = new JButton("Volver"); btnV.setBounds(230, 160, 100, 30); panelContenido.add(btnV);
        btnV.addActionListener(e -> mostrarClientes());
    }

    // --- SECCIÓN TALLERES ---
    private void mostrarTalleres() {
        limpiarPanel();
        JLabel lbl = new JLabel("GESTIÓN DE TALLERES");
        lbl.setBounds(20, 20, 200, 30);
        lbl.setFont(new Font("Tahoma", Font.BOLD, 16));
        panelContenido.add(lbl);

        if (usuario.getCategoria().equals("MAESTRO")) {
            JButton btnNuevo = new JButton("Añadir Taller");
            btnNuevo.setBounds(570, 20, 150, 30);
            panelContenido.add(btnNuevo);
            btnNuevo.addActionListener(e -> formularioNuevoTaller());
        }

        String[] col = {"ID", "Nombre", "Tipo"};
        DefaultTableModel modelo = new DefaultTableModel(col, 0);
        for (Taller t : new TallerB().listar()) modelo.addRow(new Object[]{t.getId(), t.getNombre(), t.getTipo()});
        JTable tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(20, 60, 700, 450);
        panelContenido.add(scroll);
    }

    private void formularioNuevoTaller() {
        limpiarPanel();
        JLabel lbl = new JLabel("NUEVO TALLER");
        lbl.setBounds(20, 20, 200, 30);
        lbl.setFont(new Font("Tahoma", Font.BOLD, 16));
        panelContenido.add(lbl);

        JLabel l1 = new JLabel("Nombre:"); l1.setBounds(20, 70, 100, 25); panelContenido.add(l1);
        JTextField tNom = new JTextField(); tNom.setBounds(120, 70, 200, 25); panelContenido.add(tNom);
        JLabel l2 = new JLabel("Ubicación:"); l2.setBounds(20, 110, 100, 25); panelContenido.add(l2);
        JTextField tTipo = new JTextField(); tTipo.setBounds(120, 110, 200, 25); panelContenido.add(tTipo);

        JButton btnG = new JButton("Guardar"); btnG.setBounds(120, 160, 100, 30); panelContenido.add(btnG);
        btnG.addActionListener(e -> {
            if(new TallerB().insertar(tNom.getText(), tTipo.getText())) { mostrarTalleres(); }
        });
        JButton btnV = new JButton("Volver"); btnV.setBounds(230, 160, 100, 30); panelContenido.add(btnV);
        btnV.addActionListener(e -> mostrarTalleres());
    }

    // --- SECCIÓN EMPLEADOS ---
    private void mostrarEmpleados() {
        limpiarPanel();
        JLabel lbl = new JLabel("GESTIÓN DE EMPLEADOS");
        lbl.setBounds(20, 20, 250, 30);
        lbl.setFont(new Font("Tahoma", Font.BOLD, 16));
        panelContenido.add(lbl);

        if (usuario.getCategoria().equals("MAESTRO")) {
            JButton btnNuevo = new JButton("Añadir Empleado");
            btnNuevo.setBounds(570, 20, 150, 30);
            panelContenido.add(btnNuevo);
            btnNuevo.addActionListener(e -> formularioNuevoEmpleado());
        }

        String[] col = {"Nombre Completo", "Categoría"};
        DefaultTableModel modelo = new DefaultTableModel(col, 0);
        for (Usuario u : new UsuarioB().listarUsuarios()) modelo.addRow(new Object[]{u.getNombre(), u.getCategoria()});
        JTable tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(20, 60, 700, 450);
        panelContenido.add(scroll);
    }

    private void formularioNuevoEmpleado() {
        limpiarPanel();
        JLabel lbl = new JLabel("NUEVO EMPLEADO");
        lbl.setBounds(20, 20, 200, 30);
        lbl.setFont(new Font("Tahoma", Font.BOLD, 16));
        panelContenido.add(lbl);

        JLabel l1 = new JLabel("Nombre Completo:"); l1.setBounds(20, 70, 150, 25); panelContenido.add(l1);
        JTextField tNom = new JTextField(); tNom.setBounds(150, 70, 200, 25); panelContenido.add(tNom);
        
        JLabel lAp = new JLabel("Apodo (Login):"); lAp.setBounds(20, 110, 150, 25); panelContenido.add(lAp);
        JTextField tApodo = new JTextField(); tApodo.setBounds(150, 110, 200, 25); panelContenido.add(tApodo);

        JLabel l2 = new JLabel("Contraseña:"); l2.setBounds(20, 150, 150, 25); panelContenido.add(l2);
        JTextField tPas = new JTextField(); tPas.setBounds(150, 150, 200, 25); panelContenido.add(tPas);
        
        JLabel l3 = new JLabel("Categoría:"); l3.setBounds(20, 190, 150, 25); panelContenido.add(l3);
        String[] cats = {"MAESTRO", "OFICIAL", "APRENDIZ"};
        JComboBox<String> cbCat = new JComboBox<>(cats); cbCat.setBounds(150, 190, 200, 25); panelContenido.add(cbCat);

        JButton btnG = new JButton("Guardar"); btnG.setBounds(150, 240, 100, 30); panelContenido.add(btnG);
        btnG.addActionListener(e -> {
            if(new UsuarioB().insertar(tNom.getText(), tApodo.getText(), tPas.getText(), (String)cbCat.getSelectedItem())) { 
                JOptionPane.showMessageDialog(this, "Empleado creado");
                mostrarEmpleados(); 
            }
        });
        
        JButton btnV = new JButton("Volver"); btnV.setBounds(260, 240, 100, 30); panelContenido.add(btnV);
        btnV.addActionListener(e -> mostrarEmpleados());
    }
}