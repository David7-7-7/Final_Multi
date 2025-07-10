
package Vista;

import javax.swing.JButton;

public class FormularioAdmin extends javax.swing.JFrame {

    public FormularioAdmin() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        btnGestionarVehiculos = new javax.swing.JButton();
        btnGestionarClientes = new javax.swing.JButton();
        btnGestionarEmpleados = new javax.swing.JButton();
        btnGestionarAlquileres = new javax.swing.JButton();
        btnGestionarReservas = new javax.swing.JButton();
        btnGestionarMantenimiento = new javax.swing.JButton();
        btnGestionarFacturas = new javax.swing.JButton();
        btnGestionarSucursales = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnGestionarVehiculos.setText("Gestionar Vehiculos");
        jPanel1.add(btnGestionarVehiculos);

        btnGestionarClientes.setText("Gestionar Clientes");
        jPanel1.add(btnGestionarClientes);

        btnGestionarEmpleados.setText("Gestionar Empleados");
        jPanel1.add(btnGestionarEmpleados);

        btnGestionarAlquileres.setText("Gestionar Alquileres");
        jPanel1.add(btnGestionarAlquileres);

        btnGestionarReservas.setText("Gestionar Reservas");
        jPanel1.add(btnGestionarReservas);

        btnGestionarMantenimiento.setText("Gestionar Mantenimiento");
        jPanel1.add(btnGestionarMantenimiento);

        btnGestionarFacturas.setText("Gestionar Facturas");
        jPanel1.add(btnGestionarFacturas);

        btnGestionarSucursales.setText("Gestionar Sucursales");
        jPanel1.add(btnGestionarSucursales);

        jTabbedPane1.addTab("Administración", jPanel1);

        getContentPane().add(jTabbedPane1, java.awt.BorderLayout.CENTER);

        pack();
    }

    public JButton getBtnGestionarVehiculos() {
        return btnGestionarVehiculos;
    }

    public JButton getBtnGestionarClientes() {
        return btnGestionarClientes;
    }

    public JButton getBtnGestionarEmpleados() {
        return btnGestionarEmpleados;
    }

    public JButton getBtnGestionarAlquileres() {
        return btnGestionarAlquileres;
    }

    public JButton getBtnGestionarReservas() {
        return btnGestionarReservas;
    }

    public JButton getBtnGestionarMantenimiento() {
        return btnGestionarMantenimiento;
    }

    public JButton getBtnGestionarFacturas() {
        return btnGestionarFacturas;
    }

    public JButton getBtnGestionarSucursales() {
        return btnGestionarSucursales;
    }

    private javax.swing.JButton btnGestionarAlquileres;
    private javax.swing.JButton btnGestionarClientes;
    private javax.swing.JButton btnGestionarEmpleados;
    private javax.swing.JButton btnGestionarFacturas;
    private javax.swing.JButton btnGestionarMantenimiento;
    private javax.swing.JButton btnGestionarReservas;
    private javax.swing.JButton btnGestionarSucursales;
    private javax.swing.JButton btnGestionarVehiculos;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTabbedPane jTabbedPane1;
}
