package Modelo;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.util.Date;
import java.sql.ResultSet;

public class CodigoPostal {

    private String idCodigoPostal;
    private String pais;
    private String departamento;
    private String ciudad;

    // Getters y Setters
    public String getIdCodigoPostal() {
        return idCodigoPostal;
    }

    public void setIdCodigoPostal(String idCodigoPostal) {
        this.idCodigoPostal = idCodigoPostal;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    // Verificar existencia
    public boolean existeCodigoPostal(String id) {
        boolean existe = false;
        try  {
            ConectarBD conexion = new ConectarBD();
            String sql ="SELECT 1 FROM Codigo_postal WHERE id_codigo_postal = ?";
            PreparedStatement ps = conexion.getConexion().prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            existe = rs.next();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al verificar código postal: " + e.getMessage());
        }
        return existe;
    }

    // Insertar nuevo código postal
    public boolean insertarCodigoPostal() {
        boolean resultado = false;
        try  {
            ConectarBD conexion = new ConectarBD();
            String sql ="INSERT INTO Codigo_postal ( id_codigo_postal,pais, departamento, ciudad) VALUES ( ?,?, ?, ?)";
            PreparedStatement ps = conexion.getConexion().prepareStatement(sql);
            ps.setString(1, idCodigoPostal);
            ps.setString(2, pais);
            ps.setString(3, departamento);
            ps.setString(4, ciudad);

            resultado = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al insertar código postal: " + e.getMessage());
        }
        return resultado;
    }
}
