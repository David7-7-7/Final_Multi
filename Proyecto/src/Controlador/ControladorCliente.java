package Controlador;

import Modelo.*;
import Vista.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ControladorCliente implements ActionListener {

    // Modelos
    Ingresar objetoIngresar;
    Cliente objetoModelo;
    Licencia objetoLicencia;
    CodigoPostal objetoCP;
    Vehiculos objetoVehiculo;
    Empleado objetoEmpleado;

    // Formularios
    FormularioIngresar objetoVIngresar;
    FormularioCliente objetoVista;
    FormularioCliente_1 objetoCliente;
    FormularioGerente objetoVGerente;

    // Datos
    String[] datos = new String[10];
    DefaultTableModel modelo;

    public ControladorCliente() {
        objetoIngresar = new Ingresar();
        objetoModelo = new Cliente();
        objetoLicencia = new Licencia();
        objetoCP = new CodigoPostal();
        objetoVehiculo = new Vehiculos();

        objetoVIngresar = new FormularioIngresar();
        objetoVIngresar.setVisible(true);
        objetoVIngresar.getBotonIngresar().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == objetoVIngresar.getBotonIngresar()) {
            String tipo = objetoVIngresar.getTipoUser().getSelectedItem().toString();
            String correo = objetoVIngresar.getTxtCorreo().getText();
            String contrasena = new String(objetoVIngresar.getTxtContraseña().getPassword());

            if (tipo.equals("Cliente")) {
                if (objetoIngresar.validarCliente(correo, contrasena)) {
                    JOptionPane.showMessageDialog(null, "¡Bienvenido Cliente!");
                    String idCliente = objetoModelo.obtenerIDporCorreoYContraseña(correo, contrasena);
                    mostrarFormularioClientee();
                    objetoCliente.getTxtIdCliente().setText(idCliente);
                } else {
                    int respuesta = JOptionPane.showConfirmDialog(null,
                            "Cliente no registrado. ¿Desea registrarse ahora?",
                            "Registro requerido", JOptionPane.OK_CANCEL_OPTION,
                            JOptionPane.INFORMATION_MESSAGE);
                    if (respuesta == JOptionPane.OK_OPTION) {
                        mostrarFormularioCliente();
                    }
                }
            } else if (tipo.equals("Empleado")) {
                if (objetoIngresar.validarEmpleado(correo, contrasena)) {
                    objetoEmpleado = new Empleado();
                    String idEmpleado = objetoEmpleado.obtenerIDporCorreoYContraseña(correo, contrasena);
                    JOptionPane.showMessageDialog(null, "¡Bienvenido Empleado!");
                    mostrarFormularioEmpleado();
                } else {
                    JOptionPane.showMessageDialog(null, "Credenciales incorrectas para empleado.");
                }
            }
        }

        if (objetoVista != null && e.getSource() == objetoVista.getBtnInsertar()) {
            insertarCliente();
        }

        if (objetoVGerente != null && e.getSource() == objetoVGerente.getBotonAgregarG()) {
            agregarMarcaNueva();
        }

        if (objetoVGerente != null && e.getSource() == objetoVGerente.getBotonAgregarVehiculo()) {
            agregarVehiculoNuevo();
        }
    }

    private void mostrarFormularioClientee() {
        objetoCliente = new FormularioCliente_1();
        objetoCliente.setVisible(true);
        MarcaVehiculo.cargarMarcasEnCombo(objetoCliente.getMarca());
        objetoCliente.getBtnModificar().addActionListener(this);
        objetoCliente.getBtnEliminar().addActionListener(this);
        objetoCliente.getBotonAveriguar().addActionListener(this);   
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
        objetoVGerente.getBotonAgregarVehiculo().addActionListener(this);
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

    private void agregarMarcaNueva() {
        if (objetoVGerente != null) {
            String nombreMarca = objetoVGerente.getTxtMarca1().getText().trim();

            if (!nombreMarca.isEmpty()) {
                MarcaVehiculo nueva = new MarcaVehiculo(nombreMarca);

                if (nueva.getId() == -1) {
                    if (nueva.insertar()) {
                        JOptionPane.showMessageDialog(null, "Marca agregada exitosamente.");
                        objetoVGerente.getTxtMarca1().setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al insertar la marca.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "La marca ya existe en el sistema.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese el nombre de la marca.");
            }
        }
    }

   private void agregarVehiculoNuevo() {
    try {
        String placa = objetoVGerente.getTxtPlaca1().getText().trim();
        String modelo = objetoVGerente.getTxtModelo1().getText().trim();
        int kilometraje = Integer.parseInt(objetoVGerente.getTxtKm().getText().trim());

        int idMarca = new MarcaVehiculo(objetoVGerente.getTxtMarca1().getText()).getId();
        int idColor = new ColorVehiculo(objetoVGerente.getColor().getSelectedItem().toString()).getId();
        int idBlindaje = new BlindajeVehiculo(objetoVGerente.getBlindaje().getSelectedItem().toString()).getId();
        int idCilindraje = new CilindrajeVehiculo(objetoVGerente.getCilindraje().getSelectedItem().toString()).getId();
        int idEstado = new EstadoVehiculo(objetoVGerente.getComboEstado().getSelectedItem().toString()).getId();
        int idProveedor = new ProveedorVehiculo(objetoVGerente.getComboProveedor().getSelectedItem().toString()).getId();
        int idSeguro = new SeguroVehiculo(objetoVGerente.getComboSeguro().getSelectedItem().toString()).getId();
        int idSucursal = new Sucursal(objetoVGerente.getComboSucursal().getSelectedItem().toString()).getId();
        int idTipoVehiculo = new TipoVehiculo(objetoVGerente.getComboTipoVehiculo().getSelectedItem().toString()).getId();
        int idTransmision = new TransmisionVehiculo(objetoVGerente.getComboTransmision().getSelectedItem().toString()).getId();

        Vehiculo nuevoVehiculo = new Vehiculo(placa, modelo, kilometraje, idMarca, idColor, idBlindaje,
                                              idCilindraje, idEstado, idProveedor, idSeguro,
                                              idSucursal, idTipoVehiculo, idTransmision);

        if (nuevoVehiculo.insertar()) {
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
}
