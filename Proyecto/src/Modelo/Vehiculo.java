package Modelo;

import java.sql.*;
import javax.swing.*;

public class Vehiculo {
    private String placa;
    private String modelo;
    private int kilometraje;
    private int idMarca, idColor, idBlindaje, idCilindraje, idEstado;
    private int idProveedor, idSeguro, idSucursal, idTipoVehiculo, idTransmision;

    public Vehiculo(String placa, String modelo, int kilometraje, int idMarca, int idColor, int idBlindaje,
                    int idCilindraje, int idEstado, int idProveedor, int idSeguro,
                    int idSucursal, int idTipoVehiculo, int idTransmision) {
        this.placa = placa;
        this.modelo = modelo;
        this.kilometraje = kilometraje;
        this.idMarca = idMarca;
        this.idColor = idColor;
        this.idBlindaje = idBlindaje;
        this.idCilindraje = idCilindraje;
        this.idEstado = idEstado;
        this.idProveedor = idProveedor;
        this.idSeguro = idSeguro;
        this.idSucursal = idSucursal;
        this.idTipoVehiculo = idTipoVehiculo;
        this.idTransmision = idTransmision;
    }

    public boolean insertar() {
        try {
            ConectarBD conectar = new ConectarBD();
            String sql = "INSERT INTO Vehiculo (placa, modelo, kilometraje, id_marca, id_color, id_blindaje, id_cilindraje, id_estado, id_proveedor, id_seguro, id_sucursal, id_tipo_vehiculo, id_transmision) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conectar.getConexion().prepareStatement(sql);
            ps.setString(1, placa);
            ps.setString(2, modelo);
            ps.setInt(3, kilometraje);
            ps.setInt(4, idMarca);
            ps.setInt(5, idColor);
            ps.setInt(6, idBlindaje);
            ps.setInt(7, idCilindraje);
            ps.setInt(8, idEstado);
            ps.setInt(9, idProveedor);
            ps.setInt(10, idSeguro);
            ps.setInt(11, idSucursal);
            ps.setInt(12, idTipoVehiculo);
            ps.setInt(13, idTransmision);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al insertar vehículo: " + e.getMessage());
            return false;
        }
    }
}