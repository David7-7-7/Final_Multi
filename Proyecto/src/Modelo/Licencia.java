package Modelo;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.util.Date;
import java.sql.ResultSet;


public class Licencia {
    private int puebra2;
    private int idLicencia;
    private String tipo;
    private String fechaExpedicion;
    private String fechaVencimiento;
    private String estado;

    public int getIdLicencia() {
        return idLicencia;
    }

    public void setIdLicencia(int idLicencia) {
        this.idLicencia = idLicencia;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFechaExpedicion() {
        return fechaExpedicion;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaExpedicion(String fechaExpedicion) {
        this.fechaExpedicion = fechaExpedicion;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    // Getters y Setters aquí...

    public boolean insertarLicencia() {
        try {
            ConectarBD conexion = new ConectarBD();
            String sql = "INSERT INTO licencia_conduccion (tipo, fecha_expedicion, fecha_vencimiento, estado) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conexion.getConexion().prepareStatement(sql);
            ps.setString(1, tipo);
            ps.setString(2, fechaExpedicion);
            ps.setString(3, fechaVencimiento);
            ps.setString(4, estado);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Licencia registrada correctamente.");
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al insertar licencia: " + e.getMessage());
            return false;
        }
    }
    public boolean existeLicencia(int idLicencia) {
    boolean existe = false;
    try {
        ConectarBD conexion = new ConectarBD();
        String sql = "SELECT * FROM licencia_conduccion WHERE id_licencia = ?";
        PreparedStatement ps = conexion.getConexion().prepareStatement(sql);
        ps.setInt(1, idLicencia);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            existe = true;
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al verificar licencia: " + e.getMessage());
    }
    return existe;
}
    
    
}