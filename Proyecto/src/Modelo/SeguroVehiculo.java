package Modelo;

import java.sql.*;
import javax.swing.*;

public class SeguroVehiculo {
    private int id;
    private String estado;
    private String descripcion;
    private Date vencimiento;
    private double costo;

    public SeguroVehiculo(String estado, String descripcion, Date vencimiento, double costo) {
        this.estado = estado;
        this.descripcion = descripcion;
        this.vencimiento = vencimiento;
        this.costo = costo;
        this.id = -1; // solo se asigna tras insertar
    }

    public int getId() { return id; }
    public String getEstado() { return estado; }
    public String getDescripcion() { return descripcion; }
    public Date getVencimiento() { return vencimiento; }
    public double getCosto() { return costo; }

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
                if (rs.next()) this.id = rs.getInt(1);
                return true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al insertar seguro: " + e.getMessage());
        }
        return false;
    }
}
