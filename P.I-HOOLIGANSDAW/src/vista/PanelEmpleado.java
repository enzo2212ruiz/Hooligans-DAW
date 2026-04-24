package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import controlador.*;

/**
 * Panel modular para la gestión de empleados (Usuarios).
 */
public class PanelEmpleado extends JPanel {

    private Usuario usuarioActual;

    public PanelEmpleado(Usuario usuario) {
        this.usuarioActual = usuario;
        
        setLayout(null);
        setBounds(0, 0, 750, 570);
        setBackground(Color.WHITE);

        mostrarTablaEmpleados();
    }

    private void refrescarPanel() {
        removeAll();
        repaint();
        revalidate();
    }

    /**
     * Muestra la tabla con los empleados registrados
     */
    public void mostrarTablaEmpleados() {
        refrescarPanel();

        JLabel titulo = new JLabel("GESTIÓN DE EMPLEADOS");
        titulo.setBounds(20, 20, 300, 30);
        titulo.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(titulo);

        // Solo los Maestros pueden añadir nuevos empleados
        if (usuarioActual.getCategoria().equals("MAESTRO")) {
            JButton botonNuevo = new JButton("Añadir Empleado");
            botonNuevo.setBounds(570, 20, 150, 30);
            add(botonNuevo);
            botonNuevo.addActionListener(e -> mostrarFormularioRegistro());
        }

        String[] columnas = {"Nombre Completo", "Categoría"};
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0);
        
        // Cargamos la lista de empleados desde la base de datos
        for (Usuario emp : new UsuarioB().listarUsuarios()) {
            modeloTabla.addRow(new Object[]{
                emp.getNombre(), 
                emp.getCategoria()
            });
        }

        JTable tablaEmpleados = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tablaEmpleados);
        scroll.setBounds(20, 60, 700, 450);
        add(scroll);
    }

    /**
     * Muestra el formulario para dar de alta a un empleado
     */
    private void mostrarFormularioRegistro() {
        refrescarPanel();

        JLabel tituloForm = new JLabel("REGISTRO DE NUEVO EMPLEADO");
        tituloForm.setBounds(20, 20, 300, 30);
        tituloForm.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(tituloForm);

        // --- CAMPOS DEL FORMULARIO ---
        JLabel labelNombre = new JLabel("Nombre Completo:"); 
        labelNombre.setBounds(20, 70, 150, 25); 
        add(labelNombre);
        JTextField campoNombre = new JTextField(); 
        campoNombre.setBounds(160, 70, 200, 25); 
        add(campoNombre);

        JLabel labelApodo = new JLabel("Apodo (Login):"); 
        labelApodo.setBounds(20, 110, 150, 25); 
        add(labelApodo);
        JTextField campoApodo = new JTextField(); 
        campoApodo.setBounds(160, 110, 200, 25); 
        add(campoApodo);

        JLabel labelPassword = new JLabel("Contraseña:"); 
        labelPassword.setBounds(20, 150, 150, 25); 
        add(labelPassword);
        JTextField campoPassword = new JTextField(); 
        campoPassword.setBounds(160, 150, 200, 25); 
        add(campoPassword);

        JLabel labelCategoria = new JLabel("Categoría:"); 
        labelCategoria.setBounds(20, 190, 150, 25); 
        add(labelCategoria);
        String[] categoriasDisponibles = {"MAESTRO", "OFICIAL", "APRENDIZ"};
        JComboBox<String> comboCategoria = new JComboBox<>(categoriasDisponibles);
        comboCategoria.setBounds(160, 190, 200, 25); 
        add(comboCategoria);

        // --- BOTONES ---
        JButton botonGuardar = new JButton("Guardar"); 
        botonGuardar.setBounds(160, 240, 100, 30); 
        add(botonGuardar);
        
        botonGuardar.addActionListener(e -> {
            boolean exito = new UsuarioB().insertar(
                campoNombre.getText(), 
                campoApodo.getText(), 
                campoPassword.getText(), 
                (String) comboCategoria.getSelectedItem()
            );

            if(exito) { 
                JOptionPane.showMessageDialog(this, "Empleado creado correctamente");
                mostrarTablaEmpleados(); 
            } else {
                JOptionPane.showMessageDialog(this, "Error al crear empleado");
            }
        });

        JButton botonVolver = new JButton("Volver"); 
        botonVolver.setBounds(270, 240, 100, 30); 
        add(botonVolver);
        botonVolver.addActionListener(e -> mostrarTablaEmpleados());
    }
}