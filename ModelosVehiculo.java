
// Modelos de entidades relacionadas con Vehículos (completos)

package Modelo;

import java.sql.*;
import javax.swing.*;
import java.util.Date;

// ------------------------ COLOR VEHÍCULO ------------------------
public class ColorVehiculo {
    private int id;
    private String nombre;
    public ColorVehiculo(String nombre) {
        this.nombre = nombre;
        this.id = obtenerIdPorNombre(nombre);
    }
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public static int obtenerIdPorNombre(String nombre) {
        try {
            ConectarBD conectar = new ConectarBD();
            String sql = "SELECT id_color FROM Color_vehiculo WHERE nombre_color = ?";
            PreparedStatement ps = conectar.getConexion().prepareStatement(sql);
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt("id_color");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener ID del color: " + e.getMessage());
        }
        return -1;
    }
    public boolean insertar() {
        try {
            ConectarBD conectar = new ConectarBD();
            String sql = "INSERT INTO Color_vehiculo (nombre_color) VALUES (?)";
            PreparedStatement ps = conectar.getConexion().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, this.nombre);
            if (ps.executeUpdate() > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) this.id = rs.getInt(1);
                return true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al insertar color: " + e.getMessage());
        }
        return false;
    }
    public static void cargarEnCombo(JComboBox<String> combo) {
        try {
            combo.removeAllItems();
            ConectarBD conectar = new ConectarBD();
            String sql = "SELECT nombre_color FROM Color_vehiculo ORDER BY nombre_color";
            PreparedStatement ps = conectar.getConexion().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) combo.addItem(rs.getString("nombre_color"));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar colores: " + e.getMessage());
        }
    }
}

// ------------------------ BLINDAJE VEHÍCULO ------------------------
class BlindajeVehiculo {
    private int id;
    private String descripcion;
    public BlindajeVehiculo(String descripcion) {
        this.descripcion = descripcion;
        this.id = obtenerIdPorDescripcion(descripcion);
    }
    public int getId() { return id; }
    public String getDescripcion() { return descripcion; }
    public static int obtenerIdPorDescripcion(String desc) {
        try {
            ConectarBD conectar = new ConectarBD();
            String sql = "SELECT id_blindaje FROM Blindaje_vehiculo WHERE descripcion = ?";
            PreparedStatement ps = conectar.getConexion().prepareStatement(sql);
            ps.setString(1, desc);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt("id_blindaje");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener ID de blindaje: " + e.getMessage());
        }
        return -1;
    }
    public boolean insertar() {
        try {
            ConectarBD conectar = new ConectarBD();
            String sql = "INSERT INTO Blindaje_vehiculo (descripcion) VALUES (?)";
            PreparedStatement ps = conectar.getConexion().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, this.descripcion);
            if (ps.executeUpdate() > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) this.id = rs.getInt(1);
                return true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al insertar blindaje: " + e.getMessage());
        }
        return false;
    }
    public static void cargarEnCombo(JComboBox<String> combo) {
        try {
            combo.removeAllItems();
            ConectarBD conectar = new ConectarBD();
            String sql = "SELECT descripcion FROM Blindaje_vehiculo ORDER BY descripcion";
            PreparedStatement ps = conectar.getConexion().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) combo.addItem(rs.getString("descripcion"));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar blindajes: " + e.getMessage());
        }
    }
}

// ------------------------ CILINDRAJE VEHÍCULO ------------------------
class CilindrajeVehiculo {
    private int id;
    private String descripcion;
    public CilindrajeVehiculo(String descripcion) {
        this.descripcion = descripcion;
        this.id = obtenerIdPorDescripcion(descripcion);
    }
    public int getId() { return id; }
    public String getDescripcion() { return descripcion; }
    public static int obtenerIdPorDescripcion(String desc) {
        try {
            ConectarBD conectar = new ConectarBD();
            String sql = "SELECT id_cilindraje FROM Cilindraje_vehiculo WHERE descripcion = ?";
            PreparedStatement ps = conectar.getConexion().prepareStatement(sql);
            ps.setString(1, desc);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt("id_cilindraje");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener ID de cilindraje: " + e.getMessage());
        }
        return -1;
    }
    public boolean insertar() {
        try {
            ConectarBD conectar = new ConectarBD();
            String sql = "INSERT INTO Cilindraje_vehiculo (descripcion) VALUES (?)";
            PreparedStatement ps = conectar.getConexion().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, this.descripcion);
            if (ps.executeUpdate() > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) this.id = rs.getInt(1);
                return true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al insertar cilindraje: " + e.getMessage());
        }
        return false;
    }
    public static void cargarEnCombo(JComboBox<String> combo) {
        try {
            combo.removeAllItems();
            ConectarBD conectar = new ConectarBD();
            String sql = "SELECT descripcion FROM Cilindraje_vehiculo ORDER BY descripcion";
            PreparedStatement ps = conectar.getConexion().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) combo.addItem(rs.getString("descripcion"));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar cilindrajes: " + e.getMessage());
        }
    }
}
