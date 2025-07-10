
package Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public abstract class VehiculoAtributo {

    protected int id;
    protected String descripcion;
    private final String tabla;
    private final String columnaId;
    private final String columnaDescripcion;

    public VehiculoAtributo(String descripcion, String tabla, String columnaId, String columnaDescripcion) {
        this.descripcion = descripcion;
        this.tabla = tabla;
        this.columnaId = columnaId;
        this.columnaDescripcion = columnaDescripcion;
        this.id = obtenerIdPorDescripcion(descripcion);
    }

    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int obtenerIdPorDescripcion(String desc) {
        try {
            ConectarBD conectar = new ConectarBD();
            String sql = "SELECT " + columnaId + " FROM " + tabla + " WHERE " + columnaDescripcion + " = ?";
            PreparedStatement ps = conectar.getConexion().prepareStatement(sql);
            ps.setString(1, desc);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(columnaId);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener ID de " + tabla + ": " + e.getMessage());
        }
        return -1;
    }

    public boolean insertar() {
        try {
            ConectarBD conectar = new ConectarBD();
            String sql = "INSERT INTO " + tabla + " (" + columnaDescripcion + ") VALUES (?)";
            PreparedStatement ps = conectar.getConexion().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, this.descripcion);
            if (ps.executeUpdate() > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    this.id = rs.getInt(1);
                }
                return true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al insertar en " + tabla + ": " + e.getMessage());
        }
        return false;
    }

    public static void cargarEnCombo(JComboBox<String> combo, String tabla, String columnaDescripcion) {
        try {
            combo.removeAllItems();
            ConectarBD conectar = new ConectarBD();
            String sql = "SELECT " + columnaDescripcion + " FROM " + tabla + " ORDER BY " + columnaDescripcion;
            PreparedStatement ps = conectar.getConexion().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                combo.addItem(rs.getString(columnaDescripcion));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar datos de " + tabla + ": " + e.getMessage());
        }
    }
}
