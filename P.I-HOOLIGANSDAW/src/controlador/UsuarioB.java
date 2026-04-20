package controlador;

import modelo.ConexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioB {

    public Usuario login(String apodo, String pass) {
        Usuario u = null;
        // Cambiado: nom_ape, apodo y password para coincidir con tu SQL
        String sql = "SELECT * FROM EMPLEADO WHERE apodo = ? AND password = ?";
        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, apodo);
            ps.setString(2, pass);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    u = new Usuario();
                    u.setId(rs.getInt("ID_empleado"));
                    u.setNombre(rs.getString("nom_ape")); // Usamos nom_ape
                    u.setCategoria(rs.getString("categoria"));
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        return u;
    }

    public List<Usuario> listarUsuarios() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM EMPLEADO";
        try (Connection con = ConexionBD.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("ID_empleado"));
                u.setNombre(rs.getString("nom_ape")); // Usamos nom_ape
                u.setCategoria(rs.getString("categoria"));
                lista.add(u);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return lista;
    }

    public List<Usuario> listarPorCategoria(String cat) {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM EMPLEADO WHERE categoria = ?";
        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cat);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Usuario u = new Usuario();
                    u.setId(rs.getInt("ID_empleado"));
                    u.setNombre(rs.getString("nom_ape")); // Usamos nom_ape
                    u.setCategoria(rs.getString("categoria"));
                    lista.add(u);
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        return lista;
    }

    public boolean insertar(String nom_ape, String apodo, String pass, String categoria) {
        // Ajustado para incluir nom_ape, apodo y password
        String sql = "INSERT INTO EMPLEADO (nom_ape, apodo, password, categoria) VALUES (?, ?, ?, ?)";
        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nom_ape);
            ps.setString(2, apodo);
            ps.setString(3, pass);
            ps.setString(4, categoria);
            return ps.executeUpdate() > 0;
        } catch (Exception e) { 
            e.printStackTrace(); 
            return false; 
        }
    }
}