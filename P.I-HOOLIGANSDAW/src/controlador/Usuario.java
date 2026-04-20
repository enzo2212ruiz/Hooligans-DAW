package controlador;

public class Usuario {
    private int id; // 
    private String nombre;
    private String categoria;

   
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria)
    { this.categoria = categoria; }
    @Override
    public String toString() {
       
        if (categoria == null || categoria.trim().isEmpty()) {
            return nombre;
        }       
        return nombre + " (" + categoria + ")";
    }
}