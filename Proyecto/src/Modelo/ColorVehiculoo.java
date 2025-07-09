/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author jefer
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jefer
 */
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JComboBox;
// ------------------------ COLOR VEHÍCULO ------------------------
public class ColorVehiculoo {
    private int id;
    private String nombre;
    public ColorVehiculoo(String nombre) {
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