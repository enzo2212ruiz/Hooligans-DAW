package controlador;

public class Cliente {
    private int id;
    private String nombre;
    private String superpoder;

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getSuperpoder() { return superpoder; }
    public void setSuperpoder(String superpoder)
    { this.superpoder = superpoder; }
    @Override
    public String toString() {
        return nombre;
    }
}