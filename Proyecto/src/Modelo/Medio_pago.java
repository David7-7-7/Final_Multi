
package Modelo;

public class Medio_pago {

    private int id_medio_pago;
    private String descripcion;

    public Medio_pago(int id_medio_pago, String descripcion) {
        this.id_medio_pago = id_medio_pago;
        this.descripcion = descripcion;
    }

    public Medio_pago() {
    }

    public int getId_medio_pago() {
        return id_medio_pago;
    }

    public void setId_medio_pago(int id_medio_pago) {
        this.id_medio_pago = id_medio_pago;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
