
package Modelo;

import java.util.Date;

public class Reserva_alquiler {

    private int id_reserva;
    private Date fecha_hora;
    private double abono;
    private double saldo_pendiente;
    private int id_estado_reserva;
    private int id_alquiler;

    public Reserva_alquiler(int id_reserva, Date fecha_hora, double abono, double saldo_pendiente, int id_estado_reserva, int id_alquiler) {
        this.id_reserva = id_reserva;
        this.fecha_hora = fecha_hora;
        this.abono = abono;
        this.saldo_pendiente = saldo_pendiente;
        this.id_estado_reserva = id_estado_reserva;
        this.id_alquiler = id_alquiler;
    }

    public Reserva_alquiler() {
    }

    public int getId_reserva() {
        return id_reserva;
    }

    public void setId_reserva(int id_reserva) {
        this.id_reserva = id_reserva;
    }

    public Date getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(Date fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    public double getAbono() {
        return abono;
    }

    public void setAbono(double abono) {
        this.abono = abono;
    }

    public double getSaldo_pendiente() {
        return saldo_pendiente;
    }

    public void setSaldo_pendiente(double saldo_pendiente) {
        this.saldo_pendiente = saldo_pendiente;
    }

    public int getId_estado_reserva() {
        return id_estado_reserva;
    }

    public void setId_estado_reserva(int id_estado_reserva) {
        this.id_estado_reserva = id_estado_reserva;
    }

    public int getId_alquiler() {
        return id_alquiler;
    }

    public void setId_alquiler(int id_alquiler) {
        this.id_alquiler = id_alquiler;
    }
}
