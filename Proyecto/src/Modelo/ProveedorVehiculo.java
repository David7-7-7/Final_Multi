package Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class ProveedorVehiculo {

    private int id;
    private String nombre;
    private String direccion;
    private String telefono;
    private String correo;
    private int idCuenta;

    public ProveedorVehiculo(String nombre, String direccion, String telefono, String correo, int idCuenta) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.idCuenta = idCuenta;
        this.id = obtenerIdPorNombre(nombre);
    }

    public ProveedorVehiculo(String nombre) {
        this.nombre = nombre;
        this.id = obtenerIdPorNombre(nombre);
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public int getIdCuenta() {
        return idCuenta;
    }

    public static int obtenerIdPorNombre(String nombre) {
        try {
            ConectarBD conectar = new ConectarBD();
            String sql = "SELECT id_proveedor FROM Proveedor_vehiculo WHERE nombre = ?";
            PreparedStatement ps = conectar.getConexion().prepareStatement(sql);
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("id_proveedor");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener ID del proveedor: " + e.getMessage());
        }
        return -1;
    }

    public boolean insertar() {
        try {
            ConectarBD conectar = new ConectarBD();
            String sql = "INSERT INTO Proveedor_vehiculo (nombre, direccion, telefono, correo, id_cuenta) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conectar.getConexion().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, nombre);
            ps.setString(2, direccion);
            ps.setString(3, telefono);
            ps.setString(4, correo);
            ps.setInt(5, idCuenta);

            int filas = ps.executeUpdate();
            if (filas > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    this.id = rs.getInt(1);
                }
                return true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al insertar proveedor: " + e.getMessage());
        }
        return false;
    }

    public static void cargarEnCombo(JComboBox<String> combo) {
        VehiculoAtributo.cargarEnCombo(combo, "Proveedor_vehiculo", "nombre");
    }
}