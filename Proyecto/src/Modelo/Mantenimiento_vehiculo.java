
package Modelo;

import java.util.Date;

public class Mantenimiento_vehiculo {

    private int id_mantenimiento;
    private String descripcion;
    private Date fecha_hora;
    private double valor;
    private int id_tipo;
    private int id_taller;
    private String id_vehiculo;

    public Mantenimiento_vehiculo(int id_mantenimiento, String descripcion, Date fecha_hora, double valor, int id_tipo, int id_taller, String id_vehiculo) {
        this.id_mantenimiento = id_mantenimiento;
        this.descripcion = descripcion;
        this.fecha_hora = fecha_hora;
        this.valor = valor;
        this.id_tipo = id_tipo;
        this.id_taller = id_taller;
        this.id_vehiculo = id_vehiculo;
    }

    public Mantenimiento_vehiculo() {
    }

    public int getId_mantenimiento() {
        return id_mantenimiento;
    }

    public void setId_mantenimiento(int id_mantenimiento) {
        this.id_mantenimiento = id_mantenimiento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public int getId_tipo() {
        return id_tipo;
    }

    public void setId_tipo(int id_tipo) {
        this.id_tipo = id_tipo;
    }

    public int getId_taller() {
        return id_taller;
    }

    public void setId_taller(int id_taller) {
        this.id_taller = id_taller;
    }

    public String getId_vehiculo() {
        return id_vehiculo;
    }

    public void setId_vehiculo(String id_vehiculo) {
        this.id_vehiculo = id_vehiculo;
    }
}
