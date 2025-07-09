
package Controlador;

import Modelo.*;
import Vista.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ControladorCliente implements ActionListener {

    // Modelos
    Usuario objetoUsuario;
    Cliente objetoModelo;
    Licencia objetoLicencia;
    CodigoPostal objetoCP;
    Vehiculo objetoVehiculo;
    Empleado objetoEmpleado;
    Alquiler objetoAlquiler;

    // Formularios
    FormularioIngresar objetoVIngresar;
    FormularioCliente objetoVista;
    FormularioCliente_1 objetoCliente;
    FormularioGerente objetoVGerente;
    FormularioAlquiler objetoVAlquiler;
    FormularioAdmin objetoVAdmin;

    // Datos auxiliares
    String[] datos = new String[10];
    DefaultTableModel modelo;

    // Constructor
    public ControladorCliente() {
        objetoUsuario = new Usuario();
        objetoModelo = new Cliente();
        objetoLicencia = new Licencia();
        objetoCP = new CodigoPostal();
        objetoVehiculo = new Vehiculo();

        objetoVIngresar = new FormularioIngresar();
        objetoVIngresar.setVisible(true);
        objetoVIngresar.getBotonIngresar().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == objetoVIngresar.getBotonIngresar()) {
            String correo = objetoVIngresar.getTxtCorreo().getText();
            String contrasena = new String(objetoVIngresar.getTxtContraseña().getPassword());

            Usuario usuarioValidado = objetoUsuario.validarUsuario(correo, contrasena);

            if (usuarioValidado != null) {
                switch (usuarioValidado.getRol()) {
                    case "Cliente":
                        JOptionPane.showMessageDialog(null, "¡Bienvenido Cliente!");
                        mostrarFormularioClientee();
                        objetoCliente.getTxtIdCliente().setText(String.valueOf(usuarioValidado.getIdReferencia()));
                        break;
                    case "Empleado":
                        JOptionPane.showMessageDialog(null, "¡Bienvenido Empleado!");
                        mostrarFormularioEmpleado();
                        break;
                    case "Administrador":
                        JOptionPane.showMessageDialog(null, "¡Bienvenido Administrador!");
                        mostrarFormularioAdmin();
                        break;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.");
            }
        }

        if (objetoVista != null && e.getSource() == objetoVista.getBtnInsertar()) {
            insertarCliente();
        }

        if (objetoVGerente != null && e.getSource() == objetoVGerente.getBotonAgregarG()) {
            agregarVehiculoNuevo();
        }
        if (objetoCliente != null && e.getSource() == objetoCliente.getBotonAveriguar()) {
            MarcaVehiculo marca = new MarcaVehiculo(objetoCliente.getMarca().getSelectedItem().toString());
            ColorVehiculoo color = new ColorVehiculoo(objetoCliente.getColor().getSelectedItem().toString());
            TipoVehiculo tipo = new TipoVehiculo(objetoCliente.getTipo().getSelectedItem().toString());
            TransmisionVehiculo transmision = new TransmisionVehiculo(objetoCliente.getTransmision().getSelectedItem().toString());
            Sucursal sucursal = new Sucursal(objetoCliente.getSucursal().getSelectedItem().toString());
            BlindajeVehiculo blindaje = new BlindajeVehiculo(objetoCliente.getBlindaje().getSelectedItem().toString());
            CilindrajeVehiculo cilindraje = new CilindrajeVehiculo(objetoCliente.getCilindraje().getSelectedItem().toString());

            String placaDisponible = objetoVehiculo.obtenerPlacaDisponible(
                marca, color, tipo, transmision, sucursal, blindaje, cilindraje
            );

            if (placaDisponible != null) {
                JOptionPane.showMessageDialog(null, "¡Vehículo disponible! Placa: " + placaDisponible);
                objetoCliente.getTxtPlaca().setText(placaDisponible);
            } else {
                JOptionPane.showMessageDialog(null, "No hay vehículos disponibles con esos filtros.");
            }
        }
        if (objetoCliente != null && e.getSource() == objetoCliente.getBotonAlquilar()) {
         mostrarFormularioAlquiler();
        }

    }

    private void mostrarFormularioClientee() {
        objetoCliente = new FormularioCliente_1();
        objetoCliente.setVisible(true);

        // Cargar datos en los ComboBox del formulario
        VehiculoAtributo.cargarEnCombo(objetoCliente.getMarca(), "Marca_vehiculo", "nombre_marca");
        VehiculoAtributo.cargarEnCombo(objetoCliente.getColor(), "Color_vehiculo", "nombre_color");
        VehiculoAtributo.cargarEnCombo(objetoCliente.getBlindaje(), "Blindaje_vehiculo", "descripcion");
        VehiculoAtributo.cargarEnCombo(objetoCliente.getCilindraje(), "Cilindraje_vehiculo", "descripcion");
        VehiculoAtributo.cargarEnCombo(objetoCliente.getSucursal(), "Sucursal", "nombre");
        VehiculoAtributo.cargarEnCombo(objetoCliente.getTipo(), "Tipo_vehiculo", "descripcion");
        VehiculoAtributo.cargarEnCombo(objetoCliente.getTransmision(), "Transmision_vehiculo", "descripcion");

        // Escuchadores
        objetoCliente.getBtnModificar().addActionListener(this);
        objetoCliente.getBtnEliminar().addActionListener(this);
        objetoCliente.getBotonAveriguar().addActionListener(this);
        objetoCliente.getBotonAlquilar().addActionListener(this);
    }

    private void mostrarFormularioCliente() {
        objetoVista = new FormularioCliente();
        objetoVista.setVisible(true);
        objetoVista.getBtnInsertar().addActionListener(this);
    }

    private void mostrarFormularioEmpleado() {
        objetoVGerente = new FormularioGerente();
        objetoVGerente.setVisible(true);
        objetoVGerente.getBotonAgregarG().addActionListener(this);
        VehiculoAtributo.cargarEnCombo(objetoVGerente.getDisponibilidad(), "Estado_vehiculo", "descripcion");
    }

    private void mostrarFormularioAdmin() {
        objetoVAdmin = new FormularioAdmin();
        objetoVAdmin.setVisible(true);
    }

    private void insertarCliente() {
        String idLicenciaStr = objetoVista.getTxtlicencia().getText();
        if (idLicenciaStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese un ID de licencia.");
            return;
        }

        int idLicencia = Integer.parseInt(idLicenciaStr);
        if (!objetoLicencia.existeLicencia(idLicencia)) {
            mostrarFormularioLicencia();
            return;
        }

        String codigoPostal = objetoVista.getTxtCodigoP().getText();
        if (!objetoCP.existeCodigoPostal(codigoPostal)) {
            mostrarFormularioCodigoPostal();
            return;
        }

        objetoModelo.setDocumento(objetoVista.getTxtDocumento().getText());
        objetoModelo.setNombre(objetoVista.getTxtNombre().getText());
        objetoModelo.setTelefono(objetoVista.getTxtTelefono().getText());
        objetoModelo.setDireccion(objetoVista.getTxtDireccion().getText());
        objetoModelo.setCorreo(objetoVista.getTxtCorreo().getText());
        objetoModelo.setInfracciones(objetoVista.getTxtInfracciones().getText());
        objetoModelo.setIdLicencia(idLicenciaStr);
        objetoModelo.setIdTipoDocumento(objetoVista.getTipoD().getSelectedItem().toString());
        objetoModelo.setIdTipoCliente(objetoVista.getTipoC().getSelectedItem().toString());
        objetoModelo.setIdCodigoPostal(codigoPostal);
        objetoModelo.setContraseña(new String(objetoVista.getTxtContraseña().getPassword()));

        objetoModelo.insertar();
        limpiarFormularioRegistro();
    }

   private void agregarVehiculoNuevo() {
    try {
        String placa = objetoVGerente.getTxtPlaca1().getText().trim();
        String modelo = objetoVGerente.getTxtModelo1().getText().trim();
        String nChasis = objetoVGerente.getTxtNChasis().getText().trim();
        int kilometraje = Integer.parseInt(objetoVGerente.getTxtKm().getText().trim());

        MarcaVehiculo marca = new MarcaVehiculo(objetoVGerente.getTxtMarca1().getText().trim());
        if (marca.getId() == -1) {
            marca.insertar();
        }

        ColorVehiculoo color = new ColorVehiculoo(objetoVGerente.getColor().getSelectedItem().toString());
        BlindajeVehiculo blindaje = new BlindajeVehiculo(objetoVGerente.getBlindaje().getSelectedItem().toString());
        CilindrajeVehiculo cilindraje = new CilindrajeVehiculo(objetoVGerente.getCilindraje().getSelectedItem().toString());
        EstadoVehiculo estado = new EstadoVehiculo(objetoVGerente.getDisponibilidad().getSelectedItem().toString());
        if (estado.getId() == -1) {
            estado.insertar();
        }
        ProveedorVehiculo proveedor = new ProveedorVehiculo(objetoVGerente.getProveedores().getSelectedItem().toString());
        SeguroVehiculo seguro = new SeguroVehiculo(objetoVGerente.getSeguro().getSelectedItem().toString());
        Sucursal sucursal = new Sucursal(objetoVGerente.getSucursal().getSelectedItem().toString());
        TipoVehiculo tipo = new TipoVehiculo(objetoVGerente.getTipo().getSelectedItem().toString());
        TransmisionVehiculo transmision = new TransmisionVehiculo(objetoVGerente.getTransmision().getSelectedItem().toString());

        Vehiculo nuevoVehiculo = new Vehiculo(
            placa, nChasis, modelo, kilometraje, marca, color,
            tipo, blindaje, transmision, cilindraje,
            seguro, estado, proveedor, sucursal
        );

        if (nuevoVehiculo.insertarVehiculo()) {
            JOptionPane.showMessageDialog(null, "Vehículo registrado correctamente.");
        } else {
            JOptionPane.showMessageDialog(null, "Error al registrar el vehículo.");
        }

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Kilometraje debe ser un número válido.");
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "Error al registrar vehículo: " + ex.getMessage());
    }
}


    private void mostrarFormularioLicencia() {
        FormularioLicencia formLicencia = new FormularioLicencia();
        formLicencia.setVisible(true);
        formLicencia.getBtnRegistrarLicencia().addActionListener(ev -> {
            Licencia nueva = new Licencia();
            nueva.setTipo(formLicencia.getTipoLincencia().getSelectedItem().toString());
            nueva.setFechaExpedicion(formLicencia.getTxtExpedicion().getText());
            nueva.setFechaVencimiento(formLicencia.getTxtVencimiento().getText());
            nueva.setEstado(formLicencia.getEstado().getSelectedItem().toString());
            if (nueva.insertarLicencia()) {
                JOptionPane.showMessageDialog(null, "Licencia registrada con éxito.");
                formLicencia.dispose();
            }
        });
    }

    private void mostrarFormularioCodigoPostal() {
        FormularioCodigoPostal formCP = new FormularioCodigoPostal();
        formCP.setVisible(true);
        formCP.getBtnCodigoPostal().addActionListener(ev -> {
            CodigoPostal nuevo = new CodigoPostal();
            nuevo.setIdCodigoPostal(formCP.getTxtcodigop().getText());
            nuevo.setPais(formCP.getTxtpais().getText());
            nuevo.setDepartamento(formCP.getTxtdepartamento().getText());
            nuevo.setCiudad(formCP.getTxtciudad().getText());
            if (nuevo.insertarCodigoPostal()) {
                JOptionPane.showMessageDialog(null, "Código Postal registrado.");
                formCP.dispose();
            }
        });
    }

    private void limpiarFormularioRegistro() {
        objetoVista.getTxtDocumento().setText("");
        objetoVista.getTxtNombre().setText("");
        objetoVista.getTxtTelefono().setText("");
        objetoVista.getTxtDireccion().setText("");
        objetoVista.getTxtCorreo().setText("");
        objetoVista.getTxtInfracciones().setText("");
        objetoVista.getTxtlicencia().setText("");
        objetoVista.getTipoD().setSelectedIndex(0);
        objetoVista.getTipoC().setSelectedIndex(0);
        objetoVista.getTxtCodigoP().setText("");
        objetoVista.getTxtContraseña().setText("");
    }

    private void mostrarFormularioAlquiler() {
    objetoVAlquiler = new FormularioAlquiler();
    objetoVAlquiler.setVisible(true);

    // Puedes precargar datos si lo deseas:
    objetoVAlquiler.getTxtID().setText(objetoCliente.getTxtIdCliente().getText());
    objetoVAlquiler.getTxtPlaca().setText(objetoCliente.getTxtPlaca().getText());

}
}
