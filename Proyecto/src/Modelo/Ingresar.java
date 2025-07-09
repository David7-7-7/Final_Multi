/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author jefer
 */

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.util.Date;
import java.sql.ResultSet;

public class Ingresar {

    public Ingresar() {
    }

    public boolean validarCliente(String correo, String contrasena) {
        try {
            ConectarBD conexion = new ConectarBD();
            String sql = "SELECT * FROM Cliente WHERE correo = ? AND contrasena = ?";
            PreparedStatement stmt = conexion.getConexion().prepareStatement(sql);
            stmt.setString(1, correo);
            stmt.setString(2, contrasena);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (Exception e) {
            System.out.println("Error al validar cliente: " + e.getMessage());
            return false;
        }
    }

    public boolean validarEmpleado(String correo, String contrasena) {
        try {
            ConectarBD conexion = new ConectarBD();
            String sql = "SELECT * FROM Empleado WHERE correo = ? AND contrasena = ?";
            PreparedStatement stmt = conexion.getConexion().prepareStatement(sql);
            stmt.setString(1, correo);
            stmt.setString(2, contrasena);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (Exception e) {
            System.out.println("Error al validar empleado: " + e.getMessage());
            return false;
        }
    }
    
}

