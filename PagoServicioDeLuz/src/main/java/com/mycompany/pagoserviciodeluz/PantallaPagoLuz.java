/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pagoserviciodeluz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author daya
 */
public class PantallaPagoLuz extends JFrame{
     private JTextField txtNumeroServicio;
    private JList<Cliente> listaClientes;
    private DefaultListModel<Cliente> modeloLista;
    private JTextArea areaInformacion;
    private JPasswordField txtNumeroTarjeta;
    private JButton btnConfirmarPago;

    public PantallaPagoLuz() {

        // Configuramos la ventana principal.
        setTitle("Sistema de Pago de Luz");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(820, 540);
        setLocationRelativeTo(null);

        // Creamos los componentes de la pantalla.
        txtNumeroServicio = new JTextField();
        modeloLista = new DefaultListModel<>();
        listaClientes = new JList<>(modeloLista);

        areaInformacion = new JTextArea(12, 35);
        areaInformacion.setEditable(false);
        areaInformacion.setLineWrap(true);
        areaInformacion.setWrapStyleWord(true);

        txtNumeroTarjeta = new JPasswordField();
        txtNumeroTarjeta.setToolTipText(
                "Usa una tarjeta ficticia: 4111111111111111");

        btnConfirmarPago = new JButton("Confirmar pago");

        txtNumeroTarjeta.setEnabled(false);
        btnConfirmarPago.setEnabled(false);

        // Colocamos el campo del numero de servicio.
        JPanel campoBusqueda = new JPanel(new BorderLayout(5, 5));

        campoBusqueda.add(
                new JLabel("Numero de servicio"), BorderLayout.NORTH);

        campoBusqueda.add(
                txtNumeroServicio, BorderLayout.CENTER);

        JPanel busqueda = new JPanel(new BorderLayout());
        busqueda.add(campoBusqueda, BorderLayout.NORTH);

        // Colocamos la lista de clientes a la derecha.
        JPanel resultados = new JPanel(new BorderLayout(5, 5));

        resultados.add(
                new JLabel("Clientes coincidentes"), BorderLayout.NORTH);

        resultados.add(
                new JScrollPane(listaClientes), BorderLayout.CENTER);

        JPanel parteSuperior = new JPanel(new GridLayout(1, 2, 15, 0));
        parteSuperior.add(busqueda);
        parteSuperior.add(resultados);

        // Creamos la seccion para ingresar la tarjeta.
        JPanel tarjeta = new JPanel(new BorderLayout(8, 8));

        tarjeta.add(
                new JLabel("Numero de tarjeta"), BorderLayout.WEST);

        tarjeta.add(
                txtNumeroTarjeta, BorderLayout.CENTER);

        tarjeta.add(
                btnConfirmarPago, BorderLayout.EAST);

        // Aqui mostraremos el consumo y despues el recibo.
        JPanel parteInferior = new JPanel(new BorderLayout(8, 8));

        parteInferior.add(
                new JScrollPane(areaInformacion), BorderLayout.CENTER);

        parteInferior.add(tarjeta, BorderLayout.SOUTH);

        // Organizamos los elementos de la ventana.
        JPanel contenido = new JPanel(new BorderLayout(12, 12));

        contenido.setBorder(
                BorderFactory.createEmptyBorder(16, 16, 16, 16));

        contenido.add(
                new JLabel("PAGAR SERVICIO DE LUZ"), BorderLayout.NORTH);

        contenido.add(parteSuperior, BorderLayout.CENTER);
        contenido.add(parteInferior, BorderLayout.SOUTH);

        setContentPane(contenido);

        solicitarNumeroServicio();
    }

    // Solicitamos al usuario su numero de servicio.
    public void solicitarNumeroServicio() {

        areaInformacion.setText(
                "Ingresa un numero de servicio para consultar tu consumo.");
    }

    // Actualizamos la lista de clientes encontrados.
    public void mostrarClientesCoincidentes(List<Cliente> clientes) {

        modeloLista.clear();

        for (Cliente cliente : clientes) {
            modeloLista.addElement(cliente);
        }
    }

    // Mostramos los datos de consumo del cliente.
    public void mostrarDatosConsumo(Consumo consumo) {

        Cliente cliente = listaClientes.getSelectedValue();

        if (cliente == null || consumo == null) {
            return;
        }

        areaInformacion.setText(
                "DATOS DE CONSUMO\n\nCliente: " + cliente.getNombre()
                + "\nNumero de servicio: " + cliente.getNumeroServicio()
                + "\n" + consumo.obtenerDatosConsumo());

        if (consumo.estaPagado()) {
            txtNumeroTarjeta.setEnabled(false);
            btnConfirmarPago.setEnabled(false);
        }
    }

    // Habilitamos el campo para ingresar la tarjeta.
    public void solicitarNumeroTarjeta() {

        txtNumeroTarjeta.setEnabled(true);
        btnConfirmarPago.setEnabled(true);
    }

    // Mostramos el recibo en el espacio del consumo.
    public void mostrarRecibo(Pago pago) {

        if (pago == null) {
            return;
        }

        areaInformacion.setText(pago.obtenerDatosPago());

        txtNumeroTarjeta.setText("");
        txtNumeroTarjeta.setEnabled(false);
        btnConfirmarPago.setEnabled(false);
    }

    // Conectamos los eventos de la vista con el controlador.
    public void registrarEventos(ControlPagoLuz controlador) {

        // Buscamos clientes mientras se escribe.
        txtNumeroServicio.getDocument().addDocumentListener(
                new DocumentListener() {

            private void buscar() {

                areaInformacion.setText(
                        "Selecciona un cliente para ver su consumo.");

                txtNumeroTarjeta.setText("");
                txtNumeroTarjeta.setEnabled(false);
                btnConfirmarPago.setEnabled(false);

                mostrarClientesCoincidentes(
                        controlador.buscarClientesCoincidentes(
                                txtNumeroServicio.getText()));
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                buscar();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                buscar();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                buscar();
            }
        });

        // Mostramos el consumo al seleccionar un cliente.
        listaClientes.addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()
                    && listaClientes.getSelectedValue() != null) {

                Cliente cliente = listaClientes.getSelectedValue();

                if (controlador.seleccionarCliente(
                        cliente.getNumeroServicio()) != null) {

                    Consumo consumo = controlador.consultarDatosConsumo();

                    mostrarDatosConsumo(consumo);

                    if (consumo != null && !consumo.estaPagado()) {
                        solicitarNumeroTarjeta();
                    }
                }
            }
        });

        // Registramos el pago al presionar el boton.
        btnConfirmarPago.addActionListener(e -> {

            try {

                String numeroTarjeta =
                        new String(txtNumeroTarjeta.getPassword());

                controlador.registrarPago(numeroTarjeta);

                mostrarRecibo(controlador.generarRecibo());

            } catch (IllegalArgumentException | IllegalStateException ex) {

                mostrarMensajeError(ex.getMessage());
            }
        });
    }

    // Mostramos un mensaje cuando ocurre un error.
    public void mostrarMensajeError(String mensaje) {

        JOptionPane.showMessageDialog(
                this,
                mensaje,
                "Aviso",
                JOptionPane.WARNING_MESSAGE);
    }
}

