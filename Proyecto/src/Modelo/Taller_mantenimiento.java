
package Modelo;

public class Taller_mantenimiento {

    private int id_taller;
    private String nombre;
    private String direccion;
    private String telefono;

    public Taller_mantenimiento(int id_taller, String nombre, String direccion, String telefono) {
        this.id_taller = id_taller;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public Taller_mantenimiento() {
    }

    public int getId_taller() {
        return id_taller;
    }

    public void setId_taller(int id_taller) {
        this.id_taller = id_taller;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
