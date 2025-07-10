
package Modelo;

public class Factura {

    private int id_factura;
    private double valor;
    private int id_alquiler;
    private int id_cliente;
    private String id_vehiculo;
    private int id_det_factura;

    public Factura(int id_factura, double valor, int id_alquiler, int id_cliente, String id_vehiculo, int id_det_factura) {
        this.id_factura = id_factura;
        this.valor = valor;
        this.id_alquiler = id_alquiler;
        this.id_cliente = id_cliente;
        this.id_vehiculo = id_vehiculo;
        this.id_det_factura = id_det_factura;
    }

    public Factura() {
    }

    public int getId_factura() {
        return id_factura;
    }

    public void setId_factura(int id_factura) {
        this.id_factura = id_factura;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getId_alquiler() {
        return id_alquiler;
    }

    public void setId_alquiler(int id_alquiler) {
        this.id_alquiler = id_alquiler;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getId_vehiculo() {
        return id_vehiculo;
    }

    public void setId_vehiculo(String id_vehiculo) {
        this.id_vehiculo = id_vehiculo;
    }

    public int getId_det_factura() {
        return id_det_factura;
    }

    public void setId_det_factura(int id_det_factura) {
        this.id_det_factura = id_det_factura;
    }
}
