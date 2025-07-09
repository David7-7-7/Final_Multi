package Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Empleado extends Persona {

    private String idEmpleado;
    private String salario;
    private String cargo;
    private String tipoDocumento;
    private String contrasena;

    public Empleado(String idEmpleado, String documento, String nombre, String telefono, String direccion, String correo, String salario, String cargo, String tipoDocumento, String contrasena) {
        super(documento, nombre, telefono, direccion, correo);
        this.idEmpleado = idEmpleado;
        this.salario = salario;
        this.cargo = cargo;
        this.tipoDocumento = tipoDocumento;
        this.contrasena = contrasena;
    }

    public Empleado() {
        super();
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getSalario() {
        return salario;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void insertar() {
        try {
            ConectarBD conexion = new ConectarBD();
            String sql = "INSERT INTO Empleado (documento, nombre, salario, cargo, telefono, direccion, correo, tipo_documento, contrasena) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conexion.getConexion().prepareStatement(sql);
            stmt.setString(1, documento);
            stmt.setString(2, nombre);
            stmt.setString(3, salario);
            stmt.setString(4, cargo);
            stmt.setString(5, telefono);
            stmt.setString(6, direccion);
            stmt.setString(7, correo);
            stmt.setString(8, tipoDocumento);
            stmt.setString(9, contrasena);
            stmt.execute();

            JOptionPane.showMessageDialog(null, "Empleado registrado correctamente.");
            conexion.getConexion().close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al registrar empleado: " + e.getMessage());
        }
    }

    public String obtenerIDporCorreoYContraseña(String correo, String contraseña) {
        String idEmpleado = null;
        try {
            ConectarBD conexion = new ConectarBD();
            String sql = "SELECT id_empleado FROM Empleado WHERE correo = ? AND contrasena = ?";
            PreparedStatement stmt = conexion.getConexion().prepareStatement(sql);
            stmt.setString(1, correo);
            stmt.setString(2, contrasena);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                idEmpleado = rs.getString("id_empleado");
            }

            rs.close();
            conexion.getConexion().close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtener ID del empleado: " + e.getMessage());
        }

        return idEmpleado;
    }
}