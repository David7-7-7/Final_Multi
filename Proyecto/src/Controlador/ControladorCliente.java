package Controlador;

import Modelo.Cliente;
import Modelo.CodigoPostal;
import Modelo.Ingresar;
import Modelo.Licencia;
import Vista.FormularioCliente;
import Vista.FormularioCliente_1;
import Vista.FormularioCodigoPostal;
import Vista.FormularioIngresar;
import Vista.FormularioLicencia;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ControladorCliente implements ActionListener {

    // Modelos
    Ingresar objetoIngresar;
    Cliente objetoModelo ;
    Licencia objetoLicencia ;
    CodigoPostal objetoCP ;

    // Formularios
    FormularioIngresar objetoVIngresar ;
    FormularioCliente objetoVista;
    FormularioCliente_1 objetoCliente;

    // Datos
    String[] datos = new String[10];
    DefaultTableModel modelo;

    public ControladorCliente() {   
        // Modelos
        objetoIngresar = new Ingresar();
        objetoModelo = new Cliente();
        objetoLicencia = new Licencia();
        objetoCP = new CodigoPostal();
        
        // Formularios
        objetoVIngresar = new FormularioIngresar();
        
        
        objetoVIngresar.setVisible(true);
        objetoVIngresar.getBotonIngresar().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Evento de login
        if (e.getSource() == objetoVIngresar.getBotonIngresar()) {
            String tipo = objetoVIngresar.getTipoUser().getSelectedItem().toString();
            String correo = objetoVIngresar.getTxtCorreo().getText();
            String contrasena = new String(objetoVIngresar.getTxtContraseña().getPassword());

            if (tipo.equals("Cliente")) {
                if (objetoIngresar.validarCliente(correo, contrasena)) {
                    JOptionPane.showMessageDialog(null, "¡Bienvenido Cliente!");
                    String idCliente = objetoModelo.obtenerIDporCorreoYContraseña(correo, contrasena);
                    //System.out.println("ID del cliente logueado: " + idCliente);
                    mostrarFormularioClientee();
                    objetoCliente.getTxtIdCliente().setText(idCliente);
                } else {
                    int respuesta = JOptionPane.showConfirmDialog(null,
                            "Cliente no registrado. ¿Desea registrarse ahora?",
                            "Registro requerido",
                            JOptionPane.OK_CANCEL_OPTION,
                            JOptionPane.INFORMATION_MESSAGE);
                    if (respuesta == JOptionPane.OK_OPTION) {
                        mostrarFormularioCliente();
                    }
                }
            }
        }

        // Botones del formulario de cliente
        if (objetoVista != null) {
            if (e.getSource() == objetoVista.getBtnInsertar()) {
                insertarCliente();
            } /*else if (e.getSource() == objetoCliente.getBtnConsulta()) {
                consultarClientes();
            }*/ else if (e.getSource() == objetoCliente.getBtnModificar()) {
                modificarCliente();
            }/* else if (e.getSource() == objetoCliente.getBtnBuscar()) {
                buscarCliente();
            }*/ else if (e.getSource() == objetoCliente.getBtnEliminar()) {
                eliminarCliente();
            }
        }
    }

    private void mostrarFormularioClientee() {
        objetoCliente = new FormularioCliente_1();
        objetoCliente.setVisible(true);

        //objetoCliente.getBtnInsertar().addActionListener(this);
        //objetoCliente.getBtnConsulta().addActionListener(this);
        objetoCliente.getBtnModificar().addActionListener(this);
        //objetoCliente.getBtnBuscar().addActionListener(this);
        objetoCliente.getBtnEliminar().addActionListener(this);
    }
    
    private void mostrarFormularioCliente() {
        objetoVista = new FormularioCliente();
        objetoVista.setVisible(true);

        objetoVista.getBtnInsertar().addActionListener(this);
        //objetoVista.getBtnConsulta().addActionListener(this);
        objetoCliente.getBtnModificar().addActionListener(this);
        //objetoVista.getBtnBuscar().addActionListener(this);
        objetoCliente.getBtnEliminar().addActionListener(this);
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

   /* private void consultarClientes() {
        modelo = (DefaultTableModel) objetoVista.getTabla().getModel();
        modelo.setRowCount(0); // Limpiar tabla
        modelo = objetoModelo.consultar();
        objetoVista.getTabla().setModel(modelo);
    }*/

    private void modificarCliente() {
        String ID_Cliente = objetoCliente.getTxtIdCliente().getText();
        objetoModelo.setDocumento(objetoCliente.getTxtDocumento1().getText());
        objetoModelo.setNombre(objetoCliente.getTxtNombre1().getText());
        objetoModelo.setTelefono(objetoCliente.getTxtTelefono1().getText());
        objetoModelo.setDireccion(objetoCliente.getTxtDireccion1().getText());
        objetoModelo.setCorreo(objetoCliente.getTxtCorreo1().getText());
        objetoModelo.setInfracciones(objetoCliente.getTxtInfracciones1().getText());
        objetoModelo.setIdLicencia(objetoCliente.getTxtlicencia1().getText());
        objetoModelo.setIdTipoDocumento(objetoCliente.getTipoD1().getSelectedItem().toString());
        objetoModelo.setIdTipoCliente(objetoCliente.getTipoC1().getSelectedItem().toString());
        objetoModelo.setIdCodigoPostal(objetoCliente.getTxtCodigoP1().getText());
        
        objetoCliente.getTxtIdCliente().setText(ID_Cliente);
        objetoModelo.modificar(ID_Cliente);
    }

    private void buscarCliente() {
        String id_Cliente = objetoCliente.getTxtIdCliente().getText();
        datos = objetoModelo.buscar(id_Cliente, datos);

        objetoCliente.getTxtDocumento1().setText(datos[0]);
        objetoCliente.getTxtNombre1().setText(datos[1]);
        objetoCliente.getTxtTelefono1().setText(datos[2]);
        objetoCliente.getTxtDireccion1().setText(datos[3]);
        objetoCliente.getTxtCorreo1().setText(datos[4]);
        objetoCliente.getTxtInfracciones1().setText(datos[5]);
        objetoCliente.getTxtlicencia1().setText(datos[6]);
        objetoCliente.getTipoD1().setSelectedItem(datos[7]);
        objetoCliente.getTipoC1().setSelectedItem(datos[8]);
        objetoCliente.getTxtCodigoP1().setText(datos[9]);
    }

    private void eliminarCliente() {
        String ID_Cliente = objetoCliente.getTxtIdCliente().getText();
        objetoModelo.eliminar(ID_Cliente);
        limpiarFormularioModificar();
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

    private void limpiarFormularioModificar() {
        objetoCliente.getTxtDocumento1().setText("");
        objetoCliente.getTxtNombre1().setText("");
        objetoCliente.getTxtTelefono1().setText("");
        objetoCliente.getTxtDireccion1().setText("");
        objetoCliente.getTxtCorreo1().setText("");
        objetoCliente.getTxtInfracciones1().setText("");
        objetoCliente.getTxtlicencia1().setText("");
        objetoCliente.getTipoD1().setSelectedIndex(0);
        objetoCliente.getTipoC1().setSelectedIndex(0);
        objetoCliente.getTxtCodigoP1().setText("");
    }
}
