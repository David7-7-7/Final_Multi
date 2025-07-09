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
