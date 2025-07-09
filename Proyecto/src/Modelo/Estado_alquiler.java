
package Modelo;

public class Estado_alquiler {

    private int id_estado;
    private String descripcion;

    public Estado_alquiler(int id_estado, String descripcion) {
        this.id_estado = id_estado;
        this.descripcion = descripcion;
    }

    public Estado_alquiler() {
    }

    public int getId_estado() {
        return id_estado;
    }

    public void setId_estado(int id_estado) {
        this.id_estado = id_estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
