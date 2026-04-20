package modelo;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Clase encargada de realizar la conexión con la base de datos MySQL.
 * Centraliza los datos de acceso para que el resto del programa
 * pueda conectarse si llamamos al metodo conectar
 */
public class ConexionBD {

	private static final String URL = "jdbc:mysql://localhost/ednamoda";
    private static final String USER = "root";
    private static final String PASS = "12345678";
    private static final String driver = "com.mysql.cj.jdbc.Driver";

    /**
     * Con este metodo lo que conseguimos es conectarnos a la base de 
     * datos y nos devuelva esa conexion o nos de un null si falla
     */
    public static Connection conectar() {

        Connection con = null;

        try {
           
            con = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Conexión correcta a la BD");
        } catch (Exception e) {
            System.out.println("Error en la conexión");
            e.printStackTrace();
        }

        return con;
    }
}