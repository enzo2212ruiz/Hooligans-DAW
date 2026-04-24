package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import controlador.*;

/**
 * Panel modular para la gestión de talleres.
 */
public class PanelTaller extends JPanel {

    private Usuario usuarioActual;

    public PanelTaller(Usuario usuario) {
        this.usuarioActual = usuario;
        
        setLayout(null);
        setBounds(0, 0, 750, 570);
        setBackground(Color.WHITE);

        mostrarTablaTalleres();
    }

    private void refrescarPanel() {
        removeAll();
        repaint();
        revalidate();
    }

    /**
     * Muestra la tabla con el listado de talleres
     */
    public void mostrarTablaTalleres() {
        refrescarPanel();

        JLabel titulo = new JLabel("GESTIÓN DE TALLERES");
        titulo.setBounds(20, 20, 250, 30);
        titulo.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(titulo);

        // Solo los Maestros tienen permiso para añadir talleres
        if (usuarioActual.getCategoria().equals("MAESTRO")) {
            JButton botonNuevoTaller = new JButton("Añadir Taller");
            botonNuevoTaller.setBounds(570, 20, 150, 30);
            add(botonNuevoTaller);
            botonNuevoTaller.addActionListener(e -> mostrarFormularioRegistro());
        }

        String[] nombresColumnas = {"ID", "Nombre", "Tipo/Ubicación"};
        DefaultTableModel modeloTabla = new DefaultTableModel(nombresColumnas, 0);
        
        // Cargamos los datos desde la lógica de negocio (TallerB)
        for (Taller taller : new TallerB().listar()) {
            modeloTabla.addRow(new Object[]{
                taller.getId(), 
                taller.getNombre(), 
                taller.getTipo()
            });
        }

        JTable tablaDatos = new JTable(modeloTabla);
        JScrollPane scrollPanel = new JScrollPane(tablaDatos);
        scrollPanel.setBounds(20, 60, 700, 450);
        add(scrollPanel);
    }

    /**
     * Muestra el formulario para registrar un taller nuevo
     */
    private void mostrarFormularioRegistro() {
        refrescarPanel();

        JLabel tituloForm = new JLabel("REGISTRO DE NUEVO TALLER");
        tituloForm.setBounds(20, 20, 300, 30);
        tituloForm.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(tituloForm);

        JLabel labelNombre = new JLabel("Nombre del Taller:"); 
        labelNombre.setBounds(20, 70, 150, 25); 
        add(labelNombre);
        
        JTextField campoNombre = new JTextField(); 
        campoNombre.setBounds(180, 70, 200, 25); 
        add(campoNombre);

        JLabel labelUbicacion = new JLabel("Ubicación / Tipo:"); 
        labelUbicacion.setBounds(20, 110, 150, 25); 
        add(labelUbicacion);
        
        JTextField campoUbicacion = new JTextField(); 
        campoUbicacion.setBounds(180, 110, 200, 25); 
        add(campoUbicacion);

        // --- BOTONES DE ACCIÓN ---
        JButton botonGuardar = new JButton("Guardar Taller"); 
        botonGuardar.setBounds(180, 160, 130, 30); 
        add(botonGuardar);
        
        botonGuardar.addActionListener(e -> {
            boolean exito = new TallerB().insertar(
                campoNombre.getText(), 
                campoUbicacion.getText()
            );

            if(exito) { 
                JOptionPane.showMessageDialog(this, "Taller registrado correctamente");
                mostrarTablaTalleres(); 
            } else {
                JOptionPane.showMessageDialog(this, "Error al guardar el taller");
            }
        });

        JButton botonVolver = new JButton("Volver"); 
        botonVolver.setBounds(320, 160, 100, 30); 
        add(botonVolver);
        botonVolver.addActionListener(e -> mostrarTablaTalleres());
    }
}