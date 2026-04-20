/**
 * Ventana de inicio de sesión de la aplicación.
 * Desde aquí el usuario introduce su apodo y contraseña
 * para acceder al sistema.
 */

package vista;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JOptionPane;

import controlador.Usuario;
import controlador.UsuarioDAO;


public class VentanaLogin extends JFrame {
    
    // Etiquetas de texto de la ventana
    private JLabel lblTitulo = new JLabel("Pantalla Login");
    private JLabel lblUsuario = new JLabel("Usuario");
    private JLabel lblPassword = new JLabel("Password");

    // Campos de texto donde el usuario escribe sus datos
    private JTextField textUsuario = new JTextField();
    private JTextField textPassword = new JTextField();

    // Botones de la ventana
    private JButton btnLogin = new JButton("Login");
    private JButton btnBorrar = new JButton("Borrar");
    
    /**
     * Constructor de la ventana login.
     * Configura el tamaño, los componentes y la hace visible.
     */
    public VentanaLogin() {
        setTitle("Login Edna Moda");
        setBounds(500, 200, 250, 250);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        configLabels();
        configTextFields();
        configButtons();
        addPanelComponents();
        
        setVisible(true);
    }
    
    /**
     * Configura la posición y tamaño de los campos de texto.
     */
    private void configTextFields() {
        textUsuario.setBounds(110, 71, 86, 20);
        textUsuario.setColumns(10);
        
        textPassword.setBounds(110, 119, 86, 20);
        textPassword.setColumns(10);
    }
    
    /**
     * Configura la posición de los botones y sus acciones.
     */
    private void configButtons() {
        btnLogin.setBounds(24, 164, 89, 23);

        // Acción del botón login
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // Se recogen los datos escritos por el usuario
                String usuario = textUsuario.getText();
                String password = textPassword.getText();

                // Se crea el objeto que va a consultar la base de datos
                UsuarioDAO dao = new UsuarioDAO();

                // Se comprueba si el usuario existe en la BD
                Usuario u = dao.login(usuario, password);

                // Si existe, se abre el menú principal
                if (u != null) {
                    dispose();
                    new VentanaMenu(u);
                } else {
                    // Si no existe, se muestra un mensaje de error
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
                }
            }
        });

        btnBorrar.setBounds(123, 164, 89, 23);

        // Acción del botón borrar
        btnBorrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Vacía los dos campos de texto
                textPassword.setText("");
                textUsuario.setText("");
            }
        });
    }
     
    /**
     * Configura el estilo y posición de las etiquetas.
     */
    private void configLabels() {
        lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblUsuario.setBounds(24, 70, 64, 19);
        
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblPassword.setBounds(24, 120, 70, 19);

        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 25));
        lblTitulo.setBounds(10, 11, 209, 32);
    }
    
    /**
     * Añade todos los componentes al contenido de la ventana.
     */
    private void addPanelComponents() {
        getContentPane().setLayout(null);
        getContentPane().add(lblPassword);
        getContentPane().add(lblUsuario);
        getContentPane().add(textUsuario);
        getContentPane().add(textPassword);
        getContentPane().add(btnLogin);
        getContentPane().add(btnBorrar);
        getContentPane().add(lblTitulo);
    }
}