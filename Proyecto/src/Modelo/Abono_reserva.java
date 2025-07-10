
package Modelo;

import java.util.Date;

public class Abono_reserva {

    private int id_abono;
    private double valor;
    private Date fecha_hora;
    private int id_reserva;
    private int id_medio_pago;

    public Abono_reserva(int id_abono, double valor, Date fecha_hora, int id_reserva, int id_medio_pago) {
        this.id_abono = id_abono;
        this.valor = valor;
        this.fecha_hora = fecha_hora;
        this.id_reserva = id_reserva;
        this.id_medio_pago = id_medio_pago;
    }

    public Abono_reserva() {
    }

    public int getId_abono() {
        return id_abono;
    }

    public void setId_abono(int id_abono) {
        this.id_abono = id_abono;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(Date fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    public int getId_reserva() {
        return id_reserva;
    }

    public void setId_reserva(int id_reserva) {
        this.id_reserva = id_reserva;
    }

    public int getId_medio_pago() {
        return id_medio_pago;
    }

    public void setId_medio_pago(int id_medio_pago) {
        this.id_medio_pago = id_medio_pago;
    }
}
