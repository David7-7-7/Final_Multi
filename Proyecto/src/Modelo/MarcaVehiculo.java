package Modelo;

import java.util.HashMap;
import java.util.Map;
import Modelo.ConectarBD;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

public class MarcaVehiculo {
    private int id;
    private String nombre;

    public MarcaVehiculo(String nombre) {
        this.nombre = nombre;
        this.id = obtenerIdPorNombre(nombre);
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public static int obtenerIdPorNombre(String nombre) {
        try {
            ConectarBD conectar = new ConectarBD();
            String sql = "SELECT id_marca FROM Marca_vehiculo WHERE nombre_marca = ?";
            PreparedStatement ps = conectar.getConexion().prepareStatement(sql);
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("id_marca");
            } else {
                return -1; // Marca no encontrada
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener ID de la marca: " + e.getMessage());
            return -1;
        }
    }
    public boolean insertar() {
    try {
        ConectarBD conectar = new ConectarBD();
        String sql = "INSERT INTO Marca_vehiculo (nombre_marca) VALUES (?)";
        PreparedStatement ps = conectar.getConexion().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, this.nombre);

        int filasAfectadas = ps.executeUpdate();

        if (filasAfectadas > 0) {
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                this.id = rs.getInt(1); // Asignamos el ID generado automáticamente
            }
            return true;
        } else {
            return false;
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al insertar marca: " + e.getMessage());
        return false;
    }
}
public static void cargarMarcasEnCombo(javax.swing.JComboBox<String> combo) {
    try {
        combo.removeAllItems(); // Limpiar el combo antes de llenarlo
        ConectarBD conectar = new ConectarBD();
        String sql = "SELECT nombre_marca FROM Marca_vehiculo ORDER BY nombre_marca";
        PreparedStatement ps = conectar.getConexion().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            combo.addItem(rs.getString("nombre_marca"));
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al cargar marcas: " + e.getMessage());
    }
}
   
}