
package Modelo;

public class Descuento_alquiler {

    private int id_descuento;
    private String descripcion;
    private double valor;

    public Descuento_alquiler(int id_descuento, String descripcion, double valor) {
        this.id_descuento = id_descuento;
        this.descripcion = descripcion;
        this.valor = valor;
    }

    public Descuento_alquiler() {
    }

    public int getId_descuento() {
        return id_descuento;
    }

    public void setId_descuento(int id_descuento) {
        this.id_descuento = id_descuento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
