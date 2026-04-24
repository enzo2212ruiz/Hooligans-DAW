package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import controlador.*;

/**
 * Panel modular para la gestión de clientes.
 */
public class PanelCliente extends JPanel {

    private Usuario usuarioActual;

    public PanelCliente(Usuario usuario) {
        this.usuarioActual = usuario;
        
        setLayout(null);
        setBounds(0, 0, 750, 570);
        setBackground(Color.WHITE);

        mostrarTablaClientes();
    }

    private void refrescarPanel() {
        removeAll();
        repaint();
        revalidate();
    }

    /**
     * Muestra la tabla con el listado de clientes
     */
    public void mostrarTablaClientes() {
        refrescarPanel();

        JLabel titulo = new JLabel("GESTIÓN DE CLIENTES");
        titulo.setBounds(20, 20, 250, 30);
        titulo.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(titulo);

        // Solo el Maestro puede añadir clientes
        if (usuarioActual.getCategoria().equals("MAESTRO")) {
            JButton botonNuevo = new JButton("Añadir Cliente");
            botonNuevo.setBounds(570, 20, 150, 30);
            add(botonNuevo);
            botonNuevo.addActionListener(e -> mostrarFormularioRegistro());
        }

        String[] columnas = {"ID", "Nombre", "Superpoder"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
        
        // Cargamos los datos desde la lógica de negocio
        for (Cliente cliente : new ClienteB().listar()) {
            modelo.addRow(new Object[]{
                cliente.getId(), 
                cliente.getNombre(), 
                cliente.getSuperpoder()
            });
        }

        JTable tablaDatos = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tablaDatos);
        scroll.setBounds(20, 60, 700, 450);
        add(scroll);
    }

    /**
     * Muestra el formulario para crear un nuevo cliente
     */
    private void mostrarFormularioRegistro() {
        refrescarPanel();

        JLabel tituloForm = new JLabel("NUEVO CLIENTE");
        tituloForm.setBounds(20, 20, 200, 30);
        tituloForm.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(tituloForm);

        JLabel labelNombre = new JLabel("Nombre:"); 
        labelNombre.setBounds(20, 70, 100, 25); 
        add(labelNombre);
        
        JTextField campoNombre = new JTextField(); 
        campoNombre.setBounds(120, 70, 200, 25); 
        add(campoNombre);

        JLabel labelPoder = new JLabel("Superpoder:"); 
        labelPoder.setBounds(20, 110, 100, 25); 
        add(labelPoder);
        
        JTextField campoPoder = new JTextField(); 
        campoPoder.setBounds(120, 110, 200, 25); 
        add(campoPoder);

        JButton botonGuardar = new JButton("Guardar"); 
        botonGuardar.setBounds(120, 160, 100, 30); 
        add(botonGuardar);
        
        botonGuardar.addActionListener(e -> {
            if(new ClienteB().insertar(campoNombre.getText(), campoPoder.getText())) { 
                JOptionPane.showMessageDialog(this, "Cliente registrado con éxito");
                mostrarTablaClientes(); 
            } else {
                JOptionPane.showMessageDialog(this, "Error al guardar");
            }
        });

        JButton botonVolver = new JButton("Volver"); 
        botonVolver.setBounds(230, 160, 100, 30); 
        add(botonVolver);
        botonVolver.addActionListener(e -> mostrarTablaClientes());
    }
}