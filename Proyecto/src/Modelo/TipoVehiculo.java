package Modelo;

import java.sql.*;
import javax.swing.*;

public class TipoVehiculo {
    private int id;
    private String descripcion;
    private int capacidad;
    private String combustible;
    private double tarifaDia;

    public TipoVehiculo(String descripcion, int capacidad, String combustible, double tarifaDia) {
        this.descripcion = descripcion;
        this.capacidad = capacidad;
        this.combustible = combustible;
        this.tarifaDia = tarifaDia;
        this.id = obtenerIdPorDescripcion(descripcion);
    }

    public int getId() { return id; }
    public String getDescripcion() { return descripcion; }
    public int getCapacidad() { return capacidad; }
    public String getCombustible() { return combustible; }
    public double getTarifaDia() { return tarifaDia; }

    public static int obtenerIdPorDescripcion(String descripcion) {
        try {
            ConectarBD conectar = new ConectarBD();
            String sql = "SELECT id_tipo FROM Tipo_vehiculo WHERE descripcion = ?";
            PreparedStatement ps = conectar.getConexion().prepareStatement(sql);
            ps.setString(1, descripcion);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt("id_tipo");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener ID del tipo: " + e.getMessage());
        }
        return -1;
    }

    public boolean insertar() {
        try {
            ConectarBD conectar = new ConectarBD();
            String sql = "INSERT INTO Tipo_vehiculo (descripcion, capacidad, combustible, tarifa_dia) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conectar.getConexion().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, descripcion);
            ps.setInt(2, capacidad);
            ps.setString(3, combustible);
            ps.setDouble(4, tarifaDia);

            int filas = ps.executeUpdate();
            if (filas > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) this.id = rs.getInt(1);
                return true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al insertar tipo vehículo: " + e.getMessage());
        }
        return false;
    }

    public static void cargarEnCombo(JComboBox<String> combo) {
        try {
            combo.removeAllItems();
            ConectarBD conectar = new ConectarBD();
            String sql = "SELECT descripcion FROM Tipo_vehiculo ORDER BY descripcion";
            PreparedStatement ps = conectar.getConexion().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) combo.addItem(rs.getString("descripcion"));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar tipos de vehículo: " + e.getMessage());
        }
    }
}

