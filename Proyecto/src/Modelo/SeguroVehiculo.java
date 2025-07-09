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

public class SeguroVehiculo {

    private int id;
    private String estado;
    private String descripcion;
    private Date vencimiento;
    private double costo;

    // Constructor completo para inserción
    public SeguroVehiculo(String estado, String descripcion, Date vencimiento, double costo) {
        this.estado = estado;
        this.descripcion = descripcion;
        this.vencimiento = vencimiento;
        this.costo = costo;
        this.id = -1; // Se asigna tras insertar
    }

    // Constructor simplificado para obtener ID por estado
    public SeguroVehiculo(String estado) {
        this.estado = estado;
        this.id = obtenerIdPorEstado(estado);
    }

    public int getId() {
        return id;
    }

    public String getEstado() {
        return estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Date getVencimiento() {
        return vencimiento;
    }

    public double getCosto() {
        return costo;
    }

    public static int obtenerIdPorEstado(String estado) {
        try {
            ConectarBD conectar = new ConectarBD();
            String sql = "SELECT id_seguro FROM Seguro_vehiculo WHERE estado = ?";
            PreparedStatement ps = conectar.getConexion().prepareStatement(sql);
            ps.setString(1, estado);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("id_seguro");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener ID del seguro: " + e.getMessage());
        }
        return -1;
    }

    public boolean insertar() {
        try {
            ConectarBD conectar = new ConectarBD();
            String sql = "INSERT INTO Seguro_vehiculo (estado, descripcion, vencimiento, costo) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conectar.getConexion().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, estado);
            ps.setString(2, descripcion);
            ps.setDate(3, new java.sql.Date(vencimiento.getTime()));
            ps.setDouble(4, costo);

            int filas = ps.executeUpdate();
            if (filas > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    this.id = rs.getInt(1);
                }
                return true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al insertar seguro: " + e.getMessage());
        }
        return false;
    }

    public static void cargarEnCombo(JComboBox<String> combo) {
        try {
            combo.removeAllItems();
            ConectarBD conectar = new ConectarBD();
            String sql = "SELECT estado FROM Seguro_vehiculo ORDER BY estado";
            PreparedStatement ps = conectar.getConexion().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                combo.addItem(rs.getString("estado"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar seguros: " + e.getMessage());
        }
    }
}
