package Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Cliente extends Persona {

    private String idCliente;
    private String infracciones;
    private String idLicencia;
    private String idTipoDocumento;
    private String idTipoCliente;
    private String idCodigoPostal;
    private String contraseña;

    public Cliente(String idCliente, String documento, String nombre, String telefono, String direccion, String correo, String infracciones, String idLicencia, String idTipoDocumento, String idTipoCliente, String idCodigoPostal, String contraseña) {
        super(documento, nombre, telefono, direccion, correo);
        this.idCliente = idCliente;
        this.infracciones = infracciones;
        this.idLicencia = idLicencia;
        this.idTipoDocumento = idTipoDocumento;
        this.idTipoCliente = idTipoCliente;
        this.idCodigoPostal = idCodigoPostal;
        this.contraseña = contraseña;
    }

    public Cliente() {
        super();
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getInfracciones() {
        return infracciones;
    }

    public void setInfracciones(String infracciones) {
        this.infracciones = infracciones;
    }

    public String getIdLicencia() {
        return idLicencia;
    }

    public void setIdLicencia(String idLicencia) {
        this.idLicencia = idLicencia;
    }

    public String getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(String idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public String getIdTipoCliente() {
        return idTipoCliente;
    }

    public void setIdTipoCliente(String idTipoCliente) {
        this.idTipoCliente = idTipoCliente;
    }

    public String getIdCodigoPostal() {
        return idCodigoPostal;
    }

    public void setIdCodigoPostal(String idCodigoPostal) {
        this.idCodigoPostal = idCodigoPostal;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public void insertar() {
        try {
            ConectarBD conexion = new ConectarBD();
            String sql = "INSERT INTO Cliente (documento, nombre, telefono, direccion, correo, infracciones, id_licencia, tipo_documento, tipo_cliente, id_codigo_postal, contrasena) VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conexion.getConexion().prepareStatement(sql);
            stmt.setString(1, documento);
            stmt.setString(2, nombre);
            stmt.setString(3, telefono);
            stmt.setString(4, direccion);
            stmt.setString(5, correo);
            stmt.setString(6, infracciones);
            stmt.setString(7, idLicencia);
            stmt.setString(8, idTipoDocumento);
            stmt.setString(9, idTipoCliente);
            stmt.setString(10, idCodigoPostal);
            stmt.setString(11, contraseña);
            stmt.execute();

            JOptionPane.showMessageDialog(null, "Cliente registrado correctamente.");
            conexion.getConexion().close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error del sistema: " + e.getMessage());
        }
    }

    public String obtenerIDporCorreoYContraseña(String correo, String contraseña) {
        String idCliente = null;
        try {
            ConectarBD conexion = new ConectarBD();
            String sql = "SELECT id_cliente FROM Cliente WHERE correo = ? AND contrasena = ?";
            PreparedStatement stmt = conexion.getConexion().prepareStatement(sql);
            stmt.setString(1, correo);
            stmt.setString(2, contraseña);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                idCliente = rs.getString("id_cliente");
            }

            rs.close();
            conexion.getConexion().close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtener ID del cliente: " + e.getMessage());
        }

        return idCliente;
    }
}