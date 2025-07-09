
package Modelo;

public class Det_factura {

    private int id_det_factura;
    private int id_servicio;
    private double valor;
    private double impuestos;

    public Det_factura(int id_det_factura, int id_servicio, double valor, double impuestos) {
        this.id_det_factura = id_det_factura;
        this.id_servicio = id_servicio;
        this.valor = valor;
        this.impuestos = impuestos;
    }

    public Det_factura() {
    }

    public int getId_det_factura() {
        return id_det_factura;
    }

    public void setId_det_factura(int id_det_factura) {
        this.id_det_factura = id_det_factura;
    }

    public int getId_servicio() {
        return id_servicio;
    }

    public void setId_servicio(int id_servicio) {
        this.id_servicio = id_servicio;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getImpuestos() {
        return impuestos;
    }

    public void setImpuestos(double impuestos) {
        this.impuestos = impuestos;
    }
}
