
package Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Usuario {

    private int idUsuario;
    private String correo;
    private String contrasena;
    private String rol;
    private int idReferencia;

    public Usuario(int idUsuario, String correo, String contrasena, String rol, int idReferencia) {
        this.idUsuario = idUsuario;
        this.correo = correo;
        this.contrasena = contrasena;
        this.rol = rol;
        this.idReferencia = idReferencia;
    }

    public Usuario() {
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public int getIdReferencia() {
        return idReferencia;
    }

    public void setIdReferencia(int idReferencia) {
        this.idReferencia = idReferencia;
    }

    public Usuario validarUsuario(String correo, String contrasena) {
        Usuario usuario = null;
        try {
            ConectarBD conexion = new ConectarBD();
            String sql = "SELECT * FROM Usuario WHERE correo = ? AND contrasena = ?";
            PreparedStatement ps = conexion.getConexion().prepareStatement(sql);
            ps.setString(1, correo);
            ps.setString(2, contrasena);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setContrasena(rs.getString("contrasena"));
                usuario.setRol(rs.getString("rol"));
                usuario.setIdReferencia(rs.getInt("id_referencia"));
            }

            rs.close();
            ps.close();
            conexion.getConexion().close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al validar usuario: " + e.getMessage());
        }
        return usuario;
    }
}
