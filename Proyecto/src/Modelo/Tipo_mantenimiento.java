
package Modelo;

public class Tipo_mantenimiento {

    private int id_tipo;
    private String descripcion;

    public Tipo_mantenimiento(int id_tipo, String descripcion) {
        this.id_tipo = id_tipo;
        this.descripcion = descripcion;
    }

    public Tipo_mantenimiento() {
    }

    public int getId_tipo() {
        return id_tipo;
    }

    public void setId_tipo(int id_tipo) {
        this.id_tipo = id_tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
