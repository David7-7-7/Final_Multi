
package Modelo;

import java.util.Date;

public class Cuenta_cobrar {

    private int id_cuenta_cobrar;
    private Date fecha_hora;
    private double valor;
    private String descripcion;
    private int id_medio_pago;
    private int id_tipo_entidad;
    private int id_entidad;

    public Cuenta_cobrar(int id_cuenta_cobrar, Date fecha_hora, double valor, String descripcion, int id_medio_pago, int id_tipo_entidad, int id_entidad) {
        this.id_cuenta_cobrar = id_cuenta_cobrar;
        this.fecha_hora = fecha_hora;
        this.valor = valor;
        this.descripcion = descripcion;
        this.id_medio_pago = id_medio_pago;
        this.id_tipo_entidad = id_tipo_entidad;
        this.id_entidad = id_entidad;
    }

    public Cuenta_cobrar() {
    }

    public int getId_cuenta_cobrar() {
        return id_cuenta_cobrar;
    }

    public void setId_cuenta_cobrar(int id_cuenta_cobrar) {
        this.id_cuenta_cobrar = id_cuenta_cobrar;
    }

    public Date getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(Date fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getId_medio_pago() {
        return id_medio_pago;
    }

    public void setId_medio_pago(int id_medio_pago) {
        this.id_medio_pago = id_medio_pago;
    }

    public int getId_tipo_entidad() {
        return id_tipo_entidad;
    }

    public void setId_tipo_entidad(int id_tipo_entidad) {
        this.id_tipo_entidad = id_tipo_entidad;
    }

    public int getId_entidad() {
        return id_entidad;
    }

    public void setId_entidad(int id_entidad) {
        this.id_entidad = id_entidad;
    }
}
