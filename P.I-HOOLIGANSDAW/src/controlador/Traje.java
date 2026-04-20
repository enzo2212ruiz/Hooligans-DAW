package controlador;

/**
 * Clase que representa un Traje en el sistema.
 */
public class Traje {
    private int id;
    private String nombre;
    private String estado;

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    
    // Este método ayuda a que el ComboBox muestre el nombre
    @Override
    public String toString() {
        return nombre;
    }
}