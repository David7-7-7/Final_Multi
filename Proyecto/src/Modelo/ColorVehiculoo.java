
package Modelo;

public class ColorVehiculoo extends VehiculoAtributo {

    public ColorVehiculoo(String nombre) {
        super(nombre, "Color_vehiculo", "id_color", "nombre_color");
    }

    public String getNombre() {
        return super.getDescripcion();
    }
}
