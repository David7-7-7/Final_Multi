package Modelo;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
public class Alquiler {

    public boolean insertarAlquiler(String placa, String idCliente, String fechaInicioStr, String fechaFinStr, String medioPago, double descuento) {
        try {
            ConectarBD conexion = new ConectarBD();

            // Convertir fechas a LocalDate
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date fechaInicio = sdf.parse(fechaInicioStr);
            java.util.Date fechaFin = sdf.parse(fechaFinStr);
            LocalDate inicio = fechaInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate fin = fechaFin.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            // Validación de fechas
            long dias = ChronoUnit.DAYS.between(inicio, fin);
            if (dias <= 0) {
                JOptionPane.showMessageDialog(null, "La fecha de salida debe ser posterior a la de entrada.");
                return false;
            }

            // Obtener el tipo de vehículo desde la placa
            String sqlTipo = "SELECT id_tipo_vehiculo FROM vehiculo WHERE placa = ?";
            PreparedStatement psTipo = conexion.getConexion().prepareStatement(sqlTipo);
            psTipo.setString(1, placa);
            ResultSet rsTipo = psTipo.executeQuery();

            int idTipo = -1;
            if (rsTipo.next()) {
                idTipo = rsTipo.getInt("id_tipo_vehiculo");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el tipo de vehículo para la placa.");
                return false;
            }

            // Obtener valor por día del tipo de vehículo
            String sqlValor = "SELECT valor_dia FROM tipo_vehiculo WHERE id = ?";
            PreparedStatement psValor = conexion.getConexion().prepareStatement(sqlValor);
            psValor.setInt(1, idTipo);
            ResultSet rsValor = psValor.executeQuery();

            double valorDia = 0;
            if (rsValor.next()) {
                valorDia = rsValor.getDouble("valor_dia");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el valor por día del tipo de vehículo.");
                return false;
            }

            // Calcular total
            double totalSinDescuento = valorDia * dias;
            double totalFinal = totalSinDescuento - (totalSinDescuento * descuento / 100.0);

            // Insertar en la tabla alquiler
            String sqlInsert = "INSERT INTO alquiler (placa, id_cliente, fecha_inicio, fecha_fin, dias, medio_pago, descuento, total) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement psInsert = conexion.getConexion().prepareStatement(sqlInsert);
            psInsert.setString(1, placa);
            psInsert.setString(2, idCliente);
            psInsert.setDate(3, new java.sql.Date(fechaInicio.getTime()));
            psInsert.setDate(4, new java.sql.Date(fechaFin.getTime()));
            psInsert.setInt(5, (int) dias);
            psInsert.setString(6, medioPago);
            psInsert.setDouble(7, descuento);
            psInsert.setDouble(8, totalFinal);

            int resultado = psInsert.executeUpdate();
            if (resultado > 0) {
                JOptionPane.showMessageDialog(null, "Alquiler registrado correctamente.");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Error al registrar el alquiler.");
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            return false;
        }
    }
    public int calcularDias(String fechaInicio, String fechaFin) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date inicio = sdf.parse(fechaInicio);
            Date fin = sdf.parse(fechaFin);
            long diferenciaMillis = fin.getTime() - inicio.getTime();
            long dias = TimeUnit.DAYS.convert(diferenciaMillis, TimeUnit.MILLISECONDS);
            return (int) dias;
        } catch (Exception e) {
            e.printStackTrace();
            return -1; // Error
        }
    }
      public double calcularTotal(int dias, double valorDia, String tipoDescuento) {
        double descuento = 0.0;

        switch (tipoDescuento.toLowerCase()) {
            case "5%":
                descuento = 0.05;
                break;
            case "10%":
                descuento = 0.10;
                break;
            case "15%":
                descuento = 0.15;
                break;
            case "sin descuento":
            default:
                descuento = 0.0;
        }

        double totalBruto = dias * valorDia;
        return totalBruto - (totalBruto * descuento);
    }
}

