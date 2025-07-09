package Controlador;

import Modelo.*;
import Vista.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Vehiculos;

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

    // Datos auxiliares
    String[] datos = new String[10];
    DefaultTableModel modelo;

    // Constructor
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
           // agregarMarcaNueva();
            agregarVehiculoNuevo();
        }
    }

    private void mostrarFormularioClientee() {
        objetoCliente = new FormularioCliente_1();
        objetoCliente.setVisible(true);

        // Cargar datos en los ComboBox del formulario
        MarcaVehiculo.cargarMarcasEnCombo(objetoCliente.getMarca());
        ColorVehiculoo.cargarEnCombo(objetoCliente.getColor());
        BlindajeVehiculo.cargarEnCombo(objetoCliente.getBlindaje());
        CilindrajeVehiculo.cargarEnCombo(objetoCliente.getCilindraje());
        Sucursal.cargarEnCombo(objetoCliente.getSucursal());
        TipoVehiculo.cargarEnCombo(objetoCliente.getTipo());
        TransmisionVehiculo.cargarEnCombo(objetoCliente.getTransmision());
        

        // Escuchadores
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
        EstadoVehiculo.cargarEnCombo(objetoVGerente.getDisponibilidad());
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
        String nChasis = objetoVGerente.getTxtNChasis().getText().trim();
        int kilometraje = Integer.parseInt(objetoVGerente.getTxtKm().getText().trim());

        // --- Marca ---
        String nombreMarca = objetoVGerente.getTxtMarca1().getText().trim();
        MarcaVehiculo marca = new MarcaVehiculo(nombreMarca);
        int idMarca = marca.getId();

        if (idMarca == -1) {
            if (marca.insertar()) {
                idMarca = marca.getId();
                JOptionPane.showMessageDialog(null, "Marca '" + nombreMarca + "' agregada automáticamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo registrar la nueva marca.");
                return;
            }
        }

        // --- Resto de datos relacionados ---
        int idColor = new ColorVehiculoo(objetoVGerente.getColor().getSelectedItem().toString()).getId();

        if (idColor == -1) {
            JOptionPane.showMessageDialog(null, "El color seleccionado no existe en la base de datos.");
            return; // Cancela el proceso
        }
        int idBlindaje = new BlindajeVehiculo(objetoVGerente.getBlindaje().getSelectedItem().toString()).getId();
        int idCilindraje = new CilindrajeVehiculo(objetoVGerente.getCilindraje().getSelectedItem().toString()).getId();
        EstadoVehiculo estado = new EstadoVehiculo(objetoVGerente.getDisponibilidad().getSelectedItem().toString());
        int idEstado = estado.getId();

        if (idEstado == -1) {
            if (estado.insertar()) {
                idEstado = estado.getId();
                JOptionPane.showMessageDialog(null, "Estado agregado automáticamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo registrar el nuevo estado.");
                return;
            }
        }
        int idProveedor = new ProveedorVehiculo(objetoVGerente.getProveedores().getSelectedItem().toString()).getId();
        int idSeguro = new SeguroVehiculo(objetoVGerente.getSeguro().getSelectedItem().toString()).getId();
        int idSucursal = new Sucursal(objetoVGerente.getSucursal().getSelectedItem().toString()).getId();
        int idTipoVehiculo = new TipoVehiculo(objetoVGerente.getTipo().getSelectedItem().toString()).getId();
        int idTransmision = new TransmisionVehiculo(objetoVGerente.getTransmision().getSelectedItem().toString()).getId();

        // --- Crear e insertar vehículo ---
        Vehiculos nuevoVehiculo = new Vehiculos(
            placa, nChasis, modelo, kilometraje, idMarca, idColor,
            idTipoVehiculo, idBlindaje, idTransmision, idCilindraje,
            idSeguro, idEstado, idProveedor, idSucursal
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
}
