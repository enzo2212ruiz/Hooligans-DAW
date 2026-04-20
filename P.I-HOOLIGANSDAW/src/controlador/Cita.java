package controlador;

public class Cita {
    private int id;
    private String fecha; 
    private String hora;
    private String taller;
    private String responsable;
    private String traje;

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }

    public String getHora() { return hora; }
    public void setHora(String hora) { this.hora = hora; }

    public String getTaller() { return taller; }
    public void setTaller(String taller) { this.taller = taller; }

    public String getResponsable() { return responsable; }
    public void setResponsable(String responsable) { this.responsable = responsable; }

    public String getTraje() { return traje; }
    public void setTraje(String traje) { this.traje = traje; }
}