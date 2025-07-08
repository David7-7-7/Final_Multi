package Modelo;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.awt.HeadlessException;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

public class Cliente {

    private String idCliente;
    private String documento;
    private String nombre;
    private String telefono;
    private String direccion;
    private String correo;
    private String infracciones;
    private String idLicencia;
    private String idTipoDocumento;
    private String idTipoCliente;
    private String idCodigoPostal;
    private String Contraseña;

    // Getters y Setters
    public String getIdCliente() { return idCliente; }
    public void setIdCliente(String idCliente) { this.idCliente = idCliente; }

    public String getDocumento() { return documento; }
    public void setDocumento(String documento) { this.documento = documento; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getInfracciones() { return infracciones; }
    public void setInfracciones(String infracciones) { this.infracciones = infracciones; }

    public String getIdLicencia() { return idLicencia; }
    public void setIdLicencia(String idLicencia) { this.idLicencia = idLicencia; }

    public String getIdTipoDocumento() { return idTipoDocumento; }
    public void setIdTipoDocumento(String idTipoDocumento) { this.idTipoDocumento = idTipoDocumento; }

    public String getIdTipoCliente() { return idTipoCliente; }
    public void setIdTipoCliente(String idTipoCliente) { this.idTipoCliente = idTipoCliente; }

    public String getIdCodigoPostal() { return idCodigoPostal; }
    public void setIdCodigoPostal(String idCodigoPostal) { this.idCodigoPostal = idCodigoPostal; }

    public String getContraseña() { return Contraseña;}
    public void setContraseña(String Contraseña) { this.Contraseña = Contraseña; }



    // INSERTAR
    public void insertar() {
    try {
        ConectarBD conexion = new ConectarBD();


        // Si está activa, se inserta el cliente
        String sql = "INSERT INTO Cliente (documento, nombre, telefono, direccion, correo, infracciones, id_licencia, tipo_documento, tipo_cliente, id_codigo_postal, contrasena) VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = conexion.getConexion().prepareStatement(sql);
        stmt.setString(1, documento);
        stmt.setString(2, nombre);
        stmt.setString(3, telefono);
        stmt.setString(4, direccion);
        stmt.setString(5, correo);
        stmt.setString(6, infracciones);
        stmt.setString(7, idLicencia);
        stmt.setString(8, idTipoDocumento);
        stmt.setString(9, idTipoCliente);
        stmt.setString(10, idCodigoPostal);
        stmt.setString(11, Contraseña);
        stmt.execute();

        JOptionPane.showMessageDialog(null, "Cliente registrado correctamente.");
        conexion.getConexion().close();

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error SQL: " + e.getMessage());
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error del sistema: " + e.getMessage());
    }
}


    // CONSULTAR
    public DefaultTableModel consultar() {
        ConectarBD conexion = new ConectarBD();
        String[] titulos = {
            "ID", "Documento", "Nombre", "Teléfono", "Dirección", "Correo",
            "Infracciones", "ID Licencia", "Tipo Documento", "Tipo Cliente",
            "Código Postal", "Contraseña"
        };
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);
        int columnas = titulos.length;

        try {
            Statement sentencia = conexion.getConexion().createStatement();
            ResultSet resultado = sentencia.executeQuery("SELECT * FROM Cliente");

            while (resultado.next()) {
                Object[] fila = new Object[columnas];
                fila[0] = resultado.getString("id_cliente");
                fila[1] = resultado.getString("documento");
                fila[2] = resultado.getString("nombre");
                fila[3] = resultado.getString("telefono");
                fila[4] = resultado.getString("direccion");
                fila[5] = resultado.getString("correo");
                fila[6] = resultado.getString("infracciones");
                fila[7] = resultado.getString("id_licencia");
                fila[8] = resultado.getString("tipo_documento");
                fila[9] = resultado.getString("tipo_cliente");
                fila[10] = resultado.getString("id_codigo_postal");
                fila[11] = resultado.getString("contrasena");

                modelo.addRow(fila);
            }

            resultado.close();
            conexion.getConexion().close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al consultar clientes: " + e.getMessage());
        }

        return modelo;
    }

    // ACTUALIZAR
    public String[] buscar(String ID_Cliente, String[] fila) {
        try {
            String criterio = ID_Cliente;
            ConectarBD conexion = new ConectarBD();
            Statement sentencia = (Statement) conexion.getConexion().createStatement();
            ResultSet resultado = sentencia.executeQuery("select * from cliente where id_cliente='" + criterio + "'");
            while (resultado.next()) {
              //  fila[0] = resultado.getString("id_cliente");
                fila[0] = resultado.getString("documento");
                fila[1] = resultado.getString("nombre");
                fila[2] = resultado.getString("telefono");
                fila[3] = resultado.getString("direccion");
                fila[4] = resultado.getString("correo");
                fila[5] = resultado.getString("infracciones");
                fila[6] = resultado.getString("id_licencia");
                fila[7] = resultado.getString("tipo_documento");
                fila[8] = resultado.getString("tipo_cliente");
                fila[9] = resultado.getString("id_codigo_postal");
                fila[10] = resultado.getString("contrasena");
            }
            resultado.close();
            conexion.getConexion().close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error:" + e, "Información", JOptionPane.INFORMATION_MESSAGE);
        }
        return fila;
    }

    public void modificar(String ID_Cliente) {
        try {
            ConectarBD conexion = new ConectarBD();
            String instruccion = "UPDATE Cliente SET documento=?, nombre=?, telefono=?, direccion=?, correo=?, infracciones=?, id_licencia=?, tipo_documento=?, tipo_cliente=?, id_codigo_postal=?, contrasena=? WHERE  id_cliente='" + ID_Cliente + "'";
            conexion.sentencia = conexion.getConexion().prepareStatement(instruccion);
            conexion.sentencia.setString(1, documento);
            conexion.sentencia.setString(2, nombre);
            conexion.sentencia.setString(3, telefono);
            conexion.sentencia.setString(4, direccion);
            conexion.sentencia.setString(5, correo);
            conexion.sentencia.setString(6, infracciones);     
            conexion.sentencia.setString(7, idLicencia);     
            conexion.sentencia.setString(8, idTipoDocumento);
            conexion.sentencia.setString(9, idTipoCliente);
            conexion.sentencia.setString(10, idCodigoPostal);   
            conexion.sentencia.setString(11, Contraseña);   
            conexion.sentencia.execute();
            JOptionPane.showMessageDialog(null, "Registro Modificado", "Información",JOptionPane.INFORMATION_MESSAGE);
      conexion.getConexion().close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL:" + e, "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }


    // ELIMINAR
    public void eliminar(String ID_Cliente) {
        int seleccion = JOptionPane.showOptionDialog(null, "¿Desea ELIMINAR EL REGISTRO (Si/No)", "Seleccione una opción", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Si", "No"}, "Si");
        if ((seleccion + 1) == 1) {
            try {
                ConectarBD conexion = new ConectarBD();
                String instruccion = "Delete from Cliente where id_cliente=" + ID_Cliente;
                conexion.sentencia = (PreparedStatement) conexion.getConexion().prepareStatement(instruccion);
                conexion.sentencia.execute();
                JOptionPane.showMessageDialog(null, "Registro Eliminado", "Información", JOptionPane.INFORMATION_MESSAGE);
                conexion.getConexion().close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error SQL:" + e, "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Registro NO ELIMINADO", "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public String obtenerIDporCorreoYContraseña(String correo, String contraseña) {
    String idCliente = null;
    try {
        ConectarBD conexion = new ConectarBD();
        String sql = "SELECT id_cliente FROM Cliente WHERE correo = ? AND contrasena = ?";
        PreparedStatement stmt = conexion.getConexion().prepareStatement(sql);
        stmt.setString(1, correo);
        stmt.setString(2, contraseña);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            idCliente = rs.getString("id_cliente");
        }

        rs.close();
        conexion.getConexion().close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error al obtener ID del cliente: " + e.getMessage());
    }

    return idCliente;
}
}
