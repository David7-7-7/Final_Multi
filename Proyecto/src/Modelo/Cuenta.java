
package Modelo;

public class Cuenta {

    private int id_cuenta;
    private int id_cuenta_pagar;
    private int id_cuenta_cobrar;

    public Cuenta(int id_cuenta, int id_cuenta_pagar, int id_cuenta_cobrar) {
        this.id_cuenta = id_cuenta;
        this.id_cuenta_pagar = id_cuenta_pagar;
        this.id_cuenta_cobrar = id_cuenta_cobrar;
    }

    public Cuenta() {
    }

    public int getId_cuenta() {
        return id_cuenta;
    }

    public void setId_cuenta(int id_cuenta) {
        this.id_cuenta = id_cuenta;
    }

    public int getId_cuenta_pagar() {
        return id_cuenta_pagar;
    }

    public void setId_cuenta_pagar(int id_cuenta_pagar) {
        this.id_cuenta_pagar = id_cuenta_pagar;
    }

    public int getId_cuenta_cobrar() {
        return id_cuenta_cobrar;
    }

    public void setId_cuenta_cobrar(int id_cuenta_cobrar) {
        this.id_cuenta_cobrar = id_cuenta_cobrar;
    }
}
