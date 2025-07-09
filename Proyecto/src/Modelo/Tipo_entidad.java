
package Modelo;

public class Tipo_entidad {

    private int id_tipo_entidad;
    private String descripcion;

    public Tipo_entidad(int id_tipo_entidad, String descripcion) {
        this.id_tipo_entidad = id_tipo_entidad;
        this.descripcion = descripcion;
    }

    public Tipo_entidad() {
    }

    public int getId_tipo_entidad() {
        return id_tipo_entidad;
    }

    public void setId_tipo_entidad(int id_tipo_entidad) {
        this.id_tipo_entidad = id_tipo_entidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
