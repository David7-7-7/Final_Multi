package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import javax.swing.JOptionPane;

public class Alquiler {
    private int idAlquiler;
    private Timestamp fechaSalida;
    private Timestamp fechaEntrada;
    private String placaVehiculo;
    private int idCliente;
    private int idSucursal;
    private int idMedioPago;
    private int idEstado;
    private int idDescuento;
    private double valor;

    public Alquiler() {}

    public int getIdAlquiler() {
        return idAlquiler;
    }

    public void setIdAlquiler(int idAlquiler) {
        this.idAlquiler = idAlquiler;
    }

    public Timestamp getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Timestamp fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Timestamp getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(Timestamp fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    public int getIdMedioPago() {
        return idMedioPago;
    }

    public void setIdMedioPago(int idMedioPago) {
        this.idMedioPago = idMedioPago;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public int getIdDescuento() {
        return idDescuento;
    }

    public void setIdDescuento(int idDescuento) {
        this.idDescuento = idDescuento;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Alquiler(Timestamp fechaSalida, Timestamp fechaEntrada, String placaVehiculo,
                    int idCliente, int idSucursal, int idMedioPago,
                    int idEstado, int idDescuento, double valor) {
        this.fechaSalida = fechaSalida;
        this.fechaEntrada = fechaEntrada;
        this.placaVehiculo = placaVehiculo;
        this.idCliente = idCliente;
        this.idSucursal = idSucursal;
        this.idMedioPago = idMedioPago;
        this.idEstado = idEstado;
        this.idDescuento = idDescuento;
        this.valor = valor;
    }

    public int registrarYObtenerID() {
        int idGenerado = -1;

        try {
            ConectarBD conexion = new ConectarBD();

            String sql = """
                INSERT INTO alquiler (
                    fecha_hora_salida, fecha_hora_entrada,
                    id_vehiculo, id_cliente, id_sucursal,
                    id_medio_pago, id_estado, id_descuento, valor
                ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;

            PreparedStatement ps = conexion.getConexion().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setTimestamp(1, fechaSalida);
            ps.setTimestamp(2, fechaEntrada);
            ps.setString(3, placaVehiculo);
            ps.setInt(4, idCliente);
            ps.setInt(5, idSucursal);
            ps.setInt(6, idMedioPago);
            ps.setInt(7, idEstado);
            ps.setInt(8, idDescuento);
            ps.setDouble(9, valor);

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    idGenerado = rs.getInt(1);
                    JOptionPane.showMessageDialog(null, "Alquiler registrado correctamente con ID: " + idGenerado);
                }
            } else {
                JOptionPane.showMessageDialog(null, "No se registró el alquiler.");
            }

            ps.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al registrar alquiler: " + e.getMessage());
        }

        return idGenerado;
    }

    public boolean insertarAlquiler(String placa, String idClienteStr, String sucursalStr, String medioPagoStr, String estadoStr) {
        try {
            ConectarBD conexion = new ConectarBD();
            Connection con = conexion.getConexion();

            int idCliente = Integer.parseInt(idClienteStr);
            int idSucursal = new Sucursal(sucursalStr).getId();
            int idMedioPago = new MedioPago(medioPagoStr).getId();
            int idEstado = new EstadoAlquiler(estadoStr).getId();
            int idDescuento = 0;
            double valor = 100000.0;

            Timestamp ahora = new Timestamp(System.currentTimeMillis());

            String sql = """
                INSERT INTO alquiler (
                    fecha_hora_salida, fecha_hora_entrada,
                    id_vehiculo, id_cliente, id_sucursal,
                    id_medio_pago, id_estado, id_descuento, valor
                ) VALUES (?, NULL, ?, ?, ?, ?, ?, ?, ?)
            """;

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setTimestamp(1, ahora);
            ps.setString(2, placa);
            ps.setInt(3, idCliente);
            ps.setInt(4, idSucursal);
            ps.setInt(5, idMedioPago);
            ps.setInt(6, idEstado);
            ps.setInt(7, idDescuento);
            ps.setDouble(8, valor);

            int filas = ps.executeUpdate();
            ps.close();

            return filas > 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al insertar alquiler básico: " + e.getMessage());
            return false;
        }
    }
}