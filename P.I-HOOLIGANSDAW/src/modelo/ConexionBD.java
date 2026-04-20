package modelo;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Clase encargada de realizar la conexión con la base de datos MySQL.
 * Centraliza los datos de acceso para que el resto del programa
 * pueda conectarse llamando al método conectar().
 */
public class ConexionBD {

	private static final String URL = "jdbc:mysql://localhost/ednamoda";
    private static final String USER = "root";
    private static final String PASS = "12345678";
    private static final String driver = "com.mysql.cj.jdbc.Driver";

    /**
     * Método que abre una conexión con la base de datos.
     * @return objeto Connection si la conexión es correcta, o null si falla
     */
    public static Connection conectar() {

        Connection con = null;

        try {
            // Intenta abrir la conexión con la BD
            con = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Conexión correcta a la BD");
        } catch (Exception e) {
            // Si falla, muestra mensaje de error
            System.out.println("Error en la conexión");
            e.printStackTrace();
        }

        return con;
    }
}