package controlador;

public class Usuario {
    private int id; // <--- ESTO ES LO QUE FALTA
    private String nombre;
    private String categoria;

    // Getter y Setter para ID
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria)
    { this.categoria = categoria; }
    @Override
    public String toString() {
        // Si no hay categoría (como en el caso de "-- Ninguno --"), solo muestra el nombre
        if (categoria == null || categoria.trim().isEmpty()) {
            return nombre;
        }
        // Para el resto, muestra: Nombre (CATEGORÍA)
        return nombre + " (" + categoria + ")";
    }
}