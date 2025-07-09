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
import javax.swing.table.DefaultTableModel;

public class Empleado {
    private String idEmpleado;
    private String documento;
    private String nombre;
    private String salario;
    private String cargo;
    private String telefono;
    private String direccion;
    private String correo;
    private String tipoDocumento;
    private String contrasena;

    // Getters y Setters
    public String getIdEmpleado() { return idEmpleado; }
    public void setIdEmpleado(String idEmpleado) { this.idEmpleado = idEmpleado; }

    public String getDocumento() { return documento; }
    public void setDocumento(String documento) { this.documento = documento; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getSalario() { return salario; }
    public void setSalario(String salario) { this.salario = salario; }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getTipoDocumento() { return tipoDocumento; }
    public void setTipoDocumento(String tipoDocumento) { this.tipoDocumento = tipoDocumento; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    // INSERTAR
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

    // CONSULTAR
    public DefaultTableModel consultar() {
        ConectarBD conexion = new ConectarBD();
        String[] titulos = {"ID", "Documento", "Nombre", "Salario", "Cargo", "Teléfono", "Dirección", "Correo", "Tipo Documento", "Contrasena"};
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);
        int columnas = titulos.length;

        try {
            Statement sentencia = conexion.getConexion().createStatement();
            ResultSet resultado = sentencia.executeQuery("SELECT * FROM Empleado");

            while (resultado.next()) {
                Object[] fila = new Object[columnas];
                fila[0] = resultado.getString("id_empleado");
                fila[1] = resultado.getString("documento");
                fila[2] = resultado.getString("nombre");
                fila[3] = resultado.getString("salario");
                fila[4] = resultado.getString("cargo");
                fila[5] = resultado.getString("telefono");
                fila[6] = resultado.getString("direccion");
                fila[7] = resultado.getString("correo");
                fila[8] = resultado.getString("tipo_documento");
                fila[9] = resultado.getString("contrasena");
                modelo.addRow(fila);
            }

            resultado.close();
            conexion.getConexion().close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al consultar empleados: " + e.getMessage());
        }

        return modelo;
    }

    // BUSCAR
    public String[] buscar(String ID_Empleado, String[] fila) {
        try {
            ConectarBD conexion = new ConectarBD();
            Statement sentencia = conexion.getConexion().createStatement();
            ResultSet resultado = sentencia.executeQuery("SELECT * FROM Empleado WHERE id_empleado='" + ID_Empleado + "'");

            while (resultado.next()) {
                fila[0] = resultado.getString("documento");
                fila[1] = resultado.getString("nombre");
                fila[2] = resultado.getString("salario");
                fila[3] = resultado.getString("cargo");
                fila[4] = resultado.getString("telefono");
                fila[5] = resultado.getString("direccion");
                fila[6] = resultado.getString("correo");
                fila[7] = resultado.getString("tipo_documento");
                fila[8] = resultado.getString("contrasena");
            }

            resultado.close();
            conexion.getConexion().close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al buscar empleado: " + e.getMessage());
        }

        return fila;
    }

    // MODIFICAR
    public void modificar(String ID_Empleado) {
        try {
            ConectarBD conexion = new ConectarBD();
            String sql = "UPDATE Empleado SET documento=?, nombre=?, salario=?, cargo=?, telefono=?, direccion=?, correo=?, tipo_documento=?, contrasena=? WHERE id_empleado=?";
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
            stmt.setString(10, ID_Empleado);
            stmt.execute();

            JOptionPane.showMessageDialog(null, "Empleado modificado correctamente.");
            conexion.getConexion().close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al modificar empleado: " + e.getMessage());
        }
    }

    // ELIMINAR
    public void eliminar(String ID_Empleado) {
        int confirmacion = JOptionPane.showConfirmDialog(null, "¿Eliminar el registro?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                ConectarBD conexion = new ConectarBD();
                String sql = "DELETE FROM Empleado WHERE id_empleado=?";
                PreparedStatement stmt = conexion.getConexion().prepareStatement(sql);
                stmt.setString(1, ID_Empleado);
                stmt.execute();

                JOptionPane.showMessageDialog(null, "Empleado eliminado correctamente.");
                conexion.getConexion().close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al eliminar empleado: " + e.getMessage());
            }
        }
    }

    // LOGIN: obtener ID por correo y contraseña
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
