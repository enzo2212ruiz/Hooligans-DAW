package controlador;

import modelo.ConexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CitaB {

    // Lista todas las citas uniendo tablas para ver nombres en lugar de IDs
    public List<Cita> listarCitas() {
        List<Cita> lista = new ArrayList<>();
        String sql = "SELECT c.ID_cita, c.dia, c.hora, t.nombre as taller, e.nom_ape as responsable, tr.nombre as traje " +
                     "FROM CITA c " +
                     "JOIN TALLER t ON c.ID_taller = t.ID_taller " +
                     "JOIN EMPLEADO e ON c.ID_responsable = e.ID_empleado " +
                     "JOIN TRAJE tr ON c.ID_traje = tr.ID_traje";
        try (Connection con = ConexionBD.conectar(); 
             Statement st = con.createStatement(); 
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Cita c = new Cita();
                c.setId(rs.getInt("ID_cita"));
                c.setFecha(rs.getString("dia"));
                c.setHora(rs.getString("hora"));
                c.setTaller(rs.getString("taller"));
                c.setResponsable(rs.getString("responsable"));
                c.setTraje(rs.getString("traje"));
                lista.add(c);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return lista;
    }

    // Obtiene nombres de empleados para el ComboBox
    public List<String> obtenerNombresEmpleados() {
        List<String> nombres = new ArrayList<>();
        String sql = "SELECT nom_ape FROM EMPLEADO";
        try (Connection con = ConexionBD.conectar(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) { nombres.add(rs.getString("nom_ape")); }
        } catch (Exception e) { e.printStackTrace(); }
        return nombres;
    }

    // Busca el ID de un empleado a partir de su nombre
    public int buscarIdEmpleado(String nombre) {
        int id = 0;
        String sql = "SELECT ID_empleado FROM EMPLEADO WHERE nom_ape = ?";
        try (Connection con = ConexionBD.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) { id = rs.getInt("ID_empleado"); }
        } catch (Exception e) { e.printStackTrace(); }
        return id;
    }

    // Inserta una cita nueva
    public boolean insertarCita(String fecha, String hora, int duracion, int idTaller, int idRespon, int idTraje) {
        String sql = "INSERT INTO CITA (dia, hora, duracion, ID_taller, ID_responsable, ID_traje) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = ConexionBD.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, fecha);
            ps.setString(2, hora);
            ps.setInt(3, duracion);
            ps.setInt(4, idTaller);
            ps.setInt(5, idRespon);
            ps.setInt(6, idTraje);
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); return false; }
    }

    // Modifica una cita existente
    public boolean modificarCita(int id, String fecha, String hora, int idRespon) {
        String sql = "UPDATE CITA SET dia = ?, hora = ?, ID_responsable = ? WHERE ID_cita = ?";
        try (Connection con = ConexionBD.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, fecha);
            ps.setString(2, hora);
            ps.setInt(3, idRespon);
            ps.setInt(4, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); return false; }
    }

    public boolean eliminarCita(int idCita) {
        try (Connection con = ConexionBD.conectar()) {
            // Borrar primero en ASISTE para evitar error de FK
            PreparedStatement ps1 = con.prepareStatement("DELETE FROM ASISTE WHERE ID_cita = ?");
            ps1.setInt(1, idCita);
            ps1.executeUpdate();
            
            PreparedStatement ps2 = con.prepareStatement("DELETE FROM CITA WHERE ID_cita = ?");
            ps2.setInt(1, idCita);
            return ps2.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); return false; }
    }
}