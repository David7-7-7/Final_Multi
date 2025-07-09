
package Modelo;

public class MarcaVehiculo extends VehiculoAtributo {

    public MarcaVehiculo(String nombre) {
        super(nombre, "Marca_vehiculo", "id_marca", "nombre_marca");
    }

    public String getNombre() {
        return super.getDescripcion();
    }
}
