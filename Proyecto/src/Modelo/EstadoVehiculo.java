/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author jefer
 */
import java.sql.*;
import javax.swing.*;
import java.util.Date;
public class EstadoVehiculo {
    private int id;
    private String descripcion;

    public EstadoVehiculo(String descripcion) {
        this.descripcion = descripcion;
        this.id = obtenerIdPorDescripcion(descripcion);
    }

    public int getId() { return id; }
    public String getDescripcion() { return descripcion; }

    public static int obtenerIdPorDescripcion(String descripcion) {
        try {
            ConectarBD conectar = new ConectarBD();
            String sql = "SELECT id_estado FROM Estado_vehiculo WHERE descripcion = ?";
            PreparedStatement ps = conectar.getConexion().prepareStatement(sql);
            ps.setString(1, descripcion);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt("id_estado");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener ID del estado: " + e.getMessage());
        }
        return -1;
    }

    public boolean insertar() {
        try {
            ConectarBD conectar = new ConectarBD();
            String sql = "INSERT INTO Estado_vehiculo (descripcion) VALUES (?)";
            PreparedStatement ps = conectar.getConexion().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, this.descripcion);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) this.id = rs.getInt(1);
                return true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al insertar estado: " + e.getMessage());
        }
        return false;
    }

    public static void cargarEnCombo(JComboBox<String> combo) {
        try {
            combo.removeAllItems();
            ConectarBD conectar = new ConectarBD();
            String sql = "SELECT descripcion FROM Estado_vehiculo ORDER BY descripcion";
            PreparedStatement ps = conectar.getConexion().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) combo.addItem(rs.getString("descripcion"));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar estados: " + e.getMessage());
        }
    }
}