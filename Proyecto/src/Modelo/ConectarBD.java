package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConectarBD {

    public Connection conexion;
    public PreparedStatement sentencia;

    public ConectarBD() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("No se pudo cargar el driver JDBC.");
        }

        // ← Aquí se añadió ?useSSL=false
        String ruta = "jdbc:mysql://localhost:3306/alquiler_vehiculo?useSSL=false";

        try {
            conexion = DriverManager.getConnection(ruta, "root", "1234");
        } catch (SQLException e) {
            System.out.println("Error al conectar: " + e.getMessage());
        }
    }

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    public PreparedStatement getSentencia() {
        return sentencia;
    }

    public void setSentencia(PreparedStatement sentencia) {
        this.sentencia = sentencia;
    }
}
