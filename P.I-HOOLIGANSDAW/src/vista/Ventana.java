package vista;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Ventana extends JFrame {

    public Ventana() {
        setTitle("Ventana Principal");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel lbl = new JLabel("Bienvenido", JLabel.CENTER);
        add(lbl);

        setVisible(true);
    }
}