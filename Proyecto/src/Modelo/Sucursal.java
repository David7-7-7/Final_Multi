package Modelo;

import java.sql.*;
import javax.swing.*;

public class Sucursal {
    private int id;
    private String nombre;
    private String direccion;
    private String telefono;
    private String gerente;
    private String idCodigoPostal;

    public Sucursal(String nombre, String direccion, String telefono, String gerente, String idCodigoPostal) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.gerente = gerente;
        this.idCodigoPostal = idCodigoPostal;
        this.id = obtenerIdPorNombre(nombre);
    }
    
    public Sucursal(String nombre) {
    this.nombre = nombre;
    this.id = obtenerIdPorNombre(nombre);
    }
        
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDireccion() { return direccion; }
    public String getTelefono() { return telefono; }
    public String getGerente() { return gerente; }
    public String getIdCodigoPostal() { return idCodigoPostal; }

    public static int obtenerIdPorNombre(String nombre) {
        try {
            ConectarBD conectar = new ConectarBD();
            String sql = "SELECT id_sucursal FROM Sucursal WHERE nombre = ?";
            PreparedStatement ps = conectar.getConexion().prepareStatement(sql);
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt("id_sucursal");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener ID de la sucursal: " + e.getMessage());
        }
        return -1;
    }

    public boolean insertar() {
        try {
            ConectarBD conectar = new ConectarBD();
            String sql = "INSERT INTO Sucursal (nombre, direccion, telefono, gerente, id_codigo_postal) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conectar.getConexion().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, nombre);
            ps.setString(2, direccion);
            ps.setString(3, telefono);
            ps.setString(4, gerente);
            ps.setString(5, idCodigoPostal);

            int filas = ps.executeUpdate();
            if (filas > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) this.id = rs.getInt(1);
                return true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al insertar sucursal: " + e.getMessage());
        }
        return false;
    }

    public static void cargarEnCombo(JComboBox<String> combo) {
        try {
            combo.removeAllItems();
            ConectarBD conectar = new ConectarBD();
            String sql = "SELECT nombre FROM Sucursal ORDER BY nombre";
            PreparedStatement ps = conectar.getConexion().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) combo.addItem(rs.getString("nombre"));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar sucursales: " + e.getMessage());
        }
    }
}
