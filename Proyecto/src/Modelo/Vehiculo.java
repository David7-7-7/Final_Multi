
package Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Vehiculo {

    private String placa;
    private String nChasis;
    private String modelo;
    private int kilometraje;
    private MarcaVehiculo marca;
    private ColorVehiculoo color;
    private TipoVehiculo tipoVehiculo;
    private BlindajeVehiculo blindaje;
    private TransmisionVehiculo transmision;
    private CilindrajeVehiculo cilindraje;
    private SeguroVehiculo seguroVehiculo;
    private EstadoVehiculo estadoVehiculo;
    private ProveedorVehiculo proveedor;
    private Sucursal sucursal;

    public Vehiculo() {}

    public Vehiculo(String placa, String nChasis, String modelo, int kilometraje, MarcaVehiculo marca, ColorVehiculoo color, TipoVehiculo tipoVehiculo, BlindajeVehiculo blindaje, TransmisionVehiculo transmision, CilindrajeVehiculo cilindraje, SeguroVehiculo seguroVehiculo, EstadoVehiculo estadoVehiculo, ProveedorVehiculo proveedor, Sucursal sucursal) {
        this.placa = placa;
        this.nChasis = nChasis;
        this.modelo = modelo;
        this.kilometraje = kilometraje;
        this.marca = marca;
        this.color = color;
        this.tipoVehiculo = tipoVehiculo;
        this.blindaje = blindaje;
        this.transmision = transmision;
        this.cilindraje = cilindraje;
        this.seguroVehiculo = seguroVehiculo;
        this.estadoVehiculo = estadoVehiculo;
        this.proveedor = proveedor;
        this.sucursal = sucursal;
    }

    public boolean insertarVehiculo() {
        try {
            ConectarBD conexion = new ConectarBD();
            String sql = "INSERT INTO Vehiculo (placa, n_chasis, modelo, kilometraje, id_marca, id_color, " +
                         "id_tipo_vehiculo, id_blindaje, id_transmision, id_cilindraje, id_seguro_vehiculo, " +
                         "id_estado_vehiculo, id_proveedor, id_sucursal) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = conexion.getConexion().prepareStatement(sql);
            ps.setString(1, placa);
            ps.setString(2, nChasis);
            ps.setString(3, modelo);
            ps.setInt(4, kilometraje);
            ps.setInt(5, marca.getId());
            ps.setInt(6, color.getId());
            ps.setInt(7, tipoVehiculo.getId());
            ps.setInt(8, blindaje.getId());
            ps.setInt(9, transmision.getId());
            ps.setInt(10, cilindraje.getId());
            ps.setInt(11, seguroVehiculo.getId());
            ps.setInt(12, estadoVehiculo.getId());
            ps.setInt(13, proveedor.getId());
            ps.setInt(14, sucursal.getId());

            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Vehículo registrado correctamente.");
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al insertar vehículo: " + e.getMessage());
            return false;
        }
    }

    public boolean consultarVehiculoConFiltros(MarcaVehiculo marca) {
        try {
            ConectarBD conexion = new ConectarBD();
            String sql = "SELECT * FROM Vehiculo WHERE id_marca = ? LIMIT 1";

            PreparedStatement ps = conexion.getConexion().prepareStatement(sql);
            ps.setInt(1, marca.getId());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                this.placa = rs.getString("placa");
                this.nChasis = rs.getString("n_chasis");
                this.modelo = rs.getString("modelo");
                this.kilometraje = rs.getInt("kilometraje");
                this.marca = new MarcaVehiculo(rs.getString("id_marca"));
                this.color = new ColorVehiculoo(rs.getString("id_color"));
                this.tipoVehiculo = new TipoVehiculo(rs.getString("id_tipo_vehiculo"));
                this.blindaje = new BlindajeVehiculo(rs.getString("id_blindaje"));
                this.transmision = new TransmisionVehiculo(rs.getString("id_transmision"));
                this.cilindraje = new CilindrajeVehiculo(rs.getString("id_cilindraje"));
                this.seguroVehiculo = new SeguroVehiculo(rs.getString("id_seguro_vehiculo"));
                this.estadoVehiculo = new EstadoVehiculo(rs.getString("id_estado_vehiculo"));
                this.proveedor = new ProveedorVehiculo(rs.getString("id_proveedor"));
                this.sucursal = new Sucursal(rs.getString("id_sucursal"));
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró un vehículo con esa marca.");
                return false;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al consultar vehículo: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarVehiculo(String placaEliminar) {
        try {
            ConectarBD conexion = new ConectarBD();
            String sql = "DELETE FROM Vehiculo WHERE placa = ?";
            PreparedStatement ps = conexion.getConexion().prepareStatement(sql);
            ps.setString(1, placaEliminar);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                JOptionPane.showMessageDialog(null, "Vehículo eliminado correctamente.");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el vehículo para eliminar.");
                return false;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar vehículo: " + e.getMessage());
            return false;
        }
    }

    public static boolean verificarDisponibilidad(MarcaVehiculo marca) {
        try {
            ConectarBD conexion = new ConectarBD();
            String sql = "SELECT id_estado_vehiculo FROM Vehiculo WHERE id_marca = ?;";

            PreparedStatement ps = conexion.getConexion().prepareStatement(sql);
            ps.setInt(1, marca.getId());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int estado = rs.getInt("id_estado_vehiculo");
                return estado == 1;  // true si es disponible
            } else {
                return false;  // no encontrado, lo tomamos como no disponible
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al verificar disponibilidad: " + e.getMessage());
            return false;
        }
    }

    public boolean verificarDisponibilidadFiltrada(MarcaVehiculo marca, ColorVehiculoo color, TipoVehiculo tipo, TransmisionVehiculo transmision,
                                               Sucursal sucursal, BlindajeVehiculo blindaje, CilindrajeVehiculo cilindraje) {
        boolean disponible = false;
        try {
            ConectarBD conexion = new ConectarBD();

            String sql = """
            SELECT * FROM Vehiculo
            WHERE id_marca = ?
              AND id_color = ?
              AND id_tipo_vehiculo = ?
              AND id_transmision = ?
              AND id_sucursal = ?
              AND id_blindaje = ?
              AND id_cilindraje = ?
              AND id_estado_vehiculo = (SELECT id_estado FROM estado_vehiculo WHERE descripcion = 'Disponible')
        """;

            PreparedStatement ps = conexion.getConexion().prepareStatement(sql);
            ps.setInt(1, marca.getId());
            ps.setInt(2, color.getId());
            ps.setInt(3, tipo.getId());
            ps.setInt(4, transmision.getId());
            ps.setInt(5, sucursal.getId());
            ps.setInt(6, blindaje.getId());
            ps.setInt(7, cilindraje.getId());

            ResultSet rs = ps.executeQuery();
            disponible = rs.next(); // Si hay resultados, hay al menos un vehículo disponible

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al consultar disponibilidad: " + e.getMessage());
        }

        return disponible;
    }

    public String obtenerPlacaDisponible(MarcaVehiculo marca, ColorVehiculoo color, TipoVehiculo tipo, TransmisionVehiculo transmision,
                                     Sucursal sucursal, BlindajeVehiculo blindaje, CilindrajeVehiculo cilindraje) {
        String placa = null;
        try {
            ConectarBD conexion = new ConectarBD();
            String sql = """
            SELECT placa FROM Vehiculo
            WHERE id_marca = ?
              AND id_color = ?
              AND id_tipo_vehiculo = ?
              AND id_transmision = ?
              AND id_sucursal = ?
              AND id_blindaje = ?
              AND id_cilindraje = ?
              AND id_estado_vehiculo = 1
            LIMIT 1
        """;

            PreparedStatement ps = conexion.getConexion().prepareStatement(sql);
            ps.setInt(1, marca.getId());
            ps.setInt(2, color.getId());
            ps.setInt(3, tipo.getId());
            ps.setInt(4, transmision.getId());
            ps.setInt(5, sucursal.getId());
            ps.setInt(6, blindaje.getId());
            ps.setInt(7, cilindraje.getId());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                placa = rs.getString("placa");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al consultar disponibilidad: " + e.getMessage());
        }
        return placa;
    }
}
