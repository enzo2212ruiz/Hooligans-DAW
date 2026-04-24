package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import controlador.*;

public class PanelCita extends JPanel {

    private Usuario usuarioActual;
    private JTable tablaDatos;
    private CitaB controlador = new CitaB();

    public PanelCita(Usuario usuario) {
        this.usuarioActual = usuario;
        setLayout(null);
        setBounds(0, 0, 750, 570);
        setBackground(Color.WHITE);
        mostrarPantallaPrincipal();
    }

    public void mostrarPantallaPrincipal() {
        removeAll();
        JLabel titulo = new JLabel("GESTIÓN DE CITAS");
        titulo.setBounds(20, 20, 200, 30);
        titulo.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(titulo);

        if (usuarioActual.getCategoria().equals("MAESTRO")) {
            JButton btnIrCrear = new JButton("Nueva Cita");
            btnIrCrear.setBounds(570, 20, 150, 30);
            add(btnIrCrear);
            btnIrCrear.addActionListener(e -> mostrarFormularioCrear());
        }

        String[] cols = {"ID", "Fecha", "Hora", "Taller", "Responsable", "Traje"};
        DefaultTableModel modelo = new DefaultTableModel(cols, 0);
        for (Cita c : controlador.listarCitas()) {
            modelo.addRow(new Object[]{c.getId(), c.getFecha(), c.getHora(), c.getTaller(), c.getResponsable(), c.getTraje()});
        }
        
        tablaDatos = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tablaDatos);
        scroll.setBounds(20, 60, 700, 380);
        add(scroll);

        JButton btnBorrar = new JButton("Borrar");
        btnBorrar.setBounds(20, 460, 100, 30);
        add(btnBorrar);
        btnBorrar.addActionListener(e -> {
            int fila = tablaDatos.getSelectedRow();
            if (fila != -1) {
                int id = (int) tablaDatos.getValueAt(fila, 0);
                if (controlador.eliminarCita(id)) {
                    mostrarPantallaPrincipal();
                }
            }
        });

        JButton btnModificar = new JButton("Modificar");
        btnModificar.setBounds(130, 460, 100, 30);
        add(btnModificar);
        btnModificar.addActionListener(e -> {
            int fila = tablaDatos.getSelectedRow();
            if (fila != -1) {
                int id = (int) tablaDatos.getValueAt(fila, 0);
                String f = (String) tablaDatos.getValueAt(fila, 1);
                String h = (String) tablaDatos.getValueAt(fila, 2);
                String res = (String) tablaDatos.getValueAt(fila, 4);
                String traj = (String) tablaDatos.getValueAt(fila, 5);
                mostrarFormularioEditar(id, f, h, res, traj);
            }
        });

        revalidate(); repaint();
    }

    private void mostrarFormularioCrear() {
        removeAll();
        JLabel t = new JLabel("NUEVA CITA");
        t.setBounds(20, 20, 200, 30); t.setFont(new Font("Tahoma", Font.BOLD, 16)); add(t);

        JLabel lF = new JLabel("Fecha (AAAA-MM-DD):"); lF.setBounds(20, 70, 150, 25); add(lF);
        JTextField txtFecha = new JTextField(); txtFecha.setBounds(180, 70, 150, 25); add(txtFecha);

        JLabel lH = new JLabel("Hora (HH:MM):"); lH.setBounds(20, 110, 150, 25); add(lH);
        JTextField txtHora = new JTextField(); txtHora.setBounds(180, 110, 150, 25); add(txtHora);

        JLabel lRes = new JLabel("Responsable:"); lRes.setBounds(20, 150, 150, 25); add(lRes);
        JComboBox<String> comboRes = new JComboBox<>();
        for(String n : controlador.obtenerNombresEmpleados()) comboRes.addItem(n);
        comboRes.setBounds(180, 150, 180, 25); add(comboRes);

        JLabel lTraj = new JLabel("Nombre Traje:"); lTraj.setBounds(20, 190, 150, 25); add(lTraj);
        JTextField txtTraje = new JTextField(); txtTraje.setBounds(180, 190, 150, 25); add(txtTraje);

        JButton btnGuardar = new JButton("Guardar Cita");
        btnGuardar.setBounds(180, 240, 120, 30); add(btnGuardar);
        btnGuardar.addActionListener(e -> {
            String nombreEmp = comboRes.getSelectedItem().toString();
            int idEmp = controlador.buscarIdEmpleado(nombreEmp);
            
            boolean ok = controlador.insertarCita(txtFecha.getText(), txtHora.getText(), 60, 1, idEmp, 1);
            if (ok) {
                JOptionPane.showMessageDialog(this, "Cita guardada");
                mostrarPantallaPrincipal();
            }
        });

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(310, 240, 100, 30); add(btnVolver);
        btnVolver.addActionListener(e -> mostrarPantallaPrincipal());

        revalidate(); repaint();
    }

    private void mostrarFormularioEditar(int id, String f, String h, String res, String traj) {
        removeAll();
        JLabel t = new JLabel("EDITANDO CITA #" + id);
        t.setBounds(20, 20, 200, 30); t.setFont(new Font("Tahoma", Font.BOLD, 16)); add(t);

        JTextField txtF = new JTextField(f); txtF.setBounds(130, 70, 150, 25); add(txtF);
        JTextField txtH = new JTextField(h); txtH.setBounds(130, 110, 150, 25); add(txtH);

        JComboBox<String> comboRes = new JComboBox<>();
        for(String n : controlador.obtenerNombresEmpleados()) comboRes.addItem(n);
        comboRes.setSelectedItem(res);
        comboRes.setBounds(130, 150, 180, 25); add(comboRes);

        JTextField txtT = new JTextField(traj); txtT.setBounds(130, 190, 150, 25); add(txtT);

        JButton btnAct = new JButton("Actualizar");
        btnAct.setBounds(130, 240, 120, 30); add(btnAct);
        btnAct.addActionListener(e -> {
            int idEmp = controlador.buscarIdEmpleado(comboRes.getSelectedItem().toString());
            if (controlador.modificarCita(id, txtF.getText(), txtH.getText(), idEmp)) {
                JOptionPane.showMessageDialog(this, "Actualizado");
                mostrarPantallaPrincipal();
            }
        });

        revalidate(); 
        repaint();
    }
}