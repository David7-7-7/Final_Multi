package modelo;

import Modelo.ConectarBD;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
public class Vehiculos {

    private String placa;
    private String nChasis;
    private String modelo;
    private int kilometraje;
    private int idMarca;
    private int idColor;
    private int idTipoVehiculo;
    private int idBlindaje;
    private int idTransmision;
    private int idCilindraje;
    private int idSeguroVehiculo;
    private int idEstadoVehiculo;
    private int idProveedor;
    private int idSucursal;

    // --- Constructor vacío ---
    public Vehiculos() {}

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getnChasis() {
        return nChasis;
    }

    public void setnChasis(String nChasis) {
        this.nChasis = nChasis;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(int kilometraje) {
        this.kilometraje = kilometraje;
    }

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public int getIdColor() {
        return idColor;
    }

    public void setIdColor(int idColor) {
        this.idColor = idColor;
    }

    public int getIdTipoVehiculo() {
        return idTipoVehiculo;
    }

    public void setIdTipoVehiculo(int idTipoVehiculo) {
        this.idTipoVehiculo = idTipoVehiculo;
    }

    public int getIdBlindaje() {
        return idBlindaje;
    }

    public void setIdBlindaje(int idBlindaje) {
        this.idBlindaje = idBlindaje;
    }

    public int getIdTransmision() {
        return idTransmision;
    }

    public void setIdTransmision(int idTransmision) {
        this.idTransmision = idTransmision;
    }

    public int getIdCilindraje() {
        return idCilindraje;
    }

    public void setIdCilindraje(int idCilindraje) {
        this.idCilindraje = idCilindraje;
    }

    public int getIdSeguroVehiculo() {
        return idSeguroVehiculo;
    }

    public void setIdSeguroVehiculo(int idSeguroVehiculo) {
        this.idSeguroVehiculo = idSeguroVehiculo;
    }

    public int getIdEstadoVehiculo() {
        return idEstadoVehiculo;
    }

    public void setIdEstadoVehiculo(int idEstadoVehiculo) {
        this.idEstadoVehiculo = idEstadoVehiculo;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    // --- Constructor completo ---
    public Vehiculos(String placa, String nChasis, String modelo, int kilometraje, int idMarca, int idColor,
                    int idTipoVehiculo, int idBlindaje, int idTransmision, int idCilindraje,
                    int idSeguroVehiculo, int idEstadoVehiculo, int idProveedor, int idSucursal) {
        this.placa = placa;
        this.nChasis = nChasis;
        this.modelo = modelo;
        this.kilometraje = kilometraje;
        this.idMarca = idMarca;
        this.idColor = idColor;
        this.idTipoVehiculo = idTipoVehiculo;
        this.idBlindaje = idBlindaje;
        this.idTransmision = idTransmision;
        this.idCilindraje = idCilindraje;
        this.idSeguroVehiculo = idSeguroVehiculo;
        this.idEstadoVehiculo = idEstadoVehiculo;
        this.idProveedor = idProveedor;
        this.idSucursal = idSucursal;
    }

    // ========== MÉTODOS DE BASE DE DATOS ==========

    // INSERTAR VEHÍCULO
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
            ps.setInt(5, idMarca);
            ps.setInt(6, idColor);
            ps.setInt(7, idTipoVehiculo);
            ps.setInt(8, idBlindaje);
            ps.setInt(9, idTransmision);
            ps.setInt(10, idCilindraje);
            ps.setInt(11, idSeguroVehiculo);
            ps.setInt(12, idEstadoVehiculo);
            ps.setInt(13, idProveedor);
            ps.setInt(14, idSucursal);

            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Vehículo registrado correctamente.");
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al insertar vehículo: " + e.getMessage());
            return false;
        }
    }

    // CONSULTAR VEHÍCULO POR PLACA
    public boolean consultarVehiculoConFiltros(int idMarca) {
    try {
        ConectarBD conexion = new ConectarBD();
        String sql = "SELECT * FROM Vehiculo WHERE id_marca = ? LIMIT 1";

        PreparedStatement ps = conexion.getConexion().prepareStatement(sql);
        ps.setInt(1, idMarca);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            // Puedes llenar los atributos si quieres, o solo retornar true
            this.placa = rs.getString("placa");
            this.nChasis = rs.getString("n_chasis");
            this.modelo = rs.getString("modelo");
            this.kilometraje = rs.getInt("kilometraje");
            this.idMarca = rs.getInt("id_marca");
            this.idColor = rs.getInt("id_color");
            this.idTipoVehiculo = rs.getInt("id_tipo_vehiculo");
            this.idBlindaje = rs.getInt("id_blindaje");
            this.idTransmision = rs.getInt("id_transmision");
            this.idCilindraje = rs.getInt("id_cilindraje");
            this.idSeguroVehiculo = rs.getInt("id_seguro_vehiculo");
            this.idEstadoVehiculo = rs.getInt("id_estado_vehiculo");
            this.idProveedor = rs.getInt("id_proveedor");
            this.idSucursal = rs.getInt("id_sucursal");
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

    

    // ELIMINAR VEHÍCULO
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

    // VERIFICAR DISPONIBILIDAD POR FILTROS
    public static boolean verificarDisponibilidad(int idMarca) {
    try {
        ConectarBD conexion = new ConectarBD();
        String sql = "SELECT id_estado_vehiculo FROM Vehiculo WHERE id_marca = ?;";
        
        PreparedStatement ps = conexion.getConexion().prepareStatement(sql);
        ps.setInt(1, idMarca);
        
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
    
    
}
