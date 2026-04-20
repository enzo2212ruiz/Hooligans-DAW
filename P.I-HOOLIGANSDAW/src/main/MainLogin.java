package main;

import javax.swing.UIManager;
import vista.VentanaLogin;

/**
 * Clase principal del programa.
 * Desde aquí arranca la aplicación con un estilo visual moderno.
 */
public class MainLogin {
    
    public static void main(String[] args) {
        try {
            // Intenta aplicar el diseño visual del sistema operativo (Windows, Mac o Linux)
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // Si hay un error, el programa seguirá con el estilo básico
            System.out.println("No se pudo cargar el estilo visual: " + e.getMessage());
        }
        
        // Arranca la ventana de login
        new VentanaLogin();
    }
}