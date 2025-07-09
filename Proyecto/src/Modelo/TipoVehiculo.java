package Modelo;

public class TipoVehiculo extends VehiculoAtributo {

    private int capacidad;
    private String combustible;
    private double tarifaDia;

    public TipoVehiculo(String descripcion, int capacidad, String combustible, double tarifaDia) {
        super(descripcion, "Tipo_vehiculo", "id_tipo", "descripcion");
        this.capacidad = capacidad;
        this.combustible = combustible;
        this.tarifaDia = tarifaDia;
    }

    public TipoVehiculo(String descripcion) {
        super(descripcion, "Tipo_vehiculo", "id_tipo", "descripcion");
    }

    public int getCapacidad() {
        return capacidad;
    }

    public String getCombustible() {
        return combustible;
    }

    public double getTarifaDia() {
        return tarifaDia;
    }
}