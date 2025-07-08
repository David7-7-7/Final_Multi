package Modelo;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

/// SEBASTIAN ES UN CACORRO HIJUEPUTA OCN LA MAMA MAS TETUDA
public class Licencia {
    private int puebra2;
    private int idLicencia;
    private String tipo;
    private String fechaExpedicion;
    private String fechaVencimiento;
    private String estado;

    public int getIdLicencia() {
        return idLicencia;
    }

    public void setIdLicencia(int idLicencia) {
        this.idLicencia = idLicencia;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFechaExpedicion() {
        return fechaExpedicion;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaExpedicion(String fechaExpedicion) {
        this.fechaExpedicion = fechaExpedicion;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    // Getters y Setters aquí...

    public boolean insertarLicencia() {
        try {
            ConectarBD conexion = new ConectarBD();
            String sql = "INSERT INTO licencia_conduccion (tipo, fecha_expedicion, fecha_vencimiento, estado) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conexion.getConexion().prepareStatement(sql);
            ps.setString(1, tipo);
            ps.setString(2, fechaExpedicion);
            ps.setString(3, fechaVencimiento);
            ps.setString(4, estado);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Licencia registrada correctamente.");
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al insertar licencia: " + e.getMessage());
            return false;
        }
    }
    public boolean existeLicencia(int idLicencia) {
    boolean existe = false;
    try {
        ConectarBD conexion = new ConectarBD();
        String sql = "SELECT * FROM licencia_conduccion WHERE id_licencia = ?";
        PreparedStatement ps = conexion.getConexion().prepareStatement(sql);
        ps.setInt(1, idLicencia);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            existe = true;
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al verificar licencia: " + e.getMessage());
    }
    return existe;
}
    
    public DefaultTableModel consultar() 
    {
        ConectarBD conexion = new ConectarBD();
        String titulos[] = {"placa", "n_chasis", "modelo", "kilometraje", "id_marca", "id_color", "id_tipo_vehiculo", "id_blindaje", 
                            "id_transmision", "id_cilindraje", "id_seguro_vehiculo", "id_estado_vehiculo", "id_proveedor", "id_sucursal"};
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);

        try 
        {
            Statement sentencia = conexion.getConexion().createStatement();
            ResultSet resultado = sentencia.executeQuery("SELECT * FROM vehiculo");

            while (resultado.next()) 
            {
                Object[] fila = new Object[14];

                fila[0] = resultado.getString("placa");
                fila[1] = resultado.getString("n_chasis");
                fila[2] = resultado.getString("modelo");
                fila[3] = resultado.getInt("kilometraje");
                fila[4] = resultado.getInt("id_marca");
                fila[5] = resultado.getInt("id_color");
                fila[6] = resultado.getInt("id_tipo_vehiculo");
                fila[7] = resultado.getInt("id_blindaje");
                fila[8] = resultado.getInt("id_transmision");
                fila[9] = resultado.getInt("id_cilindraje");
                fila[10] = resultado.getInt("id_seguro_vehiculo");
                fila[11] = resultado.getInt("id_estado_vehiculo");
                fila[12] = resultado.getInt("id_proveedor");
                fila[13] = resultado.getInt("id_sucursal");

                modelo.addRow(fila);
            }

            resultado.close();
            conexion.getConexion().close();
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error....:" + e, "Información", JOptionPane.INFORMATION_MESSAGE);
        }
            return modelo;
    }
    
    
}