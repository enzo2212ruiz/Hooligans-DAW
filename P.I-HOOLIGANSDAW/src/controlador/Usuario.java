//MODELO DE DATOS
package controlador;

/**
 * Clase que representa al usuario que inicia sesión en la aplicación.
 * En este caso, el usuario será un empleado de la base de datos.
 */
public class Usuario {

    // Nombre completo del empleado
    private String nombre;

    // Categoría del empleado: APRENDIZ, OFICIAL o MAESTRO
    private String categoria;

    
    public String getNombre() {
        return nombre;
    }

   
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    public String getCategoria() {
        return categoria;
    }
    void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
