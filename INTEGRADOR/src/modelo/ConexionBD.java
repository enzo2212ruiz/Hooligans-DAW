package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String URL_BD = "jdbc:mysql://localhost:3306/EdnaModa";
    private static final String USUARIO_BD = "root";
    private static final String PASSWORD_BD = "root";

    public static Connection conectar() {
        Connection conexion = null;

        try {
            conexion = DriverManager.getConnection(URL_BD, USUARIO_BD, PASSWORD_BD);
            System.out.println("Conexión realizada correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos.");
            System.out.println(e.getMessage());
        }

        return conexion;
    }
}