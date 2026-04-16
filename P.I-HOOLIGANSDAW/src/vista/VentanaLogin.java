package vista;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import vista.VentanaMenu;

import controlador.Usuario;

public class VentanaLogin extends JFrame {
    
    private JLabel lblTitulo = new JLabel("Pantalla Login");
    private JLabel lblUsuario = new JLabel("Usuario");
    private JLabel lblPassword = new JLabel("Password");
    private JTextField textUsuario = new JTextField();
    private JTextField textPassword = new JTextField();
    private JButton btnLogin = new JButton("Login");
    private JButton btnBorrar = new JButton("Borrar");
    
    public VentanaLogin() {
        setBounds(500, 200, 250, 250);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        configLabels();
        configTextFields();
        configButtons();
        addPanelComponents();
        
        setVisible(true);
    }
    
    private void configTextFields() {
        textUsuario.setBounds(110, 71, 86, 20);
        textUsuario.setColumns(10);
        
        textPassword.setBounds(110, 119, 86, 20);
        textPassword.setColumns(10);
    }
    
    private void configButtons() {
        btnLogin.setBounds(24, 164, 89, 23);

        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String usuario = textUsuario.getText();
                String password = textPassword.getText();

                // SIMULAMOS EL ROL
                String rol = "";

                if (usuario.equals("maestro")) {
                    rol = "MAESTRO";
                } else if (usuario.equals("oficial")) {
                    rol = "OFICIAL";
                } else {
                    rol = "APRENDIZ";
                }

                // Cerrar login
                setVisible(false);

                // Abrir menú pasando el rol
                new VentanaMenu(rol);
            }
        });

        btnBorrar.setBounds(123, 164, 89, 23);

        btnBorrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textPassword.setText("");
                textUsuario.setText("");
            }
        });
    }
     
    
    
    private void configLabels() {
        lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblUsuario.setBounds(24, 70, 64, 19);
        
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblPassword.setBounds(24, 120, 64, 19);

        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 25));
        lblTitulo.setBounds(10, 11, 209, 32);
    }
    
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
    
//    private Usuario comprobarLogin(String usuario, String password) {
//    	Usuario u = new Usuario();
//   	
//    	if (usuario.equals("Lucas")&& password.equals("Lucas")) {
//    		u.setNombre(usuario);
//    		u.setApellido1("apellido1");
//    		u.setApellido2("apellido2");
//    		u.setPassword(password);
//    		u.setIdCategoria(3);
//    		u.setCategoria("Aprendiz");
//    		
//    		
//    		
//    	}
//    	return  u;
//    }
}